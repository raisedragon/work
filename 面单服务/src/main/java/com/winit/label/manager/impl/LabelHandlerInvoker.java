package com.winit.label.manager.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winit.commons.FtpUtil;
import com.winit.commons.StatusCode;
import com.winit.exception.LabelBusinessException;
import com.winit.exception.LabelException;
import com.winit.exception.LabelSystemException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.LabelHandler.Result;
import com.winit.label.manager.LabelHandlerFactory;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.Label;
import com.winit.label.model.Label.StatuType;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Product;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.label.service.DeliveryWayService;
import com.winit.label.service.LabelService;
import com.winit.label.support.ConfigUtil;
/**
 * @author longsheng.wang
 */
@Service
public class LabelHandlerInvoker 
{

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= 1L;
	
	private static String KEY_FTP_HOST = "WT_LABLE_FTP_HOST";
	private static String KEY_FTP_PORT = "WT_LABLE_FTP_PORT";
	private static String KEY_FTP_ACCOUNT = "WT_LABLE_FTP_ACCOUNT";
	private static String KEY_FTP_PASSWORD = "WT_LABLE_FTP_PASSWORD";
	private static String KEY_FTP_PATH = "WT_LABLE_FTP_PATH";
	private static String KEY_LABEL_URL_PREFIX = "WT_LABLE_LABEL_URL_PREFIX";
	
	@Autowired
	private LabelHandlerFactory labelHandlerFactory;
	
	@Autowired
	private LabelService labelService;
	@Autowired
	DeliveryWayService deliveryWayService;
	
	private String currentDirectry = "";
	
	
	FtpUtil ftpUtil = null;
	

	public ResponseMessage invoke(RequestMessage requestMessage)
	{
	
		ResponseMessage response = new ResponseMessage();
		
		
		try
		{
			checkRequestMessage(requestMessage);
			String documentNo = requestMessage.getDocumentNo();
			String logisticsCode = requestMessage.getLogisticsCode();
			response.setDocumentNo(documentNo);
			
			DeliveryWay deliveryWay =  deliveryWayService.findByCode(logisticsCode);
			
			//delivery way not fount
			if(deliveryWay==null){
				response.setStatusCode(101);
				response.setMessage("Label delivery way specify by logistic's code " 
					+ logisticsCode
					+ " not found");
				return response;
			}
			
			LabelHandler labelHandler = labelHandlerFactory.getHandler(deliveryWay);
			
			//label handler not found
			if (labelHandler == null)
			{
				response.setStatusCode(102);
				response.setMessage("Label generate service specify by logistic's code " 
					+ logisticsCode
					+ " not found");
				return response;
			}
			Label label = labelService.findByDocumentNo(documentNo);
			
			//label exists
			if (label != null)
			{
				//require generate a new one anymore
				if (requestMessage.isRequiredNew())
				{
					if(labelHandler.isIdempotent()){
						throw new LabelBusinessException("Label has already handle before, the type of this lable is not support rehandle. ");
					}
					Result result = labelHandler.handle(requestMessage,deliveryWay);
					label.setFileCode(result.getBase64Code());
					label.setTrackingNo(result.getTrackNo());
					label.setStatus(StatuType.Got);
				}else{
					//not require generate a new one, a valid one already exists.
					if(StringUtils.isNotEmpty(label.getFileCode()) && label.getStatus()==StatuType.Upload){
						String fullfilename = getUploadFileName(label, deliveryWay);
						response.setTrackingNo(label.getTrackingNo());
						response.setFilePath(fullfilename);
						return response;
					}
				}
			}
			else //label not exists, create a new one
			{
				Result result = labelHandler.handle(requestMessage,deliveryWay);
				label = new Label();
				label.setCreated(new Date());
				label.setDocumentNo(documentNo);
				label.setFileCode(result.getBase64Code());
				label.setTrackingNo(result.getTrackNo());
				label.setStatus(StatuType.Got);
			}
			
			String fullfilename = getUploadFileName(label, deliveryWay);
			
			//未上传，进行上传
			if(label.getStatus()!=StatuType.Upload){
				boolean uploadSuccess = uploadLabel(fullfilename, label.getFileCode());
				
				if(!uploadSuccess){
					label.setStatus(StatuType.Got);
					save(label);
					throw new LabelSystemException("Upload label failure");
				}
			}
			
			label.setStatus(StatuType.Upload);
			String filePath = ConfigUtil.getValue(KEY_LABEL_URL_PREFIX)+fullfilename;
			response.setFilePath(filePath);
			save(label);
			response.setStatusCode(StatusCode.SUCCESS);
			response.setTrackingNo(label.getTrackingNo());
		}
		catch (LabelBusinessException exception)
		{
			response.setStatusCode(StatusCode.BUSINESS_EXCEPTION);
			response.setMessage(exception.getMessage());
		}
		catch (LabelSystemException exception)
		{
			response.setStatusCode(StatusCode.SYSTEM_EXCEPTION);
			response.setMessage(exception.getMessage());
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			response.setStatusCode(StatusCode.OTHER_EXCEPTION);
			response.setMessage(ExceptionUtils.getStackTrace(exception));
		}
		
		return response;
	}
	
	
	/**
	 * 获取面单上传的文件路径名
	 * @param warehourse 
	 * @param label
	 * @param deliveryWay
	 * @return
	 */
	private String getUploadFileName(Label label,DeliveryWay deliveryWay){
		String path = getStorePath(label.getCreated());
		String fullfilename = path+"/"+label.getDocumentNo()+"."+deliveryWay.getExtension();
		return fullfilename;
	}
	
	/**
	 * 保存面单
	 * @param label
	 * @param isNew
	 */
	private void save(Label label){
		if(label.getId()==null){
			labelService.addLabel(label);
		}else{
			labelService.updateLabel(label);
		}
	}

	/**
	 * 校验消息有效性
	 * 
	 * @param request
	 */
	private void checkRequestMessage(RequestMessage request)
	{
		String msg = "Request Message is invalid: ";
		if(request==null){
			new LabelException(msg+"request message can not be empty");
		}
		
		if(StringUtils.isEmpty(request.getDocumentNo())){
			new LabelException(msg+"documentNo can not be empty");
		}
		
		if(request.getLogisticsCode()==null){
			new LabelException(msg+"logistic code can not be empty");
		}
		
		if(request.getConsignee()==null){
			new LabelException(msg+"consignee can not be null");
		}
		
		if(request.getConsignee().getPostcode()==null){
			request.getConsignee().setPostcode("");
		}else{
			request.getConsignee().setPostcode(request.getConsignee().getPostcode().toUpperCase());
		}
		
		if(request.getConsignee().getCountryCode().equals("UK")) {
			request.getConsignee().setCountryCode("GB");
		}
		
		
		
		if(0==request.getWeight()){
			double pkgWeight = 0;
			for(Product product:request.getProducts()){
				pkgWeight = BigDecimal.valueOf(pkgWeight).add(BigDecimal.valueOf( product.getQty()).multiply(BigDecimal.valueOf(product.getWeight()))).doubleValue();
			}
			request.setWeight(pkgWeight);
		}
	}
	
	/**
	 * 上传面单
	 * @param filename
	 * @param code
	 * @return
	 */
	private boolean uploadLabel(String filename,String code) {
		try{
			ensureFtpInitialize(false);
			String storePath = FilenameUtils.getFullPath(filename);
			for(int i=0;i<10;i++){
				try{
	//				ftpUtil.uploadLable(uploadFile, Base64.decodeBase64(lable.getFileCode()));
					
					
					if(!currentDirectry.equals(storePath)){
						//try change directory
						boolean changeSuccess = ftpUtil.getFtpClient().changeWorkingDirectory(storePath);
						
						if(!changeSuccess){
							//try create directory
							boolean createSuccess = ftpUtil.getFtpClient().makeDirectory(storePath);
							if(createSuccess){
								changeSuccess = ftpUtil.getFtpClient().changeWorkingDirectory(storePath);
							}
						}
						//if change directory success, save it.
						if(changeSuccess){
							currentDirectry=storePath;
						}else{
							continue;
						}
					}
					
					boolean fileUpload = ftpUtil.uploadLable(filename,
							Base64.decodeBase64(code.getBytes("utf-8")));
					
					return fileUpload;
				}catch(Exception ex){
					if(ex instanceof IOException){
						Thread.sleep(1000);
						ensureFtpInitialize(true);
					}
					
					throw new LabelSystemException("FTP error: " + ex.getMessage());
				}
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	/**
	 * 确保FTP Client已初始化
	 * @param reCreate
	 * @throws Exception
	 */
	private void ensureFtpInitialize(boolean reCreate) throws Exception{
		if(!reCreate){
			if(ftpUtil!=null){
				return;
			}
		}
		
		if(ftpUtil!=null){
			try{
				ftpUtil.closeServer();
			}catch(Exception e){
				
			}
		}
		
		String ip = ConfigUtil.getValue(KEY_FTP_HOST);
		int port = ConfigUtil.getIntValue(KEY_FTP_PORT, 21);
		String account = ConfigUtil.getValue(KEY_FTP_ACCOUNT);
		String password = ConfigUtil.getValue(KEY_FTP_PASSWORD);
		String path = ConfigUtil.getValue(KEY_FTP_PATH);
		ftpUtil = new FtpUtil(ip, port, account, password, path);
	}
	
	private String getStorePath(Date date){
		Long millis = date.getTime();
		String path = ConfigUtil.getValue(KEY_FTP_PATH);
		if(!path.endsWith("/")){
			path +="/";
		}
		return path + DateFormatUtils.format(millis, "yyyy/MM/dd");
	}
}

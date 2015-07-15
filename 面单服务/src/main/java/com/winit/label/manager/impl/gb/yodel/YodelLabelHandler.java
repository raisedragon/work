package com.winit.label.manager.impl.gb.yodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.exception.LabelBusinessException;
import com.winit.label.db.SequenceGenerator;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

/**
 * 生成UPS的面单，返回面单的Base64位编码
 * @author longsheng.wang
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class YodelLabelHandler implements LabelHandler
{
	
	private static final String		YODEL_LABEL_REPORT			= "uk_yodel_lable.jrxml";
	
	private static final int ADDRESS_LEN  = 35;
	
	@Autowired
	private YodelPropertiesService yodelPropertiesService;
	
	@Autowired
	private YodelDestStationService yodelDestStationService;
	
	@Autowired
	private YodelDestPrdServicesService yodelDestPrdServicesService;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@Autowired
	private YodelDestExceptionService yodelDestExceptionService;
	
	
	@Autowired
	private YodelReamusIdService yodelReamusIdService;
	@Autowired
	private InUseredService inUseredService;
	
	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		
		String currentVersion = yodelPropertiesService.getCurrentGazetteerVersion();
		String version =  yodelPropertiesService.getCurrentGazetteerVersion();
		Service service = getService(requestMessage, deliveryWay,version);
		assertNotNull(service,"Product & service not supported at this postcode [postal:"+requestMessage.getConsignee().getPostcode() +",version:"+version+"]");
		YodelService yodelService = service.getService();
		YodelReamusId yodeReamusID = service.getReamusId();
		String addressLine2 = "";
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress2())){
			addressLine2+=requestMessage.getConsignee().getAddress2();
		}
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress3())){
			addressLine2+=" "+requestMessage.getConsignee().getAddress3();
		}
		
		String licencePlateNumber = generateLicencePlateNumber();
		//save tracking no
//		exWarehouse.setTrackingNo(licencePlateNumber);
//		exWarehouse.saveEx(trxName);
		
		String eyeReadableLicencePlateNumber  = generateEyeReadableLicencePlateNumber(licencePlateNumber);
		// 生成面单code 返回
		Map param = new HashMap();
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("productLine1", yodelService.getProductLine1());
		param.put("productLine2", yodelService.getProductLine2());
		param.put("meter", YodelConfig.METER());
		param.put("from", YodelConfig.LABLE_FROM());
		param.put("orgName", ensureNotNull(requestMessage.getConsignee().getName()).toUpperCase());
		param.put("orgTel", ensureNotNull(requestMessage.getConsignee().getPhone()));
		param.put("departmentName", "");
		param.put("addressLine1", checkLength(ensureNotNull(requestMessage.getConsignee().getAddress1()),ADDRESS_LEN,"Consignee address line1 is too long").toUpperCase());
		param.put("addressLine2",checkLength(addressLine2,ADDRESS_LEN,"Consignee address line2 is too long").toUpperCase());
		param.put("city", ensureNotNull(requestMessage.getConsignee().getCity()).toUpperCase());
		param.put("country", ensureNotNull(finalCountryCode(requestMessage.getConsignee().getCountryCode())).toUpperCase());
		param.put("postcode", ensureNotNull(requestMessage.getConsignee().getPostcode()));
		param.put("serviceCode", yodelService.getServiceCode());
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("locationName", yodeReamusID.getLocationName());
		param.put("version", currentVersion);
		param.put("routingBarcode", generateRoutingBarcode(requestMessage, yodelService));
		param.put("licencePlateBarcode", licencePlateNumber);
		param.put("eyeReadableLicencePlate", eyeReadableLicencePlateNumber);
		
//		Class clazz = Class.forName("org.compiere.report.ReportStarter");
//		Object obj = clazz.newInstance();
//		Method method = obj.getClass().getDeclaredMethod("generatePdfAsBase64", String.class, Map.class);
//		return (String) method.invoke(obj, YODEL_LABEL_REPORT, param);
		
		String code = JasperReportUtils.generatePdfAsBase64(YODEL_LABEL_REPORT, param);
		return new Result(code, licencePlateNumber);
		
		
	}
	
	
	/**
	 * generate Eye-readableLicence Plate Value <br/>
	 * (J)JD00 022 123 1234 1234 
	 * @return
	 */
	public  String generateRoutingBarcode(RequestMessage requestMessage,YodelService service){
		String barcode = 
				"2L"+ 
				finalCountryCode(requestMessage.getConsignee().getCountryCode())+
				finalPostal(requestMessage.getConsignee().getPostcode())+
				"+"+
				service.getProductCode()+
				service.getDateCode()+
				service.getTimeCode()+
				service.getFeatureId();
				
		return barcode;
	}
	
	private String ensureNotNull(String str){
		if(str==null){
			return "";
		}
		return str;
	}
	
	private String finalPostal(String postal){
		return postal.replaceAll("\\s", "");
	}
	
	/**
	 * generate Eye-readableLicence Plate Value <br/>
	 * (J)JD00 022 123 1234 1234 
	 * @return
	 */
	public  String generateEyeReadableLicencePlateNumber(String licencePlateNumber){
		String lpn = "("+licencePlateNumber.substring(0, 1)+")"
				+licencePlateNumber.substring(1,5)
				+" "
				+licencePlateNumber.substring(5,8)
				+" "
				+licencePlateNumber.substring(8,11)
				+" "
				+licencePlateNumber.substring(11,15)
				+" "
				+licencePlateNumber.substring(15);
		return lpn;
	}
	

	
	
	/**
	 * Licence Plate Barcode Value <br/>
	 * JJD0002298765000001
	 * @return
	 */
	public  String generateLicencePlateNumber(){
		String prefix = YodelConfig.LICENCE_PLATE_NUMBERS_PREFIX();
		String sequence = getTrackSeqNextVal();
		checkLength(sequence, 6, "licence plate numbers exceed allowed range.");
		String lpn = prefix+YodelConfig.METER()+StringUtils.leftPad(sequence,6,"0");
		return lpn;
	}
	
	/**
	 * 获取轨迹自增长序列号
	 * 1-1,000,000
	 * @return
	 */
	private String getTrackSeqNextVal(){
		
		long seq = sequenceGenerator.nextVal("TRACKNO_SEQ_KEY");
		return String.valueOf(seq);
//		
//		String seq = DB.getSQLValueString(null, "select WT_YODEL_TRACKNO_SEQ.nextval from dual");
//		return seq;
	}
	
	private String finalCountryCode(String countryCode){
		if("UK".equals(countryCode)){
			return "GB";
		}
		return countryCode;
	}
	
//	private String getLocationName(Properties ctx,String trxName,MOWMSExWarehouse exWarehouse,MYodelService service){
//		
//		List<MOWMSExWareHouseProduct> plist = exWarehouse.getChildren(MOWMSExWareHouseProduct.Table_Name,
//				"", "");
//		BigDecimal packingWeight = new BigDecimal(0);
//		for (int j = 0; j < plist.size(); j++)
//		{
//			MOWMSExWareHouseProduct mExWarehouseProduct = plist.get(j);
//			I_M_Product prd = mExWarehouseProduct.getM_Product();
//			packingWeight = packingWeight.add(prd.getWeight().multiply(mExWarehouseProduct.getQty()));
//		}
//		
//		String productCode  = service.getProductCode();
//		String country = finalCountryCode(exWarehouse.getCountryName());
//		String postcode = exWarehouse.getPostal();
//		MYodelDestStation destStation = MYodelDestStation.getByProductCodeAndVersion(ctx, trxName,country, postcode ,packingWeight.doubleValue(),productCode,service.getVersion());
//		assertNotNull(destStation, "Can not found Destination Station by[country:"+country+",postal:"+postcode+",productCode:"+productCode+",version:"+service.getVersion()+"]");
//		String serviceCtrReamusID = destStation.getServiceCtrReamusID();
//		MYodelReamusID reamusID = MYodelReamusID.getByReamusIdAndVersion(ctx, trxName, serviceCtrReamusID, service.getVersion());
//		assertNotNull(reamusID,"Can not found ReamusID by[reamusID:"+serviceCtrReamusID+",version:"+service.getVersion()+"]");
//		return reamusID.getLocationName();
//	}
	
	public <T> T assertNotNull(T obj,String msg){
		if(obj==null){
			throw new LabelBusinessException(msg);
		}
		return obj;
	}
	
	
	private String checkLength(String str,int len,String msg){
		if(str==null){
			return str;
		}
		if(str.length()>len){
			throw new LabelBusinessException(msg);
		}
		return str;
	}
	
	
	public static class Service{
		private YodelService service;
		private YodelReamusId reamusId;
		
		public Service(YodelService service, YodelReamusId reamusId)
		{
			this.service = service;
			this.reamusId = reamusId;
		}
		public YodelService getService()
		{
			return service;
		}
		public YodelReamusId getReamusId()
		{
			return reamusId;
		}
		
		
	}
	
	public Service getService(RequestMessage requestMessage, DeliveryWay deliveryWay,String version){
		String serviceType = deliveryWay.getServiceCode();
		
//		List<MOWMSExWareHouseProduct> plist = exWarehouse.getChildren(MOWMSExWareHouseProduct.Table_Name,
//				"", "");
//		BigDecimal packingWeight = new BigDecimal(0);
//		for (int j = 0; j < plist.size(); j++)
//		{
//			MOWMSExWareHouseProduct mExWarehouseProduct = plist.get(j);
//			I_M_Product prd = mExWarehouseProduct.getM_Product();
//			packingWeight = packingWeight.add(prd.getWeight().multiply(mExWarehouseProduct.getQty()));
//		}
		
		String country = requestMessage.getConsignee().getCountryCode();
		String postcode = requestMessage.getConsignee().getPostcode();
		
		List<YodelDestStation> destStations = yodelDestStationService.getByPostCode(country,postcode , version);
		if(destStations==null||destStations.size()==0){
			assertNotNull(null,"Product & service not supported at this postcode [country:"+country+",postal:"+postcode+",version:"+version+"]");
		}
		for(YodelDestStation destStation:destStations){
			
			List<YodelDestPrdServices> destPrdServiceses = yodelDestPrdServicesService.getByServiceCtrReamusID(destStation.getServiceCtrReamusId(),destStation.getProductCode(),version);
			if(destPrdServiceses==null||destPrdServiceses.size()==0){
				continue;
			}
			for(YodelDestPrdServices destPrdServices:destPrdServiceses){
				String allowed = destPrdServices.getAllowed();
				if("Y".equals(allowed)){
					//
				}else{
					YodelDestException destException = yodelDestExceptionService.getByProductCodeAndVersion(destPrdServices.getProductCode(),destPrdServices.getFeatureCode(),version,country,postcode );
					if(destException==null){
						continue;
					}
				}
				YodelService service = inUseredService.getYodelService(version, serviceType, destPrdServices.getProductCode(), destPrdServices.getFeatureCode(),requestMessage.getWeight());
				
				if(service==null){
					continue;
				}
				YodelReamusId reamusID = yodelReamusIdService.getByReamusIdAndVersion(destPrdServices.getServiceCtrReamusId(), service.getVersion());
				if(reamusID==null){
					continue;
				}
				
				return new Service(service, reamusID);
			}
			
		}
		return null;
		
	}

	public static void main(String[] args) throws IOException
	{
//		Adempiere.startup(true);
//		FileOutputStream fos = new FileOutputStream("D:/work/MyWork/UK/Yodel/Lable/temp/a.txt");
//		YodelLable lable = new YodelLable();
//		
//		List<String> postcodes = getPostCode();
//		for(String postcode:postcodes){
//			List<Service> services = lable.getService2(Env.getCtx(), null, postcode, "126");
//			List<String> strs = new ArrayList<String>();
//			strs.add("postcode:"+postcode);
//			strs.add("size:"+services.size());
//			for(Service service:services){
//				strs.add(service.getService().getServiceCode());
//			}
//			fos.write(StringUtils.join(strs.iterator(),",").getBytes());
//		}
//		
//		fos.close();
		
	}
	
	static List<String> getPostCode(){
		List<String> list =  new ArrayList<String>();
		list.add("BT67 0BS");
		return list;
	}



	@Override
	public boolean isIdempotent()
	{
		return true;
	}
	
}

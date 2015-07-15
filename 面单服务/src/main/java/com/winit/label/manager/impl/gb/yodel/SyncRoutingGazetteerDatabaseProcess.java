package com.winit.label.manager.impl.gb.yodel;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.commons.FtpUtil;
import com.winit.commons.spring.AppUtil;
import com.winit.exception.LabelException;
import com.winit.label.support.ConfigUtil;


/**
 * 
 * Yodel demostic 
 * <br/>
 * 同步Gazetteer的数据到系统数据库中
 * 
 * @author longsheng.wang
 *
 */
public class SyncRoutingGazetteerDatabaseProcess implements InterruptableJob
{
	
	public static Logger log = LoggerFactory.getLogger(SyncRoutingGazetteerDatabaseProcess.class);
	
	private static boolean lock=true;
	
	YodelPropertiesService yodelPropertiesService = (YodelPropertiesService) AppUtil.getBean(YodelPropertiesService.class);
	YodelServiceService yodelServiceService= (YodelServiceService) AppUtil.getBean(YodelServiceService.class);
	YodelDestExceptionService yodelDestExceptionService = (YodelDestExceptionService)AppUtil.getBean(YodelDestExceptionService.class);
	YodelDestPrdServicesService yodelDestPrdServicesService= (YodelDestPrdServicesService) AppUtil.getBean(YodelDestPrdServicesService.class);
	YodelDestStationService yodelDestStationService= (YodelDestStationService) AppUtil.getBean(YodelDestStationService.class);
	YodelReamusIdService yodelReamusIdService= (YodelReamusIdService) AppUtil.getBean(YodelReamusIdService.class);

	
	protected String doIt() throws Exception
	{
		
		if(!lock){
			return "The last task is executing";
		}
		lock=false;
		try{
			
			Set<String> zips = new HashSet<String>();
			
			FtpUtil client = null;
			try
			{
				client = new FtpUtil(YodelConfig.GAZETTEER_FTP_HOST(), YodelConfig.GAZETTEER_FTP_PORT(),
						YodelConfig.GAZETTEER_FTP_USERNAME(), YodelConfig.GAZETTEER_FTP_PASSWORD(),
						YodelConfig.GAZETTEER_FTP_PATH());
				List<String> files = client.getFileList("");
				for (int i = 0; i < files.size(); i++)
				{
					String fileName = files.get(i);
					//只处理特定文件名的文件
					if (!fileName.matches(YodelConfig.GAZETTEER_ZIP_FILE_NAME))
					{
						continue;
					}

					log.error( "Retrieve file <" + fileName + "> ......");
					
					//通过文件名，计算版本号
					String version = getVersionByFileName(fileName);
					// 如果该版本在已管理过，则跳过
					if (StringUtils.isNotEmpty(yodelPropertiesService.getValue("VERSION", version, null)))
					{
						log.error( "Gazetteer version <" + version + "> already handled, skip.");
						continue;
					}
					//将文件从FTP下载到本在临时文件夹
					String localOutFilePath = ConfigUtil.getValue("WT_LABLE_DHL_PDFPATH") + File.separator + fileName;
					FileOutputStream fos = null;
					try
					{
						File localOutFile = new File(localOutFilePath);
						if(localOutFile.exists()){
							localOutFile.delete();
						}
						fos = new FileOutputStream(localOutFilePath, true);
						boolean retrieveSuccess = client.getFtpClient().retrieveFile(fileName, fos);
						if(retrieveSuccess){
							zips.add(localOutFilePath);
						}else{
							log.error( "Retrieve file <" + fileName + "> failure");
						}
					}
					catch (Exception e)
					{
						log.error(e.getMessage());
						throw new LabelException(e);
					}
					finally
					{

						if (fos != null)
						{
							fos.close();
						}
					}
				}

			}
			finally
			{
				if (client != null)
				{
					try{
						client.closeServer();
					}catch(Exception e){
						//
					}
				}
			}
			
			//read file content
			for(String zip:zips){
				String version = getVersionByFileName(zip);
				try{
					read(zip, version);
				}catch(Exception e){
					log.error(e.getMessage());
				}
				
			}
			return "@success@";
			
		}finally{
			lock=true;
		}
	}

	private String getVersionByFileName(String filename){
		int versionIndexEnd = filename.lastIndexOf(".");
		int versionIndexBegin = versionIndexEnd -3;
		String version = filename.substring(versionIndexBegin,versionIndexEnd);
		return version;
	}
	
	
	private void readActivate(ZipFile zipFile,String version) throws IOException{
		ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_ACTIVATE_FILE_NAME_TEMPLATE, version));
		InputStream entryIs = zipFile.getInputStream(entry);

		log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
		try
		{
			BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

			String line = null;
			while ((line = bf.readLine()) != null)
			{
				if (StringUtils.isNotBlank(line))
				{
					YodelProperties yodelProperties = new YodelProperties();
					yodelProperties.setKey("ACTIVATE");
					yodelProperties.setValue(line);
					yodelProperties.setVersion(version);
					yodelPropertiesService.add(yodelProperties);
					break;
				}
			}
		}
		finally
		{
			if (null != entryIs)
			{
				entryIs.close();
			}
		}
		log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
	}
	
	private void readVersion(ZipFile zipFile,String version) throws  IOException{
		ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_VERSION_FILE_NAME_TEMPLATE, version));
		InputStream entryIs = zipFile.getInputStream(entry);

		log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
		try
		{
			BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

			String line = null;
			while ((line = bf.readLine()) != null)
			{
				if (StringUtils.isNotBlank(line))
				{
					YodelProperties yodelProperties = new YodelProperties();
					yodelProperties.setKey("VERSION");
					yodelProperties.setValue(line);
					yodelProperties.setVersion(version);
					yodelPropertiesService.add(yodelProperties);
					break;
				}
			}
		}
		finally
		{
			if (null != entryIs)
			{
				entryIs.close();
			}
		}
		log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
		
	}
	private void readService(ZipFile zipFile,String version) throws  IOException{
		ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_SERVICE_FILE_NAME_TEMPLATE, version));
		InputStream entryIs = zipFile.getInputStream(entry);

		log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
		try
		{

			BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

			String line = null;
			// new MYodelService().de
			while ((line = bf.readLine()) != null)
			{
				if (StringUtils.isNotBlank(line))
				{
					saveService(line, version);
				}
			}
		}
		finally
		{
			if (null != entryIs)
			{
				entryIs.close();
			}
		}
		log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
	}
	
	private void readReamusId(ZipFile zipFile,String version) throws  IOException{
		ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_REAMUSID_FILE_NAME_TEMPLATE, version));
		InputStream entryIs = zipFile.getInputStream(entry);

		log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
		try
		{

			BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

			String line = null;
			line = bf.readLine();
			while ((line = bf.readLine()) != null)
			{
				if (StringUtils.isNotBlank(line))
				{
					saveReamusID(line, version);
				}
			}
		}
		finally
		{
			if (null != entryIs)
			{
				entryIs.close();
			}
		}
		log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
	}
	
	private void readDestinationStation(ZipFile zipFile,String version) throws  IOException{
		ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_DESTINATION_STATION_FILE_NAME_TEMPLATE, version));
		InputStream entryIs = zipFile.getInputStream(entry);

		log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
		try
		{

			BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

			String line = null;
			line = bf.readLine();
			while ((line = bf.readLine()) != null)
			{
				if (StringUtils.isNotBlank(line))
				{
					saveDestinationStation(line, version);
				}
			}
		}

		finally
		{
			if (null != entryIs)
			{
				entryIs.close();
			}
		}
		log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
		
	}
private void readDestinationException(ZipFile zipFile,String version) throws  IOException{
		
	ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_DESTINATION_EXCEPTION_FILE_NAME_TEMPLATE, version));
	InputStream entryIs = zipFile.getInputStream(entry);

	log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
	try
	{

		BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

		String line = null;
		line = bf.readLine();
		while ((line = bf.readLine()) != null)
		{
			if (StringUtils.isNotBlank(line))
			{
				saveDestinationException(line, version);
			}
		}
	}

	finally
	{
		if (null != entryIs)
		{
			entryIs.close();
		}
	}
	log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
	}
private void readDestinationPrdservices(ZipFile zipFile,String version) throws  IOException{
	ZipEntry entry=zipFile.getEntry(String.format(YodelConfig.GAZETTEER_DESTINATION_PRDSERVICES_FILE_NAME_TEMPLATE, version));
	InputStream entryIs = zipFile.getInputStream(entry);

	log.error( "Reading file <"+entry.getName()+"> of Gazetteer compressed file");
// reasumID
try
{

	BufferedReader bf = new BufferedReader(new InputStreamReader(entryIs));

	String line = null;
	line = bf.readLine();
	while ((line = bf.readLine()) != null)
	{
		if (StringUtils.isNotBlank(line))
		{
			saveDestinationPrdServices(line, version);
		}
	}
}

finally
{
	if (null != entryIs)
	{
		entryIs.close();
	}
}
log.error( "Read file <"+entry.getName()+"> of Gazetteer compressed file success");
	
}
	private InputStream read(String file, String version) throws IOException
	{
		//
		ZipFile zipFile = new ZipFile(file);

		ByteArrayInputStream bais = null;
		
		readActivate(zipFile, version);
		readVersion(zipFile, version);
		readService(zipFile, version);
		readReamusId(zipFile, version);
		readDestinationStation(zipFile, version);
		readDestinationException(zipFile, version);
		readDestinationPrdservices(zipFile, version);
		return bais;
	}


	/**
	 * 保存Yodel service
	 * 
	 * @param trxName 事务名
	 * @param line 固定格式的service
	 * @param version 版本号
	 */
	private void saveService(String line, String version)
	{
		YodelService yodelService = new YodelService();
		yodelService.setServiceId(line.substring(0, 3).trim());
		yodelService.setServiceDescription(line.substring(3, 48).trim());
		yodelService.setProductLine1(line.substring(48, 63).trim());
		yodelService.setProductLine2(line.substring(63, 98).trim());
		yodelService.setProductCode(line.substring(98, 100).trim());
		yodelService.setDateCode(line.substring(100, 102).trim());
		yodelService.setDayText(line.substring(102, 103).trim());
		yodelService.setTimeCode(line.substring(103, 104).trim());
		yodelService.setTimeText(line.substring(104, 105).trim());
		yodelService.setServiceCode(line.substring(105, 115).trim());
		yodelService.setFeatureId(line.substring(115, 118).trim());
		yodelService.setFeatureCode(line.substring(118, 120).trim());
		yodelService.setFileType(line.substring(120, 123).trim());
		yodelService.setConsignmentFlag(line.substring(123, 124).trim());
		yodelService.setDsFlag(line.substring(124, 125).trim());
		yodelService.setFiller(line.substring(125, 379).trim());
		yodelService.setVersion(version);
		yodelServiceService.add(yodelService);

	}

	private void saveReamusID(String line, String version)
	{
		YodelReamusId reamusID = new YodelReamusId();
		String[] fields = (line + " ").split("\\|");
		reamusID.setReamusId(fields[0]);
		reamusID.setLocationName(fields[1]);
		reamusID.setOpunit(fields[2]);
		reamusID.setCountryCode(fields[3]);
		reamusID.setLocationId(fields[4].trim());
		reamusID.setVersion(version);
		yodelReamusIdService.add(reamusID);
	}

	private void saveDestinationStation(String line, String version)
	{
		YodelDestStation destStation = new YodelDestStation();
		String[] fields = (line + " ").split("\\|");
		destStation.setCountryCode(fields[0]);
		destStation.setCity(fields[1]);
		destStation.setState(fields[2]);
		destStation.setFromPostcode(fields[3]);
		destStation.setToPostcode(fields[4]);
		destStation.setProductCode(fields[5]);
		destStation.setFromWeight(fields[6]);
		destStation.setToWeight(fields[7]);
		destStation.setServiceCtrReamusId(fields[8]);
		destStation.setHubReamusId(fields[9].trim());
		destStation.setVersion(version);

		yodelDestStationService.add(destStation);

	}

	private void saveDestinationException(String line, String version)
	{
		YodelDestException entity = new YodelDestException();
		String[] fields = (line + " ").split("\\|");

		entity.setCountryCode(fields[0]);
		entity.setCity(fields[1]);
		entity.setState(fields[2]);
		entity.setFromPostcode(fields[3]);
		entity.setToPostcode(fields[4]);
		entity.setProductCode(fields[5]);
		entity.setFeatureCode(fields[6].trim());
		entity.setVersion(version);

		yodelDestExceptionService.add(entity);

	}

	private void saveDestinationPrdServices(String line, String version)
	{
		YodelDestPrdServices entity = new YodelDestPrdServices();
		String[] fields = (line + " ").split("\\|");

		entity.setServiceCtrReamusId(fields[0]);
		entity.setProductCode(fields[1]);
		entity.setFeatureCode(fields[2]);
		entity.setAllowed(fields[3].trim());
		entity.setVersion(version);
		yodelDestPrdServicesService.add(entity);

	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException
	{
		try
		{
			doIt();
		}
		catch (Exception e)
		{
			new  JobExecutionException(e);
		}
		
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException
	{
		// TODO Auto-generated method stub
		
	}

}

package com.winit.label.manager.impl.us.usps.intl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.commons.soap.SOAPLoggingHandler;
import com.winit.exception.LabelException;
import com.winit.label.manager.impl.us.usps.intl.model.AuthenticateResult;
import com.winit.label.manager.impl.us.usps.intl.model.CalculatePostage;
import com.winit.label.manager.impl.us.usps.intl.model.CalculatePostageResult;
import com.winit.label.manager.impl.us.usps.intl.model.CloseDispatchResult;
import com.winit.label.manager.impl.us.usps.intl.model.CommonResult;
import com.winit.label.manager.impl.us.usps.intl.model.DestinationLocationsResult;
import com.winit.label.manager.impl.us.usps.intl.model.ExpectedShipResult;
import com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchPackagesResult;
import com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchResult;
import com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchesResult;
import com.winit.label.manager.impl.us.usps.intl.model.GXGCommodityInfoResult;
import com.winit.label.manager.impl.us.usps.intl.model.GenerateReportResult;
import com.winit.label.manager.impl.us.usps.intl.model.GetAvailableReportResult;
import com.winit.label.manager.impl.us.usps.intl.model.LabelResult;
import com.winit.label.manager.impl.us.usps.intl.model.LoadAndProcessPackageData;
import com.winit.label.manager.impl.us.usps.intl.model.LoadAndProcessPackageDataResponse;
import com.winit.label.manager.impl.us.usps.intl.model.LoadAndRecordLabeledPackage;
import com.winit.label.manager.impl.us.usps.intl.model.LoadAndRecordLabeledPackageResponse;
import com.winit.label.manager.impl.us.usps.intl.model.LoadPackageData;
import com.winit.label.manager.impl.us.usps.intl.model.LoadPackageDataResponse;
import com.winit.label.manager.impl.us.usps.intl.model.ProcessPackage;
import com.winit.label.manager.impl.us.usps.intl.model.ProcessPackageSoap;
import com.winit.label.manager.impl.us.usps.intl.model.TrackResult;
import com.winit.label.manager.impl.us.usps.intl.model.TrackingWithPostalCodeResult;
import com.winit.label.manager.impl.us.usps.intl.model.ext.request.ObjectFactory;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.DispatchConfirmation;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.PackageError;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.ResponseManifest;
import com.winit.label.support.ConfigUtil;

public class UspsIntlProcessPackageSoapWrapper
{

	
	private static Logger	logger				= LoggerFactory.getLogger(UspsIntlProcessPackageSoapWrapper.class);

	private ProcessPackageSoap	port				= null;

	public ProcessPackageSoap getProcessPackageSoap()
	{
		return this.port;
	}

	private static String			accessToken;
	private static String			labelingSiteAccessToken;


	private static UspsIntlProcessPackageSoapWrapper wrapper = null;
	
	public static UspsIntlProcessPackageSoapWrapper getInstance(){
		if(wrapper==null){
			wrapper = new UspsIntlProcessPackageSoapWrapper();
		}
		
		return wrapper;
	}
	
	private UspsIntlProcessPackageSoapWrapper() 
	{
		ProcessPackage service = new ProcessPackage();
		
		service.setHandlerResolver(new HandlerResolver() {
			@SuppressWarnings("rawtypes")
			public List<Handler> getHandlerChain(PortInfo portInfo)
			{
				 List<Handler> handlerList = new ArrayList<Handler>();
				 Handler handler = new SOAPLoggingHandler();
		         handlerList.add( handler );
		          
		         return handlerList;

			}
		});
		
		port = service.getProcessPackageSoap();
		
		
		((BindingProvider) port).getRequestContext().put("com.sun.xml.ws.connect.timeout",UspsIntlConfig.USPS_INTL_GSS_CONNECT_TIME_OUT() ); 

		((BindingProvider) port).getRequestContext().put("com.sun.xml.ws.request.timeout", UspsIntlConfig.USPS_INTL_GSS_REQUEST_TIME_OUT());
		
	}

	
	private String getLabelingSiteAccessToken() throws RemoteException
	{
		return this.getLabelingSiteAccessToken(false);
	}
	
	private String getLabelingSiteAccessToken(boolean newOne) throws RemoteException
	{
		if(newOne){
			labelingSiteAccessToken =  authenticateUser(true);
		}else if (StringUtils.isEmpty(labelingSiteAccessToken))
		{
			labelingSiteAccessToken = authenticateUser(true);
		}
		return labelingSiteAccessToken;
	}
	
	private String getAccessToken() throws RemoteException
	{
		return this.getAccessToken(false);
	}
	

	private String getAccessToken(boolean newOne) throws RemoteException
	{
		if(newOne){
			accessToken = authenticateUser();
		}else if (StringUtils.isEmpty(accessToken))
		{
			accessToken = authenticateUser();
		}
		return accessToken;
	}
	private String authenticateUser() throws RemoteException{
		return this.authenticateUser(false);
	}

	private String authenticateUser(boolean isLabelingSite) throws RemoteException
	{
		
		String userId ;
		String password;
		String locationId;
		String workstationId;
		if(isLabelingSite){
			userId = UspsIntlConfig.USPS_INTL_GSS_LABELING_USER_ID();
			password = UspsIntlConfig.USPS_INTL_GSS_LABELING_PASSWORD();
			locationId = UspsIntlConfig.USPS_INTL_GSS_LABELING_LOCATION_ID();
			workstationId = UspsIntlConfig.USPS_INTL_GSS_WORKSTATION_ID();
		}else{
			userId = UspsIntlConfig.USPS_INTL_GSS_USER_ID();
			password = UspsIntlConfig.USPS_INTL_GSS_PASSWORD();
			locationId = UspsIntlConfig.USPS_INTL_GSS_LOCATION_ID();
			workstationId = UspsIntlConfig.USPS_INTL_GSS_WORKSTATION_ID();
		}
		AuthenticateResult result = port.authenticateUser(userId, password, locationId,workstationId);

		if (result.getResponseCode() != 0)
		{
			throw new LabelException(result.getMessage());
		}
		return result.getAccessToken();
	}


	public AuthenticateResult authenticateUser(String userID, String password, String locationID, String workstationID) throws RemoteException
	{
		return port.authenticateUser(userID, password, locationID, workstationID);
	}

	public CommonResult logoutUser() throws RemoteException
	{
		CommonResult commonResult =  port.logoutUser(this.getAccessToken());
		if(commonResult.getResponseCode()==0){
			UspsIntlProcessPackageSoapWrapper.accessToken=null;
		}
		return commonResult;
	}
	
	public CommonResult logoutUserLabelingSite() throws RemoteException
	{
		CommonResult commonResult =  port.logoutUser(this.getAccessToken());
		if(commonResult.getResponseCode()==0){
			UspsIntlProcessPackageSoapWrapper.labelingSiteAccessToken=null;
		}
		return commonResult;
	}

	public LabelResult regeneratePackageCustomsLabel(String packageID, String mailingAgentID, int boxNumber,
			String fileFormat) throws RemoteException
	{
		LabelResult result = port.regeneratePackageCustomsLabel(packageID, mailingAgentID, boxNumber, fileFormat,
				this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.regeneratePackageCustomsLabel(packageID, mailingAgentID, boxNumber, fileFormat,
					this.getAccessToken(true));
		}
		return result;
	}

	public LabelResult getImageLabelsForPackage(String packageID, String mailingAgentID, int boxNumber,
			String fileFormat) throws RemoteException
	{
		LabelResult result = port.getImageLabelsForPackage(packageID, mailingAgentID, boxNumber, fileFormat,
				this.getLabelingSiteAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.getImageLabelsForPackage(packageID, mailingAgentID, boxNumber, fileFormat,
					this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult removeLabeledPackage(String packageID, int boxNumber) throws RemoteException
	{
		CommonResult result = port.removeLabeledPackage(packageID, boxNumber, this.getLabelingSiteAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.removeLabeledPackage(packageID, boxNumber, this.getLabelingSiteAccessToken(true));
		}
		return result;
	}

	public CommonResult removePackageFromOpenDispatch(String packageID, String mailingAgentID, int boxNumber) throws RemoteException
	{
		CommonResult result = port.removePackageFromOpenDispatch(packageID, mailingAgentID, boxNumber,
				this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port
					.removePackageFromOpenDispatch(packageID, mailingAgentID, boxNumber, this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult processThePackage(String packageID, String mailingAgentID, int boxNumber) throws RemoteException
	{
		CommonResult result = port.processThePackage(packageID, mailingAgentID, boxNumber, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.processThePackage(packageID, mailingAgentID, boxNumber, this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult processThePackageToDestinationLocation(String packageID, String mailingAgentID, int boxNumber,
			String destinationLocationID) throws RemoteException
	{
		CommonResult result = port.processThePackageToDestinationLocation(packageID, mailingAgentID, boxNumber,
				destinationLocationID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.processThePackageToDestinationLocation(packageID, mailingAgentID, boxNumber,
					destinationLocationID, this.getAccessToken(true));
		}
		return result;
	}

	public static class LoadAndProcessPackageDataResultWrapper
	{

		private LoadAndProcessPackageDataResponse.LoadAndProcessPackageDataResult	loadAndProcessPackageDataResult;

		private ResponseManifest				manifest;

		public LoadAndProcessPackageDataResultWrapper(LoadAndProcessPackageDataResponse.LoadAndProcessPackageDataResult loadAndProcessPackageDataResult)
		{
			this.loadAndProcessPackageDataResult = loadAndProcessPackageDataResult;
		}

		public LoadAndProcessPackageDataResponse.LoadAndProcessPackageDataResult getLoadAndProcessPackageDataResult()
		{
			return loadAndProcessPackageDataResult;
		}

		@SuppressWarnings("unchecked")
		public ResponseManifest getManifest() throws JAXBException
		{
			if (manifest == null)
			{
				List<Object> rt = loadAndProcessPackageDataResult.getContent();
				if (rt == null || rt.isEmpty())
				{
					return this.manifest;
				}


				this.manifest = ((JAXBElement<ResponseManifest>)rt.get(0)).getValue();
			}

			return manifest;
		}

	}

	public LoadAndProcessPackageDataResultWrapper loadAndProcessPackageData(LoadAndProcessPackageData.XmlDoc xmlDoc) throws JAXBException, RemoteException
	{
		LoadAndProcessPackageDataResponse.LoadAndProcessPackageDataResult result = port.loadAndProcessPackageData(xmlDoc, this.getAccessToken());
		LoadAndProcessPackageDataResultWrapper wrapper = new LoadAndProcessPackageDataResultWrapper(result);
		ResponseManifest manifest;
		manifest = wrapper.getManifest();
		if (manifest == null)
		{
			return wrapper;
		}
		DispatchConfirmation dispatchConfirmation = manifest.getDispatchConfirmation();
		if (dispatchConfirmation == null)
		{
			return wrapper;

		}
		if (dispatchConfirmation.getRejectPackageCount() == 0)
		{
			return wrapper;
		}
		List<PackageError> packageErrors = manifest.getPackageErrors();
		for (PackageError packageError : packageErrors)
		{
			List<String> errorDescriptioins = packageError.getErrorDescription();
			for (String ed : errorDescriptioins)
			{
				if (ed.contains("Invalid or expired access token."))
				{
					LoadAndProcessPackageDataResponse.LoadAndProcessPackageDataResult  result2 = port.loadAndProcessPackageData(xmlDoc,
							this.getAccessToken(true));
					LoadAndProcessPackageDataResultWrapper wrapper2 = new LoadAndProcessPackageDataResultWrapper(result2);
					return wrapper2;
				}
			}
		}
		return wrapper;
	}
	
	
	
	public static class LoadPackageDataResultWrapper
	{

		private LoadPackageDataResponse.LoadPackageDataResult 	loadPackageDataResult;

		private ResponseManifest				manifest;

		public LoadPackageDataResultWrapper(LoadPackageDataResponse.LoadPackageDataResult loadPackageDataResult)
		{
			this.loadPackageDataResult = loadPackageDataResult;
		}

		public LoadPackageDataResponse.LoadPackageDataResult getLoadAndProcessPackageDataResult()
		{
			return loadPackageDataResult;
		}

		@SuppressWarnings({ "unchecked" })
		public ResponseManifest getManifest() throws JAXBException
		{
			if (manifest == null)
			{
				List<Object> rt = loadPackageDataResult.getContent();
				if (rt == null || rt.isEmpty())
				{
					return this.manifest;
				}

				this.manifest =  ((JAXBElement<ResponseManifest>)rt.get(0)).getValue();
			}

			return manifest;
		}

	}

	public LoadPackageDataResultWrapper loadPackageData(LoadPackageData.XmlDoc xmlDoc) throws JAXBException, RemoteException
	{
		LoadPackageDataResponse.LoadPackageDataResult result = port.loadPackageData(xmlDoc, this.getAccessToken());
		LoadPackageDataResultWrapper wrapper = new LoadPackageDataResultWrapper(result);
		ResponseManifest manifest;
		manifest = wrapper.getManifest();
		if (manifest == null)
		{
			return wrapper;
		}
		DispatchConfirmation dispatchConfirmation = manifest.getDispatchConfirmation();
		if (dispatchConfirmation == null)
		{
			return wrapper;

		}
		if (dispatchConfirmation.getRejectPackageCount() == 0)
		{
			return wrapper;
		}
		List<PackageError> packageErrors = manifest.getPackageErrors();
		for (PackageError packageError : packageErrors)
		{
			List<String> errorDescriptioins = packageError.getErrorDescription();
			for (String ed : errorDescriptioins)
			{
				if (ed.contains("Invalid or expired access token."))
				{
					LoadPackageDataResponse.LoadPackageDataResult  result2 = port.loadPackageData(xmlDoc,
							this.getAccessToken(true));
					LoadPackageDataResultWrapper wrapper2 = new LoadPackageDataResultWrapper(result2);
					return wrapper2;
				}
			}
		}
		return wrapper;
	}

	
	public static class LoadAndRecordLabeledPackageResultWrapper
	{

		private LoadAndRecordLabeledPackageResponse.LoadAndRecordLabeledPackageResult	loadAndRecordLabeledPackageResult;

		private ResponseManifest				manifest;

		public LoadAndRecordLabeledPackageResultWrapper(LoadAndRecordLabeledPackageResponse.LoadAndRecordLabeledPackageResult loadAndRecordLabeledPackageResult)
		{
			this.loadAndRecordLabeledPackageResult = loadAndRecordLabeledPackageResult;
		}

		public LoadAndRecordLabeledPackageResponse.LoadAndRecordLabeledPackageResult getLoadAndProcessPackageDataResult()
		{
			return loadAndRecordLabeledPackageResult;
		}

		@SuppressWarnings("unchecked")
		public ResponseManifest getManifest() throws JAXBException
		{
			if (manifest == null)
			{
				List<Object> rt = loadAndRecordLabeledPackageResult.getContent();
				if (rt == null || rt.isEmpty())
				{
					return this.manifest;
				}

				this.manifest = ((JAXBElement<ResponseManifest>)rt.get(0)).getValue();
			}

			return manifest;
		}

	}
	
	public LoadAndRecordLabeledPackageResultWrapper loadAndRecordLabeledPackage(LoadAndRecordLabeledPackage.XmlDoc xmlDoc) throws JAXBException, RemoteException
	{
		LoadAndRecordLabeledPackageResponse.LoadAndRecordLabeledPackageResult result = port.loadAndRecordLabeledPackage(xmlDoc, this.getLabelingSiteAccessToken());
		LoadAndRecordLabeledPackageResultWrapper wrapper = new LoadAndRecordLabeledPackageResultWrapper(result);
		ResponseManifest manifest;
		manifest = wrapper.getManifest();
		if (manifest == null)
		{
			return wrapper;
		}
		DispatchConfirmation dispatchConfirmation = manifest.getDispatchConfirmation();
		if (dispatchConfirmation == null)
		{
			return wrapper;

		}
		if (dispatchConfirmation.getRejectPackageCount() == 0)
		{
			return wrapper;
		}
		List<PackageError> packageErrors = manifest.getPackageErrors();
		for (PackageError packageError : packageErrors)
		{
			List<String> errorDescriptioins = packageError.getErrorDescription();
			for (String ed : errorDescriptioins)
			{
				if (ed.contains("Invalid or expired access token."))
				{
					LoadAndRecordLabeledPackageResponse.LoadAndRecordLabeledPackageResult  result2 = port.loadAndRecordLabeledPackage(xmlDoc,
							this.getLabelingSiteAccessToken(true));
					LoadAndRecordLabeledPackageResultWrapper wrapper2 = new LoadAndRecordLabeledPackageResultWrapper(result2);
					return wrapper2;
				}
			}
		}
		return wrapper;
	}

	public CommonResult processLabeledPackage(String packageID, String mailingAgentID, int boxNumber) throws RemoteException
	{
		CommonResult result = port.processLabeledPackage(packageID, mailingAgentID, boxNumber, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.processLabeledPackage(packageID, mailingAgentID, boxNumber, this.getAccessToken(true));
		}
		return result;
	}

	public TrackResult trackPackage(String packageID, String mailingAgentID, int boxNumber) throws RemoteException
	{
		TrackResult result = port.trackPackage(packageID, mailingAgentID, boxNumber, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.trackPackage(packageID, mailingAgentID, boxNumber, this.getAccessToken(true));
		}
		return result;
	}

	public CloseDispatchResult closeDispatch(String vehicleNum, String vehicleType, String depDateTime,
			String arrDateTime) throws RemoteException
	{
		CloseDispatchResult result = port.closeDispatch(vehicleNum, vehicleType, depDateTime, arrDateTime,
				this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.closeDispatch(vehicleNum, vehicleType, depDateTime, arrDateTime, this.getAccessToken(true));
		}
		return result;
	}

	public CloseDispatchResult closeDispatchToDestinationLocation(String destinationLocationID, String vehicleNum,
			String vehicleType, String depDateTime, String arrDateTime) throws RemoteException
	{
		CloseDispatchResult result = port.closeDispatchToDestinationLocation(destinationLocationID, vehicleNum,
				vehicleType, depDateTime, arrDateTime, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.closeDispatchToDestinationLocation(destinationLocationID, vehicleNum, vehicleType,
					depDateTime, arrDateTime, this.getAccessToken(true));
		}
		return result;
	}

	public GetAvailableReportResult getAvailableReportsForDispatch(String dispatchID) throws RemoteException
	{
		GetAvailableReportResult result = port.getAvailableReportsForDispatch(dispatchID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.getAvailableReportsForDispatch(dispatchID, this.getAccessToken(true));
		}
		return result;
	}

	public GetAvailableReportResult getRequiredReportsForDispatch(String dispatchID) throws RemoteException
	{
		GetAvailableReportResult result = port.getRequiredReportsForDispatch(dispatchID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.getRequiredReportsForDispatch(dispatchID, this.getAccessToken(true));
		}
		return result;
	}

	public GenerateReportResult generateReport(String dispatchID, String reportID) throws RemoteException
	{
		GenerateReportResult result = port.generateReport(dispatchID, reportID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.generateReport(dispatchID, reportID, this.getAccessToken(true));
		}
		return result;
	}

	public GenerateReportResult generateActivityReport(String mailingAgentID, String locationID, String startDate,
			String stopDate, String reportID) throws RemoteException
	{
		GenerateReportResult result = port.generateActivityReport(mailingAgentID, locationID, startDate, stopDate,
				reportID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.generateActivityReport(mailingAgentID, locationID, startDate, stopDate, reportID,
					this.getAccessToken(true));
		}
		return result;
	}

	public GenerateReportResult generateVolumePostageReport(String mailingAgentID, String locationID,
			int includeDestinationCountry, String startDate, String stopDate, String reportID) throws RemoteException
	{
		GenerateReportResult result = port.generateVolumePostageReport(mailingAgentID, locationID,
				includeDestinationCountry, startDate, stopDate, reportID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.generateVolumePostageReport(mailingAgentID, locationID, includeDestinationCountry, startDate,
					stopDate, reportID, this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult openReceptacle(String destinationLocationID) throws RemoteException
	{
		CommonResult result = port.openReceptacle(destinationLocationID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.openReceptacle(destinationLocationID, this.getAccessToken(true));
		}
		return result;
	}

	public DestinationLocationsResult getDestinationLocations() throws RemoteException
	{
		DestinationLocationsResult result = port.getDestinationLocations(this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.getDestinationLocations(this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult closeReceptacle(String receptacleID) throws RemoteException
	{
		CommonResult result = port.closeReceptacle(receptacleID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.closeReceptacle(receptacleID, this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult uploadPackageFile(byte[] fileData, String fileName) throws RemoteException
	{
		CommonResult result = port.uploadPackageFile(fileData, fileName, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.uploadPackageFile(fileData, fileName, this.getAccessToken(true));
		}
		return result;
	}

//	public VerifyGXGPackageResult verifyGXGPackage(String packageID, String mailingAgentID, int boxNumber,
//			GXGRequest gXGRequest)
//	{
//		VerifyGXGPackageResult result = port.verifyGXGPackage(packageID, mailingAgentID, boxNumber, gXGRequest,
//				this.getAccessToken());
//		return result;
//	}

//	public VerifyGXGPackageToDestinationLocationResult verifyGXGPackageToDestinationLocation(String packageID,
//			String mailingAgentID, int boxNumber, String destinationLocationID,
//			VerifyGXGPackageToDestinationLocation.GXGRequest gXGRequest)
//	{
//		VerifyGXGPackageToDestinationLocationResult result = port.verifyGXGPackageToDestinationLocation(packageID,
//				mailingAgentID, boxNumber, destinationLocationID, gXGRequest, this.getAccessToken());
//		return result;
//	}

	public GXGCommodityInfoResult getGXGCommodityInfo() throws RemoteException
	{
		GXGCommodityInfoResult result = port.getGXGCommodityInfo(this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.getGXGCommodityInfo(this.getAccessToken(true));
		}
		return result;
	}

	public FrozenDispatchResult freezeDispatch(String freezeDispatchID) throws RemoteException
	{
		FrozenDispatchResult result = port.freezeDispatch(freezeDispatchID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.freezeDispatch(freezeDispatchID, this.getAccessToken(true));
		}
		return result;
	}

	public FrozenDispatchResult freezeDispatchForLocation(String freezeDispatchID, String destinationLocationID) throws RemoteException
	{
		FrozenDispatchResult result = port.freezeDispatchForLocation(freezeDispatchID, destinationLocationID,
				this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.freezeDispatchForLocation(freezeDispatchID, destinationLocationID, this.getAccessToken(true));
		}
		return result;
	}

	public CloseDispatchResult closeFrozenDispatch(String freezeDispatchID, String vehicleNum, String vehicleType,
			String depDateTime, String arrDateTime) throws RemoteException
	{
		CloseDispatchResult result = port.closeFrozenDispatch(freezeDispatchID, vehicleNum, vehicleType, depDateTime,
				arrDateTime, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.closeFrozenDispatch(freezeDispatchID, vehicleNum, vehicleType, depDateTime, arrDateTime,
					this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult addPackageToFrozenDispatch(String freezeDispatchID, String packageID) throws RemoteException
	{
		CommonResult result = port.addPackageToFrozenDispatch(freezeDispatchID, packageID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.addPackageToFrozenDispatch(freezeDispatchID, packageID, this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult removePackageFromFrozenDispatch(String freezeDispatchID, String packageID,
			String mailingAgentID, int boxNumber) throws RemoteException
	{
		CommonResult result = port.removePackageFromFrozenDispatch(freezeDispatchID, packageID, mailingAgentID,
				boxNumber, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.removePackageFromFrozenDispatch(freezeDispatchID, packageID, mailingAgentID, boxNumber,
					this.getAccessToken(true));
		}
		return result;
	}

	public FrozenDispatchesResult retrieveFrozenDispatchesInfo() throws RemoteException
	{
		FrozenDispatchesResult result = port.retrieveFrozenDispatchesInfo(this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.retrieveFrozenDispatchesInfo(this.getAccessToken(true));
		}
		return result;
	}

	public CalculatePostageResult calculatePostage(CalculatePostage.XmlDoc xmlDoc) throws RemoteException
	{
		CalculatePostageResult result = port.calculatePostage(xmlDoc, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.calculatePostage(xmlDoc, this.getAccessToken(true));
		}
		return result;
	}

	public FrozenDispatchPackagesResult retrieveFrozenDispatchPackagesInfo(String freezeDispatchID) throws RemoteException
	{
		FrozenDispatchPackagesResult result = port.retrieveFrozenDispatchPackagesInfo(freezeDispatchID,
				this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.retrieveFrozenDispatchPackagesInfo(freezeDispatchID, this.getAccessToken(true));
		}
		return result;
	}

	public TrackingWithPostalCodeResult trackPackageWithPostalCode(String packageID, String mailingAgentID,
			int boxNumber) throws RemoteException
	{
		TrackingWithPostalCodeResult result = port.trackPackageWithPostalCode(packageID, mailingAgentID, boxNumber,
				this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.trackPackageWithPostalCode(packageID, mailingAgentID, boxNumber, this.getAccessToken(true));
		}
		return result;
	}

//	public boolean refreshWebComponent(String val)
//	{
//		boolean result = port.refreshWebComponent(val);
//		return result;
//	}

//	public GetShipmentHistoryResult getShipmentHistory(String startDate, String endDate)
//	{
//		GetShipmentHistoryResult result = port.getShipmentHistory(startDate, endDate, this.getAccessToken());
//		return result;
//	}

//	public SearchForProcessedPackageResult searchForProcessedPackage()
//	{
//		SearchForProcessedPackageResult result = port.searchForProcessedPackage(this.getAccessToken(),
//				this.getAccessToken(), this.getAccessToken(), this.getAccessToken(), this.getAccessToken(),
//				this.getAccessToken(), this.getAccessToken());
//		return result;
//	}

//	public GetCurrentDispatchResult getCurrentDispatch()
//	{
//		GetCurrentDispatchResult result = port.getCurrentDispatch(this.getAccessToken(), this.getAccessToken());
//		return result;
//	}

//	public GetSystemStatusMessageResult getSystemStatusMessage()
//	{
//		GetSystemStatusMessageResult result = port.getSystemStatusMessage();
//		return result;
//	}

	public GenerateReportResult generatePartialDispatchReport(String locationID) throws RemoteException
	{
		GenerateReportResult result = port.generatePartialDispatchReport(locationID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.generatePartialDispatchReport(locationID, this.getAccessToken(true));
		}
		return result;
	}

	public ExpectedShipResult getExpectedShipDate(String destinationLocationID) throws RemoteException
	{
		ExpectedShipResult result = port.getExpectedShipDate(destinationLocationID, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.getExpectedShipDate(destinationLocationID, this.getAccessToken(true));
		}
		return result;
	}

	public CommonResult setExpectedShipDate(String destinationLocationID, String expectedShipDate) throws RemoteException
	{
		CommonResult result = port.setExpectedShipDate(destinationLocationID, expectedShipDate, this.getAccessToken());
		if (result.getResponseCode() == 501)
		{
			result = port.setExpectedShipDate(destinationLocationID, expectedShipDate, this.getAccessToken(true));
		}
		return result;
	}

//	public LookupHSForLegalDescriptionResult lookupHSForLegalDescription(String countryOfImport, String searchText,
//			String dateOfImport, String classificationType, String matchLevel, String searchType)
//	{
//		LookupHSForLegalDescriptionResult result = port.lookupHSForLegalDescription(countryOfImport, searchText,
//				dateOfImport, classificationType, matchLevel, searchType, this.getAccessToken());
//		return result;
//	}

//	public LookupHSForCommonDescriptionResult lookupHSForCommonDescription(String searchText, String matchLevel)
//	{
//		LookupHSForCommonDescriptionResult result = port.lookupHSForCommonDescription(searchText, matchLevel,
//				this.getAccessToken());
//		return result;
//	}

//	public CalculateLandedCostResult calculateLandedCost(String countryOfImport, String countryOfExport,
//			String modeOfTransport, String incoTerm, String taxDetail, String dateOfImport, Items items)
//	{
//		CalculateLandedCostResult result = port.calculateLandedCost(countryOfImport, countryOfExport, modeOfTransport,
//				incoTerm, taxDetail, dateOfImport, items, this.getAccessToken());
//		return result;
//	}
}
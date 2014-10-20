package com.winit.svr.label.handler.impl.usa.usps;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.apache.axis.message.MessageElement;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.svr.LabelException;
import com.winit.svr.label.context.ContextUtils;
import com.winit.svr.label.handler.impl.commons.axis.AxisLoggingHandler;
import com.winit.svr.label.handler.impl.usa.usps.model.AuthenticateResult;
import com.winit.svr.label.handler.impl.usa.usps.model.CalculatePostageResult;
import com.winit.svr.label.handler.impl.usa.usps.model.CalculatePostageXmlDoc;
import com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult;
import com.winit.svr.label.handler.impl.usa.usps.model.CommonResult;
import com.winit.svr.label.handler.impl.usa.usps.model.DestinationLocationsResult;
import com.winit.svr.label.handler.impl.usa.usps.model.ExpectedShipResult;
import com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchPackagesResult;
import com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchResult;
import com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchesResult;
import com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult;
import com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult;
import com.winit.svr.label.handler.impl.usa.usps.model.GetAvailableReportResult;
import com.winit.svr.label.handler.impl.usa.usps.model.LabelResult;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadAndProcessPackageDataXmlDoc;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageXmlDoc;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadPackageDataResponseLoadPackageDataResult;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadPackageDataXmlDoc;
import com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageLocator;
import com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap;
import com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoapStub;
import com.winit.svr.label.handler.impl.usa.usps.model.TrackResult;
import com.winit.svr.label.handler.impl.usa.usps.model.TrackingWithPostalCodeResult;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.request.ObjectFactory;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.DispatchConfirmation;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.PackageError;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.ResponseManifest;

public class USPSIntlProcessPackageSoapWrapper
{

	
	private static Logger	logger				= LoggerFactory.getLogger(USPSIntlProcessPackageSoapWrapper.class);

	private static  String	USPS_INTL_GSS_LABELING_USER_ID(){
		return ContextUtils.getValue("USPS_INTL_GSS_LABELING_USER_ID");
	}
	private static  String	USPS_INTL_GSS_LABELING_PASSWORD	(){
		return ContextUtils.getValue("USPS_INTL_GSS_LABELING_PASSWORD");
	}
	private static  String	USPS_INTL_GSS_LABELING_LOCATION_ID(){
		return ContextUtils.getValue("USPS_INTL_GSS_LABELING_LOCATION_ID");
	}

	private static  String	USPS_INTL_GSS_USER_ID(){
		return ContextUtils.getValue("USPS_INTL_GSS_USER_ID");
	}	
	private static  String	USPS_INTL_GSS_PASSWORD(){
		return ContextUtils.getValue("USPS_INTL_GSS_PASSWORD");
	}		
	private static  String	USPS_INTL_GSS_LOCATION_ID	(){
		return ContextUtils.getValue("USPS_INTL_GSS_LOCATION_ID");
	}	
	private static  String	USPS_INTL_GSS_WORKSTATION_ID(){
		return ContextUtils.getValue("USPS_INTL_GSS_WORKSTATION_ID");
	}		
	private static  String  USPS_INTL_GSS_URL(){
		return ContextUtils.getValue("USPS_INTL_GSS_URL");
	}
	
	private static final String	USPS_INTL_GSS_CONNECT_TIME_OUT	(){
		return ContextUtils.getValue("USPS_INTL_GSS_CONNECT_TIME_OUT");
	}	
	private static final String	USPS_INTL_GSS_REQUEST_TIME_OUT	(){
		return ContextUtils.getValue("USPS_INTL_GSS_REQUEST_TIME_OUT");
	}	
	
	private ProcessPackageSoap	port				= null;

	public ProcessPackageSoap getProcessPackageSoap()
	{
		return this.port;
	}

	private static String			accessToken;
	private static String			labelingSiteAccessToken;

	private static JAXBContext		jaxbCtx;
	private static Unmarshaller		unmarshaller;

	private static USPSIntlProcessPackageSoapWrapper wrapper = null;
	
	public static USPSIntlProcessPackageSoapWrapper getInstance() throws ServiceException{
		if(wrapper==null){
			try
			{
				wrapper = new USPSIntlProcessPackageSoapWrapper();
			}
			catch (MalformedURLException e)
			{
				throw new LabelException(e); 
			}
		}
		
		return wrapper;
	}
	
	@SuppressWarnings("restriction")
	private USPSIntlProcessPackageSoapWrapper() throws MalformedURLException, ServiceException
	{
		try
		{

			jaxbCtx = JAXBContext.newInstance(ObjectFactory.class, 
					com.winit.svr.label.handler.impl.usa.usps.model.ext.response.ObjectFactory.class);

			unmarshaller = jaxbCtx.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			throw new LabelException(e);
		}
//		ProcessPackageLocator locator = new ProcessPackageLocator();
//		HandlerRegistry handlerRegistry = locator.getHandlerRegistry();
////		javax.xml.namespace.QName qname = new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ProcessPackageSoap");
//		javax.xml.namespace.QName qname = locator.getServiceName();
//		List chain = handlerRegistry.getHandlerChain(qname);
//		HandlerInfo info = new HandlerInfo();
//        info.setHandlerClass(SOAPLoggingHandler.class);
//        chain.add(info);
       
//		ProcessPackageSoap  service 
//		port= locator.getProcessPackageSoap();
//		port = new com.lable.usa.usps.model.ProcessPackageSoapProxy(USPS_INTL_GSS_URL());
		
		ProcessPackageLocator locator = new ProcessPackageLocator();
        HandlerRegistry handlerRegistry = locator.getHandlerRegistry();
        QName qname = new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ProcessPackageSoap");
        
        @SuppressWarnings("rawtypes")
        List chain = handlerRegistry.getHandlerChain(qname);
        HandlerInfo info = new HandlerInfo();
        info.setHandlerClass(AxisLoggingHandler.class);
        chain.add(info);
        port = locator.getProcessPackageSoap(new URL(USPS_INTL_GSS_URL()));
        ((ProcessPackageSoapStub) port).setTimeout(Integer.valueOf(USPS_INTL_GSS_REQUEST_TIME_OUT()));
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
			userId = USPS_INTL_GSS_LABELING_USER_ID();
			password = USPS_INTL_GSS_LABELING_PASSWORD();
			locationId = USPS_INTL_GSS_LABELING_LOCATION_ID();
			workstationId = USPS_INTL_GSS_WORKSTATION_ID();
		}else{
			userId = USPS_INTL_GSS_USER_ID();
			password = USPS_INTL_GSS_PASSWORD();
			locationId = USPS_INTL_GSS_LOCATION_ID();
			workstationId = USPS_INTL_GSS_WORKSTATION_ID();
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
			USPSIntlProcessPackageSoapWrapper.accessToken=null;
		}
		return commonResult;
	}
	
	public CommonResult logoutUserLabelingSite() throws RemoteException
	{
		CommonResult commonResult =  port.logoutUser(this.getAccessToken());
		if(commonResult.getResponseCode()==0){
			USPSIntlProcessPackageSoapWrapper.labelingSiteAccessToken=null;
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

		private LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult	loadAndProcessPackageDataResult;

		private ResponseManifest				manifest;

		public LoadAndProcessPackageDataResultWrapper(LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult loadAndProcessPackageDataResult)
		{
			this.loadAndProcessPackageDataResult = loadAndProcessPackageDataResult;
		}

		public LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult getLoadAndProcessPackageDataResult()
		{
			return loadAndProcessPackageDataResult;
		}

		@SuppressWarnings("restriction")
		public ResponseManifest getManifest() throws JAXBException
		{
			if (manifest == null)
			{
				MessageElement[] rt = loadAndProcessPackageDataResult.get_any();
				if (rt == null || rt.length==0)
				{
					return this.manifest;
				}

				JAXBContext jaxbCtx = JAXBContext.newInstance(ObjectFactory.class,
						com.winit.svr.label.handler.impl.usa.usps.model.ext.response.ObjectFactory.class);
				this.manifest = unmarshaller.unmarshal((org.w3c.dom.Node) rt[0], ResponseManifest.class).getValue();
			}

			return manifest;
		}

	}

	@SuppressWarnings("restriction")
	public LoadAndProcessPackageDataResultWrapper loadAndProcessPackageData(LoadAndProcessPackageDataXmlDoc xmlDoc) throws JAXBException, RemoteException
	{
		LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult result = port.loadAndProcessPackageData(xmlDoc, this.getAccessToken());
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
					LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult  result2 = port.loadAndProcessPackageData(xmlDoc,
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

		private LoadPackageDataResponseLoadPackageDataResult 	loadPackageDataResult;

		private ResponseManifest				manifest;

		public LoadPackageDataResultWrapper(LoadPackageDataResponseLoadPackageDataResult loadPackageDataResult)
		{
			this.loadPackageDataResult = loadPackageDataResult;
		}

		public LoadPackageDataResponseLoadPackageDataResult getLoadAndProcessPackageDataResult()
		{
			return loadPackageDataResult;
		}

		@SuppressWarnings("restriction")
		public ResponseManifest getManifest() throws JAXBException
		{
			if (manifest == null)
			{
				MessageElement[] rt = loadPackageDataResult.get_any();
				if (rt == null || rt.length==0)
				{
					return this.manifest;
				}

				JAXBContext jaxbCtx = JAXBContext.newInstance(ObjectFactory.class,
						com.winit.svr.label.handler.impl.usa.usps.model.ext.response.ObjectFactory.class);

				Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
				// Marshaller marshaller =jaxbCtx.createMarshaller();

				this.manifest = unmarshaller.unmarshal((org.w3c.dom.Node) rt[0], ResponseManifest.class).getValue();
			}

			return manifest;
		}

	}

	public LoadPackageDataResultWrapper loadPackageData(LoadPackageDataXmlDoc xmlDoc) throws JAXBException, RemoteException
	{
		LoadPackageDataResponseLoadPackageDataResult result = port.loadPackageData(xmlDoc, this.getAccessToken());
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
					LoadPackageDataResponseLoadPackageDataResult  result2 = port.loadPackageData(xmlDoc,
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

		private LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult	loadAndRecordLabeledPackageResult;

		private ResponseManifest				manifest;

		public LoadAndRecordLabeledPackageResultWrapper(LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult loadAndRecordLabeledPackageResult)
		{
			this.loadAndRecordLabeledPackageResult = loadAndRecordLabeledPackageResult;
		}

		public LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult getLoadAndProcessPackageDataResult()
		{
			return loadAndRecordLabeledPackageResult;
		}

		@SuppressWarnings("restriction")
		public ResponseManifest getManifest() throws JAXBException
		{
			if (manifest == null)
			{
				MessageElement[] rt = loadAndRecordLabeledPackageResult.get_any();
				if (rt == null || rt.length==0)
				{
					return this.manifest;
				}

				JAXBContext jaxbCtx = JAXBContext.newInstance(ObjectFactory.class,
						com.winit.svr.label.handler.impl.usa.usps.model.ext.response.ObjectFactory.class);

				Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
				// Marshaller marshaller =jaxbCtx.createMarshaller();

				this.manifest = unmarshaller.unmarshal((org.w3c.dom.Node) rt[0], ResponseManifest.class).getValue();
			}

			return manifest;
		}

	}
	
	public LoadAndRecordLabeledPackageResultWrapper loadAndRecordLabeledPackage(LoadAndRecordLabeledPackageXmlDoc xmlDoc) throws JAXBException, RemoteException
	{
		LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult result = port.loadAndRecordLabeledPackage(xmlDoc, this.getLabelingSiteAccessToken());
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
					LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult  result2 = port.loadAndRecordLabeledPackage(xmlDoc,
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

	public CalculatePostageResult calculatePostage(CalculatePostageXmlDoc xmlDoc) throws RemoteException
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
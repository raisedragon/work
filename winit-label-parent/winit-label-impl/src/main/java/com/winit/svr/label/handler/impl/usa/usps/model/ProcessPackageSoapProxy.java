package com.winit.svr.label.handler.impl.usa.usps.model;

public class ProcessPackageSoapProxy implements com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap {
  private String _endpoint = null;
  private com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap processPackageSoap = null;
  
  public ProcessPackageSoapProxy() {
    _initProcessPackageSoapProxy();
  }
  
  public ProcessPackageSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initProcessPackageSoapProxy();
  }
  
  private void _initProcessPackageSoapProxy() {
    try {
      processPackageSoap = (new com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageLocator()).getProcessPackageSoap();
      if (processPackageSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)processPackageSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)processPackageSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (processPackageSoap != null)
      ((javax.xml.rpc.Stub)processPackageSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap getProcessPackageSoap() {
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap;
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.AuthenticateResult authenticateUser(java.lang.String userID, java.lang.String password, java.lang.String locationID, java.lang.String workstationID) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.authenticateUser(userID, password, locationID, workstationID);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult logoutUser(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.logoutUser(accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LabelResult regeneratePackageCustomsLabel(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String fileFormat, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.regeneratePackageCustomsLabel(packageID, mailingAgentID, boxNumber, fileFormat, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LabelResult getImageLabelsForPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String fileFormat, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getImageLabelsForPackage(packageID, mailingAgentID, boxNumber, fileFormat, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult removeLabeledPackage(java.lang.String packageID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.removeLabeledPackage(packageID, boxNumber, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult removePackageFromOpenDispatch(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.removePackageFromOpenDispatch(packageID, mailingAgentID, boxNumber, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult processThePackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.processThePackage(packageID, mailingAgentID, boxNumber, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult processThePackageToDestinationLocation(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.processThePackageToDestinationLocation(packageID, mailingAgentID, boxNumber, destinationLocationID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult loadAndProcessPackageData(com.winit.svr.label.handler.impl.usa.usps.model.LoadAndProcessPackageDataXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.loadAndProcessPackageData(xmlDoc, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LoadPackageDataResponseLoadPackageDataResult loadPackageData(com.winit.svr.label.handler.impl.usa.usps.model.LoadPackageDataXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.loadPackageData(xmlDoc, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult loadAndRecordLabeledPackage(com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.loadAndRecordLabeledPackage(xmlDoc, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult processLabeledPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.processLabeledPackage(packageID, mailingAgentID, boxNumber, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.TrackResult trackPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.trackPackage(packageID, mailingAgentID, boxNumber, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult closeDispatch(java.lang.String vehicleNum, java.lang.String vehicleType, java.lang.String depDateTime, java.lang.String arrDateTime, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.closeDispatch(vehicleNum, vehicleType, depDateTime, arrDateTime, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult closeDispatchToDestinationLocation(java.lang.String destinationLocationID, java.lang.String vehicleNum, java.lang.String vehicleType, java.lang.String depDateTime, java.lang.String arrDateTime, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.closeDispatchToDestinationLocation(destinationLocationID, vehicleNum, vehicleType, depDateTime, arrDateTime, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GetAvailableReportResult getAvailableReportsForDispatch(java.lang.String dispatchID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getAvailableReportsForDispatch(dispatchID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GetAvailableReportResult getRequiredReportsForDispatch(java.lang.String dispatchID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getRequiredReportsForDispatch(dispatchID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generateReport(java.lang.String dispatchID, java.lang.String reportID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.generateReport(dispatchID, reportID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generateActivityReport(java.lang.String mailingAgentID, java.lang.String locationID, java.lang.String startDate, java.lang.String stopDate, java.lang.String reportID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.generateActivityReport(mailingAgentID, locationID, startDate, stopDate, reportID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generateVolumePostageReport(java.lang.String mailingAgentID, java.lang.String locationID, int includeDestinationCountry, java.lang.String startDate, java.lang.String stopDate, java.lang.String reportID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.generateVolumePostageReport(mailingAgentID, locationID, includeDestinationCountry, startDate, stopDate, reportID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult openReceptacle(java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.openReceptacle(destinationLocationID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.DestinationLocationsResult getDestinationLocations(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getDestinationLocations(accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult closeReceptacle(java.lang.String receptacleID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.closeReceptacle(receptacleID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult uploadPackageFile(byte[] fileData, java.lang.String fileName, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.uploadPackageFile(fileData, fileName, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageResponseVerifyGXGPackageResult verifyGXGPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageGXGRequest GXGRequest, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.verifyGXGPackage(packageID, mailingAgentID, boxNumber, GXGRequest, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageToDestinationLocationResponseVerifyGXGPackageToDestinationLocationResult verifyGXGPackageToDestinationLocation(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String destinationLocationID, com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageToDestinationLocationGXGRequest GXGRequest, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.verifyGXGPackageToDestinationLocation(packageID, mailingAgentID, boxNumber, destinationLocationID, GXGRequest, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult getGXGCommodityInfo(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getGXGCommodityInfo(accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchResult freezeDispatch(java.lang.String freezeDispatchID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.freezeDispatch(freezeDispatchID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchResult freezeDispatchForLocation(java.lang.String freezeDispatchID, java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.freezeDispatchForLocation(freezeDispatchID, destinationLocationID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult closeFrozenDispatch(java.lang.String freezeDispatchID, java.lang.String vehicleNum, java.lang.String vehicleType, java.lang.String depDateTime, java.lang.String arrDateTime, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.closeFrozenDispatch(freezeDispatchID, vehicleNum, vehicleType, depDateTime, arrDateTime, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult addPackageToFrozenDispatch(java.lang.String freezeDispatchID, java.lang.String packageID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.addPackageToFrozenDispatch(freezeDispatchID, packageID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult removePackageFromFrozenDispatch(java.lang.String freezeDispatchID, java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.removePackageFromFrozenDispatch(freezeDispatchID, packageID, mailingAgentID, boxNumber, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchesResult retrieveFrozenDispatchesInfo(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.retrieveFrozenDispatchesInfo(accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CalculatePostageResult calculatePostage(com.winit.svr.label.handler.impl.usa.usps.model.CalculatePostageXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.calculatePostage(xmlDoc, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchPackagesResult retrieveFrozenDispatchPackagesInfo(java.lang.String freezeDispatchID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.retrieveFrozenDispatchPackagesInfo(freezeDispatchID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.TrackingWithPostalCodeResult trackPackageWithPostalCode(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.trackPackageWithPostalCode(packageID, mailingAgentID, boxNumber, accessToken);
  }
  
  public boolean refreshWebComponent(java.lang.String val) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.refreshWebComponent(val);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GetShipmentHistoryResponseGetShipmentHistoryResult getShipmentHistory(java.lang.String startDate, java.lang.String endDate, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getShipmentHistory(startDate, endDate, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.SearchForProcessedPackageResponseSearchForProcessedPackageResult searchForProcessedPackage(java.lang.String accessToken, java.lang.String packageID, java.lang.String orderID, java.lang.String recipientLastName, java.lang.String recipientFirstName, java.lang.String recipientBusinessName, java.lang.String recipientCountryCode) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.searchForProcessedPackage(accessToken, packageID, orderID, recipientLastName, recipientFirstName, recipientBusinessName, recipientCountryCode);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GetCurrentDispatchResponseGetCurrentDispatchResult getCurrentDispatch(java.lang.String accessToken, java.lang.String destinationLocationID) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getCurrentDispatch(accessToken, destinationLocationID);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GetSystemStatusMessageResponseGetSystemStatusMessageResult getSystemStatusMessage() throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getSystemStatusMessage();
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generatePartialDispatchReport(java.lang.String locationID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.generatePartialDispatchReport(locationID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.ExpectedShipResult getExpectedShipDate(java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.getExpectedShipDate(destinationLocationID, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult setExpectedShipDate(java.lang.String destinationLocationID, java.lang.String expectedShipDate, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.setExpectedShipDate(destinationLocationID, expectedShipDate, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForLegalDescriptionResponseLookupHSForLegalDescriptionResult lookupHSForLegalDescription(java.lang.String countryOfImport, java.lang.String searchText, java.lang.String dateOfImport, java.lang.String classificationType, java.lang.String matchLevel, java.lang.String searchType, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.lookupHSForLegalDescription(countryOfImport, searchText, dateOfImport, classificationType, matchLevel, searchType, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForCommonDescriptionResponseLookupHSForCommonDescriptionResult lookupHSForCommonDescription(java.lang.String searchText, java.lang.String matchLevel, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.lookupHSForCommonDescription(searchText, matchLevel, accessToken);
  }
  
  public com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostResponseCalculateLandedCostResult calculateLandedCost(java.lang.String countryOfImport, java.lang.String countryOfExport, java.lang.String modeOfTransport, java.lang.String incoTerm, java.lang.String taxDetail, java.lang.String dateOfImport, com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostItems items, java.lang.String accessToken) throws java.rmi.RemoteException{
    if (processPackageSoap == null)
      _initProcessPackageSoapProxy();
    return processPackageSoap.calculateLandedCost(countryOfImport, countryOfExport, modeOfTransport, incoTerm, taxDetail, dateOfImport, items, accessToken);
  }
  
  
}
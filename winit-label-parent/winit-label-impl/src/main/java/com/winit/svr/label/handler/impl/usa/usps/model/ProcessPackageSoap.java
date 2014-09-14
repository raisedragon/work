/**
 * ProcessPackageSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public interface ProcessPackageSoap extends java.rmi.Remote {

    /**
     * This method is called first to consume GSS Mailer Web Services.
     * Use the returned access token for each subsequent request.  The token
     * has a session timeout of 20 minutes.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.AuthenticateResult authenticateUser(java.lang.String userID, java.lang.String password, java.lang.String locationID, java.lang.String workstationID) throws java.rmi.RemoteException;

    /**
     * Logs out the user.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult logoutUser(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Retrieves customs label for a specified package ID processed
     * up to 6 months.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LabelResult regeneratePackageCustomsLabel(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String fileFormat, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Retrieves labels for a specified package in JPG or PNG image
     * format.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LabelResult getImageLabelsForPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String fileFormat, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Removes a labeled package from further processing. Used by
     * labeling locations only.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult removeLabeledPackage(java.lang.String packageID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Removes a package from the open dispatch.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult removePackageFromOpenDispatch(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Processes a removed/re-entered or new package.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult processThePackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Processes a package to a destination location. Used by mailers
     * who use multiple destination locations.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult processThePackageToDestinationLocation(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Accepts package data from a mailer in the predefined XML format
     * and processes it.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LoadAndProcessPackageDataResponseLoadAndProcessPackageDataResult loadAndProcessPackageData(com.winit.svr.label.handler.impl.usa.usps.model.LoadAndProcessPackageDataXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Accepts package data from a mailer in the predefined XML format.
     * Overwrites previous version if reloaded.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LoadPackageDataResponseLoadPackageDataResult loadPackageData(com.winit.svr.label.handler.impl.usa.usps.model.LoadPackageDataXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Accepts package data from an originating mailer in the predefined
     * XML format. Used by labeling locations only.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageResponseLoadAndRecordLabeledPackageResult loadAndRecordLabeledPackage(com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Processes a labeled package at consolidator mailer location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult processLabeledPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Retrieves the history of tracking events for a package.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.TrackResult trackPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Closes the open dispatch at the mailer�s location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult closeDispatch(java.lang.String vehicleNum, java.lang.String vehicleType, java.lang.String depDateTime, java.lang.String arrDateTime, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Closes the dispatch to the destination location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult closeDispatchToDestinationLocation(java.lang.String destinationLocationID, java.lang.String vehicleNum, java.lang.String vehicleType, java.lang.String depDateTime, java.lang.String arrDateTime, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Returns a list of all reports that are available for a dispatch.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GetAvailableReportResult getAvailableReportsForDispatch(java.lang.String dispatchID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Returns a list of all required reports that are available for
     * a dispatch.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GetAvailableReportResult getRequiredReportsForDispatch(java.lang.String dispatchID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Generates the requested report for a closed dispatch.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generateReport(java.lang.String dispatchID, java.lang.String reportID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Generates the requested report for a specific mailing agent,
     * location, and time period.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generateActivityReport(java.lang.String mailingAgentID, java.lang.String locationID, java.lang.String startDate, java.lang.String stopDate, java.lang.String reportID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Generates the Volume & Postage report for a specified mailing
     * agent, location, and time period.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generateVolumePostageReport(java.lang.String mailingAgentID, java.lang.String locationID, int includeDestinationCountry, java.lang.String startDate, java.lang.String stopDate, java.lang.String reportID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Opens a receptacle for a destination location. Used only by
     * mailers using receptacles.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult openReceptacle(java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Returns a list of all destination locations that are available
     * for a mailer.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.DestinationLocationsResult getDestinationLocations(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Sets the Receptacle ID and closes the receptacle if the receptacle
     * contains packages (otherwise, deletes it). Used only by mailers using
     * receptacles.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult closeReceptacle(java.lang.String receptacleID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Uploads a package file to the GSS server.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult uploadPackageFile(byte[] fileData, java.lang.String fileName, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Verifies the GXG package for processing.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageResponseVerifyGXGPackageResult verifyGXGPackage(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageGXGRequest GXGRequest, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Verifies the GXG package for processing, with destination location
     * ID as an additional parameter.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageToDestinationLocationResponseVerifyGXGPackageToDestinationLocationResult verifyGXGPackageToDestinationLocation(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String destinationLocationID, com.winit.svr.label.handler.impl.usa.usps.model.VerifyGXGPackageToDestinationLocationGXGRequest GXGRequest, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Returns a list of commodity names used for GXG.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult getGXGCommodityInfo(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Freezes the dispatch for a given location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchResult freezeDispatch(java.lang.String freezeDispatchID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Freezes the dispatch for a given location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchResult freezeDispatchForLocation(java.lang.String freezeDispatchID, java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Closes the frozen dispatch at the mailer�s location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CloseDispatchResult closeFrozenDispatch(java.lang.String freezeDispatchID, java.lang.String vehicleNum, java.lang.String vehicleType, java.lang.String depDateTime, java.lang.String arrDateTime, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Freezes the open dispatch for the mailer�s location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult addPackageToFrozenDispatch(java.lang.String freezeDispatchID, java.lang.String packageID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Removes a package from a frozen dispatch.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult removePackageFromFrozenDispatch(java.lang.String freezeDispatchID, java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Retrieves all frozen dispatches for the location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchesResult retrieveFrozenDispatchesInfo(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Allow a mailer to query for the postage amount, prior to processing
     * a package.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CalculatePostageResult calculatePostage(com.winit.svr.label.handler.impl.usa.usps.model.CalculatePostageXmlDoc xmlDoc, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Retrieves frozen dispatch package details.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.FrozenDispatchPackagesResult retrieveFrozenDispatchPackagesInfo(java.lang.String freezeDispatchID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Retrieves the history of tracking events with the Postal Code
     * for a package.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.TrackingWithPostalCodeResult trackPackageWithPostalCode(java.lang.String packageID, java.lang.String mailingAgentID, int boxNumber, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * For GSS support use only. Refreshes the Web Component.
     */
    public boolean refreshWebComponent(java.lang.String val) throws java.rmi.RemoteException;

    /**
     * Retrieves shipment history for the location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GetShipmentHistoryResponseGetShipmentHistoryResult getShipmentHistory(java.lang.String startDate, java.lang.String endDate, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Search for a processed package.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.SearchForProcessedPackageResponseSearchForProcessedPackageResult searchForProcessedPackage(java.lang.String accessToken, java.lang.String packageID, java.lang.String orderID, java.lang.String recipientLastName, java.lang.String recipientFirstName, java.lang.String recipientBusinessName, java.lang.String recipientCountryCode) throws java.rmi.RemoteException;

    /**
     * Retrieves current dispatch for a location.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GetCurrentDispatchResponseGetCurrentDispatchResult getCurrentDispatch(java.lang.String accessToken, java.lang.String destinationLocationID) throws java.rmi.RemoteException;

    /**
     * Retrieves system status message.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GetSystemStatusMessageResponseGetSystemStatusMessageResult getSystemStatusMessage() throws java.rmi.RemoteException;

    /**
     * Generates Partial Dispatch Report
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GenerateReportResult generatePartialDispatchReport(java.lang.String locationID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get Expected Ship Date
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.ExpectedShipResult getExpectedShipDate(java.lang.String destinationLocationID, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Set Expected Ship Date
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CommonResult setExpectedShipDate(java.lang.String destinationLocationID, java.lang.String expectedShipDate, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Lookup harmonization number for legal description.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForLegalDescriptionResponseLookupHSForLegalDescriptionResult lookupHSForLegalDescription(java.lang.String countryOfImport, java.lang.String searchText, java.lang.String dateOfImport, java.lang.String classificationType, java.lang.String matchLevel, java.lang.String searchType, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Lookup harmonization number for common description.
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForCommonDescriptionResponseLookupHSForCommonDescriptionResult lookupHSForCommonDescription(java.lang.String searchText, java.lang.String matchLevel, java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Calculate Landed Cost
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostResponseCalculateLandedCostResult calculateLandedCost(java.lang.String countryOfImport, java.lang.String countryOfExport, java.lang.String modeOfTransport, java.lang.String incoTerm, java.lang.String taxDetail, java.lang.String dateOfImport, com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostItems items, java.lang.String accessToken) throws java.rmi.RemoteException;
}

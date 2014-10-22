
package com.winit.label.manager.impl.us.usps.intl.model;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.12
 * 2014-09-22T15:51:41.073+08:00
 * Generated source version: 2.7.12
 * 
 */
public final class ProcessPackageSoap_ProcessPackageSoap_Client {

    private static final QName SERVICE_NAME = new QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ProcessPackage");

    private ProcessPackageSoap_ProcessPackageSoap_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ProcessPackage.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ProcessPackage ss = new ProcessPackage(wsdlURL, SERVICE_NAME);
        ProcessPackageSoap port = ss.getProcessPackageSoap();  
        
        {
        System.out.println("Invoking getShipmentHistory...");
        java.lang.String _getShipmentHistory_startDate = "";
        java.lang.String _getShipmentHistory_endDate = "";
        java.lang.String _getShipmentHistory_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GetShipmentHistoryResponse.GetShipmentHistoryResult _getShipmentHistory__return = port.getShipmentHistory(_getShipmentHistory_startDate, _getShipmentHistory_endDate, _getShipmentHistory_accessToken);
        System.out.println("getShipmentHistory.result=" + _getShipmentHistory__return);


        }
        {
        System.out.println("Invoking getDestinationLocations...");
        java.lang.String _getDestinationLocations_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.DestinationLocationsResult _getDestinationLocations__return = port.getDestinationLocations(_getDestinationLocations_accessToken);
        System.out.println("getDestinationLocations.result=" + _getDestinationLocations__return);


        }
        {
        System.out.println("Invoking removePackageFromOpenDispatch...");
        java.lang.String _removePackageFromOpenDispatch_packageID = "";
        java.lang.String _removePackageFromOpenDispatch_mailingAgentID = "";
        int _removePackageFromOpenDispatch_boxNumber = 0;
        java.lang.String _removePackageFromOpenDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _removePackageFromOpenDispatch__return = port.removePackageFromOpenDispatch(_removePackageFromOpenDispatch_packageID, _removePackageFromOpenDispatch_mailingAgentID, _removePackageFromOpenDispatch_boxNumber, _removePackageFromOpenDispatch_accessToken);
        System.out.println("removePackageFromOpenDispatch.result=" + _removePackageFromOpenDispatch__return);


        }
        {
        System.out.println("Invoking retrieveFrozenDispatchPackagesInfo...");
        java.lang.String _retrieveFrozenDispatchPackagesInfo_freezeDispatchID = "";
        java.lang.String _retrieveFrozenDispatchPackagesInfo_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchPackagesResult _retrieveFrozenDispatchPackagesInfo__return = port.retrieveFrozenDispatchPackagesInfo(_retrieveFrozenDispatchPackagesInfo_freezeDispatchID, _retrieveFrozenDispatchPackagesInfo_accessToken);
        System.out.println("retrieveFrozenDispatchPackagesInfo.result=" + _retrieveFrozenDispatchPackagesInfo__return);


        }
        {
        System.out.println("Invoking calculatePostage...");
        com.winit.label.manager.impl.us.usps.intl.model.CalculatePostage.XmlDoc _calculatePostage_xmlDoc = null;
        java.lang.String _calculatePostage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CalculatePostageResult _calculatePostage__return = port.calculatePostage(_calculatePostage_xmlDoc, _calculatePostage_accessToken);
        System.out.println("calculatePostage.result=" + _calculatePostage__return);


        }
        {
        System.out.println("Invoking freezeDispatch...");
        java.lang.String _freezeDispatch_freezeDispatchID = "";
        java.lang.String _freezeDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchResult _freezeDispatch__return = port.freezeDispatch(_freezeDispatch_freezeDispatchID, _freezeDispatch_accessToken);
        System.out.println("freezeDispatch.result=" + _freezeDispatch__return);


        }
        {
        System.out.println("Invoking processThePackageToDestinationLocation...");
        java.lang.String _processThePackageToDestinationLocation_packageID = "";
        java.lang.String _processThePackageToDestinationLocation_mailingAgentID = "";
        int _processThePackageToDestinationLocation_boxNumber = 0;
        java.lang.String _processThePackageToDestinationLocation_destinationLocationID = "";
        java.lang.String _processThePackageToDestinationLocation_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _processThePackageToDestinationLocation__return = port.processThePackageToDestinationLocation(_processThePackageToDestinationLocation_packageID, _processThePackageToDestinationLocation_mailingAgentID, _processThePackageToDestinationLocation_boxNumber, _processThePackageToDestinationLocation_destinationLocationID, _processThePackageToDestinationLocation_accessToken);
        System.out.println("processThePackageToDestinationLocation.result=" + _processThePackageToDestinationLocation__return);


        }
        {
        System.out.println("Invoking loadAndProcessPackageData...");
        com.winit.label.manager.impl.us.usps.intl.model.LoadAndProcessPackageData.XmlDoc _loadAndProcessPackageData_xmlDoc = null;
        java.lang.String _loadAndProcessPackageData_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LoadAndProcessPackageDataResponse.LoadAndProcessPackageDataResult _loadAndProcessPackageData__return = port.loadAndProcessPackageData(_loadAndProcessPackageData_xmlDoc, _loadAndProcessPackageData_accessToken);
        System.out.println("loadAndProcessPackageData.result=" + _loadAndProcessPackageData__return);


        }
        {
        System.out.println("Invoking generateActivityReport...");
        java.lang.String _generateActivityReport_mailingAgentID = "";
        java.lang.String _generateActivityReport_locationID = "";
        java.lang.String _generateActivityReport_startDate = "";
        java.lang.String _generateActivityReport_stopDate = "";
        java.lang.String _generateActivityReport_reportID = "";
        java.lang.String _generateActivityReport_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GenerateReportResult _generateActivityReport__return = port.generateActivityReport(_generateActivityReport_mailingAgentID, _generateActivityReport_locationID, _generateActivityReport_startDate, _generateActivityReport_stopDate, _generateActivityReport_reportID, _generateActivityReport_accessToken);
        System.out.println("generateActivityReport.result=" + _generateActivityReport__return);


        }
        {
        System.out.println("Invoking refreshWebComponent...");
        java.lang.String _refreshWebComponent_val = "";
        boolean _refreshWebComponent__return = port.refreshWebComponent(_refreshWebComponent_val);
        System.out.println("refreshWebComponent.result=" + _refreshWebComponent__return);


        }
        {
        System.out.println("Invoking trackPackage...");
        java.lang.String _trackPackage_packageID = "";
        java.lang.String _trackPackage_mailingAgentID = "";
        int _trackPackage_boxNumber = 0;
        java.lang.String _trackPackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.TrackResult _trackPackage__return = port.trackPackage(_trackPackage_packageID, _trackPackage_mailingAgentID, _trackPackage_boxNumber, _trackPackage_accessToken);
        System.out.println("trackPackage.result=" + _trackPackage__return);


        }
        {
        System.out.println("Invoking getImageLabelsForPackage...");
        java.lang.String _getImageLabelsForPackage_packageID = "";
        java.lang.String _getImageLabelsForPackage_mailingAgentID = "";
        int _getImageLabelsForPackage_boxNumber = 0;
        java.lang.String _getImageLabelsForPackage_fileFormat = "";
        java.lang.String _getImageLabelsForPackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LabelResult _getImageLabelsForPackage__return = port.getImageLabelsForPackage(_getImageLabelsForPackage_packageID, _getImageLabelsForPackage_mailingAgentID, _getImageLabelsForPackage_boxNumber, _getImageLabelsForPackage_fileFormat, _getImageLabelsForPackage_accessToken);
        System.out.println("getImageLabelsForPackage.result=" + _getImageLabelsForPackage__return);


        }
        {
        System.out.println("Invoking freezeDispatchForLocation...");
        java.lang.String _freezeDispatchForLocation_freezeDispatchID = "";
        java.lang.String _freezeDispatchForLocation_destinationLocationID = "";
        java.lang.String _freezeDispatchForLocation_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchResult _freezeDispatchForLocation__return = port.freezeDispatchForLocation(_freezeDispatchForLocation_freezeDispatchID, _freezeDispatchForLocation_destinationLocationID, _freezeDispatchForLocation_accessToken);
        System.out.println("freezeDispatchForLocation.result=" + _freezeDispatchForLocation__return);


        }
        {
        System.out.println("Invoking removePackageFromFrozenDispatch...");
        java.lang.String _removePackageFromFrozenDispatch_freezeDispatchID = "";
        java.lang.String _removePackageFromFrozenDispatch_packageID = "";
        java.lang.String _removePackageFromFrozenDispatch_mailingAgentID = "";
        int _removePackageFromFrozenDispatch_boxNumber = 0;
        java.lang.String _removePackageFromFrozenDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _removePackageFromFrozenDispatch__return = port.removePackageFromFrozenDispatch(_removePackageFromFrozenDispatch_freezeDispatchID, _removePackageFromFrozenDispatch_packageID, _removePackageFromFrozenDispatch_mailingAgentID, _removePackageFromFrozenDispatch_boxNumber, _removePackageFromFrozenDispatch_accessToken);
        System.out.println("removePackageFromFrozenDispatch.result=" + _removePackageFromFrozenDispatch__return);


        }
        {
        System.out.println("Invoking getRequiredReportsForDispatch...");
        java.lang.String _getRequiredReportsForDispatch_dispatchID = "";
        java.lang.String _getRequiredReportsForDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GetAvailableReportResult _getRequiredReportsForDispatch__return = port.getRequiredReportsForDispatch(_getRequiredReportsForDispatch_dispatchID, _getRequiredReportsForDispatch_accessToken);
        System.out.println("getRequiredReportsForDispatch.result=" + _getRequiredReportsForDispatch__return);


        }
        {
        System.out.println("Invoking searchForProcessedPackage...");
        java.lang.String _searchForProcessedPackage_accessToken = "";
        java.lang.String _searchForProcessedPackage_packageID = "";
        java.lang.String _searchForProcessedPackage_orderID = "";
        java.lang.String _searchForProcessedPackage_recipientLastName = "";
        java.lang.String _searchForProcessedPackage_recipientFirstName = "";
        java.lang.String _searchForProcessedPackage_recipientBusinessName = "";
        java.lang.String _searchForProcessedPackage_recipientCountryCode = "";
        com.winit.label.manager.impl.us.usps.intl.model.SearchForProcessedPackageResponse.SearchForProcessedPackageResult _searchForProcessedPackage__return = port.searchForProcessedPackage(_searchForProcessedPackage_accessToken, _searchForProcessedPackage_packageID, _searchForProcessedPackage_orderID, _searchForProcessedPackage_recipientLastName, _searchForProcessedPackage_recipientFirstName, _searchForProcessedPackage_recipientBusinessName, _searchForProcessedPackage_recipientCountryCode);
        System.out.println("searchForProcessedPackage.result=" + _searchForProcessedPackage__return);


        }
        {
        System.out.println("Invoking logoutUser...");
        java.lang.String _logoutUser_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _logoutUser__return = port.logoutUser(_logoutUser_accessToken);
        System.out.println("logoutUser.result=" + _logoutUser__return);


        }
        {
        System.out.println("Invoking getCurrentDispatch...");
        java.lang.String _getCurrentDispatch_accessToken = "";
        java.lang.String _getCurrentDispatch_destinationLocationID = "";
        com.winit.label.manager.impl.us.usps.intl.model.GetCurrentDispatchResponse.GetCurrentDispatchResult _getCurrentDispatch__return = port.getCurrentDispatch(_getCurrentDispatch_accessToken, _getCurrentDispatch_destinationLocationID);
        System.out.println("getCurrentDispatch.result=" + _getCurrentDispatch__return);


        }
        {
        System.out.println("Invoking verifyGXGPackageToDestinationLocation...");
        java.lang.String _verifyGXGPackageToDestinationLocation_packageID = "";
        java.lang.String _verifyGXGPackageToDestinationLocation_mailingAgentID = "";
        int _verifyGXGPackageToDestinationLocation_boxNumber = 0;
        java.lang.String _verifyGXGPackageToDestinationLocation_destinationLocationID = "";
        com.winit.label.manager.impl.us.usps.intl.model.VerifyGXGPackageToDestinationLocation.GXGRequest _verifyGXGPackageToDestinationLocation_gxgRequest = null;
        java.lang.String _verifyGXGPackageToDestinationLocation_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.VerifyGXGPackageToDestinationLocationResponse.VerifyGXGPackageToDestinationLocationResult _verifyGXGPackageToDestinationLocation__return = port.verifyGXGPackageToDestinationLocation(_verifyGXGPackageToDestinationLocation_packageID, _verifyGXGPackageToDestinationLocation_mailingAgentID, _verifyGXGPackageToDestinationLocation_boxNumber, _verifyGXGPackageToDestinationLocation_destinationLocationID, _verifyGXGPackageToDestinationLocation_gxgRequest, _verifyGXGPackageToDestinationLocation_accessToken);
        System.out.println("verifyGXGPackageToDestinationLocation.result=" + _verifyGXGPackageToDestinationLocation__return);


        }
        {
        System.out.println("Invoking getExpectedShipDate...");
        java.lang.String _getExpectedShipDate_destinationLocationID = "";
        java.lang.String _getExpectedShipDate_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.ExpectedShipResult _getExpectedShipDate__return = port.getExpectedShipDate(_getExpectedShipDate_destinationLocationID, _getExpectedShipDate_accessToken);
        System.out.println("getExpectedShipDate.result=" + _getExpectedShipDate__return);


        }
        {
        System.out.println("Invoking loadPackageData...");
        com.winit.label.manager.impl.us.usps.intl.model.LoadPackageData.XmlDoc _loadPackageData_xmlDoc = null;
        java.lang.String _loadPackageData_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LoadPackageDataResponse.LoadPackageDataResult _loadPackageData__return = port.loadPackageData(_loadPackageData_xmlDoc, _loadPackageData_accessToken);
        System.out.println("loadPackageData.result=" + _loadPackageData__return);


        }
        {
        System.out.println("Invoking removeLabeledPackage...");
        java.lang.String _removeLabeledPackage_packageID = "";
        int _removeLabeledPackage_boxNumber = 0;
        java.lang.String _removeLabeledPackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _removeLabeledPackage__return = port.removeLabeledPackage(_removeLabeledPackage_packageID, _removeLabeledPackage_boxNumber, _removeLabeledPackage_accessToken);
        System.out.println("removeLabeledPackage.result=" + _removeLabeledPackage__return);


        }
        {
        System.out.println("Invoking generateVolumePostageReport...");
        java.lang.String _generateVolumePostageReport_mailingAgentID = "";
        java.lang.String _generateVolumePostageReport_locationID = "";
        int _generateVolumePostageReport_includeDestinationCountry = 0;
        java.lang.String _generateVolumePostageReport_startDate = "";
        java.lang.String _generateVolumePostageReport_stopDate = "";
        java.lang.String _generateVolumePostageReport_reportID = "";
        java.lang.String _generateVolumePostageReport_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GenerateReportResult _generateVolumePostageReport__return = port.generateVolumePostageReport(_generateVolumePostageReport_mailingAgentID, _generateVolumePostageReport_locationID, _generateVolumePostageReport_includeDestinationCountry, _generateVolumePostageReport_startDate, _generateVolumePostageReport_stopDate, _generateVolumePostageReport_reportID, _generateVolumePostageReport_accessToken);
        System.out.println("generateVolumePostageReport.result=" + _generateVolumePostageReport__return);


        }
        {
        System.out.println("Invoking addPackageToFrozenDispatch...");
        java.lang.String _addPackageToFrozenDispatch_freezeDispatchID = "";
        java.lang.String _addPackageToFrozenDispatch_packageID = "";
        java.lang.String _addPackageToFrozenDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _addPackageToFrozenDispatch__return = port.addPackageToFrozenDispatch(_addPackageToFrozenDispatch_freezeDispatchID, _addPackageToFrozenDispatch_packageID, _addPackageToFrozenDispatch_accessToken);
        System.out.println("addPackageToFrozenDispatch.result=" + _addPackageToFrozenDispatch__return);


        }
        {
        System.out.println("Invoking retrieveFrozenDispatchesInfo...");
        java.lang.String _retrieveFrozenDispatchesInfo_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.FrozenDispatchesResult _retrieveFrozenDispatchesInfo__return = port.retrieveFrozenDispatchesInfo(_retrieveFrozenDispatchesInfo_accessToken);
        System.out.println("retrieveFrozenDispatchesInfo.result=" + _retrieveFrozenDispatchesInfo__return);


        }
        {
        System.out.println("Invoking setExpectedShipDate...");
        java.lang.String _setExpectedShipDate_destinationLocationID = "";
        java.lang.String _setExpectedShipDate_expectedShipDate = "";
        java.lang.String _setExpectedShipDate_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _setExpectedShipDate__return = port.setExpectedShipDate(_setExpectedShipDate_destinationLocationID, _setExpectedShipDate_expectedShipDate, _setExpectedShipDate_accessToken);
        System.out.println("setExpectedShipDate.result=" + _setExpectedShipDate__return);


        }
        {
        System.out.println("Invoking verifyGXGPackage...");
        java.lang.String _verifyGXGPackage_packageID = "";
        java.lang.String _verifyGXGPackage_mailingAgentID = "";
        int _verifyGXGPackage_boxNumber = 0;
        com.winit.label.manager.impl.us.usps.intl.model.VerifyGXGPackage.GXGRequest _verifyGXGPackage_gxgRequest = null;
        java.lang.String _verifyGXGPackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.VerifyGXGPackageResponse.VerifyGXGPackageResult _verifyGXGPackage__return = port.verifyGXGPackage(_verifyGXGPackage_packageID, _verifyGXGPackage_mailingAgentID, _verifyGXGPackage_boxNumber, _verifyGXGPackage_gxgRequest, _verifyGXGPackage_accessToken);
        System.out.println("verifyGXGPackage.result=" + _verifyGXGPackage__return);


        }
        {
        System.out.println("Invoking getAvailableReportsForDispatch...");
        java.lang.String _getAvailableReportsForDispatch_dispatchID = "";
        java.lang.String _getAvailableReportsForDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GetAvailableReportResult _getAvailableReportsForDispatch__return = port.getAvailableReportsForDispatch(_getAvailableReportsForDispatch_dispatchID, _getAvailableReportsForDispatch_accessToken);
        System.out.println("getAvailableReportsForDispatch.result=" + _getAvailableReportsForDispatch__return);


        }
        {
        System.out.println("Invoking lookupHSForCommonDescription...");
        java.lang.String _lookupHSForCommonDescription_searchText = "";
        java.lang.String _lookupHSForCommonDescription_matchLevel = "";
        java.lang.String _lookupHSForCommonDescription_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LookupHSForCommonDescriptionResponse.LookupHSForCommonDescriptionResult _lookupHSForCommonDescription__return = port.lookupHSForCommonDescription(_lookupHSForCommonDescription_searchText, _lookupHSForCommonDescription_matchLevel, _lookupHSForCommonDescription_accessToken);
        System.out.println("lookupHSForCommonDescription.result=" + _lookupHSForCommonDescription__return);


        }
        {
        System.out.println("Invoking lookupHSForLegalDescription...");
        java.lang.String _lookupHSForLegalDescription_countryOfImport = "";
        java.lang.String _lookupHSForLegalDescription_searchText = "";
        java.lang.String _lookupHSForLegalDescription_dateOfImport = "";
        java.lang.String _lookupHSForLegalDescription_classificationType = "";
        java.lang.String _lookupHSForLegalDescription_matchLevel = "";
        java.lang.String _lookupHSForLegalDescription_searchType = "";
        java.lang.String _lookupHSForLegalDescription_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LookupHSForLegalDescriptionResponse.LookupHSForLegalDescriptionResult _lookupHSForLegalDescription__return = port.lookupHSForLegalDescription(_lookupHSForLegalDescription_countryOfImport, _lookupHSForLegalDescription_searchText, _lookupHSForLegalDescription_dateOfImport, _lookupHSForLegalDescription_classificationType, _lookupHSForLegalDescription_matchLevel, _lookupHSForLegalDescription_searchType, _lookupHSForLegalDescription_accessToken);
        System.out.println("lookupHSForLegalDescription.result=" + _lookupHSForLegalDescription__return);


        }
        {
        System.out.println("Invoking generateReport...");
        java.lang.String _generateReport_dispatchID = "";
        java.lang.String _generateReport_reportID = "";
        java.lang.String _generateReport_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GenerateReportResult _generateReport__return = port.generateReport(_generateReport_dispatchID, _generateReport_reportID, _generateReport_accessToken);
        System.out.println("generateReport.result=" + _generateReport__return);


        }
        {
        System.out.println("Invoking closeReceptacle...");
        java.lang.String _closeReceptacle_receptacleID = "";
        java.lang.String _closeReceptacle_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _closeReceptacle__return = port.closeReceptacle(_closeReceptacle_receptacleID, _closeReceptacle_accessToken);
        System.out.println("closeReceptacle.result=" + _closeReceptacle__return);


        }
        {
        System.out.println("Invoking processLabeledPackage...");
        java.lang.String _processLabeledPackage_packageID = "";
        java.lang.String _processLabeledPackage_mailingAgentID = "";
        int _processLabeledPackage_boxNumber = 0;
        java.lang.String _processLabeledPackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _processLabeledPackage__return = port.processLabeledPackage(_processLabeledPackage_packageID, _processLabeledPackage_mailingAgentID, _processLabeledPackage_boxNumber, _processLabeledPackage_accessToken);
        System.out.println("processLabeledPackage.result=" + _processLabeledPackage__return);


        }
        {
        System.out.println("Invoking processThePackage...");
        java.lang.String _processThePackage_packageID = "";
        java.lang.String _processThePackage_mailingAgentID = "";
        int _processThePackage_boxNumber = 0;
        java.lang.String _processThePackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _processThePackage__return = port.processThePackage(_processThePackage_packageID, _processThePackage_mailingAgentID, _processThePackage_boxNumber, _processThePackage_accessToken);
        System.out.println("processThePackage.result=" + _processThePackage__return);


        }
        {
        System.out.println("Invoking trackPackageWithPostalCode...");
        java.lang.String _trackPackageWithPostalCode_packageID = "";
        java.lang.String _trackPackageWithPostalCode_mailingAgentID = "";
        int _trackPackageWithPostalCode_boxNumber = 0;
        java.lang.String _trackPackageWithPostalCode_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.TrackingWithPostalCodeResult _trackPackageWithPostalCode__return = port.trackPackageWithPostalCode(_trackPackageWithPostalCode_packageID, _trackPackageWithPostalCode_mailingAgentID, _trackPackageWithPostalCode_boxNumber, _trackPackageWithPostalCode_accessToken);
        System.out.println("trackPackageWithPostalCode.result=" + _trackPackageWithPostalCode__return);


        }
        {
        System.out.println("Invoking uploadPackageFile...");
        byte[] _uploadPackageFile_fileData = new byte[0];
        java.lang.String _uploadPackageFile_fileName = "";
        java.lang.String _uploadPackageFile_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _uploadPackageFile__return = port.uploadPackageFile(_uploadPackageFile_fileData, _uploadPackageFile_fileName, _uploadPackageFile_accessToken);
        System.out.println("uploadPackageFile.result=" + _uploadPackageFile__return);


        }
        {
        System.out.println("Invoking loadAndRecordLabeledPackage...");
        com.winit.label.manager.impl.us.usps.intl.model.LoadAndRecordLabeledPackage.XmlDoc _loadAndRecordLabeledPackage_xmlDoc = null;
        java.lang.String _loadAndRecordLabeledPackage_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LoadAndRecordLabeledPackageResponse.LoadAndRecordLabeledPackageResult _loadAndRecordLabeledPackage__return = port.loadAndRecordLabeledPackage(_loadAndRecordLabeledPackage_xmlDoc, _loadAndRecordLabeledPackage_accessToken);
        System.out.println("loadAndRecordLabeledPackage.result=" + _loadAndRecordLabeledPackage__return);


        }
        {
        System.out.println("Invoking authenticateUser...");
        java.lang.String _authenticateUser_userID = "";
        java.lang.String _authenticateUser_password = "";
        java.lang.String _authenticateUser_locationID = "";
        java.lang.String _authenticateUser_workstationID = "";
        com.winit.label.manager.impl.us.usps.intl.model.AuthenticateResult _authenticateUser__return = port.authenticateUser(_authenticateUser_userID, _authenticateUser_password, _authenticateUser_locationID, _authenticateUser_workstationID);
        System.out.println("authenticateUser.result=" + _authenticateUser__return);


        }
        {
        System.out.println("Invoking getSystemStatusMessage...");
        com.winit.label.manager.impl.us.usps.intl.model.GetSystemStatusMessageResponse.GetSystemStatusMessageResult _getSystemStatusMessage__return = port.getSystemStatusMessage();
        System.out.println("getSystemStatusMessage.result=" + _getSystemStatusMessage__return);


        }
        {
        System.out.println("Invoking generatePartialDispatchReport...");
        java.lang.String _generatePartialDispatchReport_locationID = "";
        java.lang.String _generatePartialDispatchReport_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GenerateReportResult _generatePartialDispatchReport__return = port.generatePartialDispatchReport(_generatePartialDispatchReport_locationID, _generatePartialDispatchReport_accessToken);
        System.out.println("generatePartialDispatchReport.result=" + _generatePartialDispatchReport__return);


        }
        {
        System.out.println("Invoking regeneratePackageCustomsLabel...");
        java.lang.String _regeneratePackageCustomsLabel_packageID = "";
        java.lang.String _regeneratePackageCustomsLabel_mailingAgentID = "";
        int _regeneratePackageCustomsLabel_boxNumber = 0;
        java.lang.String _regeneratePackageCustomsLabel_fileFormat = "";
        java.lang.String _regeneratePackageCustomsLabel_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.LabelResult _regeneratePackageCustomsLabel__return = port.regeneratePackageCustomsLabel(_regeneratePackageCustomsLabel_packageID, _regeneratePackageCustomsLabel_mailingAgentID, _regeneratePackageCustomsLabel_boxNumber, _regeneratePackageCustomsLabel_fileFormat, _regeneratePackageCustomsLabel_accessToken);
        System.out.println("regeneratePackageCustomsLabel.result=" + _regeneratePackageCustomsLabel__return);


        }
        {
        System.out.println("Invoking calculateLandedCost...");
        java.lang.String _calculateLandedCost_countryOfImport = "";
        java.lang.String _calculateLandedCost_countryOfExport = "";
        java.lang.String _calculateLandedCost_modeOfTransport = "";
        java.lang.String _calculateLandedCost_incoTerm = "";
        java.lang.String _calculateLandedCost_taxDetail = "";
        java.lang.String _calculateLandedCost_dateOfImport = "";
        com.winit.label.manager.impl.us.usps.intl.model.CalculateLandedCost.Items _calculateLandedCost_items = null;
        java.lang.String _calculateLandedCost_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CalculateLandedCostResponse.CalculateLandedCostResult _calculateLandedCost__return = port.calculateLandedCost(_calculateLandedCost_countryOfImport, _calculateLandedCost_countryOfExport, _calculateLandedCost_modeOfTransport, _calculateLandedCost_incoTerm, _calculateLandedCost_taxDetail, _calculateLandedCost_dateOfImport, _calculateLandedCost_items, _calculateLandedCost_accessToken);
        System.out.println("calculateLandedCost.result=" + _calculateLandedCost__return);


        }
        {
        System.out.println("Invoking closeFrozenDispatch...");
        java.lang.String _closeFrozenDispatch_freezeDispatchID = "";
        java.lang.String _closeFrozenDispatch_vehicleNum = "";
        java.lang.String _closeFrozenDispatch_vehicleType = "";
        java.lang.String _closeFrozenDispatch_depDateTime = "";
        java.lang.String _closeFrozenDispatch_arrDateTime = "";
        java.lang.String _closeFrozenDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CloseDispatchResult _closeFrozenDispatch__return = port.closeFrozenDispatch(_closeFrozenDispatch_freezeDispatchID, _closeFrozenDispatch_vehicleNum, _closeFrozenDispatch_vehicleType, _closeFrozenDispatch_depDateTime, _closeFrozenDispatch_arrDateTime, _closeFrozenDispatch_accessToken);
        System.out.println("closeFrozenDispatch.result=" + _closeFrozenDispatch__return);


        }
        {
        System.out.println("Invoking openReceptacle...");
        java.lang.String _openReceptacle_destinationLocationID = "";
        java.lang.String _openReceptacle_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CommonResult _openReceptacle__return = port.openReceptacle(_openReceptacle_destinationLocationID, _openReceptacle_accessToken);
        System.out.println("openReceptacle.result=" + _openReceptacle__return);


        }
        {
        System.out.println("Invoking getGXGCommodityInfo...");
        java.lang.String _getGXGCommodityInfo_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.GXGCommodityInfoResult _getGXGCommodityInfo__return = port.getGXGCommodityInfo(_getGXGCommodityInfo_accessToken);
        System.out.println("getGXGCommodityInfo.result=" + _getGXGCommodityInfo__return);


        }
        {
        System.out.println("Invoking closeDispatch...");
        java.lang.String _closeDispatch_vehicleNum = "";
        java.lang.String _closeDispatch_vehicleType = "";
        java.lang.String _closeDispatch_depDateTime = "";
        java.lang.String _closeDispatch_arrDateTime = "";
        java.lang.String _closeDispatch_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CloseDispatchResult _closeDispatch__return = port.closeDispatch(_closeDispatch_vehicleNum, _closeDispatch_vehicleType, _closeDispatch_depDateTime, _closeDispatch_arrDateTime, _closeDispatch_accessToken);
        System.out.println("closeDispatch.result=" + _closeDispatch__return);


        }
        {
        System.out.println("Invoking closeDispatchToDestinationLocation...");
        java.lang.String _closeDispatchToDestinationLocation_destinationLocationID = "";
        java.lang.String _closeDispatchToDestinationLocation_vehicleNum = "";
        java.lang.String _closeDispatchToDestinationLocation_vehicleType = "";
        java.lang.String _closeDispatchToDestinationLocation_depDateTime = "";
        java.lang.String _closeDispatchToDestinationLocation_arrDateTime = "";
        java.lang.String _closeDispatchToDestinationLocation_accessToken = "";
        com.winit.label.manager.impl.us.usps.intl.model.CloseDispatchResult _closeDispatchToDestinationLocation__return = port.closeDispatchToDestinationLocation(_closeDispatchToDestinationLocation_destinationLocationID, _closeDispatchToDestinationLocation_vehicleNum, _closeDispatchToDestinationLocation_vehicleType, _closeDispatchToDestinationLocation_depDateTime, _closeDispatchToDestinationLocation_arrDateTime, _closeDispatchToDestinationLocation_accessToken);
        System.out.println("closeDispatchToDestinationLocation.result=" + _closeDispatchToDestinationLocation__return);


        }

        System.exit(0);
    }

}

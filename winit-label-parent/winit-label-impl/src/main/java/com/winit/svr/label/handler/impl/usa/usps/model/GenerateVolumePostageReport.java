/**
 * GenerateVolumePostageReport.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class GenerateVolumePostageReport  implements java.io.Serializable {
    private java.lang.String mailingAgentID;

    private java.lang.String locationID;

    private int includeDestinationCountry;

    private java.lang.String startDate;

    private java.lang.String stopDate;

    private java.lang.String reportID;

    private java.lang.String accessToken;

    public GenerateVolumePostageReport() {
    }

    public GenerateVolumePostageReport(
           java.lang.String mailingAgentID,
           java.lang.String locationID,
           int includeDestinationCountry,
           java.lang.String startDate,
           java.lang.String stopDate,
           java.lang.String reportID,
           java.lang.String accessToken) {
           this.mailingAgentID = mailingAgentID;
           this.locationID = locationID;
           this.includeDestinationCountry = includeDestinationCountry;
           this.startDate = startDate;
           this.stopDate = stopDate;
           this.reportID = reportID;
           this.accessToken = accessToken;
    }


    /**
     * Gets the mailingAgentID value for this GenerateVolumePostageReport.
     * 
     * @return mailingAgentID
     */
    public java.lang.String getMailingAgentID() {
        return mailingAgentID;
    }


    /**
     * Sets the mailingAgentID value for this GenerateVolumePostageReport.
     * 
     * @param mailingAgentID
     */
    public void setMailingAgentID(java.lang.String mailingAgentID) {
        this.mailingAgentID = mailingAgentID;
    }


    /**
     * Gets the locationID value for this GenerateVolumePostageReport.
     * 
     * @return locationID
     */
    public java.lang.String getLocationID() {
        return locationID;
    }


    /**
     * Sets the locationID value for this GenerateVolumePostageReport.
     * 
     * @param locationID
     */
    public void setLocationID(java.lang.String locationID) {
        this.locationID = locationID;
    }


    /**
     * Gets the includeDestinationCountry value for this GenerateVolumePostageReport.
     * 
     * @return includeDestinationCountry
     */
    public int getIncludeDestinationCountry() {
        return includeDestinationCountry;
    }


    /**
     * Sets the includeDestinationCountry value for this GenerateVolumePostageReport.
     * 
     * @param includeDestinationCountry
     */
    public void setIncludeDestinationCountry(int includeDestinationCountry) {
        this.includeDestinationCountry = includeDestinationCountry;
    }


    /**
     * Gets the startDate value for this GenerateVolumePostageReport.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this GenerateVolumePostageReport.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the stopDate value for this GenerateVolumePostageReport.
     * 
     * @return stopDate
     */
    public java.lang.String getStopDate() {
        return stopDate;
    }


    /**
     * Sets the stopDate value for this GenerateVolumePostageReport.
     * 
     * @param stopDate
     */
    public void setStopDate(java.lang.String stopDate) {
        this.stopDate = stopDate;
    }


    /**
     * Gets the reportID value for this GenerateVolumePostageReport.
     * 
     * @return reportID
     */
    public java.lang.String getReportID() {
        return reportID;
    }


    /**
     * Sets the reportID value for this GenerateVolumePostageReport.
     * 
     * @param reportID
     */
    public void setReportID(java.lang.String reportID) {
        this.reportID = reportID;
    }


    /**
     * Gets the accessToken value for this GenerateVolumePostageReport.
     * 
     * @return accessToken
     */
    public java.lang.String getAccessToken() {
        return accessToken;
    }


    /**
     * Sets the accessToken value for this GenerateVolumePostageReport.
     * 
     * @param accessToken
     */
    public void setAccessToken(java.lang.String accessToken) {
        this.accessToken = accessToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerateVolumePostageReport)) return false;
        GenerateVolumePostageReport other = (GenerateVolumePostageReport) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mailingAgentID==null && other.getMailingAgentID()==null) || 
             (this.mailingAgentID!=null &&
              this.mailingAgentID.equals(other.getMailingAgentID()))) &&
            ((this.locationID==null && other.getLocationID()==null) || 
             (this.locationID!=null &&
              this.locationID.equals(other.getLocationID()))) &&
            this.includeDestinationCountry == other.getIncludeDestinationCountry() &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.stopDate==null && other.getStopDate()==null) || 
             (this.stopDate!=null &&
              this.stopDate.equals(other.getStopDate()))) &&
            ((this.reportID==null && other.getReportID()==null) || 
             (this.reportID!=null &&
              this.reportID.equals(other.getReportID()))) &&
            ((this.accessToken==null && other.getAccessToken()==null) || 
             (this.accessToken!=null &&
              this.accessToken.equals(other.getAccessToken())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMailingAgentID() != null) {
            _hashCode += getMailingAgentID().hashCode();
        }
        if (getLocationID() != null) {
            _hashCode += getLocationID().hashCode();
        }
        _hashCode += getIncludeDestinationCountry();
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getStopDate() != null) {
            _hashCode += getStopDate().hashCode();
        }
        if (getReportID() != null) {
            _hashCode += getReportID().hashCode();
        }
        if (getAccessToken() != null) {
            _hashCode += getAccessToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerateVolumePostageReport.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">GenerateVolumePostageReport"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAgentID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "MailingAgentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "LocationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeDestinationCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "IncludeDestinationCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stopDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "StopDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ReportID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "AccessToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

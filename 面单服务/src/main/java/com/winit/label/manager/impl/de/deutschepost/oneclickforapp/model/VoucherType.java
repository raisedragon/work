/**
 * VoucherType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class VoucherType  implements java.io.Serializable {
    private java.lang.String voucherId;

    private java.lang.String trackId;

    public VoucherType() {
    }

    public VoucherType(
           java.lang.String voucherId,
           java.lang.String trackId) {
           this.voucherId = voucherId;
           this.trackId = trackId;
    }


    /**
     * Gets the voucherId value for this VoucherType.
     * 
     * @return voucherId
     */
    public java.lang.String getVoucherId() {
        return voucherId;
    }


    /**
     * Sets the voucherId value for this VoucherType.
     * 
     * @param voucherId
     */
    public void setVoucherId(java.lang.String voucherId) {
        this.voucherId = voucherId;
    }


    /**
     * Gets the trackId value for this VoucherType.
     * 
     * @return trackId
     */
    public java.lang.String getTrackId() {
        return trackId;
    }


    /**
     * Sets the trackId value for this VoucherType.
     * 
     * @param trackId
     */
    public void setTrackId(java.lang.String trackId) {
        this.trackId = trackId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VoucherType)) return false;
        VoucherType other = (VoucherType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.voucherId==null && other.getVoucherId()==null) || 
             (this.voucherId!=null &&
              this.voucherId.equals(other.getVoucherId()))) &&
            ((this.trackId==null && other.getTrackId()==null) || 
             (this.trackId!=null &&
              this.trackId.equals(other.getTrackId())));
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
        if (getVoucherId() != null) {
            _hashCode += getVoucherId().hashCode();
        }
        if (getTrackId() != null) {
            _hashCode += getTrackId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VoucherType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("voucherId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "voucherId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "trackId"));
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

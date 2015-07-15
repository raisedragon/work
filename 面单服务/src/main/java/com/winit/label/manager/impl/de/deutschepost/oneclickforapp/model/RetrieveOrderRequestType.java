/**
 * RetrieveOrderRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Request to retrieve an order that has already been placed
 */
public class RetrieveOrderRequestType  implements java.io.Serializable {
    /* Unique user ID */
    private java.lang.String userToken;

    /* Shop order ID. */
    private java.lang.String shopOrderId;

    public RetrieveOrderRequestType() {
    }

    public RetrieveOrderRequestType(
           java.lang.String userToken,
           java.lang.String shopOrderId) {
           this.userToken = userToken;
           this.shopOrderId = shopOrderId;
    }


    /**
     * Gets the userToken value for this RetrieveOrderRequestType.
     * 
     * @return userToken   * Unique user ID
     */
    public java.lang.String getUserToken() {
        return userToken;
    }


    /**
     * Sets the userToken value for this RetrieveOrderRequestType.
     * 
     * @param userToken   * Unique user ID
     */
    public void setUserToken(java.lang.String userToken) {
        this.userToken = userToken;
    }


    /**
     * Gets the shopOrderId value for this RetrieveOrderRequestType.
     * 
     * @return shopOrderId   * Shop order ID.
     */
    public java.lang.String getShopOrderId() {
        return shopOrderId;
    }


    /**
     * Sets the shopOrderId value for this RetrieveOrderRequestType.
     * 
     * @param shopOrderId   * Shop order ID.
     */
    public void setShopOrderId(java.lang.String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveOrderRequestType)) return false;
        RetrieveOrderRequestType other = (RetrieveOrderRequestType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userToken==null && other.getUserToken()==null) || 
             (this.userToken!=null &&
              this.userToken.equals(other.getUserToken()))) &&
            ((this.shopOrderId==null && other.getShopOrderId()==null) || 
             (this.shopOrderId!=null &&
              this.shopOrderId.equals(other.getShopOrderId())));
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
        if (getUserToken() != null) {
            _hashCode += getUserToken().hashCode();
        }
        if (getShopOrderId() != null) {
            _hashCode += getShopOrderId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveOrderRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "userToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopOrderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "shopOrderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

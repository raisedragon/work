/**
 * ShoppingCartValidationErrorInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Defines a single error message when validating the shopping cart
 */
public class ShoppingCartValidationErrorInfo  implements java.io.Serializable {
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorCodes id;

    private java.lang.String message;

    public ShoppingCartValidationErrorInfo() {
    }

    public ShoppingCartValidationErrorInfo(
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorCodes id,
           java.lang.String message) {
           this.id = id;
           this.message = message;
    }


    /**
     * Gets the id value for this ShoppingCartValidationErrorInfo.
     * 
     * @return id
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorCodes getId() {
        return id;
    }


    /**
     * Sets the id value for this ShoppingCartValidationErrorInfo.
     * 
     * @param id
     */
    public void setId(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorCodes id) {
        this.id = id;
    }


    /**
     * Gets the message value for this ShoppingCartValidationErrorInfo.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ShoppingCartValidationErrorInfo.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartValidationErrorInfo)) return false;
        ShoppingCartValidationErrorInfo other = (ShoppingCartValidationErrorInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartValidationErrorInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationErrorInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationErrorCodes"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "message"));
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

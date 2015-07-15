/**
 * ShoppingCartValidationException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Defines the error message when validating the shopping cart
 */
public class ShoppingCartValidationException  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private java.lang.String message1;

    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo[] errors;

    public ShoppingCartValidationException() {
    }

    public ShoppingCartValidationException(
           java.lang.String message1,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo[] errors) {
        this.message1 = message1;
        this.errors = errors;
    }


    /**
     * Gets the message1 value for this ShoppingCartValidationException.
     * 
     * @return message1
     */
    public java.lang.String getMessage1() {
        return message1;
    }


    /**
     * Sets the message1 value for this ShoppingCartValidationException.
     * 
     * @param message1
     */
    public void setMessage1(java.lang.String message1) {
        this.message1 = message1;
    }


    /**
     * Gets the errors value for this ShoppingCartValidationException.
     * 
     * @return errors
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this ShoppingCartValidationException.
     * 
     * @param errors
     */
    public void setErrors(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo[] errors) {
        this.errors = errors;
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo getErrors(int i) {
        return this.errors[i];
    }

    public void setErrors(int i, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo _value) {
        this.errors[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartValidationException)) return false;
        ShoppingCartValidationException other = (ShoppingCartValidationException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.message1==null && other.getMessage1()==null) || 
             (this.message1!=null &&
              this.message1.equals(other.getMessage1()))) &&
            ((this.errors==null && other.getErrors()==null) || 
             (this.errors!=null &&
              java.util.Arrays.equals(this.errors, other.getErrors())));
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
        if (getMessage1() != null) {
            _hashCode += getMessage1().hashCode();
        }
        if (getErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartValidationException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationException"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationErrorInfo"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}

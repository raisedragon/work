/**
 * GetSystemStatusMessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class GetSystemStatusMessageResponse  implements java.io.Serializable {
    private com.winit.svr.label.handler.impl.usa.usps.model.GetSystemStatusMessageResponseGetSystemStatusMessageResult getSystemStatusMessageResult;

    public GetSystemStatusMessageResponse() {
    }

    public GetSystemStatusMessageResponse(
           com.winit.svr.label.handler.impl.usa.usps.model.GetSystemStatusMessageResponseGetSystemStatusMessageResult getSystemStatusMessageResult) {
           this.getSystemStatusMessageResult = getSystemStatusMessageResult;
    }


    /**
     * Gets the getSystemStatusMessageResult value for this GetSystemStatusMessageResponse.
     * 
     * @return getSystemStatusMessageResult
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GetSystemStatusMessageResponseGetSystemStatusMessageResult getGetSystemStatusMessageResult() {
        return getSystemStatusMessageResult;
    }


    /**
     * Sets the getSystemStatusMessageResult value for this GetSystemStatusMessageResponse.
     * 
     * @param getSystemStatusMessageResult
     */
    public void setGetSystemStatusMessageResult(com.winit.svr.label.handler.impl.usa.usps.model.GetSystemStatusMessageResponseGetSystemStatusMessageResult getSystemStatusMessageResult) {
        this.getSystemStatusMessageResult = getSystemStatusMessageResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetSystemStatusMessageResponse)) return false;
        GetSystemStatusMessageResponse other = (GetSystemStatusMessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getSystemStatusMessageResult==null && other.getGetSystemStatusMessageResult()==null) || 
             (this.getSystemStatusMessageResult!=null &&
              this.getSystemStatusMessageResult.equals(other.getGetSystemStatusMessageResult())));
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
        if (getGetSystemStatusMessageResult() != null) {
            _hashCode += getGetSystemStatusMessageResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSystemStatusMessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">GetSystemStatusMessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSystemStatusMessageResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "GetSystemStatusMessageResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">>GetSystemStatusMessageResponse>GetSystemStatusMessageResult"));
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

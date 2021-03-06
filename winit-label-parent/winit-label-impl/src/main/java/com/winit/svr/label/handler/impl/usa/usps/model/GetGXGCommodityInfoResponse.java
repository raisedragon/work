/**
 * GetGXGCommodityInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class GetGXGCommodityInfoResponse  implements java.io.Serializable {
    private com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult getGXGCommodityInfoResult;

    public GetGXGCommodityInfoResponse() {
    }

    public GetGXGCommodityInfoResponse(
           com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult getGXGCommodityInfoResult) {
           this.getGXGCommodityInfoResult = getGXGCommodityInfoResult;
    }


    /**
     * Gets the getGXGCommodityInfoResult value for this GetGXGCommodityInfoResponse.
     * 
     * @return getGXGCommodityInfoResult
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult getGetGXGCommodityInfoResult() {
        return getGXGCommodityInfoResult;
    }


    /**
     * Sets the getGXGCommodityInfoResult value for this GetGXGCommodityInfoResponse.
     * 
     * @param getGXGCommodityInfoResult
     */
    public void setGetGXGCommodityInfoResult(com.winit.svr.label.handler.impl.usa.usps.model.GXGCommodityInfoResult getGXGCommodityInfoResult) {
        this.getGXGCommodityInfoResult = getGXGCommodityInfoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetGXGCommodityInfoResponse)) return false;
        GetGXGCommodityInfoResponse other = (GetGXGCommodityInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getGXGCommodityInfoResult==null && other.getGetGXGCommodityInfoResult()==null) || 
             (this.getGXGCommodityInfoResult!=null &&
              this.getGXGCommodityInfoResult.equals(other.getGetGXGCommodityInfoResult())));
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
        if (getGetGXGCommodityInfoResult() != null) {
            _hashCode += getGetGXGCommodityInfoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetGXGCommodityInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">GetGXGCommodityInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getGXGCommodityInfoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "GetGXGCommodityInfoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "GXGCommodityInfoResult"));
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

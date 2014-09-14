/**
 * LookupHSForLegalDescriptionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class LookupHSForLegalDescriptionResponse  implements java.io.Serializable {
    private com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForLegalDescriptionResponseLookupHSForLegalDescriptionResult lookupHSForLegalDescriptionResult;

    public LookupHSForLegalDescriptionResponse() {
    }

    public LookupHSForLegalDescriptionResponse(
           com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForLegalDescriptionResponseLookupHSForLegalDescriptionResult lookupHSForLegalDescriptionResult) {
           this.lookupHSForLegalDescriptionResult = lookupHSForLegalDescriptionResult;
    }


    /**
     * Gets the lookupHSForLegalDescriptionResult value for this LookupHSForLegalDescriptionResponse.
     * 
     * @return lookupHSForLegalDescriptionResult
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForLegalDescriptionResponseLookupHSForLegalDescriptionResult getLookupHSForLegalDescriptionResult() {
        return lookupHSForLegalDescriptionResult;
    }


    /**
     * Sets the lookupHSForLegalDescriptionResult value for this LookupHSForLegalDescriptionResponse.
     * 
     * @param lookupHSForLegalDescriptionResult
     */
    public void setLookupHSForLegalDescriptionResult(com.winit.svr.label.handler.impl.usa.usps.model.LookupHSForLegalDescriptionResponseLookupHSForLegalDescriptionResult lookupHSForLegalDescriptionResult) {
        this.lookupHSForLegalDescriptionResult = lookupHSForLegalDescriptionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LookupHSForLegalDescriptionResponse)) return false;
        LookupHSForLegalDescriptionResponse other = (LookupHSForLegalDescriptionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lookupHSForLegalDescriptionResult==null && other.getLookupHSForLegalDescriptionResult()==null) || 
             (this.lookupHSForLegalDescriptionResult!=null &&
              this.lookupHSForLegalDescriptionResult.equals(other.getLookupHSForLegalDescriptionResult())));
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
        if (getLookupHSForLegalDescriptionResult() != null) {
            _hashCode += getLookupHSForLegalDescriptionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LookupHSForLegalDescriptionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">LookupHSForLegalDescriptionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lookupHSForLegalDescriptionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "LookupHSForLegalDescriptionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">>LookupHSForLegalDescriptionResponse>LookupHSForLegalDescriptionResult"));
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

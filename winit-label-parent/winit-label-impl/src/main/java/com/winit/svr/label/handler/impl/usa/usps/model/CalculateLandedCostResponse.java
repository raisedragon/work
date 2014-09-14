/**
 * CalculateLandedCostResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class CalculateLandedCostResponse  implements java.io.Serializable {
    private com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostResponseCalculateLandedCostResult calculateLandedCostResult;

    public CalculateLandedCostResponse() {
    }

    public CalculateLandedCostResponse(
           com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostResponseCalculateLandedCostResult calculateLandedCostResult) {
           this.calculateLandedCostResult = calculateLandedCostResult;
    }


    /**
     * Gets the calculateLandedCostResult value for this CalculateLandedCostResponse.
     * 
     * @return calculateLandedCostResult
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostResponseCalculateLandedCostResult getCalculateLandedCostResult() {
        return calculateLandedCostResult;
    }


    /**
     * Sets the calculateLandedCostResult value for this CalculateLandedCostResponse.
     * 
     * @param calculateLandedCostResult
     */
    public void setCalculateLandedCostResult(com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostResponseCalculateLandedCostResult calculateLandedCostResult) {
        this.calculateLandedCostResult = calculateLandedCostResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CalculateLandedCostResponse)) return false;
        CalculateLandedCostResponse other = (CalculateLandedCostResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.calculateLandedCostResult==null && other.getCalculateLandedCostResult()==null) || 
             (this.calculateLandedCostResult!=null &&
              this.calculateLandedCostResult.equals(other.getCalculateLandedCostResult())));
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
        if (getCalculateLandedCostResult() != null) {
            _hashCode += getCalculateLandedCostResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CalculateLandedCostResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">CalculateLandedCostResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calculateLandedCostResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "CalculateLandedCostResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">>CalculateLandedCostResponse>CalculateLandedCostResult"));
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

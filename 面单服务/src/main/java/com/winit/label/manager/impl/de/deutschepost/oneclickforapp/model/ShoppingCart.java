/**
 * ShoppingCart.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Description of the franking marks and the shopping cart
 */
public class ShoppingCart  implements java.io.Serializable {
    private java.lang.String shopOrderId;

    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherType[] voucherList;

    public ShoppingCart() {
    }

    public ShoppingCart(
           java.lang.String shopOrderId,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherType[] voucherList) {
           this.shopOrderId = shopOrderId;
           this.voucherList = voucherList;
    }


    /**
     * Gets the shopOrderId value for this ShoppingCart.
     * 
     * @return shopOrderId
     */
    public java.lang.String getShopOrderId() {
        return shopOrderId;
    }


    /**
     * Sets the shopOrderId value for this ShoppingCart.
     * 
     * @param shopOrderId
     */
    public void setShopOrderId(java.lang.String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }


    /**
     * Gets the voucherList value for this ShoppingCart.
     * 
     * @return voucherList
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherType[] getVoucherList() {
        return voucherList;
    }


    /**
     * Sets the voucherList value for this ShoppingCart.
     * 
     * @param voucherList
     */
    public void setVoucherList(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherType[] voucherList) {
        this.voucherList = voucherList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCart)) return false;
        ShoppingCart other = (ShoppingCart) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.shopOrderId==null && other.getShopOrderId()==null) || 
             (this.shopOrderId!=null &&
              this.shopOrderId.equals(other.getShopOrderId()))) &&
            ((this.voucherList==null && other.getVoucherList()==null) || 
             (this.voucherList!=null &&
              java.util.Arrays.equals(this.voucherList, other.getVoucherList())));
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
        if (getShopOrderId() != null) {
            _hashCode += getShopOrderId().hashCode();
        }
        if (getVoucherList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVoucherList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVoucherList(), i);
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
        new org.apache.axis.description.TypeDesc(ShoppingCart.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCart"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopOrderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "shopOrderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("voucherList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "voucherList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "voucher"));
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

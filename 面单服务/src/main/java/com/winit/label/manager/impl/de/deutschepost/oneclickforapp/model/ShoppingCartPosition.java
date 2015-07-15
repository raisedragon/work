/**
 * ShoppingCartPosition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Shopping item
 */
public class ShoppingCartPosition  implements java.io.Serializable {
    /* Product ID */
    private int productCode;

    /* If a motif exists, transfer the ID */
    private java.lang.Integer imageID;

    /* With address-linked products, the sender and the recipient
     * must be specified */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding address;

    /* Additional information on the order item */
    private java.lang.String additionalInfo;

    /* Determines the layout of the franking mark. */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout;

    public ShoppingCartPosition() {
    }

    public ShoppingCartPosition(
           int productCode,
           java.lang.Integer imageID,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding address,
           java.lang.String additionalInfo,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout) {
           this.productCode = productCode;
           this.imageID = imageID;
           this.address = address;
           this.additionalInfo = additionalInfo;
           this.voucherLayout = voucherLayout;
    }


    /**
     * Gets the productCode value for this ShoppingCartPosition.
     * 
     * @return productCode   * Product ID
     */
    public int getProductCode() {
        return productCode;
    }


    /**
     * Sets the productCode value for this ShoppingCartPosition.
     * 
     * @param productCode   * Product ID
     */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }


    /**
     * Gets the imageID value for this ShoppingCartPosition.
     * 
     * @return imageID   * If a motif exists, transfer the ID
     */
    public java.lang.Integer getImageID() {
        return imageID;
    }


    /**
     * Sets the imageID value for this ShoppingCartPosition.
     * 
     * @param imageID   * If a motif exists, transfer the ID
     */
    public void setImageID(java.lang.Integer imageID) {
        this.imageID = imageID;
    }


    /**
     * Gets the address value for this ShoppingCartPosition.
     * 
     * @return address   * With address-linked products, the sender and the recipient
     * must be specified
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding getAddress() {
        return address;
    }


    /**
     * Sets the address value for this ShoppingCartPosition.
     * 
     * @param address   * With address-linked products, the sender and the recipient
     * must be specified
     */
    public void setAddress(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding address) {
        this.address = address;
    }


    /**
     * Gets the additionalInfo value for this ShoppingCartPosition.
     * 
     * @return additionalInfo   * Additional information on the order item
     */
    public java.lang.String getAdditionalInfo() {
        return additionalInfo;
    }


    /**
     * Sets the additionalInfo value for this ShoppingCartPosition.
     * 
     * @param additionalInfo   * Additional information on the order item
     */
    public void setAdditionalInfo(java.lang.String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


    /**
     * Gets the voucherLayout value for this ShoppingCartPosition.
     * 
     * @return voucherLayout   * Determines the layout of the franking mark.
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout getVoucherLayout() {
        return voucherLayout;
    }


    /**
     * Sets the voucherLayout value for this ShoppingCartPosition.
     * 
     * @param voucherLayout   * Determines the layout of the franking mark.
     */
    public void setVoucherLayout(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout) {
        this.voucherLayout = voucherLayout;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartPosition)) return false;
        ShoppingCartPosition other = (ShoppingCartPosition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.productCode == other.getProductCode() &&
            ((this.imageID==null && other.getImageID()==null) || 
             (this.imageID!=null &&
              this.imageID.equals(other.getImageID()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.additionalInfo==null && other.getAdditionalInfo()==null) || 
             (this.additionalInfo!=null &&
              this.additionalInfo.equals(other.getAdditionalInfo()))) &&
            ((this.voucherLayout==null && other.getVoucherLayout()==null) || 
             (this.voucherLayout!=null &&
              this.voucherLayout.equals(other.getVoucherLayout())));
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
        _hashCode += getProductCode();
        if (getImageID() != null) {
            _hashCode += getImageID().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getAdditionalInfo() != null) {
            _hashCode += getAdditionalInfo().hashCode();
        }
        if (getVoucherLayout() != null) {
            _hashCode += getVoucherLayout().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartPosition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPosition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "productCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "imageID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AddressBinding"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "additionalInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("voucherLayout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "voucherLayout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherLayout"));
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

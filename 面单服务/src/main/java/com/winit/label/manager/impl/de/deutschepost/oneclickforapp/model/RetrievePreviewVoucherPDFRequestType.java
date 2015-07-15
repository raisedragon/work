/**
 * RetrievePreviewVoucherPDFRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class RetrievePreviewVoucherPDFRequestType  implements java.io.Serializable {
    /* ID which identifies the product within the PPL */
    private int productCode;

    /* ID of the motif that is to be displayed in the preview */
    private java.lang.Integer imageID;

    /* Layout of the franking mark, e.g., address-linked, not address-linked */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout;

    /* Print format ID */
    private int pageFormatId;

    public RetrievePreviewVoucherPDFRequestType() {
    }

    public RetrievePreviewVoucherPDFRequestType(
           int productCode,
           java.lang.Integer imageID,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout,
           int pageFormatId) {
           this.productCode = productCode;
           this.imageID = imageID;
           this.voucherLayout = voucherLayout;
           this.pageFormatId = pageFormatId;
    }


    /**
     * Gets the productCode value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @return productCode   * ID which identifies the product within the PPL
     */
    public int getProductCode() {
        return productCode;
    }


    /**
     * Sets the productCode value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @param productCode   * ID which identifies the product within the PPL
     */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }


    /**
     * Gets the imageID value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @return imageID   * ID of the motif that is to be displayed in the preview
     */
    public java.lang.Integer getImageID() {
        return imageID;
    }


    /**
     * Sets the imageID value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @param imageID   * ID of the motif that is to be displayed in the preview
     */
    public void setImageID(java.lang.Integer imageID) {
        this.imageID = imageID;
    }


    /**
     * Gets the voucherLayout value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @return voucherLayout   * Layout of the franking mark, e.g., address-linked, not address-linked
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout getVoucherLayout() {
        return voucherLayout;
    }


    /**
     * Sets the voucherLayout value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @param voucherLayout   * Layout of the franking mark, e.g., address-linked, not address-linked
     */
    public void setVoucherLayout(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout) {
        this.voucherLayout = voucherLayout;
    }


    /**
     * Gets the pageFormatId value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @return pageFormatId   * Print format ID
     */
    public int getPageFormatId() {
        return pageFormatId;
    }


    /**
     * Sets the pageFormatId value for this RetrievePreviewVoucherPDFRequestType.
     * 
     * @param pageFormatId   * Print format ID
     */
    public void setPageFormatId(int pageFormatId) {
        this.pageFormatId = pageFormatId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrievePreviewVoucherPDFRequestType)) return false;
        RetrievePreviewVoucherPDFRequestType other = (RetrievePreviewVoucherPDFRequestType) obj;
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
            ((this.voucherLayout==null && other.getVoucherLayout()==null) || 
             (this.voucherLayout!=null &&
              this.voucherLayout.equals(other.getVoucherLayout()))) &&
            this.pageFormatId == other.getPageFormatId();
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
        if (getVoucherLayout() != null) {
            _hashCode += getVoucherLayout().hashCode();
        }
        _hashCode += getPageFormatId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrievePreviewVoucherPDFRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPDFRequestType"));
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
        elemField.setFieldName("voucherLayout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "voucherLayout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherLayout"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageFormatId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "pageFormatId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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

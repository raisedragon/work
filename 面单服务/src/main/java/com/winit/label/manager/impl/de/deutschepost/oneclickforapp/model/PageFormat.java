/**
 * PageFormat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Structure of a print format
 */
public class PageFormat  implements java.io.Serializable {
    private int id;

    /* Yes, if addresses can be printed on the franking marks using
     * the print format. */
    private boolean isAddressPossible;

    /* Yes, if motifs can be printed on the franking marks using the
     * print format */
    private boolean isImagePossible;

    /* Name of the print format, e.g., DIN A4 normal paper or letter
     * C5 162 x 229 */
    private java.lang.String name;

    /* Description of the print format. */
    private java.lang.String description;

    /* Specification of the print medium. */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageType pageType;

    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormatPageLayout pageLayout;

    public PageFormat() {
    }

    public PageFormat(
           int id,
           boolean isAddressPossible,
           boolean isImagePossible,
           java.lang.String name,
           java.lang.String description,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageType pageType,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormatPageLayout pageLayout) {
           this.id = id;
           this.isAddressPossible = isAddressPossible;
           this.isImagePossible = isImagePossible;
           this.name = name;
           this.description = description;
           this.pageType = pageType;
           this.pageLayout = pageLayout;
    }


    /**
     * Gets the id value for this PageFormat.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this PageFormat.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the isAddressPossible value for this PageFormat.
     * 
     * @return isAddressPossible   * Yes, if addresses can be printed on the franking marks using
     * the print format.
     */
    public boolean isIsAddressPossible() {
        return isAddressPossible;
    }


    /**
     * Sets the isAddressPossible value for this PageFormat.
     * 
     * @param isAddressPossible   * Yes, if addresses can be printed on the franking marks using
     * the print format.
     */
    public void setIsAddressPossible(boolean isAddressPossible) {
        this.isAddressPossible = isAddressPossible;
    }


    /**
     * Gets the isImagePossible value for this PageFormat.
     * 
     * @return isImagePossible   * Yes, if motifs can be printed on the franking marks using the
     * print format
     */
    public boolean isIsImagePossible() {
        return isImagePossible;
    }


    /**
     * Sets the isImagePossible value for this PageFormat.
     * 
     * @param isImagePossible   * Yes, if motifs can be printed on the franking marks using the
     * print format
     */
    public void setIsImagePossible(boolean isImagePossible) {
        this.isImagePossible = isImagePossible;
    }


    /**
     * Gets the name value for this PageFormat.
     * 
     * @return name   * Name of the print format, e.g., DIN A4 normal paper or letter
     * C5 162 x 229
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PageFormat.
     * 
     * @param name   * Name of the print format, e.g., DIN A4 normal paper or letter
     * C5 162 x 229
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this PageFormat.
     * 
     * @return description   * Description of the print format.
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PageFormat.
     * 
     * @param description   * Description of the print format.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the pageType value for this PageFormat.
     * 
     * @return pageType   * Specification of the print medium.
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageType getPageType() {
        return pageType;
    }


    /**
     * Sets the pageType value for this PageFormat.
     * 
     * @param pageType   * Specification of the print medium.
     */
    public void setPageType(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageType pageType) {
        this.pageType = pageType;
    }


    /**
     * Gets the pageLayout value for this PageFormat.
     * 
     * @return pageLayout
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormatPageLayout getPageLayout() {
        return pageLayout;
    }


    /**
     * Sets the pageLayout value for this PageFormat.
     * 
     * @param pageLayout
     */
    public void setPageLayout(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormatPageLayout pageLayout) {
        this.pageLayout = pageLayout;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PageFormat)) return false;
        PageFormat other = (PageFormat) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            this.isAddressPossible == other.isIsAddressPossible() &&
            this.isImagePossible == other.isIsImagePossible() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.pageType==null && other.getPageType()==null) || 
             (this.pageType!=null &&
              this.pageType.equals(other.getPageType()))) &&
            ((this.pageLayout==null && other.getPageLayout()==null) || 
             (this.pageLayout!=null &&
              this.pageLayout.equals(other.getPageLayout())));
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
        _hashCode += getId();
        _hashCode += (isIsAddressPossible() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsImagePossible() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getPageType() != null) {
            _hashCode += getPageType().hashCode();
        }
        if (getPageLayout() != null) {
            _hashCode += getPageLayout().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PageFormat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PageFormat"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAddressPossible");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "isAddressPossible"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isImagePossible");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "isImagePossible"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "pageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PageType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageLayout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "pageLayout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">PageFormat>pageLayout"));
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

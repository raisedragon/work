/**
 * ImageItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Structure which represents an image within an image category
 */
public class ImageItem  implements java.io.Serializable {
    /* Name of the motif */
    private int imageID;

    /* Description of the motif */
    private java.lang.String imageDescription;

    /* Short image caption */
    private java.lang.String imageSlogan;

    /* URL to the image */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink links;

    public ImageItem() {
    }

    public ImageItem(
           int imageID,
           java.lang.String imageDescription,
           java.lang.String imageSlogan,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink links) {
           this.imageID = imageID;
           this.imageDescription = imageDescription;
           this.imageSlogan = imageSlogan;
           this.links = links;
    }


    /**
     * Gets the imageID value for this ImageItem.
     * 
     * @return imageID   * Name of the motif
     */
    public int getImageID() {
        return imageID;
    }


    /**
     * Sets the imageID value for this ImageItem.
     * 
     * @param imageID   * Name of the motif
     */
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }


    /**
     * Gets the imageDescription value for this ImageItem.
     * 
     * @return imageDescription   * Description of the motif
     */
    public java.lang.String getImageDescription() {
        return imageDescription;
    }


    /**
     * Sets the imageDescription value for this ImageItem.
     * 
     * @param imageDescription   * Description of the motif
     */
    public void setImageDescription(java.lang.String imageDescription) {
        this.imageDescription = imageDescription;
    }


    /**
     * Gets the imageSlogan value for this ImageItem.
     * 
     * @return imageSlogan   * Short image caption
     */
    public java.lang.String getImageSlogan() {
        return imageSlogan;
    }


    /**
     * Sets the imageSlogan value for this ImageItem.
     * 
     * @param imageSlogan   * Short image caption
     */
    public void setImageSlogan(java.lang.String imageSlogan) {
        this.imageSlogan = imageSlogan;
    }


    /**
     * Gets the links value for this ImageItem.
     * 
     * @return links   * URL to the image
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink getLinks() {
        return links;
    }


    /**
     * Sets the links value for this ImageItem.
     * 
     * @param links   * URL to the image
     */
    public void setLinks(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink links) {
        this.links = links;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ImageItem)) return false;
        ImageItem other = (ImageItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.imageID == other.getImageID() &&
            ((this.imageDescription==null && other.getImageDescription()==null) || 
             (this.imageDescription!=null &&
              this.imageDescription.equals(other.getImageDescription()))) &&
            ((this.imageSlogan==null && other.getImageSlogan()==null) || 
             (this.imageSlogan!=null &&
              this.imageSlogan.equals(other.getImageSlogan()))) &&
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              this.links.equals(other.getLinks())));
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
        _hashCode += getImageID();
        if (getImageDescription() != null) {
            _hashCode += getImageDescription().hashCode();
        }
        if (getImageSlogan() != null) {
            _hashCode += getImageSlogan().hashCode();
        }
        if (getLinks() != null) {
            _hashCode += getLinks().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImageItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ImageItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "imageID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "imageDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageSlogan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "imageSlogan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "links"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "MotiveLink"));
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

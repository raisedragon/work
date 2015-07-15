/**
 * MotiveLink.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class MotiveLink  implements java.io.Serializable {
    /* URL to the motif */
    private java.lang.String link;

    /* URL to the motif preview (thumbnail) */
    private java.lang.String linkThumbnail;

    public MotiveLink() {
    }

    public MotiveLink(
           java.lang.String link,
           java.lang.String linkThumbnail) {
           this.link = link;
           this.linkThumbnail = linkThumbnail;
    }


    /**
     * Gets the link value for this MotiveLink.
     * 
     * @return link   * URL to the motif
     */
    public java.lang.String getLink() {
        return link;
    }


    /**
     * Sets the link value for this MotiveLink.
     * 
     * @param link   * URL to the motif
     */
    public void setLink(java.lang.String link) {
        this.link = link;
    }


    /**
     * Gets the linkThumbnail value for this MotiveLink.
     * 
     * @return linkThumbnail   * URL to the motif preview (thumbnail)
     */
    public java.lang.String getLinkThumbnail() {
        return linkThumbnail;
    }


    /**
     * Sets the linkThumbnail value for this MotiveLink.
     * 
     * @param linkThumbnail   * URL to the motif preview (thumbnail)
     */
    public void setLinkThumbnail(java.lang.String linkThumbnail) {
        this.linkThumbnail = linkThumbnail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MotiveLink)) return false;
        MotiveLink other = (MotiveLink) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.link==null && other.getLink()==null) || 
             (this.link!=null &&
              this.link.equals(other.getLink()))) &&
            ((this.linkThumbnail==null && other.getLinkThumbnail()==null) || 
             (this.linkThumbnail!=null &&
              this.linkThumbnail.equals(other.getLinkThumbnail())));
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
        if (getLink() != null) {
            _hashCode += getLink().hashCode();
        }
        if (getLinkThumbnail() != null) {
            _hashCode += getLinkThumbnail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MotiveLink.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "MotiveLink"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "link"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkThumbnail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "linkThumbnail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

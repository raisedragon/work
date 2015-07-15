/**
 * PageFormatPageLayout.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class PageFormatPageLayout  implements java.io.Serializable {
    /* Dimension of the print format in millimeters */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension size;

    /* Page orientation */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Orientation orientation;

    /* Spacing between labels in millimeters */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension labelSpacing;

    /* Number of label items in X and Y direction */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Position labelCount;

    /* Inner margin size of the print format in millimeters */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.BorderDimension margin;

    public PageFormatPageLayout() {
    }

    public PageFormatPageLayout(
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension size,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Orientation orientation,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension labelSpacing,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Position labelCount,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.BorderDimension margin) {
           this.size = size;
           this.orientation = orientation;
           this.labelSpacing = labelSpacing;
           this.labelCount = labelCount;
           this.margin = margin;
    }


    /**
     * Gets the size value for this PageFormatPageLayout.
     * 
     * @return size   * Dimension of the print format in millimeters
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension getSize() {
        return size;
    }


    /**
     * Sets the size value for this PageFormatPageLayout.
     * 
     * @param size   * Dimension of the print format in millimeters
     */
    public void setSize(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension size) {
        this.size = size;
    }


    /**
     * Gets the orientation value for this PageFormatPageLayout.
     * 
     * @return orientation   * Page orientation
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Orientation getOrientation() {
        return orientation;
    }


    /**
     * Sets the orientation value for this PageFormatPageLayout.
     * 
     * @param orientation   * Page orientation
     */
    public void setOrientation(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Orientation orientation) {
        this.orientation = orientation;
    }


    /**
     * Gets the labelSpacing value for this PageFormatPageLayout.
     * 
     * @return labelSpacing   * Spacing between labels in millimeters
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension getLabelSpacing() {
        return labelSpacing;
    }


    /**
     * Sets the labelSpacing value for this PageFormatPageLayout.
     * 
     * @param labelSpacing   * Spacing between labels in millimeters
     */
    public void setLabelSpacing(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension labelSpacing) {
        this.labelSpacing = labelSpacing;
    }


    /**
     * Gets the labelCount value for this PageFormatPageLayout.
     * 
     * @return labelCount   * Number of label items in X and Y direction
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Position getLabelCount() {
        return labelCount;
    }


    /**
     * Sets the labelCount value for this PageFormatPageLayout.
     * 
     * @param labelCount   * Number of label items in X and Y direction
     */
    public void setLabelCount(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Position labelCount) {
        this.labelCount = labelCount;
    }


    /**
     * Gets the margin value for this PageFormatPageLayout.
     * 
     * @return margin   * Inner margin size of the print format in millimeters
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.BorderDimension getMargin() {
        return margin;
    }


    /**
     * Sets the margin value for this PageFormatPageLayout.
     * 
     * @param margin   * Inner margin size of the print format in millimeters
     */
    public void setMargin(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.BorderDimension margin) {
        this.margin = margin;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PageFormatPageLayout)) return false;
        PageFormatPageLayout other = (PageFormatPageLayout) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.size==null && other.getSize()==null) || 
             (this.size!=null &&
              this.size.equals(other.getSize()))) &&
            ((this.orientation==null && other.getOrientation()==null) || 
             (this.orientation!=null &&
              this.orientation.equals(other.getOrientation()))) &&
            ((this.labelSpacing==null && other.getLabelSpacing()==null) || 
             (this.labelSpacing!=null &&
              this.labelSpacing.equals(other.getLabelSpacing()))) &&
            ((this.labelCount==null && other.getLabelCount()==null) || 
             (this.labelCount!=null &&
              this.labelCount.equals(other.getLabelCount()))) &&
            ((this.margin==null && other.getMargin()==null) || 
             (this.margin!=null &&
              this.margin.equals(other.getMargin())));
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
        if (getSize() != null) {
            _hashCode += getSize().hashCode();
        }
        if (getOrientation() != null) {
            _hashCode += getOrientation().hashCode();
        }
        if (getLabelSpacing() != null) {
            _hashCode += getLabelSpacing().hashCode();
        }
        if (getLabelCount() != null) {
            _hashCode += getLabelCount().hashCode();
        }
        if (getMargin() != null) {
            _hashCode += getMargin().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PageFormatPageLayout.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">PageFormat>pageLayout"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("size");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "size"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Dimension"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orientation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "orientation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Orientation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("labelSpacing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "labelSpacing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Dimension"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("labelCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "labelCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Position"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "margin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "BorderDimension"));
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

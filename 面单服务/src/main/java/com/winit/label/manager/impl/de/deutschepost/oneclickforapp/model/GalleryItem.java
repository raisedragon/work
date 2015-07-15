/**
 * GalleryItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Element in the motif gallery
 */
public class GalleryItem  implements java.io.Serializable {
    /* Motif category */
    private java.lang.String category;

    /* Motif category description */
    private java.lang.String categoryDescription;

    /* Motif category ID */
    private int categoryId;

    /* List of all images within the image category */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem[] images;

    public GalleryItem() {
    }

    public GalleryItem(
           java.lang.String category,
           java.lang.String categoryDescription,
           int categoryId,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem[] images) {
           this.category = category;
           this.categoryDescription = categoryDescription;
           this.categoryId = categoryId;
           this.images = images;
    }


    /**
     * Gets the category value for this GalleryItem.
     * 
     * @return category   * Motif category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this GalleryItem.
     * 
     * @param category   * Motif category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the categoryDescription value for this GalleryItem.
     * 
     * @return categoryDescription   * Motif category description
     */
    public java.lang.String getCategoryDescription() {
        return categoryDescription;
    }


    /**
     * Sets the categoryDescription value for this GalleryItem.
     * 
     * @param categoryDescription   * Motif category description
     */
    public void setCategoryDescription(java.lang.String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


    /**
     * Gets the categoryId value for this GalleryItem.
     * 
     * @return categoryId   * Motif category ID
     */
    public int getCategoryId() {
        return categoryId;
    }


    /**
     * Sets the categoryId value for this GalleryItem.
     * 
     * @param categoryId   * Motif category ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    /**
     * Gets the images value for this GalleryItem.
     * 
     * @return images   * List of all images within the image category
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem[] getImages() {
        return images;
    }


    /**
     * Sets the images value for this GalleryItem.
     * 
     * @param images   * List of all images within the image category
     */
    public void setImages(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem[] images) {
        this.images = images;
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem getImages(int i) {
        return this.images[i];
    }

    public void setImages(int i, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem _value) {
        this.images[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GalleryItem)) return false;
        GalleryItem other = (GalleryItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.categoryDescription==null && other.getCategoryDescription()==null) || 
             (this.categoryDescription!=null &&
              this.categoryDescription.equals(other.getCategoryDescription()))) &&
            this.categoryId == other.getCategoryId() &&
            ((this.images==null && other.getImages()==null) || 
             (this.images!=null &&
              java.util.Arrays.equals(this.images, other.getImages())));
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
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getCategoryDescription() != null) {
            _hashCode += getCategoryDescription().hashCode();
        }
        _hashCode += getCategoryId();
        if (getImages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getImages(), i);
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
        new org.apache.axis.description.TypeDesc(GalleryItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "GalleryItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "categoryDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "categoryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("images");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "images"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ImageItem"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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

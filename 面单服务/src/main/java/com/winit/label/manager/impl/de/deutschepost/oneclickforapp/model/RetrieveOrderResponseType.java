/**
 * RetrieveOrderResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * The shopping cart that was generated
 */
public class RetrieveOrderResponseType  implements java.io.Serializable {
    /* Link for printing the shopping cart */
    private java.lang.String link;

    /* Detailed description of the shopping cart that was generated */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart shoppingCart;

    public RetrieveOrderResponseType() {
    }

    public RetrieveOrderResponseType(
           java.lang.String link,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart shoppingCart) {
           this.link = link;
           this.shoppingCart = shoppingCart;
    }


    /**
     * Gets the link value for this RetrieveOrderResponseType.
     * 
     * @return link   * Link for printing the shopping cart
     */
    public java.lang.String getLink() {
        return link;
    }


    /**
     * Sets the link value for this RetrieveOrderResponseType.
     * 
     * @param link   * Link for printing the shopping cart
     */
    public void setLink(java.lang.String link) {
        this.link = link;
    }


    /**
     * Gets the shoppingCart value for this RetrieveOrderResponseType.
     * 
     * @return shoppingCart   * Detailed description of the shopping cart that was generated
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    /**
     * Sets the shoppingCart value for this RetrieveOrderResponseType.
     * 
     * @param shoppingCart   * Detailed description of the shopping cart that was generated
     */
    public void setShoppingCart(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveOrderResponseType)) return false;
        RetrieveOrderResponseType other = (RetrieveOrderResponseType) obj;
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
            ((this.shoppingCart==null && other.getShoppingCart()==null) || 
             (this.shoppingCart!=null &&
              this.shoppingCart.equals(other.getShoppingCart())));
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
        if (getShoppingCart() != null) {
            _hashCode += getShoppingCart().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveOrderResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderResponseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "link"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shoppingCart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "shoppingCart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCart"));
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

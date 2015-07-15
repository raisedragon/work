/**
 * ShoppingCartResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Link for printing the shopping cart
 */
public class ShoppingCartResponseType  implements java.io.Serializable {
    /* A link to a ZIP file which contains the images of the franking
     * marks. */
    private java.lang.String link;

    /* Postage account credit balance after purchase */
    private int walletBallance;

    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart shoppingCart;

    public ShoppingCartResponseType() {
    }

    public ShoppingCartResponseType(
           java.lang.String link,
           int walletBallance,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart shoppingCart) {
           this.link = link;
           this.walletBallance = walletBallance;
           this.shoppingCart = shoppingCart;
    }


    /**
     * Gets the link value for this ShoppingCartResponseType.
     * 
     * @return link   * A link to a ZIP file which contains the images of the franking
     * marks.
     */
    public java.lang.String getLink() {
        return link;
    }


    /**
     * Sets the link value for this ShoppingCartResponseType.
     * 
     * @param link   * A link to a ZIP file which contains the images of the franking
     * marks.
     */
    public void setLink(java.lang.String link) {
        this.link = link;
    }


    /**
     * Gets the walletBallance value for this ShoppingCartResponseType.
     * 
     * @return walletBallance   * Postage account credit balance after purchase
     */
    public int getWalletBallance() {
        return walletBallance;
    }


    /**
     * Sets the walletBallance value for this ShoppingCartResponseType.
     * 
     * @param walletBallance   * Postage account credit balance after purchase
     */
    public void setWalletBallance(int walletBallance) {
        this.walletBallance = walletBallance;
    }


    /**
     * Gets the shoppingCart value for this ShoppingCartResponseType.
     * 
     * @return shoppingCart
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    /**
     * Sets the shoppingCart value for this ShoppingCartResponseType.
     * 
     * @param shoppingCart
     */
    public void setShoppingCart(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartResponseType)) return false;
        ShoppingCartResponseType other = (ShoppingCartResponseType) obj;
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
            this.walletBallance == other.getWalletBallance() &&
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
        _hashCode += getWalletBallance();
        if (getShoppingCart() != null) {
            _hashCode += getShoppingCart().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartResponseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "link"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("walletBallance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "walletBallance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Amount"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shoppingCart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "shoppingCart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCart"));
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

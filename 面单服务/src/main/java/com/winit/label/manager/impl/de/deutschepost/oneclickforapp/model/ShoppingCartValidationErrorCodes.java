/**
 * ShoppingCartValidationErrorCodes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class ShoppingCartValidationErrorCodes implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ShoppingCartValidationErrorCodes(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _invalidUser = "invalidUser";
    public static final java.lang.String _invalidPplId = "invalidPplId";
    public static final java.lang.String _invalidProductcode = "invalidProductcode";
    public static final java.lang.String _invalidTotalAmount = "invalidTotalAmount";
    public static final java.lang.String _walletBalanceNotEnough = "walletBalanceNotEnough";
    public static final java.lang.String _walletNotAvailable = "walletNotAvailable";
    public static final java.lang.String _invalidMotive = "invalidMotive";
    public static final java.lang.String _invalidPageFormat = "invalidPageFormat";
    public static final java.lang.String _productExpired = "productExpired";
    public static final java.lang.String _invalidShopOrderId = "invalidShopOrderId";
    public static final ShoppingCartValidationErrorCodes invalidUser = new ShoppingCartValidationErrorCodes(_invalidUser);
    public static final ShoppingCartValidationErrorCodes invalidPplId = new ShoppingCartValidationErrorCodes(_invalidPplId);
    public static final ShoppingCartValidationErrorCodes invalidProductcode = new ShoppingCartValidationErrorCodes(_invalidProductcode);
    public static final ShoppingCartValidationErrorCodes invalidTotalAmount = new ShoppingCartValidationErrorCodes(_invalidTotalAmount);
    public static final ShoppingCartValidationErrorCodes walletBalanceNotEnough = new ShoppingCartValidationErrorCodes(_walletBalanceNotEnough);
    public static final ShoppingCartValidationErrorCodes walletNotAvailable = new ShoppingCartValidationErrorCodes(_walletNotAvailable);
    public static final ShoppingCartValidationErrorCodes invalidMotive = new ShoppingCartValidationErrorCodes(_invalidMotive);
    public static final ShoppingCartValidationErrorCodes invalidPageFormat = new ShoppingCartValidationErrorCodes(_invalidPageFormat);
    public static final ShoppingCartValidationErrorCodes productExpired = new ShoppingCartValidationErrorCodes(_productExpired);
    public static final ShoppingCartValidationErrorCodes invalidShopOrderId = new ShoppingCartValidationErrorCodes(_invalidShopOrderId);
    public java.lang.String getValue() { return _value_;}
    public static ShoppingCartValidationErrorCodes fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ShoppingCartValidationErrorCodes enumeration = (ShoppingCartValidationErrorCodes)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ShoppingCartValidationErrorCodes fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartValidationErrorCodes.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationErrorCodes"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}

/**
 * ShoppingCartPDFPosition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class ShoppingCartPDFPosition  extends com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPosition  implements java.io.Serializable {
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherPosition position;

    public ShoppingCartPDFPosition() {
    }

    public ShoppingCartPDFPosition(
           int productCode,
           java.lang.Integer imageID,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding address,
           java.lang.String additionalInfo,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout voucherLayout,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherPosition position) {
        super(
            productCode,
            imageID,
            address,
            additionalInfo,
            voucherLayout);
        this.position = position;
    }


    /**
     * Gets the position value for this ShoppingCartPDFPosition.
     * 
     * @return position
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherPosition getPosition() {
        return position;
    }


    /**
     * Sets the position value for this ShoppingCartPDFPosition.
     * 
     * @param position
     */
    public void setPosition(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherPosition position) {
        this.position = position;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartPDFPosition)) return false;
        ShoppingCartPDFPosition other = (ShoppingCartPDFPosition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.position==null && other.getPosition()==null) || 
             (this.position!=null &&
              this.position.equals(other.getPosition())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getPosition() != null) {
            _hashCode += getPosition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartPDFPosition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPDFPosition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("position");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "position"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherPosition"));
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

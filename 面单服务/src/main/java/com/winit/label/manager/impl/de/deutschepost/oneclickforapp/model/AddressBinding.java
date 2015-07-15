/**
 * AddressBinding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Sender and recipient address
 */
public class AddressBinding  implements java.io.Serializable {
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress sender;

    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress receiver;

    public AddressBinding() {
    }

    public AddressBinding(
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress sender,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress receiver) {
           this.sender = sender;
           this.receiver = receiver;
    }


    /**
     * Gets the sender value for this AddressBinding.
     * 
     * @return sender
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress getSender() {
        return sender;
    }


    /**
     * Sets the sender value for this AddressBinding.
     * 
     * @param sender
     */
    public void setSender(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress sender) {
        this.sender = sender;
    }


    /**
     * Gets the receiver value for this AddressBinding.
     * 
     * @return receiver
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress getReceiver() {
        return receiver;
    }


    /**
     * Sets the receiver value for this AddressBinding.
     * 
     * @param receiver
     */
    public void setReceiver(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress receiver) {
        this.receiver = receiver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressBinding)) return false;
        AddressBinding other = (AddressBinding) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sender==null && other.getSender()==null) || 
             (this.sender!=null &&
              this.sender.equals(other.getSender()))) &&
            ((this.receiver==null && other.getReceiver()==null) || 
             (this.receiver!=null &&
              this.receiver.equals(other.getReceiver())));
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
        if (getSender() != null) {
            _hashCode += getSender().hashCode();
        }
        if (getReceiver() != null) {
            _hashCode += getReceiver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressBinding.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AddressBinding"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "sender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "NamedAddress"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiver");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "receiver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "NamedAddress"));
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

/**
 * Name.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Definition of recipient’s or sender’s name elements
 */
public class Name  implements java.io.Serializable {
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PersonName personName;

    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CompanyName companyName;

    public Name() {
    }

    public Name(
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PersonName personName,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CompanyName companyName) {
           this.personName = personName;
           this.companyName = companyName;
    }


    /**
     * Gets the personName value for this Name.
     * 
     * @return personName
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PersonName getPersonName() {
        return personName;
    }


    /**
     * Sets the personName value for this Name.
     * 
     * @param personName
     */
    public void setPersonName(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PersonName personName) {
        this.personName = personName;
    }


    /**
     * Gets the companyName value for this Name.
     * 
     * @return companyName
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CompanyName getCompanyName() {
        return companyName;
    }


    /**
     * Sets the companyName value for this Name.
     * 
     * @param companyName
     */
    public void setCompanyName(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CompanyName companyName) {
        this.companyName = companyName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Name)) return false;
        Name other = (Name) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.personName==null && other.getPersonName()==null) || 
             (this.personName!=null &&
              this.personName.equals(other.getPersonName()))) &&
            ((this.companyName==null && other.getCompanyName()==null) || 
             (this.companyName!=null &&
              this.companyName.equals(other.getCompanyName())));
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
        if (getPersonName() != null) {
            _hashCode += getPersonName().hashCode();
        }
        if (getCompanyName() != null) {
            _hashCode += getCompanyName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Name.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Name"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "personName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PersonName"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "companyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CompanyName"));
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

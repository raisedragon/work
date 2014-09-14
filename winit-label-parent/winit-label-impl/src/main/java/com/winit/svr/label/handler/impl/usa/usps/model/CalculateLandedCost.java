/**
 * CalculateLandedCost.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class CalculateLandedCost  implements java.io.Serializable {
    private java.lang.String countryOfImport;

    private java.lang.String countryOfExport;

    private java.lang.String modeOfTransport;

    private java.lang.String incoTerm;

    private java.lang.String taxDetail;

    private java.lang.String dateOfImport;

    private com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostItems items;

    private java.lang.String accessToken;

    public CalculateLandedCost() {
    }

    public CalculateLandedCost(
           java.lang.String countryOfImport,
           java.lang.String countryOfExport,
           java.lang.String modeOfTransport,
           java.lang.String incoTerm,
           java.lang.String taxDetail,
           java.lang.String dateOfImport,
           com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostItems items,
           java.lang.String accessToken) {
           this.countryOfImport = countryOfImport;
           this.countryOfExport = countryOfExport;
           this.modeOfTransport = modeOfTransport;
           this.incoTerm = incoTerm;
           this.taxDetail = taxDetail;
           this.dateOfImport = dateOfImport;
           this.items = items;
           this.accessToken = accessToken;
    }


    /**
     * Gets the countryOfImport value for this CalculateLandedCost.
     * 
     * @return countryOfImport
     */
    public java.lang.String getCountryOfImport() {
        return countryOfImport;
    }


    /**
     * Sets the countryOfImport value for this CalculateLandedCost.
     * 
     * @param countryOfImport
     */
    public void setCountryOfImport(java.lang.String countryOfImport) {
        this.countryOfImport = countryOfImport;
    }


    /**
     * Gets the countryOfExport value for this CalculateLandedCost.
     * 
     * @return countryOfExport
     */
    public java.lang.String getCountryOfExport() {
        return countryOfExport;
    }


    /**
     * Sets the countryOfExport value for this CalculateLandedCost.
     * 
     * @param countryOfExport
     */
    public void setCountryOfExport(java.lang.String countryOfExport) {
        this.countryOfExport = countryOfExport;
    }


    /**
     * Gets the modeOfTransport value for this CalculateLandedCost.
     * 
     * @return modeOfTransport
     */
    public java.lang.String getModeOfTransport() {
        return modeOfTransport;
    }


    /**
     * Sets the modeOfTransport value for this CalculateLandedCost.
     * 
     * @param modeOfTransport
     */
    public void setModeOfTransport(java.lang.String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }


    /**
     * Gets the incoTerm value for this CalculateLandedCost.
     * 
     * @return incoTerm
     */
    public java.lang.String getIncoTerm() {
        return incoTerm;
    }


    /**
     * Sets the incoTerm value for this CalculateLandedCost.
     * 
     * @param incoTerm
     */
    public void setIncoTerm(java.lang.String incoTerm) {
        this.incoTerm = incoTerm;
    }


    /**
     * Gets the taxDetail value for this CalculateLandedCost.
     * 
     * @return taxDetail
     */
    public java.lang.String getTaxDetail() {
        return taxDetail;
    }


    /**
     * Sets the taxDetail value for this CalculateLandedCost.
     * 
     * @param taxDetail
     */
    public void setTaxDetail(java.lang.String taxDetail) {
        this.taxDetail = taxDetail;
    }


    /**
     * Gets the dateOfImport value for this CalculateLandedCost.
     * 
     * @return dateOfImport
     */
    public java.lang.String getDateOfImport() {
        return dateOfImport;
    }


    /**
     * Sets the dateOfImport value for this CalculateLandedCost.
     * 
     * @param dateOfImport
     */
    public void setDateOfImport(java.lang.String dateOfImport) {
        this.dateOfImport = dateOfImport;
    }


    /**
     * Gets the items value for this CalculateLandedCost.
     * 
     * @return items
     */
    public com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostItems getItems() {
        return items;
    }


    /**
     * Sets the items value for this CalculateLandedCost.
     * 
     * @param items
     */
    public void setItems(com.winit.svr.label.handler.impl.usa.usps.model.CalculateLandedCostItems items) {
        this.items = items;
    }


    /**
     * Gets the accessToken value for this CalculateLandedCost.
     * 
     * @return accessToken
     */
    public java.lang.String getAccessToken() {
        return accessToken;
    }


    /**
     * Sets the accessToken value for this CalculateLandedCost.
     * 
     * @param accessToken
     */
    public void setAccessToken(java.lang.String accessToken) {
        this.accessToken = accessToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CalculateLandedCost)) return false;
        CalculateLandedCost other = (CalculateLandedCost) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.countryOfImport==null && other.getCountryOfImport()==null) || 
             (this.countryOfImport!=null &&
              this.countryOfImport.equals(other.getCountryOfImport()))) &&
            ((this.countryOfExport==null && other.getCountryOfExport()==null) || 
             (this.countryOfExport!=null &&
              this.countryOfExport.equals(other.getCountryOfExport()))) &&
            ((this.modeOfTransport==null && other.getModeOfTransport()==null) || 
             (this.modeOfTransport!=null &&
              this.modeOfTransport.equals(other.getModeOfTransport()))) &&
            ((this.incoTerm==null && other.getIncoTerm()==null) || 
             (this.incoTerm!=null &&
              this.incoTerm.equals(other.getIncoTerm()))) &&
            ((this.taxDetail==null && other.getTaxDetail()==null) || 
             (this.taxDetail!=null &&
              this.taxDetail.equals(other.getTaxDetail()))) &&
            ((this.dateOfImport==null && other.getDateOfImport()==null) || 
             (this.dateOfImport!=null &&
              this.dateOfImport.equals(other.getDateOfImport()))) &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              this.items.equals(other.getItems()))) &&
            ((this.accessToken==null && other.getAccessToken()==null) || 
             (this.accessToken!=null &&
              this.accessToken.equals(other.getAccessToken())));
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
        if (getCountryOfImport() != null) {
            _hashCode += getCountryOfImport().hashCode();
        }
        if (getCountryOfExport() != null) {
            _hashCode += getCountryOfExport().hashCode();
        }
        if (getModeOfTransport() != null) {
            _hashCode += getModeOfTransport().hashCode();
        }
        if (getIncoTerm() != null) {
            _hashCode += getIncoTerm().hashCode();
        }
        if (getTaxDetail() != null) {
            _hashCode += getTaxDetail().hashCode();
        }
        if (getDateOfImport() != null) {
            _hashCode += getDateOfImport().hashCode();
        }
        if (getItems() != null) {
            _hashCode += getItems().hashCode();
        }
        if (getAccessToken() != null) {
            _hashCode += getAccessToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CalculateLandedCost.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">CalculateLandedCost"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryOfImport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "CountryOfImport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryOfExport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "CountryOfExport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modeOfTransport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ModeOfTransport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incoTerm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "IncoTerm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "TaxDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateOfImport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "DateOfImport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "Items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">>CalculateLandedCost>Items"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "AccessToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

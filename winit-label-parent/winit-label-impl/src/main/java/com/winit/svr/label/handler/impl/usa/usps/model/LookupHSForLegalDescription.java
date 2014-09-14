/**
 * LookupHSForLegalDescription.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class LookupHSForLegalDescription  implements java.io.Serializable {
    private java.lang.String countryOfImport;

    private java.lang.String searchText;

    private java.lang.String dateOfImport;

    private java.lang.String classificationType;

    private java.lang.String matchLevel;

    private java.lang.String searchType;

    private java.lang.String accessToken;

    public LookupHSForLegalDescription() {
    }

    public LookupHSForLegalDescription(
           java.lang.String countryOfImport,
           java.lang.String searchText,
           java.lang.String dateOfImport,
           java.lang.String classificationType,
           java.lang.String matchLevel,
           java.lang.String searchType,
           java.lang.String accessToken) {
           this.countryOfImport = countryOfImport;
           this.searchText = searchText;
           this.dateOfImport = dateOfImport;
           this.classificationType = classificationType;
           this.matchLevel = matchLevel;
           this.searchType = searchType;
           this.accessToken = accessToken;
    }


    /**
     * Gets the countryOfImport value for this LookupHSForLegalDescription.
     * 
     * @return countryOfImport
     */
    public java.lang.String getCountryOfImport() {
        return countryOfImport;
    }


    /**
     * Sets the countryOfImport value for this LookupHSForLegalDescription.
     * 
     * @param countryOfImport
     */
    public void setCountryOfImport(java.lang.String countryOfImport) {
        this.countryOfImport = countryOfImport;
    }


    /**
     * Gets the searchText value for this LookupHSForLegalDescription.
     * 
     * @return searchText
     */
    public java.lang.String getSearchText() {
        return searchText;
    }


    /**
     * Sets the searchText value for this LookupHSForLegalDescription.
     * 
     * @param searchText
     */
    public void setSearchText(java.lang.String searchText) {
        this.searchText = searchText;
    }


    /**
     * Gets the dateOfImport value for this LookupHSForLegalDescription.
     * 
     * @return dateOfImport
     */
    public java.lang.String getDateOfImport() {
        return dateOfImport;
    }


    /**
     * Sets the dateOfImport value for this LookupHSForLegalDescription.
     * 
     * @param dateOfImport
     */
    public void setDateOfImport(java.lang.String dateOfImport) {
        this.dateOfImport = dateOfImport;
    }


    /**
     * Gets the classificationType value for this LookupHSForLegalDescription.
     * 
     * @return classificationType
     */
    public java.lang.String getClassificationType() {
        return classificationType;
    }


    /**
     * Sets the classificationType value for this LookupHSForLegalDescription.
     * 
     * @param classificationType
     */
    public void setClassificationType(java.lang.String classificationType) {
        this.classificationType = classificationType;
    }


    /**
     * Gets the matchLevel value for this LookupHSForLegalDescription.
     * 
     * @return matchLevel
     */
    public java.lang.String getMatchLevel() {
        return matchLevel;
    }


    /**
     * Sets the matchLevel value for this LookupHSForLegalDescription.
     * 
     * @param matchLevel
     */
    public void setMatchLevel(java.lang.String matchLevel) {
        this.matchLevel = matchLevel;
    }


    /**
     * Gets the searchType value for this LookupHSForLegalDescription.
     * 
     * @return searchType
     */
    public java.lang.String getSearchType() {
        return searchType;
    }


    /**
     * Sets the searchType value for this LookupHSForLegalDescription.
     * 
     * @param searchType
     */
    public void setSearchType(java.lang.String searchType) {
        this.searchType = searchType;
    }


    /**
     * Gets the accessToken value for this LookupHSForLegalDescription.
     * 
     * @return accessToken
     */
    public java.lang.String getAccessToken() {
        return accessToken;
    }


    /**
     * Sets the accessToken value for this LookupHSForLegalDescription.
     * 
     * @param accessToken
     */
    public void setAccessToken(java.lang.String accessToken) {
        this.accessToken = accessToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LookupHSForLegalDescription)) return false;
        LookupHSForLegalDescription other = (LookupHSForLegalDescription) obj;
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
            ((this.searchText==null && other.getSearchText()==null) || 
             (this.searchText!=null &&
              this.searchText.equals(other.getSearchText()))) &&
            ((this.dateOfImport==null && other.getDateOfImport()==null) || 
             (this.dateOfImport!=null &&
              this.dateOfImport.equals(other.getDateOfImport()))) &&
            ((this.classificationType==null && other.getClassificationType()==null) || 
             (this.classificationType!=null &&
              this.classificationType.equals(other.getClassificationType()))) &&
            ((this.matchLevel==null && other.getMatchLevel()==null) || 
             (this.matchLevel!=null &&
              this.matchLevel.equals(other.getMatchLevel()))) &&
            ((this.searchType==null && other.getSearchType()==null) || 
             (this.searchType!=null &&
              this.searchType.equals(other.getSearchType()))) &&
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
        if (getSearchText() != null) {
            _hashCode += getSearchText().hashCode();
        }
        if (getDateOfImport() != null) {
            _hashCode += getDateOfImport().hashCode();
        }
        if (getClassificationType() != null) {
            _hashCode += getClassificationType().hashCode();
        }
        if (getMatchLevel() != null) {
            _hashCode += getMatchLevel().hashCode();
        }
        if (getSearchType() != null) {
            _hashCode += getSearchType().hashCode();
        }
        if (getAccessToken() != null) {
            _hashCode += getAccessToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LookupHSForLegalDescription.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">LookupHSForLegalDescription"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryOfImport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "CountryOfImport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "SearchText"));
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
        elemField.setFieldName("classificationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ClassificationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "MatchLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "SearchType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

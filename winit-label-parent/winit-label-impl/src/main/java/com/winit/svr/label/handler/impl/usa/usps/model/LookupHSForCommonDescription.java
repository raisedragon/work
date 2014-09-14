/**
 * LookupHSForCommonDescription.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class LookupHSForCommonDescription  implements java.io.Serializable {
    private java.lang.String searchText;

    private java.lang.String matchLevel;

    private java.lang.String accessToken;

    public LookupHSForCommonDescription() {
    }

    public LookupHSForCommonDescription(
           java.lang.String searchText,
           java.lang.String matchLevel,
           java.lang.String accessToken) {
           this.searchText = searchText;
           this.matchLevel = matchLevel;
           this.accessToken = accessToken;
    }


    /**
     * Gets the searchText value for this LookupHSForCommonDescription.
     * 
     * @return searchText
     */
    public java.lang.String getSearchText() {
        return searchText;
    }


    /**
     * Sets the searchText value for this LookupHSForCommonDescription.
     * 
     * @param searchText
     */
    public void setSearchText(java.lang.String searchText) {
        this.searchText = searchText;
    }


    /**
     * Gets the matchLevel value for this LookupHSForCommonDescription.
     * 
     * @return matchLevel
     */
    public java.lang.String getMatchLevel() {
        return matchLevel;
    }


    /**
     * Sets the matchLevel value for this LookupHSForCommonDescription.
     * 
     * @param matchLevel
     */
    public void setMatchLevel(java.lang.String matchLevel) {
        this.matchLevel = matchLevel;
    }


    /**
     * Gets the accessToken value for this LookupHSForCommonDescription.
     * 
     * @return accessToken
     */
    public java.lang.String getAccessToken() {
        return accessToken;
    }


    /**
     * Sets the accessToken value for this LookupHSForCommonDescription.
     * 
     * @param accessToken
     */
    public void setAccessToken(java.lang.String accessToken) {
        this.accessToken = accessToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LookupHSForCommonDescription)) return false;
        LookupHSForCommonDescription other = (LookupHSForCommonDescription) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchText==null && other.getSearchText()==null) || 
             (this.searchText!=null &&
              this.searchText.equals(other.getSearchText()))) &&
            ((this.matchLevel==null && other.getMatchLevel()==null) || 
             (this.matchLevel!=null &&
              this.matchLevel.equals(other.getMatchLevel()))) &&
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
        if (getSearchText() != null) {
            _hashCode += getSearchText().hashCode();
        }
        if (getMatchLevel() != null) {
            _hashCode += getMatchLevel().hashCode();
        }
        if (getAccessToken() != null) {
            _hashCode += getAccessToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LookupHSForCommonDescription.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", ">LookupHSForCommonDescription"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "SearchText"));
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

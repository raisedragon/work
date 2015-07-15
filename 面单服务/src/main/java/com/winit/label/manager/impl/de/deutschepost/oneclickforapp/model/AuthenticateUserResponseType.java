/**
 * AuthenticateUserResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Structure including user and wallet information
 */
public class AuthenticateUserResponseType  implements java.io.Serializable {
    /* Token for user identification */
    private java.lang.String userToken;

    /* Current credit balance on the postage account in eurocents */
    private int walletBalance;

    /* TRUE if the user has agreed to the current GT&Cs, otherwise
     * FALSE */
    private boolean showTermsAndConditions;

    private java.lang.String infoMessage;

    public AuthenticateUserResponseType() {
    }

    public AuthenticateUserResponseType(
           java.lang.String userToken,
           int walletBalance,
           boolean showTermsAndConditions,
           java.lang.String infoMessage) {
           this.userToken = userToken;
           this.walletBalance = walletBalance;
           this.showTermsAndConditions = showTermsAndConditions;
           this.infoMessage = infoMessage;
    }


    /**
     * Gets the userToken value for this AuthenticateUserResponseType.
     * 
     * @return userToken   * Token for user identification
     */
    public java.lang.String getUserToken() {
        return userToken;
    }


    /**
     * Sets the userToken value for this AuthenticateUserResponseType.
     * 
     * @param userToken   * Token for user identification
     */
    public void setUserToken(java.lang.String userToken) {
        this.userToken = userToken;
    }


    /**
     * Gets the walletBalance value for this AuthenticateUserResponseType.
     * 
     * @return walletBalance   * Current credit balance on the postage account in eurocents
     */
    public int getWalletBalance() {
        return walletBalance;
    }


    /**
     * Sets the walletBalance value for this AuthenticateUserResponseType.
     * 
     * @param walletBalance   * Current credit balance on the postage account in eurocents
     */
    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }


    /**
     * Gets the showTermsAndConditions value for this AuthenticateUserResponseType.
     * 
     * @return showTermsAndConditions   * TRUE if the user has agreed to the current GT&Cs, otherwise
     * FALSE
     */
    public boolean isShowTermsAndConditions() {
        return showTermsAndConditions;
    }


    /**
     * Sets the showTermsAndConditions value for this AuthenticateUserResponseType.
     * 
     * @param showTermsAndConditions   * TRUE if the user has agreed to the current GT&Cs, otherwise
     * FALSE
     */
    public void setShowTermsAndConditions(boolean showTermsAndConditions) {
        this.showTermsAndConditions = showTermsAndConditions;
    }


    /**
     * Gets the infoMessage value for this AuthenticateUserResponseType.
     * 
     * @return infoMessage
     */
    public java.lang.String getInfoMessage() {
        return infoMessage;
    }


    /**
     * Sets the infoMessage value for this AuthenticateUserResponseType.
     * 
     * @param infoMessage
     */
    public void setInfoMessage(java.lang.String infoMessage) {
        this.infoMessage = infoMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthenticateUserResponseType)) return false;
        AuthenticateUserResponseType other = (AuthenticateUserResponseType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userToken==null && other.getUserToken()==null) || 
             (this.userToken!=null &&
              this.userToken.equals(other.getUserToken()))) &&
            this.walletBalance == other.getWalletBalance() &&
            this.showTermsAndConditions == other.isShowTermsAndConditions() &&
            ((this.infoMessage==null && other.getInfoMessage()==null) || 
             (this.infoMessage!=null &&
              this.infoMessage.equals(other.getInfoMessage())));
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
        if (getUserToken() != null) {
            _hashCode += getUserToken().hashCode();
        }
        _hashCode += getWalletBalance();
        _hashCode += (isShowTermsAndConditions() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInfoMessage() != null) {
            _hashCode += getInfoMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthenticateUserResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserResponseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "userToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("walletBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "walletBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Amount"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showTermsAndConditions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "showTermsAndConditions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "infoMessage"));
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

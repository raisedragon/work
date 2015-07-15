/**
 * ShoppingCartPDFRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;


/**
 * Content of the shopping cart
 */
public class ShoppingCartPDFRequestType  implements java.io.Serializable {
    /* Unique user ID */
    private java.lang.String userToken;

    private java.lang.String shopOrderId;

    /* ID of the print format in which the item is printed */
    private int pageFormatId;

    /* PPL which the products in the shopping cart relate to */
    private java.lang.Integer ppl;

    /* Shopping cart items */
    private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition[] positions;

    /* Total value of the shopping cart */
    private int total;

    public ShoppingCartPDFRequestType() {
    }

    public ShoppingCartPDFRequestType(
           java.lang.String userToken,
           java.lang.String shopOrderId,
           int pageFormatId,
           java.lang.Integer ppl,
           com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition[] positions,
           int total) {
           this.userToken = userToken;
           this.shopOrderId = shopOrderId;
           this.pageFormatId = pageFormatId;
           this.ppl = ppl;
           this.positions = positions;
           this.total = total;
    }


    /**
     * Gets the userToken value for this ShoppingCartPDFRequestType.
     * 
     * @return userToken   * Unique user ID
     */
    public java.lang.String getUserToken() {
        return userToken;
    }


    /**
     * Sets the userToken value for this ShoppingCartPDFRequestType.
     * 
     * @param userToken   * Unique user ID
     */
    public void setUserToken(java.lang.String userToken) {
        this.userToken = userToken;
    }


    /**
     * Gets the shopOrderId value for this ShoppingCartPDFRequestType.
     * 
     * @return shopOrderId
     */
    public java.lang.String getShopOrderId() {
        return shopOrderId;
    }


    /**
     * Sets the shopOrderId value for this ShoppingCartPDFRequestType.
     * 
     * @param shopOrderId
     */
    public void setShopOrderId(java.lang.String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }


    /**
     * Gets the pageFormatId value for this ShoppingCartPDFRequestType.
     * 
     * @return pageFormatId   * ID of the print format in which the item is printed
     */
    public int getPageFormatId() {
        return pageFormatId;
    }


    /**
     * Sets the pageFormatId value for this ShoppingCartPDFRequestType.
     * 
     * @param pageFormatId   * ID of the print format in which the item is printed
     */
    public void setPageFormatId(int pageFormatId) {
        this.pageFormatId = pageFormatId;
    }


    /**
     * Gets the ppl value for this ShoppingCartPDFRequestType.
     * 
     * @return ppl   * PPL which the products in the shopping cart relate to
     */
    public java.lang.Integer getPpl() {
        return ppl;
    }


    /**
     * Sets the ppl value for this ShoppingCartPDFRequestType.
     * 
     * @param ppl   * PPL which the products in the shopping cart relate to
     */
    public void setPpl(java.lang.Integer ppl) {
        this.ppl = ppl;
    }


    /**
     * Gets the positions value for this ShoppingCartPDFRequestType.
     * 
     * @return positions   * Shopping cart items
     */
    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition[] getPositions() {
        return positions;
    }


    /**
     * Sets the positions value for this ShoppingCartPDFRequestType.
     * 
     * @param positions   * Shopping cart items
     */
    public void setPositions(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition[] positions) {
        this.positions = positions;
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition getPositions(int i) {
        return this.positions[i];
    }

    public void setPositions(int i, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition _value) {
        this.positions[i] = _value;
    }


    /**
     * Gets the total value for this ShoppingCartPDFRequestType.
     * 
     * @return total   * Total value of the shopping cart
     */
    public int getTotal() {
        return total;
    }


    /**
     * Sets the total value for this ShoppingCartPDFRequestType.
     * 
     * @param total   * Total value of the shopping cart
     */
    public void setTotal(int total) {
        this.total = total;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartPDFRequestType)) return false;
        ShoppingCartPDFRequestType other = (ShoppingCartPDFRequestType) obj;
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
            ((this.shopOrderId==null && other.getShopOrderId()==null) || 
             (this.shopOrderId!=null &&
              this.shopOrderId.equals(other.getShopOrderId()))) &&
            this.pageFormatId == other.getPageFormatId() &&
            ((this.ppl==null && other.getPpl()==null) || 
             (this.ppl!=null &&
              this.ppl.equals(other.getPpl()))) &&
            ((this.positions==null && other.getPositions()==null) || 
             (this.positions!=null &&
              java.util.Arrays.equals(this.positions, other.getPositions()))) &&
            this.total == other.getTotal();
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
        if (getShopOrderId() != null) {
            _hashCode += getShopOrderId().hashCode();
        }
        _hashCode += getPageFormatId();
        if (getPpl() != null) {
            _hashCode += getPpl().hashCode();
        }
        if (getPositions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPositions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPositions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getTotal();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartPDFRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPDFRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "userToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopOrderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "shopOrderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageFormatId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "pageFormatId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ppl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ppl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "positions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPDFPosition"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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

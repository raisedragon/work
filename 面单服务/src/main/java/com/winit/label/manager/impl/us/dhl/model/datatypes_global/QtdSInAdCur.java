//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.04 at 03:48:22 ���� CST 
//


package com.winit.label.manager.impl.us.dhl.model.datatypes_global;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * QtdSInAdCur
 * 
 * <p>Java class for QtdSInAdCur complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QtdSInAdCur">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CurrencyCode" type="{http://www.dhl.com/datatypes_global}CurrencyCode"/>
 *         &lt;element name="CurrencyRoleTypeCode" type="{http://www.dhl.com/datatypes_global}CurrencyRoleTypeCode"/>
 *         &lt;element name="PackageCharge" type="{http://www.dhl.com/datatypes_global}PackageCharge"/>
 *         &lt;element name="ShippingCharge" type="{http://www.dhl.com/datatypes_global}ShippingCharge"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QtdSInAdCur", propOrder = {
    "currencyCode",
    "currencyRoleTypeCode",
    "packageCharge",
    "shippingCharge"
})
public class QtdSInAdCur {

    @XmlElement(name = "CurrencyCode", required = true)
    protected String currencyCode;
    @XmlElement(name = "CurrencyRoleTypeCode", required = true)
    protected CurrencyRoleTypeCode currencyRoleTypeCode;
    @XmlElement(name = "PackageCharge", required = true)
    protected BigDecimal packageCharge;
    @XmlElement(name = "ShippingCharge", required = true)
    protected BigDecimal shippingCharge;

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the currencyRoleTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyRoleTypeCode }
     *     
     */
    public CurrencyRoleTypeCode getCurrencyRoleTypeCode() {
        return currencyRoleTypeCode;
    }

    /**
     * Sets the value of the currencyRoleTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyRoleTypeCode }
     *     
     */
    public void setCurrencyRoleTypeCode(CurrencyRoleTypeCode value) {
        this.currencyRoleTypeCode = value;
    }

    /**
     * Gets the value of the packageCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPackageCharge() {
        return packageCharge;
    }

    /**
     * Sets the value of the packageCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPackageCharge(BigDecimal value) {
        this.packageCharge = value;
    }

    /**
     * Gets the value of the shippingCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShippingCharge() {
        return shippingCharge;
    }

    /**
     * Sets the value of the shippingCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShippingCharge(BigDecimal value) {
        this.shippingCharge = value;
    }

}

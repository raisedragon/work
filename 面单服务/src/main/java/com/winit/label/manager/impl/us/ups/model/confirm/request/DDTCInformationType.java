//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:39:52 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.confirm.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DDTCInformationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DDTCInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITARExemptionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USMLCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EligiblePartyIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnitOfMeasurement" type="{}CodeType" minOccurs="0"/>
 *         &lt;element name="SignificantMilitaryEquipmentIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACMNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DDTCInformationType", propOrder = {
    "itarExemptionNumber",
    "usmlCategoryCode",
    "eligiblePartyIndicator",
    "registrationNumber",
    "quantity",
    "unitOfMeasurement",
    "significantMilitaryEquipmentIndicator",
    "acmNumber"
})
public class DDTCInformationType {

    @XmlElement(name = "ITARExemptionNumber")
    protected String itarExemptionNumber;
    @XmlElement(name = "USMLCategoryCode")
    protected String usmlCategoryCode;
    @XmlElement(name = "EligiblePartyIndicator")
    protected String eligiblePartyIndicator;
    @XmlElement(name = "RegistrationNumber")
    protected String registrationNumber;
    @XmlElement(name = "Quantity")
    protected String quantity;
    @XmlElement(name = "UnitOfMeasurement")
    protected CodeType unitOfMeasurement;
    @XmlElement(name = "SignificantMilitaryEquipmentIndicator")
    protected String significantMilitaryEquipmentIndicator;
    @XmlElement(name = "ACMNumber")
    protected String acmNumber;

    /**
     * 获取itarExemptionNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITARExemptionNumber() {
        return itarExemptionNumber;
    }

    /**
     * 设置itarExemptionNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITARExemptionNumber(String value) {
        this.itarExemptionNumber = value;
    }

    /**
     * 获取usmlCategoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSMLCategoryCode() {
        return usmlCategoryCode;
    }

    /**
     * 设置usmlCategoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSMLCategoryCode(String value) {
        this.usmlCategoryCode = value;
    }

    /**
     * 获取eligiblePartyIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligiblePartyIndicator() {
        return eligiblePartyIndicator;
    }

    /**
     * 设置eligiblePartyIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligiblePartyIndicator(String value) {
        this.eligiblePartyIndicator = value;
    }

    /**
     * 获取registrationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * 设置registrationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

    /**
     * 获取quantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * 获取unitOfMeasurement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    /**
     * 设置unitOfMeasurement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setUnitOfMeasurement(CodeType value) {
        this.unitOfMeasurement = value;
    }

    /**
     * 获取significantMilitaryEquipmentIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignificantMilitaryEquipmentIndicator() {
        return significantMilitaryEquipmentIndicator;
    }

    /**
     * 设置significantMilitaryEquipmentIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignificantMilitaryEquipmentIndicator(String value) {
        this.significantMilitaryEquipmentIndicator = value;
    }

    /**
     * 获取acmNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACMNumber() {
        return acmNumber;
    }

    /**
     * 设置acmNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACMNumber(String value) {
        this.acmNumber = value;
    }

}

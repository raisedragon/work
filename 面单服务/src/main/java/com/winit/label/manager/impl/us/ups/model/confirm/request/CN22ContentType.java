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
 * <p>CN22ContentType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CN22ContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CN22ContentQuantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22ContentDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22ContentWeight" type="{}ProductWeightType" minOccurs="0"/>
 *         &lt;element name="CN22ContentTotalValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22ContentCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22ContentCountryOfOrigin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22ContentTariffNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CN22ContentType", propOrder = {
    "cn22ContentQuantity",
    "cn22ContentDescription",
    "cn22ContentWeight",
    "cn22ContentTotalValue",
    "cn22ContentCurrencyCode",
    "cn22ContentCountryOfOrigin",
    "cn22ContentTariffNumber"
})
public class CN22ContentType {

    @XmlElement(name = "CN22ContentQuantity")
    protected String cn22ContentQuantity;
    @XmlElement(name = "CN22ContentDescription")
    protected String cn22ContentDescription;
    @XmlElement(name = "CN22ContentWeight")
    protected ProductWeightType cn22ContentWeight;
    @XmlElement(name = "CN22ContentTotalValue")
    protected String cn22ContentTotalValue;
    @XmlElement(name = "CN22ContentCurrencyCode")
    protected String cn22ContentCurrencyCode;
    @XmlElement(name = "CN22ContentCountryOfOrigin")
    protected String cn22ContentCountryOfOrigin;
    @XmlElement(name = "CN22ContentTariffNumber")
    protected String cn22ContentTariffNumber;

    /**
     * 获取cn22ContentQuantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22ContentQuantity() {
        return cn22ContentQuantity;
    }

    /**
     * 设置cn22ContentQuantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22ContentQuantity(String value) {
        this.cn22ContentQuantity = value;
    }

    /**
     * 获取cn22ContentDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22ContentDescription() {
        return cn22ContentDescription;
    }

    /**
     * 设置cn22ContentDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22ContentDescription(String value) {
        this.cn22ContentDescription = value;
    }

    /**
     * 获取cn22ContentWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProductWeightType }
     *     
     */
    public ProductWeightType getCN22ContentWeight() {
        return cn22ContentWeight;
    }

    /**
     * 设置cn22ContentWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProductWeightType }
     *     
     */
    public void setCN22ContentWeight(ProductWeightType value) {
        this.cn22ContentWeight = value;
    }

    /**
     * 获取cn22ContentTotalValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22ContentTotalValue() {
        return cn22ContentTotalValue;
    }

    /**
     * 设置cn22ContentTotalValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22ContentTotalValue(String value) {
        this.cn22ContentTotalValue = value;
    }

    /**
     * 获取cn22ContentCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22ContentCurrencyCode() {
        return cn22ContentCurrencyCode;
    }

    /**
     * 设置cn22ContentCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22ContentCurrencyCode(String value) {
        this.cn22ContentCurrencyCode = value;
    }

    /**
     * 获取cn22ContentCountryOfOrigin属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22ContentCountryOfOrigin() {
        return cn22ContentCountryOfOrigin;
    }

    /**
     * 设置cn22ContentCountryOfOrigin属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22ContentCountryOfOrigin(String value) {
        this.cn22ContentCountryOfOrigin = value;
    }

    /**
     * 获取cn22ContentTariffNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22ContentTariffNumber() {
        return cn22ContentTariffNumber;
    }

    /**
     * 设置cn22ContentTariffNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22ContentTariffNumber(String value) {
        this.cn22ContentTariffNumber = value;
    }

}

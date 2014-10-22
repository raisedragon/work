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
 * <p>UltimateConsigneeType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UltimateConsigneeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}CompanyName" minOccurs="0"/>
 *         &lt;element name="Address" type="{}AddressType" minOccurs="0"/>
 *         &lt;element name="UltimateConsigneeType" type="{}UltimateConsigneeTypeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UltimateConsigneeType", propOrder = {
    "companyName",
    "address",
    "ultimateConsigneeType"
})
public class UltimateConsigneeType {

    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "UltimateConsigneeType")
    protected UltimateConsigneeTypeType ultimateConsigneeType;

    /**
     * 获取companyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置companyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * 获取address属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * 设置address属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * 获取ultimateConsigneeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UltimateConsigneeTypeType }
     *     
     */
    public UltimateConsigneeTypeType getUltimateConsigneeType() {
        return ultimateConsigneeType;
    }

    /**
     * 设置ultimateConsigneeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UltimateConsigneeTypeType }
     *     
     */
    public void setUltimateConsigneeType(UltimateConsigneeTypeType value) {
        this.ultimateConsigneeType = value;
    }

}

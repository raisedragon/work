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
 * <p>EEIFilingOptionType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EEIFilingOptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UPSFiled" type="{}UPSFiledType" minOccurs="0"/>
 *         &lt;element name="ShipperFiled" type="{}ShipperFiledType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EEIFilingOptionType", propOrder = {
    "code",
    "eMailAddress",
    "description",
    "upsFiled",
    "shipperFiled"
})
public class EEIFilingOptionType {

    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "EMailAddress")
    protected String eMailAddress;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "UPSFiled")
    protected UPSFiledType upsFiled;
    @XmlElement(name = "ShipperFiled")
    protected ShipperFiledType shipperFiled;

    /**
     * 获取code属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * 获取eMailAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMailAddress() {
        return eMailAddress;
    }

    /**
     * 设置eMailAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMailAddress(String value) {
        this.eMailAddress = value;
    }

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取upsFiled属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UPSFiledType }
     *     
     */
    public UPSFiledType getUPSFiled() {
        return upsFiled;
    }

    /**
     * 设置upsFiled属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UPSFiledType }
     *     
     */
    public void setUPSFiled(UPSFiledType value) {
        this.upsFiled = value;
    }

    /**
     * 获取shipperFiled属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipperFiledType }
     *     
     */
    public ShipperFiledType getShipperFiled() {
        return shipperFiled;
    }

    /**
     * 设置shipperFiled属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipperFiledType }
     *     
     */
    public void setShipperFiled(ShipperFiledType value) {
        this.shipperFiled = value;
    }

}

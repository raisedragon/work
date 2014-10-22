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
 * <p>PackageServiceOptionsType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PackageServiceOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeliveryConfirmation" type="{}PackageServiceOptionsDeliveryConfirmationType" minOccurs="0"/>
 *         &lt;element name="InsuredValue" type="{}InsuredValueType" minOccurs="0"/>
 *         &lt;element name="COD" type="{}PackageServiceOptionsCODType" minOccurs="0"/>
 *         &lt;element name="VerbalConfirmation" type="{}VerbalConfirmationType" minOccurs="0"/>
 *         &lt;element name="ShipperReleaseIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notification" type="{}PackageServiceOptionsNotificationType" minOccurs="0"/>
 *         &lt;element name="DryIce" type="{}DryIceType" minOccurs="0"/>
 *         &lt;element name="UPSPremiumCareIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageServiceOptionsType", propOrder = {
    "deliveryConfirmation",
    "insuredValue",
    "cod",
    "verbalConfirmation",
    "shipperReleaseIndicator",
    "notification",
    "dryIce",
    "upsPremiumCareIndicator"
})
public class PackageServiceOptionsType {

    @XmlElement(name = "DeliveryConfirmation")
    protected PackageServiceOptionsDeliveryConfirmationType deliveryConfirmation;
    @XmlElement(name = "InsuredValue")
    protected InsuredValueType insuredValue;
    @XmlElement(name = "COD")
    protected PackageServiceOptionsCODType cod;
    @XmlElement(name = "VerbalConfirmation")
    protected VerbalConfirmationType verbalConfirmation;
    @XmlElement(name = "ShipperReleaseIndicator")
    protected String shipperReleaseIndicator;
    @XmlElement(name = "Notification")
    protected PackageServiceOptionsNotificationType notification;
    @XmlElement(name = "DryIce")
    protected DryIceType dryIce;
    @XmlElement(name = "UPSPremiumCareIndicator")
    protected String upsPremiumCareIndicator;

    /**
     * 获取deliveryConfirmation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageServiceOptionsDeliveryConfirmationType }
     *     
     */
    public PackageServiceOptionsDeliveryConfirmationType getDeliveryConfirmation() {
        return deliveryConfirmation;
    }

    /**
     * 设置deliveryConfirmation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageServiceOptionsDeliveryConfirmationType }
     *     
     */
    public void setDeliveryConfirmation(PackageServiceOptionsDeliveryConfirmationType value) {
        this.deliveryConfirmation = value;
    }

    /**
     * 获取insuredValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InsuredValueType }
     *     
     */
    public InsuredValueType getInsuredValue() {
        return insuredValue;
    }

    /**
     * 设置insuredValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InsuredValueType }
     *     
     */
    public void setInsuredValue(InsuredValueType value) {
        this.insuredValue = value;
    }

    /**
     * 获取cod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageServiceOptionsCODType }
     *     
     */
    public PackageServiceOptionsCODType getCOD() {
        return cod;
    }

    /**
     * 设置cod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageServiceOptionsCODType }
     *     
     */
    public void setCOD(PackageServiceOptionsCODType value) {
        this.cod = value;
    }

    /**
     * 获取verbalConfirmation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VerbalConfirmationType }
     *     
     */
    public VerbalConfirmationType getVerbalConfirmation() {
        return verbalConfirmation;
    }

    /**
     * 设置verbalConfirmation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VerbalConfirmationType }
     *     
     */
    public void setVerbalConfirmation(VerbalConfirmationType value) {
        this.verbalConfirmation = value;
    }

    /**
     * 获取shipperReleaseIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperReleaseIndicator() {
        return shipperReleaseIndicator;
    }

    /**
     * 设置shipperReleaseIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperReleaseIndicator(String value) {
        this.shipperReleaseIndicator = value;
    }

    /**
     * 获取notification属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageServiceOptionsNotificationType }
     *     
     */
    public PackageServiceOptionsNotificationType getNotification() {
        return notification;
    }

    /**
     * 设置notification属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageServiceOptionsNotificationType }
     *     
     */
    public void setNotification(PackageServiceOptionsNotificationType value) {
        this.notification = value;
    }

    /**
     * 获取dryIce属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DryIceType }
     *     
     */
    public DryIceType getDryIce() {
        return dryIce;
    }

    /**
     * 设置dryIce属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DryIceType }
     *     
     */
    public void setDryIce(DryIceType value) {
        this.dryIce = value;
    }

    /**
     * 获取upsPremiumCareIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPSPremiumCareIndicator() {
        return upsPremiumCareIndicator;
    }

    /**
     * 设置upsPremiumCareIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPSPremiumCareIndicator(String value) {
        this.upsPremiumCareIndicator = value;
    }

}

//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:39:53 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.confirm.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Response"/>
 *         &lt;element ref="{}ShipmentCharges" minOccurs="0"/>
 *         &lt;element ref="{}BillingWeight"/>
 *         &lt;element ref="{}ShipmentIdentificationNumber"/>
 *         &lt;element ref="{}ShipmentDigest"/>
 *         &lt;element ref="{}NegotiatedRates" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "response",
    "shipmentCharges",
    "billingWeight",
    "shipmentIdentificationNumber",
    "shipmentDigest",
    "negotiatedRates"
})
@XmlRootElement(name = "ShipmentConfirmResponse")
public class ShipmentConfirmResponse {

    @XmlElement(name = "Response", required = true)
    protected Response response;
    @XmlElement(name = "ShipmentCharges")
    protected ShipmentCharges shipmentCharges;
    @XmlElement(name = "BillingWeight", required = true)
    protected BillingWeight billingWeight;
    @XmlElement(name = "ShipmentIdentificationNumber", required = true)
    protected String shipmentIdentificationNumber;
    @XmlElement(name = "ShipmentDigest", required = true)
    protected String shipmentDigest;
    @XmlElement(name = "NegotiatedRates")
    protected NegotiatedRates negotiatedRates;

    /**
     * 获取response属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getResponse() {
        return response;
    }

    /**
     * 设置response属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setResponse(Response value) {
        this.response = value;
    }

    /**
     * 获取shipmentCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentCharges }
     *     
     */
    public ShipmentCharges getShipmentCharges() {
        return shipmentCharges;
    }

    /**
     * 设置shipmentCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentCharges }
     *     
     */
    public void setShipmentCharges(ShipmentCharges value) {
        this.shipmentCharges = value;
    }

    /**
     * 获取billingWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillingWeight }
     *     
     */
    public BillingWeight getBillingWeight() {
        return billingWeight;
    }

    /**
     * 设置billingWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillingWeight }
     *     
     */
    public void setBillingWeight(BillingWeight value) {
        this.billingWeight = value;
    }

    /**
     * 获取shipmentIdentificationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentIdentificationNumber() {
        return shipmentIdentificationNumber;
    }

    /**
     * 设置shipmentIdentificationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentIdentificationNumber(String value) {
        this.shipmentIdentificationNumber = value;
    }

    /**
     * 获取shipmentDigest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentDigest() {
        return shipmentDigest;
    }

    /**
     * 设置shipmentDigest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentDigest(String value) {
        this.shipmentDigest = value;
    }

    /**
     * 获取negotiatedRates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NegotiatedRates }
     *     
     */
    public NegotiatedRates getNegotiatedRates() {
        return negotiatedRates;
    }

    /**
     * 设置negotiatedRates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NegotiatedRates }
     *     
     */
    public void setNegotiatedRates(NegotiatedRates value) {
        this.negotiatedRates = value;
    }

}

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
 * <p>ShipmentServiceOptionsCODType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ShipmentServiceOptionsCODType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODFundsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODAmount" type="{}ShipmentServiceOptionsCODAmountType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentServiceOptionsCODType", propOrder = {
    "codCode",
    "codFundsCode",
    "codAmount"
})
public class ShipmentServiceOptionsCODType {

    @XmlElement(name = "CODCode", required = true)
    protected String codCode;
    @XmlElement(name = "CODFundsCode")
    protected String codFundsCode;
    @XmlElement(name = "CODAmount", required = true)
    protected ShipmentServiceOptionsCODAmountType codAmount;

    /**
     * 获取codCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODCode() {
        return codCode;
    }

    /**
     * 设置codCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODCode(String value) {
        this.codCode = value;
    }

    /**
     * 获取codFundsCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODFundsCode() {
        return codFundsCode;
    }

    /**
     * 设置codFundsCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODFundsCode(String value) {
        this.codFundsCode = value;
    }

    /**
     * 获取codAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsCODAmountType }
     *     
     */
    public ShipmentServiceOptionsCODAmountType getCODAmount() {
        return codAmount;
    }

    /**
     * 设置codAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsCODAmountType }
     *     
     */
    public void setCODAmount(ShipmentServiceOptionsCODAmountType value) {
        this.codAmount = value;
    }

}

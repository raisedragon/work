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
 * <p>LabelDeliveryType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LabelDeliveryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMailMessage" type="{}LabelDeliveryEMailMessageType" minOccurs="0"/>
 *         &lt;element name="LabelLinksIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelDeliveryType", propOrder = {
    "eMailMessage",
    "labelLinksIndicator"
})
public class LabelDeliveryType {

    @XmlElement(name = "EMailMessage")
    protected LabelDeliveryEMailMessageType eMailMessage;
    @XmlElement(name = "LabelLinksIndicator")
    protected String labelLinksIndicator;

    /**
     * 获取eMailMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LabelDeliveryEMailMessageType }
     *     
     */
    public LabelDeliveryEMailMessageType getEMailMessage() {
        return eMailMessage;
    }

    /**
     * 设置eMailMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LabelDeliveryEMailMessageType }
     *     
     */
    public void setEMailMessage(LabelDeliveryEMailMessageType value) {
        this.eMailMessage = value;
    }

    /**
     * 获取labelLinksIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelLinksIndicator() {
        return labelLinksIndicator;
    }

    /**
     * 设置labelLinksIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelLinksIndicator(String value) {
        this.labelLinksIndicator = value;
    }

}

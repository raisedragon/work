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
 * <p>ShipmentServiceOptionsNotificationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ShipmentServiceOptionsNotificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NotificationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMailMessage" type="{}ShipmentServiceOptionsNotificationEMailMessageType"/>
 *         &lt;element name="VoiceMessage" type="{}ShipmentServiceOptionsNotificationVoiceMessageType" minOccurs="0"/>
 *         &lt;element name="TextMessage" type="{}ShipmentServiceOptionsNotificationTextMessageType" minOccurs="0"/>
 *         &lt;element name="Locale" type="{}LocaleType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentServiceOptionsNotificationType", propOrder = {
    "notificationCode",
    "eMailMessage",
    "voiceMessage",
    "textMessage",
    "locale"
})
public class ShipmentServiceOptionsNotificationType {

    @XmlElement(name = "NotificationCode", required = true)
    protected String notificationCode;
    @XmlElement(name = "EMailMessage", required = true)
    protected ShipmentServiceOptionsNotificationEMailMessageType eMailMessage;
    @XmlElement(name = "VoiceMessage")
    protected ShipmentServiceOptionsNotificationVoiceMessageType voiceMessage;
    @XmlElement(name = "TextMessage")
    protected ShipmentServiceOptionsNotificationTextMessageType textMessage;
    @XmlElement(name = "Locale")
    protected LocaleType locale;

    /**
     * 获取notificationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationCode() {
        return notificationCode;
    }

    /**
     * 设置notificationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationCode(String value) {
        this.notificationCode = value;
    }

    /**
     * 获取eMailMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsNotificationEMailMessageType }
     *     
     */
    public ShipmentServiceOptionsNotificationEMailMessageType getEMailMessage() {
        return eMailMessage;
    }

    /**
     * 设置eMailMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsNotificationEMailMessageType }
     *     
     */
    public void setEMailMessage(ShipmentServiceOptionsNotificationEMailMessageType value) {
        this.eMailMessage = value;
    }

    /**
     * 获取voiceMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsNotificationVoiceMessageType }
     *     
     */
    public ShipmentServiceOptionsNotificationVoiceMessageType getVoiceMessage() {
        return voiceMessage;
    }

    /**
     * 设置voiceMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsNotificationVoiceMessageType }
     *     
     */
    public void setVoiceMessage(ShipmentServiceOptionsNotificationVoiceMessageType value) {
        this.voiceMessage = value;
    }

    /**
     * 获取textMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsNotificationTextMessageType }
     *     
     */
    public ShipmentServiceOptionsNotificationTextMessageType getTextMessage() {
        return textMessage;
    }

    /**
     * 设置textMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsNotificationTextMessageType }
     *     
     */
    public void setTextMessage(ShipmentServiceOptionsNotificationTextMessageType value) {
        this.textMessage = value;
    }

    /**
     * 获取locale属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LocaleType }
     *     
     */
    public LocaleType getLocale() {
        return locale;
    }

    /**
     * 设置locale属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LocaleType }
     *     
     */
    public void setLocale(LocaleType value) {
        this.locale = value;
    }

}

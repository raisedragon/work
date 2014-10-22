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
 * <p>PreAlertNotificationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PreAlertNotificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMailMessage" type="{}PreAlertEMailMessageType" minOccurs="0"/>
 *         &lt;element name="VoiceMessage" type="{}PreAlertVoiceMessageType" minOccurs="0"/>
 *         &lt;element name="TextMessage" type="{}PreAlertTextMessageType" minOccurs="0"/>
 *         &lt;element name="Locale" type="{}LocaleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreAlertNotificationType", propOrder = {
    "eMailMessage",
    "voiceMessage",
    "textMessage",
    "locale"
})
public class PreAlertNotificationType {

    @XmlElement(name = "EMailMessage")
    protected PreAlertEMailMessageType eMailMessage;
    @XmlElement(name = "VoiceMessage")
    protected PreAlertVoiceMessageType voiceMessage;
    @XmlElement(name = "TextMessage")
    protected PreAlertTextMessageType textMessage;
    @XmlElement(name = "Locale", required = true)
    protected LocaleType locale;

    /**
     * 获取eMailMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PreAlertEMailMessageType }
     *     
     */
    public PreAlertEMailMessageType getEMailMessage() {
        return eMailMessage;
    }

    /**
     * 设置eMailMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PreAlertEMailMessageType }
     *     
     */
    public void setEMailMessage(PreAlertEMailMessageType value) {
        this.eMailMessage = value;
    }

    /**
     * 获取voiceMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PreAlertVoiceMessageType }
     *     
     */
    public PreAlertVoiceMessageType getVoiceMessage() {
        return voiceMessage;
    }

    /**
     * 设置voiceMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PreAlertVoiceMessageType }
     *     
     */
    public void setVoiceMessage(PreAlertVoiceMessageType value) {
        this.voiceMessage = value;
    }

    /**
     * 获取textMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PreAlertTextMessageType }
     *     
     */
    public PreAlertTextMessageType getTextMessage() {
        return textMessage;
    }

    /**
     * 设置textMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PreAlertTextMessageType }
     *     
     */
    public void setTextMessage(PreAlertTextMessageType value) {
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

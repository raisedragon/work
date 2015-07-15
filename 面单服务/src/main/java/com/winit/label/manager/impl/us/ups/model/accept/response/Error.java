//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:40:10 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.accept.response;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}ErrorSeverity"/>
 *         &lt;element ref="{}ErrorCode"/>
 *         &lt;element ref="{}ErrorDescription" minOccurs="0"/>
 *         &lt;element ref="{}MinimumRetrySeconds" minOccurs="0"/>
 *         &lt;element ref="{}ErrorLocation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ErrorDigest" maxOccurs="unbounded" minOccurs="0"/>
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
    "errorSeverity",
    "errorCode",
    "errorDescription",
    "minimumRetrySeconds",
    "errorLocation",
    "errorDigest"
})
@XmlRootElement(name = "Error")
public class Error {

    @XmlElement(name = "ErrorSeverity", required = true)
    protected String errorSeverity;
    @XmlElement(name = "ErrorCode", required = true)
    protected String errorCode;
    @XmlElement(name = "ErrorDescription")
    protected String errorDescription;
    @XmlElement(name = "MinimumRetrySeconds")
    protected String minimumRetrySeconds;
    @XmlElement(name = "ErrorLocation")
    protected List<ErrorLocation> errorLocation;
    @XmlElement(name = "ErrorDigest")
    protected List<String> errorDigest;

    /**
     * 获取errorSeverity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorSeverity() {
        return errorSeverity;
    }

    /**
     * 设置errorSeverity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorSeverity(String value) {
        this.errorSeverity = value;
    }

    /**
     * 获取errorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置errorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * 获取errorDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * 设置errorDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
    }

    /**
     * 获取minimumRetrySeconds属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRetrySeconds() {
        return minimumRetrySeconds;
    }

    /**
     * 设置minimumRetrySeconds属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRetrySeconds(String value) {
        this.minimumRetrySeconds = value;
    }

    /**
     * Gets the value of the errorLocation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorLocation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorLocation }
     * 
     * 
     */
    public List<ErrorLocation> getErrorLocation() {
        if (errorLocation == null) {
            errorLocation = new ArrayList<ErrorLocation>();
        }
        return this.errorLocation;
    }

    /**
     * Gets the value of the errorDigest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorDigest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorDigest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getErrorDigest() {
        if (errorDigest == null) {
            errorDigest = new ArrayList<String>();
        }
        return this.errorDigest;
    }

}

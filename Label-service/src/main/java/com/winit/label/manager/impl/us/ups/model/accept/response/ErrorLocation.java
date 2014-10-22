//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:40:10 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.accept.response;

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
 *         &lt;element ref="{}ErrorLocationElementName" minOccurs="0"/>
 *         &lt;element ref="{}ErrorLocationAttributeName" minOccurs="0"/>
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
    "errorLocationElementName",
    "errorLocationAttributeName"
})
@XmlRootElement(name = "ErrorLocation")
public class ErrorLocation {

    @XmlElement(name = "ErrorLocationElementName")
    protected String errorLocationElementName;
    @XmlElement(name = "ErrorLocationAttributeName")
    protected String errorLocationAttributeName;

    /**
     * 获取errorLocationElementName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLocationElementName() {
        return errorLocationElementName;
    }

    /**
     * 设置errorLocationElementName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLocationElementName(String value) {
        this.errorLocationElementName = value;
    }

    /**
     * 获取errorLocationAttributeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLocationAttributeName() {
        return errorLocationAttributeName;
    }

    /**
     * 设置errorLocationAttributeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLocationAttributeName(String value) {
        this.errorLocationAttributeName = value;
    }

}

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
 * <p>PackageServiceOptionsDeliveryConfirmationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PackageServiceOptionsDeliveryConfirmationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DCISType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DCISNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageServiceOptionsDeliveryConfirmationType", propOrder = {
    "dcisType",
    "dcisNumber"
})
public class PackageServiceOptionsDeliveryConfirmationType {

    @XmlElement(name = "DCISType", required = true)
    protected String dcisType;
    @XmlElement(name = "DCISNumber")
    protected String dcisNumber;

    /**
     * 获取dcisType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDCISType() {
        return dcisType;
    }

    /**
     * 设置dcisType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDCISType(String value) {
        this.dcisType = value;
    }

    /**
     * 获取dcisNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDCISNumber() {
        return dcisNumber;
    }

    /**
     * 设置dcisNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDCISNumber(String value) {
        this.dcisNumber = value;
    }

}

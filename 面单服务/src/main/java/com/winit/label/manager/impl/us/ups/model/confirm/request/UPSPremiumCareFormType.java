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
 * <p>UPSPremiumCareFormType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UPSPremiumCareFormType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PageSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrintType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumOfCopies" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LanguageForUPSPremiumCare" type="{}LanguageForUPSPremiumCareType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSPremiumCareFormType", propOrder = {
    "shipmentDate",
    "pageSize",
    "printType",
    "numOfCopies",
    "languageForUPSPremiumCare"
})
public class UPSPremiumCareFormType {

    @XmlElement(name = "ShipmentDate", required = true)
    protected String shipmentDate;
    @XmlElement(name = "PageSize", required = true)
    protected String pageSize;
    @XmlElement(name = "PrintType", required = true)
    protected String printType;
    @XmlElement(name = "NumOfCopies", required = true)
    protected String numOfCopies;
    @XmlElement(name = "LanguageForUPSPremiumCare", required = true)
    protected LanguageForUPSPremiumCareType languageForUPSPremiumCare;

    /**
     * 获取shipmentDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentDate() {
        return shipmentDate;
    }

    /**
     * 设置shipmentDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentDate(String value) {
        this.shipmentDate = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageSize(String value) {
        this.pageSize = value;
    }

    /**
     * 获取printType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintType() {
        return printType;
    }

    /**
     * 设置printType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintType(String value) {
        this.printType = value;
    }

    /**
     * 获取numOfCopies属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumOfCopies() {
        return numOfCopies;
    }

    /**
     * 设置numOfCopies属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumOfCopies(String value) {
        this.numOfCopies = value;
    }

    /**
     * 获取languageForUPSPremiumCare属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LanguageForUPSPremiumCareType }
     *     
     */
    public LanguageForUPSPremiumCareType getLanguageForUPSPremiumCare() {
        return languageForUPSPremiumCare;
    }

    /**
     * 设置languageForUPSPremiumCare属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageForUPSPremiumCareType }
     *     
     */
    public void setLanguageForUPSPremiumCare(LanguageForUPSPremiumCareType value) {
        this.languageForUPSPremiumCare = value;
    }

}

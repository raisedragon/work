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
 *         &lt;element name="RateChart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}TransportationCharges"/>
 *         &lt;element ref="{}ServiceOptionsCharges"/>
 *         &lt;element ref="{}AccessorialCharges" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}SurCharges" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}TotalCharges"/>
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
    "rateChart",
    "transportationCharges",
    "serviceOptionsCharges",
    "accessorialCharges",
    "surCharges",
    "totalCharges"
})
@XmlRootElement(name = "ShipmentCharges")
public class ShipmentCharges {

    @XmlElement(name = "RateChart")
    protected String rateChart;
    @XmlElement(name = "TransportationCharges", required = true)
    protected MonetaryType transportationCharges;
    @XmlElement(name = "ServiceOptionsCharges", required = true)
    protected MonetaryType serviceOptionsCharges;
    @XmlElement(name = "AccessorialCharges")
    protected List<MonetaryType> accessorialCharges;
    @XmlElement(name = "SurCharges")
    protected List<MonetaryType> surCharges;
    @XmlElement(name = "TotalCharges", required = true)
    protected MonetaryType totalCharges;

    /**
     * 获取rateChart属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChart() {
        return rateChart;
    }

    /**
     * 设置rateChart属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChart(String value) {
        this.rateChart = value;
    }

    /**
     * 获取transportationCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MonetaryType }
     *     
     */
    public MonetaryType getTransportationCharges() {
        return transportationCharges;
    }

    /**
     * 设置transportationCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryType }
     *     
     */
    public void setTransportationCharges(MonetaryType value) {
        this.transportationCharges = value;
    }

    /**
     * 获取serviceOptionsCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MonetaryType }
     *     
     */
    public MonetaryType getServiceOptionsCharges() {
        return serviceOptionsCharges;
    }

    /**
     * 设置serviceOptionsCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryType }
     *     
     */
    public void setServiceOptionsCharges(MonetaryType value) {
        this.serviceOptionsCharges = value;
    }

    /**
     * Gets the value of the accessorialCharges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessorialCharges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessorialCharges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonetaryType }
     * 
     * 
     */
    public List<MonetaryType> getAccessorialCharges() {
        if (accessorialCharges == null) {
            accessorialCharges = new ArrayList<MonetaryType>();
        }
        return this.accessorialCharges;
    }

    /**
     * Gets the value of the surCharges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surCharges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurCharges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonetaryType }
     * 
     * 
     */
    public List<MonetaryType> getSurCharges() {
        if (surCharges == null) {
            surCharges = new ArrayList<MonetaryType>();
        }
        return this.surCharges;
    }

    /**
     * 获取totalCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MonetaryType }
     *     
     */
    public MonetaryType getTotalCharges() {
        return totalCharges;
    }

    /**
     * 设置totalCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryType }
     *     
     */
    public void setTotalCharges(MonetaryType value) {
        this.totalCharges = value;
    }

}

//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:39:52 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.confirm.request;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ItemizedPaymentInformationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ItemizedPaymentInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentCharge" type="{}ShipmentChargeType" maxOccurs="2"/>
 *         &lt;element name="SplitDutyVATIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemizedPaymentInformationType", propOrder = {
    "shipmentCharge",
    "splitDutyVATIndicator"
})
public class ItemizedPaymentInformationType {

    @XmlElement(name = "ShipmentCharge", required = true)
    protected List<ShipmentChargeType> shipmentCharge;
    @XmlElement(name = "SplitDutyVATIndicator")
    protected String splitDutyVATIndicator;

    /**
     * Gets the value of the shipmentCharge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentCharge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentCharge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentChargeType }
     * 
     * 
     */
    public List<ShipmentChargeType> getShipmentCharge() {
        if (shipmentCharge == null) {
            shipmentCharge = new ArrayList<ShipmentChargeType>();
        }
        return this.shipmentCharge;
    }

    /**
     * 获取splitDutyVATIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitDutyVATIndicator() {
        return splitDutyVATIndicator;
    }

    /**
     * 设置splitDutyVATIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitDutyVATIndicator(String value) {
        this.splitDutyVATIndicator = value;
    }

}

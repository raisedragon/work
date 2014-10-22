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
 * <p>BillThirdPartyType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BillThirdPartyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BillThirdPartyShipper" type="{}BillThirdPartyShipperType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillThirdPartyType", propOrder = {
    "billThirdPartyShipper"
})
public class BillThirdPartyType {

    @XmlElement(name = "BillThirdPartyShipper", required = true)
    protected BillThirdPartyShipperType billThirdPartyShipper;

    /**
     * 获取billThirdPartyShipper属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillThirdPartyShipperType }
     *     
     */
    public BillThirdPartyShipperType getBillThirdPartyShipper() {
        return billThirdPartyShipper;
    }

    /**
     * 设置billThirdPartyShipper属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillThirdPartyShipperType }
     *     
     */
    public void setBillThirdPartyShipper(BillThirdPartyShipperType value) {
        this.billThirdPartyShipper = value;
    }

}

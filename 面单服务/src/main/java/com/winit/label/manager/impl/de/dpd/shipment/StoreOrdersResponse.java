//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.21 at 03:30:12 ���� CST 
//


package com.winit.label.manager.impl.de.dpd.shipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeOrdersResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeOrdersResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderResult" type="{http://dpd.com/common/service/types/ShipmentService/2.0}storeOrdersResponseType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeOrdersResponse", propOrder = {
    "orderResult"
})
public class StoreOrdersResponse {

    @XmlElement(required = true)
    protected StoreOrdersResponseType orderResult;

    /**
     * Gets the value of the orderResult property.
     * 
     * @return
     *     possible object is
     *     {@link StoreOrdersResponseType }
     *     
     */
    public StoreOrdersResponseType getOrderResult() {
        return orderResult;
    }

    /**
     * Sets the value of the orderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreOrdersResponseType }
     *     
     */
    public void setOrderResult(StoreOrdersResponseType value) {
        this.orderResult = value;
    }

}

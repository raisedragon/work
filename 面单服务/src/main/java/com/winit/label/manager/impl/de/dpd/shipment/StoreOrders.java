//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.21 at 03:30:12 ���� CST 
//


package com.winit.label.manager.impl.de.dpd.shipment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeOrders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeOrders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paperFormat" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *               &lt;enumeration value="A4"/>
 *               &lt;enumeration value="A6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="order" type="{http://dpd.com/common/service/types/ShipmentService/2.0}shipmentServiceData" maxOccurs="30"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeOrders", propOrder = {
    "paperFormat",
    "order"
})
public class StoreOrders {

    protected String paperFormat;
    @XmlElement(required = true)
    protected List<ShipmentServiceData> order;

    /**
     * Gets the value of the paperFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaperFormat() {
        return paperFormat;
    }

    /**
     * Sets the value of the paperFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaperFormat(String value) {
        this.paperFormat = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the order property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentServiceData }
     * 
     * 
     */
    public List<ShipmentServiceData> getOrder() {
        if (order == null) {
            order = new ArrayList<ShipmentServiceData>();
        }
        return this.order;
    }
    
    public void setOrder(List<ShipmentServiceData> order) {
    	this.order = order;
    }

}

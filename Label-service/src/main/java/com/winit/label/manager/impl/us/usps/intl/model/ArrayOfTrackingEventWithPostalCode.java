
package com.winit.label.manager.impl.us.usps.intl.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfTrackingEventWithPostalCode complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrackingEventWithPostalCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackingEventWithPostalCode" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}TrackingEventWithPostalCode" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrackingEventWithPostalCode", propOrder = {
    "trackingEventWithPostalCode"
})
public class ArrayOfTrackingEventWithPostalCode {

    @XmlElement(name = "TrackingEventWithPostalCode", nillable = true)
    protected List<TrackingEventWithPostalCode> trackingEventWithPostalCode;

    /**
     * Gets the value of the trackingEventWithPostalCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackingEventWithPostalCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackingEventWithPostalCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrackingEventWithPostalCode }
     * 
     * 
     */
    public List<TrackingEventWithPostalCode> getTrackingEventWithPostalCode() {
        if (trackingEventWithPostalCode == null) {
            trackingEventWithPostalCode = new ArrayList<TrackingEventWithPostalCode>();
        }
        return this.trackingEventWithPostalCode;
    }

}

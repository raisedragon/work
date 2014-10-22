
package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackPackageWithPostalCodeResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}TrackingWithPostalCodeResult" minOccurs="0"/>
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
    "trackPackageWithPostalCodeResult"
})
@XmlRootElement(name = "TrackPackageWithPostalCodeResponse")
public class TrackPackageWithPostalCodeResponse {

    @XmlElement(name = "TrackPackageWithPostalCodeResult")
    protected TrackingWithPostalCodeResult trackPackageWithPostalCodeResult;

    /**
     * ��ȡtrackPackageWithPostalCodeResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TrackingWithPostalCodeResult }
     *     
     */
    public TrackingWithPostalCodeResult getTrackPackageWithPostalCodeResult() {
        return trackPackageWithPostalCodeResult;
    }

    /**
     * ����trackPackageWithPostalCodeResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingWithPostalCodeResult }
     *     
     */
    public void setTrackPackageWithPostalCodeResult(TrackingWithPostalCodeResult value) {
        this.trackPackageWithPostalCodeResult = value;
    }

}

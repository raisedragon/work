
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
 *         &lt;element name="TrackPackageResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}TrackResult" minOccurs="0"/>
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
    "trackPackageResult"
})
@XmlRootElement(name = "TrackPackageResponse")
public class TrackPackageResponse {

    @XmlElement(name = "TrackPackageResult")
    protected TrackResult trackPackageResult;

    /**
     * ��ȡtrackPackageResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TrackResult }
     *     
     */
    public TrackResult getTrackPackageResult() {
        return trackPackageResult;
    }

    /**
     * ����trackPackageResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TrackResult }
     *     
     */
    public void setTrackPackageResult(TrackResult value) {
        this.trackPackageResult = value;
    }

}

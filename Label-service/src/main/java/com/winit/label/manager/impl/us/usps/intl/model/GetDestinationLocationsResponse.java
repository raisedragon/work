
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
 *         &lt;element name="GetDestinationLocationsResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}DestinationLocationsResult" minOccurs="0"/>
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
    "getDestinationLocationsResult"
})
@XmlRootElement(name = "GetDestinationLocationsResponse")
public class GetDestinationLocationsResponse {

    @XmlElement(name = "GetDestinationLocationsResult")
    protected DestinationLocationsResult getDestinationLocationsResult;

    /**
     * ��ȡgetDestinationLocationsResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link DestinationLocationsResult }
     *     
     */
    public DestinationLocationsResult getGetDestinationLocationsResult() {
        return getDestinationLocationsResult;
    }

    /**
     * ����getDestinationLocationsResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationLocationsResult }
     *     
     */
    public void setGetDestinationLocationsResult(DestinationLocationsResult value) {
        this.getDestinationLocationsResult = value;
    }

}

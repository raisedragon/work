
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
 *         &lt;element name="CloseDispatchToDestinationLocationResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}CloseDispatchResult" minOccurs="0"/>
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
    "closeDispatchToDestinationLocationResult"
})
@XmlRootElement(name = "CloseDispatchToDestinationLocationResponse")
public class CloseDispatchToDestinationLocationResponse {

    @XmlElement(name = "CloseDispatchToDestinationLocationResult")
    protected CloseDispatchResult closeDispatchToDestinationLocationResult;

    /**
     * ��ȡcloseDispatchToDestinationLocationResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CloseDispatchResult }
     *     
     */
    public CloseDispatchResult getCloseDispatchToDestinationLocationResult() {
        return closeDispatchToDestinationLocationResult;
    }

    /**
     * ����closeDispatchToDestinationLocationResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CloseDispatchResult }
     *     
     */
    public void setCloseDispatchToDestinationLocationResult(CloseDispatchResult value) {
        this.closeDispatchToDestinationLocationResult = value;
    }

}

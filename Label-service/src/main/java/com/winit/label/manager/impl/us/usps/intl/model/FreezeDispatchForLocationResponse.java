
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
 *         &lt;element name="FreezeDispatchForLocationResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}FrozenDispatchResult" minOccurs="0"/>
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
    "freezeDispatchForLocationResult"
})
@XmlRootElement(name = "FreezeDispatchForLocationResponse")
public class FreezeDispatchForLocationResponse {

    @XmlElement(name = "FreezeDispatchForLocationResult")
    protected FrozenDispatchResult freezeDispatchForLocationResult;

    /**
     * ��ȡfreezeDispatchForLocationResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link FrozenDispatchResult }
     *     
     */
    public FrozenDispatchResult getFreezeDispatchForLocationResult() {
        return freezeDispatchForLocationResult;
    }

    /**
     * ����freezeDispatchForLocationResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link FrozenDispatchResult }
     *     
     */
    public void setFreezeDispatchForLocationResult(FrozenDispatchResult value) {
        this.freezeDispatchForLocationResult = value;
    }

}

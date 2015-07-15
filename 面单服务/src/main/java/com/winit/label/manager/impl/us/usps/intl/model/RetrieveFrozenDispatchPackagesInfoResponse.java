
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
 *         &lt;element name="RetrieveFrozenDispatchPackagesInfoResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}FrozenDispatchPackagesResult" minOccurs="0"/>
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
    "retrieveFrozenDispatchPackagesInfoResult"
})
@XmlRootElement(name = "RetrieveFrozenDispatchPackagesInfoResponse")
public class RetrieveFrozenDispatchPackagesInfoResponse {

    @XmlElement(name = "RetrieveFrozenDispatchPackagesInfoResult")
    protected FrozenDispatchPackagesResult retrieveFrozenDispatchPackagesInfoResult;

    /**
     * ��ȡretrieveFrozenDispatchPackagesInfoResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link FrozenDispatchPackagesResult }
     *     
     */
    public FrozenDispatchPackagesResult getRetrieveFrozenDispatchPackagesInfoResult() {
        return retrieveFrozenDispatchPackagesInfoResult;
    }

    /**
     * ����retrieveFrozenDispatchPackagesInfoResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link FrozenDispatchPackagesResult }
     *     
     */
    public void setRetrieveFrozenDispatchPackagesInfoResult(FrozenDispatchPackagesResult value) {
        this.retrieveFrozenDispatchPackagesInfoResult = value;
    }

}

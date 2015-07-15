
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
 *         &lt;element name="RetrieveFrozenDispatchesInfoResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}FrozenDispatchesResult" minOccurs="0"/>
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
    "retrieveFrozenDispatchesInfoResult"
})
@XmlRootElement(name = "RetrieveFrozenDispatchesInfoResponse")
public class RetrieveFrozenDispatchesInfoResponse {

    @XmlElement(name = "RetrieveFrozenDispatchesInfoResult")
    protected FrozenDispatchesResult retrieveFrozenDispatchesInfoResult;

    /**
     * ��ȡretrieveFrozenDispatchesInfoResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link FrozenDispatchesResult }
     *     
     */
    public FrozenDispatchesResult getRetrieveFrozenDispatchesInfoResult() {
        return retrieveFrozenDispatchesInfoResult;
    }

    /**
     * ����retrieveFrozenDispatchesInfoResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link FrozenDispatchesResult }
     *     
     */
    public void setRetrieveFrozenDispatchesInfoResult(FrozenDispatchesResult value) {
        this.retrieveFrozenDispatchesInfoResult = value;
    }

}

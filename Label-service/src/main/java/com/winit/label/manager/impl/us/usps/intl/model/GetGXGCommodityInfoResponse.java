
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
 *         &lt;element name="GetGXGCommodityInfoResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GXGCommodityInfoResult" minOccurs="0"/>
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
    "getGXGCommodityInfoResult"
})
@XmlRootElement(name = "GetGXGCommodityInfoResponse")
public class GetGXGCommodityInfoResponse {

    @XmlElement(name = "GetGXGCommodityInfoResult")
    protected GXGCommodityInfoResult getGXGCommodityInfoResult;

    /**
     * ��ȡgetGXGCommodityInfoResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GXGCommodityInfoResult }
     *     
     */
    public GXGCommodityInfoResult getGetGXGCommodityInfoResult() {
        return getGXGCommodityInfoResult;
    }

    /**
     * ����getGXGCommodityInfoResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GXGCommodityInfoResult }
     *     
     */
    public void setGetGXGCommodityInfoResult(GXGCommodityInfoResult value) {
        this.getGXGCommodityInfoResult = value;
    }

}

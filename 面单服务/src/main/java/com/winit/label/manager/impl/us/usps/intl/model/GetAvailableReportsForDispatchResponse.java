
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
 *         &lt;element name="GetAvailableReportsForDispatchResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GetAvailableReportResult" minOccurs="0"/>
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
    "getAvailableReportsForDispatchResult"
})
@XmlRootElement(name = "GetAvailableReportsForDispatchResponse")
public class GetAvailableReportsForDispatchResponse {

    @XmlElement(name = "GetAvailableReportsForDispatchResult")
    protected GetAvailableReportResult getAvailableReportsForDispatchResult;

    /**
     * ��ȡgetAvailableReportsForDispatchResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GetAvailableReportResult }
     *     
     */
    public GetAvailableReportResult getGetAvailableReportsForDispatchResult() {
        return getAvailableReportsForDispatchResult;
    }

    /**
     * ����getAvailableReportsForDispatchResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GetAvailableReportResult }
     *     
     */
    public void setGetAvailableReportsForDispatchResult(GetAvailableReportResult value) {
        this.getAvailableReportsForDispatchResult = value;
    }

}

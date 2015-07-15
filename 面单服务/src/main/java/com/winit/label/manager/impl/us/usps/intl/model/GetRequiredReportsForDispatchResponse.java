
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
 *         &lt;element name="GetRequiredReportsForDispatchResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GetAvailableReportResult" minOccurs="0"/>
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
    "getRequiredReportsForDispatchResult"
})
@XmlRootElement(name = "GetRequiredReportsForDispatchResponse")
public class GetRequiredReportsForDispatchResponse {

    @XmlElement(name = "GetRequiredReportsForDispatchResult")
    protected GetAvailableReportResult getRequiredReportsForDispatchResult;

    /**
     * ��ȡgetRequiredReportsForDispatchResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GetAvailableReportResult }
     *     
     */
    public GetAvailableReportResult getGetRequiredReportsForDispatchResult() {
        return getRequiredReportsForDispatchResult;
    }

    /**
     * ����getRequiredReportsForDispatchResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GetAvailableReportResult }
     *     
     */
    public void setGetRequiredReportsForDispatchResult(GetAvailableReportResult value) {
        this.getRequiredReportsForDispatchResult = value;
    }

}

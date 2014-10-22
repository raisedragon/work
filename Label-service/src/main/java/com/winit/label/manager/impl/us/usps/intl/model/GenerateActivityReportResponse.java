
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
 *         &lt;element name="GenerateActivityReportResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GenerateReportResult" minOccurs="0"/>
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
    "generateActivityReportResult"
})
@XmlRootElement(name = "GenerateActivityReportResponse")
public class GenerateActivityReportResponse {

    @XmlElement(name = "GenerateActivityReportResult")
    protected GenerateReportResult generateActivityReportResult;

    /**
     * ��ȡgenerateActivityReportResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GenerateReportResult }
     *     
     */
    public GenerateReportResult getGenerateActivityReportResult() {
        return generateActivityReportResult;
    }

    /**
     * ����generateActivityReportResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateReportResult }
     *     
     */
    public void setGenerateActivityReportResult(GenerateReportResult value) {
        this.generateActivityReportResult = value;
    }

}

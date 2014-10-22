
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
 *         &lt;element name="GenerateReportResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GenerateReportResult" minOccurs="0"/>
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
    "generateReportResult"
})
@XmlRootElement(name = "GenerateReportResponse")
public class GenerateReportResponse {

    @XmlElement(name = "GenerateReportResult")
    protected GenerateReportResult generateReportResult;

    /**
     * ��ȡgenerateReportResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GenerateReportResult }
     *     
     */
    public GenerateReportResult getGenerateReportResult() {
        return generateReportResult;
    }

    /**
     * ����generateReportResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateReportResult }
     *     
     */
    public void setGenerateReportResult(GenerateReportResult value) {
        this.generateReportResult = value;
    }

}

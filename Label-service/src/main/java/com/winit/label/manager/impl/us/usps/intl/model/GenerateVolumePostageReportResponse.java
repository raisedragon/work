
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
 *         &lt;element name="GenerateVolumePostageReportResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GenerateReportResult" minOccurs="0"/>
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
    "generateVolumePostageReportResult"
})
@XmlRootElement(name = "GenerateVolumePostageReportResponse")
public class GenerateVolumePostageReportResponse {

    @XmlElement(name = "GenerateVolumePostageReportResult")
    protected GenerateReportResult generateVolumePostageReportResult;

    /**
     * ��ȡgenerateVolumePostageReportResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GenerateReportResult }
     *     
     */
    public GenerateReportResult getGenerateVolumePostageReportResult() {
        return generateVolumePostageReportResult;
    }

    /**
     * ����generateVolumePostageReportResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateReportResult }
     *     
     */
    public void setGenerateVolumePostageReportResult(GenerateReportResult value) {
        this.generateVolumePostageReportResult = value;
    }

}


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
 *         &lt;element name="GeneratePartialDispatchReportResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}GenerateReportResult" minOccurs="0"/>
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
    "generatePartialDispatchReportResult"
})
@XmlRootElement(name = "GeneratePartialDispatchReportResponse")
public class GeneratePartialDispatchReportResponse {

    @XmlElement(name = "GeneratePartialDispatchReportResult")
    protected GenerateReportResult generatePartialDispatchReportResult;

    /**
     * ��ȡgeneratePartialDispatchReportResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GenerateReportResult }
     *     
     */
    public GenerateReportResult getGeneratePartialDispatchReportResult() {
        return generatePartialDispatchReportResult;
    }

    /**
     * ����generatePartialDispatchReportResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateReportResult }
     *     
     */
    public void setGeneratePartialDispatchReportResult(GenerateReportResult value) {
        this.generatePartialDispatchReportResult = value;
    }

}

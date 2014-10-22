
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
 *         &lt;element name="CalculatePostageResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}CalculatePostageResult" minOccurs="0"/>
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
    "calculatePostageResult"
})
@XmlRootElement(name = "CalculatePostageResponse")
public class CalculatePostageResponse {

    @XmlElement(name = "CalculatePostageResult")
    protected CalculatePostageResult calculatePostageResult;

    /**
     * ��ȡcalculatePostageResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CalculatePostageResult }
     *     
     */
    public CalculatePostageResult getCalculatePostageResult() {
        return calculatePostageResult;
    }

    /**
     * ����calculatePostageResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CalculatePostageResult }
     *     
     */
    public void setCalculatePostageResult(CalculatePostageResult value) {
        this.calculatePostageResult = value;
    }

}

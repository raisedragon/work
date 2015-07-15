
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
 *         &lt;element name="RegeneratePackageCustomsLabelResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}LabelResult" minOccurs="0"/>
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
    "regeneratePackageCustomsLabelResult"
})
@XmlRootElement(name = "RegeneratePackageCustomsLabelResponse")
public class RegeneratePackageCustomsLabelResponse {

    @XmlElement(name = "RegeneratePackageCustomsLabelResult")
    protected LabelResult regeneratePackageCustomsLabelResult;

    /**
     * ��ȡregeneratePackageCustomsLabelResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link LabelResult }
     *     
     */
    public LabelResult getRegeneratePackageCustomsLabelResult() {
        return regeneratePackageCustomsLabelResult;
    }

    /**
     * ����regeneratePackageCustomsLabelResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link LabelResult }
     *     
     */
    public void setRegeneratePackageCustomsLabelResult(LabelResult value) {
        this.regeneratePackageCustomsLabelResult = value;
    }

}

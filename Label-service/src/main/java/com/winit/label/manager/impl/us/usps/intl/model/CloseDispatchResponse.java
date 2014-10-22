
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
 *         &lt;element name="CloseDispatchResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}CloseDispatchResult" minOccurs="0"/>
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
    "closeDispatchResult"
})
@XmlRootElement(name = "CloseDispatchResponse")
public class CloseDispatchResponse {

    @XmlElement(name = "CloseDispatchResult")
    protected CloseDispatchResult closeDispatchResult;

    /**
     * ��ȡcloseDispatchResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CloseDispatchResult }
     *     
     */
    public CloseDispatchResult getCloseDispatchResult() {
        return closeDispatchResult;
    }

    /**
     * ����closeDispatchResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CloseDispatchResult }
     *     
     */
    public void setCloseDispatchResult(CloseDispatchResult value) {
        this.closeDispatchResult = value;
    }

}

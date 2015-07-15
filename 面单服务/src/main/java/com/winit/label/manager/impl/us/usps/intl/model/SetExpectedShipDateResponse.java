
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
 *         &lt;element name="SetExpectedShipDateResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}CommonResult" minOccurs="0"/>
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
    "setExpectedShipDateResult"
})
@XmlRootElement(name = "SetExpectedShipDateResponse")
public class SetExpectedShipDateResponse {

    @XmlElement(name = "SetExpectedShipDateResult")
    protected CommonResult setExpectedShipDateResult;

    /**
     * ��ȡsetExpectedShipDateResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CommonResult }
     *     
     */
    public CommonResult getSetExpectedShipDateResult() {
        return setExpectedShipDateResult;
    }

    /**
     * ����setExpectedShipDateResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CommonResult }
     *     
     */
    public void setSetExpectedShipDateResult(CommonResult value) {
        this.setExpectedShipDateResult = value;
    }

}

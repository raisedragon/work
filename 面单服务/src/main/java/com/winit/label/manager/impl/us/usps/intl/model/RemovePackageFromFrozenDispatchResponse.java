
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
 *         &lt;element name="RemovePackageFromFrozenDispatchResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}CommonResult" minOccurs="0"/>
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
    "removePackageFromFrozenDispatchResult"
})
@XmlRootElement(name = "RemovePackageFromFrozenDispatchResponse")
public class RemovePackageFromFrozenDispatchResponse {

    @XmlElement(name = "RemovePackageFromFrozenDispatchResult")
    protected CommonResult removePackageFromFrozenDispatchResult;

    /**
     * ��ȡremovePackageFromFrozenDispatchResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CommonResult }
     *     
     */
    public CommonResult getRemovePackageFromFrozenDispatchResult() {
        return removePackageFromFrozenDispatchResult;
    }

    /**
     * ����removePackageFromFrozenDispatchResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CommonResult }
     *     
     */
    public void setRemovePackageFromFrozenDispatchResult(CommonResult value) {
        this.removePackageFromFrozenDispatchResult = value;
    }

}

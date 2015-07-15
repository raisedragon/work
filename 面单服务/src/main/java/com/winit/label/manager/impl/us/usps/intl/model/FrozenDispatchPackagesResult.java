
package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FrozenDispatchPackagesResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="FrozenDispatchPackagesResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrozenDispatchPkgs" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}ArrayOfFrozenDispatchPackage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FrozenDispatchPackagesResult", propOrder = {
    "responseCode",
    "message",
    "frozenDispatchPkgs"
})
public class FrozenDispatchPackagesResult {

    @XmlElement(name = "ResponseCode")
    protected int responseCode;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "FrozenDispatchPkgs")
    protected ArrayOfFrozenDispatchPackage frozenDispatchPkgs;

    /**
     * ��ȡresponseCode���Ե�ֵ��
     * 
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * ����responseCode���Ե�ֵ��
     * 
     */
    public void setResponseCode(int value) {
        this.responseCode = value;
    }

    /**
     * ��ȡmessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * ����message���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * ��ȡfrozenDispatchPkgs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFrozenDispatchPackage }
     *     
     */
    public ArrayOfFrozenDispatchPackage getFrozenDispatchPkgs() {
        return frozenDispatchPkgs;
    }

    /**
     * ����frozenDispatchPkgs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFrozenDispatchPackage }
     *     
     */
    public void setFrozenDispatchPkgs(ArrayOfFrozenDispatchPackage value) {
        this.frozenDispatchPkgs = value;
    }

}

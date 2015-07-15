
package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CloseDispatchResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CloseDispatchResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispatchID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CloseDispatchResult", propOrder = {
    "responseCode",
    "message",
    "dispatchID",
    "packageCount"
})
public class CloseDispatchResult {

    @XmlElement(name = "ResponseCode")
    protected int responseCode;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "DispatchID")
    protected String dispatchID;
    @XmlElement(name = "PackageCount")
    protected int packageCount;

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
     * ��ȡdispatchID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispatchID() {
        return dispatchID;
    }

    /**
     * ����dispatchID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispatchID(String value) {
        this.dispatchID = value;
    }

    /**
     * ��ȡpackageCount���Ե�ֵ��
     * 
     */
    public int getPackageCount() {
        return packageCount;
    }

    /**
     * ����packageCount���Ե�ֵ��
     * 
     */
    public void setPackageCount(int value) {
        this.packageCount = value;
    }

}


package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GXGCommodityInfoResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="GXGCommodityInfoResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CommodityNames" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="CommodityEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GXGCommodityInfoResult", propOrder = {
    "responseCode",
    "message",
    "commodityNames",
    "commodityEffectiveDate"
})
public class GXGCommodityInfoResult {

    @XmlElement(name = "ResponseCode")
    protected int responseCode;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "CommodityNames")
    protected ArrayOfString commodityNames;
    @XmlElement(name = "CommodityEffectiveDate")
    protected String commodityEffectiveDate;

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
     * ��ȡcommodityNames���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getCommodityNames() {
        return commodityNames;
    }

    /**
     * ����commodityNames���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setCommodityNames(ArrayOfString value) {
        this.commodityNames = value;
    }

    /**
     * ��ȡcommodityEffectiveDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityEffectiveDate() {
        return commodityEffectiveDate;
    }

    /**
     * ����commodityEffectiveDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityEffectiveDate(String value) {
        this.commodityEffectiveDate = value;
    }

}

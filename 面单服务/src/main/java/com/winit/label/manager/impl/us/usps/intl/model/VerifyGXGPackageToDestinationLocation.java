
package com.winit.label.manager.impl.us.usps.intl.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
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
 *         &lt;element name="PackageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailingAgentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BoxNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DestinationLocationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GXGRequest" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AccessToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "packageID",
    "mailingAgentID",
    "boxNumber",
    "destinationLocationID",
    "gxgRequest",
    "accessToken"
})
@XmlRootElement(name = "VerifyGXGPackageToDestinationLocation")
public class VerifyGXGPackageToDestinationLocation {

    @XmlElement(name = "PackageID")
    protected String packageID;
    @XmlElement(name = "MailingAgentID")
    protected String mailingAgentID;
    @XmlElement(name = "BoxNumber")
    protected int boxNumber;
    @XmlElement(name = "DestinationLocationID")
    protected String destinationLocationID;
    @XmlElement(name = "GXGRequest")
    protected VerifyGXGPackageToDestinationLocation.GXGRequest gxgRequest;
    @XmlElement(name = "AccessToken")
    protected String accessToken;

    /**
     * ��ȡpackageID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageID() {
        return packageID;
    }

    /**
     * ����packageID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageID(String value) {
        this.packageID = value;
    }

    /**
     * ��ȡmailingAgentID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAgentID() {
        return mailingAgentID;
    }

    /**
     * ����mailingAgentID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAgentID(String value) {
        this.mailingAgentID = value;
    }

    /**
     * ��ȡboxNumber���Ե�ֵ��
     * 
     */
    public int getBoxNumber() {
        return boxNumber;
    }

    /**
     * ����boxNumber���Ե�ֵ��
     * 
     */
    public void setBoxNumber(int value) {
        this.boxNumber = value;
    }

    /**
     * ��ȡdestinationLocationID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationLocationID() {
        return destinationLocationID;
    }

    /**
     * ����destinationLocationID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationLocationID(String value) {
        this.destinationLocationID = value;
    }

    /**
     * ��ȡgxgRequest���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link VerifyGXGPackageToDestinationLocation.GXGRequest }
     *     
     */
    public VerifyGXGPackageToDestinationLocation.GXGRequest getGXGRequest() {
        return gxgRequest;
    }

    /**
     * ����gxgRequest���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link VerifyGXGPackageToDestinationLocation.GXGRequest }
     *     
     */
    public void setGXGRequest(VerifyGXGPackageToDestinationLocation.GXGRequest value) {
        this.gxgRequest = value;
    }

    /**
     * ��ȡaccessToken���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * ����accessToken���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessToken(String value) {
        this.accessToken = value;
    }


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
     *         &lt;any/>
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
        "content"
    })
    public static class GXGRequest {

        @XmlMixed
        @XmlAnyElement(lax = true)
        protected List<Object> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Object }
         * {@link String }
         * 
         * 
         */
        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<Object>();
            }
            return this.content;
        }

    }

}

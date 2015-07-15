
package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FrozenDispatch complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="FrozenDispatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FrozenDispGUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrozenDispatchID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginationLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "FrozenDispatch", propOrder = {
    "frozenDispGUID",
    "frozenDispatchID",
    "creationDateTime",
    "destinationLocation",
    "originationLocation",
    "packageCount"
})
public class FrozenDispatch {

    @XmlElement(name = "FrozenDispGUID")
    protected String frozenDispGUID;
    @XmlElement(name = "FrozenDispatchID")
    protected String frozenDispatchID;
    @XmlElement(name = "CreationDateTime")
    protected String creationDateTime;
    @XmlElement(name = "DestinationLocation")
    protected String destinationLocation;
    @XmlElement(name = "OriginationLocation")
    protected String originationLocation;
    @XmlElement(name = "PackageCount")
    protected int packageCount;

    /**
     * ��ȡfrozenDispGUID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrozenDispGUID() {
        return frozenDispGUID;
    }

    /**
     * ����frozenDispGUID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrozenDispGUID(String value) {
        this.frozenDispGUID = value;
    }

    /**
     * ��ȡfrozenDispatchID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrozenDispatchID() {
        return frozenDispatchID;
    }

    /**
     * ����frozenDispatchID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrozenDispatchID(String value) {
        this.frozenDispatchID = value;
    }

    /**
     * ��ȡcreationDateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * ����creationDateTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDateTime(String value) {
        this.creationDateTime = value;
    }

    /**
     * ��ȡdestinationLocation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * ����destinationLocation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationLocation(String value) {
        this.destinationLocation = value;
    }

    /**
     * ��ȡoriginationLocation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginationLocation() {
        return originationLocation;
    }

    /**
     * ����originationLocation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginationLocation(String value) {
        this.originationLocation = value;
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

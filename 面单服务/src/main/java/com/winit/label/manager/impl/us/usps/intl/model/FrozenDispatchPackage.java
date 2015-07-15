
package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FrozenDispatchPackage complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="FrozenDispatchPackage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PackageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USPSPackageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginatingMailerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceipentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FrozenDispatchPackage", propOrder = {
    "packageID",
    "uspsPackageID",
    "originatingMailerName",
    "receipentName"
})
public class FrozenDispatchPackage {

    @XmlElement(name = "PackageID")
    protected String packageID;
    @XmlElement(name = "USPSPackageID")
    protected String uspsPackageID;
    @XmlElement(name = "OriginatingMailerName")
    protected String originatingMailerName;
    @XmlElement(name = "ReceipentName")
    protected String receipentName;

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
     * ��ȡuspsPackageID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSPSPackageID() {
        return uspsPackageID;
    }

    /**
     * ����uspsPackageID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSPSPackageID(String value) {
        this.uspsPackageID = value;
    }

    /**
     * ��ȡoriginatingMailerName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginatingMailerName() {
        return originatingMailerName;
    }

    /**
     * ����originatingMailerName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginatingMailerName(String value) {
        this.originatingMailerName = value;
    }

    /**
     * ��ȡreceipentName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceipentName() {
        return receipentName;
    }

    /**
     * ����receipentName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceipentName(String value) {
        this.receipentName = value;
    }

}

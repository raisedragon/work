
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
 *         &lt;element name="CountryOfImport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryOfExport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModeOfTransport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncoTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateOfImport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Items" minOccurs="0">
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
    "countryOfImport",
    "countryOfExport",
    "modeOfTransport",
    "incoTerm",
    "taxDetail",
    "dateOfImport",
    "items",
    "accessToken"
})
@XmlRootElement(name = "CalculateLandedCost")
public class CalculateLandedCost {

    @XmlElement(name = "CountryOfImport")
    protected String countryOfImport;
    @XmlElement(name = "CountryOfExport")
    protected String countryOfExport;
    @XmlElement(name = "ModeOfTransport")
    protected String modeOfTransport;
    @XmlElement(name = "IncoTerm")
    protected String incoTerm;
    @XmlElement(name = "TaxDetail")
    protected String taxDetail;
    @XmlElement(name = "DateOfImport")
    protected String dateOfImport;
    @XmlElement(name = "Items")
    protected CalculateLandedCost.Items items;
    @XmlElement(name = "AccessToken")
    protected String accessToken;

    /**
     * ��ȡcountryOfImport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryOfImport() {
        return countryOfImport;
    }

    /**
     * ����countryOfImport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryOfImport(String value) {
        this.countryOfImport = value;
    }

    /**
     * ��ȡcountryOfExport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryOfExport() {
        return countryOfExport;
    }

    /**
     * ����countryOfExport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryOfExport(String value) {
        this.countryOfExport = value;
    }

    /**
     * ��ȡmodeOfTransport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeOfTransport() {
        return modeOfTransport;
    }

    /**
     * ����modeOfTransport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeOfTransport(String value) {
        this.modeOfTransport = value;
    }

    /**
     * ��ȡincoTerm���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncoTerm() {
        return incoTerm;
    }

    /**
     * ����incoTerm���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncoTerm(String value) {
        this.incoTerm = value;
    }

    /**
     * ��ȡtaxDetail���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxDetail() {
        return taxDetail;
    }

    /**
     * ����taxDetail���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxDetail(String value) {
        this.taxDetail = value;
    }

    /**
     * ��ȡdateOfImport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfImport() {
        return dateOfImport;
    }

    /**
     * ����dateOfImport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfImport(String value) {
        this.dateOfImport = value;
    }

    /**
     * ��ȡitems���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CalculateLandedCost.Items }
     *     
     */
    public CalculateLandedCost.Items getItems() {
        return items;
    }

    /**
     * ����items���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CalculateLandedCost.Items }
     *     
     */
    public void setItems(CalculateLandedCost.Items value) {
        this.items = value;
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
    public static class Items {

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

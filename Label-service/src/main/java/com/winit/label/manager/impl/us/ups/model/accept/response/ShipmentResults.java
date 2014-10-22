//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:40:10 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.accept.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}ShipmentCharges" minOccurs="0"/>
 *         &lt;element ref="{}NegotiatedRates" minOccurs="0"/>
 *         &lt;element ref="{}BillingWeight"/>
 *         &lt;element name="ShipmentIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}ControlLogReceipt" minOccurs="0"/>
 *         &lt;element ref="{}PackageResults" maxOccurs="unbounded"/>
 *         &lt;element name="Form" type="{}IntlForms" minOccurs="0"/>
 *         &lt;element name="CODTurnInPage" type="{}CODImageType" minOccurs="0"/>
 *         &lt;element name="HighValueReport" type="{}HighValueReportType" minOccurs="0"/>
 *         &lt;element name="LabelURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalLanguageLabelURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceiptURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalLanguageReceiptURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "shipmentCharges",
    "negotiatedRates",
    "billingWeight",
    "shipmentIdentificationNumber",
    "controlLogReceipt",
    "packageResults",
    "form",
    "codTurnInPage",
    "highValueReport",
    "labelURL",
    "localLanguageLabelURL",
    "receiptURL",
    "localLanguageReceiptURL"
})
@XmlRootElement(name = "ShipmentResults")
public class ShipmentResults {

    @XmlElement(name = "ShipmentCharges")
    protected ShipmentCharges shipmentCharges;
    @XmlElement(name = "NegotiatedRates")
    protected NegotiatedRates negotiatedRates;
    @XmlElement(name = "BillingWeight", required = true)
    protected BillingWeight billingWeight;
    @XmlElement(name = "ShipmentIdentificationNumber")
    protected String shipmentIdentificationNumber;
    @XmlElement(name = "ControlLogReceipt")
    protected ImageType controlLogReceipt;
    @XmlElement(name = "PackageResults", required = true)
    protected List<PackageResults> packageResults;
    @XmlElement(name = "Form")
    protected IntlForms form;
    @XmlElement(name = "CODTurnInPage")
    protected CODImageType codTurnInPage;
    @XmlElement(name = "HighValueReport")
    protected HighValueReportType highValueReport;
    @XmlElement(name = "LabelURL")
    protected String labelURL;
    @XmlElement(name = "LocalLanguageLabelURL")
    protected String localLanguageLabelURL;
    @XmlElement(name = "ReceiptURL")
    protected String receiptURL;
    @XmlElement(name = "LocalLanguageReceiptURL")
    protected String localLanguageReceiptURL;

    /**
     * 获取shipmentCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentCharges }
     *     
     */
    public ShipmentCharges getShipmentCharges() {
        return shipmentCharges;
    }

    /**
     * 设置shipmentCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentCharges }
     *     
     */
    public void setShipmentCharges(ShipmentCharges value) {
        this.shipmentCharges = value;
    }

    /**
     * 获取negotiatedRates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NegotiatedRates }
     *     
     */
    public NegotiatedRates getNegotiatedRates() {
        return negotiatedRates;
    }

    /**
     * 设置negotiatedRates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NegotiatedRates }
     *     
     */
    public void setNegotiatedRates(NegotiatedRates value) {
        this.negotiatedRates = value;
    }

    /**
     * 获取billingWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BillingWeight }
     *     
     */
    public BillingWeight getBillingWeight() {
        return billingWeight;
    }

    /**
     * 设置billingWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BillingWeight }
     *     
     */
    public void setBillingWeight(BillingWeight value) {
        this.billingWeight = value;
    }

    /**
     * 获取shipmentIdentificationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentIdentificationNumber() {
        return shipmentIdentificationNumber;
    }

    /**
     * 设置shipmentIdentificationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentIdentificationNumber(String value) {
        this.shipmentIdentificationNumber = value;
    }

    /**
     * 获取controlLogReceipt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ImageType }
     *     
     */
    public ImageType getControlLogReceipt() {
        return controlLogReceipt;
    }

    /**
     * 设置controlLogReceipt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ImageType }
     *     
     */
    public void setControlLogReceipt(ImageType value) {
        this.controlLogReceipt = value;
    }

    /**
     * Gets the value of the packageResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageResults }
     * 
     * 
     */
    public List<PackageResults> getPackageResults() {
        if (packageResults == null) {
            packageResults = new ArrayList<PackageResults>();
        }
        return this.packageResults;
    }

    /**
     * 获取form属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IntlForms }
     *     
     */
    public IntlForms getForm() {
        return form;
    }

    /**
     * 设置form属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IntlForms }
     *     
     */
    public void setForm(IntlForms value) {
        this.form = value;
    }

    /**
     * 获取codTurnInPage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CODImageType }
     *     
     */
    public CODImageType getCODTurnInPage() {
        return codTurnInPage;
    }

    /**
     * 设置codTurnInPage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CODImageType }
     *     
     */
    public void setCODTurnInPage(CODImageType value) {
        this.codTurnInPage = value;
    }

    /**
     * 获取highValueReport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HighValueReportType }
     *     
     */
    public HighValueReportType getHighValueReport() {
        return highValueReport;
    }

    /**
     * 设置highValueReport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HighValueReportType }
     *     
     */
    public void setHighValueReport(HighValueReportType value) {
        this.highValueReport = value;
    }

    /**
     * 获取labelURL属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelURL() {
        return labelURL;
    }

    /**
     * 设置labelURL属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelURL(String value) {
        this.labelURL = value;
    }

    /**
     * 获取localLanguageLabelURL属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalLanguageLabelURL() {
        return localLanguageLabelURL;
    }

    /**
     * 设置localLanguageLabelURL属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalLanguageLabelURL(String value) {
        this.localLanguageLabelURL = value;
    }

    /**
     * 获取receiptURL属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptURL() {
        return receiptURL;
    }

    /**
     * 设置receiptURL属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptURL(String value) {
        this.receiptURL = value;
    }

    /**
     * 获取localLanguageReceiptURL属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalLanguageReceiptURL() {
        return localLanguageReceiptURL;
    }

    /**
     * 设置localLanguageReceiptURL属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalLanguageReceiptURL(String value) {
        this.localLanguageReceiptURL = value;
    }

}

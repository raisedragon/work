//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:39:52 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.confirm.request;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>InternationalFormsType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="InternationalFormsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormType" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="UserCreatedForm" type="{}UserCreatedFormType" minOccurs="0"/>
 *         &lt;element name="CN22Form" type="{}CN22FormType" minOccurs="0"/>
 *         &lt;element name="UPSPremiumCareForm" type="{}UPSPremiumCareFormType" minOccurs="0"/>
 *         &lt;element name="AdditionalDocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormGroupIdName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEDFilingOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contacts" type="{}ContactsType" minOccurs="0"/>
 *         &lt;element name="Product" type="{}ProductType" maxOccurs="unbounded"/>
 *         &lt;element ref="{}InvoiceNumber" minOccurs="0"/>
 *         &lt;element name="InvoiceDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PurchaseOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}TermsOfShipment" minOccurs="0"/>
 *         &lt;element ref="{}ReasonForExport" minOccurs="0"/>
 *         &lt;element ref="{}Comments" minOccurs="0"/>
 *         &lt;element ref="{}DeclarationStatement" minOccurs="0"/>
 *         &lt;element name="Discount" type="{}DiscountType" minOccurs="0"/>
 *         &lt;element name="FreightCharges" type="{}FreightChargesType" minOccurs="0"/>
 *         &lt;element name="InsuranceCharges" type="{}InsuranceChargesType" minOccurs="0"/>
 *         &lt;element name="OtherCharges" type="{}OtherChargesType" minOccurs="0"/>
 *         &lt;element ref="{}CurrencyCode" minOccurs="0"/>
 *         &lt;element name="BlanketPeriod" type="{}BlanketPeriodType" minOccurs="0"/>
 *         &lt;element name="ExportDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExportingCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}CarrierID" minOccurs="0"/>
 *         &lt;element name="InBondCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntryNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PointOfOrigin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModeOfTransport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PortOfExport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PortOfUnloading" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoadingPier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}PartiesToTransaction" minOccurs="0"/>
 *         &lt;element name="RoutedExportTransactionIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContainerizedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="License" type="{}LicenseType" minOccurs="0"/>
 *         &lt;element name="ECCNNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipperMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OverridePaperlessIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MultiCurrencyInvoiceLineTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PointOfOriginType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EEIFilingOption" type="{}EEIFilingOptionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalFormsType", propOrder = {
    "formType",
    "userCreatedForm",
    "cn22Form",
    "upsPremiumCareForm",
    "additionalDocumentIndicator",
    "formGroupIdName",
    "sedFilingOption",
    "contacts",
    "product",
    "invoiceNumber",
    "invoiceDate",
    "purchaseOrderNumber",
    "termsOfShipment",
    "reasonForExport",
    "comments",
    "declarationStatement",
    "discount",
    "freightCharges",
    "insuranceCharges",
    "otherCharges",
    "currencyCode",
    "blanketPeriod",
    "exportDate",
    "exportingCarrier",
    "carrierID",
    "inBondCode",
    "entryNumber",
    "pointOfOrigin",
    "modeOfTransport",
    "portOfExport",
    "portOfUnloading",
    "loadingPier",
    "partiesToTransaction",
    "routedExportTransactionIndicator",
    "containerizedIndicator",
    "license",
    "eccnNumber",
    "shipperMemo",
    "overridePaperlessIndicator",
    "multiCurrencyInvoiceLineTotal",
    "pointOfOriginType",
    "eeiFilingOption"
})
public class InternationalFormsType {

    @XmlElement(name = "FormType", required = true)
    protected List<String> formType;
    @XmlElement(name = "UserCreatedForm")
    protected UserCreatedFormType userCreatedForm;
    @XmlElement(name = "CN22Form")
    protected CN22FormType cn22Form;
    @XmlElement(name = "UPSPremiumCareForm")
    protected UPSPremiumCareFormType upsPremiumCareForm;
    @XmlElement(name = "AdditionalDocumentIndicator")
    protected String additionalDocumentIndicator;
    @XmlElement(name = "FormGroupIdName")
    protected String formGroupIdName;
    @XmlElement(name = "SEDFilingOption")
    protected String sedFilingOption;
    @XmlElement(name = "Contacts")
    protected ContactsType contacts;
    @XmlElement(name = "Product", required = true)
    protected List<ProductType> product;
    @XmlElement(name = "InvoiceNumber")
    protected String invoiceNumber;
    @XmlElement(name = "InvoiceDate")
    protected String invoiceDate;
    @XmlElement(name = "PurchaseOrderNumber")
    protected String purchaseOrderNumber;
    @XmlElement(name = "TermsOfShipment")
    protected String termsOfShipment;
    @XmlElement(name = "ReasonForExport")
    protected String reasonForExport;
    @XmlElement(name = "Comments")
    protected String comments;
    @XmlElement(name = "DeclarationStatement")
    protected String declarationStatement;
    @XmlElement(name = "Discount")
    protected DiscountType discount;
    @XmlElement(name = "FreightCharges")
    protected FreightChargesType freightCharges;
    @XmlElement(name = "InsuranceCharges")
    protected InsuranceChargesType insuranceCharges;
    @XmlElement(name = "OtherCharges")
    protected OtherChargesType otherCharges;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "BlanketPeriod")
    protected BlanketPeriodType blanketPeriod;
    @XmlElement(name = "ExportDate")
    protected String exportDate;
    @XmlElement(name = "ExportingCarrier")
    protected String exportingCarrier;
    @XmlElement(name = "CarrierID")
    protected String carrierID;
    @XmlElement(name = "InBondCode")
    protected String inBondCode;
    @XmlElement(name = "EntryNumber")
    protected String entryNumber;
    @XmlElement(name = "PointOfOrigin")
    protected String pointOfOrigin;
    @XmlElement(name = "ModeOfTransport")
    protected String modeOfTransport;
    @XmlElement(name = "PortOfExport")
    protected String portOfExport;
    @XmlElement(name = "PortOfUnloading")
    protected String portOfUnloading;
    @XmlElement(name = "LoadingPier")
    protected String loadingPier;
    @XmlElement(name = "PartiesToTransaction")
    protected String partiesToTransaction;
    @XmlElement(name = "RoutedExportTransactionIndicator")
    protected String routedExportTransactionIndicator;
    @XmlElement(name = "ContainerizedIndicator")
    protected String containerizedIndicator;
    @XmlElement(name = "License")
    protected LicenseType license;
    @XmlElement(name = "ECCNNumber")
    protected String eccnNumber;
    @XmlElement(name = "ShipperMemo")
    protected String shipperMemo;
    @XmlElement(name = "OverridePaperlessIndicator")
    protected String overridePaperlessIndicator;
    @XmlElement(name = "MultiCurrencyInvoiceLineTotal")
    protected String multiCurrencyInvoiceLineTotal;
    @XmlElement(name = "PointOfOriginType")
    protected String pointOfOriginType;
    @XmlElement(name = "EEIFilingOption")
    protected EEIFilingOptionType eeiFilingOption;

    /**
     * Gets the value of the formType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFormType() {
        if (formType == null) {
            formType = new ArrayList<String>();
        }
        return this.formType;
    }

    /**
     * 获取userCreatedForm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UserCreatedFormType }
     *     
     */
    public UserCreatedFormType getUserCreatedForm() {
        return userCreatedForm;
    }

    /**
     * 设置userCreatedForm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UserCreatedFormType }
     *     
     */
    public void setUserCreatedForm(UserCreatedFormType value) {
        this.userCreatedForm = value;
    }

    /**
     * 获取cn22Form属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CN22FormType }
     *     
     */
    public CN22FormType getCN22Form() {
        return cn22Form;
    }

    /**
     * 设置cn22Form属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CN22FormType }
     *     
     */
    public void setCN22Form(CN22FormType value) {
        this.cn22Form = value;
    }

    /**
     * 获取upsPremiumCareForm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UPSPremiumCareFormType }
     *     
     */
    public UPSPremiumCareFormType getUPSPremiumCareForm() {
        return upsPremiumCareForm;
    }

    /**
     * 设置upsPremiumCareForm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UPSPremiumCareFormType }
     *     
     */
    public void setUPSPremiumCareForm(UPSPremiumCareFormType value) {
        this.upsPremiumCareForm = value;
    }

    /**
     * 获取additionalDocumentIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalDocumentIndicator() {
        return additionalDocumentIndicator;
    }

    /**
     * 设置additionalDocumentIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalDocumentIndicator(String value) {
        this.additionalDocumentIndicator = value;
    }

    /**
     * 获取formGroupIdName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormGroupIdName() {
        return formGroupIdName;
    }

    /**
     * 设置formGroupIdName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormGroupIdName(String value) {
        this.formGroupIdName = value;
    }

    /**
     * 获取sedFilingOption属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEDFilingOption() {
        return sedFilingOption;
    }

    /**
     * 设置sedFilingOption属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEDFilingOption(String value) {
        this.sedFilingOption = value;
    }

    /**
     * 获取contacts属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ContactsType }
     *     
     */
    public ContactsType getContacts() {
        return contacts;
    }

    /**
     * 设置contacts属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ContactsType }
     *     
     */
    public void setContacts(ContactsType value) {
        this.contacts = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the product property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductType }
     * 
     * 
     */
    public List<ProductType> getProduct() {
        if (product == null) {
            product = new ArrayList<ProductType>();
        }
        return this.product;
    }

    /**
     * 获取invoiceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * 设置invoiceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * 获取invoiceDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * 设置invoiceDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceDate(String value) {
        this.invoiceDate = value;
    }

    /**
     * 获取purchaseOrderNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    /**
     * 设置purchaseOrderNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseOrderNumber(String value) {
        this.purchaseOrderNumber = value;
    }

    /**
     * 获取termsOfShipment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermsOfShipment() {
        return termsOfShipment;
    }

    /**
     * 设置termsOfShipment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermsOfShipment(String value) {
        this.termsOfShipment = value;
    }

    /**
     * 获取reasonForExport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonForExport() {
        return reasonForExport;
    }

    /**
     * 设置reasonForExport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonForExport(String value) {
        this.reasonForExport = value;
    }

    /**
     * 获取comments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置comments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * 获取declarationStatement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeclarationStatement() {
        return declarationStatement;
    }

    /**
     * 设置declarationStatement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeclarationStatement(String value) {
        this.declarationStatement = value;
    }

    /**
     * 获取discount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getDiscount() {
        return discount;
    }

    /**
     * 设置discount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setDiscount(DiscountType value) {
        this.discount = value;
    }

    /**
     * 获取freightCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FreightChargesType }
     *     
     */
    public FreightChargesType getFreightCharges() {
        return freightCharges;
    }

    /**
     * 设置freightCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FreightChargesType }
     *     
     */
    public void setFreightCharges(FreightChargesType value) {
        this.freightCharges = value;
    }

    /**
     * 获取insuranceCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InsuranceChargesType }
     *     
     */
    public InsuranceChargesType getInsuranceCharges() {
        return insuranceCharges;
    }

    /**
     * 设置insuranceCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InsuranceChargesType }
     *     
     */
    public void setInsuranceCharges(InsuranceChargesType value) {
        this.insuranceCharges = value;
    }

    /**
     * 获取otherCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OtherChargesType }
     *     
     */
    public OtherChargesType getOtherCharges() {
        return otherCharges;
    }

    /**
     * 设置otherCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OtherChargesType }
     *     
     */
    public void setOtherCharges(OtherChargesType value) {
        this.otherCharges = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取blanketPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BlanketPeriodType }
     *     
     */
    public BlanketPeriodType getBlanketPeriod() {
        return blanketPeriod;
    }

    /**
     * 设置blanketPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BlanketPeriodType }
     *     
     */
    public void setBlanketPeriod(BlanketPeriodType value) {
        this.blanketPeriod = value;
    }

    /**
     * 获取exportDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportDate() {
        return exportDate;
    }

    /**
     * 设置exportDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportDate(String value) {
        this.exportDate = value;
    }

    /**
     * 获取exportingCarrier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportingCarrier() {
        return exportingCarrier;
    }

    /**
     * 设置exportingCarrier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportingCarrier(String value) {
        this.exportingCarrier = value;
    }

    /**
     * 获取carrierID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierID() {
        return carrierID;
    }

    /**
     * 设置carrierID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierID(String value) {
        this.carrierID = value;
    }

    /**
     * 获取inBondCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInBondCode() {
        return inBondCode;
    }

    /**
     * 设置inBondCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInBondCode(String value) {
        this.inBondCode = value;
    }

    /**
     * 获取entryNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryNumber() {
        return entryNumber;
    }

    /**
     * 设置entryNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryNumber(String value) {
        this.entryNumber = value;
    }

    /**
     * 获取pointOfOrigin属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointOfOrigin() {
        return pointOfOrigin;
    }

    /**
     * 设置pointOfOrigin属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointOfOrigin(String value) {
        this.pointOfOrigin = value;
    }

    /**
     * 获取modeOfTransport属性的值。
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
     * 设置modeOfTransport属性的值。
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
     * 获取portOfExport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortOfExport() {
        return portOfExport;
    }

    /**
     * 设置portOfExport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortOfExport(String value) {
        this.portOfExport = value;
    }

    /**
     * 获取portOfUnloading属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortOfUnloading() {
        return portOfUnloading;
    }

    /**
     * 设置portOfUnloading属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortOfUnloading(String value) {
        this.portOfUnloading = value;
    }

    /**
     * 获取loadingPier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadingPier() {
        return loadingPier;
    }

    /**
     * 设置loadingPier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadingPier(String value) {
        this.loadingPier = value;
    }

    /**
     * 获取partiesToTransaction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartiesToTransaction() {
        return partiesToTransaction;
    }

    /**
     * 设置partiesToTransaction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartiesToTransaction(String value) {
        this.partiesToTransaction = value;
    }

    /**
     * 获取routedExportTransactionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutedExportTransactionIndicator() {
        return routedExportTransactionIndicator;
    }

    /**
     * 设置routedExportTransactionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutedExportTransactionIndicator(String value) {
        this.routedExportTransactionIndicator = value;
    }

    /**
     * 获取containerizedIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerizedIndicator() {
        return containerizedIndicator;
    }

    /**
     * 设置containerizedIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerizedIndicator(String value) {
        this.containerizedIndicator = value;
    }

    /**
     * 获取license属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LicenseType }
     *     
     */
    public LicenseType getLicense() {
        return license;
    }

    /**
     * 设置license属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseType }
     *     
     */
    public void setLicense(LicenseType value) {
        this.license = value;
    }

    /**
     * 获取eccnNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECCNNumber() {
        return eccnNumber;
    }

    /**
     * 设置eccnNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECCNNumber(String value) {
        this.eccnNumber = value;
    }

    /**
     * 获取shipperMemo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperMemo() {
        return shipperMemo;
    }

    /**
     * 设置shipperMemo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperMemo(String value) {
        this.shipperMemo = value;
    }

    /**
     * 获取overridePaperlessIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverridePaperlessIndicator() {
        return overridePaperlessIndicator;
    }

    /**
     * 设置overridePaperlessIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverridePaperlessIndicator(String value) {
        this.overridePaperlessIndicator = value;
    }

    /**
     * 获取multiCurrencyInvoiceLineTotal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultiCurrencyInvoiceLineTotal() {
        return multiCurrencyInvoiceLineTotal;
    }

    /**
     * 设置multiCurrencyInvoiceLineTotal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultiCurrencyInvoiceLineTotal(String value) {
        this.multiCurrencyInvoiceLineTotal = value;
    }

    /**
     * 获取pointOfOriginType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointOfOriginType() {
        return pointOfOriginType;
    }

    /**
     * 设置pointOfOriginType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointOfOriginType(String value) {
        this.pointOfOriginType = value;
    }

    /**
     * 获取eeiFilingOption属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EEIFilingOptionType }
     *     
     */
    public EEIFilingOptionType getEEIFilingOption() {
        return eeiFilingOption;
    }

    /**
     * 设置eeiFilingOption属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EEIFilingOptionType }
     *     
     */
    public void setEEIFilingOption(EEIFilingOptionType value) {
        this.eeiFilingOption = value;
    }

}

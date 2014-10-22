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
 * <p>ShipmentType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ShipmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnService" type="{}ReturnServiceType" minOccurs="0"/>
 *         &lt;element name="DocumentsOnly" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Shipper" type="{}ShipperType"/>
 *         &lt;element name="ShipTo" type="{}ShipToType"/>
 *         &lt;element name="AlternateDeliveryAddress" type="{}AlternateDeliveryAddressType" minOccurs="0"/>
 *         &lt;element name="ShipFrom" type="{}ShipFromType" minOccurs="0"/>
 *         &lt;element name="SoldTo" type="{}SoldToType" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="PaymentInformation" type="{}PaymentInformationType" minOccurs="0"/>
 *           &lt;element name="ItemizedPaymentInformation" type="{}ItemizedPaymentInformationType" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="GoodsNotInFreeCirculationIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateInformation" type="{}RateInformationType" minOccurs="0"/>
 *         &lt;element name="MovementReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{}ReferenceNumberType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Service" type="{}ServiceType"/>
 *         &lt;element name="InvoiceLineTotal" type="{}InvoiceLineTotalType" minOccurs="0"/>
 *         &lt;element name="NumOfPiecesInShipment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USPSEndorsement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MILabelCN22Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubClassification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CostCenter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IrregularIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentServiceOptions" type="{}ShipmentServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="Package" type="{}PackageType" maxOccurs="unbounded"/>
 *         &lt;element name="ItemizedChargesRequestedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentIndicationType" type="{}IndicationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentType", propOrder = {
    "description",
    "returnService",
    "documentsOnly",
    "shipper",
    "shipTo",
    "alternateDeliveryAddress",
    "shipFrom",
    "soldTo",
    "paymentInformation",
    "itemizedPaymentInformation",
    "goodsNotInFreeCirculationIndicator",
    "rateInformation",
    "movementReferenceNumber",
    "referenceNumber",
    "service",
    "invoiceLineTotal",
    "numOfPiecesInShipment",
    "uspsEndorsement",
    "miLabelCN22Indicator",
    "subClassification",
    "costCenter",
    "packageID",
    "irregularIndicator",
    "shipmentServiceOptions",
    "_package",
    "itemizedChargesRequestedIndicator",
    "shipmentIndicationType"
})
public class ShipmentType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ReturnService")
    protected ReturnServiceType returnService;
    @XmlElement(name = "DocumentsOnly")
    protected String documentsOnly;
    @XmlElement(name = "Shipper", required = true)
    protected ShipperType shipper;
    @XmlElement(name = "ShipTo", required = true)
    protected ShipToType shipTo;
    @XmlElement(name = "AlternateDeliveryAddress")
    protected AlternateDeliveryAddressType alternateDeliveryAddress;
    @XmlElement(name = "ShipFrom")
    protected ShipFromType shipFrom;
    @XmlElement(name = "SoldTo")
    protected SoldToType soldTo;
    @XmlElement(name = "PaymentInformation")
    protected PaymentInformationType paymentInformation;
    @XmlElement(name = "ItemizedPaymentInformation")
    protected ItemizedPaymentInformationType itemizedPaymentInformation;
    @XmlElement(name = "GoodsNotInFreeCirculationIndicator")
    protected String goodsNotInFreeCirculationIndicator;
    @XmlElement(name = "RateInformation")
    protected RateInformationType rateInformation;
    @XmlElement(name = "MovementReferenceNumber")
    protected String movementReferenceNumber;
    @XmlElement(name = "ReferenceNumber")
    protected List<ReferenceNumberType> referenceNumber;
    @XmlElement(name = "Service", required = true)
    protected ServiceType service;
    @XmlElement(name = "InvoiceLineTotal")
    protected InvoiceLineTotalType invoiceLineTotal;
    @XmlElement(name = "NumOfPiecesInShipment")
    protected String numOfPiecesInShipment;
    @XmlElement(name = "USPSEndorsement")
    protected String uspsEndorsement;
    @XmlElement(name = "MILabelCN22Indicator")
    protected String miLabelCN22Indicator;
    @XmlElement(name = "SubClassification")
    protected String subClassification;
    @XmlElement(name = "CostCenter")
    protected String costCenter;
    @XmlElement(name = "PackageID")
    protected String packageID;
    @XmlElement(name = "IrregularIndicator")
    protected String irregularIndicator;
    @XmlElement(name = "ShipmentServiceOptions")
    protected ShipmentServiceOptionsType shipmentServiceOptions;
    @XmlElement(name = "Package", required = true)
    protected List<PackageType> _package;
    @XmlElement(name = "ItemizedChargesRequestedIndicator")
    protected String itemizedChargesRequestedIndicator;
    @XmlElement(name = "ShipmentIndicationType")
    protected List<IndicationType> shipmentIndicationType;

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取returnService属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReturnServiceType }
     *     
     */
    public ReturnServiceType getReturnService() {
        return returnService;
    }

    /**
     * 设置returnService属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnServiceType }
     *     
     */
    public void setReturnService(ReturnServiceType value) {
        this.returnService = value;
    }

    /**
     * 获取documentsOnly属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentsOnly() {
        return documentsOnly;
    }

    /**
     * 设置documentsOnly属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentsOnly(String value) {
        this.documentsOnly = value;
    }

    /**
     * 获取shipper属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipperType }
     *     
     */
    public ShipperType getShipper() {
        return shipper;
    }

    /**
     * 设置shipper属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipperType }
     *     
     */
    public void setShipper(ShipperType value) {
        this.shipper = value;
    }

    /**
     * 获取shipTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipToType }
     *     
     */
    public ShipToType getShipTo() {
        return shipTo;
    }

    /**
     * 设置shipTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipToType }
     *     
     */
    public void setShipTo(ShipToType value) {
        this.shipTo = value;
    }

    /**
     * 获取alternateDeliveryAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AlternateDeliveryAddressType }
     *     
     */
    public AlternateDeliveryAddressType getAlternateDeliveryAddress() {
        return alternateDeliveryAddress;
    }

    /**
     * 设置alternateDeliveryAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AlternateDeliveryAddressType }
     *     
     */
    public void setAlternateDeliveryAddress(AlternateDeliveryAddressType value) {
        this.alternateDeliveryAddress = value;
    }

    /**
     * 获取shipFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipFromType }
     *     
     */
    public ShipFromType getShipFrom() {
        return shipFrom;
    }

    /**
     * 设置shipFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipFromType }
     *     
     */
    public void setShipFrom(ShipFromType value) {
        this.shipFrom = value;
    }

    /**
     * 获取soldTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SoldToType }
     *     
     */
    public SoldToType getSoldTo() {
        return soldTo;
    }

    /**
     * 设置soldTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SoldToType }
     *     
     */
    public void setSoldTo(SoldToType value) {
        this.soldTo = value;
    }

    /**
     * 获取paymentInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentInformationType }
     *     
     */
    public PaymentInformationType getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * 设置paymentInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInformationType }
     *     
     */
    public void setPaymentInformation(PaymentInformationType value) {
        this.paymentInformation = value;
    }

    /**
     * 获取itemizedPaymentInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ItemizedPaymentInformationType }
     *     
     */
    public ItemizedPaymentInformationType getItemizedPaymentInformation() {
        return itemizedPaymentInformation;
    }

    /**
     * 设置itemizedPaymentInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ItemizedPaymentInformationType }
     *     
     */
    public void setItemizedPaymentInformation(ItemizedPaymentInformationType value) {
        this.itemizedPaymentInformation = value;
    }

    /**
     * 获取goodsNotInFreeCirculationIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsNotInFreeCirculationIndicator() {
        return goodsNotInFreeCirculationIndicator;
    }

    /**
     * 设置goodsNotInFreeCirculationIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsNotInFreeCirculationIndicator(String value) {
        this.goodsNotInFreeCirculationIndicator = value;
    }

    /**
     * 获取rateInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateInformationType }
     *     
     */
    public RateInformationType getRateInformation() {
        return rateInformation;
    }

    /**
     * 设置rateInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateInformationType }
     *     
     */
    public void setRateInformation(RateInformationType value) {
        this.rateInformation = value;
    }

    /**
     * 获取movementReferenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementReferenceNumber() {
        return movementReferenceNumber;
    }

    /**
     * 设置movementReferenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementReferenceNumber(String value) {
        this.movementReferenceNumber = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceNumberType }
     * 
     * 
     */
    public List<ReferenceNumberType> getReferenceNumber() {
        if (referenceNumber == null) {
            referenceNumber = new ArrayList<ReferenceNumberType>();
        }
        return this.referenceNumber;
    }

    /**
     * 获取service属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getService() {
        return service;
    }

    /**
     * 设置service属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setService(ServiceType value) {
        this.service = value;
    }

    /**
     * 获取invoiceLineTotal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InvoiceLineTotalType }
     *     
     */
    public InvoiceLineTotalType getInvoiceLineTotal() {
        return invoiceLineTotal;
    }

    /**
     * 设置invoiceLineTotal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceLineTotalType }
     *     
     */
    public void setInvoiceLineTotal(InvoiceLineTotalType value) {
        this.invoiceLineTotal = value;
    }

    /**
     * 获取numOfPiecesInShipment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumOfPiecesInShipment() {
        return numOfPiecesInShipment;
    }

    /**
     * 设置numOfPiecesInShipment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumOfPiecesInShipment(String value) {
        this.numOfPiecesInShipment = value;
    }

    /**
     * 获取uspsEndorsement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSPSEndorsement() {
        return uspsEndorsement;
    }

    /**
     * 设置uspsEndorsement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSPSEndorsement(String value) {
        this.uspsEndorsement = value;
    }

    /**
     * 获取miLabelCN22Indicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMILabelCN22Indicator() {
        return miLabelCN22Indicator;
    }

    /**
     * 设置miLabelCN22Indicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMILabelCN22Indicator(String value) {
        this.miLabelCN22Indicator = value;
    }

    /**
     * 获取subClassification属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubClassification() {
        return subClassification;
    }

    /**
     * 设置subClassification属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubClassification(String value) {
        this.subClassification = value;
    }

    /**
     * 获取costCenter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostCenter() {
        return costCenter;
    }

    /**
     * 设置costCenter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostCenter(String value) {
        this.costCenter = value;
    }

    /**
     * 获取packageID属性的值。
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
     * 设置packageID属性的值。
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
     * 获取irregularIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIrregularIndicator() {
        return irregularIndicator;
    }

    /**
     * 设置irregularIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIrregularIndicator(String value) {
        this.irregularIndicator = value;
    }

    /**
     * 获取shipmentServiceOptions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsType }
     *     
     */
    public ShipmentServiceOptionsType getShipmentServiceOptions() {
        return shipmentServiceOptions;
    }

    /**
     * 设置shipmentServiceOptions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsType }
     *     
     */
    public void setShipmentServiceOptions(ShipmentServiceOptionsType value) {
        this.shipmentServiceOptions = value;
    }

    /**
     * Gets the value of the package property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the package property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageType }
     * 
     * 
     */
    public List<PackageType> getPackage() {
        if (_package == null) {
            _package = new ArrayList<PackageType>();
        }
        return this._package;
    }

    /**
     * 获取itemizedChargesRequestedIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemizedChargesRequestedIndicator() {
        return itemizedChargesRequestedIndicator;
    }

    /**
     * 设置itemizedChargesRequestedIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemizedChargesRequestedIndicator(String value) {
        this.itemizedChargesRequestedIndicator = value;
    }

    /**
     * Gets the value of the shipmentIndicationType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentIndicationType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentIndicationType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndicationType }
     * 
     * 
     */
    public List<IndicationType> getShipmentIndicationType() {
        if (shipmentIndicationType == null) {
            shipmentIndicationType = new ArrayList<IndicationType>();
        }
        return this.shipmentIndicationType;
    }

}

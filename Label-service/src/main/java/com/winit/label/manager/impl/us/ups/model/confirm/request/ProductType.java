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
 * <p>ProductType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ProductType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Description" maxOccurs="unbounded"/>
 *         &lt;element name="Unit" type="{}UnitType" minOccurs="0"/>
 *         &lt;element ref="{}CommodityCode" minOccurs="0"/>
 *         &lt;element ref="{}PartNumber" minOccurs="0"/>
 *         &lt;element ref="{}OriginCountryCode" minOccurs="0"/>
 *         &lt;element name="JointProductionIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetCostCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetCostDateRange" type="{}NetCostDateRangeType" minOccurs="0"/>
 *         &lt;element name="PreferenceCriteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProducerInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}MarksAndNumbers" minOccurs="0"/>
 *         &lt;element ref="{}NumberOfPackagesPerCommodity" minOccurs="0"/>
 *         &lt;element name="ProductWeight" type="{}ProductWeightType" minOccurs="0"/>
 *         &lt;element name="VehicleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScheduleB" type="{}ScheduleBType" minOccurs="0"/>
 *         &lt;element name="ExportType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEDTotalValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExcludeFromForm" type="{}ExcludeFromFormType" minOccurs="0"/>
 *         &lt;element name="ProductCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackingListInfo" type="{}PackingListInfoType" minOccurs="0"/>
 *         &lt;element name="EEIInformation" type="{}EEIInformationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductType", propOrder = {
    "description",
    "unit",
    "commodityCode",
    "partNumber",
    "originCountryCode",
    "jointProductionIndicator",
    "netCostCode",
    "netCostDateRange",
    "preferenceCriteria",
    "producerInfo",
    "marksAndNumbers",
    "numberOfPackagesPerCommodity",
    "productWeight",
    "vehicleID",
    "scheduleB",
    "exportType",
    "sedTotalValue",
    "excludeFromForm",
    "productCurrencyCode",
    "packingListInfo",
    "eeiInformation"
})
public class ProductType {

    @XmlElement(name = "Description", required = true)
    protected List<String> description;
    @XmlElement(name = "Unit")
    protected UnitType unit;
    @XmlElement(name = "CommodityCode")
    protected String commodityCode;
    @XmlElement(name = "PartNumber")
    protected String partNumber;
    @XmlElement(name = "OriginCountryCode")
    protected String originCountryCode;
    @XmlElement(name = "JointProductionIndicator")
    protected String jointProductionIndicator;
    @XmlElement(name = "NetCostCode")
    protected String netCostCode;
    @XmlElement(name = "NetCostDateRange")
    protected NetCostDateRangeType netCostDateRange;
    @XmlElement(name = "PreferenceCriteria")
    protected String preferenceCriteria;
    @XmlElement(name = "ProducerInfo")
    protected String producerInfo;
    @XmlElement(name = "MarksAndNumbers")
    protected String marksAndNumbers;
    @XmlElement(name = "NumberOfPackagesPerCommodity")
    protected String numberOfPackagesPerCommodity;
    @XmlElement(name = "ProductWeight")
    protected ProductWeightType productWeight;
    @XmlElement(name = "VehicleID")
    protected String vehicleID;
    @XmlElement(name = "ScheduleB")
    protected ScheduleBType scheduleB;
    @XmlElement(name = "ExportType")
    protected String exportType;
    @XmlElement(name = "SEDTotalValue")
    protected String sedTotalValue;
    @XmlElement(name = "ExcludeFromForm")
    protected ExcludeFromFormType excludeFromForm;
    @XmlElement(name = "ProductCurrencyCode")
    protected String productCurrencyCode;
    @XmlElement(name = "PackingListInfo")
    protected PackingListInfoType packingListInfo;
    @XmlElement(name = "EEIInformation")
    protected EEIInformationType eeiInformation;

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDescription() {
        if (description == null) {
            description = new ArrayList<String>();
        }
        return this.description;
    }

    /**
     * 获取unit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getUnit() {
        return unit;
    }

    /**
     * 设置unit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setUnit(UnitType value) {
        this.unit = value;
    }

    /**
     * 获取commodityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /**
     * 设置commodityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityCode(String value) {
        this.commodityCode = value;
    }

    /**
     * 获取partNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * 设置partNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNumber(String value) {
        this.partNumber = value;
    }

    /**
     * 获取originCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCountryCode() {
        return originCountryCode;
    }

    /**
     * 设置originCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCountryCode(String value) {
        this.originCountryCode = value;
    }

    /**
     * 获取jointProductionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJointProductionIndicator() {
        return jointProductionIndicator;
    }

    /**
     * 设置jointProductionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJointProductionIndicator(String value) {
        this.jointProductionIndicator = value;
    }

    /**
     * 获取netCostCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetCostCode() {
        return netCostCode;
    }

    /**
     * 设置netCostCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetCostCode(String value) {
        this.netCostCode = value;
    }

    /**
     * 获取netCostDateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NetCostDateRangeType }
     *     
     */
    public NetCostDateRangeType getNetCostDateRange() {
        return netCostDateRange;
    }

    /**
     * 设置netCostDateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NetCostDateRangeType }
     *     
     */
    public void setNetCostDateRange(NetCostDateRangeType value) {
        this.netCostDateRange = value;
    }

    /**
     * 获取preferenceCriteria属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceCriteria() {
        return preferenceCriteria;
    }

    /**
     * 设置preferenceCriteria属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceCriteria(String value) {
        this.preferenceCriteria = value;
    }

    /**
     * 获取producerInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerInfo() {
        return producerInfo;
    }

    /**
     * 设置producerInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerInfo(String value) {
        this.producerInfo = value;
    }

    /**
     * 获取marksAndNumbers属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarksAndNumbers() {
        return marksAndNumbers;
    }

    /**
     * 设置marksAndNumbers属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarksAndNumbers(String value) {
        this.marksAndNumbers = value;
    }

    /**
     * 获取numberOfPackagesPerCommodity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfPackagesPerCommodity() {
        return numberOfPackagesPerCommodity;
    }

    /**
     * 设置numberOfPackagesPerCommodity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfPackagesPerCommodity(String value) {
        this.numberOfPackagesPerCommodity = value;
    }

    /**
     * 获取productWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProductWeightType }
     *     
     */
    public ProductWeightType getProductWeight() {
        return productWeight;
    }

    /**
     * 设置productWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProductWeightType }
     *     
     */
    public void setProductWeight(ProductWeightType value) {
        this.productWeight = value;
    }

    /**
     * 获取vehicleID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * 设置vehicleID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleID(String value) {
        this.vehicleID = value;
    }

    /**
     * 获取scheduleB属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ScheduleBType }
     *     
     */
    public ScheduleBType getScheduleB() {
        return scheduleB;
    }

    /**
     * 设置scheduleB属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleBType }
     *     
     */
    public void setScheduleB(ScheduleBType value) {
        this.scheduleB = value;
    }

    /**
     * 获取exportType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportType() {
        return exportType;
    }

    /**
     * 设置exportType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportType(String value) {
        this.exportType = value;
    }

    /**
     * 获取sedTotalValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEDTotalValue() {
        return sedTotalValue;
    }

    /**
     * 设置sedTotalValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEDTotalValue(String value) {
        this.sedTotalValue = value;
    }

    /**
     * 获取excludeFromForm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExcludeFromFormType }
     *     
     */
    public ExcludeFromFormType getExcludeFromForm() {
        return excludeFromForm;
    }

    /**
     * 设置excludeFromForm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExcludeFromFormType }
     *     
     */
    public void setExcludeFromForm(ExcludeFromFormType value) {
        this.excludeFromForm = value;
    }

    /**
     * 获取productCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCurrencyCode() {
        return productCurrencyCode;
    }

    /**
     * 设置productCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCurrencyCode(String value) {
        this.productCurrencyCode = value;
    }

    /**
     * 获取packingListInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackingListInfoType }
     *     
     */
    public PackingListInfoType getPackingListInfo() {
        return packingListInfo;
    }

    /**
     * 设置packingListInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackingListInfoType }
     *     
     */
    public void setPackingListInfo(PackingListInfoType value) {
        this.packingListInfo = value;
    }

    /**
     * 获取eeiInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EEIInformationType }
     *     
     */
    public EEIInformationType getEEIInformation() {
        return eeiInformation;
    }

    /**
     * 设置eeiInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EEIInformationType }
     *     
     */
    public void setEEIInformation(EEIInformationType value) {
        this.eeiInformation = value;
    }

}

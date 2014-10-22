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
 * <p>LabelSpecificationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LabelSpecificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LabelPrintMethod" type="{}LabelPrintMethodCodeDescriptionType"/>
 *         &lt;element name="HTTPUserAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LabelStockSize" type="{}LabelStockSizeType" minOccurs="0"/>
 *         &lt;element name="LabelImageFormat" type="{}LabelImageFormatCodeDescriptionType"/>
 *         &lt;element name="Instruction" type="{}InstructionCodeDescriptionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelSpecificationType", propOrder = {
    "labelPrintMethod",
    "httpUserAgent",
    "labelStockSize",
    "labelImageFormat",
    "instruction"
})
public class LabelSpecificationType {

    @XmlElement(name = "LabelPrintMethod", required = true)
    protected LabelPrintMethodCodeDescriptionType labelPrintMethod;
    @XmlElement(name = "HTTPUserAgent")
    protected String httpUserAgent;
    @XmlElement(name = "LabelStockSize")
    protected LabelStockSizeType labelStockSize;
    @XmlElement(name = "LabelImageFormat", required = true)
    protected LabelImageFormatCodeDescriptionType labelImageFormat;
    @XmlElement(name = "Instruction")
    protected List<InstructionCodeDescriptionType> instruction;

    /**
     * 获取labelPrintMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LabelPrintMethodCodeDescriptionType }
     *     
     */
    public LabelPrintMethodCodeDescriptionType getLabelPrintMethod() {
        return labelPrintMethod;
    }

    /**
     * 设置labelPrintMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LabelPrintMethodCodeDescriptionType }
     *     
     */
    public void setLabelPrintMethod(LabelPrintMethodCodeDescriptionType value) {
        this.labelPrintMethod = value;
    }

    /**
     * 获取httpUserAgent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHTTPUserAgent() {
        return httpUserAgent;
    }

    /**
     * 设置httpUserAgent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHTTPUserAgent(String value) {
        this.httpUserAgent = value;
    }

    /**
     * 获取labelStockSize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LabelStockSizeType }
     *     
     */
    public LabelStockSizeType getLabelStockSize() {
        return labelStockSize;
    }

    /**
     * 设置labelStockSize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LabelStockSizeType }
     *     
     */
    public void setLabelStockSize(LabelStockSizeType value) {
        this.labelStockSize = value;
    }

    /**
     * 获取labelImageFormat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LabelImageFormatCodeDescriptionType }
     *     
     */
    public LabelImageFormatCodeDescriptionType getLabelImageFormat() {
        return labelImageFormat;
    }

    /**
     * 设置labelImageFormat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LabelImageFormatCodeDescriptionType }
     *     
     */
    public void setLabelImageFormat(LabelImageFormatCodeDescriptionType value) {
        this.labelImageFormat = value;
    }

    /**
     * Gets the value of the instruction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instruction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstruction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstructionCodeDescriptionType }
     * 
     * 
     */
    public List<InstructionCodeDescriptionType> getInstruction() {
        if (instruction == null) {
            instruction = new ArrayList<InstructionCodeDescriptionType>();
        }
        return this.instruction;
    }

}

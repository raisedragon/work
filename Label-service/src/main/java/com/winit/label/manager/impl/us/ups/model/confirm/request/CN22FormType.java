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
 * <p>CN22FormType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CN22FormType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LabelSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrintsPerPage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LabelPrintType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22OtherDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FoldHereText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CN22Content" type="{}CN22ContentType" maxOccurs="3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CN22FormType", propOrder = {
    "labelSize",
    "printsPerPage",
    "labelPrintType",
    "cn22Type",
    "cn22OtherDescription",
    "foldHereText",
    "cn22Content"
})
public class CN22FormType {

    @XmlElement(name = "LabelSize")
    protected String labelSize;
    @XmlElement(name = "PrintsPerPage")
    protected String printsPerPage;
    @XmlElement(name = "LabelPrintType")
    protected String labelPrintType;
    @XmlElement(name = "CN22Type")
    protected String cn22Type;
    @XmlElement(name = "CN22OtherDescription")
    protected String cn22OtherDescription;
    @XmlElement(name = "FoldHereText")
    protected String foldHereText;
    @XmlElement(name = "CN22Content")
    protected List<CN22ContentType> cn22Content;

    /**
     * 获取labelSize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelSize() {
        return labelSize;
    }

    /**
     * 设置labelSize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelSize(String value) {
        this.labelSize = value;
    }

    /**
     * 获取printsPerPage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintsPerPage() {
        return printsPerPage;
    }

    /**
     * 设置printsPerPage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintsPerPage(String value) {
        this.printsPerPage = value;
    }

    /**
     * 获取labelPrintType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelPrintType() {
        return labelPrintType;
    }

    /**
     * 设置labelPrintType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelPrintType(String value) {
        this.labelPrintType = value;
    }

    /**
     * 获取cn22Type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22Type() {
        return cn22Type;
    }

    /**
     * 设置cn22Type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22Type(String value) {
        this.cn22Type = value;
    }

    /**
     * 获取cn22OtherDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCN22OtherDescription() {
        return cn22OtherDescription;
    }

    /**
     * 设置cn22OtherDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCN22OtherDescription(String value) {
        this.cn22OtherDescription = value;
    }

    /**
     * 获取foldHereText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoldHereText() {
        return foldHereText;
    }

    /**
     * 设置foldHereText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoldHereText(String value) {
        this.foldHereText = value;
    }

    /**
     * Gets the value of the cn22Content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cn22Content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCN22Content().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CN22ContentType }
     * 
     * 
     */
    public List<CN22ContentType> getCN22Content() {
        if (cn22Content == null) {
            cn22Content = new ArrayList<CN22ContentType>();
        }
        return this.cn22Content;
    }

}

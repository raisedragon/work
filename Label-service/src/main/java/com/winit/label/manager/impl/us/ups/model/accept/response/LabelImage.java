//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:40:10 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.accept.response;

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
 *         &lt;element ref="{}LabelImageFormat"/>
 *         &lt;element ref="{}GraphicImage"/>
 *         &lt;element ref="{}InternationalSignatureGraphicImage" minOccurs="0"/>
 *         &lt;element ref="{}HTMLImage" minOccurs="0"/>
 *         &lt;element ref="{}PDF417" minOccurs="0"/>
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
    "labelImageFormat",
    "graphicImage",
    "internationalSignatureGraphicImage",
    "htmlImage",
    "pdf417"
})
@XmlRootElement(name = "LabelImage")
public class LabelImage {

    @XmlElement(name = "LabelImageFormat", required = true)
    protected LabelImageFormat labelImageFormat;
    @XmlElement(name = "GraphicImage", required = true)
    protected String graphicImage;
    @XmlElement(name = "InternationalSignatureGraphicImage")
    protected String internationalSignatureGraphicImage;
    @XmlElement(name = "HTMLImage")
    protected String htmlImage;
    @XmlElement(name = "PDF417")
    protected String pdf417;

    /**
     * 获取labelImageFormat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LabelImageFormat }
     *     
     */
    public LabelImageFormat getLabelImageFormat() {
        return labelImageFormat;
    }

    /**
     * 设置labelImageFormat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LabelImageFormat }
     *     
     */
    public void setLabelImageFormat(LabelImageFormat value) {
        this.labelImageFormat = value;
    }

    /**
     * 获取graphicImage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGraphicImage() {
        return graphicImage;
    }

    /**
     * 设置graphicImage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGraphicImage(String value) {
        this.graphicImage = value;
    }

    /**
     * 获取internationalSignatureGraphicImage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalSignatureGraphicImage() {
        return internationalSignatureGraphicImage;
    }

    /**
     * 设置internationalSignatureGraphicImage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalSignatureGraphicImage(String value) {
        this.internationalSignatureGraphicImage = value;
    }

    /**
     * 获取htmlImage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHTMLImage() {
        return htmlImage;
    }

    /**
     * 设置htmlImage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHTMLImage(String value) {
        this.htmlImage = value;
    }

    /**
     * 获取pdf417属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDF417() {
        return pdf417;
    }

    /**
     * 设置pdf417属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDF417(String value) {
        this.pdf417 = value;
    }

}

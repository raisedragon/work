//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:39:53 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.confirm.response;

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
 *         &lt;element ref="{}TransactionReference" minOccurs="0"/>
 *         &lt;element ref="{}ResponseStatusCode"/>
 *         &lt;element ref="{}ResponseStatusDescription" minOccurs="0"/>
 *         &lt;element ref="{}Error" maxOccurs="unbounded" minOccurs="0"/>
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
    "transactionReference",
    "responseStatusCode",
    "responseStatusDescription",
    "error"
})
@XmlRootElement(name = "Response")
public class Response {

    @XmlElement(name = "TransactionReference")
    protected TransactionReference transactionReference;
    @XmlElement(name = "ResponseStatusCode", required = true)
    protected String responseStatusCode;
    @XmlElement(name = "ResponseStatusDescription")
    protected String responseStatusDescription;
    @XmlElement(name = "Error")
    protected List<Error> error;

    /**
     * 获取transactionReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransactionReference }
     *     
     */
    public TransactionReference getTransactionReference() {
        return transactionReference;
    }

    /**
     * 设置transactionReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionReference }
     *     
     */
    public void setTransactionReference(TransactionReference value) {
        this.transactionReference = value;
    }

    /**
     * 获取responseStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    /**
     * 设置responseStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseStatusCode(String value) {
        this.responseStatusCode = value;
    }

    /**
     * 获取responseStatusDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseStatusDescription() {
        return responseStatusDescription;
    }

    /**
     * 设置responseStatusDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseStatusDescription(String value) {
        this.responseStatusDescription = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Error }
     * 
     * 
     */
    public List<Error> getError() {
        if (error == null) {
            error = new ArrayList<Error>();
        }
        return this.error;
    }

}


package com.winit.label.manager.impl.us.usps.intl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="RefreshWebComponentResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "refreshWebComponentResult"
})
@XmlRootElement(name = "RefreshWebComponentResponse")
public class RefreshWebComponentResponse {

    @XmlElement(name = "RefreshWebComponentResult")
    protected boolean refreshWebComponentResult;

    /**
     * ��ȡrefreshWebComponentResult���Ե�ֵ��
     * 
     */
    public boolean isRefreshWebComponentResult() {
        return refreshWebComponentResult;
    }

    /**
     * ����refreshWebComponentResult���Ե�ֵ��
     * 
     */
    public void setRefreshWebComponentResult(boolean value) {
        this.refreshWebComponentResult = value;
    }

}


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
 *         &lt;element name="AuthenticateUserResult" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}AuthenticateResult" minOccurs="0"/>
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
    "authenticateUserResult"
})
@XmlRootElement(name = "AuthenticateUserResponse")
public class AuthenticateUserResponse {

    @XmlElement(name = "AuthenticateUserResult")
    protected AuthenticateResult authenticateUserResult;

    /**
     * ��ȡauthenticateUserResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link AuthenticateResult }
     *     
     */
    public AuthenticateResult getAuthenticateUserResult() {
        return authenticateUserResult;
    }

    /**
     * ����authenticateUserResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticateResult }
     *     
     */
    public void setAuthenticateUserResult(AuthenticateResult value) {
        this.authenticateUserResult = value;
    }

}

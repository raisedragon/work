
package com.winit.label.manager.impl.us.usps.intl.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfFrozenDispatchPackage complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFrozenDispatchPackage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FrozenDispatchPackage" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}FrozenDispatchPackage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFrozenDispatchPackage", propOrder = {
    "frozenDispatchPackage"
})
public class ArrayOfFrozenDispatchPackage {

    @XmlElement(name = "FrozenDispatchPackage", nillable = true)
    protected List<FrozenDispatchPackage> frozenDispatchPackage;

    /**
     * Gets the value of the frozenDispatchPackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frozenDispatchPackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrozenDispatchPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FrozenDispatchPackage }
     * 
     * 
     */
    public List<FrozenDispatchPackage> getFrozenDispatchPackage() {
        if (frozenDispatchPackage == null) {
            frozenDispatchPackage = new ArrayList<FrozenDispatchPackage>();
        }
        return this.frozenDispatchPackage;
    }

}

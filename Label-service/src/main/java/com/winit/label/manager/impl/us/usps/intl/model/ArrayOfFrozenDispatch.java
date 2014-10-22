
package com.winit.label.manager.impl.us.usps.intl.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfFrozenDispatch complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFrozenDispatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FrozenDispatch" type="{http://www.usps-cpas.com/usps-cpas/GSSAPI/}FrozenDispatch" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFrozenDispatch", propOrder = {
    "frozenDispatch"
})
public class ArrayOfFrozenDispatch {

    @XmlElement(name = "FrozenDispatch", nillable = true)
    protected List<FrozenDispatch> frozenDispatch;

    /**
     * Gets the value of the frozenDispatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frozenDispatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrozenDispatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FrozenDispatch }
     * 
     * 
     */
    public List<FrozenDispatch> getFrozenDispatch() {
        if (frozenDispatch == null) {
            frozenDispatch = new ArrayList<FrozenDispatch>();
        }
        return this.frozenDispatch;
    }

}

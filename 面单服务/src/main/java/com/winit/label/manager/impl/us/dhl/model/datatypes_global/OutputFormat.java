//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.04 at 03:48:22 ���� CST 
//


package com.winit.label.manager.impl.us.dhl.model.datatypes_global;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutputFormat.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OutputFormat">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="PL2"/>
 *     &lt;enumeration value="ZPL2"/>
 *     &lt;enumeration value="JPG"/>
 *     &lt;enumeration value="PNG"/>
 *     &lt;enumeration value="EPL2"/>
 *     &lt;enumeration value="EPLN"/>
 *     &lt;enumeration value="ZPLN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OutputFormat")
@XmlEnum
public enum OutputFormat {

    PDF("PDF"),
    @XmlEnumValue("PL2")
    PL_2("PL2"),
    @XmlEnumValue("ZPL2")
    ZPL_2("ZPL2"),
    JPG("JPG"),
    PNG("PNG"),
    @XmlEnumValue("EPL2")
    EPL_2("EPL2"),
    EPLN("EPLN"),
    ZPLN("ZPLN");
    private final String value;

    OutputFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OutputFormat fromValue(String v) {
        for (OutputFormat c: OutputFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

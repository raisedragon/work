/**
 * ProcessPackage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public interface ProcessPackage extends javax.xml.rpc.Service {

/**
 * GSS Mailer Web Service
 */
    public java.lang.String getProcessPackageSoapAddress();

    public com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap getProcessPackageSoap() throws javax.xml.rpc.ServiceException;

    public com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap getProcessPackageSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

/**
 * ProcessPackageLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.svr.label.handler.impl.usa.usps.model;

public class ProcessPackageLocator extends org.apache.axis.client.Service implements com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackage {

/**
 * GSS Mailer Web Service
 */

    public ProcessPackageLocator() {
    }


    public ProcessPackageLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProcessPackageLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProcessPackageSoap
    private java.lang.String ProcessPackageSoap_address = "https://gss.usps.com/usps-cpas/TestGSSAPI/GSSMailerWebService.asmx";

    public java.lang.String getProcessPackageSoapAddress() {
        return ProcessPackageSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProcessPackageSoapWSDDServiceName = "ProcessPackageSoap";

    public java.lang.String getProcessPackageSoapWSDDServiceName() {
        return ProcessPackageSoapWSDDServiceName;
    }

    public void setProcessPackageSoapWSDDServiceName(java.lang.String name) {
        ProcessPackageSoapWSDDServiceName = name;
    }

    public com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap getProcessPackageSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProcessPackageSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProcessPackageSoap(endpoint);
    }

    public com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap getProcessPackageSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoapStub _stub = new com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoapStub(portAddress, this);
            _stub.setPortName(getProcessPackageSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProcessPackageSoapEndpointAddress(java.lang.String address) {
        ProcessPackageSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoapStub _stub = new com.winit.svr.label.handler.impl.usa.usps.model.ProcessPackageSoapStub(new java.net.URL(ProcessPackageSoap_address), this);
                _stub.setPortName(getProcessPackageSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ProcessPackageSoap".equals(inputPortName)) {
            return getProcessPackageSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ProcessPackage");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.usps-cpas.com/usps-cpas/GSSAPI/", "ProcessPackageSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProcessPackageSoap".equals(portName)) {
            setProcessPackageSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

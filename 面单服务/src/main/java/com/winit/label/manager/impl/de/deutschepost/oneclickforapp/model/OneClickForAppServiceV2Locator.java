/**
 * OneClickForAppServiceV2Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class OneClickForAppServiceV2Locator extends org.apache.axis.client.Service implements com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppServiceV2 {

    public OneClickForAppServiceV2Locator() {
    }


    public OneClickForAppServiceV2Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OneClickForAppServiceV2Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OneClickForAppPortV2
    private java.lang.String OneClickForAppPortV2_address = "https://internetmarke.deutschepost.de/OneClickForAppV2/OneClickForAppServiceV2";

    public java.lang.String getOneClickForAppPortV2Address() {
        return OneClickForAppPortV2_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OneClickForAppPortV2WSDDServiceName = "OneClickForAppPortV2";

    public java.lang.String getOneClickForAppPortV2WSDDServiceName() {
        return OneClickForAppPortV2WSDDServiceName;
    }

    public void setOneClickForAppPortV2WSDDServiceName(java.lang.String name) {
        OneClickForAppPortV2WSDDServiceName = name;
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2 getOneClickForAppPortV2() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OneClickForAppPortV2_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOneClickForAppPortV2(endpoint);
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2 getOneClickForAppPortV2(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppBindingV2Stub _stub = new com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppBindingV2Stub(portAddress, this);
            _stub.setPortName(getOneClickForAppPortV2WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOneClickForAppPortV2EndpointAddress(java.lang.String address) {
        OneClickForAppPortV2_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2.class.isAssignableFrom(serviceEndpointInterface)) {
                com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppBindingV2Stub _stub = new com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppBindingV2Stub(new java.net.URL(OneClickForAppPortV2_address), this);
                _stub.setPortName(getOneClickForAppPortV2WSDDServiceName());
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
        if ("OneClickForAppPortV2".equals(inputPortName)) {
            return getOneClickForAppPortV2();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "OneClickForAppServiceV2");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "OneClickForAppPortV2"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OneClickForAppPortV2".equals(portName)) {
            setOneClickForAppPortV2EndpointAddress(address);
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

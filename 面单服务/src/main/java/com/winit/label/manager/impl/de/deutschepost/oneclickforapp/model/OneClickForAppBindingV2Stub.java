/**
 * OneClickForAppBindingV2Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class OneClickForAppBindingV2Stub extends org.apache.axis.client.Stub implements com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2 {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[10];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrievePageFormats");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePageFormatsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePageFormatsRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePageFormatsRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePageFormatsResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePageFormatsResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "pageFormat"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrievePublicGallery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePublicGalleryRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePublicGalleryRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePublicGalleryRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePublicGalleryResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePublicGalleryResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "items"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkoutShoppingCartPDF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CheckoutShoppingCartPDFRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPDFRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CheckoutShoppingCartPDFResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationException"),
                      "de.dpag.oneclickforapp.V2.ShoppingCartValidationException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"),
                      "de.dpag.oneclickforapp.V2.IdentifyException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkoutShoppingCartPNG");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CheckoutShoppingCartPNGRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPNGRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPNGRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CheckoutShoppingCartPNGResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationException"),
                      "de.dpag.oneclickforapp.V2.ShoppingCartValidationException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"),
                      "de.dpag.oneclickforapp.V2.IdentifyException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("authenticateUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserException"),
                      "de.dpag.oneclickforapp.V2.AuthenticateUserException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrievePreviewVoucherPDF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPDFRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPDFRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPDFRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPDFResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidProductException"),
                      "de.dpag.oneclickforapp.V2.InvalidProductException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidProductException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidPageFormatException"),
                      "de.dpag.oneclickforapp.V2.InvalidPageFormatException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidPageFormatException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidMotiveException"),
                      "de.dpag.oneclickforapp.V2.InvalidMotiveException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidMotiveException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrievePreviewVoucherPNG");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPNGRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPNGRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPNGRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPNGResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidProductException"),
                      "de.dpag.oneclickforapp.V2.InvalidProductException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidProductException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidMotiveException"),
                      "de.dpag.oneclickforapp.V2.InvalidMotiveException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidMotiveException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrievePrivateGallery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePrivateGalleryRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePrivateGalleryRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePrivateGalleryRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePrivateGalleryResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePrivateGalleryResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "imageLink"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"),
                      "de.dpag.oneclickforapp.V2.IdentifyException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderRequestType"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderRequestType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderResponseType"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"),
                      "de.dpag.oneclickforapp.V2.IdentifyException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderException"),
                      "de.dpag.oneclickforapp.V2.RetrieveOrderException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createShopOrderId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CreateShopOrderIdRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">CreateShopOrderIdRequest"), com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">CreateShopOrderIdResponse"));
        oper.setReturnClass(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CreateShopOrderIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"),
                      "de.dpag.oneclickforapp.V2.IdentifyException",
                      new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    public OneClickForAppBindingV2Stub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public OneClickForAppBindingV2Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public OneClickForAppBindingV2Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">CreateShopOrderIdRequest");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">CreateShopOrderIdResponse");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", ">PageFormat>pageLayout");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormatPageLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Address");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Address.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AddressBinding");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Amount");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserErrorCodes");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserErrorCodes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "AuthenticateUserResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "BorderDimension");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.BorderDimension.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "CompanyName");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CompanyName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Dimension");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Dimension.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "GalleryItem");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "IdentifyException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ImageID");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ImageItem");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ImageItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidMotiveException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidPageFormatException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidPageFormatException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "InvalidProductException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Link");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "MotiveLink");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Name");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Name.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "NamedAddress");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Orientation");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Orientation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PageFormat");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PageFormatId");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PageType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PersonName");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PersonName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "Position");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Position.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PPL");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ProductCode");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderErrorCodes");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderErrorCodes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrieveOrderResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePageFormatsRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePageFormatsRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePageFormatsResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "PageFormat");
            qName2 = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "pageFormat");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPDFRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPDFRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherPNGRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPNGRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePreviewVoucherResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePrivateGalleryRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePrivateGalleryRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePrivateGalleryResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "MotiveLink");
            qName2 = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "imageLink");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePublicGalleryRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePublicGalleryRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "RetrievePublicGalleryResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "GalleryItem");
            qName2 = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "items");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShopOrderId");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCart");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPDFPosition");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPDFRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPNGRequestType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPNGRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPosition");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPosition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartPrice");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartResponseType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationErrorCodes");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorCodes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationErrorInfo");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationErrorInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "ShoppingCartValidationException");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "UserToken");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherLayout");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherList");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherType");
            qName2 = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "voucher");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherPosition");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherPosition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "VoucherType");
            cachedSerQNames.add(qName);
            cls = com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "WalletBalance");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[] retrievePageFormats(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePageFormatsRequestType parameter) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "retrievePageFormats"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[] retrievePublicGallery(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePublicGalleryRequestType parameter) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "retrievePublicGallery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType checkoutShoppingCartPDF(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "checkoutShoppingCartPDF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType checkoutShoppingCartPNG(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPNGRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "checkoutShoppingCartPNG"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType authenticateUser(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "authenticateUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType retrievePreviewVoucherPDF(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPDFRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidPageFormatException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "retrievePreviewVoucherPDF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidPageFormatException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidPageFormatException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType retrievePreviewVoucherPNG(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPNGRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "retrievePreviewVoucherPNG"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[] retrievePrivateGallery(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePrivateGalleryRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "retrievePrivateGallery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType retrieveOrder(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "retrieveOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse createShopOrderId(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdRequest createShopOrderIdRequest) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createShopOrderId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {createShopOrderIdRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) {
              throw (com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}

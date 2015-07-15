package com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model;

public class OneClickForAppPortTypeV2Proxy implements com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2 {
  private String _endpoint = null;
  private com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2 oneClickForAppPortTypeV2 = null;
  
  public OneClickForAppPortTypeV2Proxy() {
    _initOneClickForAppPortTypeV2Proxy();
  }
  
  public OneClickForAppPortTypeV2Proxy(String endpoint) {
    _endpoint = endpoint;
    _initOneClickForAppPortTypeV2Proxy();
  }
  
  private void _initOneClickForAppPortTypeV2Proxy() {
    try {
      oneClickForAppPortTypeV2 = (new com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppServiceV2Locator()).getOneClickForAppPortV2();
      if (oneClickForAppPortTypeV2 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)oneClickForAppPortTypeV2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)oneClickForAppPortTypeV2)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (oneClickForAppPortTypeV2 != null)
      ((javax.xml.rpc.Stub)oneClickForAppPortTypeV2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2 getOneClickForAppPortTypeV2() {
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2;
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem[] retrievePublicGallery(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePublicGalleryRequestType parameter) throws java.rmi.RemoteException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.retrievePublicGallery(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType checkoutShoppingCartPDF(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.checkoutShoppingCartPDF(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType checkoutShoppingCartPNG(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPNGRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.checkoutShoppingCartPNG(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType authenticateUser(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.authenticateUser(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType retrievePreviewVoucherPDF(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPDFRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidPageFormatException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.retrievePreviewVoucherPDF(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType retrievePreviewVoucherPNG(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPNGRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.retrievePreviewVoucherPNG(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink[] retrievePrivateGallery(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePrivateGalleryRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.retrievePrivateGallery(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType retrieveOrder(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderRequestType parameter) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.retrieveOrder(parameter);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse createShopOrderId(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdRequest createShopOrderIdRequest) throws java.rmi.RemoteException, com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.createShopOrderId(createShopOrderIdRequest);
  }
  
  public com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat[] retrievePageFormats(com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePageFormatsRequestType parameter) throws java.rmi.RemoteException{
    if (oneClickForAppPortTypeV2 == null)
      _initOneClickForAppPortTypeV2Proxy();
    return oneClickForAppPortTypeV2.retrievePageFormats(parameter);
  }
  
  
}
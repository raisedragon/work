package com.winit.label.manager.impl.de.deutschepost.oneclickforapp;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.apache.axis.AxisProperties;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Address;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AddressBinding;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.AuthenticateUserResponseType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CompanyName;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdRequest;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.CreateShopOrderIdResponse;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.GalleryItem;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.IdentifyException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidMotiveException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidPageFormatException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.InvalidProductException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.MotiveLink;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Name;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.NamedAddress;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppBindingV2Stub;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppPortTypeV2;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.OneClickForAppServiceV2Locator;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PageFormat;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.PersonName;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrieveOrderResponseType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePageFormatsRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPDFRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherPNGRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePreviewVoucherResponseType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePrivateGalleryRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.RetrievePublicGalleryRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFPosition;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPDFRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPNGRequestType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartPosition;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartResponseType;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.ShoppingCartValidationException;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherLayout;
import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.VoucherPosition;

/**
 * @author longsheng.wang
 *
 */
public class OneClickForAppPortTypeV2Wrapper {
	
	private static Logger logger = Logger.getLogger(OneClickForAppPortTypeV2Wrapper.class.getName());
	
	private static OneClickForAppPortTypeV2 port;
	
	private static HttpClient httpClient;

	private static String s_userToken;//="ce7b9b86";
	
	private static OneClickForAppPortTypeV2Wrapper instance;
	
	/**
	 * 获取OneClickForAppPortTypeV2Wrapper对象实例。系统中最多仅有一个实例（单例模式）。
	 * @return
	 * @throws Exception 
	 */
	public static OneClickForAppPortTypeV2Wrapper getInstance() throws Exception{
		if(instance!=null){
			instance =new OneClickForAppPortTypeV2Wrapper();
		}
		return instance;
	}
	

	private String getUserToken() {
		if (s_userToken == null) {
			try {
				this.authenticateUser();
			} catch (AuthenticateUserException e) {
				e.printStackTrace();
				throw new LabelBusinessException(e);
			} catch (RemoteException e) {
				logger.info(e.getMessage());
				throw new LabelBusinessException(e);
			}
		}
		return s_userToken;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OneClickForAppPortTypeV2Wrapper() throws Exception {

		String url =  DeutschePostConfig.SERVICE_URL();

		OneClickForAppServiceV2Locator locator = new OneClickForAppServiceV2Locator();
		HandlerRegistry handlerRegistry = locator.getHandlerRegistry();
		QName qname = new javax.xml.namespace.QName("http://oneclickforapp.dpag.de/V2", "OneClickForAppPortV2");

		List chain = handlerRegistry.getHandlerChain(qname);
		HandlerInfo info = new HandlerInfo();
		info.setHandlerClass(AxisLoggingHandler.class);
		chain.add(info);
		port = locator.getOneClickForAppPortV2(new URL(url));
		((OneClickForAppBindingV2Stub) port).setTimeout(Integer.valueOf(60 * 1000));

	}


	public void authenticateUser() throws RemoteException {

		String username = DeutschePostConfig.SERVICE_USERNAME();
		String password = DeutschePostConfig.SERVICE_PASSWORD();
		AuthenticateUserRequestType req = new AuthenticateUserRequestType();
		req.setUsername(username);
		req.setPassword(password);
		setHeader((OneClickForAppBindingV2Stub) port);
		AuthenticateUserResponseType res = port.authenticateUser(req);
		
		//TODO check error message
		s_userToken = res.getUserToken();
	}

	private static void setHeader(OneClickForAppBindingV2Stub port) {
		String namespace = "http://oneclickforapp.dpag.de/V2";
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(DeutschePostConfig.SERVICE_TIME_ZONE()));

		String PARTNER_ID = DeutschePostConfig.SERVICE_PARTNER_ID();
		String REQUEST_TIMESTAMP = DateFormatUtils.format(cal.getTime(),"ddMMyyyy-HHmmss", TimeZone.getTimeZone("Europe/Berlin"));
		String KEY_PHASE =DeutschePostConfig.SERVICE_KEY_PHASE();
		String SCHLUESSEL_DPWN_MEINMARKTPLATZ = DeutschePostConfig.SERVICE_SCHLUESSEL_DPWN_MEINMARKTPLATZ();

		String cat = PARTNER_ID + "::" + REQUEST_TIMESTAMP + "::" + KEY_PHASE + "::" + SCHLUESSEL_DPWN_MEINMARKTPLATZ;
		String md5 = DigestUtils.md5Hex(cat);

		String PARTNER_SIGNATURE = md5.substring(0, 8);

		SOAPHeaderElement PARTNER_ID_el = new SOAPHeaderElement(namespace,"PARTNER_ID", PARTNER_ID);
		SOAPHeaderElement REQUEST_TIMESTAMP_el = new SOAPHeaderElement(namespace, "REQUEST_TIMESTAMP", REQUEST_TIMESTAMP);
		SOAPHeaderElement KEY_PHASE_el = new SOAPHeaderElement(namespace,"KEY_PHASE", KEY_PHASE);
		SOAPHeaderElement PARTNER_SIGNATURE_el = new SOAPHeaderElement(namespace, "PARTNER_SIGNATURE", PARTNER_SIGNATURE);

		port.setHeader(PARTNER_ID_el);
		port.setHeader(REQUEST_TIMESTAMP_el);
		port.setHeader(KEY_PHASE_el);
		port.setHeader(PARTNER_SIGNATURE_el);
	}


	public GalleryItem[]  retrievePublicGallery() throws RemoteException {
		RetrievePublicGalleryRequestType request = new RetrievePublicGalleryRequestType();
		getUserToken();
		GalleryItem[] items = port.retrievePublicGallery(request);
		return items;
	}

//	@Test
	public void checkoutShoppingCartPDF() throws RemoteException,
			ShoppingCartValidationException, IdentifyException {
		ShoppingCartPDFRequestType request = new ShoppingCartPDFRequestType();

		request.setUserToken(this.getUserToken());

		request.setPageFormatId(7);
			
		 request.setPpl(21);

		// request.setShopOrderId(shopOrderId);

		List<ShoppingCartPDFPosition> positions = new ArrayList<ShoppingCartPDFPosition>();
		ShoppingCartPDFPosition position = new ShoppingCartPDFPosition();
		position.setProductCode(1);
		position.setImageID(79929186);
		AddressBinding addressBinding = new AddressBinding();

		// /sender address
		NamedAddress senderNamedAddress = new NamedAddress();
		Name senderName = new Name();
		CompanyName senderCompanyName = new CompanyName();
		senderCompanyName.setCompany("company");
//		PersonName senderPersonName = new PersonName();
//		senderPersonName.setFirstname("firstname");
//		senderPersonName.setLastname("lastname");
//		senderPersonName.setSalutation("salutation");
//		senderPersonName.setTitle("title");
//		senderCompanyName.setPersonName(senderPersonName);
		senderName.setCompanyName(senderCompanyName);
		senderNamedAddress.setName(senderName);
		Address senderAddress = new Address();
//		senderAddress.setAdditional("additional");
		senderAddress.setCity("city");
		senderAddress.setCountry("DE");
		senderAddress.setHouseNo("houseNo");
		senderAddress.setStreet("street");
		senderAddress.setZip("123456");
		senderNamedAddress.setAddress(senderAddress);
		addressBinding.setSender(senderNamedAddress);

		// /receiver address
		NamedAddress receiverNamedAddress = new NamedAddress();
		Name receiverName = new Name();
//		CompanyName receiverCompanyName = new CompanyName();
//		receiverCompanyName.setCompany("company");
//		receiverName.setCompanyName(receiverCompanyName);
		PersonName receiverPersonName = new PersonName();
		receiverPersonName.setFirstname("firstname");
		receiverPersonName.setLastname(".");
//		receiverPersonName.setSalutation("salutation");
//		receiverPersonName.setTitle("title");
		receiverName.setPersonName(receiverPersonName);
		receiverNamedAddress.setName(receiverName);
		Address receiverAddress = new Address();
//		receiverAddress.setAdditional("additional");
		receiverAddress.setCity("city");
		receiverAddress.setCountry("DE");
		receiverAddress.setHouseNo("houseNo");
		receiverAddress.setStreet("street");
		receiverAddress.setZip("123456");
		receiverNamedAddress.setAddress(receiverAddress);
		addressBinding.setReceiver(receiverNamedAddress);

		position.setAddress(addressBinding);
		position.setAdditionalInfo("additionalInfo");
		position.setVoucherLayout(VoucherLayout.AddressZone);
		
		VoucherPosition voucherPosition  = new VoucherPosition();
		voucherPosition.setLabelX(1);
		voucherPosition.setLabelY(1);
		voucherPosition.setPage(1);
		position.setPosition(voucherPosition);
		
		positions.add(position);
		request.setPositions(positions.toArray(new ShoppingCartPDFPosition[0]));


		request.setTotal(60);

		int[] pageFormatIds = getPageFormatIds();
//		int[] pageFormatIds = new int[]{2};
		for(int pfId:pageFormatIds){
			try{
				request.setPageFormatId(pfId);
				ShoppingCartResponseType response = port.checkoutShoppingCartPDF(request);
				String url = response.getLink();
				
				GetMethod  method = new GetMethod(url);
				httpClient.executeMethod(method);
				if(HttpStatus.SC_OK!=method.getStatusCode()){
					throw new  LabelBusinessException(method.getStatusText());
				}
				String filename="D:\\work\\MyWork\\DE\\deutschepost-2\\temp\\PageFormatId_"+pfId+".pdf";
				byte[] datas = method.getResponseBody();
				FileOutputStream fos = new FileOutputStream(filename);
				fos.write(datas);
				fos.close();
				System.out.println(ToStringBuilder.reflectionToString(response));
			}catch (IOException ex) {
				System.out.println(ExceptionUtils.getFullStackTrace(ex));
			}
		}
	}

	
	
	
	public ShoppingCartResponseType checkoutShoppingCartPNG(ShoppingCartPNGRequestType request) throws HttpException, IOException {
		request.setUserToken(getUserToken());
		ShoppingCartResponseType response = port.checkoutShoppingCartPNG(request);
		
		return response;
		
	}


	
	
	public AuthenticateUserResponseType authenticateUser(
			AuthenticateUserRequestType parameter) throws RemoteException,
			AuthenticateUserException {
		// TODO Auto-generated method stub
		return null;
	}

	public RetrievePreviewVoucherResponseType retrievePreviewVoucherPDF(
			RetrievePreviewVoucherPDFRequestType parameter)
			throws RemoteException, InvalidProductException,
			InvalidPageFormatException, InvalidMotiveException {
		// TODO Auto-generated method stub
		return null;
	}

	public RetrievePreviewVoucherResponseType retrievePreviewVoucherPNG(
			RetrievePreviewVoucherPNGRequestType parameter)
			throws RemoteException, InvalidProductException,
			InvalidMotiveException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Test
	public void retrievePrivateGallery(
			)
			throws RemoteException, IdentifyException {
		RetrievePrivateGalleryRequestType request = new RetrievePrivateGalleryRequestType();
		request.setUserToken(getUserToken());
		MotiveLink[] items = port.retrievePrivateGallery(request);
		for(MotiveLink item:items){
			System.out.println(ToStringBuilder.reflectionToString(item));
		}
		// TODO Auto-generated method stub
//		return null;
	}

	public RetrieveOrderResponseType retrieveOrder(
			RetrieveOrderRequestType parameter) throws RemoteException,
			IdentifyException, RetrieveOrderException {
		// TODO Auto-generated method stub
		return null;
	}

	public CreateShopOrderIdResponse createShopOrderId(
			CreateShopOrderIdRequest createShopOrderIdRequest)
			throws RemoteException, IdentifyException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Test
	public void retrievePageFormats() throws RemoteException {
		RetrievePageFormatsRequestType request = new RetrievePageFormatsRequestType();
		setHeader((OneClickForAppBindingV2Stub) port);
		PageFormat[] pageFormats = port.retrievePageFormats(request);
		for (PageFormat f : pageFormats) {
			System.out.println(ToStringBuilder.reflectionToString(f));
		}
	}
	
	private OneClickForAppPortTypeV2 getHeaderSetPort(){
		setHeader((OneClickForAppBindingV2Stub) port);
		return port;
	}
	
	private int[] getPageFormatIds(){
		int ids[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31,
				32, 33, 35, 37, 39, 40, 41, 42, 44, 49, 51, 52, 53, 54, 55, 56,
				57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72,
				73, 74, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
				90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103,
				104, 105, 106, 107, 108, 109, 112, 113, 114, 115, 116, 136 };
		return ids;
	}

	public String getShipLableCode(Map<String, Object> map, Properties ctx,
			String documentNo, String trxName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}

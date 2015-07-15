package com.winit.label.manager.impl.de.deutschepost.oneclickforapp;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.apache.axis.AxisProperties;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.LabelHandler;
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
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

/**
 * Deutsche Post 1click4app 面单
 * @author longsheng.wang
 *
 */
@Component("deutschePostLabelHandler_1click4app")
public class DeutschePostLabelHandler implements LabelHandler {
	private OneClickForAppPortTypeV2Wrapper port;
	private static HttpClient s_httpClient;


	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception{
		port = OneClickForAppPortTypeV2Wrapper.getInstance();
		ShoppingCartPNGRequestType shoppingCartPNGRequest = buildShoppingCartPNGRequest(requestMessage,  deliveryWay);
		ShoppingCartResponseType response = port.checkoutShoppingCartPNG(shoppingCartPNGRequest);
		String code = handleResponse(response);
		return new Result(code,null);
	}

	/**
	 * 获取HttpClient对象实例
	 * @return
	 */
	private static HttpClient getHttpClient() {
		if (s_httpClient == null) {
			s_httpClient = new HttpClient();
			s_httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(60 * 1000);
			s_httpClient.getHttpConnectionManager().getParams()
					.setSoTimeout(60 * 1000);

		}
		return s_httpClient;
	}

	/**
	 * 解析返回报文
	 * @param response
	 * @return 面单的Base64编码
	 * @throws HttpException
	 * @throws IOException
	 */
	private String handleResponse(ShoppingCartResponseType response) throws HttpException, IOException{
		
		String url = response.getLink();
		GetMethod  method = new GetMethod(url);
		getHttpClient().executeMethod(method);
		if(HttpStatus.SC_OK!=method.getStatusCode()){
			throw new  LabelBusinessException(method.getStatusText());
		}
		ZipInputStream zis=new ZipInputStream(method.getResponseBodyAsStream());
		
	   ZipEntry entry; 
	   String code = null;
       while((entry = zis.getNextEntry())!=null && !entry.isDirectory()){  
    	  if("png".equalsIgnoreCase(FilenameUtils.getExtension(entry.getName()))){
    		  byte[] datas = IOUtils.toByteArray(zis);  
    		  code = new String(Base64.encodeBase64(datas));
    	  }
       }
       return code;
	}


	public ShoppingCartPNGRequestType buildShoppingCartPNGRequest(RequestMessage requestMessage, DeliveryWay deliveryWay) {
		
		///检查数据合法性（长度、是否为空等）。
		String msgTmep = "[{0}] :Value [{1}] with length [{2}], exceeds maximum length facet of [{3}]";
		String msgNullTemp = "Field [{0}] is required.";
		
		
		
		if (requestMessage.getConsignee().getName() == null){
			throw new LabelBusinessException( MessageFormat.format(msgNullTemp,"Receiver Name"));
		}
		checkLength(requestMessage.getConsignee().getName(), 50, MessageFormat.format(
				msgTmep, "Receiver Name", requestMessage.getConsignee().getName(),
				requestMessage.getConsignee().getName().length(), 50));
			

		if (requestMessage.getConsignee().getCountryCode() == null){
			throw new LabelBusinessException( MessageFormat.format(msgNullTemp,"Receiver Country Code"));
		}
			checkLength(requestMessage.getConsignee().getCountryCode(), 3, MessageFormat.format(
					msgTmep, "Receiver Country Code", requestMessage.getConsignee()
							.getCountryCode(), requestMessage.getConsignee().getCountryCode()
							.length(), 3));

		List<String> addr23 = new ArrayList<String>();
		String additional = null;
		String street = null;
		if (StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress2())) {
			addr23.add(requestMessage.getConsignee().getAddress2());
		}
		if (StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress3())) {
			addr23.add(requestMessage.getConsignee().getAddress3());
		}

		if (addr23.size() > 0) {
			street = StringUtils.join(addr23.iterator(), " ");
			additional = requestMessage.getConsignee().getAddress1();
		} else {
			street = requestMessage.getConsignee().getAddress1();
		}

		if (street  == null){
			throw new LabelBusinessException( MessageFormat.format(msgNullTemp,"Receiver Address1"));
		}
		checkLength(street, 50, MessageFormat.format(msgTmep,
				"Receiver Address1/Address2+Address3", street,
				street.length(), 50));
	
		if (additional != null)
			checkLength(additional, 50, MessageFormat.format(msgTmep,
					"Receiver Address1/Address2+Address3", additional,
					additional.length(), 50));

		if (requestMessage.getConsignee().getHouseNo() != null)
			checkLength(requestMessage.getConsignee().getHouseNo(), 10,
					MessageFormat.format(msgTmep, "Receiver House No.",
							requestMessage.getConsignee().getHouseNo(), requestMessage.getConsignee()
									.getHouseNo().length(), 10));
		if (requestMessage.getConsignee().getPostcode()  == null){
			throw new LabelBusinessException( MessageFormat.format(msgNullTemp,"Receiver Postal"));
		}
		
		
		/// //

		ShoppingCartPNGRequestType request = new ShoppingCartPNGRequestType();

		request.setPpl(getPPL(deliveryWay));

		List<ShoppingCartPosition> positions = new ArrayList<ShoppingCartPosition>();
		ShoppingCartPosition position = new ShoppingCartPosition();
		position.setProductCode(getProducyCode(deliveryWay));
		AddressBinding addressBinding = new AddressBinding();

		// /sender address
		NamedAddress senderNamedAddress = new NamedAddress();
		Name senderName = new Name();
		CompanyName senderCompanyName = new CompanyName();
		senderCompanyName.setCompany(DeutschePostConfig.SENDER_COMPANY());
		// PersonName senderPersonName = new PersonName();
		// senderPersonName.setFirstname("firstname");
		// senderPersonName.setLastname("lastname");
		// senderPersonName.setSalutation("salutation");
		// senderPersonName.setTitle("title");
		// senderCompanyName.setPersonName(senderPersonName);
		senderName.setCompanyName(senderCompanyName);
		senderNamedAddress.setName(senderName);
		Address senderAddress = new Address();
		// senderAddress.setAdditional("additional");
		senderAddress.setCity(DeutschePostConfig.SENDER_CITY());
		senderAddress.setCountry(DeutschePostConfig.SENDER_COUNTRY());
		senderAddress.setHouseNo(DeutschePostConfig.SENDER_HOUSE_NO());
		senderAddress.setStreet(DeutschePostConfig.SENDER_STREET());
		senderAddress.setZip(DeutschePostConfig.SENDER_ZIP());
		senderNamedAddress.setAddress(senderAddress);
		addressBinding.setSender(senderNamedAddress);

		// /receiver address
		NamedAddress receiverNamedAddress = new NamedAddress();
		Name receiverName = new Name();
		CompanyName receiverCompanyName = new CompanyName();
		receiverCompanyName.setCompany(requestMessage.getConsignee().getName());
		receiverName.setCompanyName(receiverCompanyName);
		// PersonName receiverPersonName = new PersonName();
		// receiverPersonName.setFirstname("firstname");
		// receiverPersonName.setLastname(".");
		// receiverPersonName.setSalutation("salutation");
		// receiverPersonName.setTitle("title");
		// receiverName.setPersonName(receiverPersonName);
		receiverNamedAddress.setName(receiverName);
		Address receiverAddress = new Address();
		receiverAddress.setAdditional(additional);
		receiverAddress.setCity(requestMessage.getConsignee().getCity());
		receiverAddress.setCountry(iso2CountryCodeToIso3CountryCode(requestMessage.getConsignee()
				.getCountryCode()));
		receiverAddress.setHouseNo(StringUtils.isEmpty(requestMessage.getConsignee()
				.getHouseNo()) ? DeutschePostConfig.DEFAULT_HOUSE_NO()
				: requestMessage.getConsignee().getHouseNo());
		receiverAddress.setStreet(street);
		receiverAddress.setZip(requestMessage.getConsignee().getPostcode());
		receiverNamedAddress.setAddress(receiverAddress);
		addressBinding.setReceiver(receiverNamedAddress);

		position.setAddress(addressBinding);
		// TODO DOCUMENTNO
		position.setAdditionalInfo(requestMessage.getDocumentNo());
		position.setVoucherLayout(VoucherLayout.AddressZone);
		positions.add(position);
		request.setPositions(positions.toArray(new ShoppingCartPosition[0]));

		request.setTotal(getTotalValue(deliveryWay));

		return request;
	}

	/**
	 * 获取PPL
	 * @param deliveryWay 
	 * 
	 * @param warehouse
	 * @return
	 */
	private static Integer getPPL(DeliveryWay deliveryWay) {
		//TODO
		return Integer.valueOf(1);
	}
	/**
	 * 获取PPL
	 * 
	 * @param warehouse
	 * @return
	 */
	private static Integer getProducyCode(DeliveryWay deliveryWay) {
		//TODO
		return Integer.valueOf(1);
	}

	/**
	 * 获取Total Value
	 * 
	 * @param warehouse
	 * @return
	 */
	private static Integer getTotalValue(DeliveryWay deliveryWay) {
		//TODO
		return Integer.valueOf(60);
	}

	private void checkLength(String str, int len, String msg) {
		if (str != null) {
			if (str.length() > len)
				throw new LabelBusinessException(msg);
		}
	}

	private String iso2CountryCodeToIso3CountryCode(String iso2CountryCode) {
		Locale locale = new Locale("", iso2CountryCode);
		return locale.getISO3Country();
	}


	@Override
	public boolean isIdempotent() {
		return false;
	}
}

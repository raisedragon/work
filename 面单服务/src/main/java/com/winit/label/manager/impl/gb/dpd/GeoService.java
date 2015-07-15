package com.winit.label.manager.impl.gb.dpd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.impl.gb.dpd.model.BaseOutput;
import com.winit.label.manager.impl.gb.dpd.model.CreateManifestOutput;
import com.winit.label.manager.impl.gb.dpd.model.CreateManifestParameters;
import com.winit.label.manager.impl.gb.dpd.model.DeleteShipmentOutput;
import com.winit.label.manager.impl.gb.dpd.model.Error;
import com.winit.label.manager.impl.gb.dpd.model.GetLabelsOutput;
import com.winit.label.manager.impl.gb.dpd.model.GetManifestByIdOutput;
import com.winit.label.manager.impl.gb.dpd.model.GetServiceOutput;
import com.winit.label.manager.impl.gb.dpd.model.GetShipmentOutput;
import com.winit.label.manager.impl.gb.dpd.model.InsertShipmentOutput;
import com.winit.label.manager.impl.gb.dpd.model.InsertShipmentParameters;
import com.winit.label.manager.impl.gb.dpd.model.ListOfServiceParameters;
import com.winit.label.manager.impl.gb.dpd.model.ListOfServicesOutput;
import com.winit.label.manager.impl.gb.dpd.model.LoginOutput;
import com.winit.label.manager.impl.gb.dpd.model.ObjectFactory;
import com.winit.label.manager.impl.gb.dpd.model.Trackingrequest;
import com.winit.label.manager.impl.gb.dpd.model.Trackingresponse;


public class GeoService
{
	private Logger log = LoggerFactory.getLogger(GeoService.class);

	private HttpClient client;
	
	private static GeoService instance;
	
	private static String geoSession;
	
	private static Marshaller marshaller;
	
	private static Unmarshaller unmarshaller;
	
	private GeoService()
	{
		if(client==null){
			client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
			client.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		}
	}
	
	public synchronized static GeoService getInstance() throws JAXBException{
		if(instance==null){
			instance = new GeoService();
		}
		JAXBContext ctx = JAXBContext.newInstance(ObjectFactory.class,Trackingresponse.class);
		marshaller = ctx.createMarshaller();
		unmarshaller = ctx.createUnmarshaller();
		return instance;
	}
		
	
	public LoginOutput login(String username,String password) throws HttpException, IOException{
		String auth = username+":"+ password;
		String authEnc =new  String(Base64.encodeBase64(auth.getBytes()));
		String url =DpdConfig.HOST()+ "/user/?action=login";
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Authorization", "Basic "+authEnc);
		method.setRequestHeader("Content-Type", "application/json");
		method.setRequestHeader("Accept", "application/json");
		method.setRequestHeader("GEOClient", "account/"+username);

		int statusCode = client.executeMethod(method);
		checkStatus(statusCode,method);
		
		return parseResonse(method, LoginOutput.class);
	}
	
	protected <T  extends BaseOutput> T parseResonse(HttpMethod method,Class<T> clazz){
		try{
			String responseStr = method.getResponseBodyAsString();
			log.info(responseStr);
			JSONObject json = JSONObject.parseObject(responseStr);
			T t = JSONObject.parseObject(responseStr,clazz);
			Object obj  = json.get("error");
			
			Error[] errors = null;
			if(obj!=null){
				if(obj instanceof JSONObject){
					Error error = JSONObject.toJavaObject((JSONObject)obj, Error.class);
					errors = new Error[]{error};
				}else if(obj instanceof JSONArray){
					List<Error> es = new ArrayList<Error>();
					for(int i = 0; i < ((JSONArray)obj).size();i++){
						JSONObject jobj = (JSONObject)((JSONArray)obj).get(i);
						Error e = JSONObject.toJavaObject(jobj,Error.class);
						es.add(e);
					}
					errors = (Error[]) es.toArray(new Error[0]);
				}
			}
			t.setErrors(errors);
			return t;
		}catch(Exception e){
			throw new LabelBusinessException(e);
		}
	}
	
	public InsertShipmentOutput insertShipment(InsertShipmentParameters parameters) throws HttpException, IOException{
		String param = JSONObject.toJSONString(parameters,SerializerFeature.WriteMapNullValue);
		log.info(param);
		String url = DpdConfig.HOST() + "/shipping/shipment/";
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type", "application/json");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		RequestEntity requestEntity = new StringRequestEntity(param);
		method.setRequestEntity(requestEntity);
		
		int statusCode = client.executeMethod(method);
		checkStatus(statusCode,method);
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		InsertShipmentOutput output = parseResonse(method, InsertShipmentOutput.class);
		
		if(isSessionExpired(output.getErrors())){
			output = parseResonse(method, InsertShipmentOutput.class);
		}
		return output;
		
	}
	
	public ListOfServicesOutput listOfServices(ListOfServiceParameters parameters) throws HttpException, IOException{
		List<String> params = new ArrayList<String>();
		if(parameters.getCollectionDetails()!=null){
			if(parameters.getCollectionDetails().getAddress()!=null){
				if(StringUtils.isNotEmpty(parameters.getCollectionDetails().getAddress().getLocality())){
					params.add("collectionDetails.address.locality="+URLEncoder.encode(parameters.getCollectionDetails().getAddress().getLocality(),"UTF-8"));
				}
				
				if(StringUtils.isNotEmpty(parameters.getCollectionDetails().getAddress().getCounty())){
					params.add("collectionDetails.address.county="+URLEncoder.encode(parameters.getCollectionDetails().getAddress().getCounty(),"UTF-8"));
				}
				
				if(StringUtils.isNotEmpty(parameters.getCollectionDetails().getAddress().getPostcode())){
					params.add("collectionDetails.address.postcode="+URLEncoder.encode(parameters.getCollectionDetails().getAddress().getPostcode(),"UTF-8"));
				}
				
				if(StringUtils.isNotEmpty(parameters.getCollectionDetails().getAddress().getCountryCode())){
					params.add("collectionDetails.address.countryCode="+URLEncoder.encode(parameters.getCollectionDetails().getAddress().getCountryCode(),"UTF-8"));
				}
				
			}
			if(parameters.getDeliveryDetails().getAddress()!=null){
				if(StringUtils.isNotEmpty(parameters.getDeliveryDetails().getAddress().getLocality())){
					params.add("deliveryDetails.address.locality="+URLEncoder.encode(parameters.getDeliveryDetails().getAddress().getLocality(),"UTF-8"));
				}
				
				if(StringUtils.isNotEmpty(parameters.getDeliveryDetails().getAddress().getCounty())){
					params.add("deliveryDetails.address.county="+URLEncoder.encode(parameters.getDeliveryDetails().getAddress().getCounty(),"UTF-8"));
				}
				
				if(StringUtils.isNotEmpty(parameters.getDeliveryDetails().getAddress().getPostcode())){
					params.add("deliveryDetails.address.postcode="+URLEncoder.encode(parameters.getDeliveryDetails().getAddress().getPostcode(),"UTF-8"));
				}
				
				if(StringUtils.isNotEmpty(parameters.getDeliveryDetails().getAddress().getCountryCode())){
					params.add("deliveryDetails.address.countryCode="+URLEncoder.encode(parameters.getDeliveryDetails().getAddress().getCountryCode(),"UTF-8"));
				}
				
			}
			if(parameters.getBusinessUnit()!=null){
				params.add("businessUnit="+URLEncoder.encode(parameters.getBusinessUnit()+"","UTF-8"));
			}
			if(parameters.getDeliveryDirection()!=null){
				params.add("deliveryDirection="+URLEncoder.encode(parameters.getDeliveryDirection()+"","UTF-8"));
			}
			if(parameters.getNumberOfParcels()!=null){
				params.add("numberOfParcels="+URLEncoder.encode(parameters.getNumberOfParcels()+"","UTF-8"));
			}
			if(parameters.getShipmentType()!=null){
				params.add("shipmentType="+URLEncoder.encode(parameters.getShipmentType()+"","UTF-8"));
			}
			if(parameters.getTotalWeight()!=null){
				params.add("totalWeight="+URLEncoder.encode(parameters.getTotalWeight()+"","UTF-8"));
			}
		}
		String paramStr = StringUtils.join(params.iterator(), "&");
		String url = DpdConfig.HOST() + "/shipping/network/";
		log.info(url);
		GetMethod method = new GetMethod(url+"?"+paramStr);
		method.setRequestHeader("Content-Type", "application/json");
		method.setRequestHeader("Accept", "application/json");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		int statusCode = client.executeMethod(method);
		
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
		ListOfServicesOutput output = parseResonse(method, ListOfServicesOutput.class);
		if(isSessionExpired(output.getErrors())){
			output = parseResonse(method, ListOfServicesOutput.class);
		}
		return output;
	}
	
	
	private boolean isSessionExpired(Error[] error){
		if(error==null||error.length==0){
			return false;
		}
		for(Error err:error){
			if("403".equals(err.getErrorCode())){
				return true;
			}
		}
		return false;
	}
	
	
	private String getGEOSession() throws HttpException, IOException
	{
		return getGEOSession(false);
	}
	
	private String getGEOSession(boolean newOne) throws HttpException, IOException
	{
		if(newOne||StringUtils.isEmpty(geoSession)){
			LoginOutput loginOutput = login(DpdConfig.USER_NAME(), DpdConfig.PASSWORD());
			if(loginOutput.getErrors()!=null&&loginOutput.getErrors().length>0){
				String errMsg = "";
				for(Error err:loginOutput.getErrors()){
					errMsg += err.getErrorMessage();
				}
				LabelBusinessException ex =  new LabelBusinessException(errMsg);
				throw ex;
			}
			
			geoSession = loginOutput.getData().getGeoSession();
		}
		return geoSession;
	}

	public GetLabelsOutput getLabels(long shipmentId) throws HttpException, IOException{

//		/shipping/shipment/89088/label/
		String url = DpdConfig.HOST() + "/shipping/shipment/"+shipmentId+"/label/";
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("Accept", "text/vnd.eltron-epl");
//		method.setRequestHeader("Accept", "image/jpeg");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		int statusCode = client.executeMethod(method);

		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
		GetLabelsOutput output = null;
		try{
			output = parseResonse(method, GetLabelsOutput.class);		
		}catch(Exception e){
			
		}
		if(output!=null){
			if(isSessionExpired(output.getErrors())){
				method.setRequestHeader("GEOSession", this.getGEOSession(true));
				output = parseResonse(method, GetLabelsOutput.class);
			}
		}else{
			output = new GetLabelsOutput();
			output.setPrintString(method.getResponseBodyAsString().trim());
		}
		return output;
//		return parseResonse(method, GetLabelsOutput.class);
		
	}
	
	public GetShipmentOutput getShipment(long shipmentId) throws HttpException, IOException{
		String url = DpdConfig.HOST() + "/shipping/shipment/"+shipmentId+"/";
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("Accept", "application/json");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		int statusCode = client.executeMethod(method);
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
//		return parseResonse(method, GetShipmentOutput.class);
		GetShipmentOutput output = parseResonse(method, GetShipmentOutput.class);
		if(isSessionExpired(output.getErrors())){
			method.setRequestHeader("GEOSession", this.getGEOSession(true));
			output = parseResonse(method, GetShipmentOutput.class);
		}
		return output;
	}
	
	public DeleteShipmentOutput deleteShipment(long shipmentId) throws HttpException, IOException{
		String url = DpdConfig.HOST() + "/shipping/shipment/"+shipmentId+"/";
		DeleteMethod method = new DeleteMethod(url);
		method.setRequestHeader("Accept", "application/json");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		int statusCode = client.executeMethod(method);
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
//		return parseResonse(method, DeleteShipmentOutput.class);
		
		DeleteShipmentOutput output = parseResonse(method, DeleteShipmentOutput.class);
		if(isSessionExpired(output.getErrors())){
			output = parseResonse(method, DeleteShipmentOutput.class);
		}
		return output;
	}
	
	
	public GetServiceOutput getService(String serviceCode) throws HttpException, IOException{
		String url = DpdConfig.HOST() + "/shipping/network/"+serviceCode+"/";
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("Accept", "application/json");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		int statusCode = client.executeMethod(method);
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
		
		GetServiceOutput output = parseResonse(method, GetServiceOutput.class);
		if(isSessionExpired(output.getErrors())){
			output = parseResonse(method, GetServiceOutput.class);
		}
		return output;
	}
	
	
	public CreateManifestOutput createManifest(CreateManifestParameters parameters) throws HttpException, IOException{
		String param = JSONObject.toJSONString(parameters,SerializerFeature.WriteMapNullValue);
		log.info(param);
		
		String url = DpdConfig.HOST() + "/shipping/manifest/";
		PostMethod method = new PostMethod(url);
		
		method.setRequestHeader("Accept", "application/json");
		method.setRequestHeader("Content-Type", "application/json");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		RequestEntity requestEntity = new StringRequestEntity(param);
		method.setRequestEntity(requestEntity);
		
		int statusCode = client.executeMethod(method);
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
		CreateManifestOutput output = parseResonse(method, CreateManifestOutput.class);
		if(isSessionExpired(output.getErrors())){
			output = parseResonse(method, CreateManifestOutput.class);
		}
		return output;
	}
	
	
	public GetManifestByIdOutput getManifestById(long manifestId) throws HttpException, IOException{
		String url = DpdConfig.HOST() + "/shipping/manifest/"+manifestId;
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("Accept", "text/html");
		method.setRequestHeader("GEOClient", "account/"+DpdConfig.USER_NAME());
		method.setRequestHeader("GEOSession", this.getGEOSession());
		
		int statusCode = client.executeMethod(method);
		
		if(statusCode==HttpStatus.SC_UNAUTHORIZED){
			method.setRequestHeader("GEOSession",this.getGEOSession(true));
			statusCode = client.executeMethod(method);
		}
		
		checkStatus(statusCode,method);
		
		GetManifestByIdOutput output = new GetManifestByIdOutput();
		output.setPrintString(method.getResponseBodyAsString());
		return output;
	}
	
	private void checkStatus(int statusCode,HttpMethod method) throws URIException{
		if (statusCode != HttpStatus.SC_OK){
			log.error(method.getStatusLine().toString());
			throw new LabelBusinessException("Request failed:" + method.getStatusLine());
		}
	}
	
	
	public Trackingresponse tracking(Trackingrequest request) throws HttpException, IOException, JAXBException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		marshaller.marshal(request, os);
		String requestStr = new String(os.toByteArray());
		log.info(requestStr);
		String url = DpdConfig.TRACKING_HOST();
		PostMethod method = new PostMethod(url);
		
		method.setRequestHeader("Accept", "application/xml");
		method.setRequestHeader("Content-Type", "application/xml");
		
		RequestEntity requestEntity = new StringRequestEntity(requestStr);
		method.setRequestEntity(requestEntity);
		
		int statusCode = client.executeMethod(method);
		checkStatus(statusCode,method);
		log.info(method.getResponseBodyAsString());
		Trackingresponse trackingresponse = (Trackingresponse) unmarshaller.unmarshal(method.getResponseBodyAsStream());
//		TODO
//		Trackingresponse trackingresponse = JSONObject.parseObject(method.getResponseBodyAsString(), Trackingresponse.class);
		return trackingresponse;
//		return output;
	}
}

package com.winit.label.manager.impl.us.ups;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.winit.label.manager.impl.us.ups.model.accept.accessrequest.AccessRequest;
import com.winit.label.manager.impl.us.ups.model.accept.accessrequest.ObjectFactory;
import com.winit.label.manager.impl.us.ups.model.accept.request.RequestType;
import com.winit.label.manager.impl.us.ups.model.accept.request.ShipmentAcceptRequest;
import com.winit.label.manager.impl.us.ups.model.accept.request.TransactionReferenceType;
import com.winit.label.manager.impl.us.ups.model.accept.response.ShipmentAcceptResponse;
import com.winit.label.support.ConfigUtil;


@Component("jaxbShipAcceptClient")
public class JaxbShipAcceptClient {
	protected static final Logger logger = Logger.getLogger(JaxbShipAcceptClient.class);
	
	public Map<String,Object> getBaseCode(String digest,String documentNo) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		StringWriter strWriter = null;
        try {

        	//Create JAXBContext and marshaller for AccessRequest object
        	JAXBContext accessRequestJAXBC = JAXBContext.newInstance(AccessRequest.class.getPackage().getName() );
			Marshaller accessRequestMarshaller = accessRequestJAXBC.createMarshaller();
			ObjectFactory accessRequestObjectFactory = new ObjectFactory();
			AccessRequest accessRequest = accessRequestObjectFactory.createAccessRequest();
			populateAccessRequest(accessRequest);


			JAXBContext shipAcceptRequestJAXBC = JAXBContext.newInstance(ShipmentAcceptRequest.class.getPackage().getName() );


			Marshaller shipAcceptRequestMarshaller = shipAcceptRequestJAXBC.createMarshaller();
			com.winit.label.manager.impl.us.ups.model.accept.request.ObjectFactory requestObjectFactory = new com.winit.label.manager.impl.us.ups.model.accept.request.ObjectFactory();
			ShipmentAcceptRequest shipAcceptRequest = requestObjectFactory.createShipmentAcceptRequest();
			populateShipAcceptRequest(shipAcceptRequest,digest);


			strWriter = new StringWriter();
			accessRequestMarshaller.marshal(accessRequest, strWriter);
			shipAcceptRequestMarshaller.marshal(shipAcceptRequest, strWriter);
			strWriter.flush();
			strWriter.close();
//			System.out.println("Request: " + strWriter.getBuffer().toString());
			logger.info(strWriter.getBuffer().toString());
			String strResults =contactService(strWriter.getBuffer().toString());
			logger.info(strResults);
			//Parse response object
			JAXBContext shipAcceptJAXBC = JAXBContext.newInstance(ShipmentAcceptResponse.class.getPackage().getName());
			Unmarshaller shipAceeptUnmarshaller = shipAcceptJAXBC.createUnmarshaller();
			ByteArrayInputStream input = new ByteArrayInputStream(strResults.getBytes());
			Object objResponse = shipAceeptUnmarshaller.unmarshal(input);
			ShipmentAcceptResponse shipAcceptResponse = (ShipmentAcceptResponse)objResponse;
			if ("1".equals(shipAcceptResponse.getResponse().getResponseStatusCode()))
			{
				map.put("trackNo", shipAcceptResponse.getShipmentResults().getPackageResults().get(0).getTrackingNumber());
				map.put("fileCode",shipAcceptResponse.getShipmentResults().getPackageResults().get(0).getLabelImage()
						.getGraphicImage());
			}

        } catch (Exception e) {
        	throw new Exception("error");
		} finally{
			try{
				if(strWriter != null){
					strWriter.close();
					strWriter = null;
				}
			}catch (Exception e) {
				throw new Exception("error");
			}
		}
		return map;
    }

	public  String contactService(String xmlInputString) throws Exception{
		String outputStr = null;
		OutputStream outputStream = null;
		try {

			URL url = new URL(ConfigUtil.getValue("WT_LABLE_API_ENDPOINT_URL")+ConfigUtil.getValue("WT_LABLE_API_ENDPOINT_URL_ACCEPT_PATH"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			System.out.println("Client established connection with " + url.toString());
			// Setup HTTP POST parameters
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);

			outputStream = connection.getOutputStream();
			outputStream.write(xmlInputString.getBytes());
			outputStream.flush();
			outputStream.close();
//			System.out.println("Http status = " + connection.getResponseCode() + " " + connection.getResponseMessage());

			outputStr = readURLConnection(connection);
//			System.out.println(outputStr);
		} catch (Exception e) {
//			System.out.println("Error sending data to server");
			
			throw e;
		} finally {
			if(outputStream != null){
				outputStream.close();
				outputStream = null;
			}
		}
		return outputStr;
	}

	/**
	 * This method read all of the data from a URL connection to a String
	 */

	public  String readURLConnection(URLConnection uc) throws Exception {
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			int letter = 0;
			reader.readLine();
			while ((letter = reader.read()) != -1){
				buffer.append((char) letter);
			}
			reader.close();
		} catch (Exception e) {
//			System.out.println("Could not read from URL: " + e.toString());
			throw e;
		} finally {
			if(reader != null){
				reader.close();
				reader = null;
			}
		}
		return buffer.toString();
	}

    /**
     * Populates the access request object.
     * @param accessRequest
     */
	public  void populateAccessRequest(AccessRequest accessRequest){
    	accessRequest.setAccessLicenseNumber(ConfigUtil.getValue("WT_LABLE_API_LICENSE_NUMBER"));
    	accessRequest.setUserId(ConfigUtil.getValue("WT_LABLE_API_USER_NAME"));
    	accessRequest.setPassword(ConfigUtil.getValue("WT_LABLE_API_PASSWORD"));
    }

  
	public  void populateShipAcceptRequest(ShipmentAcceptRequest shipRequest,String digest) {
		RequestType request = new RequestType();
		TransactionReferenceType transactionReference = new TransactionReferenceType();
		transactionReference.setCustomerContext("JAXB Test Client");
		request.setTransactionReference(transactionReference);
		request.setRequestAction("01");
		shipRequest.setRequest(request);
		shipRequest.setShipmentDigest(digest);
	}
}

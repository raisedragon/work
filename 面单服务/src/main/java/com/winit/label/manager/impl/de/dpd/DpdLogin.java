package com.winit.label.manager.impl.de.dpd;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


import com.winit.commons.http.HttpClientUtil;
import com.winit.label.manager.impl.de.dpd.login.request.Login;
import com.winit.label.manager.impl.de.dpd.login.response.AuthenticationFault;

/**
 * 调用DPD Login API
 * @author temuser2
 *
 */
public class DpdLogin
{
	
	public static String	AUTH	= "LTU3MzQ5NTE2ODE3Nzk5NjY4MTIRMTM5ODQwNTgyMzQ3MARR";
	public static String	DEPOT	= "0128";

	/**
	 * 调用login接口
	 * @throws Exception
	 */
	public void login() throws Exception {
		String request = getLoginSoap();
		String response = HttpClientUtil.post(DpdConfig.WT_DPD_LOGIN_API_URL(), request.getBytes("utf-8"),"text/xml;charset=UTF-8",null);
		Object obj = parseResponse(response);
		if(obj instanceof Login) {
			Login login = (Login) obj;
			AUTH = login.getAuthToken();
			DEPOT = login.getDepot();
		} else {
			AuthenticationFault fault = (AuthenticationFault) obj;
			String msg = fault.getErrorMessage();
			throw new RuntimeException(msg);
		}
	}
	
	/**
	 * 获取login报文
	 * @return
	 */
	private String getLoginSoap() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<soapenv:Envelope");
			buffer.append(" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"");
			buffer.append(" xmlns:ns=\"http://dpd.com/common/service/types/LoginService/2.0\">");
			buffer.append("<soapenv:Header/>");
			buffer.append("<soapenv:Body>");
				buffer.append("<ns:getAuth>");
					buffer.append("<delisId>").append(DpdConfig.WT_DPD_USERNAME()).append("</delisId>");
					buffer.append("<password>").append(DpdConfig.WT_DPD_PASSWORD()).append("</password>");
					buffer.append("<messageLanguage>en_EN</messageLanguage>");
				buffer.append("</ns:getAuth>");
			buffer.append("</soapenv:Body>");
		buffer.append("</soapenv:Envelope>");
		
		return buffer.toString();
	}
	
	/**
	 * 解析回执报文
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	private Object parseResponse(String xml) throws Exception {
		XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(new ByteArrayInputStream(xml.getBytes("utf-8")));
        
        boolean success = true;
        while(xsr.hasNext()) {
        	xsr.next();
        	if(xsr.getEventType() == XMLStreamReader.START_ELEMENT) {
	        	if(xsr.getLocalName().equals("return")) {
	        		success = true;
	        		break;
	        	}
	        	
	        	if(xsr.getLocalName().equals("authenticationFault")) {
	        		success = false;
	        		break;
	        	}
        	}
        }
        
        if(success) {
	        JAXBContext jc = JAXBContext.newInstance(Login.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        JAXBElement<Login> element = unmarshaller.unmarshal(xsr, Login.class);
	        xsr.close();
	
	        return element.getValue();
        } else {
        	JAXBContext jc = JAXBContext.newInstance(AuthenticationFault.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        JAXBElement<AuthenticationFault> element = unmarshaller.unmarshal(xsr, AuthenticationFault.class);
	        xsr.close();
	
	        return element.getValue();
        }
	}
	
}

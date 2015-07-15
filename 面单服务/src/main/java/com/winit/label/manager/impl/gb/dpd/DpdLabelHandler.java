package com.winit.label.manager.impl.gb.dpd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.gb.dpd.model.Address;
import com.winit.label.manager.impl.gb.dpd.model.AddressPoint;
import com.winit.label.manager.impl.gb.dpd.model.AuditInfo;
import com.winit.label.manager.impl.gb.dpd.model.CollectionDetails;
import com.winit.label.manager.impl.gb.dpd.model.Consignment;
import com.winit.label.manager.impl.gb.dpd.model.ContactDetails;
import com.winit.label.manager.impl.gb.dpd.model.DeliveryDetails;
import com.winit.label.manager.impl.gb.dpd.model.Error;
import com.winit.label.manager.impl.gb.dpd.model.GetLabelsOutput;
import com.winit.label.manager.impl.gb.dpd.model.InsertShipmentOutput;
import com.winit.label.manager.impl.gb.dpd.model.InsertShipmentParameters;
import com.winit.label.manager.impl.gb.dpd.model.ListOfServiceData;
import com.winit.label.manager.impl.gb.dpd.model.ListOfServiceParameters;
import com.winit.label.manager.impl.gb.dpd.model.ListOfServicesOutput;
import com.winit.label.manager.impl.gb.dpd.model.NotificationDetails;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.Label;
import com.winit.label.model.LogisticsDist;
import com.winit.label.model.PostcodeGroup;
import com.winit.label.model.RequestMessage;
import com.winit.label.service.LabelService;
import com.winit.label.service.LogisticsDistService;
import com.winit.label.service.PostcodeGroupService;


@Component("GB_dpdLableHandler")
public class DpdLabelHandler implements LabelHandler
{
	
	@Autowired
	private LogisticsDistService logisticsDistService;
	@Autowired
	private PostcodeGroupService postcodeGroupService;
	
	
	@Autowired
	private LabelService labelService;

	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		
		checkLength(requestMessage.getConsignee().getName(), 35, "Reciver name maximum length exceeded, limit 35.");
		checkLength(requestMessage.getConsignee().getCity(), 35, "Reciver city maximum length exceeded, limit 35.");
		checkLength(requestMessage.getConsignee().getState(), 35, "Reciver state maximum length exceeded, limit 35.");
		
		List<String> addr23 = new ArrayList<String>();
		String locality = null;
		String street = null;
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress2())){
			addr23.add(requestMessage.getConsignee().getAddress2());
		}
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress3())){
			addr23.add(requestMessage.getConsignee().getAddress3());
		}
		
		if(addr23.size()>0){
			street = StringUtils.join(addr23.iterator(), " ");
			locality = requestMessage.getConsignee().getAddress1();
		}else{
			street =requestMessage.getConsignee().getAddress1();
		}
		
		checkLength(street, 35, "Reciver street maximum length exceeded, limit 35.");
		checkLength(locality, 35, "Reciver locality maximum length exceeded, limit 35.");
		
		String postCode = requestMessage.getConsignee().getPostcode();
		
		GeoService service = GeoService.getInstance();
		String serviceCode=deliveryWay.getServiceCode();
		//////
		if("^".equals(serviceCode)){
			String distName = "";
			
			String postCodePre = postCode.trim().split("\\s")[0];

			List<LogisticsDist> logisticsDists = logisticsDistService.getByDeliveryWayId(deliveryWay.getId(), null);
					
			out:
			for(LogisticsDist dist:logisticsDists){
				List<PostcodeGroup> postCodeGroups = postcodeGroupService.getByLogisticsDistId(dist.getId(), null);
				for(PostcodeGroup postCodeGroup:postCodeGroups){
					String tPostCode = postCodeGroup.getPostcode();
					if(DpdConfig.LOGISTIC_DIST_ALL_MATCH_POSTAL_GROUP().contains(tPostCode)){
						distName = dist.getName();
						continue;
					}
					if(postCodePre.startsWith(tPostCode)){
						distName = dist.getName();
						break out;
					}
				}
			}
			
			serviceCode = DpdConfig.SERVICE_BY_LOGISTIC_DIST(deliveryWay.getName(), distName);
		}
		
		if(StringUtils.isEmpty(serviceCode)){
			throw new LabelBusinessException("serviceCode is empty");
		}
		
		
		//////
		
		Date currentTime = new Date();
		
//			BigDecimal packageValue = new BigDecimal(0);
		
		//shipment is save as lable.lableValue
		Label label = labelService.findByDocumentNo(requestMessage.getDocumentNo());
		Long shipmentId=null;
		if(label!=null){
			String shipmentIdStr = label.getConsignmentRef2();
			////STEP: DELETE SHIPMENT
			//if shipmentId is not empty, delete shipment 
			if(StringUtils.isNotEmpty(shipmentIdStr)){
				shipmentId = Long.valueOf(shipmentIdStr);
				GetLabelsOutput getLabelsOutput = service.getLabels(shipmentId);
				if(getLabelsOutput.getErrors()!=null){
					if("1004".equals(getLabelsOutput.getErrors()[0].getErrorCode())){
						shipmentId = null;
					}else{
						checkError(getLabelsOutput.getErrors());
					}
				}else{
//					return getLabelsOutput.getPrintString();
					String commands =  getLabelsOutput.getPrintString();
					String base64Code = new String(Base64.encodeBase64(commands.getBytes()));
					return new Result(base64Code, label.getTrackingNo());
				}
				
			}
		}
		
		CollectionDetails collectionDetails = new CollectionDetails();
		
		Address collectionDetailsAddress = new Address();
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_ORGANISATION()))
			collectionDetailsAddress.setOrganisation(DpdConfig.SENDER_ORGANISATION());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_COUNTRY_CODE()))
			collectionDetailsAddress.setCountryCode(DpdConfig.SENDER_COUNTRY_CODE());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_POST_CODE()))
			collectionDetailsAddress.setPostcode(DpdConfig.SENDER_POST_CODE());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_STREET()))
			collectionDetailsAddress.setStreet(DpdConfig.SENDER_STREET());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_LOCALITY()))
			collectionDetailsAddress.setLocality(DpdConfig.SENDER_LOCALITY());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_TOWN()))
			collectionDetailsAddress.setTown(DpdConfig.SENDER_TOWN());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_COUNTY()))
			collectionDetailsAddress.setCounty(DpdConfig.SENDER_COUNTY());
		
		ContactDetails collectionDetailsContactDetails = new ContactDetails();

		if (StringUtils.isNotEmpty(DpdConfig.SENDER_CONTACT_NAME()))
			collectionDetailsContactDetails.setContactName(DpdConfig.SENDER_CONTACT_NAME());
		if (StringUtils.isNotEmpty(DpdConfig.SENDER_TELEPHONE()))
			collectionDetailsContactDetails.setTelephone(DpdConfig.SENDER_TELEPHONE());
		
		collectionDetails.setAddress(collectionDetailsAddress);
		collectionDetails.setContactDetails(collectionDetailsContactDetails);
		/////
		DeliveryDetails deliveryDetails = new DeliveryDetails();
		Address deliveryDetailsAddress = new Address();
		deliveryDetailsAddress.setCountryCode(finalCountryCode(requestMessage.getConsignee().getCountryCode()));
		deliveryDetailsAddress.setCounty(requestMessage.getConsignee().getState());
		deliveryDetailsAddress.setTown(requestMessage.getConsignee().getCity());
		deliveryDetailsAddress.setLocality(locality);
		deliveryDetailsAddress.setStreet(street);
		deliveryDetailsAddress.setPostcode(requestMessage.getConsignee().getPostcode());
		
		
		
		AddressPoint deliveryDetailsAddressPoint = new AddressPoint();
		
		ContactDetails deliveryDetailsContactDetails = new ContactDetails();
		deliveryDetailsContactDetails.setContactName(requestMessage.getConsignee().getName());
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getPhone())){
			deliveryDetailsContactDetails.setTelephone(requestMessage.getConsignee().getPhone());
		}else{
			deliveryDetailsContactDetails.setTelephone("0");
		}
		
		NotificationDetails deliveryDetailsNotificationDetails = new NotificationDetails();
		
		deliveryDetailsNotificationDetails.setEmail(requestMessage.getConsignee().getEmail());
		//deliveryDetailsNotificationDetails.setMobile(warehouse.getPhone());
		
		deliveryDetails.setAddress(deliveryDetailsAddress);
		deliveryDetails.setAddressPoint(deliveryDetailsAddressPoint);
		deliveryDetails.setContactDetails(deliveryDetailsContactDetails);
		deliveryDetails.setNotificationDetails(deliveryDetailsNotificationDetails);
		
		String networkCode = null;
		
		
		////STEP: LIST OF SERVICE
		ListOfServiceParameters listOfServicesParameters = new ListOfServiceParameters();
		{
			listOfServicesParameters.setTotalWeight((float)requestMessage.getWeight());
			listOfServicesParameters.setShipmentType(0);
			listOfServicesParameters.setDeliveryDirection(1);
			listOfServicesParameters.setNumberOfParcels(1);
			listOfServicesParameters.setCollectionDetails(collectionDetails);
			listOfServicesParameters.setDeliveryDetails(deliveryDetails);
		}
		ListOfServicesOutput listOfServicesOutput = service.listOfServices(listOfServicesParameters);
		checkError(listOfServicesOutput.getErrors());
		
		if(listOfServicesOutput.getData()!=null && listOfServicesOutput.getData().length>0){
			for(ListOfServiceData data:listOfServicesOutput.getData()){
				if(serviceCode.equals(data.getNetwork().getNetworkCode())){
					networkCode = data.getNetwork().getNetworkCode();
					break;
				}
			}
		}
		
		if(networkCode==null){
			throw new LabelBusinessException("Logistics service not support");
		}
		
		
		InsertShipmentParameters insertShipmentParameters = new InsertShipmentParameters();
		List<Consignment> consignments = new ArrayList<Consignment>();
		Consignment consignment = new Consignment();
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setCreatedDate(DateFormatUtils.format(currentTime, "yyyy-MM-dd hh:mm:ss").replaceAll(" ", "T"));
		auditInfo.setUpdatedDate(DateFormatUtils.format(currentTime, "yyyy-MM-dd hh:mm:ss").replaceAll(" ", "T"));
		
		consignment.setAuditInfo(auditInfo);
		consignment.setCollectionDetails(collectionDetails);
//			consignment.setCustomsValue(packageValue.floatValue());
		consignment.setDeliveryDetails(deliveryDetails);
		consignment.setLiability(false);
		consignment.setNetworkCode(networkCode);
		consignment.setNumberOfParcels(1);
//		consignment.setParcel(parcel);
//		consignment.setParcelDescription(parcelDescription);
		consignment.setShippingRef1(requestMessage.getDocumentNo());
//		consignment.setShippingRef2(shippingRef2);
//		consignment.setShippingRef3(shippingRef3);
		consignment.setTotalWeight((float)requestMessage.getWeight());
		consignments.add(consignment);
		
//		insertShipmentParameters.setCollectionDate(DateFormatUtils.format(currentTime, "yyyy-MM-dd hh:mm:ss").replaceAll(" ", "T"));
		insertShipmentParameters.setCollectionDate("2015-05-05T10:10:10");
		insertShipmentParameters.setCollectionOnDelivery(true);
		insertShipmentParameters.setConsignment(consignments);
		insertShipmentParameters.setConsolidate(false);
//		insertShipmentParameters.setInvoice(invoice);
//		insertShipmentParameters.setJob_id(job_id);
		
		InsertShipmentOutput insertShipmentOutput = service.insertShipment(insertShipmentParameters);
		checkError(insertShipmentOutput.getErrors());
		shipmentId = insertShipmentOutput.getData().getShipmentId();
		String trackingNo = null;
		if(insertShipmentOutput.getData().getConsignmentDetail()!=null && insertShipmentOutput.getData().getConsignmentDetail().length>0){
			String[] parcelNumbers = insertShipmentOutput.getData().getConsignmentDetail()[0].getParcelNumbers();
			if(parcelNumbers!=null && parcelNumbers.length>0){
				trackingNo = parcelNumbers[0];
			}
		}
		
		if(StringUtils.isEmpty(trackingNo)){
			throw new LabelBusinessException("Insert Shipment without error and tracking no!");
		}
		
		
		////STEP: GET LABEL
		GetLabelsOutput getLabelsOutput = service.getLabels(shipmentId);
		checkError(getLabelsOutput.getErrors());
//		System.out.println(getLabelsOutput.getPrintString());
		
		
		String commands =  getLabelsOutput.getPrintString();
		String code = new String(Base64.encodeBase64(commands.getBytes()));
		return new Result(code, trackingNo,null,""+shipmentId);
	}
	
	private String finalCountryCode(String code){
		if("UK".equals(code)){
			return "GB";
		}
		return code;
	}
	
	protected void checkError(Error[] errs){
		if(errs==null|| errs.length==0){
			return;
		}
		String emsg = JSONArray.toJSONString(errs);
//		for(Error err:errs){
//			JSONObject.toJSONString(errs);
//			emsg = emsg+ err.getErrorMessage()+" ";
//		}
		throw new LabelBusinessException(emsg);
	}
	
	protected void checkError(Error err){
		if(err==null){
			return;
		}
		String emsg =  JSONObject.toJSONString(err);
		throw new LabelBusinessException(emsg);
	}

	
	private void checkLength(String str,int len,String msg) throws Exception{
		if((""+str).length()>len){
			throw new LabelBusinessException(msg);
		}
	}


	@Override
	public boolean isIdempotent()
	{
		return false;
	}
}

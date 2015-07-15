package com.winit.label.manager.impl.gb.yodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InUseredService
{
	
	public static List<UseredService> inUseredServices= null;
	private static String key_ = null;
	private static String version_ = null;
	
	@Autowired
	private YodelServiceService yodelServiceService;
	
	public YodelService getYodelService(String version,String serviceType,String productCode,String featureCode, double weight){
		List<UseredService> inUseredServices = inUseredServices(version);
		for(UseredService useredService:inUseredServices){
			YodelService service = useredService.getService();
			if(StringUtils.isEmpty(useredService.getServiceType())||serviceType.equals(useredService.getServiceType())){
				if(service.getProductCode().equals(productCode) && service.getFeatureCode().equals(featureCode)){
					double toWeight = useredService.getToWeight();
					if(useredService.getToWeight() == 0){
						toWeight = Integer.MAX_VALUE;
					}
					
					if(weight > useredService.getFromWeight() && weight <= toWeight) {
						return service;
					}
				}
			}
		}
		
		return null;
		
	}
	
	public List<UseredService> inUseredServices(String version){
		String servicesStr = YodelConfig.IN_USERED_SERVICES_STR();
		
		//get in cache
		if(StringUtils.isNotEmpty(key_)&&StringUtils.isNotEmpty(version_)){
			if(key_.equals(servicesStr)&&version_.equals(version)){
				if(inUseredServices!=null){
					return inUseredServices;
				}
			}
		}
		
		key_ = servicesStr;
		version_ = version;
		
		List<UseredService> services = new ArrayList<UseredService>();
		
		String[] serviceStrAry =  servicesStr.split(",");
		
		for(String serviceCode:serviceStrAry){
			YodelService service = yodelServiceService.getByServiceCodeAndVersion(serviceCode, version);
			if(service==null){
				continue;
			}
			String serviceType = YodelConfig.SERVICE_TYPE(serviceCode);
			String serviceWeightRange = YodelConfig.SERVICE_WEIGHT_RANGE(serviceCode);
			double fromWeight=0;
			double toWeight = 0;
			if(StringUtils.isNotEmpty(serviceWeightRange)){
				String[] ranges = serviceWeightRange.split("-");
				fromWeight = Double.valueOf(ranges[0]).doubleValue();
				if(ranges.length>1){
					toWeight = Double.valueOf(ranges[1]).doubleValue();
				}
			}
			services.add(new UseredService(service, serviceType, fromWeight, toWeight));
		}
		
		inUseredServices = services;
		return services;
		
	}
	
	
	static class UseredService{
		private YodelService service;
		private String serviceType;
		private double fromWeight;
		private double toWeight;
		public UseredService(YodelService service, String serviceType, double fromWeight, double toWeight)
		{
			this.service = service;
			this.serviceType = serviceType;
			this.fromWeight = fromWeight;
			this.toWeight = toWeight;
		}
		public YodelService getService()
		{
			return service;
		}
		public String getServiceType()
		{
			return serviceType;
		}
		public double getFromWeight()
		{
			return fromWeight;
		}
		public double getToWeight()
		{
			return toWeight;
		}
		
	}

}

package com.winit.label.manager.impl.gb.dpd.model;



public class Network
{
	protected String networkCode;
	protected String networkDescription;
	protected Country[] country;
	public String getNetworkCode()
	{
		return networkCode;
	}
	public void setNetworkCode(String networkCode)
	{
		this.networkCode = networkCode;
	}
	public String getNetworkDescription()
	{
		return networkDescription;
	}
	public void setNetworkDescription(String networkDescription)
	{
		this.networkDescription = networkDescription;
	}
	public Country[] getCountry()
	{
		return country;
	}
	public void setCountry(Country[] country)
	{
		this.country = country;
	}
	
}

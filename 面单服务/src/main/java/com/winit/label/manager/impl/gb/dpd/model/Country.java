package com.winit.label.manager.impl.gb.dpd.model;

public class Country
{
	protected String countryCode; 
	protected String countryName;
	protected boolean isEUCountry ;
	protected boolean isLiabilityAllowed ;
	protected boolean isPostcodeRequired ;
	protected String isoCode ;
	protected boolean liabilityMax ;
	
	public String getCountryCode()
	{
		return countryCode;
	}
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}
	public String getCountryName()
	{
		return countryName;
	}
	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}
	public boolean isEUCountry()
	{
		return isEUCountry;
	}
	public void setEUCountry(boolean isEUCountry)
	{
		this.isEUCountry = isEUCountry;
	}
	public boolean isLiabilityAllowed()
	{
		return isLiabilityAllowed;
	}
	public void setLiabilityAllowed(boolean isLiabilityAllowed)
	{
		this.isLiabilityAllowed = isLiabilityAllowed;
	}
	public boolean isPostcodeRequired()
	{
		return isPostcodeRequired;
	}
	public void setPostcodeRequired(boolean isPostcodeRequired)
	{
		this.isPostcodeRequired = isPostcodeRequired;
	}
	public String getIsoCode()
	{
		return isoCode;
	}
	public void setIsoCode(String isoCode)
	{
		this.isoCode = isoCode;
	}
	public boolean isLiabilityMax()
	{
		return liabilityMax;
	}
	public void setLiabilityMax(boolean liabilityMax)
	{
		this.liabilityMax = liabilityMax;
	}
	
	
}

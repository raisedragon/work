package com.winit.label.manager.impl.gb.dpd.model;

public class InvoiceBillingDetails
{
	protected Address			address;
	protected ContactDetails	contactDetails;
	protected String			vatNumber;

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public ContactDetails getContactDetails()
	{
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails)
	{
		this.contactDetails = contactDetails;
	}

	public String getVatNumber()
	{
		return vatNumber;
	}

	public void setVatNumber(String vatNumber)
	{
		this.vatNumber = vatNumber;
	}

}

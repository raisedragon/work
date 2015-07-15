package com.winit.label.manager.impl.gb.dpd.model;


public class DeliveryDetails
{
	protected Address				address;
	protected AddressPoint			addressPoint;
	protected ContactDetails		contactDetails;
	protected NotificationDetails	notificationDetails;

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public AddressPoint getAddressPoint()
	{
		return addressPoint;
	}

	public void setAddressPoint(AddressPoint addressPoint)
	{
		this.addressPoint = addressPoint;
	}

	public ContactDetails getContactDetails()
	{
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails)
	{
		this.contactDetails = contactDetails;
	}

	public NotificationDetails getNotificationDetails()
	{
		return notificationDetails;
	}

	public void setNotificationDetails(NotificationDetails notificationDetails)
	{
		this.notificationDetails = notificationDetails;
	}

}

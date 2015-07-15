package com.winit.label.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author longsheng.wang
 */
public class RequestMessage implements Serializable
{

	private static final long	serialVersionUID	= 78968795114720702L;

	private String				documentNo;
	private String				logisticsCode;
	private double				length;
	private double				width;
	private double				height;
	private double				weight;
	private Consignee			consignee;
	private List<Product>		products			= new ArrayList<RequestMessage.Product>();

	private boolean				requiredNew;

	public String getDocumentNo()
	{
		return documentNo;
	}

	public void setDocumentNo(String documentNo)
	{
		this.documentNo = documentNo;
	}

	public String getLogisticsCode()
	{
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode)
	{
		this.logisticsCode = logisticsCode;
	}

	public double getLength()
	{
		return length;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public boolean isRequiredNew()
	{
		return requiredNew;
	}

	public void setRequiredNew(boolean requiredNew)
	{
		this.requiredNew = requiredNew;
	}

	public Consignee getConsignee()
	{
		return consignee;
	}

	public void setConsignee(Consignee consignee)
	{
		this.consignee = consignee;
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public void setProducts(List<Product> products)
	{
		this.products = products;
	}

	/**
	 * 收件人信息
	 * @author kaizhou.chen
	 *
	 */
	public static class Consignee
	{
		private String	name;
		private String	address1;
		private String	address2;
		private String	address3;
		private String	email;
		private String	phone;
		private String	countryCode;
		private String	state;
		private String	city;
		private String	postcode;
		private String	houseNo;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getAddress1()
		{
			return address1;
		}

		public void setAddress1(String address1)
		{
			this.address1 = address1;
		}

		public String getAddress2()
		{
			return address2;
		}

		public void setAddress2(String address2)
		{
			this.address2 = address2;
		}

		public String getAddress3()
		{
			return address3;
		}

		public void setAddress3(String address3)
		{
			this.address3 = address3;
		}

		public String getEmail()
		{
			return email;
		}

		public void setEmail(String email)
		{
			this.email = email;
		}

		public String getPhone()
		{
			return phone;
		}

		public void setPhone(String phone)
		{
			this.phone = phone;
		}

		public String getCountryCode()
		{
			return countryCode;
		}

		public void setCountryCode(String countryCode)
		{
			this.countryCode = countryCode;
		}

		public String getState()
		{
			return state;
		}

		public void setState(String state)
		{
			this.state = state;
		}

		public String getCity()
		{
			return city;
		}

		public void setCity(String city)
		{
			this.city = city;
		}

		public String getPostcode()
		{
			return postcode;
		}

		public void setPostcode(String postcode)
		{
			this.postcode = postcode;
		}

		public String getHouseNo() {
			return houseNo;
		}

		public void setHouseNo(String houseNo) {
			this.houseNo = houseNo;
		}
		
	}

	/**
	 * 产品信息
	 * @author kaizhou.chen
	 *
	 */
	public static class Product
	{
		private String					name;
		private double					length;
		private double					width;
		private double					height;
		private double					weight;
		private int						qty;
		private String					sku;

		private List<ClassifyProduct>	classifyProducts;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public double getWeight()
		{
			return weight;
		}

		public void setWeight(double weight)
		{
			this.weight = weight;
		}

		public double getLength()
		{
			return length;
		}

		public void setLength(double length)
		{
			this.length = length;
		}

		public double getWidth()
		{
			return width;
		}

		public void setWidth(double width)
		{
			this.width = width;
		}

		public double getHeight()
		{
			return height;
		}

		public void setHeight(double height)
		{
			this.height = height;
		}

		public String getSku()
		{
			return sku;
		}

		public void setSku(String sku)
		{
			this.sku = sku;
		}

		public int getQty()
		{
			return qty;
		}

		public void setQty(int qty)
		{
			this.qty = qty;
		}

		public List<ClassifyProduct> getClassifyProducts()
		{
			if(classifyProducts==null){
				classifyProducts = new ArrayList<RequestMessage.ClassifyProduct>();
			}
			return classifyProducts;
		}

		public void setClassifyProducts(List<ClassifyProduct> classifyProducts)
		{
			this.classifyProducts = classifyProducts;
		}
		
	}

	/**
	 * 产品归类表信息
	 * @author kaizhou.chen
	 *
	 */
	public static class ClassifyProduct
	{
		private String	countryCode;
		private double	priceImports;

		public String getCountryCode()
		{
			return countryCode;
		}

		public void setCountryCode(String countryCode)
		{
			this.countryCode = countryCode;
		}

		public double getPriceImports()
		{
			return priceImports;
		}

		public void setPriceImports(double priceImports)
		{
			this.priceImports = priceImports;
		}
	}

}

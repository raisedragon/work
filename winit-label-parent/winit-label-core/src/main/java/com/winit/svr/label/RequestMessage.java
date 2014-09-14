package com.winit.svr.label;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longsheng.wang
 *
 */
public class RequestMessage implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 78968795114720702L;
	
	//消息头
	private SHEAD head;
	//消息体
	private SBODY body;
	//变量
	private Map<String,Object> variables= new HashMap<String, Object>();

	public SHEAD getHead()
	{
		return head;
	}

	public void setHead(SHEAD head)
	{
		this.head = head;
	}

	public SBODY getBody()
	{
		return body;
	}

	public void setBody(SBODY body)
	{
		this.body = body;
	}
	
	public Map<String, Object> getVariables()
	{
		return variables;
	}

	public void setVariables(Map<String, Object> variables)
	{
		this.variables = variables;
	}



	public static class Logistics{
		private String code;

		public String getCode()
		{
			return code;
		}

		public void setCode(String code)
		{
			this.code = code;
		}
		
	}
	
	public static class Consignee{
		private String name;
		private String ename;
		private String address1;
		private String address2;
		private String address3;
		private String email;
		private String phone;
		private String country;
		private String province;
		private String city;
		private String postcode;
		
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public String getEname()
		{
			return ename;
		}
		public void setEname(String ename)
		{
			this.ename = ename;
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
		public String getCountry()
		{
			return country;
		}
		public void setCountry(String country)
		{
			this.country = country;
		}
		public String getProvince()
		{
			return province;
		}
		public void setProvince(String province)
		{
			this.province = province;
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
	
		
	}
	
	public static class Product{
		private String name;
		private String ename;
		private String description;
		private BigDecimal weight;
		private BigDecimal length;
		private BigDecimal width;
		private BigDecimal height;
		private String sku;
		private BigDecimal qty;
		
		private List<ClassifyProduct> classifyProducts;
		
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public String getEname()
		{
			return ename;
		}
		public void setEname(String ename)
		{
			this.ename = ename;
		}
		public String getDescription()
		{
			return description;
		}
		public void setDescription(String description)
		{
			this.description = description;
		}
		public BigDecimal getWeight()
		{
			return weight;
		}
		public void setWeight(BigDecimal weight)
		{
			this.weight = weight;
		}
		public BigDecimal getLength()
		{
			return length;
		}
		public void setLength(BigDecimal length)
		{
			this.length = length;
		}
		public BigDecimal getWidth()
		{
			return width;
		}
		public void setWidth(BigDecimal width)
		{
			this.width = width;
		}
		public BigDecimal getHeight()
		{
			return height;
		}
		public void setHeight(BigDecimal height)
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
		
		public BigDecimal getQty()
		{
			return qty;
		}
		public void setQty(BigDecimal qty)
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
	
	public static class ClassifyProduct{
		private String country;
		private BigDecimal priceImports;
		private BigDecimal priceExports;
		private String declareName;
		public String getCountry()
		{
			return country;
		}
		public void setCountry(String country)
		{
			this.country = country;
		}
		public BigDecimal getPriceImports()
		{
			return priceImports;
		}
		public void setPriceImports(BigDecimal priceImports)
		{
			this.priceImports = priceImports;
		}
		public BigDecimal getPriceExports()
		{
			return priceExports;
		}
		public void setPriceExports(BigDecimal priceExports)
		{
			this.priceExports = priceExports;
		}
		public String getDeclareName()
		{
			return declareName;
		}
		public void setDeclareName(String declareName)
		{
			this.declareName = declareName;
		}
		
		
	}
	
	public static class SHEAD{
		
	}
	
	public static class SBODY{
		
		private String documentNo;
		private Logistics logistics;
		private Consignee consignee;
		private List<Product> products = new ArrayList<RequestMessage.Product>();
		
		private BigDecimal length;
		private BigDecimal width;
		private BigDecimal height;
		
		
		private boolean requiredNew;
		
		public String getDocumentNo()
		{
			return documentNo;
		}

		public void setDocumentNo(String documentNo)
		{
			this.documentNo = documentNo;
		}

		public Logistics getLogistics()
		{
			return logistics;
		}

		public void setLogistics(Logistics logistics)
		{
			this.logistics = logistics;
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

		public BigDecimal getLength()
		{
			return length;
		}

		public void setLength(BigDecimal length)
		{
			this.length = length;
		}

		public BigDecimal getWidth()
		{
			return width;
		}

		public void setWidth(BigDecimal width)
		{
			this.width = width;
		}

		public BigDecimal getHeight()
		{
			return height;
		}

		public void setHeight(BigDecimal height)
		{
			this.height = height;
		}

		public boolean isRequiredNew()
		{
			return requiredNew;
		}

		public void setRequiredNew(boolean requiredNew)
		{
			this.requiredNew = requiredNew;
		}
		
		
		
	}
}

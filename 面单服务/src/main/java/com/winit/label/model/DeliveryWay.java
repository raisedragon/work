package com.winit.label.model;


import java.io.Serializable;

/**
 * 
 * <P>派送方式MODEL</P>
 * @author {庄坚发}
 */
public class DeliveryWay implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8449596857876931727L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 创建时间 
	 */
	private String created;
	
	/**
	 * 创建人
	 */
	private String createdBy;
	
	/**
	 * 更新时间
	 */
	private String updated;
	
	/**
	 * 更新人
	 */
	private String updatedBy;
	
	/**
	 * 派送方式名称
	 */
	private String name;
	
	
	/**
	 * 派送方式编码
	 */
	private String code;
	
	
	/**
	 *  物流商服务编码
	 */
	private String serviceCode;
	
	/**
	 * 面单文件后缀名
	 */
	private String extension;
	
	
	/**
	 * 面单实现类
	 */
	private String implClass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updateBy) {
		this.updatedBy = updateBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getExtension()
	{
		return extension;
	}

	public void setExtension(String extension)
	{
		this.extension = extension;
	}

	public String getImplClass()
	{
		return implClass;
	}

	public void setImplClass(String implClass)
	{
		this.implClass = implClass;
	}

}

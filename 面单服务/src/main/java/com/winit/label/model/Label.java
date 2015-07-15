package com.winit.label.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * <P>出库单</P>
 * @author {庄坚发}
 */
public class Label implements Serializable{
	
	public static enum StatuType{
		Got("got"),
		Upload("upload");
		private String desc;
		private StatuType(String desc)
		{
			this.desc = desc;
		}
		public String getDesc()
		{
			return desc;
		}
	}

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8161867252181389851L;

	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 出库单号
	 */
	private String documentNo;
	
	/**
	 * 快递单号
	 */
	private String trackingNo;
	
	/**
	 * 文件Base64编码
	 */
	private String fileCode;

	/**
	 * 创建时间
	 */
	private Date created;
	
	private StatuType status;
	
	
	
	/**
	 * 快递单备用信息字段1
	 */
	private String consignmentRef1;
	
	/**
	 * 快递单备用信息字段2
	 */
	private String consignmentRef2;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackNo) {
		this.trackingNo = trackNo;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public StatuType getStatus()
	{
		return status;
	}

	public void setStatus(StatuType status)
	{
		this.status = status;
	}

	public String getConsignmentRef1()
	{
		return consignmentRef1;
	}

	public void setConsignmentRef1(String consignmentRef1)
	{
		this.consignmentRef1 = consignmentRef1;
	}

	public String getConsignmentRef2()
	{
		return consignmentRef2;
	}

	public void setConsignmentRef2(String consignmentRef2)
	{
		this.consignmentRef2 = consignmentRef2;
	}
	
	
	
	
}

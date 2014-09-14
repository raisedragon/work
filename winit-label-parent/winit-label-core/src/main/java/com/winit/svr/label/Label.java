package com.winit.svr.label;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 面单对象
 * 
 * @author longsheng.wang
 *
 */
public interface Label extends Serializable
{
	public static enum StatusType
	{
		active("active"),
		delete("delete");
		
		private String desc;
		StatusType(String desc){
			this.desc=desc;
		}
		public String getDesc()
		{
			return desc;
		}
	}

	String getId();
	void setId(String id);

	String getDocumentNo();
	void setDocumentNo(String documentNo);

	String getTrackNo();
	void setTrackNo(String trackNo);

	String getFileCode();
	void setFileCode(String fileCode);

	Date getCreated();
	void setCreated(Date created);

	Date getUpdated();
	void setUpdated(Date updated);

	StatusType getStatus();
	void setStatus(StatusType status);
}

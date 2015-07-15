package com.winit.label.service;

import java.util.Map;

import com.winit.label.model.Label;

public interface LabelService {

	/**
	 * 
	 * <p>新增面单信息</p>
	 * @param label
	 * @return
	 * @author {庄坚发}
	 */
	public int addLabel(Label label);
	
	public Label queryLabelByMap(Map<String,String> map);
	
	public Label findByDocumentNo(String documentNo);

	void updateLabel(Label label);
	
}

package com.winit.label.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.winit.label.model.Label;
import com.winit.label.service.LabelService;

@Service("labelService")
public class LabelServiceImpl extends BaseServiceImpl implements LabelService{

	private String LABEL_NAMESPACE = Label.class.getName();
	
	private String ADD_LABEL = "addLabel";
	private String UPDATE_LABEL = "updateLabel";
	
	private String QUERY_LABEL_BY_MAP = "queryLabelByMap";
	
	
	@Override
	public int addLabel(Label label) {
		return save(LABEL_NAMESPACE+"."+ADD_LABEL,label);
	}

	@Override
	public void updateLabel(Label label){
		update(LABEL_NAMESPACE+"."+UPDATE_LABEL, label);
	}
	
	@Override
	public Label queryLabelByMap(Map<String, String> map) {
		return (Label) single(LABEL_NAMESPACE+"."+QUERY_LABEL_BY_MAP, map);
	}

	@Override
	public Label findByDocumentNo(String documentNo)
	{
		Map<String,String> map = new HashMap<String, String>();
		map.put("documentNo", documentNo);
		return queryLabelByMap(map);
	}
}

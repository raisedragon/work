package com.winit.svr.label;

import com.winit.svr.query.Query;

public interface LabelQuery extends Query<LabelQuery, Label>
{
	LabelQuery labelId(String id);
	
	LabelQuery documentNo(String documentNo);
	
	LabelQuery trackNo(String trackNo);
}

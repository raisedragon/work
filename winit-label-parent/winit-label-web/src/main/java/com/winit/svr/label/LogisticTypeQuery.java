package com.winit.svr.label;

import com.winit.svr.query.Query;

public interface LogisticTypeQuery extends Query<LogisticTypeQuery, LogisticType>
{
	LogisticTypeQuery logisticTypeId(String id);
	
	LogisticTypeQuery logisticTypeNameLike(String name);
}

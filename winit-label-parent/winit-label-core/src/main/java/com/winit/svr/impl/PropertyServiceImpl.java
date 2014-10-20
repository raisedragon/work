package com.winit.svr.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.svr.LabelException;
import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.LabelService;
import com.winit.svr.PropertyService;
import com.winit.svr.impl.cmd.CreateLabelQueryCmd;
import com.winit.svr.impl.cmd.CreateLogisticTypeQueryCmd;
import com.winit.svr.impl.cmd.DeleteLabelCmd;
import com.winit.svr.impl.cmd.DeleteLogisticTypeCmd;
import com.winit.svr.impl.cmd.DeletePropertyCmd;
import com.winit.svr.impl.cmd.GenerateLabelCmd;
import com.winit.svr.impl.cmd.GetLogisticTypeVariableCmd;
import com.winit.svr.impl.cmd.GetLogisticTypeVariablesCmd;
import com.winit.svr.impl.cmd.GetPropertiesCmd;
import com.winit.svr.impl.cmd.GetPropertyCmd;
import com.winit.svr.impl.cmd.HasLogisticTypeVariableCmd;
import com.winit.svr.impl.cmd.RemoveLogisticTypeVariablesCmd;
import com.winit.svr.impl.cmd.SaveLabelCmd;
import com.winit.svr.impl.cmd.SaveLogisticTypeCmd;
import com.winit.svr.impl.cmd.SavePropertyCmd;
import com.winit.svr.impl.cmd.SetLogisticTypeVariablesCmd;
import com.winit.svr.impl.persistence.entity.PropertyEntity;
import com.winit.svr.label.Label;
import com.winit.svr.label.LabelQuery;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.LogisticTypeQuery;

public class PropertyServiceImpl extends ServiceImpl implements PropertyService
{


	@Override
	public PropertyEntity findPropertyByName(String name)
	{
		return	commandExecutor.execute(new GetPropertyCmd(name));
	}

	@Override
	public void saveProperty(PropertyEntity propertyEntity)
	{
		commandExecutor.execute(new SavePropertyCmd(propertyEntity));
	}

	@Override
	public void deleteProperty(String name)
	{
		commandExecutor.execute(new DeletePropertyCmd(name));
		
	}

	@Override
	public void addProperty(String name, String value, String desc)
	{
		
		PropertyEntity entity = this.findPropertyByName(name);
		if(entity!=null){
			throw new LabelException("Object is exists");
		}
		commandExecutor.execute(new SavePropertyCmd(entity));
	}

	@Override
	public void saveProperty(String name, String value, String desc)
	{
		PropertyEntity entity = new PropertyEntity(name, value, desc);
		commandExecutor.execute(new SavePropertyCmd(entity));
		
	}

}

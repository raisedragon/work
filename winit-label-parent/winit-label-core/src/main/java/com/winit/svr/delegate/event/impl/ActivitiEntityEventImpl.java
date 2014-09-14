/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.winit.svr.delegate.event.impl;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.delegate.event.ActivitiEntityEvent;
import com.winit.svr.delegate.event.ActivitiEvent;
import com.winit.svr.delegate.event.ActivitiEventType;

/**
 * Base class for all {@link ActivitiEvent} implementations, related to entities.
 * 
 * @author Frederik Heremans
 */
public class ActivitiEntityEventImpl extends ActivitiEventImpl implements ActivitiEntityEvent {

	protected Object entity;
	
	public ActivitiEntityEventImpl(Object entity, ActivitiEventType type) {
		super(type);
		if(entity == null) {
			throw new LabelIllegalArgumentException("Entity cannot be null.");
		}
	  this.entity = entity;
  }
	
	@Override
	public Object getEntity() {
		return entity;
	}
}

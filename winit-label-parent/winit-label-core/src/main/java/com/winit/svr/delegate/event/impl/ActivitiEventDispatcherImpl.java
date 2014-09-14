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

import com.winit.svr.delegate.event.ActivitiEntityEvent;
import com.winit.svr.delegate.event.ActivitiEvent;
import com.winit.svr.delegate.event.ActivitiEventDispatcher;
import com.winit.svr.delegate.event.ActivitiEventListener;
import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.interceptor.CommandContext;

/**
 * Class capable of dispatching events.
 * 
 * @author Frederik Heremans
 */
public class ActivitiEventDispatcherImpl implements ActivitiEventDispatcher {

	protected ActivitiEventSupport eventSupport;
	protected boolean enabled = true;

	public ActivitiEventDispatcherImpl() {
		eventSupport = new ActivitiEventSupport();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void addEventListener(ActivitiEventListener listenerToAdd) {
		eventSupport.addEventListener(listenerToAdd);
	}

	@Override
	public void addEventListener(ActivitiEventListener listenerToAdd, ActivitiEventType... types) {
		eventSupport.addEventListener(listenerToAdd, types);
	}

	@Override
	public void removeEventListener(ActivitiEventListener listenerToRemove) {
		eventSupport.removeEventListener(listenerToRemove);
	}

	@Override
	public void dispatchEvent(ActivitiEvent event) {
		if (enabled) {
			eventSupport.dispatchEvent(event);
		}

	}

	

}

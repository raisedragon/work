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
package com.winit.svr.impl.persistence;

import com.winit.svr.impl.interceptor.Session;
import com.winit.svr.impl.interceptor.SessionFactory;
import com.winit.svr.impl.persistence.entity.MembershipEntityManager;
import com.winit.svr.impl.persistence.entity.MembershipIdentityManager;


/**
 * @author Joram Barrez
 */
public class MembershipEntityManagerFactory implements SessionFactory {

  public Class< ? > getSessionType() {
    return MembershipIdentityManager.class;
  }

  public Session openSession() {
    return new MembershipEntityManager();
  }

}

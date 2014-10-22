
package com.winit.svr.impl.persistence.manager;

import com.winit.svr.impl.interceptor.Session;
import com.winit.svr.impl.interceptor.SessionFactory;
import com.winit.svr.impl.persistence.entity.GroupEntityManager;
import com.winit.svr.impl.persistence.entity.GroupIdentityManager;


public class TreeEntityManagerFactory implements SessionFactory {

  public Class< ? > getSessionType() {
    return TreeEntityManager.class;
  }

  public Session openSession() {
    return new TreeEntityManager();
  }

}

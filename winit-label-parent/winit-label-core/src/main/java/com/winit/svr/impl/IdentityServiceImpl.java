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
package com.winit.svr.impl;

import com.winit.svr.IdentityService;
import com.winit.svr.identity.Group;
import com.winit.svr.identity.GroupQuery;
import com.winit.svr.identity.NativeGroupQuery;
import com.winit.svr.identity.NativeUserQuery;
import com.winit.svr.identity.Picture;
import com.winit.svr.identity.User;
import com.winit.svr.identity.UserQuery;
import com.winit.svr.impl.cmd.CheckPassword;
import com.winit.svr.impl.cmd.CreateGroupCmd;
import com.winit.svr.impl.cmd.CreateGroupQueryCmd;
import com.winit.svr.impl.cmd.CreateMembershipCmd;
import com.winit.svr.impl.cmd.CreateUserCmd;
import com.winit.svr.impl.cmd.CreateUserQueryCmd;
import com.winit.svr.impl.cmd.DeleteGroupCmd;
import com.winit.svr.impl.cmd.DeleteMembershipCmd;
import com.winit.svr.impl.cmd.DeleteUserCmd;
import com.winit.svr.impl.cmd.DeleteUserInfoCmd;
import com.winit.svr.impl.cmd.GetUserInfoCmd;
import com.winit.svr.impl.cmd.GetUserInfoKeysCmd;
import com.winit.svr.impl.cmd.GetUserPictureCmd;
import com.winit.svr.impl.cmd.SaveGroupCmd;
import com.winit.svr.impl.cmd.SaveUserCmd;
import com.winit.svr.impl.cmd.SetUserInfoCmd;
import com.winit.svr.impl.cmd.SetUserPictureCmd;
import com.winit.svr.impl.identity.Authentication;
import com.winit.svr.impl.persistence.entity.GroupEntity;
import com.winit.svr.impl.persistence.entity.IdentityInfoEntity;

import java.util.List;


/**
 * @author Tom Baeyens
 */
public class IdentityServiceImpl extends ServiceImpl implements IdentityService {
  
  public Group newGroup(String groupId) {
    return commandExecutor.execute(new CreateGroupCmd(groupId));
  }

  public User newUser(String userId) {
    return commandExecutor.execute(new CreateUserCmd(userId));
  }

  public void saveGroup(Group group) {
    commandExecutor.execute(new SaveGroupCmd((GroupEntity) group));
  }

  public void saveUser(User user) {
    commandExecutor.execute(new SaveUserCmd(user));
  }
  
  public UserQuery createUserQuery() {
    return commandExecutor.execute(new CreateUserQueryCmd());
  }

  @Override
  public NativeUserQuery createNativeUserQuery() {
    return new NativeUserQueryImpl(commandExecutor);
  }

  public GroupQuery createGroupQuery() {
    return commandExecutor.execute(new CreateGroupQueryCmd());
  }

  @Override
  public NativeGroupQuery createNativeGroupQuery() {
    return new NativeGroupQueryImpl(commandExecutor);
  }

  public void createMembership(String userId, String groupId) {
    commandExecutor.execute(new CreateMembershipCmd(userId, groupId));
  }

  public void deleteGroup(String groupId) {
    commandExecutor.execute(new DeleteGroupCmd(groupId));
  }

  public void deleteMembership(String userId, String groupId) {
    commandExecutor.execute(new DeleteMembershipCmd(userId, groupId));
  }

  public boolean checkPassword(String userId, String password) {
    return commandExecutor.execute(new CheckPassword(userId, password));
  }

  public void deleteUser(String userId) {
    commandExecutor.execute(new DeleteUserCmd(userId));
  }

  public void setUserPicture(String userId, Picture picture) {
    commandExecutor.execute(new SetUserPictureCmd(userId, picture));
  }

  public Picture getUserPicture(String userId) {
    return commandExecutor.execute(new GetUserPictureCmd(userId));
  }

  public void setAuthenticatedUserId(String authenticatedUserId) {
    Authentication.setAuthenticatedUserId(authenticatedUserId);
  }

  public String getUserInfo(String userId, String key) {
    return commandExecutor.execute(new GetUserInfoCmd(userId, key));
  }

  public List<String> getUserInfoKeys(String userId) {
    return commandExecutor.execute(new GetUserInfoKeysCmd(userId, IdentityInfoEntity.TYPE_USERINFO));
  }

  public void setUserInfo(String userId, String key, String value) {
    commandExecutor.execute(new SetUserInfoCmd(userId, key, value));
  }

  public void deleteUserInfo(String userId, String key) {
    commandExecutor.execute(new DeleteUserInfoCmd(userId, key));
  }
}

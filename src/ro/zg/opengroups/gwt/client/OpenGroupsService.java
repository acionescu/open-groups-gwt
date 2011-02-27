/*******************************************************************************
 * Copyright 2011 Adrian Cristian Ionescu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ro.zg.opengroups.gwt.client;

import java.util.Map;

import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.vo.AppState;
import ro.zg.webapp.core.shared.vo.Command;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("OpenGroupsService")
public interface OpenGroupsService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static OpenGroupsServiceAsync instance;
		public static OpenGroupsServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(OpenGroupsService.class);
			}
			return instance;
		}
	}
	
	public OpenGroupsAppState execute(String commandId, Map<String,Object> params) throws IllegalArgumentException;
	public OpenGroupsAppState execute(Command command) throws IllegalArgumentException;
	
}

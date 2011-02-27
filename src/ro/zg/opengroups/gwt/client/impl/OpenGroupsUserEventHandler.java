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
package ro.zg.opengroups.gwt.client.impl;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ro.zg.opengroups.gwt.client.OpenGroupsServiceAsync;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.views.UserEventToCommandMapper;
import ro.zg.webapp.core.shared.views.ViewsManager;
import ro.zg.webapp.core.shared.views.support.AbstractUserEventHandler;
import ro.zg.webapp.core.shared.vo.Command;

public abstract class OpenGroupsUserEventHandler extends AbstractUserEventHandler {
    private OpenGroupsServiceAsync openGroupsService;

    public OpenGroupsUserEventHandler(OpenGroupsServiceAsync openGroupsService, UserEventToCommandMapper mapper) {
	super(mapper);
	this.openGroupsService = openGroupsService;
    }

    @Override
    protected void submitCommand(final Command command, final ViewsManager viewsManager) {
	
	System.out.println("sending "+command.getParams());
	openGroupsService.execute(command, new AsyncCallback<OpenGroupsAppState>() {

	    public void onSuccess(OpenGroupsAppState result) {
		result.setCurrentViewId(command.getViewId());
		System.out.println("Updating "+command.getViewId());
		viewsManager.update(result);
	    }

	    public void onFailure(Throwable caught) {
		caught.printStackTrace();
	    }
	});
    }

}

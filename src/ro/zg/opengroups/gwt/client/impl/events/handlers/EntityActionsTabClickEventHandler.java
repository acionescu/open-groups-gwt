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
package ro.zg.opengroups.gwt.client.impl.events.handlers;

import ro.zg.opengroups.gwt.client.OpenGroupsServiceAsync;
import ro.zg.opengroups.gwt.client.impl.OpenGroupsUserEventHandler;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.webapp.core.shared.event.UserEvent;
import ro.zg.webapp.core.shared.views.UserEventToCommandMapper;
import ro.zg.webapp.core.shared.views.ViewsManager;

public class EntityActionsTabClickEventHandler extends OpenGroupsUserEventHandler{

    public EntityActionsTabClickEventHandler(OpenGroupsServiceAsync openGroupsService, UserEventToCommandMapper mapper) {
	super(openGroupsService, mapper);
    }

    public void handleUserEvent(UserEvent userEvent, ViewsManager viewsManager) {
	submitCommandForEvent(userEvent, viewsManager);
	viewsManager.setCachedParam(OpenGroupsParams.CURRENT_TAB_ACTION_PATH, userEvent.getEventParam(OpenGroupsParams.CURRENT_TAB_ACTION_PATH));
    }

    
    
}

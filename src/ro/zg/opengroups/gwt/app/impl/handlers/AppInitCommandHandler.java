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
package ro.zg.opengroups.gwt.app.impl.handlers;

import java.util.List;

import ro.zg.opengroups.gwt.app.impl.constants.OpenGroupsRuntimeConfigParams;
import ro.zg.opengroups.gwt.shared.constants.MessagesKeys;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.opengroups.gwt.shared.vo.EntityState;
import ro.zg.webapp.core.shared.vo.AppState;
import ro.zg.webapp.core.vo.CommandContext;

public class AppInitCommandHandler extends BaseOpenGroupsCommandHandler{

    public void handle(CommandContext commandContext) {
	System.out.println("inside app init handler");
	Integer selectedEntityId = (Integer)commandContext.getCommandParam(OpenGroupsParams.ENTITY_ID);
	
	int defaultEntityId = ((Long)getAppConfigParam(commandContext, OpenGroupsRuntimeConfigParams.DEFAULT_ENTITY_ID)).intValue();
	int metaEntityId = ((Long)getAppConfigParam(commandContext, OpenGroupsRuntimeConfigParams.APP_ENTITY_ID)).intValue();
	if(selectedEntityId == null) {
	    selectedEntityId=defaultEntityId;
	}
	/* load entity data */
	 loadEntityData(selectedEntityId, commandContext);
	
	AppState appState = commandContext.getAppState();
	
	/* set quick links data */
	appState.putParam(OpenGroupsAppParams.MAIN_ENTITY_ID, defaultEntityId);
	appState.putParam(OpenGroupsAppParams.META_ENTITY_ID, metaEntityId);
	
	addCaption(MessagesKeys.MAIN_ENTITY_CAPTION, commandContext);
	addCaption(MessagesKeys.META_ENTITY_CAPTION, commandContext);
	
	System.out.println("exiting app init handler");
    }
}

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
package ro.zg.opengroups.gwt.app.impl.loaders;

import java.util.HashMap;
import java.util.Map;

import ro.zg.opengroups.gwt.app.impl.handlers.AppInitCommandHandler;
import ro.zg.opengroups.gwt.app.impl.handlers.GetEntitiesListCommandHandler;
import ro.zg.opengroups.gwt.app.impl.handlers.OpenEntityCommandHandler;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsCommands;
import ro.zg.util.config.ConfigParamLoader;
import ro.zg.webapp.core.contract.CommandHandler;

public class OpenGroupsCommandHandlersLoader implements ConfigParamLoader{

    public Object load(Map<String,Object> resources) throws Exception {
	Map<String,CommandHandler> handlers=new HashMap<String, CommandHandler>();
	handlers.put(OpenGroupsCommands.APP_INIT, new AppInitCommandHandler());
	handlers.put(OpenGroupsCommands.OPEN_ENTITY, new OpenEntityCommandHandler());
	
	GetEntitiesListCommandHandler listEntitiesHandler= new GetEntitiesListCommandHandler();
	handlers.put(OpenGroupsCommands.ENTITY_LIST_RECENT_ACTIVITY, listEntitiesHandler);
	handlers.put(OpenGroupsCommands.ENTITY_LIST_BY_GLOBAL_PRIORITY, listEntitiesHandler);
	handlers.put(OpenGroupsCommands.ENTITY_LIST_BY_MY_PRIORITY, listEntitiesHandler);
	handlers.put(OpenGroupsCommands.ENTITY_LIST_MOST_POPULAR, listEntitiesHandler);
	handlers.put(OpenGroupsCommands.ENTITY_LIST_NEWEST, listEntitiesHandler);
	return handlers;
    }

}

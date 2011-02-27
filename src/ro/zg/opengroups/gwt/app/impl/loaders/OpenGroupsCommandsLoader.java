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

import java.util.LinkedHashMap;
import java.util.Map;

import ro.zg.opengroups.gwt.app.impl.resources.OpenGroupsResources;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsCommands;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsViews;
import ro.zg.util.config.ConfigParamLoader;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.webapp.core.shared.vo.CommandDefinition;
import ro.zg.webapp.core.vo.CommandConfig;
import ro.zg.webapp.core.vo.support.CommandDefinitionBuilder;

public class OpenGroupsCommandsLoader implements ConfigParamLoader{
    private AvailableActionsLoader actionsLoader;
    
    public OpenGroupsCommandsLoader(AvailableActionsLoader actionsLoader) {
	this.actionsLoader=actionsLoader;
    }
    
    public Object load(Map<String,Object> resources) throws Exception {
	Map<String, CommandConfig> commands= new LinkedHashMap<String, CommandConfig>();
	
	commands.put(OpenGroupsCommands.APP_INIT, new CommandConfig(OpenGroupsCommands.APP_INIT, null));
	commands.put(OpenGroupsCommands.OPEN_ENTITY, new CommandConfig(OpenGroupsCommands.OPEN_ENTITY, null));
	
	GenericNameValueList actionsList = (GenericNameValueList)actionsLoader.load(resources);
	
	for (int i = 0; i < actionsList.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) actionsList.getValueForIndex(i);
	    CommandDefinition cd = CommandDefinitionBuilder.buildCommandDefinition(row);
	    initDisplayName(cd);
	    commands.put(cd.getUniqueCommandDesc(), new CommandConfig(cd.getUniqueCommandDesc(),null,cd));
	}
	
	return commands;
    }
    
    private void initDisplayName(CommandDefinition cd) {
	String commandName = cd.getActionName();
	String displayName = OpenGroupsResources.getMessage(commandName);

	if (displayName == null) {
	    displayName = OpenGroupsResources.getMessage(commandName + "/" + cd.getTargetEntityType());
	}
	cd.setDisplayName(displayName);
    }

}

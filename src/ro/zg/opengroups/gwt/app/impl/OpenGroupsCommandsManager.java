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
package ro.zg.opengroups.gwt.app.impl;

import java.util.Map;

import ro.zg.util.config.ConfigLoader;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.webapp.core.shared.vo.AppState;
import ro.zg.webapp.core.shared.vo.CommandDefinition;
import ro.zg.webapp.core.support.AbstractCommandsManager;
import ro.zg.webapp.core.support.config.CommandsManagerConfig;

public class OpenGroupsCommandsManager extends AbstractCommandsManager{
    
    @Override
    protected CommandsManagerConfig getConfig(ConfigLoader loader) throws Exception {
	return new CommandsManagerConfig(loader);
    }

    @Override
    protected CommandsManagerConfig getConfig(GenericNameValueContext params, Map<String,Object> resources) throws Exception {
	return new CommandsManagerConfig(params,resources);
    }

    public Map<String, CommandDefinition> getAvailableCommands(AppState appState) {
	// TODO Auto-generated method stub
	return null;
    }

}

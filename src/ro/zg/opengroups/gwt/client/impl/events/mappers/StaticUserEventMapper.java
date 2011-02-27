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
package ro.zg.opengroups.gwt.client.impl.events.mappers;

import ro.zg.webapp.core.shared.event.UserEvent;
import ro.zg.webapp.core.shared.views.UserEventToCommandMapper;
import ro.zg.webapp.core.shared.views.ViewsManager;
import ro.zg.webapp.core.shared.vo.Command;

public class StaticUserEventMapper implements UserEventToCommandMapper{
    private String commandId;
    
    public StaticUserEventMapper() {
	
    }
    
    public StaticUserEventMapper(String commandId) {
	this.commandId=commandId;
    }
    
    public Command getCommandForUserEvent(UserEvent userEvent, ViewsManager viewsManager) {
	Command command = Command.build(commandId, userEvent.getEventParams(), userEvent.getTargetViewId());
	return command;
    }

    /**
     * @return the commandId
     */
    public String getCommandId() {
        return commandId;
    }

    /**
     * @param commandId the commandId to set
     */
    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    
    
}

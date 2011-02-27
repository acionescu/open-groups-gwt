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
package ro.zg.opengroups.gwt.client.views.factories;

import java.util.HashMap;
import java.util.Map;

import ro.zg.webapp.core.shared.views.AbstractViewFactory;
import ro.zg.webapp.core.shared.views.View;

public class ViewsFactoryForCommand implements AbstractViewFactory{
    private Map<String,String> commandViewTypeMap=new HashMap<String, String>();
    private AbstractViewFactory viewsFactoryForType;
    
    public void addMapping(String commandName, String viewType) {
	commandViewTypeMap.put(commandName, viewType);
    }

    public View createView(String viewIdentifier) {
	String viewType = commandViewTypeMap.get(viewIdentifier);
	if(viewType == null) {
	    throw new IllegalArgumentException("No view type specified for command '"+viewIdentifier+"'");
	}
	return viewsFactoryForType.createView(viewType);
    }

    /**
     * @return the commandViewTypeMap
     */
    public Map<String, String> getCommandViewTypeMap() {
        return commandViewTypeMap;
    }

    /**
     * @return the viewsFactoryForType
     */
    public AbstractViewFactory getViewsFactoryForType() {
        return viewsFactoryForType;
    }

    /**
     * @param commandViewTypeMap the commandViewTypeMap to set
     */
    public void setCommandViewTypeMap(Map<String, String> commandViewTypeMap) {
        this.commandViewTypeMap = commandViewTypeMap;
    }

    /**
     * @param viewsFactoryForType the viewsFactoryForType to set
     */
    public void setViewsFactoryForType(AbstractViewFactory viewsFactoryForType) {
        this.viewsFactoryForType = viewsFactoryForType;
    }

}

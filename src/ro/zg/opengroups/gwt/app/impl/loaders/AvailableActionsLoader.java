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

import java.util.Map;

import ro.zg.opengroups.gwt.app.impl.NetcellActionExecutor;
import ro.zg.opengroups.gwt.app.impl.vo.NetcellActionResponse;
import ro.zg.util.config.ConfigParamLoader;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.webapp.core.vo.ActionContext;

public class AvailableActionsLoader implements ConfigParamLoader{
    private static String GET_ALL_AVAILABLE_ACTIONS = "ro.problems.flows.get-all-available-actions";
    private NetcellActionExecutor actionExecutor;
    
    private GenericNameValueList actionsList;
    
    public AvailableActionsLoader(NetcellActionExecutor actionExecutor) {
	this.actionExecutor=actionExecutor;
    }
    
    public Object load(Map<String,Object> resources)  {
	if(actionsList == null) {
	    init();
	}
	return actionsList;
    }

    private void init() {
	ActionContext context = new ActionContext(GET_ALL_AVAILABLE_ACTIONS,null);
	NetcellActionResponse actionResponse = actionExecutor.execute(context);
	GenericNameValueContext commandResponse = actionResponse.getResponseContext();
	if(commandResponse != null) {
	    actionsList = (GenericNameValueList) commandResponse.getValue("result");
	}
    }
    
}

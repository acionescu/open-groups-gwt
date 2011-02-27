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

import ro.zg.commons.exceptions.ContextAwareException;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.webapp.core.loaders.ActionsLoader;

public class NetcellActionsLoader extends ActionsLoader{
    private AvailableActionsLoader actionsLoader;
    
    public NetcellActionsLoader(AvailableActionsLoader actionsLoader) {
	this.actionsLoader=actionsLoader;
    }

    public Object load(Map<String,Object> resources) throws ContextAwareException {
	    GenericNameValueList list = (GenericNameValueList)actionsLoader.load(resources);
	    return getActions(list);

    }

}

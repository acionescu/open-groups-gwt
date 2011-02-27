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
package ro.zg.opengroups.gwt.shared.vo;

import java.util.Map;

import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.webapp.core.shared.vo.AppState;

public class OpenGroupsAppState extends AppState{

    /**
     * 
     */
    private static final long serialVersionUID = -443685346565946634L;
    
    private ServerSerializableTypes typesLoader;

    
    public EntityList getLastResponseAsEntityList() {
	return (EntityList)getParam(OpenGroupsAppParams.LAST_COMMAND_RESPONSE);
    }
    
    public String getMainEntityId() {
	return getParam(OpenGroupsAppParams.MAIN_ENTITY_ID).toString();
    }
    
    public String getMetaEntityId() {
	return getParam(OpenGroupsAppParams.META_ENTITY_ID).toString();
    }
    
    public String getCaption(String key) {
	Map<String,String> captions = (Map<String,String>)getParam(OpenGroupsAppParams.CAPTIONS);
	return captions.get(key);
    }
}

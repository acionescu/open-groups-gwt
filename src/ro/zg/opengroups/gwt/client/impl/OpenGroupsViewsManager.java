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
package ro.zg.opengroups.gwt.client.impl;

import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.webapp.core.shared.views.support.BaseViewsManager;
import ro.zg.webapp.core.shared.vo.AppState;

public class OpenGroupsViewsManager extends BaseViewsManager{

    /**
     * 
     */
    private static final long serialVersionUID = 4780799359830671010L;

    @Override
    protected void updateCachedParams(AppState appState) {
	Entity entity = (Entity)appState.getParam(OpenGroupsAppParams.SELECTED_ENTITY);
	if(entity != null) {
	    setCachedParam(OpenGroupsParams.ENTITY_ID, entity.getId());
	}
	
    }

}

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
package ro.zg.opengroups.gwt.client.views;

import ro.zg.opengroups.gwt.client.views.support.VerticalPaneGwtView;
import ro.zg.opengroups.gwt.shared.constants.ViewsTypes;
import ro.zg.opengroups.gwt.shared.vo.EntityList;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.event.UserEvent;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EntitiesListView extends VerticalPaneGwtView<OpenGroupsAppState>{
    private FiltersListView filtersView;
    private SimpleEntitiesListView simpleListView;
    private ListPageInfoView pageInfoView;
    
    @Override
    public Widget construct() {
	filtersView=(FiltersListView)createViewForType(ViewsTypes.FILTERS_LIST_VIEW, this);
	simpleListView=(SimpleEntitiesListView)createViewForType(ViewsTypes.SIMPLE_ENTITIES_LIST_VIEW, this);
	pageInfoView=(ListPageInfoView)createViewForType(ViewsTypes.LIST_PAGE_INFO_VIEW, this);
	
	VerticalPanel container = getContainer();
	container.setStylePrimaryName("og-EntitiesList");
	container.add(filtersView.construct());
	container.add(simpleListView.construct());
	container.add(pageInfoView.construct());
	
	return container;
    }

    @Override
    public void update(OpenGroupsAppState appState) {
	EntityList entityList = appState.getLastResponseAsEntityList();
	filtersView.update(entityList.getFiltersList());
	simpleListView.update(entityList);
	pageInfoView.update(entityList);
    }
    
    @Override
    protected void handleUserEvent(UserEvent userEvent) {
	
    }
}

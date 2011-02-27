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
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.views.ViewsManager;

import com.google.gwt.user.client.ui.Panel;

public class EntityView extends VerticalPaneGwtView<OpenGroupsAppState>{
    /**
     * 
     */
    private static final long serialVersionUID = -3965792146250033036L;
    private EntityDataView dataView;
    private EntityUserActionsTabView tabActionsView;
    private EntityUserActionsFooterView footerActionsView;
    
    public EntityView(ViewsManager viewsManager) {
	setViewsManager(viewsManager);
    }
    
    @Override
    public Panel construct() {
	dataView = new EntityDataView(getViewsManager());
	tabActionsView=(EntityUserActionsTabView)createViewForType(ViewsTypes.ENTITY_USER_ACTIONS_TAB_VIEW, this);
	footerActionsView = (EntityUserActionsFooterView)createViewForType(ViewsTypes.ENTITY_USER_ACTIONS_FOOTER_VIEW, this);
	tabActionsView.getContainer().getTabBar().setStylePrimaryName("og-CommandsTabBar");
	tabActionsView.getContainer().getDeckPanel().setStylePrimaryName("og-CommandsTabPanelBottom");
	
	Panel p= getContainer();
	p.setWidth("100%");
	p.add(dataView.construct());
	p.add(footerActionsView.construct());
	p.add(tabActionsView.construct());
	return p;
    }

//    private void update(Entity entity) {
//	dataView.update(entity);
//	Map<String,CommandDefinitionsList> commandsMap=entity.getAvailableCommands();
//	tabActionsView.update(commandsMap.get(CommandsLocations.TAB));
//    }

    @Override
    public void update(OpenGroupsAppState appState) {
//	update((Entity)appState.getParam(OpenGroupsAppParams.SELECTED_ENTITY));
	dataView.update(appState);
	tabActionsView.update(appState);
    }

}

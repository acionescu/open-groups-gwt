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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ro.zg.opengroups.gwt.client.views.support.BaseGwtView;
import ro.zg.opengroups.gwt.client.views.support.TabbedPanelGwtView;
import ro.zg.opengroups.gwt.shared.constants.CommandsLocations;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.opengroups.gwt.shared.constants.UserEventParams;
import ro.zg.opengroups.gwt.shared.constants.ViewsTypes;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.event.UserEvent;
import ro.zg.webapp.core.shared.vo.CommandDefinition;
import ro.zg.webapp.core.shared.vo.CommandDefinitionsList;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TabPanel;

public class EntityUserActionsTabView extends TabbedPanelGwtView<OpenGroupsAppState> {
    /**
     * 
     */
    private static final long serialVersionUID = 6377676785969446197L;
    public static final String COMMANDS_TAB="COMMANDS_TAB";
    
    private HandlerRegistration handlerReg;
    private List<Integer> currentActionPath=new ArrayList<Integer>();
    
    public EntityUserActionsTabView() {
	
    }
    
    @Override
    public TabPanel construct() {
	return getContainer();
	
    }
    
    private void update(CommandDefinitionsList cdl, List<Integer> desiredActionPath, List<Integer> currentActionPath) {
	TabPanel container = getContainer();
	this.currentActionPath=currentActionPath;
	if(handlerReg!=null) {
	    handlerReg.removeHandler();
	}
	container.clear();
	addCommandDefinitionsList(cdl, desiredActionPath, container);
    }

    private void addCommandDefinitionsList(final CommandDefinitionsList cdl, final List<Integer> desiredActionPath,
	    final TabPanel tabPanel) {
	final Map<Integer,CommandDefinition> commandsMap=new LinkedHashMap<Integer, CommandDefinition>();
	final Map<Integer,BaseGwtView> viewsMap=new HashMap<Integer, BaseGwtView>();
	
	if (cdl != null) {
	    int tabIndex=0;
	    for (CommandDefinition cd : cdl.getCommandDefinitions().values()) {
		commandsMap.put(tabIndex, cd);
		if (cd instanceof CommandDefinitionsList) {
		    EntityUserActionsTabView newView=(EntityUserActionsTabView)createViewForType(ViewsTypes.ENTITY_USER_ACTIONS_TAB_VIEW, this);
		    TabPanel currentTabPanel = newView.construct();
		    currentTabPanel.getTabBar().setStylePrimaryName("og-CommandsTabBar");
		    currentTabPanel.getDeckPanel().setStylePrimaryName("og-CommandsTabPanelBottom");
		    tabPanel.add(currentTabPanel, cd.getDisplayName());
		    viewsMap.put(tabIndex, newView);
		} else {
		    BaseGwtView newView = (BaseGwtView)createViewForCommand(cd.getActionName(), this);
		    tabPanel.add(newView.construct(), cd.getDisplayName());
		    viewsMap.put(tabIndex, newView);
		}
		tabIndex++;
	    }
	    /* set the selection listener */
	    handlerReg = tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
		public void onSelection(SelectionEvent<Integer> event) {
		    Integer si = event.getSelectedItem();
		    updateCurrentActionPath(si);
		    BaseGwtView view = viewsMap.get(si);
		    if(view instanceof EntityUserActionsTabView) {
			((EntityUserActionsTabView) view).update((CommandDefinitionsList)commandsMap.get(si), desiredActionPath, new ArrayList<Integer>(currentActionPath));
		    }
		    else {
			CommandDefinition cd = commandsMap.get(si);
			UserEvent userEvent = new UserEvent();
			userEvent.setSourceViewType(getViewType());
			userEvent.setElementId(COMMANDS_TAB);
			userEvent.setEventType(UserEvent.CLICK);
			userEvent.addEventParam(UserEventParams.COMMAND_ID, cd.getUniqueCommandDesc());
			userEvent.addEventParam(OpenGroupsParams.CURRENT_TAB_ACTION_PATH, new ArrayList<Integer>(currentActionPath));
			userEvent.setTargetViewId(view.getId());
			System.out.println(userEvent.getFullEventType());
			dispatchEventToManager(userEvent);
		    }
		}
	    });
	    /* if didn't tell otherwise, select the first action */
	    int nextIndex = 0;
	    /* select the desired action */
	    if(desiredActionPath != null && desiredActionPath.size()>0) {
		nextIndex=desiredActionPath.remove(0);
	    }
	    currentActionPath.add(nextIndex);
	    tabPanel.selectTab(nextIndex);
	}
    }
    
    private void updateCurrentActionPath(int newIndex) {
	int size=currentActionPath.size();
	/* the size should be > 0 */
	currentActionPath.remove(size-1);
	currentActionPath.add(newIndex);
    }

    @Override
    public void update(OpenGroupsAppState appState) {
	
	Entity entity = (Entity) appState.getParam(OpenGroupsAppParams.SELECTED_ENTITY);
	Map<String, CommandDefinitionsList> commandsMap = entity.getAvailableCommands();
	update(commandsMap.get(CommandsLocations.TAB), entity.getState().getDesiredActionIndexes(), new ArrayList<Integer>());
    }
}

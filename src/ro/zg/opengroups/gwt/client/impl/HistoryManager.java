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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.webapp.core.shared.event.EventListener;
import ro.zg.webapp.core.shared.event.UserEvent;
import ro.zg.webapp.core.shared.views.UserEventsManager;
import ro.zg.webapp.core.shared.views.ViewState;
import ro.zg.webapp.core.shared.views.support.ViewStateChangedEvent;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class HistoryManager implements EventListener<ViewStateChangedEvent>, ValueChangeHandler<String> {
    public static final String URL_FRAGMENT_SEPARATOR = "*";
    public static final String TAB_ACTION_SEPARATOR = "/";
    public static final String URL_FRAGMENT_SEPARATOR_REGEX="\\"+URL_FRAGMENT_SEPARATOR;
    
    private UserEventsManager userEventsManager;

    /**
     * Called when the ViewState changed
     */
    public void onEvent(ViewStateChangedEvent event) {
	System.out.println("view state changed: " + event.getViewState().getCachedParams());
	updateUrlFragment(event.getViewState());
    }

    private void updateUrlFragment(ViewState viewState) {
	String currentFragment = History.getToken();
	String newFragment = encodeUrlFragment(viewState);

	if (!currentFragment.equals(newFragment)) {
	    History.newItem(newFragment, false);
	}

    }

    private String encodeUrlFragment(ViewState viewState) {
	Integer entityId = (Integer) viewState.getCachedParam(OpenGroupsParams.ENTITY_ID);
	List<Integer> currentTabActionPath = (List<Integer>) viewState
		.getCachedParam(OpenGroupsParams.CURRENT_TAB_ACTION_PATH);

	String fragment = "";
	if (entityId == null || currentTabActionPath == null || currentTabActionPath.size() == 0) {
	    return fragment;
	}

	fragment += entityId;
	fragment += URL_FRAGMENT_SEPARATOR + encodeTabActionPath(currentTabActionPath);

	return fragment;
    }

    private String encodeTabActionPath(List<Integer> actionPath) {
	String path = actionPath.remove(0).toString();
	for (Integer i : actionPath) {
	    path += TAB_ACTION_SEPARATOR + i;
	}
	return path;
    }

    private Map<String, Object> decodeUrlFragment(String input) {
	Map<String, Object> params = new HashMap<String, Object>();
	if (input == null || "".equals(input.trim())) {
	    return params;
	}
	input = input.trim();
	String tokens[] = input.split(URL_FRAGMENT_SEPARATOR_REGEX);
	int size=tokens.length;
	if(size > 0) {
	    Integer entityId = decodeInteger(tokens[0]);
	    if(entityId == null) {
		return params;
	    }
	    params.put(OpenGroupsParams.ENTITY_ID, entityId);
	}
	if(size > 1) {
	    params.put(OpenGroupsParams.DESIRED_TAB_ACTION_PATH, decodeTabActionPath(tokens[1]));
	}
	return params;
    }

    private Integer decodeInteger(String input) {
	if(input == null) {
	    return null;
	}
	input=input.trim();
	try {
	    Integer integer = new Integer(input);
	    return integer;
	} catch (Exception e) {
	    return null;
	}
    }

    private List<Integer> decodeTabActionPath(String input) {
	System.out.println("decode tab action path : "+input);
	if (input == null || "".equals(input.trim())) {
	    return null;
	}
	input = input.trim();
	List<Integer> path = new ArrayList<Integer>();
	String[] pathArray = input.split(TAB_ACTION_SEPARATOR);

	for (String token : pathArray) {
	    Integer actionIndex = decodeInteger(token);
	    if(actionIndex == null) {
		return path;
	    }
	    path.add(actionIndex);
	}
	return path;
    }

    /**
     * Called when history (url fragment) is modified by user/browser
     */
    public void onValueChange(ValueChangeEvent<String> event) {
	Map<String,Object> params = decodeUrlFragment(event.getValue());
	System.out.println("history changed: " + event.getValue()+ "-> "+params);
	UserEvent userEvent = new UserEvent();
	userEvent.setEventType(UserEvent.HISTORY_CHANGED);
	userEvent.setEventParams(params);
	/* pass the event to the UserEventsManager for handling */
	userEventsManager.handleUserEvent(userEvent);
    }

    /**
     * @return the userEventsManager
     */
    public UserEventsManager getUserEventsManager() {
        return userEventsManager;
    }

    /**
     * @param userEventsManager the userEventsManager to set
     */
    public void setUserEventsManager(UserEventsManager userEventsManager) {
        this.userEventsManager = userEventsManager;
    }
    
}

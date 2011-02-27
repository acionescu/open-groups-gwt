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
package ro.zg.opengroups.gwt.app.impl.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.zg.opengroups.gwt.app.impl.constants.OpenGroupsRuntimeConfigParams;
import ro.zg.opengroups.gwt.app.impl.support.VoSupport;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.opengroups.gwt.shared.vo.EntityList;
import ro.zg.opengroups.gwt.shared.vo.EntityState;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.webapp.core.contract.CommandHandler;
import ro.zg.webapp.core.shared.vo.AppState;
import ro.zg.webapp.core.shared.vo.CommandDefinitionsList;
import ro.zg.webapp.core.vo.ActionResponse;
import ro.zg.webapp.core.vo.AppContext;
import ro.zg.webapp.core.vo.CommandContext;

public abstract class BaseOpenGroupsCommandHandler implements CommandHandler {
    private static final String GET_ENTITY_INFO_FLOW = "ro.problems.flows.get-entity-info-by-id";
    private static final String GET_STATUSES_FLOW = "ro.problems.flows.get-statuses";
    private static final String GET_TAGS_FLOW = "ro.problems.flows.get-tags";

    public Entity getEntityForId(int entityId, CommandContext commandContext) {
	Entity entity = new Entity(entityId);
	refreshEntity(entity, commandContext);
	return entity;
    }

    public void loadEntityData(int entityId, CommandContext commandContext) {
	Entity entity = getEntityForId(entityId, commandContext);
	entity.setAvailableCommands(getAvailableCommands(entity.getComplexType(), commandContext));

	AppState appState = commandContext.getAppState();

	/* set entity */
	addOpenEntity(entity.getId(), appState);
	/* set desiredTabActionPath */
	List<Integer> desiredTabActionPath = (List<Integer>) commandContext
		.getCommandParam(OpenGroupsParams.DESIRED_TAB_ACTION_PATH);
	if (desiredTabActionPath != null && desiredTabActionPath.size() > 0) {
	    EntityState entityState = entity.getState();
	    entityState.setDesiredActionIndexes(desiredTabActionPath);
	}
	appState.putParam(OpenGroupsAppParams.SELECTED_ENTITY, entity);
    }

    public void refreshEntity(Entity entity, CommandContext commandContext) {
	refreshEntity(entity, commandContext.getAppContext(), commandContext.getAppState());
    }

    public void refreshEntity(Entity entity, AppContext appContext, AppState appState) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put(OpenGroupsParams.ENTITY_ID, entity.getId());
	params.put(OpenGroupsParams.USER_ID, appState.getParam(OpenGroupsAppParams.USER_ID));
	ActionResponse<?> actionResponse = appContext.executeAction(GET_ENTITY_INFO_FLOW, params);
	GenericNameValueList result = (GenericNameValueList) actionResponse.getResponseContext().getValue("result");
	if (result != null) {
	    VoSupport.updateEntity(entity, (GenericNameValueContext) result.getValueForIndex(0), appContext);
	} else {
	    throw new RuntimeException("Failed to retrieve entity info for id " + entity.getId());
	}
    }

    public EntityList buildEntityListFromResponse(GenericNameValueContext dataContext, boolean showEntityType,
	    AppContext appContext) {
	return VoSupport.buildEntityList(dataContext, showEntityType, appContext);
    }

    public Object getAppConfigParam(CommandContext cc, String appConfigParam) {
	Map<String, Object> appConfigParams = (Map<String, Object>) cc
		.getRuntimeAppParam(OpenGroupsRuntimeConfigParams.APP_CONFIG_PARAMS);
	return appConfigParams.get(appConfigParam);
    }

    public static List<String> getSubtypesForComplexType(AppContext appContext, String complexType) {
	Map<String, List<String>> subtypesMap = (Map<String, List<String>>) appContext
		.getRuntimeAppParam(OpenGroupsRuntimeConfigParams.ENTITIES_TYPES_RELATIONS);
	return subtypesMap.get(complexType);
    }

    public static Object getComplexEntityParam(AppContext appContext, String complexType, String paramName) {
	Map<String, GenericNameValueContext> complexEntitiesParams = (Map<String, GenericNameValueContext>) appContext
		.getRuntimeAppParam(OpenGroupsRuntimeConfigParams.COMPLEX_ENTITIES_TYPES);
	return complexEntitiesParams.get(complexType).getValue(paramName);
    }

    public static Boolean getComplexEntityBooleanParam(AppContext appContext, String complexType, String paramName) {
	Object paramValue = getComplexEntityParam(appContext, complexType, paramName);
	String value = paramValue.toString();
	if ("y".equals(value)) {
	    return true;
	} else if ("n".equals(value)) {
	    return false;
	}
	return (Boolean) paramValue;
    }

    public void addOpenEntity(int id, AppState appState) {
	List<Integer> openEntities = (List<Integer>) appState.getParam(OpenGroupsAppParams.OPEN_ENTITIES_IDS);
	if (openEntities == null) {
	    openEntities = new ArrayList<Integer>();
	    appState.putParam(OpenGroupsAppParams.OPEN_ENTITIES_IDS, openEntities);
	}
	openEntities.add(id);
    }

    public static void addCaption(String messageKey, CommandContext commandContext) {
	AppState appState = commandContext.getAppState();
	AppContext appContext = commandContext.getAppContext();
	String caption = appContext.getMessage(messageKey);
	Map<String, String> captions = (Map<String, String>) appState.getParam(OpenGroupsAppParams.CAPTIONS);
	if (captions == null) {
	    captions = new HashMap<String, String>();
	    appState.putParam(OpenGroupsAppParams.CAPTIONS, captions);
	}
	captions.put(messageKey, caption);
    }

    public Map<String, CommandDefinitionsList> getAvailableCommands(String complexEntityType,
	    CommandContext commandContext) {
	return commandContext.getAvailableCommands(complexEntityType);
    }

    public Map<String, CommandDefinitionsList> getGlobalActions(CommandContext commandContext) {
	return commandContext.getAvailableCommands("*");
    }

    public GenericNameValueList getStatuses(CommandContext commandcontext) {
	ActionResponse<?> actionResponse = commandcontext.executeAction(GET_STATUSES_FLOW,
		new HashMap<String, Object>());
	return (GenericNameValueList) actionResponse.getResponseContext().getValue("result");
    }

    public GenericNameValueList getTags(CommandContext commandcontext) {
	ActionResponse<?> actionResponse = commandcontext.executeAction(GET_TAGS_FLOW, new HashMap<String, Object>());
	return (GenericNameValueList) actionResponse.getResponseContext().getValue("result");
    }
}

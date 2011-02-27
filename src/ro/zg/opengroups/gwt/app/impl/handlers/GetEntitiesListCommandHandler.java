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

import java.util.Map;

import ro.zg.opengroups.gwt.app.impl.constants.ComplexEntityParam;
import ro.zg.opengroups.gwt.shared.constants.MessagesKeys;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsCommandsParams;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsParams;
import ro.zg.opengroups.gwt.shared.vo.EntityList;
import ro.zg.opengroups.gwt.shared.vo.Filter;
import ro.zg.opengroups.gwt.shared.vo.FiltersList;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.webapp.core.vo.ActionResponse;
import ro.zg.webapp.core.vo.AppContext;
import ro.zg.webapp.core.vo.CommandContext;

public class GetEntitiesListCommandHandler extends BaseOpenGroupsCommandHandler {

    public void handle(CommandContext commandContext) {
	FiltersList filtersList = getFiltersList(commandContext);	
	Map<String,Object> params = commandContext.getCommandParams();
	initParams(params);
	params.putAll(filtersList.getSelectedValues());
	String targetEntityComplexType = commandContext.getTargetEntityComplexType();
	params.put(OpenGroupsParams.WITH_CONTENT, getComplexEntityBooleanParam(commandContext.getAppContext(), targetEntityComplexType, ComplexEntityParam.LIST_WITH_CONTENT));
	
	ActionResponse<?> response = commandContext.executeCommand(params);
	
	boolean showEntityType = "*".equals(targetEntityComplexType);
	
	EntityList entityList = buildEntityListFromResponse(response.getResponseContext(), showEntityType, commandContext.getAppContext());
	entityList.setCurrentPage((Integer)params.get(OpenGroupsParams.PAGE_NUMBER));
	entityList.setItemsOnPage((Integer)params.get(OpenGroupsParams.ITEMS_ON_PAGE));
	entityList.setFiltersList(filtersList);
	
	commandContext.putAppStateParam(OpenGroupsAppParams.LAST_COMMAND_RESPONSE, entityList);
    }

    private void initParams(Map<String,Object> params) {
	if(!params.containsKey(OpenGroupsParams.PAGE_NUMBER)) {
	    params.put(OpenGroupsParams.PAGE_NUMBER, 1);
	}
	if(!params.containsKey(OpenGroupsParams.ITEMS_ON_PAGE)) {
	    params.put(OpenGroupsParams.ITEMS_ON_PAGE, 10);
	}
	params.put(OpenGroupsParams.PARENT_ID, params.remove(OpenGroupsParams.ENTITY_ID));
    }
    
    private FiltersList getFiltersList(CommandContext commandContext) {
	FiltersList filtersList = new FiltersList();
	initFiltersList(commandContext, filtersList);
	Map<String,Object> filterValues = (Map<String,Object>) commandContext.removeCommandParam(OpenGroupsCommandsParams.FILTERS_VALUES);
	filtersList.update(filterValues);
	return filtersList;
    }
    

    private void initFiltersList(CommandContext commandContext, FiltersList filtersList) {
	String targetEntityComplexType = (String) commandContext.getTargetEntityComplexType();
//		.getCommandParam(OpenGroupsParams.TARGET_ENTITY_COMPLEX_TYPE);
	Integer userId = (Integer) commandContext.getCommandParam(OpenGroupsParams.USER_ID);
	AppContext appContext = commandContext.getAppContext();
	/* depth filter */
	boolean allowRecursiveList = getComplexEntityBooleanParam(appContext, targetEntityComplexType,
		ComplexEntityParam.ALLOW_RECURSIVE_LIST);
	if (allowRecursiveList) {
	    Filter f = filtersList.getFilter(OpenGroupsParams.DEPTH);
	    if (f == null) {
		filtersList.addFilter(getDepthFilter(commandContext, targetEntityComplexType));
	    }
	}
	/* my status filter */
	boolean allowStatus = getComplexEntityBooleanParam(appContext, targetEntityComplexType,
		ComplexEntityParam.ALLOW_STATUS);

	if (allowStatus) {
	    boolean initMyStatusFilter = false;
	    /* my status can only be shown if the user is logged in, because is the user's status */
	    if (userId != null) {
		Filter myStatusFilter = filtersList.getFilter(OpenGroupsParams.STATUS);
		if (myStatusFilter == null) {
		    initMyStatusFilter = true;
		}
	    }

	    Filter globalStatusFilter = filtersList.getFilter(OpenGroupsParams.GLOBAL_STATUS);

	    if (globalStatusFilter == null || initMyStatusFilter) {
		GenericNameValueList statusesList = getStatuses(commandContext);
		if (initMyStatusFilter) {
		    filtersList.addFilter(getStatusFilter(commandContext, statusesList));
		}
		if (globalStatusFilter == null) {
		    filtersList.addFilter(getGlobalStatusFilter(commandContext, statusesList));
		}
	    }
	}

	boolean allowTag = getComplexEntityBooleanParam(appContext, targetEntityComplexType,
		ComplexEntityParam.ALLOW_TAG);
	if (allowTag) {
	    Filter f = filtersList.getFilter(OpenGroupsParams.TAG);
	    if (f == null) {
		GenericNameValueList tagsList = getTags(commandContext);
		filtersList.addFilter(getTagsFilter(commandContext, tagsList));
	    }
	}

	filtersList.addFilter(getSearchFilter(commandContext));
    }

    private Filter getDepthFilter(CommandContext commandContext, String targetEntityComplexType) {
	Filter filter = new Filter();
	filter.setParamName(OpenGroupsParams.DEPTH);
	filter.setCaption(commandContext.getMessage(MessagesKeys.BY_DEPT));
	filter.addFilterOption(commandContext.getMessage(MessagesKeys.LIST_FIRST_LEVEL
		+ targetEntityComplexType.toLowerCase()), "0");
	filter.addFilterOption(
		commandContext.getMessage(MessagesKeys.LIST_ALL + targetEntityComplexType.toLowerCase()), null);
	return filter;
    }

    private Filter getStatusFilter(CommandContext commandContext, GenericNameValueList statusesList) {
	Filter filter = new Filter();
	filter.setParamName(OpenGroupsParams.STATUS);
	filter.setCaption(commandContext.getMessage(MessagesKeys.BY_MY_STATUS));
	filter.addFilterOption("", null);
	for (int i = 0; i < statusesList.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) statusesList.getValueForIndex(i);
	    String status = (String) row.getValue("status");
	    filter.addFilterOption(status, status);
	}

	return filter;
    }

    private Filter getGlobalStatusFilter(CommandContext commandContext, GenericNameValueList statusesList) {
	Filter filter = new Filter();
	filter.setParamName(OpenGroupsParams.GLOBAL_STATUS);
	filter.setCaption(commandContext.getMessage(MessagesKeys.BY_GLOBAL_STATUS));
	filter.addFilterOption("", null);
	for (int i = 0; i < statusesList.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) statusesList.getValueForIndex(i);
	    String status = (String) row.getValue("status");
	    filter.addFilterOption(status, status);
	}

	return filter;
    }

    private Filter getTagsFilter(CommandContext commandContext, GenericNameValueList tagsList) {
	Filter filter = new Filter();
	filter.setParamName(OpenGroupsParams.TAG);
	filter.setCaption(commandContext.getMessage(MessagesKeys.BY_TAG));
	for (int i = 0; i < tagsList.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) tagsList.getValueForIndex(i);
	    String status = (String) row.getValue("tag");
	    filter.addFilterOption(status, status);
	}
	return filter;
    }

    private Filter getSearchFilter(CommandContext commandContext) {
	Filter filter = new Filter();
	filter.setParamName(OpenGroupsParams.SEARCH_STRING);
	filter.setCaption(commandContext.getMessage(MessagesKeys.SEARCH));
	return filter;
    }
}

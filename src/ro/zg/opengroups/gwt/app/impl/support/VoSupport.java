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
package ro.zg.opengroups.gwt.app.impl.support;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ro.zg.opengroups.gwt.app.impl.constants.ComplexEntityParam;
import ro.zg.opengroups.gwt.app.impl.handlers.BaseOpenGroupsCommandHandler;
import ro.zg.opengroups.gwt.shared.constants.MessagesKeys;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.opengroups.gwt.shared.vo.EntityList;
import ro.zg.opengroups.gwt.shared.vo.EntityUserData;
import ro.zg.opengroups.gwt.shared.vo.Tag;
import ro.zg.util.data.GenericNameValue;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.webapp.core.vo.AppContext;

public class VoSupport {

    public static Tag buildTag(GenericNameValueContext dataMap) {
	Tag tag = new Tag();
	tag.setId((Long) dataMap.getValue("id"));
	tag.setTagName((String) dataMap.getValue("tag"));
	return tag;
    }

    public static List<Tag> buildTagsList(GenericNameValueList list) {
	List<Tag> tags = new ArrayList<Tag>();
	if (list != null) {
	    for (int i = 0; i < list.size(); i++) {
		tags.add(buildTag((GenericNameValueContext) list.getValueForIndex(i)));
	    }
	}
	return tags;
    }

    public static EntityUserData buildEntityUserData(GenericNameValueContext dataMap) {
	EntityUserData eud = new EntityUserData();
	eud.setVote((String) dataMap.getValue("user_vote"));
	Long priority = (Long) dataMap.getValue("priority");
	if (priority != null) {
	    eud.setPriority(priority.intValue());
	}
	eud.setStatus((String) dataMap.getValue("status"));
	eud.setLastVoteUpdate((Timestamp) dataMap.getValue("last_vote_update"));
	eud.setLastPriorityUpdate((Timestamp) dataMap.getValue("last_priority_update"));
	eud.setLastStatusUpdate((Timestamp) dataMap.getValue("last_status_update"));

	boolean recordCreated = (eud.getLastVoteUpdate() != null) || (eud.getLastPriorityUpdate() != null)
		|| (eud.getLastStatusUpdate() != null);
	eud.setRecordCreated(recordCreated);
	return eud;
    }

    public static Entity buildEntity(GenericNameValueContext dataMap, AppContext appContext) {
	Entity entity = new Entity();
	updateEntity(entity, dataMap, appContext);
	return entity;
    }

    public static void updateEntity(Entity entity, GenericNameValueContext dataMap, AppContext appContext) {
	// System.out.println("Update entity " + entity + " from " + dataMap);
	entity.setId(((Long) dataMap.getValue("id")).intValue());
	entity.setComplexType((String) dataMap.getValue("complex_type"));
	entity.setTitle((String) dataMap.getValue("title"));
	entity.setContent((String) dataMap.getValue("content"));
	entity.setContentPreview((String) dataMap.getValue("content_preview"));
	entity.setInsertDate((Timestamp) dataMap.getValue("insert_date"));
	entity.setSubtypesCount((Integer) dataMap.getValue("subtypes_count"));
	entity.setProVotes((Long) dataMap.getValue("pro_votes"));
	entity.setOpposedVotes((Long) dataMap.getValue("opposed_votes"));
	entity.setTotalVotes((Long) dataMap.getValue("total_votes"));
	entity.setCreatorId((Long) dataMap.getValue("creator_id"));
	if (dataMap.getValue("general_priority") != null) {
	    Integer genPriority = new Integer(dataMap.getValue("general_priority").toString());
	    if (genPriority != null) {
		entity.setGeneralPriority(genPriority);
	    }
	} else {
	    entity.setGeneralPriority(-1);
	}
	if (dataMap.getValue("general_status") != null) {
	    entity.setGeneralStatus((String) dataMap.getValue("general_status"));
	} else {
	    entity.setGeneralStatus(null);
	}

	entity.setUserData(buildEntityUserData(dataMap));
	initSubtypeEntitiesInfo(entity, dataMap);
	/* if displayed in the recent activity list */
	String parentEntityTitle = (String) dataMap.getValue("parent_title");
	entity.setParentEntityTitle(parentEntityTitle);
	if (parentEntityTitle != null) {
	    Long parentEntityId = (Long) dataMap.getValue("parent_entity_id");
	    entity.setParentEntityId(parentEntityId);
	    entity.setLastActionId(Integer.parseInt(dataMap.getValue("action_type_id").toString()));
	    entity.setLastActionType((String) dataMap.getValue("action_type"));
	    entity.addCaption(MessagesKeys.LAST_ACTION_TYPE,appContext.getMessage(entity.getLastActionType()));
	} else {
	    entity.setParentEntityId(-1);
	    entity.setLastActionId(-1);
	    entity.setLastActionType(null);
	}
	String complexType = entity.getComplexType();
	entity.setPostInfoVisible(BaseOpenGroupsCommandHandler.getComplexEntityBooleanParam(appContext, complexType, ComplexEntityParam.SHOW_POST_INFO));
	entity.setVotingAllowed(BaseOpenGroupsCommandHandler.getComplexEntityBooleanParam(appContext, complexType, ComplexEntityParam.ALLOW_VOTING));
	entity.setStatusAllowed(BaseOpenGroupsCommandHandler.getComplexEntityBooleanParam(appContext, complexType, ComplexEntityParam.ALLOW_STATUS));
	
	entity.addCaption(MessagesKeys.TAGS, appContext.getMessage(MessagesKeys.TAGS));
	entity.addCaption(MessagesKeys.VOTES, appContext.getMessage(MessagesKeys.VOTES));
	entity.addCaption(MessagesKeys.STATUS, appContext.getMessage(MessagesKeys.STATUS));
	entity.addCaption(MessagesKeys.PRIORITY, appContext.getMessage(MessagesKeys.PRIORITY));
	
	for(String subtype : entity.getSubtypeEntitiesCount().keySet()) {
	    String key = MessagesKeys.SUBTYPE_PREFIX+subtype;
	    entity.addCaption(key, appContext.getMessage(key));
	}
    }

    private static void initSubtypeEntitiesInfo(Entity entity, GenericNameValueContext dataMap) {
	String suffix = "_subtype_count";
	String recursiveSuffinx = "_recursive" + suffix;
	for (Object o : dataMap.getParametersAsList()) {
	    GenericNameValue p = (GenericNameValue) o;
	    String name = p.getName();
	    /* get recursive subtypes count */
	    int index = name.indexOf(recursiveSuffinx);
	    if (index > 0) {
		String subtype = name.substring(0, index);
		entity.addRecursiveSubtypeEntityCount(subtype, ((Long) dataMap.getValue(name)).intValue());
		continue;
	    }
	    /* get first level subtypes count */
	    index = name.indexOf(suffix);
	    if (index > 0) {
		String subtype = name.substring(0, index);
		entity.addSutypeEntityCount(subtype, ((Long) dataMap.getValue(name)).intValue());
	    }
	}
    }

    public static EntityList buildEntityList(GenericNameValueContext dataContext, boolean showEntityType,
	    AppContext appContext) {
	GenericNameValueList list = (GenericNameValueList) dataContext.getValue("result");
	EntityList entityList = buildEntityList(list, showEntityType, appContext);
	Object totalItemsCount = dataContext.getValue("totalItemsCount");
	if (totalItemsCount != null) {
	    entityList.setTotalItemsCount(Integer.parseInt(totalItemsCount.toString()));
	}
	return entityList;
    }

    public static EntityList buildEntityList(GenericNameValueList sourceList, boolean showEntityType,
	    AppContext appContext) {
	EntityList entityList = new EntityList();

	for (int i = 0; i < sourceList.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) sourceList.getValueForIndex(i);
	    Entity currentEntity = buildEntity(row, appContext);
	    if (showEntityType) {
		currentEntity.getState().setEntityTypeVisible(showEntityType);
		currentEntity.addCaption(MessagesKeys.ENTITY_TYPE,appContext.getMessage(currentEntity.getComplexType()));
	    }
	    entityList.addItem(currentEntity);
	}
	return entityList;
    }
}

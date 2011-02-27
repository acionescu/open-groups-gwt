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

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ro.zg.webapp.core.shared.vo.CommandDefinitionsList;

public class Entity implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5161673096891817997L;

    private int id;
    private String complexType;
    private String simpleType;
    private String title;
    private String content;
    private String contentPreview;
    private Timestamp insertDate;
    private int subtypesCount;
    private Map<String, Integer> subtypeEntitiesCount = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> recursiveSubtypeEntitiesCount = new LinkedHashMap<String, Integer>();
    private long proVotes;
    private long opposedVotes;
    private long totalVotes;
    private long creatorId;
    private int generalPriority=-1;
    private String generalStatus;
    private List<Tag> tags = new ArrayList<Tag>();
    /* these are populated when the entity is displayed in the recent activity list */
    private long parentEntityId = -1L;
    private String parentEntityTitle;
    private int lastActionId = -1;
    private String lastActionType;
    private boolean postInfoVisible;
    private boolean votingAllowed;
    private boolean statusAllowed;
    private EntityUserData userData;
    private EntityState state = new EntityState();
    private Map<String, CommandDefinitionsList> availableCommands;
    
    /**
     * Holds the different captions needed to present this entity in the interface
     */
    private Map<String,String> captions = new HashMap<String, String>();
    
    
    
    public Entity() {

    }

    public Entity(int id) {
	this.id = id;
    }

    public Entity(int id, String complexType) {
	this.id = id;
	setComplexType(complexType);
    }   
        
    public void addCaption(String key, String value) {
	captions.put(key, value);
    }
    
    public String getCaption(String key) {
	return captions.get(key);
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return the complexType
     */
    public String getComplexType() {
        return complexType;
    }
    /**
     * @return the simpleType
     */
    public String getSimpleType() {
        return simpleType;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @return the contentPreview
     */
    public String getContentPreview() {
        return contentPreview;
    }
    /**
     * @return the insertDate
     */
    public Timestamp getInsertDate() {
        return insertDate;
    }
    /**
     * @return the subtypesCount
     */
    public int getSubtypesCount() {
        return subtypesCount;
    }
    /**
     * @return the subtypeEntitiesCount
     */
    public Map<String, Integer> getSubtypeEntitiesCount() {
        return subtypeEntitiesCount;
    }
    /**
     * @return the recursiveSubtypeEntitiesCount
     */
    public Map<String, Integer> getRecursiveSubtypeEntitiesCount() {
        return recursiveSubtypeEntitiesCount;
    }
    /**
     * @return the proVotes
     */
    public long getProVotes() {
        return proVotes;
    }
    /**
     * @return the opposedVotes
     */
    public long getOpposedVotes() {
        return opposedVotes;
    }
    /**
     * @return the totalVotes
     */
    public long getTotalVotes() {
        return totalVotes;
    }
    /**
     * @return the creatorId
     */
    public long getCreatorId() {
        return creatorId;
    }
    /**
     * @return the generalPriority
     */
    public int getGeneralPriority() {
        return generalPriority;
    }
    /**
     * @return the generalStatus
     */
    public String getGeneralStatus() {
        return generalStatus;
    }
    /**
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }
    /**
     * @return the parentEntityId
     */
    public long getParentEntityId() {
        return parentEntityId;
    }
    /**
     * @return the parentEntityTitle
     */
    public String getParentEntityTitle() {
        return parentEntityTitle;
    }
    /**
     * @return the lastActionId
     */
    public int getLastActionId() {
        return lastActionId;
    }
    /**
     * @return the lastActionType
     */
    public String getLastActionType() {
        return lastActionType;
    }
    /**
     * @return the userData
     */
    public EntityUserData getUserData() {
        return userData;
    }
    /**
     * @return the state
     */
    public EntityState getState() {
        return state;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @param complexType the complexType to set
     */
    public void setComplexType(String complexType) {
        this.complexType = complexType;
    }
    /**
     * @param simpleType the simpleType to set
     */
    public void setSimpleType(String simpleType) {
        this.simpleType = simpleType;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @param contentPreview the contentPreview to set
     */
    public void setContentPreview(String contentPreview) {
        this.contentPreview = contentPreview;
    }
    /**
     * @param insertDate the insertDate to set
     */
    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }
    /**
     * @param subtypesCount the subtypesCount to set
     */
    public void setSubtypesCount(int subtypesCount) {
        this.subtypesCount = subtypesCount;
    }
    /**
     * @param subtypeEntitiesCount the subtypeEntitiesCount to set
     */
    public void setSubtypeEntitiesCount(Map<String, Integer> subtypeEntitiesCount) {
        this.subtypeEntitiesCount = subtypeEntitiesCount;
    }
    /**
     * @param recursiveSubtypeEntitiesCount the recursiveSubtypeEntitiesCount to set
     */
    public void setRecursiveSubtypeEntitiesCount(Map<String, Integer> recursiveSubtypeEntitiesCount) {
        this.recursiveSubtypeEntitiesCount = recursiveSubtypeEntitiesCount;
    }
    /**
     * @param proVotes the proVotes to set
     */
    public void setProVotes(long proVotes) {
        this.proVotes = proVotes;
    }
    /**
     * @param opposedVotes the opposedVotes to set
     */
    public void setOpposedVotes(long opposedVotes) {
        this.opposedVotes = opposedVotes;
    }
    /**
     * @param totalVotes the totalVotes to set
     */
    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }
    /**
     * @param creatorId the creatorId to set
     */
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
    /**
     * @param generalPriority the generalPriority to set
     */
    public void setGeneralPriority(int generalPriority) {
        this.generalPriority = generalPriority;
    }
    /**
     * @param generalStatus the generalStatus to set
     */
    public void setGeneralStatus(String generalStatus) {
        this.generalStatus = generalStatus;
    }
    /**
     * @param tags the tags to set
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    /**
     * @param parentEntityId the parentEntityId to set
     */
    public void setParentEntityId(long parentEntityId) {
        this.parentEntityId = parentEntityId;
    }
    /**
     * @param parentEntityTitle the parentEntityTitle to set
     */
    public void setParentEntityTitle(String parentEntityTitle) {
        this.parentEntityTitle = parentEntityTitle;
    }
    /**
     * @param lastActionId the lastActionId to set
     */
    public void setLastActionId(int lastActionId) {
        this.lastActionId = lastActionId;
    }
    /**
     * @param lastActionType the lastActionType to set
     */
    public void setLastActionType(String lastActionType) {
        this.lastActionType = lastActionType;
    }
    /**
     * @param userData the userData to set
     */
    public void setUserData(EntityUserData userData) {
        this.userData = userData;
    }
    /**
     * @param state the state to set
     */
    public void setState(EntityState state) {
        this.state = state;
    }
    
    public void addSutypeEntityCount(String subtype, int count) {
	subtypeEntitiesCount.put(subtype, count);
    }
    
    public void addRecursiveSubtypeEntityCount(String subtype, int count) {
	recursiveSubtypeEntitiesCount.put(subtype, count);
    }

    /**
     * @return the availableCommands
     */
    public Map<String, CommandDefinitionsList> getAvailableCommands() {
        return availableCommands;
    }

    /**
     * @param availableCommands the availableCommands to set
     */
    public void setAvailableCommands(Map<String, CommandDefinitionsList> availableCommands) {
        this.availableCommands = availableCommands;
    }

    /**
     * @return the captions
     */
    public Map<String, String> getCaptions() {
        return captions;
    }

    /**
     * @param captions the captions to set
     */
    public void setCaptions(Map<String, String> captions) {
        this.captions = captions;
    }

    /**
     * @return the postInfoVisible
     */
    public boolean isPostInfoVisible() {
        return postInfoVisible;
    }

    /**
     * @param postInfoVisible the postInfoVisible to set
     */
    public void setPostInfoVisible(boolean postInfoVisible) {
        this.postInfoVisible = postInfoVisible;
    }

    /**
     * @return the votingAllowed
     */
    public boolean isVotingAllowed() {
        return votingAllowed;
    }

    /**
     * @param votingAllowed the votingAllowed to set
     */
    public void setVotingAllowed(boolean votingAllowed) {
        this.votingAllowed = votingAllowed;
    }

    /**
     * @return the statusAllowed
     */
    public boolean isStatusAllowed() {
        return statusAllowed;
    }

    /**
     * @param statusAllowed the statusAllowed to set
     */
    public void setStatusAllowed(boolean statusAllowed) {
        this.statusAllowed = statusAllowed;
    }

}

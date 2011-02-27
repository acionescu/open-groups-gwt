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

public class EntityUserData implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -3770751174373742374L;
    private String vote;
    private int priority;
    private String status;
    private Timestamp lastVoteUpdate;
    private Timestamp lastPriorityUpdate;
    private Timestamp lastStatusUpdate;
    private boolean recordCreated;
    
    
    public EntityUserData() {
	
    }
    
    
    /**
     * @return the vote
     */
    public String getVote() {
        return vote;
    }


    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the lastVoteUpdate
     */
    public Timestamp getLastVoteUpdate() {
        return lastVoteUpdate;
    }

    /**
     * @return the lastPriorityUpdate
     */
    public Timestamp getLastPriorityUpdate() {
        return lastPriorityUpdate;
    }

    /**
     * @return the lastStatusUpdate
     */
    public Timestamp getLastStatusUpdate() {
        return lastStatusUpdate;
    }

    /**
     * @param vote the vote to set
     */
    public void setVote(String vote) {
        this.vote = vote;
    }

   
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param lastVoteUpdate the lastVoteUpdate to set
     */
    public void setLastVoteUpdate(Timestamp lastVoteUpdate) {
        this.lastVoteUpdate = lastVoteUpdate;
    }

    /**
     * @param lastPriorityUpdate the lastPriorityUpdate to set
     */
    public void setLastPriorityUpdate(Timestamp lastPriorityUpdate) {
        this.lastPriorityUpdate = lastPriorityUpdate;
    }

    /**
     * @param lastStatusUpdate the lastStatusUpdate to set
     */
    public void setLastStatusUpdate(Timestamp lastStatusUpdate) {
        this.lastStatusUpdate = lastStatusUpdate;
    }

    /**
     * @return the recordCreated
     */
    public boolean isRecordCreated() {
        return recordCreated;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @param recordCreated the recordCreated to set
     */
    public void setRecordCreated(boolean recordCreated) {
        this.recordCreated = recordCreated;
    }
    
}

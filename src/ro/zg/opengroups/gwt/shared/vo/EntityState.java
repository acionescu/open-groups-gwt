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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the state of a selected entity in the user session
 * 
 * @author adi
 * 
 */
public class EntityState implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 901016127262257178L;
    public static final int DEFAULT_ITEMS_PER_PAGE = 10;

    private boolean opened;
    private int itemsPerPage = DEFAULT_ITEMS_PER_PAGE;
    private List<String> activeActions = new ArrayList<String>();
    private Integer currentListTotalItemsCount;
    private boolean entityTypeVisible;
    private boolean setStatusButtonVisible;

    // private List<String> desiredActionTabsQueue = new ArrayList<String>();
    // private List<String> currentActionTabsQueue = new ArrayList<String>();

    // private CommandDefinition currentTabAction;
    // private ComponentContainer currentTabActionContainer;
    // private Table childrenListContainer;

    private Map<String, Integer> currentPageForAction = new HashMap<String, Integer>();

    private List<Integer> desiredActionIndexes = new ArrayList<Integer>();
    private List<Integer> currentActionIndexes = new ArrayList<Integer>();
    
    
    public void appendCurrentActionIndex(int index) {
	currentActionIndexes.add(index);
    }
    
    public void resetCurrentActionIndexes() {
	currentActionIndexes.clear();
    }
    
    /**
     * @return the desiredActionIndexes
     */
    public List<Integer> getDesiredActionIndexes() {
	return desiredActionIndexes;
    }

    /**
     * @return the currentActionIndexes
     */
    public List<Integer> getCurrentActionIndexes() {
	return currentActionIndexes;
    }

    /**
     * @param desiredActionIndexes
     *            the desiredActionIndexes to set
     */
    public void setDesiredActionIndexes(List<Integer> desiredActionIndexes) {
	this.desiredActionIndexes = desiredActionIndexes;
    }

    /**
     * @param currentActionIndexes
     *            the currentActionIndexes to set
     */
    public void setCurrentActionIndexes(List<Integer> currentActionIndexes) {
	this.currentActionIndexes = currentActionIndexes;
    }

    /**
     * @return the opened
     */
    public boolean isOpened() {
	return opened;
    }

    /**
     * @param opened
     *            the opened to set
     */
    public void setOpened(boolean opened) {
	this.opened = opened;
    }

    public void setActionActive(String actionName) {
	activeActions.add(actionName);
    }

    public void setActionInactive(String actionName) {
	activeActions.remove(actionName);
    }

    /**
     * @return the activeActions
     */
    public List<String> getActiveActions() {
	return activeActions;
    }

    public boolean isActionActive(String actionName) {
	return activeActions.contains(actionName);
    }

    /**
     * @return the currentListTotalItemsCount
     */
    public Integer getCurrentListTotalItemsCount() {
	return currentListTotalItemsCount;
    }

    /**
     * @param currentListTotalItemsCount
     *            the currentListTotalItemsCount to set
     */
    public void setCurrentListTotalItemsCount(Integer currentListTotalItemsCount) {
	this.currentListTotalItemsCount = currentListTotalItemsCount;
    }

    /**
     * @return the entityTypeVisible
     */
    public boolean isEntityTypeVisible() {
	return entityTypeVisible;
    }

    /**
     * @param entityTypeVisible
     *            the entityTypeVisible to set
     */
    public void setEntityTypeVisible(boolean entityTypeVisible) {
	this.entityTypeVisible = entityTypeVisible;
    }

    /**
     * @return the setStatusButtonVisible
     */
    public boolean isSetStatusButtonVisible() {
	return setStatusButtonVisible;
    }

    /**
     * @param setStatusButtonVisible
     *            the setStatusButtonVisible to set
     */
    public void setSetStatusButtonVisible(boolean setStatusButtonVisible) {
	this.setStatusButtonVisible = setStatusButtonVisible;
    }

    /**
     * @return the itemsPerPage
     */
    public int getItemsPerPage() {
	return itemsPerPage;
    }

    public void setCurrentPageForAction(String actionPath, int page) {
	currentPageForAction.put(actionPath, page);
    }

    public int getCurrentPageForAction(String actionPath) {
	if (currentPageForAction.containsKey(actionPath)) {
	    return currentPageForAction.get(actionPath);
	}
	return 1;
    }

    public void resetPageInfo() {
	currentPageForAction.clear();
    }

    // public void resetPageInfoForCurrentAction() {
    // currentPageForAction.remove(getCurrentActionPath());
    // }
    //
    // public int getCurrentPageForCurrentAction() {
    // return getCurrentPageForAction(getCurrentActionPath());
    // }
    //    
    // public void setCurrentPageForCurrentAction(int page) {
    // setCurrentPageForAction(getCurrentActionPath(), page);
    // }
}

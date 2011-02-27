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


public class EntityList implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -4397961187139376157L;
    
    private ArrayList<Entity> itemsList= new ArrayList<Entity>();
    private int totalItemsCount;
    private int currentPage;
    private int itemsOnPage;
    private FiltersList filtersList;
    
    public void addItem(Entity entity) {
	itemsList.add(entity);
    }
    
    public int totalPages() {
	return (int)Math.ceil((double)totalItemsCount/itemsOnPage);
    }
    
    /**
     * @return the itemsList
     */
    public ArrayList<Entity> getItemsList() {
        return itemsList;
    }

    /**
     * @param itemsList the itemsList to set
     */
    public void setItemsList(ArrayList<Entity> itemsList) {
        this.itemsList = itemsList;
    }

    /**
     * @return the totalItemsCount
     */
    public int getTotalItemsCount() {
        return totalItemsCount;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return the itemsOnPage
     */
    public int getItemsOnPage() {
        return itemsOnPage;
    }

    /**
     * @return the filtersList
     */
    public FiltersList getFiltersList() {
        return filtersList;
    }

    /**
     * @param totalItemsCount the totalItemsCount to set
     */
    public void setTotalItemsCount(int totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @param itemsOnPage the itemsOnPage to set
     */
    public void setItemsOnPage(int itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    /**
     * @param filtersList the filtersList to set
     */
    public void setFiltersList(FiltersList filtersList) {
        this.filtersList = filtersList;
    }
    
}

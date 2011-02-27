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
import java.util.LinkedHashMap;
import java.util.Map;

public class Filter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6099845576545499640L;

    private String caption;
    private String paramName;
    private Map<String,FilterOption> filterOptions = new LinkedHashMap<String, FilterOption>();
    private String selectedValue;
    
    public Filter() {
	
    }
    
    public void addFilterOption(FilterOption fo) {
	filterOptions.put(fo.getValue(),fo);
    }

    public void addFilterOption(String caption, String value) {
	FilterOption fo = new FilterOption(caption, paramName, value);
	addFilterOption(fo);
    }

    public FilterOption getSelectedOption() {
	if (filterOptions != null ) {
	    return filterOptions.get(selectedValue);
	}
	return null;
    }

    public String getSelectedValue() {
	FilterOption fo = getSelectedOption();
	if(fo != null) {
	    return fo.getValue();
	}
	return null;
    }

    /**
     * @return the caption
     */
    public String getCaption() {
	return caption;
    }

    /**
     * @return the paramName
     */
    public String getParamName() {
	return paramName;
    }

  

    /**
     * @param caption
     *            the caption to set
     */
    public void setCaption(String caption) {
	this.caption = caption;
    }

    /**
     * @param paramName
     *            the paramName to set
     */
    public void setParamName(String paramName) {
	this.paramName = paramName;
    }

    /**
     * @return the filterOptions
     */
    public Map<String, FilterOption> getFilterOptions() {
        return filterOptions;
    }

    /**
     * @param filterOptions the filterOptions to set
     */
    public void setFilterOptions(Map<String, FilterOption> filterOptions) {
        this.filterOptions = filterOptions;
    }

    /**
     * @param selectedValue the selectedValue to set
     */
    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

}

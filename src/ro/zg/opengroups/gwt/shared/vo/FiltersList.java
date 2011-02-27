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

public class FiltersList implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1080045259574772041L;
    
    private Map<String, Filter> filters=new LinkedHashMap<String, Filter>();
    
    public void update(Map<String,Object> values) {
	if(values == null) {
	    return;
	}
	for(Map.Entry<String, Object> entry : values.entrySet()) {
	    Filter f = filters.get(entry.getKey());
	    f.setSelectedValue(entry.getValue().toString());
	}
    }
    
    public Map<String,Object> getSelectedValues(){
	Map<String,Object> values=new LinkedHashMap<String, Object>();
	for(Map.Entry<String, Filter> entry: filters.entrySet()) {
	    values.put(entry.getKey(), entry.getValue().getSelectedValue());
	}
	return values;
    }
    
    public void addFilter(Filter f) {
	filters.put(f.getParamName(),f);
    }


    public Filter getFilter(String paramName) {
	return filters.get(paramName);
    }
    
    /**
     * @return the filters
     */
    public Map<String, Filter> getFilters() {
        return filters;
    }


    /**
     * @param filters the filters to set
     */
    public void setFilters(Map<String, Filter> filters) {
        this.filters = filters;
    }
    
}

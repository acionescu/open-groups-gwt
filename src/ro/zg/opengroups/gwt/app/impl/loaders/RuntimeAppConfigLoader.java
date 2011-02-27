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
package ro.zg.opengroups.gwt.app.impl.loaders;

import java.util.HashMap;
import java.util.Map;

import ro.zg.opengroups.gwt.app.impl.NetcellActionExecutor;
import ro.zg.opengroups.gwt.app.impl.constants.OpenGroupsRuntimeConfigParams;
import ro.zg.util.config.ConfigParamLoader;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.GenericNameValueList;
import ro.zg.util.data.ListMap;

public class RuntimeAppConfigLoader implements ConfigParamLoader {
    private NetcellActionExecutor actionExecutor;

    private static final String GET_APPLICATION_CONFIG_PARAMS_FLOW = "ro.problems.flows.get-application-config-params";
    private static final String GET_COMPLEX_ENTITY_TYPES_FLOW = "ro.problems.flows.get-complex-entity-types";
    private static final String GET_ENTITIES_TYPES_RELATIONS = "ro.problems.flows.get-all-entities-types-relations";

    public RuntimeAppConfigLoader(NetcellActionExecutor actionExecutor) {
	this.actionExecutor = actionExecutor;
    }

    public Object load(Map<String,Object> resources) throws Exception {
	return loadConfig();
    }

    private GenericNameValueContext loadConfig() {
	GenericNameValueContext params = new GenericNameValueContext();
	params.put(OpenGroupsRuntimeConfigParams.APP_CONFIG_PARAMS, loadApplicationConfigParams());
	params.put(OpenGroupsRuntimeConfigParams.COMPLEX_ENTITIES_TYPES, loadComplexEntityTypes());
	params.put(OpenGroupsRuntimeConfigParams.ENTITIES_TYPES_RELATIONS, loadEntitiesTypesRelations());
	return params;
    }

    private Map<String,Object> loadApplicationConfigParams() {
	GenericNameValueContext response = actionExecutor.execute(GET_APPLICATION_CONFIG_PARAMS_FLOW, new HashMap())
		.getResponseContext();
	if (response == null) {
	    return null;
	}
	Map<String, Object> appConfigParams = new HashMap<String, Object>();

	GenericNameValueList result = (GenericNameValueList) response.getValue("result");
	for (int i = 0; i < result.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) result.getValueForIndex(i);
	    String paramName = row.getValue("param_name").toString();
	    Long intValue = (Long) row.getValue("int_value");
	    Float floatValue = (Float) row.getValue("number_value");
	    String stringValue = (String) row.getValue("string_value");
	    if (intValue != null) {
		appConfigParams.put(paramName, intValue);
	    } else if (stringValue != null) {
		appConfigParams.put(paramName, stringValue);
	    } else if (floatValue != null) {
		appConfigParams.put(paramName, floatValue);
	    }
	}
	return appConfigParams;
    }

    private Map<String,GenericNameValueContext> loadComplexEntityTypes() {
	GenericNameValueContext response = actionExecutor.execute(GET_COMPLEX_ENTITY_TYPES_FLOW, new HashMap()).getResponseContext();
	if (response == null) {
	    return null;
	}
	
	Map<String,GenericNameValueContext> params=new HashMap<String, GenericNameValueContext>();
	
	GenericNameValueList result = (GenericNameValueList) response.getValue("result");
	for (int i = 0; i < result.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) result.getValueForIndex(i);
	    params.put(row.getValue("complex_type").toString(), row);
	}
	return params;
    }

    private ListMap<String, String> loadEntitiesTypesRelations() {
	GenericNameValueContext response = actionExecutor.execute(GET_ENTITIES_TYPES_RELATIONS, new HashMap()).getResponseContext();
	if (response == null) {
	    return null;
	}
	
	ListMap<String, String> params = new ListMap<String, String>();
	
	GenericNameValueList result = (GenericNameValueList) response.getValue("result");
	for (int i = 0; i < result.size(); i++) {
	    GenericNameValueContext row = (GenericNameValueContext) result.getValueForIndex(i);
	    params.add(row.getValue("source_complex_type").toString(), row.getValue("complex_subtype")
		    .toString());
	}
	return params;
    }

}

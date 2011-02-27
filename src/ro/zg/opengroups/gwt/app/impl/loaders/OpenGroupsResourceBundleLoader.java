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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import ro.zg.util.config.ConfigParamLoader;

public class OpenGroupsResourceBundleLoader implements ConfigParamLoader{

    public Object load(Map<String,Object> resources) throws Exception {
	ResourceBundle messagesBundle=null;
	try {
	    String fileName = "OpenGroupsMessages_" + new Locale("ro", "RO") + ".properties";
	    InputStream in = (Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
	    messagesBundle = new PropertyResourceBundle(new InputStreamReader(in, "UTF-8"));
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return messagesBundle;
    }

}

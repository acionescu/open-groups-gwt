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
package ro.zg.opengroups.gwt.server;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ro.zg.netcell.client.ThinClientNetcellDao;
import ro.zg.opengroups.gwt.app.impl.NetcellActionExecutor;
import ro.zg.opengroups.gwt.app.impl.OpenGroupsActionsManager;
import ro.zg.opengroups.gwt.app.impl.OpenGroupsAppManager;
import ro.zg.opengroups.gwt.app.impl.OpenGroupsAppStateFactory;
import ro.zg.opengroups.gwt.app.impl.OpenGroupsCommandsManager;
import ro.zg.opengroups.gwt.app.impl.loaders.AvailableActionsLoader;
import ro.zg.opengroups.gwt.app.impl.loaders.NetcellActionsLoader;
import ro.zg.opengroups.gwt.app.impl.loaders.OpenGroupsCommandHandlersLoader;
import ro.zg.opengroups.gwt.app.impl.loaders.OpenGroupsCommandsLoader;
import ro.zg.opengroups.gwt.app.impl.loaders.OpenGroupsConfigLoader;
import ro.zg.opengroups.gwt.app.impl.loaders.OpenGroupsResourceBundleLoader;
import ro.zg.opengroups.gwt.app.impl.loaders.RuntimeAppConfigLoader;
import ro.zg.opengroups.gwt.client.OpenGroupsService;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.util.config.ObjectFromClassLoader;
import ro.zg.util.config.ObjectLoader;
import ro.zg.webapp.core.constants.ActionsManagerConfigParams;
import ro.zg.webapp.core.constants.AppManagerConfigParams;
import ro.zg.webapp.core.shared.vo.AppState;
import ro.zg.webapp.core.shared.vo.Command;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class OpenGroupsServiceImpl extends RemoteServiceServlet implements OpenGroupsService {

    /**
     * 
     */
    private static final long serialVersionUID = 4882771984296724087L;

    private OpenGroupsAppManager appManager;

    public void init(ServletConfig config) throws ServletException {
	super.init(config);

	appManager = new OpenGroupsAppManager();
	appManager.setAppStateFactory(new OpenGroupsAppStateFactory());
	OpenGroupsConfigLoader configLoader = new OpenGroupsConfigLoader();
	try {
	    configLoader.addResource(AppManagerConfigParams.RESOURCES_BUNDLE, new OpenGroupsResourceBundleLoader().load(null));
	} catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	ThinClientNetcellDao netcellDao = new ThinClientNetcellDao("localhost", 2000);
	NetcellActionExecutor actionExecutor = new NetcellActionExecutor(netcellDao);
	AvailableActionsLoader availableActionsLoader = new AvailableActionsLoader(actionExecutor);
	configLoader.addLoader(ActionsManagerConfigParams.ACTIONS_MAP, new NetcellActionsLoader(availableActionsLoader));
	configLoader.addLoader(ActionsManagerConfigParams.ACTION_EXECUTOR, new ObjectLoader(actionExecutor));
	configLoader.addLoader(AppManagerConfigParams.ACTIONS_MANAGER, new ObjectFromClassLoader(
		OpenGroupsActionsManager.class));
	configLoader.addLoader(AppManagerConfigParams.COMMANDS_MANAGER, new ObjectFromClassLoader(
		OpenGroupsCommandsManager.class));
	configLoader.addLoader(AppManagerConfigParams.RUNTIME_APP_CONFIG, new RuntimeAppConfigLoader(actionExecutor));
	configLoader.addLoader(AppManagerConfigParams.COMMAND_HANDLERS, new OpenGroupsCommandHandlersLoader());
	configLoader.addLoader(AppManagerConfigParams.COMMAND_MAPPINGS, new OpenGroupsCommandsLoader(availableActionsLoader));
//	configLoader.addLoader(AppManagerConfigParams.RESOURCES_BUNDLE, new OpenGroupsResourceBundleLoader());
	try {
	    appManager.init(configLoader);
	    System.out.println(appManager.getConfig());
	    System.out.println("OpenGroupsService initialized");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public OpenGroupsAppState execute(String commandId, Map<String, Object> params) throws IllegalArgumentException {
	Command command = Command.build(commandId, params);
	return execute(command);
    }
    
    public OpenGroupsAppState execute(Command command) throws IllegalArgumentException{
	System.out.println("Executing " + command.getId());
	try {
	    HttpServletRequest hsr = getThreadLocalRequest();
	    HttpSession session = hsr.getSession(true);
//	    AppState appState = (AppState)session.getAttribute("AppState");
	    OpenGroupsAppState appState = (OpenGroupsAppState)appManager.execute(command);
//	    session.setAttribute("AppState", appState);
	    return appState;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
}

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
package ro.zg.opengroups.gwt.app.impl;

import ro.zg.netcell.client.ThinClientNetcellDao;
import ro.zg.netcell.control.CommandResponse;
import ro.zg.opengroups.gwt.app.impl.vo.NetcellActionResponse;
import ro.zg.webapp.core.support.AbstractActionExecutor;
import ro.zg.webapp.core.vo.ActionContext;

public class NetcellActionExecutor extends AbstractActionExecutor<CommandResponse>{
    private ThinClientNetcellDao netcellDao;

    public NetcellActionExecutor() {
	
    }
    
    public NetcellActionExecutor(ThinClientNetcellDao dao) {
	netcellDao=dao;
    }
    
    public NetcellActionResponse execute(ActionContext context) {
	CommandResponse response = netcellDao.execute(context.getActionId(), context.getParams());
	return new NetcellActionResponse(response);
    }

    /**
     * @return the netcellDao
     */
    public ThinClientNetcellDao getNetcellDao() {
        return netcellDao;
    }

    /**
     * @param netcellDao the netcellDao to set
     */
    public void setNetcellDao(ThinClientNetcellDao netcellDao) {
        this.netcellDao = netcellDao;
    }

    
    
}

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
package ro.zg.opengroups.gwt.client.views;

import ro.zg.opengroups.gwt.client.views.support.VerticalPaneGwtView;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.views.ViewsManager;

import com.google.gwt.user.client.ui.Widget;

public class TabCommandView extends VerticalPaneGwtView<OpenGroupsAppState>{

    /**
     * 
     */
    private static final long serialVersionUID = -244984640391967316L;
    
    public TabCommandView(ViewsManager viewsManager) {
	setViewsManager(viewsManager);
    }

    @Override
    public Widget construct() {
	return getContainer();
    }

    @Override
    public void update(OpenGroupsAppState appState) {
	System.out.println("tab command update");
	
    }

}

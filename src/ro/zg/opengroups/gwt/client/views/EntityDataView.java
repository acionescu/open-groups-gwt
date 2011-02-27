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
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsAppParams;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;
import ro.zg.webapp.core.shared.views.ViewsManager;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class EntityDataView extends VerticalPaneGwtView<OpenGroupsAppState>{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 2637303406784648502L;

    public EntityDataView(ViewsManager viewsManager) {
	setViewsManager(viewsManager);
    }
    
    @Override
    public Panel construct() {
	Panel p= getContainer();
	p.setStylePrimaryName("og-EntityDataPane");
	return p;
    }

    private void update(Entity entity) {
	Panel container = getContainer();
	container.clear();
	container.add(new Label(entity.getTitle()));
	
//	FlowPanel contentWrapper = new FlowPanel();
//	contentWrapper.setStylePrimaryName("og-EntityContentWrapper");
	ScrollPanel contentPane = new ScrollPanel(new HTML(entity.getContent()));
//	contentWrapper.add(contentPane);
	contentPane.addStyleName("og-EntityContentPane");
//	container.add(contentWrapper);
	container.add(contentPane);
    }

    @Override
    public void update(OpenGroupsAppState appState) {
	update((Entity)appState.getParam(OpenGroupsAppParams.SELECTED_ENTITY));
    }
}

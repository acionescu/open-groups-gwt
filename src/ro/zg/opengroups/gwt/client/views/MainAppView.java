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
import ro.zg.opengroups.gwt.shared.constants.ViewsTypes;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;

import com.google.gwt.user.client.ui.VerticalPanel;

public class MainAppView extends VerticalPaneGwtView<OpenGroupsAppState>{
    /**
     * 
     */
    private static final long serialVersionUID = 5178002295168491915L;
    private QuickLinksView quickLinksView;
    private UserDataView userDataView;
    private EntityView entityView;

    @Override
    public VerticalPanel construct() {
	quickLinksView=(QuickLinksView)createViewForType(ViewsTypes.QUICK_LINKS_VIEW, this);
	userDataView=new UserDataView(getViewsManager());
	entityView=new EntityView(getViewsManager());
	
	VerticalPanel container = getContainer();
	container.setStylePrimaryName("og-MainPane");
	container.add(quickLinksView.construct());
	VerticalPanel dataContainer = new VerticalPanel(); 
	dataContainer.setStylePrimaryName("og-DataPane");
	dataContainer.add(userDataView.construct());
	dataContainer.add(entityView.construct());
	container.add(dataContainer);
	return container;
    }

    public void update(OpenGroupsAppState appState) {
	quickLinksView.update(appState);
	userDataView.update(appState);
	entityView.update(appState);
    }

}

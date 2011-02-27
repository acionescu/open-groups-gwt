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

import ro.zg.opengroups.gwt.client.views.support.FlowPaneGwtView;
import ro.zg.opengroups.gwt.shared.constants.MessagesKeys;
import ro.zg.opengroups.gwt.shared.vo.OpenGroupsAppState;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;

public class QuickLinksView extends FlowPaneGwtView<OpenGroupsAppState>{

    @Override
    public FlowPanel construct() {
	return getContainer();
    }

    @Override
    public void update(OpenGroupsAppState appState) {
	FlowPanel pane = getContainer();
	pane.clear();
	pane.setStyleName("og-QuickLinkPane");
	
	Hyperlink mainEntityLink = new Hyperlink(appState.getCaption(MessagesKeys.MAIN_ENTITY_CAPTION), appState.getMainEntityId());
	mainEntityLink.setStyleName("og-QuickLink");
	pane.add(mainEntityLink);
	
	Hyperlink metaEntityLink = new Hyperlink(appState.getCaption(MessagesKeys.META_ENTITY_CAPTION), appState.getMetaEntityId());
	metaEntityLink.setStyleName("og-QuickLink");
	pane.add(metaEntityLink);
	
    }

}

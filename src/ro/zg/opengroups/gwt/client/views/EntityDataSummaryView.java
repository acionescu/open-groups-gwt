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

import java.util.List;

import ro.zg.opengroups.gwt.client.views.support.VerticalPaneGwtView;
import ro.zg.opengroups.gwt.shared.constants.MessagesKeys;
import ro.zg.opengroups.gwt.shared.vo.Entity;
import ro.zg.opengroups.gwt.shared.vo.EntityState;
import ro.zg.opengroups.gwt.shared.vo.EntityUserData;
import ro.zg.opengroups.gwt.shared.vo.Tag;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EntityDataSummaryView extends VerticalPaneGwtView<Entity> {

    @Override
    public Widget construct() {
	return getContainer();
    }

    @Override
    public void update(Entity entity) {
	VerticalPanel container = getContainer();
	EntityState state = entity.getState();

	HorizontalPanel titlePane = new HorizontalPanel();
	if (state.isEntityTypeVisible()) {
	    
	    Label entityTypeLabel = new Label(entity.getCaption(MessagesKeys.ENTITY_TYPE));
	    entityTypeLabel.setStylePrimaryName("og-EntityTypeLabel");
	    titlePane.add(entityTypeLabel);
	}
	String lastActionTypeCaption = entity.getCaption(MessagesKeys.LAST_ACTION_TYPE);
	if (lastActionTypeCaption != null) {
	    Label actionTypeLabel = new Label(lastActionTypeCaption);
	    actionTypeLabel.setStylePrimaryName("og-ActionTypeLabel");
	    titlePane.add(actionTypeLabel);
	}
	titlePane.add(new Hyperlink(entity.getTitle(), "" + entity.getId()));
	container.add(titlePane);

	container.add(getTagsAndDatePane(entity));
	container.add(getStatsPane(entity));

    }

    private Widget getTagsAndDatePane(Entity entity) {
	HorizontalPanel tagsAndDatePane = new HorizontalPanel();
	tagsAndDatePane.add(getTagsPane(entity.getTags(), entity.getCaption(MessagesKeys.TAGS)));
	if (entity.isPostInfoVisible()) {
	    tagsAndDatePane.add(getPostDateInfo(entity.getInsertDate().toString()));
	}
	return tagsAndDatePane;
    }

    private Widget getPostDateInfo(String postDate) {
	postDate = postDate.split("\\.")[0];
	return new Label(postDate);
    }

    private Widget getTagsPane(List<Tag> tags, String caption) {
	HorizontalPanel tagsPane = new HorizontalPanel();
	if (tags != null && tags.size() > 0) {
	    tagsPane.add(new Label(caption));
	    for (Tag tag : tags) {
		tagsPane.add(new Label(tag.getTagName()));
	    }
	}
	return tagsPane;
    }

    private Widget getStatsPane(Entity entity) {
	HorizontalPanel statsPane = new HorizontalPanel();
	/* votes */
	String votes = entity.getCaption(MessagesKeys.VOTES) + ": ";
	votes += entity.getProVotes() + "-" + entity.getOpposedVotes();
	statsPane.add(new Label(votes));
	
	/* status */
	EntityUserData userData = entity.getUserData();
	if (entity.isStatusAllowed()) {
	    String statusInfo = "-";
	    if (userData.getStatus() != null) {
		statusInfo = userData.getStatus();
	    }
	    statusInfo +=" / ";
	    if (entity.getGeneralStatus() != null) {
		statusInfo+= entity.getGeneralStatus();
	    }
	    else {
		statusInfo += "-";
	    }
	    String statusCaption = entity.getCaption(MessagesKeys.STATUS);
	    statsPane.add(new Label(statusCaption+": "+statusInfo));
	}
	return statsPane;
    }
}

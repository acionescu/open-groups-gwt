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
package ro.zg.opengroups.gwt.client;

import ro.zg.opengroups.gwt.client.impl.HistoryManager;
import ro.zg.opengroups.gwt.client.impl.OpenGroupsViewsManager;
import ro.zg.opengroups.gwt.client.impl.events.handlers.AppLoadEventHandler;
import ro.zg.opengroups.gwt.client.impl.events.handlers.EntityActionsTabClickEventHandler;
import ro.zg.opengroups.gwt.client.impl.events.mappers.DynamicCommandSelectedMapper;
import ro.zg.opengroups.gwt.client.impl.events.mappers.StaticUserEventMapper;
import ro.zg.opengroups.gwt.client.views.MainAppView;
import ro.zg.opengroups.gwt.client.views.factories.CreateEntityWithTagsViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.EntitiesListViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.EntityDataSummaryViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.EntityUpdateViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.EntityUserActionTabViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.EntityUserActionsFooterViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.FiltersListViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.ListPageInfoViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.QuickLinksViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.SimpleEntitiesListViewFactory;
import ro.zg.opengroups.gwt.client.views.factories.ViewsFactoryForCommand;
import ro.zg.opengroups.gwt.shared.constants.OpenGroupsCommands;
import ro.zg.opengroups.gwt.shared.constants.ViewsTypes;
import ro.zg.opengroups.gwt.shared.vo.ServerSerializableTypes;
import ro.zg.webapp.core.shared.event.UserEvent;
import ro.zg.webapp.core.shared.views.ViewFactory;
import ro.zg.webapp.core.shared.views.support.BaseAbstractViewFactory;
import ro.zg.webapp.core.shared.views.support.BaseUserEventsManager;
import ro.zg.webapp.core.shared.views.support.BaseViewsManager;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class OpenGroupsApp implements EntryPoint {
    private final TypesLoaderAsync typesLoaderService = GWT.create(TypesLoader.class);
    private final OpenGroupsServiceAsync openGroupsService = GWT.create(OpenGroupsService.class);

    public void onModuleLoad() {
	// typesLoaderService.loadTypes(new ServerSerializableTypes(), new AsyncCallback<ServerSerializableTypes>() {
	//
	// public void onFailure(Throwable caught) {
	// // TODO Auto-generated method stub
	//		
	// }
	//
	// public void onSuccess(ServerSerializableTypes result) {
	// System.out.println("types loaded");
	//		
	// }
	// });

	typesLoaderService.loadTypes(new ServerSerializableTypes(), new AsyncCallback<ServerSerializableTypes>() {

	    public void onFailure(Throwable caught) {
		System.out.println("Failed to load types");
	    }

	    public void onSuccess(ServerSerializableTypes result) {
		System.out.println("Types loaded.");
	    }
	});
	final BaseUserEventsManager userEventsManager = new BaseUserEventsManager();
	final BaseViewsManager viewsManager = new OpenGroupsViewsManager();
	viewsManager.setUserEventsManager(userEventsManager);
	userEventsManager.setViewsManager(viewsManager);

	DynamicCommandSelectedMapper dcsm = new DynamicCommandSelectedMapper();
	AppLoadEventHandler appInitEventHandler = new AppLoadEventHandler(openGroupsService, new StaticUserEventMapper(
		OpenGroupsCommands.APP_INIT));
	userEventsManager.addUserEventHandler(UserEvent.LOAD_APP, appInitEventHandler);
	userEventsManager.addUserEventHandler(UserEvent.HISTORY_CHANGED, appInitEventHandler);
	
	userEventsManager.addUserEventHandler(ViewsTypes.ENTITY_USER_ACTIONS_TAB_VIEW + ".COMMANDS_TAB.CLICK",
		new EntityActionsTabClickEventHandler(openGroupsService, dcsm));

	BaseAbstractViewFactory viewsFactoryForType = new BaseAbstractViewFactory();
	ViewFactory entitiesListViewFactory = new EntitiesListViewFactory();
	viewsFactoryForType.addFactory(ViewsTypes.ENTITES_LIST_VIEW, entitiesListViewFactory);
	ViewFactory createEntityViewFactory = new CreateEntityWithTagsViewFactory();
	viewsFactoryForType.addFactory(ViewsTypes.CREATE_ENTITY_WITH_TAGS_VIEW, createEntityViewFactory);
	viewsFactoryForType.addFactory(ViewsTypes.FILTERS_LIST_VIEW, new FiltersListViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.SIMPLE_ENTITIES_LIST_VIEW, new SimpleEntitiesListViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.LIST_PAGE_INFO_VIEW, new ListPageInfoViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.ENTITY_DATA_SUMMARY_VIEW, new EntityDataSummaryViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.QUICK_LINKS_VIEW, new QuickLinksViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.ENTITY_USER_ACTIONS_TAB_VIEW, new EntityUserActionTabViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.ENTITY_USER_ACTIONS_FOOTER_VIEW,
		new EntityUserActionsFooterViewFactory());
	viewsFactoryForType.addFactory(ViewsTypes.ENTITY_UPDATE_VIEW, new EntityUpdateViewFactory());
	

	ViewsFactoryForCommand viewsFactoryForCommand = new ViewsFactoryForCommand();
	viewsFactoryForCommand.setViewsFactoryForType(viewsFactoryForType);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_LIST_RECENT_ACTIVITY, ViewsTypes.ENTITES_LIST_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_LIST_BY_GLOBAL_PRIORITY,
		ViewsTypes.ENTITES_LIST_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_LIST_BY_MY_PRIORITY, ViewsTypes.ENTITES_LIST_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_LIST_NEWEST, ViewsTypes.ENTITES_LIST_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_LIST_MOST_POPULAR, ViewsTypes.ENTITES_LIST_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.CREATE_ENTITY_WITH_TAGS,
		ViewsTypes.CREATE_ENTITY_WITH_TAGS_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_UPDATE, ViewsTypes.ENTITY_UPDATE_VIEW);
	viewsFactoryForCommand.addMapping(OpenGroupsCommands.ENTITY_CREATE, ViewsTypes.ENTITY_UPDATE_VIEW);
	
	/* set view factories on viewsManager */
	viewsManager.setViewFactoryForType(viewsFactoryForType);
	viewsManager.setViewFactoryForCommand(viewsFactoryForCommand);

	final MainAppView mainAppView = new MainAppView();
	viewsManager.setMainView(mainAppView);

	/* init history */

	// History.addValueChangeHandler(new ValueChangeHandler<String>() {
	//
	// public void onValueChange(ValueChangeEvent<String> event) {
	// System.out.println("History event: " + event.getValue());
	// UserEvent userEvent = new UserEvent();
	// userEvent.setEventType(UserEvent.HISTORY_CHANGED);
	// }
	// });

	HistoryManager historyManager = new HistoryManager();
	historyManager.setUserEventsManager(userEventsManager);
	viewsManager.addViewStateChangedListener(historyManager);
	History.addValueChangeHandler(historyManager);

	final VerticalPanel vp = new VerticalPanel();
	vp.add(mainAppView.construct());
	vp.setWidth("100%");
	vp.setHeight(Window.getClientHeight() + "px");
	Window.addResizeHandler(new ResizeHandler() {

	    public void onResize(ResizeEvent event) {
		int height = event.getHeight();
		vp.setHeight(height + "px");
	    }
	});
	RootPanel.get().add(vp);
	// RootLayoutPanel.get().add(vp);

	// RootLayoutPanel.get().add(mainAppView.construct());

	UserEvent loadEvent = new UserEvent();
	loadEvent.setEventType(UserEvent.LOAD_APP);
	viewsManager.handleUserEvent(loadEvent);
    }
}

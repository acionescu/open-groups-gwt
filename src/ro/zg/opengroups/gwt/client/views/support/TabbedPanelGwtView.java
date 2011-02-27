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
package ro.zg.opengroups.gwt.client.views.support;

import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.TabPanel;

public abstract class TabbedPanelGwtView<A> extends BaseGwtView<A>{


    @Override
    public TabPanel createContainer() {
//	TabLayoutPanel t= new TabLayoutPanel(300, Unit.PX);
	TabPanel t = new TabPanel();
	t.setWidth("100%");
	return t;
//	return new DecoratedTabPanel();
    }
    
    public TabPanel getContainer() {
	return (TabPanel)super.getContainer();
    }

}

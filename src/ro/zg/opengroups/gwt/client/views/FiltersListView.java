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

import ro.zg.opengroups.gwt.client.views.support.HorizontalPaneGwtView;
import ro.zg.opengroups.gwt.shared.vo.FiltersList;

import com.google.gwt.user.client.ui.Widget;

public class FiltersListView extends HorizontalPaneGwtView<FiltersList>{

    @Override
    public Widget construct() {
	return getContainer();
    }

    @Override
    public void update(FiltersList filtersList) {
	// TODO Auto-generated method stub
	
    }

}

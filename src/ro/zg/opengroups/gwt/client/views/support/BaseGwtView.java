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

import ro.zg.webapp.core.shared.views.View;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class BaseGwtView<A> extends View<A>{
    /**
     * 
     */
    private static final long serialVersionUID = 8250821879727921305L;
    private Widget container;
    
    /**
     * The subclass must create a container for itself
     * @return
     */
    public abstract Widget createContainer();
    
    /**
     * Offers the subclass the posibility to construct it's structure
     */
    public abstract Widget construct();
    
    
    /**
     * @return the container
     */
    public Widget getContainer() {
        if(container==null) {
            container=createContainer();
        }
        return container;
    }
    
    /**
     * @param container the container to set
     */
    public void setContainer(Panel container) {
        this.container = container;
    }
    
}

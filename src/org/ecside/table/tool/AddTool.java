/*
 * Copyright 2006-2007 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ecside.table.tool;

import org.ecside.core.ECSideConstants;
import org.ecside.core.TableConstants;
import org.ecside.core.TableModel;
import org.ecside.preferences.PreferencesConstants;
import org.ecside.util.HtmlBuilder;

/**
 * @author Wei Zijun
 *
 */

public class AddTool extends BaseTool {
	public AddTool(){
		super();
	}
	public AddTool(HtmlBuilder html,TableModel model) {
		super(html,model);
	}
	
    public void buildTool() {
    	
    	if (!getTableModel().getTable().isEditable()){
    		return;
    	}
    	
		boolean showTooltips = getTableModel().getTable().isShowTooltips();

    	String action=getToolAction();
    	
    	getHtmlBuilder().td(1).nowrap().styleClass("addTool").close();
    	getHtmlBuilder().append("<nobr>");
 
		getHtmlBuilder().input().type("button");
		getHtmlBuilder().styleClass(TableConstants.ADD_BUTTON_CSS);
		getHtmlBuilder().onclick(action);
		if (showTooltips) {
			getHtmlBuilder().title(getTableModel().getMessages().getMessage(PreferencesConstants.TOOLBAR_ADD_TOOLTIP));
		}
		getHtmlBuilder().xclose();
		
         getHtmlBuilder().append("</nobr>");
         getHtmlBuilder().tdEnd();
    }

    
    public String getToolAction() {
    	
    	String formId=getTableModel().getTable().getTableId();
        String templateId=getTableModel().getPreferences().getPreference(PreferencesConstants.TABLE_DEFALUT_ADD_TEMPLATE);
        StringBuffer action = new StringBuffer(ECSideConstants.UTIL_FUNCTION_NAME+".addToGird(");
        action.append("this,'"+formId+'_'+templateId+"', '"+formId+"'");
        action.append(");");
        
        
        String onInvokeAction = getTableModel().getTable().getOnInvokeAction();
        if (onInvokeAction!=null&&onInvokeAction.length()>0){
        	action.append(onInvokeAction);
        }

        
        return action.toString();
    }

}

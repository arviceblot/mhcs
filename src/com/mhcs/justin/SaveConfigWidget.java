package com.mhcs.justin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mhcs.logan.Module;
import com.mhcs.logan.ModuleManager;

public class SaveConfigWidget {
	private TextBox input = new TextBox();
	private Label EnterConfName = new Label("Enter Configuration Name: ");
	private Button save;
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel hz = new HorizontalPanel();
	private Storage moduleStore;
	
	public SaveConfigWidget() { 
		vp.add(EnterConfName);
		vp.add(hz);
		hz.add(input);
		hz.add(save);
		
		save = new Button("Save",  new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//create a key to save the configuration as
				String key = input.getValue();
				//create value to save as
				String value = "[";
				ModuleManager moduleManager = ModuleManager.getInstance();
				for(Module module :moduleManager) {
					//Test
					/*    String GPSData = "[" +
					    "{code:1,status:\"undamaged\",turns:0,X:5,Y:5}," +
					    "{code:2,status:\"undamaged\",turns:0,X:5,Y:6}," +
					    "{code:3,status:\"undamaged\",turns:0,X:5,Y:7}" +
					    "]";
					*/
				value += 
			    "{code:"+module.getCode()+",status:\""+module.getStatus().toString()
			    +",turns:"+module.getOrientation().toString()+",X:"+
			    module.getX()+",Y:"+module.getY()+"}";
				moduleStore = Storage.getLocalStorageIfSupported();
				}
				value += "]";
				if(moduleStore!=null) {
					moduleStore.setItem(key, value);
				} else Window.alert("Unable to Save.");
				
			}
			
		});
		
		
	}
	public Panel getMainPanel() {
		return vp;
	}

}

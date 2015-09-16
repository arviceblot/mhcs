package com.mhcs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.mhcs.brenda.LoginManager;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MarsHCS implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		LoginManager login = new LoginManager();

		RootLayoutPanel.get().add(login.getTable());
	}
}

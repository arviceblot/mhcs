package com.mhcs.logan;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Manager for various configurations.
 * @author Logan Sales
 * @version 0.1
 */
public class ConfigurationManager {
	private final MinimumConfiguration fpc;
	private final MinimumConfiguration minimum;
	private final Panel mainPanel;
	
	public ConfigurationManager() {
		this.mainPanel = new VerticalPanel();
		
		int[] minReqs = new int[10];
		minReqs[Module.PLAIN] = 3;
		minReqs[Module.DORMITORY] = 1;
		minReqs[Module.SANITATION] = 1;
		minReqs[Module.FOOD] = 1;
		minReqs[Module.GYM] = 0;
		minReqs[Module.CANTEEN] = 1;
		minReqs[Module.POWER] = 1;
		minReqs[Module.CONTROL] = 1;
		minReqs[Module.AIRLOCK] = 1;
		minReqs[Module.MEDICAL] = 0;
		
		this.fpc = new MinimumConfiguration(minReqs, true);
		this.minimum = new MinimumConfiguration(minReqs, false);
		
		//final StackLayoutPanel stack = new StackLayoutPanel(Unit.PX);
		HorizontalPanel minimums = new HorizontalPanel();
		//stack.add(new HTML("Minimum Configurations"), minimums, 200);
		HorizontalPanel fulls = new HorizontalPanel();
		//stack.add(new HTML("Full Configurations"), fulls, 200);
		
		//this.mainPanel.add(stack);
		minimums.add(fpc.getMainPanel());
		minimums.add(minimum.getMainPanel());
		this.mainPanel.add(minimums);
		this.mainPanel.add(fulls);
	}
	
	

	/**
	 * Getter for main panel.
	 * @return main panel
	 */
	public Panel getMainPanel() {
		return mainPanel;
	}
}

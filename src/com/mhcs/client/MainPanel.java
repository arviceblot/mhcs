package com.mhcs.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mhcs.joshua.MapNavigator;
import com.mhcs.justin.GPSStatusBar;
import com.mhcs.logan.ConfigurationManager;
import com.mhcs.logan.Logging;
import com.mhcs.logan.ModuleManager;
import com.mhcs.logan.WeatherFeed;

public class MainPanel {

	//private DockLayoutPanel dock;
	private FlexTable table;
	
	public MainPanel() {
		table = new FlexTable();
		
		VerticalPanel vp = new VerticalPanel();
		Logging logging = new Logging();
		Panel loggingPanel = logging.getMainPanel();
		vp.add(loggingPanel);
		
		TabPanel selections = new TabPanel();
		selections.add(vp, "Logging");
		ConfigurationManager configManager = new ConfigurationManager();
		selections.add(configManager.getMainPanel(), "Configurations");
		selections.selectTab(0);
		
		TabPanel info = new TabPanel();
		ScrollPanel modulePanel = new ScrollPanel();
		modulePanel.add(ModuleManager.getModuleTable());
		info.add(logging.getOutput(), "Modules");
		info.add(new HTML("information"), "Information");
		info.selectTab(0);

		HorizontalPanel statusBar = new HorizontalPanel();
		WeatherFeed weather = new WeatherFeed();
		Panel weatherPanel = weather.getMainPanel();
		GPSStatusBar gps = new GPSStatusBar();
		Panel gp = gps.getMainPanel();
		statusBar.add(gp);
		statusBar.add(weatherPanel);
		
		MapNavigator map = new MapNavigator();
		Panel mapGrid = map.getMapGrid();
		mapGrid.setPixelSize(600, 400);
		
		table.setWidget(0, 0, mapGrid);
		table.setWidget(1, 0, info);
		//table.getFlexCellFormatter().setRowSpan(0, 0, 2);
		table.setWidget(0, 1, selections);
		table.getFlexCellFormatter().setRowSpan(0, 1, 2);
		table.setWidget(2, 0, statusBar);
		table.getFlexCellFormatter().setColSpan(2, 0, 2);
		
		RootLayoutPanel.get().add(table);

//		DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
//		dock.addSouth(statusBar, 300);
//		dock.addWest(mapGrid, 600);
//		dock.add(vp);
//		RootLayoutPanel.get().add(dock);
	}
}

package com.mhcs.logan;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.mhcs.joshua.ModuleDataEvent;

/**
 * Bar graph representation of modules.
 * @author Logan Sales
 */
public class Histogram {
	private static final int UNDAMAGED = 1;
	private static final int DAMAGED = 2;
	private static final int UNCERTAIN = 3;
	private Options options;
	private DataTable data;
	private BarChart bar;
	private final Panel mainPanel;

	public Histogram() {
		this.mainPanel = new VerticalPanel();
		
		ModuleDataEvent.register(ModuleManager.EVENT_BUS,
				new ModuleDataEvent.Handler() {

					@Override
					public void onRemovedModule(final ModuleDataEvent modEvent) {
						removeModule(ModuleManager.getModule(
								modEvent.getModuleID()));
					}

					@Override
					public void onAddedModule(final ModuleDataEvent modEvent) {
						addModule(ModuleManager.getModule(
								modEvent.getModuleID()));
					}

					@Override
					public void onUpdateModule(ModuleDataEvent modEvent) {
						// TODO Auto-generated method stub
						
					}
				
		});

		final Runnable onLoadCallback = new Runnable() {
			/**
			 * Run for it.
			 */
			@Override
			public void run() {
				bar = new BarChart(createTable(), createOptions());
				mainPanel.add(bar);
			}

		};
		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback,
				BarChart.PACKAGE);

	}

	public Panel getMainPanel() {
		return this.mainPanel;
	}

	private Options createOptions() {
		this.options = Options.create();
		this.options.setWidth(420);
		this.options.setHeight(300);
		this.options.setTitle("Histogram of Module Status");
		return this.options;
	}

	

	private AbstractDataTable createTable() {
		this.data = DataTable.create();
		this.data.addColumn(ColumnType.STRING, "Module Type");
		this.data.addColumn(ColumnType.NUMBER, "Undamaged");
		this.data.addColumn(ColumnType.NUMBER, "Damaged");
		this.data.addColumn(ColumnType.NUMBER, "Uncertain");
		this.data.addRows(10);
		
		this.data.setValue(0, 0, "Plain");
		this.data.setValue(0, UNDAMAGED, 0);
		this.data.setValue(0, DAMAGED, 0);
		this.data.setValue(0, UNCERTAIN, 0);
		
		this.data.setValue(1, 0, "Dormitory");
		this.data.setValue(1, UNDAMAGED, 0);
		this.data.setValue(1, DAMAGED, 0);
		this.data.setValue(1, UNCERTAIN, 0);
		
		this.data.setValue(2, 0, "Sanitation");
		this.data.setValue(2, UNDAMAGED, 0);
		this.data.setValue(2, DAMAGED, 0);
		this.data.setValue(2, UNCERTAIN, 0);
		
		this.data.setValue(3, 0, "Food/Water");
		this.data.setValue(3, UNDAMAGED, 0);
		this.data.setValue(3, DAMAGED, 0);
		this.data.setValue(3, UNCERTAIN, 0);
		
		this.data.setValue(4, 0, "Gym/Relaxation");
		this.data.setValue(4, UNDAMAGED, 0);
		this.data.setValue(4, DAMAGED, 0);
		this.data.setValue(4, UNCERTAIN, 0);
		
		this.data.setValue(5, 0, "Canteen");
		this.data.setValue(5, UNDAMAGED, 0);
		this.data.setValue(5, DAMAGED, 0);
		this.data.setValue(5, UNCERTAIN, 0);
		
		this.data.setValue(6, 0, "Power");
		this.data.setValue(6, UNDAMAGED, 0);
		this.data.setValue(6, DAMAGED, 0);
		this.data.setValue(6, UNCERTAIN, 0);
		
		this.data.setValue(7, 0, "Control");
		this.data.setValue(7, UNDAMAGED, 0);
		this.data.setValue(7, DAMAGED, 0);
		this.data.setValue(7, UNCERTAIN, 0);
		
		this.data.setValue(8, 0, "Airlock");
		this.data.setValue(8, UNDAMAGED, 0);
		this.data.setValue(8, DAMAGED, 0);
		this.data.setValue(8, UNCERTAIN, 0);
		
		this.data.setValue(9, 0, "Medical");
		this.data.setValue(9, UNDAMAGED, 0);
		this.data.setValue(9, DAMAGED, 0);
		this.data.setValue(9, UNCERTAIN, 0);
		
		return this.data;
	}
	
	public void addModule(final Module module) {
		// Increment the value of the type
		final int row = module.getTypeInt();
		int col;
		if (module.getStatus().equalsName(Status.UNDAMAGED.toString())) {
			col = UNDAMAGED;
		} else if (module.getStatus().equalsName(Status.DAMAGED.toString())) {
			col = DAMAGED;
		} else {
			col = UNCERTAIN;
		}
		this.data.setValue(row, col, this.data.getValueInt(row, col) + 1);
		this.bar.draw(getData(), this.options);
	}
	
	public void removeModule(final Module module) {
		// Decrement the value of the type
		final int row = module.getTypeInt();
		int col;
		if (module.getStatus().equalsName(Status.UNDAMAGED.toString())) {
			col = UNDAMAGED;
		} else if (module.getStatus().equalsName(Status.DAMAGED.toString())) {
			col = DAMAGED;
		} else {
			col = UNCERTAIN;
		}
		this.data.setValue(row, col, this.data.getValueInt(row, col) - 1);
		this.bar.draw(getData(), this.options);
	}
	
	private AbstractDataTable getData() {
		return this.data;
	}
}

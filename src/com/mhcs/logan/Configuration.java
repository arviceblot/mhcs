package com.mhcs.logan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mhcs.joshua.ModuleDataEvent;

/**
 * Module configuration for a possible habitat.
 * @author Logan Sales
 * @version 0.3
 */
public class Configuration {
	protected int[] requirements;
	protected int[] contains;
	/**
	 * Modules used in the configuration.
	 */
	protected List<Module> modules;
	/**
	 * 2D array for module positions.
	 */
	protected Module[][] layout;
	protected Panel mainPanel;
	/**
	 * The center of mass of modules used on the map.
	 */
	protected int[] centerMass;
	protected ModuleManager modManager;
	/**
	 * Where to put the center of mass relative to the map.
	 */
	protected int[] offset;
	protected int[] size;
	
	public Configuration(final int[] reqs) {
		this.requirements = reqs;
		this.contains = new int[10];
		this.modules = new ArrayList<Module>();
		this.mainPanel = new VerticalPanel();
		
		this.centerMass = new int[2];
		this.centerMass[0] = 0;
		this.centerMass[1] = 0;
		
		this.offset = new int[2];
		this.offset[0] = 0;
		this.offset[1] = 0;
		
		this.modManager = ModuleManager.getInstance();
		
		this.size = new int[2];
		this.size[0] = 20;
		this.size[1] = 20;
		
		// Initialize the layout of modules to null
		this.layout = new Module[20][20];
//		for (int row = 0; row < 20; row ++) {
//			for (int col = 0; col < 20; col++) {
//				layout[row][col] = null;
//			}
//		}
		
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
		
		
	}
	
	protected void removeModule(final Module module) {
		// We only care if the module removed was undamaged
		if (module.getStatus() == Status.UNDAMAGED) {
			for (Module checkModule: modules) {
				if (checkModule.getCode() == module.getCode()) {
					this.modules.remove(checkModule);
					final int index = module.getTypeInt();
					this.contains[index] = contains[index] - 1;
					break;
				}
			}
		}
	}
	
	protected void addModule(final Module module) {
		// Only add the module if it is undamaged
		if (module.getStatus() == Status.UNDAMAGED) {
			final int index = module.getTypeInt();
			// if type is needed, add the module
			if (this.requirements[index] > this.contains[index]) {
				this.modules.add(module);
				this.contains[index] = contains[index] + 1;
				this.updateCenterMass();
				this.checkRequirements();
			} else if (this.requirements[index] >= 1) {
				// else check if reqs for type > 1,
				// and if closer than module contained having that type,
				// replace it.
				final List<Module> foundType = new ArrayList<Module>();
				for (Module checkModule: modules) {
					if (checkModule.getTypeInt() == index) {
						foundType.add(checkModule);
					}
				}
				// search through the modules added to the config to see if the
				// new module is closer
				for (Module checkModule: foundType) {
					if (isCloser(centerMass, module, checkModule)) {
						// module is closer, so replace the farther one
						this.modules.remove(checkModule);
						this.modules.add(module);
						this.updateCenterMass();
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Checks if module1 is closer to a location than module2 is.
	 * @param location
	 * @param module1
	 * @param module2
	 * @return
	 */
	protected static boolean isCloser(final int location[], final Module module1,
			final Module module2) {
		if (distance(location[1], location[0], module1.getX(), module1.getY()) >=
				distance(location[1], location[0], module2.getX(), module2.getY())) {
			return true;
		} else {
			return false;
		}
	}
		
	protected static double distance(final int x1, final int y1, final int x2,
			final int y2) {
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	protected void checkRequirements() {
		for (int i = 0; i < 10; i++) {
			if (this.requirements[i] <= this.contains[i]) {
				continue;
			} else {
				return;
			}
		}
		meetsRequirements();
	}
	
	/**
	 * Call this if module changes.
	 */
	protected void updateCenterMass() {
		int numModules = 0;
		int sumX = 0;
		int sumY = 0;
		for (Module module: this.modules) {
			numModules++;
			sumX += module.getX();
			sumY += module.getY();
		}
		this.centerMass[0] = sumX / numModules;
		this.centerMass[1] = sumY / numModules;
	}
	
	/**
	 * Populates the module array with ones used by this configuration.
	 */
	protected void buildLayout() {	
	}
	
	/**
	 * Check if configuration has a module.
	 * @param findModule to find
	 * @return true if has, else false
	 */
	protected boolean hasModule(final Module findModule) {
		for (Module module: modules) {
			if (module.getCode() == findModule.getCode()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Only called when requirements for configuration are met.
	 */
	protected void meetsRequirements() {
		buildLayout();
	}
	
	public int[] getOffset() {
		return this.offset;
	}
	
	public void setOffset(final int newOffset[]) {
		this.offset[0] = newOffset[0];
		this.offset[1] = newOffset[1];
	}
	
	public Module[][] getLayout() {
		return layout;
	}
	
	public Panel getMainPanel() {
		return this.mainPanel;
	}
}

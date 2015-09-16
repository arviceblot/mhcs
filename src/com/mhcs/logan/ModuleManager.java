package com.mhcs.logan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.mhcs.joshua.ModuleDataEvent;

/**
 * Class for managing the various modules.
 * @author Logan Sales
 * @version 1.1
 */
public class ModuleManager implements Iterable<Module> {
//josh changes to get it to work
	public final static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);
//end josh added stuff
	/**
	 * Singleton implementation of the Module Manager.
	 */
	private static ModuleManager moduleManager;

	/**
	 * Storage of the modules.
	 */
	private static List<Module> modules;
	private static FlexTable moduleTable;

	/**
	 * Default constructor.
	 */
	protected ModuleManager() {
		modules = new ArrayList<Module>();
		
		moduleTable = new FlexTable();
		moduleTable.setText(0, 0, "Code");
		moduleTable.setText(0, 1, "Type");
		moduleTable.setText(0, 2, "Status");
		moduleTable.setText(0, 3, "Orientation");
		moduleTable.setText(0, 4, "Location");
	}

	/**
	 * Gets an instance of the module manager.
	 * @return moduleManager instance
	 */
	public static ModuleManager getInstance() {
		if (moduleManager == null) {
			moduleManager = new ModuleManager();
		}
		return moduleManager;
	}
	
	public static FlexTable getModuleTable() {
		return moduleTable;
	}

	/**
	 * Adds a new module to manage.
	 * @param newModule to add
	 * @return true if the insertion was successful, false if present
	 */
	public static boolean add(final Module newModule) {
		final ModuleDataEvent modEvent = new ModuleDataEvent(ModuleDataEvent.ADD_MODULE, newModule.getCode());
		// Sorted insertion
		for (int i = 0; i < modules.size(); i++) {
			if (modules.get(i).getCode() < newModule.getCode()) {
				continue;
			} else if (modules.get(i).getCode() == newModule.getCode()) {
				// Module already present
				return false;
			} else {
				// Found stop to add
				modules.add(i, newModule);
				EVENT_BUS.fireEvent(modEvent);
				addTable(newModule);
				return true;
			}
		}
		// Otherwise just add it at the end (empty list or last)
		final boolean added = modules.add(newModule);
		EVENT_BUS.fireEvent(modEvent);
		return added;
	}

	/**
	 * Removes an existing module.
	 * @param module to remove
	 * @return the module removed
	 */
	public static Module remove(final Module module) {
		// Ghost call to other method
		return remove(module.getCode());
	}

	/**
	 * Removes and existing module with a particular code.
	 * @param code the module to remove
	 * @return the removed module
	 */
	public static Module remove(final int code) {
		final ModuleDataEvent modEvent = new ModuleDataEvent(ModuleDataEvent.ADD_MODULE, code);
		// More simple implementation than remove(module)
		// Could be useful in other implementations
		final int index = findModule(code);
		if (index == -1) {
			// Module not found
			return null;
		} else {
			final Module removedModule = modules.remove(index);
			EVENT_BUS.fireEvent(modEvent);
			return removedModule;
		}
	}

	/**
	 * Updates an existing module.
	 * @param newModule the new module
	 * @return the previous module
	 */
	public static Module update(final Module newModule) {
		// Search through modules for one with the same code
		final int index = findModule(newModule);
		if (index == -1) {
			// Module not found
			return null;
		} else {
			return modules.set(index, newModule);
		}
	}
	
	/**
	 * Searches the modules for a specific code.
	 * @param moduleCode to find
	 * @return found module else null
	 */
	public static Module getModule(final int moduleCode) {
		for (Module module: modules) {
			if (module.getCode() == moduleCode) {
				return module;
			}
		}
		return null;
	}
	
	/**
	 * Searches the modules for a specific module.
	 * @param getModule to find
	 * @return found module else null
	 */
	public static Module getModule(final Module getModule) {
		return getModule(getModule.getCode());
	}

	/**
	 * Getter for the module iterator.
	 * Iterator API for the module manager. Allow use of for (Module module:
	 * modManager) after a call to something like ModuleManager modManager =
	 * ModuleManager.getInstance();
	 * @return iterator over modules
	 */
	public final Iterator<Module> iterator() {
		return modules.iterator();
	}
	
	/**
	 * Finds the module index if found.
	 * @param findCode to search
	 * @return index, else -1 if not found
	 */
	private static int findModule(final int findCode) {
		for (int i = 0; i < modules.size(); i++) {
			if (modules.get(i).getCode() == findCode) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Finds the module index if found.
	 * @param findModule to search
	 * @return index, else -1 if not found
	 */
	private static int findModule(final Module findModule) {
		return findModule(findModule.getCode());
	}
	
	private static void addTable(final Module module) {
		final int row = moduleTable.getRowCount();
		moduleTable.setText(row, 0, module.getCode() + "");
		moduleTable.setText(row, 1, module.getType());
		moduleTable.setText(row, 2, module.getStatus().toString());
		moduleTable.setText(row, 3, module.getOrientation().toString());
		moduleTable.setText(row, 4, "(" + module.getX() + ", " + module.getY() + ")");
	}
}

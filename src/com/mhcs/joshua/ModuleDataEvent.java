package com.mhcs.joshua;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * This is to allow communication from the Module Manager to the other programs.
 * 
 * @author josh
 * 
 */
public class ModuleDataEvent extends Event<ModuleDataEvent.Handler> {

	/**
	 * this is an interface to let the Handler know there are going to be two
	 * differnt tings to do here
	 * 
	 * @author josh
	 * 
	 */
	public interface Handler {

		void onAddedModule(ModuleDataEvent modEvent);

		void onRemovedModule(ModuleDataEvent modEvent);
		
		void onUpdateModule(ModuleDataEvent modEvent);
	}

	public static Type<ModuleDataEvent.Handler> TYPE = new Type<ModuleDataEvent.Handler>();

	public static final int ADD_MODULE = 1;
	public static final int REMOVE_MODULE = -1;
	public static final int UPDATE_MODULE = 0;

	private final int eventCall; //
	private final int moduleNumber;

	public ModuleDataEvent(int eventType, int moduleID) {

		this.eventCall = eventType;
		this.moduleNumber = moduleID;

	}

	// type return part of the event constructor
	@Override
	public Type<ModuleDataEvent.Handler> getAssociatedType() {

		return TYPE;

	}

	/**
	 * 
	 * @param eventBus
	 *            event bus to use
	 * @param handler
	 *            handle event like so
	 * @return eventbus handler
	 */
	public static HandlerRegistration register(EventBus eventBus,
			ModuleDataEvent.Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	/**
	 * Generaged stub to say what onFunction to be used... I think
	 */
	@Override
	protected void dispatch(Handler handler) {

		if (eventCall == 1) {

			handler.onAddedModule(this);

		} else if (eventCall == 0) {
			
			handler.onUpdateModule(this);
			
		} else {

			handler.onRemovedModule(this);

		}

	}

	/**
	 * Displays the ModuleID of the module that caused the Event
	 * 
	 * @return module ID number to be access that specific module
	 */
	public int getModuleID() {

		return moduleNumber;

	}

}

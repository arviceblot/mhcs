package com.mhcs.brenda;

import com.google.gwt.storage.client.Storage;


/**
 * This is the class that will take in date strings and store them.
 * Also I will attempt to locate them in local storage.
 * @author brenda
 *
 */
public class DateStorage {

	/**
	 * This is the object that will contact local storage.
	 */
	private Storage moduleStore = Storage.getLocalStorageIfSupported();
	/**
	 * This is the constructor.  Nothing needs to be instantiated here.
	 */
	public DateStorage() {
	}
	/**
	 * this is the function that attempts to store the date string.
	 * @param key the key that will be used to store the date string.
	 * @param date the string that contains a date.
	 */
	public final void storeDate(final String key, final String date) {
		moduleStore.setItem(key, date);
	}
	/**
	 * This is the function that attempts to recall the date string.
	 * @param key this is the key that will be looked up in local
	 * storage that is linked with the date wanted.
	 * @return this returns the date as a string.
	 */
	public final String getDate(final String key) {
		if (moduleStore != null) {
			return moduleStore.getItem(key);
		} else {
			return null;
		}
	}
}

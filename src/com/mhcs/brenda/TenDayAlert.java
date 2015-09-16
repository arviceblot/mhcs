package com.mhcs.brenda;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;

/**
 * This is the class that takes two dates and compares them.
 * @author brenda
 *
 */
public class TenDayAlert {
	/**
	 * this is the date that the system was last calibrated.
	 */
	private Date oldDate;
	/**
	 * this is todays date.
	 */
	private Date newDate;
	/**
	 * This is the object that will try and store the old date for later.
	 */
	private DateStorage storage;
	/**
	 * This is the the key that will be used to store the date.
	 */
	private String dateKey = "key";

	/**
	 * constructor for this class.  This will compare the dates and then
	 * tell the user how many days it has been since calibration.
	 */
	public TenDayAlert() {
		newDate = new Date();
		storage = new DateStorage();
		/**
		 * This try catch statement is needed in case the parse fails.
		 * This will happen if there is not
		 * date stored in local storage.
		 */
		try {
			oldDate = DateTimeFormat.getFormat(
					"mm/dd/yyyy").parse(
							storage.getDate(
							      dateKey));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			oldDate = newDate;
		}
		/**
		 * this will tell the user how many days
		 * it has been since calibration of the rover.
		 */
		Window.alert("Days Past since calibrating the system: "
		 + CalendarUtil.getDaysBetween(newDate, oldDate));
		/**
		 * after the user has been notified, the old
		 * date needs to be put back in storage.
		 * However If it has been ten days then todays
		 * date needs to be put in storage instead.
		 */
		if (oldDate.equals(null)) {
			storage.storeDate(dateKey, newDate.toString());
		}
		if (CalendarUtil.getDaysBetween(newDate, oldDate) >= 10) {
			storage.storeDate(dateKey, newDate.toString());
		} else {
			storage.storeDate(dateKey, oldDate.toString());
		}
	}
}

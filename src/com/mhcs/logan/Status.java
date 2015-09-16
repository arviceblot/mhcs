package com.mhcs.logan;

/**
 * Enumerated Status type for Module representation.
 * Credit: http://stackoverflow.com/a/6667403
 * @author Logan Sales
 * @version 1.1
 */
public enum Status {
	/**
	 * Immediately usable.
	 */
	UNDAMAGED("undamaged"),
	/**
	 * Needs repair to function.
	 */
	DAMAGED("damaged"),
	/**
	 * Uncertain after manual inspection.
	 */
	UNCERTAIN("uncertain");

	/**
	 * String representation of the status.
	 */
	private final String name;

	/**
	 * Constructs the status with string name.
	 * @param string to build
	 */
	private Status(final String string) {
		this.name = string;
	}

	/**
	 * Check equality of string and the name.
	 * @param otherName to check
	 * @return true if same
	 */
	public boolean equalsName(final String otherName) {
		return this.name.equals(otherName);
	}

	/**
	 * String value of status.
	 * @return name
	 */
	public String toString() {
		return this.name;
	}
	
	/**
	 * Getter for a Status value from a status string.
	 * @param status to check
	 * @return Status of string else null
	 */
	public static Status getStatus(final String status) {
		Status returnStatus = null;
		if (UNDAMAGED.equalsName(status)) {
			returnStatus = UNDAMAGED;
		} else if (DAMAGED.equalsName(status)) {
			returnStatus = DAMAGED;
		} else if (UNCERTAIN.equalsName(status)) {
			returnStatus = UNCERTAIN;
		}
		return returnStatus;
	}
}

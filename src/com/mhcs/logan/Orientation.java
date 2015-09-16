package com.mhcs.logan;

/**
 * Enumerated Orientation type for module representation.
 * Credit: http://stackoverflow.com/a/6667403
 * @author Logan Sales
 * @version 1.1
 */
public enum Orientation {
	/**
	 * No turns needed.
	 */
	UPRIGHT("upright"),
	/**
	 * Only one turn needed.
	 */
	ONETURN("one turn"),
	/**
	 * Essentially upside-down.
	 */
	TWOTURNS("two turns");

	/**
	 * String representation of the orientation.
	 */
	private final String name;

	/**
	 * Constructs the orientation with a string name.
	 * @param string name
	 */
	private Orientation(final String string) {
		this.name = string;
	}

	/**
	 * Checks whether the string equals the name.
	 * @param otherName to check
	 * @return true if same
	 */
	public boolean equalsName(final String otherName) {
		return this.name.equals(otherName);
	}

	/**
	 * Returns the string name.
	 * @return name
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Getter for the value associated with local storage.
	 * @param orientation to check
	 * @return storage value
	 */
	public static int toStorageValue(final Orientation orientation) {
		// Should never return -1
		int value = -1;
		if (orientation.equalsName(UPRIGHT.name)) {
			value = 0;
		} else if (orientation.equalsName(ONETURN.name)) {
			value = 1;
		} else if (orientation.equalsName(TWOTURNS.name)) {
			value = 2;
		}
		return value;
	}

	/**
	 * Getter for an Orientation value from an orientation string.
	 * @param orienation to check
	 * @return Orientation of string else null
	 */
	public static Orientation getOrientation(final String orienation) {
		Orientation returnOrientation = null;
		if (UPRIGHT.equalsName(orienation)) {
			returnOrientation = UPRIGHT;
		} else if (ONETURN.equalsName(orienation)) {
			returnOrientation = ONETURN;
		} else if (TWOTURNS.equalsName(orienation)) {
			returnOrientation = TWOTURNS;
		}
		return returnOrientation;
	}

	/**
	 * Getter for an Orientation value from an integer value.
	 * @param orientation to check
	 * @return Orientation value of integer value
	 */
	public static Orientation getOrientation(final int orientation) {
		Orientation returnOrientation = null;
		if (orientation == 0) {
			returnOrientation = UPRIGHT;
		} else if (orientation == 1) {
			returnOrientation = ONETURN;
		} else if (orientation == 2) {
			returnOrientation = TWOTURNS;
		}
		return returnOrientation;
	}
}

package com.mhcs.logan;

/**
 * Data class to represent a module.
 * @author Logan Sales
 * @version 1.1
 */
public class Module {
	/**
	 * Minimum value for plain module code.
	 */
	private static final int MIN_PLAIN = 1;
	/**
	 * Maximum value for plain module code.
	 */
	private static final int MAX_PLAIN = 40;
	/**
	 * Minimum value for dormitory module code.
	 */
	private static final int MIN_DORMITORY = 61;
	/**
	 * Maximum value for dormitory module code.
	 */
	private static final int MAX_DORMITORY = 80;
	/**
	 * Minimum value for sanitation module code.
	 */
	private static final int MIN_SANITATION = 91;
	/**
	 * Maximum value for sanitation module code.
	 */
	private static final int MAX_SANITATION = 100;
	/**
	 * Minimum value for food/water module code.
	 */
	private static final int MIN_FOOD = 111;
	/**
	 * Maximum value for food/water module code.
	 */
	private static final int MAX_FOOD = 120;
	/**
	 * Minimum value for gym/relaxation module code.
	 */
	private static final int MIN_GYM = 131;
	/**
	 * Maximum value for gym/relaxation module code.
	 */
	private static final int MAX_GYM = 134;
	/**
	 * Minimum value for canteen module code.
	 */
	private static final int MIN_CANTEEN = 141;
	/**
	 * Maximum value for canteen module code.
	 */
	private static final int MAX_CANTEEN = 144;
	/**
	 * Minimum value for power module code.
	 */
	private static final int MIN_POWER = 151;
	/**
	 * Maximum value for power module code.
	 */
	private static final int MAX_POWER = 154;
	/**
	 * Minimum value for control module code.
	 */
	private static final int MIN_CONTROL = 161;
	/**
	 * Maximum value for control module code.
	 */
	private static final int MAX_CONTROL = 164;
	/**
	 * Minimum value for airlock module code.
	 */
	private static final int MIN_AIRLOCK = 171;
	/**
	 * Maximum value for airlock module code.
	 */
	private static final int MAX_AIRLOCK = 174;
	/**
	 * Minimum value for medical module code.
	 */
	private static final int MIN_MEDICAL = 181;
	/**
	 * Maximum value for medical module code.
	 */
	private static final int MAX_MEDICAL = 184;
	
	/**
	 * Type code for plain module type.
	 */
	public static final int PLAIN = 0;
	/**
	 * Type code for dormitory module type.
	 */
	public static final int DORMITORY = 1;
	/**
	 * Type code for sanitation module type.
	 */
	public static final int SANITATION = 2;
	/**
	 * Type code for food/water module type.
	 */
	public static final int FOOD = 3;
	/**
	 * Type code for gym/relaxation module type.
	 */
	public static final int GYM = 4;
	/**
	 * Type code for canteen module type.
	 */
	public static final int CANTEEN = 5;
	/**
	 * Type code for power module type.
	 */
	public static final int POWER = 6;
	/**
	 * Type code for control module type.
	 */
	public static final int CONTROL = 7;
	/**
	 * Type code for airlock module type.
	 */
	public static final int AIRLOCK = 8;
	/**
	 * Type code for medical module type.
	 */
	public static final int MEDICAL = 9;
	
	/**
	 * Code for module (like and id).
	 */
	private int code;
	/**
	 * How damaged the module is.
	 */
	private Status status;
	/**
	 * How many turns required to bring the module upright.
	 */
	private Orientation orientation;
	/**
	 * Module x coordinate.
	 */
	private int x;
	/**
	 * Module y coordinate.
	 */
	private int y;

	/**
	 * Default constructor.
	 * @param initCode input
	 * @param initStatus input
	 * @param initOrientation input
	 * @param initX input
	 * @param initY input
	 */
	public Module(final int initCode, final Status initStatus,
			final Orientation initOrientation,
			final int initX, final int initY) {
		this.code = initCode;
		this.status = initStatus;
		this.orientation = initOrientation;
		this.x = initX;
		this.y = initY;
	}

	/**
	 * Getter for code.
	 * @return code
	 */
	public final int getCode() {
		return this.code;
	}

	/**
	 * Getter for status.
	 * @return status
	 */
	public final Status getStatus() {
		return this.status;
	}

	/**
	 * Getter for orientation.
	 * @return orientation
	 */
	public final Orientation getOrientation() {
		return this.orientation;
	}

	/**
	 * Getter for x coordinate.
	 * @return x
	 */
	public final int getX() {
		return this.x;
	}

	/**
	 * Getter for y coordinate.
	 * @return y
	 */
	public final int getY() {
		return this.y;
	}

	/**
	 * Getter for module type.
	 * @return type
	 */
	public final String getType() {
		String type = "";
		if (this.code >= MIN_PLAIN && this.code <= MAX_PLAIN) {
			type = "plain";
		} else if (this.code >= MIN_DORMITORY && this.code <= MAX_DORMITORY) {
			type = "dormitory";
		} else if (this.code >= MIN_SANITATION && this.code <= MAX_SANITATION) {
			type = "sanitation";
		} else if (this.code >= MIN_FOOD && this.code <= MAX_FOOD) {
			type = "food/water";
		} else if (this.code >= MIN_GYM && this.code <= MAX_GYM) {
			type = "gym/relaxation";
		} else if (this.code >= MIN_CANTEEN && this.code <= MAX_CANTEEN) {
			type = "canteen";
		} else if (this.code >= MIN_POWER && this.code <= MAX_POWER) {
			type = "power";
		} else if (this.code >= MIN_CONTROL && this.code <= MAX_CONTROL) {
			type = "control";
		} else if (this.code >= MIN_AIRLOCK && this.code <= MAX_AIRLOCK) {
			type = "airlock";
		} else if (this.code >= MIN_MEDICAL && this.code <= MAX_MEDICAL) {
			type = "medical";
		}
		return type;
	}
	
	/**
	 * Getter for integer representation of type.
	 * @return type
	 */
	public final int getTypeInt() {
		int type = 0;
		if (this.code >= MIN_PLAIN && this.code <= MAX_PLAIN) {
			type = PLAIN;
		} else if (this.code >= MIN_DORMITORY && this.code <= MAX_DORMITORY) {
			type = DORMITORY;
		} else if (this.code >= MIN_SANITATION && this.code <= MAX_SANITATION) {
			type = SANITATION;
		} else if (this.code >= MIN_FOOD && this.code <= MAX_FOOD) {
			type = FOOD;
		} else if (this.code >= MIN_GYM && this.code <= MAX_GYM) {
			type = GYM;
		} else if (this.code >= MIN_CANTEEN && this.code <= MAX_CANTEEN) {
			type = CANTEEN;
		} else if (this.code >= MIN_POWER && this.code <= MAX_POWER) {
			type = POWER;
		} else if (this.code >= MIN_CONTROL && this.code <= MAX_CONTROL) {
			type = CONTROL;
		} else if (this.code >= MIN_AIRLOCK && this.code <= MAX_AIRLOCK) {
			type = AIRLOCK;
		} else if (this.code >= MIN_MEDICAL && this.code <= MAX_MEDICAL) {
			type = MEDICAL;
		}
		return type;
	}

	/**
	 * Setter for code.
	 * @param newCode the new code
	 */
	public final void setCode(final int newCode) {
		this.code = newCode;
	}

	/**
	 * Setter for status.
	 * @param newStatus the new status
	 */
	public final void setStatus(final Status newStatus) {
		this.status = newStatus;
	}

	/**
	 * Setter for orientation.
	 * @param newOrientation the new orientation
	 */
	public final void setOrientation(final Orientation newOrientation) {
		this.orientation = newOrientation;
	}

	/**
	 * Setter for x coordinate.
	 * @param newX the new x coordinate
	 */
	public final void setX(final int newX) {
		this.x = newX;
	}

	/**
	 * Setter for y coordinate.
	 * @param newY the new y coordinate
	 */
	public final void setY(final int newY) {
		this.y = newY;
	}
	
}

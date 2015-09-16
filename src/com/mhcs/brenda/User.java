package com.mhcs.brenda;

/**
 * This is the class that ties usernames and passwords together into users.
 * @author brenda
 *
 */
public class User {
    /**This is the constructor and it takes in
	 * the users username and password.
	 * @param username1 is the users name for the system
	 * @param password1 is the users password to get into the system
	 */
	public User(final String username1, final String password1) {
		this.username = username1;
		this.password = password1;
	}
	/**
	 * @param username this is the username of the user
	 */
	private String username;
	/**
	 * @param password this is the password of the user
	 */
	private String password;
	/**
	 * this lets outside sources get the username.
	 * @return returns the username
	 */
	public final String getUsername() {
		return username;
	}
	/**
	 * This takes a new username and changes the
	 * users username1 to the new one.
	 * @param username1 is the new username
	 */
	public final void setUsername(final String username1) {
		this.username = username1;
	}
	/**
	 * This lets other parts of the software
	 * get the users password.
	 * @return returns the password
	 */
	public final String getPassword() {
		return password;
	}
	/**
	 * This takes a new password and changes the
	 * users password1 to the new one.
	 * @param password1 is the new password
	 */
	public final void setPassword(final String password1) {
		this.password = password1;
	}
}

package com.mhcs.brenda;

import java.util.ArrayList;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.mhcs.client.MainPanel;

/**
 * This is the login manager that takes care of
 * everything to do with logging into the system.
 * @author brenda
 *
 */
public class LoginManager {

    /**
     * @param table this is the table that the login screen will be placed on
     */
    private FlexTable table;
    /**
     * @param textBoxUsername this is the textbox that
     * the user will enter in the username
     */
    private TextBox textBoxUsername;
    /**
     * @param textBoxPassword this is the textbox that
     * the user will enter in the password
     */
    private TextBox textBoxPassword;
	  /**
     * @param users is the list of usernames and passwords
     * that coincide with each other
     */
	private ArrayList<User> users;

	/**
	 * This class will be the first window that appears when the system is
	 * started
	 * there should be two text boxes, one for a username and one for a
	 * password.  there should also be a button called log-in.
	 * once the log-in button is clicked, the username and password will be
	 * tested against the list of usernames and passwords.
	 * If the username or passowrd is wrong, there will be an alert window
	 * that pops up and says "invalid username/password"
	 * If the username and password are correct and they match, then the
	 * screen will change into the actual user interface.
	 * the GWT API has a password text box that will be used for the textbox
	 * for the password.
	 * key value pairs using dictionary
	 */
	public LoginManager() {
		//create a new table and list for the
		//flex table and the list of users
		User user = new User("brooks", "bunny");
		users = new ArrayList<User>();
		users.add(user);
		table = new FlexTable();

		// label for username text box
		Label lblUsername = new Label("Username:");
		table.setWidget(0, 0, lblUsername);

		// username text box
		textBoxUsername = new TextBox();
		table.setWidget(0, 1, textBoxUsername);

		// label for password text box
		Label lblPassword = new Label("Password:");
		table.setWidget(1, 0, lblPassword);

		// password text box
		textBoxPassword = new PasswordTextBox();
		table.setWidget(1, 1, textBoxPassword);

		//this is the sign in button
		Button btnSignIn = new Button("Sign In");
		btnSignIn.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				String username = textBoxUsername.getText();
				String password = textBoxPassword.getText();
				//if the button is pressed when there
				//is nothing in the username or password
				if (username.length() == 0
						|| password.length() == 0) {
					Window.alert(
							"Username or"
							+ "password is empty.");
				} else if (checkUsernameAndPassword(
						username, password)) {
					/*this means that the username and
					 * passowrd are correct this will
					 * get rid of the login screen and
					 * bring the user to the main screen.
					 */
					SoundController soundController = new SoundController();
				    Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,//Switch with Basic
				        "sounds/ConnectionSuccessful.mp3");//filepath war/sounds/ConnectionSuccessful

				    sound.play();
					changeScreen();
				} else {
					/*this means that the username
					 * or password is incorrect
					 */
					Window.alert(
							"Username or password"
					+ " is incorrect.");
					textBoxUsername.setText("");
					textBoxPassword.setText("");
				}
			}
		});
		table.setWidget(3, 1, btnSignIn);
	}

	/**
	 * This function will crereturn the sign in table to the caller
	 * this will be used to start up the system.
	 * The button in this will check that the username and password
	 * are correct then let the user login
	 * To login the screen change fucntion is called.
	 * @return returns the table that has the login features in it.
	 */
	public final FlexTable getTable() {
		return table;
	}

	/**
	 * this function checks the username and the password
	 * against the usernames and passwords that
	 * are stored in in the users list.
	 * @param username this is the username that the user entered
	 * @param password this is the password that the user entered
	 * @return this returns a boolean that is true if the username
	 * and password are the same as one of the users passwords.
	 */
	private boolean checkUsernameAndPassword(
			final String username, final String password) {
		for (int i = 0; i < users.size(); i++) {
			User person = users.get(i);
			String personUsername = person.getUsername();
			String personPassword = person.getPassword();
			if (username.equals(personUsername)
					&& password.equals(personPassword)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * this class clears the root panel then it makes
	 * a new main panel which will have all of the
	 * other user stories in it.
	 */
	private void changeScreen() {

		//clears the root panel
		RootLayoutPanel.get().clear();
		new MainPanel();
	}


}

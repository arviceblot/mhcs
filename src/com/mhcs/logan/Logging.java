package com.mhcs.logan;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Module Login Panel.
 * @author Logan Sales
 * @version 1.1
 */
public class Logging {
	/**
	 * Module manager instance.
	 */
	private final transient ModuleManager modManager;
	/**
	 * Sample output area for debugging.
	 */
	private final TextArea output;
	/**
	 * Main panel to add to basic GUI.
	 */
	private final VerticalPanel mainPanel;
	/**
	 * Width for user input UI fields.
	 */
	private final transient String inputWidth = "9pc";

	/**
	 * Default constructor for logging.
	 */
	public Logging() {
		// Get an instance for the module manager for iterating over, etc.
		this.modManager = ModuleManager.getInstance();
		final FlexTable table = new FlexTable();
		
		// Create the input box for getting the module code.
		final Label codeLabel = new Label("Code");
		final IntegerBox codeBox = new IntegerBox();
		codeBox.addKeyPressHandler(new KeyPressHandler() {
			/**
			 * No need to add anything here for now.
			 */
			@Override
			public void onKeyPress(final KeyPressEvent event) {
				// Do not add digit-only part
			}
		});
		codeBox.setAlignment(TextAlignment.RIGHT);
		codeBox.setMaxLength(3);
		codeBox.setWidth(this.inputWidth);
		// Make a panel to add
		table.setWidget(0, 0, codeLabel);
		table.setWidget(0, 1, codeBox);

		// Create the list box for the module status.
		final Label statusLabel = new Label("Status");
		final ListBox statusBox = new ListBox();
		// Create the possible options from those available
		statusBox.addItem(Status.UNDAMAGED.toString());
		statusBox.addItem(Status.DAMAGED.toString());
		statusBox.addItem(Status.UNCERTAIN.toString());
		statusBox.setVisibleItemCount(1);
		statusBox.setWidth(this.inputWidth);
		// add them to the table
		table.setWidget(1, 0, statusLabel);
		table.setWidget(1, 1, statusBox);

		// Create the list box for the orientation.
		final Label orientationLabel = new Label("Orientation");
		final ListBox orientationBox = new ListBox();
		// Create the possible options from those available
		orientationBox.addItem(Orientation.UPRIGHT.toString());
		orientationBox.addItem(Orientation.ONETURN.toString());
		orientationBox.addItem(Orientation.TWOTURNS.toString());
		orientationBox.setVisibleItemCount(1);
		orientationBox.setWidth(this.inputWidth);
		// add them to the table
		table.setWidget(2, 0, orientationLabel);
		table.setWidget(2, 1, orientationBox);

		// Create the integer boxes for the x and y coordinates.
		// These must not be TextBoxes as GWT does not allow valid
		// integer parsing from them.
		final Label xLabel = new Label("X");
		final IntegerBox xBox = new IntegerBox();
		xBox.addKeyPressHandler(new KeyPressHandler() {
			/**
			 * No need to add anything here for now.
			 */
			@Override
			public void onKeyPress(final KeyPressEvent event) {
				// Do not add digit-only part
			}
		});
		xBox.setAlignment(TextAlignment.RIGHT);
		xBox.setMaxLength(3);
		xBox.setWidth(this.inputWidth);
		// add them to the table
		table.setWidget(3, 0, xLabel);
		table.setWidget(3, 1, xBox);

		// Input for the y coordinate
		final Label yLabel = new Label("Y");
		final IntegerBox yBox = new IntegerBox();
		yBox.addKeyPressHandler(new KeyPressHandler() {
			/**
			 * No need to add anything here for now.
			 */
			@Override
			public void onKeyPress(final KeyPressEvent event) {
				// Do not add digit-only part
			}
		});
		yBox.setAlignment(TextAlignment.RIGHT);
		yBox.setMaxLength(3);
		yBox.setWidth(this.inputWidth);
		// add them to the table
		table.setWidget(4, 0, yLabel);
		table.setWidget(4, 1, yBox);

		// Module list output/debugging
		this.output = new TextArea();
		this.output.setCharacterWidth(36);
		this.output.setVisibleLines(5);
		this.output.setReadOnly(true);

		// Button to log the module
		final Button log = new Button("Log", new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				// Check all fields have entries first, otherwise
				// null pointer exception.
				int newCode = 0;
				int newX = 0;
				int newY = 0;
				try {
					newCode = codeBox.getValue();
					newX = xBox.getValue();
					newY = yBox.getValue();
				} catch (NullPointerException e) {
					return;
				}

				// Check the status
				Status newStatus;
				if (Status.UNDAMAGED.equalsName(statusBox.getValue(statusBox
						.getSelectedIndex()))) {
					newStatus = Status.UNDAMAGED;
				} else if (Status.DAMAGED.equalsName(statusBox
						.getValue(statusBox.getSelectedIndex()))) {
					newStatus = Status.DAMAGED;
				} else {
					newStatus = Status.UNCERTAIN;
				}

				// Check the orientation
				Orientation newOrientation;
				if (Orientation.UPRIGHT.equalsName(orientationBox
						.getValue(orientationBox.getSelectedIndex()))) {
					newOrientation = Orientation.UPRIGHT;
				} else if (Orientation.ONETURN.equalsName(orientationBox
						.getValue(orientationBox.getSelectedIndex()))) {
					newOrientation = Orientation.ONETURN;
				} else {
					newOrientation = Orientation.TWOTURNS;
				}

				// Create the new module to add based on given input.
				final Module newModule = new Module(newCode, newStatus,
						newOrientation, newX, newY);
				if (ModuleManager.add(newModule)) {
					// If the add was valid, print each module added.
					final StringBuffer outString = new StringBuffer();
					for (Module module : modManager) {
						outString.append(module.getCode());
						outString.append("\t");
						outString.append(module.getStatus().toString());
						outString.append("\t");
						outString.append(module.getOrientation().toString());
						outString.append("\t");
						outString.append(module.getX());
						outString.append("\t");
						outString.append(module.getY());
						outString.append("\t\n");
					}
					SoundController soundController = new SoundController();
				    Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,//Switch with Basic
				        "sounds/ModuleLogged.mp3");//filepath war/sounds/ModuleLogged
				    sound.play();
					output.setText(outString.toString());
				} else {
					SoundController soundController = new SoundController();
				    Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,//Switch with Basic
				        "sounds/Error.mp3");//filepath from HTML war/sounds/Error
				    sound.play();
					output.setText("ERROR");
				}

				// Reset all fields for next module input
				codeBox.setText("");
				statusBox.setSelectedIndex(0);
				orientationBox.setSelectedIndex(0);
				xBox.setText("");
				yBox.setText("");
			}
		});

		// OK formatting.
		// This will have the buttons and output
		table.setWidget(5, 1, log);

		// Add everything to the main panel for ease
		this.mainPanel = new VerticalPanel();
		this.mainPanel.add(table);
		this.mainPanel.add(new Histogram().getMainPanel());
	}
	
	public TextArea getOutput() {
		return this.output;
	}

	/**
	 * Getter for the main panel.
	 * @return mainPanel panel
	 */
	public final Panel getMainPanel() {
		return this.mainPanel;
	}
}

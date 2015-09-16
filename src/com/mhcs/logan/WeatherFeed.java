package com.mhcs.logan;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * Weather feed for the astronauts!
 * @author Logan Sales
 * @version 1.1
 */
public class WeatherFeed {
	/**
	 * Attempts to reconnect/refresh the weather feed.
	 */
	private final Button refresh;
	/**
	 * String representation of the temperature.
	 */
	private String sTemp;
	/**
	 * String representation of the visibility.
	 */
	private String sVisibility;
	/**
	 * Main panel for GUI.
	 */
	private final HorizontalPanel mainPanel;

	/**
	 * Default constructor.
	 */
	public WeatherFeed() {
		this.sTemp = "";
		this.sVisibility = "";
		this.mainPanel = new HorizontalPanel();
		final Label wLabel = new Label();

		// Wunderground logo. May not be shrunk further
		final Image logo = new Image("images/wundergroundLogo_blue_horz.jpg");

		// This button will handle reconnecting to wunderground.
		this.refresh = new Button("Refresh", new ClickHandler() {
			/**
			 * Connect can be called at any time to refresh the weather
			 * information.
			 */
			public void connect() {
				// URL access for the Wunderground API with
				// Logan's developer key
				String url = "http://api.wunderground.com/api/2b1d5ebe9971b83d"
						+ "/conditions/q/MN/Duluth.json?";
				url = URL.encode(url);

				// Create this in order to connect to wunderground
				final JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
				jsonp.requestObject(url, new AsyncCallback<JavaScriptObject>() {
					@Override
					public void onFailure(final Throwable caught) {
						// Seems to pop up regardless in onModuleLoad
						Window.alert("Weather feed unavailable!");
					}
					
					@Override
					public void onSuccess(final JavaScriptObject jsValue) {
						final JSONObject obj = new JSONObject(jsValue);
						final String result = obj.toString();

						// This is for the main parsing object
						final JSONObject jsonA = (JSONObject)
								JSONParser.parseLenient(result);

						// From here we can get any field from jsonA
						// But first get past the first layer
						final JSONValue jTry = jsonA.get("current_observation");
						final JSONObject jsonB = (JSONObject)
								JSONParser.parseLenient(jTry.toString());

						// then we can get temp, visibility, etc
						final JSONValue temp = jsonB.get("temp_c");
						final JSONValue visibility = jsonB.get("visibility_km");
						
						// Format the temp string with a degrees C symbol
						setsTemp(temp.toString() + "\u2103");
						setsVisibility(visibility.toString() + " km");
						wLabel.setText(getsTemp() + " " + getsVisibility());
					}
				});
			}
			@Override
			public void onClick(final ClickEvent event) {
				// This way we can call connect from outside code
				this.connect();
			}
		});
		// Format the main panel so it looks OK
		this.mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		this.mainPanel.add(wLabel);
		this.mainPanel.add(logo);
		this.mainPanel.add(this.refresh);
	}
	
	/**
	 * Getter for the temperature string.
	 * @return temp string
	 */
	public final String getsTemp() {
		return this.sTemp;
	}

	/**
	 * Setter for temperature string.
	 * @param newTemp so set
	 */
	public final void setsTemp(final String newTemp) {
		this.sTemp = newTemp;
	}

	/**
	 * Getter for visibility string.
	 * @return visibility string
	 */
	public final String getsVisibility() {
		return this.sVisibility;
	}

	/**
	 * Setter for visibility string.
	 * @param newVisibility to set
	 */
	public final void setsVisibility(final String newVisibility) {
		this.sVisibility = newVisibility;
	}

	/**
	 * Getter for main panel.
	 * @return main panel
	 */
	public final Panel getMainPanel() {
		return this.mainPanel;
	}
}

package com.mhcs.justin;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mhcs.logan.Module;
import com.mhcs.logan.ModuleManager;
import com.mhcs.logan.Orientation;
import com.mhcs.logan.Status;

public class GPSStatusBar {
	//Creates storage variable here to allow for the button handler to clear storage
	private Storage moduleStore = Storage.getLocalStorageIfSupported();
	private boolean isConnected=false;
	private HorizontalPanel mainPanel;
	TextBox statusBox;
	TextBox inputTest;
	Label statusLabel;
	Label testCaseLabel;
	Button connectButton;
	Button disconnectButton;
	Button clearStorage;
	
	/**
	 * Determines if the gps system is connected, if it is connected
	 * The text in the status bar is set to display as such, else
	 * The text is set to connection not established and a window alert
	 * notifies the astronauts as such.
	 * @param isConnected
	 */
	public GPSStatusBar() {
		//Creation of the panel that will hold each of the widgets 
		// of the status bar
		mainPanel = new HorizontalPanel();
		//create a status label
		statusLabel = new Label();
		//set the text of the label so the user can identify
		//the purpose of the text box located tangent to the 
		//status label
		statusLabel.setText("GPS Status:");
		
		statusBox = new TextBox();
		statusBox.setSize("400", "20");
		statusBox.setReadOnly(true);
		
		
		testCaseLabel = new Label();
		testCaseLabel.setText("Test Case:");
		inputTest = new TextBox();
		inputTest.setMaxLength(1);
		
		connectButton = new Button("Attempt Connect", new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//Code for the data reading from the gps
				String url = "http://www.d.umn.edu/~abrooks/SomeTests.php?q="+inputTest.getValue();
				url = URL.encode(url);
				
				// Send request to server and catch any errors.
				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
				
				try {
					Request request = builder.sendRequest(null, new RequestCallback() {
					public void onError(Request request, Throwable exception) {
					Window.alert("onError: Could NOT retrieve JSON");
					}
					public void onResponseReceived(Request request, Response response) {
						if (200 == response.getStatusCode()) { 
							
							isConnected=true;
							if(isConnected) {
								statusBox.setText("Connection Established");

							} else{			
								//Window.alert("GPS Connection Unavailable!");
								statusBox.setText("Connection Not Established... Attempting Reconnect");
								//below: isConnected is set to just receive the connection from the 
								//connection test class the test class is set to simulate a connection
								//from the system. The connection is set to the value delivered by the 
								//simulated connection class. 
								//isConnected = ConnectionTester.getConnection();
								//pingSystem(isConnected);
								
							}
							
							
						String rt = response.getText();
/*					    SoundController soundController = new SoundController();
					    Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_BASIC,//Switch with Basic
					        "sounds/ConnectionSuccessful.mp3");//filepath war/sounds/ConnectionSuccessful

					    sound.play();*/
						//creates new storage
//						StorageClass store = new StorageClass();
						//store.updateModuleManager(GPSData);
						update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
						} else { //Status code is not 200
						Window.alert("Could not retrieve JSON (" + response.getStatusText()
						+ ")");
						}
						}
						});
						} catch (RequestException e) {
						Window.alert("RequestException: Couldn't retrieve JSON");
						}
				}
				
				
				public void update(String rt) {
					VerticalPanel vp = new VerticalPanel();
					vp.add(new Label(rt)); //TO VIEW
					//RootLayoutPanel.get().add(vp);
					String sAll = rt;
				    //This is creating a JSON array that will be used to be parsed
				    //Once parsed new modules will be created from it and stored in the module manager
				    //This code stays.
				    JSONArray jA = 
				       (JSONArray)JSONParser.parseLenient(rt);
				    //Declaration of JSONNumber & etc. used for parsing storage
				        JSONNumber jN;
				        //declaration of JSONString js
				        JSONString jS;
				        //code is used for module code number, i.e. 1 = 001 or module 1.
				        int code;
				        //numberOfTurns is used for the amount of turns required for the
				        //module to be situated on the surface so it is upright
				        int numberOfTurns;
				        //x is representative of the x location of the module relative 
				        //to the landing area grid.
				        int x;
				        //y is representative of the y location of the module relative 
				        //to the landing area grid
				        int y;
				        //statusString is representative of if the module is damaged
				        //or uncertain, or undamaged.
				        String statusString;
				        //End of variable declaration for variables used to parse
				        
				        for (int i = 0; i < jA.size(); i++) {
				        	//Gets JSON Object from the JSONArray, the first object found.
				            JSONObject jO = (JSONObject)jA.get(i);
				            //Gets stuff after code: in the string
				            //String GPSData = "[" +
				            //"{code:001,status:\"undamaged\",turns:0,X:5,Y:5}," +
				            //"{code:002,status:\"undamaged\",turns:0,X:5,Y:6}," +
				            //"]";
				            //Set the JSONNumber equal to the code located within the
				            //JSON object. JSONObject is the value in the key,value pair
				            //in the string.
				            jN = (JSONNumber) jO.get("code");
				            //Set the code equal to the JSONNumber casted to an integer.
				            //The code will later be utilized by the underlying module code logic.
				            code = (int) jN.doubleValue();
				            //Parse the JO for a string known as status
				            jS = (JSONString) jO.get("status");
				            //put the JS string into the variable statusString
				            statusString = jS.stringValue();
				            //set now the JSONNumber to turns, taken from the JSONObject
				            jN = (JSONNumber) jO.get("turns");
				            //number of turns is set to the value obtained from the 
				            //JSONNumber which was pulled from the JSONObject
				            numberOfTurns = (int) jN.doubleValue();
				            //Pull the JSONNumber from the JSONObject
				            jN = (JSONNumber) jO.get("X");
				            //set the x location to the to the value obtained from the 
				            //JSONObject
				            x = (int) jN.doubleValue();
				            //Repeat the above two lines for the y component
				            //Pull from object, set y equal to value piece
				            jN = (JSONNumber) jO.get("Y");
				            y = (int) jN.doubleValue();
				            assert x>=0;
				            assert y>=0;
				            assert code>=1;
							// creates a new module, given the:
							// (CODE, STATUS, ORIENTATION, and X, Y coords)  
							Module newModule = new Module(code, Status.getStatus(statusString), 
									//get the orientation and x,y for the new module.
									Orientation.getOrientation(numberOfTurns) , x, y);
									
							// If the module manager has no conflicting parameters,
							// then the output will be displayed and the module is added
							// However, if the module is conflicting, then an error
							// will be displayed explaining that the module is
							// duplicated
							if (ModuleManager.add(newModule) == true) {
							//	output.setText(modManager.toString());
							} else {
								Window.alert("Duplicate Module Code Entered");
							//	output.setText(modManager.toString());
							}
			 }
         }
	});
		
		disconnectButton = new Button("Disconnect", new ClickHandler() {
			public void onClick(ClickEvent event) {
				isConnected = false;
				statusBox.setText("Disconnected");
				Window.alert("Disconnected from Server.");
			    SoundController soundController = new SoundController();
			    Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,//Switch with Basic
			        "sounds/Disconnected.mp3");//filepath from HTML war/sounds/Error
			    sound.play();
			}
		});
		clearStorage = new Button("ClearData", new ClickHandler() {
			public void onClick(ClickEvent event) {
				moduleStore.clear();
			}
		});

		//sets the mainpanel components after this line to alignt to the center
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		//add the status label to the panel
		mainPanel.add(statusLabel);
		//add the status box to the mainpanel
		mainPanel.add(statusBox);
		//add the test input to mainpanel
		mainPanel.add(inputTest);
		//add the connection button to the mainpanel
		mainPanel.add(connectButton);
		//add the disconnect button to the mainpanel
		mainPanel.add(disconnectButton);
		//add the clearStorageButton to the mainpanel
		mainPanel.add(clearStorage);

	}
	//method yields the mainPanel which displays the status bar and all
	//relevant components.
	public Panel getMainPanel() {
		return mainPanel;
	}

}
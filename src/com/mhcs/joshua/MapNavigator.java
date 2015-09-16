package com.mhcs.joshua;

import java.util.LinkedList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.mhcs.logan.Module;
import com.mhcs.logan.ModuleManager;
import com.mhcs.logan.Orientation;
import com.mhcs.logan.Status;

public class MapNavigator {

	// constants
	final static private int BORDER_WIDTH = 1;
	final static private int MARK_SELECT_LOC = 0;
	final static private int MARK_BAD = 1;
	final static private int MARK_CURRENT_LOC = 2;
	final static private int ROVER_LOC = 3;
	final static private int MOVE_MODULES = 4;

	// public consts
	final static public int MODULE_AT_LOC = -2020;
	final static public int EMPTY_LOC = -1010;
	final static public int USABLE_LOC = -1020;
	final static public int BAD_LAND = -3010;
	final static public int OUT_OF_BOUNDS = -4010;
	final static public int MODULE_NOT_FOUND = -5010;
	final static public int STATUS_OK = 0;
	final static public int MODULE_HI = 1000;
	final static public int USABLE_LAND_LOWVAL = -1999;
	final static public int USABLE_LAND_HIGHVAL = -1000;
	final static public int NO_VAL = -9999;

	// private variables
	private int xMapImageSize;
	private int yMapImageSize;

	// current mouse location
	private int mouseRow;
	private int mouseCol;

	private int dragStartRow;
	private int dragStartCol;

	// current location selected
	private int selectedRow;
	private int selectedCol;

	// current location of the computer with this program
	private int currentRow;
	private int currentCol;

	// current location of the computer with this program
	private int roverRow;
	private int roverCol;

	private ListBox functBox;

	private int arrowColor;

	// keeps track of the arrows
	private LinkedList<int[]> arrowList;

	// list of where ghost modules are
	private LinkedList<Module> ghostList;

	// Each Image in the grid.
	private Image marsMap[][] = new Image[ImageURLs.MAP_ROWS][ImageURLs.MAP_COLS];

	/*
	 * MarpGrid EMPTY_LOC = Display map image chunk BAD_LAND = Display Red block
	 * for impossible to use map block <= 0 module number
	 */
	private Module mapGridData[][] = new Module[ImageURLs.MAP_ROWS][ImageURLs.MAP_COLS];

	private Grid marsGrid = new Grid(ImageURLs.MAP_ROWS, ImageURLs.MAP_COLS);
	private DockLayoutPanel wholeMapPanel;
	private Label topCords;

	/**
	 * Constructor this adds the and loads the basic map data. IMPORTANT: map
	 * counts down 0,0 is at the top
	 * 
	 */
	public MapNavigator() {

		marsGrid.setStyleName("mhcsmarsmap");

		// set the selected location to not on map.
		selectedRow = EMPTY_LOC;
		selectedCol = EMPTY_LOC;

		// set the location of the computer holding the laptop nowhere on the
		// map.
		currentRow = EMPTY_LOC;
		currentCol = EMPTY_LOC;

		roverRow = EMPTY_LOC;
		roverCol = EMPTY_LOC;

		mouseRow = EMPTY_LOC;
		mouseCol = EMPTY_LOC;

		arrowList = new LinkedList<int[]>();
		ghostList = new LinkedList<Module>();

		arrowColor = ImageURLs.GREEN;

		functBox = new ListBox(false);
		functBox.setTitle("Map Functionality");
		functBox.addItem("Select Area");
		functBox.addItem("Mark Bad");
		functBox.addItem("Current Location");
		functBox.addItem("RoverLoc");
		functBox.addItem("Move Modules");
		functBox.setVisibleItemCount(1);

		ModuleDataEvent.register(ModuleManager.EVENT_BUS,
				new ModuleDataEvent.Handler() {
					public void onAddedModule(ModuleDataEvent modEvent) {

						addModule(ModuleManager.getModule(modEvent
								.getModuleID()));

					}

					@Override
					public void onRemovedModule(ModuleDataEvent modEvent) {

						final Module remMod = ModuleManager.getModule(modEvent
								.getModuleID());

						removeModule(remMod.getCode(), remMod.getY(),
								remMod.getX());

					}

					public void onUpdateModule(ModuleDataEvent modEvent) {

						// a move event basicly
						removeModule(modEvent.getModuleID());

						addModule(ModuleManager.getModule(modEvent
								.getModuleID()));

					}

				});

		// Sets the image size
		xMapImageSize = ImageURLs.MAP_X_1TO1_SIZE;
		yMapImageSize = ImageURLs.MAP_Y_1TO1_SIZE;
		topCords = new Label("");

		final ScrollPanel mapScroll = new ScrollPanel(marsGrid);
		wholeMapPanel = new DockLayoutPanel(Unit.EM);
		final FlowPanel ctrlPanel = new FlowPanel();

		marsGrid.setBorderWidth(BORDER_WIDTH);
		marsGrid.setCellPadding(0);
		marsGrid.setCellSpacing(0);

		// load map images sets this to the map images
		for (int row = 0; row < ImageURLs.MAP_ROWS; ++row) {
			for (int col = 0; col < ImageURLs.MAP_COLS; ++col) {

				mapGridData[row][col] = new Module(EMPTY_LOC, Status.UNDAMAGED,
						Orientation.UPRIGHT, col, row);
				setMapImage(row, col);
				marsGrid.getCellFormatter().setStyleName(row, col,
						"mapdefaultcell");

			}
		}

		final Button zoomIn = new Button("Zoom In");
		zoomIn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (xMapImageSize < 200 && yMapImageSize < 200) {
					xMapImageSize = xMapImageSize + 10;
					yMapImageSize = yMapImageSize + 10;
					changeMapSize();
				}
			}
		});

		final Button zoomOut = new Button("Zoom Out");
		zoomOut.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (xMapImageSize > 10 && yMapImageSize > 10) {
					xMapImageSize = xMapImageSize - 10;
					yMapImageSize = yMapImageSize - 10;
					changeMapSize();
				}
			}
		});

		final Button zoomReset = new Button("Reset Zoom");
		zoomReset.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				xMapImageSize = ImageURLs.MAP_X_1TO1_SIZE;
				yMapImageSize = ImageURLs.MAP_Y_1TO1_SIZE;
				changeMapSize();
			}
		});

		marsGrid.addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {
				event.preventDefault();
				// check if drop events enabled.
				if (functBox.getSelectedIndex() == MOVE_MODULES) {

					moveModule(dragStartRow, dragStartCol, mouseRow, mouseCol);

				}

			}
		});

		final Button searchForModule = new Button("Find Module");
		searchForModule.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (inMapRange(roverRow, roverCol)) {

					findPathToModule(roverRow, roverCol);

				}
			}
		});

		final Button clrArrow = new Button("clear \narrows");
		clrArrow.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (inMapRange(roverRow, roverCol)) {

					clearArrows();

				}
			}
		});

		ctrlPanel.add(zoomIn);
		ctrlPanel.add(zoomOut);
		ctrlPanel.add(zoomReset);
		ctrlPanel.add(functBox);
		ctrlPanel.add(searchForModule);
		ctrlPanel.add(clrArrow);
		wholeMapPanel.addNorth(topCords, 2);
		wholeMapPanel.addSouth(ctrlPanel, 3);
		wholeMapPanel.add(mapScroll);

	}

	/**
	 * adds a module based on the module
	 * 
	 * @param module
	 *            module info
	 * @return returns 0 for everything was ok, -101 for object already there,
	 *         BAD_LAND for unusable land and -9001 for out of map bounds
	 * 
	 */
	public int addModule(final Module module) {

		int status = mapLocStatus(module);
		if (status == USABLE_LOC) {

			mapGridData[module.getY()][module.getX()] = module;
			setMapImage(module.getY(), module.getX());
			status = STATUS_OK;

		}

		return status;
	}

	/**
	 * adds a module at the row and col, of the specific number
	 * 
	 * @param ModuleNumber
	 *            the unique ID number for the module
	 * @param row
	 *            the Y location where the module should be placed
	 * @param col
	 *            the X location where the module should be placed
	 * @return return BAD_LAND for land unusable, MODULE_AT_LOC for land
	 *         occupied, OUT_OF_BOUNDS for out of map and 0 for everything is ok
	 * 
	 */
	public int addModule(int moduleNumber, int row, int col) {
		int status = mapLocStatus(row, col);

		if (status == USABLE_LOC) {

			mapGridData[row][col] = ModuleManager.getModule(moduleNumber);
			setMapImage(row, col);
			status = STATUS_OK;

		}

		return status;

	}

	/**
	 * removes the Module at map location and then returns it. if there was any.
	 * 
	 * @param row
	 * @param col
	 * @return module at that map locatoin, ret
	 */
	public Module getAndRemoveModule(int row, int col) {

		int status = mapLocStatus(row, col);
		Module theMod = null;
		theMod = mapGridData[row][col];

		if (status == MODULE_AT_LOC) {

			mapGridData[row][col] = new Module(EMPTY_LOC, Status.UNDAMAGED,
					Orientation.UPRIGHT, col, row);
			;
			setMapImage(row, col);

			status = STATUS_OK;

		}

		return theMod;

	}

	/**
	 * This removes the module with the specific number
	 * 
	 * @param moduleNumber
	 *            module number that should be at that locatoin
	 * @param row
	 *            the row on the map
	 * @param col
	 *            the column on the map
	 * @return STATUS_OK if found, EMPTY_LOC for not there, MODULE_AT_LOC if the
	 *         wrong module is there.
	 */
	public int removeModule(int moduleNumber, int row, int col) {

		int status = mapLocStatus(row, col);

		if (status == MODULE_AT_LOC
				&& mapGridData[row][col].getCode() == moduleNumber) {

			mapGridData[row][col] = new Module(EMPTY_LOC, Status.UNDAMAGED,
					Orientation.UPRIGHT, col, row);
			;
			setMapImage(row, col);

			status = STATUS_OK;

		}

		return status;

	}

	/**
	 * This removes the module with the specific number number of the module
	 * 
	 * @param moduleNumber
	 *            module number that should be at that locatoin
	 * @param row
	 *            the row on the map
	 * @param col
	 *            the column on the map
	 * @return STATUS_OK if found, EMPTY_LOC for not there, MODULE_AT_LOC if the
	 *         wrong module is there.
	 */
	public int removeModule(Module module, int row, int col) {

		int status = mapLocStatus(row, col);

		if (status == MODULE_AT_LOC
				&& mapGridData[row][col].getCode() == module.getCode()) {

			mapGridData[row][col] = new Module(EMPTY_LOC, Status.UNDAMAGED,
					Orientation.UPRIGHT, col, row);
			;
			setMapImage(row, col);

			status = STATUS_OK;

		}

		return status;

	}

	/**
	 * Only use if you are unsure of placement, remove with row and col much
	 * more efficent This will also only the first instance found within the
	 * map, and not all of them
	 * 
	 * @param moduleNumber
	 * @return STATUS_OK if found, EMPTY_LOC for not there, MODULE_AT_LOC if the
	 *         wrong module is there.
	 */
	public int removeModule(int moduleNumber) {
		int status = MODULE_NOT_FOUND;

		for (int row = 0; row < ImageURLs.MAP_ROWS; ++row) {
			for (int col = 0; col < ImageURLs.MAP_COLS; ++col) {

				if (mapGridData[row][col].getCode() == moduleNumber) {

					mapGridData[row][col] = new Module(EMPTY_LOC,
							Status.UNDAMAGED, Orientation.UPRIGHT, col, row);

					setMapImage(row, col);

					status = STATUS_OK;

				}

			}
		}

		return status;

	}

	/**
	 * Checks the location cited in the location of the module but doesn't look
	 * any further if it can not find it.
	 * 
	 * @param module
	 *            the module with the data
	 * @return STATUS_OK if found, EMPTY_LOC for not there, MODULE_AT_LOC if the
	 *         wrong module is there.
	 */
	public int removeModule(Module module) {

		int status = MODULE_NOT_FOUND;

		int thatrow = module.getY();

		int thatcol = module.getX();

		if (mapGridData[thatrow][thatcol].getCode() == module.getCode()) {

			mapGridData[thatrow][thatcol] = new Module(EMPTY_LOC,
					Status.UNDAMAGED, Orientation.UPRIGHT, thatcol, thatrow);
			;
			setMapImage(thatrow, thatcol);

			status = STATUS_OK;

		}

		return status;

	}

	/**
	 * Checks the location cited in the location of the module but then looks
	 * for it on the map if it can not find it
	 * 
	 * @param module
	 *            the module with the data
	 * @return STATUS_OK if found, EMPTY_LOC for not there, MODULE_AT_LOC if the
	 *         wrong module is there.
	 */
	public int removeModuleOrLookForIt(Module module) {

		int status = MODULE_NOT_FOUND;

		int thatrow = module.getY();

		int thatcol = module.getX();

		if (mapGridData[thatrow][thatcol].getCode() == module.getCode()) {

			mapGridData[thatrow][thatcol] = new Module(EMPTY_LOC,
					Status.UNDAMAGED, Orientation.UPRIGHT, thatcol, thatrow);
			;
			setMapImage(thatrow, thatcol);

			status = STATUS_OK;

		}

		for (int row = 0; row < ImageURLs.MAP_ROWS; ++row) {
			for (int col = 0; col < ImageURLs.MAP_COLS; ++col) {

				if (mapGridData[row][col].getCode() == module.getCode()) {

					mapGridData[row][col] = new Module(EMPTY_LOC,
							Status.UNDAMAGED, Orientation.UPRIGHT, col, row);
					;
					setMapImage(row, col);

					status = STATUS_OK;

				}

			}
		}

		return status;

	}

	/**
	 * moves a module
	 * 
	 * @param startRow
	 * @param startCol
	 * @param endRow
	 * @param endCol
	 * @return status of move
	 */
	public int moveModule(final int startRow, final int startCol,
			final int endRow, final int endCol) {

		int status = mapLocStatus(startRow, startCol);

		if (status == MODULE_AT_LOC) {

			Module movingMod = mapGridData[startRow][startCol];

			status = mapLocStatus(endRow, endCol);

			if (status == USABLE_LOC) {

				mapGridData[startRow][startCol] = new Module(EMPTY_LOC,
						Status.UNDAMAGED, Orientation.UPRIGHT, startCol,
						startRow);
				setMapImage(startRow, startCol);

				movingMod.setY(endRow);
				movingMod.setX(endCol);

				mapGridData[endRow][endCol] = movingMod;
				setMapImage(endRow, endCol);

				status = STATUS_OK;

			}

		}

		return status;

	}

	/**
	 * Finds the info at the postion specified
	 * 
	 * @param row
	 *            Y position to look at module
	 * @param col
	 *            X posistion to look at module
	 * @return the module if found or null if not found
	 */
	public Module getModuleAt(final int row, final int col) {
		Module module = null;

		if (mapGridData[row][col].getCode() > EMPTY_LOC) {

			module = mapGridData[row][col];
		}

		return module;

	}

	/**
	 * getPanel() requires no arguments.
	 * 
	 * @return returns a panel of the map
	 */
	public Panel getPanel() {
		return wholeMapPanel;
	}

	/**
	 * Sets the Maps's size. This size does not include decorations such as
	 * border, margin, and padding.
	 * 
	 * @param xSize
	 *            the object's new width, in CSS units (e.g. "10px", "1em")
	 * @param ySize
	 *            the object's new height, in CSS units (e.g. "10px", "1em"
	 * @return the map panel and controls to the size you specified
	 */
	public Panel getPanel(final String xSize, final String ySize) {
		wholeMapPanel.setSize(xSize, ySize);
		return wholeMapPanel;
	}

	/**
	 * Sets the Map Panel Size in pixels specified
	 * 
	 * @param xSize
	 *            sets the pixel size width
	 * @param ySize
	 *            sets the pixel size height
	 * @return returns a panel with your specififed pixel size with map controls
	 */
	public Panel getPanelPixelSize(final int xSize, final int ySize) {
		wholeMapPanel.setPixelSize(xSize, ySize);
		return wholeMapPanel;
	}

	/**
	 * so some code does not break should be replaced with getPanel()
	 * 
	 * @return a map panel with controls
	 */
	public Panel getMapGrid() {
		return getPanel();
	}

	/**
	 * Is the X and Y or row and col within map bounds.
	 * 
	 * @param row
	 *            Y value for map
	 * @param col
	 *            X value for map
	 * @return true if it within the maps bounds
	 */
	public boolean inMapRange(final int row, final int col) {
		boolean inRange = false;
		if (row < ImageURLs.MAP_ROWS && row < ImageURLs.MAP_COLS && row >= 0
				&& col >= 0) {

			inRange = true;

		}

		return inRange;

	}

	/**
	 * sets the Rover Location
	 * 
	 * @param row
	 * @param col
	 * @return returns error codes or 0 for ok
	 */
	public int setRoverLoc(final int row, final int col) {
		int status = BAD_LAND;

		if (roverRow == row && roverCol == col) {

			roverRow = EMPTY_LOC;
			roverCol = EMPTY_LOC;

		} else if (roverRow < 0 && roverCol < 0) {

			roverRow = row;
			roverCol = col;
			status = STATUS_OK;

		} else {

			int oldRow = roverRow;
			int oldCol = roverCol;
			roverRow = row;
			roverCol = col;
			borderColor(oldRow, oldCol);

			status = STATUS_OK;

		}

		borderColor(row, col);

		return status;

	}

	/**
	 * accessor for rover Variable Y or Row
	 * 
	 * @return returns the Y or Row value can return negative number if no rover
	 *         present
	 */
	public int getRoverRow() {
		return roverRow;
	}

	/**
	 * accesser for the rover Variable X or Col
	 * 
	 * @return returns the X or Col value can return negative value if no rover
	 *         present
	 */
	public int getRoverCol() {
		return roverCol;
	}

	/**
	 * sets the current location of this program
	 * 
	 * @param row
	 * @param col
	 * @return module with the lecation of were it is or at least it has X and Y
	 *         data in it.
	 */
	public Module setCurrentLoc(final int row, final int col) {

		if (currentRow == row && currentCol == col) {

			currentRow = EMPTY_LOC;
			currentCol = EMPTY_LOC;

		} else if (currentRow < 0 && currentCol < 0) {

			currentRow = row;
			currentCol = col;

		} else {

			int oldRow = currentRow;
			int oldCol = currentCol;
			currentRow = row;
			currentCol = col;
			borderColor(oldRow, oldCol);

		}

		borderColor(row, col);

		String info = "Mars Map:  X = " + col + "   , Y = " + row;
		if (currentRow >= 0 && currentCol >= 0) {
			info = info + "\n" + "Current Location X = " + currentCol
					+ "  Y = " + currentRow;
		}
		topCords.setText(info);

		return mapGridData[row][col];

	}

	/**
	 * returns the module that is at the current locatoin of the laptop with
	 * this program, the Yellow square
	 * 
	 * @return module at the Yellow square location null if current is not
	 *         possible
	 */
	public Module getCurrentModule() {
		Module module = null;

		if (inMapRange(currentRow, currentCol)) {
			module = mapGridData[currentRow][currentCol];
		}

		return module;
	}

	/**
	 * gets the CurrentRow or Yellow sqaure Row or Y value can return a negative
	 * number of BAD_LAND
	 * 
	 * @return Row or Y value can return negative number for BAD_LAND
	 */
	public int getCurrentRow() {
		return currentRow;
	}

	/**
	 * gets the CurrentCol or Yellow square position Col or X value
	 * 
	 * @return Col or X value can return negative number for BAD_LAND value
	 */
	public int getCurrentCol() {
		return currentCol;
	}

	/**
	 * returns the module that is at the selected location the Blue square
	 * 
	 * @return module at seleted location can return null
	 */
	public Module getSelectedModule() {
		Module module = null;

		if (inMapRange(selectedRow, selectedCol)) {
			module = mapGridData[selectedRow][selectedCol];
		}

		return module;

	}

	/**
	 * accesser for Selected or Blue square Row or Y value
	 * 
	 * @return Row or Y value can return negative numbers when nothing is
	 *         selected
	 */
	public int getSelectedRow() {
		return selectedRow;
	}

	/**
	 * accessor for selected or Blue square Col or X value
	 * 
	 * @return Col or X value can return negative numbers when nothing is
	 *         selected
	 */
	public int getSelectedCol() {
		return selectedCol;
	}

	/**
	 * clears the Arrows on the map.
	 */
	public void clearArrows() {

		int[] arrow;
		int status;

		while (!arrowList.isEmpty()) {

			arrow = arrowList.remove();
			status = mapLocStatus(arrow[0], arrow[1]);
			if (status == USABLE_LOC) {
				mapGridData[arrow[0]][arrow[1]].setCode(EMPTY_LOC);
				setMapImage(arrow[0], arrow[1]);
			}

		}

	}

	/**
	 * Sets a ghost (a semi trasnparent image) of a module at ghostRow and
	 * ghostCol will not display anything on a bad block or when a module is
	 * already there.
	 * 
	 * @param module
	 *            module to be ghosted
	 * @param ghostRow
	 * @param ghostCol
	 */
	public void setGhost(final Module module, int ghostRow, int ghostCol) {

		int status = mapLocStatus(ghostRow, ghostCol);

		if (status == USABLE_LOC) {

			Module ghost = new Module(module.getCode() * -1,
					module.getStatus(), module.getOrientation(), ghostRow,
					ghostCol);
			mapGridData[ghostRow][ghostCol] = ghost;
			ghostList.add(ghost);
			setMapImage(ghostRow, ghostCol);
		}

	}

	/**
	 * clears all the ghost modules from the map unless it has changed
	 */
	public void clearGhost() {
		Module ghost;
		int status;

		while (!ghostList.isEmpty()) {

			ghost = ghostList.remove();
			status = mapLocStatus(ghost.getY(), ghost.getX());
			if (status == USABLE_LOC) {
				mapGridData[ghost.getY()][ghost.getX()].setCode(EMPTY_LOC);
				setMapImage(ghost.getY(), ghost.getX());
			}

		}
	}

	/**
	 * makes a path to one set location on the map does not erase the arrows of
	 * other calls, displays yellow arrows
	 * 
	 * @param startRow
	 * @param startCol
	 * @param endRow
	 * @param endCol
	 */
	public void showPath(final int startRow, final int startCol,
			final int endRow, final int endCol) {

		// check to make sure current and selected are not land locked
		if (inMapRange(startRow, startCol)) {

			int maxRows = ImageURLs.MAP_ROWS;
			int maxCols = ImageURLs.MAP_COLS;

			int pathGrid[][] = new int[maxRows][maxCols];

			for (int row = 0; row < maxRows; ++row) {
				for (int col = 0; col < maxCols; ++col) {
					int mapStatus = mapLocStatus(row, col);

					if (mapStatus == USABLE_LOC) {

						pathGrid[row][col] = mapSearch.PASSABLE;

					} else {

						pathGrid[row][col] = mapSearch.IMPASSABLE;

					}

				}
			}

			pathGrid[endRow][endCol] = mapSearch.GOALTILE;

			mapSearch search = new mapSearch(pathGrid, maxRows, maxCols);

			arrowPath(ImageURLs.YELLOW, search.search(startRow, startCol));

		}

	}

	/**
	 * this changes the images
	 * 
	 * @param row
	 *            row of image locatoin
	 * @param col
	 *            image Y location
	 */
	private void setMapImage(final int row, final int col) {

		marsMap[row][col] = new Image();
		final int thatRow = row;
		final int thatCol = col;
		marsMap[row][col].addErrorHandler(new ErrorHandler() {
			public void onError(ErrorEvent event) {

				// handle errors with error image
				marsMap[thatRow][thatCol].setUrl(ImageURLs.ERROR_URL);
				marsMap[thatRow][thatCol].setTitle("There is an Error");
			}
		});

		marsMap[row][col].setPixelSize(xMapImageSize, yMapImageSize);

		if (mapGridData[row][col].getCode() == BAD_LAND) {

			// Mark square as unusable with a redsquare
			marsMap[row][col].setUrl(ImageURLs.REDSQUARE_URL);
			marsMap[row][col].setTitle("Land is Unusable"
					+ locSelectionInfo(thatRow, thatCol));

		} else if (mapGridData[row][col].getCode() == EMPTY_LOC) {

			// add in blank image over the actual image.
			marsMap[row][col].setUrl(ImageURLs.BLANK_IMG_URL);
			marsMap[row][col].setTitle("Land usable at X = " + col + ", Y = "
					+ row + locSelectionInfo(thatRow, thatCol));

		} else if (mapGridData[row][col].getCode() <= ImageURLs.RIGHT_ARROW
				&& mapGridData[row][col].getCode() >= ImageURLs.DOWN_ARROW) {

			// load the right pointing arrow
			marsMap[row][col].setUrl(ImageURLs.getArrow(
					mapGridData[row][col].getCode(), arrowColor));
			marsMap[row][col].setTitle("Land usable at X = " + col + ", Y = "
					+ row + locSelectionInfo(thatRow, thatCol));

		} else if (mapGridData[row][col].getCode() > USABLE_LAND_LOWVAL
				&& mapGridData[row][col].getCode() < MODULE_HI) {

			marsMap[row][col].setUrl(ImageURLs
					.moduleIdToURL(mapGridData[row][col].getCode()));
			marsMap[row][col]
					.setTitle(getModuleDescripiton(mapGridData[row][col]));
			marsMap[row][col].addDragStartHandler(new DragStartHandler() {

				@Override
				public void onDragStart(DragStartEvent event) {
					dragStartRow = thatRow;
					dragStartCol = thatCol;
				}
			});

		} else {

			marsMap[row][col].setUrl(ImageURLs.ERROR_URL);
			marsMap[row][col].setTitle("Internal Program Error"
					+ locSelectionInfo(thatRow, thatCol));

		}

		marsMap[row][col].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				mapClickHelper(thatRow, thatCol);

			}
		});

		// things that every map location should have
		marsMap[row][col].setPixelSize(xMapImageSize, yMapImageSize);
		marsMap[row][col].addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {

				mouseRow = thatRow;
				mouseCol = thatCol;
				String info = "Mars Map:  X = " + mouseCol + "   , Y = "
						+ mouseRow;
				if (currentRow >= 0 && currentCol >= 0) {
					info = info + "\n" + "Current Location X = " + currentCol
							+ "  Y = " + currentRow;
				}
				topCords.setText(info);

			}
		});

		marsMap[row][col].addDragOverHandler(new DragOverHandler() {
			public void onDragOver(DragOverEvent event) {

				mouseRow = thatRow;
				mouseCol = thatCol;
				String info = "Mars Map:  X = " + mouseCol + "   , Y = "
						+ mouseRow;
				if (currentRow >= 0 && currentCol >= 0) {
					info = info + "\n" + "Current Location X = " + currentCol
							+ "  Y = " + currentRow;
				}
				topCords.setText(info);

			}
		});

		updateGrid(row, col);
	}

	/**
	 * Update a specific image on the map grid of the marsGrid map
	 * 
	 * @param row
	 *            which Image on the Y axis to update
	 * @param col
	 *            which Image on the X axis to update
	 */
	private void updateGrid(int row, int col) {

		marsGrid.setWidget(row, col, marsMap[row][col]);

	}

	/**
	 * Returns a string describing the module and its condition
	 * 
	 * @param module
	 *            module that you want the Description of
	 * @return string of the module condition
	 */
	private String getModuleDescripiton(final Module module) {
		String modInfo = "module information retrival error";

		modInfo = "Module Type: " + module.getType() + "\n"
				+ "Module Orentation: " + module.getOrientation().name() + "\n"
				+ "Module Status: " + module.getStatus().name()
				+ locSelectionInfo(module.getY(), module.getX());

		return modInfo;
	}

	/**
	 * Changes the the Grid data to if BAD_LAND (red block or impassable) if
	 * already EMPTY_LOC (map data) and then runs the image update.
	 * 
	 * @param row
	 *            Y axis portion that should be changed
	 * @param col
	 *            X axis portion that should be changed
	 */
	private void toggleImpassable(final int row, final int col) {

		int status = mapLocStatus(row, col);
		if (status == BAD_LAND) {

			mapGridData[row][col].setCode(EMPTY_LOC);

		} else if (status == USABLE_LOC) {

			mapGridData[row][col].setCode(BAD_LAND);

		}

		setMapImage(row, col);

	}

	/**
	 * this is for resizing the map images.
	 */
	private void changeMapSize() {
		for (int row = 0; row < ImageURLs.MAP_ROWS; ++row) {
			for (int col = 0; col < ImageURLs.MAP_COLS; ++col) {
				marsMap[row][col].setPixelSize(xMapImageSize, yMapImageSize);
			}
		}
	}

	/**
	 * marks the position that is selected on the map is located
	 * 
	 * @param row
	 * @param col
	 */
	private void setSelectedLoc(final int row, final int col) {

		if (selectedRow == row && selectedCol == col) {

			selectedRow = EMPTY_LOC;
			selectedCol = EMPTY_LOC;

		} else if (selectedRow < 0 && selectedCol < 0) {

			selectedRow = row;
			selectedCol = col;

		} else {

			int oldRow = selectedRow;
			int oldCol = selectedCol;
			selectedRow = row;
			selectedCol = col;
			borderColor(oldRow, oldCol);

		}

		borderColor(row, col);

	}

	/**
	 * returns the status of the module location
	 * 
	 * @param row
	 *            - int
	 * @param col
	 *            - int
	 * @return status if module is at location (not specific module), empty
	 *         space, land unsuable, out of map range,
	 */
	private int mapLocStatus(final int row, final int col) {
		int status = 0;

		if (!inMapRange(row, col)) {

			status = OUT_OF_BOUNDS; // out of bounds error number

		} else {

			int moduleCode = mapGridData[row][col].getCode();

			if (moduleCode >= 0) {
				status = MODULE_AT_LOC; // module at location error
			} else if (USABLE_LAND_HIGHVAL > mapGridData[row][col].getCode()
					&& USABLE_LAND_LOWVAL < mapGridData[row][col].getCode()) {
				status = USABLE_LOC; // module not at location;
			} else if (moduleCode == BAD_LAND) {
				status = BAD_LAND;
			}

		}

		return status;
	}

	/**
	 * gets information from module then runs the broken apart other version of
	 * it self this just gets the X and Y where the module is going to be
	 * placed. not the data on the module
	 * 
	 * @param module
	 *            the module you want to check the info on
	 * @return returns status of module location
	 */
	private int mapLocStatus(final Module module) {

		return mapLocStatus(module.getY(), module.getX());
	}

	/**
	 * avoids confusing if statments when setting the color of a border
	 * 
	 * @param row
	 * @param col
	 */
	private void borderColor(final int row, final int col) {

		if ((currentRow == row && currentCol == col && selectedRow == row && selectedCol == col)
				|| (roverRow == row && roverCol == col && selectedRow == row && selectedCol == col)
				|| (currentRow == row && currentCol == col && roverRow == row && roverCol == col)) {

			marsGrid.getCellFormatter().setStyleName(row, col, "mapwhitecell");

		} else if (currentRow == row
				&& currentCol == col
				&& ((selectedRow != row || selectedCol != col) || (roverRow != row || roverCol != col))) {

			marsGrid.getCellFormatter().setStyleName(row, col, "mapyellowcell");

		} else if (((currentRow != row || currentCol != col) || (roverRow != row || roverCol != col))
				&& selectedRow == row && selectedCol == col) {

			marsGrid.getCellFormatter().setStyleName(row, col, "mapbluecell");

		} else if (((currentRow != row || currentCol != col) || (selectedRow != row || selectedCol != col))
				&& (roverRow == row && roverCol == col)) {

			marsGrid.getCellFormatter().setStyleName(row, col, "maplimecell");

		} else {

			marsGrid.getCellFormatter()
					.setStyleName(row, col, "mapdefaultcell");

		}

	}

	/**
	 * sends a string telling the what is at the location such as the rover
	 * current selection, bad area, or even laptop position
	 * 
	 * @param row
	 * @param col
	 * @return a string starting a new line saying what is there
	 */
	private String locSelectionInfo(final int row, final int col) {

		String info = "";

		if (row == currentRow && col == currentCol) {

			info = info + "\nYou Are Currently Here";

		}

		if (row == selectedRow && col == selectedCol) {

			info = info + "\nThis Land is Selected";

		}

		if (row == roverRow && col == roverCol) {

			info = info + "\nThe Rover is currently Here";

		}

		return info;

	}

	/**
	 * made to help streamline the onClick events to one line
	 * 
	 * @param row
	 * @param col
	 */
	private void mapClickHelper(final int row, final int col) {
		final int status = mapLocStatus(row, col);

		if (functBox.getSelectedIndex() == MARK_SELECT_LOC) {

			setSelectedLoc(row, col);

		} else if (functBox.getSelectedIndex() == MARK_BAD
				&& (status == USABLE_LOC || status == BAD_LAND)) {

			toggleImpassable(row, col);

		} else if (functBox.getSelectedIndex() == MARK_CURRENT_LOC) {

			setCurrentLoc(row, col);

		} else if (functBox.getSelectedIndex() == ROVER_LOC) {

			setRoverLoc(row, col);

		}

	}

	/**
	 * finds the path to the closest module BFS
	 * 
	 * @param startRow
	 * @param startCol
	 * @param arrowColor
	 *            this is to change the arrow color (not used)
	 */
	private void findPathToModule(final int startRow, final int startCol) {

		clearArrows(); // clear other arrows

		// check to make sure current and selected are not land locked
		if (inMapRange(startRow, startCol)) {

			int maxRows = ImageURLs.MAP_ROWS;
			int maxCols = ImageURLs.MAP_COLS;

			int pathGrid[][] = new int[maxRows][maxCols];

			for (int row = 0; row < maxRows; ++row) {
				for (int col = 0; col < maxCols; ++col) {
					int mapStatus = mapLocStatus(row, col);

					if (mapStatus == USABLE_LOC) {

						pathGrid[row][col] = mapSearch.PASSABLE;

					} else if (mapStatus == MODULE_AT_LOC) {

						pathGrid[row][col] = mapSearch.GOALTILE;

					} else {

						pathGrid[row][col] = mapSearch.IMPASSABLE;

					}

				}
			}

			mapSearch search = new mapSearch(pathGrid, maxRows, maxCols);

			arrowPath(ImageURLs.GREEN, search.search(startRow, startCol));

		}

	}

	/**
	 * prints an arrow path.
	 * 
	 * @param arrowColor
	 *            using consts can change arrow colors
	 * @param arrowPath
	 *            arrow path
	 */
	private void arrowPath(final int arrowColor, LinkedList<int[]> arrowPath) {

		while (!arrowPath.isEmpty()) {
			int[] loc = arrowPath.removeLast();
			if (loc[2] != NO_VAL) {
				arrowList.add(loc);
				mapGridData[loc[0]][loc[1]].setCode(loc[2]);
				setMapImage(loc[0], loc[1]);

			}
		}
	}

}

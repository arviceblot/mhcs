package com.mhcs.brenda;

import com.mhcs.logan.Module;
/**
 * This is the class that determines a number based on how good a habitat configuration is designed.
 * @author brenda
 *
 */
public class QualityControl {

	public QualityControl(Module[][] MinConfig, int configRows, int configCols) {
		config = MinConfig;
		rows = configRows;
		columns = configCols;
	}
	/**
	 * this is the function you call to get the number that corresponds to how "good" a habitat is.
	 * @return the heuristic for the configuration is returned.
	 */
	public int Heuristic() {
		int dormitoryCount = 0;
		int sanitationCount = 0;
		Module current;
		for(int y = 0; y < rows; y++) {
			for(int x = 0; x < columns; x++) {
				current = config[y][x];
				if(current != null) {
					if (60 < current.getCode() && current.getCode()< 81) {
						dormitoryCount++;
						heuristic += spacesFromAirlock(x, y);
						if(dormitoryWithin2Plain(x, y)) {
							heuristic += 2;
						}
					} else if (90 < current.getCode() && current.getCode()< 101) {
						sanitationCount++;
						if(gymWithin1Plain(x, y)) {
							heuristic += 2;
						} else if(gymWithin2Plain(x, y)) {
							heuristic += 1;		
						} else {
							//not close enough to considered apart of a good configuration.
						}
					} else if (110 < current.getCode() && current.getCode()< 121) {
						if(canteenWithin2Plain(x, y)) {
							heuristic += 3;
						} else if(canteenWithin3Plain(x, y)) {
							heuristic +=2;
						} else {
							heuristic +=1;
						}
					} else if (180 < current.getCode() && current.getCode()< 185){
						if(spacesFromAirlock(x, y) <=2) {
							heuristic +=4;
						}
					} else {
						//do nothing because all of the heuristics have been checked for
					}
					
				}
			}
		}
		if(dormitoryCount/sanitationCount == 2) {
			heuristic += 2;
		}
		return heuristic;
	}
	/**
	 * this is the the function you call to change the configuatrion you want to find heuristics for.
	 * @param MinConfig
	 * @param configRows
	 * @param configCols
	 */
	public void changeConfiguration(Module[][] MinConfig, int configRows, int configCols) {
		config = MinConfig;
		rows = configRows;
		columns = configCols;
	}
	
	private boolean canteenWithin2Plain(int xStart, int yStart) {
		Module current;
		for(int x = xStart - 2; x <= xStart + 2; x++) {
			for(int y = yStart - 2; y <= yStart + 2; x++) {
				current = config[x][y];
				if(x == xStart || y==yStart) {
					//do nothing this is the starting module
				} else {
					if (140 < current.getCode() && current.getCode()< 145) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean canteenWithin3Plain(int xStart, int yStart) {
		Module current;
		for(int x = xStart - 3; x <= xStart + 3; x++) {
			for(int y = yStart - 3; y <= yStart + 3; x++) {
				current = config[x][y];
				if(x == xStart || y==yStart) {
					//do nothing this is the starting module
				} else {
					if (140 < current.getCode() && current.getCode()< 145) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean gymWithin1Plain(int xStart, int yStart) {
		Module current;
		for(int x = xStart - 1; x <= xStart + 1; x++) {
			for(int y = yStart - 1; y <= yStart + 1; x++) {
				current = config[x][y];
				if(x == xStart || y==yStart) {
					//do nothing this is the starting module
				} else {
					if (130 < current.getCode() && current.getCode()< 135) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean gymWithin2Plain(int xStart, int yStart) {
		Module current;
		for(int x = xStart - 2; x <= xStart + 2; x++) {
			for(int y = yStart - 2; y <= yStart + 2; x++) {
				current = config[x][y];
				if(x == xStart || y==yStart) {
					//do nothing this is the starting module
				} else {
					if (130 < current.getCode() && current.getCode()< 135) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	private boolean dormitoryWithin2Plain(int xStart, int yStart) {
		Module current;
		for(int x = xStart - 2; x <= xStart + 2; x++) {
			for(int y = yStart - 2; y <= yStart + 2; x++) {
				current = config[x][y];
				if(x == xStart || y==yStart) {
					//do nothing this is the starting module
				} else {
					if (60 < current.getCode() && current.getCode()< 81) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	private int spacesFromAirlock(int xStart, int yStart) {
		Module current;
		for(int y = 0; y < rows; y++) {
			for(int x = 0; x < columns; x++) {
				current = config[y][x];
				if(current != null) {
					if (170 < current.getCode() && current.getCode()< 175) {
						int differnceY = Math.abs(yStart- y);
						int differnceX = Math.abs(xStart-x);
						if(differnceX < differnceY) {
							return differnceY;
						} else {
							return differnceX;
						}
					}
				}
			}
		}		
		return 0;
	}

	private Module[][] config;
	private int rows;
	private int columns;
	private int heuristic;
}

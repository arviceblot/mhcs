package com.mhcs.joshua;

import java.util.LinkedList;

public class gridNodeList {

	//these match the up left right down arrows in MapNavigator.java
	public final static int NORTH = -1200;
	public final static int EAST = -1100;
	public final static int SOUTH = -1400;
	public final static int WEST = -1300;
	public final static int NO_VAL = -9999;
	
	
	private int rowY;
	private int colX;
	private gridNodeList parent;
	
	public gridNodeList(final int row, final int col) {
		this.rowY = row;
		this.colX = col;
		this.parent = null;
	}
	
	public gridNodeList() {
		this.rowY = NO_VAL;
		this.colX = NO_VAL;
		this.parent = null;
	}

	public gridNodeList(final int row,final int col,final gridNodeList forfather) {
		this.rowY = row;
		this.colX = col;
		this.parent = forfather;
	}
	
	/**
	 * calculates a path.
	 * @return a linked list with a integer array with 3 ints
	 * the array has 0 for rows, 1 for col and 2 for arrow direction
	 */
	public LinkedList<int[]> getPath(){
		
		LinkedList<int[]> path = new LinkedList<int[]>();
		gridNodeList list = this;
		int arrow = NO_VAL;
		int row = list.getRow(), col = list.getCol();
		while(list != null)
		{
			if (row > list.getRow()) {
				
				arrow = SOUTH;
				
			} else if (row < list.getRow()) {
				
				arrow = NORTH;
			}
			
			//look at columns next.
			if (col > list.getCol()) {
				
				arrow = EAST;
				
			} else if (col < list.getCol()) {
				
				arrow = WEST;
			}
			row = list.getRow(); 
			col = list.getCol();
			int[] location = {row, col, arrow} ;
			path.addFirst(location);
			list = list.getParent();
			
			//choose next arrow position.
			//look at row first
			
			
			
		}
		
		return path;
		
	}
	
	public int getRow() {
		return this.rowY;
	}
	
	public int getCol() {
		return this.colX;
	}
	
	public gridNodeList getParent() {
		return this.parent;
	}
	
}

package com.mhcs.joshua;

import java.util.LinkedList;
import java.util.Queue;

public class mapSearch {

	int[][] grid;
	int gridMaxY;
	int gridMaxX;
	public static final int PASSABLE = -100;
	public static final int IMPASSABLE = -999;
	public static final int GOALTILE = -386;

	/**
	 * Constructor for mapSearch, needs search max values for the array.
	 * 
	 * @param searchGrid
	 *            actual array to be passed in.
	 * @param maxRow
	 *            max first value of array.
	 * @param maxCol
	 *            max second value of array.
	 */
	mapSearch(final int[][] searchGrid, final int maxRow, final int maxCol) {

		grid = searchGrid;
		this.gridMaxY = maxRow;
		this.gridMaxX = maxCol;

	}

	public LinkedList<int[]> search(final int startRow, final int startCol) {
		gridNodeList root = new gridNodeList(startRow, startCol);
		gridNodeList end = new gridNodeList();
		gridNodeList current = new gridNodeList();
		boolean keepLooking = true;
		Queue<gridNodeList> toDo = new LinkedList<gridNodeList>();
		toDo.add(root);

		while ((!toDo.isEmpty()) && keepLooking) {

			int count = 0;
			current = toDo.remove();
			int row = current.getRow(), col = current.getCol();
			LinkedList<Integer> rows = new LinkedList<Integer>();
			LinkedList<Integer> cols = new LinkedList<Integer>();


				// LOOK UP
				if (keepLooking && inBounds(row + 1, col) && grid[row + 1][col] != IMPASSABLE) {
					count += 1;
					keepLooking = markGrid(row + 1, col); // mark it as looked at
					rows.addFirst(row + 1);
					cols.addFirst(col);
				}

				// look right
				if (keepLooking && inBounds(row, col + 1) && grid[row][col + 1] != IMPASSABLE) {
					count += 1;
					keepLooking = markGrid(row, col + 1); // mark it as looked at
					rows.addFirst(row);
					cols.addFirst(col + 1);
				}

				// LOOK DOWN
				if (keepLooking && inBounds(row - 1, col) && grid[row - 1][col] != IMPASSABLE) {
					count += 1;
					keepLooking = markGrid(row - 1, col); // mark it as looked at
					rows.addFirst(row - 1);
					cols.addFirst(col);
				}

				// look left
				if (keepLooking && inBounds(row, col - 1) && grid[row][col - 1] != IMPASSABLE) {
					count += 1;
					keepLooking = markGrid(row, col - 1); // mark it as looked at
					rows.addFirst(row);
					cols.addFirst(col - 1);
				}

				if (keepLooking) {
					gridNodeList[] newNodes = new gridNodeList[count];

					for (int inodes = 0; inodes < count; inodes++) {

						newNodes[inodes] = new gridNodeList(rows.remove(),
								cols.remove(), current);
						toDo.add(newNodes[inodes]);
					}
					
				} else {
					
					end = new gridNodeList(rows.removeLast(),
							cols.removeLast(), current);
				}

		}
		return end.getPath();
	}

	private boolean inBounds(final int row, final int col) {

		return (row < gridMaxY && row >= 0 && col < gridMaxX && col >= 0);

	}
	
	/**
	 * mark the grid if it is not a goal tile.
	 * @param row
	 * @param col
	 * @return returns false when it shouldn't look for the 
	 * goal anymore. 
	 */
	private boolean markGrid(final int row, final int col) {
		
		boolean keepLooking = false;
		
		if (grid[row][col] != GOALTILE) {
			
			grid[row][col] = IMPASSABLE;
			keepLooking = true;
			
		}
		
		return keepLooking;
		
	}

}

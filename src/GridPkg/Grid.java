package GridPkg;

import java.io.IOException;

import GUIPkg.GridSelectorOptionPane;

/** 
 * Description: Grid is a mapping of a 2D surface in a cartesian plane; composed of square cells
 * @authors	Anita Szilagyi, Bruno Boivin, Kaichen Wang, Salman Hashmi, Shahrzad Ti
 * @version	1.0
 * @since	2013-11-23	
 */

public class Grid {

	/**
	 * Number of Rows of grid (y values)
	 */
	private int rowCount;
	/**
	 * Number of Rows of grid (x values)
	 */
	private int colCount;
	/**
	 * Individual cells of the Grid
	 */
	private GridCell[][] gridCells;
	/**
	 * Default number of rows
	 */
	private final int ROW_COUNT_DEFAULT = 50;
	/**
	 * Default number of columns
	 */
	private final int COL_COUNT_DEFAULT = 75;

	public Grid () {
		
		this.rowCount = ROW_COUNT_DEFAULT;
		this.colCount = COL_COUNT_DEFAULT;
		this.gridCells = new GridCell[this.colCount][this.rowCount];

		initializeGrid ();
	}  
	
	/**
	 * Initializes a new grid in which each cell (GridCell) is Empty
	 */
	private void initializeGrid () {
		for (int i = 0; i < this.colCount; i++) {
		    for (int j = 0; j < this.rowCount; j++) {
		    	this.gridCells[i][j] = GridCell.Empty;
		    }
		}
	}	
	
	/**
	 * Calls GUI to prompt user to select a map (Grid)
	 * then update a Grid accordingly
	 * @param existing Grid
	 * @return updated Grid
	 */
	public static Grid promptMapSelection (Grid currentGrid) {
		
		GridSelectorOptionPane selectGrid = new GridSelectorOptionPane();
		
		if (selectGrid.getSelectedMapPath() != null){
			currentGrid.setGridCells(selectGrid.getSelectedGridCells());
		} 
		return currentGrid;
	}	
	
	/**
	 * Prepares an int array containing obstacle coordinates loaded from a map file
	 * @param path to map file
	 * @return int array containing obstacle coordinates
	 */
	private int [] loadObstacles (String mapPath) {
		
		int obstacleCoords [] = null;
		
		try {
			obstacleCoords = GridFileLoader.readFile(mapPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return obstacleCoords;
	}
	
	/**
	 * adds obstacles to the grid
	 * @param path to map file
	 */
	public void addObstacles (String selectedMapPath) {
		
		int [] obstacleCoords = loadObstacles(selectedMapPath);
		int [] obstacleCoordsPair = new int [4];
		int xCoord1,xCoord2,yCoord1,yCoord2;
		int xStart, xEnd, yStart, yEnd;

		for (int i = 0; i < obstacleCoords.length; i = i + 4) {
			
			for (int j = 0; j < 4; j++) {
				obstacleCoordsPair [j] = obstacleCoords [i + j];
			}
			
			//Note: for the user, origin (0,0) is the lower-left corner of map
			xCoord1 = obstacleCoordsPair[0];
			xCoord2 = obstacleCoordsPair[2];
			yCoord1 = this.rowCount - obstacleCoordsPair[1];
			yCoord2 = this.rowCount - obstacleCoordsPair[3];
			
			if(xCoord1 < 0){
				xCoord1 = 0;
			}
			if(xCoord2 < 0){
				xCoord2 = 0;
			}
			if(yCoord1 < 0){
				yCoord1 = 0;
			}
			if(yCoord2 < 0){
				yCoord2 = 0;
			}
			if(xCoord1 > this.colCount){
				xCoord1 = this.colCount;
			}
			if(xCoord2 > this.colCount){
				xCoord2 = this.colCount;
			}
			if(yCoord1 > this.rowCount){
				yCoord1 = this.rowCount;
			}
			if(yCoord2 > this.rowCount){
				yCoord2 = this.rowCount;
			}
				
			if (xCoord1 <= xCoord2){
				xStart = xCoord1;
				xEnd = xCoord2;
			}
			else {
				xStart = xCoord2;
				xEnd = xCoord1;
			}
			
			if (yCoord1 <= yCoord2){
				yStart = yCoord1;
				yEnd = yCoord2;
			}
			else {
				yStart = yCoord2;
				yEnd = yCoord1;
			}
			
			for (int x = xStart; x < xEnd; x++) {
			    for (int y = yStart; y < yEnd; y++) {
			    	this.gridCells[x][y] = GridCell.Obstacle;
			    }
			}	
		}	
	}
	
	/**
	 * Resets an existing grid to its initial state; 
	 * with each cell either being empty or an obstacle
	 * @param cells of an existing Grid
	 */
	public static void resetGrid (GridCell[][] gridCells) {
		for(int i = 0; i < gridCells.length; i++) {
			for(int j=0; j< gridCells[i].length; j++) {
				if(gridCells[i][j] != GridCell.Obstacle){
					gridCells[i][j] = GridCell.Empty;
				}
			}
		}
	}
	
	//getters
	/**
	 * @return value of Grid's cells in 2D array
	 */
	public GridCell[][] getGridCells (){
		return this.gridCells;
	}
	
	/**
	 * @return number of rows in Grid
	 */
	public int getGridRow (){
		return this.rowCount;
	}
	
	/**
	 * @return number of columns in Grid
	 */
	public int getGridCol (){
		return this.colCount;
	}
	
	//setters
	/**
	 * @param sets Grid's cells with parameter
	 */
	public void setGridCells (GridCell[][] gridCells){
		this.gridCells = gridCells;
	}

}
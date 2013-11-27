package GridPkg;

import java.io.IOException;

import GUIPkg.GridSelectorOptionPane;

//import java.util.Vector;

public class Grid {

	private int rowCount;
	private int colCount;
	private GridCell[][] gridCells;
	
	private final int ROW_COUNT_DEFAULT = 50;
	private final int COL_COUNT_DEFAULT = 75;

	public Grid () {
		
		this.rowCount = ROW_COUNT_DEFAULT;
		this.colCount = COL_COUNT_DEFAULT;
		this.gridCells = new GridCell[this.colCount][this.rowCount];

		initializeGrid ();
	}  
	
	//Initializes a new grid in which each cell of the grid is empty or an obstacle
	private void initializeGrid () {
		
		//First set all gridCells as empty
		for (int i = 0; i < this.colCount; i++) {
		    for (int j = 0; j < this.rowCount; j++) {
		    	this.gridCells[i][j] = GridCell.Empty;
		    }
		}
	}	
	
	
	public static Grid promptMapSelection (Grid currentGrid) {
			
		//Prompt user to select a map
		GridSelectorOptionPane selectGrid = new GridSelectorOptionPane();
		
//		//Add obstacles corresponding to the selected map
//		addObstacles(selectGrid.getSelectedMapPath());
		
		if (selectGrid.getSelectedMapPath() != null){
			currentGrid.setGridCells(selectGrid.getSelectedGridCells());
		} 
		return currentGrid;
	}	
	
	
	//ONLY FOR TESTING PURPOSES: adds obstacles at select coordinates
	private void initializeGridTest (){
		for (int i = 0; i < this.colCount; i++) {
		    for (int j = 0; j < this.rowCount; j++) {
		    	this.gridCells[i][j] = GridCell.Empty;
		    }
		}
		this.gridCells[5][5] = GridCell.Obstacle;
		this.gridCells[10][25] = GridCell.Obstacle;
	}
	
	//prepares an int array containing obstacle coordinates loaded from a map file
	private int [] loadObstacles (String mapPath) {
		
		int obstacleCoords [] = null;
		
		try {
			obstacleCoords = GridFileLoader.readFile(mapPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return obstacleCoords;
	}
	
	//adds obstacles to the grid
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
	
	//Resets an existing grid to its initial state; with each cell either being empty or an obstacle
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
	public GridCell[][] getGridCells (){
		return this.gridCells;
	}
	
	public int getGridRow (){
		return this.rowCount;
	}
	
	public int getGridCol (){
		return this.colCount;
	}
	
	//setters
	public void setGridCells (GridCell[][] gridCells){
		this.gridCells = gridCells;
	}

}
package GridPkg;

import java.io.File;

//import java.util.Vector;

public class Grid {

	private int rowCount;
	private int colCount;
	private GridCell[][] gridCells;
	

	public Grid () {
	  
		/* For now, the grid's properties are hard-coded. custom grids will be implemented later. */
	  
		this.rowCount = 50;
		this.colCount = 75;
		this.gridCells = new GridCell[rowCount][colCount];
	
	}  
  
	public void clearBoard ( GridCell[][] gridCells ) {
		
		GridCell[][] currentGridCells = gridCells;
		
		for(int i = 0; i < currentGridCells.length; i++) {
			
			for(int j=0; j< currentGridCells[i].length; j++) {
				currentGridCells[i][j] = GridCell.Empty;
			}
		}
	}
	
	public GridCell[][] getgGridCells (){
		return this.gridCells;
	}
	
	public int getGridRow (){
		return this.rowCount;
	}
	
	public int getGridCol (){
		return this.colCount;
	}
 

}
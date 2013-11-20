package GridPkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import GUIPkg.ChooseMapsDisplay;

//import java.util.Vector;

public class Grid {

	private int rowCount;
	private int colCount;
	private GridCell[][] gridCells;
	
	private boolean isPreviewEnabled = false;
	
	public Grid () {
		/* For now, the grid's properties are hard-coded. custom grids will be implemented later. */
	  
		this.rowCount = 50;
		this.colCount = 75;
		this.gridCells = new GridCell[this.colCount][this.rowCount];
		

		initializeGrid ();
		
		previewGrid ();
	}  
	
	//Initializes a new grid in which each cell of the grid is empty or an obstacle
	private void initializeGrid () {
	
//		ChooseMapsDisplay selectGrid = new ChooseMapsDisplay();
//		selectGrid.setVisible(true);
//		
//		while (!selectGrid.getIsGridChosen()){
////			System.out.println (selectGrid.getGridChosen());
//		}
//		
//		System.out.println (selectGrid.getGridChosen());
//		
//		
//		String mapPath = "maps/" + selectGrid.getGridChosen() + ".txt"; 
//		
//		loadGridFromFile(mapPath);
		
		
		for (int i = 0; i < this.colCount; i++) {
		    for (int j = 0; j < this.rowCount; j++) {
		    	this.gridCells[i][j] = GridCell.Empty;
		    }
		}
	}	
	
	private void initializeGridTest (){
		for (int i = 0; i < this.colCount; i++) {
		    for (int j = 0; j < this.rowCount; j++) {
		    	this.gridCells[i][j] = GridCell.Empty;
		    }
		}
		
		this.gridCells[5][5] = GridCell.Obstacle;
		this.gridCells[6][6] = GridCell.Obstacle;
	}
	
	
	private void loadGridFromFile (String mapSelected) {
		
		String obstacleCoords = null;
		
		try {
			obstacleCoords = GridFileLoader.readFile(mapSelected);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println ("hello" + obstacleCoords);
		
		
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
	
	public GridCell[][] getGridCells (){
		return this.gridCells;
	}
	
	public int getGridRow (){
		return this.rowCount;
	}
	
	public int getGridCol (){
		return this.colCount;
	}
 
	public void setPreviewVisible ( boolean isPreviewEnabled ){
		this.isPreviewEnabled = isPreviewEnabled;	
	}
	
	
	private void previewGrid (){
		if  (isPreviewEnabled) {
			//Asks user whether a preview of the grid should be displayed
			if (JOptionPane.showConfirmDialog
			(null, "Show Grid Preview?", "Grid", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
		    == JOptionPane.YES_OPTION) 
			{
				//If YES, display preview of grid in a separate window (JFrame)
				JFrame gridPreview = new JFrame();
				gridPreview.setTitle ("Current Grid Preview");
				gridPreview.setSize (500,550);
				gridPreview.setResizable (false);
				gridPreview.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				
				JTextArea textArea = new JTextArea ("");
				textArea.setFont(new Font("Lucida Grande", Font.BOLD, 5));
				gridPreview.add(textArea);
				
				for (int i = 0; i < this.colCount; i++) {
				    for (int j = 0; j < this.rowCount; j++) {
				    	GridCell gridCell = this.gridCells [i][j];
				    	if (gridCell == GridCell.Empty){
				    		textArea.append("  " + ".." + "  ");
				    	}
				    	else if (gridCell == GridCell.Obstacle){
				    		textArea.append("  " + "0" + "  ");
				    	}
				    }
					textArea.append("\n");
				}
				
				gridPreview.setVisible(true);
			}
		}
	}
	

}
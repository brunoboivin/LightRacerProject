package GridPkg;

import java.io.File;

import javax.swing.*;

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
				gridPreview.setSize (300,300);
				gridPreview.setResizable (true);
				gridPreview.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				
				JTextArea textArea = new JTextArea ("");
				gridPreview.add(textArea);
				
				for (int i = 0; i < this.colCount; i++) {
				    for (int j = 0; j < this.rowCount; j++) {
				    	GridCell gridCell = this.gridCells [i][j];
				    	if (gridCell == GridCell.Empty){
				    		textArea.append("." + " ");
				    	}
				    	else if (gridCell == GridCell.Obstacle){
				    		textArea.append("0" + " ");
				    	}
				    }
					textArea.append("\n");
				}
				
				gridPreview.setVisible(true);
			}
		}
	}
	
	private void initializeGrid (){
		for (int i = 0; i < this.colCount; i++) {
		    for (int j = 0; j < this.rowCount; j++) {
		    	this.gridCells[i][j] = GridCell.Empty;
		    }
		}
	}	
}
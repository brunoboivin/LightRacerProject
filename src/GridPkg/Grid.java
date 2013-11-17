package GridPkg;

import java.io.File;

import javax.swing.*;

//import java.util.Vector;

public class Grid {

	private int rowCount;
	private int colCount;
	private GridCell[][] gridCells;
	
	private boolean isPreviewVisible = true;
	
	public Grid () {
		/* For now, the grid's properties are hard-coded. custom grids will be implemented later. */
	  
		this.rowCount = 50;
		this.colCount = 75;
		this.gridCells = new GridCell[this.colCount][this.rowCount];
		
		initializeGrid ();
//		previewGrid ();
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
 
	public void setPreviewVisible ( boolean isPreviewVisible ){
		this.isPreviewVisible = isPreviewVisible;	
	}
	
	private void previewGrid (){
		
		//Asks user whether a preview of the grid should be displayed
		if (JOptionPane.showConfirmDialog
		(null, "Show Grid Preview?", "Grid", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
	    == JOptionPane.YES_OPTION) 
		{
			//If YES, display preview of grid in a separate window (JFrame)
			JFrame gridPreview = new JFrame();
			gridPreview.setTitle ("Current Grid Preview");
			gridPreview.setSize (200,200);
			gridPreview.setResizable (false);
			gridPreview.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			
			
			JTextArea textArea = new JTextArea ("Test");
			gridPreview.add(textArea);
			gridPreview.setVisible(true);
			
//			for (int i = 0; i < this.colCount; i++) {
//			    for (int j = 0; j < this.rowCount; j++) {
//			    	GridCell gridCell = this.gridCells [i][j];
//			    	textArea.setText(gridCell + " ");
//			    }
//				textArea.setText("\n");
//			}
			
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
package GridPkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


/** 
 * Description: Paints a preview of a select Grid (and its cells) to the screen
 * @authors	Anita Szilagyi, Bruno Boivin, Kaichen Wang, Salman Hashmi, Shahrzad Ti
 * @version	1.0
 * @since	2013-11-23	
 */

public class PreviewGrid extends JPanel {

	/**
	 * cells of a Grid
	 */
	private GridCell[][] mapGrid;
	/**
	 * desired pixel size of a cell
	 */
	private int cellSize = 5;
	/**
	 * number of columns of Grid
	 */
	private int colNum = 75;
	/**
	 * number of rows of Grid
	 */
	private int rowNum = 50;

	public PreviewGrid (GridCell[][] map) {
		this.mapGrid=map;
		setLayout(null);
		setPreferredSize(new Dimension(colNum*cellSize, rowNum*cellSize));
		setBackground(Color.BLACK);	
	}
	
	@Override
	/**
	 * Draws cells to screen with colors
	 * corresponding to whether cell is obstacle of empty
	 */
	public void paintComponent(Graphics cell) {
		super.paintComponent(cell);
		for(int x = 0; x < colNum; x++) {
			for(int y = 0; y < rowNum; y++) {
				if(mapGrid[x][y] == GridCell.Obstacle) {
					cell.setColor(Color.YELLOW);
					cell.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
				}
			}
		}
		cell.setColor(Color.DARK_GRAY);
		cell.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		for(int x = 0; x < colNum; x++) {
			for(int y = 0; y < rowNum; y++) {
				cell.drawLine(x * cellSize, 0, x * cellSize, getHeight());
				cell.drawLine(0, y * cellSize, getWidth(), y * cellSize);
			}
		}	
	}
}

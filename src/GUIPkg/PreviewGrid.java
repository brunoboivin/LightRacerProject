package GUIPkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import GridPkg.GridCell;



public class PreviewGrid extends JPanel {

	private GridCell[][] mapGrid;
	private int cellSize= 5;
	private int colNum=75;
	private int rowNum=50;
	/**
	 * Create the panel.
	 */
	public PreviewGrid(GridCell[][] map) 
	{
		this.mapGrid=map;
		setLayout(null);
		
		setPreferredSize(new Dimension(colNum*cellSize, rowNum*cellSize));
		setBackground(Color.BLACK);
		
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		
		super.paintComponent(g);
		
		
		//grid
		for(int x = 0; x < colNum; x++) 
		{
			for(int y = 0; y < rowNum; y++) {
			//	TileType type = getTile(x, y);
				if(mapGrid[x][y] == GridCell.Obstacle) {
					//drawTile(x * cellSize, y * cellSize, type, g);
					g.setColor(Color.YELLOW);
					g.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
				}
			}
		}
		
		
		
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		for(int x = 0; x < colNum; x++) 
		{
			for(int y = 0; y < rowNum; y++) 
			{
				g.drawLine(x * cellSize, 0, x * cellSize, getHeight());
				g.drawLine(0, y * cellSize, getWidth(), y * cellSize);
			}
		}	
		
	}

}

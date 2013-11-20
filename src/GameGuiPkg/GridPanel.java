package GameGuiPkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePkg.TronGame;
import GridPkg.*;


public class GridPanel extends JPanel 
{

	/**
	 * @param args
	 */

	private int ROW_COUNT;
	private int COL_COUNT;
	/**
	 * The size of each tile in pixels.
	 */
	public static final int TILE_SIZE = 13;
	private static final Font FONT = new Font("STARWARS", Font.BOLD, 25);
	/**
	 * The array of cells that make up this board.
	 */
	private GridCell[][] cells;
	
	private static final Color TEXT_COLOR=new Color(25, 25, 112);
	
	private TronGame game;
	/**
	 * Creates a new GridPanel instance.
	 * @param game The TronGame instance.
	 */
	//private GameStatus status;
	
	public GridPanel(TronGame game) 
	{
		this.game = game;
		Grid grid = new Grid();
		this.cells = grid.getGridCells();
		this.COL_COUNT = grid.getGridCol();
		this.ROW_COUNT = grid.getGridRow();
		
		setPreferredSize(new Dimension(COL_COUNT * TILE_SIZE, ROW_COUNT * TILE_SIZE));
		setBackground(Color.WHITE);
	}
	
	/**
	 * Clears all of the cells on the board and sets their values to null. this has to be done by the Grid Class
	 */
	public void clearBoard() {
		Grid.resetGrid(this.cells);
	}
	
	/**
	 * Sets the tile at the desired coordinate.
	 * @param point The coordinate of the tile.
	 * @param type The type to set the tile to.
	 */
	public void setCell(Point point, GridCell type)
	{
		setCell(point.x, point.y, type);
	}
	
	
	/**
	 * Sets the cell at the desired coordinate.
	 * @param x The x coordinate of the tile.
	 * @param y The y coordinate of the tile.
	 * @param type The type to set the tile to.
	 */
	public void setCell(int x, int y, GridCell type) 
	{
		cells[x][y] = type;
	}
	
	
	
	/**
	 * Gets the cell at the desired coordinate.
	 * @param x The x coordinate of the tile.
	 * @param y The y coordinate of the tile.
	 * @return
	 */
	public GridCell getCell(int x, int y) 
	{
		return cells[x][y];
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		/*
		 * Loop through each cell on the board and draw it if it
		 * is not null.
		 */
		for(int x = 0; x < COL_COUNT; x++)
		{
			for(int y = 0; y < ROW_COUNT; y++) 
			{
				GridCell type = getCell(x, y);
				if(type != null) 
				{
					drawCell(x * TILE_SIZE, y * TILE_SIZE, type, g);
				}
			}
		}
		/*
		 * Draw the grid on the board. This makes it easier to see exactly
		 * where we in relation to the fruit.
		 * 
		 * The panel is one pixel too small to draw the bottom and right
		 * outlines, so we outline the board with a rectangle separately.
		 */
		/*g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		for(int x = 0; x < COL_COUNT; x++) 
		{
			for(int y = 0; y < ROW_COUNT; y++) 
			{
				g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, getHeight());
				g.drawLine(0, y * TILE_SIZE, getWidth(), y * TILE_SIZE);
			
			}
		}*/
		/*
		 * Show a message on the screen based on the current game state.
		 */
		if( (game.status.isGameOver()) || (game.status.isNewGame()) || (game.status.isPaused()) || (game.status.isRoundOver())) 
		{
			g.setColor(TEXT_COLOR);
			
			/*
			 * Get the center coordinates of the board.
			 */
			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;
			
			/*
			 * Allocate the messages for and set their values based on the game
			 * state.
			 */
			String largeMessage = null;
			String smallMessage = null;
			String gameMessage=null;
			if(game.status.isNewGame()) 
			{
				largeMessage = "Tron Game!";
				smallMessage = "Press Enter to Start";
			} 
			else if (game.status.isRoundOver())
			{
				if (game.roundWinner()=="")
					gameMessage= "Tie";
				else
					gameMessage=game.roundWinner()+" Won this round";
				largeMessage = "Round"+game.status.getRoundNumber()+" is over";
				smallMessage = "Press Enter to start the next round";
				
			}
			else if(game.status.isGameOver()) 
			{
				gameMessage=game.winnerIs()+" Won :) *****"+game.loserIs()+" Lost :(";
				largeMessage = "Game Over!";
				smallMessage ="Press SPACE to play Again or ESC to go back to the Main Menu";
			} 
			else if(game.status.isPaused()) 
			{
				largeMessage = "Paused";
				smallMessage = "Press P to Resume";
			}
			
			/*
			 * Set the message font and draw the messages in the center of the board.
			 */
			g.setFont(FONT);
			if (gameMessage != null)
			{	
				g.drawString(gameMessage, centerX - g.getFontMetrics().stringWidth(gameMessage) / 2, centerY-50 );
			}
			g.drawString(largeMessage, centerX - g.getFontMetrics().stringWidth(largeMessage) / 2, centerY);
			g.drawString(smallMessage, centerX - g.getFontMetrics().stringWidth(smallMessage) / 2, centerY + 50);
		}
	}
	
	
	private void drawCell(int x, int y, GridCell type, Graphics g) 
	{
		switch(type) 
		{
		case Empty:
			break;
		
		case Obstacle:
			g.setColor(Color.BLACK);
			g.fillOval(x + 2, y + 2, TILE_SIZE - 4, TILE_SIZE - 4);
			break;	
		/*
		 * The Racer1 body is depicted as a blue square that takes up the
		 * entire tile.
		 */
		case YodaIcon:
			ImageIcon yoda = new ImageIcon(SidePanel.class.getResource("/GameGuiPkg/yodaRacer.gif"));
			Image yodaImg=yoda.getImage();
			g.drawImage(yodaImg, x-10, y-10, TILE_SIZE+15, TILE_SIZE+15, null);
			break;
		
		case DarthVaderIcon :
			ImageIcon darthVader = new ImageIcon(SidePanel.class.getResource("/GameGuiPkg/darthVaderRacer.gif"));
			Image darthVaderImg=darthVader.getImage();
			g.drawImage(darthVaderImg, x-10, y-10, TILE_SIZE+15, TILE_SIZE+15, null);
			break;

		case GreenLight:
			//Fill the tile in with green.
			g.setColor(new Color(144, 238, 144));
			g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			break;
		
		case RedLight:
			//Fill the tile in with red.
			g.setColor(new Color(255, 99, 71));
			g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			break;
		
			
			
		
		}
	}
	
	public int getGridPanelRow(){
		return this.ROW_COUNT;
	}
	
	public int getGridPanelCol (){
		return this.COL_COUNT;
	}
	

}




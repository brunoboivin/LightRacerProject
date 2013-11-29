

package GameGUIPkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GamePkg.Game;
import GridPkg.*;

/** Class used to display the game and its components.
 * @author Shahrzad Tighnavardmollasaraei <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */

public class GridPanel extends JPanel 
{
	
	/**
	 * The number of rows.
	 */
	private int ROW_COUNT;
	/**
	 * The number of columns.
	 */
	private int COL_COUNT;
	/**
	 * The size of each tile in pixels.
	 */
	public static final int CELL_SIZE = 13;
	/**
	 * The font used to display messages.
	 */
	private static final Font FONT = new Font("STARWARS", Font.BOLD, 25);
	/**
	 * The array of cells that make up this board.
	 */
	private GridCell[][] cells;
	
	/**
	 * The Game instance.
	 */
	private Game game;
	/**
	 * The Grid instance.
	 */
	private Grid gameGrid;
	
	/**
	 * Creates a new GridPanel instance.
	 * @param game The TronGame instance.
	 */
	public GridPanel(Game game, Grid grid) 
	{
		this.game = game;
		this.gameGrid = grid;
		this.initGrid(this.gameGrid);
		
		
		setPreferredSize(new Dimension(COL_COUNT * CELL_SIZE, ROW_COUNT * CELL_SIZE));
		setBackground(Color.WHITE);
	}
	/**
	 * Returns the height of GridPanel.
	 * @return Height 
	 */
	public int heightSize()
	{
		return (ROW_COUNT* CELL_SIZE);
	}
	/**
	 * Clears all of the cells on the board and sets their values to null. this has to be done by the Grid Class
	 */
	public void clearBoard() 
	{
		Grid.resetGrid(this.cells);
	}
	private void initGrid(Grid newGrid)
	{
		this.gameGrid=newGrid;
		this.cells = newGrid.getGridCells();
		this.COL_COUNT = newGrid.getGridCol();
		this.ROW_COUNT = newGrid.getGridRow();
	}
	/**
	 * Changes the map between rounds.
	 */
	public void changeGrid()
	{
		Grid newGrid = Grid.promptMapSelection(gameGrid);
		this.initGrid(newGrid);	
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
	/**
	 * Paints game board/panel (cells and the messages).
	 */
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
					drawCell(x * CELL_SIZE, y * CELL_SIZE, type, g);
				}
			}
		}
		
		/*
		 * Show a message on the screen based on the current game state.
		 */
		if( (game.status.isGameOver()) || (game.status.isNewGame()) || (game.status.isPaused()) || (game.status.isRoundOver())) 
		{
			
			
			/*
			 * Get the center coordinates of the board.
			 */
			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;
		
			
			g.setColor(Color.WHITE);
			
			g.fillRect(200, centerY-200, 575, 325);
			/*
			 * Allocate the messages for and set their values based on the game
			 * state.
			 */
			g.setColor(Color.BLACK);
			g.drawRect(200, centerY-200, 575, 325);
			String headerMsg ="";
			String gameMsg ="";
			String roundMsg	="Round ";
			String spaceMsg ="SPACE : ";
			String enterMsg ="ENTER : Change the Map";
			String escMsg ="";
			
			if(game.status.isNewGame()) 
			{
				headerMsg	="Welcome!";
				spaceMsg=spaceMsg+"Start the game";
				roundMsg=roundMsg+game.status.getRoundNumber();
				escMsg ="ESC : Go back to the Main Menu ";
			} 
			else if (game.status.isRoundOver())
			{
				if (game.roundWinner()=="")
					gameMsg= "It's a Tie";
				else
					gameMsg=game.roundWinner()+" won this round";
				roundMsg =roundMsg+game.status.getRoundNumber()+" is over";
				spaceMsg=spaceMsg+"Start the next Round";
				
			}
			else if(game.status.isGameOver()) 
			{
				gameMsg=game.winnerIs()+ " is the WINNER !";
				roundMsg = "Game Over!";
				spaceMsg=spaceMsg+"Play again";
				escMsg ="ESC : Go back to the Main Menu ";
			} 
			else if(game.status.isPaused()) 
			{
				headerMsg = "Paused";
				spaceMsg="";
				enterMsg="";
				roundMsg= "P : Resume";
			}
			
			/*
			 * Set the message font and draw the messages in the center of the board.
			 */
			g.setFont(FONT);
			g.drawString(headerMsg, centerX - g.getFontMetrics().stringWidth(headerMsg) / 2, centerY-150 );
			g.drawString(gameMsg, centerX - g.getFontMetrics().stringWidth(gameMsg) / 2, centerY-100 );
			g.drawString(roundMsg, centerX - g.getFontMetrics().stringWidth(roundMsg) / 2, centerY-50 );
			g.drawString(enterMsg, centerX - g.getFontMetrics().stringWidth(enterMsg) / 2, centerY );
			g.drawString(spaceMsg, centerX - g.getFontMetrics().stringWidth(spaceMsg) / 2, centerY+50);
			g.drawString(escMsg, centerX - g.getFontMetrics().stringWidth(escMsg) / 2, centerY+100 );
		}
	}
	/**
	 * Draws the GridCell.
	 * @param x The horizontal coordinate of the cell
	 * @param y	The vertical coordinate of the cell
	 * @param type The type of the GridCell
	 * @param g Graphics
	 */
	
	private void drawCell(int x, int y, GridCell type, Graphics g) 
	{
		switch(type) 
		{
		case Empty:
			break;
		
		case Obstacle:
			g.setColor(Color.GRAY);
			g.fillRect(x + 2, y + 2, CELL_SIZE - 1, CELL_SIZE - 1);
			break;	
		/*
		 * The Racer1 body is depicted as a blue square that takes up the
		 * entire tile.
		 */
		case YodaIcon:
			ImageIcon yoda = new ImageIcon("res/img/yodaRacer.gif");
			Image yodaImg=yoda.getImage();
			g.drawImage(yodaImg, x-10, y-10, CELL_SIZE+15, CELL_SIZE+15, null);
			break;
		
		case DarthVaderIcon :
			ImageIcon darthVader = new ImageIcon("res/img/darthVaderRacer.gif");
			Image darthVaderImg=darthVader.getImage();
			g.drawImage(darthVaderImg, x-10, y-10, CELL_SIZE+15, CELL_SIZE+15, null);
			break;

		case GreenLight:
			//Fill the tile in with green.
			g.setColor(new Color(144, 238, 144));
			g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			break;
		
		case RedLight:
			//Fill the tile in with red.
			g.setColor(new Color(255, 99, 71));
			g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			break;
		
			
			
		
		}
	}
	/**
	 * 
	 * @return The number of rows in the GridPanel.
	 */
	public int getGridPanelRow(){
		return this.ROW_COUNT;
	}
	
	/**
	 * 
	 * @return The number of columns in the GridPanel.
	 */
	public int getGridPanelCol (){
		return this.COL_COUNT;
	}
	

}




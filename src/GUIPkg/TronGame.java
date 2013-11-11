package GUIPkg;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.JFrame;

import RacerPkg.*;
import GridPkg.*;




public class TronGame extends JFrame
{
	
	
	/**
	 * The number of milliseconds that should pass between each frame.
	 */
	private static final long FRAME_TIME = 1000L / 50L;
		
	/**
	 * The maximum number of directions that we can have polled in the
	 * direction list.
	 */
	private static final int MAX_DIRECTIONS = 3;
	
	/**
	 * The GridPanel instance.
	 */
	private GridPanel board;
	
	
	/**
	 * The SidePanel instance.
	 */
	//private SidePanel side;
	/**
	 * The Clock instance for handling the game logic.
	 */
	private Clock logicTimer;
	
	/**
	 * Whether or not we're running a new game.
	 */
	private boolean isNewGame;
	
	/**
	 * Whether or not the game is over.
	 */
	private boolean isGameOver;
	
	/**	
	 * Whether or not the game is paused.
	 */
	private boolean isPaused;
	
	
	/**
	 * The list that contains the points for the player1.
	 */
	private LinkedList<Point> player1;
	
	/**
	 * The list that contains the points for the player2.
	 */
	private LinkedList<Point> player2;
	/**
	 * The lists that contains the queued directions.
	 */
	private LinkedList<Direction> directionsP1;
	private LinkedList<Direction> directionsP2;
	
	private TronGame() 
	{
		super("Tron Prototype1");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		/*
		 * Initialize the game's panels and add them to the window.
		 */
		this.board = new GridPanel(this);
		
		
		//this.side = new SidePanel(this);
		
		add(board, BorderLayout.CENTER);
		
		//add(side, BorderLayout.EAST);
		
		/*
		 * Adds a new key listener to the frame to process input. 
		 */
		addKeyListener(new KeyAdapter() 
		{
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				switch(e.getKeyCode()) 
				{

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to North before adding the
				 * direction to the list.
				 */
				//case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					if(!isPaused && !isGameOver) 
					{
						if(directionsP1.size() < MAX_DIRECTIONS) 
						{
							Direction last = directionsP1.peekLast();
							if(last != Direction.South && last != Direction.North) 
							{
								directionsP1.addLast(Direction.North);
							}
						}
					}
					break;
					/*
					 * If the game is not paused, and the game is not over...
					 * 
					 * Ensure that the direction list is not full, and that the most
					 * recent direction is adjacent to South before adding the
					 * direction to the list.
					 */	
					//case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						if(!isPaused && !isGameOver) 
						{
							if(directionsP1.size() < MAX_DIRECTIONS)
							{
								Direction last = directionsP1.peekLast();
								if(last != Direction.North && last != Direction.South) 
								{
									directionsP1.addLast(Direction.South);
								}
							}
						}
						break;
					
					/*
					 * If the game is not paused, and the game is not over...
					 * 
					 * Ensure that the direction list is not full, and that the most
					 * recent direction is adjacent to West before adding the
					 * direction to the list.
					 */						
					//case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						if(!isPaused && !isGameOver)
						{
							if(directionsP1.size() < MAX_DIRECTIONS) 
							{
								Direction last = directionsP1.peekLast();
								if(last != Direction.East && last != Direction.West) 
								{
									directionsP1.addLast(Direction.West);
								}
							}
						}
						break;
				
					/*
					 * If the game is not paused, and the game is not over...
					 * 
					 * Ensure that the direction list is not full, and that the most
					 * recent direction is adjacent to East before adding the
					 * direction to the list.
					 */		
					//case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if(!isPaused && !isGameOver) 
						{
							if(directionsP1.size() < MAX_DIRECTIONS) 
							{
								Direction last = directionsP1.peekLast();
								if(last != Direction.West && last != Direction.East) 
								{
									directionsP1.addLast(Direction.East);
								}
							}
						}
						break;
					
					/*
					 * If the game is not over, toggle the paused flag and update
					 * the logicTimer's pause flag accordingly.
					 */
					case KeyEvent.VK_P:
						if(!isGameOver) 
						{
							isPaused = !isPaused;
							logicTimer.setPaused(isPaused);
						}
						break;
					
					/*
					 * Reset the game if one is not currently in progress.
					 */
					case KeyEvent.VK_ENTER:
						if(isNewGame || isGameOver)
						{
							resetGame();
						}
						break;
					}
				}
				
			});
	

		/*
		 * Resize the window to the appropriate size, center it on the
		 * screen and display it.
		 */
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

	/**
	 * Updates the game's logic.
	 */
	private void updateGame() 
	{
		/*
		 * Gets the type of cell that the head of the racer collided with. If 
		 * the racer hit a wall, RacerBody will be returned, as both conditions
		 * are handled identically.
		 */
		GridCell collision = updatePlayer();
		
		/*
		 * Here we handle the different possible collisions.
		 * 
		 */
		
		if(collision == GridCell.RacerABody || collision == GridCell.Obstacle )
		{
			isGameOver = true;
			logicTimer.setPaused(true);
		} 
	}
	
	

	/**
	 * Updates the Racer's position.
	 * @return GridCell cell that the head moved into.
	 */
	private GridCell updatePlayer() 
	{

		/*
		 * Here we peek at the next direction rather than polling it. 
		 */
		Direction currentDirection = directionsP1.peekFirst();
				
		/*
		 * Here we calculate the new point that the racerA's head will be at
		 * after the update.
		 */		
		Point head = new Point(player1.peekFirst());
		switch(currentDirection) 
		{
		case North:
			head.y--;
			break;
			
		case South:
			head.y++;
			break;
			
		case West:
			head.x--;
			break;
			
		case East:
			head.x++;
			break;
		}
		
		/*
		 * If the racer has moved out of bounds ('hit' a wall), we can just
		 * return that it's collided with itself, as both cases are handled
		 * identically.
		 */
		if(head.x < 0 || head.x >= GridPanel.COL_COUNT || head.y < 0 || head.y >= GridPanel.ROW_COUNT) 
		{
			return GridCell.RacerABody; //Pretend we collided with our body.
		}
		
		/*
		 * Here we get the cell that was located at the new head position
		 */
		
		GridCell old = board.getCell(head.x, head.y);
	
		
		
		/*
		 * Update the racers's position on the board if it didn't have a collision:
		 * 
		 * 1. Set the old head position to a body cell.
		 * 2. Add the new head to the cell.
		 * 3. Set the new head position to a head cell.
		 * 
		 * If more than one direction is in the queue, poll it to read new
		 * input.
		 */
		if(old != GridCell.RacerABody && old != GridCell.Obstacle) 
		{
			board.setCell(player1.peekFirst(), GridCell.RacerABody);
			player1.push(head);
			board.setCell(head, GridCell.RacerAHead);
			if(directionsP1.size() > 1) {
				directionsP1.poll();
			}
		}
				
		return old;
	}
	
	
	/**
	 * Starts the game running.
	 */
	private void startGame() 
	{
		/*
		 * Initialize everything we're going to be using.
		 */
		//this.random = new Random();
		this.player1 = new LinkedList<>();
		this.directionsP1 = new LinkedList<>();
		/*
		 * clock speed enables faster or slower updates to the game. 
		 * the higher the time the faster the racer.
		 */
		this.logicTimer = new Clock(30.0f);
		this.isNewGame = true;
		
		//Set the timer to paused initially.
		logicTimer.setPaused(true);

		/*
		 * This is the game loop. It will update and render the game and will
		 * continue to run until the game window is closed.
		 */
		while(true) 
		{
			//Get the current frame's start time.
			long start = System.nanoTime();
			
			//Update the logic timer.
			logicTimer.updateClock();
			
			/*
			 * If a cycle has elapsed on the logic timer, then update the game.
			 */
			if(logicTimer.hasElapsedCycle()) {
				updateGame();
			}
			
			//Repaint the board and side panel with the new content.
			board.repaint();
			//side.repaint();
			
			/*
			 * Calculate the delta time between since the start of the frame
			 * and sleep for the excess time to cap the frame rate. While not
			 * incredibly accurate, it is sufficient for our purposes.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void resetGame() 
	{
		/*
		 * Reset the score statistics. 
		 */
		//this.score = 0;
				
		/*
		 * Reset both the new game and game over flags.
		 */
		this.isNewGame = false;
		this.isGameOver = false;
		
		/*
		 * Create the head at the center of the board.
		 */
		Point head = new Point(54, 54);

		/*
		 * Clear the racer list and add the head.
		 */
		player1.clear();
		player1.add(head);
		
		/*
		 * Clear the board and add the head.
		 */
		board.clearBoard();
		board.setCell(head, GridCell.RacerAHead);
		
		/*
		 * Clear the directions and add north as the
		 * default direction.
		 */
		directionsP1.clear();
		directionsP1.add(Direction.North);
		
		/*
		 * Reset the logic timer.
		 */
		logicTimer.resetClock();
		
	}
	
	/**
	 * Gets the flag that indicates whether or not we're playing a new game.
	 * @return The new game flag.
	 */
	public boolean isNewGame() 
	{
		return isNewGame;
	}
	
	/**
	 * Gets the flag that indicates whether or not the game is over.
	 * @return The game over flag.
	 */
	public boolean isGameOver() 
	{
		return isGameOver;
	}
	
	/**
	 * Gets the flag that indicates whether or not the game is paused.
	 * @return The paused flag.
	 */
	public boolean isPaused() {
		return isPaused;
	}
	public Direction getDirection() {
		return directionsP1.peek();
	}
	
	/**
	 * Entry point of the program.
	 * @param args Unused.
	 */
	public static void main(String[] args) 
	{
		TronGame tron = new TronGame();
		tron.startGame();
	}

}

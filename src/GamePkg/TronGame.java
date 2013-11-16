package GamePkg;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import RacerPkg.*;
import GameControllerPkg.User;
import GridPkg.*;




public class TronGame extends JFrame
{
	
	
	/**
	 * The number of milliseconds that should pass between each frame.
	 */
	private static final long FRAME_TIME = 1000L / 50L;
		
	
	
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
	 * The GameStatus instance for checking the status of the current game
	 * 	 
	 */
	public GameStatus status;
	
	/**
	 * The RacerA instance
	 */
	private Racer racerA;
	
	/**
	 * The RacerB instance
	 */
	private Racer racerB;

	
	
	private TronGame() 
	{
		super("Tron Prototype1");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		/*
		 * Initialize the game's status
		 */
		this.status= new GameStatus(this);		
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
					 */
					case KeyEvent.VK_W:
						if(!status.isPaused() && !status.isGameOver()) 
						{
							racerB.updateDirection(Direction.Up);
						}
						break;
					case KeyEvent.VK_UP:
						if(!status.isPaused() && !status.isGameOver()) 
						{
							racerA.updateDirection(Direction.Up);
						}
						break;	
					
					case KeyEvent.VK_S:
						if(!status.isPaused() && !status.isGameOver()) 
						{
							racerB.updateDirection(Direction.Down);
						}
						break;
					case KeyEvent.VK_DOWN:
						if(!status.isPaused() && !status.isGameOver()) 
						{
							racerA.updateDirection(Direction.Down);
						}
						break;
										
					case KeyEvent.VK_A:
						if(!status.isPaused() && !status.isGameOver())
						{
							racerB.updateDirection(Direction.Left);
						}
						break;
						
					case KeyEvent.VK_LEFT:
						if(!status.isPaused() && !status.isGameOver())
						{
							racerA.updateDirection(Direction.Left);
						}
						break;
				
					case KeyEvent.VK_D:
						if(!status.isPaused() && !status.isGameOver()) 
						{
							racerB.updateDirection(Direction.Right);
						}
						break;
						
					case KeyEvent.VK_RIGHT:
						if(!status.isPaused() && !status.isGameOver()) 
						{
							racerA.updateDirection(Direction.Right);
						}
						break;
					
					/*
					 * If the game is not over, toggle the paused flag and update
					 * the logicTimer's pause flag accordingly.
					 */
					case KeyEvent.VK_P:
						if(!status.isGameOver()) 
						{
							status.setPaused(!status.isPaused());
							logicTimer.setPaused(status.isPaused());
						}
						break;
					
					/*
					 * Reset the game if one is not currently in progress.
					 */
					case KeyEvent.VK_ENTER:
						if(status.isNewGame() || status.isGameOver())
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
		GridCell collisionA = racerA.updateRacer(board);
		GridCell collisionB = racerB.updateRacer(board);
		
		/*
		 * Here we handle the different possible collisions.
		 * 
		 */
		
		if(collisionA != GridCell.Empty )
		{
			status.setGameOver(true);
			logicTimer.setPaused(true);
		} 
		
		if(collisionB != GridCell.Empty )
		{
			status.setGameOver(true);
			logicTimer.setPaused(true);
		} 
	}
	
	
	/**
	 * Starts the game running.
	 */
	private void startGame(User userA,User userB) 
	{
		/*
		 * Initialize everything we're going to be using.
		 */
		racerA=new Racer(userA,ID.A);
		racerB=new Racer(userB,ID.B);
		/*
		 * clock speed enables faster or slower updates to the game. 
		 * the higher the time the faster the racer.
		 */
		this.logicTimer = new Clock(30.0f);
		this.status.setNewGame(true);
		this.status.setGameOver(false);
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
	/**
	 * Resets the game.
	 */
	private void resetGame() 
	{
		/*
		 * Reset the score statistics. 
		 */
		//this.score = 0;
				
		/*
		 * Reset both the new game and game over flags.
		 */
		this.status.setNewGame(false);
		this.status.setGameOver(false);
		
		/*
		 * Create the heads at the two opposite corners.
		 */
		Point headA = new Point(54, 54);
		Point headB = new Point(0, 54);
		/*
		 * Clear the board and add the heads.
		 */
		board.clearBoard();
		board.setCell(headA, GridCell.RacerAHead);
		board.setCell(headB, GridCell.RacerBHead);
		
		racerA.setUpRacer(headA, Direction.Up);
		racerB.setUpRacer(headB, Direction.Up);
		
		
		/*
		 * Reset the logic timer.
		 */
		logicTimer.resetClock();
		
	}
	/*public GameStatus getStatus()
	{
		return this.status;
	}
	*/
	

	/**
	 * Entry point of the program.
	 * @param args Unused.
	 */
	public static void main(String[] args) 
	{
		User userA=new User("A","a");
		User userB=new User("B","b");
		
		TronGame tron = new TronGame();
		tron.startGame(userA,userB);
	}

}

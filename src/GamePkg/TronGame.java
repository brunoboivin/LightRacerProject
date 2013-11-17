package GamePkg;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import RacerPkg.*;
import UserPkg.User;
import GridPkg.*;




public class TronGame extends JFrame implements Runnable,KeyListener
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

	/**
	 * 
	 */
	public String winner;
	
	public TronGame() 
	{
		super("Tron Prototype1");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
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
		addKeyListener(this);
	
		new Thread(this).start();
		/*
		 * Resize the window to the appropriate size, center it on the
		 * screen and display it.
		 */
		pack();
		setLocationRelativeTo(null);
		
		
		
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
			winner=racerB.getUser().getUsername();
			status.setGameOver(true);
			logicTimer.setPaused(true);
		} 
		
		if(collisionB != GridCell.Empty )
		{
			winner=racerA.getUser().getUsername();
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
		winner="";
		
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
		Point headA = new Point(74, 0);
		Point headB = new Point(0, 49);
		/*
		 * Clear the board and add the heads.
		 */
		board.clearBoard();
		board.setCell(headA, GridCell.RacerAHead);
		board.setCell(headB, GridCell.RacerBHead);
		
		racerA.setUpRacer(headA, Direction.Down);
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
	public String winnerIs()
	{
		return winner;
	}

	/**
	 * Entry point of the program.
	 * @param args Unused.
	 */
	public static void main(String[] args) 
	{
		new TronGame();
		//tron.startGame(userA,userB);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void run()
	{
		this.logicTimer = new Clock(30.0f);
		this.status.setNewGame(true);
		this.status.setGameOver(false);
		User userA=new User("Blue","a");
		User userB=new User("Red","b");
		startGame(userA,userB);
		
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

}

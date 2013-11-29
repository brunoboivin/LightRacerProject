package GamePkg;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import RacerPkg.*;
import UserPkg.User;
import GUIPkg.MainFrame;
import GameGUIPkg.GridPanel;
import GameGUIPkg.SidePanel;
import GridPkg.*;

/** Class used to create the JFrame for the Game and running it.
 * 
 * @author Shahrzad Tighnavardmollasaraei <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */


public class Game extends JFrame implements Runnable,KeyListener
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
	protected SidePanel side;
	
	/**
	 * The Clock instance for handling the game logic.
	 */
	private Clock logicTimer;
	
	/**
	 * The GameStatus instance for checking the status of the current game.
	 * 	 
	 */
	public GameStatus status;
	
	/**
	 * The RacerA instance (Darth Vader).
	 */
	private Racer racerA;
	
	/**
	 * The RacerB instance (Yoda).
	 */
	private Racer racerB;

	/**
	 * The Racer instance for the winner of the current round.
	 */
	private Racer roundWinner;
	/**
	 * The String name of the winner of the game.
	 */
	private String gameWinner;
	/**
	 * The String username of the Yoda Racer.
	 */
	public String yodaUser;
	/**
	 * The String username of the DarthVader Racer.
	 */
	public String darthUser;
	/**
	 * The String username of the loser of the game
	 */
	private String gameLoser;
	/**
	 * The constructor of the TronGame which initializes
	 * the frame and its components.
	 * @param asDarthVader User as DarthVader
	 * @param asYoda User as Yoda
	 * @param gameGrid The Game Grid
	 */
	public Game(User asDarthVader,User asYoda, Grid gameGrid) 
	{
		super("Light Battles Demo");
		setLayout(new BorderLayout());
		
		
		yodaUser=asYoda.getUsername();
		darthUser=asDarthVader.getUsername();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		
		setResizable(false);
		
		/*
		 * Initialize the game's status
		 */
		this.status= new GameStatus(this);		
		/*
		 * Initialize the game's panels and add them to the window.
		 */
		this.board = new GridPanel(this,gameGrid);
	//	System.out.println("height is"+TronGame.HEIGHT);
		this.side = new SidePanel(this,board.heightSize());
		
		//setPreferredSize(new Dimension(305+board.getGridPanelCol()*13,board.heightSize()));
		
		
		add(board, BorderLayout.CENTER);
		
		add(side, BorderLayout.WEST);
		
		
		
		/*
		 * Adds a new key listener to the frame to process input. 
		 */
		addKeyListener(this);
		/*
		 * Initialize the racers 
		 */
		initRacers(asDarthVader,asYoda);
	
		new Thread(this).start();
		/*
		 * Resize the window to the appropriate size, center it on the
		 * screen and display it.
		 */
		
		this.pack();
		
		setVisible(true);
		setLocationRelativeTo(null);
		//setVisible(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
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
		
		//If they collide with their own light or the wall at the same time
		if(collisionA== GridCell.RedLight && collisionB==GridCell.GreenLight) 
		{
			roundWinner=null;
			status.setRoundOver(true);
			logicTimer.setPaused(true);
		}
		//If they collide head to head
		else if((collisionA == GridCell.YodaIcon) || (collisionB == GridCell.DarthVaderIcon))
		{
			roundWinner=null;
			status.setRoundOver(true);
			logicTimer.setPaused(true);
		}
		else if(collisionA != GridCell.Empty && collisionA != GridCell.YodaIcon)
		{
			++racerB.wonRounds;
			roundWinner=racerB;
			status.setRoundOver(true);
			logicTimer.setPaused(true);
		} 
		
		else if(collisionB != GridCell.Empty && collisionB != GridCell.DarthVaderIcon )
		{
			++racerA.wonRounds;
			roundWinner=racerA;
			status.setRoundOver(true);
			logicTimer.setPaused(true);
		} 
	}
	
	
	/**
	 * Initializes the Racer instances.
	 * @param userA First User as DarthVader Racer
	 * @param userB	Second User as Yoda Racer
	 */
	private void initRacers(User userA,User userB) 
	{
		/*
		 * Initialize everything we're going to be using.
		 */
		racerA=new Racer(userA,ID.DARTHVADER);
		racerB=new Racer(userB,ID.YODA);
		roundWinner=null;
		gameLoser="";
		gameWinner="";
		
	}
	/**
	 * Resets the game.
	 */
	private void resetGame() 
	{
		
		/*
		 * Reset both the new game and game over flags.
		 */
		
		this.status.setNewGame(false);
		this.status.setRoundOver(false);
		//this.status.setGameOver(false);
		
		/*
		 * Create the heads at the two opposite corners.
		 */
		Point headA = new Point(0, 49);
		
		Point headB = new Point(74, 0);
		
		/*
		 * Clear the board and add the heads.
		 */
		board.clearBoard();
		board.setCell(headA, GridCell.DarthVaderIcon);
		board.setCell(headB, GridCell.YodaIcon);
		
		racerA.setUpRacer(headA, Direction.Up);
		racerB.setUpRacer(headB, Direction.Down);
		
		
		
		/*
		 * Reset the logic timer.
		 */
		logicTimer.resetClock();
		
	}
	/**
	 * Returns the maximum number of wins between two Racers.
	 * @return maxWin The maximum number of rounds won.
	 */	
	public int totalRoundWins()
	{
		if(this.racerB.wonRounds> this.racerA.wonRounds)
		{
			gameWinner=racerB.getUser().getUsername();
			gameLoser=racerA.getUser().getUsername();
			return this.racerB.wonRounds;
		}
		else
		{
			gameWinner=racerA.getUser().getUsername();
			gameLoser=racerB.getUser().getUsername();
			return this.racerA.wonRounds;
		}
	}
	/**
	 * Returns the winner of the current round as a String.
	 * @return roundWinner The username of the winnner of the racer.
	 */
	public String roundWinner()
	{
		if(roundWinner==null)
			return "";
		return roundWinner.getUser().username;
	}
	/**
	 * Return the ID of the Winner Racer. (NULL if it was a tie.)
	 * @return ID of the Winner
	 */
	public ID winnerId()
	{
		if(roundWinner==null)
			return ID.NULL;
		return roundWinner.racerId;
	}
	/**
	 * Returns the final winner as a String.
	 * @return Winner
	 */
	public String winnerIs()
	{	
		return gameWinner;
	}
	/**
	 * Returns the final loser as a String.
	 * @return Loser
	 */
	public String loserIs()
	{	
		return gameLoser;
	}
	/**
	 * Reinitializes the racers as well as the game status for new rounds.
	 */
	private void replayGame()
	{
		this.status=new GameStatus(this);
		this.initRacers(racerA.getUser(), racerB.getUser());
		
		this.side.removeAll();
		this.side.paintHeaders(1);
				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) 
		{

			/*
			 * If the game is not paused, and the game is not over...
			 * 
			 */
			case KeyEvent.VK_W:
				if(!status.isPaused() && !status.isRoundOver()) 
				{
					racerA.updateDirection(Direction.Up);
				}
				break;
			case KeyEvent.VK_UP:
				if(!status.isPaused() && !status.isRoundOver()) 
				{
					racerB.updateDirection(Direction.Up);
				}
				break;	
			
			case KeyEvent.VK_S:
				if(!status.isPaused() && !status.isRoundOver()) 
				{
					racerA.updateDirection(Direction.Down);
				}
				break;
			case KeyEvent.VK_DOWN:
				if(!status.isPaused() && !status.isRoundOver()) 
				{
					racerB.updateDirection(Direction.Down);
				}
				break;
								
			case KeyEvent.VK_A:
				if(!status.isPaused() && !status.isRoundOver())
				{
					racerA.updateDirection(Direction.Left);
				}
				break;
				
			case KeyEvent.VK_LEFT:
				if(!status.isPaused() && !status.isRoundOver())
				{
					racerB.updateDirection(Direction.Left);
				}
				break;
		
			case KeyEvent.VK_D:
				if(!status.isPaused() && !status.isRoundOver()) 
				{
					racerA.updateDirection(Direction.Right);
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(!status.isPaused() && !status.isRoundOver()) 
				{
					racerB.updateDirection(Direction.Right);
				}
				break;
			
			/*
			 * If the game is not over, toggle the paused flag and update
			 * the logicTimer's pause flag accordingly.
			 */
			case KeyEvent.VK_P:
				if(!status.isRoundOver()) 
				{
					status.setPaused(!status.isPaused());
					logicTimer.setPaused(status.isPaused());
				}
				break;
			
			/*
			 * Reset the game if one is not currently in progress.
			 */
			case KeyEvent.VK_SPACE:
				if(status.isNewGame() || status.isRoundOver())
				{
					if(!status.isGameOver())
						{
							resetGame();					
						}
				}
				else if(status.isGameOver())
				{	
					this.replayGame();
					resetGame();
					
				}
				break;
			case KeyEvent.VK_ENTER:
				if(status.isRoundOver() || status.isGameOver() || status.isNewGame())
				{
					this.board.changeGrid();
					this.board.repaint();
					
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if(status.isGameOver() || status.isNewGame())
					{	
						MainFrame.grid = new Grid();
						this.dispose();
					}
				break;
			}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	/**
	 * The runnable method of the class
	 */
	public void run()
	{
		this.logicTimer = new Clock(30.0f);
		this.status.setNewGame(true);
		this.status.setRoundOver(false);
		
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
                side.repaint();
               
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

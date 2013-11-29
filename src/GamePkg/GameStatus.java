package GamePkg;

import StatisticsPkg.Statistics;
/** Class used to define the current state of the game.
 * 
 * @author Shahrzad Tighnavardmollasaraei <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class GameStatus {

	/**
	 * The TronGame instance.
	 */
	private Game tronGame;
	/**
	 * The number of rounds played for a game.
	 */
	private int roundNumber;
	
	/**
	 * Whether we're running a new game.
	 */
	private boolean isNewGame;
	
	/**
	 * Whether or not the game is over.
	 */
	private boolean isGameOver;
	/**
	 * Whether or not the current round of the game is over.
	 */
	private boolean isRoundOver;
	
	/**	
	 * Whether or not the game is paused.
	 */
	private boolean isPaused ;
	/**
	 * Instance indicating whether or not the statistics are updated.
	 */
	private boolean statUpdated;
	/**
	 * Whether of not 3 rounds are done or not.
	 */
	private boolean threeRounds;
	/**
	 * GameStatus constructor.
	 * @param game
	 */
	public GameStatus(Game game)
	{
		this.tronGame=game;
		this.setGameOver(false);
		this.statUpdated=false;
		this.threeRounds=false;
	}
	/**
	 * Gets the flag that indicates whether or not we're playing a new game.
	 * @return The new game flag.
	 */
	public boolean isNewGame() 
	{
		return this.isNewGame;
	}
	/**
	 * Setting the status if New game or not.
	 * @param newGame
	 */
	
	public void setNewGame(boolean newGame) 
	{
		this.isNewGame = newGame;
	}

	/**
	 * Sets the flag that indicates whether or not the current round of the game is over.
	 * It also increments the number of rounds played.
	 * @param roundOver
	 */
	public void setRoundOver(boolean roundOver) 
	{
		this.isRoundOver=roundOver;
		if(roundOver==true)
		{
			++roundNumber;
		}
		
		if(roundOver==true)
		{
			if(roundNumber>3 && threeRounds==false)
			{
				this.tronGame.side.removeAll();
				this.tronGame.side.paintHeaders(roundNumber);
				this.threeRounds=true;
			}
			if(roundNumber>3 && (roundNumber%3)==0)
			{
				threeRounds=false;
			}
		}
		
	}
	/**
	 * Gets the flag that indicates whether or not the current round is over.
	 * @return The round over flag.
	 */
	
	public boolean isRoundOver() 
	{
		return this.isRoundOver;
	}
	/**
	 *
	 * @return the number of rounds played so far.
	 */
	public int getRoundNumber() 
	{
		return roundNumber;
	}	
	/**
	 * Gets the flag that indicates whether or not the game is over.
	 * If the game is over it updates the statistics.
	 * @return The game over flag.
	 */
	public boolean isGameOver() 
	{
		if(roundNumber>=2)
		{	/*
			 *If two or more round have been played check for the max number of wins
			 */
			if(this.tronGame.totalRoundWins()>=2)
			{
				/*
				 * If one player has won 2 or more rounds flag the game over.
				 */
				this.setRoundOver(false);
				this.setGameOver(true);
				
				
				if(statUpdated!=true)
				{	Statistics.update(tronGame.winnerIs(),tronGame.loserIs());
					statUpdated=true;
					//System.out.println("saved");
					//this.tronGame.run();
				}
			}
				
				
		}
		return this.isGameOver;
	}
	
	
	/**
	 * Setting if the game is over or not
	 * @param gameOver
	 */
	private void setGameOver(boolean gameOver) 
	{
		this.isGameOver = gameOver;
	}

	/**
	 * Gets the flag that indicates whether or not the game is paused.
	 * @return The paused flag.
	 */
	public boolean isPaused() 
	{
		return this.isPaused;
	}
	/**
	 * Setting the status as paused or not.
	 * @param paused
	 */
	public void setPaused(boolean paused) 
	{
		this.isPaused = paused;
	}
	
}

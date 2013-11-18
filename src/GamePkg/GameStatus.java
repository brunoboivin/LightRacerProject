package GamePkg;

import StatisticsPkg.Statistics;

public class GameStatus {

	/**
	 * The TronGame instance.
	 */
	private TronGame tronGame;
	/**
	 * The number of rounds played for a game
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
	 * GameStatus constructor.
	 * @param game
	 */
	public GameStatus(TronGame game)
	{
		this.tronGame=game;
		this.setGameOver(false);
	}
	/**
	 * Gets the flag that indicates whether or not we're playing a new game.
	 * @return The new game flag.
	 */
	public boolean isNewGame() 
	{
		return this.isNewGame;
	}
	
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
	public int getRoundNumber() {
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
				Statistics.update(tronGame.winnerIs(),tronGame.loserIs());
			}
				
				
		}
		return this.isGameOver;
	}
	
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

	public void setPaused(boolean paused) 
	{
		this.isPaused = paused;
	}

}

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
	private boolean statUpdated;
	private boolean threeRounds;
	/**
	 * GameStatus constructor.
	 * @param game
	 */
	public GameStatus(TronGame game)
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
				//roundNumber=1;
			}
			if(roundNumber>3 && (roundNumber%3)==0)
			{
			//	this.tronGame.side.removeAll();
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
	
	private void setGameOver(boolean gameOver) 
	{
		this.isGameOver = gameOver;
		//if(gameOver==false)
			//this.statUpdated=false;
		//System.out.println("stat is updated"+this.statUpdated);
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
	/*
	public void restartStatus(TronGame tron,boolean confirm)
	{
		//this.setNewGame(true);
		if(confirm==true)
		{
			tron.setGameOver(false);
			this.statUpdated=false;
		}
	}
	*/
}

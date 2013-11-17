package GamePkg;

import StatisticsPkg.Statistics;

public class GameStatus {

	/**
	 * @param args
	 */
	/**
	 * Whether or not we're running a new game.
	 */
	private TronGame tronGame;
	private int roundNumber;
	private boolean isNewGame;
	
	/**
	 * Whether or not the game is over.
	 */
	private boolean isGameOver;
	private boolean isRoundOver;
	
	/**	
	 * Whether or not the game is paused.
	 */
	private boolean isPaused ;
	
	
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
	
	
	public void setRoundOver(boolean roundOver) 
	{
		this.isRoundOver=roundOver;
		if(roundOver==true)
		{
			++roundNumber;
		}
	}
	
	
	public boolean isRoundOver() 
	{
		return this.isRoundOver;
	}
	
	public int getRoundNumber() {
		return roundNumber;
	}
	
	public void setNewGame(boolean newGame) 
	{
		this.isNewGame = newGame;
	}

	/**
	 * Gets the flag that indicates whether or not the game is over.
	 * @return The game over flag.
	 */
	public boolean isGameOver() 
	{
		if(roundNumber>=2)
		{
			if(this.tronGame.totalRoundWins()>=2)
			{
				this.setRoundOver(false);
				this.setGameOver(true);
				/**
				 * when the game is over the Statistics getUpdated
				 */
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

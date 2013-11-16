package GamePkg;

public class GameStatus {

	/**
	 * @param args
	 */
	/**
	 * Whether or not we're running a new game.
	 */
	private TronGame tronGame;
	private boolean isNewGame;
	
	/**
	 * Whether or not the game is over.
	 */
	private boolean isGameOver;
	
	/**	
	 * Whether or not the game is paused.
	 */
	private boolean isPaused ;
	
	
	public GameStatus(TronGame game)
	{
		this.tronGame=game;
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
	 * Gets the flag that indicates whether or not the game is over.
	 * @return The game over flag.
	 */
	public boolean isGameOver() 
	{
		return this.isGameOver;
	}
	
	public void setGameOver(boolean gameOver) 
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

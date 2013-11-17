package StatisticsPkg;

import java.io.Serializable;

/** Class used to store statistics of individual players into PlayerRecord's.
 * 
 * @author Bruno Boivin <bruno.boivin@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class PlayerRecord implements Record, Serializable {
	//Class properties
	private static final long serialVersionUID = -1212207084583292889L;
	
	private String username;
	private int gamesWon;
	private int gamesPlayed;
	
	//Constructor
	/**
	 * Constructor
	 * @param username username of player
	 */
	public PlayerRecord (String username){
		this.username = username;
	}
	
	//Getters
	/**
	 * @return username attribute
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * @return gamesWon attribute
	 */
	public int getGamesWon(){
		return this.gamesWon;
	}
	
	/**
	 * @return gamesPlayed attribute
	 */
	public int getGamesPlayed(){
		return this.gamesPlayed;
	}
	
	//Methods
	/**
	 * Increments both gamesPlayed and gamesWon attributes by 1.
	 */
	public void wonGame(){
		this.gamesPlayed++;
		this.gamesWon++;
	}
	
	/**
	 * Increments gamesPlayed attribute by 1.
	 */
	public void lostGame(){
		this.gamesPlayed++;
	}
}

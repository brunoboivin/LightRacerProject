package StatisticsPkg;

import java.io.Serializable;

/** Class used to store statistics of pairs of players into PairRecord's.
 * 
 * @author Bruno Boivin <bruno.boivin@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class PairRecord implements Record, Serializable {
	//Class properties
	private static final long serialVersionUID = -7395425916444454417L;
	
	private String playerA;
	private String playerB;
	private int gamesWonPlayerA;
	private int gamesWonPlayerB;
	
	//Constructor
	/**
	 * Constructor
	 * @param playerA username of first player
	 * @param playerB username of second player
	 */
	public PairRecord (String playerA, String playerB){
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
	//Getters
	/**
	 * @return playerA attribute
	 */
	public String getPlayerA(){
		return this.playerA;
	}
	
	/**
	 * @return playerB attribute
	 */
	public String getPlayerB(){
		return this.playerB;
	}
	
	/**
	 * @return gamesWonPlayerA attribute
	 */
	public int getGamesWonPlayerA(){
		return this.gamesWonPlayerA;
	}
	
	/**
	 * @return gamesWonPlayerB attribute
	 */
	public int getGamesWonPlayerB(){
		return this.gamesWonPlayerB;
	}
	
	//Methods
	/**
	 * Updates a PairRecord based on game winner.
	 * 
	 * @param winner username of player who won
	 */
	public void updateRecord(String winner){
		if( getPlayerA().equals(winner) ){
			gamesWonPlayerA++;
		}
		else if ( getPlayerB().equals(winner) ){
			gamesWonPlayerB++;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
}

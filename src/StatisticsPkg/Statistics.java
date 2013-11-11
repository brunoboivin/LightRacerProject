package StatisticsPkg;

import CSVPkg.CSVHandler;
import java.util.ArrayList;
import java.util.Hashtable;

/** Class used to obtain statistics of individual players, pairs of players, and top players.
 * 
 * @author Bruno Boivin <bruno.boivin@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class Statistics {
	
	/**
	 * Returns statistics of a specified player.
	 * 
	 * @param player username of player
	 * @return corresponding player record
	 */
	public static PlayerRecord getPlayerStat(String player) {
		Hashtable<String, PlayerRecord> playerRecords = getPlayerRecords();
		
		if (playerRecords.containsKey(player))
			return playerRecords.get(player);
		
		return new PlayerRecord(player); //if player plays for the 1st time, then return a new record
	}

	/**
	 * Returns statistics of a specified pair of players.
	 * 
	 * @param playerA username of first player
	 * @param playerB username of second player
	 * @return corresponding pair record
	 */
	public static PairRecord getPairRecord(String playerA, String playerB) {
		ArrayList<PairRecord> pairRecords = getPairRecords();

		for (PairRecord record : pairRecords){
			String player1 = record.getPlayerA();
			String player2 = record.getPlayerB();
			
			if( (player1.equals(playerA) && player2.equals(playerB)) 
					|| (player1.equals(playerB) && player2.equals(playerA)) ){
				return record;
			}	
		}
		
		return new PairRecord(playerA, playerB); //means A plays against B for the 1st time
	}
	
	/**
	 * Returns statistics of top 10 players.
	 * 
	 * @return list of player records
	 */
	public static ArrayList<PlayerRecord> getHighestScores() {
		return getHighestScores(10); //by default, return stats for top 10 players
	}
	
	/**
	 * Returns statistics of top X players.
	 * 
	 * @return list of player records
	 */
	public static ArrayList<PlayerRecord> getHighestScores(int x) {
		return findXHighestScores(getPlayerRecords(), x);
	}
	
	/**
	 * Updates statistics of a specified pair of players. It updates their respective player records and the corresponding pair record.
	 * 
	 * @param winner username of player who won
	 * @param loser username of player who lost
	 */
	public static void update(String winner, String loser) {
		updatePlayerStats(winner, loser);
		updatePairStats(winner, loser);
	}
	
	
	//Update Stats -- Helper Methods
	/**
	 * Updates the individual statistics of a specified pair of players.
	 * 
	 * @param winner username of player who won
	 * @param loser username of player who lost
	 */
	private static void updatePlayerStats(String winner, String loser) {
		//get map from csv file
		Hashtable<String, PlayerRecord> playerRecords = getPlayerRecords();
		
		//create new player records if needed
		if(!playerRecords.containsKey(winner)){
			playerRecords.put(winner, new PlayerRecord(winner));
		}
		
		if(!playerRecords.containsKey(loser)){
			playerRecords.put(loser, new PlayerRecord(loser));
		}

		//get player records
		PlayerRecord playerA = playerRecords.get(winner),
					 playerB = playerRecords.get(loser);
		
		//update player stats
		playerA.wonGame();
		playerB.lostGame();
		
		//write map back to file
		updatePlayerRecords(playerRecords);
	}
	
	/**
	 * Updates the pair statistics of a specified pair of players.
	 * 
	 * @param winner username of player who won
	 * @param loser username of player who lost
	 */
	private static void updatePairStats(String winner, String loser) {
		//get list of pair records from csv file
		ArrayList<PairRecord> pairRecords = getPairRecords();
		PairRecord target = null;
		boolean found = false;
		
		//loop over pairRecords to determine whether a pair record exists for these 2 players
		for (PairRecord record : pairRecords){
			if ( record.getPlayerA().equals(winner) && record.getPlayerB().equals(loser)
					|| record.getPlayerB().equals(winner) && record.getPlayerA().equals(loser)){
				target = record;
				found = true;
				break;
			}
		}
		
		//create new pair record if needed
		if(!found){
			target = new PairRecord (winner, loser);
		}
		
		//update pair stats
		target.updateRecord(winner);
		
		//add record to list of pair records if not already there
		if (!found){
			pairRecords.add(target);
		}
		
		//write list back to file
		updatePairRecords(pairRecords);
	}
	
	
	//CSV -- Helper Methods
	/**
	 * @return Hashtable that maps usernames to individual player records; the hashtable is read from a csv file
	 */
	private static Hashtable<String, PlayerRecord> getPlayerRecords(){
		return (Hashtable<String, PlayerRecord>) CSVHandler.read(StatisticsFiles.PLAYER_RECORDS.getPath());
	}
	
	/**
	 * @return ArrayList of pair records stored in a csv file
	 */
	private static ArrayList<PairRecord> getPairRecords(){
		return (ArrayList<PairRecord>) CSVHandler.read(StatisticsFiles.PAIR_RECORDS.getPath());
	}
	
	/** Overwrites csv file with an updated hashtable that maps usernames to individual player records.
	 * 
	 * @param playerRecords Hashtable that maps usernames to individual player records
	 */
	private static void updatePlayerRecords(Hashtable<String, PlayerRecord> playerRecords){
		CSVHandler.write(playerRecords, StatisticsFiles.PLAYER_RECORDS.getPath());
	}
	
	/** Overwrites csv file with an updated list of pair records.
	 * 
	 * @param pairRecords ArrayList of pair records
	 */
	private static void updatePairRecords(ArrayList<PairRecord> pairRecords){
		CSVHandler.write(pairRecords, StatisticsFiles.PAIR_RECORDS.getPath());
	}
	
	
	//Highest Scores -- Helper Methods
	/**
	 * Finds and returns the player record that has the highest number of games won.
	 * 
	 * @param playerRecords Hashtable that maps usernames to individual player records
	 * @return player record of best player
	 */
	private static PlayerRecord findHighestScore(Hashtable<String, PlayerRecord> playerRecords){
		if(playerRecords.keySet().size() == 0)
			return null;
		
		PlayerRecord max = playerRecords.elements().nextElement();
		
		for (String key : playerRecords.keySet()){
			PlayerRecord record = playerRecords.get(key);
			
			if(record.getGamesWon() > max.getGamesWon())
				max = record;
		}
		
		return max;
	}
	
	/**
	 * Finds and returns a specified number of best player records.
	 * 
	 * @param playerRecords Hashtable that maps usernames to individual player records
	 * @param x number of player records to be returned
	 * @return list of best player records
	 */
	private static ArrayList<PlayerRecord> findXHighestScores (Hashtable<String, PlayerRecord> playerRecords, int x){
		ArrayList<PlayerRecord> list = new ArrayList<PlayerRecord>();
		
		for (int i = 0; i < x; i++){
			if (playerRecords.size() == 0)
				break;
			
			PlayerRecord max = findHighestScore(playerRecords);
			list.add(max);
			playerRecords.remove(max.getUsername()); //finds the element from its associated key, then removes entry from hashtable
		}
		
		return list;
	}
	
}
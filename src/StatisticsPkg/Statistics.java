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
	
	public static PlayerRecord getPlayerStat(String player) {
		Hashtable<String, PlayerRecord> playerRecords = getPlayerRecords();
		
		if (playerRecords.containsKey(player))
			return playerRecords.get(player);
		
		return new PlayerRecord(player); //if player plays for the 1st time, then return a new record
	}

	
	
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
	
	
	public static ArrayList<PlayerRecord> getHighestScores(int x) {
		return findXHighestScores(getPlayerRecords(), x); //could hardcode x=10 since this is part of the requirements TO BE VERIFIED
	}
	
	
	public static void update(String winner, String loser) {
		updatePlayerStats(winner, loser);
		updatePairStats(winner, loser);
	}
	
	
	//Update Stats -- Helper Methods
	
	public static void updatePlayerStats(String winner, String loser) {
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
	
	public static void updatePairStats(String winner, String loser) {
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
	
	
	//CSV Helper Methods
	
	public static Hashtable<String, PlayerRecord> getPlayerRecords(){ //***MAKE IT PRIVATE
		return (Hashtable<String, PlayerRecord>) CSVHandler.read(StatisticsFiles.PLAYER_RECORDS.getPath());
	}
	
	public static ArrayList<PairRecord> getPairRecords(){ //***MAKE IT PRIVATE
		return (ArrayList<PairRecord>) CSVHandler.read(StatisticsFiles.PAIR_RECORDS.getPath());
	}
	
	private static void updatePlayerRecords(Hashtable<String, PlayerRecord> playerRecords){
		CSVHandler.write(playerRecords, StatisticsFiles.PLAYER_RECORDS.getPath());
	}
	
	private static void updatePairRecords(ArrayList<PairRecord> pairRecords){
		CSVHandler.write(pairRecords, StatisticsFiles.PAIR_RECORDS.getPath());
	}
	
	
	//Highest Scores -- Helper Methods
	
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
package StatisticsPkg;

import java.util.ArrayList;
import java.util.Hashtable;

import UserPkg.User;
import CSVPkg.*;

/** Class that runs and tests the other classes contained in the StatisticsPkg.
 * 
 * @author Bruno Boivin <bruno.boivin@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
class Driver_Stats {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long time0 = System.currentTimeMillis();
		//******TIME-BEG
		

		
		//REINITIALIZES BOTH CSV FILES
		/*
		Hashtable<String, PlayerRecord> playerRecords = new Hashtable<String, PlayerRecord>();
		ArrayList<PairRecord> pairRecords = new ArrayList<PairRecord>();

		CSVHandler.write(playerRecords, StatisticsFiles.PLAYER_RECORDS.getPath());
		CSVHandler.write(pairRecords, StatisticsFiles.PAIR_RECORDS.getPath());
		*/
		 
		//PRINT USERNAMES/PASSWORDS\
		/*
		ArrayList<User> users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");

		for (User user : users){
			System.out.println(user.username+": "+user.password);
		}
		*/
		
		//PRINT EVERYTHING (PRINTS CONTENT OF BOTH CSV FILES)
		/*
		Hashtable<String, PlayerRecord> playerRecords = Statistics.getPlayerRecords();
		ArrayList<PairRecord> pairRecords = Statistics.getPairRecords();
		
		System.out.println("PLAYERS RECORDS:");
		
		for (String key : playerRecords.keySet()){
			if (!key.equals("")){
				System.out.println(playerRecords.get(key).getUsername());
				System.out.println("  played: "+playerRecords.get(key).getGamesPlayed());
				System.out.println("  won: "+playerRecords.get(key).getGamesWon());
				System.out.println();
			}
			
		}
		
		System.out.println("******");
		
		System.out.println("PAIR RECORDS:");
		
		for (PairRecord r : pairRecords){
			if ( !r.getPlayerA().equals("") && !r.getPlayerB().equals("") ){
				System.out.println(r.getPlayerA()+" "+r.getGamesWonPlayerA());
				System.out.println(r.getPlayerB()+" "+r.getGamesWonPlayerB());
				System.out.println();
			}
		}
		*/
		
		//******TIME-END
		System.out.println();
		System.out.println(System.currentTimeMillis() - time0 + " ms");
	}

}

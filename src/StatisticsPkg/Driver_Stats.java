package StatisticsPkg;

import java.util.ArrayList;
import java.util.Hashtable;

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

		Hashtable<String, PlayerRecord> map = new Hashtable<String, PlayerRecord>();
		
		map.put("Bruno", new PlayerRecord("Bruno"));
		
		PlayerRecord r = map.elements().nextElement();
		System.out.println(r.getUsername() + " " + r.getGamesPlayed() + " " + r.getGamesWon());
		
		
		//******TIME-END
		System.out.println();
		System.out.println(System.currentTimeMillis() - time0 + " ms");
	}

}

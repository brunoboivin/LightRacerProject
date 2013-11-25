package unit_tests;

import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;

public class Test_Statistics {
	
	static Hashtable<String, PlayerRecord> playerRecords;
	
	@Before
	public void setUp() throws Exception {
		String name1 = "Bruno";
		String name2 = "Anita";		
		String name3 = "Neve";
		String name4 = "Kaichen";
		String name5 = "Salman";
		
		PlayerRecord r1 = new PlayerRecord(name1);
		PlayerRecord r2 = new PlayerRecord(name2);
		PlayerRecord r3 = new PlayerRecord(name3);
		PlayerRecord r4 = new PlayerRecord(name4);
		PlayerRecord r5 = new PlayerRecord(name5);
		
		Hashtable<String, PlayerRecord> records = new Hashtable<String, PlayerRecord>();
		records.put(name1, r1);
		records.put(name2, r2);
		records.put(name3, r3);
		records.put(name4, r4);
		records.put(name5, r5);
		
		playerRecords = records;
	}

	@After
	public void tearDown() throws Exception {
		playerRecords = null;
	}
	
	
	/*
	 * Since findHighestScore is a private method, we are using the reflexion API to test it.
	 * 
	 * findHighestScore should return the PlayerRecord of the best player
	 */
	@Test
	public void test_findHighestScore() throws Exception{
		//simulating games
		for (int i=0; i < 3; i++){
			playerRecords.get("Bruno").wonGame();
			playerRecords.get("Kaichen").lostGame();
		}
		
		for (int i=0; i < 4; i++){
			playerRecords.get("Anita").wonGame();
			playerRecords.get("Neve").lostGame();
		}
		
		for (int i=0; i < 5; i++){
			playerRecords.get("Salman").wonGame();
			playerRecords.get("Bruno").lostGame();
		}
		
		PlayerRecord result = getHighestScore();
		
		assertTrue(result.getUsername().equals("Salman"));
		assertTrue(result.getGamesPlayed() == 5 && result.getGamesWon() == 5);
	}
	
	//findHighestScore of empty Hashtable should return null
	@Test
	public void test_findHighestScore2() throws Exception{
		playerRecords = new Hashtable<String, PlayerRecord>();
		PlayerRecord result = getHighestScore();
		assertNull(result);
	}
	
	//findHighestScore of Hashtable that contains 1 element should return that element
	@Test
	public void test_findHighestScore3() throws Exception{
		playerRecords = new Hashtable<String, PlayerRecord>();
		PlayerRecord record = new PlayerRecord("Bruno");
		record.wonGame();
		playerRecords.put("Bruno", record);
		
		PlayerRecord result = getHighestScore();
		
		assertTrue(result.getUsername().equals("Bruno"));
		assertTrue(result.getGamesPlayed() == 1 && result.getGamesWon() == 1);
	}
	
	//findHighestScore of Hashtable that contains n equivalent elements should return any of them
	@Test
	public void test_findHighestScore4() throws Exception{
		PlayerRecord rec1 = playerRecords.get("Bruno");
		PlayerRecord rec2 = playerRecords.get("Anita");
		
		rec1.wonGame(); rec2.lostGame();
		rec2.wonGame(); rec1.lostGame();
		
		PlayerRecord result = getHighestScore();

		assertTrue(result.getUsername().equals("Bruno") || result.getUsername().equals("Anita"));
		assertTrue(result.getGamesPlayed() == 2 && result.getGamesWon() == 1);
	}

	//
	@Test
	public void test_findXHighestScore() {
		//findXHighestScore
		//TO BE COMPLETED
		
		/*
		 * Test scenarios:
		 * 1. Empty list
		 * 2. Return 1 element
		 * 3. Return many elements
		 * 4. Return n elements from a list of size < n
		 */
	}	
	
	/**Method used to test the private method "findHighestScore" of the Statistics class.
	 * 
	 * @return PlayerRecord of best player, i.e. the one with the highest number of games won
	 * @throws Exception
	 */
	private PlayerRecord getHighestScore() throws Exception{
		Statistics stats = new Statistics();
		
		Class [] parameterTypes = new Class[1];
		parameterTypes[0] = java.util.Hashtable.class;
		
		Method m = stats.getClass().getDeclaredMethod("findHighestScore", parameterTypes);
		m.setAccessible(true);
		
		Object [] parameters = new Object [1];
		parameters[0] = playerRecords;
		
		return (PlayerRecord) m.invoke(stats,  parameters);
	}
}

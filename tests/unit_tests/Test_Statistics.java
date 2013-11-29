package unit_tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import StatisticsPkg.PairRecord;
import StatisticsPkg.PlayerRecord;
import StatisticsPkg.Statistics;

/**
 * Unit tests for the Statistics class.
 */
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
	
	//Check whether the findHighestScore method indeed return the PlayerRecord of the best player
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

	//findXHighestScores should return an empty list when asked to return the best 0 elements
	@Test
	public void test_findXHighestScore1() throws Exception{
		ArrayList<PlayerRecord> records = getXHighestScore(playerRecords, 0);
		assertTrue(records.size() == 0);
	}	
	
	//findXHighestScores should return an empty list when given an empty Hashtable
	@Test
	public void test_findXHighestScore2() throws Exception {
		playerRecords.clear();
		ArrayList<PlayerRecord> records = getXHighestScore(playerRecords, 5);
		assertTrue(records.size() == 0);
	}	
	
	//findXHighestScores of Hashtable that contains 1 elements should return that element no matter how many elements are requested
	@Test
	public void test_findXHighestScore3() throws Exception {
		playerRecords.clear();
		playerRecords.put("John", new PlayerRecord("John"));
		
		ArrayList<PlayerRecord> records = getXHighestScore(playerRecords, 5);
		
		assertTrue(records.size() == 1);
		
		PlayerRecord record = records.get(0);
		assertTrue(record.getUsername().equals("John"));
		assertTrue(record.getGamesPlayed() == 0);
		assertTrue(record.getGamesWon() == 0);
	}	
	
	//findXHighestScores of Hashtable that contains n equivalent elements should return X of them
	@Test
	public void test_findXHighestScore4() throws Exception {		
		playerRecords.get("Bruno").wonGame();
		playerRecords.get("Anita").wonGame();
		playerRecords.get("Neve").wonGame();
		
		ArrayList<PlayerRecord> records = getXHighestScore(playerRecords, 2);
		assertTrue(records.size() == 2);
		
		String name1 = records.get(0).getUsername();
		String name2 = records.get(1).getUsername();
		
		assertTrue( (name1.equals("Bruno") || name1.equals("Anita") || name1.equals("Neve"))
					&&
					(name2.equals("Bruno") || name2.equals("Anita") || name2.equals("Neve"))
					&&
					!name1.equals(name2)
				);
	}	
	
	//findXHighestScores of Hashtable that contains n elements and asked to return X elements
	//Case 1: X > n , i.e. our table does not contain X elements. It should return what it contains
	@Test
	public void test_findXHighestScore5() throws Exception {
		assertTrue(getXHighestScore(playerRecords, 8).size() == 5);
	}
	
	//findXHighestScores of Hashtable that contains n elements and asked to return X elements
	//Case 1: X < n . It should only return X elements.
	@Test
	public void test_findXHighestScore6() throws Exception {
		assertTrue(getXHighestScore(playerRecords, 3).size() == 3);
	}
		
	//getPlayerStats should return a new PlayerRecord if player does not have a record yet
	@Test
	public void test_getPlayerStats1() {
		PlayerRecord record = Statistics.getPlayerStat("HelloWorld.ThisIsATest.HelloWorld");
		
		assertTrue(record.getUsername().equals("HelloWorld.ThisIsATest.HelloWorld"));
		assertTrue(record.getGamesWon() == 0 && record.getGamesPlayed() == 0);
	}
	
	//getPlayerStats should return the PlayerRecord associated to a specified player name
	@Test
	public void test_getPlayerStats2() {
		PlayerRecord record = Statistics.getPlayerStat("abc");
		
		assertTrue(record.getUsername().equals("abc"));
		assertTrue(record.getGamesWon() != 0 && record.getGamesPlayed() != 0);
	}
	
	//getPairStats should return a new PairRecord if the 2 players don't have one yet
	@Test
	public void test_getPairStats1() {
		PairRecord record = Statistics.getPairRecord("HelloWorld.ThisIsATest.HelloWorld", "HelloWorld.ThisIsATest.HelloWorld2");
		
		assertTrue( (record.getPlayerA().equals("HelloWorld.ThisIsATest.HelloWorld")&& record.getPlayerB().equals("HelloWorld.ThisIsATest.HelloWorld2"))
				 || (record.getPlayerB().equals("HelloWorld.ThisIsATest.HelloWorld")&& record.getPlayerA().equals("HelloWorld.ThisIsATest.HelloWorld2")));
		
		assertTrue(record.getGamesWonPlayerA() == 0 && record.getGamesWonPlayerB() == 0);
	}
	
	//getPairStats should return the PairRecord associated to the specified player names
	@Test
	public void test_getPairStats2() {
		PairRecord record = Statistics.getPairRecord("abc", "abcd");
		
		assertTrue( (record.getPlayerA().equals("abc") && record.getPlayerB().equals("abcd"))
				 || (record.getPlayerA().equals("abcd") && record.getPlayerB().equals("abc")));
		
		assertTrue(record.getGamesWonPlayerA() != 0 && record.getGamesWonPlayerB() != 0);
	}
	
	//Tests whether individual player stats are updated properly when Statistics.update is called
	@Test
	public void test_update() {
		String player1 = "TEST1TEST",
			   player2 = "TEST2TEST";
		
		PlayerRecord r1 = Statistics.getPlayerStat(player1),
		             r2 = Statistics.getPlayerStat(player2);
		
		Statistics.update(player1, player2);
		
		PlayerRecord r11 = Statistics.getPlayerStat(player1),
					 r22 = Statistics.getPlayerStat(player2);
		
		assertTrue(r11.getGamesPlayed() == r1.getGamesPlayed()+1);
		assertTrue(r22.getGamesPlayed() == r2.getGamesPlayed()+1);
		assertTrue(r11.getGamesWon() == r1.getGamesWon()+1);
	}
	
	//Tests whether pair stats are updated properly when Statistics.update is called
	@Test
	public void test_update2() {
		String player1 = "TEST1TEST",
			   player2 = "TEST2TEST";
			
		PairRecord record = Statistics.getPairRecord(player1, player2);
		
		Statistics.update(player1, player2);
		
		PairRecord record2 = Statistics.getPairRecord(player1, player2);
		
		assertTrue( (record.getGamesWonPlayerA()+1 == record2.getGamesWonPlayerA())
				 || (record.getGamesWonPlayerB()+1 == record2.getGamesWonPlayerB()));
	}
	
	
	//Helper Methods
	
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
	
	/**Method used to test the private method "findXHighestScores" of the Statistics class.
	 * 
	 * @param table
	 * @param x
	 * @return
	 * @throws Exception
	 */
	private ArrayList<PlayerRecord> getXHighestScore(Hashtable<String, PlayerRecord> table, int x) throws Exception{
		Statistics stats = new Statistics();
		
		Class [] parameterTypes = new Class[2];
		parameterTypes[0] = java.util.Hashtable.class;
		parameterTypes[1] = int.class;
		
		Method m = stats.getClass().getDeclaredMethod("findXHighestScores", parameterTypes);
		m.setAccessible(true);
		
		Object [] parameters = new Object [2];
		parameters[0] = table;
		parameters[1] = x;
		
		return (ArrayList<PlayerRecord>) m.invoke(stats,  parameters);
	}
}

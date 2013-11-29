package unit_tests;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import CSVPkg.CSVHandler;
import StatisticsPkg.PlayerRecord;
import UserPkg.User;

/**
 * Unit tests for the CSVHandler class.
 */

public class Test_CSVHandler {

	String path = "tests/unit_tests/tmp.csv";
	File file = new File(path); 
	
	@Before
	public void setUp() throws Exception {
		file.createNewFile();
	}

	@After
	public void tearDown() throws Exception {
		file.delete();
	}

	//Tests whether a serialized ArrayList of objects can be written to a file using the CSVHandler.write method
	@Test
	public void test_write_ArrayList() {
		long empty = file.length();
		ArrayList<Object> list = new ArrayList<Object>();
		
		PlayerRecord record = new PlayerRecord("John Smith");
		User user = new User("John", "Smith");
		list.add(record);
		list.add(user);
		
		CSVHandler.write(list, path);
		assertTrue(empty != file.length());
	}
	
	//Tests whether a serialized Hashtable of objects can be written to a file using the CSVHandler.write method
	@Test
	public void test_write_Hashtable() {
		long empty = file.length();
		Hashtable<Object, Object> map = new Hashtable<Object, Object>();
		
		PlayerRecord record = new PlayerRecord("John Smith");
		User user = new User("John", "Smith");
		map.put("record", record);
		map.put("user", user);
		
		CSVHandler.write(map, path);
		assertTrue(empty != file.length());
	}

	//Tests whether CSVHandler.read can retrieve an ArrayList of objects store in a csv file
	//Multiple related assertions are performed; if any of them fails, the whole test fails. 
	@Test
	public void test_read_ArrayList() {
		//write to file
		ArrayList<Object> list = new ArrayList<Object>();
		
		PlayerRecord record = new PlayerRecord("John Smith");
		User user = new User("John", "Smith");
		list.add(record);
		list.add(user);
		
		CSVHandler.write(list, path);
		
		//read from file
		ArrayList<Object> listRead = (ArrayList<Object>) CSVHandler.read(path);
		
		//verify data read is the same as data written to the file
		assertTrue(list.size() == listRead.size());
		
		PlayerRecord recordRead = (PlayerRecord) listRead.get(0);
		User userRead = (User) listRead.get(1);
		
		assertTrue( record.getUsername().equals(recordRead.getUsername()) );
		assertTrue( record.getGamesWon() == recordRead.getGamesWon() );
		assertTrue( record.getGamesPlayed() == recordRead.getGamesPlayed() );
		
		assertTrue( user.password.equals(userRead.password));
		assertTrue( user.username.equals(userRead.username));
	}
	
	//Tests whether CSVHandler.read can retrieve a Hashtable of objects store in a csv file
	//Multiple related assertions are performed; if any of them fails, the whole test fails. 
	@Test
	public void test_read_HashMap() {
		//write to file
		Hashtable<Object, Object> map = new Hashtable<Object, Object>();
		
		PlayerRecord record = new PlayerRecord("John Smith");
		User user = new User("John", "Smith");
		map.put("record", record);
		map.put("user", user);
		
		CSVHandler.write(map, path);

		//read from file
		Hashtable<Object, Object> mapRead = (Hashtable<Object, Object>) CSVHandler.read(path);
		
		//verify data read is the same as data written to the file
		assertTrue(map.keySet().size() == mapRead.keySet().size());
		
		PlayerRecord recordRead = (PlayerRecord)mapRead.get("record");
		User userRead = (User)mapRead.get("user");
		
		assertTrue( record.getUsername().equals(recordRead.getUsername()) );
		assertTrue( record.getGamesPlayed() == recordRead.getGamesPlayed() );
		assertTrue( record.getGamesWon() == recordRead.getGamesWon() );
		
		assertTrue( user.password.equals(userRead.password) );
		assertTrue( user.username.equals(userRead.username) );
	}
}

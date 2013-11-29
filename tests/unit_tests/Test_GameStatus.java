package unit_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import UserPkg.User;
import GamePkg.Game;
import GamePkg.GameStatus;
import GridPkg.Grid;

/**
 * Class to unit test GameStatus.java
 * @author Salman Hashmi <salman.hashmi2@mail.mcgill.ca>
 * @version 1.1
 * @since 2013-11-27
 *
 */
public class Test_GameStatus {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetRoundOver() throws Exception{
		User user1 = new User("salman", "1234");
		User user2 = new User("salman", "1234");
		
		Game tron = new Game(user1,user2, (new Grid()));
		GameStatus stat = new GameStatus(tron);
		
		stat.setRoundOver(false);
		assert(stat.isRoundOver() == false);
		
		stat.setRoundOver(true);
		assert(stat.isRoundOver() == true);
		
		stat.setRoundOver(true);
		stat.setRoundOver(true);// RoundNumber should be 3
		
		Class secret = stat.getClass(); 
		Field f = secret.getDeclaredField("threeRounds");
		f.setAccessible(true); // Makes it not private
		
		boolean threeRounds = (Boolean) f.get(stat); // Return threeRounds
		assert(threeRounds == false);
		
		stat.setRoundOver(true);// RoundNumber should be 4
		assert(threeRounds == true);
		
		stat.setRoundOver(true);// RoundNumber should be 5
		stat.setRoundOver(true);// RoundNumber should be 6
		assert(threeRounds == false);

		//fail("Not yet implemented");
	}

}

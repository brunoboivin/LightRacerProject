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
import GameGUIPkg.GridPanel;


/**
 * Class to unit test GameStatus.java
 * @author Salman Hashmi <salman.hashmi2@mail.mcgill.ca>
 * @version 1.2
 * @since 2013-11-27
 *
 */
public class Test_GameStatus {
	User user1 = new User("salman", "1234");
	User user2 = new User("salman", "1234");
	Grid grid = new Grid();
	Game tron = new Game(user1,user2, grid);
	GameStatus stat = new GameStatus(tron);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * Checks if  SetRoundOver correctly updates Round status
	 * @return void
	 */
	public void setRoundOverTest1() {
	
		stat.setRoundOver(false);
		assert(stat.isRoundOver() == false);
		
		stat.setRoundOver(true);
		assert(stat.isRoundOver() == true);
		
		stat.setRoundOver(true);
		stat.setRoundOver(true);// RoundNumber should be 3
	
	}
	
	@Test
	/**
	 * Checks if GameStatus is updating rounds correctly after round 3
	 * @throws Exception
	 * @return void
	 */
	public void setRoundOverTest2() throws Exception {
		
		Class secret = stat.getClass(); 
		Field f = secret.getDeclaredField("threeRounds");
		f.setAccessible(true); // Makes it not private
		
		boolean threeRounds = (Boolean) f.get(stat); // Return threeRounds
		assert(threeRounds == false);
	}
	
	@Test
	/**
	 * Checks if Game is not over at start up
	 * @throws Exception
	 * @return void
	 */
	public void isGameOverTest() throws Exception {
		// Game should not be over at start up
		assert(stat.isGameOver() == false);
	}

}

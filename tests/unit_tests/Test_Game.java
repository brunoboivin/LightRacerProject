package unit_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import RacerPkg.ID;
import UserPkg.User;
import GamePkg.Game;
import GridPkg.Grid;

/**
 * Tests Game.java
 * @author	Salman Hashmi <salman.hashmi2@mail.mcgill.ca>
 * @version	1.0
 * @since	2013-11-27
 *
 */
public class Test_Game {

		User user1 = new User ("Salman", "1234");
		User user2 = new User ("Salman", "1234");
		Game tron = new Game(user1,user2, (new Grid()));
		
	@Before
	public void setUpBeforeClass() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests the total number of rounds won
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void testTotalRoundWins() throws Exception {
		assert(tron.totalRoundWins()== 0);
	}
	
	/**
	 * Tests for the Round winner
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void testRoundWinner() throws Exception {
		assert(tron.roundWinner()=="");
	}
	
	/**
	 * Tests for the ID of the winning player
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void testWinnerId() throws Exception{
		assert(tron.winnerId()== ID.NULL);
	}
	
	/**
	 * Tests for the game winner
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void testWinnerIs() throws Exception {
		assert(tron.winnerIs()=="");
	}
	
	/**
	 * Tests for the game loser
	 * @return void
	 */
	@Test
	public void testLoserIs() {
		assert(tron.loserIs()=="");
	}
	
	
	
}



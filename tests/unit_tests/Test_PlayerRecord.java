package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import StatisticsPkg.PlayerRecord;

public class Test_PlayerRecord {

	@Test
	public void test() {
		PlayerRecord record = new PlayerRecord ("Bruno");
		assertTrue(record.getGamesPlayed() == 0 && record.getGamesWon() == 0);
		
		record.lostGame();
		assertTrue(record.getGamesPlayed() == 1 && record.getGamesWon() == 0);
	}

	@Test
	public void test2() {
		PlayerRecord record = new PlayerRecord ("Bruno");
		assertTrue(record.getGamesPlayed() == 0 && record.getGamesWon() == 0);
		
		record.wonGame();
		assertTrue(record.getGamesPlayed() == 1 && record.getGamesWon() == 1);
	}
}

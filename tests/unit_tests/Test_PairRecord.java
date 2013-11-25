package unit_tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import StatisticsPkg.PairRecord;

public class Test_PairRecord {

	@Test
	public void test() {
		PairRecord record = new PairRecord ("Bruno", "Anita");
		assertTrue(record.getGamesWonPlayerA() == 0 && record.getGamesWonPlayerB() == 0);
		
		record.updateRecord("Bruno");
		assertTrue(record.getGamesWonPlayerA() == 1 && record.getGamesWonPlayerB() == 0);
	}

	@Test
	public void test2() {
		PairRecord record = new PairRecord ("Bruno", "Anita");
		assertTrue(record.getGamesWonPlayerA() == 0 && record.getGamesWonPlayerB() == 0);
		
		record.updateRecord("Anita");
		assertTrue(record.getGamesWonPlayerA() == 0 && record.getGamesWonPlayerB() == 1);
	}
	
	@Test
	public void test3() {
		PairRecord record = new PairRecord ("Bruno", "Anita");
		assertTrue(record.getGamesWonPlayerA() == 0 && record.getGamesWonPlayerB() == 0);
		
		boolean caught = false;
		
		try{
			record.updateRecord("Bob");
		}
		catch(IllegalArgumentException e){
			caught = true;
		}
		assertTrue(caught);
	}
}

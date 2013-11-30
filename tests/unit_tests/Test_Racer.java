package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GridPkg.GridCell;
import RacerPkg.Direction;
import RacerPkg.ID;
import RacerPkg.Racer;
import UserPkg.User;

/**
 * Class to unit test RacerPkg.Racer
 * @author	Salman Hashmi <salman.hashmi2@mail.mcgill.ca>
 * @version 1.2
 * @since	2013-11-25
 *
 */

public class Test_Racer {
	
	@Before 
	public void setUp() throws Exception {
		User use = new User("Salman", "1234");
		Racer race = new Racer(use, ID.DARTHVADER);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRacer() throws Exception {
		User use = new User("Salman","1234");
		Racer race = new Racer(use, ID.DARTHVADER);
		Class secret = race.getClass();
		
		assert(race.wonRounds == 0);
		assert(race.racerId == ID.DARTHVADER);
		
		Field f = secret.getDeclaredField("headCell");
		f.setAccessible(true);
		
		assert(f.get(race) == GridCell.DarthVaderIcon);
		
		f = secret.getDeclaredField("bodyCell");
		f.setAccessible(true);
		
		assert(f.get(race) == GridCell.RedLight);
		
		f = secret.getDeclaredField("racerPoints");
		f.setAccessible(true);
		LinkedList<Point> l = ((LinkedList<Point>) (f.get(race)));
		
		assert(l.getFirst().x == 0);
		assert(l.getFirst().y == 0);
		
		f = secret.getDeclaredField("racerDir");
		f.setAccessible(true);
		LinkedList<Direction> d = ((LinkedList<Direction>) (f.get(race)));
		
		assert(d.getFirst() == Direction.Up);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		User use = new User("salman", "1234");
		Racer race = new Racer(use, ID.DARTHVADER);
		
		assert (race.getUser() == use);
	}
	
	@Test
	public void testSetUser()throws Exception{
		User use = new User("salman", "1234");
		User two = new User("usama","4567");
		Racer race = new Racer(use, ID.DARTHVADER);
		// Testing reflection
		Class secret = race.getClass(); 
		Method m = secret.getDeclaredMethod("setUser", User.class);
		m.setAccessible(true);
		m.invoke(race, two);
		
		assert(race.getUser() == two);
	}
	
	@Test
	public void testSetUpRacer() throws Exception{
		User use = new User("salman", "1234");
		Racer race = new Racer(use, ID.DARTHVADER);
		Class secret = race.getClass();
		
		race.setUpRacer( new Point(2,3),Direction.Down);
		Field f = secret.getDeclaredField("racerPoints");
		f.setAccessible(true);
		LinkedList<Point> l = ((LinkedList<Point>) (f.get(race))); //storing linkedlist in l
		
		assert(l.getFirst().x == 2);
		assert(l.getFirst().y == 3);
		
		f = secret.getDeclaredField("racerDir");
		f.setAccessible(true);
		LinkedList<Direction> d = ((LinkedList<Direction>) (f.get(race)));
		
		assert(d.getFirst() == Direction.Down);
	}
	
	@Test
	public void testUpdateDirection() throws Exception {
		User use = new User("salman", "1234");
		Racer race = new Racer(use, ID.DARTHVADER);
		Class secret = race.getClass();
		Field f = secret.getDeclaredField("racerDir");
		f.setAccessible(true);
		
		// This will not be added
		race.updateDirection(Direction.Down);
		LinkedList<Direction> d = ((LinkedList<Direction>) (f.get(race)));
		
		// Down should not override Up
		assert(d.getLast() == Direction.Up);
		
		race.updateDirection(Direction.Left);
		assert(d.getLast() == Direction.Left);
		
		race.updateDirection(Direction.Right);
		// Right should not override left
		assert(d.getLast() == Direction.Left);
		
		race.updateDirection(Direction.Down);
		assert(d.getLast() == Direction.Down);
		
		race.updateDirection(Direction.Right);
		assert(d.getLast() == Direction.Right);
		
		race.updateDirection(Direction.Up);
		assert(d.getLast() == Direction.Up);
	}
	
}

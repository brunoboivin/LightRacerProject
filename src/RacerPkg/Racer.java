package RacerPkg;

import java.awt.Point;
import java.util.LinkedList;

import UserPkg.User;

import GridPkg.GridCell;
import GridPkg.GridPanel;

public class Racer 
{
	
	/**
	 * The lists that contains the queued directions for the racer.
	 */
	private LinkedList<Direction> racerDir;
	/**
	 * The maximum number of directions that we can have polled in the
	 * direction list.
	 */
	private static final int MAX_DIRECTIONS = 3;
	/**
	 * The list that contains the points for the racer.
	 */
	private LinkedList<Point> racerPoints;
	/**
	 * The user instance.
	 */
	private User user;
	/**
	 * The number of rounds the Racer has won.
	 */
	public 	int wonRounds;
	/**
	 * GridCell instances.
	 */
	private GridCell headCell;
	private GridCell bodyCell;
	
	/**
	 * The Racer Constructor
	 * @param user
	 * @param id
	 */
	public Racer(User user,ID id)
	{
		this.setUser(user);
		this.wonRounds=0;
		this.racerPoints=new LinkedList<Point>();
		this.racerDir=new LinkedList<Direction>();	
		switch(id)
		{
			case A:
				this.headCell=GridCell.RacerAHead;
				this.bodyCell=GridCell.RacerABody;
				break;
				
			case B:
				this.headCell=GridCell.RacerBHead;
				this.bodyCell=GridCell.RacerBBody;
				break;
		}
	}
	public User getUser()
	{
		return user;
	}
	private void setUser(User user) {
		this.user = user;
	}
	/**
	 * Racer gets set up to be placed on the grid.
	 * @param head
	 * @param start
	 */
	public void setUpRacer(Point head,Direction start)
	{
		/*
		 * Clear the racer list and add the head.
		 */
		this.racerPoints.clear();
		this.racerPoints.add(head);
		/*
		 * Clear the directions and add north as the
		 * default direction.
		 */
		this.racerDir.clear();
		this.racerDir.add(start);
		
	}
	
	
	/**
	 * Updates the Racer's direction list.
	 * @param direction
	 */
	public void updateDirection(Direction direction)
	{
		switch( direction )
		{ 
			/* 
			 * Ensure that the direction list is not full, and that the most
			 * recent direction is adjacent to Up before adding the
			 * direction to the list.
			 */
			case Up:
				if(racerDir.size() < MAX_DIRECTIONS) 
				{
					Direction last = racerDir.peekLast();
					if(last != Direction.Down && last != Direction.Up) 
					{
						racerDir.addLast(Direction.Up);
					}
				}
				break;
			/* 
			 * Ensure that the direction list is not full, and that the most
			 * recent direction is adjacent to Down before adding the
			 * direction to the list.
			 */
			case Down:
				if(racerDir.size() < MAX_DIRECTIONS)
				{
					Direction last = racerDir.peekLast();
					if(last != Direction.Up && last != Direction.Down) 
					{
						racerDir.addLast(Direction.Down);
					}
				}
				break;
			/* 
			 * Ensure that the direction list is not full, and that the most
			 * recent direction is adjacent to left before adding the
			 * direction to the list.
			 */
			case Left:
				if(racerDir.size() < MAX_DIRECTIONS) 
				{
					Direction last = racerDir.peekLast();
					if(last != Direction.Right && last != Direction.Left) 
					{
						racerDir.addLast(Direction.Left);
					}
				}
				break;
			/* 
			 * Ensure that the direction list is not full, and that the most
			 * recent direction is adjacent to Up before adding the
			 * direction to the list.
			 */
				
			case Right:
				if(racerDir.size() < MAX_DIRECTIONS) 
				{
					Direction last = racerDir.peekLast();
					if(last != Direction.Left && last != Direction.Right) 
					{
						racerDir.addLast(Direction.Right);
					}
				}
				break;
		
		}
		
	}
	/**
	 * Updates the Racer's position.
	 * @return GridCell cell that the head moved into.
	 */
	public GridCell updateRacer(GridPanel board) 
	{

		/*
		 * Here we peek at the next direction rather than polling it. 
		 */
		
		Direction currentDirection = racerDir.peekFirst();
		
		/*
		 * Here we calculate the new point that the racer's head will be at
		 * after the update.
		 */		
		Point head = new Point(racerPoints.peekFirst());
		
		switch(currentDirection) 
		{
		case Up:
			head.y--;
			break;
			
		case Down:
			head.y++;
			break;
			
		case Left:
			head.x--;
			break;
			
		case Right:
			head.x++;
			break;
		}
		
		/*
		 * If the racer has moved out of bounds ('hit' a wall), we can just
		 * return that it's collided with itself, as both cases are handled
		 * identically.
		 */
		if(head.x < 0 || head.x >= board.getGridPanelCol() || head.y < 0 || head.y >= board.getGridPanelRow() ) 
		{
			return bodyCell; //Pretend we collided with our body.
		}
		
		/*
		 * Here we get the cell that was located at the new head position
		 */
		
		GridCell newHeadCell = board.getCell(head.x, head.y);
	
		
		
		/*
		 * Update the racers's position on the board if the newHeadCell is Empty:
		 * 
		 * 1. Set the old head position to a body cell.
		 * 2. Add the new head to the cell.
		 * 3. Set the new head position to a head cell.
		 * 
		 * If more than one direction is in the queue, poll it to read new
		 * input.
		 */
		if(newHeadCell==GridCell.Empty) 
		{
			board.setCell(racerPoints.peekFirst(),bodyCell);
			racerPoints.push(head);
			board.setCell(head, headCell);
			if(racerDir.size() > 1) 
			{
				racerDir.poll();
			}
		}
				
		return newHeadCell;
	}
	
	
}
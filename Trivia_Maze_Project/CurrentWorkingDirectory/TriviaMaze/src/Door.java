import java.awt.Color;
import java.awt.Graphics;

/*
 * Developed By: Isaiah Grant
 * The purpose of this class is to allow access to a given triviaItem
 * It will also provide an interface to allow a user to check if this door is locked / unlocked
 * and to lock / unlock the door setting isAttempted and isLocked accordingly.
 */
public class Door 
{
	protected boolean isAttempted;
	protected boolean isLocked;
	protected TriviaItem triviaItem;
	
	public Door()
	{
		this.isAttempted = false;
		this.isLocked = false;
		this.triviaItem = new TrueFalse();
	}
	
	public Door(TriviaItem trivia)
	{
		this.isAttempted = false;
		this.isLocked = false;
		this.triviaItem = trivia;
	}
	
	public boolean isLocked()
	{
		return isAttempted && this.isLocked;
	}
	
	public void lockDoor()
	{
		this.isAttempted = true;
		this.isLocked = true;
	}
	
	public void unlockDoor()
	{
		this.isAttempted = true;
		this.isLocked = false;
	}
	
	public TriviaItem getTriviaItem()
	{
		return this.triviaItem;
	}
	
	public void setTriviaItem(TriviaItem trivia)
	{
		this.triviaItem = trivia;
	}
	
	public boolean getIsAttempted() 
	{
		return isAttempted;
	}

	public void setIsAttempted(boolean isAttempted) 
	{
		this.isAttempted = isAttempted;
	}
	
	
	public void draw(int x, int y, int roomSize, Direction direction, Graphics brush)
	{
		int doorThickness = 4;
		int doorWidth = roomSize / 2;
		
		if(direction == Direction.RIGHT)
		{
			if(!this.isAttempted)
			{
				brush.setColor(Color.black);
				brush.fillRect(x, y, doorThickness, doorWidth);
			}
			
			if(this.isAttempted && !this.isLocked)
			{
				brush.setColor(Color.green);
				brush.fillRect(x, y, doorThickness, doorWidth);
			}
									
			if(this.isAttempted && this.isLocked)
			{
				brush.setColor(Color.red);
				brush.fillRect(x, y, doorThickness, doorWidth);
			}
			
			
		}		
		
		if(direction == Direction.DOWN)
		{
			if(!this.isAttempted)
			{
				brush.setColor(Color.black);
				brush.fillRect(x, y, doorWidth,doorThickness);
			}
			if(this.isAttempted && !this.isLocked)
			{
				brush.setColor(Color.green);
				brush.fillRect(x, y, doorWidth,doorThickness);
			}			
			
			if(this.isAttempted && this.isLocked)
			{
				brush.setColor(Color.red);
				brush.fillRect(x, y, doorWidth,doorThickness);
			}			
			
			
			
		}
		
	}
	

}

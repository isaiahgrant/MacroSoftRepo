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
}

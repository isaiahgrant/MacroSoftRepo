/*
 * Developed By: Isaiah Grant
 * The purpose of this class is to allow access to a given triviaItem
 * It will also provide an interface to allow a user to check if this door is locked / unlocked
 * NOTE: Since the trivia item factory is contained within the Game / Maze Builder I have provided
 * the interface to allow setting the trivia Item that is defaulted to TrueFalse DVC
 */
public class Door 
{
	private boolean isAttempted;
	private boolean isLocked;
	private TriviaItem triviaItem;
	
	public Door()
	{
		this.isAttempted = false;
		this.isLocked = false;
		this.triviaItem = new TrueFalse();
	}
	
	public boolean isLocked()
	{
		return this.isLocked;
	}
	
	public boolean isAttempted()
	{
		return this.isAttempted;
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
	
	public TriviaItem getTriviaItem() 
	{
		//TODO
		return null;
	}
}

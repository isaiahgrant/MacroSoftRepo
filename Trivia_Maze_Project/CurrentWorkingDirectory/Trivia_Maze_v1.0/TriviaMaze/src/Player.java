//Created by:Andrey Melnikov
//5.7.2014

//Class that represents a player
//Players have a name, location, and 
//Data about how many questions they answered and answered correctly

//This class contains a static method - isValidPlayer
//To make sure a Player is valid before being created.
//Use isValidPlayer before creating an instance of this class
public class Player 
{
	private String name;
	private int questionsAnsweredCorrectly;
	private int totalQuestionsAnswered;
	private Coordinates location;
	
	//Constructor with all of the fields
	public Player(String name, int questionsAnsweredCorrectly, int totalQuestionsAnswered, 
			Coordinates location)
	{
		this.name = name;
		this.questionsAnsweredCorrectly = questionsAnsweredCorrectly;
		this.totalQuestionsAnswered = totalQuestionsAnswered;
		this.location = location;
	}
	
	
	//Constructor used to create a totally new player (starts at location (0,0) )
	public Player(String name)
	{
		this(name, 0, 0, new Coordinates(0,0));
	}
	
	
	//Constructor used to create a player at a particular location 
	public Player(String name, Coordinates location)
	{
		this(name, 0, 0, location);
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public String getName()
	{
		return this.name;
	}
	
	
	//Throws IllegalArgumentException if < 0
	public void setQuestionsAnsweredCorrectly(int amount)
	{
		if(amount < 0)
		{
			String message = "Number of questions answered correctly can't be negative.\n" +
							 "Number of questions answered correctly:" + amount;
			throw new IllegalArgumentException(message);
		}
		
		this.questionsAnsweredCorrectly = amount;
	}
	
	
	public void increaseQuestionsAnsweredByOne()
	{
		this.questionsAnsweredCorrectly++;
	}
	
	
	public int getQuestionsAnsweredCorrectly()
	{
		return this.questionsAnsweredCorrectly;
	}
	
	
	//Throws IllegalArgumentException if < 0
	public void setTotalQuestionsAnswered(int amount)
	{
		if(amount < 0)
		{
			String message = "Number of questions answered can't be negative.\n" +
							 "Number of questions answered:" + amount;
			throw new IllegalArgumentException(message);
		}
		
		this.totalQuestionsAnswered = amount;
	}
	
	
	public void increaseTotalQuestionsAnsweredByOne()
	{
		this.totalQuestionsAnswered++;
	}
	
	
	public int getTotalQuestionsAnswered()
	{
		return this.totalQuestionsAnswered;
	}
	
	
	public Coordinates getPlayerLocation()
	{
		return this.location;
	}
	
	
	@Override
	public String toString()
	{
		return "Name:" + this.name + "\n" + 
			   "Questions Answered Correctly:" + this.questionsAnsweredCorrectly + "\n" + 
			   "Total Questions Answered:" + this.totalQuestionsAnswered + "\n" + 
			   "Location:" + this.location;
			   
	}
	
	
	//Returns true if all of the fields are proper
	public static boolean isValidPlayer(String name, int questionsAnsweredCorrectly, int totalQuestionsAnswered, 
			Coordinates location)
	{
		return questionsAnsweredCorrectly >= 0 && 
			   totalQuestionsAnswered >= 0 && 
			   Coordinates.areValidCoordinates(location.getRow(), location.getColumn()) &&
			   totalQuestionsAnswered >= questionsAnsweredCorrectly;
	}
}

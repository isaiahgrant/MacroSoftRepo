//Created by:Andrey Melnikov
//5.7.2014

//Class that handles player input via keyboard 

import java.util.*;

public class PlayerInput 
{
	private Scanner keyboard;
	
	public PlayerInput()
	{
		keyboard = new Scanner(System.in);
	}
	
	public String getPlayerName()
	{
		System.out.print("Enter your name:");
		
		return keyboard.nextLine();
	}
	
	public String getAnswerToQuestion()
	{
		
		//TODO - consider more checking here.
		System.out.print("Your answer:");
		return this.keyboard.next();
	}
	
	public Direction getDirectionToMove()
	{
		boolean done = false;
		
		String direction = null;

		
		//Consider redoing this with no exception handling
		while(!done)
		{
			System.out.print("Where would you like to go? (N,S,E,W):");
			
			
			try
			{
				direction = keyboard.next();
				
				if(!isValidDirectionInput(direction))
				{
					throw new IllegalArgumentException();
				}
				
				done = true;
				
			}
			catch(Exception error)
			{
				System.out.println("That is not a valid direction.");
			}
		}
		
		return convertStringToDirection(direction);
	}
	
	public boolean isValidDirectionInput(String directionInput)
	{
		return directionInput.equalsIgnoreCase("N") || 
			   directionInput.equalsIgnoreCase("S") ||
			   directionInput.equalsIgnoreCase("E") ||
			   directionInput.equalsIgnoreCase("W");
	}
	
	public Direction convertStringToDirection(String input)
	{
		if(input.equalsIgnoreCase("N"))
		{
			return Direction.UP;
		}
		
		if(input.equalsIgnoreCase("S"))
		{
			return Direction.DOWN;
		}
		
		if(input.equalsIgnoreCase("E"))
		{
			return Direction.RIGHT;
		}
		
		if(input.equalsIgnoreCase("W"))
		{
			return Direction.LEFT;
		}
		
		return null; //Error
	}
	
	
	//Performs closing up duties
	//Closes the scanner
	public void cleanUp()
	{
		this.keyboard.close();
	}
}


/*
 * Written by Alex Staeheli 5/6/2014
 * Obsolete.  We are using Database Driven TriviaFactory
 * in the final version.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Factory class that reads in one line from a file, makes an appropriate TriviaItem based on input, and returns it.
 * Each time getTriviaItem is called it reads in the next line of the file.  If it has reached the end of file then
 * the exception is caught and a MultiChoice TriviaItem is created with "default" in all of the fields and is returned. 
 */
public class TriviaFactory 
{
	private Scanner fin;
	
	public TriviaFactory(File in)
	{
		try 
		{
			this.fin = new Scanner(in);
		} catch (FileNotFoundException e) 
		{
			System.out.println("File was not found");
		}		
	}//end EVC
	
	
	/*
	 * Reads in a line from the file, parses the input, and constructs 
	 * an appropriately typed TriviaItem object and returns it.
	 */
	public TriviaItem getTriviaItem()
	{
		String input;
		String[] tokens;
		TriviaItem temp = null;
		
		try
		{
			input = fin.nextLine();
			if(input.equalsIgnoreCase("0 0"))
			{
				return new MultiChoice();
			}
			
			tokens = input.split("-");
			if(tokens[0].equalsIgnoreCase("mc"))
			{
				temp = new MultiChoice(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
			}
			
			if(tokens[0].equalsIgnoreCase("tf"))
			{
				temp = new TrueFalse(tokens[0], tokens[1], tokens[2], tokens[3]);
			}
		} catch (NoSuchElementException e)
		{
			System.out.println("Reached the end of data entries.  Generating TriviaItems with default values");
			temp = new MultiChoice();
		}		
		
		return temp;
	}//end getTriviaItem
	
	
	/*
	 * Closes the open file.
	 */
	public void closeFactory()
	{
		fin.close();
	}//end closeFactory

	
}//end TriviaFactory
/*
 * Written by Alex Staeheli 5/16/2014
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class TriviaFactoryDB 
{
	private Connection connection = null;
	private ResultSet resultSet = null;
	private Statement statement = null;
	private Random rnd = null;
	private int maxRows;
	private ArrayList<Integer> usedRows;	
	
	/*
	 * Creates a connection with sqlite database and generates a statement object.
	 * Also queries the database to determine the maximum number of rows in the database
	 */
	public TriviaFactoryDB()
	{
		usedRows = new ArrayList<Integer>();
		rnd = new Random(System.currentTimeMillis());
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:triviadb.db");
			statement = connection.createStatement();
		}catch(Exception e)
		{
			System.out.println("Unable to load driver class");
			e.printStackTrace();
		}		
		
		try
		{
			resultSet = statement.executeQuery("SELECT MAX(ID) FROM TRIVIA;");
			maxRows = resultSet.getInt(1);			
		}catch(Exception e)
		{
			System.out.println("Unable to make a connection");
			e.printStackTrace();
		}
		
	}//end TriviaFactoryDB constructor
	
	
	/*
	 * Random TriviaItem generator.
	 * Generates a random number within the range of total rows of entries in the database.
	 * Maintains a list of rows that have been selected already and if the item has been selected
	 * a new random number is generated.  After a valid row number has been selected it queries the
	 * database for that row, creates a TriviaItem of the correct type based the "type" field in the
	 * database and returns it.
	 */
	public TriviaItem getTriviaItem()
	{
		TriviaItem temp = null;
		Integer tempRnd;
		
		if(usedRows.size() == maxRows)
		{
			return new MultiChoice();
		}		
				
		tempRnd = Math.abs((rnd.nextInt() % maxRows)) + 1;
		if(!usedRows.contains(tempRnd))
		{
			usedRows.add(tempRnd);
		}
		
		else
		{
			
			while(usedRows.contains(tempRnd))
			{
				tempRnd = Math.abs((rnd.nextInt() % maxRows)) + 1;
			}
			
			usedRows.add(tempRnd);
		}
		
		try
		{
			resultSet = statement.executeQuery("SELECT * FROM TRIVIA WHERE ID = " + tempRnd + ";");
			
		}catch(Exception e)
		{
			System.err.println("There was an erro querying the database.");
		}
		
		try
		{
			resultSet.next();
			if(resultSet.getString(2).equalsIgnoreCase("tf"))
			{
				temp = new TrueFalse(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
				return temp;
			}
			
			if(resultSet.getString(2).equalsIgnoreCase("mc"))
			{
				temp = new MultiChoice(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
				return temp;
			}
			
		}catch(SQLException e)
		{
			//All DB entries have been used.  Generating Default values for TriviaItems
			return new MultiChoice();
		}
		
		return null;
	}//end getTriviaItem
	
	
	/*
	 * Closes the Database objects.
	 */
	public void closeFactory()
	{
		try 
		{
			statement.close();
			resultSet.close();
			connection.close();
		} catch (SQLException e) 
		{
			System.err.println("Error closing SQL objects");
			e.printStackTrace();
		}
		
	}//end closeFactory
	
	
}//end TriviaFactoryDB
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TriviaFactoryDB 
{
	private Connection connection = null;
	private ResultSet resultSet = null;
	private Statement statement = null;
	
	public TriviaFactoryDB()
	{
		
		try
		{
			Class.forName("org.sqlite.JDBC");
		}catch(Exception e)
		{
			System.out.println("Unable to load driver class");
			e.printStackTrace();
		}
		
		
		try
		{
			connection = DriverManager.getConnection("jdbc:sqlite:triviadb.db");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM trivia;");
		}catch(Exception e)
		{
			System.out.println("Unable to make a connection");
			e.printStackTrace();
		}
		
	}
	
	public TriviaItem getTriviaItem()
	{
		TriviaItem temp = null;
		
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
			System.out.println("Reached the end of data entries.  Generating TriviaItems with default values");
			temp = new MultiChoice();
			return temp;
		}
		return null;
	}
	
	
	public void closeFactory()
	{
		try
		{
			statement.close();
			connection.close();
		}catch(Exception e)
		{
			System.out.println("Unable to close factory");
		}
				
	}
	
	
}//end TriviaFactoryDB
















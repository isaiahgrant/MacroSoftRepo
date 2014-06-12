
/*
 *Written by Alex Staeheli 5/18/2014
 *This class deletes the entries in the database and populates it with the contents of TriviaFile.txt 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;


public class PopulateTriviaDB {

	public static void main(String[] args) 
	{
		int i, count;
		Statement statement = null;
		Connection connection = null;
		
		String input, sql;
		File in = new File("TriviaFile.txt");
		String[] tokens = null;
		Scanner fin = null;		
		
		try 
		{
			fin = new Scanner(in);
		} catch (FileNotFoundException e) 
		{
			System.out.println("File was not found");
		}
		
		try
		{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e)
		{
			System.out.println("Unable to load driver class");
			e.printStackTrace();
		}
		
		try
		{
			connection = DriverManager.getConnection("jdbc:sqlite:triviadb.db");
			statement = connection.createStatement();
			
			sql = "DELETE FROM TRIVIA";
			statement.executeUpdate(sql);
			
			count = 1;
			while(fin.hasNext())
			{
				input = fin.nextLine();
				tokens = input.split("-");
				
			
				if(tokens[0].equalsIgnoreCase("tf"))
				{
					sql = "INSERT INTO Trivia " + "VALUES (" + count + ", '" + tokens[0] + "', '" + tokens[1] + "', '" + tokens[2] + "', '" + tokens[3] + "', '" + "', '" + "', '" + "', '" + "')";
					System.out.println(sql);
					statement.executeUpdate(sql);
					count++;
				}
				
				if(tokens[0].equalsIgnoreCase("mc"))
				{
					sql = "INSERT INTO Trivia " + "VALUES (" + count + ", '" + tokens[0] + "', '" + tokens[1] + "', '" + tokens[2] + "', '" + tokens[3] + "', '" + tokens[4]  + "', '" + tokens[5] + "', '" + tokens[6] + "', '" + tokens[7] + "')";
					System.out.println(sql);
					statement.executeUpdate(sql);
					count++;
				}
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}

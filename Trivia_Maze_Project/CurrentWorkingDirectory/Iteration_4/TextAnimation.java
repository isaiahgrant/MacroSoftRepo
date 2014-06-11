//Created by:Andrey Melnikov
//5.27.2014

//Class that takes care of drawing a text line by line with a delay
//into a JTextArea
//implemented as a runnable so can be run in a separate thread and
//not interfere with other GUI components.
//Assumes last line of text is a newline character ('\n')

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextArea;

public class TextAnimation implements Runnable
{
	private static final int ANIMATION_DELAY = 200; //In milliseconds
	
	private String text;
	private JTextArea output;
	
	public TextAnimation(String text, JTextArea output)
	{
		this.text = text;
		this.output = output;
	}
	
	public void run()
	{
		int newLineIndex = this.text.indexOf('\n');
		int lastNewLineIndex = 0;
		String line = "";
		
		while( lastNewLineIndex < (this.text.length() - 1) )
		{
			try
			{
				line = this.text.substring(lastNewLineIndex, newLineIndex);
				output.append(line);
				
				lastNewLineIndex = newLineIndex;
				newLineIndex = this.text.indexOf('\n', lastNewLineIndex + 1);

				Thread.sleep(ANIMATION_DELAY);
			}
			catch(Exception error)
			{
				System.out.println("Thread sleep error");
			}
		}
	}
	
	//Gets a text file (assumed ASCII art)
	//and returns it as a string, where each newline
	//is denoted by \n
	//The last character is guaranteed to be a newline, '\n'
	//Returns an empty line if there was an error or file was empty.
	public static String getTextFileAsString(String fileName)
	{
		String result = "";
		
		try
		{
			Scanner fin = new Scanner(new File(fileName));
			
			while(fin.hasNextLine())
			{
				result += fin.nextLine() + "\n";
			}
			
			fin.close();
		}
		catch(FileNotFoundException error)
		{
			System.out.println("Error, file not found");
			error.printStackTrace();
		}

		return result;
	}
	
}

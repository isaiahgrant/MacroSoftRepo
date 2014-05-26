//Andrey Melnikov
//Created 5.25.2014

//Represents the game over screen, where player is
//presented with either an ASCII trophy art if he won
//Or a frowny face if he lost

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class GameOver implements GamePortion, ActionListener
{
	private JTextArea asciiOutput;
	
	private JLabel playerName;
	private JLabel playerQuestionsAnsweredCorrectly;
	private JLabel playerTotalQuestionsAnswered;
	
	private JButton newGameButton;
	private JButton quitButton;
	
	private JPanel buttons;
	
	private JFrame window;
	
	public GameOver(int width, int height, ActionListener listener)
	{
		this.asciiOutput = new JTextArea(40, 40);
		this.setUpButtons(listener);
		this.setUpWindow(width, height);
		
		//this.drawWinningArt();
	}
	
	public void setUpButtons(ActionListener listener)
	{
		this.newGameButton = new JButton("New Game");
		this.newGameButton.addActionListener(listener);
		
		this.quitButton = new JButton("Quit");
		this.quitButton.addActionListener(this);
	}
	
	public void setUpWindow(int width, int height)
	{
		this.window = new JFrame("Game Over");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new BorderLayout());
		
		this.window.add(this.asciiOutput, BorderLayout.CENTER);
		
		
		//temp
		
		this.window.add(this.newGameButton, BorderLayout.WEST);
		this.window.add(this.quitButton, BorderLayout.EAST);
		//endtemp
		
		this.window.setSize( new Dimension(width, height) );
		this.window.setLocation(50, 50); //Reconfigure to be as centered as possible
		this.window.setVisible(true);
	}
	
	public void drawWinningArt()
	{	
		
		this.window.invalidate();
		
		try{
		this.asciiOutput.append(".-..-\"\"``\"\"-..-.\n");
        this.asciiOutput.append("|(`\\`'----'`/`)|\n");
        this.asciiOutput.append(" \\\\ ;:.    ; //\n");
        this.asciiOutput.append("  \\\\|%.    |//\n");
        this.asciiOutput.append("   )|%:    |(\n");
        this.asciiOutput.append(" ((,|%.    |,))\n");
        this.asciiOutput.append("  '-\\::.   /-'\n");
        this.asciiOutput.append("     '::..'\n");
        this.asciiOutput.append("       }{\n");
        this.asciiOutput.append("      {__} \n");
        this.asciiOutput.append("     /    \\\n");
        this.asciiOutput.append("    |`----'|\n");
        this.asciiOutput.append("    | [#1] |\n");
        this.asciiOutput.append("    '.____.'\n");   
		}
		catch(Exception error)
		{
			System.out.println("Thread sleep error");
		}
	}
	
	public void drawLosingArt()
	{
		
	}
	
	@Override 
	public void close()
	{
		this.window.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == this.quitButton)
		{
			this.close();
		}
	}
}

//Andrey Melnikov
//Created 5.25.2014

//Represents the game over screen, where player is
//presented with either an ASCII trophy art if he won
//Or a frowny face if he lost

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;

public class GameOver extends GamePortion implements ActionListener
{
	private static final String WINNING_ASCII_ART_FILENAME = "winningASCIIArt.txt";
	private static final String LOSING_ASCII_ART_FILENAME = "losingASCIIArt.txt";

	private JTextArea asciiOutput;
	private JPanel asciiOutputContainer;
	
	
	private JLabel playerName;
	private JLabel playerQuestionsAnsweredCorrectly;
	private JLabel playerTotalQuestionsAnswered;
	private JLabel playerCorrectPercentage;
	
	private JPanel playerStatistics;
	
	private JButton newGameButton;
	private JButton quitButton;
	
	private JPanel buttons;
	
	public GameOver(int width, int height, Player player, boolean playerWon, ActionListener listener)
	{
		this.setUpTextOutput();
		this.setUpButtons(listener);
		this.setUpPlayerStatistics(player);
		this.setUpWindow(width, height);
		
		if(playerWon)
		{
			drawWinningArt();
		}
		else
		{
			drawLosingArt();
		}

	}
	
	public void setUpTextOutput()
	{
		this.asciiOutput = new JTextArea(15, 30);
		this.asciiOutput.setEditable(false);
		
		//Note: font type is important, otherwise
		//ASCII art may look skewed. Size does not affect skewness.
		Font outputFont = new Font("Courier New", Font.PLAIN, 20);
		this.asciiOutput.setFont(outputFont);
		
		FlowLayout outputHolder = new FlowLayout();
		this.asciiOutputContainer = new JPanel(outputHolder);
		
		this.asciiOutputContainer.add(this.asciiOutput);
	}
	
	public void setUpButtons(ActionListener listener)
	{
		this.newGameButton = new JButton("New Game");
		this.newGameButton.addActionListener(listener);
		
		this.quitButton = new JButton("Quit");
		this.quitButton.addActionListener(this);
		
		FlowLayout buttonLayout = new FlowLayout();
		buttonLayout.setHgap(200);
		
		this.buttons = new JPanel(buttonLayout);
		
		this.buttons.add(this.newGameButton);
		this.buttons.add(this.quitButton);
		
	}
	
	public void setUpPlayerStatistics(Player player)
	{
		JLabel statistics = new JLabel("Player Statistics");
		
		this.playerName = new JLabel("Player Name:" + player.getName());
		this.playerQuestionsAnsweredCorrectly = new JLabel("Questions Answered Correctly:" + player.getQuestionsAnsweredCorrectly());
		this.playerTotalQuestionsAnswered = new JLabel("Total Questions Answered:" + player.getTotalQuestionsAnswered());
		
		double correctQuestionPercentage = 0.0;
		
		if(player.getTotalQuestionsAnswered() != 0)
		{
			correctQuestionPercentage = (double)player.getQuestionsAnsweredCorrectly() / (double)player.getTotalQuestionsAnswered();
			correctQuestionPercentage *= 100;
		}
		
		this.playerCorrectPercentage = new JLabel("Player Correct: " + (int)correctQuestionPercentage  + "% of the time");
		
		this.playerStatistics = new JPanel(new GridLayout(5, 1));
		
		this.playerStatistics.add(statistics);
		this.playerStatistics.add(this.playerName);
		this.playerStatistics.add(this.playerQuestionsAnsweredCorrectly);
		this.playerStatistics.add(this.playerTotalQuestionsAnswered);
		this.playerStatistics.add(this.playerCorrectPercentage);
		
	}
	
	public void setUpWindow(int width, int height)
	{
		this.window = new JFrame("Game Over");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new BorderLayout());
		

		
		this.window.add(this.asciiOutputContainer, BorderLayout.NORTH);
		
		
		//temp
		

		
		//this.window.add(this.newGameButton, BorderLayout.WEST);
		//this.window.add(this.quitButton, BorderLayout.EAST);
		this.window.add(this.buttons, BorderLayout.SOUTH);
		this.window.add(this.playerStatistics, BorderLayout.CENTER);
		//endtemp
		
		this.window.setSize( new Dimension(width, height) );
		this.window.setVisible(true);
	}
	
	public void drawWinningArt()
	{	
		String asciiArt = TextAnimation.getTextFileAsString(WINNING_ASCII_ART_FILENAME);
		this.drawASCIIArt(asciiArt);
	}
	
	public void drawLosingArt()
	{
		String asciiArt = TextAnimation.getTextFileAsString(LOSING_ASCII_ART_FILENAME);
		this.drawASCIIArt(asciiArt);
	}
	
	public void drawASCIIArt(String art)
	{
		Thread outputThread = new Thread(new TextAnimation(art, this.asciiOutput));
		outputThread.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == this.quitButton)
		{
			this.close();
			System.exit(0);
		}
	}
}

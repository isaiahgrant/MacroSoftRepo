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
	private JLabel playerCorrectPercentage;
	
	private JPanel playerStatistics;
	
	private JButton newGameButton;
	private JButton quitButton;
	
	private JPanel buttons;
	
	private JFrame window;
	
	public GameOver(int width, int height, Player player, ActionListener listener)
	{

		
		this.setUpTextOutput();
		this.setUpButtons(listener);
		this.setUpPlayerStatistics(player);
		this.setUpWindow(width, height);
		
		drawWinningArt();

	}
	
	public void setUpTextOutput()
	{
		this.asciiOutput = new JTextArea(15, 40);
		
		Font outputFont = new Font("Arial", Font.PLAIN, 20);

		this.asciiOutput.setFont(outputFont);
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
		
		double correctQuestionPercentage = (double)player.getQuestionsAnsweredCorrectly() / (double)player.getTotalQuestionsAnswered();
		
		this.playerCorrectPercentage = new JLabel("Player Correct: " + correctQuestionPercentage  + " of the time");
		
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
		

		
		this.window.add(this.asciiOutput, BorderLayout.NORTH);
		
		
		//temp
		

		
		//this.window.add(this.newGameButton, BorderLayout.WEST);
		//this.window.add(this.quitButton, BorderLayout.EAST);
		this.window.add(this.buttons, BorderLayout.SOUTH);
		this.window.add(this.playerStatistics, BorderLayout.CENTER);
		//endtemp
		
		this.window.setSize( new Dimension(width, height) );
		this.window.setLocation(50, 50); //Reconfigure to be as centered as possible
		this.window.setVisible(true);
	}
	
	public void drawWinningArt()
	{	
		//Good lord...Try to find a better solution
		new Thread( new Runnable()
		{
				
				public void run()
				{
					try
					{
						asciiOutput.append(".-..-\"\"``\"\"-..-.\n"); Thread.sleep(200);
				        asciiOutput.append("|(`\\`'----'`/`)|\n");Thread.sleep(200); 
				        asciiOutput.append(" \\\\ ;:.    ; //\n");Thread.sleep(200); 
				        asciiOutput.append("  \\\\|%.    |//\n");Thread.sleep(200); 
				        asciiOutput.append("   )|%:    |(\n");Thread.sleep(200);
				        asciiOutput.append(" ((,|%.    |,))\n");Thread.sleep(200);
				        asciiOutput.append("  ' -\\::.   /-'\n");Thread.sleep(200);
				        asciiOutput.append("      '::..'\n");Thread.sleep(200);
				        asciiOutput.append("        }{\n");Thread.sleep(200);
				        asciiOutput.append("       {__} \n");Thread.sleep(200);
				        asciiOutput.append("      /    \\\n");Thread.sleep(200);
				        asciiOutput.append("     |`----'|\n");Thread.sleep(200);
				        asciiOutput.append("     | [#1] |\n");Thread.sleep(200);
				        asciiOutput.append("     '.____.'\n");   Thread.sleep(200);
					}
					catch(Exception error)
					{
						System.out.println("Thread sleep error");
					}
				}
								
		}
		
		).start();
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

//Andrey Melnikov
//Created 5.21.2014

//A GUI Introduction screen that allows
//a player to start a new game, or view help for the game
//help launches a tutorial in a pdf (launches in browser?)

/*
 * Edited by Isaiah Grant
 * Last edit: 5/27/14
 * Edit notes: Implemented help menu, added Labyrinth picture and corresponding libraries
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class IntroductionGUI implements ActionListener, GamePortion
{
	private BufferedImage mainPicture;
	private JButton newGame; 
	private JButton loadGame; //If there is time.
	private JButton help;
	
	private JFrame window;
	
	public IntroductionGUI(int width, int height, ActionListener endIntro)
	{
		this.newGame = new JButton("Start New Game");
		this.newGame.addActionListener(endIntro);
		
		this.loadGame = new JButton("Load Game");
		this.loadGame.addActionListener(this);
		
		this.help = new JButton("Help");
		this.help.addActionListener(this);
		
		JPanel buttonContainer = new JPanel(new FlowLayout());
		
		buttonContainer.add(this.newGame);
		buttonContainer.add(this.loadGame);
		buttonContainer.add(this.help);
		
		//Set up Labyrinth Image
		BufferedImage buffImage = null;
		JLabel imageLabel = new JLabel();
		try
		{
			buffImage = ImageIO.read(new File("./labyrinth.jpg"));
			imageLabel = new JLabel(new ImageIcon(buffImage));
		}
		catch(IOException e)
		{
			JOptionPane.showConfirmDialog(null, "Unfortunately, the labyrinth image file could not be found");
		}
		
		
		this.window = new JFrame("Introduction Screen");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new BorderLayout());
		this.window.add(buttonContainer, BorderLayout.SOUTH);
		this.window.add(imageLabel, BorderLayout.NORTH);
		
		this.window.setSize( new Dimension(width, height) );
		this.window.setLocation(50, 50); //Reconfigure to be as centered as possible
		this.window.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == loadGame)
		{
			JOptionPane.showMessageDialog(null, "Not yet implemented");
		}
		else if(event.getSource() == help)
		{
			JOptionPane.showMessageDialog(null, "**!Welcome to Macrosoft's Trivial Trivia Maze!**\n\n"+
												"To begin you may select 'Start New Game'.\n" +
												"Once a new game is started you begin at the entrance room of the maze. Your\n" +
												"objective is to reach the exit room. Each room has at most 4 doors. Each door\n" +
												"has a corresponding trivia question. To move from one room to another you must\n"+
												"choose the direction (or door) for which you would like to attempt to move.\n" +
												"After a direction is chosen you will be prompted with a trivia question.\n" +
												"Answer the question correctly and you are allowed to pass through the door.\n" +
												"An incorrect answer causes the attempted door to be locked, barring all future\n" +
												"access. Good Luck!");
		}
	}
	
	@Override
	public void close()
	{
		this.window.dispose();
	}
}

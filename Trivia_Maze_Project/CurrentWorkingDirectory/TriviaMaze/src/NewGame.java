//Andrey Melnikov
//Created 5.21.2014

//A GUI New Game screen that collects
//the player's name, and chosen difficulty.


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


public class NewGame implements GamePortion
{
	private JLabel nameLabel;
	private JLabel difficultyLabel;
	
	private JTextField nameField;
	
	private JButton launchGame;
	
	private JFrame window;
	
	public NewGame(int width, int height, ActionListener endNewGame)
	{	
		this.nameLabel = new JLabel("Name");
		
		this.nameField = new JTextField(10);
		
		this.difficultyLabel = new JLabel("Difficulty");
		
		this.launchGame = new JButton("Launch Game");
		this.launchGame.addActionListener(endNewGame);
		
		this.window = new JFrame("New Game Screen");
		this.window.setLayout(new BorderLayout());
		this.window.add(this.nameLabel, BorderLayout.WEST);
		this.window.add(this.nameField, BorderLayout.CENTER);
		this.window.add(this.difficultyLabel, BorderLayout.EAST);
		this.window.add(this.launchGame, BorderLayout.NORTH);
		
		this.window.setSize( new Dimension(width, height) );
		this.window.setLocation(50, 50); //Reconfigure to be as centered as possible
		this.window.setVisible(true);
	}
	
	@Override
	public void close()
	{
		this.window.dispose();
	}
}

//Andrey Melnikov
//Created 5.21.2014

//A GUI New Game screen that collects
//the player's name, and chosen difficulty.


//Last Edited By: Isaiah Grant
//6.4.2014
//Cleaned up layout. added combobox for difficulty selection as well as interface for sharing player and difficulty information

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;


public class NewGame extends GamePortion
{
	private JLabel nameLabel;
	private JLabel difficultyLabel;
	
	private JTextField nameField;
	
	private JButton launchGame;
	
	private Difficulty chosenDifficulty;
	private String chosenName = "Player";
	
	public NewGame(int width, int height, ActionListener endNewGame)
	{	
		this.nameLabel = new JLabel("Enter Name");
		
		this.nameField = new JTextField(10);
		
		this.difficultyLabel = new JLabel("Select Difficulty");
		String [] difficulties = {"EASY", "MODERATE", "HARD", "EXTREME"};
		
		this.launchGame = new JButton("Launch Game");
		this.launchGame.addActionListener(endNewGame);

		FlowLayout nameFlow = new FlowLayout();
		JPanel nameContainer = new JPanel(nameFlow);
		nameContainer.add(this.nameLabel);
		nameContainer.add(this.nameField);
		
		FlowLayout difficultyFlow = new FlowLayout();
		JPanel difficultyContainer = new JPanel(difficultyFlow);
		difficultyContainer.add(this.difficultyLabel);
		JComboBox difficultyCombo = new JComboBox(difficulties);
		difficultyCombo.setSelectedIndex(0);
		difficultyCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				String cmbType = (String) jcmbType.getSelectedItem();
				if(cmbType.equals("EASY")) { chosenDifficulty = Difficulty.EASY; }
				else if(cmbType.equals("MODERATE")) { chosenDifficulty = Difficulty.MODERATE; }
				else if(cmbType.equals("HARD")) { chosenDifficulty = Difficulty.HARD; }
				else { chosenDifficulty = Difficulty.EXTREME; }
			}
		});
		difficultyContainer.add(difficultyCombo);
		
		this.window = new JFrame("New Game Screen");
		this.window.setLayout(new FlowLayout());
		this.window.add(nameContainer);
		this.window.add(difficultyContainer);
		this.window.add(this.launchGame);
		
		this.window.setSize( new Dimension(width, height) );
		this.window.setVisible(true);
	}
	
	public Difficulty getDifficulty()
	{
		return this.chosenDifficulty;
	}
	
	public String getPName()
	{
		return this.chosenName;
	}
}

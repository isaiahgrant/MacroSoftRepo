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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class NewGame extends GamePortion implements ActionListener
{
	final int ICON_HGAP = 40;
	
	private JLabel nameLabel;
	private JLabel difficultyLabel;
	
	private JTextField nameField;
	
	private JButton launchGame;
	
	private JRadioButton icon1;
	private JRadioButton icon2;
	private JRadioButton icon3;
	private JRadioButton icon4;
	private JRadioButton icon5;
	private JRadioButton icon6;
	
	private Difficulty chosenDifficulty;
	private String chosenName = "Player";
	private String chosenIcon = "pikachu";
	
	public NewGame(int width, int height, ActionListener endNewGame)
	{
		this.window = new JFrame("New Game Screen");
		this.window.setLayout(new FlowLayout());
		this.window.setSize( new Dimension(width, height) );
		this.window.setVisible(true);
		this.launchGame = new JButton("Launch Game");
		this.launchGame.addActionListener(endNewGame);

		//Name Components
		setupNameComponents();
		
		//Difficulty Components
		setupDifficultyComponents();
		
		//Icon Components
		setupIconComponents();
		
		this.window.add(this.launchGame);
	}
	
	public void setupNameComponents()
	{
		this.nameLabel = new JLabel("Enter Name");
		this.nameField = new JTextField(10);
		FlowLayout nameFlow = new FlowLayout();
		JPanel nameContainer = new JPanel(nameFlow);
		this.nameField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) 
			{
				chosenName = nameField.getText();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) 
			{
				chosenName = nameField.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) 
			{
				chosenName = nameField.getText();
			}
		});
		nameContainer.add(this.nameLabel);
		nameContainer.add(this.nameField);
		
		this.window.add(nameContainer);
	}
	
	public void setupDifficultyComponents()
	{
		this.difficultyLabel = new JLabel("Select Difficulty");
		String [] difficulties = {"EASY", "MODERATE", "HARD", "EXTREME"};
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
		
		this.window.add(difficultyContainer);
	}
	
	public void setupIconComponents()
	{
		FlowLayout radioButtonFlow = new FlowLayout();
		JPanel radioButtonContainer = new JPanel(radioButtonFlow);
		this.icon1 = new JRadioButton("Pikachu", true);
		this.icon2 = new JRadioButton("Samus", false);
		this.icon3 = new JRadioButton("Mario", false);
		this.icon4 = new JRadioButton("Link", false);
		this.icon5 = new JRadioButton("Kirby", false);
		this.icon6 = new JRadioButton("Doom", false);
		this.icon1.addActionListener(this);
		this.icon2.addActionListener(this);
		this.icon3.addActionListener(this);
		this.icon4.addActionListener(this);
		this.icon5.addActionListener(this);
		this.icon6.addActionListener(this);
		radioButtonContainer.add(icon1);
		radioButtonContainer.add(icon2);
		radioButtonContainer.add(icon3);
		radioButtonContainer.add(icon4);
		radioButtonContainer.add(icon5);
		radioButtonContainer.add(icon6);
		
		FlowLayout iconImageFlow = new FlowLayout();
		iconImageFlow.setHgap(ICON_HGAP);
		JPanel iconImageContainer = new JPanel(iconImageFlow);
		JLabel imageLabel1;
		JLabel imageLabel2;
		JLabel imageLabel3;
		JLabel imageLabel4;
		JLabel imageLabel5;
		JLabel imageLabel6;
		
		imageLabel1 = openImage("./images/pikachuIcon.png");
		imageLabel2 = openImage("./images/samusIcon.png");
		imageLabel3 = openImage("./images/marioIcon.png");
		imageLabel4 = openImage("./images/linkIcon.png");
		imageLabel5 = openImage("./images/kirbyIcon.png");
		imageLabel6 = openImage("./images/doomIcon.png");
		
		iconImageContainer.add(imageLabel1);
		iconImageContainer.add(imageLabel2);
		iconImageContainer.add(imageLabel3);
		iconImageContainer.add(imageLabel4);
		iconImageContainer.add(imageLabel5);
		iconImageContainer.add(imageLabel6);
		
		this.window.add(radioButtonContainer);
		this.window.add(iconImageContainer);
	}
	
	private JLabel openImage(String path)
	{
		BufferedImage buffImage;
		JLabel imageLabel;
		try
		{
			buffImage = ImageIO.read(new File(path));
			imageLabel = new JLabel(new ImageIcon(buffImage));
			return imageLabel;
		}
		catch(IOException e)
		{
			JOptionPane.showConfirmDialog(null, "Unfortunately, the icon image file could not be found");
			return new JLabel();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if( event.getSource().equals(this.icon1) )
		{
			this.chosenIcon = "pikachu";
			this.icon2.setSelected(false);
			this.icon3.setSelected(false);
			this.icon4.setSelected(false);
			this.icon5.setSelected(false);
			this.icon6.setSelected(false);
		}
		else if( event.getSource().equals(this.icon2) )
		{
			this.chosenIcon = "samus";
			this.icon1.setSelected(false);
			this.icon3.setSelected(false);
			this.icon4.setSelected(false);
			this.icon5.setSelected(false);
			this.icon6.setSelected(false);
		}
		else if( event.getSource().equals(this.icon3) )
		{
			this.chosenIcon = "mario";
			this.icon1.setSelected(false);
			this.icon2.setSelected(false);
			this.icon4.setSelected(false);
			this.icon5.setSelected(false);
			this.icon6.setSelected(false);
		}
		else if( event.getSource().equals(this.icon4) )
		{
			this.chosenIcon = "link";
			this.icon1.setSelected(false);
			this.icon2.setSelected(false);
			this.icon3.setSelected(false);
			this.icon5.setSelected(false);
			this.icon6.setSelected(false);
		}
		else if( event.getSource().equals(this.icon5) )
		{
			this.chosenIcon = "kirby";
			this.icon1.setSelected(false);
			this.icon2.setSelected(false);
			this.icon3.setSelected(false);
			this.icon4.setSelected(false);
			this.icon6.setSelected(false);
		}
		else if( event.getSource().equals(this.icon6) )
		{
			this.chosenIcon = "doom";
			this.icon1.setSelected(false);
			this.icon2.setSelected(false);
			this.icon3.setSelected(false);
			this.icon4.setSelected(false);
			this.icon5.setSelected(false);
		}
		//Guarantee that at least ONE is selected
		if(!(this.icon1.isSelected() || this.icon2.isSelected() || this.icon3.isSelected() ||
				this.icon4.isSelected() || this.icon5.isSelected() || this.icon6.isSelected()))
		{
			this.chosenIcon = "pikachu";
			this.icon1.setSelected(true);
		}
	}
	
	public Difficulty getDifficulty()
	{
		return this.chosenDifficulty;
	}
	
	public String getPName()
	{
		return this.chosenName;
	}
	
	public String getIcon()
	{
		return this.chosenIcon + "Icon";
	}
}
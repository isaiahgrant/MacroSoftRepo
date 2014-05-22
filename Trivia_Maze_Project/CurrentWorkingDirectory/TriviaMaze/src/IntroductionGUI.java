//Andrey Melnikov
//Created 5.21.2014

//A GUI Introduction screen that allows
//a player to start a new game, or view help for the game
//help launches a tutorial in a pdf (launches in browser?)

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class IntroductionGUI implements ActionListener, GamePortion
{
	private BufferedImage mainPicture;
	private JButton newGame; 
	private JButton loadGame; //If there's time.
	private JButton help;
	
	private JFrame window;
	
	public IntroductionGUI(ActionListener endIntro)
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
		
		this.window = new JFrame("Introduction Screen");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new BorderLayout());
		this.window.add(buttonContainer, BorderLayout.SOUTH);
		this.window.pack();
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
			JOptionPane.showMessageDialog(null, "This will show the tutorial.");
		}
	}
	
	@Override
	public void close()
	{
		this.window.dispose();
	}
}

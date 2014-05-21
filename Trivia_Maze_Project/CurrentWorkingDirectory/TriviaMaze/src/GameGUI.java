//Andrey Melnikov
//Created 5.19.2014

//Extension of Game class that includes a GUI

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;

//TODO - dynamic sizing of components.

public class GameGUI extends Game implements ActionListener
{
	/*public static void main(String[] args)
	{
		GameGUI mainGame = new GameGUI(800, 500);
		mainGame.draw();
	}*/
	
	private JFrame window;
	private JPanel canvas;
	private Graphics brush;
	
	private JTextArea outputArea;
	private JTextField inputArea;
	private JPanel inputOutput;
	
	private JButton northButton;
	private JButton eastButton;
	private JButton southButton;
	private JButton westButton;
	
	private JPanel directionButtons;
	
	
	public GameGUI(int width, int height)
	{
		//super();
		
		this.gameMaze = new MazeGUI(new Player("Sven"));

		this.setUpCanvas(width, height);
		this.setUpTextInputOutput();
		this.setUpInputButtons();
		this.setUpWindow();
		//this.drawSomething();
	}
	
	private void setUpCanvas(int width, int height)
	{
		BufferedImage drawingImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.brush = drawingImage.getGraphics();
		this.brush.setColor(Color.BLACK);
		
		
		//Set up label and panel to hold image
		JLabel imageHolder = new JLabel();
		imageHolder.setIcon(new ImageIcon(drawingImage) );
		canvas = new JPanel(new FlowLayout() );
		canvas.setBackground(Color.WHITE);
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.add(imageHolder);
	}
	
	private void setUpTextInputOutput()
	{	
		this.outputArea = new JTextArea(13, 30);
		this.outputArea.setBackground(Color.WHITE);
		
		this.inputArea = new JTextField(16);
		this.inputArea.setBackground(Color.WHITE);
		
		this.inputOutput = new JPanel(new BorderLayout());
		this.inputOutput.add(outputArea, BorderLayout.NORTH);
		this.inputOutput.add(inputArea, BorderLayout.SOUTH);
		

		//this.inputOutput.setPreferredSize(new Dimension(150, 160));
	}
	
	private void setUpInputButtons()
	{
		this.northButton = new JButton("N");
		this.northButton.addActionListener(this);
		this.eastButton = new JButton("E");
		this.eastButton.addActionListener(this);
		this.southButton = new JButton("S");
		this.southButton.addActionListener(this);
		this.westButton = new JButton("W");
		this.westButton.addActionListener(this);
		
		
		this.directionButtons = new JPanel(new BorderLayout());
		
		this.directionButtons.add(this.northButton, BorderLayout.NORTH);
		this.directionButtons.add(this.eastButton, BorderLayout.EAST);
		this.directionButtons.add(this.southButton, BorderLayout.SOUTH);
		this.directionButtons.add(this.westButton, BorderLayout.WEST);

	}
	
	private void setUpWindow()
	{
		window = new JFrame("Trivia Maze");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setLayout(new BorderLayout());
		window.setResizable(true); //TODO implement resize functionality
		
		window.add(this.canvas, BorderLayout.NORTH);
		window.add(this.inputOutput, BorderLayout.WEST);
		window.add(this.directionButtons, BorderLayout.EAST);
		
		window.pack();
		window.setVisible(true);
	}
	
	private void drawSomething()
	{
		for(int i = 0; i < 50; i++)
		{
			this.brush.drawOval(50 + 2*i, 50+ 3*i, 200, 200);
		}
	}
	
	@Override
	public void draw()
	{
		//Need to figure out inheritance hierarchy - obviously 
		//there is no draw(Graphics pen method in the ASCII version)
		this.brush.setColor(Color.CYAN);
		this.brush.fillRect(0, 0, this.window.getWidth(), this.window.getHeight());
		((MazeGUI)this.gameMaze).draw(this.brush);
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == this.northButton)
		{
			JOptionPane.showMessageDialog(null, "North");
		}
		else if(event.getSource() == this.eastButton)
		{
			JOptionPane.showMessageDialog(null, "East");
		}
		else if(event.getSource() == this.southButton)
		{
			JOptionPane.showMessageDialog(null, "South");
		}
		else if(event.getSource() == this.westButton)
		{
			JOptionPane.showMessageDialog(null, "West");
		}
	}
	
	
	//Processes movement
	public void processInput(Direction direction)
	{
		//TODO
	}
	
	//Processes answering a question
	public void processInput(String answer)
	{
		
	}
}

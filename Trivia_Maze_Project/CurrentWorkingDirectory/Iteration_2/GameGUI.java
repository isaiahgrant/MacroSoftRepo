//Andrey Melnikov
//Created 5.19.2014

//Extension of Game class that includes a GUI

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GameGUI extends Game
{
	/*public static void main(String[] args)
	{
		GameGUI mainGame = new GameGUI(800, 600);
	}*/
	
	private JFrame window;
	private JPanel canvas;
	private Graphics pen;
	
	private JTextArea outputArea;
	private JTextField inputArea;
	private JPanel inputOutput;
	
	private JButton northButton;
	private JPanel directionButtons;
	
	
	public GameGUI(int width, int height)
	{
		super();
		

		this.setUpCanvas(width, height);
		this.setUpTextInputOutput();
		this.setUpInputButtons();
		this.setUpWindow();
		this.drawSomething();
	}
	
	private void setUpCanvas(int width, int height)
	{
		BufferedImage drawingImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pen = drawingImage.getGraphics();
		pen.setColor(Color.BLACK);
		
		
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
		this.outputArea = new JTextArea(8, 13);
		this.outputArea.setBackground(Color.BLUE);
		
		this.inputArea = new JTextField(13);
		this.inputArea.setBackground(Color.RED);
		
		this.inputOutput = new JPanel();
		this.inputOutput.add(outputArea, BorderLayout.NORTH);
		this.inputOutput.add(inputArea, BorderLayout.SOUTH);
		

		this.inputOutput.setPreferredSize(new Dimension(150, 160));
	}
	
	private void setUpInputButtons()
	{
		this.northButton = new JButton("N");
		
		this.directionButtons = new JPanel();
		
		this.directionButtons.add(this.northButton);
	}
	
	private void setUpWindow()
	{
		window = new JFrame("Trivia Maze");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
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
			this.pen.drawOval(50 + 2*i, 50+ 3*i, 200, 200);
		}
	}
	
}

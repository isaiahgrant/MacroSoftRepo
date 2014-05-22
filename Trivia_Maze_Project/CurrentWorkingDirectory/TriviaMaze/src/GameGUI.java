//Andrey Melnikov
//Created 5.19.2014

//Extension of Game class that includes a GUI

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;

//TODO - dynamic sizing of components.

public class GameGUI extends Game implements ActionListener, GamePortion
{
	/*public static void main(String[] args)
	{
		GameGUI mainGame = new GameGUI(800, 650);
		mainGame.draw();
	}*/
	
	/*public static void main(String[] args)
	{
		GameGUI mainGame = new GameGUI(800, 650, Difficulty.EASY);
		mainGame.draw();
	}*/
	
	private JFrame window;
	private JPanel canvas;
	private Graphics brush;
	
	private JTextArea outputArea;
	private JTextField inputArea;
	private JPanel inputOutput;
	private JButton submitAnswer;
	
	private JButton northButton;
	private JButton eastButton;
	private JButton southButton;
	private JButton westButton;
	
	private JPanel directionButtons;
	
	private GameState currentState;
	
	
	//DEBUGING MATERIAL
	private JButton drawASCIIMaze;
	//END DEBUGING MATERIAL
	
	public GameGUI(int width, int height, Difficulty difficulty, ActionListener listener)
//	public GameGUI(int width, int height, Difficulty difficulty)
	{
		super();
		
		this.gameMaze = new MazeGUI(new Player("Sven"));

		this.setUpCanvas(width, height);
		this.setUpTextInputOutput();
		this.setUpInputButtons();
		this.setUpWindow();
		//this.drawSomething();
		
		//DEBUGING MATERIAL
		drawASCIIMaze = new JButton("Draw ASCII Maze");
		drawASCIIMaze.addActionListener(this);
		this.window.add(drawASCIIMaze, BorderLayout.CENTER);
		//END DEBUGING MATERIAL
		
		this.draw();
		currentState = GameState.GETTING_MOVEMENT_INPUT;
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
		
		this.submitAnswer = new JButton("Submit Answer");
		this.submitAnswer.addActionListener(this);
		
		this.inputOutput = new JPanel(new BorderLayout());
		this.inputOutput.add(outputArea, BorderLayout.NORTH);
		this.inputOutput.add(inputArea, BorderLayout.CENTER);
		this.inputOutput.add(submitAnswer, BorderLayout.SOUTH);
		

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
		this.brush.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		((MazeGUI)this.gameMaze).draw(this.brush);
		this.canvas.repaint();
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == this.northButton)
		{
			this.processInput(Direction.UP);
		}
		else if(event.getSource() == this.eastButton)
		{
			this.processInput(Direction.RIGHT);
		}
		else if(event.getSource() == this.southButton)
		{
			this.processInput(Direction.DOWN);
		}
		else if(event.getSource() == this.westButton)
		{
			this.processInput(Direction.LEFT);
		}
		else if(event.getSource() == this.submitAnswer)
		{
			this.processInput();
		}
		
		//DEBUGING MATERIAL
		else if(event.getSource() == this.drawASCIIMaze)
		{
			this.setOutputText(this.gameMaze.drawMazeDebug());
		}	
		//END DEBUGING MATERIAL
	}
	
	
	//Processes movement
	public void processInput(Direction direction)
	{
		if(this.currentState == GameState.GETTING_MOVEMENT_INPUT)
		{
			if(this.gameMaze.isValidMove(direction))
			{
				String question = "In which country is the city of Dzambul located?";//this.gameMaze.getQuestion(direction);
				this.setOutputText(question);
				
				this.currentState = GameState.GETTING_QUESTION_ANSWER;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You can't move there.");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please answer the question before trying to move.");
		}
		
		this.draw();
	}
	
	//Processes answering a question
	public void processInput()
	{
		if(this.currentState == GameState.GETTING_QUESTION_ANSWER)
		{
			String answer = this.getAnswerToQuestion();
			
			if(this.gameMaze.isValidAnswer(answer))
			{
				JOptionPane.showMessageDialog(null, "NEED TO PROCESS ANSWER AND INDICATE THAT PLAYER WAS RIGHT OR WRONG.");
				
				this.clearInputOutputText();
				this.currentState = GameState.GETTING_MOVEMENT_INPUT;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "That is not a valid answer.");
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "There is no question to answer right now.");
		}
	}
	
	private void setOutputText(String message)
	{
		this.outputArea.setText(message);
	}
	
	private String getAnswerToQuestion()
	{
		return this.inputArea.getText();
	}
	
	private void clearInputOutputText()
	{
		this.outputArea.setText("");
		this.inputArea.setText("");
	}
	
	@Override
	public void close() 
	{
		this.window.dispose();
	}
}

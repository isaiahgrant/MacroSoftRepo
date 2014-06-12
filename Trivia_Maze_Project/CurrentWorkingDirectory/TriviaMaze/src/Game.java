//Andrey Melnikov
//Created 5.19.2014

//GUI Game class, handles input from the player and its interaction with the maze,
//popping up a question prompt, and detecting if a game is won/over.

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Game extends GamePortion implements ActionListener, KeyListener
{
	private Maze gameMaze;
	private MazeBuilder mazeBuilder;
	
	private JPanel canvas;
	private Graphics brush;
	
	private QuestionPrompt questionPrompt;
	
	private JButton northButton;
	private JButton eastButton;
	private JButton southButton;
	private JButton westButton;
	
	//Labels used for correct/wrong notice and player stats
	private JLabel wrongNotice;
	private JLabel correctNotice;
	private JLabel playerNameLabel;
	private JLabel correctAnswersLabel;
	private JLabel totalAnswersLabel;
	private JLabel correctPercentageLabel;
	private JLabel doorknob;
	
	private JPanel directionButtons;
	private JPanel directionButtonsSouth;
	
	private JPanel controlsAndInformation;
	
	private GameState currentState;

	private ActionListener gameOverListener;
	private boolean playerWon;
	
	
	//Initialization of Game. Creates a window of size width/height
	//Passes player data, difficulty, and player icon to maze
	public Game(int width, int height, String playerName, Difficulty difficulty, String playerIcon, ActionListener listener)
	{
		this.mazeBuilder = new MazeBuilder(playerName, difficulty);
		this.gameMaze = this.mazeBuilder.getNewMaze();
		this.gameMaze.setIcon(playerIcon);
		
		this.gameOverListener = listener;
		
		int canvasWidth = Maze.getMazeWidthInPixels();
		int canvasHeight = Maze.getMazeHeightInPixels();
		
		this.questionPrompt = new QuestionPrompt("", "", this);
		this.questionPrompt.setVisible(false);
		
		this.setUpCanvas(canvasWidth, canvasHeight);
		this.setUpInputButtons();
		this.setUpControlsAndInformation();
		this.setUpWindow(width, height);
		
		this.playerWon = false;
		currentState = GameState.GETTING_MOVEMENT_INPUT;	
		
		this.draw();
	}
	
	
	//Creates a area to draw the maze and its associated graphics
	private void setUpCanvas(int width, int height)
	{
		BufferedImage drawingImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.brush = drawingImage.getGraphics();
		this.brush.setColor(Color.BLACK);
		
		//Set up label and panel to hold image
		JLabel imageHolder = new JLabel();
		imageHolder.setIcon(new ImageIcon(drawingImage) );
		
		FlowLayout canvasLayout = new FlowLayout();
		canvasLayout.setHgap(0);
		canvasLayout.setVgap(0);
		
		canvas = new JPanel(canvasLayout );
		canvas.setBackground(Color.WHITE);
		canvas.add(imageHolder);
	}

	
	//Creates and initializes the direction buttons (N/S/E/W)
	//This method adds the proper listeners for keyboard input
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
		
		this.northButton.addKeyListener(this);
		this.eastButton.addKeyListener(this);
		this.westButton.addKeyListener(this);
		this.southButton.addKeyListener(this);
		
		JPanel northButtonWrapper = new JPanel(new FlowLayout());
		JPanel eastButtonWrapper = new JPanel(new FlowLayout());
		JPanel southButtonWrapper = new JPanel(new FlowLayout());
		JPanel westButtonWrapper = new JPanel(new FlowLayout());

		northButtonWrapper.add(this.northButton);
		eastButtonWrapper.add(this.eastButton);
		southButtonWrapper.add(this.southButton);
		westButtonWrapper.add(this.westButton);
		
		this.directionButtons = new JPanel(new BorderLayout());
		this.directionButtonsSouth = new JPanel(new BorderLayout());
		
		this.directionButtons.add(northButtonWrapper, BorderLayout.NORTH);
		this.directionButtons.add(eastButtonWrapper, BorderLayout.EAST);
		this.directionButtons.add(westButtonWrapper, BorderLayout.WEST);
		
		this.directionButtonsSouth.add(southButtonWrapper, BorderLayout.NORTH);	
	}
	
	
	//Sets up right side of Game GUI
	//This includes the doorknob picture, the direction buttons
	//And player statistics as well as an area that indicates if a
	//player's answer was right/wrong
	private void setUpControlsAndInformation()
	{
		this.controlsAndInformation = new JPanel(new BorderLayout());
		
		JPanel rightWrongAndPlayerStats = new JPanel(new GridLayout(6, 1));
		JPanel correctAndStatsFlow = new JPanel(new FlowLayout());
		JPanel doorknobLayout = new JPanel(new FlowLayout());
		
		JPanel directionButtonsContainer = new JPanel(new BorderLayout());
				
		directionButtonsContainer.add(this.directionButtons, BorderLayout.NORTH);
		directionButtonsContainer.add(this.directionButtonsSouth, BorderLayout.CENTER);
		
		//Sets up labels for displaying correct/wrong answer and player stats.
		this.wrongNotice = new JLabel("Wrong!");
		this.wrongNotice.setVisible(false);
		this.wrongNotice.setForeground(Color.RED);
		
		this.correctNotice = new JLabel("Correct!");
		this.correctNotice.setVisible(false);
		this.correctNotice.setForeground(Color.BLUE);
		
		this.playerNameLabel = new JLabel("Player Name: " + this.gameMaze.getPlayer().getName());
		this.playerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		this.correctAnswersLabel = new JLabel("Correct Answers: 0");
		this.correctAnswersLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		this.totalAnswersLabel = new JLabel("Total Answered: 0");
		this.totalAnswersLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		this.correctPercentageLabel = new JLabel("Percentage: 100%");
		this.correctPercentageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		try
		{
			this.doorknob = new JLabel(new ImageIcon(ImageIO.read(new File("./images/doorknob.png"))));
		}
		catch(IOException e)
		{
			JOptionPane.showConfirmDialog(null, "The doorknob image could not be loaded.");
		}
		
		rightWrongAndPlayerStats.add(this.wrongNotice);
		rightWrongAndPlayerStats.add(this.correctNotice);
		rightWrongAndPlayerStats.add(this.playerNameLabel);
		rightWrongAndPlayerStats.add(this.correctAnswersLabel);
		rightWrongAndPlayerStats.add(this.totalAnswersLabel);
		rightWrongAndPlayerStats.add(this.correctPercentageLabel);
		
		doorknobLayout.add(this.doorknob);

		doorknobLayout.setBackground(Color.BLACK);
				
		this.controlsAndInformation.add(directionButtonsContainer, BorderLayout.NORTH);

		this.controlsAndInformation.add(doorknobLayout, BorderLayout.CENTER);
		
		correctAndStatsFlow.add(rightWrongAndPlayerStats);
		this.controlsAndInformation.add(correctAndStatsFlow, BorderLayout.SOUTH);		
	}

	
	//Sets up the main window that contains all of the Game components, and their layout
	private void setUpWindow(int width, int height)
	{
		window = new JFrame("Trivia Maze");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout windowLayout = new BorderLayout();
		windowLayout.setHgap(0);
		windowLayout.setVgap(0);
		
		window.setLayout(new BorderLayout());
		
		window.setResizable(true);
		
		window.add(this.canvas, BorderLayout.WEST);
		
		window.add(this.controlsAndInformation, BorderLayout.EAST);
		
		this.window.setSize( new Dimension(width, height) );
		window.setVisible(true);
	}
	
	
	//Handles the drawing of the game components
	//Draws a background for the maze, and the maze itself
	//Forces a redraw of the canvas after it is finished.
	public void draw()
	{
		this.brush.setColor(Color.BLACK);
		this.brush.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		this.gameMaze.draw(this.brush);
		this.canvas.repaint();
	}

	
	//Handles the N/S/E/W button presses resulting from clicking them
	//Also handles getting the supplied answer from a QuestionPrompt
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

		else if(event.getActionCommand().equals("questionPromptAnswer"))
		{
			this.processQuestion( this.questionPrompt.getAnswer() );
		}
	}
	
	
	//Processes player movement
	//Activates the QuestionPrompt if the user attempted a new door
	//Checks if game is over/winnable and reacts accordingly
	public void processInput(Direction direction)
	{
		if(this.currentState == GameState.GETTING_MOVEMENT_INPUT)
		{	
			//added to remove the "Wrong!" and "Correct!" when the player tries to move.
			this.correctNotice.setVisible(false);
			this.wrongNotice.setVisible(false);
			
			if(this.gameMaze.isValidMove(direction))
			{
				String question = this.gameMaze.getQuestion(direction);
				String questionType = this.gameMaze.getCurrentQuestionType();
				
				if(!question.equals(""))
				{
					this.questionPrompt.setQuestion(question, questionType);
					this.questionPrompt.setVisible(true);
					
					this.currentState = GameState.GETTING_QUESTION_ANSWER;
				}
				else
				{
					this.gameMaze.movePlayer();
				}
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
		
		if(this.gameEnded())
		{
			this.processGameEnded();
		}
	}
	
	
	//Gets the player's answer from QuestionPrompt and processes it
	//If the answer is correct/incorrect, a label will pop up indicating so on the GUI
	//Updates Player statistics on the Game GUI
	public void processQuestion(String answer)
	{
		if(this.currentState == GameState.GETTING_QUESTION_ANSWER)
		{	
			if(this.gameMaze.processAnswer(answer))
			{
				this.correctNotice.setVisible(true);
				this.correctAnswersLabel.setText("Correct Answers: " + this.gameMaze.getPlayer().getQuestionsAnsweredCorrectly());
			}
			else
			{
				this.wrongNotice.setVisible(true);
			}
			
			this.totalAnswersLabel.setText("Total Answered: " + this.gameMaze.getPlayer().getTotalQuestionsAnswered());
			this.correctPercentageLabel.setText("Percentage: " + ((int)((double)this.gameMaze.getPlayer().getQuestionsAnsweredCorrectly()/(double)this.gameMaze.getPlayer().getTotalQuestionsAnswered()*100)) + "%");
			
			this.currentState = GameState.GETTING_MOVEMENT_INPUT;
			this.questionPrompt.clearScreen();
			this.questionPrompt.setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "There is no question to answer right now.");
		}
		
		this.draw();
		
		if(this.gameEnded())
		{
			this.processGameEnded();
		}
	}
	
	
	//Gets the player in the game
	public Player getPlayer()
	{
		return this.gameMaze.getPlayer();
	}
	
	
	//Returns true if the game has ended
	//That is, if the game is over or is not winnable anymore
	private boolean gameEnded()
	{
		return this.gameFinished() || !this.gameWinnable();
	}
	
	
	//Takes appropriate action if the game has ended
	//Indicates to GameDriver to transition to GameOver GUI
	private void processGameEnded()
	{
		if(this.gameFinished())
		{
			this.playerWon = true;
		} //Default is false, so no need to change it
		
		this.gameOverListener.actionPerformed(new ActionEvent(this, 0, "endGame"));
	}
	
	
	//Returns if the player won or lost - true if won, false if lost
	public boolean getPlayerWon()
	{
		return this.playerWon;
	}
	
	
	//Returns true if the game has been won, false otherwise
	public boolean gameFinished()
	{
		return this.gameMaze.isPlayerAtExit();
	}
	
	//Returns false if the player has enough doors locked
	//So that they can not get to the exit
	//True otherwise.
	public boolean gameWinnable()
	{
		return this.gameMaze.isWinnable();
	}

	
	//Method required for KeyListener Interface
	//Not implemented as it is not needed
	@Override
	public void keyPressed(KeyEvent event) 
	{
		//Not needed here, but required for KeyListener interface.
	}

	
	//Method that detects the presses of keyboard arrow keys
	//And moves the player accordingly
	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_UP)
		{
			this.processInput(Direction.UP);
		}
		else if(event.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			this.processInput(Direction.RIGHT);
		}
		else if(event.getKeyCode() == KeyEvent.VK_DOWN)
		{
			this.processInput(Direction.DOWN);
		}
		else if(event.getKeyCode() == KeyEvent.VK_LEFT)
		{
			this.processInput(Direction.LEFT);
		}
	}

	
	//Method required for KeyListener Interface
	//Not implemented as it is not needed
	@Override
	public void keyTyped(KeyEvent event) 
	{
		//Not needed here, but required for KeyListener interface.
	}
}

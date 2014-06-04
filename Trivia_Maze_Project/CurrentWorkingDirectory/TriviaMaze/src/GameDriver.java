//Andrey Melnikov
//Created 5.21.2014

//Controls game flow - from introduction screen, to new game screen,
//To the game, and to game over.

import java.awt.event.*;

public class GameDriver implements ActionListener
{
	//Make the screen width as big as the maximum sized maze and room for GUI buttons on right
	public static final int DEFAULT_WIDTH = Maze.getMazeWidthInPixels()  + 140;
	//Make the screen height as big as the maximum sized maze and the window borders
	public static final int DEFAULT_HEIGHT = Maze.getMazeHeightInPixels() + 38; 
	
	public static void main(String[] args)
	{
		GameDriver game = new GameDriver();
	}
	
	private GamePortion currentGamePart;
	
	public GameDriver()
	{
		this.currentGamePart = new Introduction(DEFAULT_WIDTH,DEFAULT_HEIGHT, this);
		this.currentGamePart.centerOnScreen();
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		this.currentGamePart.close();
		
		if( this.currentGamePart.getClass().getSimpleName().equals("Introduction") )
		{
			this.currentGamePart = new NewGame(550,80, this);
		}
		else if( this.currentGamePart.getClass().getSimpleName().equals("NewGame") )
		{
			String pname = ((NewGame)this.currentGamePart).getPName();
			Difficulty theDiff = ((NewGame)this.currentGamePart).getDifficulty();
			this.currentGamePart = new Game(DEFAULT_WIDTH,DEFAULT_HEIGHT, pname, theDiff, this);
		}
		else if(this.currentGamePart.getClass().getSimpleName().equals("Game"))
		{
			Player gamePlayer = ((Game)this.currentGamePart).getPlayer();
			boolean playerWon =   ((Game)this.currentGamePart).getPlayerWon();
			
			this.currentGamePart = new GameOver(DEFAULT_WIDTH,DEFAULT_HEIGHT, gamePlayer,
												playerWon, this);
		}
		else if(this.currentGamePart.getClass().getSimpleName().equals("GameOver"))
		{
			this.currentGamePart = new NewGame(DEFAULT_WIDTH,DEFAULT_HEIGHT, this);
		}
		
		if(this.currentGamePart != null)
		{
			this.currentGamePart.centerOnScreen();
		}
	}
}

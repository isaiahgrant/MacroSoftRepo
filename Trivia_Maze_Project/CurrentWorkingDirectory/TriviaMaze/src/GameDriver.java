//Andrey Melnikov
//Created 5.21.2014

//Controls game flow - from introduction screen, to new game screen,
//To the game, and to game over.

import java.awt.event.*;

public class GameDriver implements ActionListener
{
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 650;
	
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
			this.currentGamePart = new NewGame(DEFAULT_WIDTH,DEFAULT_HEIGHT, this);
		}
		else if( this.currentGamePart.getClass().getSimpleName().equals("NewGame") )
		{
			//Get player name and difficulty from NewGameGUI here
			this.currentGamePart = new Game(DEFAULT_WIDTH,DEFAULT_HEIGHT, "Sven", Difficulty.EXTREME, this);
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

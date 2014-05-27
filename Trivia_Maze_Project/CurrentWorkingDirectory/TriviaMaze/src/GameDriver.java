//Andrey Melnikov
//Created 5.21.2014

//Controls game flow - from introduction screen, to new game screen,
//To the game, and to game over.

import java.awt.event.*;

public class GameDriver implements ActionListener
{
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 650;
	
	/*public static void main(String[] args)
	{
		GameDriver game = new GameDriver();
	}*/
	
	private GamePortion currentGamePart;
	
	public GameDriver()
	{
		this.currentGamePart = new IntroductionGUI(DEFAULT_WIDTH,DEFAULT_HEIGHT, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		this.currentGamePart.close();
		
		if( this.currentGamePart.getClass().getSimpleName().equals("IntroductionGUI") )
		{
			this.currentGamePart = new NewGameGUI(DEFAULT_WIDTH,DEFAULT_HEIGHT, this);
		}
		else if( this.currentGamePart.getClass().getSimpleName().equals("NewGameGUI") )
		{
			//Get player name and difficulty from NewGameGUI here
			this.currentGamePart = new GameGUI(DEFAULT_WIDTH,DEFAULT_HEIGHT, Difficulty.EASY, this);
		}
		else if(this.currentGamePart.getClass().getSimpleName().equals("GameGUI"))
		{
			Player gamePlayer = ((GameGUI)this.currentGamePart).getPlayer();
			this.currentGamePart = new GameOver(DEFAULT_WIDTH,DEFAULT_HEIGHT, gamePlayer, this);
		}
		else if(this.currentGamePart.getClass().getSimpleName().equals("GameOver"))
		{
			this.currentGamePart = new NewGameGUI(DEFAULT_WIDTH,DEFAULT_HEIGHT, this);
		}
	}
}

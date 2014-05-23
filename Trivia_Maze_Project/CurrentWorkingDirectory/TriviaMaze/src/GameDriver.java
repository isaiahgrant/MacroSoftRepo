//Andrey Melnikov
//Created 5.21.2014

//Controls game flow - from introduction screen, to new game screen,
//To the game, and to game over.

import java.awt.event.*;

public class GameDriver implements ActionListener
{
	/*public static void main(String[] args)
	{
		GameDriver game = new GameDriver();
	}*/
	
	private GamePortion currentGamePart;
	
	public GameDriver()
	{
		this.currentGamePart = new IntroductionGUI(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		this.currentGamePart.close();
		
		if( this.currentGamePart.getClass().getSimpleName().equals("IntroductionGUI") )
		{
			this.currentGamePart = new NewGameGUI(this);
		}
		else if( this.currentGamePart.getClass().getSimpleName().equals("NewGameGUI") )
		{
			//Get player name and difficulty from NewGameGUI here
			this.currentGamePart = new GameGUI(800, 650, Difficulty.EASY, this);
		}
	}
}

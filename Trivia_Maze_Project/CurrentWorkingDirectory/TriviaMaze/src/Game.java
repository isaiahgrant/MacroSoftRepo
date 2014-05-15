//Created by:Andrey Melnikov
//5.7.2014

//Class that represents the Game

//Tentative:
//This class handles the running of the game
//This includes all of the initialization, as well as 
//High level handling of user input
public class Game 
{
	private PlayerInput input;
	private Maze gameMaze;
	
	public static void main(String[] args)
	{
		Game mainGame = new Game();

		mainGame.draw();
		
		while( !mainGame.gameFinished() )
		{
			mainGame.processLogic();
			mainGame.draw();
		}
		
		mainGame.cleanUp();
	}
	
	
	public Game()
	{
		this.input = new PlayerInput();
		
		String playerName = input.getPlayerName();
		
		Player mainPlayer = new Player(playerName);
		
		this.gameMaze = new Maze(mainPlayer);
	}
	
	
	public void startGame()
	{
		//TODO
	}
	
	
	public void processLogic()
	{
		Direction directionToMove = null;
		
		directionToMove = this.input.getDirectionToMove();
		
		if( this.gameMaze.isValidMove(directionToMove) )
		{
			this.gameMaze.getQuestion(directionToMove);
			
			String answer = null;
			
			answer = this.input.getAnswerToQuestion();
			
			while( !this.gameMaze.isValidAnswer(answer) )
			{
				System.out.println("That is not a valid answer.");
				System.out.println("Please Enter a valid answer.");
				answer = this.input.getAnswerToQuestion();
			}
			
			
			//TODO - How does maze do its processing, knowing the player has gotten
			//The answer right?
			if(this.gameMaze.isCorrectAnswer(answer))
			{
				this.gameMaze.movePlayer(directionToMove);
			}
		}
		else
		{
			System.out.println("You can't move there.");
		}		
	}
	
	public void draw()
	{
		this.gameMaze.drawMaze();
	}
	
	public boolean gameFinished()
	{
		return this.gameMaze.isPlayerAtExit();
	}
	
	public void cleanUp()
	{
		this.input.cleanUp();
	}
}

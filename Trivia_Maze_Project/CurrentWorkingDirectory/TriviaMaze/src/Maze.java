import java.awt.Color;
import java.awt.Graphics;

/**
 * @author ajohnston
 *
 * Let me know what you think of the drawMaze methods.
 * Pretty much everything in this class will get more complicated as 
 * the rest of our project does.
 */
public class Maze {

	private Room[][] rooms;
	private int[][] simpleMaze; //Only used in isWinnable() method.
	private Room entrance;
	private Room exit;
	private Door current_Door;
	private Player player;
	
	public Maze(Player p1, Room[][] newRooms)
	{
		this.rooms = newRooms;
		this.current_Door = null;
		this.entrance = rooms[0][0];
		this.exit = rooms[2][2];
		this.player = p1;
		
		simpleMaze = new int[this.rooms.length][this.rooms.length];
	}
	
	Room[][] generateMaze()
	{
		Room[][] rooms = new Room[3][3];
		return rooms;
	}
	
	boolean isValidMove(Direction direct)
	{
		int x = this.player.getPlayerLocation().getColumn();
		int y = this.player.getPlayerLocation().getRow();
		Room curRoom = this.rooms[x][y];
		if(direct.equals(Direction.LEFT) && curRoom.getWestDoor().isLocked())
			;
		else if(x == 2 && direct.equals(Direction.RIGHT))
			return false;
		else if(y == 0 && direct.equals(Direction.UP))
			return false;
		else if(y == 2 && direct.equals(Direction.DOWN))
			return false;
		return true;
	}
	
	boolean isValidAnswer(String answer)
	{
		//TEMP CODE FOR PRESENTATION - REMOVE WHEN DONE
		return true;
		//return this.current_Door.getTriviaItem().getAnswer().equals(answer);
	}
	
	boolean isCorrectAnswer(String answer)
	{
		return this.current_Door.getTriviaItem().getAnswer().equals(answer);	
	}
	
	void processAnswer(String answer)
	{
		this.player.increaseTotalQuestionsAnsweredByOne();
		if(this.isCorrectAnswer(answer))
		{
			this.player.increaseQuestionsAnsweredByOne();
			this.current_Door.unlockDoor();
			movePlayer(Direction.UP);
		}
		else
		{
			this.current_Door.lockDoor();
		}
	}
	
	void movePlayer(Direction direct)
	{
		if(direct == Direction.UP)
			this.player.getPlayerLocation().decreaseRowByOne();
		if(direct == Direction.DOWN)
			this.player.getPlayerLocation().increaseRowByOne();
		if(direct == Direction.LEFT)
			this.player.getPlayerLocation().decreaseColumnByOne();
		if(direct == Direction.RIGHT)
			this.player.getPlayerLocation().increaseColumnByOne();		
	}
	
	boolean isSolvable()
	{
		return true;
	}
	
	//thinking doing this with an array would be easier and also less prone to errors.
	void drawMaze()
	{
		int i, j;
		
		for(i = 0; i < rooms.length; i++)
		{
			for(j = 0; j < rooms[i].length; j++)
			{
				if(i == 0)
				{
					System.out.print(" ___ ");
				}
			}
			
			System.out.println();
			
			for(j = 0; j < rooms[i].length; j++)
			{
				if(this.player.getPlayerLocation().getRow() == i && this.player.getPlayerLocation().getColumn() == j)//prints player location
				{
					System.out.print("|" + "_P_" + "|");
				}
				
				else if(i == rooms.length - 1 && j == rooms[i].length - 1)//prints exit location
				{
					System.out.print("|" + "_X_" + "|");
				}
				
				else
				{
					System.out.print("|" + "___" + "|");
				}
			}
		}

		System.out.println();
	}
	
	//DEBUGING MATERIAL
	String drawMazeDebug()
	{
		int i, j;
		
		String result = "";
		
		for(i = 0; i < rooms.length; i++)
		{
			for(j = 0; j < rooms[i].length; j++)
			{
				if(i == 0)
				{
					result += " ___ ";
				}
			}
			
			result += "\n";
			
			for(j = 0; j < rooms[i].length; j++)
			{
				if(this.player.getPlayerLocation().getRow() == i && this.player.getPlayerLocation().getColumn() == j)//prints player location
				{
					result += "|" + "_P_" + "|";
				}
				
				else if(i == rooms.length - 1 && j == rooms[i].length - 1)//prints exit location
				{
					result += "|" + "_X_" + "|";
				}
				
				else
				{
					result +="|" + "___" + "|";
				}
			}
		}

		result += "\n";
		
		return result;
	}
	//END DEBUGING MATERIAL
	
	//this is my implementation with an array
	void drawMazeArray()
	{
		char[][] mazeAra = new char[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
				mazeAra[i][j] = '.';
		}
		mazeAra[0][0] = 'E';
		mazeAra[3][3] = 'X';
		mazeAra[this.player.getPlayerLocation().getColumn()][this.player.getPlayerLocation().getRow()] = 'P';
		
		System.out.println(". . . . . . . . . . .");
		for(int i = 0; i < 3; i ++)
		{
			System.out.print(".");
			for(int j = 0; j < 3; j++)
			{
				System.out.print(" " + mazeAra[i][j] + " .");
			}
			System.out.println("\n. . . . . . . . . . .");
		}
	}
	
	public boolean isPlayerAtExit()
	{
		//TEMP CODE FOR PRESENTATION REMOVE WHEN NEEDED
		return this.player.getPlayerLocation().getRow() == (this.rooms.length-1) &&
				this.player.getPlayerLocation().getColumn() == (this.rooms.length-1);
		
		//END TEMP CODE
		//return false;
	}
	
	public String getQuestion(Direction direction)
	{
		//TODO
		//System.out.println("Maze.getQuestion(): not yet implemented!");
		
		//TEMP CODE
		String question = "";
		Room currentRoom = this.rooms[this.player.getPlayerLocation().getRow()][this.player.getPlayerLocation().getColumn()];
		try
		{
		if(direction == Direction.UP)
		{
			question = currentRoom.getNorthDoor().getTriviaItem().askQuestion();
		}
		else if(direction == Direction.DOWN)
		{
			question = currentRoom.getSouthDoor().getTriviaItem().askQuestion();			
		}  
		else if(direction == Direction.RIGHT)
		{
			question = currentRoom.getEastDoor().getTriviaItem().askQuestion();			
		}
		else if(direction == Direction.LEFT)
		{
			question = currentRoom.getWestDoor().getTriviaItem().askQuestion();		
		}
		System.out.println(question);
		}
		catch(Exception e)
		{
			question = "Error: question was not found! Maybe the trivia factory ran out of questions!";
		}
		return question;
		//END TEMP CODE
	}
	
	//Returns true if it is possible to get to the exit of the maze
	//from the players current location.
	//False otherwise
	public boolean isWinnable()
	{
		return isWinnable(this.player.getPlayerLocation().getRow(), 
						  this.player.getPlayerLocation().getColumn());
	}
	
	//Returns true if it is possible to get to the exit of the maze
	//from the given row/column
	//False otherwise
	private boolean isWinnable(int row, int column)
	{
		//Mark room as visited
		this.simpleMaze[row][column] = 3;
		
		//TODO Need to modify this to include Exit coordinate
		//Are we at the exit?
		if( row == this.simpleMaze.length && column == this.simpleMaze.length)
		{
			return true;
		}
		
		//Is north door open?
		if( row > 0 && this.northPathValid(row, column) )
		{
			boolean winnableByNorth = this.isWinnable(row - 1, column);
			
			if(winnableByNorth)
			{
				return true;
			}
		}
		
		//Is east door open?
		if( column < (this.simpleMaze.length -1) && this.eastPathValid(row, column) )
		{
			boolean winnableByEast = this.isWinnable(row, column + 1 );
			
			if(winnableByEast)
			{
				return true;
			}
		}
		
		//Is south door open?
		if( row < (this.simpleMaze.length - 1) && this.southPathValid(row, column) )
		{
			boolean winnableBySouth = this.isWinnable(row + 1, column);
			
			if(winnableBySouth)
			{
				return true;
			}
		}
		
		//Is west door open?
		if( column > 0 && this.westPathValid(row, column) )
		{
			boolean winnableByWest = this.isWinnable(row, column -1 );
			
			if(winnableByWest)
			{
				return true;
			}
		}
		
		//No path available
		return false;
	}
	
	//The *****PathValid methods are helper methods for isWinnable()
	//They return true if the specified path is valid to traverse to
	//False otherwise
	private boolean northPathValid(int row, int column)
	{
		return !this.rooms[row][column].getNorthDoor().isLocked() && 
				this.simpleMaze[row-1][column] != 3;
	}
	
	private boolean eastPathValid(int row, int column)
	{
		return !this.rooms[row][column].getEastDoor().isLocked() &&
				this.simpleMaze[row][column+1] != 3;
	}
	
	private boolean southPathValid(int row, int column)
	{
		return !this.rooms[row][column].getSouthDoor().isLocked() &&
				this.simpleMaze[row+1][column] != 3;
	}
	
	private boolean westPathValid(int row, int column)
	{
		return !this.rooms[row][column].getWestDoor().isLocked() &&
				this.simpleMaze[row][column - 1] != 3;
	}
	
	//END The *****PathValid methods 
	
	
	public void draw(Graphics brush)
	{
		int i, j, roomSize;
		roomSize = 60;
		
		
		for(i = 0; i < rooms.length; i++)
		{
			for(j = 0; j < rooms[i].length; j++)
			{
				rooms[i][j].draw(j * roomSize, i * roomSize, roomSize, brush);		
			}
			
		}
		
	}//END draw
}

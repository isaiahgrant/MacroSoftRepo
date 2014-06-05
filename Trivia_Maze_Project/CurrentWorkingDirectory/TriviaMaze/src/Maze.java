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
	public static final int MAX_WIDTH = 10;
	public static final int MAX_HEIGHT = 10;
	public static final int ROOM_SIZE = 60;
	
	
	private RoomTextures roomTextures;
	
	private Coordinates entrance;
	private Coordinates exit;
	
	private Direction currentPlayerDirection;
	
	private Door current_Door;
	private Player player;
	
	public Maze(Player p1, Room[][] newRooms)
	{
		this.rooms = newRooms;
		this.current_Door = null;

		this.entrance = new Coordinates(0,0);
		this.exit = new Coordinates(newRooms.length-1, newRooms[newRooms.length -1].length-1);
	
		//Making the exit Random, could easily work with making the entrance random as well.
//		this.exit = new Coordinates((int)(Math.random() * newRooms.length-1), (int)(Math.random() * newRooms.length[0].length-1))
//		
//		while(this.exit.getColumn() + this.exit.getRow() < newRooms.length())
//		{
//			this.exit = new Coordinates((int)(Math.random() * newRooms.length-1), (int)(Math.random() * newRooms.length[0].length-1));
//		}
		
		this.currentPlayerDirection = null;
		
		this.player = p1;
		
		simpleMaze = new int[this.rooms.length][this.rooms.length];
		
		this.roomTextures = new RoomTextures("pikachuIcon");
		
	}
	
	Room[][] generateMaze()
	{
		Room[][] rooms = new Room[3][3];
		return rooms;
	}
	
	boolean isValidMove(Direction direction)
	{
		int row = this.player.getPlayerLocation().getRow();
		int col = this.player.getPlayerLocation().getColumn();
		
		int maxRows = this.rooms.length - 1;
		int maxCols = this.rooms[this.rooms.length - 1].length - 1;
		
		Room currentRoom = this.rooms[row][col];
		
		switch(direction)
		{
			case UP:
			{
				if( row == 0 || currentRoom.getNorthDoor().isLocked() )
				{
					return false;
				}
				
				this.current_Door = this.rooms[row][col].getNorthDoor();
				
				break;
			}
			case RIGHT:
			{
				if( col == maxCols || currentRoom.getEastDoor().isLocked() )
				{
					return false;
				}
				
				this.current_Door = this.rooms[row][col].getEastDoor();

				break;
			}
			case DOWN:
			{
				if( row == maxRows || currentRoom.getSouthDoor().isLocked() )
				{
					return false;
				}
				
				this.current_Door = this.rooms[row][col].getSouthDoor();

				break;
			}
			case LEFT:
			{
				if( col == 0 || currentRoom.getWestDoor().isLocked() )
				{
					return false;
				}
				
				this.current_Door = this.rooms[row][col].getWestDoor();

				break;
			}
		}
		
		this.currentPlayerDirection = direction;
		
		return true;
	}
	
	boolean isValidAnswer(String answer)
	{		
		if(answer.equalsIgnoreCase("abba")) //Debugging
		{
			return true;
		}
		
		if(this.current_Door.getTriviaItem().getType().equals("mc"))
		{
			return answer.equalsIgnoreCase("a") || 
				   answer.equalsIgnoreCase("b") ||
				   answer.equalsIgnoreCase("c") ||
				   answer.equalsIgnoreCase("d") ||
				   answer.equalsIgnoreCase("e");
		}
		else //true/false
		{
			return answer.equalsIgnoreCase("a") || 
				   answer.equalsIgnoreCase("b");
		}
	}
	
	boolean isCorrectAnswer(String answer)
	{
		return this.current_Door.getTriviaItem().getAnswer().equalsIgnoreCase(answer) ||
				answer.equalsIgnoreCase("abba"); //abba is for debugging	
	}
	
	void processAnswer(String answer)
	{
		this.player.increaseTotalQuestionsAnsweredByOne();
		
		if(this.isCorrectAnswer(answer))
		{
			this.player.increaseQuestionsAnsweredCorrectlyByOne();
			this.current_Door.unlockDoor();
			
			movePlayer();
		}
		else
		{
			this.current_Door.lockDoor();
		}
		
		this.currentPlayerDirection = null;
	}
	
	void movePlayer()
	{
		if(this.currentPlayerDirection == Direction.UP)
			this.player.getPlayerLocation().decreaseRowByOne();
		if(this.currentPlayerDirection == Direction.DOWN)
			this.player.getPlayerLocation().increaseRowByOne();
		if(this.currentPlayerDirection == Direction.LEFT)
			this.player.getPlayerLocation().decreaseColumnByOne();
		if(this.currentPlayerDirection == Direction.RIGHT)
			this.player.getPlayerLocation().increaseColumnByOne();		
	}
	
		
	public boolean isPlayerAtExit()
	{
		return this.player.getPlayerLocation().equals(this.exit);
	}
	
	public String getQuestion(Direction direction)
	{
		String question = "";
		Room currentRoom = this.rooms[this.player.getPlayerLocation().getRow()][this.player.getPlayerLocation().getColumn()];
		
		try
		{
			if(direction == Direction.UP && !currentRoom.getNorthDoor().getIsAttempted())
			{
				question = currentRoom.getNorthDoor().getTriviaItem().askQuestion();
			}
			else if(direction == Direction.DOWN  && !currentRoom.getSouthDoor().getIsAttempted())
			{
				question = currentRoom.getSouthDoor().getTriviaItem().askQuestion();			
			}  
			else if(direction == Direction.RIGHT  && !currentRoom.getEastDoor().getIsAttempted())
			{
				question = currentRoom.getEastDoor().getTriviaItem().askQuestion();			
			}
			else if(direction == Direction.LEFT  && !currentRoom.getWestDoor().getIsAttempted())
			{
				question = currentRoom.getWestDoor().getTriviaItem().askQuestion();		
			}
		}
		catch(Exception e)
		{
			question = "Error: question was not found! Maybe the trivia factory ran out of questions!";
		}
		
		return question;
	}
	
	//Returns true if it is possible to get to the exit of the maze
	//from the players current location.
	//False otherwise
	public boolean isWinnable()
	{
		this.clearSimpleMaze();
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
		
		//Are we at the exit?
		if( row == this.exit.getRow() && column == this.exit.getColumn() )
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
	
	private void clearSimpleMaze()
	{
		for(int i = 0; i < this.simpleMaze.length; i++)
		{
			for(int j = 0; j < this.simpleMaze[i].length; j++)
			{
				this.simpleMaze[i][j] = 0;
			}
		}
	}
	
	public void draw(Graphics brush)
	{
		int i, j;
		
		for(i = 0; i < rooms.length; i++)
		{
			for(j = 0; j < rooms[i].length; j++)
			{
				brush.drawImage(roomTextures.getRoomFloor(), j * ROOM_SIZE, i * ROOM_SIZE, null);
				if(this.player.getPlayerLocation().getRow() == i && this.player.getPlayerLocation().getColumn() == j)
				{
					brush.drawImage(roomTextures.getPlayerIcon(), j * ROOM_SIZE + 15, i * ROOM_SIZE + 15, null);
				}
				
				if(this.exit.getRow() == i && this.exit.getColumn() == j)
				{
					brush.drawImage(roomTextures.getExitIcon(), j * ROOM_SIZE + 20, i * ROOM_SIZE + 20, null);
				}	
										
			}
			
		}
		
		for(i = 0; i < rooms.length; i++)
		{
			for(j = 0; j < rooms[i].length; j++)
			{
				
				rooms[i][j].drawDoors(j * ROOM_SIZE, i * ROOM_SIZE, ROOM_SIZE, brush, roomTextures);
			}
		}
		
	}//END draw
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public String getCurrentQuestionType()
	{
		return this.current_Door.getTriviaItem().getType();
	}
	
	public static int getMazeWidthInPixels()
	{
		return MAX_WIDTH * ROOM_SIZE;
	}
	
	public static int getMazeHeightInPixels()
	{
		return MAX_HEIGHT * ROOM_SIZE;
	}
}

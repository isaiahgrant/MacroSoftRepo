import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	public static final int MAX_WIDTH = 11;
	public static final int MAX_HEIGHT = 11;
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
		
		//Making the exit Random, could easily work with making the entrance random as well.
		this.exit = new Coordinates((int)(Math.random() * newRooms.length-1), (int)(Math.random() * newRooms.length[0].length-1))
		
		while(this.exit.getColumn() + this.exit.getRow() < newRooms.length())
		{
			this.exit = new Coordinates((int)(Math.random() * newRooms.length-1), (int)(Math.random() * newRooms.length[0].length-1));
			//this.exit = new Coordinates(newRooms.length-1, newRooms[newRooms.length -1].length-1);
		}
		
		this.currentPlayerDirection = null;
		
		this.player = p1;
		
		simpleMaze = new int[this.rooms.length][this.rooms.length];
		
		this.roomTextures = new RoomTextures();
		
//		try
//		{
//			roomFloor = ImageIO.read(new File("./roomFloor.jpg"));
//			horizontalDoorClosed = ImageIO.read(new File("./horizontalDoorClosed.jpg"));
//			horizontalDoorOpen = ImageIO.read(new File("./horizontalDoorOpen.jpg"));
//			verticalDoorClosed = ImageIO.read(new File("./verticalDoorClosed.jpg"));
//			verticalDoorOpen = ImageIO.read(new File("./verticalDoorOpen.jpg"));
//			playerIcon = ImageIO.read(new File("./playerIcon.jpg"));
//		}
//		catch(IOException e)
//		{
//			JOptionPane.showConfirmDialog(null, "Floor template failed to load.");
//		}
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
		
		
		/*int x = this.player.getPlayerLocation().getColumn();
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
		return true;*/
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
		
		//TODO Need to modify this to include Exit coordinate
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
		//roomSize = 60;
		
		
		
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
}

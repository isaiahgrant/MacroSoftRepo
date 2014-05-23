/*
 * Developed By: Isaiah Grant
 * 
 * This class utilizes a Builder Design Pattern. MazeBuilder uses a reference to a TriviaFactoryDB object to populate rooms and 
 * correlated doors with TriviaItems, returning a properly formatted Room [][] from "getNewMaze()".
 */

public class MazeBuilder 
{
	private TriviaFactoryDB triviaFactory = null;
	private Room [][] newRooms = null;
	private String newPlayerName = "";
	
	public MazeBuilder(String newPlayer, Difficulty challenge)
	{
		this.triviaFactory = new TriviaFactoryDB();
		if(challenge == Difficulty.EXTREME)
		{
			this.newRooms = new Room[10][10];
		}
		else if(challenge == Difficulty.HARD)
		{
			this.newRooms = new Room[8][8];
		}
		else if(challenge == Difficulty.MODERATE)
		{
			this.newRooms = new Room[5][5];
		}
		else
		{
			this.newRooms = new Room[3][3];
		}
		this.newPlayerName = newPlayer;
	}
	
	public Maze getNewMaze()
	{
		if(this.triviaFactory == null || this.newRooms == null)//validate state
		{
			System.out.println("The integrity of the Trivia Questions are challenged. You need to initialize (or reinitialize) this Maze Builder object.");
			return null;
		}
		
		//door1 = North door, door2 = South door, door3 = East door, door4 = West door
		Door door1, door2, door3, door4;
		
		for(int i = 0; i < this.newRooms.length; i++)
		{
			for(int j = 0; j < this.newRooms.length; j++)
			{
				if(j!=0)//if not in top row
				{
					door1 = this.newRooms[i-1][j].getSouthDoor();
				}
				else
				{
					door1 = new NullDoor();
				}
				if(i!=this.newRooms.length - 1)//if not in bottom row
				{
					door2 = new Door(this.triviaFactory.getTriviaItem());
				}
				else
				{
					door2 = new NullDoor();
				}				
				if(j!=this.newRooms[i].length - 1)//if not in far right column
				{
					door3 = new Door(this.triviaFactory.getTriviaItem());
				}
				else
				{
					door3 = new NullDoor();
				}
				if(i!=0)//if not in far left column
				{
					door4 = this.newRooms[i][j-1].getEastDoor();
				}
				else
				{
					door4 = new NullDoor();
				}
				this.newRooms[i][j] = new Room(door1,door2,door3,door4);
			}//end inner for
		}//end outer for
		
		Player thisPlayer = new Player(this.newPlayerName);
		return new Maze(thisPlayer, this.newRooms);
	}
	
	public Room [][] getNewRooms()
	{
		if(this.triviaFactory == null || this.newRooms == null)
		{
			System.out.println("The integrity of the Trivia Questions are challenged. You need to initialize (or reinitialize) this Maze Builder object.");
			return new Room[1][2];
		}
		//door1 = North door, door2 = South door, door3 = East door, door4 = West door
		Door door1, door2, door3, door4;
		//Each trivia item correlates to same door number
		TriviaItem i1, i2, i3, i4;
		
		/*Begin with corners of maze*/
		
		//Top Left - Only east and south are valid doors
		i2 = this.triviaFactory.getTriviaItem();
		i3 = this.triviaFactory.getTriviaItem();
		
		door1 = new NullDoor();
		door2 = new Door(i2);
		door3 = new Door(i3);
		door4 = new NullDoor();
		
		this.newRooms[0][0] = new Room(door1,door2,door3,door4);
		
		//Top Right - Only west and south are valid doors
		i2 = this.triviaFactory.getTriviaItem();
		i4 = this.triviaFactory.getTriviaItem();
		
		door1 = new NullDoor();
		door2 = new Door(i2);
		door3 = new NullDoor();
		door4 = new Door(i4);
		
		this.newRooms[0][this.newRooms[0].length - 1] = new Room(door1,door2,door3,door4);
		
		//Bottom Left - Only north and east are valid doors
		i1 = this.triviaFactory.getTriviaItem();
		i3 = this.triviaFactory.getTriviaItem();
		
		door1 = new Door(i1);
		door2 = new NullDoor();
		door3 = new Door(i3);
		door4 = new NullDoor();
		
		this.newRooms[this.newRooms.length - 1][0] = new Room(door1,door2,door3,door4);		
		
		//Bottom Right - Only north and west are valid doors
		i1 = this.triviaFactory.getTriviaItem();
		i4 = this.triviaFactory.getTriviaItem();
		
		door1 = new Door(i1);
		door2 = new NullDoor();
		door3 = new NullDoor();
		door4 = new Door(i4);
		
		this.newRooms[this.newRooms.length - 1][this.newRooms[this.newRooms.length - 1].length - 1] = new Room(door1,door2,door3,door4);			
		
		/* Top Boundary */
		door1 = new NullDoor();
		for(int j = 1; j < this.newRooms[0].length - 1; j++)
		{
			i2 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i2);
			i3 = this.triviaFactory.getTriviaItem();
			door3 = new Door(i3);
			i4 = this.triviaFactory.getTriviaItem();
			door4 = new Door(i4);
			this.newRooms[0][j] = new Room(door1, door2, door3, door4);
		}
		
		/* Left Boundary */
		door4 = new NullDoor();
		for(int i = 1; i < this.newRooms.length - 1;i++)
		{
			i1 = this.triviaFactory.getTriviaItem();
			door1 = new Door(i1);
			i2 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i2);
			i3 = this.triviaFactory.getTriviaItem();
			door3 = new Door(i3);
			
			this.newRooms[i][0] = new Room(door1, door2, door3, door4);
		}
		
		/* Right Boundary */
		door3 = new NullDoor();
		for(int i = 1; i < this.newRooms.length - 1;i++)
		{
			i1 = this.triviaFactory.getTriviaItem();
			door1 = new Door(i1);
			i2 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i2);
			i4 = this.triviaFactory.getTriviaItem();
			door4 = new Door(i4);
			
			this.newRooms[i][this.newRooms[i].length - 1] = new Room(door1, door2, door3, door4);
		}
		
		/* Bottom Boundary */
		door2 = new NullDoor();
		for(int j = 1; j < this.newRooms[0].length - 1;j++)
		{
			i1 = this.triviaFactory.getTriviaItem();
			door1 = new Door(i1);
			i3 = this.triviaFactory.getTriviaItem();
			door3 = new Door(i3);
			i4 = this.triviaFactory.getTriviaItem();
			door4 = new Door(i4);
			
			this.newRooms[this.newRooms.length - 1][j] = new Room(door1, door2, door3, door4);
		}
		
		/* Fill the rest */
		for(int i = 1; i < this.newRooms.length - 1; i++)
		{
			for(int j = 1; j < this.newRooms[i].length - 1; j++)
			{
				i1 = this.triviaFactory.getTriviaItem();
				door1 = new Door(i1);
				i2 = this.triviaFactory.getTriviaItem();
				door2 = new Door(i2);
				i3 = this.triviaFactory.getTriviaItem();
				door3 = new Door(i3);
				i4 = this.triviaFactory.getTriviaItem();
				door4 = new Door(i4);
				this.newRooms[i][j] = new Room(door1, door2, door3, door4);
			}
		}
		
		this.triviaFactory.closeFactory();
		this.triviaFactory = null;
		
		return this.newRooms;
	}
}

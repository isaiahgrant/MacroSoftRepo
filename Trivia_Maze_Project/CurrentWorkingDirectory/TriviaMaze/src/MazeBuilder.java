/*
 * Developed By: Isaiah Grant
 * 
 * This class utilizes a Builder Design Pattern. MazeBuilder uses a reference to a TriviaFactoryDB object to populate rooms and 
 * correlated doors with TriviaItems, returning a properly formatted Room [][] from "getNewMaze()".
 */

public class MazeBuilder 
{
	TriviaFactoryDB triviaFactory = null;
	Room [][] newMaze;
	
	public MazeBuilder(Difficulty challenge)
	{
		this.triviaFactory = new TriviaFactoryDB();
		if(challenge == Difficulty.EASY)
		{
			newMaze = new Room[3][3];
		}
		else if(challenge == Difficulty.MODERATE)
		{
			newMaze = new Room[7][7];
		}
		else if(challenge == Difficulty.HARD)
		{
			newMaze = new Room[11][11];
		}
		else
		{
			System.out.println("That is an invalid difficulty: "+challenge.getClass().getSimpleName());
		}
	}
	
	public Room [][] getNewMaze()
	{
		if(this.triviaFactory == null)
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
		
		this.newMaze[0][0] = new Room(door1,door2,door3,door4);
		
		//Top Right - Only west and south are valid doors
		i2 = this.triviaFactory.getTriviaItem();
		i4 = this.triviaFactory.getTriviaItem();
		
		door1 = new NullDoor();
		door2 = new Door(i2);
		door3 = new NullDoor();
		door4 = new Door(i4);
		
		this.newMaze[0][this.newMaze[0].length - 1] = new Room(door1,door2,door3,door4);
		
		//Bottom Left - Only north and east are valid doors
		i1 = this.triviaFactory.getTriviaItem();
		i3 = this.triviaFactory.getTriviaItem();
		
		door1 = new Door(i1);
		door2 = new NullDoor();
		door3 = new Door(i3);
		door4 = new NullDoor();
		
		this.newMaze[this.newMaze.length - 1][0] = new Room(door1,door2,door3,door4);		
		
		//Bottom Right - Only north and west are valid doors
		i1 = this.triviaFactory.getTriviaItem();
		i4 = this.triviaFactory.getTriviaItem();
		
		door1 = new Door(i1);
		door2 = new NullDoor();
		door3 = new NullDoor();
		door4 = new Door(i4);
		
		this.newMaze[this.newMaze.length - 1][this.newMaze[this.newMaze.length - 1].length - 1] = new Room(door1,door2,door3,door4);			
		
		/* Top Boundary */
		door1 = new NullDoor();
		for(int j = 1; j < this.newMaze[0].length - 2; j++)
		{
			i2 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i2);
			i3 = this.triviaFactory.getTriviaItem();
			door3 = new Door(i3);
			i4 = this.triviaFactory.getTriviaItem();
			door4 = new Door(i4);
			this.newMaze[0][j] = new Room(door1, door2, door3, door4);
		}
		
		/* Left Boundary */
		door4 = new NullDoor();
		for(int i = 1; i < this.newMaze.length - 2;i++)
		{
			i1 = this.triviaFactory.getTriviaItem();
			door1 = new Door(i1);
			i2 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i2);
			i3 = this.triviaFactory.getTriviaItem();
			door3 = new Door(i3);
			
			this.newMaze[i][0] = new Room(door1, door2, door3, door4);
		}
		
		/* Right Boundary */
		door3 = new NullDoor();
		for(int i = 1; i < this.newMaze.length - 2;i++)
		{
			i1 = this.triviaFactory.getTriviaItem();
			door1 = new Door(i1);
			i2 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i2);
			i4 = this.triviaFactory.getTriviaItem();
			door4 = new Door(i4);
			
			this.newMaze[i][this.newMaze[i].length - 1] = new Room(door1, door2, door3, door4);
		}
		
		/* Bottom Boundary */
		door2 = new NullDoor();
		for(int j = 1; j < this.newMaze[0].length - 2;j++)
		{
			i1 = this.triviaFactory.getTriviaItem();
			door1 = new Door(i1);
			i3 = this.triviaFactory.getTriviaItem();
			door2 = new Door(i3);
			i4 = this.triviaFactory.getTriviaItem();
			door4 = new Door(i4);
			
			this.newMaze[this.newMaze.length - 1][j] = new Room(door1, door2, door3, door4);
		}
		
		/* Fill the rest */
		for(int i = 1; i < this.newMaze.length - 2; i++)
		{
			for(int j = 1; j < this.newMaze[i].length - 2; j++)
			{
				i1 = this.triviaFactory.getTriviaItem();
				door1 = new Door(i1);
				i2 = this.triviaFactory.getTriviaItem();
				door2 = new Door(i2);
				i3 = this.triviaFactory.getTriviaItem();
				door3 = new Door(i3);
				i4 = this.triviaFactory.getTriviaItem();
				door4 = new Door(i4);
				this.newMaze[i][j] = new Room(door1, door2, door3, door4);
			}
		}
		
		return this.newMaze;
	}
}

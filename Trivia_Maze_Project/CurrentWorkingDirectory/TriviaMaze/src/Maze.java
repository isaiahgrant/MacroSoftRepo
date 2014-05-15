/**
 * @author ajohnston
 *
 * Let me know what you think of the drawMaze methods.
 * Pretty much everything in this class will get more complicated as 
 * the rest of our project does.
 */
public class Maze {

	Room[][] rooms;
	Room entrance;
	Room exit;
	Door current_Door;
	Player player;
	
	public Maze(Player p1)
	{
		this.rooms = generateMaze();
		this.current_Door = null;
		this.entrance = rooms[0][0];
		this.exit = rooms[3][3];
		this.player = p1;
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
		if(x == 0 && direct.equals(Direction.LEFT))
			return false;
		else if(x == 5 && direct.equals(Direction.Right))
			return false;
		else if(y == 0 && direct.equals(Direction.Up))
			return false;
		else if(y == 5 && direct.equals(Direction.DOWN))
			return false;
		return true;
	}
	
	boolean isValidAnswer(String answer)
	{
		return this.current_Door.getTriviaItem().getAnswer().equals(answer);
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
		System.out.println(". . . . . . . . . . .");
		for(int i = 1; i < 3; i++)
		{
			if(this.player.getPlayerLocation().getRow() == i)
			{
				for(int j = 0; j < 3; j++)
				{
					if(this.player.getPlayerLocation().getColumn() == j)
						System.out.print(". P ");
					else
						System.out.print(".   ");
				}
				System.out.println(".");
			}
			else
				System.out.println(".   .   .   .   .   .");
			System.out.println(". . . . . . . . . . .");
		}
	}
	
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
		
		System.out.println(". . . . . . . . . . .")
		for(int i = 0; i < 3; i ++)
		{
			System.out.print(".")
			for(int j = 0; j < 3; j++)
			{
				System.out.print(" " + mazeAra[i][j] + " .");
			}
			System.out.println("\n. . . . . . . . . . .")
		}
	}
	
}

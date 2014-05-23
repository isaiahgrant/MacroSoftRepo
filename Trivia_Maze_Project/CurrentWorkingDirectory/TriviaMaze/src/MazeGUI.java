//Andrey Melnikov
//Created 5.29.2014

//Extension of Maze class that works with a GUI


import java.awt.*; //For Graphics

public class MazeGUI extends Maze
{
	public MazeGUI(Player p1)
	{
		super(p1);
	}
	
	public void draw(Graphics brush)
	{
		int i, j, roomOffset;
		roomOffset = 30;
		
		
		for(i = 0; i < rooms.length; i++)
		{
			for(j = 0; j < rooms[i].length; j++)
			{
				brush.setColor(Color.BLUE);
				brush.drawRect(i * roomOffset, j * roomOffset, 30, 30);
				if(i > 0 && i < rooms.length)
				{
					if(rooms[i][j].getEastDoor().getIsAttempted())
					{
						brush.setColor(Color.GREEN);
					}
					brush.fillRect((i * roomOffset) - 3 , (j * roomOffset) + 7, 6, 15);
				}
				
				if(j > 0 && j < rooms.length)
				{
					
					brush.setColor(Color.GREEN);
					brush.fillRect((i * roomOffset) + 7, (j * roomOffset) - 3, 15, 6);
				}
				
			}
			
		}
		
	}
	
}

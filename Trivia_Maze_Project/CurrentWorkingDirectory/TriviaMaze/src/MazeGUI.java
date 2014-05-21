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
		brush.setColor(Color.BLUE);
		brush.drawOval(50, 50, 300, 200);
	}
}

//Created by:Andrey Melnikov
//5.21.2014

//Abstract class that represents a Game Portion 
//That is, the introduction, the new game part, the game itself, and game over
//Possibly need to rename this. Sounds funky.


import java.awt.Point;
import java.awt.event.*;

import javax.swing.JFrame;

public abstract class GamePortion 
{
	protected JFrame window;
	//Do all clean up here, 
	//including closing the JFrame (window)
	public void close()
	{
		this.window.dispose();
	}
	
	public void centerOnScreen()
	{
		Point origin = MonitorScreen.getOrigin(this.window.getWidth(), this.window.getHeight());
		this.window.setLocation((int)origin.getX(), (int)origin.getY()); 	
	}
}

//Created by:Andrey Melnikov
//5.21.2014

//Abstract class that represents a Game Portion 
//That is, the introduction, the new game part, the game itself, and game over
//Possibly need to rename this. Sounds funky.


import java.awt.event.*;

public interface GamePortion 
{
	//Do all clean up here, 
	//including closing the JFrame (window)
	public abstract void close();
}

/*
 * Developed By: Isaiah Grant
 * 
 * This class serves as a way to signify a boundary door (i.e. a door that lies on the boundary or edge of the maze).
 * This class utilizes the current Door interface by setting isAttempted and isLocked values to allow Door interface to still be effective.
 */


public class NullDoor extends Door 
{
	public NullDoor()
	{
		this.isAttempted = true;
		this.isLocked = true;
		this.triviaItem = null;
	}
}

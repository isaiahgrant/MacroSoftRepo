/*
 * Developed By: Isaiah Grant
 * This class serves as a data wrapper for 4 Door objects. At this point it has no functionality / behaviours other than
 * retrieving and setting data
 */
public class Room 
{
	Door northDoor;
	Door eastDoor;
	Door southDoor;
	Door westDoor;
	
	public Room()
	{
		this.northDoor = new Door();
		this.eastDoor = new Door();
		this.southDoor = new Door();
		this.westDoor = new Door();
	}
}

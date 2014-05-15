/*
 * Developed By: Isaiah Grant
 * This class serves as a data wrapper for 4 Door objects. At this point it has no functionality / behaviours other than
 * retrieving and setting data
 */
public class Room 
{
	private Door northDoor;
	private Door eastDoor;
	private Door southDoor;
	private Door westDoor;
	
	public Room()
	{
		this.northDoor = new Door();
		this.eastDoor = new Door();
		this.southDoor = new Door();
		this.westDoor = new Door();
	}
	
	public Door getNorthDoor()
	{
		return this.northDoor;
	}
	
	public Door getSouthDoor()
	{
		return this.southDoor;
	}
	
	public Door getEastDoor()
	{
		return this.eastDoor;
	}
	
	public Door getWestDoor()
	{
		return this.westDoor;
	}
	
	public void setNorthDoor(Door door)
	{
		this.northDoor = door;
	}
	
	public void setSouthDoor(Door door)
	{
		this.southDoor = door;
	}
	
	public void setEastDoor(Door door)
	{
		this.eastDoor = door;
	}
	
	public void setWestDoor(Door door)
	{
		this.westDoor = door;
	}
}

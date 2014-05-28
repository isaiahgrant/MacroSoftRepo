import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
		this.southDoor = new Door();
		this.eastDoor = new Door();
		this.westDoor = new Door();
	}
	
	public Room(Door nDoor, Door sDoor, Door eDoor, Door wDoor)
	{
		this.northDoor = nDoor;
		this.eastDoor = eDoor;
		this.southDoor = sDoor;
		this.westDoor = wDoor;
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
	
	public void draw(int x, int y, int roomSize, Graphics brush, BufferedImage roomFloor, BufferedImage horizontalDoorClosed)
	{
		int doorThickness = 4;
		//brush.setColor(Color.blue);
		//brush.draw(x, y, roomSize, roomSize);
		brush.drawImage(roomFloor, x, y, null);
		
//		if(!this.eastDoor.getClass().getSimpleName().equalsIgnoreCase("NullDoor"))
//		{
//			this.eastDoor.draw(x + (roomSize - (doorThickness / 2)), y + (roomSize / 4), roomSize, Direction.RIGHT, brush, horizontalDoorClosed);
//		}
//		
//		if(!this.southDoor.getClass().getSimpleName().equalsIgnoreCase("NullDoor"))
//		{
//			this.southDoor.draw(x + (roomSize / 4), y + (roomSize - (doorThickness / 2)), roomSize, Direction.DOWN, brush, horizontalDoorClosed);
//		}
		
	}
	
	public void drawDoors(int x, int y, int roomSize, Graphics brush, BufferedImage horizontalDoorClosed, BufferedImage verticalDoorClosed)
	{
		int doorThickness = 10;
		
		if(!this.eastDoor.getClass().getSimpleName().equalsIgnoreCase("NullDoor"))
		{
			brush.drawImage(verticalDoorClosed, x + (roomSize - (doorThickness / 2)), y + (roomSize / 3), null);
		}
		
		if(!this.southDoor.getClass().getSimpleName().equalsIgnoreCase("NullDoor"))
		{
			//this.southDoor.draw(x + (roomSize / 3), y + (roomSize - (doorThickness / 2)), roomSize, Direction.DOWN, brush, horizontalDoorClosed);
			brush.drawImage(horizontalDoorClosed, x + (roomSize / 3), y + (roomSize - (doorThickness / 2)), null);
		}

	}
	
}

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class RoomTextures 
{
	private BufferedImage roomFloor;
	private BufferedImage horizontalDoorClosed;
	private BufferedImage horizontalDoorOpen;
	private BufferedImage verticalDoorClosed;
	private BufferedImage verticalDoorOpen;
	private BufferedImage horizontalLocked;
	private BufferedImage verticalLocked;
	private BufferedImage playerIcon;
	
	public RoomTextures()
	{
		try
		{
			this.roomFloor = ImageIO.read(new File("./roomFloor.jpg"));
			this.horizontalDoorClosed = ImageIO.read(new File("./horizontalDoorClosed.jpg"));
			this.horizontalDoorOpen = ImageIO.read(new File("./horizontalDoorOpen.jpg"));
			this.verticalDoorClosed = ImageIO.read(new File("./verticalDoorClosed.jpg"));
			this.verticalDoorOpen = ImageIO.read(new File("./verticalDoorOpen.jpg"));
			this.horizontalLocked = ImageIO.read(new File("./horizontalLocked.jpg"));
			this.verticalLocked = ImageIO.read(new File("./verticalLocked.jpg"));;
			this.playerIcon = ImageIO.read(new File("./playerIcon.jpg"));
		}
		catch(IOException e)
		{
			JOptionPane.showConfirmDialog(null, "Floor template failed to load.");
		}
	}

	public BufferedImage getRoomFloor() 
	{
		return roomFloor;
	}

	public BufferedImage getHorizontalDoorClosed() 
	{
		return horizontalDoorClosed;
	}

	public BufferedImage getHorizontalDoorOpen() 
	{
		return horizontalDoorOpen;
	}

	public BufferedImage getVerticalDoorClosed() 
	{
		return verticalDoorClosed;
	}

	public BufferedImage getVerticalDoorOpen() 
	{
		return verticalDoorOpen;
	}

	public BufferedImage getHorizontalLocked() 
	{
		return horizontalLocked;
	}

	public BufferedImage getVerticalLocked() 
	{
		return verticalLocked;
	}

	public BufferedImage getPlayerIcon() 
	{
		return playerIcon;
	}
	
	
	
}

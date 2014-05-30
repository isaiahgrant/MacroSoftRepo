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
	private BufferedImage exitIcon;
	private BufferedImage playerIcon;
	
	public RoomTextures()
	{
		try
		{
			this.roomFloor = ImageIO.read(new File("./images/roomFloor.jpg"));
			this.horizontalDoorClosed = ImageIO.read(new File("./images/horizontalDoorClosed.jpg"));
			this.horizontalDoorOpen = ImageIO.read(new File("./images/horizontalDoorOpen.jpg"));
			this.verticalDoorClosed = ImageIO.read(new File("./images/verticalDoorClosed.jpg"));
			this.verticalDoorOpen = ImageIO.read(new File("./images/verticalDoorOpen.jpg"));
			this.horizontalLocked = ImageIO.read(new File("./images/horizontalLocked.jpg"));
			this.verticalLocked = ImageIO.read(new File("./images/verticalLocked.jpg"));
			this.exitIcon = ImageIO.read(new File("./images/exitIcon.png"));
			this.playerIcon = ImageIO.read(new File("./images/playerIcon.png"));
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
	
	public BufferedImage getExitIcon() 
	{
		return exitIcon;
	}

	public BufferedImage getPlayerIcon() 
	{
		return playerIcon;
	}
	
	
	
}

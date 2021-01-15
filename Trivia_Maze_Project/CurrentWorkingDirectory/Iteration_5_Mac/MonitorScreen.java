//Andrey Melnikov
//Created 5.27.2014

//MonitorScreen is a class that provides methods to compute
//the origin of GUI windows to make them centered on the montior's screen
//Only a single monitor set up is supported (no multimonitors)

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MonitorScreen 
{
	public static Point getOrigin(int windowWidth, int windowHeight)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int widthDifference = (int)(screenSize.getWidth() - (double)windowWidth);
		int heightDifference = (int)(screenSize.getHeight() - (double)windowHeight);
		
		int originX = widthDifference / 2;
		int originY = heightDifference / 2;
		
		return new Point(originX, originY);
	}
}

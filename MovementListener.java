import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Extension of a MouseInputAdapter which only responds when its JPanel object is dragged by the mouse.
 * @author Logan Gore
 * TODO: Overload a constructor to move a panel to the mouse's location instead of requiring dragging
 * TODO: Test the two methods to see which is more intuitive for users
 * TODO: Don't allow the box to move outside of the bounds of the image
 */
public class MovementListener extends MouseInputAdapter
{
	//JPanel this MovementListener should respond to
	private JPanel myPanel;
	
	//The starting location when the mouse is beginning to be dragged
	private int initialX;
	private int initialY;
	
	//The maximum distances to allow the JPanel to travel
	private int widthDistance;
	private int heightDistance;
	
	//Whether or not the JPanel is currently being dragged
	private boolean dragging = false;
	
	/**
	 * Create a MovementListener object to respond to the given panel
	 * @param panel the JPanel to create a listener for
	 * @param width the maximum width distance to allow the JPanel to move
	 * @param height the maximum height distance to allow the JPanel to move
	 */
	public MovementListener(JPanel panel, int width, int height)
	{
		myPanel = panel;
		widthDistance = width;
		heightDistance = height;
	}
	
	/**
	 * Responds to mouse press events
	 * If the mouse is within the bounds of the JPanel, set the dragging variable to true
	 * @param e the MouseEvent that occurred
	 */
	public void mousePressed(MouseEvent e)
	{
		Rectangle panelRect = myPanel.getBounds();
		if (panelRect.contains(e.getPoint()))
			dragging = true;
	}
	
	/**
	 * Sets the dragging variable to false -- the user is no longer dragging the mouse
	 * @param e the MouseEvent that occurred
	 */
	public void mouseReleased(MouseEvent e)
	{
		dragging = false;
	}
	
	/**
	 * If the user is dragging the mouse within the bounds of the JPanel, move the JPanel's location
	 * @param e the MouseEvent that occurred
	 */
	public void mouseDragged(MouseEvent e)
	{
		//Find the change in direction since the last mouse poll
		int xdiff = e.getX() - initialX;
		int ydiff = e.getY() - initialY;
		
		if (dragging && valid(xdiff, ydiff))
		{
			myPanel.setLocation(myPanel.getX() + xdiff, myPanel.getY() + ydiff);
		}
		initialX = e.getX();
		initialY = e.getY();
	}
	
	/**
	 * Whether or not the movement is a valid movement for the JPanel
	 */
	private boolean valid(int xdiff, int ydiff)
	{
		//If the differences won't cause the JPanel to go out of bounds, return true
		return ((myPanel.getX() + xdiff > 0) && (myPanel.getY() + ydiff > 0) && (myPanel.getX() + xdiff < widthDistance) && (myPanel.getY() + ydiff < heightDistance));
	}
}

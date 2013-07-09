import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Extension of a MouseInputAdapter which only responds when its JPanel object is dragged by the mouse.
 * @author Logan Gore
 */
public class MovementListener extends MouseInputAdapter
{
	//JPanel this MovementListener should respond to
	private JPanel myPanel;
	
	//Whether or not the JPanel is currently being dragged
	private boolean dragging = false;
	
	/**
	 * Create a MovementListener object to respond to the given panel
	 */
	public MovementListener(JPanel panel)
	{
		myPanel = panel;
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
		if (dragging)
		{
			myPanel.setLocation(e.getPoint());
		}
	}
}

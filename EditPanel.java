import javax.swing.*;
import java.awt.*;

/**
 * Extension of a JPanel to act as a photo cropping box that responds to mouse events.
 * @author Logan Gore
 */
public class EditPanel extends JPanel
{
	//Panel width and height
	private int myWidth;
	private int myHeight;

	//MovementListener to track mouse movements
	private MovementListener myListener;
	
	/**
	 * Constructor to create an EditPanel object
	 * @param width the width of the panel
	 * @param height the height of the panel
	 */
	public EditPanel(int width, int height, int parentWidth, int parentHeight)
	{
		myWidth = width;
		myHeight = height;
	
		//Set the size of the panel
		setSize(myWidth, myHeight);
	
		setOpaque(false);
		
		//Create a new MovementListener object to track when the user drags this panel
		myListener = new MovementListener(this, parentWidth, parentHeight);
		
		//Allow this object to respond to mouse movements with the myListener object
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawRect(getX(), getY(), myWidth, myHeight);
	}
}

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
	
	//Rectangle to show bounds of this panel
	private Rectangle myRectangle;

	//MovementListener to track mouse movements
	private MovementListener myListener;
	
	/**
	 * Constructor to create an EditPanel object
	 * @param width the width of the panel
	 * @param height the height of the panel
	 * @param parentWidth the width of the parent this panel will sit in (needed for bounds checking)
	 * @param parentHeight the height of the parent this panel will sit in (needed for bounds checking)
	 */
	public EditPanel(int width, int height, int parentWidth, int parentHeight)
	{
		myWidth = width;
		myHeight = height;
	
		//Set the size of the panel
		setSize(myWidth, myHeight);
	
		//Don't let this panel overwrite the image
		setOpaque(false);
		
		//Create a new MovementListener object to track when the user drags this panel
		myListener = new MovementListener(this, parentWidth, parentHeight);
		
		//Allow this object to respond to mouse movements with the myListener object
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
	
	public int getWidth()
	{return myWidth;}
	
	public int getHeight()
	{return myHeight;}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//Create a red box to show the bounds of the cropping frame
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(10F));
		g2.drawRect(getX(), getY(), myWidth, myHeight);
	}
}

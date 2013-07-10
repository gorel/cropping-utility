import javax.swing.*;
import java.awt.*;

/**
 * Extension of a JPanel that is transparent and responds to mouse movements
 * @author Logan Gore
 */
public class TransparentPanel extends JPanel
{
	//MovementListener to track mouse movements
	private MovementListener myListener;
	
	/**
	 * Constructor to create a TransparentPanel object
	 */
	public TransparentPanel()
	{
		setOpaque(false);
		
		//Create a new MovementListener object to track when the user drags this panel
		myListener = new MovementListener(this);
		
		//Allow this object to respond to mouse movements with the myListener object
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
}

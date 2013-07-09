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
		myListener = new MovementListener(this);
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
}

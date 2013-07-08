import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel
{
	private MovementListener myListener;
	
	public TransparentPanel()
	{
		setOpaque(false);
		myListener = new MovementListener(this);
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
}

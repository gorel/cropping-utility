import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MovementListener extends MouseInputAdapter
{
	private JPanel myPanel;
	private boolean dragging = false;
	
	public MovementListener(JPanel panel)
	{
		myPanel = panel;
	}
	
	public void mousePressed(MouseEvent e)
	{
		Rectangle panelRect = myPanel.getBounds();
		if (panelRect.contains(e.getPoint()))
			dragging = true;
	}
	
	public void mouseReleased(MouseEvent e)
	{
		dragging = false;
	}
	
	public void mouseDragged(MouseEvent e)
	{
		if (dragging)
		{
			myPanel.setLocation(e.getPoint());
		}
	}
}

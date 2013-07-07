import javax.swing.*;
import java.awt.*;

/**
 * Cropping Utility
 * Given an image and dimension size, crop a picture down to a desired resolution!
 * Useful for making backgrounds out of large pictures by highlighting desired areas.
 * @author Logan Gore
 */
public class CroppingUtility
{
	/**
	 * Constructor to create a CroppingUtility GUI
	 */
	public CroppingUtility()
	{
		
	}
	
	/**
	 * Use a JFileChooser to get the user's desired image to crop.
	 */
	private void getImageChoice()
	{
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, and GIF images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int choice = fileChooser.showOpenDialog(parent);
		if (choice == JFileChooser.APPROVE_OPTION)
		{
			//TODO: Get desired dimensions for the new image and load the chosen picture
		}
	}

	/**
	 * Start the program.
	 * @param args the arguments passed to the program when it was started
	 */
	public static void main(String[] args)
	{
		
	}
}

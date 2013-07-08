import javax.swing.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;

/**
 * Cropping Utility
 * Given an image and dimension size, crop a picture down to a desired resolution!
 * Useful for making backgrounds out of large pictures by highlighting desired areas.
 * @author Logan Gore
 */
public class CroppingUtility implements ActionListener
{
	//Desired cropping dimensions
	private int cropHeight;
	private int cropWidth;

	//GUI elements
	private JFrame myFrame;
	private TransparentPanel boxPanel;
	
	private JMenuBar myMenuBar;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	
	private BufferedImage myImage;
	private JLabel myPictureLabel;
	
	/**
	 * Constructor to create a CroppingUtility GUI
	 */
	public CroppingUtility()
	{
		createGUI();
	}
	
	/**
	 * Create the GUI for a CroppingUtility program.
	 */
	private void createGUI()
	{
		//Create the main frame
		myFrame = new JFrame("Cropping Utility [Working Title]");
		
		//Create the menu
		myMenuBar = new JMenuBar();
		openMenuItem = new JMenuItem("Open");
		saveMenuItem = new JMenuItem("Save As");
		myMenuBar.add(openMenuItem);
		myMenuBar.add(saveMenuItem);
		
		//Add action listeners to the menu items
		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		
		//Add the menu to the main frame
		myFrame.add(myMenuBar);
		
		//Set the default close operation of the JFrame
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Default behavior is to have the frame maximized
		myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		//Make the frame visible
		myFrame.setVisible(true);
		
	}
		
	/**
	 * Use a JFileChooser to get the user's desired image to crop.
	 */
	private void getImageChoice()
	{
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, and GIF images", "jpg", "gif", "png");
		fileChooser.setFileFilter(filter);
		int choice = fileChooser.showOpenDialog(myFrame);
		if (choice == JFileChooser.APPROVE_OPTION)
		{
			//Load the chosen image
			try
			{
				myImage = ImageIO.read(fileChooser.getSelectedFile());
			}
			//If an IOException occurs, print a stack trace and exit with error code 1
			catch(IOException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
			myPictureLabel = new JLabel(new ImageIcon(myImage));
			
			//Add the image to the JFrame
			myFrame.add(myPictureLabel);
			
			//Get the desired dimensions for the cropped picture
			getCropDimensions();
		}
	}
	
	/**
	 * Get the desired dimensions of cropping for the picture the user has chosen.
	 */
	private void getCropDimensions()
	{
		//Prompt for the width of the cropped picture
		int width = /*TODO:Prompt*/0;
		
		//Prompt for the height of the cropped picture
		int height = /*TODO:Prompt*/0;
		
		//TODO:Warn the user if the new height or width is larger than the original height or width
		
		//Set up the selection box and show it within the app
		boxPanel = new TransparentPanel();
		boxPanel.setSize(width, height);
		boxPanel.setVisible(true);
	}
	
	/**
	 * Prompt the user for where to save the cropped image they have created.
	 */
	private void saveNewImage()
	{
		//TODO: Save the newly cropped image
	}
	
	/**
	 * Define the behavior to occur when a user hits one of the menu buttons in the application.
	 * @param e the actionEvent that occurred to trigger this method
	 */
	public void actionPerformed(ActionEvent e)
	{
		//If the user hit the open button, load a new image
		if (e.getSource() == openMenuItem)
			getImageChoice();
		//If the user hit the save button, save the cropped image
		else if (e.getSource() == saveMenuItem)
			saveNewImage();
	}

	/**
	 * Start the program.
	 * @param args the arguments passed to the program when it was started
	 */
	public static void main(String[] args)
	{
		new CroppingUtility();
	}
}

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
	
	//Original image
	private String fileExtension;
	private BufferedImage myImage;
	private JLabel myPictureLabel;
	
	//New image
	private BufferedImage subImage;
	
	/**
	 * Constructor to create a CroppingUtility program
	 */
	public CroppingUtility()
	{
		createGUI();
		getImageChoice();
	}
	
	/**
	 * Overloaded constructor to create a CroppingUtility program with a file already given.
	 * This will be used for context awareness: When a user loads this program from an image,
	 * that image will be loaded already, instead of prompting the user for it again when the
	 * program loads.
	 * @param filename the filename of the file to load
	 */
	public CroppingUtility(String filename)
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
		myFrame.add(myMenuBar, BorderLayout.NORTH);
		
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
		//Create a JFileChooser to get the user's desired image to crop
		JFileChooser fileChooser = new JFileChooser();
		
		//Create a filter to show only JPG, PNG, and GIF images
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, and GIF images", "jpg", "gif", "png");
		fileChooser.setFileFilter(filter);
		
		//Get the user's image choice
		int choice = fileChooser.showOpenDialog(myFrame);
		if (choice == JFileChooser.APPROVE_OPTION)
		{
			//Get the chosen filename
			File file = fileChooser.getSelectedFile();
			
			//Load the chosen image
			loadImage(file);
		}
	}
	
	private void loadImage(File file)
	{			
		//Get the file extension
		fileExtension = file.getName().substring(file.getName().lastIndexOf("."));
	
		try
		{
			//Load the image into a BufferedImage
			myImage = ImageIO.read(file);
		}
		//If an IOException was encountered, tell the user
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());				
		}
		
		//Create a JLabel from the picture
		myPictureLabel = new JLabel(new ImageIcon(myImage));
		
		//Add the image to the JFrame
		myFrame.add(myPictureLabel, BorderLayout.CENTER);
		
		//Get the desired dimensions for the cropped picture
		getCropDimensions();
	}
	
	/**
	 * Get the desired dimensions of cropping for the picture the user has chosen.
	 */
	private void getCropDimensions()
	{
		//Create a HeightAndWidthInputPane object to get the user's width and height dimensions
		HeightAndWidthInputPane inputPane = new HeightAndWidthInputPane(myImage.getWidth(), myImage.getHeight());
		
		//Get the height and width from the inputPane
		int width = inputPane.getWidth();
		int height = inputPane.getHeight();
		
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
		//Create the subimage based on where the transparent panel has been placed
		subImage = myImage.getSubimage(boxPanel.getX(), boxPanel.getY(), boxPanel.getWidth(), boxPanel.getHeight());
		
		//Create a JFileChooser to save the user's new subimage
		JFileChooser chooser = new JFileChooser();
		
		//Find out where the user wants to save the file
		int choice = chooser.showSaveDialog(myFrame);
		if (choice == JFileChooser.APPROVE_OPTION)
		{
			//Try to save the new image to file
			try
			{
				ImageIO.write(subImage, fileExtension, chooser.getSelectedFile());
			}
			//If an IOException was encountered, tell the user
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());				
			}
		}
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
	 * TODO: If passed with a filename argument, load that image instead of prompting
	 */
	public static void main(String[] args)
	{
		new CroppingUtility();
	}
}

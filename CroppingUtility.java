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
	
	private JScrollPane myScrollPane;
	
	//Original image
	private File inputFile;
	private String fileExtension;
	private BufferedImage myImage;
	private JLabel myPictureLabel;
	
	//New image
	private BufferedImage subImage;
		
	/**
	 * Constructor to create a CroppingUtility program.  Current supported arguments:
	 * 		-i <input file image>
	 * @param args the arguments given when the program was started
	 */
	public CroppingUtility(String[] args)
	{
		createGUI();
		
		if (args.length > 1)
			for (String arg : args)
				System.out.println(arg);
		
		getImageChoice();
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
		
		//Set the default size of the frame
		myFrame.setSize(600, 800);
		
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
			inputFile = fileChooser.getSelectedFile();
			
			//Load the chosen image
			loadImage();
		}
	}
	
	private void loadImage()
	{	
		try
		{
			//Load the image into a BufferedImage
			myImage = ImageIO.read(inputFile);
		}
		//If an IOException was encountered, tell the user
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());				
		}
		
		//Create a JLabel from the picture
		myPictureLabel = new JLabel(new ImageIcon(myImage));
		
		//Create a scroll pane to hold the image
		myScrollPane = new JScrollPane(myPictureLabel);
		
		//Add the scroll pane to the main frame
		myFrame.add(myScrollPane, BorderLayout.CENTER);
		myFrame.repaint();
		
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
		//TODO: This makes the picture invisible
		//myFrame.add(boxPanel);
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
			//Get the filename the user chose to save the new image as
			String outFileName = chooser.getSelectedFile().getName();
			
			//If the user specified a file extension, use it
			if (outFileName.indexOf(".") != -1)
				fileExtension = outFileName.substring(outFileName.lastIndexOf(".") + 1);
			//Otherwise, use the file extension of the input file
			else
				fileExtension = inputFile.getName().substring(inputFile.getName().lastIndexOf(".") + 1);
			
			//Try to save the new image to file
			try
			{
				//Write the file to disk.  Tell the user the file was written successfully
				if (ImageIO.write(subImage, fileExtension, chooser.getSelectedFile()))
					JOptionPane.showMessageDialog(null, "New image created successfully.");
				//The file could not be written.  Throw an IOException
				else
					throw new IOException("Could not write output file (Unknown cause).");
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
		new CroppingUtility(args);
	}
}

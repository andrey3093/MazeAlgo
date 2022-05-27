package filemenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser ;
import javax.swing.filechooser.FileNameExtensionFilter ;

import gui.Application;
import gui.BoxPanel;
import maze.InvalidMazeTextException;
import maze.Maze;

import java.io.BufferedReader;
import java.io.File ;
import java.io.FileReader;
import java.io.PrintWriter;


public final class ImportMenuItem extends JMenuItem implements ActionListener
{
	private final Application application  ;
	
	
	public ImportMenuItem(Application application)
	{
		super("Import") ;
		this.application = application ;
		addActionListener(this) ;
	}
	
	
	public void actionPerformed(ActionEvent evt)
	{
		JFileChooser chooser = new JFileChooser();
		
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt","txt");
	    chooser.setFileFilter(filter);
		
		chooser.setDialogTitle("Choose the maze text file :");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	String path = chooser.getSelectedFile().getAbsolutePath() ;
	    	int n = path.length() ;
	    	if (n<4 || path.substring(n-4, n).compareTo(".txt")!=0) 
	    	
	    	{JOptionPane.showMessageDialog(application, "___.txt file needed !");return;}
	    	
	    	try 
	    	{
	    		application.textCopy(new File(path),new File(application.getCurrentMazePath()));
	    		application.paintMazeFromText(path) ;
	    		application.getModel().setReadyMaze(true) ;
	    		application.getModel().setSaved(true);
	    		application.getModel().setSelectedType(0);
	    		application.getModel().setMazePath(path) ;
	    	}
	    	catch(InvalidMazeTextException e) 
	    	{
	    		JOptionPane.showMessageDialog(application, e.getName());
	    		path = application.getCurrentMazePath()  ;
	    		application.textCopy(new File(path),new File(application.getCurrentMazePath()));
	    		application.getModel().setMazePath(path) ;
	    	}
	    	catch(Exception e) {System.out.println("Error while importing :"); e.printStackTrace();}
	    	
	    }
	}
	
	
	
	
	
	
}

package mazemenu;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*  ;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawmenu.PaintMenuItem;
import gui.* ;
import maze.InvalidMazeTextException;

public final class NewMazeMenuItem extends PaintMenuItem
{
	Application application = super.getApplication() ;
	
	public NewMazeMenuItem(Application application)
	{
		super(application,"New Maze") ;
		addActionListener(this) ;
	}
	
	public final void actionPerformed(ActionEvent evt)
	{	
		
		boolean redo = false ;
		
		do
		{
			
		int length = 0;
		int width = 0;
		
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		Object[] message = {
		    "Length:", field1,
		    "Width:", field2,
		    "(max size 100x100)"};
			
		redo = false ;
		int response = JOptionPane.showConfirmDialog(application, message, "Enter your maze size", JOptionPane.OK_CANCEL_OPTION);
		if (response == JOptionPane.CANCEL_OPTION) return ;
		else if (response == JOptionPane.OK_OPTION)
		{
		    String lmsg = field1.getText();
		    String wmsg = field2.getText();
		    
		    try 
		    { 
		    	length = Integer.valueOf(lmsg); 
		    	width = Integer.valueOf(wmsg);
		    }
			catch(NumberFormatException e) 
			{
				JOptionPane.showMessageDialog(application, "Maze size should be integers !");
				redo = true ;
			}	
			catch(Exception e) {}
		    
		    if (length<0 || width<0)
		    {
				JOptionPane.showMessageDialog(application, "Maze size should be positive !");
				redo = true ;
			}
		    
		    else if (length>100 || width>100)
		    {
				JOptionPane.showMessageDialog(application, "Maze size should be smaller than 100x100 !");
				redo = true ;
			}
		    
		    if (!redo)
		    {
		    application.getModel().setMazeLength(length);
		    application.getModel().setMazeWidth(width);
		    application.refreshWindow();
		    application.getModel().setReadyMaze(true) ;
		    application.getModel().setSaved(true);
		    }
		}
		}
		while(redo);
	}
}


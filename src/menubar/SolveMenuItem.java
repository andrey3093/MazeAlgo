package menubar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.*  ;

import drawmenu.PaintMenuItem;
import gui.* ;
import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.InvalidMazeTextException;
import maze.Maze;
import maze.NoSolutionException;
import maze.WBox;


public final class SolveMenuItem extends PaintMenuItem
{	
	public SolveMenuItem(Application application)
	{
		super(application,"Solve") ;
		addActionListener(this) ;
	}

	
	public final void actionPerformed(ActionEvent evt)
	{
		Application application = super.getApplication() ;
		
		if (application.getModel().isReady()) // on ne résout que s'il n'y a pas de cases bleues
		{	
		
		super.getApplication().saveToTextFile(application.getCurrentMazePath());
		boolean solutionExists = true ;
		
		Maze M = new Maze(application.getModel().getMazeLength(),application.getModel().getMazeWidth());
		
		try
		{
			M.initFromTextFile(application.getCurrentMazePath());  //throws InvalidMazeTextException, dont la nature sera affichée dans une fenêtre pop-up
			M.dijkstraSolution(application.getMazeSolutionPath()); //throws NoSolutionExceptioт
		}
		
		catch(InvalidMazeTextException e) 
		{JOptionPane.showMessageDialog(application, e.getName()) ;
		return;}
		
		catch(NoSolutionException e) {solutionExists=false; } // on distingue le cas sans solution pour afficher un message pop-up
		
		catch(Exception e) {System.out.println(e);};
		
		if (solutionExists) 
		{
			try 
			{
				paintMazeFromText(application.getMazeSolutionPath()) ;
			}
			catch(Exception e) {e.printStackTrace();}
			
			application.getModel().setSelectedType(0); // réinitialiser le type de case séléctionné
			application.getModel().setReadyMaze(false) ; // signaler qu'il y a des cases bleues
			application.getModel().stateChanges();
		}
		else {JOptionPane.showMessageDialog(application, "This problem has no solution !");}
		}
	}
	
	
}
package mazemenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import java.util.Random;
import javax.swing.JMenuItem;

import gui.Application;
import gui.BoxPanel;
import maze.InvalidMazeTextException;



public final class GenerateMenuItem extends JMenuItem implements ActionListener
{
	private final Application application  ;
	private  double p = 0.2 ; // la proba d'avoir W au lieu de E
	
	public GenerateMenuItem(Application application)
	{
		super("Generate") ;
		this.application = application ;
		addActionListener(this) ;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		Random rand = new Random() ;
		String path = application.getCurrentMazePath() ;
		
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(path);
			
			int length =  application.getModel().getMazeLength() ;
			int width = application.getModel().getMazeWidth() ;
			int a = rand.nextInt(length*width) ;
			int d = rand.nextInt(length*width) ;
			if(a==d && d==0) d=1 ;
			if(a==d && d!=0) d=0 ;
			
			for (int j =0 ; j<width ; j++)
			{
				for (int i =0 ; i<length ; i++)
				{
					if (i+j*length==d) pw.print('D') ;
					else if (i+j*length==a) pw.print('A') ;
					else if (p<rand.nextDouble()) {pw.print('E'); p=p+0.1 ;}
					else {pw.print('W'); p=p-0.2 ;}
					
					if (i==length-1) pw.println();
				}
			}
	
			pw.close();
			
			application.paintMazeFromText(path) ;
			application.getModel().setSelectedType(0);
			application.getModel().setReadyMaze(true) ; //signaler qu'il y a des cases bleues
			application.getModel().setSaved(false);
			application.getModel().setMazePath(application.getCurrentMazePath()) ;
		}
		catch (Exception e) {System.out.println(e);};	
	}
	
}

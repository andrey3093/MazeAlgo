package drawmenu;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import gui.BoxPanel ;
import javax.swing.JMenuItem;

import gui.Application;
import maze.InvalidMazeTextException;

public abstract class PaintMenuItem extends JMenuItem implements ActionListener
//classe mère des JButtons Finish, Start, Wall, qui ne font que changer le type sélectionné
//et enlever la solution du labyrinthe donné (pour anticiper la modification)
{
private final Application application ;
	
	public PaintMenuItem(Application application, String buttonName)
	{
		super(buttonName) ;
		this.application = application ;
	}
	
	public final void paintMazeFromText(String fileName) throws InvalidMazeTextException
	{
		try
		{
			
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int length=0;
		String lab = "";
		int width =0;
		while ((line = br.readLine()) != null)
			{
			if (width==0) {length = line.length();}
				
			if (length!=line.length()) { throw new InvalidMazeTextException("Your text should be rectangle shaped !");}
				
			width++;
			lab = lab + line;
			}
			
		fr.close();
		br.close();
		
		if(verifiedMazeString(lab)) // vérifie qu'il n'y a pas d'autres termes que A,D,E,W et . (pour la solution)
		{
		
		
		application.getModel().setMazeLength(length);
	    application.getModel().setMazeWidth(width);
	    application.refreshWindow();
	    
		
		int x =0;
		int y =0;
		int size = application.getModel().getMazeLength()*application.getModel().getMazeWidth();
		for (int k=0;k<size;k++)
		{
			char val = lab.charAt(k);
			BoxPanel[][] boxTab = application.getWindow().getMazePanel().getBoxTab() ;
			
			if (val == 'E') {boxTab[y][x].setCurrentColor(Color.WHITE);}
			if (val == 'W') {boxTab[y][x].setCurrentColor(Color.BLACK);}
			if (val == 'A') {boxTab[y][x].setCurrentColor(Color.RED);}
			if (val == 'D') {boxTab[y][x].setCurrentColor(Color.GREEN);}
			if (val == '.') {boxTab[y][x].setCurrentColor(Color.BLUE);}
			
			x++;
			if (x==application.getModel().getMazeLength()) {x=0;y++;}
		}
		
	    application.getModel().setReadyMaze(true) ;
	    application.getModel().stateChanges() ;
		
		}
		}
		catch(InvalidMazeTextException e) {throw e;}
		catch(Exception e) {System.out.println(e) ;}
	}
	 
	
	public final boolean verifiedMazeString(String str) throws InvalidMazeTextException
	{
		boolean result = true ;
		for (int k=0; k<str.length();k++) 
		{
			char val = str.charAt(k);
			if ((val != 'E')&&(val != 'W')&&(val != 'A')&&(val != 'D')&&(val != '.'))
			{
				result = false ;
				throw new InvalidMazeTextException("The only allowed characters :\n'E','W','A','D'\n\n Please check you text");
			}
		}
		return result ;
	}
	
	protected final Application getApplication() // donne l'accès à l'aaplication aux JButtons
	{
		return application ;
	}
	
	protected final void verifyBlueBoxes()	
	// s'il y a des cases bleues marquant la solution du labyrinthe actuel, 
	// il retrace le labyrinthe initial CurrentMaze sans ses cases bleues

	{
		if (!application.getModel().isReady()) 
		{
			try 
			{
			paintMazeFromText(application.getCurrentMazePath()) ;
			}
			catch(Exception e) {e.printStackTrace();}
			
			application.getModel().setReadyMaze(true) ;
			application.getModel().stateChanges();
		}
	}
}

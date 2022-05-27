package gui;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.InvalidMazeTextException;


public final class Application extends JFrame implements ChangeListener // Observer du pattern MVC
{
	
	private final MenuBar menuBar ;
	private WindowPanel windowPanel ;
	private final AppModel appModel ;
	
	private final String directoryPath = System.getProperty("user.dir");
	private final String currentMazePath = directoryPath+"/data/currentmaze.txt" ;
	private final String mazeSolutionPath = directoryPath+"/data/mazesolution.txt" ;
	
	
	public Application()
	{
		super("Route Builder  (by Andrey)");
		
		appModel = new AppModel(this) ;
		appModel.addObserver(this) ;
		
		setJMenuBar(menuBar = new MenuBar(this)) ;
		setContentPane(windowPanel = new WindowPanel(this)) ;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		pack() ;	 // set components sizes, positions
		setVisible(true) ;
	}
	
	
	public final String getCurrentMazePath()
	{
		return currentMazePath;
	}
	
	public final String getMazeSolutionPath()
	{
		return mazeSolutionPath;
	}
	
	public final AppModel getModel()
	{
		return appModel  ;
	}
	
	public final WindowPanel getWindow()
	{
		return windowPanel  ;
	}
	
	public void stateChanged(BoxPanel box)
	{
		windowPanel.notifyForUpdate(box) ;
	}
	
	public void stateChanged(ChangeEvent evt) {}
	
	public void refreshWindow() //  redimentionne la JPanel représentant le labyrinthe
	{
		this.remove(windowPanel);
		this.setContentPane(windowPanel = new WindowPanel(this)) ;
		pack() ;
		setVisible(true) ;
	}
	
	
	
// Ensuite il y a les méthodes générales, appelées par des objets de différents types, tels que 
// les JButtons (fils de PaintButton et non) et les JMenuItem
// Je n'ai pas réussi à regrouper ses méthodes dans des classes abstraites mères ou dans une bibliothèque commune,
// comme tous ces objets ont accès à application, j'ai écrit le code ici :

	
// copier un fichier .txt dans un autre .txt
	
	public void textCopy(File sourceFile, File destinationFile)
	{
		try
		{
		FileReader fr = new FileReader(sourceFile);
		BufferedReader br = new BufferedReader(fr);
		String line;
		PrintWriter writer = new PrintWriter(destinationFile) ;
		
		while ((line = br.readLine()) != null) {writer.println(line);}
		br.close();
	    writer.close();
		}
		catch(Exception e) {System.out.println("Error while exporting:"); e.printStackTrace();}
	}
	

// sauvegarder le labyrinthe dessiné dans la JPanel sous forme d'un fichier .txt
	
	public final void saveToTextFile(String fileName)
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(fileName);
			
			BoxPanel[][] boxTab = this.getWindow().getMazePanel().getBoxTab() ;
			int length =  this.getModel().getMazeLength() ;
			int width = this.getModel().getMazeWidth() ;
			for (int j =0 ; j<width ; j++)
			{
				for (int i =0 ; i<length ; i++)
				{
					Color color = boxTab[j][i].getCurrentColor() ;
					
					if (color==Color.BLUE) pw.print('E') ;
					if (color==Color.WHITE) pw.print('E') ;
					if (color==Color.BLACK) pw.print('W') ;
					if (color==Color.GREEN) pw.print('D') ;
					if (color==Color.RED) pw.print('A') ;
					if (i==length-1) pw.println();
				}
			}
	
			pw.close();
		}
		catch (Exception e) {System.out.println(e);};	
	}
	
// colorier les cases de la JPanel du labyrinthe à partir d'un fichier .txt (jette une exception si le format du texte est invalide)
	
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
		
		if(verifiedMazeString(lab)) 
		{
		
		
		this.getModel().setMazeLength(length);
	    this.getModel().setMazeWidth(width);
	    this.refreshWindow();
	    
		
		int x =0;
		int y =0;
		int size = (this.getModel().getMazeLength())*(this.getModel().getMazeWidth());
		for (int k=0;k<size;k++)
		{
			char val = lab.charAt(k);
			BoxPanel[][] boxTab = this.getWindow().getMazePanel().getBoxTab() ;
			
			if (val == 'E') {boxTab[y][x].setCurrentColor(Color.WHITE);}
			if (val == 'W') {boxTab[y][x].setCurrentColor(Color.BLACK);}
			if (val == 'A') {boxTab[y][x].setCurrentColor(Color.RED);}
			if (val == 'D') {boxTab[y][x].setCurrentColor(Color.GREEN);}
			if (val == '.') {boxTab[y][x].setCurrentColor(Color.BLUE);}
			
			x++;
			if (x==this.getModel().getMazeLength()) {x=0;y++;}
		}
		
	    this.getModel().setReadyMaze(true) ;
	    this.getModel().stateChanges() ;
		
		}
		}
		catch(InvalidMazeTextException e) {throw e;}
		catch(Exception e) {System.out.println(e) ;}
	}
	 
	
// vérifier que le fichier .txt ne contient que des catactères autorisés : E,A,D,W,.
	
	public boolean verifiedMazeString(String str) throws InvalidMazeTextException
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
	


	
	
}

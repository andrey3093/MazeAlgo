package menubar;

import javax.swing.JMenu;
import mazemenu.* ;
import gui.Application;

public final class MazeMenu extends JMenu
{
	private final ResetMenuItem resetMenuItem ;
	private final GenerateMenuItem generateMenuItem ;
	private final NewMazeMenuItem newMazeMenuItem ;
	
	public MazeMenu(Application application)
	{
		super("Maze") ;
		
		add(resetMenuItem = new ResetMenuItem(application))  ;
		add(generateMenuItem = new GenerateMenuItem(application))  ;
		add(newMazeMenuItem = new NewMazeMenuItem(application))  ;
	}
}

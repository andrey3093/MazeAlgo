package menubar;

import javax.swing.JMenu;

import drawmenu.* ;
import gui.Application;

public final class DrawMenu extends JMenu
{
	private final WallMenuItem wallMenuItem ;
	private final StartMenuItem startMenuItem ;
	private final FinishMenuItem finishMenuItem ;
	private final EraseMenuItem eraseMenuItem ;
	
	public DrawMenu(Application application)
	{
		super("Draw") ;
		
		add(wallMenuItem = new WallMenuItem(application)) ;
		add(startMenuItem = new StartMenuItem(application)) ;
		add(finishMenuItem = new FinishMenuItem(application)) ;
		add(eraseMenuItem = new EraseMenuItem(application)) ;
	}
}

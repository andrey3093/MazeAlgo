package gui;

import javax.swing.* ;

import menubar.DrawMenu;
import menubar.FileMenu;
import menubar.MazeMenu;
import menubar.SolveMenuItem;

public final class MenuBar extends  JMenuBar
{
	private final FileMenu fileMenu ;
	private final MazeMenu mazeMenu ;
	private final DrawMenu drawMenu ;
	private final SolveMenuItem solveMenuItem ;
	
	public MenuBar(Application application)
	{
		super() ;
		add(fileMenu = new FileMenu(application)) ;
		add(mazeMenu = new MazeMenu(application)) ;
		add(drawMenu = new DrawMenu(application)) ;
		add(solveMenuItem = new SolveMenuItem(application)) ;
		
	}
}

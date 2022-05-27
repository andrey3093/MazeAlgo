package menubar;

import javax.swing.JMenu;
import filemenu.* ;
import gui.Application;

public final class FileMenu extends JMenu
{
	private  final  SaveMenuItem saveMenuItem ;
	private  final  ImportMenuItem importMenuItem ;
	private  final  ExportMenuItem exportMenuItem ;
	private  final  QuitMenuItem quitMenuItem ;
	
	public FileMenu(Application  application)
	{
		super("File") ;
	
		add(saveMenuItem = new SaveMenuItem(application))  ;
		add(importMenuItem = new ImportMenuItem(application))  ;
		add(exportMenuItem = new ExportMenuItem(application))  ;
		add(quitMenuItem = new QuitMenuItem(application))  ;
		
	}
	
}

package mazemenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import gui.Application;

public final class ResetMenuItem extends JMenuItem implements ActionListener
{
	private final Application application  ;
	
	public ResetMenuItem(Application application)
	{
		super("Reset") ;
		this.application = application ;
		addActionListener(this) ;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		application.refreshWindow();
	    application.getModel().setReadyMaze(true) ;
	    application.getModel().setSaved(false);
	    application.getModel().stateChanges() ;
	}
	
}

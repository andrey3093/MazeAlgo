package drawmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import gui.Application;

public final class FinishMenuItem extends PaintMenuItem
{
	public FinishMenuItem(Application application)
	{
		super(application,"Finish") ;
		addActionListener(this) ;
	}
	
	public final void actionPerformed(ActionEvent evt)
	{
		super.verifyBlueBoxes();
		super.getApplication().getModel().setSelectedType(3) ;
	}
}

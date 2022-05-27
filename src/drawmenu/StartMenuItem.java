package drawmenu;

import java.awt.event.*;
import javax.swing.*  ;

import gui.* ;

public final class StartMenuItem extends PaintMenuItem
{
	public StartMenuItem(Application application)
	{
		super(application,"Start") ;
		addActionListener(this) ;
	}
	
	public final void actionPerformed(ActionEvent evt)
	{
		super.verifyBlueBoxes();
		super.getApplication().getModel().setSelectedType(2) ;
	}
}


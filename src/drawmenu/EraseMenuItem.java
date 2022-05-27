package drawmenu;

import java.awt.event.*;
import javax.swing.*  ;

import gui.* ;

public final class EraseMenuItem extends PaintMenuItem
{
	public EraseMenuItem(Application application)
	{
		super(application,"Erase") ;
		addActionListener(this) ;
	}
	
	public final void actionPerformed(ActionEvent evt)
	{
		super.verifyBlueBoxes();
		super.getApplication().getModel().setSelectedType(0) ;
	}
}

package drawmenu;

import java.awt.event.*;
import javax.swing.*  ;

import gui.* ;

public final class WallMenuItem extends PaintMenuItem
{
	public WallMenuItem(Application application)
	{
		super(application,"Wall") ;
		addActionListener(this) ;
	}
	
	public final void actionPerformed(ActionEvent evt)
	{
		super.verifyBlueBoxes();
		super.getApplication().getModel().setSelectedType(1) ;
	}
}

package gui;

import javax.swing.* ;
import java.awt.* ;


public final class MazePanel extends JPanel
{
	private BoxPanel[][] boxTab ;
	
	
	public MazePanel(Application application, int length, int width)
	{
		super() ;
		this.boxTab = new BoxPanel[width][length] ;
		
		// on dimensionne le labyrinthe pour qu'il ne dépasse pas de l'écran
		double m = min(((double)1300) / ((double)length) , ((double)850) / ((double)width)) ;
		int echelle = (int) Math.floor( min(m,50) ) ; 
		
		
		setPreferredSize(new Dimension(length*echelle,width*echelle)) ;
		
		setLayout(new GridLayout(width,length)) ;
		for (int j=0; j<application.getModel().getMazeWidth();j++)
		{
			for (int i=0; i<application.getModel().getMazeLength();i++)
			{
				add(boxTab[j][i] = new BoxPanel(application)) ;
			}
		}
	}
	
	
	public BoxPanel[][] getBoxTab()
	{
		return this.boxTab ;
	}
	
	public void notifyForUpdate(BoxPanel box)
	{
		box.notifyForUpdate() ;
	}
	
	public double min(double a, double b)
	{
		if (a<b) return a;
		else return b;
	}
}

package filemenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.Application;
import gui.BoxPanel;

public final class QuitMenuItem extends JMenuItem implements ActionListener
{
	private final Application application  ;
	
	
	public QuitMenuItem(Application application)
	{
		super("Quit") ;
		this.application = application ;
		addActionListener(this) ;
	}
	
	
	public void actionPerformed(ActionEvent evt)
	{
		if (!application.getModel().isSaved())		
		{
			int response = JOptionPane.showInternalOptionDialog(this,
					"Drawing not saved. Save it ?","Quit application",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,null,null,null);
			switch(response)
			{
			case JOptionPane.CANCEL_OPTION : return ;
			case JOptionPane.YES_OPTION : 
				if(application.getModel().getMazePath().compareTo(application.getCurrentMazePath())==0)
				{
					JOptionPane.showMessageDialog(application, "First, you should specify the maze\n placement by using \"Export\" button ");
					return;
				}
					
				else if (!application.getModel().isSaved())
				{
				application.saveToTextFile(application.getModel().getMazePath()); // on écrit le labyrinthe sous forme de texte dans currentmaze.txt
				application.getModel().setSaved(true) ;
				}
				break ;
			case JOptionPane.NO_OPTION : break ;
			}
		}
		System.exit(0) ;
	}
	

}

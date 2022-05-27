package filemenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.Application;
import gui.BoxPanel;

public final class SaveMenuItem extends JMenuItem implements ActionListener
{
	private final Application application ;
	
	public SaveMenuItem(Application application)
	{
		super("Save") ;
		this.application = application ;
		addActionListener(this) ;
	}
	
	
	public void actionPerformed(ActionEvent evt)
	{
		if(application.getModel().getMazePath().compareTo(application.getCurrentMazePath())==0)
		{
			JOptionPane.showMessageDialog(application, "First, you should specify the maze\n placement by using \"Export\" button ");
		}
			
		else if (!application.getModel().isSaved())
		{
		application.saveToTextFile(application.getModel().getMazePath()); // on écrit le labyrinthe sous forme de texte dans currentmaze.txt
		application.getModel().setSaved(true) ;
		}
	}
	
	
}

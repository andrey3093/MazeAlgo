package gui;

import java.util.ArrayList;
// import java_tp4.src.maze.Maze;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.*  ;
import java.awt.* ;

public final class AppModel // Observable du pattern MVC
{
	private final Application application ;
	
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>() ; // liste des objets observers (ici, Application)
	
	private int selectedType ; // type de case sélectionné pour le dessin : 0=nothing, 1=WAll, 2=Start, 3=Finish
	private Boolean mazeReady ; // the displayed maze is ready to be completed (= le labyrinthe affiché n'a pas de cases bleues)
	private Boolean saved; // savoir si le labyrinthe manipulé par l'utilisateur est enregistré ou pas 
	
	private String mazePath ;	// emplacement du fichier .txt contenant la version initiale du labyrinthe manipulé
	private int mazeLength ;
	private int mazeWidth ;
	
	private boolean mousePressed ;	// état appuyé ou pas de la souris, qui permet de sélectionner plusieurs cases en
									// maintenant le clic gauche, en entrant dans les cases (JPanels)
	
	
	public AppModel(Application application)
	{
		this.application = application ;
		saved = true ;
		selectedType = 0 ;
		mazeReady = true ;
		mazePath = application.getCurrentMazePath() ;
		mazeLength = 8;
		mazeWidth  = 8;
	}
	
	
	public void addObserver(ChangeListener listener)
	{
		listeners.add(listener) ;
	}
	
	public void stateChanges() 
	{
		ChangeEvent evt = new ChangeEvent(this) ; 
		for (ChangeListener listener : listeners) listener.stateChanged(evt);
	}
	
	public void setMousePressed(boolean mouse)
	{
		mousePressed = mouse ;
	}
	
	public boolean isMousePressed()
	{
		return mousePressed ;
	}
	
	public void setSaved(boolean saved)
	{
		this.saved = saved ;
	}
	
	public boolean isSaved()
	{
		return this.saved ;
	}
	
	public int getSelectedType()
	{
		return selectedType ;
	}
	
	public void setSelectedType(int newType)
	{
		selectedType = newType ;
	}
	
	public int getMazeLength()
	{
		return mazeLength;
	}
	
	public int getMazeWidth()
	{
		return mazeWidth;
	}
	
	public void setMazeLength(int length)
	{
		mazeLength = length;
	}
	
	public void setMazeWidth(int width)
	{
		mazeWidth = width;
	}
	
	public Boolean isReady()
	{
		return mazeReady;
	}
	
	public void setReadyMaze(Boolean isReady)
	{
		mazeReady = isReady ;
	}
	
	public final String getMazePath()
	{
		return mazePath ;
	}
	
	public final void setMazePath(String newPath) 
	{
		this.mazePath = newPath ;
		// on fait confiance à l'objet qui appelle cette methode que la forme de l'adresse est valide
	}
	
}

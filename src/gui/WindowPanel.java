package gui;

import java.awt.*;
import javax.swing.*;

public final class WindowPanel extends JPanel 
{
	private Application application ;
	private MazePanel mazePanel ;
	
	
	public WindowPanel(Application application)
	{
		super() ;
		this.application = application ;
		setLayout(new BorderLayout()) ;
		add(mazePanel = new MazePanel(application,application.getModel().getMazeLength(),application.getModel().getMazeWidth()),BorderLayout.CENTER);
	}
	
	
	public void notifyForUpdate() {}
	
	public void notifyForUpdate(BoxPanel box)
	{
		mazePanel.notifyForUpdate(box) ;
	}
	
	public void setMaze(Application application,int length, int width)
	{
		this.remove(mazePanel) ;
		this.add(mazePanel = new MazePanel(application,length,width),BorderLayout.CENTER);
		application.getModel().stateChanges();
	}
	
	public MazePanel getMazePanel()
	{
		return mazePanel ;
	}
}

package gui;

import java.awt.event.*;
import javax.swing.*  ;

import java.awt.* ;


public final class BoxPanel extends JPanel implements MouseListener, MouseMotionListener
{
	private final Application application ;
	private Color currentColor ;
	
	public BoxPanel(Application application)
	{
		super() ;
		this.application = application ;
		this.currentColor = Color.WHITE ;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public final void setCurrentColor(Color newColor)
	{
		this.currentColor = newColor ;
		application.stateChanged(this); // on ne signale un changement que si on met une couleur dans la case
	}
	
	public final Color getCurrentColor()
	{
		return currentColor ;
	}
	
	private final void selectBox() 	// méthode qui colorie la case si on appuie dessus, elle sera appelé par plusieurs méthodes de listeners
	{
		Color newColor = Color.WHITE ;
		int type = application.getModel().getSelectedType() ;
		
		switch(type)  // choix de couleur selon le type de case sélectionné (Wall,Start ou Finish)
		{
		case 0 : break;
		case 1 : newColor = Color.BLACK ; break;
		case 2 : newColor = Color.GREEN ; break;
		case 3 : newColor = Color.RED ; break;
		}
		
		if(currentColor==newColor) setCurrentColor(Color.WHITE) ;
		else setCurrentColor(newColor) ;
		
		if(application.getModel().isSaved()) application.getModel().setSaved(false);
		
	}
	
	public final void notifyForUpdate()
	{
		repaint() ; 
	}
	
	@Override
	protected final void paintComponent(Graphics g)
	{
		super.paintComponent(g); // paints the background
		
		int w = getWidth() ;
		int h = getHeight() ;
		
		g.setColor(this.getCurrentColor()) ;
		g.fillRect(0,0,w,h) ;
		g.setColor(Color.BLACK) ;
		g.drawRect(-1,-1,w+1,h+1) ;
		
	}
	
	
	//	on met en place la possibilité de colorier des cases en gardant la souris appuyée :
	//	on utilise une JPanel qui regarde les mouvements de la souris et la valeur uune booléen mousePressed du modèle
	
	public final void mousePressed(MouseEvent e) 
	{
		this.selectBox(); 
		application.getModel().setMousePressed(true); 
	}
	public final void mouseReleased(MouseEvent e) {application.getModel().setMousePressed(false); }
	public final void mouseEntered(MouseEvent e) {if(application.getModel().isMousePressed()) {this.selectBox();} }
	public final void mouseClicked(MouseEvent e) {}
	public final void mouseDragged(MouseEvent e) {}
	// on spécifie que si on sélectionneStart ou Finish, même en maintenant la souris appuyée, on ne peut 
	public final void mouseExited(MouseEvent e) 
	{
		if(application.getModel().getSelectedType()>1 && application.getModel().isMousePressed())
		this.selectBox();
	}
	public final void mouseMoved(MouseEvent e) {}
}



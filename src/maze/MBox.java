package maze;
/* le code du tp6 est dans le projet "java_tp4" pour ne pas créer
 * un nouveau projet sur mon pc perso avec un environnement trop
 * récent pour les ordis de Telecom */


import dijkstra.VertexInterface;


public abstract class MBox implements VertexInterface
{

	private int X ; /* de gauche à droite */
	private int Y ; /* de haut en bas */
	
	protected MBox(int x,int y)
	{
		X = x;
		Y = y;
	}
	
	public abstract String getLabel();

	public int getX()
	{
		return X;
	}
	
	public int getY()
	{
		return Y;
	}	
	
	
	
}

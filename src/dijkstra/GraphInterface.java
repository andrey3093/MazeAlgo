package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
	
	public int getSize() ; 
	/* donner le nb de sommets, pour comparer à A */
	
	public ArrayList<VertexInterface> getAllVertices() ;
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex ) ; /* donner successeurs de x */
	/* pourquoi sur la ligne précédente en mettant Vertex en arguement au 
	 * lieu de VertexInterface ca me marche plus ? */

	
	public int getWeight(VertexInterface x, VertexInterface y) ; 
	/* donner le poide de l'arce (x,y) */
	
}

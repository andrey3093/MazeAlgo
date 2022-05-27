package dijkstra;

public interface PreviousInterface {
	
	public VertexInterface getPrevious(VertexInterface v) ; /* donner le pere du sommet v */
	public void changePrevious(VertexInterface v, VertexInterface p) ; 
	/* changer le pere du sommet v en p */
	
}

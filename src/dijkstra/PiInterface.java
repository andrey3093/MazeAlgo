package dijkstra;

public interface PiInterface {
	
	public int getPi(VertexInterface v) ; /* donner la distance racine-sommet v */
	public void changePi(VertexInterface v , int k) ; /* changer la distance racine-sommet v */
	
}

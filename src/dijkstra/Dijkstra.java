package dijkstra;

import java.util.ArrayList;


public class Dijkstra
{	
	public Dijkstra(){}

	
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r)
	{
		ASet a = new ASet() ;
		Pi pi = new Pi() ;
		Previous previous = new Previous() ;
		
		VertexInterface pivot ;
		a.addElt(r);
		pivot = r;
		ArrayList<VertexInterface> L = g.getAllVertices();
		int n = g.getSize() ;
		
		for (int i=0 ; i < n ; i++ )
		{
			VertexInterface v = L.get(i) ;
			if (v==r) { pi.changePi(v, 0) ; }
			else {pi.changePi(v, 999999) ; } ; /* 999999 = infini */
		}
		
		for (int j=1 ; j<n ; j++ )
		{
			ArrayList<VertexInterface> M = g.getSuccessors(pivot) ;
			for (int i=0 ; i < M.size() ; i++)
			{
				VertexInterface y = M.get(i);			
				int x = pi.getPi(pivot)+g.getWeight(pivot, y);
				if (!a.contains(y) && x<pi.getPi(y))
				{
					pi.changePi(y, x);
					previous.changePrevious(y, pivot);
				}
			}
			
			VertexInterface yMin = L.get(0) ;
			int piMin = 999999 ;
			for (int i=0 ; i<n ; i++)
			{
				VertexInterface v = L.get(i) ;
				if (!a.contains(v) && pi.getPi(v)<piMin)
				{
					yMin = v;
					piMin = pi.getPi(v);
				}
			}
			pivot = yMin ;
			a.addElt(pivot);
			
		}
		
		return previous ;
	}
	
}

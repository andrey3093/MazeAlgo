package dijkstra;

import java.util.Hashtable;


public class Previous extends Hashtable<VertexInterface,VertexInterface> implements PreviousInterface
{
	public Previous()
	{
		super();
	}
	
	public VertexInterface getPrevious(VertexInterface v)
	{
		return this.get(v);
	}
	
	public void changePrevious(VertexInterface v, VertexInterface p)
	{
		this.put(v, p);
	}
	
}

/*
public class Previous implements PreviousInterface
{
	
	private ArrayList<VertexInterface> ref;
	private ArrayList<VertexInterface> val;
	
	public Previous()
	{
		ref = new ArrayList<VertexInterface>();
		val = new ArrayList<VertexInterface>();
	}
	
	public VertexInterface getPrevious(VertexInterface fils)
	{
		if (ref.contains(fils)) { return val.get(ref.indexOf(fils)); } 
		else {return null;} 
	}
	
	public void changePrevious(VertexInterface fils, VertexInterface pere)
	{
		if (ref.contains(fils)) { val.set(ref.indexOf(fils), pere) ;} 
		else { ref.add(fils) ; val.add(pere); } 
	}
	
}
*/
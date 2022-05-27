package dijkstra;

import java.util.Hashtable;



public class Pi extends Hashtable<VertexInterface,Integer> implements PiInterface
{
	
	public Pi()
	{
		super();
	}
	
	public int getPi(VertexInterface v) 
	{
		return this.get(v).intValue();
	}
	
	public void changePi(VertexInterface v , int k) 
	{
		this.put(v,k);
	}
	
}



/*
public class Pi implements PiInterface
{
	private ArrayList<VertexInterface> ref;
	private ArrayList<Integer> val;
	
	public int getPi(VertexInterface v)
	{
		if (ref.contains(v)) { return val.get(ref.indexOf(v)); } 
		else {return 999999 ;} 
	}
	
	public void changePi(VertexInterface v , int k)
	{
		if (!ref.contains(v)) { ref.add(v) ; val.add(k) ;}
		else {val.set(ref.indexOf(v), k);}
	}
	
	public Pi()
	{
		ref = new ArrayList<VertexInterface>();
		val = new ArrayList<Integer>() ;
	}
	
}
*/
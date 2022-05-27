package dijkstra;

import java.util.HashSet;


public class ASet extends HashSet<VertexInterface> implements ASetInterface
{
	public ASet()
	{
		super();
	}
	
	@Override
	public int size()
	{
		return this.size();
	}
	
	
	@Override
	public boolean contains(VertexInterface v)
	{
		return super.contains(v);
	}
	
	public void addElt(VertexInterface v) 
	{
		this.add(v);
	}
	
}


/*
public class ASet implements ASetInterface
{
	private ArrayList<VertexInterface> list ;
	
	public ASet()
	{
		list = new ArrayList<VertexInterface>() ;
	}
	
	public int length()
	{
		return list.size() ;
	}
	
	public boolean contains(VertexInterface v)
	{
		return list.contains(v);
	}
	
	public void addElt(VertexInterface v) 
	{
		list.add(v);
	}
}
*/
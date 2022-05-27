package maze;

public class MBoxList 
{
	private final Integer[][] tab;
	
	public MBoxList(int length, int width)
	{
		tab = new Integer[length][width] ;
	}
	
	
	
	public void add(MBox box)
	{
		this.tab[box.getX()][box.getY()] = 1;
	}
	
	public boolean contains(int X,int Y)
	{
		return !(this.tab[X][Y]==null);
	}
}

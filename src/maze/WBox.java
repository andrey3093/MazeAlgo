package maze;

public class WBox extends MBox
{
	private final String label;
	
	
	public WBox(int x0, int y0)
	{
	  super(x0,y0);
	  label = "W";
	}
	
	public String getLabel()
	{
		return label;
	}
	
}

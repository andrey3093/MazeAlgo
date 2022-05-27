package maze;

public class EBox extends MBox
{
	private final String label;
	
	
	public EBox(int x0, int y0)
	{
	  super(x0,y0);
	  label = "E";
	}
	
	public String getLabel()
	{
		return label;
	}
	
}

package maze;

public class ABox extends MBox
{

	private final String label;
	
	
	public ABox(int x0, int y0)
	{
	  super(x0,y0);
	  label = "A";
	}
	
	public String getLabel()
	{
		return label;
	}
}

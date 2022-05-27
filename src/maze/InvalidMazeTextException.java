package maze;

public final class InvalidMazeTextException extends Exception
{
	private final String Name;
	
	public InvalidMazeTextException(String name)
	{
		super("Invalid maze text : "+ name);
		this.Name = name;
	}
	
	public final String getName()
	{
		return Name;
	}
	
}

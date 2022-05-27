package maze;

public final class NoSolutionException extends Exception
{
	public NoSolutionException()
	{
		super("No solution for this maze") ;
		
	}
}

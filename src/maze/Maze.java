package maze;

import java.util.ArrayList;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.Previous;
import dijkstra.VertexInterface;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Maze implements GraphInterface
{
	
	private final int length ; 
	private final int width ;
	private MBox[][] arr ;
	
	private MBox départ;
	private MBox arrivée;
	
	
	public Maze(int length0, int width0)
	{
		length = length0;
		width = width0;
		arr = new MBox[length][width] ;
	}
	
	private void setBox(int x,int y, MBox B)
	{
		arr[x][y] = B;
	}
	
	public int getSize()
	{
		return length*width ;
	}
	
	public int getLength()
	{
		return length ;
	}
	
	public int getWidth()
	{
		return width ;
	}
	
	public ArrayList<VertexInterface> getAllVertices()
	{
		ArrayList<VertexInterface> list = new ArrayList<VertexInterface>();
		for (int i=0;i<length;i++)
		{
			for (int j=0;j<width;j++)
			{
				list.add((VertexInterface)arr[i][j]);
			}
		}
		return list ; 
	}
	
	public MBox getMBox(int x,int y)
	{
		return arr[x][y];
	}
	
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex)
	{
		ArrayList<VertexInterface> list = new ArrayList<VertexInterface>();
		MBox box = (MBox) vertex ;	
		int X = box.getX();
		int Y = box.getY();
		int x; int y;
		
		x = X-1; y = Y;
		if (x>=0 && y>=0 && x<length && y<width) 
		{
			if (!this.getMBox(x,y).getLabel().equals("W")) {list.add((VertexInterface)this.getMBox(x,y));}
		}
		
		x = X+1; y = Y;
		if (x>=0 && y>=0 && x<length && y<width) 
		{
			if (!this.getMBox(x,y).getLabel().equals("W")) {list.add((VertexInterface)this.getMBox(x,y));}
		}
		
		x = X; y = Y-1;
		if (x>=0 && y>=0 && x<length && y<width) 
		{
			if (!this.getMBox(x,y).getLabel().equals("W")) {list.add((VertexInterface)this.getMBox(x,y));}
		}
		
		x = X; y = Y+1;
		if (x>=0 && y>=0 && x<length && y<width) 
		{
			if (!this.getMBox(x,y).getLabel().equals("W")) {list.add((VertexInterface)this.getMBox(x,y));}
		}
		
		return list;
	}
	
	
	public int getWeight(VertexInterface x, VertexInterface y)
	{
		ArrayList<VertexInterface> xSucc = this.getSuccessors(x);
		if (xSucc.contains(y)) {return 1;}
		else return 999999; /* = infinity */
	}
	
	

	public final void initFromTextFile(String fileName) throws InvalidMazeTextException
	{
		try 
		{
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			int len=0;
			String lab = "";
			int i =0;
			while ((line = br.readLine()) != null)
			{
				len = line.length(); 
				
				if (len!=this.getLength()) { throw new InvalidMazeTextException("Invalid length");}
				
				i++;
				lab = lab + line;
			}
			
			fr.close();
			br.close();
			
			if (i!=this.getWidth()) { throw new InvalidMazeTextException("Invalid width");}
			/* comme le labyrinthe est créé avant l'éxécution de cette 
			 * méthode il faut s'assurer que ses dimentions correspondent
			 * à celles du texte */
			
			int x =0;
			int y =0;
			for (int k=0;k<len*i;k++)
			{
				char val = lab.charAt(k);
				
				if (val == 'A') 
				{
					if (arrivée != null) {throw new InvalidMazeTextException("Maze should have 1 Finish !");}
					else {arrivée = new ABox(x,y) ; this.setBox(x, y, arrivée);}
					
				}
				else if (val == 'D') 
				{
					if (départ != null) {throw new InvalidMazeTextException("Maze should have 1 Start !");}
					else {départ = new DBox(x,y) ; this.setBox(x, y, départ);}
				}
				else if (val == 'W') {this.setBox(x, y, new WBox(x,y));}
				else if (val == 'E') {this.setBox(x, y, new EBox(x,y));}
				else  { throw new InvalidMazeTextException("unknown letter"); }
				
				x++;
				if (x==len) {x=0;y++;}
			}
			
			if (départ==null) {throw new InvalidMazeTextException("Forgotten Start !");}
			if (arrivée==null) {throw new InvalidMazeTextException("Forgotten Finish !");}
			
		}
		catch(InvalidMazeTextException e) {throw e ;}
		catch(IOException e) {System.out.println(e) ; }
		/* finally {try {br.close();} catch (Exception e) {} } ; */
		
	}
	
	
	
	public final void saveToTextFile(String fileName)
	{
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(fileName);
			
			int x = 0;
			int y = 0;
			int len = this.getLength() ;
			for (int i=0; i<this.getSize(); i++)
			{	
				String s = this.getMBox(x, y).getLabel() ;
				pw.print(s);
				
				x++;
				if (x==len) {x=0; y++; pw.println(); };
			}
			pw.close();
		}
		catch (Exception e) {System.out.println(e);};
		
	}
	
	
	
	public final void dijkstraSolution(String filename) throws NoSolutionException
	//écrit la solution du problème dans le fichier texte 'filename'
	{
		Previous prev = (Previous) Dijkstra.dijkstra(this , départ) ;
		
		MBox pere ;
		MBox x = arrivée;
		
		MBoxList chemin = new MBoxList(this.getLength(),this.getWidth()) ;
		chemin.add(arrivée);
		while (prev.getPrevious(x) != null ) 
		{
			
			pere = (MBox) prev.getPrevious(x);
			chemin.add(pere);
			x = pere;
		}
		
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(filename);
			
			int X = 0;
			int Y = 0;
			for (int i=0; i<this.getSize(); i++)
			{	
				if ( chemin.contains(X,Y) && 
					!(X==départ.getX() && Y==départ.getY()) &&
					!(X==arrivée.getX() && Y==arrivée.getY()) ) {pw.print(".");}
				
				else {pw.print( this.getMBox(X,Y).getLabel() ) ;}
				
				X++;
				if (X==this.getLength()) {X=0; Y++; pw.println(); };
			}
			pw.close();
			if ( !chemin.contains(départ.getX(),départ.getY()) ) {throw new NoSolutionException();}
			
			
		}
		catch(NoSolutionException e) {throw new NoSolutionException();}
		catch (Exception e) {System.out.println(e);};
		
	}
	
	
}
























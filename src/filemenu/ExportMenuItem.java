package filemenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFileChooser ;
import javax.swing.filechooser.FileNameExtensionFilter ;

import java.io.BufferedReader;
import java.io.File ;
import java.io.PrintWriter ;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileNotFoundException ;
import java.io.FileReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*  ;
import gui.* ;


public final class ExportMenuItem extends JMenuItem implements ActionListener
{
	private final Application application  ;
	
	public ExportMenuItem(Application application)
	{
		super("Export") ;
		this.application = application ;
		addActionListener(this) ;
	}
	
	
	public void actionPerformed(ActionEvent evt)
	{
		application.saveToTextFile(application.getCurrentMazePath());
		
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose the directory :");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		
		chooser.setApproveButtonText("Save into") ;
		
		
		
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    try 
	    {
	    	boolean redo = false;
	    	do
	    	{
	    		JTextField field1 = new JTextField() ;
	    		Object[] message = {"Write the file name :",field1} ;
	    		
	    		int response = JOptionPane.showConfirmDialog(application, message, "Save your maze", JOptionPane.OK_CANCEL_OPTION);
	    		if (response == JOptionPane.OK_OPTION)
	    		{
	    			String word = field1.getText();
	    			if (strContainsSpecialChar(word))
	    			{
	    				JOptionPane.showMessageDialog(application, "Special characters forbidden !");
	    				redo = true ;
	    			}
	    			else
	    			{
	    				redo = false ;
	    				
	    				if(word.length()==0) 
	    				{
	    					SimpleDateFormat formatter= new SimpleDateFormat("ddMMyyyy'at'HHmmss");
	    					Date date = new Date(System.currentTimeMillis());
	    					word = "maze"+formatter.format(date) ;
	    				}
	    				word = word + ".txt" ;
	    				
	    				String path = chooser.getSelectedFile().getAbsolutePath() ;
	    				String fullpath = path+"/"+word ;
	    		    	File newfile = new File(path+"/"+word) ;
	    		    	newfile.createNewFile();
	    				
	    		    	File file = new File(application.getCurrentMazePath());
	    		    	application.textCopy(file,newfile) ;
	    		    	
	    		    	application.getModel().setMazePath(fullpath);
	    		    	application.getModel().setSaved(true);
	    			}
	    			
	    		}
	    		if (response == JOptionPane.CANCEL_OPTION) {redo = false ;}
	    		
	    		
	    	}
	    	while(redo) ;
	    	
	    }
	    
	    
	    catch(Exception e) {System.out.println("Error while exporting :"); e.printStackTrace();}
	    	
	    }
	}

	
	public boolean strContainsSpecialChar(String str)
	{
		if (str.length()==0) return false ;
		
		boolean result = false ;
		String[] blackList = {"!","?","<",">",":","/","|","*"} ;
		for (String c : blackList)
		{
			if (str.contains(c)) result = true ;
		}
		if (str.charAt(str.length()-1)=='.' || str.charAt(str.length()-1)==' ') result = true ;
		return result ;
	}
	
	
	
}

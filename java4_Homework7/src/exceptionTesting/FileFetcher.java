package exceptionTesting;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileFetcher {
	String aLine = "";
	FileReader myFile, chooseFile;
	BufferedReader in;
	
	public void getHomework() throws FileNotFoundException, IOException {
		try {
			myFile = new FileReader("homework2.txt");
			System.out.println("I did get here");
			in = new BufferedReader(myFile);			
		}
		catch (FileNotFoundException e) {
			System.out.println("Encountered FileNotFoundException.\nPlease choose a file.");
			JFileChooser chooser = new JFileChooser();
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    }
		    chooseFile = new FileReader(chooser.getSelectedFile().toString());
			in = new BufferedReader(chooseFile);		
		}
		while (aLine != null) {
			try {
				aLine = in.readLine();
			}
			catch (IOException e) {
				System.out.println("Now we have some other I/O problem");
			}
			if (aLine != null) 
				System.out.println(aLine);
		}
	}
	
	public static void main(String [] args) {
		FileFetcher testMe = new FileFetcher();
		try {
			testMe.getHomework();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	} 
}

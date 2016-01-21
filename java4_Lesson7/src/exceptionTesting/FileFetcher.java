package exceptionTesting;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileFetcher {
	String aLine = "";
	FileReader myFile;
	BufferedReader in;
	
	public void getHomework() throws FileNotFoundException, IOException {
		//try {

			myFile = new FileReader("homework2.txt");
			System.out.println("I did get here");
			in = new BufferedReader(myFile);			
		//}
		//catch (FileNotFoundException e) {
		//	System.out.println("Can't find the file, but keep going anyway--allows for future problems!");
		//}
		while (aLine != null) {
		//	try {
				aLine = in.readLine();
		//	}
		//	catch (IOException e) {
		//		System.out.println("Now we have some other I/O problem");
		//	}
			if (aLine != null) System.out.println(aLine);
		}
	}
	/*
	public static void main(String [] args) {
		FileFetcher testMe = new FileFetcher();
		testMe.getHomework();
	} */
}

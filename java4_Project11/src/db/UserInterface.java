package db;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {
	private DatabaseManager database;
	
	public UserInterface(DatabaseManager theDatabaseManager) {
		database = theDatabaseManager;
	}
	
	public void start() {
		Scanner in = new Scanner (System.in);
		while (true) {							//Continue until the user quits
			System.out.println("Click in the Console,"
					+ "\n then enter a command:"
					+ "\n A (then Enter) to Add a phone book entry, \n"
					+ " Q (then Enter) to Quit.");
			
			String command = in.nextLine();
			//Check to make sure user enters a command
			if (command.isEmpty()) {
				System.out.println ("Invalid command. Please try again.");
			}
			else if (command.charAt(0) == 'A') {
				System.out.println ("Enter name: ");
				String name = in.nextLine();
				System.out.println("Enter phone number (XXX-XXXXXXX): ");
				String phoneNumber = in.nextLine();
				if (isPhoneNumberCorrect(phoneNumber)) {
					//Add this entry to the database
					database.addEntry(name, phoneNumber);
				} else {
					System.out.println("Error phone Number must be in the form XXX-XXXXXXX");
				}
			}
			else if (command.charAt(0) == 'Q') {
				System.out.println ("Exiting now!");
				database.close();                                             
                return;
			}
			else {
				System.out.println ("Invalid command. Please enter either A or Q.");
			}
		}
	}
	
	private boolean isPhoneNumberCorrect(String pPhoneNumber) {
		//xxx-xxxxxxx
	    Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");  
	    Matcher matcher = pattern.matcher(pPhoneNumber);

	    if (matcher.matches()) {
	    	  return true;
	    }
	    else {	    	  
	    	  return false;
	    }
	} 
}

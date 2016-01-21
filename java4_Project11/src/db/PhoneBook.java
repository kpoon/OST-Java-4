package db;

public class PhoneBook {
	public static void main(String[] args) {	//args[0] must be the username and args[1] must be the password to connect to the mysql database
		DatabaseManager databaseManager = new DatabaseManager(args[0], args[1]); 	//Create the database manager
	
		UserInterface userInterface = new UserInterface(databaseManager); 	//Create access for user input
		userInterface.start();
	}
}

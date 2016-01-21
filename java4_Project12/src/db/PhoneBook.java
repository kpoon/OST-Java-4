package db;

public class PhoneBook {

private int loginAttempts = 0;	
	
	public PhoneBook() {	
		while (loginAttempts < 3) {
			String [] info = PasswordDialog.login(this);		//static login so can call from class
			DatabaseManager databaseManager = new DatabaseManager(info[0], info[1]); 	//Create the database manager and pass login info
			
			if(databaseManager.isLoginSuccessful()) {
				UserInterface userInterface = new UserInterface(databaseManager); 	//Create access for user input
				userInterface.start();
				break;
			}
			loginAttempts++;
		}
		
		if (loginAttempts == 3) {
			PasswordDialog.showLoginFailed();
			System.exit(0);
		}
	}
	
	public static void main (String[] args) {			//instantitate to start
		PhoneBook myApp = new PhoneBook();
	}
}

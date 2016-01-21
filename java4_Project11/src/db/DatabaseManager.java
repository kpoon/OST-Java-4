package db;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;                                             // The database connection object.
    private Statement statement;                                               // the database statement object, used to execute SQL commands.

    public DatabaseManager (String username, String password ) {               // the constructor for the database manager.
        String url = "jdbc:mysql://sql.useractive.com:3306/" + username;       // our database--username is your O'Reilly login username and is passed in.
        try {
            Class.forName ("com.mysql.jdbc.Driver");                           // get the driver for this database.
            System.out.println("Driver is set; ready to go!");
        }
        catch (Exception e) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            return;                                                            // cannot even find the driver--return to caller since cannot do anything.
        }

        try {                                                                     // Establish the database connection, create a statement for execution of SQL commands.
            connection = DriverManager.getConnection (url, username, password );  // username and password are passed into this Constructor.
            statement  = connection.createStatement();                            // statement used to do things in the database (e.g., create the PhoneBook table).
            statement.execute("create table Phonebook (Name varchar (32), PhoneNumber varchar (18));");	//create a table in the database
        }
        catch (SQLException exception ) {
            System.out.println ("\n*** SQLException caught ***\n");
            while (exception != null) 
            {                                                                     // grab the exception caught to tell us the problem.
                System.out.println ("SQLState:   " + exception.getSQLState()  );
                System.out.println ("Message:    " + exception.getMessage()   );
                System.out.println ("Error code: " + exception.getErrorCode() );
                exception = exception.getNextException ();
                System.out.println ( "" );
            }
        }
        catch (java.lang.Exception exception) {                                  // perhaps there is an exception that was not SQL related.
            exception.printStackTrace();                                         // shows a trace of the exception error--like we see in the console.
        }
    }
    
    public void addEntry (String name, String phoneNumber) {			//adds an entry to the Phone book
    	try {
    		statement.execute ( "insert into PhoneBook values ('" + name + "', '" + phoneNumber + "');" );
    	} catch (SQLException exception) {
    		System.out.println ("\n*** SQLException caught ***\n");
    		while (exception != null) {
    			System.out.println ("SQLState:   " + exception.getSQLState());
    			System.out.println ("Message:    " + exception.getMessage());
    			System.out.println ("Error code: " + exception.getErrorCode());
    			exception = exception.getNextException();
    			System.out.println ("");
    		}
    	}
    	catch (java.lang.Exception exception) {
    		exception.printStackTrace();
    	}
    }
    
    // drops the table and properly closes the database
    public void close() {                                                       
        try
        {
            statement.execute("drop table PhoneBook;");
            statement.close();
            connection.close();
        }
        catch (SQLException exception) 
        {
            System.out.println("\n*** SQLException caught ***\n");
            while (exception != null) 
            {
                System.out.println("SQLState:  " + exception.getSQLState());
                System.out.println("Message:   " + exception.getMessage());
                System.out.println("ErrorCode: " + exception.getErrorCode());
                exception = exception.getNextException ();   
                System.out.println("");
            }
        }
        catch(java.lang.Exception exception ) 
        {
            exception.printStackTrace();
        }
    }
}   


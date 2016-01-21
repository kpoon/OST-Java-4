package db;

import java.sql.*;

public class DatabaseManager {

    private Connection connection;                                        // The database connection object.
    private Statement statement;                                          // the database statement object, used to execute SQL commands.
    private ResultSet resultSet;										  // results from a database query
    
    public DatabaseManager (String username, String password ) {          // the constructor for the database manager
        String url = "jdbc:mysql://sql.useractive.com:3306/" + username;
        try {
            Class.forName ("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            return;
        }

        try {                                                             // Establish the database connection, create a statement for execution of SQL commands.
            connection = DriverManager.getConnection (url, username, password );  // username and password are passed into this Constructor
            statement  = connection.createStatement();  

            DatabaseMetaData aboutDB = connection.getMetaData ();
            // do more useful things with the meta class
            String [] tableType = {"TABLE"};
            ResultSet rs = aboutDB.getTables(null, null, "PhoneBook",  tableType);  // for more info about this method, see the getTables method in DatabaseMetaData in the API

            if (!inspectForTable (rs))                                    // use this method to see if we already have the table PhoneBook
        	    statement.execute ("create table PhoneBook (Name varchar (32), PhoneNumber varchar (18) );");     // if we do NOT already have one, we want to do this 
            rs.close();                                                             // in this example, the ResultSet is local - so close it here                                     
        }
        catch (SQLException exception ) {
            System.out.println ("\n*** SQLException caught ***\n");
            while (exception != null) 
            {                                                                         // tell us the problem
                System.out.println ("SQLState:    " + exception.getSQLState()  );
                System.out.println ("Message:     " + exception.getMessage()   );
                System.out.println ("Error code:  " + exception.getErrorCode() );
                exception = exception.getNextException ();
                System.out.println ( "" );
            }
        }
        catch ( java.lang.Exception exception ) {
            exception.printStackTrace();
        }
    }

    public void addEntry (String name, String phoneNumber ){                       // adds an entry to the Phone Book
        try
        {
            statement.execute ( "insert into PhoneBook values ('" + name + "', '" + phoneNumber + "');" );
        }
        catch ( SQLException exception ) 
        {
            System.out.println ("\n*** SQLException caught ***\n");
            while ( exception != null) 
            {
                System.out.println ("SQLState:    " + exception.getSQLState()  );
                System.out.println ("Message:     " + exception.getMessage()   );
                System.out.println ("Error code:  " + exception.getErrorCode() );
                exception = exception.getNextException ();   
                System.out.println ( "" );
            }
        }
        catch(java.lang.Exception exception ) 
        {
            exception.printStackTrace();
        }
    } 

    private static boolean inspectForTable (ResultSet rs)  throws SQLException {  // will be caught when used
        int i;
        ResultSetMetaData rsmd = rs.getMetaData ();                               // Get the ResultSetMetaData. This will be used for information about the columns.
        int numCols = rsmd.getColumnCount ();                                     // Get the number of columns in the result set
        for (i=1; i<=numCols; i++) {                                           // Display column headings
            if (i > 1) System.out.print(", ");                                    // just to show what is there for our curiosity
                System.out.print(rsmd.getColumnLabel(i));
        }
        System.out.println("");

        boolean more = rs.next ();
        while (more) {                                                             // Display data, fetching until end of the result set
            // Loop through each row, getting the column data and displaying
            for (i=1; i<=numCols; i++) 
            {
                System.out.print(rs.getString(i)+"\n");   
                if (rsmd.getColumnLabel(i) == "TABLE_NAME")
                    if (rs.getString(i).equals("PhoneBook"))
                    {
                        System.out.println("Found one that equals " + rs.getString(i));   // is PhoneBook there already or not?
                        return true;                                                      // it is, tell the method that inquired
                    }
            }
            System.out.println("");
            more = rs.next ();                                                        // Fetch the next result set row
        }
        return false;                                                                  // went though all of the rows and it was not there
    }
    
    public void getEntries() {
    	try {
    		resultSet = statement.executeQuery("SELECT * FROM PhoneBook");
    		
    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int numCols = metaData.getColumnCount();
    		int i;
    		System.out.println("");
    		for (i = 1; i <= numCols; i++) {
    			if (i > 1) System.out.print("\t\t\t\t");
    			System.out.print (metaData.getColumnLabel(i));
    		}
    		System.out.println("");
    		System.out.println("");
    		
    		boolean more = resultSet.next();
    		while (more) {
    			for (i = 1; i <= numCols; i++) {
    				 System.out.print  (resultSet.getString(i) + "\t\t\t\t" );
    			}
    			System.out.println("");
    			more = resultSet.next();
    		}
    		resultSet.close();
    		System.out.println("");
    } catch (SQLException exception) {
    	System.out.println ("\n*** SQLException caught ***\n");
    	while (exception != null) {
    		System.out.println("SQLState:   " + exception.getSQLState());
    		System.out.println("Message:    " + exception.getMessage());
    		System.out.println("Error code: " + exception.getErrorCode());
    		
    		exception = exception.getNextException();
    		System.out.println("");
    	}
     } catch (java.lang.Exception exception) {
    	 exception.printStackTrace();
     }
    }	
    
    public void close(boolean remove) {                                                       // drops the table and properly closes the database
        try
        {
            if (remove) 
            {
                statement.execute("drop table PhoneBook;");
            }
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


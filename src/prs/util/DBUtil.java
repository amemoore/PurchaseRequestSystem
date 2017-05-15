package prs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static Connection connection;
    
    private DBUtil() {}

    public static synchronized Connection getConnection()  {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://localhost:3306/prs";
                String username = "prs_user";
                String password = "sesame";

                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
            	} 
            catch (SQLException e) {
                System.out.println(e);
            	} 
        return connection;
    }
    
    public static synchronized void closeConnection()  {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            	System.out.println(e);
            } finally {
                connection = null;                
            }
        }
    }
}




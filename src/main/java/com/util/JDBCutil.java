package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCutil {
	private static Connection connection = null;
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        // Step 1: Reading application.properties file
      
    if(connection == null) {
        // Open the properties file
//        FileInputStream fis = new FileInputStream("aplication.properties");
//        
//        Properties dbConfig = new Properties();
//        dbConfig.load(fis);
//        
//        // Retrieve database configuration properties
//        String url = dbConfig.getProperty("url");
//        String username = dbConfig.getProperty("username");
//        String password = dbConfig.getProperty("password");
//        String driver = dbConfig.getProperty("driver");

        // Load the database driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection using the properties
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COLLEGE", "root", "dikshapatil@00");
    	}
        return connection;
        
    }
    
    public static void closeConnection(){
    	if(connection != null) {
    		try {
				connection.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
    	}
    }
  

}
    
   

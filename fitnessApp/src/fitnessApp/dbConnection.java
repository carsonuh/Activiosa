package fitnessApp;

import java.awt.List;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;  


public class dbConnection {
	 	

	 // JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:~/fitnessDB";  
	   
	   //  Database credentials 
	   static final String USER = "root"; 
	   static final String PASS = ""; 
	   
	   
	   protected static Connection conn; 
	   protected static Statement stmt;
			   
			   
	   
	public dbConnection() {
	}
	
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public Statement getStmt() {
		return stmt;
	}
	
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	  // Connect to database
	   public void connect() { 
	         try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				setConn(conn);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Could not connect to database");
			}
	         System.out.println("Connected to database...");
	   } 
	   
	   // Close and shutdown database connection
	   public void shutdown() {
	         try {
				stmt.close();
				conn.close(); 
			
			} catch (SQLException e) {
				
			} 
	         finally { 
		        
		         try{ 
		            if(stmt!=null) stmt.close(); 
		         } catch(SQLException se2) { 
		         } // nothing we can do 
		         try { 
		            if(conn!=null) conn.close(); 
		         } catch(SQLException se){ 
		            se.printStackTrace(); 
		         } //end finally try 
	         
	         }
	     	System.out.println("Connection closed");
	   	}
	   
	   
	   
	
		
	   
	   
}


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


public class db {
	 	

	 // JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:~/fitnessDB";  
	   
	   //  Database credentials 
	   static final String USER = "root"; 
	   static final String PASS = ""; 
	   
	   
	   private Connection conn; 
	   private Statement stmt;
			   
			   
	   
	public db() {
	}
	
	// INSERT USERS
	public void insertUsers(String username, String password) throws NoSuchAlgorithmException {
		password = passwordEncrypt(password);
		String sql = "INSERT INTO USERS(username, password) VALUES('"+username+"',"+"'"+password+"')";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
		}
	}
	
	//INSERT ACCOUNT INFO
	public void insertAccountInfo(int id, String name, int feet, double inches, double currentWeight) {
		
		String sql = "INSERT INTO ACCOUNTINFO(name, height_feet, height_inches, current_weight)" +
		"VALUES('"+name+"', '"+feet+"', '"+inches+"', '"+ currentWeight+"')" +
		"WHERE user_id="+id;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
			//e.printStackTrace();
		}
	}
	
	
	//INSERT WEIGHT
	public void insertWeight(int id, Date date, double weight) {
		double bmi = 0.00;
		String sql = "INSERT INTO WEIGHT(date, weight, bmi)" +
		"VALUES('"+date+"', '"+weight+"', '"+bmi+"')" +
		"WHERE user_id="+id;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
			//e.printStackTrace();
		}
	}
	
	// UPDATE USERS
	public void updateUsers(int id, String username, String password) throws NoSuchAlgorithmException {
		password = passwordEncrypt(password);
		String sql = "UPDATE USERS SET username='"+username+"', password='"+password+
		"' WHERE id="+id;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't update....");
			//e.printStackTrace();
		}
	}
	
	
	// Get account info
		public void getAccountInfoAll(int id) {
			
			String sql ="SELECT name, height_feet, height_inches, current_weight FROM ACCOUNTINFO WHERE userid="+id;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String name = rs.getString("name");
					int feet = rs.getInt("height_feet");
					double inches = rs.getDouble("height_inches");
					double currentWeight = rs.getDouble("current_weight");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Didn't insert....\n" + sql);
				//e.printStackTrace();
			}
			
		
			
		}
		
		
	  // Connect to database
	   public void connect() { 
	         try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Could not connect to database");
			}
	         System.out.println("Connecting to database...");
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
	   
	   // Encrypt users password
	   public String passwordEncrypt(String password) throws NoSuchAlgorithmException {
		   MessageDigest digest = MessageDigest.getInstance("SHA-1");
		   byte[] result = digest.digest(password.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
	   }
}


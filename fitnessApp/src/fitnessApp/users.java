package fitnessApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the Users Table in 
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class users extends dbConnection {

	/** Database driver class that connections to the embedded database */
	static dbConnection db = new dbConnection();
	
	/** User ID */
	protected static int userID = 1;

	/** Username */
	protected static String username;
	
	/** If a user exists */
	protected static boolean exists;
	
	/** Password */
	protected static String password;	   
	
	public users() {
	
	}
	
	
	/*******************************************************************************************************
	 * Inserts data into the Users table
	 * @param username 
	 * @param password
	 * @throws NoSuchAlgorithmException
	 * @exception SQLException could not execute SQL statement
	 *******************************************************************************************************/
	public void insert(String username, String password) throws NoSuchAlgorithmException {
		
		password = passwordEncrypt(password);
		String sql = "INSERT INTO USERS(username, password) VALUES('"+username+"',"+"'"+password+"')";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
		}
		db.shutdown();
	}
	
	/********************************************************************************************************
	 * Gets data from the Users table. The data is set to local variables
	 * @exception SQLException could not execute SQL statement
	 ********************************************************************************************************/
	public static void get() {
		String sql = "SELECT username FROM USERS WHERE id="+userID;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				username = rs.getString("username");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't update....");
			//e.printStackTrace();
		}
	}
	
	/**********************************************************************************************************
	 * Updates the password based on the username
	 * @param name username
	 * @exception SQLException could not execute SQL statement
	 **********************************************************************************************************/
	public static void getByUsername(String name) {
		String sql = "SELECT username, password FROM USERS WHERE username="+name;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				username = rs.getString("username");
				password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't update....");
			//e.printStackTrace();
		}
	}
	
	/********************************************************************************************************
	 * If login is sucessful, mean password and username match. Then update the
	 * local variables.
	 * @param name username
	 * @exception SQLException could not execute SQL statement
	 ********************************************************************************************************/
	public static void loginSuccessGet(String name) {
		String sql = "SELECT username, id FROM USERS WHERE username="+name;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				username = rs.getString("username");
				userID = rs.getInt("id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't update....");
			//e.printStackTrace();
		}
	}
	
	/*******************************************************************************************************
	 * Updates the Users table with the passed values
	 * @param username username
	 * @param password password
	 * @throws NoSuchAlgorithmException
	 * @exception SQLException could not execute SQL statement
	 *******************************************************************************************************/
	public void update(String username, String password) throws NoSuchAlgorithmException {
		password = passwordEncrypt(password);
		String sql = "UPDATE USERS SET username='"+username+"', password='"+password+
		"' WHERE id="+userID;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't update....");
			//e.printStackTrace();
		}
	}
	
	/*******************************************************************************************************
	 * Gets the users password. Used to check against the entered password at login
	 * @param uname username
	 * @exception SQLException could not execute SQL statement
	 *******************************************************************************************************/
	public static void getPassword(String uname) {
		String sql = "SELECT password FROM USERS WHERE username='"+uname+"'";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	/*********************************************************************************************************
	 * Checks if a user already exists. This is used when a new user is created
	 * so that duplicate users are not created.
	 * @param uname username
	 * @exception SQLException could not execute SQL statement
	 *********************************************************************************************************/
	public static void checkIfExists(String uname) {
		String sql = "SELECT 1 FROM USERS WHERE username='"+uname+"'";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				exists = true;
			}
			else {
				exists = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	/**********************************************************************************************************
	 * Return an encrypt form of the users password. Using SHA-1
	 * @param password password
	 * @return String encrypted password
	 * @throws NoSuchAlgorithmException
	 * @exception SQLException could not execute SQL statement
	 **********************************************************************************************************/
	   public static String passwordEncrypt(String password) throws NoSuchAlgorithmException {
		   MessageDigest digest = MessageDigest.getInstance("SHA-1");
		   byte[] result = digest.digest(password.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
	   }
}

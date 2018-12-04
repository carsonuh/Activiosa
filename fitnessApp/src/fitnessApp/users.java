package fitnessApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class users extends dbConnection {

	dbConnection db = new dbConnection();
	protected static int userID = 1;
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
			   
	
	public users() {
	
	}
	
	
	// INSERT USERS
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
	
	
	//UPDATE USERS
	public void update(String username, String password) throws NoSuchAlgorithmException {
		password = passwordEncrypt(password);
		String sql = "UPDATE USERS SET username='"+username+"', password='"+password+
		"' WHERE id="+getUserID();
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
	
	 
	 //TODO: GET USERS
	
}

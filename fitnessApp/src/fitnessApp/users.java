package fitnessApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class users extends dbConnection {

	
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
	
	
	//UPDATE USERS
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
	
	 //TODO: DELETE USERS
	 
	 //TODO: GET USERS
	
}

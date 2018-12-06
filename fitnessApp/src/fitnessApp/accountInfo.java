package fitnessApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the AccountInfo Table in 
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class accountInfo extends dbConnection {

	   /** Database driver class that connections to the embedded database */
	   static dbConnection db = new dbConnection();
	   
	   /** Name of user */
	   protected static String name;
	   
	   /** Height in feet of user */
	   protected static int feet;
	   
	   /** Height in inches of user */
	   protected static int inches;
	   
	   /** Current weight of user */
	   protected static double currentWeight;
	   
		   
	   /**********************************************************************************************************
		* Inserts data into the accountInfo table in the database
		* @param name name of user
		* @param feet feet of user
		* @param inches inches of user
		* @param currentWeight current weight of user
		**********************************************************************************************************/
		public static void insert(String name, int feet, int inches, double currentWeight) {
			
			String sql = "INSERT INTO ACCOUNTINFO(user_id, name, height_feet, height_inches, current_weight)" +
			"VALUES('"+users.userID+"', '"+name+"', '"+feet+"', '"+inches+"', '"+currentWeight+"')";
			try {
				db.connect();
				conn = db.getConn();
				db.setStmt(conn.createStatement());
				stmt = db.getStmt();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Didn't insert....\n" + sql);
				//e.printStackTrace();
			}
			db.shutdown();
		}
		
		/********************************************************************************************************
		 * Updates the AccountInfo in the database with the values of the local variables if they have changed
		 ********************************************************************************************************/
		public  static void update() {
			String sql = "UPDATE ACCOUNTINFO set name='"+name+"', height_feet="+feet+", height_inches="+inches+", current_weight="+currentWeight+""
						 + "WHERE user_id="+users.userID;
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
			db.shutdown();
		}

	/***********************************************************************************************************
	 * Retrieves the fields in AccountInfo table to local variables that can be used when called in the GUI
	 ***********************************************************************************************************/
	public static void get() {
		String sql = "SELECT name, height_feet, height_inches, current_weight FROM ACCOUNTINFO " +
					"WHERE user_id="+users.userID ;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			name = rs.getString("name");
			feet = rs.getInt("height_feet");
			inches = rs.getInt("height_inches");
			currentWeight = rs.getDouble("current_weight");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
	
		
}

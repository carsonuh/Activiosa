package fitnessApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class accountInfo extends dbConnection {

	dbConnection db = new dbConnection();
	
	   protected static String name;
	   protected static int feet;
	   protected static int inches;
	   protected static double currentWeight;
	   
	   users user = new users();
	   
	   public void setName(String name) {
		   this.name = name;
	   }
	   
	   public String getName() {
		   return name;
	   }
	   
	   public void setFeet(int feet) {
		   this.feet = feet;
	   }
	   
	   public int getFeet() {
		   return feet;
	   }
	   
	   public void setInches(int inches) {
		   this.inches = inches;
	   }
	   
	   public int getInches() {
		   return inches;
	   }
	   
	   public void setCurrentWeight(double currentWeight) {
		   this.currentWeight = currentWeight;
	   }
			   
	   public double getCurrentWeight() {
		   return currentWeight;
	   }
	//INSERT ACCOUNT INFO
		public void insert(String name, int feet, int inches, double currentWeight) {
			
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
		
		
	//TODO: UPDATE ACCOUNT INFO
		public void update() {
			String sql = "UPDATE ACCOUNTINFO set name='"+name+"', height_feet='"+feet+"', height_inches='"+inches+"', current_weight='"+currentWeight+"'"
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

	public void get() {
		String sql = "SELECT name, height_feet, height_inches, current_weight FROM ACCOUNTINFO " +
					"WHERE id="+users.userID ;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			setName(rs.getString("name"));
			setFeet(rs.getInt("height_feet"));
			setInches(rs.getInt("height_inches"));
			setCurrentWeight(rs.getDouble("current_weight"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
		
}

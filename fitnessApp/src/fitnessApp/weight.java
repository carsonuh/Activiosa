package fitnessApp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class weight extends dbConnection {
	  
	 	static dbConnection db = new dbConnection();
		static accountInfo info = new accountInfo();
		protected static double weight;
		protected static Date date;
		protected static double bmi;
		protected static double bwp;
		protected static Map<String, Double> weeklyData = new HashMap<String, Double>();
		
		public static void setup() {
			weeklyData.put("SUNDAY", null);
			weeklyData.put("MONDAY", null);
			weeklyData.put("TUESDAY", null);
			weeklyData.put("WEDNESDAY", null);
			weeklyData.put("THURSDAY", null);
			weeklyData.put("FRIDAY", null);
			weeklyData.put("SATURDAY", null);
		}

	//INSERT WEIGHT
		public static void insertWeight(int id, Date date, double weight) {
			double bmi = 0.00;
			String sql = "INSERT INTO WEIGHT(user_id, date, weight, bmi)" +
			"VALUES('"+users.userID+"', '"+date+"', '"+weight+"', '"+bmi+"')";
			
			try {
				db.connect();
				conn = db.getConn();
				db.setStmt(conn.createStatement());
				stmt = db.getStmt();
				stmt.executeUpdate(sql);
				accountInfo.currentWeight = weight;
				info.update();
			} catch (SQLException e) {
 				// TODO Auto-generated catch block
				System.out.println("Didn't insert....\n" + sql);
				//e.printStackTrace();
			}
			db.shutdown();
		}
		
	//TODO: GET WEIGHT
	public static void get() {
		String sql = "SELECT date, weight, bmi FROM WEIGHT WHERE user_id="+users.userID;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			date = rs.getDate("date");
			weight = rs.getDouble("weight");
			bmi = rs.getDouble("bmi");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	public static void getWeek() {
		String sql = "SELECT weight, date FROM WEIGHT WHERE user_id="+users.userID 
				+ " AND date BETWEEN '"+DateMaker.weekStart()+"' AND '" + DateMaker.weekEnd()+"'";
	
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			setup();
			while(rs.next()) {
			weeklyData.put(DateMaker.dayOfWeek(rs.getString("date")), rs.getDouble("weight"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not insert....\n" + sql);
			
			e.printStackTrace();
		}
		db.shutdown();
	}
}

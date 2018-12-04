package fitnessApp;

import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class stepsTable extends dbConnection {

	static dbConnection db = new dbConnection();
	protected static int steps;
	protected static int floors;
	protected static double milesWalked;
	protected static Date date;
	
	protected static Map<String, Integer> weeklySteps = new HashMap<String, Integer>();
	
	public static void setup() {
		weeklySteps.put("SUNDAY", null);
		weeklySteps.put("MONDAY", null);
		weeklySteps.put("TUESDAY", null);
		weeklySteps.put("WEDNESDAY", null);
		weeklySteps.put("THURSDAY", null);
		weeklySteps.put("FRIDAY", null);
		weeklySteps.put("SATURDAY", null);
	}
	
	
	
	public static void insert(String date, int steps, int floors, double miles) {
		String sql = "INSERT INTO STEPS(user_id, date, steps, floors, miles)" + 
		"VALUES ('"+users.userID+"', PARSEDATETIME('"+date+"','yyyy-MM-dd')" +", " +steps+", "+floors+","+miles+")";
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
	
	public static void update() {
		String sql = "UPDATE TABLE steps SET steps="+steps+", floors="+floors+", miles="+milesWalked +
				"WHERE user_id="+users.userID+"' AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
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
	
	
	public static void get() {
		String sql = "SELECT steps, floors, miles FROM STEPS WHERE user_id='"+users.userID+"' AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			steps = rs.getInt("steps");
			floors = rs.getInt("floors");
			milesWalked = rs.getDouble("miles");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	public static void getWeek() {
		String sql = "SELECT steps, date FROM STEPS WHERE user_id="+users.userID +
				" AND date BETWEEN '"+DateMaker.weekStart()+"' AND '" + DateMaker.weekEnd()+"'";
	
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			setup();
			while(rs.next()) {
			
			weeklySteps.put(DateMaker.dayOfWeek(rs.getString("date")), rs.getInt("steps"));
		
			
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't insert....\n" + sql);
			//e.printStackTrace();
		}
		db.shutdown();
	}
	
}

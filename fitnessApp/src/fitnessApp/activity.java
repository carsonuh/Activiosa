package fitnessApp;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.sql.ResultSet;

public class activity extends dbConnection {
	static dbConnection db = new dbConnection();
	
	protected static Date date;
	protected static String type;
	protected static Time duration;
	protected static double distance;
	protected static double calsBurned;
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
	
	activity(){}
	
	
	//TODO insert
	public static void insert(String date, String type, String duration, double distance, double calsBurned) {
		String sql = "INSERT INTO EXERCISE(user_id, date, type, duration, distance, cals_burned)" +
				" VALUES("+users.userID+", PARSEDATETIME('"+date+"','yyyy-MM-dd'), '"+type+"' , PARSEDATETIME('"+duration+"','HH:mm:ss')"+", "+distance+", "+calsBurned+")";
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
	
	//TODO update
	
	//TODO get
	public static void getWeek(String activity) {
		String sql = "SELECT distance, date FROM EXERCISE WHERE user_id="+users.userID 
				+ " AND type='"+activity+"'"
				+ " AND date BETWEEN '"+DateMaker.weekStart()+"' AND '" + DateMaker.weekEnd()+"'";
	
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			setup();
			while(rs.next()) {
			weeklyData.put(DateMaker.dayOfWeek(rs.getString("date")), rs.getDouble("distance"));
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
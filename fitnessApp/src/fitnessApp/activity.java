package fitnessApp;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.sql.ResultSet;

/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the EXERCISE Table in 
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class activity extends dbConnection {
	
	/** Database driver class that connections to the embedded database */
	static dbConnection db = new dbConnection();
	
	/** Date of occurrence */
	protected static Date date;
	
	/** Type of activity */
	protected static String type;
	
	/** Length of activity */
	protected static Time duration;
	
	/** Distance of activity */
	protected static double distance;
	
	/** Calories burned */
	protected static double calsBurned;
	
	/** Weekly data */
	protected static Map<String, Double> weeklyData = new HashMap<String, Double>();
	
	/** Initilizes weekly data with default values of null */
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
	
	
	/**************************************************************************************
	 * Inserts data the passed parameters into the Exercise table
	 * @param date date of occurence in format yyyy-MM-dd
	 * @param type type of activity
	 * @param duration length of activity in miles
	 * @param distance distance of activtity in the format HH:mm:ss
	 * @param calsBurned amount of calories burned
	 * @exception SQLException could not execute SQL statement
	 ***************************************************************************************/
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
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	/*********************************************************************************************
	 * Gets data for the current week from the Exercise table based on the activity that is passed
	 * @param activity type of activity
	 * @exception SQLException could not execute SQL statement
	 *********************************************************************************************/
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
			e.printStackTrace();
		}
		db.shutdown();
	}
}
package fitnessApp;

import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the Steps Table in 
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class stepsTable extends dbConnection {
	
	/** Database driver class that connections to the embedded database */
	static dbConnection db = new dbConnection();
	
	/** Amount of steps */
	protected static int steps;
	
	/** Amount of floors */
	protected static int floors;
	
	/** Amount of miles walked */
	protected static double milesWalked;
	
	/* Date of occurrence */
	protected static Date date;
	
	/** Weekly steps per day */
	protected static Map<String, Integer> weeklySteps = new HashMap<String, Integer>();
	
	/*********************************************************************************************
	 * Initializes the Map to default values of null
	 *********************************************************************************************/
	public static void setup() {
		weeklySteps.put("SUNDAY", null);
		weeklySteps.put("MONDAY", null);
		weeklySteps.put("TUESDAY", null);
		weeklySteps.put("WEDNESDAY", null);
		weeklySteps.put("THURSDAY", null);
		weeklySteps.put("FRIDAY", null);
		weeklySteps.put("SATURDAY", null);
	}
	
	
	/************************************************************************************************
	 * Inserts data into the Steps Table
	 * @param date date of occurrence
	 * @param steps number of steps
	 * @param floors number of floors
	 * @param miles number of miles
	 * @exception SQLException could not execute SQL statement
	 ************************************************************************************************/
	public static void insert(String date, int steps, int floors, double miles) {
		String sql = "INSERT INTO STEPS(user_id, date, steps, floors, miles)" + 
		" VALUES ('"+users.userID+"', PARSEDATETIME('"+date+"','yyyy-MM-dd')" +", " +steps+", "+floors+","+miles+")";
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
	
	/************************************************************************************************
	 * Updates the Steps table with the local variables
	 * @exception SQLException could not execute SQL statement
	 ************************************************************************************************/
	public static void update() {
		String sql = "UPDATE steps SET steps="+steps+", floors="+floors+", miles="+milesWalked +
				" WHERE user_id="+users.userID+" AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
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
	
	/************************************************************************************************
	 * Gets data from the Steps table. The data is set to the corresponding local variables
	 * @exception SQLException could not execute SQL statement
	 ************************************************************************************************/
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
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	/**************************************************************************************************
	 * Gets data from the Steps table and populates the weekly steps if there is a value for that day
	 * @exception SQLException could not execute SQL statement
	 **************************************************************************************************/
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
			e.printStackTrace();
		}
		db.shutdown();
	}
	
}

package fitnessApp;

import java.sql.Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the Weight Table in 
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/

public class weight extends dbConnection {

	 /** Database driver class that connections to the embedded database */
	static dbConnection db = new dbConnection();
	
	/** Weight of user */
	protected static double weight;
	
	/** Date of the data pulled */
	protected static Date date;
	
	/** BMI of the user */
	protected static double bmi = 0;
	
	/** Body weight percentage */
	protected static double bwp;
	
	/** Weekly Data Map. Data is stored per day for weight */
	protected static Map<String, Double> weeklyData = new HashMap<String, Double>();

	/******************************************************************************************************
	 * Initilizies weekly data with default values of null
	 ******************************************************************************************************/
	public static void setup() {
		weeklyData.put("SUNDAY", null);
		weeklyData.put("MONDAY", null);
		weeklyData.put("TUESDAY", null);
		weeklyData.put("WEDNESDAY", null);
		weeklyData.put("THURSDAY", null);
		weeklyData.put("FRIDAY", null);
		weeklyData.put("SATURDAY", null);
	}

	/*******************************************************************************************************
	 * Inserts values into the Weight table
	 * @param date   date when the weight was taken
	 * @param weight weight in pounds
	 * @exception SQLException could not execute SQL statement
	 *******************************************************************************************************/
	public static void insertWeight(String date, double weight) {
		double bmi = 0.00;
		String sql = "INSERT INTO WEIGHT(user_id, date, weight, bmi)" + "VALUES(" + users.userID + ", PARSEDATETIME('"
				+ date + "','yyyy-MM-dd')" + ", " + weight + ", " + bmi + ")";

		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			stmt.executeUpdate(sql);
			accountInfo.currentWeight = weight;
			accountInfo.update();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		db.shutdown();
	}

	/*******************************************************************************************************
	 * Gets values from the Weight table for today's date. The data received is set equal to the local 
	 * variables that correspond to the column name
	 * @exception SQLException could not execute SQL statement
	 *******************************************************************************************************/
	public static void get() {
		String sql = "SELECT date, weight, bmi FROM WEIGHT " + "WHERE user_id=" + users.userID + " AND date='"
				+ DateMaker.ToSQLDate(DateMaker.Today()) + "'"; 
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				date = rs.getDate("date");
				weight = rs.getDouble("weight");
				bmi = rs.getDouble("bmi");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.shutdown();
	}

	/******************************************************************************************************
	 * Gets values from the Weight table for the current week. The data received is added to the Map
	 * data structure holds the information for each day
	 * @exception SQLException could not execute SQL statement
	 ******************************************************************************************************/
	public static void getWeek() {
		String sql = "SELECT weight, date FROM WEIGHT WHERE user_id=" + users.userID + " AND date BETWEEN '"
				+ DateMaker.weekStart() + "' AND '" + DateMaker.weekEnd() + "'";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			setup();
			while (rs.next()) {
				weeklyData.put(DateMaker.dayOfWeek(rs.getString("date")), rs.getDouble("weight"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.shutdown();
	}
}

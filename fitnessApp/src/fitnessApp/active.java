package fitnessApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/****************************************************************************************************
 * This class extends the dbConnection class to connect to the Active Table in
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/2018
 ****************************************************************************************************/
public class active extends dbConnection {

	/** Database driver class that connections to the embedded database */
	static dbConnection db = new dbConnection();

	/** Minutes active */
	protected static int minutes;

	/** Calories burned */
	protected static int calsBurned;

	/** Number of floors */
	protected static int floors;

	/** Date of occurrence */
	protected static SimpleDateFormat date;

	/*****************************************************************************************************
	 * Inserts values in the Active table
	 * 
	 * @param date       date of occurrence
	 * @param minutes    number of active minutes
	 * @param calsBurned calories burned
	 * @exception SQLException could not execute SQL statement
	 *****************************************************************************************************/
	public static void insert(String date, int minutes, int calsBurned) {
		String sql = "INSERT INTO ACTIVE (user_id, date, mins, cals_burned)" + "VALUES('" + users.userID
				+ "', PARSEDATETIME('" + date + "','yyyy-MM-dd')" + ", '" + minutes + "', '" + calsBurned + "')";
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

	/*********************************************************************************************************
	 * Updates the local variables into the Active table to the corresponding column
	 * 
	 * @exception SQLException could not execute SQL statement
	 *********************************************************************************************************/
	public static void update() {
		String sql = "UPDATE ACTIVE SET mins=" + minutes + ", floors=" + floors + ", cals_burned=" + calsBurned
				+ "WHERE user_id=" + users.userID + " AND date=PARSEDATETIME('" + DateMaker.Today() + "','yyyy-MM-dd')";
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

	/***********************************************************************************************************
	 * Gets the values from the Active table. Data retrieved is set equal to the
	 * local variables that correspond
	 * 
	 * @exception SQLException could not execute SQL statement
	 ***********************************************************************************************************/
	public static void get() {
		String sql = "SELECT mins, floors, cals_burned FROM ACTIVE WHERE user_id=" + users.userID
				+ " AND date=PARSEDATETIME('" + DateMaker.Today() + "','yyyy-MM-dd')";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				minutes = rs.getInt("mins");
				floors = rs.getInt("floors");
				calsBurned = rs.getInt("cals_burned");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.shutdown();
	}
}

package fitnessApp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the Meal Table in 
 * the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class meals extends dbConnection{

	/** Database driver class that connections to the embedded database */
	static dbConnection db = new dbConnection();
	
	/** Date of occurrence */
	protected static Date date;
	
	/** Amount of calories eaten for breakfast */
	protected static int breakfastCals;
	
	/** Amount of calories eaten for lunch */
	protected static int lunchCals;
	
	/** Amount of calories eaten for dinner */
	protected static int dinnerCals;
	
	/** Amount of calories eaten for snacks */
	protected static int snackCals;
	
	/** Total calories */
	protected static int totalCals;
	
	/** If a row exists */
	protected static boolean exists;
	
	/******************************************************************************************************************
	 * Inserts the passed values into the Meals table
	 * @param breakfast calories for breakfast
	 * @param lunch calories for lunch
	 * @param dinner calories for dinner
	 * @param snacks calories for snacks 
	 * @exception SQLException could not execute SQL statement
	 ******************************************************************************************************************/
	public static void insert(int breakfast, int lunch, int dinner, int snacks) {
		String sql = "INSERT INTO MEALS(user_id, date, breakfast, lunch, dinner, snacks)" +
		" VALUES("+users.userID+", PARSEDATETIME('"+DateMaker.Today()+"','yyyy-MM-dd')" +", " + breakfast+", " +lunch+ ", "+dinner+", "+snacks+")";
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
	
	/********************************************************************************************************************
	 * Updates the Meals table with the corresponding local variables 
	 * @exception SQLException could not execute SQL statement
	 ********************************************************************************************************************/
	public static void update() {
		String sql = "UPDATE meals SET breakfast="+breakfastCals+", lunch="+lunchCals+", dinner="+dinnerCals +
				", snacks="+snackCals + " WHERE user_id="+users.userID+" AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
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
	
	/******************************************************************************************************************
	 * Checks if a meal has already been inputed for today
	 * @exception SQLException could not execute SQL statement
	 ******************************************************************************************************************/
	public static void checkIfExists() {
		String sql = "SELECT 1 FROM MEALS WHERE date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				exists = true;
			}
			else {
				exists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.shutdown();
	}
	
	/******************************************************************************************************************
	 * Gets data from the Meals table. The data is then set to the corresponding local variables
	 * @exception SQLException could not execute SQL statement
	 ******************************************************************************************************************/
	public static void get() {
		String sql = "SELECT breakfast, lunch, dinner, snacks FROM MEALS" + 
				" WHERE user_id="+users.userID+" AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			breakfastCals = rs.getInt("breakfast");
			lunchCals = rs.getInt("lunch");
			dinnerCals = rs.getInt("dinner");
			snackCals = rs.getInt("snacks");
			totalCals  = breakfastCals + lunchCals + dinnerCals + snackCals;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
}

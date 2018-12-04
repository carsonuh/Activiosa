package fitnessApp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class meals extends dbConnection{

	static dbConnection db = new dbConnection();
	protected static Date date;
	protected static int breakfastCals;
	protected static int lunchCals;
	protected static int dinnerCals;
	protected static int snackCals;
	protected static int totalCals = breakfastCals + lunchCals + dinnerCals + snackCals;
	
	//TODO: INSERT MEALS
	public static void insert(String date, int breakfast, int lunch, int dinner, int snacks) {
		String sql = "INSERT INTO MEALS(user_id, date, breakfast, lunch, dinner, snacks)" +
		" VALUES("+users.userID+", PARSEDATETIME('"+date+"','yyyy-MM-dd')" +", " + breakfast+", " +lunch+ ", "+dinner+", "+snacks+")";
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
		String sql = "UPDATE TABLE meals SET breakfast="+breakfastCals+", lunch="+lunchCals+", dinner="+dinnerCals +
				", snacks="+snackCals + "WHERE user_id="+users.userID+"' AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
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
		String sql = "SELECT breakfast, lunch, dinner, snacks FROM MEALS" + 
				"WHERE user_id="+users.userID+"' AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
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
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
}

package fitnessApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class active extends dbConnection{
	static dbConnection db = new dbConnection();
	
	protected static int minutes;
	protected static int calsBurned;
	protected static int floors;
	protected static SimpleDateFormat date;
	
	public static void insert(String date, int minutes, int calsBurned) {
		String sql = "INSERT INTO ACTIVE (user_id, date, mins, cals_burned)" +
	    "VALUES('"+users.userID+"', PARSEDATETIME('"+date+"','yyyy-MM-dd')" + ", '"+minutes+"', '"+calsBurned+"')";
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
		String sql = "UPDATE ACTIVE SET mins="+minutes+", floors="+floors+", cals_burned="+calsBurned +
				"WHERE user_id="+users.userID+" AND date=PARSEDATETIME('"+DateMaker.Today()+"','yyyy-MM-dd')";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Didn't update....\n" + sql);
			//e.printStackTrace();
		}
		db.shutdown();
	}
	
	public static void get() {
		String sql = "SELECT mins, floors, cals_burned FROM ACTIVE WHERE user_id="+users.userID +" AND date=PARSEDATETIME('"+DateMaker.Today()+"','yyyy-MM-dd')";
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			minutes = rs.getInt("mins");
			floors = rs.getInt("floors");
			calsBurned = rs.getInt("cals_burned");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutdown();
	}
}

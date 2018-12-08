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
	 dbConnection db = new dbConnection();
	
	 BmiCalc bmiCalc;
	 BwpCalc bwpCalc;
	
	/** Weight of user */
	protected double weight;

	/** BMI of the user */
	protected double bmi;
	
	/** Body weight percentage */
	protected double bwp;
	
	/** Weekly Data Map. Data is stored per day for weight */
	protected Map<String, Double> weeklyData = new HashMap<String, Double>();
	
	protected boolean exists;

	/******************************************************************************************************
	 * Initilizies weekly data with default values of null
	 ******************************************************************************************************/
	private void setup() {
		weeklyData.put("SUNDAY", null);
		weeklyData.put("MONDAY", null);
		weeklyData.put("TUESDAY", null);
		weeklyData.put("WEDNESDAY", null);
		weeklyData.put("THURSDAY", null);
		weeklyData.put("FRIDAY", null);
		weeklyData.put("SATURDAY", null);
	}
	
	
	weight() {}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getBmi() {
		return bmi;
	}


	public void setBmi(double bmi) {
		this.bmi = bmi;
	}


	public double getBwp() {
		return bwp;
	}


	public void setBwp(double bwp) {
		this.bwp = bwp;
	}
	
	public boolean getExists( ) {
		return exists;
	}

	public void updateBodyWeight(Double w_lbs) {
		bmiCalc.setW_lbs(w_lbs);
		bwpCalc.setCurrent(w_lbs);
	}
	
	public double getCurrBmi() {
		return bmiCalc.bmiImperial();
	}
	
	public double getCurrBwp() {
		return bwpCalc.percentageCalc();
	}
	
	
	/*******************************************************************************************************
	 * Inserts values into the Weight table
	 * @param date   date when the weight was taken
	 * @param weight weight in pounds
	 * @exception SQLException could not execute SQL statement
	 *******************************************************************************************************/
	public void insertWeight(String date, double weight) {
		bmiCalc = new BmiCalc(weight, accountInfo.feet, accountInfo.inches);
		bmiCalc.bmiImperial();
		bmi = bmiCalc.getBmi_i();
		
		bwpCalc = new BwpCalc(accountInfo.currentWeight, weight);
		bwpCalc.setCurrent(weight);
		bwpCalc.setInitial(accountInfo.currentWeight);
		bwpCalc.percentageCalc();
		bwp = bwpCalc.getPercentage();
		
		String sql = "INSERT INTO WEIGHT(user_id, date, weight, bmi, bwp)" + " VALUES(" + users.userID + ", PARSEDATETIME('"
				+ date + "','yyyy-MM-dd')" + ", " + weight + ", " + bmi + ", " + bwp +")";
		System.out.println(sql);
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
	public void get() {
		String sql = "SELECT weight, bmi, bwp FROM WEIGHT " + "WHERE user_id=" + users.userID + 
				" AND date='"+ DateMaker.ToSQLDate(DateMaker.Today()) + "'"; 
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				weight = rs.getDouble("weight");
				bmi = rs.getDouble("bmi");
				bwp = rs.getDouble("bwp");
				
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
	public void getWeek() {
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
	
	public boolean checkIfExists() throws SQLException {
		String sql = "SELECT * FROM WEIGHT WHERE user_id="+users.userID+" AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
		
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next() ) {
				return false;
			}
		db.shutdown();
		return true;
	}
	
	public void update() {
		String sql = "UPDATE WEIGHT SET weight="+weight+", bmi="+bmi+", bwp="+bwp +
				" WHERE user_id="+users.userID+" AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
		System.out.println(sql);
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
	
	public Double getWeightDB() {
		String sql = "SELECT weight FROM WEIGHT WHERE user_id="+users.userID + " AND date='"+DateMaker.ToSQLDate(DateMaker.Today())+"'";
		Double w = 0.0;
		try {
			db.connect();
			conn = db.getConn();
			db.setStmt(conn.createStatement());
			stmt = db.getStmt();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				w = rs.getDouble("weight");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.shutdown();
		return w;
	}
}

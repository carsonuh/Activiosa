package fitnessApp;

import java.awt.List;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*************************************************************************************************************
 * This class extends the dbConnection class to connect to the AccountInfo Table
 * in the database
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class dbConnection {

	/** Database driver */
	static final String JDBC_DRIVER = "org.h2.Driver";

	/** URL to database */
	static final String DB_URL = "jdbc:h2:~/fitnessDB";

	/** username for the database login */
	static final String USER = "root";

	/** password for the database login */
	static final String PASS = "";

	/** Database connection */
	protected static Connection conn;

	/** Database statement */
	protected static Statement stmt;

	/**********************************************************************************************
	 * Class constructor
	 *********************************************************************************************/
	public dbConnection() {
	}

	/*********************************************************************************************
	 * Gets the connection of the database
	 * 
	 * @return conn database connection
	 *********************************************************************************************/
	public Connection getConn() {
		return conn;
	}

	/*********************************************************************************************
	 * Sets the connection for database
	 * 
	 * @param conn database connection
	 *********************************************************************************************/
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/********************************************************************************************
	 * Gets the statement
	 * 
	 * @return stmt database statement
	 ********************************************************************************************/
	public Statement getStmt() {
		return stmt;
	}

	/*******************************************************************************************
	 * Sets the statement
	 * 
	 * @param stmt database statement
	 *******************************************************************************************/
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	/******************************************************************************************
	 * Connects to the database using the Driver Class, URL, username, and password
	 * @exception ClassNotFoundException JDBC Driver not found
	 * @exception SQLException Connection to database could not be established 
	 ******************************************************************************************/
	public void connect() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			setConn(conn);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Could not connect to database");
		}
	}

	/*****************************************************************************************
	 * Close the connection to the database
	 * @exception SQLException Connection could not be closed
	 *****************************************************************************************/
	public void shutdown() {
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {

		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		}
	}

}

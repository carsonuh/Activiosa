package fitnessApp;

import java.sql.Date;
import java.sql.SQLException;

public class weight extends dbConnection {

	//INSERT WEIGHT
		public void insertWeight(int id, Date date, double weight) {
			double bmi = 0.00;
			String sql = "INSERT INTO WEIGHT(date, weight, bmi)" +
			"VALUES('"+date+"', '"+weight+"', '"+bmi+"')" +
			"WHERE user_id="+id;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Didn't insert....\n" + sql);
				//e.printStackTrace();
			}
		}
		
	//TODO: UPDATE WEIGHT
		
	//TODO: DELETE WEIGHT
		
	//TODO: GET WEIGHT
}

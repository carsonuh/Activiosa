package fitnessApp;

import java.sql.SQLException;

public class accountInfo extends dbConnection {

	//INSERT ACCOUNT INFO
		public void insertAccountInfo(int id, String name, int feet, double inches, double currentWeight) {
			
			String sql = "INSERT INTO ACCOUNTINFO(name, height_feet, height_inches, current_weight)" +
			"VALUES('"+name+"', '"+feet+"', '"+inches+"', '"+ currentWeight+"')" +
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
		
		
	//TODO: UPDATE ACCOUNT INFO
		
	//TODO: DELETE ACCOUNT INFO
		
	//TODO: GET ACCOUNT INFO
		
}

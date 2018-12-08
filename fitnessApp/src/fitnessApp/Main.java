package fitnessApp;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

/***
 * Main class
 * @author Carson Uecker-Herman
 *
 */
public class Main extends GUI {

	/***
	 * Launches the GUI
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		dbConnection db = new dbConnection();
		
		data.get();
		/*
		weight w = new weight();
		System.out.println("BEFORE INSERT");
		System.out.println("Current weight: " + accountInfo.currentWeight);
		System.out.println("Weight: " + w.weight);
		System.out.println("BMI: " + w.bmi);
		System.out.println("BWP: " + w.bwp);
		w.insertWeight("2018-12-7", 140);
		
		System.out.println("\nAFTER INSERT");
		System.out.println("Weight: " + w.weight);
		System.out.println("BMI: " + w.bmi);
		System.out.println("BWP: " + w.bwp);
		
		w.get();
		
		System.out.println("\nAFTER GET");
		System.out.println("Current weight: " + accountInfo.currentWeight);
		System.out.println("Weight: " + w.weight);
		System.out.println("BMI: " + w.bmi);
		System.out.println("BWP: " + w.bwp);
		*/
		
		
		launch(args);
		db.shutdown();
		
		
	} 
	
}
		
		
		
	



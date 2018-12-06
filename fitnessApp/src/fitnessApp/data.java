package fitnessApp;

/***
 * This class updates the updates and gets date from tables in the database
 * @author Carson Uecker-Herman
 * @version 12/5/2018
 */
public class data {
	
	/***
	 * Calls all get() for tables
	 */
	protected static void get() {
		stepsTable.get();
		stepsTable.getWeek();
		active.get();
		meals.get();
		accountInfo.get();
		weight.get();
	}
	
	/**
	 * Calls all update() for tables
	 */
	protected static void update() {
		stepsTable.update();
		active.update();
		meals.update();
		
	}
}

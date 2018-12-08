package calculations;
/**********************************************************************
 * This class adds together the user's input of calories.
 * 
 * The purpose of this class is to take from user input to combine all
 * entries into a total. The user will input the calories they've
 * consumed over a period of time (EX: During the user's average day) &
 * this class will add them together and then display a total when
 * prompted.
 * 
 * @author Spencer Keeton
 * @Version 11/27/18
 *********************************************************************/
public class CalTotal {
	/* This variable is the calorie total. */
	int calTotal;
	
	/* This variable is the new amount of calories being added. */
	int newCal;
	
	/******************************************************************
	 * This method is the constructor for both variables that are used.
	 * 
	 * @param calTotal
	 * 			The total amount of calories.
	 * @param newCal
	 * 			The new amount of calories to be added to the total.
	 *****************************************************************/
	public CalTotal(int calTotal, int newCal) {
		this.calTotal = calTotal;
		this.newCal = newCal;
	}

	/******************************************************************
	 * This method is the constructor for the new amount of
	 * calories that are to be added to the total. 
	 * 
	 * @param newCal
	 * 			The new amount of calories to be added to the total.
	 *****************************************************************/
	public CalTotal(int newCal) {
		this.newCal = newCal;
	}
	
	/******************************************************************
	 * This method gets the calorie total.
	 *
	 * @return calTotal
	 *****************************************************************/
	public int getCalTotal() {
		return calTotal;
	}

	/******************************************************************
	 * This method sets the calorie total.
	 * 
	 * @param calTotal
	 * 			The total amount of calories.
	 *****************************************************************/
	public void setCalTotal(int calTotal) {
		this.calTotal = calTotal;
	}

	/******************************************************************
	 * This method gets the new amount of calories to be added.
	 * 
	 * @return newCal
	 *****************************************************************/
	public int getNewCal() {
		return newCal;
	}

	/******************************************************************
	 * This method sets the new amount of calories to be added.
	 * 
	 * @param newCal
	 * 			The new amount of calories to be added to the total.
	 *****************************************************************/
	public void setNewCal(int newCal) {
		this.newCal = newCal;
	}
	
	public int findCalTotal() {
		if(newCal < 0)
			newCal = newCal * -1;
		
		calTotal = calTotal + newCal;
		return calTotal;
	}
	
	public int resetCalTotal() {
		calTotal = 0;
		return calTotal;
	}
}

package calculations;


/**********************************************************************
 * This class calculates the percentage of body weight gained or loss.
 * 
 * It uses the given initial and current weights in order to calculate
 * the percentage. A positive percentage indicates a gain of weight,
 * and a negative percentage indicates a loss of weight.
 *  
 * @author Spencer Keeton
 * @version 11/7/18
 *********************************************************************/
public class BwpCalc {
	
	/* This variable is the initial weight given. */
	double i;
	
	/* This variable is the current weight given. */
	double c;
	
	/*This variable is the calculated body weight percentage. */
	double p;
	
	/******************************************************************
	 * This method is the constructor for the BwpCalc class.
	 * 
	 * @param i
	 * 			The initial weight given.
	 * @param c
	 * 			The current weight given.
	 * @param p
	 * 			The calculated body weight percentage.
	 *****************************************************************/
	public BwpCalc(double i, double c) {
		this.i = i;
		this.c = c;
	}

	/******************************************************************
	 * This method gets the initial weight.
	 * 
	 * @return i
	 *****************************************************************/
	public double getInitial() {
		return i;
	}

	/******************************************************************
	 * This method sets the initial weight.
	 * 
	 * @param i
	 * 			The initial weight given.
	 *****************************************************************/
	public void setInitial(int i) {
		this.i = i;
	}

	/******************************************************************
	 * This method gets the current weight.
	 * 
	 * @return c
	 *****************************************************************/
	public double getCurrent() {
		return c;
	}

	/******************************************************************
	 * This method sets the current weight.
	 * 
	 * @param c
	 * 			The current weight given.
	 *****************************************************************/
	public void setCurrent(int c) {
		this.c = c;
	}

	/******************************************************************
	 * This method gets the body weight percentage.
	 * 
	 * @return p
	 *****************************************************************/
	public double getPercentage() {
		return p;
	}

	/******************************************************************
	 * This method sets the body weight percentage.
	 * 
	 * @param p
	 * 			The calculated body weight percentage.
	 *****************************************************************/
	public void setPercentage(int p) {
		this.p = p;
	}
	
	/******************************************************************
	 * This method calculates the percentage of body weight lost or
	 * gained.
	 * 
	 * NOTE: A negative percentage means that weight has been lost,
	 * while a positive percentage means that weight has been gained.
	 * 
	 * @param i
	 * 			The initial body weight given.
	 * @param c
	 * 			The current body weight given.
	 * @return b
	 * 			The calculated body percentage.
	 *****************************************************************/
	public double percentageCalc() {
		if(i < 0)
			i = i * -1;
		
		if(c < 0)
			c = c * -1;
		
		p = ((i-c)/i) * -100;
		if(p <= -100.0)
			return p + 100;
		else
			return p;
		
	}
}


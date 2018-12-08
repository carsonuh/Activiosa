package calculations;

/**********************************************************************
 * This class calculates the body mass index (BMI).
 * 
 * There are two different calculations: one for metric units & one for
 * imperial units. Both formulas use the given weight and height in
 * in order to calculate the BMI.
 * 
 * @author Spencer Keeton
 * @version 11/7/18
 *********************************************************************/
public class BmiCalc {
	/* This variable is the weight in kilograms (kg). */
	double w_kg;
	
	/* This variable is the height in meters (m). */
	double h_m;
	
	/* This variable is the weight in pounds (lbs). */
	double w_lbs;
	
	/* This variable is the height in feet (ft). */
	double h_ft;
	
	/* This variable is the weight in inches (in). */
	double h_in;
	
	/* This variable is the bmi result for metric calculations. */
	double bmi_m;
	
	/* This variable is the bmi result for imperial calculations. */
	double bmi_i;
	
	/******************************************************************
	 * This method is the constructor for the metric variables of the
	 * BmiCalc class.
	 * 
	 * @param w_kg
	 * 			The weight given (in kilograms).
	 * @param h_m
	 * 			The height given (in meters).
	 * @param bmi_m
	 * 			The BMI result for metric calculations.
	 *****************************************************************/
	public BmiCalc(double w_kg, double h_m) {
		this.w_kg = w_kg;
		this.h_m = h_m;
	}

	/******************************************************************
	 * This method is the constructor for the imperial variables of the
	 * BmiCalc class.
	 * 
	 * @param w_lbs
	 * 			The weight given (in pounds).
	 * @param h_ft
	 * 			The height given (in feet).
	 * @param h_in
	 * 			The height given (in inches).
	 * @param bmi_i
	 * 			The BMI result for imperial calculations.
	 *****************************************************************/
	public BmiCalc(double w_lbs, double h_ft, double h_in) {
		this.w_lbs = w_lbs;
		this.h_ft = h_ft;
		this.h_in = h_in;
	}

	/******************************************************************
	 * This method gets the weight given (in kilograms).
	 * 
	 * @return w_kg
	 *****************************************************************/
	public double getW_kg() {
		return w_kg;
	}
	
	/******************************************************************
	 * This method sets the weight given (in kilograms). 
	 * 
	 * @param w_kg
	 * 			The weight given (in kilograms).
	 *****************************************************************/
	public void setW_kg(double w_kg) {
		this.w_kg = w_kg;
	}
	
	/******************************************************************
	 * This method gets the height given (in meters).
	 * 
	 * @return h_m
	 *****************************************************************/
	public double getH_m() {
		return h_m;
	}
	
	/******************************************************************
	 * This method sets the height given (in meters).
	 * 
	 * @param h_m
	 * 			The height given (in meters).
	 *****************************************************************/
	public void setH_m(double h_m) {
		this.h_m = h_m;
	}
	
	/******************************************************************
	 * This method gets the weight given (in pounds).
	 * 
	 * @return w_lbs
	 *****************************************************************/
	public double getW_lbs() {
		return w_lbs;
	}
	
	/******************************************************************
	 * This method sets the weight given (in pounds).
	 * 
	 * @param w_lbs
	 * 			The weight given (in pounds).
	 *****************************************************************/
	public void setW_lbs(double w_lbs) {
		this.w_lbs = w_lbs;
	}
	
	/******************************************************************
	 * This method gets the height given (in feet).
	 * 
	 * @return h_ft
	 *****************************************************************/
	public double getH_ft() {
		return h_ft;
	}
	
	/******************************************************************
	 * This method sets the height given (in feet).
	 * 
	 * @param h_ft
	 * 			The height given (in feet).
	 *****************************************************************/
	public void setH_ft(double h_ft) {
		this.h_ft = h_ft;
	}

	/******************************************************************
	 * This method gets the height given (in inches).
	 * 
	 * @return h_in
	 *****************************************************************/
	public double getH_in() {
		return h_in;
	}

	/******************************************************************
	 * This method sets the height given (in inches).
	 * 
	 * @param h_in
	 * 			The height given (in inches).
	 *****************************************************************/
	public void setH_in(double h_in) {
		this.h_in = h_in;
	}
	
	/******************************************************************
	 * This method gets the BMI result for the metric calculation.
	 * 
	 * @return bmi_m
	 *****************************************************************/
	public double getBmi_m() {
		return bmi_m;
	}
	
	/******************************************************************
	 * This method sets the BMI result for the metric calculation.
	 * 
	 * @param bmi_m
	 * 			The BMI result for the metric calculation.
	 *****************************************************************/
	public void setBmi_m(double bmi_m) {
		this.bmi_m = bmi_m;
	}

	/******************************************************************
	 * This method gets the BMI result for the imperial calculation.
	 * 
	 * @return bmi_i
	 *****************************************************************/
	public double getBmi_i() {
		return bmi_i;
	}

	/******************************************************************
	 * This method sets the BMI result for the imperial calculation.
	 * 
	 * @param bmi_i
	 * 			The BMI result for the imperial calculation.
	 *****************************************************************/
	public void setBmi_i(double bmi_i) {
		this.bmi_i = bmi_i;
	}
	
	/******************************************************************
	 * This method converts feet into inches.
	 * 
	 * This method is a helper method to convert feet into inches, due
	 * to the imperial formula for BMI only using inches as a height
	 * measurement.
	 * 
	 * @param ft
	 * 			The feet component of the imperial measured height.
	 * @param in
	 * 			The inches component of imperial measured height.
	 * @return plc_hld + in;
	 * 			The amount of inches that the feet were converted plus
	 * 			the amount of inches provided.
	 *****************************************************************/
	public double feetToInches(double ft, double in) {
		double plc_hld = ft * 12;
		return plc_hld + in;
	}

	/******************************************************************
	 * This method calculates the BMI using the metric formula.
	 * 
	 * The metric formula for calculating BMI is:
	 * 
	 * BMI = weight(kg) / height^2(m^2)
	 * 
	 * @return bmi_m
	 * 			The BMI result for metric calculations.
	 *****************************************************************/
	public double bmiMetric() {
		bmi_m = w_kg / Math.pow(h_m, 2);
		return bmi_m;
	}
	
	/******************************************************************
	 * This method calculates the BMI using the imperial formula.
	 * 
	 * The imperial formula for calculating BMI is:
	 * 
	 * BMI = weight(lbs) * 703 / (height^2(in^2))
	 * 
	 * @return bmi_i
	 * 			The BMI result for imperial calculations.
	 *****************************************************************/
	public double bmiImperial() {
		bmi_i = w_lbs * 703 / (Math.pow(feetToInches(h_ft, h_in), 2));
		return bmi_i;
	}
}
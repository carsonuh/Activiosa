package calculations;

import static org.junit.Assert.*;
import org.junit.Test;

/**********************************************************************
 * This class contains the jUnit tests for all 3 calculation classes.
 * Each class has it's own separate section of tests. In this order:
 * -BMI
 * -BWP
 * -Calorie Total
 * 
 * @author Spencer Keeton
 * @version 12/4/18
 *********************************************************************/
public class CalcTests {
	
	/******************************************************************
	 * This method tests the constructors for the BMI class.
	 *****************************************************************/
	@Test
	public void testBmiConst() {
		//Tests for the metric inputs.
		BmiCalc bmi1 = new BmiCalc(90, 1.5);
		assertEquals(bmi1.getW_kg(), 90, 0);
		assertEquals(bmi1.getH_m(), 1.5, 0);
		
		//Tests for the imperial inputs.
		bmi1 = new BmiCalc(210, 6, 2);
		assertEquals(bmi1.getW_lbs(), 210, 0);
		assertEquals(bmi1.getH_ft(), 6, 0);
		assertEquals(bmi1.getH_in(), 2, 0);
	}
	
	/******************************************************************
	 * This method tests the set methods for the BMI class.
	 *****************************************************************/
	@Test
	public void testBmiSet() {
		//Tests the metric set methods.
		BmiCalc bmi2 = new BmiCalc(85, 1.7);
		bmi2.setW_kg(89);
		assertEquals(bmi2.getW_kg(), 89, 0);
		bmi2.setH_m(1.85);
		assertEquals(bmi2.getH_m(), 1.85, 0);
		bmi2.setBmi_m(26);
		assertEquals(bmi2.getBmi_m(), 26, 0);
		
		//Tests the imperial set methods.
		bmi2 = new BmiCalc(165, 5, 5);
		bmi2.setW_lbs(207);
		assertEquals(bmi2.getW_lbs(), 207, 0);
		bmi2.setH_ft(6);
		assertEquals(bmi2.getH_ft(), 6, 0);
		bmi2.setH_in(1);
		assertEquals(bmi2.getH_in(), 1, 0);
		bmi2.setBmi_i(27.3);
		assertEquals(bmi2.getBmi_i(), 27.3, 0);
	}
	
	/******************************************************************
	 * This method tests the feetToInches method of the BMI class.
	 *****************************************************************/
	@Test
	public void testBmiFeetToInches() {
		//This tests the functionality of the feetToInches method.
		BmiCalc bmi3 = new BmiCalc(200, 6, 0);
		assertEquals(bmi3.feetToInches(bmi3.h_ft, bmi3.h_in), 72, 0);
	}
	
	/******************************************************************
	 * This method tests the bmiMetric method of the BMI class.
	 *****************************************************************/
	@Test
	public void testBmiMetric() {
		//This tests the functionality of the feetToInches.
		BmiCalc bmi4 = new BmiCalc(79, 1.55);
		assertEquals(bmi4.bmiMetric(), 32.9, 1);
	}
	
	/******************************************************************
	 * This method tests the bmiImperial method of the BMI class.
	 *****************************************************************/
	@Test
	public void testBmiImperial() {
		//This tests the functionality of the bmiImperial of the BMI
		//class.
		BmiCalc bmi5 = new BmiCalc(194, 6, 0);
		assertEquals(bmi5.bmiImperial(), 26.3, 1);
	}
	
	//
	//
	//
	//
	
	/******************************************************************
	 * This method tests the constructor for the BWP class.
	 *****************************************************************/
	@Test
	public void testBwpConst() {
		//This tests the constructor.
		BwpCalc bwp1 = new BwpCalc(220, 200);
		assertEquals(bwp1.getInitial(), 220, 0);
		assertEquals(bwp1.getCurrent(), 200, 0);
	}
	
	/******************************************************************
	 * This method tests the set methods for weight for the BWP class.
	 *****************************************************************/
	@Test
	public void testBwpSet() {
		//This tests the set methods for the current & initial weight.
		BwpCalc bwp2 = new BwpCalc(198, 176);
		bwp2.setInitial(176);
		assertEquals(bwp2.getInitial(), 176, 0);
		bwp2.setCurrent(172);
		assertEquals(bwp2.getCurrent(), 172, 0);
	}
	
	/******************************************************************
	 * This method tests the get and set methods for percentage for the
	 * BWP class.
	 *****************************************************************/
	@Test
	public void testBwpPercTest() {
		//This tests the getPercentage & setPercentage methods.
		BwpCalc bwp3 = new BwpCalc(174, 172);
		bwp3.setPercentage(87.4);
		assertEquals(bwp3.getPercentage(), 87.4, 0);
	}
	
	/******************************************************************
	 * This method tests the percentage calculation method for the BWP 
	 * class.
	 *****************************************************************/
	@Test
	public void testBwpPrecCalc() {
		//This tests the percentage calculation for weight loss.
		BwpCalc bwp4 = new BwpCalc(209, 183);
		assertEquals(bwp4.percentageCalc(), -12.44, 1);
		
		//This tests the percentage calculation for weight gain.
		bwp4 = new BwpCalc(178, 192);
		assertEquals(bwp4.percentageCalc(), 7.87, 1);
		
		//This tests for a negative initial input.
		bwp4 = new BwpCalc(-220, 183);
		assertEquals(bwp4.percentageCalc(), -16.82, 1);
		
		//This tests for a negative current input.
		bwp4 = new BwpCalc(220, -183);
		assertEquals(bwp4.percentageCalc(), -16.82, 1);
	}
	
	//
	//
	//
	//
	
	/******************************************************************
	 * This method tests the constructors for the CalTotal class.
	 *****************************************************************/
	@Test
	public void testCalTotalConst() {
		//This tests for 2 inputs.
		CalTotal cal1 = new CalTotal(1000, 140);
		assertEquals(cal1.getCalTotal(), 1000, 0);
		assertEquals(cal1.getNewCal(), 140, 0);
		
		//This tests for 1 input.
		cal1 = new CalTotal(250);
		assertEquals(cal1.getNewCal(), 250, 0);
	}
	
	/******************************************************************
	 * This method tests the set methods for the CalTotal class.
	 *****************************************************************/
	@Test
	public void testCalTotalSet() {
		//This tests the set methods for the total & new calories.
		CalTotal cal2 = new CalTotal(1570, 302);
		cal2.setCalTotal(1782);
		assertEquals(cal2.getCalTotal(), 1782, 0);
		cal2.setNewCal(264);
		assertEquals(cal2.getNewCal(), 264, 0);
	}
	
	/******************************************************************
	 * This method tests the findCalTotal method for the CalTotal
	 * class.
	 *****************************************************************/
	@Test
	public void testCalTotalfindTotal() {
		//This tests the findCalTotal method.
		CalTotal cal3 = new CalTotal(1500, 250);
		assertEquals(cal3.findCalTotal(), 1750, 0);
		
		//This tests for a negative new calorie value.
		cal3 = new CalTotal(1350, -450);
		assertEquals(cal3.findCalTotal(), 1800, 0);
	}
	
	/******************************************************************
	 * This method tests the resetCalTotal method of the CalTotal
	 * class.
	 *****************************************************************/
	@Test
	public void testCalTotalResetTotal() {
		//This tests the resetCalTotal method.
		CalTotal cal4 = new CalTotal(1340, 170);
		cal4.resetCalTotal();
		assertEquals(cal4.getCalTotal(), 0, 0);
	}
}

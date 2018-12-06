package fitnessApp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*************************************************************************************************************
 * This provides several date conversions that are used through the entire program 
 * 
 * @author Carson Uecker-Herman
 * @version 12/5/18
 *
 *************************************************************************************************************/
public class DateMaker {

	/***
	 * Returns todays date
	 * @return LocalDateTime today's date
	 */
	public static LocalDateTime Today() {
		return LocalDateTime.now();
	}
	
	/**
	 * Converts a LocalDateTime into a SQL formatted date
	 * @param t LocalDateTime
	 * @return String SQL date yyyy-MM-dd
	 */
	public static String ToSQLDate(LocalDateTime t) {
		return t.getYear()+"-"+t.getMonthValue()+"-"+t.getDayOfMonth();
	}
	
	/***
	 * Converts a LocalDate into a SQL formatted date
	 * @param l LocalDate
	 * @return String SQL date yyyy-MM-dd
	 */
	public static String ToSQLDate(LocalDate l) {
		return l.getYear()+"-"+l.getMonthValue()+"-"+l.getDayOfMonth();
	}
	
	/***
	 * Converts today's date into a formatted string. Used for labels
	 * @return String date in format: Monday, Decemeber 17
	 */
	public static String LabelToday() {
		String month = Today().getMonth().toString().toLowerCase();
		String day = Today().getDayOfWeek().toString().toLowerCase();
		int dayNum = Today().getDayOfMonth();
		day = day.substring(0,1).toUpperCase() + day.substring(1);
		month = month.substring(0,1).toUpperCase() + month.substring(1);
		return day + ", "+month+" "+dayNum;
	}
	
	/***
	 * Gets Monday's date
	 * @return String date of Monday of the current week
	 */
	public static String weekStart() {
		 LocalDate today = LocalDate.now();
		 LocalDate monday = today.with(previousOrSame(DayOfWeek.SUNDAY));
		 return monday.toString();
	}
	
	/***
	 * Gets Saturday's date
	 * @return String date of Saturday of the current wee;
	 */
	public static String weekEnd() {
		 LocalDate today = LocalDate.now();
		 LocalDate sunday = today.with(nextOrSame(DayOfWeek.SATURDAY));
		 return sunday.toString();
	}
	
	/***
	 * Converts a date into a String and returns the day from the date
	 * @param s String of date
	 * @return String day of the week
	 */
	public static String dayOfWeek(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate day = LocalDate.parse(s, formatter);
		return day.getDayOfWeek().toString().toUpperCase();
	}
	
	/**
	 * Returns a string of the date for the week
	 * @return String mm/dd - mm/dd
	 */
	public static String weekRange() {
		String start = weekStart();
		String end = weekEnd();
		start = start.substring(5);
		end = end.substring(5);
		
		return monthName(weekStart()) + " - " + monthName(weekEnd());
	}
	
	/***
	 * Returns months name
	 * @param s String of date
	 * @return String 3 letter month name 
	 */
	public static String monthName(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate date = LocalDate.parse(s, formatter);
		
		return date.getMonth().toString().toLowerCase().substring(0,1).toUpperCase() + 
				date.getMonth().toString().toLowerCase().substring(1,3) + " " + date.getDayOfMonth();
				
		
	}
	
	/**
	 * Returns string of day to uppercase in 3 letter format
	 * @param l LocalDate
	 * @return String day in 3 letter format
	 */
	public static String graphLabelShort(LocalDate l) {
		String day = l.getDayOfWeek().toString();
		return day.substring(0, 2).toUpperCase();
	}
	
	/***
	 * Converts to number of minutes
	 * @param mins String HH:mm:ss
	 * @return number of minutes
	 * @throws ParseException
	 */
	public static int numOfMinutes(String mins) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	    Date date = sdf.parse(mins);
	    return date.getMinutes() + (date.getHours()*60);
	}
	
}

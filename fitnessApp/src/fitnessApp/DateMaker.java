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

public class DateMaker {

	public static LocalDateTime Today() {
		return LocalDateTime.now();
	}
	
	public static String ToSQLDate(LocalDateTime t) {
		return t.getYear()+"-"+t.getMonthValue()+"-"+t.getDayOfMonth();
	}
	
	public static String ToSQLDate(LocalDate l) {
		return l.getYear()+"-"+l.getMonthValue()+"-"+l.getDayOfMonth();
	}
	
	public static String LabelToday() {
		String month = Today().getMonth().toString().toLowerCase();
		String day = Today().getDayOfWeek().toString().toLowerCase();
		int dayNum = Today().getDayOfMonth();
		day = day.substring(0,1).toUpperCase() + day.substring(1);
		month = month.substring(0,1).toUpperCase() + month.substring(1);
		return day + ", "+month+" "+dayNum;
	}
	
	public static String weekStart() {
		 LocalDate today = LocalDate.now();
		 LocalDate monday = today.with(previousOrSame(DayOfWeek.SUNDAY));
		 return monday.toString();
	}
	
	public static String weekEnd() {
		 LocalDate today = LocalDate.now();
		 LocalDate sunday = today.with(nextOrSame(DayOfWeek.SATURDAY));
		 return sunday.toString();
	}
	
	public static String dayOfWeek(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate day = LocalDate.parse(s, formatter);
		return day.getDayOfWeek().toString().toUpperCase();
	}
	
	public static String weekRange() {
		String start = weekStart();
		String end = weekEnd();
		start = start.substring(5);
		end = end.substring(5);
		return "Week of " + start + "  to  " + end;
	}
	
	public static String graphLabelShort(LocalDate l) {
		String day = l.getDayOfWeek().toString();
		return day.substring(0, 2).toUpperCase();
	}
	
	public static int numOfMinutes(String mins) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	    Date date = sdf.parse(mins);
	    return date.getMinutes() + (date.getHours()*60);
	}
	
}

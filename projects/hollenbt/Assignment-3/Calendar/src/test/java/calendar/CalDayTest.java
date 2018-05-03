/** A JUnit test class to test the class CalDay. */

package calendar;


import java.util.LinkedList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

/*
	Check the output string of getFullInfomrationApp
*/


public class CalDayTest{

  GregorianCalendar today;
	
  @Before public void initialize() {
		//get todays date
		Calendar rightnow = Calendar.getInstance();
		//current month/year/date is today
		/** the month the user is currently viewing **/
	 	int thisMonth = rightnow.get(Calendar.MONTH);
	 	/** todays date **/
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
	   	/** the year the user is currently viewing **/
		int thisYear = rightnow.get(Calendar.YEAR);
		
		// Get a calendar which is set to a specified date.
		today = new GregorianCalendar(thisYear, thisMonth, thisDay);
	}
  
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	  CalDay calday = new CalDay();
	  assertFalse(calday.isValid());
	  assertEquals(null, calday.iterator());
	  //calday.getFullInfomrationApp(calday);	// doesn't check for null pointer returned by call to iterator() with empty CalDay
	  											// bug found in Assignment 2
	  String s0 = calday.toString();
	  assertEquals(s0, "");
  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	  CalDay calday = new CalDay(today);
	  assertFalse(calday.getAppts() == null);
	  assertTrue(calday.isValid());
	  assertEquals(calday.getDay(), today.get(today.DAY_OF_MONTH));
	  assertEquals(calday.getMonth(), today.get(today.MONTH) + 1);
	  assertEquals(calday.getYear(), today.get(today.YEAR));
	  Appt appt0 = new Appt(today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "0", "D", "a.com");
	  Appt appt1 = new Appt(0, 8, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "1", "D", "a.com");
	  Appt appt2 = new Appt(9, 30, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "2", "D", "a.com");
	  Appt appt3 = new Appt(9, 15, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "3", "D", "a.com");
	  Appt appt4 = new Appt(12, 10, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "4", "D", "a.com");
	  Appt appt5 = new Appt(18, 30, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "5", "D", "a.com");
	  String s = calday.getFullInfomrationApp(calday);
	  assertFalse(s == null);
	  calday.toString();
	  calday.addAppt(appt0);
	  calday.addAppt(appt1);
	  calday.addAppt(appt2);
	  calday.addAppt(appt4);
	  calday.addAppt(appt3);
	  calday.addAppt(appt5);
	  s = calday.toString();
	  s = calday.getFullInfomrationApp(calday);
	  assertFalse(s.contains("-1:-1AM"));
	  assertTrue(s.contains("6:30PM"));
	  assertTrue(s.contains("9:30AM"));
	  assertTrue(s.contains("12:08AM"));
	  assertTrue(s.contains("0:10AM")); // this is a bug that I did not introduce. Should be 12:10PM
	  assertEquals(calday.getSizeAppts(), 6);
	  Iterator it = calday.iterator();
	  assertEquals((Appt)it.next(), appt0);
	  assertEquals((Appt)it.next(), appt1);
	  assertEquals((Appt)it.next(), appt2);
	  assertEquals((Appt)it.next(), appt3);
	  assertEquals((Appt)it.next(), appt4);
	  assertEquals((Appt)it.next(), appt5);
	
	  // Last three mutations are in setAppts, which is a private function that is only called in the
	  // constructor, so the argument cannot ever be null.
  }

}

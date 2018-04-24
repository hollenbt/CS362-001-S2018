/** A JUnit test class to test the class CalDay. */

package calendar;


import java.util.LinkedList;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;


public class CalDayTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	  CalDay calday = new CalDay();
	  assertFalse(calday.isValid());
	  //calday.getFullInfomrationApp(calday);	// doesn't check for null pointer returned by call to iterator() with empty CalDay
	  String s0 = calday.toString();
	  assertEquals(s0, "");
  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	  CalDay calday = new CalDay(new GregorianCalendar());
	  assertTrue(calday.isValid());
	  Appt appt0 = new Appt(-1, -1, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt1 = new Appt(18, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt2 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt3 = new Appt(0, 8, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  calday.getFullInfomrationApp(calday);
	  calday.toString();
	  calday.addAppt(appt0);
	  calday.addAppt(appt1);
	  calday.addAppt(appt2);
	  calday.addAppt(appt3);
	  calday.getFullInfomrationApp(calday);
	  calday.toString();
	  assertEquals(calday.getSizeAppts(), 4);
  }

}

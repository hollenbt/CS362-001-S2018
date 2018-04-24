
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class DataHandlerTest{
	
	GregorianCalendar today, tomorrow;
	
	@Before public void getDay() {
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
		tomorrow = new GregorianCalendar(thisYear, thisMonth, thisDay);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
	}
	
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(-1, -1, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt1 = new Appt(18, 61, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt2 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt3 = new Appt(12, 8, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  appt1.setValid();
	  appt2.setValid();
	  appt3.setValid();
	  dh.saveAppt(appt0);
	  dh.saveAppt(appt1);
	  dh.saveAppt(appt2);
	  dh.saveAppt(appt3);
	  dh.deleteAppt(appt3);
	  dh.deleteAppt(appt3);
	  dh.deleteAppt(appt2);
	  dh.deleteAppt(appt1);
	  dh.deleteAppt(appt0);
  }
  
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	  DataHandler dh = new DataHandler("calendar.xml");
	  List<CalDay> daylist;
	  try {
		  daylist = dh.getApptRange(tomorrow, today);
		  fail();
	  }
	  catch(DateOutOfRangeException e) {}
	  daylist = dh.getApptRange(today, tomorrow);
	  tomorrow.add(Calendar.MONTH, 1);
	  daylist = dh.getApptRange(today, tomorrow);
  }
  
  @Test(timeout = 4000)
  public void test02() throws Throwable {
	  DataHandler dh = new DataHandler("calendar3.xml", false);
	  Appt appt0 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.deleteAppt(appt0);
	  dh.saveAppt(appt0);
	  dh.deleteAppt(appt0);
  }
  
  @Test(timeout = 4000)
  public void test03() throws Throwable {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt1 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt2 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt3 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt1.setRecurrence(null, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt2.setRecurrence(null, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt3.setRecurrence(null, 11, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt0.setValid();
	  appt1.setValid();
	  appt2.setValid();
	  appt3.setValid();
	  dh.deleteAppt(appt0);
	  dh.saveAppt(appt0);
	  dh.deleteAppt(appt0);
	  dh.saveAppt(appt0);
	  dh.saveAppt(appt1);
	  dh.saveAppt(appt2);
	  dh.saveAppt(appt3);
	  tomorrow.add(Calendar.YEAR, 2);
	  List<CalDay> daylist = dh.getApptRange(today, tomorrow);
	  
  }
}
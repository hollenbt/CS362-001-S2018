
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.*;


public class DataHandlerTest{
	
	GregorianCalendar today, tomorrow;
	ByteArrayOutputStream output;
	
	  public boolean hasAppt(List<Appt> l, Appt a) {
		  Iterator<Appt> it = l.iterator();
		  while (it.hasNext()) {
			  Appt b = it.next();
			  if (a.getTitle().equals(b.getTitle()))
				  	return true;
		  }
		  return false;
	  }
	
	@Before public void initialize() {		
		// Get a calendar which is set to a specified date.
		today = new GregorianCalendar(2018, 4, 7);
		tomorrow = new GregorianCalendar(2018, 4, 7);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);

		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  File f = new File("calendar.xml");
	  long modTimeOld = f.lastModified();
	  Appt appt0 = new Appt(-1, -1, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "0", "D", "a.com");
	  Appt appt1 = new Appt(18, 61, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "1", "D", "a.com");
	  Appt appt2 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "2", "D", "a.com");
	  Appt appt3 = new Appt(12, 8, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "3", "D", "a.com");
	  //appt0.setValid(); // appointments without start times are never valid (bug)
	  appt1.setValid();	  // correctly invalid
	  appt2.setValid();
	  appt3.setValid();
	  List<CalDay> daylist = dh.getApptRange(today, tomorrow);
	  int start_num = daylist.get(0).getSizeAppts();
	  
	  assertTrue(dh.saveAppt(appt0));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  long modTimeNew = f.lastModified();
	  assertFalse(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertFalse(dh.saveAppt(appt1));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertTrue(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertTrue(dh.saveAppt(appt2));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertFalse(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertTrue(dh.saveAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 3 + start_num);
	  modTimeNew = f.lastModified();
	  assertFalse(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertTrue(dh.deleteAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertFalse(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertFalse(dh.deleteAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertTrue(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertTrue(dh.deleteAppt(appt2));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertFalse(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertFalse(dh.deleteAppt(appt1));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertTrue(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
	  
	  assertTrue(dh.deleteAppt(appt0));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), start_num);
	  modTimeNew = f.lastModified();
	  assertFalse(modTimeOld == modTimeNew);
	  modTimeOld = modTimeNew;
  }
  
  @Test(timeout = 4000)
  public void test001()  throws Throwable  {
	  DataHandler dh = new DataHandler("calendar.xml", false);
	  File f = new File("calendar.xml");
	  long modTimeOld = f.lastModified();
	  Appt appt0 = new Appt(-1, -1, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "0", "D", "a.com");
	  Appt appt1 = new Appt(18, 61, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "1", "D", "a.com");
	  Appt appt2 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "2", "D", "a.com");
	  Appt appt3 = new Appt(12, 8, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "3", "D", "a.com");
	  //appt0.setValid(); // appointments without start times are never valid (bug)
	  appt1.setValid();	  // correctly invalid
	  appt2.setValid();
	  appt3.setValid();
	  List<CalDay> daylist = dh.getApptRange(today, tomorrow);
	  int start_num = daylist.get(0).getSizeAppts();
	  
	  assertTrue(dh.saveAppt(appt0));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  long modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertFalse(dh.saveAppt(appt1));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.saveAppt(appt2));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.saveAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 3 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.deleteAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertFalse(dh.deleteAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.deleteAppt(appt2));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertFalse(dh.deleteAppt(appt1));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.deleteAppt(appt0));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
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
	  assertEquals("", output.toString());
  }
  
  @Test(timeout = 4000)
  public void test02() throws Throwable {
	  DataHandler dh = new DataHandler("calendar3.xml", false);
	  Appt appt0 = new Appt(9, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.deleteAppt(appt0);
	  dh.saveAppt(appt0);
	  dh.deleteAppt(appt0);
	  
      Field field = DataHandler.class.getDeclaredField("valid");
      field.setAccessible(true);
      field.setBoolean(dh, false);
	  assertEquals(dh.getApptRange(today, tomorrow), null);	  
  }
  
  @Test(timeout = 4000)
  public void test03() throws Throwable {
	  DataHandler dh = new DataHandler("", false);
	  Appt appt0 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "0", "D", "a.com");
	  Appt appt01 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "01", "D", "a.com");
	  Appt appt1 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "1", "D", "a.com");
	  Appt appt2 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "2", "D", "a.com");
	  Appt appt3 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "3", "D", "a.com");
	  Appt appt4 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "4", "D", "a.com");
	  appt0.setRecurrence(new int[] {8, 9}, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt01.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt1.setRecurrence(new int[] {1, 2, 4, 5}, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt2.setRecurrence(null, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt3.setRecurrence(null, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt4.setRecurrence(null, 11, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt0.setValid();
	  appt01.setValid();
	  appt1.setValid();
	  appt2.setValid();
	  appt3.setValid();
	  appt4.setValid();
	  dh.deleteAppt(appt0);
	  dh.saveAppt(appt0);
	  dh.deleteAppt(appt0);
	  dh.saveAppt(appt0);
	  dh.saveAppt(appt01);
	  dh.saveAppt(appt1);
	  dh.saveAppt(appt2);
	  dh.saveAppt(appt3);
	  dh.saveAppt(appt4);
	  tomorrow.add(Calendar.YEAR, 2);
	  List<CalDay> daylist = dh.getApptRange(today, tomorrow);
	  Iterator<CalDay> it = daylist.iterator();
	  CalDay cd = it.next();
	  assertEquals("", output.toString());
	  assertEquals(cd.getSizeAppts(), 6);
	  while (it.hasNext()) {
		  cd = it.next();
		  List<Appt> appts = cd.getAppts();
		  assertFalse(hasAppt(appts, appt0));
		  GregorianCalendar temp = new GregorianCalendar(cd.getYear(), cd.getMonth() - 1, cd.getDay());
		  if (temp.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
			  assertTrue(temp.toString(), hasAppt(appts, appt2));
			  if (temp.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
				  assertTrue(temp.toString(), hasAppt(appts, appt3));
			  }
		  }
		  int d = temp.get(Calendar.DAY_OF_WEEK);
		  assertEquals(temp.toString(), hasAppt(appts, appt1), (d == 1 || d == 2 || d == 4 || d == 5));
		  assertEquals(temp.toString(), hasAppt(appts, appt01), d == today.get(Calendar.DAY_OF_WEEK));
	  }
  }
  
  @Test(timeout = 4000)
  public void test04() throws Throwable {
	  DataHandler dh = new DataHandler("", false);
	  GregorianCalendar futureDate = new GregorianCalendar(2020, 4, 7);
	  Appt appt0 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "0", "D", "a.com");
	  Appt appt1 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "1", "D", "a.com");
	  Appt appt2 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "2", "D", "a.com");
	  Appt appt3 = new Appt(9, 30, futureDate.get(Calendar.DAY_OF_MONTH), futureDate.get(Calendar.MONTH) + 1, futureDate.get(Calendar.YEAR), "3", "D", "a.com");
	  appt0.setRecurrence(new int[] {4}, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt1.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, 1);
	  appt2.setRecurrence(null, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
	  appt0.setValid();
	  appt1.setValid();
	  appt2.setValid();
	  appt3.setValid();
	  dh.saveAppt(appt0);
	  dh.saveAppt(appt1);
	  dh.saveAppt(appt2);
	  dh.saveAppt(appt3);
	  tomorrow.add(Calendar.MONTH, 1);
	  List<CalDay> daylist = dh.getApptRange(today, tomorrow);
	  Iterator<CalDay> it = daylist.iterator();
	  CalDay cd = it.next();
	  assertEquals("", output.toString());
	  assertEquals(cd.getSizeAppts(), 3);
	  GregorianCalendar lastRecurrence = new GregorianCalendar(cd.getYear(), cd.getMonth() - 1, cd.getDay());
	  lastRecurrence.add(Calendar.DAY_OF_MONTH, 7);
	  while (it.hasNext()) {
		  cd = it.next();
		  List<Appt> appts = cd.getAppts();
		  assertFalse(hasAppt(appts, appt2));
		  assertFalse(hasAppt(appts, appt3));
		  GregorianCalendar temp = new GregorianCalendar(cd.getYear(), cd.getMonth() - 1, cd.getDay());
		  int d = temp.get(Calendar.DAY_OF_WEEK);
		  assertEquals(temp.toString(), hasAppt(appts, appt0), d == 4);
		  assertEquals(temp.toString(), hasAppt(appts, appt1), !temp.after(lastRecurrence) && d == today.get(Calendar.DAY_OF_WEEK));
	  }
  }
}
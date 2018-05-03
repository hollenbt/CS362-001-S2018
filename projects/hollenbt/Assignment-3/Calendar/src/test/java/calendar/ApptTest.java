/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
    Check that XML element is set to null:
    	it is null by default, no need to call setXMLElement(null) (can't kill mutation)
*/

public class ApptTest  {

  ByteArrayOutputStream err_output;
	
  @Before public void initialize() {
		err_output = new ByteArrayOutputStream();
		System.setErr(new PrintStream(err_output));
	}
  
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(11, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertEquals("xyz@gmail.com", appt0.getEmailAddress());
      assertTrue(appt0.hasTimeSet());
      assertFalse(appt0.isRecurring());
      assertEquals(0, appt0.getRecurIncrement());
      assertEquals(null, appt0.getXmlElement());
      appt0.setValid();
      assertTrue(appt0.getValid());
      assertEquals("\t4/9/2018 at 11:30am ,Birthday Party, This is my birthday party\n", string0);
  }
@Test(timeout = 4000)
 public void test01()  throws Throwable  {
	Appt appt0 = new Appt(12, 1, 31, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    appt0.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	String string0 = appt0.toString();
	assertTrue(appt0.getRecurDays().length == 0);
    assertEquals(Appt.RECUR_BY_WEEKLY, appt0.getRecurBy());
    assertEquals(Appt.RECUR_NUMBER_FOREVER, appt0.getRecurNumber());
    assertTrue(appt0.isRecurring());
    assertEquals(1, appt0.getRecurIncrement());
    appt0.setValid();
    assertFalse(appt0.getValid());
    assertEquals("\t2/31/2018 at 12:1pm ,Birthday Party, This is my birthday party\n", string0); // bug: missing leading 0 in minute
}

@Test(timeout = 4000)
 public void test02()  throws Throwable  {
	Appt appt0 = new Appt(0, 10, 5, 2, 2018, "Birthday Party", "This is my birthday party", null);
    appt0.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	String string0 = appt0.toString();
	assertTrue(appt0.hasTimeSet());
    assertEquals(Appt.RECUR_BY_WEEKLY, appt0.getRecurBy());
    assertTrue(appt0.isRecurring());
    assertEquals(1, appt0.getRecurIncrement());
    appt0.setValid();
    assertTrue(appt0.getValid());
    assertEquals("\t2/5/2018 at 12:10am ,Birthday Party, This is my birthday party\n", string0);
    appt0.setTitle(null);
    assertEquals(appt0.getTitle(), "");
    appt0.setDescription(null);
    assertEquals(appt0.getDescription(), "");
}

@Test(timeout = 4000)
public void test03()  throws Throwable  {
	Appt appt0 = new Appt(9, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
	String string0 = appt0.toString();
	assertFalse(appt0.hasTimeSet());
	assertEquals(Appt.RECUR_BY_WEEKLY, appt0.getRecurBy());
	assertTrue(appt0.isRecurring());
	assertEquals(1, appt0.getRecurIncrement());
	appt0.setValid();
    assertFalse(appt0.getValid());
    assertTrue(appt0.isOn(9, 2, 2018));
    assertFalse(appt0.isOn(10, 2, 2018));
    assertFalse(appt0.isOn(10,  3, 2017));
}

@Test(timeout = 4000)
public void test04()  throws Throwable  {
	Appt appt0 = new Appt(10, 15, 9, 6, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    String string0 = appt0.toString();
	assertEquals("", err_output.toString());
    assertEquals("\t6/9/2018 at 10:15am ,Birthday Party, This is my birthday party\n", string0);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    // Check boundaries (should be valid)
    appt0.setStartMonth(1);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartMonth(12);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartHour(0);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartHour(23);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartMinute(0);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartMinute(59);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartYear(1);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartDay(1);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartDay(31);
    appt0.setValid();
    assertTrue(appt0.getValid());
       
    appt0.setStartMonth(6);
    appt0.setStartDay(1);
    //Check boundaries (should be invalid)
    appt0.setStartYear(0);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartDay(0);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartDay(31);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartYear(1);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMinute(-1);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMinute(60);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartHour(-1);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartHour(24);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMonth(0);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMonth(13);
    appt0.setValid();
    assertFalse(appt0.getValid());

    appt0.toString();
	assertFalse("" == err_output.toString());
}

}

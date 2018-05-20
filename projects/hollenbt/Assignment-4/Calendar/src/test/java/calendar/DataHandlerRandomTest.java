
package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Random Test Generator for DataHandler class.
 */

public class DataHandlerRandomTest {
	
	private static final int NUM_TESTS=30;

    /**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random) {
        String[] methodArray = new String[] {"deleteAppt","getApptRange"}; // The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length); // get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n]; // return the method name 
    }

    /**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random) {
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY, 77}; // The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length); // get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return the value of the  appointments to recur 
    }	
	
    /**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random) {
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER}; // The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length); // get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return appointments to recur forever or Never recur 
    }

    /**
     * Generate Random Tests that tests DataHandler Class.
     */
	 @Test
	  public void randomtest() throws Throwable {		 
		System.out.println("Start testing...");
		 
		try{
            long randomseed =System.currentTimeMillis();
            Random random = new Random(randomseed);
            
            DataHandler dh_save = new DataHandler();
            DataHandler dh_nosave = new DataHandler("", false);
            int numAppts = 30;
            LinkedList<Appt> appList = new LinkedList<Appt>();

            for (int i = 0; i < numAppts; ++i) {
                int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
                int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
                int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 32);
                int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
                int startYear=ValuesGenerator.getRandomIntBetween(random, 2016, 2018);
                String title="Birthday Party";
                String description="This is my birthday party.";
                String emailAddress="xyz@gmail.com";

                //Construct a new Appointment object with the initial data	 
                //Construct a new Appointment object with the initial data	 
                Appt appt = new Appt(startHour,
                        startMinute,
                        startDay,
                        startMonth,
                        startYear,
                        title,
                        description,
                        emailAddress);
                
                int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
                int[] recurDays = sizeArray != 0 ? ValuesGenerator.generateRandomArray(random, sizeArray) : null;
                int recur = ApptRandomTest.RandomSelectRecur(random);
                int recurIncrement = ValuesGenerator.RandInt(random);
                int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
                appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

                appt.setValid();
                appList.add(appt);
                dh_save.saveAppt(appt);
                dh_nosave.saveAppt(appt);
            }
            for (int i = 0; i < NUM_TESTS; i++) {
                String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
                if (methodName.equals("deleteAppt")) {
                    int apptNum = ValuesGenerator.getRandomIntBetween(random, 0, appList.size() - 1);
                    if (ValuesGenerator.getBoolean(0.75f, random))
                        dh_save.deleteAppt(appList.get(apptNum));
                    else dh_nosave.deleteAppt(appList.get(apptNum));
                }
                else if (methodName.equals("getApptRange")) {
                    int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 28);
                    int startMonth=ValuesGenerator.getRandomIntBetween(random, 0, 11);
                    int startYear=ValuesGenerator.getRandomIntBetween(random, 2016, 2018);
                    int endDay=ValuesGenerator.getRandomIntBetween(random, 0, 28);
                    int endMonth=ValuesGenerator.getRandomIntBetween(random, 0, 11);
                    int endYear=ValuesGenerator.getRandomIntBetween(random, 2016, 2018);
                    GregorianCalendar begin = new GregorianCalendar(startYear,startMonth,startDay);
                    GregorianCalendar end = new GregorianCalendar(endYear,endMonth,endDay);
                    try {
                        if (ValuesGenerator.getBoolean(0.75f, random))
                            dh_save.getApptRange(begin, end);
                        else dh_nosave.getApptRange(begin, end);
                    }catch(DateOutOfRangeException e) {}
                }
                
                if (((i + 1) % 10) == 0)
                    System.out.println((i + 1) + " function calls completed.");
			}
		}catch(NullPointerException e){}
	 
		 System.out.println("Done testing...");
	 }
}

package calendar;


import org.junit.Test;

import java.util.Calendar;
import java.util.Random;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;


/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {
	
    private static final long TestTimeout = 60 * 50 * 1; /* Timeout at 3 seconds */
	private static final int NUM_TESTS=100;

    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void randomtest()  throws Throwable  { 
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

            	CalDay calday = new CalDay(new GregorianCalendar());
				for (int i = 0; i < NUM_TESTS; i++) {
					int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
					int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
					int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 32);
					int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
					int startYear=ValuesGenerator.getRandomIntBetween(random, 0, 2018);
					String title="Birthday Party";
					String description="This is my birthday party.";
					String emailAddress="xyz@gmail.com";

					//Construct a new Appointment object with the initial data	 
					//Construct a new Appointment object with the initial data	 
					Appt appt = new Appt(startHour,
							startMinute ,
							startDay ,
							startMonth ,
							startYear ,
							title,
							description,
							emailAddress);
					appt.setValid();

					calday.addAppt(appt);
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	
}

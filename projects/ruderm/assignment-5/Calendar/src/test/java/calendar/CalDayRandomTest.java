package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

   /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void randomCalTest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				GregorianCalendar cal = new GregorianCalendar(2018, 1, 15);
				CalDay day = new CalDay(cal);
				long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				
				 int startHour=ValuesGenerator.RandInt(random);
				 int startMinute=ValuesGenerator.RandInt(random);
				 int startDay=ValuesGenerator.RandInt(random);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear=ValuesGenerator.RandInt(random);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 int startHour2=ValuesGenerator.RandInt(random);
				 int startMinute2=ValuesGenerator.RandInt(random);
				 int startDay2=ValuesGenerator.RandInt(random);
				 int startMonth2=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear2=ValuesGenerator.RandInt(random);
				 String title2="No Party";
				 String description2="This is not my party.";
				 //Construct two new Appointment objects with the initial data	 
				 Appt appt = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				 Appt appt2 = new Appt(startHour2,
				          startMinute2 ,
				          startDay2 ,
				          startMonth2 ,
				          startYear2 ,
				          title2,
				         description2);
			 if(!appt.getValid())continue;
			 if(!appt2.getValid())continue;
			 day.addAppt(appt);
			 day.addAppt(appt2);
			 appt.setStartMinute(120);
			 day.addAppt(appt);
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}catch(IndexOutOfBoundsException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	
}

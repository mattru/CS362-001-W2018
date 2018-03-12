package calendar;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class TimeTableRandomTest {
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
				GregorianCalendar cal = new GregorianCalendar(0, 1, 15);
				GregorianCalendar cal2 = new GregorianCalendar(10, 2, 13);
				CalDay day = new CalDay(cal);
				CalDay day2 = new CalDay(cal2);
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
				 int startHour3=ValuesGenerator.RandInt(random);
				 int startMinute3=ValuesGenerator.RandInt(random);
				 int startDay3=ValuesGenerator.RandInt(random);
				 int startMonth3=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear3=ValuesGenerator.RandInt(random);
				 String title3="My Party";
				 String description3="This is my party.";
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
				 Appt appt3 = new Appt(startHour3,
				          startMinute3 ,
				          startDay3 ,
				          startMonth3 ,
				          startYear3 ,
				          title3,
				         description3);
			 if(!appt.getValid())continue;
			 if(!appt2.getValid())continue;
			 if(!appt3.getValid())continue;
			 day.addAppt(appt);
			 day.addAppt(appt2);
			for (int i = 0; i < NUM_TESTS; i++) {
					int nextDay=ValuesGenerator.getRandomIntBetween(random, -1, 33);
					appt.setStartDay(nextDay);
					TimeTable testTable = new TimeTable();
					testTable.deleteAppt(day.getAppts(), appt);
					testTable.deleteAppt(null, appt);
					testTable.deleteAppt(day.getAppts(), null);
					testTable.deleteAppt(null, null);
					day.addAppt(appt);
					
				}
			TimeTable testTable = new TimeTable();
			
			testTable.getApptRange(day.getAppts(), cal, cal2);
			try {
				testTable.getApptRange(day.getAppts(), cal2, cal);
			}catch(DateOutOfRangeException e) {}
				
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

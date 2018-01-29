package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 GregorianCalendar cal = new GregorianCalendar(2018, 1, 15);
		 CalDay testDays = new CalDay(cal);
		 testDays.addAppt(appt);
		 //assertions
		 assertEquals(appt.toString(),testDays.getAppts().getFirst().toString());
		 CalDay noDays = new CalDay(); assertFalse(noDays.isValid());
		 assert(testDays.toString() != null);
	 }
}

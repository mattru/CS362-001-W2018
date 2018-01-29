package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

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
		 int[] a = {0, 1};
		 appt.setRecurrence(a, 1, 0, 1);
		 Appt appt2 = new Appt(startHour++,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear++ ,
		          title,
		         description);
		 appt2.setRecurrence(a, 2, 0, 1);
		 GregorianCalendar cal1 = new GregorianCalendar(2015, 2, 12);
		 GregorianCalendar cal2 = new GregorianCalendar(2018, 9, 11);
		 CalDay testDays = new CalDay(cal1);
		 testDays.addAppt(appt);
		 testDays.addAppt(appt2);
		 TimeTable testTable = new TimeTable();
		 testTable.getApptRange(testDays.getAppts(), cal1, cal2);
		 testTable.permute(testDays.getAppts(), a);
		 //assertions
		 assertNull(testTable.deleteAppt(testDays.getAppts(), appt2));
		 assertNull(testTable.deleteAppt(null, null));
	 }
//add more unit tests as you needed
}

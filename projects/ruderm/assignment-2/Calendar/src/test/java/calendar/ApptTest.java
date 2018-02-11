package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
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
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription()); 
		 
		 appt.setStartHour(24); assertFalse(appt.getValid()); appt.setStartHour(21); 
		 appt.setStartMinute(70); assertFalse(appt.getValid()); appt.setStartMinute(30);
		 appt.setStartDay(-1); assertFalse(appt.getValid()); appt.setStartDay(15);
		 appt.setStartYear(-1); assertTrue(appt.getValid()); appt.setStartYear(2018);
		 appt.setTitle(null); assertTrue(appt.getValid()); appt.setTitle("Birthday Party");
		 appt.setDescription(null); assertTrue(appt.getValid()); appt.setDescription("This is my birthday party.");
		 appt.toString();
		 appt.setStartHour(30); assertNull(appt.toString()); appt.setStartHour(21); 
		 assertEquals(0, appt.compareTo(appt)); //self comparison results in no difference
		 
		 appt.setStartMonth(12); assertTrue(appt.getValid()); appt.setStartMonth(01); //caught valid month being invalid
	 }
}

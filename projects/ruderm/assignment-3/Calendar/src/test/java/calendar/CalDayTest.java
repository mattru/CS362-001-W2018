package calendar;

/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Iterator;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	@Test
	public void test01() throws Throwable {
		int startHour = 21;
		int startMinute = 30;
		int startDay = 15;
		int startMonth = 01;
		int startYear = 2018;
		String title = "Birthday Party";
		String description = "This is my birthday party.";
		// Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		GregorianCalendar cal = new GregorianCalendar(2018, 1, 15);
		CalDay testDays = new CalDay(cal);
		testDays.addAppt(appt);
		// assertions
		assertEquals(appt.toString(), testDays.getAppts().getFirst().toString());
		CalDay noDays = new CalDay();
		assertFalse(noDays.isValid());
		assert (testDays.toString() != null);
	}

	@Test
	public void test02() {
		GregorianCalendar cal = new GregorianCalendar(2019, 7, 17);
		CalDay day = new CalDay(cal);
		assertNotNull(day.toString());
		assertNotEquals("", day.toString());
	}

	@Test
	public void test03() {
		GregorianCalendar cal = new GregorianCalendar(2019, 7, 17);
		CalDay day = new CalDay(cal);
		CalDay day2 = new CalDay();
		Iterator<Appt> testIter = (Iterator<Appt>) day.iterator();
		Iterator<Appt> testIter2 = (Iterator<Appt>) day2.iterator();
		assertNotNull(testIter);
		assertNull(testIter2);
	}

	@Test
	public void test04() {
		GregorianCalendar cal = new GregorianCalendar(2019, 7, 17);
		CalDay day = new CalDay(cal);
		CalDay day2 = new CalDay();
		assertFalse(day2.isValid());
		assertEquals(day.getYear(), 2019);
		assertEquals(day.getMonth(), 7);
		assertEquals(day.getDay(), 17);

		Appt appt = new Appt(10, 0, 1, 1, 0, "abc", "def");
		Appt appt1 = new Appt(5, 33, 1, 1, 2015, "ghi", "jkl");
		Appt appt2 = new Appt(15, 0, 1, 1, 0, "mno", "pqr");
		day.addAppt(appt);
		assertEquals(day.getSizeAppts(), 1);
		day.addAppt(appt1);
		assertEquals(day.getSizeAppts(), 2);
		assertEquals(day.getAppts().get(0).getTitle(), "ghi");
		assertEquals(day.getAppts().get(1).getTitle(), "abc");
		day.addAppt(appt2);
		assertEquals(day.getSizeAppts(), 3);
	}
	
	 

	    @Test
	    public void testIsLeapYear()
	    {
	    	int leap = CalendarUtil.NumDaysInMonth(400, 1);
	        assertTrue(29 == leap);
	    	CalendarUtil calUtils = new CalendarUtil();
	        assertEquals(false, calUtils.IsLeapYear(3));
	        assertEquals(false, calUtils.IsLeapYear(300));
	        assertEquals(true, calUtils.IsLeapYear(0));
	        assertEquals(true, calUtils.IsLeapYear(4));
	        assertEquals(true, calUtils.IsLeapYear(400));
	        
	    }

}

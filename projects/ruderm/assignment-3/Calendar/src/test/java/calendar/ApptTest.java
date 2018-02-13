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
		// assertions
		assertTrue(appt.getValid());
		assertEquals(21, appt.getStartHour());
		assertEquals(30, appt.getStartMinute());
		assertEquals(15, appt.getStartDay());
		assertEquals(01, appt.getStartMonth());
		assertEquals(2018, appt.getStartYear());
		assertEquals("Birthday Party", appt.getTitle());
		assertEquals("This is my birthday party.", appt.getDescription());

		appt.setStartHour(24);
		assertFalse(appt.getValid());
		appt.setStartHour(21);
		appt.setStartMinute(70);
		assertFalse(appt.getValid());
		appt.setStartMinute(30);
		appt.setStartDay(-1);
		assertFalse(appt.getValid());
		appt.setStartDay(15);
		appt.setStartYear(-1);
		assertTrue(appt.getValid());
		appt.setStartYear(2018);
		appt.setTitle(null);
		assertTrue(appt.getValid());
		appt.setTitle("Birthday Party");
		appt.setDescription(null);
		assertTrue(appt.getValid());
		appt.setDescription("This is my birthday party.");
		appt.toString();
		appt.setStartHour(30);
		assertNull(appt.toString());
		appt.setStartHour(21);
		assertEquals(0, appt.compareTo(appt)); // self comparison results in no difference

		appt.setStartMonth(12);
		assertTrue(appt.getValid());
		appt.setStartMonth(01); // caught valid month being invalid
	}

	@Test
	public void test02() throws Throwable {
		Appt appt = new Appt(0, 0, 1, 1, 0, "", "");
		appt.setStartDay(25 | 0);
		assertTrue(appt.getStartDay() == 25);
		appt.setStartMinute(61 | 0);
		assertFalse(appt.getValid());
	}

	@Test
	public void test03() {
		Appt appt = new Appt(0, 0, 1, 1, 0, "", "");
		Appt appt2 = new Appt(23, 59, 31, 12, 9999, "1234567890", "abcdefghijklmnopqrstuvwxyz");
		int k = appt.compareTo(appt2);
		assertNotEquals(k, 0);
		k = appt2.compareTo(appt2);
		assertEquals(k, 0);
	}

	@Test
	public void test04() throws Throwable {
		Appt appt = new Appt(0, 0, 1, 1, 0, "", "");
		Appt appt1 = new Appt(0, 0, 1, 1, 0, "", "");
		Appt appt2 = new Appt(1, 33, 15, 1, 2019, "abc", "bac");
		Appt appt3 = new Appt(0, 33, 15, 1, 2019, "abc", "bac");
		Appt appt4 = new Appt(0, 61, 15, 1, 2019, "abc", "bac");
		Appt appt5 = new Appt(0, 33, 61, 1, 2019, "abc", "bac");
		Appt appt6 = new Appt(0, 0, 61, 1, 2019, "abc", "bac");

		int recurDays[] = { 1, 2, 3 };
		appt.setRecurrence(recurDays, 2, 1, 2);

		assertNotEquals("randomstring", appt2.toString());
		assertNotEquals("randomstring", appt3.toString());
		assertFalse(appt4.getValid());
		assertFalse(appt5.getValid());
		assertFalse(appt6.getValid());
		assertEquals(2, appt.getRecurNumber());
		assertEquals(2, appt.getRecurBy());
		assertEquals(1, appt.getRecurIncrement());
		assertTrue(appt.isRecurring());
		appt.setRecurrence(recurDays, 2, 1, 0);
		assertFalse(appt.isRecurring());
		assertEquals(appt1.toString(), appt.toString());
		assertNotEquals("randomstring", appt.toString());
		assertEquals(recurDays, appt.getRecurDays());

	}

}

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
		int[] a = { 0, 1 };
		appt.setRecurrence(a, 1, 0, 1);
		Appt appt2 = new Appt(startHour++, startMinute, startDay, startMonth, startYear++, title, description);
		appt2.setRecurrence(a, 2, 0, 1);
		GregorianCalendar cal1 = new GregorianCalendar(2015, 2, 12);
		GregorianCalendar cal2 = new GregorianCalendar(2018, 9, 11);
		CalDay testDays = new CalDay(cal1);
		testDays.addAppt(appt);
		testDays.addAppt(appt2);
		TimeTable testTable = new TimeTable();
		testTable.getApptRange(testDays.getAppts(), cal1, cal2);
		testTable.permute(testDays.getAppts(), a);
		// assertions
		assertNull(testTable.deleteAppt(testDays.getAppts(), appt2));
		assertNull(testTable.deleteAppt(null, null));
	}

	@Test
	public void test02() {
		GregorianCalendar cal = new GregorianCalendar(2000, 3, 24);
		TimeTable table = new TimeTable();
		Appt appt = new Appt(0, 15, 1, 1, 2000, "abc", "def");
		Appt appt2 = new Appt(0, 3, 1, 1, 2000, "ghi", "jkl");
		CalDay myDay = new CalDay(cal);
		myDay.addAppt(appt);
		myDay.addAppt(appt2);
		int permuteT[] = { 1, 0 };
		int permuteL[] = { 1, 2, 0 };
		int permuteS[] = { 0 };

		Appt a = myDay.getAppts().get(1);
		LinkedList<Appt> l = table.permute(myDay.getAppts(), permuteT);
		assertEquals(myDay.getSizeAppts(), l.size());

		try {
			table.permute(myDay.getAppts(), permuteL);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		try {
			table.permute(myDay.getAppts(), permuteS);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void test03() {
		TimeTable table = new TimeTable();
		GregorianCalendar cal = new GregorianCalendar(2000, 3, 24);
		GregorianCalendar cal2 = new GregorianCalendar(2000, 3, 28);
		GregorianCalendar cal3 = new GregorianCalendar(2019, 3, 13);
		GregorianCalendar cal4 = new GregorianCalendar(2019, 1, 30);
		GregorianCalendar cal5 = new GregorianCalendar(2019, 12, 30);
		CalDay myDay = new CalDay(cal);
		int recurWeek[] = { 0, 1, 1, 1, 1, 1, 0 };
		int noRecurrence[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
		Appt appt = new Appt(8, 30, 30, 1, 2000, "abc", "def");
		Appt appt2 = new Appt(14, 0, 30, 1, 2019, "ghi", "jkl");
		Appt appt3 = new Appt(12, 30, 30, 1, 2019, "mno", "pqr");
		Appt appt4 = new Appt(16, 0, 25, 12, 2019, "stu", "vwx");
		Appt appt5 = new Appt(23, 11, 29, 12, 2019, "", "");
		myDay.addAppt(appt);
		myDay.addAppt(appt2);
		myDay.addAppt(appt3);
		myDay.addAppt(appt4);
		myDay.addAppt(appt5);
		appt.setRecurrence(recurWeek, Appt.RECUR_BY_WEEKLY, 1, 10);
		appt2.setRecurrence(noRecurrence, Appt.RECUR_BY_MONTHLY, 1, 12);
		;
		appt3.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, 12);
		;
		appt4.setRecurrence(noRecurrence, Appt.RECUR_BY_WEEKLY, 1, 12);
		;
		appt5.setRecurrence(noRecurrence, Appt.RECUR_BY_YEARLY, 1, 12);
		;
		try {
			LinkedList<CalDay> range = table.getApptRange(myDay.getAppts(), cal, cal2);
			assertEquals(4, range.size());
			assertEquals(myDay.getDay(), range.get(0).getDay());
		} catch (DateOutOfRangeException e) {
			assertTrue(false);
		}

		try {
			LinkedList<CalDay> range = table.getApptRange(myDay.getAppts(), cal, cal3);
			assertEquals(myDay.getDay(), range.get(0).getDay());
		} catch (DateOutOfRangeException e) {
			assertTrue(false);
		}

		try {
			LinkedList<CalDay> range = table.getApptRange(myDay.getAppts(), cal, cal4);
			assertEquals(myDay.getDay(), range.get(0).getDay());
		} catch (DateOutOfRangeException e) {
			assertTrue(false);
		}

		try {
			table.getApptRange(myDay.getAppts(), cal2, cal);
		} catch (DateOutOfRangeException e) {
			assertTrue(true);
		}

		try {
			table.getApptRange(myDay.getAppts(), cal5, cal2);
		} catch (DateOutOfRangeException e) {
			assertTrue(true);
		}
		try {
			table.getApptRange(myDay.getAppts(), cal2, cal3);
		} catch (DateOutOfRangeException e) {
			assertTrue(true);
		}
	}

	@Test
	public void test04() {
		GregorianCalendar cal = new GregorianCalendar(2000, 3, 24);
		TimeTable table = new TimeTable();
		Appt appt = new Appt(0, 15, 1, 1, 2000, "abc", "def");
		Appt appt2 = new Appt(0, 3, 1, 1, 2000, "ghi", "jkl");
		Appt appt3 = new Appt(12, 30, 30, 1, 2019, "mno", "pqr");
		Appt appt4 = new Appt(12, 60, 29, 12, 2019, "", "");
		CalDay myDay = new CalDay(cal);
		myDay.addAppt(appt);
		myDay.addAppt(appt2);
		myDay.addAppt(appt3);
		myDay.addAppt(appt4);
		assertEquals(3, myDay.getSizeAppts());
		LinkedList<Appt> a = table.deleteAppt(myDay.getAppts(), appt);

		int initialSize = myDay.getSizeAppts();
		a = table.deleteAppt(myDay.getAppts(), null);
		assertNull(a);
		assertEquals(initialSize, myDay.getSizeAppts());
		a = table.deleteAppt(null, appt2);
		assertNull(a);
		assertEquals(initialSize, myDay.getSizeAppts());
		a = table.deleteAppt(myDay.getAppts(), appt4);
		assertNull(a);
		assertEquals(initialSize, myDay.getSizeAppts());
	}

	@Test
	public void test05() throws Throwable {

		TimeTable timetable = new TimeTable();
		LinkedList<Appt> apptList = new LinkedList<Appt>();

		Appt appt = new Appt(0, 30, 1, 2, 2018, "abc", "def");
		Appt appt2 = new Appt(0, 30, 1, 2, 2018, "ghi", "jkl");
		Appt appt3 = new Appt(0, 30, 1, 2, 2018, "mno", "pqr");
		Appt appt4 = new Appt(4, 30, 1, 2, 2018, "stu", "vwx");
		Appt appt5 = new Appt(1, 30, 1, 2, 2018, "yz_", "123");
		Appt appt6 = new Appt(1, 30, 5, 5, 2018, "456", "789");

		int[] recurD = { 0, 1, 2, 4 };
		int[] test = {};
		int[] recurI = { 1 };
		appt.setRecurrence(recurD, 1, 1, 6);
		appt2.setRecurrence(test, 1, 1, 6);
		appt3.setRecurrence(recurD, 1, 1, 6);
		apptList.add(appt);
		apptList.add(appt2);
		apptList.add(appt3);
		apptList.add(appt4);
		appt4.setRecurrence(recurD, 2, 1, 6);
		apptList.add(appt5);
		apptList.add(appt6);

		Calendar rightnow = Calendar.getInstance();

		int thisMonth = rightnow.get(Calendar.MONTH);
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);

		GregorianCalendar startCal = new GregorianCalendar(2018, 2, 1);
		GregorianCalendar endCal = new GregorianCalendar(2018, 3, 1);
		LinkedList<CalDay> output = timetable.getApptRange(apptList, startCal, endCal);
		String myString = "[\t --- 2/1/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,ghi, jkl\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \t2/1/2018 at 1:30am ,yz_, 123\n" +
                " \t2/1/2018 at 4:30am ,stu, vwx\n" +
                " \n" +
                ", \t --- 2/2/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/3/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/4/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \n" +
                ", \t --- 2/5/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \n" +
                ", \t --- 2/6/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/7/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \n" +
                ", \t --- 2/8/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,ghi, jkl\n" +
                " \n" +
                ", \t --- 2/9/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/10/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/11/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \n" +
                ", \t --- 2/12/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \n" +
                ", \t --- 2/13/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/14/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,abc, def\n" +
                " \t2/1/2018 at 12:30am ,mno, pqr\n" +
                " \n" +
                ", \t --- 2/15/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,ghi, jkl\n" +
                " \n" +
                ", \t --- 2/16/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/17/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/18/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/19/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/20/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/21/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/22/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,ghi, jkl\n" +
                " \n" +
                ", \t --- 2/23/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/24/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/25/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/26/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/27/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/28/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/29/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\t2/1/2018 at 12:30am ,ghi, jkl\n" +
                " \n" +
                ", \t --- 2/30/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                ", \t --- 2/31/2018 --- \n" +
                " --- -------- Appointments ------------ --- \n" +
                "\n" +
                "]";
		assertEquals(myString, output.toString());
	}
}

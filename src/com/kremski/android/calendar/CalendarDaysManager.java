package com.kremski.android.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Adrian Kremski
 */
class CalendarDaysManager {
	
	private Calendar currentCalendar = GregorianCalendar.getInstance();
	
	List<Day> getDaysToShow() {
		List<Day> partOfDays = generatePartOfDays(currentCalendar);
		List<Day> remainingDays = generateRemainingDays(currentCalendar, partOfDays);
		partOfDays.addAll(remainingDays);
		return partOfDays;
	}
	
	Calendar getCopyOfCurrentCalendar() { 
		return CalendarUtilities.copyCalendar(currentCalendar);
	}
	
	void addValueToSpecifiedFieldOfCalendar(int field, int value) {
		currentCalendar.add(field, value);
	}
	
	int getSpecifiedFieldValueFromCalendar(int field) {
		return currentCalendar.get(field);
	}
	
	private List<Day> generatePartOfDays(Calendar currentCalendar) {
		List<Day> days = new LinkedList<Day>();
		days.addAll(CalendarUtilities.getLastWeekBeforeSpecifiedMonth(CalendarUtilities.copyCalendar(currentCalendar)));
		days.addAll(CalendarUtilities.getAllDaysFromSpecifiedMonth(CalendarUtilities.copyCalendar(currentCalendar)));
		days.addAll(CalendarUtilities.getFirstWeekAfterSpecifiedMonth(CalendarUtilities.copyCalendar(currentCalendar)));
		return days;
	}
	
	private List<Day> generateRemainingDays(Calendar currentCalendar, List<Day> partOfDays) {
		List<Day> remainingDays = new LinkedList<Day>();
		if (partOfDays.size() == 35) {
			Calendar copiedCalendar = CalendarUtilities.copyCalendar(currentCalendar);
			copiedCalendar = CalendarUtilities.getCalendarSetToFirstDayOfNextMonth(copiedCalendar);
			int lastDay = partOfDays.get(partOfDays.size()-1).getDayOfMonth();
			remainingDays.addAll(CalendarUtilities.getDaysWithinRangeFromSpecifiedMonth(lastDay, lastDay+7, copiedCalendar));
		}
		return remainingDays;
	}
	
}

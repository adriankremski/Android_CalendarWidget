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
	
	private int maxAmountOfRowsWithWeeks = 0;
	
	CalendarDaysManager(int maxAmountOfRowsWithWeeks) {
		this.maxAmountOfRowsWithWeeks = maxAmountOfRowsWithWeeks;
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
	
	List<Day> getDaysToShowInCalendar() {
		List<Day> partOfDays = generatePartOfDays();
		List<Day> remainingDays = generateRemainingDaysIfNeccessary(partOfDays);
		partOfDays.addAll(remainingDays);
		return partOfDays;
	}
	
	private List<Day> generatePartOfDays() {
		List<Day> days = new LinkedList<Day>();
		days.addAll(CalendarUtilities.getLastWeekBeforeSpecifiedMonth(currentCalendar));
		days.addAll(CalendarUtilities.getAllDaysFromSpecifiedMonth(currentCalendar));
		return days;
	}
	
	private List<Day> generateRemainingDaysIfNeccessary(List<Day> partOfDays) {
		List<Day> remainingDays = new LinkedList<Day>();
		
		int maxAmountOfDays = maxAmountOfRowsWithWeeks * 7;
		if (partOfDays.size() < maxAmountOfDays) {
			remainingDays.addAll(CalendarUtilities.getDaysRangeFromSpecifiedMonth(1, 
					maxAmountOfDays - partOfDays.size(), getPreparedCalendar(currentCalendar)));
		}
		return remainingDays;
	}
	
	private Calendar getPreparedCalendar(Calendar currentCalendar) {
		Calendar preparedCalendar = CalendarUtilities.getCalendarSetToFirstDayOfNextMonth(currentCalendar);
		return preparedCalendar;
	}
	
}

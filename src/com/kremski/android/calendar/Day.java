package com.kremski.android.calendar;

import java.util.Calendar;

public class Day {
	
	private Calendar dayCalendar = null;
	private static final Calendar currentMonthCalendar = Calendar.getInstance();
	
	public Day(Calendar calendar) {
		dayCalendar = CalendarUtilities.copyCalendar(calendar);
	}
	
	public int getDayOfWeek() {
		return dayCalendar.get(Calendar.DAY_OF_WEEK);
	}

	public int getDayOfMonth() {
		return dayCalendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getMonth() {
		return dayCalendar.get(Calendar.MONTH);
	}
	
	public long getTime() {
		return dayCalendar.getTimeInMillis();
	}
	
	public int getYear() {
		return dayCalendar.get(Calendar.YEAR);
	}
	
	public boolean isToday() {
		return isInCurrentMonth() && 
				dayCalendar.get(Calendar.DAY_OF_MONTH) == currentMonthCalendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public boolean isInCurrentMonth() {
		return dayCalendar.get(Calendar.MONTH) == currentMonthCalendar.get(Calendar.MONTH) &&
			   isInSpecifiedYear(currentMonthCalendar);
	}
	
	public boolean isInPreviousMonth() {
		if (isInSpecifiedYear(currentMonthCalendar)) {
			return dayCalendar.get(Calendar.MONTH) < currentMonthCalendar.get(Calendar.MONTH);
		}
		else if (isInYearBeforeSpecifiedOne(currentMonthCalendar)){
			return dayCalendar.get(Calendar.MONTH) == Calendar.DECEMBER &&
				   currentMonthCalendar.get(Calendar.MONTH) == Calendar.JANUARY;
		}
		return false;
	}

	public boolean isInNextMonth() {
		if (isInSpecifiedYear(currentMonthCalendar)) {
			return dayCalendar.get(Calendar.MONTH) < currentMonthCalendar.get(Calendar.MONTH);
		}
		else if (isInYearAfterSpecifiedOne(currentMonthCalendar)){
			return dayCalendar.get(Calendar.MONTH) == Calendar.JANUARY &&
				   currentMonthCalendar.get(Calendar.MONTH) == Calendar.DECEMBER;
		}
		return false;
	}
	
	public boolean isInSpecifiedYear(Calendar specifiedCalendar) {
		return dayCalendar.get(Calendar.YEAR) == specifiedCalendar.get(Calendar.YEAR);
	}
	
	public boolean isInYearAfterSpecifiedOne(Calendar specifiedCalendar) {
		return dayCalendar.get(Calendar.YEAR) - 1 == specifiedCalendar.get(Calendar.YEAR);
	}
	
	public boolean isInYearBeforeSpecifiedOne(Calendar specifiedCalendar) {
		return dayCalendar.get(Calendar.YEAR) + 1 == specifiedCalendar.get(Calendar.YEAR);
	}
	
	public boolean isInSpecifiedMonth(Calendar specifiedCalendar) {
		return dayCalendar.get(Calendar.MONTH) == specifiedCalendar.get(Calendar.MONTH) &&
			   isInSpecifiedYear(specifiedCalendar);
	}
	
	public boolean isInMonthBeforeSpecifiedOne(Calendar specifiedCalendar) {
		if (isInSpecifiedYear(specifiedCalendar)) {
			return dayCalendar.get(Calendar.MONTH) < specifiedCalendar.get(Calendar.MONTH);
		}
		else if (isInYearBeforeSpecifiedOne(specifiedCalendar)){
			return dayCalendar.get(Calendar.MONTH) == Calendar.DECEMBER &&
				   specifiedCalendar.get(Calendar.MONTH) == Calendar.JANUARY;
		}
		return false;
	}

	public boolean isInMonthAfterSpecifiedOne(Calendar specifiedCalendar) {
		if (isInSpecifiedYear(specifiedCalendar)) {
			return dayCalendar.get(Calendar.MONTH) > specifiedCalendar.get(Calendar.MONTH);
		}
		else if (isInYearAfterSpecifiedOne(specifiedCalendar)){
			return dayCalendar.get(Calendar.MONTH) == Calendar.JANUARY &&
				   specifiedCalendar.get(Calendar.MONTH) == Calendar.DECEMBER;
		}
		return false;
	}
	
	
}

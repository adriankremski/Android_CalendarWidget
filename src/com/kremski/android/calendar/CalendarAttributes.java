package com.kremski.android.calendar;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Color;

import com.kremski.utilities.ExceptionUtilities;

/**
 * @author Adrian Kremski
 */
public class CalendarAttributes {
	
	private int parentHeightDivisionValue = 7;
	private int cellTextSize = 12;
	
	private int lightBlueColor = Color.parseColor("#2fa9ef");
	private int lightGrayColor = Color.parseColor("#d8d8d8");
			
	private int monthNameColor = lightBlueColor;
	private int cellBorderColor = Color.WHITE;
	private int cellTextColor = Color.BLACK;
	private int dayOfWeekNameColor = 0;
	
	private int currentDayCellBgColor = lightBlueColor;
	private int previousMonthCellBgColor = lightGrayColor;
	private int currentMonthCellBgColor = Color.WHITE;
	private int nextMonthCellBgColor = lightGrayColor;
	
	private Map<Integer, String> monthNames = new HashMap<Integer, String>(12);
	private Map<Integer, String> dayOfWeekNames = new HashMap<Integer, String>(7);
	private Map<Integer, Integer> dayOfWeekNameColors = new HashMap<Integer, Integer>(7);
	
	public CalendarAttributes() {
		initalizeDefaultMonthNames();
		initalizeDefaultDayOfWeekNames();
		initializeDefaultDayOfWeekNameColors();
	}
	
	public void setCellTextSize(int cellTextSize) {
		ExceptionUtilities.throwRuntimeExceptionIfConditionIsTrue(
				new IllegalArgumentException("cellTextSize must be > 0"),
				cellTextSize <= 0);
		this.cellTextSize = cellTextSize;
	}
	
	public int getCellTextSize() {
		return cellTextSize;
	}
	
	public void setParentHeightDivisionValue(int parentHeightDivisionValue) {
		ExceptionUtilities.throwRuntimeExceptionIfConditionIsTrue(
				new IllegalArgumentException("parentHeightDivisionValue must be > 0"),
				parentHeightDivisionValue <= 0);
		this.parentHeightDivisionValue = parentHeightDivisionValue;
	}
	
	public int getCellSize(int parentHeight) {
		return parentHeight / parentHeightDivisionValue;
	}
	
	public int getMonthNameColor() {
		return monthNameColor;
	}
	
	public void setMonthNameColor(int monthNameColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("color can't be null"), monthNameColor);
		this.monthNameColor = monthNameColor;
	}
	
	public int getCellBorderColor() {
		return cellBorderColor;
	}
	
	public void setCellBorderColor(int cellBorderColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("color can't be null"), cellBorderColor);
		this.cellBorderColor = cellBorderColor;
	}
	
	public int getCellTextColor() {
		return cellTextColor;
	}
	
	public void setCellTextColor(int cellTextColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("color can't be null"), cellTextColor);
		this.cellTextColor = cellTextColor;
	}
	
	public int getDayOfWeekNameColor() {
		return dayOfWeekNameColor;
	}
	
	public void setDayOfWeekNameColor(int dayOfWeekNameColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("int can't be null"), dayOfWeekNameColor);
		this.dayOfWeekNameColor = dayOfWeekNameColor;
	}
	
	public int getPreviousMonthCellBgColor() {
		return previousMonthCellBgColor;
	}
	
	public void setPreviousMonthCellBgColor(int previousMonthCellBgColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("int can't be null"), previousMonthCellBgColor);
		this.previousMonthCellBgColor = previousMonthCellBgColor;
	}
	
	public int getCurrentMonthCellBgColor() {
		return currentMonthCellBgColor;
	}
	
	public void setCurrentMonthCellBgColor(int currentMonthCellBgColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("int can't be null"), currentMonthCellBgColor);
		this.currentMonthCellBgColor = currentMonthCellBgColor;
	}
	
	public int getNextMonthCellBgColor() {
		return nextMonthCellBgColor;
	}
	
	public void setNextMonthCellBgColor(int nextMonthCellBgColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("color can't be null"), nextMonthCellBgColor);
		this.nextMonthCellBgColor = nextMonthCellBgColor;
	}
	
	public int getCurrentDayCellBgColor() {
		return currentDayCellBgColor;
	}
	
	public void setCurrentDayCellBgColor(int currentDayCellBgColor) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("color can't be null"), currentDayCellBgColor);
		this.currentDayCellBgColor = currentDayCellBgColor;
	}
	
	public void setMonthNames(Map<Integer, String> monthNames) {
		this.monthNames = new HashMap<Integer, String>(monthNames);
	}
	
	public String getMonthNameByMonthNumber(int monthNumber) {
		ExceptionUtilities.throwRuntimeExceptionIfConditionIsTrue(
				new IllegalArgumentException("monthNumber must be in range from 0 to 11"),
				monthNumber < 0 || monthNumber > 11);
		return monthNames.get(monthNumber);
	}
	
	public void setDayOfWeekNames(Map<Integer, String> dayOfWeekNames) {
		this.dayOfWeekNames = new HashMap<Integer, String>(dayOfWeekNames);
	}
	
	public String getDayOfWeekNameByDayNumber(int dayNumber) {
		ExceptionUtilities.throwRuntimeExceptionIfConditionIsTrue(
				new IllegalArgumentException("dayNumber must be in range from 0 to 6"),
					dayNumber < 0 || dayNumber > 6);
		return dayOfWeekNames.get(dayNumber);
	}
	
	public void setDayOfWeekNameColors(Map<Integer, Integer> dayOfWeekNameColors) {
		this.dayOfWeekNameColors = new HashMap<Integer, Integer>(dayOfWeekNameColors);
	}
	
	public Integer getDayOfWeekNameColorByDayNumber(int dayNumber) {
		ExceptionUtilities.throwRuntimeExceptionIfConditionIsTrue(
				new IllegalArgumentException("dayNumber must be in range from 0 to 6"),
					dayNumber < 0 || dayNumber > 6);
		return dayOfWeekNameColors.get(dayNumber);
	}
	
	private void initalizeDefaultMonthNames() {
		monthNames.put(0, "january");
		monthNames.put(1, "february");
		monthNames.put(2, "march");
		monthNames.put(3, "april");
		monthNames.put(4, "may");
		monthNames.put(5, "june");
		monthNames.put(6, "july");
		monthNames.put(7, "august");
		monthNames.put(8, "september");
		monthNames.put(9, "october");
		monthNames.put(10, "november");
		monthNames.put(11, "december");
	}
	
	private void initalizeDefaultDayOfWeekNames() {
		dayOfWeekNames.put(0, "mon.");
		dayOfWeekNames.put(1, "tue.");
		dayOfWeekNames.put(2, "wed.");
		dayOfWeekNames.put(3, "thu.");
		dayOfWeekNames.put(4, "fri.");
		dayOfWeekNames.put(5, "sat.");
		dayOfWeekNames.put(6, "sun.");
	}
	
	private void initializeDefaultDayOfWeekNameColors() {
		dayOfWeekNameColors.put(0, Color.WHITE);
		dayOfWeekNameColors.put(1, Color.WHITE);
		dayOfWeekNameColors.put(2, Color.WHITE);
		dayOfWeekNameColors.put(3, Color.WHITE);
		dayOfWeekNameColors.put(4, Color.WHITE);
		dayOfWeekNameColors.put(5, lightBlueColor);
		dayOfWeekNameColors.put(6, lightBlueColor);
	}
}

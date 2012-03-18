package com.kremski.android.calendar;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.kremski.android.calendar.CalendarGridView.MovementType;
import com.kremski.utilities.ExceptionUtilities;
import com.kremski.android.calendar.R;

/**
 * @author Adrian Kremski
 */
public class CalendarView extends LinearLayout {
	
	private CalendarAdapter calendarAdapter = null;
	
	private TextView monthLabel = null;
	private ViewSwitcher calendarSwitcher = null;
	
	private boolean animatedSwitching = true;
	
	private CalendarSwipeAnimations calendarAnimations = null;
	private CalendarDaysManager calendarDaysManager = new CalendarDaysManager(7);
	private CalendarAttributes calendarAttrs = new CalendarAttributes();
	private CellOnClickListener cellOnClickListener = new CellOnClickListener() {
		
		@Override
		public void onClick(View view, Day day) {
			
		}
	};
	
	private TextView mondayLabel = null;
	private TextView tuesdayLabel = null;
	private TextView wednesdayLabel = null;
	private TextView thursdayLabel = null;
	private TextView fridayLabel = null;
	private TextView saturdayLabel = null;
	private TextView sundayLabel = null;
	
	public CalendarView(Context context) {
		super(context);
		initializeCalendarView();
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeCalendarView();
	}

	public void turnOnAnimation() {
		animatedSwitching = true;
	}
	
	public void turnOffAnimation() {
		animatedSwitching = false;
	}
	
	public void setCalendarSwipeAnimations(CalendarSwipeAnimations calendarAnimations) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("calendarAnimations can't be null"), calendarAnimations);
		this.calendarAnimations = calendarAnimations;
	}
	
	public CalendarAttributes getCalendarAttributes() {
		return calendarAttrs;
	}
	
	public void setCalendarAttributes(CalendarAttributes calendarAttrs) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("calendarAttrs can't be null"), calendarAttrs);
		this.calendarAttrs = calendarAttrs;
		calendarAdapter.setCalendarAttributes(calendarAttrs);
		monthLabel.setTextColor(calendarAttrs.getMonthNameColor());
		setProperTextForDayOfWeekLabels();
		setProperColorsForDayOfWeekLabels();
	}
	
	public void setCellOnClickListener(CellOnClickListener cellOnClickListener) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("cellOnClickListener can't be null"), cellOnClickListener);
		this.cellOnClickListener = cellOnClickListener;
		calendarAdapter.setCellOnClickListener(cellOnClickListener);
	}
	
	public void showNextDate(MovementType type) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("type can't be null"), type);
		setCalendarToChoosedDate(type);
		initializeMonthLabel(monthLabel);
		showNextDateProperly(type);
	}

	public void showPreviousDate(MovementType type) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("type can't be null"), type);
		setCalendarToChoosedDate(type);
		initializeMonthLabel(monthLabel);
		showPreviousDateProperly(type);
	}
	
	public Calendar getCurrentCalendar() {
		return calendarDaysManager.getCopyOfCurrentCalendar();
	}
	
	private void initializeCalendarView() {
		calendarAnimations = new CalendarSwipeAnimations(getContext());
		ViewGroup linearLayout = (ViewGroup)getCalendarLayout().findViewById(R.id.calendar);
		initializeCalendarGridViewSwitcher(linearLayout);
		initializeCalendarDaysGrid(linearLayout, calendarDaysManager.getDaysToShowInCalendar());
		initializeMonthLabel((TextView)linearLayout.findViewById(R.id.calendar_month));
		initializeDayOfWeekLabels(linearLayout);
	}

	private ViewGroup getCalendarLayout() {
		String service = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(service);	
		return ((ViewGroup)inflater.inflate(R.layout.calendar, this, true));
	}
	
	private void initializeCalendarGridViewSwitcher(ViewGroup layout) {
		calendarSwitcher = (ViewSwitcher)layout.findViewById(R.id.calendar_grid_switcher);
	}
	
	private void initializeCalendarDaysGrid(ViewGroup layout, List<Day> days) {
		CalendarGridView daysGrid = (CalendarGridView)layout.findViewById(R.id.calendar_grid_view);
		calendarAdapter = new CalendarAdapter(this, days, cellOnClickListener);
		daysGrid.setCalendarView(this);
		daysGrid.setAdapter(calendarAdapter);
		daysGrid.setFocusable(true);
	}
	
	private void initializeMonthLabel(TextView monthLabel) {
		this.monthLabel = monthLabel;
		int currentMonthNum = calendarDaysManager.getSpecifiedFieldValueFromCalendar(Calendar.MONTH);
		monthLabel.setTextColor(calendarAttrs.getMonthNameColor());
		monthLabel.setText(calendarAttrs.getMonthNameByMonthNumber(currentMonthNum) + " (" + 
				calendarDaysManager.getSpecifiedFieldValueFromCalendar(Calendar.YEAR) + ")");
	}

	private void initializeDayOfWeekLabels(ViewGroup layout) {
		getDayOfWeekLabelsFromLayout(layout);
		setProperTextForDayOfWeekLabels();
		setProperColorsForDayOfWeekLabels();
	}
	
	private void getDayOfWeekLabelsFromLayout(ViewGroup layout) {
		mondayLabel = (TextView)layout.findViewById(R.id.calendar_monday_label);
		tuesdayLabel = (TextView)layout.findViewById(R.id.calendar_tuesday_label);
		wednesdayLabel = (TextView)layout.findViewById(R.id.calendar_wednesday_label);
		thursdayLabel = (TextView)layout.findViewById(R.id.calendar_thursday_label);
		fridayLabel = (TextView)layout.findViewById(R.id.calendar_friday_label);
		saturdayLabel = (TextView)layout.findViewById(R.id.calendar_saturday_label);
		sundayLabel = (TextView)layout.findViewById(R.id.calendar_sunday_label);
	}
	
	private void setProperTextForDayOfWeekLabels() {
		mondayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(0));
		tuesdayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(1));
		wednesdayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(2));
		thursdayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(3));
		fridayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(4));
		saturdayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(5));
		sundayLabel.setText(calendarAttrs.getDayOfWeekNameByDayNumber(6));
	}
	
	private void setProperColorsForDayOfWeekLabels() {
		mondayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(0));
		tuesdayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(1));
		wednesdayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(2));
		thursdayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(3));
		fridayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(4));
		saturdayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(5));
		sundayLabel.setTextColor(calendarAttrs.getDayOfWeekNameColorByDayNumber(6));
	}
	
	private void setCalendarToChoosedDate(MovementType type) {
		switch(type) {
			case FROM_BOTTOM_TO_TOP : {
				calendarDaysManager.addValueToSpecifiedFieldOfCalendar(Calendar.YEAR, -1);
				break;
			}
			case FROM_TOP_TO_BOTTOM : {
				calendarDaysManager.addValueToSpecifiedFieldOfCalendar(Calendar.YEAR, 1);
				break;
			}
			case FROM_LEFT_TO_RIGHT : {
				calendarDaysManager.addValueToSpecifiedFieldOfCalendar(Calendar.MONTH, -1);
				break;
			}
			case FROM_RIGHT_TO_LEFT : {
				calendarDaysManager.addValueToSpecifiedFieldOfCalendar(Calendar.MONTH, 1);
				break;
			}
		}
	}
	
	private void showNextDateProperly(MovementType type) {
		if (animatedSwitching) {
			showNextDateWithAnimation(type);
		}
		else {
			showNextDateWithoutAnimation();
		}
	}
	
	private void showNextDateWithAnimation(MovementType type) {
		setAppropriateNextAnimation(type);
		generateAndShowNextMonth();
		calendarSwitcher.removeViewAt(0);
	}
	
	private void setAppropriateNextAnimation(MovementType type) {
		if (type == MovementType.FROM_RIGHT_TO_LEFT) {
			calendarSwitcher.setInAnimation(calendarAnimations.getInFromRightAnimation());
			calendarSwitcher.setOutAnimation(calendarAnimations.getOutFromRightAnimation());
		}
		else if (type == MovementType.FROM_TOP_TO_BOTTOM) {
			calendarSwitcher.setInAnimation(calendarAnimations.getInFromTopAnimation());
			calendarSwitcher.setOutAnimation(calendarAnimations.getOutFromTopAnimation());
		}
	}
	
	private void generateAndShowNextMonth() {
		GridView nextDaysGrid = getDaysGrid(
				(ViewFlipper.LayoutParams)calendarSwitcher.getCurrentView().getLayoutParams(),
				calendarDaysManager.getDaysToShowInCalendar());
		nextDaysGrid.setNumColumns(7);
		calendarSwitcher.addView(nextDaysGrid, 1);
		calendarSwitcher.showNext();
	}
	
	private CalendarGridView getDaysGrid(ViewFlipper.LayoutParams params, List<Day> days) {
		CalendarGridView daysGrid = new CalendarGridView(getContext());
		daysGrid.setLayoutParams(params);
		daysGrid.setCalendarView(this);
		calendarAdapter = new CalendarAdapter(this, days, cellOnClickListener);
		daysGrid.setAdapter(calendarAdapter);
		return daysGrid;
	}
	
	private void showNextDateWithoutAnimation() {
		calendarAdapter.setDays(calendarDaysManager.getDaysToShowInCalendar());
		calendarAdapter.setCurrentMonth(calendarDaysManager.getCopyOfCurrentCalendar());
		calendarAdapter.notifyDataSetChanged();
	}
	
	private void showPreviousDateProperly(MovementType type) {
		if (animatedSwitching) {
			showPreviousDateWithAnimation(type);
		}
		else {
			showPreviousDateWithoutAnimation();
		}
	}
	
	private void showPreviousDateWithAnimation(MovementType type) {
		setAppropriatePreviousAnimation(type);
		generateAndShowPreviousMonth(); 
		calendarSwitcher.removeViewAt(0);
	}
	
	private void setAppropriatePreviousAnimation(MovementType type) {
		if (type == MovementType.FROM_LEFT_TO_RIGHT) {
			calendarSwitcher.setInAnimation(calendarAnimations.getInFromLeftAnimation());
			calendarSwitcher.setOutAnimation(calendarAnimations.getOutFromLeftAnimation());
		}
		else if (type == MovementType.FROM_BOTTOM_TO_TOP) {
			calendarSwitcher.setInAnimation(calendarAnimations.getInFromBottomAnimation());
			calendarSwitcher.setOutAnimation(calendarAnimations.getOutFromBottomAnimation());
		}
	}
	
	private void generateAndShowPreviousMonth() {
		GridView prevDaysGrid = getDaysGrid(
				(ViewSwitcher.LayoutParams)calendarSwitcher.getCurrentView().getLayoutParams(), 
				calendarDaysManager.getDaysToShowInCalendar());
		prevDaysGrid.setNumColumns(7);
		calendarSwitcher.addView(prevDaysGrid, 1);
		calendarSwitcher.showNext();
	}
	
	private void showPreviousDateWithoutAnimation() {
		calendarAdapter.setDays(calendarDaysManager.getDaysToShowInCalendar());
		calendarAdapter.setCurrentMonth(calendarDaysManager.getCopyOfCurrentCalendar());
		calendarAdapter.notifyDataSetChanged();
	}
}

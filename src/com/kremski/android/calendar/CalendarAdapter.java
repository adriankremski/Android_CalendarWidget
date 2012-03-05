package com.kremski.android.calendar;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.kremski.utilities.ExceptionUtilities;

/**
 * @author Adrian Kremski
 */
class CalendarAdapter extends BaseAdapter{

	private List<Day> calendarDays = null;
	private Calendar month = null;
	
	private CalendarAttributes calendarAttrs = null;
	private CellOnClickListener cellOnClickListener = null;
	
	public CalendarAdapter(CalendarView calendarView, List<Day> calendarDays,
			CellOnClickListener cellOnClickListener) {
		initializeVariables(calendarView, calendarDays, cellOnClickListener);
	}
	
	public void setCurrentMonth(Calendar month) {
		this.month = month;
	}
	
	public void setDays(List<Day> days) {
		calendarDays = new LinkedList<Day>(days);
	}
	
	public void setCalendarAttributes(CalendarAttributes calendarAttrs) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("calendarAttrs can't be null"), calendarAttrs);
		this.calendarAttrs = calendarAttrs;
	}
	
	@Override
	public int getCount() {
		return calendarDays.size();
	}

	@Override
	public Object getItem(int position) {
		return calendarDays.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void setCellOnClickListener(CellOnClickListener cellOnClickListener) {
		this.cellOnClickListener = cellOnClickListener;
	}
	
	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		final Day day = calendarDays.get(position);
		CalendarCell cell = new CalendarCell(parent, day, calendarAttrs);
		cell.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cellOnClickListener.onClick(v, day);
			}
		});
		setAppropriateCellBackground(cell, day);
		return cell;
	}

	private void initializeVariables(CalendarView calendarView, List<Day> calendarDays, CellOnClickListener cellOnClickListener) {
		this.calendarDays = calendarDays;
		this.month = calendarView.getCopyOfCurrentCalendar();
		calendarAttrs = calendarView.getCalendarAttributes();
		this.cellOnClickListener = cellOnClickListener;
	}
	
	private void setAppropriateCellBackground(CalendarCell calendarCell, Day day) {
		if (day.isToday()) {
			calendarCell.setBackgroundColor(calendarAttrs.getCurrentDayCellBgColor());
		}
		else if (day.isInSpecifiedMonth(month)) {
			calendarCell.setBackgroundColor(calendarAttrs.getCurrentMonthCellBgColor());
		}
		else if (day.isInMonthBeforeSpecifiedOne(month)) {
			calendarCell.setBackgroundColor(calendarAttrs.getPreviousMonthCellBgColor());
		}
		else if (day.isInMonthAfterSpecifiedOne(month)) {
			calendarCell.setBackgroundColor(calendarAttrs.getNextMonthCellBgColor());
		}
	}

}

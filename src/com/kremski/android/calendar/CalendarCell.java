package com.kremski.android.calendar;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author Adrian Kremski
 */
class CalendarCell extends Button{

	private Paint backgroundPaint = null;
	
	public CalendarCell(ViewGroup parent, Day day, CalendarAttributes calendarAttrs) {
		super(parent.getContext());
		initialize(day, parent.getHeight(), calendarAttrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int measuredHeight = getMeasuredHeight();
		int measuredWidth = getMeasuredWidth();
		
		drawBackground(canvas, measuredWidth, measuredHeight);
	}
	
	private void initialize(Day day, int parentHeight, CalendarAttributes calendarAttrs) {
		initializeVariables(calendarAttrs);
		initializeAttributes(day, parentHeight, calendarAttrs);
	}
	
	private void initializeVariables(CalendarAttributes calendarAttrs) {
		backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		backgroundPaint.setColor(calendarAttrs.getCellBorderColor());
	}
	
	private void initializeAttributes(Day day, int parentHeight, CalendarAttributes calendarAttrs)  {
		setGravity(Gravity.CENTER);
		setTextSize(calendarAttrs.getCellTextSize());
		setTextColor(calendarAttrs.getCellTextColor());
		setHeight(calendarAttrs.getCellSize(parentHeight));
		setText(Integer.toString(day.getDayOfMonth()));
	}
	
	private void drawBackground(Canvas canvas, int measuredWidth, int measuredHeight){
		canvas.drawLine(0, 0, measuredWidth, 0, backgroundPaint);
		canvas.drawLine(measuredWidth, 0, measuredWidth, measuredHeight, backgroundPaint);
		canvas.drawLine(0, measuredHeight, measuredWidth, measuredHeight, backgroundPaint);
		canvas.drawLine(0, 0, 0, measuredHeight, backgroundPaint);
	}

}

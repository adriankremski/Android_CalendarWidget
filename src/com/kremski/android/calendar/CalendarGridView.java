package com.kremski.android.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * @author Adrian Kremski
 */
public class CalendarGridView extends GridView implements OnGestureListener{

	public enum MovementType {
		FROM_LEFT_TO_RIGHT,
		FROM_RIGHT_TO_LEFT,
		FROM_BOTTOM_TO_TOP,
		FROM_TOP_TO_BOTTOM
	}
	
	private static final int MIN_HORIZONTAL_DISTANCE = 240;
	private static final int MIN_VERTICAL_DISTANCE = 300;
	
	private GestureDetector gestureDetector = null;
	
	private CalendarView calendarView = null;
	
	public CalendarGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	public CalendarGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public CalendarGridView(Context context) {
		super(context);
		initialize();
	}

	public void setCalendarView(CalendarView calendarView) {
		this.calendarView = calendarView;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return gestureDetector.onTouchEvent(ev);
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (velocityX < 0 && Math.abs(velocityX) > MIN_HORIZONTAL_DISTANCE) {
			calendarView.showNextDate(MovementType.FROM_RIGHT_TO_LEFT);
			return true;
		}
		else if (velocityX  > 0 && velocityX > MIN_HORIZONTAL_DISTANCE) {
			calendarView.showPreviousDate(MovementType.FROM_LEFT_TO_RIGHT);
			return true;
		}
		if (velocityY < 0 && Math.abs(velocityY) > MIN_VERTICAL_DISTANCE) {
			calendarView.showNextDate(MovementType.FROM_TOP_TO_BOTTOM);
			return true;
		}
		else if (velocityY > 0 && velocityY > MIN_VERTICAL_DISTANCE) {
			calendarView.showPreviousDate(MovementType.FROM_BOTTOM_TO_TOP);
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	private void initialize() {
		gestureDetector = new GestureDetector(this);
	}
}

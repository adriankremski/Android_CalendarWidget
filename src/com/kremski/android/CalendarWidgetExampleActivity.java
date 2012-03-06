package com.kremski.android;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.kremski.android.calendar.CalendarAttributes;
import com.kremski.android.calendar.CalendarSwipeAnimations;
import com.kremski.android.calendar.CalendarView;
import com.kremski.android.calendar.CellOnClickListener;
import com.kremski.android.calendar.Day;
import com.kremski.android.calendar.R;

public class CalendarWidgetExampleActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeCalendarView();
    }
    
    private void initializeCalendarView() {
    	CalendarView calendarView = (CalendarView)findViewById(R.id.calendar_view);
    	initializeSwipeAnimations(calendarView);
    	initializeCalendarAttributes(calendarView);
    	initializeCellOnClickListener(calendarView);
    }
    
    private void initializeSwipeAnimations(CalendarView calendarView) {
    	CalendarSwipeAnimations csa = new CalendarSwipeAnimations(this);
    	initializeInAnimations(csa);
    	initializeOutAnimations(csa);
    	csa.setAnimationDuration(500);
    	calendarView.setCalendarSwipeAnimations(csa);
    }
    
    private void initializeInAnimations(CalendarSwipeAnimations csa) {
		csa.setInFromLeftAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_left));
		csa.setInFromRightAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_right));	
		csa.setInFromBottomAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_bottom));
		csa.setInFromTopAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_top));
	}
	
	private void initializeOutAnimations(CalendarSwipeAnimations csa) { 
		csa.setOutFromLeftAnimation(AnimationUtils.loadAnimation(this, R.anim.out_from_left));
		csa.setOutFromRightAnimation(AnimationUtils.loadAnimation(this, R.anim.out_from_right));		
		csa.setOutFromBottomAnimation(AnimationUtils.loadAnimation(this, R.anim.out_from_bottom));
		csa.setOutFromTopAnimation(AnimationUtils.loadAnimation(this, R.anim.out_from_top));
	}
	
	private void initializeCalendarAttributes(CalendarView calendarView) {
		int myGreen = Color.parseColor("#009E5F");
		CalendarAttributes cA = new CalendarAttributes();
		cA.setCurrentDayCellBgColor(myGreen);
		cA.setMonthNameColor(myGreen);
		initializeDayOfWeekNameColors(cA, myGreen);
		calendarView.setCalendarAttributes(cA);
	}
	
	private void initializeCellOnClickListener(CalendarView calendarView) {
		calendarView.setCellOnClickListener(new CellOnClickListener() {
			
			@Override
			public void onClick(View view, Day day) {
				Toast.makeText(getBaseContext(), day.getDayOfMonth() + "." + day.getMonth(), 500).show();
			}
		});
	}
	
	private void initializeDayOfWeekNameColors(CalendarAttributes cA, int weekendColor) {
		Map<Integer, Integer> dayOfWeekNameColors = new HashMap<Integer, Integer>(7);
		dayOfWeekNameColors.put(0, Color.WHITE);
		dayOfWeekNameColors.put(1, Color.WHITE);
		dayOfWeekNameColors.put(2, Color.WHITE);
		dayOfWeekNameColors.put(3, Color.WHITE);
		dayOfWeekNameColors.put(4, Color.WHITE);
		dayOfWeekNameColors.put(5, weekendColor);
		dayOfWeekNameColors.put(6, weekendColor);
		cA.setDayOfWeekNameColors(dayOfWeekNameColors);
	}
}
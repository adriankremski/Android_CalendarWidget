package com.kremski.android.calendar;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kremski.utilities.ExceptionUtilities;

/**
 * @author Adrian Kremski
 */
public class CalendarSwipeAnimations {

	private Context context = null;
	
	private Animation inFromLeftAnimation = null;
	private Animation inFromRightAnimation = null;
	private Animation outFromRightAnimation  = null;
	private Animation outFromLeftAnimation  = null;
	private Animation inFromBottomAnimation  = null;
	private Animation inFromTopAnimation  = null;
	private Animation outFromBottomAnimation  = null;
	private Animation outFromTopAnimation  = null;
	
	public CalendarSwipeAnimations(Context context) {
		this.context = context;
		initializeDefaultAnimations();
		setAnimationDuration(500);
	}
	
	public Animation getInFromLeftAnimation() {
		return inFromLeftAnimation;
	}
	
	public void setInFromLeftAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.inFromLeftAnimation = animation;
	}
	
	public Animation getInFromRightAnimation() {
		return inFromRightAnimation;
	}
	
	public void setInFromRightAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.inFromRightAnimation = animation;
	}
	
	public Animation getOutFromRightAnimation() {
		return outFromRightAnimation;
	}
	
	public void setOutFromRightAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.outFromRightAnimation = animation;
	}
	
	public Animation getOutFromLeftAnimation() {
		return outFromLeftAnimation;
	}
	
	public void setOutFromLeftAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.outFromLeftAnimation = animation;
	}
	
	public Animation getInFromBottomAnimation() {
		return inFromBottomAnimation;
	}
	
	public void setInFromBottomAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.inFromBottomAnimation = animation;
	}
	
	public Animation getInFromTopAnimation() {
		return inFromTopAnimation;
	}
	
	public void setInFromTopAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.inFromTopAnimation = animation;
	}
	
	public Animation getOutFromBottomAnimation() {
		return outFromBottomAnimation;
	}
	
	public void setOutFromBottomAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.outFromBottomAnimation = animation;
	}
	
	public Animation getOutFromTopAnimation() {
		return outFromTopAnimation;
	}
	
	public void setOutFromTopAnimation(Animation animation) {
		ExceptionUtilities.throwRuntimeExceptionIfObjectIsNull(
				new IllegalArgumentException("animation can't be null"), animation);
		this.outFromTopAnimation = animation;
	}
	
	public void setAnimationDuration(int duration) {
		ExceptionUtilities.throwRuntimeExceptionIfConditionIsTrue(
				new IllegalArgumentException("duration can't be less than 0"), duration < 0);
		
		setDurationForInAnimations(duration);
		setDurationForOutAnimations(duration);
	}
	
	private void initializeDefaultAnimations() {
		initializeDefaultInAnimations();
		initializeDefaultOutAnimations();
	}
	
	private void initializeDefaultInAnimations() {
		inFromLeftAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		inFromRightAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		inFromBottomAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		inFromTopAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
	}
	
	private void initializeDefaultOutAnimations() {
		outFromLeftAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		outFromRightAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		outFromBottomAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		outFromTopAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
	}
	
	private void setDurationForInAnimations(int duration) {
		inFromLeftAnimation.setDuration(duration);
		inFromRightAnimation.setDuration(duration);
		inFromBottomAnimation.setDuration(duration);
		inFromTopAnimation.setDuration(duration);
	}
	
	private void setDurationForOutAnimations(int duration) {
		outFromLeftAnimation.setDuration(duration);
		outFromRightAnimation.setDuration(duration);
		outFromBottomAnimation.setDuration(duration);
		outFromTopAnimation.setDuration(duration);
	}
}

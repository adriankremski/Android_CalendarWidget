package com.kremski.utilities;

import android.util.Log;

public class ExceptionUtilities {
	
	public static void throwRuntimeExceptionIfObjectIsNull(RuntimeException exception, Object object) {
		if (object == null) {
			throw exception;
		}
	}
	
	public static void logAndThrowRuntimeException(RuntimeException exception, 
			String logTag, String logMessage) {
		Log.e(logTag, logMessage);
		throw exception;
	}

	public static void throwRuntimeExceptionIfConditionIsTrue(RuntimeException exception, boolean condition) {
		if (condition) {
			throw exception;
		}
	}
}

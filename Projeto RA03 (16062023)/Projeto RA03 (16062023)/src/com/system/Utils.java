package com.system;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public abstract class Utils {
	
	public static final String DEFAULT_DATE_SEPARATOR = " ";
	
	public static double round(double number, int places) {
		// Credits: https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(number);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static long daysBetweenDates(Calendar initialDate, Calendar endDate) {
		return TimeUnit.DAYS.convert(
				endDate.getTime().getTime() - initialDate.getTime().getTime(),
				TimeUnit.MILLISECONDS
		);
	}
	
	public static String dateToString(Calendar date, boolean monthAsName, boolean reversed) {
		return dateToString(date, DEFAULT_DATE_SEPARATOR, monthAsName, reversed);
	}
	
	public static String dateToString(Calendar date, String separator, boolean monthAsName, boolean reversed) {
		String dateString = "";
		
		if (!reversed) {
			dateString += date.get(Calendar.DATE);
		} else {
			dateString += date.get(Calendar.YEAR);
		}
		
		dateString += separator;
		
		if (!monthAsName) {
			dateString += (date.get(Calendar.MONTH) + 1);
		} else {
			dateString += date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
		}
		
		dateString += separator;

		
		if (!reversed) {
			dateString += date.get(Calendar.YEAR);
		} else {
			dateString += date.get(Calendar.DATE);
		}
		
		return dateString;
	}

}

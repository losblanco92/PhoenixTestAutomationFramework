package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {
	
	
	public static String timeWithDaysAgo (int days) {
		
	return	Instant.now().minus(days, ChronoUnit.DAYS).toString();
		
	}

}

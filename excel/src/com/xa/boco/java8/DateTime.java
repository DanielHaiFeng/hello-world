package com.xa.boco.java8;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTime {

	public static void main(String[] args) {
		// Get the system clock as UTC offset 
		final Clock clock = Clock.systemUTC();
		System.out.println( clock.instant() );
		System.out.println( clock.millis() );
		
		
		// Get the local date and local time
		final LocalDate date = LocalDate.now();
		final LocalDate dateFromClock = LocalDate.now( clock );
		         
		System.out.println( date );
		System.out.println( dateFromClock );
		         
		// Get the local date and local time
		final LocalTime time = LocalTime.now();
		final LocalTime timeFromClock = LocalTime.now( clock );
		         
		System.out.println( time );
		System.out.println( timeFromClock );
		
		// Get the local date/time
		final LocalDateTime datetime = LocalDateTime.now();
		final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );
		         
		System.out.println( datetime );
		System.out.println( datetimeFromClock );
	}

}

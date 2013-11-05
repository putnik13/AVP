package com.atanor.vrecorder.util;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class FormatTime {

	private static final String SUFFIX = ":";

	public static String format(long interval) {
		final Period period = new Period(interval);

		final PeriodFormatter formatter = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2)
				.appendHours().appendSuffix(SUFFIX).appendMinutes().appendSuffix(SUFFIX).appendSeconds().toFormatter();

		return formatter.print(period);
	}
}

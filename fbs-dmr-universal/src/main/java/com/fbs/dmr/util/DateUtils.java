package com.fbs.dmr.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
	/**
	 * 
	 * @return lastPossible Date
	 */
	public static Date getEndOfDays()
	{
		return DateUtils.create(31, 12, 8099, 23, 59, 59);
	}


	/**
	 * Returns a Date set to the last possible millisecond of the day, just
	 * before midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getEndOfDay(Date day)
	{
		return getEndOfDay(day, Calendar.getInstance());
	}


	public static Date getEndOfDay(Date day, Calendar cal)
	{
		if (day == null)
		{
			day = new Date();
		}

		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

		return cal.getTime();
	}


	/**
	 * With hour to 0. If day, month and year are 0 return null.
	 */
	public static Date create(int day, int month, int year)
	{
		return create(day, month, year, 0, 0, 0);
	}


	/**
	 * If day, month and year are 0 return null.
	 */
	public static Date create(int day, int month, int year, int hourofday, int minute, int second)
	{
		if (day == 0 && month == 0 && year == 0)
		{
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, hourofday, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
}

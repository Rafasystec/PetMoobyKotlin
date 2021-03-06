package client.petmooby.com.br.petmooby.util;


import androidx.annotation.StringRes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import client.petmooby.com.br.petmooby.R;
import client.petmooby.com.br.petmooby.application.Application;


public class DateTimeUtil {
	public static final String APPLICATION_FORMAT_INPUT = "ddMMyyyy";
	public static final String APPLICATION_FORMAT_OUTPUT = "dd/MM/yyyy";


	
	public static String formatDateTime(String date, String formatIn, String formatOut) {
		try {
			Date dateToFormat 		= new SimpleDateFormat(formatIn).parse(date);
			String formattedDate 	= new SimpleDateFormat(formatOut).format(dateToFormat);
			return formattedDate;
		} catch (ParseException e) {
			return "";
		}	
	}
	
	public static String formatDateTime(Date date, String formatOut) {
		return new SimpleDateFormat(formatOut).format(date);
	}

	public static String formatDateTime(Date date, @StringRes int resId) {
		String formatOut = Application.Companion.getInstance().getString(resId);
		return new SimpleDateFormat(formatOut).format(date);
	}

	public static String formatDateTime(Date date) {
		if(date == null)return "";
		String formatOut = Application.Companion.getInstance().getString(R.string.formatDate);
		return new SimpleDateFormat(formatOut).format(date);
	}

	public static Date getDate(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month,day);
		return calendar.getTime() ;
	}

	public static Calendar addDaysAsCalendar(int days){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		return calendar;
	}

	public static Date addDaysAsDate(int days){
		Calendar calendar = addDaysAsCalendar(days);
		return calendar.getTime();
	}

	public static Calendar dateAsCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Date getOnlyDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		Date result = calendar.getTime();
		return result;
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	public static Date addHourdToADate(Date date,int hours){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	public static void main(String[] args){
		System.out.println("Execute");
	}

	public static Date getDate(int year, int month, int day, int hours, int minutes, int seconds){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month,day,hours,minutes,seconds);
		return calendar.getTime() ;
	}

	public  static Date  addDaysToADate(Date date, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	public  static Date  addYearsToADate(Date date, int year){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	public  static Date  addMonthsToADate(Date date, int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}




	
}

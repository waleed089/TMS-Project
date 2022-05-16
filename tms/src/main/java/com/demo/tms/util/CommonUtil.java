package com.demo.tms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {

	public static String getTodayDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		return todayDate;
	}
	
	public static Date randomDueDate(int num) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		
        Calendar c = Calendar.getInstance();
        try {
			c.setTime(dateFormat.parse(todayDate));
			c.add(Calendar.DATE, num);
			return dateFormat.parse(dateFormat.format(c.getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        return date;
		
	}
}

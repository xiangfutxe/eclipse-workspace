package com.sms.util;

import java.util.Calendar;
import java.util.Date;

public class TimerTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
		Date date=calendar.getTime(); 
		Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println(startDT.get(Calendar.DAY_OF_WEEK));

	}

}

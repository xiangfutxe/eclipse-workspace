package com.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.service.CustomService;
import com.service.ICustomService;

public class Test {
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
	//	ICustomService cs = new CustomService();
	Timestamp date = new Timestamp(new Date().getTime());
	//System.out.println(StringUtil.parseToString(cs.getMonthStartTime(date), DateUtil.yyyyMMddHHmmss));
	 Calendar cal = Calendar.getInstance();
	  //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
	  int n = 0;
	  String monday;
	  cal.add(Calendar.DATE, n*7);
	  //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
	  cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
	  monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	  System.out.println(monday);
	}

}

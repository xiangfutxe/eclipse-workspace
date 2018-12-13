package com.utils;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtil {
	
	 static DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(Locale.CHINESE); 
	
public static String notNull(String s){
	if(s==null||s.length()<1) return "";
	return s;
}

public static Date parseToDate(String s,String style){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	simpleDateFormat.applyPattern(style);
	Date date = null;
	if(s==null||s.length()<8)
		return null;
	
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return date;
}

public static String parseToString(Timestamp date,String style){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	simpleDateFormat.applyPattern(style);
	String str = null;
	if(date==null)
		return "";
	Date date1 = new Date(date.getTime());
		str=simpleDateFormat.format(date1);
	return str;
}

public static String decimalFormat(double amount){
	df.setGroupingSize(3); 
	if(amount==0) df.setMaximumFractionDigits(0);
	else df.setMaximumFractionDigits(3); 
	return df.format(amount);
}

public static String decimalFormat3(double amount){
	df.setGroupingSize(3); 
	if(amount==0) df.setMaximumFractionDigits(0);
	else df.setMaximumFractionDigits(2); 
	return df.format(amount);
}

public static String decimalFormat1(double amount){
	df.setGroupingSize(0); 
	if(amount==0) df.setMaximumFractionDigits(0);
	else df.setMaximumFractionDigits(1); 
	return df.format(amount);
}

public static String decimalFormat2(double amount){
	df.setGroupingSize(0); 
	if(amount==0) df.setMaximumFractionDigits(0);
	else df.setMaximumFractionDigits(3); 
	return df.format(amount);
}

public static String percentageFormat(double amount){
	if(amount==0) df.applyPattern("#"); 
	else df.applyPattern("00.00%"); 
	return df.format(amount);
}

public static String[][] getRankStr(String rank){
	String[] strarray =rank.split(",");
	String[][] rankstr = new String[10][30];
	for(int i=0;i<10;i++){
		for(int j=0;j<30;j++){
			if(strarray.length>i){
				if(strarray[i].length()==30)
				rankstr[i][j] =  strarray[i].substring(j, j+1);
				else rankstr[i][j]="";
			}
			else rankstr[i][j]="";
		}
	}
	return rankstr;
}
}

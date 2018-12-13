package com.utils;

import java.io.UnsupportedEncodingException;

public class ToChinese {
	public static String trans(String chi){
		String result=null;
		byte temp[];
		try{
			temp=chi.getBytes("utf-8");
			result=new String(temp);
			
		}catch(UnsupportedEncodingException e){
			System.out.println(e.toString());
		}
		return result;
	}
}

package com.ssm.main;

import java.util.Date;

import com.utils.StringUtil;

import net.sf.json.JSON;

public class RankTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String str = "1101,1102,1103,1104,1105,1110,1107";
		Date date1 =new Date();
		System.out.println(str.indexOf("1001")>=0);
		Date date2 =new Date();
		
		//System.out.println(str.matches("/1+0+/"));
		StringUtil.matchBeforeStr(str, "10");
		
		System.out.println(StringUtil.matchBeforeStr(str, "10"));
		System.out.println(date2.getTime()-date1.getTime());

	}

}

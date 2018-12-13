package com.ssm.test;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.StringUtil;

public class StringTest {
	
	//欧拉函数
	public static int euler(int n){ //返回euler(n)   
	     int res=n,a=n;  
	     for(int i=2;i*i<=a;i++){  
	         if(a%i==0){  
	             res=res/i*(i-1);//先进行除法是为了防止中间数据的溢出   
	             while(a%i==0) a/=i;  
	         }  
	     }  
	     if(a>1) res=res/a*(a-1);  
	     return res;  
	}  
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String str = "12345678";
		ICustomService cs = new CustomService();

		//System.out.println(MD5.GetMD5Code(str));
		System.out.println(euler(1));
		System.out.println(cs.getWeekTag(new Timestamp(StringUtil.parseToDate("2018-04-09 00:00:00", DateUtil.yyyyMMddHHmmss).getTime())));
		Calendar calendar = Calendar.getInstance(); 
		Date date = new Date();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(StringUtil.parseToString(new Timestamp(calendar.getTime().getTime()), DateUtil.yyyyMMddHHmmss));
		//Date firstDayOfMonth = calendar.getTime();  
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println(StringUtil.parseToString(new Timestamp(calendar.getTime().getTime()), DateUtil.yyyyMMddHHmmss));

	}

}

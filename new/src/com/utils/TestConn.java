package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.naming.NamingException;

import com.db.DB;
import com.db.DBConn;
import com.pojo.User;
import com.service.IUserService;
import com.service.UserService;

public class TestConn {
	static String sql = null;  
    static DB db1 = null;  
    static ResultSet ret = null;  
    /*
    public static void main(String[] args) {
    	Timestamp nextMonthFirstDate = threeMonthDate(new Timestamp(new Date().getTime()));
        printDate(nextMonthFirstDate);
    }
    */
 
    public static Timestamp threeMonth(Timestamp date) {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 4);
        Timestamp vTime = new Timestamp(calendar.getTime().getTime()-24*60*60*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        vTime=	new Timestamp(sdf1.parse(sdf.format(vTime)).getTime());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        System.out.println(vTime);
        return vTime;
      
    }
    
 public static Timestamp threeMonthDate(Timestamp date) {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH, 3);
        Timestamp vTime = new Timestamp(calendar.getTime().getTime());
       
        System.out.println(vTime);
        return vTime;
      
    }
 
    public static void printDate(Timestamp date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }
   /* 
public static void main(String[] args) throws Exception{
	
	 Connection conn = null;
     Statement st = null;
     ResultSet rs = null;
     IUserService us = new UserService();
     try{
         //获取数据库连接
         conn = new DBConn().getConnection();
       //  rs = conn.getMetaData().getTables(null, null,  "wsettle", null );
         //获取数据库自动生成的主键
       
         if(!rs.next()){
        	 rs.close();
        st = conn.createStatement();
        	st.execute(sql);
        	st.close();
        	
         User user = us.findById(conn,"AA000000");
         System.out.println(user.getUserName());
        conn.close();
     }catch (Exception e) {
         e.printStackTrace();
     }finally{
         //释放资源
    	 new DBConn().release(conn, st, rs);
     }
    
	for(int i=0;i<1;i++){
	    System.out.println((int)(1+Math.random()*(100000-1+1)));
	}
	}
	*/
    
    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");  
    public static void main(String[] args) throws ParseException {  
        // TODO Auto-generated method stub  
    	/*
    	Date date1= new Date(StringUtil.parseToDate("2017-03-03 22:23:12", DateUtil.yyyyMMddHHmmss).getTime());
    	System.out.println(dateFormat2.format(date1));
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        Date date = calendar.getTime();  
        String firstDate = dateFormat2.format(date);  
        System.err.println(firstDate);  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        date = calendar.getTime();  
        String lastDate = dateFormat2.format(date);  
        System.err.println(lastDate);  
        */
    	String date_string="2016-09-01 00:00:00";
//    	利用java中的SimpleDateFormat类，指定日期格式，注意yyyy,MM大小写
//    	这里的日期格式要求javaAPI中有详细的描述，不清楚的话可以下载相关API查看
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
    // 设置日期转化成功标识
    boolean dateflag=true;
    // 这里要捕获一下异常信息
    try
    {
    Date date = format.parse(date_string);
    } catch (ParseException e)
    {
    dateflag=false;
    }finally{
//    	成功：true ;失败:false
    System.out.println("日期是否满足要求"+dateflag);
    }
    }
}

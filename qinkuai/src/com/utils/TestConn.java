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

import com.db.DBConn;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;

public class TestConn {
	static String sql = null;  
    static ResultSet ret = null;  
    static ICustomService cs = new CustomService();
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
    	cs.sendSMS("13824534050", "亲爱的商户，您今日的订单已经确认，我们会尽快安排送货到店，请注意查收！");
    }  
}

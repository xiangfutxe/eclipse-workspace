package com.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pojo.Center;
import com.pojo.InventoryCenter;
import com.pojo.Order;
import com.pojo.Param;
import com.pojo.Promotion;
import com.pojo.Settle;
import com.pojo.TimeParam;
import com.pojo.User;
import com.pojo.WReward;
import com.pojo.WSettle;

public interface ICustomService {
	
	public Timestamp[] getTime(Connection conn,Timestamp date);
	
	public int getWeekTag(Connection conn,Timestamp date);
	
	
	 
	 public String createOrderId(Timestamp date);
	 
	 public String createWithDrewId(Timestamp date);
	 
	
	 public String createInventoryApplyId(Timestamp date);
	 
	 public String createAgentApplyId(Timestamp date);

	 public  String encodeFileName(HttpServletRequest request,  
             String fileName) throws UnsupportedEncodingException;
	 
	 
	 public Settle getSettle(Connection conn,int weekTag,String forupdate);
	 
	 public Settle getSettle(Connection conn,String property,Timestamp time,String forupdate);


	 public Param getParam(Connection conn,String name);
	 
	 public Timestamp threeMonthDate(Timestamp date);
	 
	 public Timestamp threeMonth(Timestamp date);
	 
	 public boolean getMonthEnd(Connection conn,Timestamp date);
	 
	 public boolean getMonthStart(Connection conn,Timestamp date);
	 

	 public boolean insetAdminLog(Connection conn,String adminName,String contents,Timestamp date);

	 public Center getCenter(Connection conn,String userId);
	 
	 public Promotion getPromotion(Connection conn);
	 
	 public Timestamp getMonthStartTime(Timestamp date);
	 
	 public Timestamp getMonthEndTime(Timestamp date);
	 
	 public Promotion getPromotion();
	 
	 public boolean checkTimeFormat(String timestr,String time_format);
	 
	 public String saveReward(User user1,User user2,double amout,int type,int state,String remark,Timestamp date);

	 public String saveReward2(User user1,User user2,double amout,int type,int state,String remark,Timestamp startTime,Timestamp endTime,Timestamp date);

	 public boolean createWReward(Connection conn,String table_name);
}

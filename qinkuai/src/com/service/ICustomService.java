package com.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.pojo.Branch;
import com.pojo.Center;
import com.pojo.InventoryCenter;
import com.pojo.Param;
import com.pojo.Promotion;
import com.pojo.SettleStock;
import com.pojo.ShortMessage;
import com.pojo.TimeParam;
import com.pojo.User;

public interface ICustomService {
	
	public Timestamp[] getTime(Connection conn,Timestamp date);
	
	public int getWeekTag(Connection conn,Timestamp date);
	
	public TimeParam getTimeParam(Connection conn);
	
	public InventoryCenter getInventoryCenter(Connection conn,String userId,String productId);
	
	public SettleStock getSettleForWeekTag(Connection conn,int weekTag,String forupdate);
	
	public SettleStock getSettleForTime(Connection conn,Timestamp startTime,String forupdate);

	
	 public String  getCreditDetail(Branch bh,double amount,double balance,int payType,String orderId,Timestamp entryTime) throws SQLException;

	 public String createOrderId(Timestamp date);
	 
	 public String createWithDrewId(Timestamp date);
	 
	 public String createCenterId(Timestamp date);
	 

	 public String createInventoryApplyId(Timestamp date);
	 
	 public String createPurchaseApplyId(Timestamp date);
	 
	 public  String encodeFileName(HttpServletRequest request,  
             String fileName) throws UnsupportedEncodingException;
	 
	
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
	 
	 public Timestamp getPreWeekStartTime();
	 
	 public Timestamp getPreWeekEndTime();
	 
	 public Promotion getPromotion();
	 
	 public boolean sendSMS(String tel,String content);
	 
	 public void sendSMS(Connection conn,String type);
	 
	 public void sendSMS(Connection conn,String tel, String type);
	 
	 public ShortMessage  findByType(Connection conn,String type);
	 
	 public boolean  isShop();
	 
	 public String  saveEmoneyDetail(Connection conn,Branch user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException;

	  public String  saveChargeApply(Connection conn,Branch user,String admin,String accountId,String accountName,String bankName,double emoney,String remark,int type,int state,Timestamp date,Timestamp reviewTime) throws SQLException;

	  public int getRandom(int min,int max);

}

package com.ssm.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.User;
import com.ssm.utils.StringUtil;

public interface ICustomService {
	
	 public Timestamp getNextYearTime(Timestamp date);
	 
	 public int getRandom(int min,int max);
	 
	 public void insertAdminLog(String adminName,String contents,String type,Timestamp date);
	 
	 public  String encodeFileName(HttpServletRequest request,  
             String fileName) throws UnsupportedEncodingException;
	 
		public  String getRefereeNode(User user);

		
		public  int getWeekTag(Timestamp date);
		
		 public Timestamp getNextDay(Timestamp date);

			 public Timestamp getPreDay(Timestamp date);
			 public void insertUserLog(int uid,String userId,String userName,String contents,String type,Timestamp date);
	
			 public AccountDetail returnAccountDetail(User user,double amount,double balance,int payType,String tradeType,String summary,Timestamp date);
			 
}

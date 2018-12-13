package com.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pojo.News;
import com.pojo.User;
import com.weixin.pojo.WeixinUserInfo;

public interface ICustomService {
	 public boolean insetAdminLog(Connection conn,String adminName,String contents,Timestamp date);
	 
	 public  List<News> leftNews(Connection conn);
	 
	 public void combination(String[][] chars,String[] chars1,int n,String[] tmp,List<String> slist);

	 public String  getEmoneyDetail(Connection conn,WeixinUserInfo snsUserInfo,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException;

	 public String  getIntegralDetail(Connection conn,WeixinUserInfo user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException;

	 public String  getRmoneyDetail(Connection conn,WeixinUserInfo user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException;

	 public  String encodeFileName(HttpServletRequest request,  
             String fileName) throws UnsupportedEncodingException;
}

package com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.weixin.pojo.WeixinUserInfo;

public interface IWeixinService {
	
	public String getToken(Connection conn,Timestamp date);
	
	public int getUId(Connection conn,Timestamp date) throws SQLException;
	
	public int getQId(Connection conn,Timestamp date) throws SQLException;
	
	public WeixinUserInfo findByOpenId(Connection conn,String openId);
	
	public WeixinUserInfo findByQRId(Connection conn,int qr_id);
	
}

package com.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.pojo.Settle;
import com.pojo.User;

public interface IUserService {
	
	public User findById(Connection conn,String userId);
	
	public User getUserById(Connection conn,int userId);
	
	public boolean updatePv(Connection conn,String table_name,double pv,double emoney,int id,String refereeNode,String node,String nodeABC);
	
	public boolean updateRefereePv(Connection conn,String table_name,double pv,int id,String refereeNode);
	
	public  boolean updateBelongPv(Connection conn,String table_name,double pv,double emoney,String node,String nodeABC);
	
	public boolean updateRefereePv_SUB(Connection conn,String table_name,double pv,String refereeNode);
	
	public boolean checkWSettle(Connection conn,String table_name,int weekTag,String refereeNode,String node);
		  
	public  User getSaveUser(Connection conn, String userId);
	
	public boolean checkUserVIP(Connection conn,String node,Settle st);
	
	public boolean updateVIP(Connection conn,String node,String nodeABC,Settle st);
	
	public boolean checkNodeTag(int nodeTag, User user);
	
	public int getUId(Connection conn) throws SQLException;
	
	public String getRefereeNode(User user);
	
	public String getDeclarationNode(User user);
	
	public String getNode(User user);
	
	public  String getNodeABC(User user, int nodeTag);

}

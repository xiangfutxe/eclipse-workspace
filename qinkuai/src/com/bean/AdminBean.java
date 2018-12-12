package com.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import com.db.DBConn;
import com.db.DBResult;
import com.pojo.Admin;

public class AdminBean {
	
	private Connection conn = null;
	
	private Statement stmt = null;
	
	private ResultSet rs = null;
	
	private DBConn db = new DBConn();
	
	Collection coll;
	
	public AdminBean() throws SQLException{
		conn = db.getConnection();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	}
	
	public Admin valid(String adminName,String password) throws Exception{
		DBResult dr = new DBResult();
	 Admin admin = null;
	 if(dr.createConn()){
		 String sql = "select * from admin where adminName ='"+adminName+"' and password='"+password+"'";
		rs = dr.getResult(sql);
		 System.out.println(rs.next());
		 if(rs.next()){
			admin = new Admin();
			admin.setId(rs.getInt(1));
			admin.setAdminName(rs.getString(2));
			admin.setPassword(rs.getString(3));
			admin.setPassword2(rs.getString(4));
			admin.setRank(rs.getString(5));
			admin.setState(rs.getString(6));
		 }
		  db.release(conn, stmt, rs);
	 }
	 return admin;
	}
}

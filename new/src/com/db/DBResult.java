package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.DBConn;


public class DBResult {
	

private Connection conn = null;

private Statement stm = null;

private ResultSet rs = null;

DBConn db = new DBConn();

public DBResult() throws SQLException{
		this.conn=db.getConnection();
}

public ResultSet getResult(String sql){
	try{
	stm =conn.createStatement();
	rs = stm.executeQuery(sql);
	return rs;
	}catch(Exception e){
	}
		return null;
	}

public void doExecute(String sql){
	try{
		stm =conn.createStatement();
		stm.executeQuery(sql);
		}catch(Exception e){
		}
}

public PreparedStatement getPreparedStatement(String sql){
	try{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt;
	}catch(Exception e){}
	return null;
}

public boolean createConn(){
	boolean b = false;
	if(conn!=null) b= true;
	return b;
}

public boolean update(String sql){
	boolean b = false;
	try{
		stm=conn.createStatement();
		stm.execute(sql);
		b = true;
	}catch(SQLException e){
		
	}
	return b;
}

public void query(String sql){
	try{
		stm=conn.createStatement();
		stm.executeQuery(sql);
	}catch(SQLException e){
		
	}
}

public boolean next(){
	boolean b = false;
	try{
		if(rs.next()) b =true;
	}catch(SQLException e){
		
	}
	return b;
}

public String getValue(String field){
	String value = null;
	try{
		if(rs!=null) value = rs.getString(field);
	}catch(SQLException e){
		
	}
	return value;
}

public void close(){
	try{
		db.release(conn, stm, rs);
	}catch(Exception e){
		e.printStackTrace();
	}
}

public Connection getConn() {
	return conn;
}

public void setConn(Connection conn) {
	this.conn = conn;
}

public Statement getStm() {
	return stm;
}

public void setStm(Statement stm) {
	this.stm = stm;
}

public ResultSet getRs() {
	return rs;
}

public void setRs(ResultSet rs) {
	this.rs = rs;
}

}

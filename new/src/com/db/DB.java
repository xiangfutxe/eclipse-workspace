package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

public class DB {

	 public static final String url = "jdbc:mysql://localhost:3306/hdj";  
	    public static final String name = "com.mysql.jdbc.Driver";  
	    public static final String user = "root";  
	    public static final String password = "101010"; 
	    public Connection conn = null;  
	    public PreparedStatement pst = null; 
	    
	    public DB(){
			
		}
	public DB(String sql){
		try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	public static synchronized Connection getConnection() throws Exception{
		try{
			Context initCtx = new javax.naming.InitialContext();//获取JNDI初始化上下文对象
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/dd");
			return ds.getConnection();
		}catch(SQLException e){
			throw e;
		}catch(NamingException e){
			throw e;
		}
	}
	
	 public void close() {  
	        try {  
	            this.conn.close();  
	            this.pst.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	
	
}

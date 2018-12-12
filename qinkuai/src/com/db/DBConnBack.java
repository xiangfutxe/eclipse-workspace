package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnBack {
	private static ComboPooledDataSource ds = null;
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private static int error = 0;
    //在静态代码块中创建数据库连接池
    static{
        try{
            //通过代码创建C3P0数据库连接池
            /*ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcstudy");
            ds.setUser("root");
            ds.setPassword("XDP");
            ds.setInitialPoolSize(10);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(20);*/
            
            //通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
            //ds = new ComboPooledDataSource();//使用C3P0的默认配置来创建数据源
            ds = new ComboPooledDataSource("MySQL");//使用C3P0的命名配置来创建数据源
            
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    /**
    * @Method: getConnection
    * @Description: 从数据源中获取数据库连接
    * @Anthor:孤傲苍狼
    * @return Connection
    * @throws SQLException
    */ 
    public   Connection getConnection() throws SQLException{
        //从数据源中获取数据库连接
        return ds.getConnection();
    }
    
    /**
    * @Method: release
    * @Description: 释放资源，
    * 释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
    * @Anthor:孤傲苍狼
    *
    * @param conn
    * @param st
    * @param rs
    */ 
    
    public boolean createConn(){
    	boolean b = false;
    	try {
			con  = getConnection();
			if(con!=null) b= true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return b;
    }
    
    public Statement getStmtread(){
    	try {
			con  = getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return stmt;
    }
    
    public Statement getStmt(){
    	try {
			con  = getConnection();
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return stmt;
    }

    public int getRowCount(String sql){
    	int count = 0;
    	stmt = this.getStmtread();
    	try {
			rs = stmt.executeQuery("SELECT COUNT(*) FROM "+sql);
			rs.getMetaData();
	    	if(rs.next())count = rs.getInt(1);
	    	else count = -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			count = -2;
			e.printStackTrace();
		}finally{
			this.release(con, stmt, rs);
		}
    return count;
    }
    
    public PreparedStatement getPstmt(String sql){
    	try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    return pstmt;	
    }
    
    public void close(){
    	 if(rs!=null){
             try{
                 //关闭存储查询结果的ResultSet对象
                 rs.close();
             }catch (Exception e) {
                 e.printStackTrace();
             }
             rs = null;
         }
         if(stmt!=null){
             try{
                 //关闭负责执行SQL命令的Statement对象
                 stmt.close();
             }catch (Exception e) {
                 e.printStackTrace();
             }
         }
         
         if(con!=null){
             try{
                 //将Connection连接对象还给数据库连接池
                 con.close();
             }catch (Exception e) {
                 e.printStackTrace();
             }
         }
    }
    
    public  void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try{
                //关闭存储查询结果的ResultSet对象
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //关闭负责执行SQL命令的Statement对象
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(conn!=null){
            try{
                //将Connection连接对象还给数据库连接池
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

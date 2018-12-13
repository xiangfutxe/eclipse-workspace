package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.SnapshotIF;

public class DBConn {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private static String driverClass = "org.logicalcobwebs.proxool.ProxoolDriver";//proxool驱动类  
	private static int activeCount = 0; //活动连接数  
    protected final Logger log = Logger.getLogger(DBConn.class.getName());  
    public Connection getConn() {  
        try {  
        	
            Class.forName(driverClass);   
            conn = DriverManager.getConnection("proxool.DBPool"); //此处的DBPool是在proxool.xml中配置的连接池别名  
        	
            this.showSnapshotInfo(); //查看连接池信息  
        } catch (Exception e) {  
            log.error(e.getMessage());  
            log.debug("数据库连接错误！");  
            //System.out.println(e.getMessage());  
            //System.out.println("数据库连接错误！");  
        }  
        return conn;  
    }  
    
    /** 
     * 获取连接池中的连接信息 
     */  
    private void showSnapshotInfo(){  
        try{         
            SnapshotIF snapshot = ProxoolFacade.getSnapshot("DBPool", true);         
            int curActiveCount=snapshot.getActiveConnectionCount();//获得活动连接数         
            int availableCount=snapshot.getAvailableConnectionCount();//获得可得到的连接数         
            int maxCount=snapshot.getMaximumConnectionCount() ;//获得总连接数         
            if(curActiveCount!=activeCount)//当活动连接数变化时输出的信息         
            {  
                log.debug("活动连接数:"+curActiveCount+"(active)；可得到的连接数:"+availableCount+"(available)；总连接数:"+maxCount+"(max)");  
             //System.out.println("活动连接数:"+curActiveCount+"(active)；可得到的连接数:"+availableCount+"(available)；总连接数:"+maxCount+"(max)");                      
             activeCount=curActiveCount;         
            }  
        }catch(ProxoolException e){         
            e.printStackTrace();         
        }         
    }    

    
    /**
    * @Method: getConnection
    * @Description: 从数据源中获取数据库连接
    * @Anthor:孤傲苍狼
    * @return Connection
    * @throws SQLException
    */ 
    public  Connection getConnection(){
        //从数据源中获取数据库连接
        return getConn();
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
		conn  = getConnection();
		if(conn!=null) b= true;
    	return b;
    }
    
    public Statement getStmtread(){
    	try {
			conn  = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return stmt;
    }
    
    public Statement getStmt(){
    	try {
			conn  = getConnection();
			stmt = conn.createStatement();
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
			this.release(conn, stmt, rs);
		}
    return count;
    }
    
    public PreparedStatement getPstmt(String sql){
    	try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
         if(conn!=null){
             try{
                 //将Connection连接对象还给数据库连接池
                 conn.close();
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

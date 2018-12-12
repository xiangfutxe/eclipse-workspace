<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.db.DBConn" %>
<%@page import="com.pojo.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.service.*" %>
<%
	Statement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
		Statement stmt1 = null;
	ResultSet rs1 = null;
	DBConn db = new DBConn();
	IUserService us = new UserService();
	ICustomService cs = new CustomService();
	try{
	conn = db.getConnection();
	 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
		
		List<String> slist = new ArrayList<String>();
		
		String sqlw = "select * from orderDetail";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqlw);
		while(rs.next()){
					String productId = rs.getString("productId");
					int id=rs.getInt("id");
					String sql1 ="select * from product where productId='"+productId+"'";
					stmt1 = conn.createStatement();
					rs1 = stmt1.executeQuery(sql1);
					if(rs1.next()){
					int pid = rs1.getInt("id");
					String sql = "update orderDetail set pid='"+pid+"' where id='"+id+"'";
					slist.add(sql);
					}
				}
				
		
				stmt = conn.createStatement();
				for(int i=0;i<slist.size();i++){
						stmt.addBatch(slist.get(i));
						 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
							 stmt.executeBatch();
							 stmt.close();
							 stmt = conn.createStatement();
						 }
				}
				 conn.commit();
	conn.setAutoCommit(autoCommit);
	out.println("更新产品详情成功！");
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			out.println("数据有误，请重试！");
		}
		if(stmt1!=null) stmt1.close();
		if(rs1!=null) rs1.close();
	db.close();
	
%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.db.DBConn" %>
<%@page import="com.pojo.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.service.*" %>
<%@page import="com.utils.ArithUtil" %>

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
		
		String sqlu = "select * from orders  order by id desc";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlu);
	while(rs.next()){
	String orderId=rs.getString("orderId");
	String userId=rs.getString("userId");
	double price= rs.getDouble("price");
	double total_price=0;
		String sqlw = "select * from orderDetail where orderId='"+orderId+"'";
		stmt1 = conn.createStatement();
		rs1 = stmt1.executeQuery(sqlw);
		while(rs1.next()){
				total_price = ArithUtil.add(total_price,rs1.getDouble("price"));
			
		}
		if(ArithUtil.sub(price,total_price)>0||ArithUtil.sub(price,total_price)<0)	{
		out.println(userId+":"+orderId+"; ");
		}	
		}
		if(stmt1!=null)stmt1.close();
			if(rs1!=null)rs1.close();
	out.println("检测结束！");
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
	db.close();
	
%>

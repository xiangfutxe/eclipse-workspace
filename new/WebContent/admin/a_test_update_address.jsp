<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.db.DBConn" %>
<%@page import="com.pojo.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.service.*" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.ArithUtil" %>
<%
	Statement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	DBConn db = new DBConn();
	IUserService us = new UserService();
	ICustomService cs = new CustomService();
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	try{
	conn = db.getConnection();
	List<String> slist= new ArrayList<String>();
	String sqlu = "select * from address";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlu);
	while(rs.next()){
		String userId = rs.getString("user_id");
		String province =rs.getString("province");
		String city =rs.getString("city");
		String area =rs.getString("area");
		String address =rs.getString("address");
		String sql1 = "update userinfo set province='"+province+"',city='"+city+"',area='"+area+"',address='"+address+"' where user_id='"+userId+"'";
		slist.add(sql1);
	}
	if(slist.size()>0){
	stmt = conn.createStatement();
		for(int i=0;i<slist.size();i++){
		stmt.addBatch(slist.get(i));
		}
		stmt.executeBatch();
		out.println("地址更新成功");
		}
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.db.DBConn" %>
<%@page import="com.pojo.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.service.*" %>
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
	 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
		
		String sqlm = "select max(id) from product";	
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqlm);
		int num = 0;
		if(rs.next()){
			num = rs.getInt(1);
		}
		Product[] plist = new Product[num+1];
	String sqlu = "select * from product  order by id desc";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlu);
	while(rs.next()){
		int id= rs.getInt("id");
		if(plist[id]==null) plist[id] = new Product();
		plist[id].setId(id);
		plist[id].setSpecification(rs.getString("specification"));
		plist[id].setUnit(rs.getString("unit"));
		
	}
		List<String> slist = new ArrayList<String>();
		
			String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4'  order by iay.id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("idl.pid");
				int type = rs.getInt("iay.type");
				int pay_type = rs.getInt("iay.pay_type");
				if(plist[id]==null) plist[id] = new Product();
				if(pay_type==1){
					
				}else{
					
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
	db.close();
	
%>

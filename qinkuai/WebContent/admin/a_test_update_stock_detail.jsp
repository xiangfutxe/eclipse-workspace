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
	String sql = "select * from product  order by id desc";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	while(rs.next()){
		int id= rs.getInt("id");
		if(plist[id]==null) plist[id] = new Product();
		plist[id].setId(id);
		plist[id].setSpecification(rs.getString("specification"));
		plist[id].setUnit(rs.getString("unit"));
		plist[id].setInventoryOne(rs.getString("inventory_one"));
		plist[id].setInventoryTwo(rs.getString("inventory_two"));
		plist[id].setInventoryThree(rs.getString("inventory_three"));
		
	}
	 List<String> slist = new ArrayList<String>();
	String sqlu = "select * from inventory_product_stock";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlu);
	while(rs.next()){
	int id = rs.getInt("id");
	int pid = rs.getInt("p_id");
	String sqlw = "update inventory_product_stock set inventory_one='"+plist[pid].getInventoryOne()+"',inventory_two='"+plist[pid].getInventoryTwo()+"',inventory_three='"+plist[pid].getInventoryThree()+"' where id='"+id+"'";
	slist.add(sqlw);
	}
		if(slist.size()>0){
				stmt = conn.createStatement();
				for(int i=0;i<slist.size();i++){
						stmt.addBatch(slist.get(i));
						 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
							 stmt.executeBatch();
							 stmt.close();
							 stmt = conn.createStatement();
						 }
				}
				}
				 conn.commit();
	conn.setAutoCommit(autoCommit);
	out.println("更新盘点表成功！");
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
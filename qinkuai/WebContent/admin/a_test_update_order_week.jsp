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
		List<String> slist = new ArrayList<String>();
		String sqlm = "select max(id) from branch";	
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqlm);
		int num = 0;
		if(rs.next()){
			num = rs.getInt(1);
		}
		Branch[] blist = new Branch[num+1];
	String sqlu = "select * from branch  order by id desc";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlu);
	while(rs.next()){
		int id= rs.getInt("id");
		if(blist[id]==null) blist[id] = new Branch();
		blist[id].setId(id);
		blist[id].setCredit(rs.getDouble("credit"));
		blist[id].setTag(0);
		
	}
		String sqlw = "select * from order_week where state='0'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqlw);
		while(rs.next()){
					int id = rs.getInt("b_id");
					int state=rs.getInt("state");
					if(state==0){
					 blist[id].setCredit(0);
					 blist[id].setTag(1);
					 }
				}
			for(int i=1;i<blist.length;i++){
			if(blist[i]!=null){
			if(blist[i].getTag()==1){
			String sql1 = "update branch set credit='"+blist[i].getCredit()+"' where id='"+blist[i].getId()+"'";
			slist.add(sql1);
			}
			}
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
	out.println("检测未支付会员结果完成！");
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

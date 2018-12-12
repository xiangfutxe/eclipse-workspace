package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConn;
import com.pojo.Param;
import com.pojo.Admin;
import com.pojo.Promotion;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Pager;
import com.utils.StringUtil;

public class PromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;

	ICustomService cs = new CustomService();
	IUserService us = new UserService();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		
		}else if(method.equals("edit")){
				edit(request,response);
			
		}else if(method.equals("update")){
				update(request,response);
		}

		}
	
	
	public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String sql = "select * from promotion";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Promotion as = new Promotion();
					as.setId(rs.getInt("id"));
					as.setUid(rs.getInt("uid"));
					as.setUserId(rs.getString("userId"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setAmount_1(rs.getInt("amount_1"));
					as.setAmount_2(rs.getInt("amount_2"));
					as.setAmount_3(rs.getInt("amount_3"));
					as.setAmount_4(rs.getInt("amount_4"));
					as.setAmount_5(rs.getInt("amount_5"));
					as.setStartTime(rs.getTimestamp("startTime"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setComments(rs.getString("comments"));
					request.setAttribute("promotion", as);
				}else{
					message= "未查询到需要修改的网站信息！";
				}
				
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("promotion_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("param_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String  id= StringUtil.notNull(request.getParameter("id"));
				String amount_1_str = StringUtil.notNull(request.getParameter("amount_1"));
				String amount_2_str = StringUtil.notNull(request.getParameter("amount_2"));
				String amount_3_str = StringUtil.notNull(request.getParameter("amount_3"));
				String amount_4_str = StringUtil.notNull(request.getParameter("amount_4"));
				String amount_5_str = StringUtil.notNull(request.getParameter("amount_5"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String startTime = StringUtil.notNull(request.getParameter("startTime"));
				String endTime = StringUtil.notNull(request.getParameter("endTime"));
				String comments = StringUtil.notNull(request.getParameter("comments"));
				double amount_1=0;
				double amount_2=0;
				double amount_3=0;
				double amount_4=0;
				double amount_5=0;
				if(!amount_1_str.equals("")) amount_1 = Integer.valueOf(amount_1_str);
				if(!amount_2_str.equals("")) amount_2 = Integer.valueOf(amount_2_str);

				if(!amount_3_str.equals("")) amount_3 = Integer.valueOf(amount_3_str);
				if(!amount_4_str.equals("")) amount_4 = Integer.valueOf(amount_4_str);
				if(!amount_5_str.equals("")) amount_5 = Integer.valueOf(amount_5_str);

				String sql = "select * from users where userId='"+userId+"'";
				Timestamp date = new Timestamp(new Date().getTime());
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int uid = rs.getInt("id");
					sql = "select * from promotion for update";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					if(rs.next()){
						String sqlu= "update promotion set amount_1='"+amount_1+"',amount_2='"+amount_2+"',amount_3='"+amount_3
								+"',amount_4='"+amount_4+"',amount_5='"+amount_5+"',uid='"+uid+"',userId='"+userId+"',comments='"+comments+"',startTime='"+startTime+"',endTime='"+endTime+"',entryTime='"+date+"' where id='"+id+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "网站基础信息修改成功！";
					}else message= "未查询到需要修改的基础信息！";
					
				}else{
					message= "未查询到起始节点信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "请勿重复提交数据，请在网站设置中查看是否操作成功！";
					}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("promotion_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					String paramName = StringUtil.notNull(request.getParameter("paramName"));
					String amount_1 = StringUtil.notNull(request.getParameter("amount_1"));
					String amount_2 = StringUtil.notNull(request.getParameter("amount_2"));
					String amount_3 = StringUtil.notNull(request.getParameter("amount_3"));
					String amount_4 = StringUtil.notNull(request.getParameter("amount_4"));
					String amount_5 = StringUtil.notNull(request.getParameter("amount_5"));
					String amount_6 = StringUtil.notNull(request.getParameter("amount_6"));
					String amount_7 = StringUtil.notNull(request.getParameter("amount_7"));
					String amount_8 = StringUtil.notNull(request.getParameter("amount_8"));
					String amount_9 = StringUtil.notNull(request.getParameter("amount_9"));
					String amount_10 = StringUtil.notNull(request.getParameter("amount_10"));
					String unit = StringUtil.notNull(request.getParameter("unit"));
				
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 stmt = conn.createStatement();
					 String sqli = "insert into param(paramName,amount_1,amount_2,amount_3,amount_4,amount_5,amount_6,amount_7,amount_8,amount_9,amount_10,unit,entryTime) values('"+paramName
							 +"','"+amount_1+"','"+amount_2+"','"+amount_3+"','"+amount_4+"','"+amount_5+"','"+amount_6+"','"+amount_7+"','"+amount_8+"','"+amount_9+"','"+amount_10+"','"+unit+"','"+date+"')";
					 stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					request.setAttribute("message", "参数新增成功，参数名为："+paramName);
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 rs.close();
				 stmt.close();
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在地址列表中查看是否操作成功！");
			}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("param_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		
		try {
			if(admin!=null){
				
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String sql = "select * from param where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "update param set state='0',endTime='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "地址信息删除成功！";
				}else{
					message= "未查询到需要修改的地址信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 rs.close();
				 stmt.close();
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("param_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
}

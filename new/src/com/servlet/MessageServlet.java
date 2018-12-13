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
import com.jspsmart.upload.SmartUploadException;
import com.pojo.AccountDetail;
import com.pojo.AccountSupplement;
import com.pojo.Admin;
import com.pojo.Message;
import com.pojo.Order;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Constants;
import com.utils.Pager;
import com.utils.StringUtil;

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private int pageNo = 1;
	
	private int pageSize = 10;

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
		}else if(method.equals("admin_list")){
			admin_list(request,response);
	}else if(method.equals("list")){
		list(request,response);
	}else if(method.equals("admin_reply")){
		admin_reply(request,response);
			
		}else if(method.equals("admin_reply_save")){
			try {
				admin_reply_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}else if(method.equals("save")){
			try {
				save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}

}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from message where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and (userId like'%"+userId+"%' or userName like'%"+userId+"%') order by id desc";
						
					}else{
						sql ="select * from message where  userId like'%"+userId+"%' or userName like'%"+userId+"%' order by id desc";
						}
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0};
				while(rs.next()){
					Message as = new Message();
					as.setId(rs.getInt("id"));
					as.setUserId(rs.getString("userId"));
					as.setUserName(rs.getString("userName"));
					as.setAdminMsg(rs.getString("adminMsg"));
					as.setUserMsg(rs.getString("userMsg"));
					as.setReplyTime(rs.getTimestamp("replyTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setAdmin(rs.getString("admin"));
					as.setState(rs.getInt("state"));
					result.add(as);
				}
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					coll = result.subList(startIndex, endIndex);
					Pager pager = new Pager(pageSize,pageNo,result.size(),coll);
					request.setAttribute("pager", pager);
				}
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("msg.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("msg_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(user!=null){
				
			if(db.createConn()){
				String sql  ="select * from message where  userId ='"+user.getUserId()+"' order by entryTime desc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0};
				while(rs.next()){
					Message as = new Message();
					as.setId(rs.getInt("id"));
					as.setUserId(rs.getString("userId"));
					as.setUserName(rs.getString("userName"));
					as.setAdminMsg(rs.getString("adminMsg"));
					as.setUserMsg(rs.getString("userMsg"));
					as.setReplyTime(rs.getTimestamp("replyTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setAdmin(rs.getString("admin"));
					as.setState(rs.getInt("state"));
					result.add(as);
				}
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					coll = result.subList(startIndex, endIndex);
					Pager pager = new Pager(pageSize,pageNo,result.size(),coll);
					request.setAttribute("pager", pager);
				}
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("msg.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("msg_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][5].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		System.out.println(token);
		request.getSession().setAttribute("token", String.valueOf(token));
		System.out.println("token");
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement_message.jsp");
			dispatcher.forward(request, response);
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_reply(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
		String message="";
		try{
			
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[0][2].equals("1")||admin.getState().equals("1")){
			
			if(db.createConn()){
				 conn = db.getConnection();
				 String id= StringUtil.notNull(request.getParameter("id"));
				 String sql = "select * from message where id='"+id+"'";
				 stmt =conn.createStatement();
				 rs=stmt.executeQuery(sql);
				 if(rs.next()){
					 int state = rs.getInt("state");
					 if(state==1){
						 Message msg = new Message();
						 msg.setUserId(rs.getString("userId"));
						 msg.setUserName(rs.getString("userName"));
						 msg.setId(rs.getInt("id"));
						 msg.setUserMsg(rs.getString("userMsg"));
						 msg.setEntryTime(rs.getTimestamp("entryTime"));
						 msg.setState(state);
						 request.setAttribute("MSG", msg);
					 }else{
						 message= "当前状态不能进行回复，请核对！";
					 }
				 }else{
					 message= "未找到相应的留言信息！";
				 }
				 
			}else {
				message= "数据库连接已断开！";
			}
		}else{
			message="您没有该操作权限，如有需要请与系统管理员联系！";
			
		}
		}else{
			message="管理员用户未登陆，请重新登陆！";
			
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("msg_edit.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("msg_message.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = (User)request.getSession().getAttribute("sys_user");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String error = "";
		DBConn db = new DBConn();
		try {
			if(user!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					String userMsg = StringUtil.notNull(request.getParameter("userMsg"));
					
					if(userMsg.length()>200){ error = "留言内容不能超过100个字！";}
					else if(userMsg.equals("")){ error = "扣款金额有误！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "insert into message(userId,userName,userMsg,state,entryTime) values('"+
						 user.getUserId()+"','"+user.getUserName()+"','"+userMsg+"','1','"+date+"')";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "留言发布成功，请在留言簿查看留言信息！");
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在增扣明细中查看是否操作成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("msg_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_reply_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String error = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][2].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					String adminMsg = StringUtil.notNull(request.getParameter("adminMsg"));
					String id = StringUtil.notNull(request.getParameter("id"));
					if(adminMsg.length()>200){ error = "留言内容不能超过100个字！";}
					else if(adminMsg.equals("")){ error = "扣款金额有误！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String sqls = "select * from message where id='"+id+"' for update";
					 stmt= conn.createStatement();
					 rs = stmt.executeQuery(sqls);
					 if(rs.next()){
						 if(rs.getInt("state")==1){
						 String sql = "update  message set adminMsg='"+adminMsg+"',admin='"+admin.getAdminName()+"',replyTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "留言回复成功，请在留言簿查看留言信息！");
						 }else request.setAttribute("message", "当前状态不能进行回复，请核实！");
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 rs.close();
				 stmt.close();
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在增扣明细中查看是否操作成功！");
			}
				}else{
					request.setAttribute("message","您没有该操作权限，如有需要请与系统管理员联系！");
					
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("msg_message.jsp");
			dispatcher.forward(request, response);
		}
	}
}

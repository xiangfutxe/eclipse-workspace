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
import com.pojo.AdminLog;
import com.pojo.Admin;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Pager;
import com.utils.StringUtil;

public class LogServlet extends HttpServlet {

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
		
		}else if(method.equals("admin_log_list")){
			admin_log_list(request,response);
			
		}else if(method.equals("add")){
			add(request,response);
			
		}else if(method.equals("save")){
			try {
				save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(method.equals("edit")){
				edit(request,response);
			
		}else if(method.equals("admin_log_delAll")){
			admin_log_delAll(request,response);
	}else if(method.equals("admin_log_del")){
		del(request,response);
}

}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_log_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String sql ="";
			if(db.createConn()){
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
				sql = "select * from admin_log where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and adminName like '%"+adminName+"%' and adminName!='admin' order by entryTime desc";
				}else{
					sql = "select * from admin_log where adminName like '%"+adminName+"%' and adminName!='admin' order by entryTime desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					AdminLog as = new AdminLog();
					as.setId(rs.getInt("id"));
					as.setAdminName(rs.getString("adminName"));
					as.setContents(rs.getString("contents"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
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
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				request.setAttribute("adminName", adminName);
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
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		if(user!=null){
		
		int token = (int)(1+Math.random()*(1000000-1+1));
		System.out.println(token);
		request.getSession().setAttribute("token", String.valueOf(token));
		System.out.println("token");
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(user!=null){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token = (int)(1+Math.random()*(1000000-1+1));
				System.out.println(token);
				request.getSession().setAttribute("token", String.valueOf(token));
				String sql = "select * from admin_log where id='"+id+"' order by entryTime desc";
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					AdminLog as = new AdminLog();
					as.setId(rs.getInt("id"));
					as.setAdminName(rs.getString("adminName"));
					as.setContents(rs.getString("contents"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					request.setAttribute("adminLog", as);
				}else{
					message= "未查询到需要修改的账号信息！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_message.jsp");
				dispatcher.forward(request, response);
			}
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
					String centents = StringUtil.notNull(request.getParameter("centents"));
				
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 stmt = conn.createStatement();
					 String sqli = "insert into admin_log(adminName,contents,entryTime) values('"+admin.getAdminName()
							 +"','"+centents+"','"+date+"')";
					 stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					request.setAttribute("message", "管理员日志添加成功，请在日志列表确认！");
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_message.jsp");
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
				String sql = "select * from admin_log where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "delete from admin_log where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "日志删除删除成功！";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					message= "未查询到需要修改的地址信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_log_delAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		List<String> slist = new ArrayList<String>();
		try {
			if(admin!=null){
				
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String startTime = startTimeStr+" 00:00:00";
				String  endTime = endTimeStr+" 23:59:59";
				String sql = "select * from admin_log where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					String sqlu= "delete from admin_log where id='"+rs.getInt("id")+"'";
					slist.add(sqlu);
					
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
				message= "日志批量删除成功！";
				cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminLog_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
}

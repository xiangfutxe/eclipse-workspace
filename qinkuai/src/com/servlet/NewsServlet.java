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
import com.pojo.Admin;
import com.pojo.News;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Pager;
import com.utils.StringUtil;

public class NewsServlet extends HttpServlet {

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
		}else if(method.equals("admin_news_list")){
			admin_news_list(request,response);
	}else if(method.equals("list")){
		list(request,response);
	}else if(method.equals("detail")){
		detail(request,response);
	}else if(method.equals("admin_news_detail")){
		admin_news_detail(request,response);
	}else if(method.equals("news_detail")){
		news_detail(request,response);
	}else if(method.equals("admin_news_add")){
		admin_news_add(request,response);
	}
	else if(method.equals("admin_news_edit")){
		admin_news_edit(request,response);
	}else if(method.equals("admin_news_up")){
		try {
			admin_news_up(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}else if(method.equals("admin_news_del")){
			try {
				admin_news_del(request,response);
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
			
		}else if(method.equals("admin_news_update")){
			try {
				admin_news_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}

}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_news_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String sort = StringUtil.notNull(request.getParameter("sort"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from news where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and sort like '%"+sort+"%' order by entryTime desc";
						
					}else{
							sql  ="select * from news where sort like '%"+sort+"%' order by entryTime desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					News as = new News();
					as.setId(rs.getInt("id"));
					as.setAdmin(rs.getString("admin"));
					as.setTitle(rs.getString("title"));
					as.setContents(rs.getString("contents"));
					as.setSort(rs.getString("sort"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
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
				request.setAttribute("sort",sort);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
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
		
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(user!=null){
				
			if(db.createConn()){
				String sql  ="select * from news where state='1' order by entryTime desc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					News as = new News();
					as.setId(rs.getInt("id"));
					as.setAdmin(rs.getString("admin"));
					as.setTitle(rs.getString("title"));
					as.setContents(rs.getString("contents"));
					as.setSort(rs.getString("sort"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_news_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[0][0].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
			dispatcher.forward(request, response);
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_news_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql  ="select * from news where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					News as = new News();
					as.setId(rs.getInt("id"));
					as.setAdmin(rs.getString("admin"));
					as.setTitle(rs.getString("title"));
					as.setContents(rs.getString("contents"));
					as.setSort(rs.getString("sort"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					request.setAttribute("news", as);
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
				}else{
					message= "未查询到需要修改的新闻的相关信息，请重试！";
				}
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_news_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql  ="select * from news where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					News as = new News();
					as.setId(rs.getInt("id"));
					as.setAdmin(rs.getString("admin"));
					as.setTitle(rs.getString("title"));
					as.setContents(rs.getString("contents"));
					as.setSort(rs.getString("sort"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					request.setAttribute("news", as);
				}else{
					message= "未查询到需要修改的新闻的相关信息，请重试！";
				}
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void news_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(user!=null){
				
			if(db.createConn()){
				String sql  ="select * from news where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					News as = new News();
					as.setId(rs.getInt("id"));
					as.setAdmin(rs.getString("admin"));
					as.setTitle(rs.getString("title"));
					as.setContents(rs.getString("contents"));
					as.setSort(rs.getString("sort"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					request.setAttribute("news", as);
				}else{
					message= "未查询到需要修改的新闻的相关信息，请重试！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
		
			if(db.createConn()){
				String sql  ="select * from news where id='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					News as = new News();
					as.setId(rs.getInt("id"));
					as.setAdmin(rs.getString("admin"));
					as.setTitle(rs.getString("title"));
					as.setContents(rs.getString("contents"));
					as.setSort(rs.getString("sort"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					request.setAttribute("sys_news", as);
				}else{
					message= "未查询到需要修改的新闻的相关信息，请重试！";
				}
				
			}else {
				message= "数据库连接已断开！";
			}

				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
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
		String error = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					String title = StringUtil.notNull(request.getParameter("title"));
					String sort = StringUtil.notNull(request.getParameter("sort"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "insert into news(title,sort,contents,admin,state,entryTime,endTime) values('"+
						title+"','"+sort+"','"+contents+"','"+admin.getAdminName()+"','1','"+date+"','"+date+"')";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "新闻发布成功，请在新闻列表中查看新闻信息！");
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_news_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(StringUtil.notNull(token_old).equals(token)){
					String title = StringUtil.notNull(request.getParameter("title"));
					String sort = StringUtil.notNull(request.getParameter("sort"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					String id = StringUtil.notNull(request.getParameter("id"));
					
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "update news set title='"+title+"',contents='"+contents+"',sort='"+sort+"',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "新闻修改成功，请在新闻列表中查看新闻信息！");
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_news_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				
					String id = StringUtil.notNull(request.getParameter("id"));
					
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "update news set state='0',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "新闻下线成功，请在新闻列表中查看新闻信息！");
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_news_up(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				
					String id = StringUtil.notNull(request.getParameter("id"));
					
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "update news set state='1',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "新闻上线成功，请在新闻列表中查看新闻信息！");
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("news_message.jsp");
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

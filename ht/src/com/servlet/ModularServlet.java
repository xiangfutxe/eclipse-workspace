package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.pojo.Modular;
import com.pojo.VideoInfo;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.StringUtil;

public class ModularServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	ICustomService cs = new CustomService();
	
	
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
		}else if(method.equals("list")){
			list(request,response);
	}else if(method.equals("edit")){
		edit(request,response);
}else if(method.equals("add")){
	add(request,response);
}else if(method.equals("save")){
	try {
		save(request,response);
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}else if(method.equals("update")){
	try {
		update(request,response);
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}else if(method.equals("weixin_101")){
	weixin_101(request,response);
}else if(method.equals("weixin_102")){
	weixin_102(request,response);
}else if(method.equals("weixin_103")){
	weixin_103(request,response);
}else if(method.equals("weixin_104")){
	weixin_104(request,response);
}
}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql  ="select * from modular order by id asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Modular as = new Modular();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setType(rs.getString("type"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setContents(rs.getString("contents"));
					as.setSource(rs.getString("source"));
					coll.add(as);
				}
				request.setAttribute("mlist",coll);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
			dispatcher.forward(request, response);
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql  ="select * from modular where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Modular as = new Modular();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setType(rs.getString("type"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setContents(rs.getString("contents"));
					as.setSource(rs.getString("source"));;
					request.setAttribute("modular", as);
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
				}else{
					message= "未查询到需要修改的模块的相关信息，请重试！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
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
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					String title = StringUtil.notNull(request.getParameter("title"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String source = StringUtil.notNull(request.getParameter("source"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						 String sql = "insert into modular(title,type,source,contents,publisher,entryTime,endTime) values('"+
						title+"','"+type+"','"+source+"','"+contents+"','"+admin.getAdminName()+"','"+date+"','"+date+"')";
						
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "模块发布成功，请在模块管理中查看模块信息！");
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在模块管理中查看是否操作成功！");
			}
				}else{
				
					request.setAttribute("message", "您没有权限进行该操作！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					String title = StringUtil.notNull(request.getParameter("title"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String source = StringUtil.notNull(request.getParameter("source"));
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
					
						 String sql = "update modular set title='"+title+"',source='"+source+"',type='"+type+"',contents='"+contents+"',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "模块修改成功，请在模块管理中查看模块信息！");
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				}else{
					
					request.setAttribute("message", "您没有权限进行该操作！");
				}
				}else{
			request.setAttribute("message", "请勿重复提交数据！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					
					String id = StringUtil.notNull(request.getParameter("id"));
					
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String type ="";
					 String sql = "select * from modular where id='"+id+"' for update";
					 stmt= conn.createStatement();
					 rs = stmt.executeQuery(sql);
					 if(rs.next()){
						 type = rs.getString("type");
						 String sql1 = "delete from modular where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql1);
					 }
				message= type+"模块删除成功！";
				cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					
					message="您没有权限进行该操作！";
				}
				}else{
			 message= "请勿重复提交数据！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("modular_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void weixin_102(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		String message ="";
		try {
			if(db.createConn()){
				conn = db.getConnection();
				String sql  ="select * from modular where type='微信-服务项目'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Modular as = new Modular();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setType(rs.getString("type"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setContents(rs.getString("contents"));
					as.setSource(rs.getString("source"));;
					request.setAttribute("modular", as);
					System.out.println(rs.getString("title"));
				}else{
					message= "未查询到需要修改的模块的相关信息，请重试！";
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
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("102.jsp");
			dispatcher.forward(request, response);
		}
	}
	
public void weixin_101(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		String message ="";
		try {
			if(db.createConn()){
				conn = db.getConnection();
				String sql  ="select * from modular where type='微信-总会简介'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Modular as = new Modular();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setType(rs.getString("type"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setContents(rs.getString("contents"));
					as.setSource(rs.getString("source"));;
					request.setAttribute("modular", as);
					System.out.println(rs.getString("title"));
				}else{
					message= "未查询到需要修改的模块的相关信息，请重试！";
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
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("101.jsp");
			dispatcher.forward(request, response);
		}
	}

public void weixin_103(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	String message ="";
	try {
		if(db.createConn()){
			conn = db.getConnection();
			String sql  ="select * from modular where type='微信-品牌赛事'";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql);
			if(rs.next()){
				Modular as = new Modular();
				as.setId(rs.getInt("id"));
				as.setPublisher(rs.getString("publisher"));
				as.setTitle(rs.getString("title"));
				as.setType(rs.getString("type"));
				as.setEndTime(rs.getTimestamp("endTime"));
				as.setEntryTime(rs.getTimestamp("entryTime"));
				as.setContents(rs.getString("contents"));
				as.setSource(rs.getString("source"));;
				request.setAttribute("modular", as);
				System.out.println(rs.getString("title"));
			}else{
				message= "未查询到需要修改的模块的相关信息，请重试！";
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
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("103.jsp");
		dispatcher.forward(request, response);
	}
}

public void weixin_104(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	DBConn db = new DBConn();
	String message ="";
	try {
		if(db.createConn()){
			conn = db.getConnection();
			String sql  ="select * from modular where type='微信-旗袍文化'";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql);
			if(rs.next()){
				Modular as = new Modular();
				as.setId(rs.getInt("id"));
				as.setPublisher(rs.getString("publisher"));
				as.setTitle(rs.getString("title"));
				as.setType(rs.getString("type"));
				as.setEndTime(rs.getTimestamp("endTime"));
				as.setEntryTime(rs.getTimestamp("entryTime"));
				as.setContents(rs.getString("contents"));
				as.setSource(rs.getString("source"));;
				request.setAttribute("modular", as);
			}else{
				message= "未查询到需要修改的模块的相关信息，请重试！";
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
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("104.jsp");
		dispatcher.forward(request, response);
	}
}
	
}

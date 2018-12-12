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
import com.pojo.Branch;
import com.pojo.Message;
import com.pojo.Order;
import com.pojo.ShortMessage;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Constants;
import com.utils.Pager;
import com.utils.StringUtil;

public class ShortMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private int pageNo = 1;
	
	private int pageSize = 60;

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
	}else if(method.equals("admin_add")){
		admin_add(request,response);
}else if(method.equals("admin_edit")){
	admin_edit(request,response);
}else if(method.equals("admin_save")){
			try {
				admin_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}else if(method.equals("admin_update")){
		try {
			admin_update(request,response);
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
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="select * from short_message where state='1' order by id desc";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					ShortMessage as = new ShortMessage();
					as.setId(rs.getInt("id"));
					as.setType(rs.getString("type"));
					as.setShortMsg(rs.getString("short_msg"));
					as.setTel(rs.getString("tel"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					as.setEndTime(rs.getTimestamp("end_time"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="select * from short_message where id='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					ShortMessage as = new ShortMessage();
					as.setId(rs.getInt("id"));
					as.setType(rs.getString("type"));
					as.setShortMsg(rs.getString("short_msg"));
					as.setTel(rs.getString("tel"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setState(rs.getInt("state"));
					request.setAttribute("sys_short_msg", as);
					int token_new = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token_new));
				}else{
					message= "短信信息获取失败！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void admin_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			if(admin!=null){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
					RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_add.jsp");
					dispatcher.forward(request, response);
				}else{
					request.setAttribute("message", "管理员未登陆，请登陆！");
					RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_message.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	
	
	public void admin_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
					String shortMsg = StringUtil.notNull(request.getParameter("shortMsg"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String tel = StringUtil.notNull(request.getParameter("tel"));
					if(shortMsg.length()>80){ error = "短信内容不能超过40个字！";}
					else if(shortMsg.equals("")){ error = "短信内容不能为空！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date = new Timestamp(new Date().getTime());
					 String sqls = "select * from short_message where type='"+type+"' for update";
					 stmt= conn.createStatement();
					 rs = stmt.executeQuery(sqls);
					 if(!rs.next()){
						 String sql = "insert into short_message(type,short_msg,tel,state,entry_time,end_time) "
								 +"values('"+type+"','"+shortMsg+"','"+tel+"','1','"+date+"','"+date+"')";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", type+"短信模板保存成功，请在短信模板中查看！");
						
					 }else request.setAttribute("message", "当前短信模板类型已经存在，不能新增，请核实！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void admin_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
					String shortMsg = StringUtil.notNull(request.getParameter("shortMsg"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String tel = StringUtil.notNull(request.getParameter("tel"));
					String id = StringUtil.notNull(request.getParameter("id"));
					if(shortMsg.length()>80){ error = "短信内容不能超过40个字！";}
					else if(shortMsg.equals("")){ error = "短信内容不能为空！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date = new Timestamp(new Date().getTime());
					 String sqls = "select * from short_message where type='"+type+"' for update";
					 stmt= conn.createStatement();
					 rs = stmt.executeQuery(sqls);
					 if(rs.next()){
						 int idstr = rs.getInt("id");
						 if(id.equals(String.valueOf(idstr))){
							 String sql = "update short_message set type='"+type+"',short_msg='"+shortMsg+"',tel='"+tel+"' where id='"+id+"'";
							 stmt= conn.createStatement();
							 stmt.executeUpdate(sql);
							 request.setAttribute("message", type+"短信模板修改成功，请在短信模板中查看！");
						 }else request.setAttribute("message", "当前短信模板类型已经存在，请核实！");
					 }else{
						 String sql = "update short_message set type='"+type+"',short_msg='"+shortMsg+"',tel='"+tel+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", type+"短信模板修改成功，请在短信模板中查看！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("short_msg_message.jsp");
			dispatcher.forward(request, response);
		}
	}
}

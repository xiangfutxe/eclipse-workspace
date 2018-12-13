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
import com.pojo.BankAccount;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Constants;
import com.utils.Pager;
import com.utils.StringUtil;

public class BankAccountServlet extends HttpServlet {

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
		
		}else if(method.equals("list")){
			list(request,response);
			
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
			
		}else if(method.equals("update")){
				update(request,response);
		}else if(method.equals("del")){
			del(request,response);
	}else if(method.equals("start")){
		start(request,response);
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
		int pageSize = 10;
		try {
			if(user!=null){
				
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from bank_account  order by id desc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					BankAccount as = new BankAccount();
					as.setId(rs.getInt("id"));
					as.setUid(rs.getInt("uid"));
					as.setUserId(rs.getString("user_id"));
					as.setAccountId(rs.getString("account_id"));
					as.setAccountName(rs.getString("account_name"));
					as.setBankName(rs.getString("bank_name"));
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
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][8].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
			dispatcher.forward(request, response);
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
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
				if(rankstr[6][8].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				String sql = "select * from bankAccount where id='"+id+"'  order by id desc";
				
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					BankAccount as = new BankAccount();
					as.setId(rs.getInt("id"));
					as.setAccountId(rs.getString("accountId"));
					as.setAccountName(rs.getString("accountName"));
					as.setBankName(rs.getString("bankName"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setState(rs.getInt("state"));
					request.setAttribute("bankAccount", as);
				}else{
					message= "未查询到需要修改的账号信息！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[6][8].equals("1")||admin.getState().equals("1")){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String accountName = StringUtil.notNull(request.getParameter("accountName"));
				String accountId  = StringUtil.notNull(request.getParameter("accountId"));
				String bankName  = StringUtil.notNull(request.getParameter("bankName"));
				String sql = "select * from bankAccount where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "update bankAccount set accountName='"+accountName+"',accountId='"+accountId+"',bankName='"+bankName+"',endTime='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "银行账号修改成功，最新账号为："+accountName+","+accountId+","+bankName+"！";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					message= "未查询到需要修改的账号信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "请勿重复提交数据，请在增扣明细中查看是否操作成功！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
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
		String error = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[6][8].equals("1")||admin.getState().equals("1")){
					String accountName = StringUtil.notNull(request.getParameter("accountName"));
					String accountId  = StringUtil.notNull(request.getParameter("accountId"));
					String bankName  = StringUtil.notNull(request.getParameter("bankName"));
				
					if(accountName.equals("")){ error = "账户名称不能为空！";}
					else if(accountId.equals("")){ error = "银行账号不能为空！";}
					else if(bankName.equals("")){ error = "开户银行不能为空！";}
					if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 stmt = conn.createStatement();
					 String sqli = "insert into bankAccount(accountId,accountName,bankName,state,entryTime,endTime) values('"+accountId+"','"+accountName+"','"+bankName+"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					request.setAttribute("message", "账号添加成功，请在账号列表确认！");
					cs.insetAdminLog(conn, admin.getAdminName(), "账号添加成功，请在账号列表确认！", date);

				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
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
				
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[6][9].equals("1")||admin.getState().equals("1")){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String sql = "select * from bankAccount where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "update bankAccount set state='0',endTime='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "银行账号删除成功，改账号已处于停用状态！";
				}else{
					message= "未查询到需要修改的账号信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void start(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
	
		try {
			if(admin!=null){
			
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[6][8].equals("1")||admin.getState().equals("1")){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String sql = "select * from bankAccount where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "update bankAccount set state='1',endTime='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "银行账号重启成功，改账号已处于正常状态！";
				}else{
					message= "未查询到需要修改的账号信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("bank_account_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.TimeParam;
import com.utils.DateUtil;
import com.utils.StringUtil;

public class TimeParamServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		}else if(method.equals("init")){
			try {
				init(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(method.equals("edit")){
			edit(request,response);
		}else if(method.equals("addSettle")){
			addSettle(request,response);
		}else if(method.equals("update")){
		try {
			update(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
				if(admin!=null){
				if(admin.getState().equals("1")){
					if(db.createConn()){
						String sql = "select * from timeparam where paramName ='时间参数'";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql);
						if(rs.next()){
						TimeParam tp = new TimeParam();
						tp.setId(rs.getInt("id"));
						tp.setParamName(rs.getString("paramName"));
						tp.setMonthNum(rs.getInt("monthNum"));
						tp.setStartTime(rs.getTimestamp("startTime"));
						tp.setWeekNum(rs.getDouble("weekNum"));
						request.setAttribute("timeparam", tp);
						
					}else {
						message ="未查询到相应的时间参数，请初始化时间参数！";
					}
				}else {
					message ="数据库连接已断开！";
				}
				}else{
					message ="您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message ="管理员用户未登陆，请重新登陆！";
				}
				rs.close();
				stmt.close();
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					rs.close();
					stmt.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				request.setAttribute("message", "数据保存有误，请重新录入！");
			}finally{
				db.close();
				if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("time_param_edit.jsp");
				dispatcher.forward(request, response);
				}
				else{
						RequestDispatcher dispatcher = request.getRequestDispatcher("time_param_message.jsp");
						dispatcher.forward(request, response);
				}
			}
		}
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
			if(admin.getState().equals("1")){
					String startTime = request.getParameter("startTime");
					System.out.println("startTime:"+startTime);
					String weekNum = request.getParameter("weekNum");
					System.out.println("weekNum:"+weekNum);
					String monthNum = request.getParameter("monthNum");
					if(!(StringUtil.notNull(startTime).equals("")||StringUtil.notNull(weekNum).equals("")||StringUtil.notNull(monthNum).equals(""))){
						 Timestamp date1  = new Timestamp(StringUtil.parseToDate(startTime, DateUtil.yyyyMMddHHmmss).getTime());
			if(db.createConn()){
					String ids = request.getParameter("id");
					System.out.println("ids:"+ids);
					if(!StringUtil.notNull(ids).equals("")){
					int id = Integer.valueOf(ids);
					String sql = "select * from timeparam where id='"+id+"' for update";
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(rs.next()){
					db.close();
					 String sqli = "update timeparam set startTime='"+date1+"',weekNum='"+Double.valueOf(weekNum)+"',monthNum='"+Integer.valueOf(monthNum)+"' where id='"+id+"'";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 stmt.close();
					 request.setAttribute("message", "时间参数修改成功，请在时间参数设置中查看！");
				}else{
					request.setAttribute("message", "未找到需要修改的时间参数，请在时间参数设置中查看！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 
					}else {
						request.setAttribute("message", "未取得参数ID信息，请重试！");
					}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			}else{
				request.setAttribute("message","时间参数有误，请重新确认！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			rs.close();
			stmt.close();
			
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("time_param_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void init(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{

		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		
		DBConn db = new DBConn();
		try {
			if(admin!=null){
			
				if(admin.getState().equals("1")){
			if(db.createConn()){
					
					String sql = "select * from timeparam where paramName ='时间参数'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					db.close();
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 System.out.println(date);
					 String sqli = "insert into timeparam(paramName,startTime,weekNum,monthNum) values('时间参数','"+date+"','168','4')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 stmt.close();
					 conn.commit();
					 conn.setAutoCommit(autoCommit);
					 
					 request.setAttribute("message", "时间参数初始化成功，请在时间参数设置中查看！");
				}else{
					request.setAttribute("message", "时间参数已经初始化，请在参数设置中查看！");
				}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			rs.close();
			stmt.close();
			
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("time_param_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void addSettle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(admin.getState().equals("1")){
			if(db.createConn()){
				String startTime = request.getParameter("startTime");
				String weekNum = request.getParameter("weekNum");
				String monthNum = request.getParameter("monthNum");
				if(!(StringUtil.notNull(startTime).equals("")||StringUtil.notNull(weekNum).equals("")||StringUtil.notNull(monthNum).equals(""))){
					 Timestamp date1  = new Timestamp(StringUtil.parseToDate(startTime, DateUtil.yyyyMMddHHmmss).getTime());
					 System.out.println("date1:"+date1.getTime());
					 System.out.println("weekNum:"+(long)(Double.valueOf(weekNum)*3600000));
					 Timestamp  date2 =new  Timestamp((date1.getTime() +(long)(Double.valueOf(weekNum)*3600000)));
				String sql = "select * from settle where startTime ='"+date1+"'";
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				if(!rs.next()){
					db.close();
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 String sqli = "insert into settle(startTime,endTime,tag,weekTag,state) values('"+date1+"','"+date2+"','1','0','0')";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="结算表生成成功！";
					 conn.commit();
					 conn.setAutoCommit(autoCommit);
					 
				}else{
					message="结算表已存在，请勿重复操作！";
				}
				
				
			}else{
				message="时间参数有误，请重新确认！";
			}
			}else {
				message="数据库连接已断开！";
			}
				}else{
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			}else{
				message="管理员用户未登陆，请重新登陆！";
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			db.close();
			try {
				rs.close();
				stmt.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("time_param_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	

}

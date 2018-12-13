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
import com.pojo.Address;
import com.pojo.Admin;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Constants;
import com.utils.Pager;
import com.utils.StringUtil;
import com.weixin.pojo.WeixinUserInfo;

public class AddressServlet extends HttpServlet {

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
	}

}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
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
				String sql = "select * from adr where state='1' and user_id='"+user.getUserId()+"' order by id desc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Address as = new Address();
					as.setId(rs.getInt("id"));
					as.setUserId(rs.getString("user_id"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setAdr(rs.getString("adr"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					as.setTag(rs.getInt("tag"));
					as.setTel(rs.getString("tel"));
					as.setReciver(rs.getString("reciver"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
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
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("adr_list.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("adr_msg.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");		String token_old = (String)request.getSession().getAttribute("token");
		if(user!=null){
		
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("adr_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("adr_msg.jsp");
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
				request.getSession().setAttribute("token", String.valueOf(token));
				String sql = "select * from address where id='"+id+"' order by id desc";
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Address as = new Address();
					as.setId(rs.getInt("id"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setId(rs.getInt("id"));
					as.setReciver(rs.getString("reciver"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					request.setAttribute("address", as);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("address_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("address_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message ="";
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(user!=null){
				if(StringUtil.notNull(token_old).equals(token)){
			
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String receiver = StringUtil.notNull(request.getParameter("receiver"));
				String adr = StringUtil.notNull(request.getParameter("address"));
				String province = StringUtil.notNull(request.getParameter("province"));
				String city = StringUtil.notNull(request.getParameter("city"));
				String area = StringUtil.notNull(request.getParameter("area"));
				String phone = StringUtil.notNull(request.getParameter("phone"));
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql = "select * from address where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "update address set receiver='"+receiver+"',province='"+province+"',city='"+city+"',area='"+area+"',address='"+adr+"',phone='"+phone+"',endTime='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "地址信息修改成功，请在地址列表重查看！";
				}else{
					message= "未查询到需要修改的地址信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "请勿重复提交数据，请在地址列表中查看是否操作成功！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("adr_msg.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		DBConn db = new DBConn();
		String message="";
		try {
			if(user!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					String receiver = StringUtil.notNull(request.getParameter("reciver"));
					String province = StringUtil.notNull(request.getParameter("s_province"));
					String city = StringUtil.notNull(request.getParameter("s_city"));
					String area = StringUtil.notNull(request.getParameter("s_county"));
					String adr = StringUtil.notNull(request.getParameter("s_adr"));
					String phone = StringUtil.notNull(request.getParameter("tel"));
				
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu  ="update adr set tag=0 where user_id='"+user.getUserId()+"'";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqlu);
					 String sqli = "insert into adr(uid,user_id,reciver,tel,province,city,area,adr,tag,state,entry_time,end_time) values('"+user.getId()+"','"+user.getUserId()
							 +"','"+receiver+"','"+phone +"','"+province +"','"+city +"','"+area+"','"+adr+"','1','1','"+date+"','"+date+"');";
					message=sqli;
					 stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="地址添加成功，请在账号列表确认！";
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
			message= "请勿重复提交数据，请在地址列表中查看是否操作成功！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= message+"数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("adr_msg.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		
		try {
			if(user!=null){
				
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String sql = "select * from address where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sqlu= "update address set state='0',endTime='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "地址信息删除成功！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("address_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
}

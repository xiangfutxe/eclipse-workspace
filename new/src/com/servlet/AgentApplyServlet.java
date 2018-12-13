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
import com.pojo.AgentApply;
import com.pojo.Param;
import com.pojo.Admin;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;

public class AgentApplyServlet extends HttpServlet {

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
		
		}else if(method.equals("admin_list")){
			admin_list(request,response);
			
		}else if(method.equals("add")){
			add(request,response);
			
		}else if(method.equals("save")){
			try {
				save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(method.equals("admin_detail")){
				admin_detail(request,response);
			
		}else if(method.equals("update")){
				update(request,response);
		}else if(method.equals("admin_review_yes")){
			admin_review_yes(request,response);
	}else if(method.equals("admin_review_no")){
		admin_review_no(request,response);
}else if(method.equals("del")){
			del(request,response);
	}

}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String state = StringUtil.notNull(request.getParameter("state"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from agent_apply  where  user_id like'%"+userId+"%' and state like'%"+state+"%' order by id asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					AgentApply as = new AgentApply();
					as.setId(rs.getInt("id"));
					as.setUserId(rs.getString("user_id"));
					as.setUserName(rs.getString("user_name"));
					as.setDocumentType(rs.getString("document_type"));
					as.setNumId(rs.getString("num_id"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setTel(rs.getString("tel"));
					as.setAddress(rs.getString("address"));
					as.setReviewer(rs.getString("reviewer"));
					as.setState(rs.getInt("state"));
					as.setReviewTime(rs.getTimestamp("review_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
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
				request.setAttribute("userId", userId);
				request.setAttribute("state", state);
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
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_list.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("param_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("param_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				String sql = "select * from agent_apply where id='"+id+"' order by id desc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
						AgentApply as = new AgentApply();
						as.setId(rs.getInt("id"));
						as.setUserId(rs.getString("user_id"));
						as.setUserName(rs.getString("user_name"));
						as.setDocumentType(rs.getString("document_type"));
						as.setNumId(rs.getString("num_id"));
						as.setProvince(rs.getString("province"));
						as.setCity(rs.getString("city"));
						as.setArea(rs.getString("area"));
						as.setAddress(rs.getString("address"));
						as.setTel(rs.getString("tel"));
						as.setReviewer(rs.getString("reviewer"));
						as.setState(rs.getInt("state"));
						as.setReviewTime(rs.getTimestamp("review_time"));
						as.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("agent_apply", as);
				}else{
					message= "未查询到相关代理商信息！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_message.jsp");
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
				String  userName= StringUtil.notNull(request.getParameter("userName"));

				String documentType = StringUtil.notNull(request.getParameter("documentType"));
				String numId = StringUtil.notNull(request.getParameter("numId"));
				String province = StringUtil.notNull(request.getParameter("province"));
				String city = StringUtil.notNull(request.getParameter("city"));
				String area = StringUtil.notNull(request.getParameter("area"));
				String tel = StringUtil.notNull(request.getParameter("tel"));
				String address = StringUtil.notNull(request.getParameter("address"));	
				String sql = "select * from agent_apply where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String userId=rs.getString("user_id");
					String sqlu= "update agent_apply set user_name='"+userName+"',document_type='"+documentType+"',num_id='"+numId+"',province='"+province
							+"',city='"+city+"',area='"+area+"',tel='"+tel+"',address='"+address+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= userId+"代理商申请信息保存修改成功。";
					Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					message= "未查询到需要修改的申请信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "请勿重复提交数据。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		DBConn db = new DBConn();
		String message ="";
		try {
			if(db.createConn()){
				conn = db.getConnection();
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String documentType = StringUtil.notNull(request.getParameter("documentType"));
					String numId = StringUtil.notNull(request.getParameter("numId"));
					String province = StringUtil.notNull(request.getParameter("province"));
					String city = StringUtil.notNull(request.getParameter("city"));
					String area = StringUtil.notNull(request.getParameter("area"));
					String tel = StringUtil.notNull(request.getParameter("phone"));
					String address = StringUtil.notNull(request.getParameter("address"));
					
				
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date = new Timestamp(new Date().getTime());
					 String sql ="select * from userinfo where user_id='"+userId+"' and state='1'";
					 stmt = conn.createStatement();
					 rs = stmt.executeQuery(sql);
					 if(rs.next()){
						 String phone = rs.getString("tel");
						 int uid = rs.getInt("id");
						 String userName = rs.getString("user_name");
						 if(tel.equals(phone)){
							 sql = "select * from agent_apply where user_id='"+userId+"'";
							 stmt = conn.createStatement();
							 rs = stmt.executeQuery(sql);
							 if(!rs.next()){
								 String applyId=cs.createAgentApplyId(date);
								 String sqli = "insert into agent_apply(apply_id,u_id,user_id,user_name,document_type,num_id,province,city,area,address,tel,state,entry_time) values('"
										 +applyId+"','"+uid+"','"+userId+"','"+userName+"','"+documentType+"','"+numId+"','"+province+"','"+city+"','"+area+"','"+address+"','"+tel+"','1','"+date+"')";
								 stmt = conn.createStatement();
								stmt.executeUpdate(sqli);
								
								message= "代理商申请成功，申请编号为："+applyId+"，请关注购物平台中是否有首次进货功能；<br><br>如果存在，说明初步审批已经成功，首次进货后将正式成为代理商。";
							 }else{
								 message = "你输入的会员编号已成为或者已申请成为代理商，请勿重复申请。";
							 }
						 }else{
							 message = "预留的手机号码与会员基本信息不一致。";
						 }
					 }else{
						 message = "输入的会员编号有误，请重新输入。";
					 }
					
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message="数据库连接已断开！";
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_message.jsp");
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
	
	public void admin_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				
				String sql = "select * from agent_apply where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String userId= rs.getString("user_id");
					Timestamp date = new Timestamp(new Date().getTime());
					String sqlu= "update agent_apply set state='2',reviewer='"+admin.getAdminName()+"',review_time='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					sqlu = "update userinfo set rank_join='2' where user_id='"+userId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= userId+"代理商资格审批通过。";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);

				}else{
					message= "未查询到需要的代理商申请信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "请勿重复提交数据。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				
				String sql = "select * from agent_apply where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String userId=rs.getString("user_id");
					Timestamp date = new Timestamp(new Date().getTime());
					String sqlu= "update agent_apply set state='0',reviewer='"+admin.getAdminName()+"',review_time='"+date+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= userId+"代理商资格审批不通过。";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);

				}else{
					message= "未查询到需要的代理商申请信息！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "请勿重复提交数据。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("agent_apply_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
}

package com.servlet;

import java.io.IOException;
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
import com.pojo.RankJoinUpdate;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;

public class RankJoinUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Statement stmt = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private int rankJoin = 0;
	private String str = "";
	ICustomService cs = new CustomService();
	IUserService us = new UserService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (method == null) {
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		}else if(method.equals("rankJoin_up")){
			try {
				rankJoin_up(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_update")){
			try {
				rankJoin_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_list")){
			try {
				rankJoin_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_reset_reward")){
			try {
				rankJoin_reset_reward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void rankJoin_up(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
	User user1 = null;
		try{
			if (admin != null) {
			if (db.createConn()) {
				conn = db.getConnection();
				String userId = request.getParameter("userId");
				user1 = us.findById(conn, userId);
					if(user1!=null){
						request.setAttribute("up_user", user1);		
					} else {
						message="会员信息获取失败，请重试！";
					}
			
				} else {
					message="数据库连接已断开！";
					
				}
		} else {
			message="用户未登陆，请重新登陆！";
		}
} catch (Exception e) {
	// TODO Auto-generated catch block
	conn.rollback();
	e.printStackTrace();
	message= "数据保存有误，请重新录入！";
} finally {
	db.close();
	user1=null;
	if(message.equals("")){
	RequestDispatcher dispatcher = request
			.getRequestDispatcher("rankJoin_update.jsp");
	dispatcher.forward(request, response);
	}else{
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("rankJoin_update_message.jsp");
		dispatcher.forward(request, response);
	}
	
}

	}
	
	public synchronized void rankJoin_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
	
		// java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try{
			if (admin != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					String id = StringUtil.notNull(request.getParameter("id"));
					String rankJoin = StringUtil.notNull(request.getParameter("rankJoin"));
					Timestamp date = new Timestamp(new Date().getTime());
					String sql ="select * from userinfo where id='"+id+"' for update";
					stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
						if(rs.next()){
							int rank = rs.getInt("rank_join");
							int uid = rs.getInt("id");
							String userId = rs.getString("user_id");
							if(!String.valueOf(rank).equals(rankJoin)){
								String sqlu= "update userinfo set rank_join='"+rankJoin+"' where id='"+id+"'";
								stmt = conn.createStatement();
								stmt.executeUpdate(sqlu);
								String sqlu1= "insert into join_info(u_id,user_id,price,old_rank,new_rank,state,entry_time,end_time) values('"+
										uid+"','"+userId+"','0','"+rank+"','"+rankJoin+"','2','"+date+"','"+date+"')";
								stmt = conn.createStatement();
								stmt.executeUpdate(sqlu1);
								message=userId+"等级更新成功，请查看变更列表！";
								cs.insetAdminLog(conn, admin.getAdminName(), message, date);
							}else{
								message="最新奖衔与原奖衔相同，请重新操作！";
							}
						} else {
							message="会员信息获取失败，请重试！";
						}
				conn.commit();
				conn.setAutoCommit(autoCommit);
					
				} else {
					message="数据库连接已断开！";
					
				}
		} else {
			message="用户未登陆，请重新登陆！";
		}
} catch (Exception e) {
	// TODO Auto-generated catch block
	conn.rollback();
	e.printStackTrace();
	message= "数据保存有误，请重新录入！";
} finally {
	db.close();
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("rankJoin_update_message.jsp");
		dispatcher.forward(request, response);
	
}

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void rankJoin_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List result = new ArrayList();
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTime = StringUtil
				.notNull(request.getParameter("startTime"));
		String endTime = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if (admin != null) {
						if (db.createConn()) {
							conn = db.getConnection();
							String  sql="";
							if(!(startTime.equals("")||endTime.equals(""))) 
								sql= "select * from join_info as jo,userinfo as uo where  jo.user_id=uo.user_id and jo.entry_time>='"+startTime+"' and jo.entry_time<'"+endTime+"' and jo.user_id like '%"+userId+"%' order by jo.entry_time desc";
							else sql= "select * from join_info as jo,userinfo as uo  where  jo.user_id=uo.user_id and  jo.user_id like '%"+userId+"%' order by jo.entry_time desc";
							stmt = db.getStmtread();
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								RankJoinUpdate jf = new RankJoinUpdate();
								jf.setId(rs.getInt("jo.id"));
								jf.setUserId(rs.getString("jo.user_id"));
								jf.setUserName(rs.getString("uo.user_name"));
								jf.setRankJoinOld(rs.getInt("jo.old_rank"));
								jf.setRankJoinNew(rs.getInt("jo.new_rank"));
								jf.setState(rs.getInt("jo.state"));
								jf.setPrice(rs.getDouble("jo.price"));
								jf.setEntryTime(rs.getTimestamp("jo.entry_time"));
								result.add(jf);
							}
							if (result.size() > 0) {
								if (!StringUtil.notNull(pageNoStr).equals(""))
									pageNo = Integer.valueOf(pageNoStr);
								if (!StringUtil.notNull(pageSizeStr).equals(""))
									pageSize = Integer.valueOf(pageSizeStr);
								int startIndex = pageSize * (pageNo - 1);
								int endIndex = pageSize * pageNo;
								if (result.size() < endIndex)
									endIndex = result.size();
								coll = result.subList(startIndex, endIndex);
								Pager pager = new Pager(pageSize, pageNo,
										result.size(), coll);
								request.setAttribute("pager", pager);
							}
							request.setAttribute("userId", userId);
							request.setAttribute("startTime", startTime);
							request.setAttribute("endTime", endTime);
					} else {
						message = "数据库连接出错，请稍后再试或与管理员联系！";
					}
			
			} else {
				message = "用户未登陆，请重新登陆！";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			db.close();
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_list.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_update_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	public synchronized void rankJoin_reset_reward(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
	
		// java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try{
			if (admin != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					String id = StringUtil.notNull(request.getParameter("id"));
					Timestamp date = new Timestamp(new Date().getTime());
					String sql ="select * from join_info where id='"+id+"'";
					stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
						if(rs.next()){
							String userId = rs.getString("user_id");
							Timestamp entryTime = rs.getTimestamp("entry_time");
							sql ="select * from orders where user_id='"+userId+"' and order_type='1'  and order_time='"+StringUtil.parseToString(entryTime, DateUtil.yyyyMMddHHmmss)+"'";
							stmt = conn.createStatement();
							rs=stmt.executeQuery(sql);
							if(rs.next()){
								Timestamp confirmTime = rs.getTimestamp("confirm_time");
								String sqlu= "delete from  orders where user_id='"+userId+"' and order_type='1'  and order_time='"+StringUtil.parseToString(entryTime, DateUtil.yyyyMMddHHmmss)+"'";
								stmt = conn.createStatement();
								int a1 = stmt.executeUpdate(sqlu);
								if(a1>0){
									sqlu= "update wreward set state='0' where user_by_user_id='"+userId+"' and entry_time='"+ StringUtil.parseToString(confirmTime, DateUtil.yyyyMMddHHmmss)+"'";
									stmt = conn.createStatement();
									int a2 = stmt.executeUpdate(sqlu);
									if(a2>0){
										sqlu= "update join_info set price='0' where id='"+id+"'";
										stmt = conn.createStatement();
										int a3 = stmt.executeUpdate(sqlu);
										if(a3>0){
											message=userId+"的业绩归零操作成功！";
											cs.insetAdminLog(conn, admin.getAdminName(), message, date);
										}else{
											message=userId+"的业绩归零操作失败，更新等级信息失败。";
											conn.rollback();
										}
									}else{
										message=userId+"的业绩归零操作失败，更新奖金明细失败。";
										conn.rollback();
									}
								}else{
									message=userId+"的业绩归零操作失败，更新订单失败。";
									conn.rollback();
								}
							}else{
								message="订单信息获取失败，请重试！";
							}
							
						} else {
							message="会员信息获取失败，请重试！";
						}
				conn.commit();
				conn.setAutoCommit(autoCommit);
					
				} else {
					message="数据库连接已断开！";
					
				}
		} else {
			message="用户未登陆，请重新登陆！";
		}
} catch (Exception e) {
	// TODO Auto-generated catch block
	conn.rollback();
	e.printStackTrace();
	message= "数据保存有误，请重新录入！";
} finally {
	db.close();
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("rankJoin_update_message.jsp");
		dispatcher.forward(request, response);
	
}

	}
	
	
}

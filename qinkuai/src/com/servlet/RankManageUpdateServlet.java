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
import com.pojo.RankManage;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.Pager;
import com.utils.StringUtil;

public class RankManageUpdateServlet extends HttpServlet {

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
		}else if(method.equals("rankManage_up")){
			try {
				rankManage_up(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankManage_update")){
			try {
				rankManage_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankManage_up_list")){
			try {
				rankManage_up_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void rankManage_up(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
	
		try{
			if (admin != null) {
			if (db.createConn()) {
				conn = db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				String userId = request.getParameter("userId");
				User user1 = us.findById(conn, userId);
					if(user1!=null){
					
						request.setAttribute("up_user", user1);		
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
	if(message.equals("")){
	RequestDispatcher dispatcher = request
			.getRequestDispatcher("rankManage_update.jsp");
	dispatcher.forward(request, response);
	}else{
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("rankManage_update_message.jsp");
		dispatcher.forward(request, response);
	}
	
}

	}
	
	public synchronized void rankManage_update(HttpServletRequest request,
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
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String rankManage = StringUtil.notNull(request.getParameter("rankManage"));
					Timestamp date = new Timestamp(new Date().getTime());
					String sql ="select * from users where userId='"+userId+"' for update";
					stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
						if(rs.next()){
							int rank = rs.getInt("rankManage");
							int uid = rs.getInt("id");
							String userName = rs.getString("userName");
							if(!String.valueOf(rank).equals(rankManage)){
								String sqlu= "update users set rankManage='"+rankManage+"' where userId='"+userId+"'";
								stmt = conn.createStatement();
								stmt.executeUpdate(sqlu);
								String sqlu1= "insert into rankmanageupdate(uId,userId,userName,oldRankManage,newRankManage,adminName,entryTime) values('"+
										uid+"','"+userId+"','"+userName+"','"+rank+"','"+rankManage+"','"+admin.getAdminName()+"','"+date+"')";
								stmt = conn.createStatement();
								stmt.executeUpdate(sqlu1);
								message=userId+"奖衔更新成功，请查看变更列表！";
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
				.getRequestDispatcher("rankManage_update_message.jsp");
		dispatcher.forward(request, response);
	
}

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void rankManage_up_list(HttpServletRequest request,
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
								sql= "select * from rankmanageupdate where  entryTime>='"+startTime+"' and entryTime<'"+endTime+"' and (userId like '%"+userId+"%' or userName like '%"+userId+"%') order by entryTime desc";
							else sql= "select * from rankmanageupdate where  (userId like '%"+userId+"%' or userName like '%"+userId+"%') order by entryTime desc";
							stmt = db.getStmtread();
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								RankManage jf = new RankManage();
								jf.setId(rs.getInt("id"));
								jf.setUserId(rs.getString("userId"));
								jf.setUserName(rs.getString("userName"));
								jf.setOldRankManage(rs.getInt("oldRankManage"));
								jf.setNewRankManage(rs.getInt("newRankManage"));
								jf.setAdminName(rs.getString("adminName"));
								jf.setState(rs.getInt("state"));
								jf.setEntryTime(rs.getTimestamp("entryTime"));
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
						.getRequestDispatcher("rankManage_up_list.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankManage_update_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	
	
	
}

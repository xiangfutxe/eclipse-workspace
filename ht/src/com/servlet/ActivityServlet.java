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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.User;
import com.pojo.VideoInfo;
import com.utils.Pager;
import com.utils.StringUtil;

public class ActivityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	
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
		}else if(method.equals("A_401")){
			A_401(request,response);
	}else if(method.equals("A_402")){
		A_402(request,response);
}else if(method.equals("A_403")){
	A_403(request,response);
}else if(method.equals("A_404")){
	A_404(request,response);
}else if(method.equals("A_405")){
	A_405(request,response);
}else if(method.equals("admin_list")){
			admin_list(request,response);
	}else if(method.equals("list")){
		list(request,response);
	}else if(method.equals("admin_detail")){
		admin_detail(request,response);
	}else if(method.equals("detail")){
		detail(request,response);
	}else if(method.equals("admin_add")){
		admin_add(request,response);
	}else if(method.equals("admin_save")){
		try {
			admin_save(request,response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}else if(method.equals("admin_edit")){
		admin_edit(request,response);
	}else if(method.equals("admin_up")){
		try {
			admin_up(request,response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}else if(method.equals("admin_down")){
		try {
			admin_down(request,response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}else if(method.equals("admin_del")){
		try {
			admin_del(request,response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}else if(method.equals("img_edit")){
		try {
			img_edit(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else if(method.equals("img_save")){
		try {
			img_save(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	
	
	public void A_401(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		
		String message ="";
	
		try {
			if(db.createConn()){
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("401.jsp");
			dispatcher.forward(request, response);
		}
	}

public void A_402(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("402.jsp");
		dispatcher.forward(request, response);
	}
}

public void A_403(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("403.jsp");
		dispatcher.forward(request, response);
	}
}
public void A_404(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("404.jsp");
		dispatcher.forward(request, response);
	}
}

public void A_405(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("405.jsp");
		dispatcher.forward(request, response);
	}
}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				conn = db.getConnection();
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from video_info where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and state='1' order by entryTime desc";
						
					}else{
							sql  ="select * from video_info where state='1' order by entryTime desc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					VideoInfo as = new VideoInfo();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setIntroduction(rs.getString("introduction"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setBrowseNum(rs.getInt("browse_num"));
					as.setPublisher(rs.getString("publisher"));
					as.setSource(rs.getString("source"));
					as.setImageUrl(rs.getString("image_url"));
					as.setVideoUrl(rs.getString("video_url"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
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
		int pageSize = 10;
		try {
			if(user!=null){
				
			if(db.createConn()){
				String sql  ="select * from video_info  where state='1' order by entryTime desc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					VideoInfo as = new VideoInfo();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setIntroduction(rs.getString("introduction"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setBrowseNum(rs.getInt("browse_num"));
					as.setPublisher(rs.getString("publisher"));
					as.setSource(rs.getString("source"));
					as.setImageUrl(rs.getString("image_url"));
					as.setVideoUrl(rs.getString("video_url"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_info_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql  ="select * from video_info where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					VideoInfo as = new VideoInfo();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setIntroduction(rs.getString("introduction"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setBrowseNum(rs.getInt("browse_num"));
					as.setPublisher(rs.getString("publisher"));
					as.setSource(rs.getString("source"));
					as.setImageUrl(rs.getString("image_url"));
					as.setVideoUrl(rs.getString("video_url"));
					request.setAttribute("video_info", as);
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
				}else{
					message= "未查询到需要修改的视频的相关信息，请重试！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_info_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql  ="select * from video_info where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					VideoInfo as = new VideoInfo();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setIntroduction(rs.getString("introduction"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setBrowseNum(rs.getInt("browse_num"));
					as.setPublisher(rs.getString("publisher"));
					as.setSource(rs.getString("source"));
					as.setImageUrl(rs.getString("image_url"));
					as.setVideoUrl(rs.getString("video_url"));
					request.setAttribute("video_info", as);
				}else{
					message= "未查询到相关的视频信息，请重试！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(user!=null){
				
			if(db.createConn()){
				String sql  ="select * from video_info where id='"+id+"'";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					VideoInfo as = new VideoInfo();
					as.setId(rs.getInt("id"));
					as.setPublisher(rs.getString("publisher"));
					as.setTitle(rs.getString("title"));
					as.setIntroduction(rs.getString("introduction"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setBrowseNum(rs.getInt("browse_num"));
					as.setPublisher(rs.getString("publisher"));
					as.setSource(rs.getString("source"));
					as.setImageUrl(rs.getString("image_url"));
					as.setVideoUrl(rs.getString("video_url"));
					request.setAttribute("video_info", as);
				}else{
					message= "未查询到需要修改的视频的相关信息，请重试！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_info_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					String title = StringUtil.notNull(request.getParameter("title"));
					String videoUrl = StringUtil.notNull(request.getParameter("videoUrl"));
					String source = StringUtil.notNull(request.getParameter("source"));
					String introduction = StringUtil.notNull(request.getParameter("introduction"));
					
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						 String sql = "insert into video_info(title,video_url,introduction,source,publisher,state,entryTime,endTime) values('"+
						title+"','"+videoUrl+"','"+introduction+"','"+source+"','"+admin.getAdminName()+"','1','"+date+"','"+date+"')";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "视频发布成功，请在视频列表中查看视频信息！");
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在视频列表中查看是否操作成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					String title = StringUtil.notNull(request.getParameter("title"));
					String introduction = StringUtil.notNull(request.getParameter("introduction"));
					String source = StringUtil.notNull(request.getParameter("source"));
					String video_url = StringUtil.notNull(request.getParameter("videoUrl"));
					String id = StringUtil.notNull(request.getParameter("id"));
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "update video_info set title='"+title+"',source='"+source+"',video_url='"+video_url+"',introduction='"+introduction+"',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "视频修改成功，请在视频列表中查看视频信息！");
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_down(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
					String id = StringUtil.notNull(request.getParameter("id"));
					
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sql = "update video_info set state='0',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "视频下线成功，请在视频列表中查看视频信息！");
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
					String id = StringUtil.notNull(request.getParameter("id"));
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String sql = "delete from  video_info  where id='"+id+"'";
				stmt= conn.createStatement();
				stmt.executeUpdate(sql);
				request.setAttribute("message", "视频删除成功，请在视频列表中查看视频信息！");
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_up(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
					String id = StringUtil.notNull(request.getParameter("id"));
					
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						 String sql = "update video_info set state='1',endTime='"+date+"' where id='"+id+"'";
						 stmt= conn.createStatement();
						 stmt.executeUpdate(sql);
						 request.setAttribute("message", "视频上线成功，请在视频列表中查看视频信息！");
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void img_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			String sql1 = "select * from video_info where id='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				VideoInfo pt = new VideoInfo();
				pt.setId(rs.getInt("id"));
				pt.setTitle(rs.getString("title"));
				pt.setSource(rs.getString("source"));
				pt.setVideoUrl(rs.getString("video_url"));
				pt.setImageUrl(rs.getString("image_url"));
				request.setAttribute("video_info", pt);
			}else{
				message="未获取相应的视频信息，请重试！";
			}
		}else {
			message="数据库连接已断开！";
		}	
		}else{
			message= "您没有该操作权限，如有需要请与系统管理员联系！";
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
				if(message.equals("")){
					RequestDispatcher dispatcher = request.getRequestDispatcher("video_img_edit.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void img_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
		 String path = request.getRealPath("/upload");  
		 factory.setRepository(new File(path)); 
		 factory.setSizeThreshold(1024*1024) ;  
		 ServletFileUpload upload = new ServletFileUpload(factory);  
		 String message = "";
		 DBConn db = new DBConn();
		 try {  
			 if(admin!=null){
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
		 List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		 String id = "";
		 String token = "";
		 String imgurl = "";
		 for(FileItem item : list)  
         {  
             String name = item.getFieldName();  
             if(item.isFormField())  
             {                     
                 String value = item.getString() ;  
                   if(name.equals("token")){
                	   token = value;
                   }else if(name.equals("id")) id =value;
             }  
             else  
             {  
                 String value = item.getName() ;  
                 int start = value.lastIndexOf("\\");  
                 String filename = value.substring(start+1);  
                 imgurl = filename;
                 OutputStream out = new FileOutputStream(new File(path,filename));  
                 InputStream in = item.getInputStream() ;  
                 int length = 0 ;  
                 byte [] buf = new byte[1024] ;  
                 while( (length = in.read(buf) ) != -1)  
                 		{  
                      out.write(buf, 0, length);  
                 		}  
                 in.close();  
                 out.close();  
             }  
         }  
		 int token_new = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token_new));
		
		 if(StringUtil.notNull(token_old).equals(token)){
		 if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			 String sql = "update video_info set image_url='"+imgurl+"' where id='"+id+"'";
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 message= "视频截图上传成功！";
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
		 }else {
			 message= "数据库连接已断开！";
		}
		 
	}else{
		 message= "请勿重复提交数据，请在视频列表中查看是否保存成功！";
	}
					}else{
						 message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
	}else{
		 message= "管理员用户未登陆，请重新登陆！";
	}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				 message= "管理员用户未登陆，请重新登陆！";
			}finally{
				db.close();
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("video_message.jsp");
					dispatcher.forward(request, response);
				}
		}
}

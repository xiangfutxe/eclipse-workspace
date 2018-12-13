package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConn;
import com.pojo.VideoInfo;
import com.utils.Pager;
import com.utils.StringUtil;

public class SchoolServlet extends HttpServlet {

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
		}else if(method.equals("S_601")){
			S_601(request,response);
	}else if(method.equals("S_602")){
		S_602(request,response);
}else if(method.equals("S_602_1")){
	S_602_1(request,response);
}else if(method.equals("S_603")){
	S_603(request,response);
}else if(method.equals("S_604")){
	S_604(request,response);
}
}
	
	
	
	public void S_601(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("601.jsp");
			dispatcher.forward(request, response);
		}
	}

@SuppressWarnings({ "rawtypes", "unchecked" })
public void S_602(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	DBConn db = new DBConn();
	Collection coll = new ArrayList();
	List  result = new ArrayList();
	String message ="";

	String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
	String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
	int pageNo = 1;
	int pageSize = 10;
	try {
		if(db.createConn()){
			conn = db.getConnection();
			String sql  ="select * from video_info where state='1' order by entryTime desc";
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("602.jsp");
		dispatcher.forward(request, response);
	}
}

public void S_602_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	DBConn db = new DBConn();
	String message ="";

	String id = StringUtil.notNull(request.getParameter("id"));
	try {
		if(db.createConn()){
			conn = db.getConnection();
			String sql  ="select * from video_info where id='"+id+"'";
			stmt= conn.createStatement();
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
				request.setAttribute("ht_video_info", as);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("602_1.jsp");
		dispatcher.forward(request, response);
	}
}

public void S_603(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("603.jsp");
		dispatcher.forward(request, response);
	}
}
public void S_604(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("604.jsp");
		dispatcher.forward(request, response);
	}
}
}
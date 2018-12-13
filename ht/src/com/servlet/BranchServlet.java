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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.Branch;
import com.pojo.News;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;
import com.weixin.pojo.WeixinUserInfo;

public class BranchServlet extends HttpServlet {

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
		}else if(method.equals("B_301")){
			B_301(request,response);
		}else if(method.equals("B_302")){
			B_302(request,response);
		}else if(method.equals("B_303")){
			B_303(request,response);
		}else if(method.equals("B_304")){
			B_304(request,response);
		}else if(method.equals("B_305")){
			B_305(request,response);
		}else if(method.equals("B_305_1")){
			B_305_1(request,response);
		}else if(method.equals("admin_branch_list")){
			admin_branch_list(request,response);
		}else if(method.equals("admin_branch_add")){
			admin_branch_add(request,response);
		}else if(method.equals("admin_branch_save")){
			admin_branch_save(request,response);
		}else if(method.equals("isExit")){
			isExit(request,response);
		}else if(method.equals("isExit_User")){
			isExit_User(request,response);
		}
}
	
public void B_301(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("301.jsp");
			dispatcher.forward(request, response);
		}
	}

public void B_302(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("302.jsp");
		dispatcher.forward(request, response);
	}
}
	
public void B_303(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("303.jsp");
		dispatcher.forward(request, response);
	}
}

public void B_304(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("304.jsp");
		dispatcher.forward(request, response);
	}
}

@SuppressWarnings({ "unchecked", "rawtypes" })
public void B_305(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
			String sql  ="select * from news  where sort='2' and state='1' order by entryTime desc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql);
			while(rs.next()){
				News as = new News();
				as.setId(rs.getInt("id"));
				as.setPublisher(rs.getString("publisher"));
				as.setTitle(rs.getString("title"));
				as.setContents(rs.getString("contents"));
				as.setSort(rs.getInt("sort"));
				as.setEndTime(rs.getTimestamp("endTime"));
				as.setEntryTime(rs.getTimestamp("entryTime"));
				as.setState(rs.getInt("state"));
				as.setBrowseNum(rs.getInt("browse_num"));
				as.setPublisher(rs.getString("publisher"));
				as.setSource(rs.getString("source"));
				as.setImageUrl(rs.getString("image_url"));
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
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("305.jsp");
			dispatcher.forward(request, response);
	}
}

public void B_305_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	DBConn db = new DBConn();
	String message ="";
	String id = StringUtil.notNull(request.getParameter("id"));
	try {
		if(db.createConn()){
			String sql  ="select * from news where id='"+id+"'";
					
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql);
			if(rs.next()){
				News as = new News();
				as.setId(rs.getInt("id"));
				as.setPublisher(rs.getString("publisher"));
				as.setTitle(rs.getString("title"));
				as.setContents(rs.getString("contents"));
				as.setSort(rs.getInt("sort"));
				as.setEndTime(rs.getTimestamp("endTime"));
				as.setEntryTime(rs.getTimestamp("entryTime"));
				as.setState(rs.getInt("state"));
				as.setBrowseNum(rs.getInt("browse_num"));
				as.setPublisher(rs.getString("publisher"));
				as.setSource(rs.getString("source"));
				as.setImageUrl(rs.getString("image_url"));
				request.setAttribute("news", as);
			}else{
				message= "未查询到需要修改的新闻的相关信息，请重试！";
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("305_1.jsp");
		dispatcher.forward(request, response);
	}
}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_branch_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String branchId = StringUtil.notNull(request.getParameter("branchId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][5].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from branch where user_id like'%"+userId+"%' and entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and state='1' and (branch_id like'%"+branchId+"%' or branch_name like'%"+branchId+"%') order by entryTime desc";
						
					}else{
							sql  ="select * from branch where user_id like'%"+userId+"%' and  state='1' and (branch_id like'%"+branchId+"%' or branch_name like'%"+branchId+"%') order by entryTime desc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					
					as.setAddress(rs.getString("address"));
					as.setArea(rs.getString("area"));
					as.setBranchId(rs.getString("branch_id"));
					as.setBranchName(rs.getString("branch_name"));
					as.setCity(rs.getString("city"));
					as.setImageUrl(rs.getString("image_url"));
					as.setPassword(rs.getString("password"));
					as.setPassword2(rs.getString("password2"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setImageUrl(rs.getString("image_url"));
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
				request.setAttribute("userId",userId);
				request.setAttribute("branch",branchId);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_branch_user_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String branchId = StringUtil.notNull(request.getParameter("branchId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][5].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from branch where branch_id like'%"+userId+"%' and entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and state='1' and (branch_id like'%"+branchId+"%' or branch_name like'%"+branchId+"%') order by entryTime desc";
						
					}else{
							sql  ="select * from branch where user_id like'%"+userId+"%' and  state='1' and (branch_id like'%"+branchId+"%' or branch_name like'%"+branchId+"%') order by entryTime desc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					
					as.setAddress(rs.getString("address"));
					as.setArea(rs.getString("area"));
					as.setBranchId(rs.getString("branch_id"));
					as.setBranchName(rs.getString("branch_name"));
					as.setCity(rs.getString("city"));
					as.setImageUrl(rs.getString("image_url"));
					as.setPassword(rs.getString("password"));
					as.setPassword2(rs.getString("password2"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setImageUrl(rs.getString("image_url"));
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
				request.setAttribute("userId",userId);
				request.setAttribute("branch",branchId);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void isExit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String branchId = StringUtil.notNull(request.getParameter("branchId"));
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		try {
			Map<String, Object> object = new HashMap<String, Object>();
			if (admin!= null ) {
				if (db.createConn()) {
					if (StringUtil.notNull(branchId).equals("")) {
						object.put("tag", 0);
					} else {
						String sql = "";
						sql = "select * from branch where branch_id ='" + branchId
								+ "' order by id asc";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							object.put("tag", 1);
						} else{
							object.put("tag", 2);
						}
					}
				} else {
					object.put("tag", 3);
				}
			} else {
				object.put("tag",3);
			}
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	public void isExit_User(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String userId = StringUtil.notNull(request.getParameter("userId"));
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		Map<String, Object> object = new HashMap<String, Object>();
		try {
			
			if (admin!= null ) {
				if (db.createConn()) {
					if (StringUtil.notNull(userId).equals("")) {
						object.put("tag", 0);
						object.put("nickName", "");
					} else {
						String  sql1 = "select * from weixin_userinfo where user_id ='" + userId
								+ "' order by id asc";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql1);
						if(rs.next()){
							String nickName = rs.getString("nick_name");
							String  sql2 = "select * from branch where user_id ='" + userId
									+ "' order by id asc";
							stmt = db.getStmtread();
							rs = stmt.executeQuery(sql2);
								if (rs.next()) {
									object.put("tag", 1);
									object.put("nickName", nickName);
								} else{
									object.put("tag", 2);
									object.put("nickName", nickName);
								}
						}else{
							object.put("tag", 3);
							object.put("nickName", "");
						}
						
					}
				} else {
					object.put("tag", 4);
				}
			} else {
				object.put("tag",5);
			}
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	public void admin_branch_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
		if(admin!=null){
			String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[1][6].equals("1")||admin.getState().equals("1")){
				if(db.createConn()){
					conn =db.getConnection();
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					String userId = StringUtil.notNull(request.getParameter("userId"));
					System.out.println("userId:"+userId);
					String sql = "select * from weixin_userinfo where user_id='"+userId+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						System.out.println("123");
						WeixinUserInfo user =new WeixinUserInfo();
						user.setId(rs.getInt("id"));
						user.setUserId(rs.getString("user_id"));
						user.setNickName(rs.getString("nick_name"));
						request.setAttribute("weixin_user", user);
					}else{
						message="未查询到相应的会员信息！";
					}
				}else{
					message="数据库连接失败！";
					
				}
		}else{
			message="您没有该操作权限，如有需要请与系统管理员联系！";
			
		}
		}else{
			message="管理员用户未登陆，请重新登陆！";
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，请重新录入！";
			e.printStackTrace();
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_add_2.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void admin_branch_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
			Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			String token_old = (String)request.getSession().getAttribute("token");
			String token = StringUtil.notNull(request.getParameter("token"));
			int token_new = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token_new));
			DBConn db = new DBConn();
			try {
				if(admin!=null){
					if(StringUtil.notNull(token_old).equals(token)){
						String[][] rankstr = StringUtil.getRankStr(admin.getRank());
						if(rankstr[1][5].equals("1")||admin.getState().equals("1")){
						String userId= StringUtil.notNull(request.getParameter("userId"));
						String branchId = StringUtil.notNull(request.getParameter("branchId"));
						String branchName = StringUtil.notNull(request.getParameter("branchName"));
						String password = StringUtil.notNull(request.getParameter("password"));
						String province = StringUtil.notNull(request.getParameter("province"));
						String city = StringUtil.notNull(request.getParameter("city"));
						String area = StringUtil.notNull(request.getParameter("area"));
						String address = StringUtil.notNull(request.getParameter("address"));
				if(db.createConn()){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 String  sql = "select * from weixin_userinfo where user_id='"+userId+"'";
					 stmt= conn.createStatement();
					 rs= stmt.executeQuery(sql);
					 if(rs.next()){
						 int uid =  rs.getInt("id");
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
							 String sql1 = "insert into branch(uid,user_id,branch_id,branch_name,password,password2,province,city,area,address,state,entryTime,endTime) values('"+uid+"','"+userId+"','"+
									 branchId+"','"+branchName+"','"+MD5.GetMD5Code(password)+"','"+MD5.GetMD5Code(password)+"','"+province+"','"+city+"','"+area+"','"+address+"','1','"+date+"','"+date+"')";
							 stmt= conn.createStatement();
							 stmt.executeUpdate(sql1);
							 String sql2 = "update weixin_userinfo set branch_id='"+branchId+"',branch_name='"+branchName+"' where user_id='"+userId+"'";
							 stmt= conn.createStatement();
							 stmt.executeUpdate(sql2);
							 request.setAttribute("message", "协会信息保存成功，请在协会管理中查看协会信息！");
					 }else {
							request.setAttribute("message", "会员信息获取失败！");
							conn.rollback();
						}
					 conn.commit();
					 conn.setAutoCommit(autoCommit);
				}else {
					request.setAttribute("message", "数据库连接已断开！");
				}
					}else{
						request.setAttribute("message", "你没有权限进行该操作！");
					}
					}else{
				request.setAttribute("message", "请勿重复提交数据，请在协会管理中查看协会信息是否保存成功！");
				}
				}else{
					request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					request.setAttribute("message", "数据保存有误，请重新录入！");
				}
				e.printStackTrace();
				request.setAttribute("message", "数据保存有误，请重新录入！");
			}finally{
				db.close();
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
}

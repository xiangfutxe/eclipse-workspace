package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.pojo.User;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class UserServlet extends HttpServlet {

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
		}else if(method.equals("admin_user_list")){
			admin_user_list(request,response);
		}else if(method.equals("admin_detail")){
			admin_detail(request,response);
		}else if(method.equals("user_add_branch")){
			user_add_branch(request,response);
		}else if(method.equals("user_add")){
			user_add(request,response);
		}else if(method.equals("user_save")){
			user_save(request,response);
		}else if (method.equals("userAjax")) {
			userAjax(request, response);

		}else if (method.equals("login")) {
			login(request, response);

		}else if (method.equals("login_out")) {
			login_out(request, response);

		}else if (method.equals("U_701")) {
			U_701(request, response);

		}else if (method.equals("U_702")) {
			U_702(request, response);

		}else if (method.equals("U_704")) {
			U_704(request, response);

		}
		
}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_user_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userName = StringUtil.notNull(request.getParameter("userName"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[0][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from weixin_userinfo where entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and state='1' and (user_id like'%"+userName+"%' or user_name like'%"+userName+"%') order by id desc";
						
					}else{
							sql  ="select * from weixin_userinfo where state='1' and (user_id like'%"+userName+"%' or user_name like'%"+userName+"%') order by id desc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					User as = new User();
					as.setId(rs.getInt("id"));
					as.setBranchId(rs.getString("branch_id"));
					as.setBranchName(rs.getString("branch_name"));
					as.setNickName(rs.getString("nick_name"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setIntegral(rs.getDouble("integral"));
					as.setUserId(rs.getString("user_id"));
					as.setUserName(rs.getString("user_name"));
					as.setTel(rs.getString("tel"));
					as.setPassword(rs.getString("password"));
					as.setRmoney(rs.getDouble("rmoney"));
					as.setSex(rs.getString("sex"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					as.setState(rs.getInt("state"));
					as.setImageUrl(rs.getString("qr_img"));
					as.setRankJoin(rs.getInt("rank_manage"));
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
				request.setAttribute("userName",userName);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from user_info where user_id='"+userId+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					User as = new User();
					as.setId(rs.getInt("id"));
					as.setAddress(rs.getString("address"));
					as.setBirth(rs.getTimestamp("birth"));
					as.setArea(rs.getString("area"));
					as.setBranchId(rs.getString("branch_id"));
					as.setBranchName(rs.getString("branch_name"));
					as.setCity(rs.getString("city"));
					as.setDocumentType(rs.getString("documentType"));
					as.setEmail(rs.getString("email"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setIntegral(rs.getDouble("integral"));
					as.setNumId(rs.getString("numId"));
					as.setUserId(rs.getString("user_id"));
					as.setUserName(rs.getString("user_name"));
					as.setTel(rs.getString("tel"));
					as.setPassword(rs.getString("password"));
					as.setPassword2(rs.getString("password2"));
					as.setRmoney(rs.getDouble("rmoney"));
					as.setSex(rs.getString("sex"));
					as.setWeixin(rs.getString("weixin"));
					as.setQq(rs.getString("qq"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setImageUrl(rs.getString("image_url"));
					request.setAttribute("user_info",as);
				}else {
					message= "未查询到相应会员的信息！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void user_add_branch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		try {
		
			if(db.createConn()){
				conn = db.getConnection();
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 10;
				String sql ="select * from branch where state='1' order by entryTime asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setBranchId(rs.getString("branch_id"));
					as.setBranchName(rs.getString("branch_name"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_add_branch.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void user_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		String message ="";
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
		
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql ="select * from branch where branch_id='"+id+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setBranchId(rs.getString("branch_id"));
					as.setBranchName(rs.getString("branch_name"));
					as.setEndTime(rs.getTimestamp("endTime"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setState(rs.getInt("state"));
					as.setImageUrl(rs.getString("image_url"));
					request.setAttribute("branch", as);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_add.jsp");
			dispatcher.forward(request, response);
		}
	}
	

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void user_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		String message ="";
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(StringUtil.notNull(token_old).equals(token)){
			if(db.createConn()){
				conn = db.getConnection();
				String branchId = StringUtil.notNull(request.getParameter("branchId"));
				String branchName = StringUtil.notNull(request.getParameter("branchName"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String userName = StringUtil.notNull(request.getParameter("userName"));
				String sex = StringUtil.notNull(request.getParameter("sex"));
				String password = StringUtil.notNull(request.getParameter("password"));
				String documentType = StringUtil.notNull(request.getParameter("documentType"));
				String numId = StringUtil.notNull(request.getParameter("numId"));
				String tel = StringUtil.notNull(request.getParameter("tel"));
				String phone = StringUtil.notNull(request.getParameter("phone"));
				String email = StringUtil.notNull(request.getParameter("email"));
				String qq = StringUtil.notNull(request.getParameter("qq"));
				String weixin = StringUtil.notNull(request.getParameter("weixin"));
				String province = StringUtil.notNull(request.getParameter("province"));
				String city = StringUtil.notNull(request.getParameter("city"));
				String area = StringUtil.notNull(request.getParameter("area"));
				String address = StringUtil.notNull(request.getParameter("address"));
				String sql ="select * from user_info where user_id='"+userId+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(!rs.next()){
					 Timestamp date = new Timestamp(new Date().getTime());
					String in = "insert into user_info(user_id,user_name,password,password2,birth,sex,branch_id,branch_name,"
							+"documentType,numId,tel,phone,email,qq,weixin,province,city,area,address,state,entryTime,endTime) values('"+
							userId+"','"+userName+"','"+MD5.GetMD5Code(password)+"','"+MD5.GetMD5Code(password)+"','"+date+"','"+sex
							+"','"+branchId+"','"+branchName+"','"+documentType+"','"+numId+"','"+tel+"','"+phone+"','"+email+"','"+qq+"','"+weixin+"','"+province
							+"','"+city+"','"+area+"','"+address+"','1','"+date+"','"+date+"');";
					stmt.executeUpdate(in);
					message= "会员信息保存成功，请点击会员登录！";
				}else {
					message= "会员昵称已被占用，请重新注册！";
				}
			}else {
				message= "数据库连接已断开！";
			}
			}else {
				message= "请勿重复提交数据，请尝试登录查看是否注册成功！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void userAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String userId = request.getParameter("userId");
		try {
			Map<String, Object> object = new HashMap<String, Object>();
			if (db.createConn()) {
				if (StringUtil.notNull(userId).equals("")) {
					object.put("userName", "");
				} else {
					String sql = "select * from weixin_userinfo where user_id ='" + userId
							+ "' order by id asc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						object.put("userName", rs.getString("nick_name"));
						object.put("state", rs.getInt("state"));
					} else {
						object.put("userName", "");
					}
				}
			} else {
				object.put("userName", "");
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
	
public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

@SuppressWarnings({ "rawtypes", "unchecked" })
public void U_701(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	String message ="";

	Collection coll = new ArrayList();
	List result = new ArrayList();
	try {
	
		if(db.createConn()){
			String pageNoStr = request.getParameter("pageNoStr");
			String pageSizeStr = request.getParameter("pageSizeStr");
			int pageNo = 1;
			int pageSize = 10;
			String sql ="select * from branch order by id asc";
			stmt = db.getStmtread();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Branch bh = new Branch();
				bh.setId(rs.getInt("id"));
				bh.setBranchId(rs.getString("branch_id"));
				bh.setBranchName(rs.getString("branch_name"));
				result.add(bh);
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
				Pager pager = new Pager(pageSize, pageNo, result.size(),
						coll);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("701.jsp");
		dispatcher.forward(request, response);
	}
}

public void U_702(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("702.jsp");
		dispatcher.forward(request, response);
	}
}

public void U_704(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	String userId = StringUtil.notNull(request.getParameter("userId"));
	String password = StringUtil.notNull(request.getParameter("password"));
	String message ="";
	try {
		if (userId.equals("") || password.equals("")) {
			message= "用户名或密码不能为空！";
		} else {
			if (db.createConn()) {
				conn = db.getConnection();
				//InetAddress addr = InetAddress.getLocalHost();
				//String ip=addr.getHostAddress().toString();
				String sql = "select * from user_info where user_id ='"
						+ userId + "' and state>'0' and password='"
						+ MD5.GetMD5Code(password) + "'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserId(rs.getString("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
					user.setState(rs.getInt("state"));
					request.getSession().setAttribute("ht_user", user);
					Timestamp date1 = new Timestamp(new Date().getTime());
					java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					//cs.insetAdminLog(conn, admin.getAdminName(), "管理员登陆系统，访问IP为"+ip, date);
				} else {
					message=  "用户名不存在或密码有误！";
				}
			} else {
				message= "数据库连接已断开！";
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
		if(message.equals("")){
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", message);
			System.out.println(message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}

public void login_out(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.getSession().removeAttribute("ht_user");
	response.sendRedirect("login.jsp");
}
}

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
import com.pojo.Center;
import com.pojo.Order;
import com.pojo.OrderDetail;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class CenterServlet extends HttpServlet {

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
			
		}else if(method.equals("admin_add")){
		admin_add(request,response);
		
		}else if(method.equals("admin_save")){
		try {
			admin_save(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}else if(method.equals("admin_detail")){
		admin_detail(request,response);
		
		}else if(method.equals("centerIdAjax")){
		centerIdAjax(request,response);
		
	}else if(method.equals("centerIdAjaxForUser")){
		centerIdAjaxForUser(request,response);
		
	}else if(method.equals("admin_edit")){
		admin_edit(request,response);
		
	}else if(method.equals("admin_edit_save")){
		admin_edit_save(request,response);
		
	}else if(method.equals("admin_up")){
		admin_up(request,response);
		
	}else if(method.equals("admin_up_save")){
		admin_up_save(request,response);
		
	}else if(method.equals("admin_close")){
		admin_close(request,response);
		
	}else if(method.equals("admin_open")){
		admin_open(request,response);
		
	}else if(method.equals("login")){
		login(request,response);
		
	}else if(method.equals("logout")){
		logout(request,response);
		
	}else if(method.equals("update_pass")){
		try {
			update_pass(request,response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}else if(method.equals("order_list")){
		order_list(request,response);
		
	}else if(method.equals("order_confirm_list")){
		order_confirm_list(request,response);
		
	}else if(method.equals("order_delivery_list")){
		order_delivery_list(request,response);
		
	}else if(method.equals("order_review_list")){
		order_review_list(request,response);
		
	}else if(method.equals("order_detail_confirm")){
		order_detail_confirm(request,response);
		
	}else if(method.equals("order_detail_delivery")){
		order_detail_delivery(request,response);
		
	}else if(method.equals("order_detail_review")){
		order_detail_review(request,response);
		
	}else if(method.equals("order_detail")){
		order_detail(request,response);
		
	}

}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConn db = new DBConn();
		String centerId = request.getParameter("centerId");
		String password = request.getParameter("password");
		String yzm = request.getParameter("yzm");
		String rand = (String) request.getSession().getAttribute("rand");
		String message ="";
		try {
			if (centerId == null || password == null) {
				message= "店铺编号或密码不能为空！";
			} else if (rand == null) {
				message= "验证码有误！";
			} else if (!rand.equals(yzm)) {
				message= "验证码有误！";
			}else {
				if (db.createConn()) {
					conn = db.getConnection();
					String sql = "select * from center where center_id ='"
							+ centerId + "' and state='2' and password='"
							+ MD5.GetMD5Code(password) + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						Center center = new Center();
						center.setId(rs.getInt("id"));
						center.setCenterId(rs.getString("center_id"));
						center.setCenterName(rs.getString("center_name"));
						center.setUserId(rs.getString("user_id"));
						center.setUserName(rs.getString("user_name"));
						center.setProvince(rs.getString("province"));
						center.setArea(rs.getString("area"));
						center.setCity(rs.getString("city"));
						center.setState(rs.getInt("state"));
						request.getSession().setAttribute("sys_center", center);
					} else {
						message="店铺不存在或密码有误！";
					}
				} else {
					message="数据库连接已断开！";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
				e.printStackTrace();
		} finally {
			db.close();
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}

	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("sys_center");
		response.sendRedirect("login_out.jsp");
	}
	
	public void update_pass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		DBConn db = new DBConn();
		Center center = (Center)request.getSession().getAttribute("sys_center");
		String mpass = StringUtil.notNull(request.getParameter("mpass"));
		String newpass = StringUtil.notNull(request.getParameter("newpass"));
		
		String message ="";
		try {
			if (center!= null) {
				if(!(mpass.equals("")||newpass.equals(""))){
					if(!mpass.equals("newpass")){
						if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					//Timestamp date = new Timestamp(new Date().getTime());
					String sql = "select * from center where center_id ='"
							+ center.getCenterId() + "' and state='2' and password='"
							+ MD5.GetMD5Code(mpass) + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						String sqlu = "update center set password='"+MD5.GetMD5Code(newpass)+"' where id='"+rs.getInt("id")+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message="密码更新成功，请退出系统重新登陆！";
					} else {
						message="店铺信息不存在或密码有误！";
					}
					 conn.commit();
					 conn.setAutoCommit(autoCommit);
					} else {
						message="数据库连接失败，请重试！";
					}
						} else {
							message="原密码与新密码不能相同！";
						}
				} else {
					message="原密码或新密码不能为空！";
				}
			} else {
				message="店铺未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
				e.printStackTrace();
		} finally {
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("password_message.jsp");
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
		String state = StringUtil.notNull(request.getParameter("state"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select * from center  where state  like'%"+state+"%' and (user_id like'%"+userId+"%' or user_name like'%"+userId+"%') order by state asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Center as = new Center();
					as.setId(rs.getInt("id"));
					as.setCenterId(rs.getString("center_id"));
					as.setUserId(rs.getString("user_id"));
					as.setUserName(rs.getString("user_name"));
					as.setAddress(rs.getString("address"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					as.setCenterName(rs.getString("center_name"));
					as.setPhone(rs.getString("phone"));
					as.setState(rs.getInt("state"));
					as.setType(rs.getInt("type"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setIsEmpty(rs.getInt("license"));
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
				request.setAttribute("state", state);
				request.setAttribute("userId", userId);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message=  "管理员权限不足，请重新登陆！";
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
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_list.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	

	
	public void admin_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
			String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "你没有权限进行此操作！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
		}}
			else{
				request.setAttribute("message", "用户未登陆，请重新登陆！");
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
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
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
				if(StringUtil.notNull(token_old).equals(token)){
					String centerId = StringUtil.notNull(request.getParameter("centerId"));
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String userName = StringUtil.notNull(request.getParameter("userName"));
					String centerName = StringUtil.notNull(request.getParameter("centerName"));
					String password = StringUtil.notNull(request.getParameter("password"));
					String adr = StringUtil.notNull(request.getParameter("address"));
					String phone = StringUtil.notNull(request.getParameter("phone"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String province = StringUtil.notNull(request.getParameter("province"));
					String city = StringUtil.notNull(request.getParameter("city"));
					String area = StringUtil.notNull(request.getParameter("area"));
					String license = StringUtil.notNull(request.getParameter("license"));
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						 String sql = "select * from center where center_id='"+centerId+"'";
						 stmt = conn.createStatement();
						 rs =  stmt.executeQuery(sql);
						 if(!rs.next()){
							 String sqli1 = "insert into center(center_id,center_name,password,password2,user_id,user_name,address,phone,type,province,city,area,license,state,entry_time) values('"+centerId
									 +"','"+centerName+"','"+MD5.GetMD5Code(password)+"','"+MD5.GetMD5Code(password)+"','"+userName+"','"+userId+"','"+adr+"','"+phone+"','"+type+"','"+province+"','"+city+"','"+area+"','"+license+"','2','"+date+"')";
							 stmt = conn.createStatement();
							stmt.executeUpdate(sqli1);
							
							request.setAttribute("message", centerName+"("+centerId+")"+"服务店注册成功成功！");
						
					 }else {
						 request.setAttribute("message", "服务店编号被占用，请重新录入");
						 conn.rollback();
						 request.setAttribute("tag", "2");
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
				request.setAttribute("tag", "2");
			}
				}
			else{
			request.setAttribute("message", "请勿重复提交数据，请在服务店查看是否操作成功！");
			request.setAttribute("tag", "1");
			}
				}else{
					request.setAttribute("message", "管理员权限不足！");
					request.setAttribute("tag", "2");
				}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
				request.setAttribute("tag", "2");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
			request.setAttribute("tag", "1");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	public void centerIdAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String centerId = StringUtil.notNull(request.getParameter("centerId"));
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		try {
			Map<String, Object> object = new HashMap<String, Object>();
			if (admin != null) {
				if (db.createConn()) {
					if (StringUtil.notNull(centerId).equals("")) {
						object.put("tag", "2");
					} else {
						String sql = "";
						sql = "select * from center where center_id ='" + centerId
								+ "' order by id asc";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
								object.put("tag", "1");
						} else object.put("tag", "0");
					} 
				}else {
					object.put("tag", "3");
				}
			}else {
				object.put("tag", "4");
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
	
	public void centerIdAjaxForUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String centerId = StringUtil.notNull(request.getParameter("centerId"));
		try {
			Map<String, Object> object = new HashMap<String, Object>();
				if (db.createConn()) {
					if (StringUtil.notNull(centerId).equals("")) {
						object.put("centerName", "");
						object.put("tag", "2");
					} else {
						String sql = "";
						sql = "select * from center where center_id ='" + centerId
								+ "' and state='2' order by id asc";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
								object.put("centerName", rs.getString("center_name"));
						} else object.put("centerName", "");
					} 
				}else {
					object.put("centerName", "");
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
	
	
	
	public void admin_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql = "select * from center where id='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Center center = new Center();
					center.setId(rs.getInt("id"));
					center.setCenterId(rs.getString("center_id"));
					center.setCenterName(rs.getString("center_name"));
					center.setUserId(rs.getString("user_id"));
					center.setUserName(rs.getString("user_name"));
					center.setProvince(rs.getString("province"));
					center.setType(rs.getInt("type"));
					center.setCity(rs.getString("city"));
					center.setArea(rs.getString("area"));
					center.setAddress(rs.getString("address"));	
					center.setLicense(rs.getString("license"));
					center.setPhone(rs.getString("phone"));
					center.setRefereeName(rs.getString("referee_name"));
					center.setRefereeId(rs.getString("referee_id"));
					request.setAttribute("center", center);
				}else{
					request.setAttribute("tag", "1");
					message= "没有找到需要升级的相应店铺信息！";
				}
				}else {
					request.setAttribute("tag", "1");
					message= "你没有权限进行改操作！";
				}	
			}else {
				request.setAttribute("tag", "1");
				message= "数据库连接已断开！";
			}

				}else{
					request.setAttribute("tag", "1");
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("tag", "1");
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_edit_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				String adr = StringUtil.notNull(request.getParameter("address"));
				String phone = StringUtil.notNull(request.getParameter("phone"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String userName = StringUtil.notNull(request.getParameter("userName"));
				String centerName = StringUtil.notNull(request.getParameter("centerName"));
				String province = StringUtil.notNull(request.getParameter("province"));
				String city = StringUtil.notNull(request.getParameter("city"));
				String area = StringUtil.notNull(request.getParameter("area"));
				String license = StringUtil.notNull(request.getParameter("license"));
				String refereeId = StringUtil.notNull(request.getParameter("refereeId"));
				String refereeName = StringUtil.notNull(request.getParameter("refereeName"));
				String sql = "select * from center where id='"+id+"' for update";
				Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String centerId = rs.getString("center_id");
					String sqlu= "update center set referee_name='"+refereeName+"',referee_id='"+refereeId+"', user_name='"+userName+"',user_id='"+userId+"',license='"+license+"',center_name='"+centerName+"',address='"+adr+"',phone='"+phone+"',province='"+province+"',city='"+city+"',area='"+area+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= centerId+"服务店信息修改成功，请在服务店查看！";
					request.setAttribute("tag", "1");
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					message= "未查询到需要修改的服务店信息！";
					request.setAttribute("tag", "1");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
				request.setAttribute("tag", "1");
			}

				}else{
					message=  "请勿重复提交数据，请在服务店查看是否操作成功！";
					request.setAttribute("tag", "1");
					}
				}else{
					message=  "管理员未登陆，请重新登陆！";
					request.setAttribute("tag", "1");
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
			request.setAttribute("tag", "1");
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_up(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][3].equals("1")||admin.getState().equals("1")){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql = "select * from center where id='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Center center = new Center();
					center.setId(rs.getInt("id"));
					center.setCenterId(rs.getString("center_id"));
					center.setCenterName(rs.getString("center_name"));
					center.setUserId(rs.getString("user_id"));
					center.setUserName(rs.getString("user_name"));
					center.setProvince(rs.getString("province"));
					center.setType(rs.getInt("type"));
					center.setCity(rs.getString("city"));
					center.setArea(rs.getString("area"));
					center.setAddress(rs.getString("address"));
					center.setPhone(rs.getString("phone"));
					center.setState(rs.getInt("state"));
					center.setLicense(rs.getString("license"));
					center.setRefereeId(rs.getString("referee_id"));
					center.setRefereeName(rs.getString("referee_name"));
					if(center.getState()==2){
					request.setAttribute("center", center);
					}else{
						request.setAttribute("tag", "1");
						message= "店铺当前状态不能进行修改类型操作！";
					}
					
				}else{
					request.setAttribute("tag", "1");
					message= "没有找到需要升级的相应店铺信息！";
				}
				}else {
					request.setAttribute("tag", "1");
					message= "你没有权限进行改操作！";
				}	
			}else {
				request.setAttribute("tag", "1");
				message= "数据库连接已断开！";
			}

				}else{
					request.setAttribute("tag", "1");
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("tag", "1");
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_up.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_up_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
			
				Timestamp date = new Timestamp(new Date().getTime());
				String id = StringUtil.notNull(request.getParameter("id"));
				
				String typeNew = StringUtil.notNull(request.getParameter("typeNew"));
				String sql = "select * from center where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			
				if(rs.next()){
					String centerId = rs.getString("center_id");
					int type = rs.getInt("type");
					if(!typeNew.equals(String.valueOf(type))){
					 String sqli1 = "update center set type='"+typeNew+"' where center_id='"+centerId+"'";
					
						stmt = conn.createStatement();
					stmt.executeUpdate(sqli1);
					
					message= centerId+"服务店类型变更成功，请在服务店查看！";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				 
				
				}else{
					message= "服务店当前类型与变更类型相同，请核实！";
					request.setAttribute("tag", "1");
				}
					
				}else{
					message= "未查询到需要修改的服务店信息！";
					request.setAttribute("tag", "1");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
				request.setAttribute("tag", "1");
			}

				}else{
					message=  "请勿重复提交数据，请在服务店查看是否操作成功！";
					request.setAttribute("tag", "1");
					}
				}else{
					message=  "管理员未登陆，请重新登陆！";
					request.setAttribute("tag", "1");
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
			request.setAttribute("tag", "1");
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
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
				conn = db.getConnection();
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql = "select * from center where id='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Center center = new Center();
					center.setId(rs.getInt("id"));
					center.setCenterId(rs.getString("center_id"));
					center.setCenterName(rs.getString("center_name"));
					center.setUserId(rs.getString("user_id"));
					center.setUserName(rs.getString("user_name"));
					center.setProvince(rs.getString("province"));
					center.setType(rs.getInt("type"));
					center.setCity(rs.getString("city"));
					center.setArea(rs.getString("area"));
					center.setAddress(rs.getString("address"));
					center.setPhone(rs.getString("phone"));
					center.setState(rs.getInt("state"));
					center.setLicense(rs.getString("license"));
					center.setRefereeId(rs.getString("referee_id"));
					center.setRefereeName(rs.getString("referee_name"));
					request.setAttribute("center",center);
				}else{
					request.setAttribute("tag", "1");
					message= "没有找到需要升级的相应店铺信息！";
				}
				}else {
					request.setAttribute("tag", "1");
					message= "你没有权限进行改操作！";
				}	
			}else {
				request.setAttribute("tag", "1");
				message= "数据库连接已断开！";
			}

				}else{
					request.setAttribute("tag", "1");
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("tag", "1");
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void admin_close(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
					if(db.createConn()){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				Timestamp date = new Timestamp(new Date().getTime());
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql = "select * from center where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String centerId = rs.getString("centerId");
					if(rs.getInt("state")!=0){
					 String sqli1 = "update center set state='0' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli1);
					message= centerId+"服务店关闭成功，请在服务店查看！";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
			
					}else{
						message= "服务店当前状态不能进行关闭操作！";
						request.setAttribute("tag", "1");
					}
				}else{
					message= "未查询到需要修改的服务店信息！";
					request.setAttribute("tag", "1");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
				request.setAttribute("tag", "1");
			}

				}else{
					message=  "管理员未登陆，请重新登陆！";
					request.setAttribute("tag", "1");
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
			request.setAttribute("tag", "1");
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_open(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
					if(db.createConn()){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				Timestamp date = new Timestamp(new Date().getTime());
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String sql = "select * from center where userId='"+userId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double price=0;
				double pv =0;
				User user= new User();
				if(rs.next()){
					String centerId = rs.getString("centerId");
					String password = rs.getString("password");
					String centerName = rs.getString("centerName");
					String adr = rs.getString("address");
					String phone = rs.getString("phone");
					String province = rs.getString("province");
					String city = rs.getString("city");
					String area = rs.getString("area");
					int type=rs.getInt("type");
					int state = rs.getInt("state");
					if(state==0){
					String sql1 = "select * from users where userId='"+userId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						user.setId(rs.getInt("id"));
						user.setUserId(rs.getString("userId"));
						user.setUserName(rs.getString("userName"));
						List<String> slist = new ArrayList<String>();
					 String sqli = "insert into centerDetail(uId,userId,userName,centerId,centerName,password,address,phone,type,province,city,area,amount,amount_1,is_empty,state,applyTime,entryTime,adminName,reviewTime) values('"+user.getId()
							 +"','"+user.getUserId()+"','"+user.getUserName()+"','"+centerId+"','"+centerName+"','"+password+"','"+adr+"','"+phone+"','"+type+"','"+province+"','"+city+"','"+area+"','"+price+"','"+pv+"','0','2','"+date+"','"+date+"','"+admin.getAdminName()+"','"+date+"')";
					slist.add(sqli);
					 String sqli1 = "update center set state='2' where userId='"+userId+"'";
					 slist.add(sqli1);
					 String sqli2 = "update users set centerId='2',centerType='"+type+"' where userId='"+userId+"'";
					 slist.add(sqli2);
					 if(slist.size()>0){
						stmt = conn.createStatement();
						for(int i=0;i<slist.size();i++){
							stmt.addBatch(slist.get(i));
							 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
								 stmt.executeBatch();
								 stmt.close();
								 stmt = conn.createStatement();
							 }
						}
					 }
					message= centerId+"服务店重新开通成功，请在服务店查看！";
					request.setAttribute("tag", "1");
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					
					}else{
						message= "未查询到服务店所对应的会员信息！";
						request.setAttribute("tag", "1");
					}
					}else{
						message= "服务店当前状态不能进行关闭操作！";
						request.setAttribute("tag", "1");
					}
				}else{
					message= "未查询到需要修改的服务店信息！";
					request.setAttribute("tag", "1");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
				request.setAttribute("tag", "1");
			}

				}else{
					message=  "管理员未登陆，请重新登陆！";
					request.setAttribute("tag", "1");
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
			request.setAttribute("tag", "1");
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(center!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where tag='2' and  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='1' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
					}else{
								sql ="select * from orders where tag='2' and state='1' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setUserByCenterId(rs.getString("userByCenterId"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					result.add(orders);
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
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "店铺未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_confirm_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(center!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where tag='2' and  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='1' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
					}else{
								sql ="select * from orders where tag='2' and state='1' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setUserByCenterId(rs.getString("userByCenterId"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					result.add(orders);
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
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "店铺未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_delivery_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(center!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where tag='2' and  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='2' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
					}else{
								sql ="select * from orders where tag='2' and state='2' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setUserByCenterId(rs.getString("userByCenterId"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					result.add(orders);
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
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "店铺未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_review_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(center!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where tag='2' and  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='2' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
					}else{
								sql ="select * from orders where tag='2' and state='2' and orderType='2' and userId like'%"+userId+"%' and userByCenterId ='"+center.getCenterId()+"' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setUserByCenterId(rs.getString("userByCenterId"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					result.add(orders);
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
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message=  "店铺未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_detail_confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(center!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setOrderId(orderId);
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==1){
								String pId = rs.getString("productId");
								int num = rs.getInt("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getInt("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getInt("num"));
									od.setNum(num*rs1.getInt("num"));
									coll.add(od);
								}
								if(rs1!=null)
								rs1.close();
								if(stmt1!=null)
								stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_confirm.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_detail_delivery(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(center!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setOrderId(orderId);
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==2){
								String pId = rs.getString("productId");
								int num = rs.getInt("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getInt("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getInt("num"));
									od.setNum(num*rs1.getInt("num"));
									coll.add(od);
								}
								if(rs1!=null)
								rs1.close();
								if(stmt1!=null)
								stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_delivery.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_detail_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(center!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setOrderId(orderId);
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==2){
								String pId = rs.getString("productId");
								int num = rs.getInt("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getInt("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getInt("num"));
									od.setNum(num*rs1.getInt("num"));
									coll.add(od);
								}
								if(rs1!=null)
								rs1.close();
								if(stmt1!=null)
								stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_review.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Center center = (Center)request.getSession().getAttribute("sys_center");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(center!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setOrderId(orderId);
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==2){
								String pId = rs.getString("productId");
								int num = rs.getInt("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getInt("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getInt("num"));
									od.setNum(num*rs1.getInt("num"));
									coll.add(od);
								}
								if(rs1!=null)
								rs1.close();
								if(stmt1!=null)
								stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}

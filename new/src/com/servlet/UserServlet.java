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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.db.DBConn;
import com.pojo.Address;
import com.pojo.Admin;
import com.pojo.Center;
import com.pojo.JoinInfo;
import com.pojo.News;
import com.pojo.Order;
import com.pojo.OrderDetail;
import com.pojo.Param;
import com.pojo.Product;
import com.pojo.Promotion;
import com.pojo.Settle;
import com.pojo.User;
import com.pojo.WSettle;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.ArithUtil;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private double emoney = 0;
	private double smoney = 0;
	private double dmoney = 0;
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

		} else if (method.equals("login")) {
			Login(request, response);

		}else if (method.equals("register_add")) {
			
				register_add(request, response);
			
		}else if (method.equals("register")) {
			try {
				register(request, response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}else if (method.equals("index")) {
			index(request, response);

		} else if (method.equals("psw_login")) {
			login_psw(request, response);

		} else if (method.equals("login_out")) {
			Logout(request, response);

		} else if (method.equals("user_vip_add")) {
			
				user_vip_add(request, response);
			

		} else if (method.equals("user_rank_join_save")) {
			try {
				user_rank_join_save(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (method.equals("user_save_product")) {
			try {
				user_save_product(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("user_save_confirm")) {
			try {
				user_save_confirm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (method.equals("user_list")) {
			user_list(request, response);

		} else if (method.equals("init")) {
			try {
				init(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("admin_add")) {
			admin_add(request, response);

		} else if (method.equals("userAjax")) {
			userAjax(request, response);

		} else if (method.equals("admin_user_save")) {
			try {
				admin_user_save(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (method.equals("adminList")) {
			adminList(request, response);

		}else if (method.equals("user_detail")) {
			user_detail(request, response);

		} else if (method.equals("admin_user_detail")) {
			admin_user_detail(request, response);

		} else if (method.equals("user_list_detail")) {
			user_list_detail(request, response);

		} else if (method.equals("user_update")) {
			user_update(request, response);

		} else if (method.equals("admin_user_update")) {
			admin_user_update(request, response);

		} else if (method.equals("admin_psw1_init")) {
			try {
				admin_psw1_init(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (method.equals("admin_user_lock")) {
			try {
				admin_user_lock(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (method.equals("admin_user_unlock")) {
			try {
				admin_user_unlock(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("admin_psw2_init")) {
			try {
				admin_psw2_init(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("user_password1_update")) {
			try {
				user_password1_update(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("user_password2_update")) {
			try {
				user_password2_update(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (method.equals("admin_getBelong")) {
			try {
				admin_getBelong(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}else if(method.equals("rankJoin_up")){
			try {
				rankJoin_up(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_a")){
			try {
				rankJoin_up_a(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_b")){
			try {
				rankJoin_up_b(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_c")){
			try {
				rankJoin_up_c(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_comments")){
			try {
				rankJoin_up_comments(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_all_a")){
			try {
				rankJoin_up_all_a(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_all_b")){
			try {
				rankJoin_up_all_b(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_all_comments")){
			try {
				rankJoin_up_all_comments(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_rankJoin_up")){
			try {
				admin_rankJoin_up(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("rankJoin_up_list")){
			try {
				rankJoin_up_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_rankJoin_up_save")){
			try {
				admin_rankJoin_up_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_referee_update")){
			try {
				admin_referee_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("testJson")){
			try {
				testJson(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_belongJson")){
			try {
				admin_belongJson(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_refereeJson")){
			try {
				admin_refereeJson(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_getReferee")){
			try {
				admin_getReferee(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_belongJson_list")){
			try {
				admin_belongJson_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_refereeJson_list")){
			try {
				admin_refereeJson_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_referee_list")){
			try {
				admin_referee_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("belongJson_list")){
			try {
				belongJson_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("belongJson")){
			try {
				belongJson(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("refereeJson_list")){
			try {
				refereeJson_list(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("refereeJson")){
			try {
				refereeJson(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_belong_update")){
			try {
				admin_belong_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_belong_update_save")){
			try {
				admin_belong_update_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("furthest_node")){
			try {
				furthest_node(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_furthest_node")){
			try {
				admin_furthest_node(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_user_hide")){
			try {
				admin_user_hide(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}else if(method.equals("admin_user_hide_list")){
		
			admin_user_hide_list(request,response);
	
	}else if(method.equals("admin_user_hide_reset")){
		try {
			admin_user_hide_reset(request,response);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}else if(method.equals("admin_user_authority_edit")){
			admin_user_authority_edit(request,response);
	}else if(method.equals("admin_user_authority_save")){
		admin_user_authority_save(request,response);
	}


	}

	public void Login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConn db = new DBConn();
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String password = StringUtil.notNull(request.getParameter("password"));
		String message = "";
		try {
			if (userId == null || password == null) {
				message = "用户名和密码不能为空！";
			} else {
				if (db.createConn()) {
					conn = db.getConnection();
					String sql = "select * from userinfo where user_id ='"
							+ userId + "' and password='"
							+ MD5.GetMD5Code(password) + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						userId = rs.getString("user_id");
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setUserId(StringUtil.notNull(rs.getString("user_id")));
						user.setUserName(StringUtil.notNull(rs.getString("user_name")));
						user.setDocumentType(StringUtil.notNull(rs.getString("document_type")));
						user.setSex(StringUtil.notNull(rs.getString("sex")));
						user.setAge(rs.getInt("age"));
						user.setTel(StringUtil.notNull(rs.getString("tel")));
						user.setNumId(StringUtil.notNull(rs.getString("num_id")));
						user.setPhone(StringUtil.notNull(rs.getString("phone")));
						user.setProvince(StringUtil.notNull(rs.getString("province")));
						user.setCity(StringUtil.notNull(rs.getString("city")));
						user.setArea(StringUtil.notNull(rs.getString("area")));
						user.setPassword(StringUtil.notNull(rs.getString("password")));
						user.setPassword2(StringUtil.notNull(rs.getString("password2")));
						user.setWeixin(StringUtil.notNull(rs.getString("weixin")));
						user.setEmail(StringUtil.notNull(rs.getString("email")));
						user.setQq(StringUtil.notNull(rs.getString("qq")));
						user.setRefereeUserId(rs.getString("referee_user_id"));
						user.setRefereeId(rs.getInt("referee_id"));
						user.setRankJoin(rs.getInt("rank_join"));
						user.setAddress(rs.getString("address"));
						user.setEntryTime(rs.getTimestamp("entry_time"));
						user.setState(rs.getInt("state"));
						user.setViewNum(rs.getInt("view_num"));
						user.setViewTime(rs.getTimestamp("view_time"));
						int state =rs.getInt("state");
						if(state==0){
							message = "该用户已下线，如有疑问请与管理员联系！";
						}else if(state==1){
							message = "该账号未激活或者存在风险，暂未激活，请与客服联系！";
						}else{
							
						request.getSession().setAttribute("sys_user", user);
						News news = null;
						String sql1 = "select * from news where state>'0' order by id desc";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql1);
						if(rs.next()){
							news = new News();
							news.setSort(rs.getString("sort"));;
							news.setTitle(rs.getString("title"));
							news.setContents(rs.getString("contents"));
						}
						request.getSession().setAttribute("sys_news", news);
						
						}
					} else {
						message = "用户名不存在或密码有误！";
					}
				} else {
					message = "数据库连接已断开！";
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
	public void register_add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token));
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		
		try {
			if (StringUtil.notNull(token_old).equals(token)) {
				if (db.createConn()) {
					String userName = StringUtil.notNull(request.getParameter("userName"));
					String password = StringUtil.notNull(request.getParameter("password"));
					String refereeId = StringUtil.notNull(request.getParameter("refereeId"));
					String tel = StringUtil.notNull(request.getParameter("tel"));
					Timestamp date = new Timestamp(new Date().getTime());
					conn = db.getConnection();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					String sql = "select * from userinfo where user_id ='"+ refereeId + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						int rid = rs.getInt("id");
						String refereeNode = StringUtil.notNull(rs.getString("referee_node"));
						int uid = us.getUId(conn);
						if (uid!= 0) {
							String mid = String.valueOf(uid);
							int length = 7-mid.length();
							
							for (int i=0; i<length;i++) {
								mid = "0" + mid;
							}
							mid="NEW" +mid;
							String userId = mid;
							if(refereeNode.equals("")) refereeNode = String.valueOf(rid);
							else refereeNode = refereeNode+","+String.valueOf(rid);
						String sql1 = "insert into userinfo(user_id,user_name,referee_id,referee_user_id,referee_node,password,password2,tel,state,entry_time) "
								+ "values('"+userId+"','"+userName+"','"+rid+"','"+refereeId+"','"+refereeNode+"','"+MD5.GetMD5Code(password)+"','"+MD5.GetMD5Code(password)+"','"+tel+"','1','"+date+"')";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql1);
						
						message="用户注册成功，会员编号为："+userId+",请登陆!";
						}else {
							message = "经销商注册获取编号失败！";
						}
					} else {
						message = "客服信息获取失败！";
					}
					conn.commit();
					conn.setAutoCommit(autoCommit);
				} else {
					message = "数据库连接已断开！";
				}
			} else {
				message = "请勿重复提交数据！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message = "数据格式保存有误！";
		} finally {
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("register_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConn db = new DBConn();
		User user = (User)request.getSession().getAttribute("sys_user");
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		
		try {
			if (user!=null) {
				if(db.createConn()){
					String sql  ="select * from news  order by entryTime desc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						News as = new News();
						as.setId(rs.getInt("id"));
						as.setAdmin(rs.getString("admin"));
						as.setTitle(rs.getString("title"));
						as.setContents(rs.getString("contents"));
						as.setSort(rs.getString("sort"));
						as.setEndTime(rs.getTimestamp("endTime"));
						as.setEntryTime(rs.getTimestamp("entryTime"));
						as.setState(rs.getInt("state"));
						result.add(as);
					}
					if(result.size()>0){
						int pageNo = 1;
						int pageSize = 6;
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
			} else {
					message = "会员未登陆，请重试！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			message = "数据操作有误，请重试！";
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

	public void login_psw(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String password = StringUtil.notNull(request.getParameter("password"));
		String message = "";
		try {
			if (password == null) {
				message = "支付密码不能为空！";
			} else {
				if (db.createConn()) {
					conn = db.getConnection();
					User user1 = us.findById(conn, user.getUserId());

					String sql = "select * from userinfo where user_id ='"
							+ user.getUserId() + "' and password2='"
							+ MD5.GetMD5Code(password) + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (user1!=null) {
						if(user1.getPassword2().equals(MD5.GetMD5Code(password))){
						request.getSession().setAttribute("sys_user", user1);
						request.getSession().setAttribute("psw2", user1.getPassword2());
						message = "支付密码登陆成功，你可以开始你的支付交易！";
						} else {
							message = "支付密码有误。";
						}
					} else {
						message = "用户不存在。";
					}
				} else {
					message = "数据库连接已断开。";
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("psw_login_message.jsp");
			dispatcher.forward(request, response);

		}

	}

	public void Logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("sys_user");
		response.sendRedirect("login.jsp");
	}
	
	public void user_vip_add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message= "";
		DBConn db = new DBConn();
		try {
			if(db.createConn()){
				conn=db.getConnection();
			} else {
				message = "数据库链接失败。";
			}
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message="数据保存有误，请重新录入！";
				} finally {
					db.close();
					if(message.equals("")){
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("user_vip_add.jsp");
						dispatcher.forward(request, response);
					}else{
						request.setAttribute("message", message);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("user_message.jsp");
						dispatcher.forward(request, response);
					}
				}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void user_rank_join_save(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String message= "";
		DBConn db = new DBConn();
		try {
		
		if (db.createConn()) {
			conn =db.getConnection();
			String rankJoinStr = StringUtil.notNull(request
					.getParameter("rankJoin"));
			String  refereeId= StringUtil.notNull(request.getParameter("refereeId"));
			String  userName= StringUtil.notNull(request.getParameter("userName"));
			String documentType = StringUtil.notNull(request.getParameter("documentType"));
			String password = StringUtil.notNull(request.getParameter("password"));
			String sex = StringUtil.notNull(request.getParameter("sex"));

			String numId = StringUtil.notNull(request.getParameter("numId"));
			String province = StringUtil.notNull(request.getParameter("province"));
			String city = StringUtil.notNull(request.getParameter("city"));
			String area = StringUtil.notNull(request.getParameter("area"));
			String tel = StringUtil.notNull(request.getParameter("phone"));
			String address = StringUtil.notNull(request.getParameter("address"));	
			String accountName = StringUtil.notNull(request.getParameter("accountName"));	
			String accountId = StringUtil.notNull(request.getParameter("accountId"));	
			String bankName = StringUtil.notNull(request.getParameter("bankName"));	
			String bankAdr = StringUtil.notNull(request.getParameter("bankAdr"));	


			conn = db.getConnection();
			Param p1 = cs.getParam(conn, "加盟资格");
			User refereeUser = us.findById(conn, refereeId);
			if(p1!=null){

			if (!rankJoinStr.equals("")) {
				rankJoin = Integer.valueOf(rankJoinStr);
			}
	
				if(rankJoin==1){
					emoney = p1.getAmount_1();
				}else if(rankJoin==2){
					emoney = p1.getAmount_2();
				}else if(rankJoin==3){
					emoney = p1.getAmount_3();
				}
				
			User new_user = new User();
			new_user.setRankJoin(rankJoin);
			new_user.setUserName(userName);
			new_user.setDocumentType(documentType);
			new_user.setNumId(numId);
			new_user.setSex(sex);
			new_user.setRefereeId(refereeUser.getId());
			new_user.setRefereeUserId(refereeUser.getUserId());
			new_user.setProvince(province);
			new_user.setPassword(password);
			new_user.setCity(city);
			new_user.setEmoney(emoney);
			new_user.setArea(area);
			new_user.setAddress(address);
			new_user.setAccountId(accountId);
			new_user.setAccountName(accountName);
			new_user.setBankName(bankName);
			new_user.setBankAdr(bankAdr);
			new_user.setTel(tel);
			
			Address new_adr = new Address();
			new_adr.setReceiver(userName);
			new_adr.setProvince(province);
			new_adr.setCity(city);
			new_adr.setArea(area);
			new_adr.setAddress(address);
			new_adr.setPhone(tel);
			String sql = "select * from product where state='1' order by id desc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					Collection coll = new ArrayList();
					while (rs.next()) {
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("product_name"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("product_id"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setType(rs.getInt("type"));
						product.setImageUrl(rs.getString("image_url"));
						product.setState(rs.getString("state"));
						coll.add(product);
			}
				request.setAttribute("coll", coll);
			request.getSession().setAttribute("new_user", new_user);
			request.getSession().setAttribute("address", new_adr);

				
}else {
	message = "系统参数设置有误，请截图与客服联系！";
}
						}else {
							message = "数据库连接已断开！";
						}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					conn.rollback();
					e.printStackTrace();
					message="数据保存有误，请重新录入！";
				} finally {
					db.close();
					if(message.equals("")){
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("user_product_select.jsp");
						dispatcher.forward(request, response);
					}else{
						request.setAttribute("message", message);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("user_message.jsp");
						dispatcher.forward(request, response);
					}
				}
	}
	
	

	
	
	public void user_save_product(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User new_user = (User) request.getSession().getAttribute("new_user");
		String message = "";
		DBConn db = new DBConn();
		Address address = (Address) request.getSession()
				.getAttribute("address");
		try {
				if (new_user != null) {
					conn =db.getConnection();
					rankJoin = new_user.getRankJoin();
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
					List<OrderDetail> olist = new ArrayList<OrderDetail>();
						Timestamp date1 = new Timestamp(new Date().getTime());
						java.sql.Timestamp date = new java.sql.Timestamp(
								date1.getTime());
						double totalprice = 0;
						int total_num = 0;
						String rid = cs.createOrderId(date);
						for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								int num = Integer.valueOf(numstr[i]);
								if (num > 0) {
									String sql = "select * from product where id='"
											+ Integer.valueOf(pid[i]) + "'";
									stmt = conn.createStatement();
									rs = stmt.executeQuery(sql);
									if (rs.next()) {
										OrderDetail od = new OrderDetail();
										od.setOrderId(rid);
										od.setPid(rs.getInt("id"));
										od.setNum(Integer.valueOf(numstr[i]));
										od.setProductId(rs
												.getString("product_id"));
										od.setProductName(rs
												.getString("product_name"));
										od.setProductPrice(rs.getDouble("price"));
										od.setSpecification(rs.getString("specification"));
										od.setProductType(rs
												.getString("product_type"));
										od.setType(rs.getInt("type"));
										od.setPrice(ArithUtil.mul(od.getProductPrice()
												, num));
										olist.add(od);
										totalprice = ArithUtil.add(totalprice
												,ArithUtil.mul(od.getProductPrice()
												, num));
										
									}
								}
							}
						}
						rankJoin = new_user.getRankJoin();
						double pv = new_user.getEmoney();
						if(ArithUtil.sub(totalprice,pv)>=0){
						
						Order orders = new Order();
						orders.setOrderId(rid);
						orders.setUserName(new_user.getUserName());
						orders.setOrderType(1);
						orders.setPrice(totalprice);
						orders.setAddress(address.getProvince()+address.getCity()+address.getArea()+address.getAddress());
						orders.setReceiver(address.getReceiver());
						orders.setPhone(address.getPhone());
						orders.setState(1);
						orders.setNum(total_num);
						orders.setTag(1);
						orders.setDiscount(1);
						request.getSession().setAttribute("orders", orders);

						request.getSession().setAttribute("olist", olist);
						int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
						request.getSession().setAttribute("token", String.valueOf(token));
							} else {
								message = "所选商品未达到业绩，请返回重新选购！";
							}
				} else {

					message = "未获得注册会员信息，请重新注册！";

				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			db.close();
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_save_confirm.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void user_save_confirm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User new_user = (User) request.getSession().getAttribute("new_user");
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		List<OrderDetail> olist = (List<OrderDetail>) request.getSession()
				.getAttribute("olist");
		Address address = (Address) request.getSession()
				.getAttribute("address");
		Order orders = (Order) request.getSession().getAttribute("orders");
		DBConn db = new DBConn();
		Timestamp date = new Timestamp(new Date().getTime());
		try {
			if (StringUtil.notNull(token_old).equals(token)) {
					if (new_user != null) {
						if (db.createConn()) {
						conn = db.getConnection();
						boolean autoCommit = conn.getAutoCommit();
						conn.setAutoCommit(false);
						User refereeUser = us.findById(conn, new_user.getRefereeUserId());
						if(refereeUser!=null){
							String refereeNode = "";
							if(StringUtil.notNull(refereeUser.getRefereeNode()).equals("")) refereeNode = String.valueOf(refereeUser.getId());
							else refereeNode = refereeUser.getRefereeNode()+","+String.valueOf(refereeUser.getId());
							int uid = us.getUId(conn);
							if (uid!= 0) {
								String mid = String.valueOf(uid);
								int length = 7-mid.length();
								for (int i=0; i<length;i++) {
									mid = "0" + mid;
								}
								mid="NEW" +mid;
								String userId = mid;
							String sql1 = "insert into userinfo(user_id,user_name,rank_join,document_type,num_id,referee_id,referee_user_id,referee_node,password,password2,tel,sex,province,city,area,address,account_name,account_id,bank_name,bank_adr,state,entry_time) "
									+ "values('"+userId+"','"+new_user.getUserName()+"','"+new_user.getRankJoin()+"','"+new_user.getDocumentType()+"','"+new_user.getNumId()
									+"','"+new_user.getRefereeId()+"','"+new_user.getRefereeUserId()
									+"','"+refereeNode+"','"+MD5.GetMD5Code(new_user.getPassword())+"','"+MD5.GetMD5Code(new_user.getPassword())+"','"+new_user.getTel()
									+"','"+new_user.getSex()+"','"+new_user.getProvince()+"','"+new_user.getCity()+"','"+new_user.getArea()+"','"+new_user.getAddress()+"','"+new_user.getAccountName()+"','"+new_user.getAccountId()+"','"+new_user.getBankName()+"','"+new_user.getBankAdr()+"','1','"+date+"')";
							stmt = conn
									.createStatement();
							stmt.executeUpdate(
									
									sql1,
									Statement.RETURN_GENERATED_KEYS);
							rs = stmt
									.getGeneratedKeys();
							if (rs.next()) {
								int new_id = rs.getInt(1);
							String sql2 ="insert into join_info(u_id,user_id,price,old_rank,new_rank,state,entry_time,end_time) values('"
									+new_id+"','"+userId+"','"+orders.getPrice()+"','0','"+new_user.getRankJoin()+"','1','"+date+"','"+date+"');";
							String sql3 = "insert into orders(order_id,u_id,user_id,user_name,order_type,price,receiver,phone,address,order_time,state,discount) "
											+ "values('"
									+ orders.getOrderId()
									+ "','"
									+ new_id
									+ "','"
									+ userId
									+ "','"
									+ orders.getUserName()
									+ "','"
									+ orders.getOrderType()
									+ "','"
									+ orders.getPrice()
									+ "','"
									+ orders.getReceiver()
									+ "','"
									+ orders.getPhone()
									+ "','"
									+ orders.getAddress()
									+ "','"
									+ date
									+ "','"
									+ orders.getState()
									+ "','"
									+ orders.getDiscount()
									+ "')";
							String sql4 ="insert into address(user_id,receiver,province,city,area,address,phone,tag,state,entry_time,end_time) values('"
									+userId+"','"+address.getReceiver()+"','"+address.getProvince()+"','"+address.getCity()+"','"+address.getArea()+"','"+address.getAddress()+"','"+address.getPhone()+"','0','1','"+date+"','"+date+"');";
							
							
									stmt = conn.createStatement();
									stmt.addBatch(sql2);
									stmt.addBatch(sql3);
									stmt.addBatch(sql4);
																for (int i = 0; i < olist
																		.size(); i++) {
																	String sqlt = "insert into order_detail(order_id,p_id,product_id,product_name,product_price,price,specification,num,type) values('"
																			+ olist.get(
																					i)
																					.getOrderId()
																					+ "','"
																			+ olist.get(
																					i)
																					.getPid()
																			+ "','"
																			+ olist.get(
																					i)
																					.getProductId()
																			+ "','"
																			+ olist.get(
																					i)
																					.getProductName()
																			+ "','"
																			+ olist.get(
																					i)
																					.getProductPrice()
																			+ "','"
																			
																			+ olist.get(
																					i)
																					.getPrice()
																			+ "','"
																			+ olist.get(
																					i).getSpecification()
																			+ "','"
																			+ olist.get(
																					i)
																					.getNum()
																			
																			+ "','"
																			+ olist.get(
																					i)
																					.getType()
																			+ "')";
																	stmt.addBatch(sqlt);
																}
																stmt.executeBatch();
																request.setAttribute(
																		"message",
																		"经销商注册成功；<br><br>经销商编号为："+userId+"；<br><br>登陆密码和二级密码为："
																		+new_user.getPassword()+"；<br><br>请妥善保管好编号，请及时支付相应款项给公司并让公司对账号进行审核。");
																request.setAttribute("success_tag","1");
							} else {
								request.setAttribute("message", "代理商数据保存失败。");
							}
							} else {
								request.setAttribute("message", "新编号信息获取失败。");
							}
						} else {
							request.setAttribute("message", "客服信息获取失败。");
						}
																conn.commit();
																conn.setAutoCommit(autoCommit);
						} else {
							request.setAttribute("message", "数据库连接已断开。");
						}
			} else {
				request.setAttribute("message", "新注册代理商信息获取失败，请重新注册。");
			}
					
			} else {
				request.setAttribute("message", "请勿重复提交数据。");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_message.jsp");
			dispatcher.forward(request, response);
		}

	}

	public void user_detail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		try {
			if (user != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					User user1 = us.findById(conn, user.getUserId());
					request.setAttribute("users", user1);
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token",
							String.valueOf(token));
				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_detail.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_user_detail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					String userId = request.getParameter("id");
					User user1 = us.findById(conn, userId);
					request.setAttribute("user", user1);
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token",
							String.valueOf(token));

				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_detail.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void user_list_detail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if (user != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					User user1 = us.findById(conn, id);
					request.setAttribute("new_user", user1);
				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_list_detail.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void user_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));

		try {
			conn = db.getConnection();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			if (StringUtil.notNull(token_old).equals(token)) {
				if (user != null) {
					if (db.createConn()) {
						String documentType = StringUtil.notNull(request
								.getParameter("documentType"));
						String numId = StringUtil.notNull(request
								.getParameter("numId"));
						
						String sex = StringUtil.notNull(request
								.getParameter("sex"));
						String qq = StringUtil.notNull(request
								.getParameter("qq"));
						String email = StringUtil.notNull(request
								.getParameter("email"));
						String province = StringUtil.notNull(request
								.getParameter("province"));
						String area = StringUtil.notNull(request
								.getParameter("area"));
						String address = StringUtil.notNull(request
								.getParameter("address"));
						String city = StringUtil.notNull(request
								.getParameter("city"));
						String bankName = StringUtil.notNull(request
								.getParameter("bankName"));
						String bankAdr = StringUtil.notNull(request
								.getParameter("bankAdr"));
						String accountId = StringUtil.notNull(request
								.getParameter("accountId"));
						String accountName = StringUtil.notNull(request
								.getParameter("accountName"));
						String phone = StringUtil.notNull(request
								.getParameter("phone"));
						String weixin = StringUtil.notNull(request
								.getParameter("weixin"));
						
								String sql = "select * from userinfo where user_id='"
										+ user.getUserId() + "' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								boolean b = rs.next();
								if (b) {
									String sqlu = "update userinfo set document_type='"+documentType
											+"',num_id='"+numId+"',sex='"+ sex 
											+ "',qq='" + qq + "', email='"
											+ email + "', province='"
											+ province + "', area='" + area
											+ "', address='" + address
											+ "', city='" + city
											+ "', bank_name='" + bankName
											+ "', bank_adr='" + bankAdr
											+ "',account_id='" + accountId
											+ "', account_name='" + accountName
											+ "', tel='" + phone
											+ "', weixin='" + weixin
											+ "' where user_id='"
											+ user.getUserId() + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sqlu);
									request.setAttribute("message", "会员信息保存成功！");
								} else {
									request.setAttribute("message",
											"未能获取相应的用户信息，请重试！");
								}
						
					} else {
						request.setAttribute("message", "数据库连接已断开！");
					}
				} else {
					request.setAttribute("message", "用户未登陆，请重新登陆！");
				}
			} else {
				request.setAttribute("message", "请勿重复提交数据！");
			}
			User user1 = us.findById(conn, user.getUserId());
			request.setAttribute("users", user1);
			request.getSession().setAttribute("sys_user", user1);
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			request.setAttribute("message", "数据操作有误，请核对输入信息是否符合要求！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_detail.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	public void admin_user_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			conn = db.getConnection();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			if (StringUtil.notNull(token_old).equals(token)) {
				if (admin != null) {
					if (db.createConn()) {
						String userId = StringUtil.notNull(request
								.getParameter("id"));
						String userName = StringUtil.notNull(request
								.getParameter("userName"));
						String documentType = StringUtil.notNull(request
								.getParameter("documentType"));
						String numId = StringUtil.notNull(request
								.getParameter("numId"));
						String sex = StringUtil.notNull(request
								.getParameter("sex"));
						String tel = StringUtil.notNull(request
								.getParameter("tel"));
						String qq = StringUtil.notNull(request
								.getParameter("qq"));
						String email = StringUtil.notNull(request
								.getParameter("email"));
						String province = StringUtil.notNull(request
								.getParameter("province"));
						String area = StringUtil.notNull(request
								.getParameter("area"));
						String address = StringUtil.notNull(request
								.getParameter("address"));
						String city = StringUtil.notNull(request
								.getParameter("city"));
						String bankName = StringUtil.notNull(request
								.getParameter("bankName"));
						String bankAdr = StringUtil.notNull(request
								.getParameter("bankAdr"));
						String accountId = StringUtil.notNull(request
								.getParameter("accountId"));
						String accountName = StringUtil.notNull(request
								.getParameter("accountName"));
						String phone = StringUtil.notNull(request
								.getParameter("phone"));
						String weixin = StringUtil.notNull(request
								.getParameter("weixin"));
						Timestamp date = new Timestamp(new Date().getTime());
								String sql = "select * from userinfo where userId='"
										+ userId + "' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								boolean b = rs.next();
								if (b) {
									String sqlu = "update userinfo set userName='"
											+userName+"',sex='"
											+ sex +"',documentType='"+documentType
											+"',numId='"+numId
											+"',tel='"+tel
											+ "',qq='" + qq + "', email='"
											+ email + "', province='"
											+ province + "', area='" + area
											+ "', address='" + address
											+ "', city='" + city
											+ "', bankName='" + bankName
											+ "', bankAdr='" + bankAdr
											+ "',accountId='" + accountId
											+ "', accountName='" + accountName
											+ "', phone='" + phone
											+ "', weixin='" + weixin
											+ "' where userId='"
											+ userId + "'";
									String sqlu1 = "update users set userName='"
											+userName+"' where userId='"
											+ userId + "'";
									
									String sqlu2 = "update wsettle set userName='"
											+userName+"' where userId='"
											+ userId + "'";
									stmt = conn.createStatement();
									stmt.addBatch(sqlu);
									stmt.addBatch(sqlu1);
									stmt.addBatch(sqlu2);
									stmt.executeBatch();
									request.setAttribute("message", "会员信息保存成功！");
									cs.insetAdminLog(conn, admin.getAdminName(), userId+"会员信息修改成功！", date);
								} else {
									request.setAttribute("message",
											"未能获取相应的用户信息，请重试！");
								}
							User user1 = us.findById(conn, userId);
							request.setAttribute("user", user1);
							request.getSession().setAttribute("sys_user", user1);
					} else {
						request.setAttribute("message", "数据库连接已断开！");
					}
				} else {
					request.setAttribute("message", "用户未登陆，请重新登陆！");
				}
			} else {
				request.setAttribute("message", "请勿重复提交数据！");
			}
			
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			request.setAttribute("message", "数据操作有误，请核对输入信息是否符合要求！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_detail.jsp");
			dispatcher.forward(request, response);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void user_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List result = new ArrayList();
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String refereeId = StringUtil
				.notNull(request.getParameter("refereeId"));
		String belongId = StringUtil.notNull(request.getParameter("belongId"));
		String startTime = StringUtil
				.notNull(request.getParameter("startTime"));
		String endTime = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if (user != null) {
				if (db.createConn()) {
					String sql = "";
					if (!(startTime.equals("") || endTime.equals(""))) {
						startTime = startTime + " 00:00:00";
						endTime = endTime + " 23:59:59";
						sql = "select * from users where entryTime>='"
								+ startTime + "' and entryTime<='" + endTime
								+ "' and (userId like '%" + userId
								+ "%' or userName like '%" + userId
								+ "%') and userByRefereeId like '%" + refereeId
								+ "%' and userByBelongId like '%" + belongId
								+ "%' and userByDeclarationId='"
								+ user.getUserId()
								+ "' and  state!='0' order by id asc";
					} else {
						sql = "select * from users where (userId like '%"
								+ userId + "%' or userName like '%" + userId
								+ "%') and userByRefereeId like '%" + refereeId
								+ "%' and userByBelongId like '%" + belongId
								+ "%' and userByDeclarationId='"
								+ user.getUserId()
								+ "' and  state!='0' order by id asc";
					}
					System.out.println(sql);
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						User user1 = new User();
						user1.setId(rs.getInt("id"));
						user1.setUserId(rs.getString("userId"));

						user1.setRankJoin(rs.getInt("rankJoin"));
						user1.setUserName(rs.getString("userName"));
						user1.setUserByBelongId(rs.getString("userByBelongId"));
						user1.setUserByDeclarationId(rs
								.getString("userByDeclarationId"));
						user1.setUserByRefereeId(rs
								.getString("userByRefereeId"));
						user1.setNodeTag(rs.getInt("nodeTag"));
						user1.setEmoney(rs.getDouble("emoney"));
						user1.setSmoney(rs.getDouble("smoney"));
						user1.setDmoney(rs.getDouble("dmoney"));
						user1.setRmoney(rs.getDouble("rmoney"));
						user1.setIntegral(rs.getDouble("integral"));
						user1.setPayTag(rs.getInt("payTag"));
						user1.setState(rs.getInt("state"));
						user1.setEntryTime(rs.getTimestamp("entryTime"));
						user1.setIsBelongList(rs.getInt("is_belong_list"));
						user1.setIsRefereeList(rs.getInt("is_referee_list"));
						user1.setIsDeclaration(rs.getInt("is_declaration"));
						user1.setIsUserList(rs.getInt("is_user_list"));
						result.add(user1);
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
				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_list.jsp");
			dispatcher.forward(request, response);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void adminList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List result = new ArrayList();
		String rankJoin = StringUtil.notNull(request.getParameter("rankJoin"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String refereeId = StringUtil
				.notNull(request.getParameter("refereeId"));
		String startTimeStr = StringUtil.notNull(request
				.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil
				.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request
				.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if (db.createConn()) {
				String sql = "";
				if (!(startTimeStr.equals("") || endTimeStr.equals(""))) {
					String startTime = startTimeStr + " 00:00:00";
					String endTime = endTimeStr + " 23:59:59";
					sql = "select * from userinfo where entryTime>='" + startTime
							+ "' and entryTime<='" + endTime
							+ " and referee_user_id like '%" + refereeId
							+ "%' and rank_join like '%"
							+ rankJoin
							+ "%'  and state>'0' and (user_id like '%" + userId
							+ "%' or user_name like '%" + userId
							+ "%') order by id desc";
				} else {
					sql = "select * from userinfo where (user_id like'%" + userId
							+ "%' or user_name like'%" + userId
							+ "%')  and referee_user_id like '%" + refereeId
							+ "%'  and rank_join like '%"
							+ rankJoin + "%' and state>'0'  order by id desc";

				}
				stmt = db.getStmtread();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserId(rs.getString("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setRefereeId(rs.getInt("referee_id"));
					user.setRefereeUserId(rs.getString("referee_user_id"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getInt("age"));
					user.setNumId(rs.getString("num_id"));
					user.setTel(rs.getString("tel"));
					user.setPhone(rs.getString("phone"));
					user.setDocumentType(rs.getString("document_type"));
					user.setRankJoin(rs.getInt("rank_join"));
					user.setState(rs.getInt("state"));
					user.setEntryTime(rs.getTimestamp("entry_time"));
					result.add(user);
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
				request.setAttribute("rankJoin", rankJoin);
				request.setAttribute("userId", userId);
				request.setAttribute("refereeId", refereeId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			} else {
				request.setAttribute("message", "数据库连接已断开！");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_list.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void init(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				if (admin.getState().equals("1")) {
					if (db.createConn()) {
						String sql = "select * from userinfo where user_id='"+Constants.TOP_NODE+"' order by id asc";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							Timestamp date1 = new Timestamp(
									new Date().getTime());
							java.sql.Timestamp date = new java.sql.Timestamp(
									date1.getTime());
							
							String sqli = "insert into userinfo(user_id,user_name,password,password2,rank_join,sex,age,document_type,num_id,tel,province,city,address,entry_time,is_empty,state) "
									+ "values('"+Constants.TOP_NODE+"','user','"
									+ MD5.GetMD5Code("111111")
									+ "','"
									+ MD5.GetMD5Code("111111")
									+ "','4','男','30','居民身份证','1111111111111111111','13800000000','广东省','广州市','公司总部','"+date+"','1','2')";
							
							stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							request.setAttribute("message",
									"用户初始化成功，用户编号为"+Constants.TOP_NODE+"，初始登录密码和支付密码为111111！");

							conn.commit();
							conn.setAutoCommit(autoCommit);
						} else {
							request.setAttribute("message",
									"用户已经初始化，请在联系系统管理员！");
						}
					} else {
						request.setAttribute("message", "数据库连接已断开！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_init_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void admin_psw1_init(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][6].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update userinfo set password='"
										+ MD5.GetMD5Code("12345678")
										+ "' where user_id='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);

							request.setAttribute("message",
									"用户登陆密码重置成功，请及时通知会员修改密码！");
							Timestamp date = new Timestamp(new Date().getTime());
							cs.insetAdminLog(conn, admin.getAdminName(), "用户登陆密码重置成功，请及时通知会员修改密码！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_psw_init_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void admin_psw2_init(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update userinfo set password2='"
										+ MD5.GetMD5Code("12345678")
										+ "' where user_id='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							request.setAttribute("message",
									"用户支付密码重置成功，请及时通知会员修改密码！");
							Timestamp date = new Timestamp(new Date().getTime());
							cs.insetAdminLog(conn, admin.getAdminName(), "用户登陆密码重置成功，请及时通知会员修改密码！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得需要重置密码的用户，请重试！");
					}

			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_psw_init_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_user_lock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update userinfo set state='1' where user_id='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt.close();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							Timestamp date = new Timestamp(
									new Date().getTime());
							request.setAttribute("message",
									"用户锁定成功，锁定用户将无法登陆后台及发放奖金！");
							cs.insetAdminLog(conn, admin.getAdminName(), "用户锁定成功，锁定用户将无法登陆后台及发放奖金！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

				
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_lock_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_user_unlock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
			
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
						
							List<String> slist = new ArrayList<String>();
							Timestamp date = new Timestamp(
									new Date().getTime());
							for (int i = 0; i < ids.length; i++) {
							
										String sql = "update userinfo set state='2' where user_id='" + ids[i] + "'";
										slist.add(sql);
								}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt.close();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							request.setAttribute("message",
									"用户解除锁定成功，请及时通知用户！");
							cs.insetAdminLog(conn, admin.getAdminName(), "用户解除锁定成功，请及时通知用户！", date);
							
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_lock_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void user_password1_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		try {
			if (user != null) {
				String psw = StringUtil.notNull(request.getParameter("pwd"));
				String password = StringUtil.notNull(request
						.getParameter("password"));
				if (!psw.equals("")) {
					if (!password.equals("")) {
						if (!psw.equals(password)) {

							if (db.createConn()) {
								conn = db.getConnection();
								boolean autoCommit = conn.getAutoCommit();
								conn.setAutoCommit(false);
								String sql = "select * from userinfo where user_id='"
										+ user.getUserId()
										+ "' and password='"
										+ MD5.GetMD5Code(psw) + "' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								boolean b = rs.next();

								if (b) {
									String sqlu = "update userinfo set password='"
											+ MD5.GetMD5Code(password)
											+ "' where user_id='"
											+ user.getUserId() + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sqlu);
									request.setAttribute("message",
											"用户登陆密码修改成功，请退出系统重新登陆！");
								} else {
									request.setAttribute("message",
											"原密码有误，请重试！");
								}
								conn.commit();
								conn.setAutoCommit(autoCommit);

							} else {
								request.setAttribute("message", "数据库连接已断开！");
							}
						} else {
							request.setAttribute("message", "新旧密码不能一样，请重试！");
						}

					} else {
						request.setAttribute("message", "新密码不能为空，请重试！");
					}
				} else {
					request.setAttribute("message", "原密码有误，请重试！");
				}

			} else {
				request.setAttribute("message", "用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("password1_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void user_password2_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		try {
			if (user != null) {
				String psw = StringUtil.notNull(request.getParameter("pwd"));
				String password = StringUtil.notNull(request
						.getParameter("password"));
				if (!psw.equals("")) {
					if (!password.equals("")) {
						if (!psw.equals(password)) {

							if (db.createConn()) {
								conn = db.getConnection();
								boolean autoCommit = conn.getAutoCommit();
								conn.setAutoCommit(false);
								String sql = "select * from userinfo where user_id='"
										+ user.getUserId()
										+ "' and password2='"
										+ MD5.GetMD5Code(psw) + "' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);

								if (rs.next()) {
									String sqlu = "update userinfo set password2='"
											+ MD5.GetMD5Code(password)
											+ "' where user_id='"
											+ user.getUserId() + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sqlu);
									request.setAttribute("message",
											"用户支付密码修改成功，请退出系统重新登陆！");
								} else {
									request.setAttribute("message",
											"原密码有误，请重试！");
								}
								conn.commit();
								conn.setAutoCommit(autoCommit);
							} else {
								request.setAttribute("message", "数据库连接已断开！");
							}
						} else {
							request.setAttribute("message", "新旧密码不能一样，请重试！");
						}

					} else {
						request.setAttribute("message", "新密码不能为空，请重试！");
					}
				} else {
					request.setAttribute("message", "原密码有误，请重试！");
				}

			} else {
				request.setAttribute("message", "用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("password2_message.jsp");
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
					String sql = "select * from userinfo where user_id ='" +userId
							+ "' and state='2' order by id asc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						object.put("userName", rs.getString("user_name"));
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

	
	

	
	public void admin_add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token));
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("user_add.jsp");
		dispatcher.forward(request, response);
	}

	public void user_add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token));
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("user_add.jsp");
		dispatcher.forward(request, response);
	}
	


	public synchronized void admin_user_save(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		DBConn db = new DBConn();
		try {
			conn = db.getConnection();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			if (admin != null) {
				if (StringUtil.notNull(token_old).equals(token)) {
				
						if (db.createConn()) {
							
							String refereeId = StringUtil.notNull(request
									.getParameter("refereeId"));
							String userName = StringUtil.notNull(request
									.getParameter("userName"));
							String password = StringUtil.notNull(request
									.getParameter("password"));
						
							String sex = StringUtil.notNull(request
									.getParameter("sex"));
							String ageStr = StringUtil.notNull(request
									.getParameter("age"));
							String documentType = StringUtil.notNull(request
									.getParameter("documentType"));
							String numId = StringUtil.notNull(request
									.getParameter("numId"));
							String qq = StringUtil.notNull(request
									.getParameter("qq"));
							String tel = StringUtil.notNull(request
									.getParameter("tel"));
							String email = StringUtil.notNull(request
									.getParameter("email"));
							String province = StringUtil.notNull(request
									.getParameter("province"));
							String area = StringUtil.notNull(request
									.getParameter("area"));
							String address = StringUtil.notNull(request
									.getParameter("address"));
							String city = StringUtil.notNull(request
									.getParameter("city"));
							String bankName = StringUtil.notNull(request
									.getParameter("bankName"));
							String accountId = StringUtil.notNull(request
									.getParameter("accountId"));
							String accountName = StringUtil.notNull(request
									.getParameter("accountName"));
							String rankJoinStr = StringUtil.notNull(request
									.getParameter("rankJoin"));
							String phone = StringUtil.notNull(request
									.getParameter("phone"));
							String weixin = StringUtil.notNull(request
									.getParameter("weixin"));
							String bankAdr = StringUtil.notNull(request
									.getParameter("bankAdr"));
							User refereeUser = getSaveUser(conn, refereeId);
							User declarationUser = getSaveUser(conn, Constants.TOP_NODE);
						
								if (refereeUser != null) {
									if(refereeUser.getState()==1){
									
											Timestamp date1 = new Timestamp(
													new Date().getTime());
											java.sql.Timestamp date = new java.sql.Timestamp(
													date1.getTime());
											int uid = getUId(conn);
											if (uid != 0) {
												
													String mid=String.valueOf(uid);

													int length = 8-mid.length();
													
													for (int i=0; i<length;i++) {
														mid = "0" + mid;
													}
													
												request.setAttribute("mid", mid);
												String userId = mid;
												request.setAttribute("userId", userId);
												User n_user = getSaveUser(conn,userId);
												if(n_user==null){
												String refereeNode = getRefereeNode(refereeUser);
												String declarationNode = getDeclarationNode(declarationUser);
											
												int rankJoinTag = 0;
												int rankJoin = 0;
												int age = 0;
												double backfill=0;
												if (!ageStr.equals("")) {
													age = Integer
															.valueOf(ageStr);
												}
												if (!rankJoinStr.equals("")) {
													rankJoinTag = 1;
													rankJoin = Integer
															.valueOf(rankJoinStr);
												}
											
												
												String table_name = "wsettle";
												String sqls = "insert into users(userId,userName,bid,rid,did,userByBelongId,userByRefereeId,userByDeclarationId,nodeTag,node,nodeABC,refereeNode,declarationNode,"
														+ "rankJoin,rankJoinOld,rankJoinTag,team,team_name,back_fill,isEmpty,payTag,state,entryTime) "
														+ "values('"
														+ userId
														
														+ "','"
														+ userName
														+ "','"
														+ refereeUser
																.getId()
																+ "','"
														+ declarationUser
																.getId()
														
														+ "','"
														+ refereeId
														+ "','"
														+ declarationUser
																.getUserId()
													
														+ "','"
														+ refereeNode
														+ "','"
														+ declarationNode
														+ "','"
														+ rankJoin
														+ "','"
														+ rankJoin
														+ "','"
														+ rankJoinTag
														
														+ "','"
														+ backfill
														+ "','1','1','2','"
														+ date + "')";

												int new_id = 0;
												stmt = conn.createStatement();
												stmt.executeUpdate(
														sqls,
														Statement.RETURN_GENERATED_KEYS);
												rs = stmt.getGeneratedKeys();
												if (rs.next()) {
													new_id = rs.getInt(1);
													
												String refereeAll = StringUtil
														.notNull(refereeUser
																.getRefereeAll());
												if (refereeAll.equals(""))
													refereeAll = String
															.valueOf(new_id);
												else
													refereeAll = refereeAll
															+ ","
															+ String.valueOf(new_id);
											
												
												String sqli = "insert into userinfo(userId,userName,password,password2,"
														+ "sex,age,documentType,numId,tel,phone,qq,weixin,email,province,city,area,address,bankName,accountId,accountName,bankAdr) "
														+ "values('"
														+ userId
														
														+ "','"
														+ userName
														+ "','"
														+ MD5.GetMD5Code(password)
														+ "','"
														+ MD5.GetMD5Code(password)
														+ "','"
														+ sex
														+ "','"
														+ age
														+ "','"
														+ documentType
														+ "','"
														+ numId
														+ "','"
														+ tel
														+ "','"
														+ phone
														+ "','"
														+ qq
														+ "','"
														+ weixin
														+ "','"
														+ email
														+ "','"
														+ province
														+ "','"
														+ city
														+ "','"
														+ area
														+ "','"
														+ address
														+ "','"
														+ bankName
														+ "','"
														+ accountId
														+ "','"
														+ accountName
														+ "','"
														+ bankAdr
														+ "')";
											
											
												String sqlu2 = "update users set refereeNum='"
														+ (refereeUser
																.getRefereeNum() + 1)
														+ "',refereeAll='"
														+ refereeAll
														+ "' where id='"
														+ refereeUser.getId()
														+ "'";

												String sql_j = "insert into joinInfo(uId,userId,userName,rid,newRankJoin,entryTime,state) "
														+ "values('"
														+ new_id
														+ "','"
														+ userId
														+ "','"
														+userName
														+ "','"
														+ refereeUser.getId()
														+ "','"
														+ rankJoin
														+ "','"
														+ date
														+ "','1')";
											
												stmt = conn
														.createStatement();
												stmt.addBatch(sql_j);
												stmt.addBatch(sqli);
												stmt.addBatch(sqlu2);
												stmt.executeBatch();
												
												request.setAttribute(
														"message",
														"用户保存成功，请核对用户信息：用户编号"
																+ userId
																+ ",用户登陆密码和支付密码为"
																+ password
																+ "。");
													} else {
														request.setAttribute("message",
																"新会员结算统计表生成失败，请重试！");
														conn.rollback();
													}
												
												} else {
													request.setAttribute("message",
															"会员保存时ID生成失败，请重新输入！");
													conn.rollback();
												}
											
												} else {
													request.setAttribute("message",
															"用户编号已被使用，请重新输入！");
													conn.rollback();
												}
												} else {
													request.setAttribute("message",
															"用户编号获取失败，请重新输入！");
													conn.rollback();
												}
										
										} else {
											request.setAttribute("message",
													"未能获取到团队信息，请重新注册！");
										}
								
									} else {
										request.setAttribute("message",
												"未找到相应报单人信息，请重新注册！");
									}
								
				
				} else {
					request.setAttribute("message",
							"请勿重复提交数据，请在管理员列表中查看是否保存成功！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_message_save.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	public synchronized User getSaveUser(Connection conn, String userId) {
		User user = new User();
		try {
			String sql = "select * from users where userId ='" + userId
					+ "' for update";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserId(StringUtil.notNull(rs.getString("userId")));
				user.setUserName(StringUtil.notNull(rs.getString("userName")));
				user.setNode(StringUtil.notNull(rs.getString("node")));
				user.setRefereeNode(StringUtil.notNull(rs
						.getString("refereeNode")));
				user.setDeclarationNode(StringUtil.notNull(rs
						.getString("declarationNode")));
				user.setNodeABC(StringUtil.notNull(rs.getString("nodeABC")));
				user.setUserByAId(StringUtil.notNull(rs.getString("userByAId")));
				user.setUserByBId(StringUtil.notNull(rs.getString("userByBId")));
				user.setUserByCId(StringUtil.notNull(rs.getString("userByCId")));
				user.setUserByBelongId(rs.getString("userByBelongId"));
				user.setUserByRefereeId(rs.getString("userByRefereeId"));
				user.setUserByDeclarationId(rs.getString("userByDeclarationId"));
				user.setEmoney(rs.getDouble("emoney"));
				user.setSmoney(rs.getDouble("smoney"));
				user.setDmoney(rs.getDouble("dmoney"));
				user.setRmoney(rs.getDouble("rmoney"));
				user.setPmoney(rs.getDouble("pmoney"));
				user.setIntegral(rs.getDouble("integral"));
				user.setRankJoin(rs.getInt("rankJoin"));
				user.setRankJoinOld(rs.getInt("rankJoinOld"));
				user.setNodeTag(rs.getInt("nodeTag"));
				user.setIsUp(rs.getInt("isUp"));
				user.setRefereeAll(StringUtil.notNull(rs
						.getString("refereeAll")));
				user.setValidtyTime(rs.getTimestamp("validtyTime"));
				user.setRefereeNum(rs.getInt("refereeNum"));
				user.setEntryTime(rs.getTimestamp("entryTime"));
				user.setIsEmpty(rs.getInt("isEmpty"));
				user.setIsToReal(rs.getInt("is_to_real"));
				user.setTeam(rs.getString("team"));
				user.setTeamName(rs.getString("team_name"));
				user.setState(rs.getInt("state"));
				user.setAddNum(rs.getInt("addNum"));
			} else
				user = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return user;
	}
	
	public synchronized User getUserById(Connection conn, int id) {
		User user = new User();
		try {
			String sql = "select * from users where id ='" + id
					+ "' for update";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserId(StringUtil.notNull(rs.getString("userId")));
				user.setUserName(StringUtil.notNull(rs.getString("userName")));
				user.setNode(StringUtil.notNull(rs.getString("node")));
				user.setRefereeNode(StringUtil.notNull(rs
						.getString("refereeNode")));
				user.setDeclarationNode(StringUtil.notNull(rs
						.getString("declarationNode")));
				user.setNodeABC(StringUtil.notNull(rs.getString("nodeABC")));
				user.setUserByAId(StringUtil.notNull(rs.getString("userByAId")));
				user.setUserByBId(StringUtil.notNull(rs.getString("userByBId")));
				user.setUserByCId(StringUtil.notNull(rs.getString("userByCId")));
				user.setUserByBelongId(rs.getString("userByBelongId"));
				user.setUserByRefereeId(rs.getString("userByRefereeId"));
				user.setUserByDeclarationId(rs.getString("userByDeclarationId"));
				user.setEmoney(rs.getDouble("emoney"));
				user.setSmoney(rs.getDouble("smoney"));
				user.setDmoney(rs.getDouble("dmoney"));
				user.setRmoney(rs.getDouble("rmoney"));
				user.setPmoney(rs.getDouble("pmoney"));
				user.setIntegral(rs.getDouble("integral"));
				user.setRankJoin(rs.getInt("rankJoin"));
				user.setRankJoinOld(rs.getInt("rankJoinOld"));
				user.setNodeTag(rs.getInt("nodeTag"));
				user.setIsUp(rs.getInt("isUp"));
				user.setRefereeAll(StringUtil.notNull(rs
						.getString("refereeAll")));
				user.setValidtyTime(rs.getTimestamp("validtyTime"));
				user.setRefereeNum(rs.getInt("refereeNum"));
				user.setEntryTime(rs.getTimestamp("entryTime"));
				user.setState(rs.getInt("state"));
			} else
				user = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return user;
	}

	public synchronized String getRefereeNode(User user) {
		String node = "";
		if (user.getRefereeNode().equals(""))
			node = String.valueOf(user.getId());
		else
			node = user.getRefereeNode() + "," + String.valueOf(user.getId());
		return node;
	}

	public synchronized String getDeclarationNode(User user) {
		String node = "";
		if (user.getDeclarationNode().equals(""))
			node = String.valueOf(user.getId());
		else
			node = user.getDeclarationNode() + ","
					+ String.valueOf(user.getId());
		return node;
	}

	public synchronized String getNode(User user) {
		String node = "";
		if (user.getNode().equals(""))
			node = String.valueOf(user.getId());
		else
			node = user.getNode() + "," + String.valueOf(user.getId());
		return node;
	}

	public synchronized String getNodeABC(User user, int nodeTag) {
		String node = "";
		String nodeStr = "";
		if (nodeTag == 1)
			nodeStr = "A";
		else if (nodeTag == 2)
			nodeStr = "B";
		else if (nodeTag == 3)
			nodeStr = "C";
		if (user.getNodeABC().equals(""))
			node = nodeStr;
		else
			node = user.getNodeABC() + "," + nodeStr;
		return node;
	}

	public boolean checkNodeTag(int nodeTag, User user) {
		boolean b = false;
		if (nodeTag == 1 && user.getUserByAId().equals(""))
			b = true;
		else if (nodeTag == 2 && user.getUserByBId().equals(""))
			b = true;
		else if (nodeTag == 3 && user.getUserByCId().equals(""))
			b = true;
		return b;
	}

	public int getUId(Connection conn) throws SQLException {
		int uid = 0;
		if (check_card_pool(conn)) {
			uid = get_card_id(conn);
		} else {
			add_card_pool(conn);
			uid = get_card_id(conn);
		}
		return uid;
	}
	
	public Integer[] getUIdList(Connection conn) throws SQLException {
		Integer[] uid = {0,0,0};
		if (check_card_pool(conn)) {
			uid = get_card_id_list(conn);
		} else {
			add_card_pool(conn);
			uid = get_card_id_list(conn);
		}
		return uid;
	}

	public boolean check_card_pool(Connection conn) throws SQLException {
		String sql = "select count(*) from uidpool";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		int num = 0;
		if (rs.next()) {
			num = rs.getInt(1);
		}
		if (num < 3)
			return false;
		else
			return true;
	}

	public int get_card_id(Connection conn) throws SQLException {
		String sql = "select * from uidpool where tag='0' order by id asc for update";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		int num = 0;
		int id = 0;
		if (rs.next()) {
			num = rs.getInt("uid");
			id = rs.getInt("id");
			String sqld = "update uidpool set tag ='1' where id='" + id + "'";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqld);
		} else {
			add_card_pool(conn);
			num = get_card_id(conn);
		}
		return num;
	}
	
	public Integer[] get_card_id_list(Connection conn) throws SQLException {
		String sql = "select * from uidpool where tag='0' order by id asc limit 3 for update";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		Integer[] num = {0,0,0};
		int id = 0;
		int i=0;
		while (rs.next()) {
			num[i] = rs.getInt("uid");
			id = rs.getInt("id");
			i++;
			System.out.println("i:"+i);
			String sqld = "update uidpool set tag ='1' where id='" + id + "'";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqld);
		}
		return num;
	}

	public synchronized void add_card_pool(Connection conn) throws SQLException {
		int maxId = 0;
		String sqld1 = "select max(uid) from uidpool";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqld1);
		if (rs.next()) {
			maxId = rs.getInt(1);
		} else {
			maxId = 10001;
		}
		if (maxId == 0)
			maxId = 10001;
		stmt = conn.createStatement();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3000; i++) {
			list.add(maxId + i);
		}
		int out = 0;
		int outIndex = 0;
		for (int i = 0; i < 3000; i++) {
			Random ran = new Random();
			outIndex = ran.nextInt(list.size());
			out = list.get(outIndex);
			list.remove(outIndex);
			String sqli = "insert into uidpool(uid) values('" + (out + 1)
					+ "')";
			stmt.addBatch(sqli);
		}
		String sqld = "delete from uidpool where tag='1'";
		stmt.addBatch(sqld);
		stmt.executeBatch();
	}

	public synchronized void editRank(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		String idstr = request.getParameter("id");
		try {
			if (admin != null) {
				if (!StringUtil.notNull(idstr).equals("")) {
					if (db.createConn()) {

						String sql = "select * from admin where id ='"
								+ Integer.valueOf(idstr) + "'";

						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							Admin admin1 = new Admin();
							admin1.setId(rs.getInt("id"));
							admin1.setAdminName(rs.getString("adminName"));
							admin1.setPassword(rs.getString("password"));
							admin1.setPassword2(rs.getString("password2"));
							admin1.setRank(rs.getString("rank"));
							admin1.setState(rs.getString("state"));
							admin1.setEntryTime(rs.getTimestamp("entryTime"));
							admin1.setEndTime(rs.getTimestamp("endTime"));
							request.setAttribute("admin1", admin1);
							db.close();

						} else {
							message = "用户信息有误，请重新选择！";
						}
					} else {
						message = "数据库连接已断开！";
					}
				} else {
					message = "用户ID信息有误，请重新选择！";
				}
			} else {
				message = "管理员用户未登陆，请重新登陆！";
			}
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
		} finally {
			db.close();
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("admin_rank.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("admin_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	

	public void initPsw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				if (db.createConn()) {
					String password = MD5.GetMD5Code("12345678");
					String idstr = request.getParameter("id");
					if (!StringUtil.notNull(idstr).equals("")) {
						int id = Integer.valueOf(idstr);
						String sql = "select * from admin where id ='" + id
								+ "' for update";
						conn = db.getConnection();
						boolean autoCommit = conn.getAutoCommit();
						conn.setAutoCommit(false);
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						String name = "";
						if (rs.next()) {
							name = rs.getString("adminName");
							String sqli = "update admin set password='"
									+ password + "' where id ='" + id + "'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							message = "用户密码重置成功，重置用户为" + name
									+ ",初始化密码为12345678！";
						}

						conn.commit();
						conn.setAutoCommit(autoCommit);

					} else {
						message = "用户ID信息有误，请重新选择！";
					}
				} else {
					message = "数据库连接已断开！";
				}

			} else {
				message = "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作有误，请重试！";
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				if (db.createConn()) {
					String idstr = request.getParameter("id");
					if (!StringUtil.notNull(idstr).equals("")) {
						int id = Integer.valueOf(idstr);
						String sql = "select * from admin where id ='" + id
								+ "' for update";
						conn = db.getConnection();
						boolean autoCommit = conn.getAutoCommit();
						conn.setAutoCommit(false);
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);
						String name = "";
						if (rs.next()) {
							name = rs.getString("adminName");
							db.close();
							String sqli = "update admin set state='0' where id ='"
									+ id + "'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							message = "用户删除成功，删除用户为" + name + "！";
						}

						conn.commit();
						conn.setAutoCommit(autoCommit);

					} else {
						message = "用户ID信息有误，请重新选择！";
					}
				} else {
					message = "数据库连接已断开！";
				}

			} else {
				message = "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作有误，请重试！";
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				if (db.createConn()) {
					String[] idstr = request.getParameterValues("ids");
					String ids = "";
					for (int i = 0; i < idstr.length; i++) {
						if (!StringUtil.notNull(idstr[i]).equals("")) {
							if (ids.equals(""))
								ids = ids + "'" + idstr[i] + "'";
							else
								ids = ids + ",'" + idstr[i] + "'";
						}
					}
					if (!ids.equals("")) {
						String sql = "select * from admin where id IN(" + ids
								+ ") for update";
						conn = db.getConnection();
						boolean autoCommit = conn.getAutoCommit();
						conn.setAutoCommit(false);
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sql);

						if (rs.next()) {
							db.close();
							String sqli = "update admin set state='0' where id IN("
									+ ids + ")";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							message = "批量删除成功！";
						}
						conn.commit();
						conn.setAutoCommit(autoCommit);
					} else {
						message = "为选择需要删除的用户信息，请重新选择！";
					}
				} else {
					message = "数据库连接已断开！";
				}

			} else {
				message = "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作有误，请重试！";
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
		 String filename = "会员列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<User>  result = new ArrayList<User> ();
		String rankJoin = StringUtil.notNull(request.getParameter("rankJoin"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String refereeId = StringUtil
				.notNull(request.getParameter("refereeId"));
		
		String startTimeStr = StringUtil.notNull(request
				.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		try {
			if(db.createConn()){
				String sql = "";
				if (!(startTimeStr.equals("") || endTimeStr.equals(""))) {
					String startTime = startTimeStr + " 00:00:00";
					String endTime = endTimeStr + " 23:59:59";
					sql = "select * from userinfo where entryTime>='" + startTime
							+ "' and entryTime<='" + endTime
							+ " and referee_user_id like '%" + refereeId
							+ "%' and rank_join like '%"
							+ rankJoin
							+ "%'  and state>'0' and (user_id like '%" + userId
							+ "%' or user_name like '%" + userId
							+ "%') order by id desc";
				} else {
					sql = "select * from userinfo where (user_id like'%" + userId
							+ "%' or user_name like'%" + userId
							+ "%')  and referee_user_id like '%" + refereeId
							+ "%'  and rank_join like '%"
							+ rankJoin + "%' and state>'0'  order by id desc";

				}
				stmt = db.getStmtread();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserId(rs.getString("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setRefereeId(rs.getInt("referee_id"));
					user.setRefereeUserId(rs.getString("referee_user_id"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getInt("age"));
					user.setNumId(rs.getString("num_id"));
					user.setTel(rs.getString("tel"));
					user.setPhone(rs.getString("phone"));
					user.setProvince(rs.getString("province"));
					user.setCity(rs.getString("city"));
					user.setArea(rs.getString("area"));
					user.setAccountId(rs.getString("account_id"));
					user.setAccountName(rs.getString("account_name"));
					user.setBankName(rs.getString("bank_name"));
					user.setBankAdr(rs.getString("bank_adr"));
					user.setDocumentType(rs.getString("document_type"));
					user.setRankJoin(rs.getInt("rank_join"));
					user.setState(rs.getInt("state"));
					user.setAddress(rs.getString("address"));
					user.setEntryTime(rs.getTimestamp("entry_time"));
					result.add(user);
				}
				String[][] content = new String[result.size()+1][15];
				content[0][0]="序号";
				content[0][1]="会员编号";
				content[0][2]="会员名称";
				content[0][3]="会员等级";
				content[0][4]="客服编号";
				content[0][5]="证件类型";
				content[0][6]="证件号码";
				content[0][7]="手机号码";
				content[0][8]="所在省市县";
				content[0][9]="详细地址";
				content[0][10]="持卡人姓名";
				content[0][11]="银行卡号";
				content[0][12]="开户银行";
				content[0][13]="注册时间";
				content[0][14]="当前状态";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
					String rankstr="";
					if(result.get(i).getRankJoin()==0) rankstr = "-";
					else if(result.get(i).getRankJoin()==1) rankstr = "银级代理商";
					else if(result.get(i).getRankJoin()==2) rankstr = "金级代理商";
					else if(result.get(i).getRankJoin()==3) rankstr = "钻级代理商";
					content[i+1][3]  = StringUtil.notNull(rankstr);
					
					content[i+1][4]  = StringUtil.notNull(result.get(i).getRefereeUserId());
					content[i+1][5]  =  StringUtil.notNull(result.get(i).getDocumentType());
					content[i+1][6]  =  StringUtil.notNull(result.get(i).getNumId());
					content[i+1][7]  =  StringUtil.notNull(result.get(i).getTel());
					content[i+1][8]  =  StringUtil.notNull(result.get(i).getProvince())+StringUtil.notNull(result.get(i).getCity())+ StringUtil.notNull(result.get(i).getArea());
					content[i+1][9]  =  StringUtil.notNull(result.get(i).getAddress());

					content[i+1][10]  = StringUtil.notNull(result.get(i).getAccountName());
					content[i+1][11]  = StringUtil.notNull(result.get(i).getAccountId());
					content[i+1][12]  = StringUtil.notNull(result.get(i).getBankName())+StringUtil.notNull(result.get(i).getBankAdr());

					content[i+1][13] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
					String stateStr ="";
					if(result.get(i).getState()==0) stateStr = "下线";
					else if(result.get(i).getState()==1) stateStr = "待激活";
					else if(result.get(i).getState()==2) stateStr = "已激活";
					content[i+1][14] = stateStr;
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("会员列表");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
		}
	}
	
	public synchronized void rankJoin_up(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message = "";
		String type1 =""; 
		// Timestamp date1 = new Timestamp(new Date().getTime());
		// java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try{
			if (user != null) {
			if (db.createConn()) {
				conn = db.getConnection();
					User user1 = us.findById(conn, user.getUserId());
					if(user1!=null){	
						request.setAttribute("user1", user1);		
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
	if(message.equals("")){
	RequestDispatcher dispatcher = request
			.getRequestDispatcher("rankJoin_up_a.jsp");
	dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("rankJoin_up_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void rankJoin_up_a(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message = "";
		// Timestamp date1 = new Timestamp(new Date().getTime());
		// java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try{
			if (user != null) {
			if (db.createConn()) {
				conn = db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				String userId = request.getParameter("userId");
				User user1 = us.findById(conn, userId);
				Param p  = cs.getParam(conn, "加盟资格");
				if (user1!= null) {
					if(p!=null){
						String sql ="select * from join_info where user_id='"+userId+"' and state='1'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(!rs.next()){
					String rankJoinStr = StringUtil.notNull(request
							.getParameter("rankJoin"));
					String rankJoinOldStr = StringUtil.notNull(request
							.getParameter("rankJoinOld"));
					if(!rankJoinStr.equals("")){
						rankJoin =Integer.valueOf(rankJoinStr);
						int rankJoinOld =Integer.valueOf(rankJoinOldStr);
						double emoney1=0;
						if (rankJoin == 1) {
							emoney = p.getAmount_1();
						} else if (rankJoin == 2) {
							emoney = p.getAmount_2();
						} else if (rankJoin == 3) {
							emoney = p.getAmount_3();
						} 
						 if (rankJoinOld == 2) {
							emoney1 = p.getAmount_1();
						} else if (rankJoinOld == 3) {
							emoney1 = p.getAmount_2();
						} else if (rankJoinOld == 4) {
							emoney1 = p.getAmount_3();
						} 
			
						emoney = ArithUtil.sub(emoney, emoney1);
		
						if(emoney<=0) emoney = 0;
			user1.setEmoney(emoney);
			user1.setRankJoin(rankJoin);
			user1.setRankJoinOld(rankJoinOld);
			request.getSession().setAttribute("user1", user1);
					String sql1 = "select * from product where  state='1'  order by id desc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql1);
					Collection coll = new ArrayList();
					while (rs.next()) {
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("product_name"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("product_id"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						
						product.setType(rs.getInt("type"));
						product.setState(rs.getString("state"));
						product.setImageUrl(rs.getString("image_url"));
						coll.add(product);
					}
					request.setAttribute("coll", coll);
					
					Collection coll_adr = new ArrayList();
					String sqla = "select * from address where user_id='"+user1.getUserId()+"' order by id desc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sqla);
					while(rs.next()){
						Address adr = new Address();
						adr.setId(rs.getInt("id"));
						adr.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						adr.setReceiver(rs.getString("receiver"));
						adr.setPhone(rs.getString("phone"));
						coll_adr.add(adr);
					} 
					request.setAttribute("coll_adr", coll_adr);		
					
						
					} else {
						message="你所选的等级信息有误，请重试。";
						
					}
						} else {
							message="你存在未审核的升级信息，请勿重复申请。";
							
						}
					} else {
						message="加盟资格参数获取失败，请重试。";
						
					}
			} else {
				message="未查询到相应会员的信息，请重试。";
				
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
			.getRequestDispatcher("rankJoin_up_b.jsp");
	dispatcher.forward(request, response);
	}else{
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("rankJoin_up_message.jsp");
		dispatcher.forward(request, response);
	}
	
}

	}
	
	public void rankJoin_up_b(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		User user1 = (User) request.getSession().getAttribute("user1");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (user != null) {
				if (user1 != null) {
					if(db.createConn()){
						conn = db.getConnection();
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
					String addressId = StringUtil.notNull(request.getParameter("addressId"));
					if (!addressId.equals("")) {
						List<OrderDetail> olist = new ArrayList<OrderDetail>();
						Timestamp date1 = new Timestamp(new Date().getTime());
						java.sql.Timestamp date = new java.sql.Timestamp(
								date1.getTime());
						double totalprice = 0;
						String rid = cs.createOrderId(date);
						for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if (Integer.valueOf(numstr[i]) > 0) {
									String sql = "select * from product where id='"
											+ Integer.valueOf(pid[i]) + "'";
									stmt = conn.createStatement();
									rs = stmt.executeQuery(sql);
									if (rs.next()) {
										OrderDetail od = new OrderDetail();
										od.setOrderId(rid);
										od.setNum(Integer.valueOf(numstr[i]));
										od.setProductId(rs
												.getString("product_id"));
										od.setProductName(rs
												.getString("product_name"));
										od.setProductPrice(rs.getDouble("price"));
										od.setProductType(rs
												.getString("product_type"));
										od.setType(rs.getInt("type"));
										od.setPrice(ArithUtil.mul(rs.getDouble("price")
												,Integer.valueOf(numstr[i])));
									
										olist.add(od);
										totalprice = ArithUtil.add(totalprice
												,ArithUtil.mul(od.getProductPrice()
												, Integer.valueOf(numstr[i])));
										
									}
								}
							}
						}
						String sqla = "select * from address where id='"+addressId+"'";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sqla);
						Order orders = new Order();
						if(rs.next()){
							orders.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
							orders.setReceiver(rs.getString("receiver"));
							orders.setPhone(rs.getString("phone"));
						} 
						orders.setOrderId(rid);
						orders.setuId(user.getId());
						orders.setUserId(user.getUserId());
						orders.setUserName(user.getUserName());
						orders.setOrderType(2);
						orders.setPrice(totalprice);
						orders.setState(1);
						orders.setOrderTime(date);
						orders.setDiscount(1);
						orders.setTag(1);
						orders.setUserByDeclarationId(user.getUserId());
						request.getSession().setAttribute("orders", orders);
						request.getSession().setAttribute("olist", olist);
						int token_new = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token_new));
					} else {

						message = "收货人信息不完善，请返回上一页重新完善信息！";

					}
						
					} else {
						message = "数据库连接有误，请重新登陆！";

					}
				} else {

					message = "未获得注册会员信息，请重新注册！";

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
						.getRequestDispatcher("rankJoin_up_c.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void rankJoin_up_c(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		User new_user = (User) request.getSession().getAttribute("user1");
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		List<OrderDetail> olist = (List<OrderDetail>) request.getSession()
				.getAttribute("olist");
		Order orders = (Order) request.getSession().getAttribute("orders");
		DBConn db = new DBConn();
		Timestamp date = new Timestamp(new Date().getTime());
		try {
			if(user!=null){
			if (StringUtil.notNull(token_old).equals(token)) {
					if (new_user != null) {
						if (db.createConn()) {
						conn = db.getConnection();
						boolean autoCommit = conn.getAutoCommit();
						conn.setAutoCommit(false);
						
						
							String sql2 ="insert into join_info(u_id,user_id,price,old_rank,new_rank,state,entry_time,end_time) values('"
									+user.getId()+"','"+user.getUserId()+"','"+orders.getPrice()+"','"+new_user.getRankJoinOld()+"','"+new_user.getRankJoin()+"','1','"+date+"','"+date+"');";
							String sql3 = "insert into orders(order_id,u_id,user_id,user_name,order_type,price,receiver,phone,address,order_time,state,discount) "
											+ "values('"
									+ orders.getOrderId()
									+ "','"
									+ user.getId()
									+ "','"
									+ user.getUserId()
									+ "','"
									+ orders.getUserName()
									+ "','"
									+ orders.getOrderType()
									+ "','"
									+ orders.getPrice()
									+ "','"
									+ orders.getReceiver()
									+ "','"
									+ orders.getPhone()
									+ "','"
									+ orders.getAddress()
									+ "','"
									+ date
									+ "','"
									+ orders.getState()
									+ "','"
									+ orders.getDiscount()
									+ "')";
							
									stmt = conn.createStatement();
									stmt.addBatch(sql2);
									stmt.addBatch(sql3);
																for (int i = 0; i < olist
																		.size(); i++) {
																	String sqlt = "insert into order_detail(order_id,p_id,product_id,product_name,product_price,price,specification,num,type) values('"
																			+ olist.get(
																					i)
																					.getOrderId()
																					+ "','"
																			+ olist.get(
																					i)
																					.getPid()
																			+ "','"
																			+ olist.get(
																					i)
																					.getProductId()
																			+ "','"
																			+ olist.get(
																					i)
																					.getProductName()
																			+ "','"
																			+ olist.get(
																					i)
																					.getProductPrice()
																			+ "','"
																			
																			+ olist.get(
																					i)
																					.getPrice()
																			+ "','"
																			+ olist.get(
																					i).getSpecification()
																			+ "','"
																			+ olist.get(
																					i)
																					.getNum()
																			
																			+ "','"
																			+ olist.get(
																					i)
																					.getType()
																			+ "')";
																	stmt.addBatch(sqlt);
																}
																stmt.executeBatch();
																request.setAttribute(
																		"message",
																		"经销商升级成功,购物金额为："+orders.getPrice()+";<br>请尽快将款项支付给公司，并通知公司确认订单信息。");
																request.setAttribute("success_tag","1");
																conn.commit();
																conn.setAutoCommit(autoCommit);
						} else {
							request.setAttribute("message", "数据库连接已断开。");
						}
			} else {
				request.setAttribute("message", "代理商升级信息获取失败，请重新注册。");
			}
					
			} else {
				request.setAttribute("message", "请勿重复提交数据。");
			}
			} else {
				request.setAttribute("message", "用户未登陆，请重新登陆。");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("rankJoin_up_message.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	
	public synchronized void rankJoin_up_comments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		Order orders = (Order) request.getSession().getAttribute("orders");
		String message = "";
		try {
		
				if (user != null) {
					
						String comments = StringUtil.notNull(request.getParameter("comments"));
						orders.setComments(comments);
						request.getSession().setAttribute("orders", orders);
					
				} else {
					message= "用户未登陆，请重新登陆！";
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_c.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}


	
	public void admin_rankJoin_up(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
					String userId = StringUtil.notNull(request.getParameter("userId"));
					if (!userId.equals("")) {
						if (db.createConn()) {
							conn = db.getConnection();
						User up_user = us.findById(conn, userId);
						request.getSession().setAttribute("up_user", up_user);
						
					} else {
						message = "数据库连接出错，请稍后再试或与管理员联系！";
					}
				} else {

					message = "未获得会员信息，请重新输入！";

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
						.getRequestDispatcher("rankJoin_update.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_update_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void rankJoin_up_list(HttpServletRequest request,
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
								sql= "select * from joinInfo where oldRankJoin>'0' and entryTime>='"+startTime+"' and entryTime<'"+endTime+"' and (userId like '%"+userId+"%' or userName like '%"+userId+"%') order by entryTime desc";
							else sql= "select * from joinInfo where oldRankJoin>'0' and (userId like '%"+userId+"%' or userName like '%"+userId+"%') order by entryTime desc";
							stmt = db.getStmtread();
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								JoinInfo jf = new JoinInfo();
								jf.setId(rs.getInt("id"));
								jf.setUserId(rs.getString("userId"));
								jf.setUserName(rs.getString("userName"));
								jf.setOldRank(rs.getInt("old_rank"));
								jf.setNewRank(rs.getInt("new_rank"));
								jf.setPrice(rs.getDouble("price"));
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
						.getRequestDispatcher("rankJoin_up_list.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_list_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	public void admin_rankJoin_up_save(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try {
			if (StringUtil.notNull(token_old).equals(token)) {
			if (admin != null) {
					String userId = StringUtil.notNull(request.getParameter("id"));
					String rankJoinStr = StringUtil.notNull(request.getParameter("rankJoin"));
					if (!userId.equals("")) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							User up_user = getSaveUser(conn,
									userId);
							
						if(up_user!=null){
							User refereeUser = getSaveUser(conn,
									up_user.getUserByRefereeId());
							if(refereeUser!=null){
							rankJoin = Integer.valueOf(rankJoinStr);
								if(rankJoin-up_user.getRankJoin()>0){
									String sql1 = "update userinfo set rank_join='"+rankJoin+"' where id='"+up_user.getId()+"'";
									String sql2 = "insert into joinInfo(uId,userId,userName,rid,oldRankJoin,newRankJoin,price,pv,entryTime,state) "
											+ "values('"
											+ up_user.getId()
											+ "','"
											+ up_user.getUserId()
											+ "','"
											+ up_user.getUserName()
											+ "','"
											+ refereeUser.getId()
											+ "','"
											+ up_user.getRankJoin()
											+ "','"
											+ rankJoin
											+ "','0','0','"
											+ date
											+ "','1')";
									stmt = conn.createStatement();
									stmt.addBatch(sql1);
									stmt.addBatch(sql2);
									stmt.executeBatch();
									message = "会员等级变更成功，变更会员为"+up_user.getUserName()+"("+up_user.getUserId()+")！";
									cs.insetAdminLog(conn, admin.getAdminName(), message, date);
								}else {
									message = "当前等级没有升级需求，请重试！";

								}
							}else {
								message = "推荐人信息获取失败，请重试！";

							}
						}else {
							message = "获取会员信息失败，请重试！";

						}
						conn.commit();
						conn.setAutoCommit(autoCommit);
					} else {
						message = "数据库连接出错，请稍后再试或与管理员联系！";

					}
				} else {
					message = "未获得会员信息，请重新输入！";

				}
			} else {
				message = "用户未登陆，请重新登陆！";

			}
			} else {
				message="请勿重复提交数据，请在会员列表中查看是否保存成功！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_update_message.jsp");
				dispatcher.forward(request, response);
			

		}
	}
	
	public void admin_referee_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][3].equals("1")
						|| admin.getState().equals("1")) {
					String userId = StringUtil.notNull(request.getParameter("userId"));
					if (!userId.equals("")) {
						if (db.createConn()) {
							conn = db.getConnection();
						User up_user = us.findById(conn, userId);
						request.getSession().setAttribute("up_user", up_user);
						
					} else {
						message = "数据库连接出错，请稍后再试或与管理员联系！";
					}
				} else {

					message = "未获得会员信息，请重新输入！";

				}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
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
						.getRequestDispatcher("referee_update_a.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("referee_update_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	
	public synchronized void testJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		   String id = request.getParameter("id");  
	        String name = request.getParameter("name");  
	        String level = request.getParameter("level");  
	        String otherParam = request.getParameter("otherParam");  
	        System.out.println(id + "|" + name + "|" + level + "|" + otherParam);  
	          
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	        for(int i = 0; i < 5; i++){  
	            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
	            hm.put("id",id+i);//id属性  ，数据传递    
	            hm.put("name", id+i); //name属性，显示节点名称    
	            hm.put("pId", id);  
	            hm.put("isParent", true); 
	            list.add(hm);  
	        }  
	        response.getWriter().write(JSON.toJSONString(list));  
	    }  
	
	public  void admin_belongJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		DBConn db = new DBConn();
		conn = db.getConnection();
		   String id = request.getParameter("id");  
	        String sql = "select userId from users where id='"+id+"'";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        String userId ="";
	        if(rs.next()){
	        	userId = rs.getString(1);
	        }
	        sql = "select * from users where userByBelongId='"+userId+"' and state!='0'  order by nodeTag asc";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	        while(rs.next()){
	        	 int rankJoin = rs.getInt("rankJoin");
		        	String rank ="";
		        	if (rankJoin == 1) {
						rank = rank + "关注会员；";
					} else if (rankJoin == 2) {
						rank = rank + "普通VIP会员；";
					} else if (rankJoin == 3) {
						rank = rank + "银级VIP会员；";
					} else if (rankJoin == 4) {
						rank = rank + "金级VIP会员；";
					}else rank = rank + "-；";
	        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
	            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
	            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
	            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
	            hm.put("pId", id);  
	            hm.put("icon","ztree/css/zTreeStyle/img/diy/3.png");
	            hm.put("isParent", true); 
	            list.add(hm);  
	        }  
	        response.getWriter().write(JSON.toJSONString(list));  
	        db.close();
	    }  
	
	public void admin_getBelong(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
		try {
			if (admin != null) {
				String userId = StringUtil.notNull(request
						.getParameter("userId"));
				if (!userId.equals("")) {
					if (db.createConn()) {
						conn = db.getConnection();
						String sql = "select * from users where userId='"
								+ userId + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
					      
						if (rs.next()) {
							//最外层，父节点             
							 int rankJoin = rs.getInt("rankJoin");
					        	String rank ="";
					        	if (rankJoin == 1) {
									rank = rank + "关注会员；";
								} else if (rankJoin == 2) {
									rank = rank + "普通VIP会员；";
								} else if (rankJoin == 3) {
									rank = rank + "银级VIP会员；";
								} else if (rankJoin == 4) {
									rank = rank + "金级VIP会员；";
								}else rank = rank + "-；";
				        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
				            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
				            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
				            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
						           hm.put("pId", 0);  
					            hm.put("isParent", true); 
					            hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
					            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
					            list.add(hm);  
						  }  
							request.setAttribute("userJson", JSON.toJSONString(list));
							request.setAttribute("userId", userId);

					} else {
						message = "数据库连接已断开！";
					}
				} else {
					message = "未输入所要查询的会员编号，请输入！";
				}
			} else {
				message = "管理员未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			db.close();
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_belong_json.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_belong_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public  void admin_refereeJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		DBConn db = new DBConn();
		conn = db.getConnection();
		   String id = request.getParameter("id");  
	        String sql = "select userId from users where id='"+id+"'";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        String userId ="";
	        if(rs.next()){
	        	userId = rs.getString(1);
	        }
	        sql = "select * from users where userByRefereeId='"+userId+"' and state!='0' order by id asc";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	        while(rs.next()){
	        	 int rankJoin = rs.getInt("rankJoin");
		        	String rank ="";
		        	if (rankJoin == 1) {
						rank = rank + "关注会员；";
					} else if (rankJoin == 2) {
						rank = rank + "普通VIP会员；";
					} else if (rankJoin == 3) {
						rank = rank + "银级VIP会员；";
					} else if (rankJoin == 4) {
						rank = rank + "金级VIP会员；";
					}else rank = rank + "-；";
	        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
	            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
	            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
	            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
		           hm.put("pId", id);  
	            hm.put("icon","ztree/css/zTreeStyle/img/diy/3.png");
	            hm.put("isParent", true); 
	            list.add(hm);  
	        }  
	        response.getWriter().write(JSON.toJSONString(list));  
	        db.close();
	    }  
	
	public void admin_getReferee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
		try {
			if (admin != null) {
				String userId = StringUtil.notNull(request
						.getParameter("userId"));
				if (!userId.equals("")) {
					if (db.createConn()) {
						conn = db.getConnection();
						String sql = "select * from users where userId='"
								+ userId + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
					      
						if (rs.next()) {
							//最外层，父节点             
							 int rankJoin = rs.getInt("rankJoin");
					        	String rank ="";
					        	if (rankJoin == 1) {
									rank = rank + "关注会员；";
								} else if (rankJoin == 2) {
									rank = rank + "普通VIP会员；";
								} else if (rankJoin == 3) {
									rank = rank + "银级VIP会员；";
								} else if (rankJoin == 4) {
									rank = rank + "金级VIP会员；";
								}else rank = rank + "-；";
				        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
				            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
				            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
				            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
						           hm.put("pId", 0);  
					            hm.put("isParent", true); 
					            hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
					            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
					            list.add(hm);  
						  }  
							request.setAttribute("userJson", JSON.toJSONString(list));
							request.setAttribute("userId", userId);

					} else {
						message = "数据库连接已断开！";
					}
				} else {
					message = "未输入所要查询的会员编号，请输入！";
				}
			} else {
				message = "管理员未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			db.close();
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_referee_json.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_referee_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_belongJson_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		DBConn db = new DBConn();
		
		String message="";
		try{
			if(db.createConn()){
			conn = db.getConnection();
			 String userId = request.getParameter("userId");
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	       
	        String sql = "";
	        if(StringUtil.notNull(userId).equals(""))
	        	sql = "select * from users where userId='"+Constants.TOP_NODE+"'";
	       else sql= "select * from users where userId='"+userId+"'";
		        stmt =conn.createStatement();
		        rs = stmt.executeQuery(sql); 
		        if(rs.next()){
		        	 int rankJoin = rs.getInt("rankJoin");
			        	String rank ="";
			        	if (rankJoin == 1) {
							rank = rank + "关注会员；";
						} else if (rankJoin == 2) {
							rank = rank + "普通VIP会员；";
						} else if (rankJoin == 3) {
							rank = rank + "银级VIP会员；";
						} else if (rankJoin == 4) {
							rank = rank + "金级VIP会员；";
						}else rank = rank + "-；";
		        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
		        	
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
		            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
			        hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
		            hm.put("pId","0");
		            hm.put("isParent", true); 
		            list.add(hm);  
		        }  
		        request.setAttribute("userJson", JSON.toJSONString(list));
		        System.out.println("JSON.toJSONString(list):"+JSON.toJSONString(list));
	        } else {
				message = "数据库连接已断开！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_message.jsp");
					dispatcher.forward(request, response);
				}
			}
	    }  
	
	public void admin_refereeJson_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		DBConn db = new DBConn();
		
		String message="";
		try{
			if(db.createConn()){
			conn = db.getConnection();
		  String userId = request.getParameter("userId");
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	       String sql = "";
	       if(StringUtil.notNull(userId).equals(""))
	        	sql = "select * from users where userId='"+Constants.TOP_NODE+"'";
	       else sql= "select * from users where userId='"+userId+"'";
		        stmt =conn.createStatement();
		        rs = stmt.executeQuery(sql); 
		        if(rs.next()){
		        	 int rankJoin = rs.getInt("rankJoin");
			        	String rank ="";
			        	if (rankJoin == 1) {
							rank = rank + "关注会员；";
						} else if (rankJoin == 2) {
							rank = rank + "普通VIP会员；";
						} else if (rankJoin == 3) {
							rank = rank + "银级VIP会员；";
						} else if (rankJoin == 4) {
							rank = rank + "金级VIP会员；";
						}else rank = rank + "-；";
		        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
		            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
			          hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
		            if(StringUtil.notNull(userId).equals(""))
		            hm.put("pId","0");
		            hm.put("isParent", true); 
		            list.add(hm);  
		        }  
		        request.setAttribute("userJson", JSON.toJSONString(list));
	        } else {
				message = "数据库连接已断开！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_message.jsp");
					dispatcher.forward(request, response);
				}
			}
	    }  
	
	
	
	public void admin_referee_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		DBConn db = new DBConn();
		String message="";
		try{
			if(db.createConn()){
			conn = db.getConnection();
			 String userId = request.getParameter("userId");
	        List<User> ulist = new ArrayList<User>();
	        User user = new User();
	        if(StringUtil.notNull(userId).equals("")){
	        	user = getSelectUser(conn,Constants.TOP_NODE);
	        	userId = Constants.TOP_NODE;
	        }
	       else user = getSelectUser(conn,userId);
			if(user!=null){
				 ulist.add(user); 
				 String sql="select * from users where userByRefereeId='"+userId+"'";
				 stmt = conn.createStatement();
				 rs = stmt.executeQuery(sql);
				 while(rs.next()){
					 User user1 = new User();
					 user1.setUserId(rs.getString("userId"));
					 user1.setRankJoin(rs.getInt("rankJoin"));
					 user1.setRankManage(rs.getInt("rankManage"));
					 user1.setEntryTime(rs.getTimestamp("entryTime"));
					 user1.setState(rs.getInt("state"));
					 ulist.add(user1);
				 }
		        request.setAttribute("ulist", ulist);
			} else {
				message = "未查询到会员的信息！";
			}
	        } else {
				message = "数据库连接已断开！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_list.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_message.jsp");
					dispatcher.forward(request, response);
				}
			}
	    }  
	
	public User getSelectUser(Connection conn,String userId){
		String sql="select * from users where userId='"+userId+"'";
		 User user2 =null;
        try {
			stmt =conn.createStatement();
			rs= stmt.executeQuery(sql); 
        if(rs.next()){
        	user2 = new User();
        	user2.setUserId(rs.getString("userId"));
        	user2.setUserName(rs.getString("userName"));
        	user2.setRankManage(rs.getInt("rankManage"));
        	user2.setUserByAId(rs.getString("userByAId"));
        	user2.setUserByBId(rs.getString("userByBId"));
        	user2.setUserByCId(rs.getString("userByCId"));
        	user2.setEntryTime(rs.getTimestamp("entryTime"));
        	user2.setRankJoin(rs.getInt("rankJoinOld"));
        	user2.setTotalIncome(rs.getDouble("totalIncome"));
        	user2.setRmoney(rs.getDouble("rmoney"));
        }
        } catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        return user2;
	}

	
	public void belongJson_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		
		String message="";
		try{
			if(db.createConn()){
			conn = db.getConnection();
		  
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	        	String sql = "select * from users where userId='"+user.getUserId()+"'";
		        stmt =conn.createStatement();
		        rs = stmt.executeQuery(sql); 
		        if(rs.next()){
		        	 int rankJoin = rs.getInt("rankJoin");
			        	String rank ="";
			        	if (rankJoin == 1) {
							rank = rank + "关注会员；";
						} else if (rankJoin == 2) {
							rank = rank + "普通VIP会员；";
						} else if (rankJoin == 3) {
							rank = rank + "银级VIP会员；";
						} else if (rankJoin == 4) {
							rank = rank + "金级VIP会员；";
						}else rank = rank + "-；";
		        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
		            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
		            hm.put("iconOpen","../ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","../ztree/css/zTreeStyle/img/diy/1_close.png");
		            hm.put("pId","0");
		            hm.put("isParent", true); 
		            list.add(hm);  
		        }  
		        request.setAttribute("userJson", JSON.toJSONString(list));
	        } else {
				message = "数据库连接已断开！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_message.jsp");
					dispatcher.forward(request, response);
				}
			}
	    }  
	
	public void belongJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		DBConn db = new DBConn();
		conn = db.getConnection();
		   String id = request.getParameter("id");   
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	        
	        String sql = "select userId from users where id='"+id+"'";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        String userId ="";
	        if(rs.next()){
	        	userId = rs.getString(1);
	        }
	        sql = "select * from users where userByBelongId='"+userId+"' and state>'0' order by nodeTag asc";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        
	        while(rs.next()){
	        	 int rankJoin = rs.getInt("rankJoin");
		        	String rank ="";
		        	if (rankJoin == 1) {
						rank = rank + "关注会员；";
					} else if (rankJoin == 2) {
						rank = rank + "普通VIP会员；";
					} else if (rankJoin == 3) {
						rank = rank + "银级VIP会员；";
					} else if (rankJoin == 4) {
						rank = rank + "金级VIP会员；";
					}else rank = rank + "-；";
	        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
	            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
	            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
	            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")"); 
	            hm.put("pId", id);  
	            hm.put("icon","../ztree/css/zTreeStyle/img/diy/3.png");
	            hm.put("isParent", true); 
	            list.add(hm);  
	        }  
	        db.close();
	        response.getWriter().write(JSON.toJSONString(list));  
	      
	    }  
	
	
	public void refereeJson_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		
		String message="";
		try{
			if(db.createConn()){
			conn = db.getConnection();
		  
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	       
	        String sql = "select * from users where userId='"+user.getUserId()+"'";
		        stmt =conn.createStatement();
		        rs = stmt.executeQuery(sql); 
		        if(rs.next()){
		        	 int rankJoin = rs.getInt("rankJoin");
			        	String rank ="";
			        	if (rankJoin == 1) {
							rank = rank + "关注会员；";
						} else if (rankJoin == 2) {
							rank = rank + "普通VIP会员；";
						} else if (rankJoin == 3) {
							rank = rank + "银级VIP会员；";
						} else if (rankJoin == 4) {
							rank = rank + "金级VIP会员；";
						}else rank = rank + "-；";
		        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
		            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")");  //name属性，显示节点名称
		            hm.put("iconOpen","../ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","../ztree/css/zTreeStyle/img/diy/1_close.png");
		            hm.put("pId","0");
		            hm.put("isParent", true); 
		            list.add(hm);  
		        }  
		        request.setAttribute("userJson", JSON.toJSONString(list));
	        } else {
				message = "数据库连接已断开！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_message.jsp");
					dispatcher.forward(request, response);
				}
			}
	    }  
	
	public  void refereeJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		conn = db.getConnection();
		   String id = request.getParameter("id");  
	        String name = request.getParameter("name");  
	        String level = request.getParameter("level");  
	        String otherParam = request.getParameter("otherParam");  
	        System.out.println(id + "|" + name + "|" + level + "|" + otherParam); 
	        String sql = "select userId from users where id='"+id+"'";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        String userId ="";
	        if(rs.next()){
	        	userId = rs.getString(1);
	        }
	        sql = "select * from users where userByRefereeId='"+userId+"' and state>'0' order by id asc";
	        stmt =conn.createStatement();
	        rs = stmt.executeQuery(sql); 
	        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
	        while(rs.next()){
	        	 int rankJoin = rs.getInt("rankJoin");
		        	String rank ="";
		        	if (rankJoin == 1) {
						rank = rank + "关注会员；";
					} else if (rankJoin == 2) {
						rank = rank + "普通VIP会员；";
					} else if (rankJoin == 3) {
						rank = rank + "银级VIP会员；";
					} else if (rankJoin == 4) {
						rank = rank + "金级VIP会员；";
					}else rank = rank + "-；";
	        	rank=rank+StringUtil.parseToString(rs.getTimestamp("entryTime"), DateUtil.yyyyMMddHHmmss);
	            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
	            hm.put("id",rs.getInt("id"));//id属性  ，数据传递    
	            hm.put("name", rs.getString("userId")+"("+rs.getString("userName")+"；"+rank+")");  //name属性，显示节点名称
	            hm.put("pId", id);  
	            hm.put("icon","../ztree/css/zTreeStyle/img/diy/3.png");
	            hm.put("isParent", true); 
	            list.add(hm);  
	        }  
	        db.close();
	        response.getWriter().write(JSON.toJSONString(list));  
	      
	    }  
	
	public void admin_belong_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[2][4].equals("1")
						|| admin.getState().equals("1")) {
					String userId = StringUtil.notNull(request.getParameter("userId"));
					if (!userId.equals("")) {
						if (db.createConn()) {
							conn = db.getConnection();
						User up_user = us.findById(conn, userId);
						if(up_user!=null){
						request.getSession().setAttribute("up_user", up_user);
						} else {
							message = "所选会员不存在，请重新确认！";
						}
					} else {
						message = "数据库连接出错，请稍后再试或与管理员联系！";
					}
				} else {

					message = "未获得会员信息，请重新输入！";

				}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
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
						.getRequestDispatcher("belong_update_a.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("belong_update_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	public void admin_belong_update_save(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try {
			if (StringUtil.notNull(token_old).equals(token)) {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[2][4].equals("1")
						|| admin.getState().equals("1")) {
					String userId = StringUtil.notNull(request.getParameter("id"));
					String belongId = StringUtil.notNull(request.getParameter("belongId"));
					String nodeTagStr = StringUtil.notNull(request.getParameter("nodeTag"));
					if (!userId.equals("")) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							User up_user = getSaveUser(conn,
									userId);
							User belongUser = getSaveUser(conn,
									belongId);
							String sqlm = "select max(id) from users";	
							stmt = conn.createStatement();
							rs = stmt.executeQuery(sqlm);
							int num = 0;
							if(rs.next()){
								num = rs.getInt(1);
							}
						
						if(up_user!=null){
							if(belongUser!=null){
								if(!up_user.getUserByBelongId().equals(belongUser.getUserId())){
									if(up_user.getEntryTime().getTime()-belongUser.getEntryTime().getTime()>=0){
									User belongUser_old = getSaveUser(conn,
											up_user.getUserByBelongId());
									if(belongUser_old!=null){
										if(!nodeTagStr.equals("")){
										 int nodeTag = Integer.valueOf(nodeTagStr);
										 boolean b= us.checkNodeTag(nodeTag, belongUser);
										 if(b){
									
											List<String> slist = new ArrayList<String>();
										String table_name = "wsettle";
										
										double pv = 0;
										double price =0;
										String property ="";
										String property1 ="";
										if(up_user.getNodeTag()==1) {
											property="userByAId";
										}
										else if(up_user.getNodeTag()==2) {
										
											property="userByBId";
										}
										
										String belongNodeOld = up_user.getNode();
										String belongNodeABCOld = up_user.getNodeABC();
										String belongNode ="";
										String belongNodeABC ="";
										String abc = "";
										if(nodeTag==1){
											abc="A";
											property1="userByAId";
										}
										else if(nodeTag==2) {
											abc="B";
											property1="userByBId";
										}
										else if(nodeTag==3) {
											abc="C";
											property1="userByCId";
										}
										if(StringUtil.notNull(belongUser.getNode()).equals("")) {
											belongNode = String.valueOf(belongUser.getId());
											
											belongNodeABC = abc;
										}else{
											belongNode = belongUser.getNode()+","+String.valueOf(belongUser.getId());
											belongNodeABC = belongUser.getNodeABC()+","+abc;
										}
										if(us.updateBelongPv(conn, table_name, -pv, -price, belongNodeOld, belongNodeABCOld)){
										if(us.updateBelongPv(conn, table_name, pv, price, belongNode, belongNodeABC)){
										
								
									String sql= "update users set "+property+"='' where userId='"+belongUser_old.getUserId()+"'";
									slist.add(sql);
									sql= "update users set userByBelongId='"+belongUser.getUserId()+"',nodeTag='"+nodeTag+"',node='"+belongNode+"',nodeABC='"+belongNodeABC+"' where userId='"+up_user.getUserId()+"'";
									slist.add(sql);
									sql= "update users set "+property1+"='"+up_user.getUserId()+"' where userId='"+belongUser.getUserId()+"'";
									slist.add(sql);
									if(slist.size()>0){
										stmt = conn.createStatement();
										for(int i=0;i<slist.size();i++){
											stmt.addBatch(slist.get(i));
										}
										stmt.executeBatch();
									}
									List<String> ulist = new ArrayList<String>();
									User[] user = new User[num+1];
									String sqlu = "select * from users order by id asc for update";
									stmt = conn.createStatement();
									rs = stmt.executeQuery(sqlu);
									user[1] = new User();
									user[1].setUserId(Constants.TOP_NODE);
									user[1].setRefereeNode("");
									while(rs.next()){
										int id= rs.getInt("id");
										if(user[id]==null) user[id] = new User();
										user[id].setId(id);
										user[id].setUserId(rs.getString("userId")); 
										user[id].setUserName(rs.getString("userName"));
										user[id].setNode("");
										user[id].setNodeABC("");
										user[id].setNodeTag(rs.getInt("nodeTag"));
										user[id].setUserByBelongId(rs.getString("userByBelongId"));
									}
									 for(int i=1;i<user.length;i++){
									    if(user[i]!=null){
									    	if(!user[i].equals(Constants.TOP_NODE)){
									    		String sql2 = "select id from users where userId='"+user[i].getUserByBelongId()+"'";
												stmt = conn.createStatement();
												rs = stmt.executeQuery(sql2);
												if(rs.next()){
													int rid = rs.getInt(1);
											    	String node = "";
											    	String nodeABC="";
											    	String nodeStr = "";
											    	if(user[i].getNodeTag()==1) nodeStr = "A";
											    	else if(user[i].getNodeTag()==2) nodeStr = "B";
											    	else if(user[i].getNodeTag()==3) nodeStr = "C";
											    	if(user[rid].getNode().equals("")){
											    		node = String.valueOf(rid);
											    		nodeABC= nodeStr;
											    	}
											    	else{
											    		node = user[rid].getNode()+","+String.valueOf(rid);
											    		nodeABC = user[rid].getNodeABC()+","+nodeStr;
											    	}
											    	user[i].setNode(node);
											    	user[i].setNodeABC(nodeABC);
											    	String sql3  = "update users set node='"+user[i].getNode()+"',nodeABC='"+user[i].getNodeABC()+"' where userId='"+user[i].getUserId()+"'";
											    	ulist.add(sql3);
											    }
											    }
									    }
									}
									 if(ulist.size()>0){
									 stmt = conn.createStatement();
												for(int i=0;i<ulist.size();i++){
													stmt.addBatch(ulist.get(i));
													 if ((i % 50000 == 0 && i != 0) || i == (ulist.size() - 1)) { 
														 stmt.executeBatch();
														 stmt.close();
														 stmt = conn.createStatement();
													 }
												}
									 }
									
									message = "会员服务商关系变更成功，变更会员为"+up_user.getUserName()+"("+up_user.getUserId()+")，最新接点人："+belongUser.getUserName()+"("+belongUser.getUserId()+")！";
										cs.insetAdminLog(conn, admin.getAdminName(), message, date);
										}else {
												message = "最新服务商结算信息更新失败，请重试！";
											}
										

										}else {
											message = "结算信息获取失败，请重试！";
										}
									}else {
										message = "所指定的最新服务区域已被占用，请重新！";
									}
									 }else {
											message = "最新接点的服务区域信息获取失败，请重试！";
										}
									 }else {
											message = "原服务商信息获取失败，请重试！";
										}
											
									}else {
										message = "暂时不支持后期加盟会员作为前期加盟会员的接点人！";

									}
							}else {
								message = "最新服务商与原服务商一致，不需要修改！";

							}
						}else {
							message = "获取最新服务商信息失败，请重试！";

						}
						}else {
							message = "获取需要修改服务商的会员信息失败，请重试！";

						}
						conn.commit();
						conn.setAutoCommit(autoCommit);
					} else {
						message = "数据库连接出错，请稍后再试或与管理员联系！";

					}
				} else {
					message = "未获得会员信息，请重新输入！";
				}
				} else {
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
			} else {
				message = "用户未登陆，请重新登陆！";

			}
			} else {
				message="请勿重复提交数据，请在会员列表中查看是否保存成功！";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("belong_update_message.jsp");
				dispatcher.forward(request, response);
			

		}
	}
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void rankJoin_up_all_a(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message = "";
		// Timestamp date1 = new Timestamp(new Date().getTime());
		// java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		try{
			if (user != null) {
			if (db.createConn()) {
				conn = db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				String userId = request.getParameter("userId");
				User user1 = us.findById(conn, userId);
				Param p  = cs.getParam(conn, "报单资格");
				if (user1!= null) {
					if(p!=null){
					String rankJoinStr = StringUtil.notNull(request
							.getParameter("rankJoin"));
					String rankJoinOldStr = StringUtil.notNull(request
							.getParameter("rankJoinOld"));
					if(!rankJoinStr.equals("")){
					//	if(user1.getIsUp()==1){
					//	if((date.getTime()-user.getEntryTime().getTime())>=90*24*60*60*1000){
						rankJoin =Integer.valueOf(rankJoinStr);
						int rankJoinOld =Integer.valueOf(rankJoinOldStr);
						double emoney=0;
						if (rankJoin == 2) {
							emoney = p.getAmount_1();
						} else if (rankJoin == 3) {
							emoney = p.getAmount_2();
						} else if (rankJoin == 4) {
							emoney = p.getAmount_3();
						}
						if(emoney<=0) emoney = 0;
						user1.setEmoney(emoney);
						user1.setRankJoin(rankJoin);
						user1.setRankJoinOld(rankJoinOld);
						request.getSession().setAttribute("user1", user1);
					String sql = "select * from product where productType like '%报单产品%' and (team like '%"+Constants.TOP_TEAM+"%' or team like '%"+user1.getTeam()+"%') and state='1'  order by type desc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					Collection coll = new ArrayList();
					while (rs.next()) {
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						
						product.setType(rs.getInt("type"));
						product.setState(rs.getString("state"));
						product.setImageUrl(rs.getString("imageUrl"));
						coll.add(product);
					}
					request.setAttribute("coll", coll);
					
					Collection coll_adr = new ArrayList();
					String sqla = "select * from address where userId='"+user1.getUserId()+"' order by id desc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sqla);
					while(rs.next()){
						Address adr = new Address();
						adr.setId(rs.getInt("id"));
						adr.setAddress(rs.getString("address"));
						adr.setReceiver(rs.getString("receiver"));
						adr.setPhone(rs.getString("phone"));
						coll_adr.add(adr);
					} 
					request.setAttribute("coll_adr", coll_adr);		
				
					} else {
						message="你所选的等级信息有误，请重试！";
						
					}

					} else {
						message="系统参数设置有误，请与客服联系！";
						
					}
			} else {
				message="未查询到相应会员的信息，请重试！";
				
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
			.getRequestDispatcher("rankJoin_up_all_b.jsp");
	dispatcher.forward(request, response);
	}else{
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("rankJoin_up_message.jsp");
		dispatcher.forward(request, response);
	}
	
}

	}
	
	public void rankJoin_up_all_b(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		User user1 = (User) request.getSession().getAttribute("user1");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (user != null) {
				if (user1 != null) {
					if(db.createConn()){
						conn = db.getConnection();
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
					String addressId = StringUtil.notNull(request.getParameter("addressId"));
					if (!addressId.equals("")) {
						
						List<OrderDetail> olist = new ArrayList<OrderDetail>();
						Timestamp date1 = new Timestamp(new Date().getTime());
						java.sql.Timestamp date = new java.sql.Timestamp(
								date1.getTime());
						double totalprice = 0;
						double totalpv = 0;
						String rid = cs.createOrderId(date);
						
						for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if (Integer.valueOf(numstr[i]) > 0) {
									String sql = "select * from product where id='"
											+ Integer.valueOf(pid[i]) + "'";
									stmt = db.getStmt();
									rs = stmt.executeQuery(sql);
									if (rs.next()) {
										OrderDetail od = new OrderDetail();
										od.setOrderId(rid);
										od.setNum(Integer.valueOf(numstr[i]));
										od.setProductId(rs
												.getString("productId"));
										od.setProductName(rs
												.getString("productName"));
										od.setProductPrice(rs.getDouble("price"));
										od.setProductPv(rs.getDouble("pv"));
										od.setProductType(rs
												.getString("productType"));
										od.setType(rs.getInt("type"));
										od.setPrice(ArithUtil.mul(rs.getDouble("price")
												,Integer.valueOf(numstr[i])));
										od.setPv(ArithUtil.mul(rs.getDouble("pv")
												,Integer.valueOf(numstr[i])));
										olist.add(od);
										totalprice = ArithUtil.add(totalprice
												,ArithUtil.mul(rs.getDouble("price")
												, Integer.valueOf(numstr[i])));
										totalpv = ArithUtil.add(totalpv ,ArithUtil.mul( rs.getDouble("pv")
												,Integer.valueOf(numstr[i])));
										
									}
								}
							}
						}
						String sqla = "select * from address where id='"+addressId+"'";
						stmt = db.getStmtread();
						rs = stmt.executeQuery(sqla);
						Order orders = new Order();
						if(rs.next()){
							orders.setAddress(rs.getString("address"));
							orders.setReceiver(rs.getString("receiver"));
							orders.setPhone(rs.getString("phone"));
						} 
						orders.setOrderId(rid);
						orders.setuId(user1.getId());
						orders.setUserId(user1.getUserId());
						orders.setUserName(user1.getUserName());
						orders.setOrderType(5);
						orders.setPrice(totalprice);
						orders.setPv(totalpv);
						orders.setState(1);
						orders.setOrderTime(date);
						orders.setTag(1);
						orders.setUserByDeclarationId(user.getUserId());
						request.getSession().setAttribute("orders", orders);
						request.getSession().setAttribute("olist", olist);
						int token_new = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token_new));
					} else {

						message = "收货人信息不完善，请返回上一页重新完善信息！";

					}
					
				} else {
					message = "数据库连接有误，请重新登陆！";

				}
				} else {

					message = "未获得注册会员信息，请重新注册！";

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
						.getRequestDispatcher("rankJoin_up_all_comments.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_message.jsp");
				dispatcher.forward(request, response);
			}

		}
	}
	
	public synchronized void rankJoin_up_all_comments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User) request.getSession().getAttribute("sys_user");
		Order orders = (Order) request.getSession().getAttribute("orders");
		String message = "";
		try {
				if (user != null) {
						String comments = StringUtil.notNull(request.getParameter("comments"));
						orders.setComments(comments);
						request.getSession().setAttribute("orders", orders);
					
				} else {
					message= "用户未登陆，请重新登陆！";
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_all_c.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	
	
	public  void furthest_node(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String message="";
		try{
			if(user!=null){
			if(db.createConn()){
			conn = db.getConnection();
			String sql1="select * from users where  userByBelongId='"+user.getUserId()+"' and nodeTag='1'";
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql1);
			 if(rs.next()){
				 User user1 = new User();
					user1.setId(rs.getInt("id"));
					user1.setUserId(rs.getString("userId"));
					user1.setUserName(rs.getString("userName"));
					user1.setNode(rs.getString("node"));
					List<User> ulist = new ArrayList<User>();
					String node = StringUtil.notNull(user1.getNode())+","+String.valueOf(user1.getId())+",";
					String sql2="select * from users where  userByBelongId='"+user1.getUserId()+"'  order by entryTime asc";
					 stmt = conn.createStatement();
					 rs = stmt.executeQuery(sql2);
					 while(rs.next()){
						 User user2 = new User();
						 user2.setId(rs.getInt("id"));
						 user2.setUserId(rs.getString("userId"));
						 user2.setUserName(rs.getString("userName"));
						 user2.setEntryTime(rs.getTimestamp("entryTime"));
						 user2.setNode(rs.getString("node"));
						 user2.setState(rs.getInt("state"));
						 ulist.add(user2);
					 }
					
					sql2="select * from users where node like'"+node+"%'  order by entryTime asc";
					System.out.println(sql2);
					 stmt = conn.createStatement();
					 rs = stmt.executeQuery(sql2);
					 while(rs.next()){
						 User user2 = new User();
						 user2.setId(rs.getInt("id"));
						 user2.setUserId(rs.getString("userId"));
						 user2.setUserName(rs.getString("userName"));
						 user2.setEntryTime(rs.getTimestamp("entryTime"));
						 user2.setNode(rs.getString("node"));
						 user2.setState(rs.getInt("state"));
						 ulist.add(user2);
					 }
					 
					 if(ulist.size()>0){
						 int t = 0;
						 int num = 0;
						 for(int i=0;i<ulist.size();i++){
							 int num1 = ulist.get(i).getNode().split(",").length;
							 if(num1>num){
								 num=num1;
								 t = i;
							 }
						 }
						 if(num>0){
							 message = message+"A区最远端会员是："+ulist.get(t).getUserId()+"("+ulist.get(t).getUserName()+")；";
						 }else{
							 message = message+"A区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
						 }
					 }else{
						 message = message+"A区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
					 }
			 }else{
				 message = message+"A区暂无会员；";
			 }
					 
			String sql3="select * from users where userByBelongId='"+user.getUserId()+"' and nodeTag='2'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql3);	
			 if(rs.next()){
					User user1 = new User();
							user1.setId(rs.getInt("id"));
							user1.setUserId(rs.getString("userId"));
							user1.setUserName(rs.getString("userName"));
							user1.setNode(rs.getString("node"));
							List<User> ulist1 = new ArrayList<User>();
							String node = StringUtil.notNull(user1.getNode())+","+String.valueOf(user1.getId())+",";
							String sql2="select * from users where  userByBelongId='"+user1.getUserId()+"' order by entryTime asc";
							 stmt = conn.createStatement();
							 rs = stmt.executeQuery(sql2);
							 while(rs.next()){
								 User user2 = new User();
								 user2.setId(rs.getInt("id"));
								 user2.setUserId(rs.getString("userId"));
								 user2.setUserName(rs.getString("userName"));
								 user2.setEntryTime(rs.getTimestamp("entryTime"));
								 user2.setNode(rs.getString("node"));
								 user2.setState(rs.getInt("state"));
								 ulist1.add(user2);
							 }
							sql2="select * from users where node like'"+node+"%'  order by entryTime asc";
							 stmt = conn.createStatement();
							 rs = stmt.executeQuery(sql2);
							 while(rs.next()){
								 User user2 = new User();
								 user2.setId(rs.getInt("id"));
								 user2.setUserId(rs.getString("userId"));
								 user2.setUserName(rs.getString("userName"));
								 user2.setEntryTime(rs.getTimestamp("entryTime"));
								 user2.setNode(rs.getString("node"));
								 user2.setState(rs.getInt("state"));
								 ulist1.add(user2);
							 }
							 
							 if(ulist1.size()>0){
								 int t = 0;
								 int num = 0;
								 for(int i=0;i<ulist1.size();i++){
									 int num1 = ulist1.get(i).getNode().split(",").length;
									 if(num1>num){
										 num=num1;
										 t = i;
									 }
								 }
								 if(num>0){
									 message = message+"B区最远端会员是："+ulist1.get(t).getUserId()+"("+ulist1.get(t).getUserName()+")；";
								 }else{
									 message = message+"B区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
								 }
							 }else{
								 message = message+"B区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
							 }
					 }else{
						 message = message+"B区暂无会员；";
					 }
		        } else {
					message = "数据库连接已断开！";
				}
			} else {
				message = "会员未登录，请重新登录！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("furthest_node.jsp");
					dispatcher.forward(request, response);
			}
	}
	
	public  void admin_furthest_node(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try{
			if(admin!=null){
			if(db.createConn()){
			conn = db.getConnection();
			
			String userId = StringUtil.notNull(request.getParameter("userId"));
			 String sql="select * from users where userId='"+userId+"'";
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql);
			 if(rs.next()){
				 User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserId(rs.getString("userId"));
					user.setUserName(rs.getString("userName"));
					user.setNode(rs.getString("node"));
				String sql1="select * from users where  userByBelongId='"+user.getUserId()+"' and nodeTag='1'";
				 stmt = conn.createStatement();
				 rs = stmt.executeQuery(sql1);
				 if(rs.next()){
					 User user1 = new User();
						user1.setId(rs.getInt("id"));
						user1.setUserId(rs.getString("userId"));
						user1.setUserName(rs.getString("userName"));
						user1.setNode(rs.getString("node"));
						List<User> ulist = new ArrayList<User>();
						String node = StringUtil.notNull(user1.getNode())+","+String.valueOf(user1.getId())+",";
						String sql2="select * from users where  userByBelongId='"+user1.getUserId()+"'  order by entryTime asc";
						 stmt = conn.createStatement();
						 rs = stmt.executeQuery(sql2);
						 while(rs.next()){
							 User user2 = new User();
							 user2.setId(rs.getInt("id"));
							 user2.setUserId(rs.getString("userId"));
							 user2.setUserName(rs.getString("userName"));
							 user2.setEntryTime(rs.getTimestamp("entryTime"));
							 user2.setNode(rs.getString("node"));
							 user2.setState(rs.getInt("state"));
							 ulist.add(user2);
						 }
						
						sql2="select * from users where node like'"+node+"%'  order by entryTime asc";
						System.out.println(sql2);
						 stmt = conn.createStatement();
						 rs = stmt.executeQuery(sql2);
						 while(rs.next()){
							 User user2 = new User();
							 user2.setId(rs.getInt("id"));
							 user2.setUserId(rs.getString("userId"));
							 user2.setUserName(rs.getString("userName"));
							 user2.setEntryTime(rs.getTimestamp("entryTime"));
							 user2.setNode(rs.getString("node"));
							 user2.setState(rs.getInt("state"));
							 ulist.add(user2);
						 }
						 
						 if(ulist.size()>0){
							 int t = 0;
							 int num = 0;
							 for(int i=0;i<ulist.size();i++){
								 int num1 = ulist.get(i).getNode().split(",").length;
								 if(num1>num){
									 num=num1;
									 t = i;
								 }
							 }
							 if(num>0){
								 message = message+userId+"("+user.getUserName()+")A区最远端会员是："+ulist.get(t).getUserId()+"("+ulist.get(t).getUserName()+")；";
							 }else{
								 message = message+userId+"("+user.getUserName()+")A区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
							 }
						 }else{
							 message = message+userId+"("+user.getUserName()+")A区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
						 }
				 }else{
					 message = message+userId+"("+user.getUserName()+")A区暂无会员；";
				 }
						 
				String sql3="select * from users where userByBelongId='"+user.getUserId()+"' and nodeTag='2'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql3);	
				 if(rs.next()){
						User user1 = new User();
								user1.setId(rs.getInt("id"));
								user1.setUserId(rs.getString("userId"));
								user1.setUserName(rs.getString("userName"));
								user1.setNode(rs.getString("node"));
								List<User> ulist1 = new ArrayList<User>();
								String node = StringUtil.notNull(user1.getNode())+","+String.valueOf(user1.getId())+",";
								String sql2="select * from users where  userByBelongId='"+user1.getUserId()+"' order by entryTime asc";
								 stmt = conn.createStatement();
								 rs = stmt.executeQuery(sql2);
								 while(rs.next()){
									 User user2 = new User();
									 user2.setId(rs.getInt("id"));
									 user2.setUserId(rs.getString("userId"));
									 user2.setUserName(rs.getString("userName"));
									 user2.setEntryTime(rs.getTimestamp("entryTime"));
									 user2.setNode(rs.getString("node"));
									 user2.setState(rs.getInt("state"));
									 ulist1.add(user2);
								 }
								sql2="select * from users where node like'"+node+"%'  order by entryTime asc";
								 stmt = conn.createStatement();
								 rs = stmt.executeQuery(sql2);
								 System.out.println(sql2);
								 while(rs.next()){
									 User user2 = new User();
									 user2.setId(rs.getInt("id"));
									 user2.setUserId(rs.getString("userId"));
									 user2.setUserName(rs.getString("userName"));
									 user2.setEntryTime(rs.getTimestamp("entryTime"));
									 user2.setNode(rs.getString("node"));
									 user2.setState(rs.getInt("state"));
									 ulist1.add(user2);
								 }
								 
								 if(ulist1.size()>0){
									 int t = 0;
									 int num = 0;
									 for(int i=0;i<ulist1.size();i++){
										 int num1 = ulist1.get(i).getNode().split(",").length;
										 if(num1>num){
											 num=num1;
											 t = i;
										 }
									 }
									 if(num>0){
										 message = message+"B区最远端会员是："+ulist1.get(t).getUserId()+"("+ulist1.get(t).getUserName()+")；";
									 }else{
										 message = message+"B区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
									 }
								 }else{
									 message = message+"B区最远端会员为："+user1.getUserId()+"("+user1.getUserName()+")；";
								 }
						 }else{
							 message = message+"B区暂无会员；";
						 }
				
			 } else {
					message = "为查询到该会员的信息，请核实！";
				}
		
		        } else {
					message = "数据库连接已断开！";
				}
			} else {
				message = "用户未登录，请重新登录！";
			}
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				db.close();
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("furthest_node_message.jsp");
					dispatcher.forward(request, response);
			}
	}

	public void admin_user_hide(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[2][6].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update users set is_hide='1' where userId='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt.close();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							request.setAttribute("message",
									"用户隐藏成功，锁定用户将无法在会员列表中查看！");
							Timestamp date = new Timestamp(new Date().getTime());
							cs.insetAdminLog(conn, admin.getAdminName(), "用户隐藏成功，锁定用户将无法在会员列表中查看！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_lock_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_user_hide_reset(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[2][6].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update users set is_hide='0' where userId='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt.close();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							request.setAttribute("message",
									"用户取消隐藏成功，在会员列表中查看！");
							Timestamp date = new Timestamp(new Date().getTime());
							cs.insetAdminLog(conn, admin.getAdminName(), "用户取消隐藏成功，在会员列表中查看！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_hide_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_user_hide_list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List result = new ArrayList();
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = StringUtil
				.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request
				.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {

			if (db.createConn()) {
				String sql = "select * from users where (userId like'%" + userId
							+ "%' or userName like'%" + userId
							+ "%') and is_hide='1'  order by id desc";
				
				stmt = db.getStmtread();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserId(rs.getString("userId"));
					user.setUserName(rs.getString("userName"));
					user.setUserByBelongId(rs.getString("userByBelongId"));
					user.setUserByDeclarationId(rs
							.getString("userByDeclarationId"));
					user.setUserByRefereeId(rs.getString("userByRefereeId"));
					user.setNodeTag(rs.getInt("nodeTag"));
					user.setEmoney(rs.getDouble("emoney"));
					user.setSmoney(rs.getDouble("smoney"));
					user.setDmoney(rs.getDouble("dmoney"));
					user.setRmoney(rs.getDouble("rmoney"));
					user.setPmoney(rs.getDouble("pmoney"));
					user.setPayTag(rs.getInt("payTag"));
					user.setRankJoin(rs.getInt("rankJoin"));
					user.setRankManage(rs.getInt("rankManage"));
					user.setState(rs.getInt("state"));
					user.setEntryTime(rs.getTimestamp("entryTime"));
					user.setValidtyTime(rs.getTimestamp("validtyTime"));
					result.add(user);
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
				request.setAttribute("userId", userId);
				
			} else {
				request.setAttribute("message", "数据库连接已断开！");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_hide_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_user_authority_edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][15].equals("1") || admin.getState().equals("1")) {
				if (db.createConn()) {
					conn = db.getConnection();
					String userId = request.getParameter("id");
					String sql = "select * from users where userId='"+userId+"' for update";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						User user = new User();
						user.setUserId(rs.getString("userId"));
						user.setId(rs.getInt("id"));
						user.setUserName(rs.getString("userName"));
						user.setIsBelongList(rs.getInt("is_belong_list"));
						user.setIsRefereeList(rs.getInt("is_referee_list"));
						user.setIsUserList(rs.getInt("is_user_list"));
						user.setIsDeclaration(rs.getInt("is_declaration"));
						user.setPayTag(rs.getInt("payTag"));
						request.setAttribute("admin_user", user);
						int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
						request.getSession().setAttribute("token",
								String.valueOf(token));
					}else {
						request.setAttribute("message", "会员信息获取失败！");
					}
				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
				} else {
					request.setAttribute("message", "你没有进行该操作的权限！");
				}
			}else {
				request.setAttribute("message", "管理员未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_authority_edit.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_user_authority_save(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		
			
		try {
			
			if (admin != null) {
				if (StringUtil.notNull(token_old).equals(token)) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][15].equals("1") || admin.getState().equals("1")) {
				if (db.createConn()) {
					conn = db.getConnection();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					String userId = request.getParameter("id");
					String isRefereeList = request.getParameter("isRefereeList");
					String isBelongList = request.getParameter("isBelongList");
					String isDeclaration = request.getParameter("isDeclaration");
					String isUserList = request.getParameter("isUserList");
					String payTag = request.getParameter("payTag");
					String sql = "select * from users where userId='"+userId+"' for update";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						String sql1 = "update users set is_referee_list='"+isRefereeList
								+"',is_belong_list='"+isBelongList+"',is_declaration='"
								+isDeclaration+"',is_user_list='"+isUserList+"',payTag='"+payTag+"' where userId='"+userId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql1);
						request.setAttribute("message", userId+"会员权限更新成功！");
						request.setAttribute("user_id", userId);
					}else {
						request.setAttribute("message", "会员信息获取失败！");
					}
					conn.commit();
					conn.setAutoCommit(autoCommit);
				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
				} else {
					request.setAttribute("message", "你没有进行该操作的权限！");
				}
				}else {
					request.setAttribute("message", "请勿重复提交数据！");
				}
			}else {
				request.setAttribute("message", "管理员未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("user_authority_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}


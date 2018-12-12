package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.JoinInfo;
import com.pojo.Settle;
import com.pojo.User;
import com.pojo.WSettle;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.ArithUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Statement stmt = null;

	private Connection conn = null;

	private ResultSet rs = null;

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
			try {
				Login(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (method.equals("save")) {
			try {
				save(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (method.equals("loginout")) {
			Logout(request, response);
		} else if (method.equals("register")) {
			Register(request, response);
		} else if (method.equals("list")) {
			AdminList(request, response);
		} else if (method.equals("isExit")) {
			isExit(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("editRank")) {
			editRank(request, response);
		} else if (method.equals("updateRank")) {
			updateRank(request, response);
		} else if (method.equals("initPsw")) {
			initPsw(request, response);
		} else if (method.equals("initAdmin")) {
			initAdmin(request, response);
		} else if (method.equals("del")) {
			del(request, response);
		} else if (method.equals("delAll")) {
			delAll(request, response);
		} else if (method.equals("initDataBase")) {
			initDataBase(request, response);
		} else if (method.equals("admin_password_update")) {
			try {
				admin_password_update(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("updateOldPv")) {
			try {
				updateOldPv(request, response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if (method.equals("sql_list")) {
			try {
				sql_list(request, response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	public void Login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConn db = new DBConn();
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String yzm = request.getParameter("yzm");
		String ip = request.getParameter("realIP");
		String rand = (String) request.getSession().getAttribute("rand");
		try {
			if (adminName == null || password == null) {
				request.setAttribute("message", "用户名和密码不能为空！");
			} else if (rand == null) {
				request.setAttribute("message", "验证码有误！");
			} else if (!rand.equals(yzm)) {
				request.setAttribute("message", "验证码有误！");
			} else {
				if (db.createConn()) {
					conn = db.getConnection();
					String sql = "select * from admin where adminName ='"
							+ adminName + "' and state>'0' and password='"
							+ MD5.GetMD5Code(password) + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						Admin admin = new Admin();
						admin.setId(rs.getInt("id"));
						admin.setAdminName(rs.getString("adminName"));
						admin.setPassword(rs.getString("password"));
						admin.setRank(rs.getString("rank"));
						admin.setState(rs.getString("state"));
						request.getSession().setAttribute("sys_admin", admin);
						Timestamp date1 = new Timestamp(new Date().getTime());
						java.sql.Timestamp date = new java.sql.Timestamp(
								date1.getTime());
						cs.insetAdminLog(conn, admin.getAdminName(), "管理员登陆系统，访问IP为"+ip, date);

					} else {
						request.setAttribute("message", "用户名不存在或密码有误！");
					}
				} else {
					request.setAttribute("message", "数据库连接已断开！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("success_login.jsp");
			dispatcher.forward(request, response);
		}

	}

	public void Logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("sys_admin");
		response.sendRedirect("login_out.jsp");
	}

	public void Register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void AdminList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List result = new ArrayList();
		String adminName = request.getParameter("adminName");
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 60;
		try {

			if (db.createConn()) {
				String sql = "";
				System.out.println(adminName);
				if (adminName == null)
					sql = "select * from admin where state>'1'  order by id asc";
				else if (adminName.equals(""))
					sql = "select * from admin where state>'1'  order by id asc";
				else {
					sql = "select * from admin where adminName like('%"
							+ adminName + "%') and state>'1'  order by id asc";
					request.setAttribute("adminName", adminName);
				}
				stmt = db.getStmtread();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Admin admin = new Admin();
					admin.setId(rs.getInt("id"));
					admin.setAdminName(rs.getString("adminName"));
					admin.setPassword(rs.getString("password"));
					admin.setPassword2(rs.getString("password2"));
					admin.setRank(rs.getString("rank"));
					admin.setState(rs.getString("state"));
					admin.setEntryTime(rs.getTimestamp("entryTime"));
					admin.setEndTime(rs.getTimestamp("endTime"));
					result.add(admin);
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
			} else {
				request.setAttribute("message", "数据库连接已断开！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void isExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConn db = new DBConn();
		String adminName = request.getParameter("adminName");
		int tag = 0;
		try {
			if (db.createConn()) {
				if (StringUtil.notNull(adminName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from admin where adminName ='"
							+ adminName + "' order by id asc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if (rs.next())
						tag = 1;
					else
						tag = 2;
				}
			} else {
				tag = 0;
			}

			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", tag);
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

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token));
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("admin_add.jsp");
		dispatcher.forward(request, response);
	}

	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));

		DBConn db = new DBConn();
		try {
			if (admin != null) {
				if (StringUtil.notNull(token_old).equals(token)) {
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if (rankstr[9][1].equals("1")
							|| admin.getState().equals("1")) {
						if (db.createConn()) {

							String adminName = request
									.getParameter("adminName");
							String tel = request
									.getParameter("tel");
							String password = request.getParameter("password");
							if (!(StringUtil.notNull(adminName).equals("") || StringUtil
									.notNull(password).equals(""))) {
								String sql = "select * from admin where adminName ='"
										+ adminName + "'";
								stmt = db.getStmtread();
								rs = stmt.executeQuery(sql);
								if (!rs.next()) {
									db.close();
									conn = db.getConnection();
									boolean autoCommit = conn.getAutoCommit();
									conn.setAutoCommit(false);
									Timestamp date1 = new Timestamp(
											new Date().getTime());
									java.sql.Timestamp date = new java.sql.Timestamp(
											date1.getTime());
									String sqli = "insert into admin(adminName,tel,password,password2,state,entryTime,endTime) values('"
											+ adminName
											+ "','"
											+ tel
											+ "','"
											+ MD5.GetMD5Code(password)
											+ "','"
											+ MD5.GetMD5Code(password)
											+ "','2','"
											+ date
											+ "','"
											+ date
											+ "')";
									stmt = conn.createStatement();
									stmt.executeUpdate(sqli);
									conn.commit();
									conn.setAutoCommit(autoCommit);
									request.setAttribute("message",
											"用户保存成功，请核对用户信息：用户名" + adminName
													+ ",用户密码为" + password + "。");
								} else {
									request.setAttribute("message",
											"用户名已被注册，请重新录入！");
								}
							} else {
								request.setAttribute("message",
										"用户名和用户密码不能为空，请重新录入！");
							}

						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message",
								"您没有该操作权限，如有需要请与系统管理员联系！");
					}
				} else {
					request.setAttribute("message",
							"请勿重复提交数据，请在管理员列表中查看是否保存成功！");
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
					.getRequestDispatcher("admin_message_add.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void editRank(HttpServletRequest request,
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

	public void updateRank(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		String idstr = request.getParameter("id");
		try {
			if (admin!= null) {
				if (!StringUtil.notNull(idstr).equals("")) {
					if (db.createConn()) {
						String[] rankstr = request.getParameterValues("rankstr");
						String[][] str = new String[10][30];
						String rank = "";
						for (int i = 0; i < 10; i++)
							for (int j = 0; j < 30; j++)
								str[i][j] = "0";
						if(rankstr!=null){
						for (int i = 0; i < rankstr.length; i++) {
							if (rankstr[i].length() == 3) {
								str[Integer.valueOf(rankstr[i].substring(0, 1))][Integer
										.valueOf(rankstr[i].substring(1, 3))] = "1";
							}
						}
						}
						for (int i = 0; i < 10; i++)
							for (int j = 0; j < 30; j++)
								if (i < 9 && j == 29)
									rank = rank + str[i][j] + ",";
								else
									rank = rank + str[i][j];
						int id = Integer.valueOf(idstr);
						String sql = "select * from admin where id ='" + id
								+ "' for update";
						conn = db.getConnection();
						boolean autoCommit = conn.getAutoCommit();
						conn.setAutoCommit(false);
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
							String sqli = "update admin set rank='" + rank
									+ "' where id ='" + id + "'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							admin1.setRank(rank);
							request.setAttribute("admin1", admin1);
							message = "用户权限修改成功！";
						} else {
							message = "用户信息有误，请重新选择！";
						}
						conn.commit();
						conn.setAutoCommit(autoCommit);
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
			e.printStackTrace();
			message = "数据操作有误，请重试！";
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_rank.jsp");
			dispatcher.forward(request, response);

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
							db.close();
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

	public void initAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String message = "";
		DBConn db = new DBConn();
		try {

			if (db.createConn()) {
				String password = MD5.GetMD5Code("aa11111111");
				String sql = "select * from admin where adminName ='admin'";
				conn = db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				stmt = db.getStmtread();
				rs = stmt.executeQuery(sql);
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(
						date1.getTime());
				if (!rs.next()) {
					String sqli = "insert into admin(adminName,password,password2,state,entryTime,endTime) "
							+ "values('admin','"
							+ password
							+ "','"
							+ password
							+ "','1','"
							+ date
							+ "','" + date + "')";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message = "超级管理员初始化成功，请保管好管理员信息！";
				} else {
					message = "超级管理员已经初始化，请与管理员联系！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			} else {
				message = "数据库连接已断开！";
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

	public void initDataBase(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String message = "";
		DBConn db = new DBConn();
		try {

			if (db.createConn()) {
				String sql1 = "CREATE TABLE If Not Exists admin (id int(11) NOT NULL AUTO_INCREMENT, adminName varchar(50) NOT NULL,password char(32) not null,password2 char(32) not null, rank char(109) DEFAULT '0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000',state char(1) DEFAULT '0',entryTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), UNIQUE KEY adminName(adminName)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql2 = "CREATE TABLE If Not Exists inventory (id int(11) NOT NULL AUTO_INCREMENT, inventoryName varchar(20) NOT NULL,  linkman varchar(20) DEFAULT '', address varchar(200) DEFAULT '', phone char(15) DEFAULT '', state char(1) DEFAULT '0', entryTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY id(id), UNIQUE KEY inventoryName(inventoryName)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql3 = "CREATE TABLE If Not Exists productType (id int(11) NOT NULL AUTO_INCREMENT, typeName varchar(20) NOT NULL, state char(1) DEFAULT '0', entryTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY id(id), UNIQUE KEY typeName(typeName)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql4 = "CREATE TABLE If Not Exists product (id int(11) NOT NULL AUTO_INCREMENT,productId varchar(20) NOT NULL,productName varchar(100) NOT NULL, productType varchar(105) NOT NULL,specification varchar(50) DEFAULT '',features varchar(200) DEFAULT '',price int(11) DEFAULT 0,pv int(11) DEFAULT 0, num int(11) DEFAULT 0,imageUrl varchar(200) DEFAULT '',type tinyint(1) DEFAULT '0',state char(1) DEFAULT '0', entryTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL, PRIMARY KEY (productId), UNIQUE KEY productId(productId), UNIQUE KEY id(id),index(productType)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql5 = "CREATE TABLE If Not Exists emoneyDetail (id int(11) NOT NULL AUTO_INCREMENT, userId char(10) NOT NULL,userName varchar(50) NOT NULL, amount double DEFAULT '0', balance double DEFAULT '0',payType TINYINT(1) DEFAULT '0',tradeType varchar(50) DEFAULT '',summary varchar(50) DEFAULT '',entryTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(userId),index(tradeType),index(entryTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql6 = "CREATE TABLE If Not Exists smoneyDetail (id int(11) NOT NULL AUTO_INCREMENT, userId char(10) NOT NULL, userName varchar(50) NOT NULL, amount double DEFAULT '0', balance double DEFAULT '0',payType TINYINT(1) DEFAULT '0',tradeType varchar(50) DEFAULT '',summary varchar(50) DEFAULT '',entryTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(userId),index(tradeType),index(entryTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql7 = "CREATE TABLE If Not Exists dmoneyDetail (id int(11) NOT NULL AUTO_INCREMENT, userId char(10) NOT NULL, userName varchar(50) NOT NULL, amount double DEFAULT '0', balance double DEFAULT '0',payType TINYINT(1) DEFAULT '0',tradeType varchar(50) DEFAULT '',summary varchar(50) DEFAULT '',entryTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(userId),index(tradeType),index(entryTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql8 = "CREATE TABLE If Not Exists rmoneyDetail (id int(11) NOT NULL AUTO_INCREMENT, userId char(10) NOT NULL,userName varchar(50) NOT NULL,  amount double DEFAULT '0', balance double DEFAULT '0',payType TINYINT(1) DEFAULT '0',tradeType varchar(50) DEFAULT '',summary varchar(50) DEFAULT '',entryTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(userId),index(tradeType),index(entryTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql9 = "CREATE TABLE If Not Exists settle (id int(11) NOT NULL AUTO_INCREMENT, totalPerformance double DEFAULT '0', newPerformance double DEFAULT '0',totalPrice double DEFAULT '0', newPrice double DEFAULT '0',totalNum int(11) DEFAULT '0',newNum int(11) DEFAULT '0',tag int(5) DEFAULT '0',weekTag int(5) DEFAULT '0', state varchar(2) DEFAULT '0',startTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL,entryTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(weekTag),index(tag),index(startTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql10 = "CREATE TABLE If Not Exists orders (id int(11) NOT NULL AUTO_INCREMENT, orderId char(17) NOT NULL,uId int(11) not null, userId varchar(10) NOT NULL,userName varchar(50) DEFAULT '', userByDeclarationId varchar(10) NOT NULL,receiver varchar(25) DEFAULT '',phone varchar(20) DEFAULT '',address varchar(50) DEFAULT '',inventory varchar(50) DEFAULT '', price double DEFAULT '0',pv double DEFAULT '0',orderType TINYINT(1) DEFAULT '0',adminByConfirmId varchar(50) default '',confirmTime datetime default null,adminByDeliveryId varchar(50) DEFAULT NULL,adminByReviewerId varchar(50) DEFAULT NULL,express varchar(25) DEFAULT '',expressNum varchar(25) DEFAULT '',state char(1) DEFAULT '0',orderTime datetime DEFAULT NULL,deliveryTime datetime DEFAULT NULL,reviewerTime datetime DEFAULT NULL,PRIMARY KEY (orderId),UNIQUE KEY orderId(orderId),UNIQUE KEY id(id), index(orderTime),index(userId),index(orderType)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql11 = "CREATE TABLE If Not Exists orderDetail (id int(11) NOT NULL AUTO_INCREMENT, orderId char(17) NOT NULL,productId varchar(20) NOT NULL,productName varchar(100) DEFAULT '', productType varchar(105) NOT NULL,type tinyint(1) DEFAULT '0',  productPrice double DEFAULT '0',productPv double DEFAULT '0',price double DEFAULT '0',pv double DEFAULT '0', num int(11) DEFAULT '0',PRIMARY KEY (id), UNIQUE KEY id(id), index(orderId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql12 = "CREATE TABLE If Not Exists address (id int(11) NOT NULL AUTO_INCREMENT, userId varchar(10) NOT NULL,receiver varchar(20) DEFAULT '',address varchar(100) DEFAULT '',phone varchar(13) DEFAULT '', tag char(1) DEFAULT '0',state TINYINT(1) DEFAULT '0',entryTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(userId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql13 = "CREATE TABLE If Not Exists chargeApply (id int(11) NOT NULL AUTO_INCREMENT, applyId char(17) NOT NULL, userId varchar(10) NOT NULL,userName varchar(50) DEFAULT '', amount double DEFAULT '0',remark varchar(200) DEFAULT '',type TINYINT(1) DEFAULT '0',adminByReviewerId varchar(50) DEFAULT NULL,accountId varchar(25) default '',accountName varchar(50) default '',bankName varchar(50) default '',state TINYINT(1) DEFAULT '0',applyTime datetime DEFAULT NULL,reviewTime datetime DEFAULT NULL,PRIMARY KEY (applyId),UNIQUE KEY applyId(applyId),UNIQUE KEY id(id),index(userId),index(applyTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql14 = "CREATE TABLE If Not Exists uidpool (id int(11) NOT NULL AUTO_INCREMENT, uid int(11) NOT NULL, tag TINYINT DEFAULT '0',PRIMARY KEY (uid),UNIQUE KEY uid(uid),UNIQUE KEY id(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql15 = "CREATE TABLE If Not Exists users (id int(11) NOT NULL AUTO_INCREMENT, userId varchar(10) NOT NULL, userName varchar(10) NOT NULL,emoney double DEFAULT '0',smoney double DEFAULT '0',dmoney double DEFAULT '0',rmoney double DEFAULT '0',integral double DEFAULT '0',totalIncome double DEFAULT '0',rankJoin TINYINT(1) DEFAULT '0',rankJoinTag TINYINT(1) DEFAULT '0',joinPV double DEFAULT '0',rankManage TINYINT(2) DEFAULT '0',rankManageTag TINYINT(1) DEFAULT '0',nodeTag TINYINT(1) DEFAULT '0',userByBelongId varchar(10) DEFAULT NULL,userByDeclarationId varchar(10) DEFAULT NULL,userByRefereeId varchar(10) DEFAULT NULL,refereeNum int(11) DEFAULT '0',node text DEFAULT NULL,nodeABC text DEFAULT NULL,refereeNode text DEFAULT NULL,declarationNode text DEFAULT NULL,refereeAll text DEFAULT NULL,isEmpty TINYINT(1) DEFAULT '0',userByAId varchar(10) DEFAULT NULL,userByBId varchar(10) DEFAULT NULL,userByCId varchar(10) DEFAULT NULL,payTag TINYINT(1) DEFAULT '0',state TINYINT(1) DEFAULT '0',entryTime datetime DEFAULT NULL,PRIMARY KEY (userId),UNIQUE KEY uid(userId),UNIQUE KEY id(id),index(entryTime),index(userByBelongId),index(userByRefereeId),index(userByDeclarationId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql16 = "CREATE TABLE If Not Exists userinfo (id int(11) NOT NULL AUTO_INCREMENT, userId varchar(10) NOT NULL, userName varchar(10) NOT NULL,password char(32) NOT NULL,password2 char(32) NOT NULL,sex char(2) DEFAULT '',age tinyint DEFAULT '0',documentType varchar(50) DEFAULT '',numId varchar(25) DEFAULT '',tel varchar(20) DEFAULT '',phone varchar(20) DEFAULT '',email varchar(50) DEFAULT '',qq varchar(25) DEFAULT '',weixin varchar(50) DEFAULT '',province varchar(50) DEFAULT '',city varchar(25) DEFAULT '',area varchar(25) DEFAULT '',address varchar(100) DEFAULT '',accountId varchar(25) DEFAULT '',bankName varchar(50) DEFAULT '',bankAdr varchar(50) DEFAULT '',accountName varchar(25) DEFAULT '',PRIMARY KEY (userId),UNIQUE KEY uid(userId),UNIQUE KEY id(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql17 = "CREATE TABLE If Not Exists timeparam (id int(11) NOT NULL AUTO_INCREMENT, paramName varchar(20) NOT NULL,startTime datetime DEFAULT NULL,weekNum double DEFAULT '0',monthNum tinyint DEFAULT '0',PRIMARY KEY (paramName),UNIQUE KEY uid(paramName),UNIQUE KEY id(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql18 = "CREATE TABLE If Not Exists  integralDetail (id int(11) NOT NULL AUTO_INCREMENT, userId char(10) NOT NULL,userName varchar(50) NOT NULL,  amount double DEFAULT '0', balance double DEFAULT '0',payType TINYINT(1) DEFAULT '0',tradeType varchar(50) DEFAULT '',summary varchar(50) DEFAULT '',entryTime datetime DEFAULT NULL,PRIMARY KEY (id), UNIQUE KEY id(id), index(userId),index(tradeType),index(entryTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql19 = "CREATE TABLE If Not Exists bankAccount (id int(11) NOT NULL AUTO_INCREMENT, accountName varchar(50) NOT NULL,  bankName varchar(100) DEFAULT '', accountId varchar(25) DEFAULT '', state TINYINT(1) DEFAULT '0', entryTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY id(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql20 = "CREATE TABLE If Not Exists transferDetail (id int(11) NOT NULL AUTO_INCREMENT,userByReceiveId char(10) NOT NULL,  userByReceiveIdName varchar(50) DEFAULT '',userBySendId char(10) NOT NULL,  userBySendName varchar(50) DEFAULT '', type TINYINT(1) DEFAULT '0', amount double default '0',entryTime datetime DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY id(id),index(userBySendId),index(type)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql21 = "CREATE TABLE If Not Exists accountSupplement (id int(11) NOT NULL AUTO_INCREMENT,uId int(11) NOT NULL, userId char(10) NOT NULL,  userName varchar(50) DEFAULT '', admin varchar(50) DEFAULT '', type TINYINT(1) DEFAULT '0',payType TINYINT(1) DEFAULT '0', amount double default '0',summary varchar(100) DEFAULT '',entryTime datetime DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY id(id),index(userId),index(type)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql22 = "CREATE TABLE If Not Exists withDrew (id int(11) NOT NULL AUTO_INCREMENT,applyId char(17) NOT NULL, uId int(11) NOT NULL, userId char(10) NOT NULL,  userName varchar(50) DEFAULT '', admin varchar(50) DEFAULT '', amount double default '0',actualAmount double default '0',fee double default '0',accountName varchar(50) NOT NULL,  bankName varchar(50) DEFAULT '',bankAdr varchar(50) NOT NULL,  accountId varchar(25) DEFAULT '',state tinyint default '0',reviewTime datetime DEFAULT NULL, applyTime datetime DEFAULT NULL, PRIMARY KEY (applyId), UNIQUE KEY applyId(applyId),UNIQUE KEY id(id),index(userId),index(applyTime)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql23 = "CREATE TABLE  If Not Exists  productDetail (id int(11) NOT NULL AUTO_INCREMENT,pId varchar(20) NOT NULL, productId varchar(20) NOT NULL,productName varchar(100) DEFAULT '',specification varchar(50) DEFAULT '',productType varchar(105) NOT NULL,productPrice double DEFAULT '0',productPv double DEFAULT '0',price double DEFAULT '0',pv double DEFAULT '0', num int(11) DEFAULT '0',imageUrl varchar(200) DEFAULT '',PRIMARY KEY (id), UNIQUE KEY id(id), index(productId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql24 = "CREATE TABLE  If Not Exists inventoryApply (id int(11) NOT NULL AUTO_INCREMENT, applyId char(17) NOT NULL,adminByOperatorId varchar(50) default '',adminByReviewerId varchar(50) default '',inventory varchar(20) DEFAULT '',type TINYINT(1) default '0',state TINYINT(1) default '0',reviewTime datetime DEFAULT NULL,time datetime DEFAULT NULL,PRIMARY KEY (applyId), UNIQUE KEY applyId(applyId),UNIQUE KEY id(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				String sql25 = "CREATE TABLE  If Not Exists inventoryDetail (id int(11) NOT NULL AUTO_INCREMENT, applyId char(17) NOT NULL,productId varchar(20) NOT NULL,productName varchar(100) DEFAULT '', productType varchar(105) NOT NULL,type tinyint(1) DEFAULT '0',price double DEFAULT '0', num int(11) DEFAULT '0',PRIMARY KEY (id), UNIQUE KEY id(id),index(applyId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";

				conn = db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				stmt = db.getStmtread();
				stmt.addBatch(sql1);
				stmt.addBatch(sql2);
				stmt.addBatch(sql3);
				stmt.addBatch(sql4);
				stmt.addBatch(sql5);
				stmt.addBatch(sql6);
				stmt.addBatch(sql7);
				stmt.addBatch(sql8);
				stmt.addBatch(sql9);
				stmt.addBatch(sql10);
				stmt.addBatch(sql11);
				stmt.addBatch(sql12);
				stmt.addBatch(sql13);
				stmt.addBatch(sql14);
				stmt.addBatch(sql15);
				stmt.addBatch(sql16);
				stmt.addBatch(sql17);
				stmt.addBatch(sql18);
				stmt.addBatch(sql19);
				stmt.addBatch(sql20);
				stmt.addBatch(sql21);
				stmt.addBatch(sql22);
				stmt.addBatch(sql23);
				stmt.addBatch(sql24);
				stmt.addBatch(sql25);
				stmt.executeBatch();
				stmt.close();
				conn.commit();
				conn.setAutoCommit(autoCommit);
				message = "数据库初始化成功！";
			} else {
				message = "数据库连接已断开！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			message = "数据操作有误，请重试！";
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("init_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void admin_password_update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
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
								String sql = "select * from admin where id='"
										+ admin.getId() + "' and password='"
										+ MD5.GetMD5Code(psw) + "' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								boolean b = rs.next();

								if (b) {
									String sqlu = "update admin set password='"
											+ MD5.GetMD5Code(password)
											+ "' where id='" + admin.getId()
											+ "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sqlu);
									request.setAttribute("message",
											"管理员用户登陆密码修改成功，请退出系统重新登陆！");
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
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
		}
	}



	public void updateOldPv(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
		try {
			if (admin != null) {

				if (db.createConn()) {
					conn = db.getConnection();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);

					jxl.Workbook readwb = null;
					List<JoinInfo> ulist = new ArrayList<JoinInfo>();

					// 构建Workbook对象, 只读Workbook对象

					// 直接从本地文件创建Workbook

					InputStream instream = new FileInputStream(
							"/Users/xiangfu_txe/dumps/23.xls");

					readwb = Workbook.getWorkbook(instream);
					// Sheet的下标是从0开始
					// 获取第一张Sheet表
					Sheet readsheet = readwb.getSheet(0);
					// 获取Sheet表中所包含的总列数
					int rsColumns = readsheet.getColumns();
					// 获取Sheet表中所包含的总行数
					int rsRows = readsheet.getRows();
					// 获取指定单元格的对象引用
					for (int i = 1; i < 2961; i++)

					{
						JoinInfo user = new JoinInfo();

						for (int j = 0; j < rsColumns; j++)

						{
							Cell cell = readsheet.getCell(j, i);

							if (j == 0) {
								user.setUserId(cell.getContents());
							}
							if (j == 1) {
								user.setPrice(Double.valueOf(cell.getContents()));
							} else if (j == 2) {
								user.setPv(Double.valueOf(cell.getContents()));
							}
						}
						ulist.add(user);
					}
					List<String> slist = new ArrayList<String>();
					for (int i = 0; i < ulist.size(); i++) {
						String sql = "select * from users where userIdOld='"
								+ ulist.get(i).getUserId() + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						String userId = "";
						if (rs.next()) {
							userId = rs.getString("userId");
						} else {
							message = ulist.get(i).getUserId() + "业绩信息不存在";
						}
						String sqlu = "update joinInfo set price='"
								+ ulist.get(i).getPrice() + "',pv='"
								+ ulist.get(i).getPv() + "' where userId='"
								+ userId + "'";
						slist.add(sqlu);
						if (!message.equals(""))
							break;
					}
					if (message.equals("")) {
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
						request.setAttribute("message", "会员业绩导入成功！");
					} else {
						request.setAttribute("message", message);
						conn.rollback();
					}

					conn.commit();
					conn.setAutoCommit(autoCommit);

				} else {
					request.setAttribute("message", "数据库连接已断开！");
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
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	@SuppressWarnings("rawtypes")
	public void sql_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		List<String> slist = new ArrayList<String>();
		try {
			if(admin!=null){
			if(db.createConn()){
				conn= db.getConnection();
				String pageNoStr = request.getParameter("pageNoStr");
				String pageSizeStr = request.getParameter("pageSizeStr");
				int pageNo = 1;
				 int pageSize = 60;
				 String path = "/usr/local/tomcat/webapps/ROOT/db/daily/qinkuai";

	    	        // get the folder list   
	    	        slist = getFile(path);
	    	       Collections.sort(slist,Collections.reverseOrder());
			        Collection coll = new ArrayList();
					if(slist.size()>0){
						if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
						if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
						int startIndex = pageSize*(pageNo-1);
						int endIndex = pageSize*pageNo;
						if(slist.size()<endIndex) endIndex = slist.size();
						coll = slist.subList(startIndex, endIndex);
						Pager pager = new Pager(pageSize,pageNo,slist.size(),coll);
						request.setAttribute("pager", pager);
						Timestamp date1 = new Timestamp(
								new Date().getTime());
						java.sql.Timestamp date = new java.sql.Timestamp(
								date1.getTime());
						cs.insetAdminLog(conn, admin.getAdminName(), "查看备份数据库资料信息！", date);
					}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("sql_list.jsp");
		dispatcher.forward(request, response);
	}
	}

	private List<String> getFile(String path){   
	    // get file list where the path has   
	    File file = new File(path);   
	    // get the folder list   
	    File[] array = file.listFiles();   
	      List<String> slist = new ArrayList<String>();
	    for(int i=0;i<array.length;i++){   
	        if(array[i].isFile()){   
	            // only take file name   
	           slist.add(array[i].getName());   
	        }else if(array[i].isDirectory()){   
	            getFile(array[i].getPath());   
	        }   
	    }   
	    return slist;
	}   

}

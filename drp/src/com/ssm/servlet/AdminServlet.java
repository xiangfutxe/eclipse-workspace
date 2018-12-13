package com.ssm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;

import com.ssm.pojo.User;
import com.ssm.dao.AdminDao;
import com.ssm.dao.DeptDao;
import com.ssm.dao.JobDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.WRewardDao;
import com.ssm.dao.WRewardExtremeDao;
import com.ssm.dao.WSettleDao;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.Dept;
import com.ssm.pojo.Job;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICustomService cs = new CustomService();

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
			login(request, response);

		}else if (method.equals("list")) {
			list(request, response);

		}else if (method.equals("addAdmin")) {
			addAdmin(request, response);

		}else if (method.equals("saveAdmin")) {
			saveAdmin(request, response);

		}else if (method.equals("isExit")) {
			isExit(request, response);

		}else if (method.equals("admin_detail")) {
			editAdmin(request, response);

		}else if (method.equals("updateAdmin")) {
			updateAdmin(request, response);

		}else if (method.equals("initPsw")) {
			initPsw(request, response);

		}else if (method.equals("editRank")) {
			editRank(request, response);

		}else if (method.equals("updateRank")) {
			updateRank(request, response);

		}else if (method.equals("logout")) {
			logout(request, response);

		}else if (method.equals("admin_password_update")) {
			admin_password_update(request, response);

		}else if (method.equals("importUser")) {
			importUser(request, response);

		}else if (method.equals("importAdr")) {
			importAdr(request, response);

		}else if (method.equals("importCenter")) {
			importCenter(request, response);

		}else if (method.equals("admin_importPrice_add")) {
			admin_importPrice_add(request, response);

		}else if (method.equals("admin_importPrice_save")) {
			admin_importPrice_save(request, response);
		}else if (method.equals("admin_price_summary")) {
			admin_price_summary(request, response);
		}else if (method.equals("admin_price_summary_13_15")) {
			admin_price_summary_13_15(request, response);
		}else if (method.equals("sql_list")) {
			sql_list(request, response);
		}else if (method.equals("updateBelongNode")) {
			updateBelongNode(request, response);
		}else if (method.equals("updateWSettle")) {
			updateWSettle(request, response);
		}else if (method.equals("checkWSettle")) {
			checkWSettle(request, response);
		}else if (method.equals("exportExcel_checkWReard")) {
			exportExcel_checkWReard(request, response);
		}else if (method.equals("exportExcel_checkWReard_8")) {
			exportExcel_checkWReard_8(request, response);
		}else if (method.equals("exportExcel_checkWReard_2_18")) {
			exportExcel_checkWReard_2_18(request, response);
		}else if (method.equals("updateRankJoin")) {
			updateRankJoin(request, response);
		}else if (method.equals("updateUserRefereeNum")) {
			updateUserRefereeNum(request, response);
		}else if (method.equals("updateTotalIncome")) {
			updateTotalIncome(request, response);
		}else if (method.equals("updateNodeId")) {
			updateNodeId(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String password = StringUtil.notNull(request.getParameter("password"));
		String message = "";
		try {
			if (adminName == null || password == null) {
				message = "用户名和密码不能为空！";
			} else {
				AdminDao adminDao = new AdminDao();
				Admin admin = adminDao.login(adminName, MD5.GetMD5Code(password));
				if(admin!=null){
					request.getSession().setAttribute("sys_admin", admin);
				} else {
						message = "用户名不存在或密码有误！";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("success_login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("sys_admin");
		response.sendRedirect("login_out.jsp");
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String realName = StringUtil.notNull(request.getParameter("realName"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Admin admin1 = new Admin();
			admin1.setAdminName(adminName);
			admin1.setRealName(realName);
			admin1.setState("2");
				AdminDao adminDao = new AdminDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adminDao.findAdmin(admin1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("adminName", adminName);
				request.setAttribute("realName", realName);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("admin_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("admin.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void addAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		try {
			if(admin!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					DeptDao deptDao = new DeptDao();
					JobDao jobDao = new JobDao();
					List<Dept> depts  = deptDao.findAllDept();
					List<Job> jobs = jobDao.findAllJob();
					request.setAttribute("depts",depts);
					request.setAttribute("jobs",jobs);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("admin_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("admin_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void saveAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String adminName = StringUtil.notNull(request.getParameter("adminName"));
	String realName = StringUtil.notNull(request.getParameter("realName"));
	String password = StringUtil.notNull(request.getParameter("password"));
	String jobId = StringUtil.notNull(request.getParameter("jobId"));
	String deptId = StringUtil.notNull(request.getParameter("deptId"));
	String tel = StringUtil.notNull(request.getParameter("tel"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				if (StringUtil.notNull(token_old).equals(token)) {
			
				DeptDao deptDao = new DeptDao();
				JobDao jobDao = new JobDao();
				Dept dept = deptDao.findDeptById(Integer.valueOf(deptId));
				Job job = jobDao.findJobById(Integer.valueOf(jobId));
				if(dept!=null){
					if(job!=null){
				AdminDao adminDao = new AdminDao();
				Admin admin1 = new Admin();
				admin1.setAdminName(adminName);
				admin1.setRealName(realName);
				admin1.setPassword(MD5.GetMD5Code(password));
				admin1.setPassword2(MD5.GetMD5Code(password));
				admin1.setJobId(job.getId());
				admin1.setJobName(job.getName());
				admin1.setDeptId(dept.getId());
				admin1.setDeptName(dept.getName());
				admin1.setTel(tel);
				admin1.setState("2");
				admin1.setEntryTime(date);
				admin1.setEndTime(date);
				message = adminDao.saveAdmin(admin1);
				admin1=null;
					}else{
						message = "职位信息获取失败。";
					}
				}else{
					message = "部门信息获取失败。";
				}
				}else{
					message = "请勿重复提交数据。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_add_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void editAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = (String) request.getParameter("id");
		String  message= "";
		try {
			if(admin!=null){
				if(!StringUtil.notNull(id).equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					DeptDao deptDao = new DeptDao();
					JobDao jobDao = new JobDao();
					AdminDao adminDao = new AdminDao();
					List<Dept> depts  = deptDao.findAllDept();
					List<Job> jobs = jobDao.findAllJob();
					Admin admin1 = adminDao.findAdminById(Integer.valueOf(id));
					if(admin1!=null){
					request.setAttribute("depts",depts);
					request.setAttribute("jobs",jobs);
					request.setAttribute("admin1",admin1);
					}else{
						message = "用户信息获取失败。";
					}
				}else{
					message = "用户ID获取失败。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("admin_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("admin_detail.jsp");
					dispatcher.forward(request, response);
				}
		}
	}
	
	public void editRank(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = (String) request.getParameter("id");
		String  message= "";
		try {
			if(admin!=null){
				if(!StringUtil.notNull(id).equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					AdminDao adminDao = new AdminDao();
					Admin admin1 = adminDao.findAdminById(Integer.valueOf(id));
					if(admin1!=null){
					request.setAttribute("admin1",admin1);
					}else{
						message = "用户信息获取失败。";
					}
				}else{
					message = "用户ID获取失败。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("admin_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("admin_rank.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void updateRank(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				if (StringUtil.notNull(token_old).equals(token)) {
					String[] rankstr = request.getParameterValues("rankstr");
					System.out.println("rankstr:"+rankstr);
					
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
					System.out.println("rank:"+rank);

				AdminDao adminDao = new AdminDao();
				Admin admin1 = new Admin();
				admin1.setId(Integer.valueOf(id));
				admin1.setRank(rank);
				admin1.setEndTime(date);
				adminDao.updateAdmin(admin1);
				adminDao = new AdminDao();
				admin1 = adminDao.findAdminById(admin1.getId());
				request.setAttribute("admin1", admin1);
				admin1=null;
				message = "权限修改成功。";
				}else{
					error = "请勿重复提交数据。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(error.equals("")){
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_rank.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", error);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("admin_message.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}
	
	public void updateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	String adminName = StringUtil.notNull(request.getParameter("adminName"));

	String realName = StringUtil.notNull(request.getParameter("realName"));
	String jobId = StringUtil.notNull(request.getParameter("jobId"));
	String deptId = StringUtil.notNull(request.getParameter("deptId"));
	String tel = StringUtil.notNull(request.getParameter("tel"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				if (StringUtil.notNull(token_old).equals(token)) {
				DeptDao deptDao = new DeptDao();
				JobDao jobDao = new JobDao();
				Dept dept = deptDao.findDeptById(Integer.valueOf(deptId));
				Job job = jobDao.findJobById(Integer.valueOf(jobId));
				if(dept!=null){
					if(job!=null){
				AdminDao adminDao = new AdminDao();
				Admin admin1 = new Admin();
				admin1.setId(Integer.valueOf(id));
				admin1.setAdminName(adminName);
				admin1.setRealName(realName);
				admin1.setJobId(job.getId());
				admin1.setJobName(job.getName());
				admin1.setDeptId(dept.getId());
				admin1.setDeptName(dept.getName());
				admin1.setTel(tel);
				admin1.setEndTime(date);
				message = adminDao.updateAdmin(admin1);
				admin1=null;
					}else{
						message = "职位信息获取失败。";
					}
				}else{
					message = "部门信息获取失败。";
				}
				}else{
					message = "请勿重复提交数据。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void initPsw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));

		String message = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				AdminDao adminDao = new AdminDao();
				Admin admin1 = new Admin();
				admin1.setId(Integer.valueOf(id));
				admin1.setAdminName(admin1.getAdminName());
				admin1.setPassword(MD5.GetMD5Code("12345678"));
				admin1.setEndTime(date);
				adminDao.updateAdmin(admin1);
				message = "用户密码初始化成功，初始化密码为12345678。";
				admin1=null;
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_password_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				String psw = StringUtil.notNull(request.getParameter("pwd"));
				String password = StringUtil.notNull(request.getParameter("password"));
				if(!psw.equals("")){
					if(!password.equals("")){
						if(!psw.equals(password)){
				Timestamp date = new Timestamp(new Date().getTime());
				AdminDao adminDao = new AdminDao();
				Admin admin1 = new Admin();
				admin1.setId(admin.getId());
				admin1.setPassword(MD5.GetMD5Code(password));
				admin1.setEndTime(date);
				adminDao.updateAdmin(admin1);
				message = "用户密码重置成功，新密码为："+password+"。";
				admin1=null;
						}else{
							request.setAttribute("message", "新旧密码不能一样，请重试！");
						}

						}else{
							request.setAttribute("message", "新密码不能为空，请重试！");
						}
					}else{
						request.setAttribute("message", "原密码有误，请重试！");
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙中，请稍后再试！");
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	
	public void isExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		int tag = 0;
		try {
				if (StringUtil.notNull(adminName).equals("")) {
					tag = 0;
				} else {
					AdminDao adminDao = new AdminDao();
					Admin admin1 = adminDao.findAdminByName(adminName);
					if(admin1!=null){
							tag=1;
					}else{
						tag=2;
					}
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
		} 
	}
	
	public void importUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				jxl.Workbook readwb = null;
				List<User> ulist = new ArrayList<User>();

				// 构建Workbook对象, 只读Workbook对象

				// 直接从本地文件创建Workbook

				InputStream instream = new FileInputStream(
						"/Users/xiangfu_txe/dumps/hdj-2.xls");
				readwb = Workbook.getWorkbook(instream);
				// Sheet的下标是从0开始
				// 获取第一张Sheet表
				Sheet readsheet = readwb.getSheet(0);
				// 获取Sheet表中所包含的总列数
				int rsColumns = readsheet.getColumns();
				// 获取Sheet表中所包含的总行数
				int rsRows = readsheet.getRows();
				// 获取指定单元格的对象引用
				for (int i = 1; i < 17150; i++)

				{
					User user = new User();
					
					for (int j = 0; j < rsColumns; j++)

					{
						Cell cell = readsheet.getCell(j, i);
						if (j == 1) {
							user.setUserId(cell.getContents());
						}else if (j == 2) {
							user.setUserName(cell.getContents());
						} else if (j == 3) {
								user.setRankJoin(Integer.valueOf(cell.getContents()));
								user.setRankJoinOld(Integer.valueOf(cell.getContents()));
						} else if (j == 4){
							if(!StringUtil.notNull(cell.getContents()).equals(""))
								user.setRankManage(Integer.valueOf(cell.getContents()));
							else user.setRankManage(0);
						}else if (j == 5){
							user.setUserByBelongId(cell.getContents());
						}else if (j == 6){
							user.setNodeTag(Integer.valueOf(cell.getContents()));
						}else if (j == 7){
							user.setUserByRefereeId(cell.getContents());
						}else if (j == 8){
							user.setUserByDeclarationId(cell.getContents());
						}else if (j == 9)
							user.setTel(cell.getContents());
						else if (j == 10){
							user.setEmoney(Double.valueOf(cell
									.getContents()));
						}else if (j == 11){
							user.setSmoney(Double.valueOf(cell
									.getContents()));
						}else if (j == 12){
							user.setDmoney(Double.valueOf(cell
									.getContents()));
					}else if (j == 13){
							user.setRmoney(Double.valueOf(cell
									.getContents()));
					}else if (j == 16){
							user.setAccountName(cell
									.getContents());
					}else if (j == 17){
							user.setAccountId(cell
									.getContents());
					}else if (j == 18){
							user.setBankName(cell
									.getContents());
					}else if (j == 19){
							user.setBankAdr(cell
									.getContents());
				}else if (j == 21){
							user.setIsEmpty(Integer.valueOf(cell
									.getContents()));
			}else if (j == 22){
							user.setPayTag(Integer.valueOf(cell
									.getContents()));
		}else if (j == 23){
							user.setState(2);
	}else if (j == 24){
							user.setTotalPerformance(Double.valueOf(cell
									.getContents()));
}else if (j == 25){
							user.setTotalPrice(Double.valueOf(cell
									.getContents()));
}else if (j == 26){
							user.setAtpv(Double.valueOf(cell
									.getContents()));
			}else if (j == 27){
							user.setAcpv(Double.valueOf(cell
									.getContents()));
						}else if (j == 28){
							user.setBtpv(Double.valueOf(cell
									.getContents()));
}else if (j == 29){
							user.setBcpv(Double.valueOf(cell
									.getContents()));
}else if (j == 30){
							user.setRtpv(Double.valueOf(cell
									.getContents()));
}else if (j == 31){
							user.setRefereeNum(Integer.valueOf(cell
									.getContents()));
}else if (j == 32){
							user.setPassword(cell
									.getContents());
}else if (j == 33){
							user.setPassword2(cell
									.getContents());
}else if (j == 34){
							if(!StringUtil.notNull(cell.getContents()).equals(""))
							user.setCenterId(2);
}else if (j == 35){
							user.setCenterName(cell
									.getContents());
}else if (j == 36){
							user.setCenterPsw(cell
									.getContents());
}else if (j == 37){
							user.setProvince(cell
									.getContents());
}else if (j == 38){
							user.setCity(cell
									.getContents());
}else if (j == 39){
							user.setArea(cell
									.getContents());
}else if (j == 40){
							user.setAddress(cell
									.getContents());
						}else if (j == 41){
							if(!StringUtil.notNull(cell.getContents()).equals(""))
								user.setType(Integer.valueOf(cell.getContents()));
							else user.setType(0);
						}else if (j == 42){
								user.setPhone(cell.getContents());
						}else if (j == 44){
							user.setDocumentType(cell.getContents());
						}else if (j == 45){
							user.setNumId(cell.getContents());
						}else if (j == 46){
							user.setSex(cell.getContents());
						}else if (j == 47){
							user.setRaiseNum(Integer.valueOf(cell.getContents()));
						}else if (j == 48) {
							user.setAtprice(Double.valueOf(cell.getContents()));
						}else if (j == 49) {
							user.setBtprice(Double.valueOf(cell.getContents()));
						} else if (j == 50) {
							user.setRtprice(Double.valueOf(cell.getContents()));
						}else if (j == 51) {
							user.setRtprice1(Double.valueOf(cell.getContents()));
						}else if (j == 52) {
							user.setRtpv1(Double.valueOf(cell.getContents()));
						}else if (j == 53) {
							user.setIsHide(Integer.valueOf(cell.getContents()));
						}
					}
					ulist.add(user);
				}
				AdminDao adminDao = new AdminDao();
				Timestamp date = new Timestamp(StringUtil.parseToDate("2017-11-12 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				message=adminDao.saveEmptyUser(ulist, date);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙中，请稍后再试！");
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void importUserUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				jxl.Workbook readwb = null;
				List<User> ulist = new ArrayList<User>();

				// 构建Workbook对象, 只读Workbook对象

				// 直接从本地文件创建Workbook

				InputStream instream = new FileInputStream(
						"/usr/src/hdj-2.xls");
				readwb = Workbook.getWorkbook(instream);
				// Sheet的下标是从0开始
				// 获取第一张Sheet表
				Sheet readsheet = readwb.getSheet(0);
				// 获取Sheet表中所包含的总列数
				int rsColumns = readsheet.getColumns();
				// 获取Sheet表中所包含的总行数
				int rsRows = readsheet.getRows();
				// 获取指定单元格的对象引用
				for (int i = 1; i < 17150; i++)

				{
					User user = new User();
					
					for (int j = 0; j < rsColumns; j++)
					{
						Cell cell = readsheet.getCell(j, i);
						if (j == 1) {
							user.setUserId(cell.getContents());
						}else if (j == 2) {
							user.setUserName(cell.getContents());
						} else if (j == 3) {
								user.setRankJoin(Integer.valueOf(cell.getContents()));
								user.setRankJoinOld(Integer.valueOf(cell.getContents()));
						} else if (j == 4){
							if(!StringUtil.notNull(cell.getContents()).equals(""))
								user.setRankManage(Integer.valueOf(cell.getContents()));
							else user.setRankManage(0);
						}else if (j == 5)
							user.setUserByBelongId(cell.getContents());
						else if (j == 6)
							user.setNodeTag(Integer.valueOf(cell.getContents()));
						else if (j == 7)
							user.setUserByRefereeId(cell.getContents());
						else if (j == 8)
							user.setUserByDeclarationId(cell.getContents());
						else if (j == 9)
							user.setTel(cell.getContents());
						else if (j == 10)
							user.setEmoney(Double.valueOf(cell
									.getContents()));
						else if (j == 11)
							user.setSmoney(Double.valueOf(cell
									.getContents()));
						else if (j == 12)
							user.setDmoney(Double.valueOf(cell
									.getContents()));
						else if (j == 13)
							user.setRmoney(Double.valueOf(cell
									.getContents()));
						else if (j == 16)
							user.setAccountName(cell
									.getContents());
						else if (j == 17)
							user.setAccountId(cell
									.getContents());
						else if (j == 18)
							user.setBankName(cell
									.getContents());
						else if (j == 19)
							user.setBankAdr(cell
									.getContents());
						else if (j == 21)
							user.setIsEmpty(Integer.valueOf(cell
									.getContents()));
						else if (j == 22)
							user.setPayTag(Integer.valueOf(cell
									.getContents()));
						else if (j == 23)
							user.setState(2);
						else if (j == 24)
							user.setTotalPerformance(Double.valueOf(cell
									.getContents()));
						else if (j == 25)
							user.setTotalPrice(Double.valueOf(cell
									.getContents()));
						else if (j == 26)
							user.setAtpv(Double.valueOf(cell
									.getContents()));
						else if (j == 27)
							user.setAcpv(Double.valueOf(cell
									.getContents()));
						else if (j == 28)
							user.setBtpv(Double.valueOf(cell
									.getContents()));
						else if (j == 29)
							user.setBcpv(Double.valueOf(cell
									.getContents()));
						else if (j == 30)
							user.setRtpv(Double.valueOf(cell
									.getContents()));
						else if (j == 31)
							user.setRefereeNum(Integer.valueOf(cell
									.getContents()));
						else if (j == 32)
							user.setPassword(cell
									.getContents());
						else if (j == 33)
							user.setPassword2(cell
									.getContents());
						
						else if (j == 34)
							if(!StringUtil.notNull(cell.getContents()).equals(""))
							user.setCenterId(2);
						else if (j == 35)
							user.setCenterName(cell
									.getContents());
						else if (j == 36)
							user.setCenterPsw(cell
									.getContents());
						else if (j == 37)
							user.setProvince(cell
									.getContents());
						else if (j == 38)
							user.setCity(cell
									.getContents());
						else if (j == 39)
							user.setArea(cell
									.getContents());
						else if (j == 40)
							user.setAddress(cell
									.getContents());
						else if (j == 41){
						if(!StringUtil.notNull(cell.getContents()).equals(""))
							user.setType(Integer.valueOf(cell.getContents()));
						else user.setType(0);
						}else if (j == 42)
								user.setPhone(cell.getContents());
						else if (j == 44)
							user.setDocumentType(cell.getContents());
						else if (j == 45)
							user.setNumId(cell.getContents());
						else if (j == 46)
							user.setSex(cell.getContents());
						else if (j == 47)
							user.setRaiseNum(Integer.valueOf(cell.getContents()));
						else if (j == 48) {
							user.setAtprice(Double.valueOf(cell.getContents()));
						}else if (j == 49) {
							user.setBtprice(Double.valueOf(cell.getContents()));
						} else if (j == 50) {
							user.setRtprice(Double.valueOf(cell.getContents()));
						}else if (j == 51) {
							user.setRtprice1(Double.valueOf(cell.getContents()));
						}else if (j == 52) {
							user.setRtpv1(Double.valueOf(cell.getContents()));
						}else if (j == 53) {
							user.setIsHide(Integer.valueOf(cell.getContents()));
						}
					}
					ulist.add(user);
				}
				AdminDao adminDao = new AdminDao();
				Timestamp date = new Timestamp(StringUtil.parseToDate("2017-11-12 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				message=adminDao.saveEmptyUser(ulist, date);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙中，请稍后再试！");
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void importAdr(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				jxl.Workbook readwb = null;
				List<Address> ulist = new ArrayList<Address>();
				// 构建Workbook对象, 只读Workbook对象
				// 直接从本地文件创建Workbook
				InputStream instream = new FileInputStream(
						"/Users/xiangfu_txe/dumps/hdj-ad-1.xls");
				readwb = Workbook.getWorkbook(instream);
				// Sheet的下标是从0开始
				// 获取第一张Sheet表
				Sheet readsheet = readwb.getSheet(0);
				// 获取Sheet表中所包含的总列数
				int rsColumns = readsheet.getColumns();
				// 获取Sheet表中所包含的总行数
				int rsRows = readsheet.getRows();
				// 获取指定单元格的对象引用
				for (int i = 1; i < 1472; i++)

				{
					Address adr = new Address();
					
					for (int j = 0; j < rsColumns; j++)

					{
						Cell cell = readsheet.getCell(j, i);

						if (j == 1) {
							adr.setUserId(cell.getContents());
						}else if (j == 2) {
							adr.setReceiver(cell.getContents());
						} else if (j == 3) {
							adr.setPhone(cell.getContents());
						}else if (j == 4) {
							adr.setProvince(cell.getContents());
						}else if (j == 5) {
							adr.setCity(cell.getContents());
						}else if (j == 6) {
							adr.setArea(cell.getContents());
						}else if (j == 7) {
							adr.setAddress(cell.getContents());
						}else if (j == 8) {
							adr.setState(1);
						}else if (j == 9) {
							adr.setTag(Integer.valueOf(cell.getContents()));
						}
					}
					ulist.add(adr);
				}
				AdminDao adminDao = new AdminDao();
				Timestamp date = new Timestamp(StringUtil.parseToDate("2017-11-12 05:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				message=adminDao.saveAdr(ulist, date);
				if(message.equals("")) message="yes";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙中，请稍后再试！");
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void importCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				jxl.Workbook readwb = null;
				List<Center> ulist = new ArrayList<Center>();

				// 构建Workbook对象, 只读Workbook对象

				// 直接从本地文件创建Workbook

				InputStream instream = new FileInputStream(
						"/Users/xiangfu_txe/dumps/hdj-cn-1.xls");
				readwb = Workbook.getWorkbook(instream);
				// Sheet的下标是从0开始
				// 获取第一张Sheet表
				Sheet readsheet = readwb.getSheet(0);
				// 获取Sheet表中所包含的总列数
				int rsColumns = readsheet.getColumns();
				// 获取Sheet表中所包含的总行数
				int rsRows = readsheet.getRows();
				// 获取指定单元格的对象引用
				for (int i = 1; i < 252; i++)

				{
					Center adr = new Center();
					
					for (int j = 0; j < rsColumns; j++)

					{
						Cell cell = readsheet.getCell(j, i);

						if (j == 1) {
							adr.setCenterId(cell.getContents());
							
						}else if (j == 2) {
							adr.setType(Integer.valueOf(cell.getContents()));
							
						} else if (j == 3) {
							adr.setCenterName(cell.getContents());
						}else if (j == 4) {
							adr.setUserId(cell.getContents());
						}else if (j == 5) {
							adr.setUserName(cell.getContents());
						}else if (j == 6) {
							adr.setPhone(cell.getContents());
						}else if (j == 7) {
							adr.setProvince(cell.getContents());
						}else if (j == 8) {
							adr.setCity(cell.getContents());
						}else if (j == 9) {
							adr.setArea(cell.getContents());
						}else if (j == 10) {
							adr.setAddress(cell.getContents());
						}else if (j == 11) {
							adr.setState(2);
						}else if (j == 13) {
							adr.setTypeForm(Integer.valueOf(cell.getContents()));
						}else if (j == 14) {
							adr.setMeasure(cell.getContents());
						}else if (j == 15) {
							adr.setLicense(cell.getContents());
						}
					}
					ulist.add(adr);
				}
				AdminDao adminDao = new AdminDao();
				Timestamp date = new Timestamp(StringUtil.parseToDate("2017-11-12 05:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				message=adminDao.saveCent(ulist, date);
				if(message.equals("")) message="yes";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "系统繁忙中，请稍后再试！");
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_psw_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_importPrice_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		try {
			if(admin!=null){
					
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("import_price_add.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_importPrice_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		String token_old = (String)request.getSession().getAttribute("token");
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
		 String path = request.getSession().getServletContext().getRealPath("/upload");  
		 factory.setRepository(new File(path)); 
		 factory.setSizeThreshold(1024*1024) ;  
		 ServletFileUpload upload = new ServletFileUpload(factory); 
		try {
			if(admin!=null){
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
					 jxl.Workbook readwb = null;
					 InputStream instream = new FileInputStream(new File(path,imgurl));
					 List<User> ulist = new ArrayList<User>();
						readwb = Workbook.getWorkbook(instream);
						// Sheet的下标是从0开始
						// 获取第一张Sheet表
						Sheet readsheet = readwb.getSheet(0);
						// 获取Sheet表中所包含的总列数
						int rsColumns = readsheet.getColumns();
						// 获取Sheet表中所包含的总行数
						int rsRows = readsheet.getRows();
						// 获取指定单元格的对象引用
						for (int i = 2; i < rsRows; i++){
							User wrd = new User();
							
							for (int j = 0; j < rsColumns; j++)

							{
								Cell cell = readsheet.getCell(j, i);

								if (j == 1) {
									wrd.setUserId(cell.getContents());
								}else if (j == 2) {
									wrd.setUserName(cell.getContents());
								} else if (j == 3) {
									wrd.setJoinPrice(Double.valueOf(cell.getContents()));
								}else if (j == 4) {
									wrd.setJoinPv(Double.valueOf(cell.getContents()));
								}else if (j == 5) {
									wrd.setTotalIncome(Double.valueOf(cell.getContents()));
								}
							}
							ulist.add(wrd);
				 		}
					UserDao userDao = new UserDao();
					message = userDao.importPriceExcel(ulist, admin.getAdminName());
				}else{
					message = "请勿重复提交数据。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("import_price_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_price_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					UserDao userDao = new UserDao();
					message = userDao.priceSummary();
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	@SuppressWarnings("rawtypes")
	public void sql_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		List<String> slist = new ArrayList<String>();
		try {
			if(admin!=null){
				String pageNoStr = request.getParameter("pageNoStr");
				String pageSizeStr = request.getParameter("pageSizeStr");
				int pageNo = 1;
				 int pageSize = 20;
				 String path = "/usr/local/tomcat/webapps/ROOT/db/daily/hnt";
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
						cs.insertAdminLog(admin.getAdminName(), "查看备份数据库资料信息！",ConstantsLog.LOGTYPE_16, date);
					}
		
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("message", "数据保存有误，请重新录入！");
	}finally{
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
	
	public void admin_price_summary_13_15(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					UserDao userDao = new UserDao();
					message = userDao.priceSummary_13_15();
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void updateBelongNode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					UserDao userDao = new UserDao();
					message = userDao.updateBelongNode();
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void updateWSettle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					WSettleDao wstDao = new WSettleDao();
					message = wstDao.UpdateWSettle();
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void checkWSettle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					WSettleDao wstDao = new WSettleDao();
					message = wstDao.checkWSettle();
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void updateRankJoin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					WSettleDao wstDao = new WSettleDao();
					message = wstDao.updateRankJoin();
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void exportExcel_checkWReard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());

		String weekTagStr = request.getParameter("weekTag");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "拓展奖误差明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<WReward> result = new ArrayList<WReward>();
		String message = "";
			try {
				if(admin!=null){
					WSettleDao wstDao = new WSettleDao();
					result = wstDao.checkWSettleList();
					String[][] content = new String[result.size()+2][9];
					content[0][0]="序号";
					content[0][1]="周期";
					content[0][2]="会员编号";
					content[0][3]="会员名称";
					content[0][4]="实际奖金";
					content[0][5]="系统已结";
					content[0][6]="误差";
					content[0][7]="调整前金额";
					content[0][8]="调整前误差";
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = String.valueOf(result.get(i).getWeekTag());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][4]  =  StringUtil.decimalFormat(result.get(i).getAmount());
						content[i+1][5]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+1][6]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+1][7]  =  StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+1][8]  =  StringUtil.decimalFormat(result.get(i).getAmount_4());
					}
				
				}
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("14-17周误差明细");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} 
	}
	public void exportExcel_checkWReard_8(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());

		String weekTagStr = request.getParameter("weekTag");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "见点奖误差明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<WReward> result = new ArrayList<WReward>();
		String message = "";
			try {
				if(admin!=null){
					System.out.println("start");
					WSettleDao wstDao = new WSettleDao();
					result = wstDao.checkWReward_8();
					String[][] content = new String[result.size()+2][9];
					content[0][0]="序号";
					content[0][1]="周期";
					content[0][2]="会员编号";
					content[0][3]="会员名称";
					content[0][4]="实际奖金";
					content[0][5]="系统已结";
					content[0][6]="误差";
				
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = String.valueOf(result.get(i).getWeekTag());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][4]  =  StringUtil.decimalFormat(result.get(i).getAmount());
						content[i+1][5]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+1][6]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
					}
				
				}
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("14-18周误差明细");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} 
	}
	
	public void exportExcel_checkWReard_2_18(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());

		String weekTagStr = request.getParameter("weekTag");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "对碰奖误差明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<WReward> result = new ArrayList<WReward>();
		String message = "";
			try {
				if(admin!=null){
					System.out.println("start");
					WSettleDao wstDao = new WSettleDao();
					result = wstDao.checkWReward_2_18();
					String[][] content = new String[result.size()+2][9];
					content[0][0]="序号";
					content[0][1]="周期";
					content[0][2]="会员编号";
					content[0][3]="会员名称";
					content[0][4]="实际奖金";
					content[0][5]="系统已结";
					content[0][6]="误差";
				
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = String.valueOf(result.get(i).getWeekTag());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][4]  =  StringUtil.decimalFormat(result.get(i).getAmount());
						content[i+1][5]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+1][6]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
					}
				
				}
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("18周误差明细");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} 
	}
	
	public void updateUserRefereeNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					AdminDao wstDao = new AdminDao();
					message = wstDao.updateUserRefereeNum();
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void updateTotalIncome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		
		try {
			if(admin!=null){
					AdminDao wstDao = new AdminDao();
					message = wstDao.updateTotalIncome();
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void updateNodeId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		try {
			if(admin!=null){
					AdminDao wstDao = new AdminDao();
					message = wstDao.updateNodeId();
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("price_summary_message.jsp");
					dispatcher.forward(request, response);
		}

	}
}
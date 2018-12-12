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
import com.ssm.dao.AdminLogDao;
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
import com.ssm.utils.Constants;
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

		}else if (method.equals("initAdmin")) {
			initAdmin(request, response);

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

		}else if (method.equals("editAdmin")) {
			editAdmin(request, response);

		}else if (method.equals("updateAdmin")) {
			updateAdmin(request, response);

		}else if (method.equals("removeAdmin")) {
			removeAdmin(request, response);

		}else if (method.equals("initPsw")) {
			initPsw(request, response);

		}else if (method.equals("editRank")) {
			editRank(request, response);

		}else if (method.equals("updateRank")) {
			updateRank(request, response);

		}else if (method.equals("logout")) {
			logout(request, response);

		}else if (method.equals("admin_psw_edit")) {
			admin_psw_edit(request, response);

		}else if (method.equals("admin_psw_update")) {
			admin_password_update(request, response);

		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String password = StringUtil.notNull(request.getParameter("password"));
		String ip = StringUtil.notNull(request.getParameter("ip"));
		String message = "";
		try {
			if (adminName == null || password == null) {
				message = "用户名和密码不能为空！";
			} else {
				AdminDao adminDao = new AdminDao();
				Admin admin = adminDao.login(adminName, MD5.GetMD5Code(password));
				if(admin!=null){
					request.getSession().setAttribute(Constants.ADMIN_SESSION, admin);
					Timestamp date = new Timestamp(new Date().getTime());
					AdminLogDao logdao = new AdminLogDao();
					//String adr = StringUtil.getUrlContent(ip);
		    		String address="不详";
		    		/*
					if(adr!=null){
					 JSONObject jsonObject = JSONObject.fromObject(adr);
						 if(jsonObject.get("data")!=null){
							 JSONObject json =  JSONObject.fromObject(jsonObject.get("data"));
							 if(json.get("country")!=null&&!json.get("country").equals("XX")&&!json.get("region").equals("")) address = (String) json.get("country");
							 if(json.get("region")!=null&&!json.get("region").equals("XX")&&!json.get("region").equals("")) address+= "-"+(String) json.get("region");
							 if(json.get("city")!=null&&!json.get("city").equals("XX")&&!json.get("city").equals("")) address+= "-"+(String) json.get("city");
							 if(json.get("county")!=null&&!json.get("county").equals("XX")&&!json.get("county").equals("")) address+= "-"+(String) json.get("county");
							 if(json.get("isp")!=null&&!json.get("isp").equals("XX")&&!json.get("isp").equals("")) address+= "，网络服务商:"+(String) json.get("isp");
						 }
					 }
					 */
					logdao.save(adminName,ConstantsLog.LOGTYPE_2, "用户登陆成功，主机IP:"+ip+"，所在区域:"+address+"。", date);
				} else {
						message = "用户名不存在或密码有误！";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作有误，请稍后再试！";
		} finally {
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("main.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute(Constants.ADMIN_SESSION);
		response.sendRedirect("login_out.jsp");
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String realName = StringUtil.notNull(request.getParameter("realName"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error = "";
		int lt = 0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Admin admin1 = new Admin();
			admin1.setAdminName(adminName);
			admin1.setRealName(realName);
			admin1.setState(2);
				AdminDao adminDao = new AdminDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adminDao.findAdmin(admin1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("adminName", adminName);
				request.setAttribute("realName", realName);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String request_dispatcher ="admin_list.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="admin_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="admin_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="admin_list.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void addAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message= "";
		String error="";
		int lt = 0;
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
				error = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
				String request_dispatcher = "admin_message.jsp";
				if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher = "admin_message.jsp";
				}else if (!error.equals("")) {	
					if(lt==0) {
						request_dispatcher = "admin_error.jsp";
					}else {
						request_dispatcher = "error_login.jsp";
					}
				} else {
					request_dispatcher = "admin_add.jsp";
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}

	}
	
	public void saveAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
		int lt=0;
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
				admin1.setState(2);
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
				lt=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			request.setAttribute("message", message);
			if(lt==0) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("admin_message.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("error_login.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}
	
	public void editAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = (String) request.getParameter("id");
		String  message= "";
		String error="";
		int lt=0;
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
						error = "用户信息获取失败。";
					}
				}else{
					error = "用户ID获取失败。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
				String request_dispatcher ="admin_message.jsp";
				if (!message.equals("")) {
					request_dispatcher ="admin_message.jsp";
					request.setAttribute("message", message);
				}else if (!error.equals("")) {
					if(lt==1) request_dispatcher ="error_login.jsp";
					else request_dispatcher ="admin_error.jsp";
					request.setAttribute("error", error);
				}else {
					 request_dispatcher ="admin_edit.jsp";
				}
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}
	}
	
	public void editRank(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
	
	public void removeAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String[] ids = request.getParameterValues("ids");
	
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(ids!=null&&ids.length>0) {
				AdminDao adminDao = new AdminDao();
				int count = adminDao.deleteAll(ids);
				if(count>0) message="批量删除数据成功。";
				else error="操作失败，数据有误或者未选择需要删除的信息。";
				}else {
					error="未选择需要删除的信息。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="admin_message.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="admin_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="admin_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
	public void initPsw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
	
	public void initAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = "";
		try {
				Timestamp date = new Timestamp(new Date().getTime());
				AdminDao adminDao = new AdminDao();
				Admin admin1 = new Admin();
				admin1.setAdminName("weiteng");
				admin1.setRealName("威腾公司");
				admin1.setPassword(MD5.GetMD5Code("weiteng2018"));
				admin1.setPassword2(MD5.GetMD5Code("weiteng2018"));
				admin1.setState(1);
				admin1.setTel("13800000000");
				admin1.setEndTime(date);
				admin1.setEntryTime(date);
				adminDao.saveAdmin(admin1);
				message = "超级管理员初始化成功，用户名为weiteng,密码weiteng2018。";
				admin1=null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("admin_init_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_psw_edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message= "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
				String request_dispatcher ="admin_psw_message.jsp";
				if (!message.equals("")) {
					request_dispatcher ="admin_message.jsp";
					request.setAttribute("message", message);
				}else if (!error.equals("")) {
					if(lt==1) request_dispatcher ="error_login.jsp";
					else request_dispatcher ="admin_psw_error.jsp";
					request.setAttribute("error", error);
				}else {
					 request_dispatcher ="admin_psw_edit.jsp";
				}
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_password_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		try {
			if(admin!=null){
				String psw = StringUtil.notNull(request.getParameter("psw"));
				String password = StringUtil.notNull(request.getParameter("password"));
				if(!psw.equals("")){
					if(!password.equals("")){
						if(!psw.equals(password)){
							if(MD5.GetMD5Code(psw).equals(admin.getPassword())){
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
								message= "用户原密码有误，请重试！";
							}

						}else{
							message="新旧密码不能一样，请重试！";
						}

						}else{
							message="新密码不能为空，请重试！";
						}
					}else{
						message="原密码有误，请重试！";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="系统繁忙中，请稍后再试！";
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
	
	
}
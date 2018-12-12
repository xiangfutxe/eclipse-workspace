package com.ssm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssm.dao.AdminLogDao;
import com.ssm.dao.ModuleDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Module;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class ModuleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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

		}else if (method.equals("list")) {
			list(request, response);

		}else if (method.equals("add")) {
			add(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}else if (method.equals("edit")) {
			edit(request, response);

		}else if (method.equals("update")) {
			update(request, response);

		}else if (method.equals("del")) {
			del(request, response);

		}else if (method.equals("sel")) {
			sel(request, response);

		}
	}
	
	

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error="";
		int lt = 0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Module module = new Module();
				ModuleDao adrDao = new ModuleDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adrDao.findByPage(module,pager);
				request.setAttribute("pager", pager);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="module_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="module_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="module_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="module.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					ModuleDao adrDao = new ModuleDao();
					Module  module = adrDao.findById(Integer.valueOf(id));
					request.setAttribute("module", module);
				
				}else{
					error = "信息获取失败。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="module_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="module_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="module_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="module_edit.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
					
					Timestamp date = new Timestamp(new Date().getTime());
					if(!id.equals("")) {
					ModuleDao moduleDao = new ModuleDao();
					moduleDao.delete(Integer.valueOf(id));
					AdminLogDao logDao  = new AdminLogDao();
					message = "批量删除模块成功。";
					logDao.save(admin.getAdminName(), ConstantsLog.LOGTYPE_1,message, date);
					}else {
						error="为选择需要删除的新闻信息。";
					}
				
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="module_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="module_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="module_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="module.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		String error = "";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String code = StringUtil.notNull(request.getParameter("code"));
					String title = StringUtil.notNull(request.getParameter("title"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					String id = StringUtil.notNull(request.getParameter("id"));
					if(title.length()>100){ error = "标题不能超过100个字！";}
				if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());

					ModuleDao moduleDao = new ModuleDao();
				Module module = new Module();
				module.setId(Integer.valueOf(id));
				module.setCode(Integer.valueOf(code));
				module.setTitle(title);;
				module.setContents(contents);
				module.setEndTime(date);
				int count = moduleDao.update(module);
				if(count==1) {
					message = "模块信息修改成功。";
				}else if(count==2) {
					message="模块更新操作异常。";
				}else {
					message="模块更新失败。";
				}
			}
				}else{
					error = "请勿重复提交数据。";
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
			String request_dispatcher ="module_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="module_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="module_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="module.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message= "";
		String error = "";
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
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="module_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="module_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="module_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="module_add.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error = "";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String title = StringUtil.notNull(request.getParameter("title"));
					String code = StringUtil.notNull(request.getParameter("code"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					
					if(title.length()>100){ error = "标题不能超过50个字！";}
			if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					ModuleDao moduleDao = new ModuleDao();
					Module module = new Module();
					module.setCode(Integer.valueOf(code));
					module.setTitle(title);;
					module.setContents(contents);
					module.setEndTime(date);
					module.setEntryTime(date);
				int result = moduleDao.save(module);
				if(result==0) error="数据保存失败。";
				else if(result==1) message ="模块保存成功！";
				else if(result==2) error="数据保存异常。";
				module=null;
			}
				}else{
					error = "请勿重复提交数据。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String request_dispatcher ="module_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="module_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="module_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="module.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	

	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = StringUtil.notNull(request.getParameter("code"));
		
		String message = "";
		try {
			ModuleDao moduleDao = new ModuleDao();
			Module module = moduleDao.findByCode(Integer.valueOf(code));
			
				request.setAttribute("module", module);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据操作异常。";
		} finally {
				request.setAttribute("message", message);
			
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("module.jsp");
				dispatcher.forward(request, response);
		
		}
	}
}
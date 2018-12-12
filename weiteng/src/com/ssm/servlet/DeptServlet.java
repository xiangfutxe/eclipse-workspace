package com.ssm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ssm.dao.DeptDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Dept;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class DeptServlet extends HttpServlet {

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

		} else if (method.equals("list")) {
			list(request, response);

		}else if (method.equals("removeDept")) {
			removeDept(request, response);

		}else if (method.equals("editDept")) {
			editDept(request, response);

		}else if (method.equals("isDeptExit")) {
			isDeptExit(request, response);

		}else if (method.equals("isDeptExitUpdate")) {
			isDeptExitUpdate(request, response);

		}else if (method.equals("updateDept")) {
			updateDept(request, response);

		}else if (method.equals("addDept")) {
			addDept(request, response);

		}else if (method.equals("saveDept")) {
			saveDept(request, response);

		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String name = StringUtil.notNull(request.getParameter("name"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 4;
		String message = "";
		String error="";
		int lt = 0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Dept dept = new Dept();
			dept.setName(name);
				DeptDao deptDao = new DeptDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = deptDao.findDept(dept,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("name", name);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="dept_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="dept_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="dept_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="dept.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void editDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		String  error= "";
		int lt =0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				DeptDao deptDao = new DeptDao();
				Dept dept = deptDao.findDeptById(Integer.valueOf(id));
				request.setAttribute("dept", dept);
				}else{
					error = "部门信息获取失败。";
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
				String request_dispatcher ="dept_message.jsp";
				if (!message.equals("")) {
					request_dispatcher ="dept_message.jsp";
					request.setAttribute("message", message);
				}else if (!error.equals("")) {
					if(lt==1) request_dispatcher ="error_login.jsp";
					else request_dispatcher ="dept_error.jsp";
					request.setAttribute("error", error);
				}else {
					request_dispatcher ="dept_edit.jsp";
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}

	}
	
	public void removeDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String[] ids = request.getParameterValues("ids");
		String message = "";
		String error = "";
		int lt =0;
		try {
			if(admin!=null){
				DeptDao deptDao = new DeptDao();
				deptDao.deleteAll(ids);
				message = "批量删除部门成功。";
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="dept_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="dept_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="dept_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isDeptExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					DeptDao deptDao = new DeptDao();
					Dept dept1 = deptDao.findDeptByName(name);
					if(dept1!=null){
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
	
	public void isDeptExitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					DeptDao deptDao = new DeptDao();
					Dept dept1 = deptDao.findDeptByName(name);
					if(dept1!=null){
						if(String.valueOf(dept1.getId()).equals(id)){
							
							tag=2;
						}else{
							tag=1;
						}
					}else{
						tag=2;
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", String.valueOf(tag));
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void updateDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String name = StringUtil.notNull(request.getParameter("name"));
	String remark = StringUtil.notNull(request.getParameter("remark"));
	
	String id = StringUtil.notNull(request.getParameter("id"));
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
					Timestamp date = new Timestamp(new Date().getTime());

					DeptDao deptDao = new DeptDao();
					Dept dept = new Dept();
					dept.setId(Integer.valueOf(id));
					dept.setName(name);
					dept.setRemark(remark);
					dept.setEndTime(date);;
					deptDao.updateDept(dept);
					message = "部门修改成功。";
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
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="dept_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="dept_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="dept_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void addDept(HttpServletRequest request, HttpServletResponse response)
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
			error = "数据操作异常。";
		} finally {
				String request_dispatcher ="dept_message.jsp";
				if (!message.equals("")) {
					request_dispatcher ="dept_message.jsp";
					request.setAttribute("message", message);
				}else if (!error.equals("")) {
					if(lt==1) request_dispatcher ="error_login.jsp";
					else request_dispatcher ="dept_error.jsp";
					request.setAttribute("error", error);
				}else {
					 request_dispatcher ="dept_add.jsp";
				}
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}

	}
	
	public void saveDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String name = StringUtil.notNull(request.getParameter("name"));
	String remark = StringUtil.notNull(request.getParameter("remark"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
				DeptDao deptDao = new DeptDao();
				Dept dept = new Dept();
				dept.setName(name);
				dept.setRemark(remark);
				dept.setState(1);
				dept.setEntryTime(date);
				dept.setEndTime(date);
				message = deptDao.saveDept(dept);
				dept=null;
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
			error = "数据操作异常.";
		} finally {
			String request_dispatcher ="dept_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="dept_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="dept_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
}
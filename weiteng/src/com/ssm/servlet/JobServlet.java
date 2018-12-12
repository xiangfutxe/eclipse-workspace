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

import com.ssm.dao.JobDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Job;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class JobServlet extends HttpServlet {

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

		}else if (method.equals("removeJob")) {
			removeJob(request, response);

		}else if (method.equals("editJob")) {
			editJob(request, response);

		}else if (method.equals("isJobExit")) {
			isJobExit(request, response);

		}else if (method.equals("isJobExitUpdate")) {
			isJobExitUpdate(request, response);

		}else if (method.equals("updateJob")) {
			updateJob(request, response);

		}else if (method.equals("addJob")) {
			addJob(request, response);

		}else if (method.equals("saveJob")) {
			saveJob(request, response);

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
		String error = "";
		int lt=0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Job Job = new Job();
			Job.setName(name);
				JobDao JobDao = new JobDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = JobDao.findJob(Job,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("name", name);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作有误。";
		} finally {
			String request_dispatcher ="job_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="job_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="job_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="job.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void editJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				JobDao JobDao = new JobDao();
				Job Job = JobDao.findJobById(Integer.valueOf(id));
				request.setAttribute("job", Job);
				}else{
					error = "部门信息获取失败。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="job_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="job_message.jsp";
				request.setAttribute("message", message);
			}else  if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="job_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="job_edit.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void removeJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String[] ids = request.getParameterValues("ids");
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				JobDao JobDao = new JobDao();
				JobDao.deleteAll(ids);
				message = "批量删除职务成功。";
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="job_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="job_message.jsp";
				request.setAttribute("message", message);
			}else  if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="job_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isJobExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					JobDao JobDao = new JobDao();
					Job Job1 = JobDao.findJobByName(name);
					if(Job1!=null){
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
	
	public void isJobExitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					JobDao JobDao = new JobDao();
					Job Job1 = JobDao.findJobByName(name);
					if(Job1!=null){
						if(String.valueOf(Job1.getId()).equals(id)){
							
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
	
	public void updateJob(HttpServletRequest request, HttpServletResponse response)
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
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());

				JobDao JobDao = new JobDao();
				Job Job = new Job();
				Job.setId(Integer.valueOf(id));
				Job.setName(name);
				Job.setRemark(remark);
				Job.setEndTime(date);;
				JobDao.updateJob(Job);
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
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="job_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="job_message.jsp";
				request.setAttribute("message", message);
			}else  if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="job_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void addJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="job_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="job_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="job_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="job_add.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void saveJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String name = StringUtil.notNull(request.getParameter("name"));
	String remark = StringUtil.notNull(request.getParameter("remark"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
				JobDao JobDao = new JobDao();
				Job Job = new Job();
				Job.setName(name);
				Job.setRemark(remark);
				Job.setState(1);
				Job.setEntryTime(date);
				Job.setEndTime(date);
				message = JobDao.saveJob(Job);
				Job=null;
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
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="job_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="job_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="job_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
}
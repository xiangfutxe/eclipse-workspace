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

import com.ssm.dao.MessageDao;
import com.ssm.dao.NewsDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Message;
import com.ssm.pojo.News;
import com.ssm.pojo.User;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class MessageServlet extends HttpServlet {

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
		}else if (method.equals("admin_list")) {
			admin_list(request, response);

		}else if (method.equals("admin_reply")) {
			admin_reply(request, response);

		}else if (method.equals("admin_reply_save")) {
			admin_reply_save(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("sys_user");
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(user!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Message msg = new Message();
			msg.setUserId(user.getUserId());
			msg.setState(1);
				MessageDao adrDao = new MessageDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adrDao.findByPage(msg,pager);
				request.setAttribute("pager", pager);
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
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
						.getRequestDispatcher("msg_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("msg_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	


	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Message msg = new Message();
			msg.setUserId(userId);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				msg.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				msg.setEndTime(endTime);
			}
				MessageDao adrDao = new MessageDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adrDao.findByPage(msg,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
						.getRequestDispatcher("msg_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("msg_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void admin_reply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					MessageDao adrDao = new MessageDao();
					Message  msg = adrDao.findById(Integer.valueOf(id));
					if(msg.getState()==1){
							request.setAttribute("msg", msg);
					 }else{
						 message= "当前状态不能进行回复，请核对！";
					 }
				}else{
					message= "未找到相应的留言信息！";
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
							.getRequestDispatcher("msg_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("msg_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	

	
	public void admin_reply_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String adminMsg = StringUtil.notNull(request.getParameter("adminMsg"));
					String id = StringUtil.notNull(request.getParameter("id"));
					if(adminMsg.length()>200){ error = "留言内容不能超过100个字！";}
					else if(adminMsg.equals("")){ error = "扣款金额有误！";}
			if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					MessageDao adrDao = new MessageDao();
					Message msg = new Message();
					msg.setId(Integer.valueOf(id));
					msg.setAdminMsg(adminMsg);
					msg.setState(2);
					msg.setAdmin(admin.getAdminName());
					msg.setReplyTime(date);
					message = adrDao.updateMessage(msg);
					if(message.equals(""))message="系统繁忙中，请稍后再试。";
					else message = "留言回复成功。";
			}else{
				message = error;
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
					.getRequestDispatcher("msg_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error = "";
		try {
			if(user!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String userMsg = StringUtil.notNull(request.getParameter("userMsg"));
					
					if(userMsg.length()>200){ error = "留言内容不能超过100个字！";}
					else if(userMsg.equals("")){ error = "扣款金额有误！";}
			if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					MessageDao adrDao = new MessageDao();
					Message msg = new Message();
					msg.setUserId(user.getUserId());
					msg.setUserName(user.getUserName());
					msg.setUserMsg(userMsg);
					msg.setState(1);
					msg.setEntryTime(date);
				message = adrDao.saveMessage(msg);
				msg=null;
				if(message.equals(""))message="系统繁忙中，请稍后再试。";
			}else {
				request.setAttribute("message",error);
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
					.getRequestDispatcher("msg_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
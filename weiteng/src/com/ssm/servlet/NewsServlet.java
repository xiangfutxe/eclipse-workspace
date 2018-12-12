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
import com.ssm.dao.NewsDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.News;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class NewsServlet extends HttpServlet {

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

		} else if (method.equals("news_detail")) {
			news_detail(request, response);

		}else if (method.equals("admin_news_list")) {
			admin_news_list(request, response);

		}else if (method.equals("admin_news_detail")) {
			admin_news_detail(request, response);

		}else if (method.equals("add")) {
			add(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}else if (method.equals("edit")) {
			admin_news_edit(request, response);

		}else if (method.equals("update")) {
			admin_news_update(request, response);

		}else if (method.equals("admin_news_up")) {
			admin_news_up(request, response);

		}else if (method.equals("admin_news_del")) {
			admin_news_del(request, response);

		}else if (method.equals("delAll")) {
			delAll(request, response);

		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(user!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			News news = new News();
			news.setState(1);
				NewsDao adrDao = new NewsDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adrDao.findByPage(news,pager);
				request.setAttribute("pager", pager);
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
						.getRequestDispatcher("news_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("news_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void news_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(user!=null){
				if(!id.equals("")){
					NewsDao adrDao = new NewsDao();
					News  news = adrDao.findById(Integer.valueOf(id));
					request.setAttribute("news", news);
				}else{
					message = "信息获取失败。";
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
							.getRequestDispatcher("news_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("news_detail.jsp");
					dispatcher.forward(request, response);
				}
		}

	}

	public void admin_news_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String sort = StringUtil.notNull(request.getParameter("sort"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
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
			News news = new News();
			news.setSort(sort);;
			news.setState(1);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				news.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				news.setEndTime(endTime);
			}
				NewsDao adrDao = new NewsDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adrDao.findByPage(news,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("sort",sort);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_news_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					NewsDao adrDao = new NewsDao();
					News  news = adrDao.findById(Integer.valueOf(id));
					request.setAttribute("news", news);
				
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
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news_detail.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void admin_news_edit(HttpServletRequest request, HttpServletResponse response)
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
					NewsDao adrDao = new NewsDao();
					News  news = adrDao.findById(Integer.valueOf(id));
					request.setAttribute("news", news);
				
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
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news_edit.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void admin_news_del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
					
					Timestamp date = new Timestamp(new Date().getTime());

					NewsDao adrDao = new NewsDao();
				News news = new News();
				news.setId(Integer.valueOf(id));
				news.setState(0);
				news.setEndTime(date);
				message = adrDao.updateNews(news);
				message = "新闻信息下线成功。";
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
					String[] ids = request.getParameterValues("ids");
					
					Timestamp date = new Timestamp(new Date().getTime());
					if(ids!=null&ids.length>0) {
					NewsDao newsDao = new NewsDao();
					newsDao.deleteAll(ids);
					AdminLogDao logDao  = new AdminLogDao();
					message = "批量删除新闻成功。";
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
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	
	
	public void admin_news_update(HttpServletRequest request, HttpServletResponse response)
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
					String title = StringUtil.notNull(request.getParameter("title"));
					String sort = StringUtil.notNull(request.getParameter("sort"));
					String source = StringUtil.notNull(request.getParameter("source"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					String id = StringUtil.notNull(request.getParameter("id"));
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());

					NewsDao adrDao = new NewsDao();
				News news = new News();
				news.setId(Integer.valueOf(id));
				news.setTitle(title);;
				news.setContents(contents);
				news.setSource(source);
				news.setSort(sort);
				news.setEndTime(date);
				message = adrDao.updateNews(news);
				message = "新闻信息修改成功。";
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
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news.jsp";
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
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news_add.jsp";
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
					String sort = StringUtil.notNull(request.getParameter("sort"));
					String source = StringUtil.notNull(request.getParameter("source"));
					String contents = StringUtil.notNull(request.getParameter("contents"));
					
					if(title.length()>100){ error = "标题不能超过100个字！";}
			if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					NewsDao newsDao = new NewsDao();
					News news = new News();
					news.setTitle(title);;
					news.setPublisher(admin.getAdminName());
					news.setSource(source);
					news.setBrowseNum((long) 0);
					news.setContents(contents);
					news.setSort(sort);
					news.setState(1);
					news.setEndTime(date);
					news.setEntryTime(date);
				int result = newsDao.saveNews(news);
				if(result==0) error="数据保存失败。";
				else if(result==1) message ="新闻保存成功！";
				else if(result==2) error="数据保存异常。";
				news=null;
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
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_news_up(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
					
					Timestamp date = new Timestamp(new Date().getTime());

					NewsDao adrDao = new NewsDao();
				News news = new News();
				news.setId(Integer.valueOf(id));
				news.setState(1);
				news.setEndTime(date);
				message = adrDao.updateNews(news);
				message = "新闻信息上线成功。";
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="news_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="news_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="news_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="news.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
}
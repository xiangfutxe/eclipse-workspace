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

import com.ssm.dao.ParamDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Param;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class ParamServlet extends HttpServlet {

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

		}else if (method.equals("remove")) {
			remove(request, response);

		}else if (method.equals("edit")) {
			edit(request, response);

		}else if (method.equals("isExit")) {
			isExit(request, response);

		}else if (method.equals("isExitUpdate")) {
			isExitUpdate(request, response);

		}else if (method.equals("update")) {
			update(request, response);

		}else if (method.equals("add")) {
			add(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String paramName = StringUtil.notNull(request.getParameter("paramName"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Param Param = new Param();
			Param.setParamName(paramName);
				ParamDao ParamDao = new ParamDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = ParamDao.findParam(Param,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("paramName", paramName);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="param_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="param_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="param_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="param.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				ParamDao ParamDao = new ParamDao();
				Param param = ParamDao.findParamById(Integer.valueOf(id));
				if(param!=null) {
					request.setAttribute("param",param);
				}else {
					error = "参数信息获取失败。";
				}
				
				}else{
					error = "未获取对应ID的参数信息。";
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
			String request_dispatcher ="param_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="param_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="param_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="param_edit.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String[] ids = request.getParameterValues("ids");
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(ids!=null&&ids.length>0) {
					ParamDao ParamDao = new ParamDao();
					ParamDao.deleteAll(ids);
					message = "批量删除参数成功。";
				}else {
					error="未获得需要删除的信息。";
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
			String request_dispatcher ="param_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="param_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="param_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void isExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("paramName");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					ParamDao ParamDao = new ParamDao();
					Param Param1 = ParamDao.findParamByName(name);
					if(Param1!=null){
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
	
	public void isExitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("paramName");
		String id = request.getParameter("id");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					ParamDao ParamDao = new ParamDao();
					Param Param1 = ParamDao.findParamByName(name);
					if(Param1!=null){
						if(String.valueOf(Param1.getId()).equals(id)){
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
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
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
					String id = StringUtil.notNull(request.getParameter("id"));
					String paramName = StringUtil.notNull(request.getParameter("paramName"));
					String amount_1 = StringUtil.notNull(request.getParameter("amount_1"));
					String amount_2 = StringUtil.notNull(request.getParameter("amount_2"));
					String amount_3 = StringUtil.notNull(request.getParameter("amount_3"));
					String amount_4 = StringUtil.notNull(request.getParameter("amount_4"));
					String amount_5 = StringUtil.notNull(request.getParameter("amount_5"));
					String amount_6 = StringUtil.notNull(request.getParameter("amount_6"));
					String amount_7 = StringUtil.notNull(request.getParameter("amount_7"));
					String amount_8 = StringUtil.notNull(request.getParameter("amount_8"));
					String amount_9 = StringUtil.notNull(request.getParameter("amount_9"));
					String amount_10 = StringUtil.notNull(request.getParameter("amount_10"));
					String unit = StringUtil.notNull(request.getParameter("unit"));
					Timestamp date= new Timestamp(new Date().getTime());
				ParamDao ParamDao = new ParamDao();
				Param param = new Param();
				param.setId(Integer.valueOf(id));
				param.setParamName(paramName);
				param.setAmount_1(Double.valueOf(amount_1));
				param.setAmount_2(Double.valueOf(amount_2));
				param.setAmount_3(Double.valueOf(amount_3));
				param.setAmount_4(Double.valueOf(amount_4));
				param.setAmount_5(Double.valueOf(amount_5));
				param.setAmount_6(Double.valueOf(amount_6));
				param.setAmount_7(Double.valueOf(amount_7));
				param.setAmount_8(Double.valueOf(amount_8));
				param.setAmount_9(Double.valueOf(amount_9));
				param.setAmount_10(Double.valueOf(amount_10));
				param.setUnit(unit);
				param.setEndTime(date);
				ParamDao.updateParam(param);
				message = "参数修改成功。";
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
			String request_dispatcher ="param_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="param_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="param_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
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
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="param_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="param_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="param_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="param_add.jsp";
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
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
					String paramName = StringUtil.notNull(request.getParameter("paramName"));
					String amount_1 = StringUtil.notNull(request.getParameter("amount_1"));
					String amount_2 = StringUtil.notNull(request.getParameter("amount_2"));
					String amount_3 = StringUtil.notNull(request.getParameter("amount_3"));
					String amount_4 = StringUtil.notNull(request.getParameter("amount_4"));
					String amount_5 = StringUtil.notNull(request.getParameter("amount_5"));
					String amount_6 = StringUtil.notNull(request.getParameter("amount_6"));
					String amount_7 = StringUtil.notNull(request.getParameter("amount_7"));
					String amount_8 = StringUtil.notNull(request.getParameter("amount_8"));
					String amount_9 = StringUtil.notNull(request.getParameter("amount_9"));
					String amount_10 = StringUtil.notNull(request.getParameter("amount_10"));
					String unit = StringUtil.notNull(request.getParameter("unit"));
				ParamDao ParamDao = new ParamDao();
				Param param = new Param();
				param.setParamName(paramName);
				param.setAmount_1(Double.valueOf(amount_1));
				param.setAmount_2(Double.valueOf(amount_2));
				param.setAmount_3(Double.valueOf(amount_3));
				param.setAmount_4(Double.valueOf(amount_4));
				param.setAmount_5(Double.valueOf(amount_5));
				param.setAmount_6(Double.valueOf(amount_6));
				param.setAmount_7(Double.valueOf(amount_7));
				param.setAmount_8(Double.valueOf(amount_8));
				param.setAmount_9(Double.valueOf(amount_9));
				param.setAmount_10(Double.valueOf(amount_10));
				param.setUnit(unit);
				param.setState(1);
				param.setEndTime(date);
				param.setEntryTime(date);
				message = ParamDao.saveParam(param);
				param=null;
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
			String request_dispatcher ="param_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="param_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="param_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
}
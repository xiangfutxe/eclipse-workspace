package com.ssm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ssm.dao.PromotionDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Promotion;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class PromotionServlet extends HttpServlet {

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

		}else if (method.equals("removePro")) {
			removePro(request, response);

		}else if (method.equals("editPro")) {
			editPro(request, response);

		}else if (method.equals("isExit")) {
			isExit(request, response);

		}else if (method.equals("isExitUpdate")) {
			isExitUpdate(request, response);

		}else if (method.equals("updatePro")) {
			updatePro(request, response);

		}else if (method.equals("addPro")) {
			addPro(request, response);

		}else if (method.equals("savePro")) {
			savePro(request, response);

		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				PromotionDao proDao = new PromotionDao();
				List<Promotion> coll = proDao.findByAll();
				request.setAttribute("coll", coll);
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
						.getRequestDispatcher("promotion_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("promotion.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void editPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				PromotionDao PromotionDao = new PromotionDao();
				Promotion Promotion = PromotionDao.findById(Integer.valueOf(id));
				request.setAttribute("promotion", Promotion);
				}else{
					message = "促销信息获取失败。";
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
							.getRequestDispatcher("promotion_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("promotion_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void removePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				PromotionDao PromotionDao = new PromotionDao();
				Promotion pro = new Promotion();
				pro.setId(Integer.valueOf(id));
				pro.setState(0);
				PromotionDao.updatePro(pro);
				message = "促销政策删除成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("promotion_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					PromotionDao PromotionDao = new PromotionDao();
					Promotion Promotion1 = PromotionDao.findByName(name);
					if(Promotion1!=null){
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
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					PromotionDao PromotionDao = new PromotionDao();
					Promotion Promotion1 = PromotionDao.findByName(name);
					if(Promotion1!=null){
						if(String.valueOf(Promotion1.getId()).equals(id)){
							
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
	
	public void updatePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	
	
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String id = StringUtil.notNull(request.getParameter("id"));
					String name = StringUtil.notNull(request.getParameter("name"));
					String num1 = StringUtil.notNull(request.getParameter("num1"));
					String num2 = StringUtil.notNull(request.getParameter("num2"));
					String num3 = StringUtil.notNull(request.getParameter("num3"));
					String num4 = StringUtil.notNull(request.getParameter("num4"));
					String num5 = StringUtil.notNull(request.getParameter("num5"));
					String startTimeStr  = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr  = StringUtil.notNull(request.getParameter("endTime"));
					String summary  = StringUtil.notNull(request.getParameter("summary"));
				if(!name.equals("")&&!num1.equals("")&&!num2.equals("")&&!num3.equals("")&&!num4.equals("")&&!num5.equals("")
						&&!startTimeStr.equals("")&&!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr, DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr, DateUtil.yyyyMMddHHmmss).getTime());

					PromotionDao PromotionDao = new PromotionDao();
				Promotion pro = new Promotion();
				pro.setId(Integer.valueOf(id));
				pro.setName(name);
				pro.setNum1(Double.valueOf(num1));
				pro.setNum2(Double.valueOf(num2));
				pro.setNum3(Double.valueOf(num3));
				pro.setNum4(Double.valueOf(num4));
				pro.setNum5(Double.valueOf(num5));
				pro.setStartTime(startTime);
				pro.setEndTime(endTime);
				pro.setSummary(summary);
				message =PromotionDao.updatePro(pro);
				}else{
					message = "参数设置不能为空。";
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
					.getRequestDispatcher("promotion_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void addPro(HttpServletRequest request, HttpServletResponse response)
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
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("promotion_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("promotion_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void savePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
					String name = StringUtil.notNull(request.getParameter("name"));
					String num1 = StringUtil.notNull(request.getParameter("num1"));
					String num2 = StringUtil.notNull(request.getParameter("num2"));
					String num3 = StringUtil.notNull(request.getParameter("num3"));
					String num4 = StringUtil.notNull(request.getParameter("num4"));
					String num5 = StringUtil.notNull(request.getParameter("num5"));
					String startTimeStr  = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr  = StringUtil.notNull(request.getParameter("endTime"));
					String summary  = StringUtil.notNull(request.getParameter("summary"));
					if(!name.equals("")&&!num1.equals("")&&!num2.equals("")&&!num3.equals("")&&!num4.equals("")&&!num5.equals("")
							&&!startTimeStr.equals("")&&!startTimeStr.equals("")){
						Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr, DateUtil.yyyyMMddHHmmss).getTime());
						Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr, DateUtil.yyyyMMddHHmmss).getTime());

						PromotionDao PromotionDao = new PromotionDao();
					Promotion pro = new Promotion();
					pro.setName(name);
					pro.setNum1(Double.valueOf(num1));
					pro.setNum2(Double.valueOf(num2));
					pro.setNum3(Double.valueOf(num3));
					pro.setNum4(Double.valueOf(num4));
					pro.setNum5(Double.valueOf(num5));
					pro.setStartTime(startTime);
					pro.setEndTime(endTime);
					pro.setState(1);
					pro.setEntryTime(date);
					pro.setSummary(summary);
					message =PromotionDao.savePro(pro);
					}else{
						message = "参数设置不能为空。";
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
					.getRequestDispatcher("promotion_add_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
}
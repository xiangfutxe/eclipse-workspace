package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Order;
import com.ssm.pojo.Address;
import com.ssm.dao.AddressDao;
import com.ssm.dao.CenterDao;
import com.ssm.dao.JoinInfoDao;
import com.ssm.dao.NewsDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.ParamDao;
import com.ssm.dao.ProductDao;
import com.ssm.dao.UidPoolDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.UserLogDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.News;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class UserServlet extends HttpServlet {

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

		}else if (method.equals("index")) {
			index(request, response);

		}else if (method.equals("login_out")) {
			login_out(request, response);

		}else if (method.equals("user_detail")) {
			user_detail(request, response);

		}else if (method.equals("admin_list")) {
			admin_list(request, response);
			
		}else if (method.equals("admin_special_list")) {
			admin_special_list(request, response);
		}else if (method.equals("admin_user_money_list")) {
			admin_user_money_list(request, response);

		}else if (method.equals("userAjax")) {
			userAjax(request, response);

		}else if (method.equals("userAjax_admin")) {
			userAjax_admin(request, response);

		}else if (method.equals("moneyAjax")) {
			moneyAjax(request, response);

		}else if (method.equals("centerAjax")) {
			centerAjax(request, response);

		}else if (method.equals("admin_user_save")) {
			admin_user_save(request, response);

		}else if (method.equals("admin_user_add")) {
			admin_user_add(request, response);

		}else if (method.equals("admin_user_update")) {
			admin_user_update(request, response);

		}else if (method.equals("refereeAjax_admin")) {
			refereeAjax_admin(request, response);

		}else if (method.equals("refereeAjax_user")) {
			refereeAjax_user(request, response);

		}else if (method.equals("belongAjax_admin")) {
			belongAjax_admin(request, response);

		}else if (method.equals("belongAjax_user")) {
			belongAjax_user(request, response);

		}else if (method.equals("user_empty_add")) {
			user_empty_add(request, response);

		}else if (method.equals("user_empty_save")) {
			user_empty_save(request, response);

		}else if (method.equals("psw_login")) {
			psw_login(request, response);

		}else if (method.equals("user_add")) {
			user_add(request, response);

		}else if (method.equals("user_save")) {
			user_save(request, response);

		}else if (method.equals("user_update")) {
			user_update(request, response);

		}else if (method.equals("user_update_id")) {
			user_update_id(request, response);

		}else if (method.equals("admin_user_back")) {
			admin_user_back(request, response);

		}else if (method.equals("admin_rankJoin_up")) {
			admin_rankJoin_up(request, response);

		}else if (method.equals("admin_rankJoin_up_save")) {
			admin_rankJoin_up_save(request, response);

		}else if (method.equals("admin_user_detail")) {
			admin_user_detail(request, response);
		}else if (method.equals("admin_belongJson")) {
			admin_belongJson(request, response);
		} else if (method.equals("admin_getBelong")) {
			admin_getBelong(request, response);
		}else if (method.equals("admin_refereeJson")) {
			admin_refereeJson(request, response);
		} else if (method.equals("admin_getReferee")) {
			admin_getReferee(request, response);
		}else if (method.equals("belongJson")) {
			belongJson(request, response);
	} else if (method.equals("getBelong")) {
			getBelong(request, response);
	}else if (method.equals("refereeJson")) {
			refereeJson(request, response);
	} else if (method.equals("getReferee")) {
			getReferee(request, response);
	}else if (method.equals("admin_user_authority_edit")) {
		admin_user_authority_edit(request, response);
	}else if (method.equals("admin_user_authority_save")) {
		admin_user_authority_save(request, response);
		
	}else if (method.equals("admin_user_hide_list")) {
		admin_user_hide_list(request, response);
	}else if (method.equals("admin_user_hide_reset")) {
		admin_user_hide_reset(request, response);
	}else if (method.equals("admin_user_hide")) {
		admin_user_hide(request, response);
	}else if (method.equals("admin_user_money_hide_list")) {
		admin_user_money_hide_list(request, response);
	} else if (method.equals("user_password1_update")) {
		user_password1_update(request, response);
	} else if (method.equals("user_password2_update")) {
		user_password2_update(request, response);
	}else if (method.equals("admin_psw1_init")) {
		admin_psw1_init(request, response);
	} else if (method.equals("admin_psw2_init")) {
		admin_psw2_init(request, response);
	}else if (method.equals("getMD5")) {
		getMD5(request, response);
	}else if (method.equals("rankJoin_up")) {
		rankJoin_up(request, response);
	}else if (method.equals("rankJoin_up_a")) {
		rankJoin_up_a(request, response);
	}else if (method.equals("rankJoin_up_b")) {
		rankJoin_up_b(request, response);
	}else if (method.equals("admin_referee_update")) {
		admin_referee_update(request, response);
	}else if (method.equals("admin_referee_update_save")) {
		admin_referee_update_save(request, response);
	}else if (method.equals("admin_belong_update")) {
		admin_belong_update(request, response);
	}else if (method.equals("admin_belong_update_save")) {
		admin_belong_update_save(request, response);
	}else if (method.equals("exportExcel_user_list")) {
		exportExcel_user_list(request, response);
	}else if (method.equals("exportExcel_user_hide_list")) {
		exportExcel_user_hide_list(request, response);
	}else if (method.equals("exportExcel_user_money_list")) {
		exportExcel_user_money_list(request, response);
	}else if (method.equals("exportExcel_user_money_hide_list")) {
		exportExcel_user_money_hide_list(request, response);
	}else if (method.equals("admin_referee_reback_list")) {
		admin_referee_reback_list(request, response);
	}else if (method.equals("admin_belong_reback_list")) {
		admin_belong_reback_list(request, response);
	}else if (method.equals("exportExcel_referee_reback")) {
		exportExcel_referee_reback(request, response);
	}else if (method.equals("exportExcel_belong_reback")) {
		exportExcel_belong_reback(request, response);
	}else if (method.equals("admin_user_lock")) {
			admin_user_lock(request, response);
	}else if (method.equals("admin_user_unlock")) {
			admin_user_unlock(request, response);
	}else if (method.equals("init")) {
		init(request, response);
	}
		
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String password = StringUtil.notNull(request.getParameter("password"));
		String ip = StringUtil.notNull(request.getParameter("ip"));
		String hostname = StringUtil.notNull(request.getParameter("hostname"));
		
		String macAddress = StringUtil.notNull(request.getParameter("macAddress"));

		String message = "";
		int num=0;
		try {
			if (userId == null || password == null) {
				message = "用户名和密码不能为空！";
			} else {
				
				UserDao userDao = new UserDao();
				User user = userDao.login(userId, MD5.GetMD5Code(password),ip);
				if(user!=null){
					if(user.getState()>0){
						if(user.getPayTag()==1){
							if(user.getVisitNum()>1) num=1;
					request.getSession().setAttribute("sys_user", user);
					ProductDao productDao = new ProductDao();
					Product p = new Product();
					p.setType(1);
					p.setState("1");
					p.setIsHide(0);
					List<Product> coll_pro = productDao.findByProduct(p);
					request.setAttribute("coll_pro", coll_pro);
					NewsDao newsDao = new NewsDao();
					News news = new News();
					news.setState(1);
					Pager pager= new Pager();
					pager.setPageNo(1);
					pager.setPageSize(5);
					Collection coll_new = newsDao.findByPage(news, pager).getResultList();
					request.setAttribute("coll_new", coll_new);
						}else {
							message = "用户名账户已冻结，请与客服联系。";
					}
					}else {
						message = "用户名不存在。";
				}
					
				} else {
						message = "用户名不存在或密码有误！";
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} finally {
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				if(num==1){
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				}else{
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("contract.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

	}
	
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if(user!=null){
			
					ProductDao productDao = new ProductDao();
					Product p = new Product();
					p.setType(1);
					p.setState("1");
					p.setIsHide(1);
					List<Product> coll_pro = productDao.findByProduct(p);
					request.setAttribute("coll_pro", coll_pro);
					NewsDao newsDao = new NewsDao();
					News news = new News();
					news.setState(1);
					Pager pager= new Pager();
					pager.setPageNo(1);
					pager.setPageSize(5);
					Collection coll_new = newsDao.findByPage(news, pager).getResultList();
					request.setAttribute("coll_new", coll_new);
				} else {
						message = "用户未登录！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}

	}
	
	public void login_out(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("sys_user");
		request.getSession().removeAttribute("psw2");

		response.sendRedirect("login.jsp");
	}
	
	public void psw_login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String password = StringUtil.notNull(request.getParameter("password"));
		String message = "";
		try {
			if(user!=null){
			
				UserDao userDao = new UserDao();
				User user1 = userDao.findById(user.getId());
				if(user1!=null){
					if(user1.getPassword2().equals(MD5.GetMD5Code(password))){
						request.getSession().setAttribute("psw2",
								user1.getPassword2());
						message = "支付密码登陆成功。";
					}else{
						message = "支付密码有误！";
					}
					
				} else {
						message = "支付密码有误！";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("psw_login_message.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	public void user_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
				UserDao userDao = new UserDao();
				User user1 = userDao.findUserInfoByUseId(user.getUserId());
				if(user1!=null){
					request.setAttribute("user1", user1);
				} else {
						message = "未查询到相关的会员信息";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} finally {
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_detail_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_detail.jsp");
				dispatcher.forward(request, response);
			}
		}

	}
	
	public synchronized void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String rankJoinStr = StringUtil.notNull(request.getParameter("rankJoin"));
		String rankManageStr = StringUtil.notNull(request.getParameter("rankManage"));
		String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
		String belongId = StringUtil.notNull(request.getParameter("belongId")).toUpperCase();
		String declarationId = StringUtil.notNull(request.getParameter("declarationId")).toUpperCase();
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
			int rankJoin =0;
			int rankManage =0;
			if(!rankJoinStr.equals("")) rankJoin= Integer.valueOf(rankJoinStr);
			if(!rankManageStr.equals("")) rankManage= Integer.valueOf(rankManageStr);
			User user1 = new User();
			user1.setUserId(userId);;
			user1.setRankJoin(rankJoin);
			user1.setRankManage(rankManage);
			user1.setUserByBelongId(belongId);
			user1.setUserByRefereeId(refereeId);
			user1.setUserByDeclarationId(declarationId);
			user1.setIsHide(0);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				user1.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				user1.setEndTime(endTime);
			}
				UserDao userDao = new UserDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = userDao.findUserByPage(user1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("rankJoin", rankJoinStr);
				request.setAttribute("rankManage", rankManageStr);
				request.setAttribute("userId", userId);
				request.setAttribute("refereeId", refereeId);
				request.setAttribute("declarationId", declarationId);
				request.setAttribute("belongId", belongId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public synchronized void admin_special_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String userName = StringUtil.notNull(request.getParameter("userName"));
		String tel = StringUtil.notNull(request.getParameter("tel"));
		String documentType = StringUtil.notNull(request.getParameter("documentType"));
		
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message="";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			User user1 = new User();
			user1.setUserName(userName);;
			user1.setTel(tel);
			user1.setNumId(documentType);
			user1.setIsHide(0);
			String str ="";
			if(!userName.equals("")) str = str+"会员名称："+userName+".";
			if(!documentType.equals("")) str = str+"证件号码："+documentType+".";
			if(!tel.equals("")) str = str+"联系电话："+tel+".";
				UserDao userDao = new UserDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = userDao.findUsersByPage(user1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userName", userName);
				request.setAttribute("tel", tel);
				request.setAttribute("documentType", documentType);
				if(!str.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					cs.insertAdminLog(admin.getAdminName(), "会员高级查询，查询条件为："+str, ConstantsLog.LOGTYPE_4, date);
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} finally {
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_special_list.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public synchronized void admin_user_money_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			User user1 = new User();
			user1.setUserId(userId);;
			user1.setIsHide(0);
				UserDao userDao = new UserDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = userDao.findUserByPage(user1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message ="系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_money_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_money_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void userAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String userName="";
		try {
				if (userId.equals("")) {
					userName = "";
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
						if(user1.getPayTag()==1)
						userName= user1.getUserName();
						else userName="";
					}else{
						userName="";
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName",userName);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void userAjax_admin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String userName="";
		try {
				if (userId.equals("")) {
					userName = "";
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
						
						userName= user1.getUserName();
					}else{
						userName="";
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName",userName);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void moneyAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		double emoney = 0;
		double smoney =0;
		double dmoney = 0;
		double rmoney = 0;
		try {
			if (!StringUtil.notNull(userId).equals("")) {
					UserDao userDao = new UserDao();
					User user1 = userDao.findMoneyByUserId(userId);
					if(user1!=null){
						if(user1.getPayTag()==1){
						emoney = user1.getEmoney();
						smoney = user1.getSmoney();
						dmoney = user1.getDmoney();
						rmoney = user1.getRmoney();
						}
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("emoney",emoney);
			object.put("smoney",smoney);
			object.put("dmoney",dmoney);
			object.put("rmoney",rmoney);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void centerAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = StringUtil.notNull(request.getParameter("centerId")).toUpperCase();
		String userId="";
		try {
			if (!StringUtil.notNull(centerId).equals("")) {
					CenterDao centerDao = new CenterDao();
					Center center = centerDao.findByCenterId(centerId);
					if(center!=null){
						if(center.getState()==2){
							userId= center.getUserId();
						}
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userId",userId);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void refereeAjax_admin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
		String userName="";
		try {
				if (refereeId.equals("")) {
					userName = "";
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(refereeId);
					if(user1!=null){
						if(user1.getPayTag()==1){
							if(user1.getState()==2)
							userName= user1.getUserName();
						}else{
							userName="";
						}
					}else{
						userName="";
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName",userName);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void belongAjax_admin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String belongId = StringUtil.notNull(request.getParameter("belongId")).toUpperCase();
		String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
		String userName="";
		int nodeTag = 0;
		try {
				if (StringUtil.notNull(belongId).equals("")||StringUtil.notNull(refereeId).equals("")) {
					userName = "";
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(belongId);
					if(user1!=null){
						if(user1.getPayTag()==1){
						if(user1.getState()==2){
							boolean b = false;
							if(belongId.equals(refereeId)){
								b = true;
							}else{
							 userDao = new UserDao();
							 User user2 = userDao.findByUserId(refereeId);
							 String[] strr = StringUtil.notNull(
										user1.getNode()).split(",");
								for (int i = 0; i < strr.length; i++) {
									if (!strr[i].equals("")) {
										if (strr[i].equals(String
												.valueOf(user2.getId())))
											b = true;
									}
								}
							}
							if(b){
							userName= user1.getUserName();
							nodeTag= nodeBelong(user1.getUserByAId(),
											user1.getUserByBId());
							}
						}
						}else{
							userName="";
						}
					}else{
						userName="";
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName",userName);
			object.put("nodeTag",nodeTag);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void belongAjax_user(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String belongId = StringUtil.notNull(request.getParameter("belongId")).toUpperCase();
		String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
		String userName="";
		int nodeTag = 0;
		try {
			if(user!=null){
				if (StringUtil.notNull(belongId).equals("")||StringUtil.notNull(refereeId).equals("")) {
					userName = "";
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(belongId);
					if(user1!=null){
						if(user1.getPayTag()==1){
						if(user1.getState()>=1){
							boolean b = false;
							if(belongId.equals(refereeId)){
								b = true;
							}else{
							 userDao = new UserDao();
							 User user2 = userDao.findByUserId(refereeId);
							 String[] strr = StringUtil.notNull(
										user1.getNode()).split(",");
								for (int i = 0; i < strr.length; i++) {
									if (!strr[i].equals("")) {
										if (strr[i].equals(String
												.valueOf(user2.getId())))
											b = true;
									}
								}
							}
							if(b){
							userName= user1.getUserName();
							nodeTag= nodeBelong(user1.getUserByAId(),
											user1.getUserByBId());
							}
						}
						}else{
							userName="";
						}
					}else{
						userName="";
					}
			} 
			}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName",userName);
			object.put("nodeTag",nodeTag);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void refereeAjax_user(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
		
		String userName="";
		try {
			if(user!=null){
				if (StringUtil.notNull(refereeId).equals("")) {
					userName = "";
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(refereeId);
					if(user1!=null){
						if(user1.getPayTag()==1){
						if(user1.getState()>=1){
							boolean b = false;
							if(user.getUserId().equals(refereeId)){
								b = true;
							}else{
							 String[] strr = StringUtil.notNull(
										user1.getNode()).split(",");
								for (int i = 0; i < strr.length; i++) {
									if (!strr[i].equals("")) {
										if (strr[i].equals(String.valueOf(user.getId())))
											b = true;
									}
								}
							}
							if(b){
							userName= user1.getUserName();
							}
						}
					}else{
						userName="";
					}
					}else{
						userName="";
					}
			} 
			}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName",userName);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

		public Integer nodeBelong(String AId, String BId) {
			int node = 0;
			AId = StringUtil.notNull(AId);
			BId = StringUtil.notNull(BId);
			if (AId.equals("") && BId.equals(""))
				node = 1;
			else if (AId.equals(""))
				node = 2;
			else if (BId.equals(""))
				node = 3;
			else
				node = 5;
			return node;
		}
	
	public void admin_user_add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("user_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	
	
	public void admin_user_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
	try{
		
	if(admin!=null){
	String belongId = StringUtil.notNull(request
			.getParameter("belongId")).toUpperCase();
	String refereeId = StringUtil.notNull(request
			.getParameter("refereeId")).toUpperCase();
	String userId = StringUtil.notNull(request
			.getParameter("userId")).toUpperCase();
	String userName = StringUtil.notNull(request
			.getParameter("userName"));
	String password = StringUtil.notNull(request
			.getParameter("password"));
	String nodeTagStr = StringUtil.notNull(request
			.getParameter("nodeTag"));
	String sex = StringUtil.notNull(request
			.getParameter("sex"));
	String ageStr = StringUtil.notNull(request
			.getParameter("age"));
	String documentType = StringUtil.notNull(request
			.getParameter("documentType"));
	String numId = StringUtil.notNull(request
			.getParameter("numId"));
	String qq = StringUtil.notNull(request
			.getParameter("qq"));
	String tel = StringUtil.notNull(request
			.getParameter("tel"));
	String email = StringUtil.notNull(request
			.getParameter("email"));
	String province = StringUtil.notNull(request
			.getParameter("province"));
	String area = StringUtil.notNull(request
			.getParameter("area"));
	String address = StringUtil.notNull(request
			.getParameter("address"));
	String city = StringUtil.notNull(request
			.getParameter("city"));
	String bankName = StringUtil.notNull(request
			.getParameter("bankName"));
	String accountId = StringUtil.notNull(request
			.getParameter("accountId"));
	String accountName = StringUtil.notNull(request
			.getParameter("accountName"));
	String rankJoinStr = StringUtil.notNull(request
			.getParameter("rankJoin"));
	String rankManageStr = StringUtil.notNull(request
			.getParameter("rankManage"));
	String phone = StringUtil.notNull(request
			.getParameter("phone"));
	String weixin = StringUtil.notNull(request
			.getParameter("weixin"));
	String bankAdr = StringUtil.notNull(request
			.getParameter("bankAdr"));
	String isToReal = StringUtil.notNull(request
			.getParameter("isToReal"));
	Timestamp date = new Timestamp(new Date().getTime());
	User user1 = new User();
	User userinfo = new User();
	user1.setUserName(userName);
	user1.setRankManage(Integer.valueOf(rankManageStr));
	if(Integer.valueOf(rankManageStr)>0)
		user1.setRankManageTag(1);
	user1.setRankJoin(Integer.valueOf(rankJoinStr));
	user1.setRankJoinOld(Integer.valueOf(rankJoinStr));
	if(Integer.valueOf(rankJoinStr)>0)
		user1.setRankJoinTag(1);
	user1.setRankJoinOriginal(Integer.valueOf(rankJoinStr));
	user1.setUserByRefereeId(refereeId);
	user1.setUserByBelongId(belongId);
	user1.setNodeTag(Integer.valueOf(nodeTagStr));
	user1.setUserByDeclarationId(Constants.TOP_NODE);
	user1.setState(2);
	user1.setIsEmpty(1);
	user1.setPayTag(1);
	user1.setEntryTime(date);
	user1.setEndTime(date);
	userinfo.setUserName(userName);
	userinfo.setSex(sex);
	userinfo.setDocumentType(documentType);
	userinfo.setNumId(numId);
	userinfo.setEmail(email);
	userinfo.setTel(tel);
	userinfo.setPhone(phone);
	userinfo.setQq(qq);
	userinfo.setWeixin(weixin);
	userinfo.setProvince(province);
	userinfo.setCity(city);
	userinfo.setArea(area);
	userinfo.setAddress(address);
	userinfo.setPassword(MD5.GetMD5Code(password));
	userinfo.setPassword2(MD5.GetMD5Code(password));
	userinfo.setIsToReal(Integer.valueOf(isToReal));
	userinfo.setAccountName(accountName);
	userinfo.setBankName(bankName);
	userinfo.setBankAdr(bankAdr);
	userinfo.setState(2);
	userinfo.setAccountId(accountId);
	UidPoolDao upDao =  new UidPoolDao();
	Integer[] uid = upDao.getUidList();
	if(uid!=null){
		if(uid[0]>0){
			if(userId.length()==2){
					String[] mid = new String[3];
					for(int i=0;i<3;i++){
						mid[i]=String.valueOf(uid[i]);
						for (int j = 0; j < 8 - String
								.valueOf(uid[i])
								.length(); j++) {
							mid[i] = "0" + mid[i];
						}
						mid[i]=userId +mid[i];
					}
					request.setAttribute("mid", mid);
					userId = mid[0];
					user1.setUserId(userId);
					userinfo.setUserId(userId);
			UserDao userDao = new UserDao();
			message = userDao.saveUserForAdmin(user1,userinfo);
			cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
		}else{
			message = "会员自定义编号长度有误,自定义编号长度为2。";
		}
	}else{
		message = "会员编号获取失败。";
	}
	}else{
		message = "会员编号获取失败。";
	}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据有误，请重试。";
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_add_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_user_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
	try{
		
	if(admin!=null){
	
	String userId = StringUtil.notNull(request
			.getParameter("userId")).toUpperCase();
	String userName = StringUtil.notNull(request
			.getParameter("userName"));
	String sex = StringUtil.notNull(request
			.getParameter("sex"));
	String documentType = StringUtil.notNull(request
			.getParameter("documentType"));
	String numId = StringUtil.notNull(request
			.getParameter("numId"));
	String qq = StringUtil.notNull(request
			.getParameter("qq"));
	String tel = StringUtil.notNull(request
			.getParameter("tel"));
	String email = StringUtil.notNull(request
			.getParameter("email"));
	String province = StringUtil.notNull(request
			.getParameter("province"));
	String area = StringUtil.notNull(request
			.getParameter("area"));
	String address = StringUtil.notNull(request
			.getParameter("address"));
	String city = StringUtil.notNull(request
			.getParameter("city"));
	String bankName = StringUtil.notNull(request
			.getParameter("bankName"));
	String accountId = StringUtil.notNull(request
			.getParameter("accountId"));
	String accountName = StringUtil.notNull(request
			.getParameter("accountName"));
	
	String phone = StringUtil.notNull(request
			.getParameter("phone"));
	String weixin = StringUtil.notNull(request
			.getParameter("weixin"));
	String bankAdr = StringUtil.notNull(request
			.getParameter("bankAdr"));
	Timestamp date = new Timestamp(new Date().getTime());
	User user1 = new User();
	User userinfo = new User();
	user1.setUserId(userId);
	user1.setUserName(userName);
	user1.setEndTime(date);
	userinfo.setUserName(userName);
	userinfo.setSex(sex);
	userinfo.setDocumentType(documentType);
	userinfo.setNumId(numId);
	userinfo.setEmail(email);
	userinfo.setTel(tel);
	userinfo.setPhone(phone);
	userinfo.setQq(qq);
	userinfo.setWeixin(weixin);
	userinfo.setProvince(province);
	userinfo.setCity(city);
	userinfo.setArea(area);
	userinfo.setAddress(address);
	userinfo.setAccountName(accountName);
	userinfo.setBankName(bankName);
	userinfo.setBankAdr(bankAdr);
	userinfo.setAccountId(accountId);
			UserDao userDao = new UserDao();
			message = userDao.updateUser(user1,userinfo);
			if(message.equals("yes")){
				message="会员信息更新成功。";
				cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
			}
	
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据有误，请重试。";
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void user_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String message = "";
	try{
		
	if(user!=null){
	
	String sex = StringUtil.notNull(request
			.getParameter("sex"));
	String documentType = StringUtil.notNull(request
			.getParameter("documentType"));
	String numId = StringUtil.notNull(request
			.getParameter("numId"));
	String qq = StringUtil.notNull(request
			.getParameter("qq"));
	
	String email = StringUtil.notNull(request
			.getParameter("email"));
	String province = StringUtil.notNull(request
			.getParameter("province"));
	String area = StringUtil.notNull(request
			.getParameter("area"));
	String address = StringUtil.notNull(request
			.getParameter("address"));
	String city = StringUtil.notNull(request
			.getParameter("city"));
	String bankName = StringUtil.notNull(request
			.getParameter("bankName"));
	String accountId = StringUtil.notNull(request
			.getParameter("accountId"));
	String accountName = StringUtil.notNull(request
			.getParameter("accountName"));
	
	String phone = StringUtil.notNull(request
			.getParameter("phone"));
	String weixin = StringUtil.notNull(request
			.getParameter("weixin"));
	String bankAdr = StringUtil.notNull(request
			.getParameter("bankAdr"));
	Timestamp date = new Timestamp(new Date().getTime());
	User userinfo = new User();
	UserDao userDao = new UserDao();
	User uif =  userDao.findUserInfoByUseId(user.getUserId());
	userinfo.setId(uif.getId());
	userinfo.setSex(sex);
	userinfo.setDocumentType(documentType);
	userinfo.setNumId(numId);
	userinfo.setEmail(email);
	userinfo.setPhone(phone);
	userinfo.setQq(qq);
	userinfo.setWeixin(weixin);
	userinfo.setProvince(province);
	userinfo.setCity(city);
	userinfo.setArea(area);
	userinfo.setAddress(address);
	userinfo.setAccountName(accountName);
	userinfo.setBankName(bankName);
	userinfo.setBankAdr(bankAdr);
	userinfo.setAccountId(accountId);
	userDao = new UserDao();
			if(userDao.updateUserInfo(userinfo)>0){
				message="会员信息更新成功。";
				userDao = new UserDao();
				user = userDao.findById(user.getId());
				request.getSession().setAttribute("sys_user", user);
				request.setAttribute("user1", user);
			}else{
				message="会员信息更新失败。";
			}
	
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据有误，请重试。";
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_detail_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	public void user_empty_add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("user_empty_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_empty_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void user_empty_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String message = "";
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	try{
		
	if(user!=null){
		if(StringUtil.notNull(token_old).equals(token)){

	String belongId = StringUtil.notNull(request
			.getParameter("belongId")).toUpperCase();
	String refereeId = StringUtil.notNull(request
			.getParameter("refereeId")).toUpperCase();
	String userId = StringUtil.notNull(request
			.getParameter("userId")).toUpperCase();
	String userName = StringUtil.notNull(request
			.getParameter("userName"));
	String password = StringUtil.notNull(request
			.getParameter("password"));
	String nodeTagStr = StringUtil.notNull(request
			.getParameter("nodeTag"));
	String sex = StringUtil.notNull(request
			.getParameter("sex"));
	String ageStr = StringUtil.notNull(request
			.getParameter("age"));
	String documentType = StringUtil.notNull(request
			.getParameter("documentType"));
	String numId = StringUtil.notNull(request
			.getParameter("numId"));
	String qq = StringUtil.notNull(request
			.getParameter("qq"));
	String tel = StringUtil.notNull(request
			.getParameter("tel"));
	String email = StringUtil.notNull(request
			.getParameter("email"));
	String province = StringUtil.notNull(request
			.getParameter("province"));
	String area = StringUtil.notNull(request
			.getParameter("area"));
	String address = StringUtil.notNull(request
			.getParameter("address"));
	String city = StringUtil.notNull(request
			.getParameter("city"));
	String bankName = StringUtil.notNull(request
			.getParameter("bankName"));
	String accountId = StringUtil.notNull(request
			.getParameter("accountId"));
	String accountName = StringUtil.notNull(request
			.getParameter("accountName"));
	String rankJoinStr = StringUtil.notNull(request
			.getParameter("rankJoin"));
	String rankManageStr = StringUtil.notNull(request
			.getParameter("rankManage"));
	String phone = StringUtil.notNull(request
			.getParameter("phone"));
	String weixin = StringUtil.notNull(request
			.getParameter("weixin"));
	String bankAdr = StringUtil.notNull(request
			.getParameter("bankAdr"));
	String isToReal = StringUtil.notNull(request
			.getParameter("isToReal"));
	Timestamp date = new Timestamp(new Date().getTime());
	User user1 = new User();
	User userinfo = new User();
	user1.setUserName(userName);
	user1.setRankJoin(Integer.valueOf(rankJoinStr));
	user1.setRankJoinOld(Integer.valueOf(rankJoinStr));
	if(Integer.valueOf(rankJoinStr)>0)
		user1.setRankJoinTag(1);
	user1.setRankJoinOriginal(Integer.valueOf(rankJoinStr));
	user1.setUserByRefereeId(refereeId);
	user1.setUserByBelongId(belongId);
	user1.setNodeTag(Integer.valueOf(nodeTagStr));
	user1.setUserByDeclarationId(user.getUserId());
	user1.setState(1);
	user1.setIsEmpty(1);
	user1.setPayTag(1);
	user1.setEntryTime(date);
	user1.setEndTime(date);
	user1.setUserIdByBelongCenter(user.getUserId());
	user1.setIdByBelongCenter(user.getId());
	userinfo.setUserName(userName);
	userinfo.setSex(sex);
	userinfo.setDocumentType(documentType);
	userinfo.setNumId(numId);
	userinfo.setEmail(email);
	userinfo.setTel(tel);
	userinfo.setPhone(phone);
	userinfo.setQq(qq);
	userinfo.setWeixin(weixin);
	userinfo.setProvince(province);
	userinfo.setCity(city);
	userinfo.setArea(area);
	userinfo.setAddress(address);
	userinfo.setPassword(MD5.GetMD5Code(password));
	userinfo.setPassword2(MD5.GetMD5Code(password));
	userinfo.setAccountName(accountName);
	userinfo.setBankName(bankName);
	userinfo.setBankAdr(bankAdr);
	userinfo.setState(1);
	userinfo.setAccountId(accountId);
	UidPoolDao upDao =  new UidPoolDao();
	Integer[] uid = upDao.getUidList();
	if(uid!=null){
		if(uid[0]>0){
			if(userId.length()==2){
					String[] mid = new String[3];
					for(int i=0;i<3;i++){
						mid[i]=String.valueOf(uid[i]);
						for (int j = 0; j < 8 - String
								.valueOf(uid[i])
								.length(); j++) {
							mid[i] = "0" + mid[i];
						}
						mid[i]=userId +mid[i];
					}
					request.setAttribute("mid", mid);
					userId = mid[0];
					user1.setUserId(userId);
					userinfo.setUserId(userId);
			UserDao userDao = new UserDao();
			message = userDao.saveEmptyUser(user1,userinfo);
		}else{
			message = "会员自定义编号长度有误,自定义编号长度为2。";
		}
	}else{
		message = "会员编号获取失败。";
	}
	}else{
		message = "会员编号获取失败。";
	}
		}else{
			message = "请勿重复提交数据。";
		}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_empty_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void user_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
		String  message= "";
		try {
			if(user!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					String rankJoinStr =StringUtil.notNull(request.getParameter("rankJoin")) ;
					ParamDao paramDao = new ParamDao();
					if(!rankJoinStr.equals("")){
					int rankJoin = Integer.valueOf(rankJoinStr);
					Param p = paramDao.findParamByName("报单金额");
					User new_user = new User();
					new_user.setRankJoin(rankJoin);
					if(p!=null){
					double emoney = 0;
						if(rankJoin==1)emoney=  p.getAmount_1();
						else if(rankJoin==2)emoney=  p.getAmount_2();
						else if(rankJoin==3)emoney=  p.getAmount_3();
						else if(rankJoin==4)emoney=  p.getAmount_4();
						else if(rankJoin==5)emoney=  p.getAmount_5();
						new_user.setEmoney(emoney);
						request.getSession().setAttribute("new_user", new_user);
						ProductDao productDao = new ProductDao();
						Product product = new Product();
						product.setProductType("报单产品");
						product.setState("1");
						product.setIsHide(1);
						List<Product> plist = productDao.findByProduct(product);
						request.setAttribute("coll", plist);
					}else{
						message = "获取报单资格失败。";
					}
					
					}else{
						message = "加盟等级获取失败。";
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
							.getRequestDispatcher("user_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_add.jsp");
					dispatcher.forward(request, response);
				}
		}
	}
		
		public void user_save(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try{
		if(user!=null){
			if(StringUtil.notNull(token_old).equals(token)){

		String belongId = StringUtil.notNull(request
				.getParameter("belongId")).toUpperCase();
		String refereeId = StringUtil.notNull(request
				.getParameter("refereeId")).toUpperCase();
		String centerId = StringUtil.notNull(request
				.getParameter("centerId")).toUpperCase();
		String userId = StringUtil.notNull(request
				.getParameter("userId")).toUpperCase();
		String userName = StringUtil.notNull(request
				.getParameter("userName"));
		String password = StringUtil.notNull(request
				.getParameter("password"));
		String nodeTagStr = StringUtil.notNull(request
				.getParameter("nodeTag"));
		String sex = StringUtil.notNull(request
				.getParameter("sex"));
		String documentType = StringUtil.notNull(request
				.getParameter("documentType"));
		String numId = StringUtil.notNull(request
				.getParameter("numId"));
		String qq = StringUtil.notNull(request
				.getParameter("qq"));
		String tel = StringUtil.notNull(request
				.getParameter("tel"));
		String email = StringUtil.notNull(request
				.getParameter("email"));
		String province = StringUtil.notNull(request
				.getParameter("province"));
		String area = StringUtil.notNull(request
				.getParameter("area"));
		String address = StringUtil.notNull(request
				.getParameter("address"));
		String city = StringUtil.notNull(request
				.getParameter("city"));
		String bankName = StringUtil.notNull(request
				.getParameter("bankName"));
		String accountId = StringUtil.notNull(request
				.getParameter("accountId"));
		String accountName = StringUtil.notNull(request
				.getParameter("accountName"));
		String rankJoinStr = StringUtil.notNull(request
				.getParameter("rankJoin"));
		String rankManageStr = StringUtil.notNull(request
				.getParameter("rankManage"));
		String phone = StringUtil.notNull(request
				.getParameter("phone"));
		String weixin = StringUtil.notNull(request
				.getParameter("weixin"));
		String bankAdr = StringUtil.notNull(request
				.getParameter("bankAdr"));
		Timestamp date = new Timestamp(new Date().getTime());
		User user1 = new User();
		User userinfo = new User();
		user1.setUserName(userName);
		
		user1.setRankJoin(Integer.valueOf(rankJoinStr));
		user1.setRankJoinOld(Integer.valueOf(rankJoinStr));
		if(Integer.valueOf(rankJoinStr)>0)
			user1.setRankJoinTag(1);
		user1.setRankJoinOriginal(Integer.valueOf(rankJoinStr));
		user1.setUserByRefereeId(refereeId);
		user1.setUserByBelongId(belongId);
		user1.setNodeTag(Integer.valueOf(nodeTagStr));
		user1.setUserByDeclarationId(user.getUserId());
		user1.setState(2);
		user1.setIsEmpty(0);
		user1.setPayTag(1);
		user1.setEntryTime(date);
		user1.setEndTime(date);
		user1.setUserIdByBelongCenter(centerId);
		user1.setIdByBelongCenter(user.getId());
		userinfo.setUserName(userName);
		userinfo.setSex(sex);
		userinfo.setDocumentType(documentType);
		userinfo.setNumId(numId);
		userinfo.setEmail(email);
		userinfo.setTel(tel);
		userinfo.setPhone(phone);
		userinfo.setQq(qq);
		userinfo.setWeixin(weixin);
		userinfo.setProvince(province);
		userinfo.setCity(city);
		userinfo.setArea(area);
		userinfo.setAddress(address);
		userinfo.setPassword(MD5.GetMD5Code(password));
		userinfo.setPassword2(MD5.GetMD5Code(password));
		userinfo.setAccountName(accountName);
		userinfo.setBankName(bankName);
		userinfo.setBankAdr(bankAdr);
		userinfo.setState(2);
		userinfo.setAccountId(accountId);
		
		Address adr = new Address();
		adr.setProvince(province);
		adr.setCity(city);
		adr.setArea(area);
		adr.setAddress(address);
		adr.setReceiver(userName);
		adr.setPhone(tel);
		adr.setTag(1);
		adr.setState(1);
		adr.setEndTime(date);
		adr.setEntryTime(date);
		String[] numstr = request.getParameterValues("numstr");
		String[] pid = request.getParameterValues("pid");
		
		String rid = "R"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
		Order orders = new Order();
		orders.setOrderId(rid);
		orders.setUserName(userName);
		orders.setOrderType(1);
		orders.setAddress(province+city+area+address);
		orders.setReceiver(userName);
		orders.setReceiver(userName);
		orders.setPhone(tel);
		orders.setState(1);
		orders.setOrderType(1);
		orders.setOrderTime(date);
		
		orders.setUserByDeclarationId(user.getUserId());
		UidPoolDao upDao =  new UidPoolDao();
		Integer[] uid = upDao.getUidList();
		if(uid!=null){
			if(uid[0]>0){
				if(userId.length()==2){
						String[] mid = new String[3];
						for(int i=0;i<3;i++){
							mid[i]=String.valueOf(uid[i]);
							for (int j = 0; j < 8 - String
									.valueOf(uid[i])
									.length(); j++) {
								mid[i] = "0" + mid[i];
							}
							mid[i]=userId +mid[i];
						}
						request.setAttribute("mid", mid);
						userId = mid[0];
						user1.setUserId(userId);
						userinfo.setUserId(userId);
						orders.setUserId(userId);
							adr.setUserId(userId);
				UserDao userDao = new UserDao();
				message = userDao.saveUser(user1,userinfo,orders,adr,pid,numstr);
				if(message.equals("yes")){
					message=user.getUserName()+"信息保存成功，请核对用户信息：<br><br>会员编号为：<span style='color:red;font-weight:bold;'>"+userId+"</span>;<br><br>"
							+ "登陆密码和支付密码是："+password+"。<br>";
					request.setAttribute("tag", "1");
					request.setAttribute("userId", userId);

				}
				
			}else{
				message = "会员自定义编号长度有误,自定义编号长度为2。";
			}
		}else{
			message = "会员编号获取失败。";
		}
		}else{
			message = "会员编号获取失败。";
		}
		}else{
			message = "用户未登陆，请重新登陆。";
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
							.getRequestDispatcher("user_add_message.jsp");
					dispatcher.forward(request, response);
				
			}
		}
		
		
		public void user_update_id(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try{
		if(user!=null){
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String id = StringUtil.notNull(request.getParameter("id"));
			System.out.println(userId+":"+id);
			UserDao userDao = new UserDao();
			message = userDao.updateUserId(id,userId);
			if(message.equals("yes")){
				message = "会员编号修改成功，新注册会员编号为："+userId+"！";
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
					.getRequestDispatcher("user_add_message.jsp");
			dispatcher.forward(request, response);
		
	}
}
		public int getRandom(int min,int max){
	        Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;
	        return s;
	}
		
		public void admin_user_back(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try{
		if(admin!=null){
		String id = StringUtil.notNull(request
				.getParameter("id"));
		Timestamp date = new Timestamp(new Date().getTime());
		User user = new User();
		
		UserDao userDao = new UserDao();
		
		user = userDao.findUsersById(Integer.valueOf(id));
		if(user!=null){
			if(!(user.getEmoney()>0||user.getDmoney()>0||user.getRmoney()>0)){
				if(user.getUserByAId().equals("")&&user.getUserByBId().equals("")&&StringUtil.notNull(user.getRefereeAll()).equals("")){
					JoinInfoDao joinInforDao = new JoinInfoDao();
					List<JoinInfo> jlist = joinInforDao.findByUser(user.getUserId());
					if(jlist!=null&&jlist.size()==1){
						userDao = new UserDao();
						message = userDao.user_back(user);
						if(message.equals("yes")){
							message =user.getUserId()+"会员退单成功。";
							cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
						}
					}else{
						message="该会员还存在其他升级信息，请先退其它升级信息。";
					}
				}else{
					message="该会员已发展业务，请先将线下业务清零后才能进行退单。";
				}
				
			}else{
				message="会员资金账户存在余额，请通知会员处理后才能进行退单。";
			}
		}else{
			message="未查询到需要退单的会员信息。";
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
							.getRequestDispatcher("user_message.jsp");
					dispatcher.forward(request, response);
				
			}
		}
		
		public void admin_rankJoin_up(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try{
		if(admin!=null){
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			UserDao userDao = new UserDao();
			User user = userDao.findByUserId(userId);
			if(user!=null){
				request.setAttribute("up_user", user);
				int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
				request.getSession().setAttribute("token", String.valueOf(token));
			}else{
				message ="会员信息获取失败。";
			}

		}else{
			message ="用户未登录，请重新登陆。";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_update.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_update_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
		
		public void admin_rankJoin_up_save(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try{
		if(admin!=null){
			if (StringUtil.notNull(token_old).equals(token)) {
				String userId = StringUtil.notNull(request.getParameter("id")).toUpperCase();
				String rankJoinStr = StringUtil.notNull(request.getParameter("rankJoin"));
				if(!userId.equals("")){
				if(!rankJoinStr.equals("")){
					UserDao userDao = new UserDao();
		    		Timestamp date = new Timestamp(new Date().getTime());
					message =  userDao.user_rankJoin(userId, Integer.valueOf(rankJoinStr),date);
					if(message.equals("yes")){
						message = "会员等级变更成功，变更会员为"+userId+"。";
						cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
					}
					
				}else{
					message ="升级等级获取失败。";
				}
				}else{
					message ="会员编号获取失败。";
				}
				
			}else{
				message ="请勿重复提交数据。";
			}

		}else{
			message ="用户未登录，请重新登陆。";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_update_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
		
		public void admin_user_detail(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if(admin!=null){
					
				String userId= StringUtil.notNull(request.getParameter("id")).toUpperCase();
					UserDao userDao = new UserDao();
					User user1 = userDao.findUserInfoByUseId(userId);
					if(user1!=null){
						request.setAttribute("user1", user1);
					} else {
							message = "未查询到相关的会员信息";
					}
				} else {
					message = "用户未登陆，请重新登陆";
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_detail.jsp");
					dispatcher.forward(request, response);
				}
			}

		}
		
		public void admin_getBelong(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String userId = StringUtil.notNull(request
							.getParameter("userId")).toUpperCase();
					if (!userId.equals("")) {
						 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
						 UserDao userDao = new UserDao();
					     User user =userDao.findByUserId(userId);
							if(user!=null){           
								   int rankJoin = user.getRankJoin();
								   int rankManage=user.getRank();
						        	String rank = "";
									if (rankJoin == 1) {
										rank = rank + "银卡会员；";
									} else if (rankJoin == 2) {
										rank = rank + "金卡会员；";
									} else if (rankJoin == 3) {
										rank = rank + "白金会员；";
									} else if (rankJoin == 4) {
										rank = rank + "VIP会员；";
									} else if (rankJoin == 5) {
										rank = rank + "至尊会员；";
									} else
										rank = rank + "-；";
									if (rankManage == 1)
										rank = rank + Constants.RANK_1;
									else if (rankManage == 2)
										rank = rank + Constants.RANK_2;
									else if (rankManage == 3)
										rank = rank + Constants.RANK_3;
									else if (rankManage == 4)
										rank = rank + Constants.RANK_4;
									else if (rankManage == 5)
										rank = rank + Constants.RANK_5;
									else
										rank = rank + "-；";
									rank=rank+StringUtil.parseToString(user.getEntryTime(), DateUtil.yyyyMMddHHmmss);
							           
						            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
						            hm.put("id",user.getId());//id属性  ，数据传递    
						            hm.put("name", user.getUserId()+"("+user.getUserName()+"；"+rank+")"); //name属性，显示节点名称
							        hm.put("pId", 0);
							        hm.put("level", 0);
						            hm.put("isParent", true); 
						            hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
						            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
						            list.add(hm);  
							  }  
							
								request.setAttribute("userJson", JSON.toJSONString(list));
								request.setAttribute("userId", userId);

					} else {
						message = "未输入所要查询的会员编号，请输入！";
					}
				} else {
					message = "管理员未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		public  void admin_belongJson(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			
			   String id = StringUtil.notNull(request.getParameter("id")).toUpperCase();  
			   String levelstr = request.getParameter("level");
			   int level = Integer.valueOf(levelstr);
			   UserDao userDao = new UserDao();
			     List<User> users =userDao.findUsersByProperty("belong_id", Integer.valueOf(id));
				  List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  

			     if(users!=null){
			     for(int i=0;i<users.size();i++){
			      
		       
		     
		        	int nodeTag = users.get(i).getNodeTag();
		        	String nodestr = "";
		        	if(nodeTag==1) nodestr = "A";
		        	else if(nodeTag==2) nodestr = "B";
		        	int rankJoin = users.get(i).getRankJoin();
		        	int rankManage=users.get(i).getRank();
		        	String rank = "";
					if (rankJoin == 1) {
						rank = rank + "银卡会员；";
					} else if (rankJoin == 2) {
						rank = rank + "金卡会员；";
					} else if (rankJoin == 3) {
						rank = rank + "白金会员；";
					} else if (rankJoin == 4) {
						rank = rank + "VIP会员；";
					} else if (rankJoin == 5) {
						rank = rank + "至尊会员；";
					} else
						rank = rank + "-；";
					if (rankManage == 1)
						rank = rank + Constants.RANK_1;
					else if (rankManage == 2)
						rank = rank + Constants.RANK_2;
					else if (rankManage == 3)
						rank = rank + Constants.RANK_3;
					else if (rankManage == 4)
						rank = rank + Constants.RANK_4;
					else if (rankManage == 5)
						rank = rank + Constants.RANK_5;
					else
						rank = rank + "-；";
					rank=rank+StringUtil.parseToString(users.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
			           
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",users.get(i).getId());//id属性  ，数据传递    
		            hm.put("name", nodestr+String.valueOf(level+1)+":"+users.get(i).getUserId()+"("+users.get(i).getUserName()+"；"+rank+")"); //name属性，显示节点名称
			        hm.put("pId", id);
			        hm.put("level", level+1);
		            hm.put("isParent", true); 
		            hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
		            list.add(hm);  
		        }  
			     }
		        response.getWriter().write(JSON.toJSONString(list));  
		        
		    }  
		
		
		public  void admin_refereeJson(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			 String id = StringUtil.notNull(request.getParameter("id")).toUpperCase();  
			   String levelstr = request.getParameter("level");
			   int level = Integer.valueOf(levelstr);
			   UserDao userDao = new UserDao();
			     List<User> users =userDao.findUsersByProperty("referee_id", Integer.valueOf(id));
				  List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  

			     if(users!=null){
			     for(int i=0;i<users.size();i++){
		        	int rankJoin = users.get(i).getRankJoin();
		        	int rankManage=users.get(i).getRank();
		        	String rank = "";
					if (rankJoin == 1) {
						rank = rank + "银卡会员；";
					} else if (rankJoin == 2) {
						rank = rank + "金卡会员；";
					} else if (rankJoin == 3) {
						rank = rank + "白金会员；";
					} else if (rankJoin == 4) {
						rank = rank + "VIP会员；";
					} else if (rankJoin == 5) {
						rank = rank + "至尊会员；";
					} else
						rank = rank + "-；";
					if (rankManage == 1)
						rank = rank + Constants.RANK_1;
					else if (rankManage == 2)
						rank = rank + Constants.RANK_2;
					else if (rankManage == 3)
						rank = rank + Constants.RANK_3;
					else if (rankManage == 4)
						rank = rank + Constants.RANK_4;
					else if (rankManage == 5)
						rank = rank + Constants.RANK_5;
					else
						rank = rank + "-；";
					rank=rank+StringUtil.parseToString(users.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
			           
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",users.get(i).getId());//id属性  ，数据传递    
		            hm.put("name", users.get(i).getUserId()+"("+users.get(i).getUserName()+"；"+rank+")"); //name属性，显示节点名称
			        hm.put("pId", id);
			        hm.put("level", level+1);
		            hm.put("isParent", true); 
		            hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
		            list.add(hm);  
		        }  
			     }
		        response.getWriter().write(JSON.toJSONString(list));  
		        
		    }  
		
		public void admin_getReferee(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String userId = StringUtil.notNull(request
							.getParameter("userId")).toUpperCase();
					if (!userId.equals("")) {
						 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
						 UserDao userDao = new UserDao();
					     User user =userDao.findByUserId(userId);
							if(user!=null){           
								   int rankJoin = user.getRankJoin();
								   int rankManage=user.getRank();
						        	String rank = "";
									if (rankJoin == 1) {
										rank = rank + "银卡会员；";
									} else if (rankJoin == 2) {
										rank = rank + "金卡会员；";
									} else if (rankJoin == 3) {
										rank = rank + "白金会员；";
									} else if (rankJoin == 4) {
										rank = rank + "VIP会员；";
									} else if (rankJoin == 5) {
										rank = rank + "至尊会员；";
									} else
										rank = rank + "-；";
									if (rankManage == 1)
										rank = rank + Constants.RANK_1;
									else if (rankManage == 2)
										rank = rank + Constants.RANK_2;
									else if (rankManage == 3)
										rank = rank + Constants.RANK_3;
									else if (rankManage == 4)
										rank = rank + Constants.RANK_4;
									else if (rankManage == 5)
										rank = rank + Constants.RANK_5;
									else
										rank = rank + "-；";
									rank=rank+StringUtil.parseToString(user.getEntryTime(), DateUtil.yyyyMMddHHmmss);
							           
						            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
						            hm.put("id",user.getId());//id属性  ，数据传递    
						            hm.put("name", user.getUserId()+"("+user.getUserName()+"；"+rank+")"); //name属性，显示节点名称
							        hm.put("pId", 0);
							        hm.put("level", 0);
						            hm.put("isParent", true); 
						            hm.put("iconOpen","ztree/css/zTreeStyle/img/diy/1_open.png");
						            hm.put("iconClose","ztree/css/zTreeStyle/img/diy/1_close.png");
						            list.add(hm);  
							  }  
								request.setAttribute("userJson", JSON.toJSONString(list));
								request.setAttribute("userId", userId);

					} else {
						message = "未输入所要查询的会员编号，请输入！";
					}
				} else {
					message = "管理员未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		public void getBelong(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			UserDao userDao1 = new UserDao();
			 UserDao userDao = new UserDao();

			String message = "";
			try {
				if (user != null) {
					user = userDao1.findByUserId(user.getUserId());
					String auth1 = StringUtil.notNull(user.getAuthority());                    if(auth1.substring(10, 11).equals("1")){
						 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
						 String userId=StringUtil.notNull(request.getParameter("userId"));
						 if(userId.equals("")) userId=user.getUserId();
					    User user1 =userDao.findByUserId(userId);
					    if(user1!=null){  
					    boolean b = false;
					    if(user.getUserId().equals(user1.getUserId())){
					    	b =true;
					    }else{
						    String[] str = user1.getNode().split(",");
						    for(int i=0;i<str.length;i++){
						    	if(str[i].equals(String.valueOf(user.getId()))){
						    		b= true;
						    		break;
						    	}
						    }
					    }
							    if(b){     
								   int rankJoin = user1.getRankJoin();
								   int rankManage=user1.getRank();
						        	String rank = "";
									if (rankJoin == 1) {
										rank = rank + "银卡会员；";
									} else if (rankJoin == 2) {
										rank = rank + "金卡会员；";
									} else if (rankJoin == 3) {
										rank = rank + "白金会员；";
									} else if (rankJoin == 4) {
										rank = rank + "VIP会员；";
									} else if (rankJoin == 5) {
										rank = rank + "至尊会员；";
									} else
										rank = rank + "-；";
									if (rankManage == 1)
										rank = rank + Constants.RANK_1;
									else if (rankManage == 2)
										rank = rank + Constants.RANK_2;
									else if (rankManage == 3)
										rank = rank + Constants.RANK_3;
									else if (rankManage == 4)
										rank = rank + Constants.RANK_4;
									else if (rankManage == 5)
										rank = rank + Constants.RANK_5;
									else
										rank = rank + "-；";
									rank=rank+StringUtil.parseToString(user1.getEntryTime(), DateUtil.yyyyMMddHHmmss);
							           
						            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
						            hm.put("id",user1.getId());//id属性  ，数据传递    
						            hm.put("name", user1.getUserId()+"("+user1.getUserName()+"；"+rank+")"); //name属性，显示节点名称
							        hm.put("pId", 0);
							        hm.put("level", 0);
						            hm.put("isParent", true); 
						            hm.put("iconOpen","../ztree/css/zTreeStyle/img/diy/1_open.png");
						            hm.put("iconClose","../ztree/css/zTreeStyle/img/diy/1_close.png");
						            list.add(hm);  
						            Timestamp date = new Timestamp(new Date().getTime());
						    		UserLog userlog = new UserLog();
						    		UserLogDao userlogDao = new UserLogDao();
						    		userlog.setUid(user.getId());
					    			userlog.setUserId(user.getUserId());
					    			userlog.setUserName(user.getUserName());
					    			userlog.setType(ConstantsLog.USERLOGTYPE_5);
					    			userlog.setContents(userId+"会员销售关系查询。");
					    			userlog.setEntryTime(date);
					    			userlogDao.saveUserLog(userlog);
						            request.setAttribute("userJson", JSON.toJSONString(list));
						            request.setAttribute("userId", userId);
							 
					    } else {
							message = "查无此人的相关信息。";
							
						}
							    
				} else {
					message = "会员信息获取失败。";
				}
                    } else {
    					message = "会员查询权限获取失败。";
    				}
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				userDao1=null;
				userDao=null;
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_belong_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		public  void belongJson(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String message = "";
			UserDao userDao1 = new UserDao();
			   UserDao userDao = new UserDao();

			try {
				if (user != null) {
					user = userDao1.findByUserId(user.getUserId());
					String auth1 = StringUtil.notNull(user.getAuthority()); 
                    if(auth1.substring(10, 11).equals("1")){
			   String id = StringUtil.notNull(request.getParameter("id")).toUpperCase();  
			   String levelstr = request.getParameter("level");
			   int level = Integer.valueOf(levelstr);
			     List<User> users =userDao.findUsersByProperty("belong_id", Integer.valueOf(id));
				  List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  

			     if(users!=null){
			     for(int i=0;i<users.size();i++){
		     
		        	int nodeTag = users.get(i).getNodeTag();
		        	String nodestr = "";
		        	if(nodeTag==1) nodestr = "A";
		        	else if(nodeTag==2) nodestr = "B";
		        	int rankJoin = users.get(i).getRankJoin();
		        	int rankManage=users.get(i).getRank();
		        	String rank = "";
					if (rankJoin == 1) {
						rank = rank + "银卡会员；";
					} else if (rankJoin == 2) {
						rank = rank + "金卡会员；";
					} else if (rankJoin == 3) {
						rank = rank + "白金会员；";
					} else if (rankJoin == 4) {
						rank = rank + "VIP会员；";
					} else if (rankJoin == 5) {
						rank = rank + "至尊会员；";
					} else
						rank = rank + "-；";
					if (rankManage == 1)
						rank = rank + Constants.RANK_1;
					else if (rankManage == 2)
						rank = rank + Constants.RANK_2;
					else if (rankManage == 3)
						rank = rank + Constants.RANK_3;
					else if (rankManage == 4)
						rank = rank + Constants.RANK_4;
					else if (rankManage == 5)
						rank = rank + Constants.RANK_5;
					else
						rank = rank + "-；";
					rank=rank+StringUtil.parseToString(users.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
			           
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",users.get(i).getId());//id属性  ，数据传递    
		            hm.put("name", nodestr+String.valueOf(level+1)+":"+users.get(i).getUserId()+"("+users.get(i).getUserName()+"；"+rank+")"); //name属性，显示节点名称
			        hm.put("pId", id);
			        hm.put("level", level+1);
		            hm.put("isParent", true); 
		            hm.put("iconOpen","../ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","../ztree/css/zTreeStyle/img/diy/1_close.png");
		            list.add(hm);  
		        }  
			     }
		        response.getWriter().write(JSON.toJSONString(list));  
                    } else {
						message = "会员查询权限获取失败。";
					}
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			}  finally {
				userDao1=null;
				userDao=null;
			} 
		    }  
		
		
		public  void refereeJson(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String message = "";
			UserDao userDao1 = new UserDao();
			  UserDao userDao = new UserDao();
			try {
				if (user != null) {
					user = userDao1.findByUserId(user.getUserId());
					String auth1 = StringUtil.notNull(user.getAuthority()); 
					if(auth1.substring(11, 12).equals("1")){
			 String id = StringUtil.notNull(request.getParameter("id")).toUpperCase();  
			   String levelstr = request.getParameter("level");
			   int level = Integer.valueOf(levelstr);
			 
			     List<User> users =userDao.findUsersByProperty("referee_id", Integer.valueOf(id));
				  List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  

			     if(users!=null){
			     for(int i=0;i<users.size();i++){
		        	int rankJoin = users.get(i).getRankJoin();
		        	int rankManage=users.get(i).getRank();
		        	String rank = "";
					if (rankJoin == 1) {
						rank = rank + "银卡会员；";
					} else if (rankJoin == 2) {
						rank = rank + "金卡会员；";
					} else if (rankJoin == 3) {
						rank = rank + "白金会员；";
					} else if (rankJoin == 4) {
						rank = rank + "VIP会员；";
					} else if (rankJoin == 5) {
						rank = rank + "至尊会员；";
					} else
						rank = rank + "-；";
					if (rankManage == 1)
						rank = rank + Constants.RANK_1;
					else if (rankManage == 2)
						rank = rank + Constants.RANK_2;
					else if (rankManage == 3)
						rank = rank + Constants.RANK_3;
					else if (rankManage == 4)
						rank = rank + Constants.RANK_4;
					else if (rankManage == 5)
						rank = rank + Constants.RANK_5;
					else
						rank = rank + "-；";
					rank=rank+StringUtil.parseToString(users.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
			           
		            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
		            hm.put("id",users.get(i).getId());//id属性  ，数据传递    
		            hm.put("name", users.get(i).getUserId()+"("+users.get(i).getUserName()+"；"+rank+")"); //name属性，显示节点名称
			        hm.put("pId", id);
			        hm.put("level", level+1);
		            hm.put("isParent", true); 
		            hm.put("iconOpen","../ztree/css/zTreeStyle/img/diy/1_open.png");
		            hm.put("iconClose","../ztree/css/zTreeStyle/img/diy/1_close.png");
		            list.add(hm);  
		        }  
			     }
		        response.getWriter().write(JSON.toJSONString(list));  
                    } else {
						message = "会员查询权限获取失败。";
					}
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			}  finally {
				userDao1=null;
				userDao=null;
			}
		    }  
		
		public void getReferee(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			User user = (User) request.getSession().getAttribute("sys_user");
			String message = "";
			UserDao userDao1 = new UserDao();
			 UserDao userDao = new UserDao();

			try {
				if (user != null) {
					user = userDao1.findByUserId(user.getUserId());
					String auth1 = StringUtil.notNull(user.getAuthority()); 
					if(auth1.substring(11, 12).equals("1")){
						 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
						 String userId=StringUtil.notNull(request.getParameter("userId"));
						 if(userId.equals("")) userId=user.getUserId();
					    User user1 =userDao.findByUserId(userId);
					    if(user1!=null){  
					    boolean b = false;
					    if(user.getUserId().equals(user1.getUserId())){
					    	b =true;
					    }else{
						    String[] str = user1.getRefereeNode().split(",");
						    for(int i=0;i<str.length;i++){
						    	if(str[i].equals(String.valueOf(user.getId()))){
						    		b= true;
						    		break;
						    	}
						    }
					    }
							    if(b){       
								   int rankJoin = user1.getRankJoin();
						        	int rankManage=user1.getRank();
						        	String rank = "";
									if (rankJoin == 1) {
										rank = rank + "银卡会员；";
									} else if (rankJoin == 2) {
										rank = rank + "金卡会员；";
									} else if (rankJoin == 3) {
										rank = rank + "白金会员；";
									} else if (rankJoin == 4) {
										rank = rank + "VIP会员；";
									} else if (rankJoin == 5) {
										rank = rank + "至尊会员；";
									} else
										rank = rank + "-；";
									if (rankManage == 1)
										rank = rank + Constants.RANK_1;
									else if (rankManage == 2)
										rank = rank + Constants.RANK_2;
									else if (rankManage == 3)
										rank = rank + Constants.RANK_3;
									else if (rankManage == 4)
										rank = rank + Constants.RANK_4;
									else if (rankManage == 5)
										rank = rank + Constants.RANK_5;
									else
										rank = rank + "-；";
									rank=rank+StringUtil.parseToString(user1.getEntryTime(), DateUtil.yyyyMMddHHmmss);
							           
						            HashMap<String,Object> hm = new HashMap<String,Object>();   //最外层，父节点             
						            hm.put("id",user1.getId());//id属性  ，数据传递    
						            hm.put("name", user1.getUserId()+"("+user1.getUserName()+"；"+rank+")"); //name属性，显示节点名称
							        hm.put("pId", 0);
							        hm.put("level", 0);
						            hm.put("isParent", true); 
						            hm.put("iconOpen","../ztree/css/zTreeStyle/img/diy/1_open.png");
						            hm.put("iconClose","../ztree/css/zTreeStyle/img/diy/1_close.png");
						            list.add(hm);  
						            Timestamp date = new Timestamp(new Date().getTime());
						    		UserLog userlog = new UserLog();
						    		UserLogDao userlogDao = new UserLogDao();
						    		userlog.setUid(user.getId());
					    			userlog.setUserId(user.getUserId());
					    			userlog.setUserName(user.getUserName());
					    			userlog.setType(ConstantsLog.USERLOGTYPE_4);
					    			userlog.setContents(userId+"会员服务关系查询。");
					    			userlog.setEntryTime(date);
					    			userlogDao.saveUserLog(userlog);
						            request.setAttribute("userJson", JSON.toJSONString(list));
						            request.setAttribute("userId", userId);
							 
					    } else {
							message = "查无此人的相关信息。";
							
						}
					   
				} else {
					message = "会员信息获取失败。";
				}
                    } else {
						message = "会员查询权限获取失败。";
					}
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				userDao1= null;
				userDao= null;
				if (message.equals("")) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_json.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_referee_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		public void admin_user_authority_edit(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String userId = StringUtil.notNull(request.getParameter("id")).toUpperCase();
			
			String message = "";
			try {
				if(admin!=null){
					UserDao userDao = new UserDao();
					User user = userDao.findByUserId(userId);
					if(user!=null){
						request.setAttribute("admin_user", user);
						request.setAttribute("userId", userId);
					}else{
						message = "会员信息获取失败。";
					}
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message ="系统繁忙中，请稍后再试。";
				e.printStackTrace();
			} finally {
				
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_authority_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_authority_edit.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		public void admin_user_authority_save(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String id = StringUtil.notNull(request.getParameter("id"));
			String userId = StringUtil.notNull(request.getParameter("userId"));
			String auth_old = StringUtil.notNull(request.getParameter("auth_old"));
			String isMax = StringUtil.notNull(request.getParameter("isMax"));
			String capFactor = StringUtil.notNull(request.getParameter("capFactor"));
			String message = "";
			try {
				if(admin!=null){
					if(!(id.equals("")||isMax.equals("")||capFactor.equals(""))){
					UserDao userDao = new UserDao();
					User user = new User();
					user.setId(Integer.valueOf(id));
					String auth_str = "";
					for(int i=1;i<31;i++){
						String str = StringUtil.notNull(request.getParameter("str"+i));
						if(str.equals("")){
							if(i<11)
							str ="1";
							else str="0";
						}
						auth_str += str;
					}
					if(!auth_str.equals(auth_old)){
						user.setAuthority(auth_str);
						user.setIsMax(Integer.valueOf(isMax));
						user.setCapFactor(Integer.valueOf(capFactor));
						int num = userDao.updateUsers(user);
						if(num>0){
							request.setAttribute("user_id", userId);
							message = userId+"会员权限修改成功。";
						}else{
							message = "会员权限保存失败，请重新核对。";
						}
					}else{
						message = "会员权限未发生变化，无需更新。";
					}
					}else{
						message = "会员信息获取失败。";
					}
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message ="系统繁忙中，请稍后再试。";
				e.printStackTrace();
			} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_authority_message.jsp");
					dispatcher.forward(request, response);
				
			}
		}
		
		public void admin_user_hide(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if (admin != null) {
						String[] ids = request.getParameterValues("ids");
						if (ids.length > 0) {
							if(ids.length<=200){
									UserDao userDao = new UserDao();
									message = userDao.updateUsers(ids,1);
									if(message.equals("yes")){
										Timestamp date = new Timestamp(new Date().getTime());
										message = "用户隐藏成功，锁定用户将无法在会员列表中查看！";
									}
							} else {
								message = "一次性隐藏会员不能超过200个，请分批进行隐藏";
							}
						} else {
							message = "未获得会员相应的ID信息，请重试！";
						}
				} else {
					message = "管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_hide_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void admin_user_hide_reset(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message ="";
			try {
				if (admin != null) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if(ids.length<=200){
								UserDao userDao = new UserDao();
								message = userDao.updateUsers(ids,0);
								if(message.equals("yes")){
									Timestamp date = new Timestamp(new Date().getTime());
									message = "用户取消隐藏成功，用户将在会员列表中查看！";
									cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
								}
						} else {
							message= "一次性取消隐藏会员不能超过200个，请分批进行隐藏";
						}
					} else {
					  message = "未获得会员相应的ID信息，请重试！";
					}
				} else {
					message = "管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message="数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_hide_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void admin_user_hide_list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
			String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int pageNo = 1;
			int pageSize = 20;
			String message = "";
			try {
				if(admin!=null){
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				User user1 = new User();
				user1.setUserId(userId);
				user1.setIsHide(1);
					UserDao userDao = new UserDao();
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = userDao.findUserByPage(user1,pager);
					request.setAttribute("pager", pager);
					request.setAttribute("userId", userId);
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} finally {
				
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_hide_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_hide_list.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		public void admin_user_money_hide_list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
			String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int pageNo = 1;
			int pageSize = 20;
			String message = "";
			try {
				if(admin!=null){
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				User user1 = new User();
				user1.setUserId(userId);
				user1.setIsHide(1);
					UserDao userDao = new UserDao();
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = userDao.findUserByPage(user1,pager);
					request.setAttribute("pager", pager);
					request.setAttribute("userId", userId);
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} finally {
				
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_money_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_money_hide_list.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		public void user_password1_update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String message="";
			try {
				if (user != null) {
						String password = StringUtil.notNull(request.getParameter("password"));
						UserDao userDao = new UserDao();
								if(userDao.updatePsw(user.getUserId(), MD5.GetMD5Code(password), 1)>0){
										message = "用户登陆密码修改成功，请重新登陆。";
								}else{
									message = "密码修改失败，或与原密码一样。";
								}
							
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("password1_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void user_password2_update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String message="";
			try {
				if (user != null) {
						String password = StringUtil.notNull(request.getParameter("password"));
						UserDao userDao = new UserDao();
						if(userDao.updatePsw(user.getUserId(), MD5.GetMD5Code(password), 2)>0){
								message = "用户支付密码修改成功，请重新登陆。";
						}else{
							message = "密码修改失败，或与原密码一样。";
						}
					
							
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("password2_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void admin_psw1_init(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if (admin != null) {
						String[] ids = request.getParameterValues("ids");
						if (ids.length > 0) {
							UserDao userDao = new UserDao();
							Timestamp date = new Timestamp(
									new Date().getTime());
							if(userDao.updatePsw(ids,MD5.GetMD5Code("12345678"),1)>0){
								message="用户登陆密码重置成功，请及时通知会员修改密码！";
								cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
							
						} else {
							message= "密码批量重置失败。";
						}

					} else {
						message="未获得会员相应的ID信息，请重试！";
					}
				} else {
					message="管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
						
				e.printStackTrace();
				message="数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void admin_psw2_init(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if (admin != null) {
						String[] ids = request.getParameterValues("ids");
						if (ids.length > 0) {
							UserDao userDao = new UserDao();
							Timestamp date = new Timestamp(
									new Date().getTime());
							if(userDao.updatePsw(ids,MD5.GetMD5Code("12345678"),2)>0){
								
								message="用户支付密码重置成功，请及时通知会员修改密码！";
								cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
							
							} else {
								message= "密码批量重置失败。";
							}

						} else {
							message="未获得会员相应的ID信息，请重试！";
						}
				} else {
					message="管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
						
				e.printStackTrace();
				message="数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void getMD5(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String psw2 = StringUtil.notNull(request.getParameter("psw2"));
			try {
					if (psw2.equals("")) {
						psw2 = "";
					} else {
						psw2 = MD5.GetMD5Code(psw2);
					}
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("psw2",psw2);
				request.getSession().setAttribute("psw2",psw2);
				JSONObject jsonObject = JSONObject.fromObject(object);
				PrintWriter pw = response.getWriter();
				pw.print(jsonObject.toString());
				pw.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		public  void rankJoin_up(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String message="";
			try {
				if (user != null) {
					String type = StringUtil.notNull(request
							.getParameter("type"));
					String userId ="";
					if(type.equals("2")){
						userId = StringUtil.notNull(request
								.getParameter("userId"));
					}else userId = user.getUserId();
					
						UserDao userDao = new UserDao();
						User user1 = userDao.findByUserId(userId);
						if(user1!=null){	
							request.setAttribute("user1", user1);		
						} else {
							message="会员信息获取失败，请重试！";
						}
							
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				if(message.equals("")){
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("rankJoin_up_a.jsp");
					dispatcher.forward(request, response);
					}else{
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("rankJoin_up_message.jsp");
						dispatcher.forward(request, response);
					}
			}
		}
		
		public  void rankJoin_up_a(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String message="";
			try {
				if (user != null) {
					String userId = StringUtil.notNull(request.getParameter("userId"));
					UserDao userDao  = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if (user1!= null) {
					ParamDao paramDao = new ParamDao();
					Param p = paramDao.findParamByName("报单金额");
					if (p!= null) {
						String rankJoinStr = StringUtil.notNull(request
								.getParameter("rankJoin"));
						String rankJoinOldStr = StringUtil.notNull(request
								.getParameter("rankJoinOld"));
						if(!rankJoinStr.equals("")){
							int rankJoin =Integer.valueOf(rankJoinStr);
							int rankJoinOld =Integer.valueOf(rankJoinOldStr);
							double emoney=0;
							double emoney1=0;

							if (rankJoin == 1) {
								emoney = p.getAmount_1();
							} else if (rankJoin == 2) {
								emoney =  p.getAmount_2();
							} else if (rankJoin == 3) {
								emoney =  p.getAmount_3();
							} else if (rankJoin == 4) {
								emoney =  p.getAmount_4();
							} else if (rankJoin == 5) {
								emoney =  p.getAmount_5();
							} 
							if (rankJoinOld == 1) {
								emoney1 =  p.getAmount_1();
							} else if (rankJoinOld == 2) {
								emoney1 =  p.getAmount_2();
							} else if (rankJoinOld == 3) {
								emoney1 =  p.getAmount_3();
							} else if (rankJoinOld == 4) {
								emoney1 =  p.getAmount_4();
							} else if (rankJoinOld == 5) {
								emoney1 =  p.getAmount_5();
							} 
							emoney = emoney-emoney1;
							
							if(emoney<=0) emoney = 0;
							user1.setEmoney(emoney);
							user1.setRankJoin(rankJoin);
							user1.setRankJoinOld(rankJoinOld);
							request.getSession().setAttribute("user1", user1);
							ProductDao productDao = new ProductDao();
							Product product = new Product();
							product.setProductType("报单产品");
							product.setState("1");
							product.setIsHide(1);
							List<Product> plist = productDao.findByProduct(product);
							request.setAttribute("coll", plist);
							AddressDao  adrDao = new AddressDao();
							List<Address> coll_adr = adrDao.findByUserId(user1.getUserId());
							request.setAttribute("coll_adr", coll_adr);	
							int token_new = (int)(1+Math.random()*(1000000-1+1));
							request.getSession().setAttribute("token", String.valueOf(token_new));
						}else{
							message="会员等级信息获取失败，请重试！";
							}
					}else{
						
						message="报单参数获取失败，请重试！";
						}
						}else{
					
						message="会员信息获取失败，请重试！";
						}
							
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				if(message.equals("")){
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("rankJoin_up_b.jsp");
					dispatcher.forward(request, response);
					}else{
						request.setAttribute("message", message);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("rankJoin_up_message.jsp");
						dispatcher.forward(request, response);
					}
			}
		}
		
		public  void rankJoin_up_b(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			User user = (User) request.getSession().getAttribute("sys_user");
			String token_old = (String) request.getSession().getAttribute("token");
			String token = (String) request.getParameter("token");
			int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token_new));
			User user1 = (User) request.getSession().getAttribute("user1");
			String message="";
			try {
				if (user != null) {
					if (StringUtil.notNull(token_old).equals(token)) {
						String[] numstr = request.getParameterValues("numstr");
						String[] pid = request.getParameterValues("pid");
						String addressId = StringUtil.notNull(request.getParameter("addressId"));
						String centerId = StringUtil.notNull(request.getParameter("centerId"));

					AddressDao  adrDao = new AddressDao();
					Address  adr = adrDao.findById(Integer.valueOf(addressId));
					Timestamp date = new Timestamp(new Date().getTime());
					String rid = "R"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));
					Order orders = new Order();
					orders.setOrderId(rid);
					orders.setuId(user1.getId());
					orders.setUserId(user1.getUserId());
					orders.setUserName(user1.getUserName());
					orders.setOrderType(5);
					orders.setAddress(adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAddress());
					orders.setReceiver(adr.getReceiver());
					orders.setPhone(adr.getPhone());
					orders.setState(1);
					orders.setOrderTime(date);
					orders.setUserByDeclarationId(user.getUserId());
					orders.setUserByCenterId(centerId);
					orders.setCenterId(user.getId());
					JoinInfo jf = new JoinInfo();
					jf.setUid(user1.getId());
					jf.setUserId(user1.getUserId());
					jf.setUserName(user1.getUserName());
					jf.setNewRankJoin(user1.getRankJoin());
					jf.setOldRankJoin(user1.getRankJoinOld());
					jf.setState(2);
					jf.setDeclarationId(user.getId());
					jf.setRid(user1.getRefereeId());
					jf.setEntryTime(date);
					UserDao  userDao = new UserDao();
					message = userDao.saveRankJoinUp(user,user1,orders,jf,pid,numstr);
					if(message.equals("yes"))
						message = "升级订单保存成功,请在订单列表中查看。";
					
					} else {
						message = "请勿重复提交数据。";
					}
				} else {
					message = "用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据保存有误，请重新录入！";
			} finally {
				
						request.setAttribute("message", message);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("rankJoin_up_message.jsp");
						dispatcher.forward(request, response);
			}
		}
		
		public void admin_referee_update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					UserDao userDao = new UserDao();
					User  up_user = userDao.findByUserId(userId);
					if(up_user!=null){
						request.getSession().setAttribute("up_user", up_user);
					}else message="用户不存在，请重新输入！";
					
				} else {
					message = "用户未登陆，请重新登陆！";

				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_referee_update_a.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_referee_update_message.jsp");
				dispatcher.forward(request, response);
			}
		}

		}
		
		public void admin_referee_update_save(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String userId = StringUtil.notNull(request.getParameter("id")).toUpperCase();
					String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
					
					if (!userId.equals("")) {
						if (!refereeId.equals("")) {
							UserDao userDao = new UserDao();
							message = userDao.updateRefereeId(userId, refereeId,admin.getAdminName());
							
						}else message="最新推荐人信息获取失败";
						
					}else message="用户不存在，请重新输入！";
					
				} else {
					message = "用户未登陆，请重新登陆！";

				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_referee_update_message.jsp");
				dispatcher.forward(request, response);
		}

		}
		
		public void admin_belong_update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					UserDao userDao = new UserDao();
					User  up_user = userDao.findByUserId(userId);
					if(up_user!=null){
						request.getSession().setAttribute("up_user", up_user);
					}else message="用户不存在，请重新输入！";
					
				} else {
					message = "用户未登陆，请重新登陆！";

				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			if (message.equals("")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_belong_update_a.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_belong_update_message.jsp");
				dispatcher.forward(request, response);
			}
		}

		}
		
		public void admin_belong_update_save(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String userId = StringUtil.notNull(request.getParameter("id")).toUpperCase();
					String belongId = StringUtil.notNull(request.getParameter("belongId")).toUpperCase();
					String nodeTagStr = StringUtil.notNull(request.getParameter("nodeTag"));

					if (!userId.equals("")) {
						if (!belongId.equals("")) {
							if(!userId.equals(belongId)){
								if (!nodeTagStr.equals("")) {
									Integer nodeTag = Integer.valueOf(nodeTagStr);
							UserDao userDao = new UserDao();
							message = userDao.updateBelongId(userId, belongId,nodeTag,admin.getAdminName());
								}else message="销售商区域信息获取失败。";
							}else message="销售商不能为本人";
						}else message="最新推荐人信息获取失败";
						
					}else message="用户不存在，请重新输入！";
					
				} else {
					message = "用户未登陆，请重新登陆！";

				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_belong_update_message.jsp");
				dispatcher.forward(request, response);
		}

		}
		
		
		public void exportExcel_user_list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员列表"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String rankJoinStr = StringUtil.notNull(request.getParameter("rankJoin"));
			String rankManageStr = StringUtil.notNull(request.getParameter("rankManage"));
			String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
			String belongId = StringUtil.notNull(request.getParameter("belongId")).toUpperCase();
			String declarationId = StringUtil.notNull(request.getParameter("declarationId")).toUpperCase();
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
				int rankJoin =0;
				int rankManage =0;
				if(!rankJoinStr.equals("")) rankJoin= Integer.valueOf(rankJoinStr);
				if(!rankManageStr.equals("")) rankManage= Integer.valueOf(rankManageStr);
				User user1 = new User();
				user1.setUserId(userId);;
				user1.setRankJoin(rankJoin);
				user1.setRankManage(rankManage);
				user1.setUserByBelongId(belongId);
				user1.setUserByRefereeId(refereeId);
				user1.setUserByDeclarationId(declarationId);
				user1.setIsHide(0);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					user1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					user1.setEndTime(endTime);
				}
					UserDao userDao = new UserDao();
					result = userDao.findUserByList(user1);
					String[][] content = new String[result.size()+1][30];
					content[0][0]="序号";
					content[0][1]="会员编号";
					content[0][2]="会员名称";
					content[0][3]="会员等级";
					content[0][4]="管理级别";
					content[0][5]="销售商";
					content[0][6]="销售区域";
					content[0][7]="服务商";
					content[0][8]="报单人";
					content[0][9]="证件类型";
					content[0][10]="证件号码";
					content[0][11]="联系电话";
					content[0][12]="报单券余额";
					content[0][13]="购物券余额";
					content[0][14]="复消券余额";
					content[0][15]="奖金券余额";
					content[0][16]="电子券余额";
					content[0][17]="电子券有效期";
					content[0][18]="银行账户信息";
					content[0][19]="注册时间";
					content[0][20]="空单标示";
					content[0][21]="奖金状态";
					content[0][22]="当前状态";
					content[0][23]="空单数量";
					content[0][24]="移网标示";
					
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
						String rankstr="";
						if(result.get(i).getRankJoin()==0) rankstr = "-";
						else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
						else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
						else if(result.get(i).getRankJoin()==3) rankstr = "白金会员";
						else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
						else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
						content[i+1][3]  = StringUtil.notNull(rankstr);
						String managestr="";
						if(result.get(i).getRankManage()==0) managestr = "-";
						else if(result.get(i).getRankManage()==1) managestr = "一星经理";
						else if(result.get(i).getRankManage()==2) managestr = "二星经理";
						else if(result.get(i).getRankManage()==3) managestr = "三星经理";
						else if(result.get(i).getRankManage()==4) managestr = "四星经理";
						else if(result.get(i).getRankManage()==5) managestr = "五星经理";
						else if(result.get(i).getRankManage()==6) managestr = "一星钻石";
						else if(result.get(i).getRankManage()==7) managestr = "二星钻石";
						else if(result.get(i).getRankManage()==8) managestr = "三星钻石";
						else if(result.get(i).getRankManage()==9) managestr = "四星钻石";
						else if(result.get(i).getRankManage()==10) managestr = "五星钻石";
						else if(result.get(i).getRankManage()==11) managestr = "董事";
						content[i+1][4]  = StringUtil.notNull(managestr);
						content[i+1][5]  =  StringUtil.notNull(result.get(i).getUserByBelongId());
						String nodestr = "";
						if(result.get(i).getNodeTag()==1) nodestr="A区";
						else if(result.get(i).getNodeTag()==2) nodestr="B区";
						else if(result.get(i).getNodeTag()==3) nodestr="C区";
						else nodestr="-";
						content[i+1][6]  = StringUtil.notNull(nodestr);
						content[i+1][7]  = StringUtil.notNull(result.get(i).getUserByRefereeId());
						content[i+1][8]  = StringUtil.notNull(result.get(i).getUserByDeclarationId());
						content[i+1][9] = StringUtil.notNull(result.get(i).getDocumentType());
						content[i+1][10] = StringUtil.notNull(result.get(i).getNumId());
						content[i+1][11]  = StringUtil.notNull(result.get(i).getTel());
						content[i+1][12]  = StringUtil.decimalFormat(result.get(i).getEmoney());
						content[i+1][13]  = StringUtil.decimalFormat(0);
						content[i+1][14]  = StringUtil.decimalFormat(result.get(i).getDmoney());
						content[i+1][15]  = StringUtil.decimalFormat(result.get(i).getRmoney());
						content[i+1][16]  = StringUtil.decimalFormat(result.get(i).getPmoney());
						content[i+1][17] = StringUtil.parseToString(result.get(i).getValidtyTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][18]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName())+StringUtil.notNull(result.get(i).getBankAdr());
						content[i+1][19] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
						String emptystr ="";
						if(result.get(i).getIsEmpty()==0) emptystr = "否";
						else if(result.get(i).getIsEmpty()==1) emptystr = "是";
						content[i+1][20] = emptystr;
						String tagstr ="";
						if(result.get(i).getPayTag()==0) tagstr = "锁定";
						else if(result.get(i).getPayTag()==1) tagstr = "正常";
						content[i+1][21] = tagstr;
						String stateStr ="";
						if(result.get(i).getState()==0) stateStr = "下线";
						else if(result.get(i).getState()>=1) stateStr = "在线";
						content[i+1][22] = stateStr;
						content[i+1][23] = String.valueOf(result.get(i).getEmptyNum());
						content[i+1][24] = String.valueOf(result.get(i).getIsOld());
						
					}
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("会员列表");  
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
		
		public void exportExcel_user_hide_list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员列表"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String rankJoinStr = StringUtil.notNull(request.getParameter("rankJoin"));
			String rankManageStr = StringUtil.notNull(request.getParameter("rankManage"));
			String refereeId = StringUtil.notNull(request.getParameter("refereeId")).toUpperCase();
			String belongId = StringUtil.notNull(request.getParameter("belongId")).toUpperCase();
			String declarationId = StringUtil.notNull(request.getParameter("declarationId")).toUpperCase();
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
				int rankJoin =0;
				int rankManage =0;
				if(!rankJoinStr.equals("")) rankJoin= Integer.valueOf(rankJoinStr);
				if(!rankManageStr.equals("")) rankManage= Integer.valueOf(rankManageStr);
				User user1 = new User();
				user1.setUserId(userId);;
				user1.setRankJoin(rankJoin);
				user1.setRankManage(rankManage);
				user1.setUserByBelongId(belongId);
				user1.setUserByRefereeId(refereeId);
				user1.setIsHide(1);
				user1.setUserByDeclarationId(declarationId);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					user1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					user1.setEndTime(endTime);
				}
					UserDao userDao = new UserDao();
					result = userDao.findUserByList(user1);
					String[][] content = new String[result.size()+1][30];
					content[0][0]="序号";
					content[0][1]="会员编号";
					content[0][2]="会员名称";
					content[0][3]="会员等级";
					content[0][4]="管理级别";
					content[0][5]="销售商";
					content[0][6]="销售区域";
					content[0][7]="服务商";
					content[0][8]="报单人";
					content[0][9]="证件类型";
					content[0][10]="证件号码";
					content[0][11]="联系电话";
					content[0][12]="报单券余额";
					content[0][13]="购物券余额";
					content[0][14]="复消券余额";
					content[0][15]="奖金券余额";
					content[0][16]="电子券余额";
					content[0][17]="电子券有效期";
					content[0][18]="银行账户信息";
					content[0][19]="注册时间";
					content[0][20]="空单标示";
					content[0][21]="奖金状态";
					content[0][22]="当前状态";
					content[0][23]="空单数量";
					content[0][24]="移网标示";
					
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
						String rankstr="";
						if(result.get(i).getRankJoin()==0) rankstr = "-";
						else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
						else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
						else if(result.get(i).getRankJoin()==3) rankstr = "白金会员";
						else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
						else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
						content[i+1][3]  = StringUtil.notNull(rankstr);
						String managestr="";
						if(result.get(i).getRankManage()==0) managestr = "-";
						else if(result.get(i).getRankManage()==1) managestr = "一星经理";
						else if(result.get(i).getRankManage()==2) managestr = "二星经理";
						else if(result.get(i).getRankManage()==3) managestr = "三星经理";
						else if(result.get(i).getRankManage()==4) managestr = "四星经理";
						else if(result.get(i).getRankManage()==5) managestr = "五星经理";
						else if(result.get(i).getRankManage()==6) managestr = "一星钻石";
						else if(result.get(i).getRankManage()==7) managestr = "二星钻石";
						else if(result.get(i).getRankManage()==8) managestr = "三星钻石";
						else if(result.get(i).getRankManage()==9) managestr = "四星钻石";
						else if(result.get(i).getRankManage()==10) managestr = "五星钻石";
						else if(result.get(i).getRankManage()==11) managestr = "董事";
						content[i+1][4]  = StringUtil.notNull(managestr);
						content[i+1][5]  =  StringUtil.notNull(result.get(i).getUserByBelongId());
						String nodestr = "";
						if(result.get(i).getNodeTag()==1) nodestr="A区";
						else if(result.get(i).getNodeTag()==2) nodestr="B区";
						else if(result.get(i).getNodeTag()==3) nodestr="C区";
						else nodestr="-";
						content[i+1][6]  = StringUtil.notNull(nodestr);
						content[i+1][7]  = StringUtil.notNull(result.get(i).getUserByRefereeId());
						content[i+1][8]  = StringUtil.notNull(result.get(i).getUserByDeclarationId());
						content[i+1][9] = StringUtil.notNull(result.get(i).getDocumentType());
						content[i+1][10] = StringUtil.notNull(result.get(i).getNumId());
						content[i+1][11]  = StringUtil.notNull(result.get(i).getTel());
						content[i+1][12]  = StringUtil.decimalFormat(result.get(i).getEmoney());
						content[i+1][13]  = StringUtil.decimalFormat(0);
						content[i+1][14]  = StringUtil.decimalFormat(result.get(i).getDmoney());
						content[i+1][15]  = StringUtil.decimalFormat(result.get(i).getRmoney());
						content[i+1][16]  = StringUtil.decimalFormat(result.get(i).getPmoney());
						content[i+1][17] = StringUtil.parseToString(result.get(i).getValidtyTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][18]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName())+StringUtil.notNull(result.get(i).getBankAdr());
						content[i+1][19] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
						String emptystr ="";
						if(result.get(i).getIsEmpty()==0) emptystr = "否";
						else if(result.get(i).getIsEmpty()==1) emptystr = "是";
						content[i+1][20] = emptystr;
						String tagstr ="";
						if(result.get(i).getPayTag()==0) tagstr = "锁定";
						else if(result.get(i).getPayTag()==1) tagstr = "正常";
						content[i+1][21] = tagstr;
						String stateStr ="";
						if(result.get(i).getState()==0) stateStr = "下线";
						else if(result.get(i).getState()>=1) stateStr = "在线";
						content[i+1][22] = stateStr;
						content[i+1][23] = String.valueOf(result.get(i).getEmptyNum());
						content[i+1][24] = String.valueOf(result.get(i).getIsOld());
						
					}
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("会员列表");  
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
		
		public void admin_referee_reback_list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String pageNoStr = request.getParameter("pageNoStr");
					String pageSizeStr = request.getParameter("pageSizeStr");
					int pageNo = 1;
					int pageSize = 20;
					   String userId = StringUtil.notNull(request.getParameter("userId"));
					   request.setAttribute("userId", userId);
					   if (!StringUtil.notNull(pageNoStr).equals(""))
							pageNo = Integer.valueOf(pageNoStr);
						if (!StringUtil.notNull(pageSizeStr).equals(""))
							pageSize = Integer.valueOf(pageSizeStr);
						int startIndex = pageSize * (pageNo - 1);
						int endIndex = pageSize * pageNo;
						UserDao userDao = new UserDao();
						User user1 = userDao.findByUserId(userId);
						if(user1!=null){
						 String[] str = StringUtil.notNull(user1.getRefereeNode()).split(",");
						 String sql="";
						 for(int i=str.length;i>0;i--){
							 if(sql.equals("")) sql = "("+str[i-1];
							 else sql+=","+str[i-1];
						 }
						 if(!sql.equals("")) {
							 sql=" id in "+sql+") order by id desc limit "+startIndex+","+endIndex;
							 userDao = new UserDao();
							 List<User> coll = userDao.findUserBySql(sql);
							 Pager pager = new Pager();
							 pager.setStartIndex(startIndex);
							 pager.setEndIndex(endIndex);
							 pager.setPageNo(pageNo);
							 pager.setPageSize(pageSize);
							 pager.setRowCount(str.length);
							 pager.setResultList(coll);
							 request.setAttribute("pager", pager);
						 }
						 
					
					}else message="用户不存在，请重新输入！";
					
				} else {
					message = "用户未登陆，请重新登陆！";

				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_referee_reback_list.jsp");
				dispatcher.forward(request, response);
		}

		}
		
		public void admin_belong_reback_list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message = "";
			try {
				if (admin != null) {
					String pageNoStr = request.getParameter("pageNoStr");
					String pageSizeStr = request.getParameter("pageSizeStr");
					int pageNo = 1;
					int pageSize = 20;
					   String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					   request.setAttribute("userId", userId);
					   if (!StringUtil.notNull(pageNoStr).equals(""))
							pageNo = Integer.valueOf(pageNoStr);
						if (!StringUtil.notNull(pageSizeStr).equals(""))
							pageSize = Integer.valueOf(pageSizeStr);
						int startIndex = pageSize * (pageNo - 1);
						int endIndex = pageSize * pageNo;
						UserDao userDao = new UserDao();
						User user1 = new User();
						user1.setUserId("");;
						Integer max = userDao.maxId(user1);
						   int uid= 0;

						if(max!=null){
							UserDao userDao1 = new UserDao();
							List<User> ulist = userDao1.findUserByList(user1);
							User[] user =new User[max+1];
							for(int i=0;i<ulist.size();i++){
								int id = ulist.get(i).getId();
								if(user[id]==null) user[id] = new User();
								user[id].setId(ulist.get(i).getId());
					        	user[id].setUserId(ulist.get(i).getUserId());
					        	if(user[id].getUserId().equals(userId)) uid = id;
					        	user[id].setUserName(ulist.get(i).getUserName());
					        	user[id].setRankJoin(ulist.get(i).getRankJoin());
					        	user[id].setRankManage(ulist.get(i).getRankManage());
					        	user[id].setNode(ulist.get(i).getNode());
					        	user[id].setNodeABC(ulist.get(i).getNodeABC());
					        	user[id].setRefereeNode(ulist.get(i).getRefereeNode());
					        	user[id].setState(ulist.get(i).getState());
					        	user[id].setEntryTime(ulist.get(i).getEntryTime());
					        	user[id].setArea("");
					        	user[id].setTag(0);
							}
					if(uid>0){
							  List<User> result = new ArrayList<User>();  
						        result.add(user[uid]);
						        String[] str = StringUtil.notNull(user[uid].getNode()).split(",");
						        String[] str1 = StringUtil.notNull(user[uid].getNodeABC()).split(",");
						        int t=1;
						        for(int i=str.length-1;i>=0;i--){
						        	if(!str[i].equals("")){
						        		int sid = Integer.valueOf(str[i]);
						        		if(user[sid]!=null){
						        			user[sid].setTag(t);
						        			user[sid].setArea(str1[i]);
						        			result.add(user[sid]);
						        			t++;
						        		}
						        	}
						        }
						        if (result.size() > 0) {
									
									if (result.size() < endIndex)
										endIndex = result.size();
									
									
							 List<User> coll = result.subList(startIndex, endIndex);
							 Pager pager = new Pager();
							 pager.setStartIndex(startIndex);
							 pager.setEndIndex(endIndex);
							 pager.setPageNo(pageNo);
							 pager.setPageSize(pageSize);
							 pager.setRowCount(str.length);
							 pager.setResultList(coll);
							 request.setAttribute("pager", pager);
						 }
					}else message="暂无记录！";
					
					}else message="用户不存在，请重新输入！";
					
				} else {
					message = "用户未登陆，请重新登陆！";

				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据保存有误，请重新录入！";
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_belong_reback_list.jsp");
				dispatcher.forward(request, response);
		}

		}
		
		public void exportExcel_referee_reback(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员服务关系列表(反向)"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
					 String userId = StringUtil.notNull(request.getParameter("userId"));
					   request.setAttribute("userId", userId);
					 
						UserDao userDao = new UserDao();
						User user1 = userDao.findByUserId(userId);
						if(user1!=null){
						 String[] str = StringUtil.notNull(user1.getRefereeNode()).split(",");
						 String sql="";
						 for(int i=str.length;i>0;i--){
							 if(sql.equals("")) sql = "("+str[i-1];
							 else sql+=","+str[i-1];
						 }
						 if(!sql.equals("")) {
							 sql=" id in "+sql+") order by id desc";
					
								 userDao = new UserDao();
								result = userDao.findUserBySql(sql);
						 }
						}
					
					
						String[][] content = new String[result.size()+1][7];
						content[0][0]="位置";
						content[0][1]="会员编号";
						content[0][2]="会员名称";
						content[0][3]="会员等级";
						content[0][4]="管理级别";
						content[0][5]="注册时间";
						content[0][6]="当前状态";
						
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
							String rankstr="";
							if(result.get(i).getRankJoin()==0) rankstr = "-";
							else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
							else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
							else if(result.get(i).getRankJoin()==3) rankstr = "白金卡会员";
							else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
							else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
							content[i+1][3]  = StringUtil.notNull(rankstr);
							String managestr="";
							if(result.get(i).getRankJoin()==0) managestr = "-";
							else if(result.get(i).getRankManage()==1) managestr = "一星经理";
							else if(result.get(i).getRankManage()==2) managestr = "二星经理";
							else if(result.get(i).getRankManage()==3) managestr = "三星经理";
							else if(result.get(i).getRankManage()==4) managestr = "四星经理";
							else if(result.get(i).getRankManage()==5) managestr = "五星经理";
							else if(result.get(i).getRankManage()==6) managestr = "一星钻石";
							else if(result.get(i).getRankManage()==7) managestr = "二星钻石";
							else if(result.get(i).getRankManage()==8) managestr = "三星钻石";
							else if(result.get(i).getRankManage()==9) managestr = "四星钻石";
							else if(result.get(i).getRankManage()==10) managestr = "五星钻石";
							else if(result.get(i).getRankManage()==11) managestr = "董事";
							content[i+1][4]  = StringUtil.notNull(managestr);
							
							content[i+1][5] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
							
							String stateStr ="";
							if(result.get(i).getState()==0) stateStr = "下线";
							else if(result.get(i).getState()==1) stateStr = "在线";
							content[i+1][6] = stateStr;
							
						}
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("会员服务关系反向列表");  
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
		
		public void exportExcel_belong_reback(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员销售关系列表(反向)"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
					 String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					
						UserDao userDao = new UserDao();
						User user1 = new User();
						user1.setUserId("");;
						Integer max = userDao.maxId(user1);
						   int uid= 0;
						if(max!=null){
							UserDao userDao1 = new UserDao();
							List<User> ulist = userDao1.findUserByList(user1);
							User[] user =new User[max+1];
							for(int i=0;i<ulist.size();i++){
								int id = ulist.get(i).getId();
								if(user[id]==null) user[id] = new User();
								user[id].setId(ulist.get(i).getId());
					        	user[id].setUserId(ulist.get(i).getUserId());
					        	if(user[id].getUserId().equals(userId)) uid = id;
					        	user[id].setUserName(ulist.get(i).getUserName());
					        	user[id].setRankJoin(ulist.get(i).getRankJoin());
					        	user[id].setRankManage(ulist.get(i).getRankManage());
					        	user[id].setNode(ulist.get(i).getNode());
					        	user[id].setNodeABC(ulist.get(i).getNodeABC());
					        	user[id].setRefereeNode(ulist.get(i).getRefereeNode());
					        	user[id].setState(ulist.get(i).getState());
					        	user[id].setEntryTime(ulist.get(i).getEntryTime());
					        	user[id].setArea("");
					        	user[id].setTag(0);
							}
					if(uid>0){
						        result.add(user[uid]);
						        String[] str = StringUtil.notNull(user[uid].getNode()).split(",");
						        String[] str1 = StringUtil.notNull(user[uid].getNodeABC()).split(",");
						        int t=1;
						        for(int i=str.length-1;i>=0;i--){
						        	if(!str[i].equals("")){
						        		int sid = Integer.valueOf(str[i]);
						        		if(user[sid]!=null){
						        			user[sid].setTag(t);
						        			user[sid].setArea(str1[i]);
						        			result.add(user[sid]);
						        			t++;
						        		}
						        	}
						        }
					}
					}
					String[][] content = new String[result.size()+1][7];
					content[0][0]="位置";
					content[0][1]="会员编号";
					content[0][2]="会员名称";
					content[0][3]="会员等级";
					content[0][4]="管理级别";
					content[0][5]="注册时间";
					content[0][6]="当前状态";
					
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = result.get(i).getArea()+String.valueOf(result.get(i).getTag());
						content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
						String rankstr="";
						if(result.get(i).getRankJoin()==0) rankstr = "-";
						else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
						else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
						else if(result.get(i).getRankJoin()==3) rankstr = "白金卡会员";
						else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
						else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
						content[i+1][3]  = StringUtil.notNull(rankstr);
						String managestr="";
						if(result.get(i).getRankJoin()==0) managestr = "-";
						else if(result.get(i).getRankManage()==1) managestr = "一星经理";
						else if(result.get(i).getRankManage()==2) managestr = "二星经理";
						else if(result.get(i).getRankManage()==3) managestr = "三星经理";
						else if(result.get(i).getRankManage()==4) managestr = "四星经理";
						else if(result.get(i).getRankManage()==5) managestr = "五星经理";
						else if(result.get(i).getRankManage()==6) managestr = "一星钻石";
						else if(result.get(i).getRankManage()==7) managestr = "二星钻石";
						else if(result.get(i).getRankManage()==8) managestr = "三星钻石";
						else if(result.get(i).getRankManage()==9) managestr = "四星钻石";
						else if(result.get(i).getRankManage()==10) managestr = "五星钻石";
						else if(result.get(i).getRankManage()==11) managestr = "董事";
						content[i+1][4]  = StringUtil.notNull(managestr);
						
						content[i+1][5] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
						
						String stateStr ="";
						if(result.get(i).getState()==0) stateStr = "下线";
						else if(result.get(i).getState()==2) stateStr = "在线";
						content[i+1][6] = stateStr;
						
					}
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("会员服务关系反向列表");  
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
		
		
		public void admin_user_lock(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if (admin != null) {
						String[] ids = request.getParameterValues("ids");
						if (ids.length > 0) {
							UserDao userDao = new UserDao();
							Timestamp date = new Timestamp(
									new Date().getTime());
						message=userDao.updateLock(ids, "0");
						if(!message.equals("")){
								cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
						} else {
							message= "用户冻结失败。";
						}

					} else {
						message="未获得会员相应的ID信息，请重试！";
					}
				} else {
					message="管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
						
				e.printStackTrace();
				message="数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		

		public void admin_user_unlock(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if (admin != null) {
						String[] ids = request.getParameterValues("ids");
						if (ids.length > 0) {
							UserDao userDao = new UserDao();
							Timestamp date = new Timestamp(
									new Date().getTime());
							message =userDao.updateLock(ids, "1");
							if(!message.equals("")){
								cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_4, date);
							
						} else {
							message= "用户解锁失败。";
						}

					} else {
						message="未获得会员相应的ID信息，请重试！";
					}
				} else {
					message="管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
						
				e.printStackTrace();
				message="数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		public void init(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if (admin != null) {
							UserDao userDao = new UserDao();
							if(userDao.findByUserId("AA000000")==null){
							userDao = new UserDao();
							userDao.initUser();
								message="会员数据初始化成功！";
							}else{
								message="会员已初始化，请查看会员列表！";
							}
					
				} else {
					message="管理员用户未登陆，请重新登陆！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message="数据保存有误，请重新录入！";
			} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("user_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void exportExcel_user_money_list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员账户余额"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
				User user1 = new User();
				user1.setUserId(userId);
				user1.setIsHide(0);
					UserDao userDao = new UserDao();
					result = userDao.findUserByList(user1);
					double[] sum = {0,0,0,0,0};

					String[][] content = new String[result.size()+2][10];
					content[0][0]="序号";
					content[0][1]="会员编号";
					content[0][2]="会员名称";
					content[0][3]="报单券余额";
					content[0][4]="购物券余额";
					content[0][5]="复消券余额";
					content[0][6]="奖金券余额";
					content[0][7]="电子券余额";
					content[0][8]="电子券有效期";
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][3]  = StringUtil.decimalFormat(result.get(i).getEmoney());
						content[i+1][4]  = StringUtil.decimalFormat(0);
						content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getDmoney());
						content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getRmoney());
						content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getPmoney());
						content[i+1][8] = StringUtil.parseToString(result.get(i).getValidtyTime(), DateUtil.yyyyMMddHHmmss);
						sum[0] = ArithUtil.add(sum[0], result.get(i).getEmoney());
						sum[2] = ArithUtil.add(sum[2], result.get(i).getDmoney());
						sum[3] = ArithUtil.add(sum[3], result.get(i).getRmoney());
						sum[4] = ArithUtil.add(sum[4], result.get(i).getPmoney());
						
					}
					content[result.size()+1][0]  ="-";
					content[result.size()+1][1]  = "合计";
					content[result.size()+1][2]  = "-";
					content[result.size()+1][3]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+1][4]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+1][5]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+1][6]  = StringUtil.decimalFormat(sum[3]);
					content[result.size()+1][7]  = StringUtil.decimalFormat(sum[4]);
					content[result.size()+1][8]  = "-";
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("会员账户余额列表");  
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
		
		public void exportExcel_user_money_hide_list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "公司会员账户余额"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
				User user1 = new User();
				user1.setUserId(userId);
				user1.setIsHide(1);
					UserDao userDao = new UserDao();
					result = userDao.findUserByList(user1);
					double[] sum = {0,0,0,0,0};
					String[][] content = new String[result.size()+2][10];
					content[0][0]="序号";
					content[0][1]="会员编号";
					content[0][2]="会员名称";
					content[0][3]="报单券余额";
					content[0][4]="购物券余额";
					content[0][5]="复消券余额";
					content[0][6]="奖金券余额";
					content[0][7]="电子券余额";
					content[0][8]="电子券有效期";
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][3]  = StringUtil.decimalFormat(result.get(i).getEmoney());
						content[i+1][4]  = StringUtil.decimalFormat(0);
						content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getDmoney());
						content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getRmoney());
						content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getPmoney());
						content[i+1][8] = StringUtil.parseToString(result.get(i).getValidtyTime(), DateUtil.yyyyMMddHHmmss);
						sum[0] = ArithUtil.add(sum[0], result.get(i).getEmoney());
						sum[2] = ArithUtil.add(sum[2], result.get(i).getDmoney());
						sum[3] = ArithUtil.add(sum[3], result.get(i).getRmoney());
						sum[4] = ArithUtil.add(sum[4], result.get(i).getPmoney());
						
					}
					content[result.size()+1][0]  ="-";
					content[result.size()+1][1]  = "合计";
					content[result.size()+1][2]  = "-";
					content[result.size()+1][3]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+1][4]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+1][5]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+1][6]  = StringUtil.decimalFormat(sum[3]);
					content[result.size()+1][7]  = StringUtil.decimalFormat(sum[4]);
					content[result.size()+1][8]  = "-";
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("公司会员账户余额列表");  
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
		
			
}
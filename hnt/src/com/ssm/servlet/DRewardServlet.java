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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.ssm.pojo.User;
import com.ssm.dao.DRewardDao;
import com.ssm.dao.SettleDayDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.ParamDao;
import com.ssm.dao.SettleDao;
import com.ssm.dao.WRewardDao;
import com.ssm.dao.WRewardExtremeDao;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.DReward;
import com.ssm.pojo.DSettle;
import com.ssm.pojo.SettleDay;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.Settle;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class DRewardServlet extends HttpServlet {

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

		} else if (method.equals("initSettle")) {
			initSettle(request, response);

		}else if (method.equals("admin_reward_day")) {
			admin_reward_day(request, response);

		}else if (method.equals("admin_reward_day_1")) {
			admin_reward_day_1(request, response);

		}else if (method.equals("admin_reward_day_0")) {
			admin_reward_day_0(request, response);
		}else if (method.equals("admin_reward_day_confirm")) {
			admin_reward_day_confirm(request, response);

		}else if (method.equals("admin_reward_day_reset")) {
			admin_reward_day_reset(request, response);

		}else if(method.equals("admin_reward_day_list")){
			admin_reward_day_list(request,response);
			
		}else if(method.equals("admin_reward_day_paytag_list")){
			admin_reward_day_paytag_list(request,response);
			
		}else if(method.equals("admin_reward_day_hide_list")){
			admin_reward_day_hide_list(request,response);
			
		}else if(method.equals("admin_reward_extreme_week_list")){
			admin_reward_extreme_week_list(request,response);
			
		}else if(method.equals("admin_reward_extreme_import_add")){
			admin_reward_extreme_import_add(request,response);
			
		}else if(method.equals("admin_reward_extreme_import")){
			admin_reward_extreme_import(request,response);
			
		}else if(method.equals("reward_day_list")){
			reward_day_list(request,response);
			
		}else if(method.equals("admin_reward_day_summary")){
			admin_reward_day_summary(request,response);
			
		}else if(method.equals("admin_reward_day_hide_summary")){
			admin_reward_day_hide_summary(request,response);
			
		}else if(method.equals("admin_reward_day_empty_summary")){
			admin_reward_day_empty_summary(request,response);
			
		}else if(method.equals("admin_reward_day_payoff")){
			admin_reward_day_payoff(request,response);
			
		}else if(method.equals("admin_reward_extreme_week_payoff")){
			admin_reward_extreme_week_payoff(request,response);
			
		}else if(method.equals("exportExcel")){
			exportExcel(request, response);
		}else if(method.equals("exportExcel_hide")){
			exportExcel_hide(request, response);
		}else if(method.equals("exportExcel_payTag")){
			exportExcel_payTag(request, response);
		}else if(method.equals("admin_reward_day_summary_all")){
			admin_reward_day_summary_all(request,response);
			
		}else if(method.equals("admin_reward_extra_import_add")){
			admin_reward_extra_import_add(request,response);
			
		}else if(method.equals("admin_reward_extra_import")){
			admin_reward_extra_import(request,response);
		}
	}
	
	public void admin_reward_day(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message1= "";
		try {
			if(admin!=null){
				SettleDayDao stDao = new SettleDayDao();
				SettleDay st = stDao.getEndSettle();
				if(st!=null){
					if(st.getState()>=3){
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(st.getDayTime());
						calendar.add(Calendar.DAY_OF_MONTH, 1); 
						Timestamp startTime =new Timestamp(calendar.getTime().getTime());
						request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMdd));
						request.setAttribute("state", "0");
						request.setAttribute("tag", 0);

					}else{
						Timestamp startTime = st.getDayTime();
						request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMdd));
						request.setAttribute("state",String.valueOf(st.getState()));
						request.setAttribute("tag", 1);
					}
				}else{
					Date date = new Date();
					String ymd = StringUtil.parseToString(new Timestamp(date.getTime()), DateUtil.yyyyMMdd);
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(ymd+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMdd));
					request.setAttribute("state", "0");
					request.setAttribute("tag", 0);
				}
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			}else{
				message1 = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message1", message1);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("reward_day.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_day_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				if(startTimeStr.equals("")){
					SettleDayDao stDao = new SettleDayDao();
					SettleDay st = stDao.getEndSettle();
					if(st!=null){
						if(st.getWeekTag()>0){
							Timestamp startTime = st.getDayTime();
							DRewardDao wrdDao = new DRewardDao();
							DReward wrd = new DReward();
							wrd.setUserId(userId);
							wrd.setDayTime(startTime);
							Pager pager= new Pager();
							pager.setPageNo(pageNo);
							pager.setPageSize(pageSize);
							pager = wrdDao.findByPage(wrd,"0", pager);
							request.setAttribute("pager", pager);
							startTimeStr = StringUtil.parseToString(startTime, DateUtil.yyyyMMdd);
						}
					}
				}else{
					Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					DRewardDao wrdDao = new DRewardDao();
					DReward wrd = new DReward();
					wrd.setUserId(userId);
					wrd.setDayTime(startTime);
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = wrdDao.findByPage(wrd,"0", pager);
					request.setAttribute("pager", pager);
				}
				request.setAttribute("userId", userId);
				request.setAttribute("startTime", startTimeStr);
			}else{
				message ="用户未登录，请重新登陆";
			}
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_day_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_reward_day_hide_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				if(startTimeStr.equals("")){
					SettleDayDao stDao = new SettleDayDao();
					SettleDay st = stDao.getEndSettle();
					if(st!=null){
						if(st.getWeekTag()>0){
							Timestamp startTime = st.getDayTime();
							DRewardDao wrdDao = new DRewardDao();
							DReward wrd = new DReward();
							wrd.setUserId(userId);
							wrd.setDayTime(startTime);
							Pager pager= new Pager();
							pager.setPageNo(pageNo);
							pager.setPageSize(pageSize);
							pager = wrdDao.findByPage(wrd,"1", pager);
							request.setAttribute("pager", pager);
							startTimeStr = StringUtil.parseToString(startTime, DateUtil.yyyyMMdd);
						}
					}
				}else{
					Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					DRewardDao wrdDao = new DRewardDao();
					DReward wrd = new DReward();
					wrd.setUserId(userId);
					wrd.setDayTime(startTime);
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = wrdDao.findByPage(wrd,"1", pager);
					request.setAttribute("pager", pager);
				}
				request.setAttribute("userId", userId);
				request.setAttribute("startTime", startTimeStr);
			
			}else{
				message ="用户未登录，请重新登陆";
			}
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_day_hide_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_reward_day_paytag_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				if(startTimeStr.equals("")){
					SettleDayDao stDao = new SettleDayDao();
					SettleDay st = stDao.getEndSettle();
					if(st!=null){
						if(st.getWeekTag()>0){
							Timestamp startTime = st.getDayTime();
							DRewardDao wrdDao = new DRewardDao();
							DReward wrd = new DReward();
							wrd.setUserId(userId);
							wrd.setPayTag(0);
							wrd.setDayTime(startTime);
							Pager pager= new Pager();
							pager.setPageNo(pageNo);
							pager.setPageSize(pageSize);
							pager = wrdDao.findByPage(wrd,"0", pager);
							request.setAttribute("pager", pager);
							startTimeStr = StringUtil.parseToString(startTime, DateUtil.yyyyMMdd);
						}
					}
				}else{
					Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					DRewardDao wrdDao = new DRewardDao();
					DReward wrd = new DReward();
					wrd.setUserId(userId);
					wrd.setDayTime(startTime);
					wrd.setPayTag(0);
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = wrdDao.findByPage(wrd,"0", pager);
					request.setAttribute("pager", pager);
				}
				request.setAttribute("userId", userId);
				request.setAttribute("startTime", startTimeStr);
			}else{
				message ="用户未登录，请重新登陆";
			}
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_day_paytag_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_reward_day_payoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				if(!startTimeStr.equals("")){
					Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
							DRewardDao wrdDao = new DRewardDao();
							message =wrdDao.payoff(startTime, admin.getAdminName());
					
 
				}else{
					message="需要发放奖金的周期有误。";
				}

				
			}else{
				message ="用户未登录，请重新登陆";
			}
		}catch(Exception e){
			e.printStackTrace();
			message= "数据有误，奖金发放失败。";
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_day_payoff_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void reward_day_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if(user!=null){
				DRewardDao wrdDao = new DRewardDao();
				List<DReward> wlist = wrdDao.findByList(user.getUserId());
				if(wlist!=null&&wlist.size()>0){
					request.setAttribute("wlist", wlist);
				}else message="暂无奖金记录。";
			}else message="会员未登录。";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据获取失败。";
			e.printStackTrace();
		} finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_day_list.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void initSettle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				Settle st = new Settle();
				Timestamp date=new Timestamp(new Date().getTime());
				Timestamp startTime=new Timestamp(StringUtil.parseToDate("2017-10-01 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				st.setWeekTag(0);
				st.setState(3);
				st.setTag(0);
				st.setStartTime(startTime);
				st.setEndTime(startTime);
				st.setEntryTime(date);
				SettleDao stDao = new SettleDao();
				if(stDao.init_settle(st)>0)
					message = "第"+st.getWeekTag()+"期结算表信息初始化成功。";
				else message = "第"+st.getWeekTag()+"期结算记录已存在或者数据保存有误。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据初始化有误。";
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("init_settle_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	public void admin_reward_day_0(HttpServletRequest request, HttpServletResponse response)
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
					 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				Timestamp date = new Timestamp(new Date().getTime());
				Timestamp startTime = null;
				if(!startTimeStr.equals("")) startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				if(startTime!=null){
					SettleDayDao stDao = new SettleDayDao();
					SettleDay st = new SettleDay();
					st.setWeekTag(cs.getWeekTag(startTime));
					st.setState(1);
					st.setTag(0);
					st.setEntryTime(date);
					st.setDayTime(startTime);
					if(stDao.save(st)>0){
						message = startTimeStr+"结算开始，请继续点击奖金结算进行操作。";
					}else{
						message = "结算周期已存在或者录入数据有误，请核对。";
					}
				}else{
					message = "时间段信息获取失败。";
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
					.getRequestDispatcher("reward_day_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_reset(HttpServletRequest request, HttpServletResponse response)
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
					 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
						
				if(!startTimeStr.equals("")){
					Timestamp startTime = null;
					if(!startTimeStr.equals("")) startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					SettleDayDao stDao = new SettleDayDao();
					int weekTag = cs.getWeekTag(startTime);
					if(stDao.del(startTime,weekTag)>0){
						message = startTimeStr+"结算重置成功，请继续点击奖金结算进行操作。";
					}else{
						message = "结算周期撤回失败，请核对。";
					}
						
				}else{
					message = "结算周期信息获取失败。";
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
					.getRequestDispatcher("reward_day_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_confirm(HttpServletRequest request, HttpServletResponse response)
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
					 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					if(!startTimeStr.equals("")){
						Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						DRewardDao wrdDao = new DRewardDao();
						message = wrdDao.confirmReward(startTime);
				}else{
					message = "结算周期信息获取失败。";
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
					.getRequestDispatcher("reward_day_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
					String payTagStr = StringUtil.notNull(request.getParameter("payTag"));
					 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
						
					DRewardDao wrdDao  = new DRewardDao();
					User user_sel = new User();
					if(!payTagStr.equals("")){
						user_sel.setPayTag(Integer.valueOf(payTagStr));
					}
					DReward wrd = wrdDao.awardSummary(startTimeStr,user_sel);
							if(wrd!=null){
								request.setAttribute("startTime", StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMdd));
								request.setAttribute("sys_wrd", wrd);
							}else{
								message="数据获取失败。";
							}
						
					request.setAttribute("payTag", payTagStr);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("reward_day_summary.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_payTag_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
					String weekTagStr = request.getParameter("weekTag");
				
					SettleDao stDao = new SettleDao();
					int weekTag1 = stDao.maxWeekTag();
					List<Integer> slist = new ArrayList<Integer>();
					for(int i=weekTag1;i>0;i--){
						slist.add(i);
					}
					request.setAttribute("slist", slist);
					int weekTag=0;
					if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
					else weekTag = Integer.valueOf(weekTagStr);
					if(weekTag==0){
						weekTag=weekTag1;
					}
					request.setAttribute("weekTag", weekTag);

					stDao = new SettleDao();
					Settle st = stDao.findByWeekTag(weekTag);
					WRewardDao wrdDao = new WRewardDao();
					WReward wrd = new WReward();
					String tableName = "wreward_"+weekTag;
					User user_sel =null;
					wrd = wrdDao.awardSummary(tableName,user_sel);
					if(wrd!=null){
						wrd.setStartTime(st.getStartTime());
						wrd.setEndTime(st.getEndTime());
						wrd.setAmount(ArithUtil.add(st.getNewPrice(),st.getNewPrice_1()));
						wrd.setNewPerformance(ArithUtil.add(st.getNewPerformance(),st.getNewPerformance_1()));
						request.setAttribute("sys_wrd", wrd);
					}else{
						message="数据获取失败。";
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
					.getRequestDispatcher("reward_day_summary.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_summary_all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
					String weekTagStr1 = request.getParameter("weekTag1");
					String weekTagStr2 = request.getParameter("weekTag2");
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 20;
					SettleDao stDao = new SettleDao();
					int weekTag = stDao.maxWeekTag();
					List<Integer> slist = new ArrayList<Integer>();
					for(int i=weekTag;i>0;i--){
						slist.add(i);
					}
					request.setAttribute("slist", slist);
					int weekTag1=0;
					int weekTag2=0;
					if(StringUtil.notNull(weekTagStr1).equals(""))weekTag1 = 1;
					else weekTag1 = Integer.valueOf(weekTagStr1);
					
					if(StringUtil.notNull(weekTagStr2).equals(""))weekTag2 = weekTag;
					else weekTag2 = Integer.valueOf(weekTagStr2);
					
					if(weekTag==0){
						weekTag1=weekTag;
						weekTag2=weekTag;
					}
					request.setAttribute("weekTag1", weekTag1);
					request.setAttribute("weekTag2", weekTag2);
					if(weekTag2-weekTag1>=0){
					stDao = new SettleDao();
					WRewardDao wrdDao = new WRewardDao();
					List<WReward> result = wrdDao.awardSummaryAll(weekTag1,weekTag2);
					if(result!=null){
						 if (!StringUtil.notNull(pageNoStr).equals(""))
								pageNo = Integer.valueOf(pageNoStr);
							if (!StringUtil.notNull(pageSizeStr).equals(""))
								pageSize = Integer.valueOf(pageSizeStr);
							int startIndex = pageSize * (pageNo - 1);
							int endIndex = pageSize * pageNo;
							   if (result.size() > 0) {
									
									if (result.size() < endIndex)
										endIndex = result.size();
							 List<WReward> coll = result.subList(startIndex, endIndex);
							 Pager pager = new Pager();
							 pager.setStartIndex(startIndex);
							 pager.setEndIndex(endIndex);
							 pager.setPageNo(pageNo);
							 pager.setPageSize(pageSize);
							 pager.setRowCount(result.size());
							 pager.setResultList(coll);
							 request.setAttribute("pager", pager);
						}
					}else{
						message="数据获取失败。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("reward_day_summary_all.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_hide_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
				 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					
				DRewardDao wrdDao  = new DRewardDao();
				User user_sel = new User();
				user_sel.setIsHide(1);
    			
				DReward wrd = wrdDao.awardSummary(startTimeStr,user_sel);
						if(wrd!=null){
							request.setAttribute("startTime", StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMdd));
							request.setAttribute("sys_wrd", wrd);
					}else{
						message="数据获取失败。";
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
					.getRequestDispatcher("reward_day_hide_summary.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_empty_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
			
				 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					
					DRewardDao wrdDao  = new DRewardDao();
					User user_sel = new User();
					user_sel.setIsHide(0);
					user_sel.setIsEmpty(1);
					DReward wrd = wrdDao.awardSummary(startTimeStr,user_sel);
							if(wrd!=null){
								request.setAttribute("startTime", StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMdd));
								request.setAttribute("sys_wrd", wrd);
				
					}else{
						message="数据获取失败。";
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
					.getRequestDispatcher("reward_day_empty_summary.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_day_1(HttpServletRequest request, HttpServletResponse response)
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
					 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				
				if(!startTimeStr.equals("")){
					Timestamp dayTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					DRewardDao  wrdDao = new DRewardDao();
					message = wrdDao.countReward(dayTime);
					 if(message.equals("yes")||message.equals("yes1"))
						message = startTimeStr+"期奖金汇总成功，请继续点击奖金结算进行操作。";
					
						}else{
							message = "结算周期时间获取失败。";
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
					.getRequestDispatcher("reward_day_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_reward_check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
				
					DRewardDao  wrdDao = new DRewardDao();
					int count = wrdDao.checkReward();
					request.setAttribute("count", count);
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("reward_check.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	
	public void admin_reward_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String message = "";
		try {
			if(admin!=null){
				
					DRewardDao  wrdDao = new DRewardDao();
					String msg = wrdDao.addReward();
					message= msg;
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("reward_add.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void addParam(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("param_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("param_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void saveParam(HttpServletRequest request, HttpServletResponse response)
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
				param.setEntryTime(date);
				message = ParamDao.saveParam(param);
				param=null;
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
					.getRequestDispatcher("param_add_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void exportExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = startTimeStr+"奖金明细.xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<DReward> result = new ArrayList<DReward>();
		String message = "";
			try {
				if(admin!=null){
					if(!startTimeStr.equals("")){
						Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						
					DRewardDao wrdDao = new DRewardDao();
					DReward wrd = new DReward();
					wrd.setUserId(userId);
					wrd.setDayTime(startTime);
					result = wrdDao.findByList(wrd,"0");
					String[][] content = new String[result.size()+2][21];
					content[0][0]="序号";
					content[0][1]="开始时间";
					content[0][2]="结束时间";
					content[0][3]="会员编号";
					content[0][4]="会员姓名";
					content[0][5]="加盟级别";
					content[0][6]="会员奖衔";
					content[0][7]="推荐奖";
					content[0][8]="见点奖";
					content[0][9]="层碰奖";
					content[0][10]="拓展奖";
					content[0][11]="培育奖";
					content[0][12]="领导奖";
					content[0][13]="物流补助";
					content[0][14]="消费奖";
					content[0][15]="奖金合计";
					content[0][16]="复消";
					content[0][17]="扣税";
					content[0][18]="应发金额";
					content[0][19]="状态";
					double[] sum={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][2]  = StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][3]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][4]  = StringUtil.notNull(result.get(i).getUserName());
						String rankJoinstr = "";
						if(result.get(i).getRankJoin()==1)rankJoinstr="银卡会员";
						else if(result.get(i).getRankJoin()==2)rankJoinstr="金卡会员";
						else if(result.get(i).getRankJoin()==3)rankJoinstr="白金卡会员";
						else if(result.get(i).getRankJoin()==4)rankJoinstr="VIP会员";
						else if(result.get(i).getRankJoin()==5)rankJoinstr="至尊会员";
						content[i+1][5]  =rankJoinstr;
						String rank="";
						int rankManage = result.get(i).getRankManage();
						if (rankManage == 1)
							rank = rank + "一级经理";
						else if (rankManage == 2)
							rank = rank + "二级经理";
						else if (rankManage == 3)
							rank = rank + "三级经理";
						else if (rankManage== 4)
							rank = rank + "四级经理";
						else if (rankManage == 5)
							rank = rank + "五级经理";
						else
							rank = rank + "-";
						content[i+1][6]  = rank;
						content[i+1][7]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+1][8]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+1][9]  =  StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getAmount_4());
						content[i+1][11]  = StringUtil.decimalFormat(result.get(i).getAmount_5());
						content[i+1][12] = StringUtil.decimalFormat(result.get(i).getAmount_6());
						content[i+1][13] =StringUtil.decimalFormat(result.get(i).getAmount_7());
						content[i+1][14] = StringUtil.decimalFormat(result.get(i).getAmount_8());
						content[i+1][15] = StringUtil.decimalFormat(result.get(i).getAmount_9());
						content[i+1][16] = StringUtil.decimalFormat(result.get(i).getAmount_10());
						content[i+1][17] = StringUtil.decimalFormat(result.get(i).getAmount_11());
						content[i+1][18] = StringUtil.decimalFormat(result.get(i).getAmount_12());
						String stateStr ="";
						if(result.get(i).getState()==0) stateStr = "结算中";
						else if(result.get(i).getState()==1) stateStr = "待发";
						else if(result.get(i).getState()==2) stateStr = "已发";
						content[i+1][19] = stateStr;
						
						sum[0] = ArithUtil.add(sum[0],result.get(i).getAmount_1());
						sum[1] = ArithUtil.add(sum[1],result.get(i).getAmount_2());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getAmount_3());
						sum[3] = ArithUtil.add(sum[3],result.get(i).getAmount_4());
						sum[4] = ArithUtil.add(sum[4],result.get(i).getAmount_5());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getAmount_6());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getAmount_7());
						sum[7] = ArithUtil.add(sum[7],result.get(i).getAmount_8());
						sum[8] = ArithUtil.add(sum[8],result.get(i).getAmount_9());
						sum[9] = ArithUtil.add(sum[9],result.get(i).getAmount_10());
						sum[10] = ArithUtil.add(sum[10],result.get(i).getAmount_11());
						sum[11] = ArithUtil.add(sum[11],result.get(i).getAmount_12());
					}
					content[result.size()+1][0]  = String.valueOf(result.size()+1);
					content[result.size()+1][1]  = "合计";
					content[result.size()+1][2]  = "-";
					content[result.size()+1][3]  = "-";
					content[result.size()+1][4]  = "-";
					content[result.size()+1][5]  = "-";
					content[result.size()+1][6]  = "-";
					content[result.size()+1][7]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+1][8]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+1][9]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+1][10] = StringUtil.decimalFormat(sum[3]);
					content[result.size()+1][11] = StringUtil.decimalFormat(sum[4]);
					content[result.size()+1][12] = StringUtil.decimalFormat(sum[5]);
					content[result.size()+1][13] = StringUtil.decimalFormat(sum[6]);
					content[result.size()+1][14] = StringUtil.decimalFormat(sum[7]);
					content[result.size()+1][15] = StringUtil.decimalFormat(sum[8]);
					content[result.size()+1][16] = StringUtil.decimalFormat(sum[9]);
					content[result.size()+1][17] = StringUtil.decimalFormat(sum[10]);
					content[result.size()+1][18] = StringUtil.decimalFormat(sum[11]);
					content[result.size()+1][19] = "-";
				}
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet(startTimeStr+"奖金明细");  
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
						message = "结算周期时间获取失败。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} 
	}
	
	public void exportExcel_hide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = startTimeStr+"公司点位奖金明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<DReward> result = new ArrayList<DReward>();
		String message = "";
			try {
				if(admin!=null){
					if(!startTimeStr.equals("")){
						Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						
					DRewardDao wrdDao = new DRewardDao();
					DReward wrd = new DReward();
					wrd.setUserId(userId);
					wrd.setDayTime(startTime);
					result = wrdDao.findByList(wrd,"1");
					String[][] content = new String[result.size()+2][21];
					content[0][0]="序号";
					content[0][1]="开始时间";
					content[0][2]="结束时间";
					content[0][3]="会员编号";
					content[0][4]="会员姓名";
					content[0][5]="加盟级别";
					content[0][6]="会员奖衔";
					content[0][7]="推荐奖";
					content[0][8]="见点奖";
					content[0][9]="层碰奖";
					content[0][10]="拓展奖";
					content[0][11]="培育奖";
					content[0][12]="领导奖";
					content[0][13]="物流补助";
					content[0][14]="消费奖";
					content[0][15]="奖金合计";
					content[0][16]="复消";
					content[0][17]="扣税";
					content[0][18]="应发金额";
					content[0][19]="状态";
					double[] sum={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][2]  = StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][3]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][4]  = StringUtil.notNull(result.get(i).getUserName());
						String rankJoinstr = "";
						if(result.get(i).getRankJoin()==1)rankJoinstr="银卡会员";
						else if(result.get(i).getRankJoin()==2)rankJoinstr="金卡会员";
						else if(result.get(i).getRankJoin()==3)rankJoinstr="白金卡会员";
						else if(result.get(i).getRankJoin()==4)rankJoinstr="VIP会员";
						else if(result.get(i).getRankJoin()==5)rankJoinstr="至尊会员";
						content[i+1][5]  =rankJoinstr;
						String rank="";
						int rankManage = result.get(i).getRankManage();
						if (rankManage == 1)
							rank = rank + "一级经理";
						else if (rankManage == 2)
							rank = rank + "二级经理";
						else if (rankManage == 3)
							rank = rank + "三级经理";
						else if (rankManage== 4)
							rank = rank + "四级经理";
						else if (rankManage == 5)
							rank = rank + "五级经理";
						else
							rank = rank + "-";
						content[i+1][6]  = rank;
						content[i+1][7]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+1][8]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+1][9]  =  StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getAmount_4());
						content[i+1][11]  = StringUtil.decimalFormat(result.get(i).getAmount_5());
						content[i+1][12] = StringUtil.decimalFormat(result.get(i).getAmount_6());
						content[i+1][13] =StringUtil.decimalFormat(result.get(i).getAmount_7());
						content[i+1][14] = StringUtil.decimalFormat(result.get(i).getAmount_8());
						content[i+1][15] = StringUtil.decimalFormat(result.get(i).getAmount_9());
						content[i+1][16] = StringUtil.decimalFormat(result.get(i).getAmount_10());
						content[i+1][17] = StringUtil.decimalFormat(result.get(i).getAmount_11());
						content[i+1][18] = StringUtil.decimalFormat(result.get(i).getAmount_12());
						String stateStr ="";
						if(result.get(i).getState()==0) stateStr = "结算中";
						else if(result.get(i).getState()==1) stateStr = "待发";
						else if(result.get(i).getState()==2) stateStr = "已发";
						content[i+1][19] = stateStr;
						sum[0] = ArithUtil.add(sum[0],result.get(i).getAmount_1());
						sum[1] = ArithUtil.add(sum[1],result.get(i).getAmount_2());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getAmount_3());
						sum[3] = ArithUtil.add(sum[3],result.get(i).getAmount_4());
						sum[4] = ArithUtil.add(sum[4],result.get(i).getAmount_5());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getAmount_6());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getAmount_7());
						sum[7] = ArithUtil.add(sum[7],result.get(i).getAmount_8());
						sum[8] = ArithUtil.add(sum[8],result.get(i).getAmount_9());
						sum[9] = ArithUtil.add(sum[9],result.get(i).getAmount_10());
						sum[10] = ArithUtil.add(sum[10],result.get(i).getAmount_11());
						sum[11] = ArithUtil.add(sum[11],result.get(i).getAmount_12());
					}
					content[result.size()+1][0]  = String.valueOf(result.size()+1);
					content[result.size()+1][1]  = "合计";
					content[result.size()+1][2]  = "-";
					content[result.size()+1][3]  = "-";
					content[result.size()+1][4]  = "-";
					content[result.size()+1][5]  = "-";
					content[result.size()+1][6]  = "-";
					content[result.size()+1][7]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+1][8]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+1][9]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+1][10] = StringUtil.decimalFormat(sum[3]);
					content[result.size()+1][11] = StringUtil.decimalFormat(sum[4]);
					content[result.size()+1][12] = StringUtil.decimalFormat(sum[5]);
					content[result.size()+1][13] = StringUtil.decimalFormat(sum[6]);
					content[result.size()+1][14] = StringUtil.decimalFormat(sum[7]);
					content[result.size()+1][15] = StringUtil.decimalFormat(sum[8]);
					content[result.size()+1][16] = StringUtil.decimalFormat(sum[9]);
					content[result.size()+1][17] = StringUtil.decimalFormat(sum[10]);
					content[result.size()+1][18] = StringUtil.decimalFormat(sum[11]);
					content[result.size()+1][19] = "-";
				}
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet(startTimeStr+"公司点位奖金明细");  
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
						message = "奖金日期获取失败。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} 
	}
	
	public void exportExcel_payTag(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = startTimeStr+"冻点奖金明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<DReward> result = new ArrayList<DReward>();
		String message = "";
			try {
				
				if(admin!=null){
					if(!startTimeStr.equals("")){
						Timestamp startTime=new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						
					DRewardDao wrdDao = new DRewardDao();
					DReward wrd = new DReward();
					wrd.setUserId(userId);
					wrd.setDayTime(startTime);
					wrd.setPayTag(0);
					result = wrdDao.findByList(wrd,"0");
					String[][] content = new String[result.size()+2][21];
					content[0][0]="序号";
					content[0][1]="开始时间";
					content[0][2]="结束时间";
					content[0][3]="会员编号";
					content[0][4]="会员姓名";
					content[0][5]="加盟级别";
					content[0][6]="会员奖衔";
					content[0][7]="推荐奖";
					content[0][8]="见点奖";
					content[0][9]="层碰奖";
					content[0][10]="拓展奖";
					content[0][11]="培育奖";
					content[0][12]="领导奖";
					content[0][13]="物流补助";
					content[0][14]="消费奖";
					content[0][15]="奖金合计";
					content[0][16]="复消";
					content[0][17]="扣税";
					content[0][18]="应发金额";
					content[0][19]="状态";
					double[] sum={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][2]  = StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
						content[i+1][3]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][4]  = StringUtil.notNull(result.get(i).getUserName());
						String rankJoinstr = "";
						if(result.get(i).getRankJoin()==1)rankJoinstr="银卡会员";
						else if(result.get(i).getRankJoin()==2)rankJoinstr="金卡会员";
						else if(result.get(i).getRankJoin()==3)rankJoinstr="白金卡会员";
						else if(result.get(i).getRankJoin()==4)rankJoinstr="VIP会员";
						else if(result.get(i).getRankJoin()==5)rankJoinstr="至尊会员";
						content[i+1][5]  =rankJoinstr;
						String rank="";
						int rankManage = result.get(i).getRankManage();
						if (rankManage == 1)
							rank = rank + "一级经理";
						else if (rankManage == 2)
							rank = rank + "二级经理";
						else if (rankManage == 3)
							rank = rank + "三级经理";
						else if (rankManage== 4)
							rank = rank + "四级经理";
						else if (rankManage == 5)
							rank = rank + "五级经理";
						else
							rank = rank + "-";
						content[i+1][6]  = rank;
						content[i+1][7]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+1][8]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+1][9]  =  StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getAmount_4());
						content[i+1][11]  = StringUtil.decimalFormat(result.get(i).getAmount_5());
						content[i+1][12] = StringUtil.decimalFormat(result.get(i).getAmount_6());
						content[i+1][13] =StringUtil.decimalFormat(result.get(i).getAmount_7());
						content[i+1][14] = StringUtil.decimalFormat(result.get(i).getAmount_8());
						content[i+1][15] = StringUtil.decimalFormat(result.get(i).getAmount_9());
						content[i+1][16] = StringUtil.decimalFormat(result.get(i).getAmount_10());
						content[i+1][17] = StringUtil.decimalFormat(result.get(i).getAmount_11());
						content[i+1][18] = StringUtil.decimalFormat(result.get(i).getAmount_12());
						String stateStr ="";
						if(result.get(i).getState()==0) stateStr = "结算中";
						else if(result.get(i).getState()==1) stateStr = "待发";
						else if(result.get(i).getState()==2) stateStr = "已发";
						content[i+1][19] = stateStr;
						sum[0] = ArithUtil.add(sum[0],result.get(i).getAmount_1());
						sum[1] = ArithUtil.add(sum[1],result.get(i).getAmount_2());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getAmount_3());
						sum[3] = ArithUtil.add(sum[3],result.get(i).getAmount_4());
						sum[4] = ArithUtil.add(sum[4],result.get(i).getAmount_5());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getAmount_6());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getAmount_7());
						sum[7] = ArithUtil.add(sum[7],result.get(i).getAmount_8());
						sum[8] = ArithUtil.add(sum[8],result.get(i).getAmount_9());
						sum[9] = ArithUtil.add(sum[9],result.get(i).getAmount_10());
						sum[10] = ArithUtil.add(sum[10],result.get(i).getAmount_11());
						sum[11] = ArithUtil.add(sum[11],result.get(i).getAmount_12());
					}
					content[result.size()+1][0]  = String.valueOf(result.size()+1);
					content[result.size()+1][1]  = "合计";
					content[result.size()+1][2]  = "-";
					content[result.size()+1][3]  = "-";
					content[result.size()+1][4]  = "-";
					content[result.size()+1][5]  = "-";
					content[result.size()+1][6]  = "-";
					content[result.size()+1][7]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+1][8]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+1][9]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+1][10] = StringUtil.decimalFormat(sum[3]);
					content[result.size()+1][11] = StringUtil.decimalFormat(sum[4]);
					content[result.size()+1][12] = StringUtil.decimalFormat(sum[5]);
					content[result.size()+1][13] = StringUtil.decimalFormat(sum[6]);
					content[result.size()+1][14] = StringUtil.decimalFormat(sum[7]);
					content[result.size()+1][15] = StringUtil.decimalFormat(sum[8]);
					content[result.size()+1][16] = StringUtil.decimalFormat(sum[9]);
					content[result.size()+1][17] = StringUtil.decimalFormat(sum[10]);
					content[result.size()+1][18] = StringUtil.decimalFormat(sum[11]);
					content[result.size()+1][19] = "-";
				}
			
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet(startTimeStr+"冻点奖金明细");  
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
						message = "奖金日期获取失败。";
					} 
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		} 
	}
	
	public void admin_reward_extreme_week_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				String weekTagStr = StringUtil.notNull(request.getParameter("weekTag"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				request.setAttribute("weekTag", weekTagStr);
				request.setAttribute("weekTag", weekTagStr);
				WRewardExtremeDao wrdDao = new WRewardExtremeDao();
				WRewardExtreme wrd = new WRewardExtreme();
				wrd.setUserId(userId);
				if(!weekTagStr.equals("")){
					wrd.setWeekTag(Integer.valueOf(weekTagStr));
				}
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wrdDao.findByPage(wrd, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
			}else{
				message ="用户未登录，请重新登陆";
			}
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_extreme_week_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_reward_extreme_import_add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("reward_extreme_import.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_extreme_import(HttpServletRequest request, HttpServletResponse response)
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
					 List<WRewardExtreme> ulist = new ArrayList<WRewardExtreme>();
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
							WRewardExtreme wrd = new WRewardExtreme();
							
							for (int j = 0; j < rsColumns; j++)

							{
								Cell cell = readsheet.getCell(j, i);

								if (j == 1) {
									wrd.setUserId(cell.getContents());
								}else if (j == 2) {
									wrd.setUserName(cell.getContents());
								} else if (j == 3) {
									wrd.setAmount_1(Double.valueOf(cell.getContents()));
								}else if (j == 4) {
									wrd.setAmount_2(Double.valueOf(cell.getContents()));
								}else if (j == 5) {
									wrd.setAmount_3(Double.valueOf(cell.getContents()));
								}else if (j == 6) {
									wrd.setAmount_4(Double.valueOf(cell.getContents()));
								}else if (j == 8) {
									wrd.setWeekTag(Integer.valueOf(cell.getContents()));
								}else if (j == 9) {
									wrd.setStartTime(new Timestamp(StringUtil.parseToDate(cell.getContents(), DateUtil.yyyyMMddHHmmss).getTime()));
								}else if (j == 10) {
									wrd.setEndTime(new Timestamp(StringUtil.parseToDate(cell.getContents(), DateUtil.yyyyMMddHHmmss).getTime()));
								}
							}
							ulist.add(wrd);
				 		}
					WRewardExtremeDao wrdDao = new WRewardExtremeDao();
					message = wrdDao.importExcel(ulist, admin.getAdminName());
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
							.getRequestDispatcher("reward_extreme_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_extreme_week_payoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				String weekTagStr = request.getParameter("weekTag");
				if(!weekTagStr.equals("")){
				
				int weekTag= Integer.valueOf(weekTagStr);
				if(weekTag>0){
							WRewardExtremeDao wrdDao = new WRewardExtremeDao();
							message =wrdDao.payoff(weekTag, admin.getAdminName());
						
				
				}else{
					message="未查询到有相关需要周期。";
				}
				}else{
					message="需要发放奖金的周期有误。";
				}

				
			}else{
				message ="用户未登录，请重新登陆";
			}
		}catch(Exception e){
			e.printStackTrace();
			message= "数据有误，奖金发放失败。";
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_extreme_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_reward_extra_import_add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("reward_extra_import.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_extra_import(HttpServletRequest request, HttpServletResponse response)
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
					 List<WReward> ulist = new ArrayList<WReward>();
						readwb = Workbook.getWorkbook(instream);
						// Sheet的下标是从0开始
						// 获取第一张Sheet表
						Sheet readsheet = readwb.getSheet(0);
						// 获取Sheet表中所包含的总列数
						int rsColumns = readsheet.getColumns();
						// 获取Sheet表中所包含的总行数
						int rsRows = readsheet.getRows();
						// 获取指定单元格的对象引用
						for (int i = 1; i < rsRows; i++){
							WReward wrd = new WReward();
							for (int j = 0; j < rsColumns; j++)

							{
								Cell cell = readsheet.getCell(j, i);
								if (j == 2) {
									wrd.setUserId(cell.getContents());
								}else if (j == 6) {
									wrd.setAmount(Double.valueOf(cell.getContents()));
								}
							}
							ulist.add(wrd);
				 		}
					WRewardDao wrdDao = new WRewardDao();
					message = wrdDao.importExtraExcel(ulist, admin.getAdminName());
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
							.getRequestDispatcher("reward_extra_message.jsp");
					dispatcher.forward(request, response);
		}

	}
	
}
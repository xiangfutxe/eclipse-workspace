package com.ssm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.ssm.dao.ParamDao;
import com.ssm.dao.RewardDao;
import com.ssm.dao.RewardDetailDao;
import com.ssm.dao.SettleDao;
import com.ssm.dao.WRewardDao;
import com.ssm.dao.WRewardExtremeDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Param;
import com.ssm.pojo.Reward;
import com.ssm.pojo.RewardDetail;
import com.ssm.pojo.Settle;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class RewardServlet extends HttpServlet {

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
		} else if (method.equals("list")) {
			initSettle(request, response);
		}else if (method.equals("admin_reward_month")) {
			admin_reward_month(request, response);
		}else if (method.equals("admin_reward_month_0")) {
			admin_reward_month_0(request, response);
		}else if (method.equals("admin_reward_month_1")) {
			admin_reward_month_1(request, response);
		}else if(method.equals("admin_reward_month_list")){
			admin_reward_month_list(request,response);
			
		}else if(method.equals("admin_reward_month_reset")){
			admin_reward_month_reset(request,response);
			
		}else if(method.equals("reward_month_list")){
			reward_month_list(request,response);
			
		}else if(method.equals("exportExcel")){
			exportExcel(request, response);
		}else if(method.equals("initSettle")){
			initSettle(request, response);
		}
	}
	
	public void admin_reward_month_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message="";
		try {
			if(admin!=null){
				String monthTagStr = request.getParameter("monthTag");
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				SettleDao stDao = new SettleDao();
				int monthTag1 = stDao.maxWeekTag();
				List<Integer> slist = new ArrayList<Integer>();
				for(int i=monthTag1;i>0;i--){
					slist.add(i);
				}
				request.setAttribute("slist", slist);
				int monthTag=0;
				if(StringUtil.notNull(monthTagStr).equals(""))monthTag = 0;
				else monthTag = Integer.valueOf(monthTagStr);
				if(monthTag==0){
					monthTag=monthTag1;
				}
				request.setAttribute("monthTag", monthTag);
				RewardDao wrdDao = new RewardDao();
				Reward wrd = new Reward();
				wrd.setUserId(userId);
				wrd.setMonthTag(monthTag);
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wrdDao.findByPage(wrd, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
				request.setAttribute("monthTag", monthTag);
			}else{
				message ="用户未登录，请重新登陆";
			}
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_month_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void reward_month_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if(user!=null){
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
				request.setAttribute("weekTag", String.valueOf(weekTag));
				if(weekTag>0){
					String tableName="wreward_"+weekTag;
				WRewardDao wrdDao = new WRewardDao();
				WReward wrd = new WReward();
				wrd.setUserId(user.getUserId());
				wrd.setWeekTag(weekTag);
				wrd = wrdDao.findByUserId(user.getUserId(),tableName,weekTag);
				if(wrd!=null){
					request.setAttribute("wrd", wrd);
				}else message="数据获取失败。";

			}else message="未获取相应周期的数据。";
			}else message="会员未登录。";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据获取失败。";
			e.printStackTrace();
		} finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_list.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void initSettle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		try {
			if(admin!=null){
				Settle st = new Settle();
				Timestamp date=new Timestamp(new Date().getTime());
				Timestamp startTime=new Timestamp(StringUtil.parseToDate("2018-09-01 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				st.setMonthTag(0);
				st.setState(3);
				st.setStartTime(startTime);
				st.setEndTime(startTime);
				st.setEntryTime(date);
				SettleDao stDao = new SettleDao();
				if(stDao.init_settle(st)>0)
					message = "第"+st.getMonthTag()+"期结算表信息初始化成功。";
				else message = "第"+st.getMonthTag()+"期结算记录已存在或者数据保存有误。";
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
	
	public void admin_reward_month(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message1= "";
		int stag = 0;
		try {
			if(admin!=null){
				SettleDao stDao = new SettleDao();
				Settle st = stDao.getNextSettle();
				if(st!=null){
					int monthTag = st.getMonthTag();
					Timestamp startTime =st.getStartTime();
					Timestamp endTime =st.getEndTime();
					int state = st.getState();
				if(monthTag==0){
					stag=2;
					request.setAttribute("tag", stag);
					request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss));
					request.setAttribute("state", String.valueOf(state));
					request.setAttribute("monthTag", monthTag);
				}else{
					if(state==3){
						stag=2;
						request.setAttribute("tag", stag);
						request.setAttribute("startTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("state", String.valueOf(state));
						request.setAttribute("monthTag", monthTag);
					}else{
						stag=1;
						request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("endTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("message","当前结算周期：" + StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss)+ "至"
								+ StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("state", String.valueOf(state));
						request.setAttribute("tag", stag);
						request.setAttribute("monthTag", monthTag);
					}
				}
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				}else{
					message1 = "结算信息失败，请查看是否已初始化结算表。";
				}
			}else{
				message1 = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					request.setAttribute("message1", message1);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("reward_month.jsp");
					dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_month_0(HttpServletRequest request, HttpServletResponse response)
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
					 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					 String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
					 String monthTagStr = StringUtil.notNull(request.getParameter("monthTag"));
				Timestamp startTime = null;
				Timestamp endTime = null;
				Timestamp date=new Timestamp(new Date().getTime());
				SettleDao stDao = new SettleDao();
				Settle st0 = stDao.findByMonthTag(0);
				int monthTag = 0;
				if(!monthTagStr.equals("")) monthTag = Integer.valueOf(monthTagStr)+1;
				if(!startTimeStr.equals("")) startTime=new Timestamp(StringUtil.parseToDate(startTimeStr, DateUtil.yyyyMMddHHmmss).getTime());
				if(!endTimeStr.equals("")) endTime=new Timestamp(StringUtil.parseToDate(endTimeStr, DateUtil.yyyyMMddHHmmss).getTime());
				if(startTime!=null&&endTime!=null){
					if(endTime.getTime()-startTime.getTime()>0){
						if(monthTag>0){
					Settle st = new Settle();
					st.setMonthTag(monthTag);
					st.setTotalPrice(st0.getTotalPrice());
					st.setEntryTime(date);
					st.setStartTime(startTime);
					st.setEndTime(endTime);
					st.setState(1);
					stDao = new SettleDao();
					message = stDao.save(st);
					if(message.equals("yes")){
						message = "第"+monthTag+"期结算开始，请继续点击奖金结算进行操作。";
					}else error=message;
						}else{
							error = "结算周期时间获取失败。";
						}
					}else{
						error = "结束时间必须大于开始时间。";
					}
				}else{
					error = "时间段信息获取失败。";
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
			String request_dispatcher ="reward_month_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="reward_month_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="reward_month_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="reward_month_message.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_month_1(HttpServletRequest request, HttpServletResponse response)
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
					if (StringUtil.notNull(token_old).equals(token)) {
						 String monthTagStr = StringUtil.notNull(request.getParameter("monthTag"));
					
					int monthTag = 0;
					if(!monthTagStr.equals("")) monthTag = Integer.valueOf(monthTagStr);
							if(monthTag>0){
						RewardDao  wrdDao = new RewardDao();
						message = wrdDao.countReward(monthTag);
						 if(message.equals("yes"))
							message = "第"+monthTag+"期佣金汇总成功，请继续点击佣金结算进行操作。";
							}else {
								error=message;
								message="";
							}
							}else{
								error = "结算周期时间获取失败。";
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
			String request_dispatcher ="reward_month_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="reward_month_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="reward_month_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="reward_month_message.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void admin_reward_month_reset(HttpServletRequest request, HttpServletResponse response)
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
					if (StringUtil.notNull(token_old).equals(token)) {
						 String monthTagStr = StringUtil.notNull(request.getParameter("monthTag"));
					
					int monthTag = 0;
					if(!monthTagStr.equals("")) monthTag = Integer.valueOf(monthTagStr);
							if(monthTag>0){
						RewardDao  wrdDao = new RewardDao();
						message = wrdDao.resetReward(monthTag);
						 if(message.equals("yes"))
							message = "第"+monthTag+"期佣金汇总成功，请继续点击佣金结算进行操作。";
							}else {
								error=message;
								message="";
							}
							}else{
								error = "结算周期时间获取失败。";
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
			String request_dispatcher ="reward_month_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="reward_month_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="reward_month_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="reward_month_message.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	
	public void exportExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());

		String weekTagStr = request.getParameter("weekTag");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "第"+weekTagStr+"期奖金明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		 List<WReward> result = new ArrayList<WReward>();
		String message = "";
			try {
				if(admin!=null){
					int weekTag=0;
					if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
					else weekTag = Integer.valueOf(weekTagStr);
					WRewardDao wrdDao = new WRewardDao();
					WReward wrd = new WReward();
					wrd.setUserId(userId);
					wrd.setWeekTag(weekTag);
					result = wrdDao.findByList(wrd,"0");
					String[][] content = new String[result.size()+2][21];
					content[0][0]="序号";
					content[0][1]="开始时间";
					content[0][2]="结束时间";
					content[0][3]="会员编号";
					content[0][4]="会员姓名";
					content[0][5]="加盟级别";
					content[0][6]="会员奖衔";
					content[0][7]="创业奖";
					content[0][8]="推荐奖";
					content[0][9]="见点奖";
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
						content[i+1][8]  =  StringUtil.decimalFormat(result.get(i).getAmount_7());
						content[i+1][9]  =  StringUtil.decimalFormat(result.get(i).getAmount_8());
						content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+1][11]  = StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+1][12] = StringUtil.decimalFormat(result.get(i).getAmount_4());
						content[i+1][13] =StringUtil.decimalFormat(result.get(i).getAmount_5());
						content[i+1][14] = StringUtil.decimalFormat(result.get(i).getAmount_6());
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
						sum[1] = ArithUtil.add(sum[1],result.get(i).getAmount_7());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getAmount_8());
						sum[3] = ArithUtil.add(sum[3],result.get(i).getAmount_2());
						sum[4] = ArithUtil.add(sum[4],result.get(i).getAmount_3());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getAmount_4());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getAmount_5());
						sum[7] = ArithUtil.add(sum[7],result.get(i).getAmount_6());
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
					 HSSFSheet sheet = wb.createSheet("奖金明细");  
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
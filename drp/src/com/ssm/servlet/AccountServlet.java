package com.ssm.servlet;

import java.io.IOException;
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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.ssm.dao.AdminDao;
import com.ssm.dao.ChargeApplyDao;
import com.ssm.dao.DeptDao;
import com.ssm.dao.JobDao;
import com.ssm.dao.MoneyDao;
import com.ssm.dao.ReportDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Admin;
import com.ssm.pojo.ChargeApply;
import com.ssm.pojo.Dept;
import com.ssm.pojo.Job;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class AccountServlet extends HttpServlet {

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

		} else if (method.equals("money_balance")) {
			money_balance(request, response);

		} else if (method.equals("admin_money_balance")) {
			admin_money_balance(request, response);

		}else if (method.equals("admin_money_summary")) {
			admin_money_summary(request, response);

		}else if (method.equals("money_detail")) {
			money_detail(request, response);

		} else if (method.equals("admin_money_detail")) {
			admin_money_detail(request, response);
		}else if (method.equals("money_transfer")) {
			money_transfer(request, response);
		}else if (method.equals("money_transfer_save")) {
			money_transfer_save(request, response);
		}else if (method.equals("to_emoney")) {
			to_emoney(request, response);
		}else if (method.equals("to_emoney_save")) {
			to_emoney_save(request, response);
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}
	}
	

	public void money_balance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if (user != null) {
				UserDao userDao = new UserDao();
				User user1 = userDao.findMoneyByUserId(user.getUserId());
				if(user1!=null){
					request.setAttribute("user1", user1);
				}else{
					message="会员信息获取失败。";
				}
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("money_balance.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_money_balance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if (admin!=null) {
				MoneyDao moneyDao = new MoneyDao();
				User user1 = moneyDao.findByAllMoney();
				if(user1!=null){
					Double[]  sum= new Double[10];
					sum[0] = user1.getEmoney();
					sum[1] = user1.getSmoney();
					sum[2] = user1.getDmoney();
					sum[3] = user1.getRmoney();
					sum[4] = user1.getPmoney();
					sum[5] = user1.getChuangyeAward();
					sum[6] = user1.getChuangyeAwardPaid();
					sum[7]= ArithUtil.sub(user1.getChuangyeAward(), user1.getChuangyeAwardPaid());
					sum[8]=	ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(user1.getEmoney(), user1.getSmoney()), user1.getDmoney()), user1.getRmoney()), user1.getPmoney());
					request.setAttribute("sum", sum);
				}else{
					message="会员信息获取失败。";
				}
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("balance_summary.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void money_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if (user != null) {
				String type = StringUtil.notNull(request.getParameter("type"));
				String tradeType =StringUtil.notNull(request.getParameter("tradeType"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 20;
				double[] sum = {0,0};
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				MoneyDao moneyDao = new MoneyDao();
				AccountDetail ad = new AccountDetail();
				
				ad.setUserId(user.getUserId());
				ad.setTradeType(tradeType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					ad.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					ad.setEndTime(endTime);
				}
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = moneyDao.findByPage(ad, type, pager);
				sum = pager.getSum();
				request.setAttribute("pager",pager);
				request.setAttribute("type",type);
				request.setAttribute("tradeType",tradeType);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum", sum);
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("money_detail.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	public void admin_money_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if (admin != null) {
				String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
				String type = StringUtil.notNull(request.getParameter("type"));
				String tradeType =StringUtil.notNull(request.getParameter("tradeType"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 20;
				double[] sum = {0,0};
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				MoneyDao moneyDao = new MoneyDao();
				AccountDetail ad = new AccountDetail();
				ad.setUserId(userId);
				ad.setTradeType(tradeType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					ad.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					ad.setEndTime(endTime);
				}
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = moneyDao.findByPage(ad, type, pager);
				sum = pager.getSum();
				request.setAttribute("pager",pager);
				request.setAttribute("userId",userId);
				request.setAttribute("type",type);
				request.setAttribute("tradeType",tradeType);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum", sum);
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("money_detail.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	public void money_transfer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if (user != null) {
				int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
				request.getSession().setAttribute("token", String.valueOf(token));
				UserDao userDao = new UserDao();
				User user1 = userDao.findByUserId(user.getUserId());
				if(user1!=null) {	
					request.getSession().setAttribute("sys_user", user1);
				}else{
					message="会员信息获取失败。";
				}
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("money_transfer.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void money_transfer_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if (user != null) {
				if(StringUtil.notNull(token_old).equals(token)){
					String amount =StringUtil.notNull(request.getParameter("amount"));
					String userId =StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					String type =StringUtil.notNull(request.getParameter("type"));
					String password =StringUtil.notNull(request.getParameter("password2"));
					if(!userId.equals(user.getUserId())){
						if(MD5.GetMD5Code(password).equals(user.getPassword2())){
							if(!amount.equals("")){
								double emoney = Double.valueOf(amount);
								if(emoney>0){
									Timestamp date = new Timestamp(new Date().getTime());
									if(type.equals("1")){
										MoneyDao moneyDao = new MoneyDao();
										message = moneyDao.saveEMoneyTransfer(user.getUserId(), userId, emoney, date);
										if(message.equals("yes")){
											message = user.getUserId()+"转账交易成功，收款人："+userId+",转账金额："+StringUtil.decimalFormat(emoney)+";";
										}
									}else{
										message = "账户类型不支持转账。";
									}
								}else{
									message="转账金额必须大于0，请重试。";
									}
							}else{
								message="申请金额格式有误，请重试！";
							}
					}else{
						message="支付密码有误，请重新输入！";
					}
					}else{
						message= "收款人不能为自己。";
					}
				}else{
					message="请勿重复提交数据。";
					}
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据加载异常。";
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("money_transfer_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void to_emoney(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		try {
			if(user!=null){
				UserDao userDao = new UserDao();
				User user1 = userDao.findByUserId(user.getUserId());
				if(user1!=null) 		{	
					request.getSession().setAttribute("sys_user", user1);
				}

				int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
				request.getSession().setAttribute("token", String.valueOf(token));
			}
		}finally{
			RequestDispatcher dispatcher = request.getRequestDispatcher("rmoney_to_emoney.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void to_emoney_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if (user != null) {
				if(StringUtil.notNull(token_old).equals(token)){
					String amount =StringUtil.notNull(request.getParameter("amount"));
					String password =StringUtil.notNull(request.getParameter("password2"));
					if(MD5.GetMD5Code(password).equals(user.getPassword2())){
							if(!amount.equals("")){
								double emoney = Double.valueOf(amount);
								if(emoney>0){
									Timestamp date = new Timestamp(new Date().getTime());
										MoneyDao moneyDao = new MoneyDao();
										message = moneyDao.saveToEmoney(user.getUserId(),  emoney, date);
										if(message.equals("yes")){
											message = user.getUserId()+"奖金券转报单券成功，转账金额："+StringUtil.decimalFormat(emoney)+";";
										}
								}else{
									message="转账金额必须大于0，请重试。";
									}
							}else{
								message="申请金额格式有误，请重试！";
							}
					}else{
						message="支付密码有误，请重新输入！";
					}
					
				}else{
					message="请勿重复提交数据。";
					}
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据加载异常。";
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rmoney_to_emoney_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_money_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if (admin!=null) {
				 String startTimeStr = request.getParameter("startTime");
					String endTimeStr = request.getParameter("endTime");
					if(!(StringUtil.notNull(startTimeStr).equals("")||StringUtil.notNull(startTimeStr).equals(""))){
						
						Timestamp startTime =new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						Timestamp endTime =new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						ReportDao rpDao = new ReportDao();
						AccountDetail ad = rpDao.moneyBalanceSummary(startTime, endTime);
						request.setAttribute("sys_ad", ad);
						request.setAttribute("startTime", startTimeStr);
						request.setAttribute("endTime", endTimeStr);
				}else{
					message="时间信息获取失败。";
				}
			}else{
				message="用户未登录，请重新登陆后再试。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("money_summary.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			String type = StringUtil.notNull(request.getParameter("type"));
		 String str_type ="";
			if(type.equals("1")) str_type  ="报单券";
			else if(type.equals("2")) str_type  ="购物券";
			else if(type.equals("3")) str_type  ="复消券";
			else if(type.equals("4")) str_type  ="奖金券";
		 String filename = str_type+"资金明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<AccountDetail>  result = new ArrayList<AccountDetail> ();
			try {
				
				String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
				String tradeType =StringUtil.notNull(request.getParameter("tradeType"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
			
				MoneyDao moneyDao = new MoneyDao();
				AccountDetail ad = new AccountDetail();
				ad.setUserId(userId);
				ad.setTradeType(tradeType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					ad.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					ad.setEndTime(endTime);
				}
				result = moneyDao.findAllByList(ad, type);
				String[][] content = new String[result.size()+2][13];
				content[0][0]="序号";
				content[0][1]="交易日期";
				content[0][2]="交易类型";
				content[0][3]="收入";
				content[0][4]="支出";
				content[0][5]="余额";
				content[0][6]="用户编号";
				content[0][7]="用户姓名";
				content[0][8]="摘要";
				double[] sum = {0,0,0};
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
					content[i+1][2]  = StringUtil.notNull(result.get(i).getTradeType());
					if(result.get(i).getPayType()==1){
					content[i+1][3]  = StringUtil.decimalFormat2(result.get(i).getAmount());
					content[i+1][4]  ="0";
					sum[0] = ArithUtil.add(sum[0], result.get(i).getAmount());
					}else{
						content[i+1][3]  = "0";
						content[i+1][4]  =StringUtil.decimalFormat2(result.get(i).getAmount());
						sum[1] = ArithUtil.add(sum[0], result.get(i).getAmount());
					}
					content[i+1][5]  = StringUtil.decimalFormat2(result.get(i).getBalance());
					content[i+1][6] = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][7] = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][8] = StringUtil.notNull(result.get(i).getSummary());
				}
				content[result.size()+1][0]  = "";
				content[result.size()+1][1] = "";
				content[result.size()+1][2] = ""	;
				content[result.size()+1][3] = StringUtil.decimalFormat2(sum[0]);
				content[result.size()+1][4] = StringUtil.decimalFormat2(sum[1]);
				content[result.size()+1][5] = ""	;
				content[result.size()+1][6] = ""	;
				content[result.size()+1][7] = ""	;
				content[result.size()+1][8] = ""	;
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("资金明细");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
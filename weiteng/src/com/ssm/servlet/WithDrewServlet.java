package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import com.ssm.pojo.User;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.dao.OrderDao;
import com.ssm.dao.ParamDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.WithDrewDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.WithDrew;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.MD5;

public class WithDrewServlet extends HttpServlet {

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

		} else if (method.equals("admin_list")) {
			admin_list(request, response);

		}else if(method.equals("list")){
			apply_list(request,response);
			
		}else if(method.equals("add")){
			apply_add(request,response);
			
		}else if(method.equals("save")){
			apply_save(request,response);
			
		}else if(method.equals("apply_yes")){
			apply_yes(request,response);
			
		}else if(method.equals("apply_yes_reback")){
			apply_yes_reback(request,response);
			
		}else if(method.equals("apply_no")){
			apply_no(request,response);
			
		}else if (method.equals("amountAjax")) {
			amountAjax(request, response);

		}else if(method.equals("exportExcel")){
			exportExcel(request, response);
		}else if(method.equals("apply_comment_edit")){
			apply_comment_edit(request,response);
			
		}else if(method.equals("apply_comment_save")){
			apply_comment_save(request,response);
		}
	}
	
	public void apply_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String state = StringUtil.notNull(request.getParameter("state"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int pageNo = 1;
			int pageSize = 20;
			String message = "";
			try {
				if(user!=null){
				if(!pageNoStr.equals("")) pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals("")) pageSize = Integer.valueOf(pageSizeStr);
				WithDrew wd = new WithDrew();
				wd.setUserId(user.getUserId());;
				if(!state.equals(""))
				wd.setState(Integer.valueOf(state));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					wd.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					wd.setEndTime(endTime);
				}
					WithDrewDao wdDao = new WithDrewDao();
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = wdDao.findByPage(wd, pager);
					request.setAttribute("pager", pager);
					request.setAttribute("state",state);
					request.setAttribute("startTime",startTimeStr);
					request.setAttribute("endTime",endTimeStr);
				}else{
					message = "用户未登陆，请重新登陆。";
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message=  "数据操作异常，请重试！";
			e.printStackTrace();
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("with_drew_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void apply_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String message ="";
		try {
			if(user!=null){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				UserDao userDao = new UserDao();
				User user1 = userDao.findByUserId(user.getUserId());
				if(user1!=null){
					request.setAttribute("user1", user1);
					if(user1.getAccountName().equals("")||user1.getAccountId().equals("")||user1.getBankName().equals("")||user1.getBankAdr().equals("")) {
						message= "会员的收款账户信息获取失败，请在个人信息中设置好收款账户信息！";
					}
					
				}else {
					message= "会员信息获取失败";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("with_drew_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("with_drew_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
			if(user!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String amount =StringUtil.notNull(request.getParameter("amount"));
				String password =StringUtil.notNull(request.getParameter("password"));
				String accountId =StringUtil.notNull(request.getParameter("accountId"));
				String accountName =StringUtil.notNull(request.getParameter("accountName"));
				String bankName =StringUtil.notNull(request.getParameter("bankName"));
				String bankAdr =StringUtil.notNull(request.getParameter("bankAdr"));
				if(!(accountId.equals("")||accountName.equals("")||bankName.equals("")||bankAdr.equals(""))){
						if(!amount.equals("")){
							User user1 = new User();
							user1.setUserId(user.getUserId());
							user1.setAccountId(accountId);
							user1.setAccountName(accountName);
							user1.setBankAdr(bankAdr);
							user1.setBankName(bankName);
							user1.setPassword(MD5.GetMD5Code(password));
							WithDrewDao WithDrewDao = new WithDrewDao();
							message = WithDrewDao.apply_save(Double.valueOf(amount), user1);
						
					}else{
					 message= "申请金额不能为空，请重试！";
					}
				}
				else{
				message="收款账户资料不全，请在个人资料中补全后再试！";
				}
				}else{
					message=  "请勿重复提交数据！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("with_drew_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void apply_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message ="";
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
					if(!id.equals("")){
						Timestamp date = new Timestamp(new Date().getTime());
							WithDrewDao WithDrewDao = new WithDrewDao();
							message = WithDrewDao.apply_yes(admin.getAdminName(),id, date);
						}else{
							message= "申请编号获取失败，请重试！";
						}
				
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("with_drew_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void apply_yes_reback(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message ="";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
					if(!id.equals("")){
						Timestamp date = new Timestamp(new Date().getTime());
							WithDrewDao WithDrewDao = new WithDrewDao();
							message = WithDrewDao.apply_yes_reback(admin.getAdminName(),id, date);
						}else{
							error= "申请编号获取失败，请重试！";
						}
				
				}else{
					error=  "用户未登陆，请重新登陆！";
					lt++;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			String request_dispatcher ="with_drew_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="with_drew_message.jsp";
				request.setAttribute("message", message);
			}else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="with_drew_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="with_drew_message.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
			
		}
	}
	
	public void apply_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message ="";
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
					if(!id.equals("")){
						Timestamp date = new Timestamp(new Date().getTime());
							WithDrewDao WithDrewDao = new WithDrewDao();
							message = WithDrewDao.apply_no(admin.getAdminName(),id, date);
						}else{
							message= "申请编号获取失败，请重试！";
						}
				
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("with_drew_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}

	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String state = StringUtil.notNull(request.getParameter("state"));
	String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
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
			WithDrew wd = new WithDrew();
			wd.setUserId(userId);;
			if(!state.equals(""))
			wd.setState(Integer.valueOf(state));
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				wd.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				wd.setEndTime(endTime);
			}
				WithDrewDao wdDao = new WithDrewDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wdDao.findByPage(wd, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId",userId);
				request.setAttribute("state",state);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String request_dispatcher ="with_drew_list.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="with_drew_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="with_drew_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="with_drew_list.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void amountAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String amountStr = StringUtil.notNull(request.getParameter("amount"));
		double rmoney = 0;
		try {
			Map<String, Object> object = new HashMap<String, Object>();
			if (!StringUtil.notNull(userId).equals("")) {
				if (amountStr.equals("")) {
					object.put("tag", 2);
				} else {
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);

					if(user1!=null){
						rmoney = user1.getRmoney();
						double balance = ArithUtil.sub(rmoney, Double.valueOf(amountStr));

						if(balance>=0)
							object.put("tag", 0);
						else object.put("tag", 1);
					}else{
						 object.put("tag", 1);
					}
				}
			} else{
				 object.put("tag", 1);
			}
			
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void exportExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "提现申请"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<WithDrew>  result = new ArrayList<WithDrew> ();
			String userId = StringUtil.notNull(request.getParameter("userId"));
			String state = StringUtil.notNull(request.getParameter("state"));
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
			String message="";
			try {
				if(admin!=null){
					WithDrew wd = new WithDrew();
					wd.setUserId(userId);;
					if(!state.equals(""))
					wd.setState(Integer.valueOf(state));
					if(!startTimeStr.equals("")){
						Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						wd.setStartTime(startTime);
					}
					if(!endTimeStr.equals("")){
						Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						wd.setEndTime(endTime);
					}
						WithDrewDao wdDao = new WithDrewDao();
			result = wdDao.findByList(wd);
			double[] sum = {0,0,0};
				String[][] content = new String[result.size()+2][13];
				content[0][0]="序号";
				content[0][1]="申请编号";
				content[0][2]="会员编号";
				content[0][3]="会员姓名";
				content[0][4]="申请金额";
				content[0][5]="手续费";
				content[0][6]="到账金额";
				content[0][7]="收款账户信息";
				content[0][8]="申请时间";
				content[0][9]="审批人";
				content[0][10]="审核时间";
				content[0][11]="当前状态";
				content[0][12]="备注";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getApplyId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][4]  = StringUtil.decimalFormat2(result.get(i).getAmount());
					content[i+1][5]  =  StringUtil.decimalFormat2(result.get(i).getFee());
					content[i+1][6]  = StringUtil.decimalFormat2(result.get(i).getActualAmount());
					content[i+1][7]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName())+StringUtil.notNull(result.get(i).getBankAdr());
					content[i+1][8] = StringUtil.parseToString(result.get(i).getApplyTime(), DateUtil.yyyyMMddHHmmss);
					content[i+1][9] =StringUtil.notNull(result.get(i).getReviewerId());
					content[i+1][10] = StringUtil.parseToString(result.get(i).getApplyTime(), DateUtil.yyyyMMddHHmmss);
					String stateStr ="";
					if(result.get(i).getState()==0) stateStr = "已删除";
					else if(result.get(i).getState()==1) stateStr = "待审批";
					else if(result.get(i).getState()==2) stateStr = "审批通过";
					else if(result.get(i).getState()==3) stateStr = "审批不通过";
					content[i+1][11] = stateStr;
					content[i+1][12] =StringUtil.notNull(result.get(i).getComments());
					sum[0] = ArithUtil.add(sum[0],result.get(i).getAmount());
					sum[1] = ArithUtil.add(sum[1],result.get(i).getFee());
					sum[2] = ArithUtil.add(sum[2],result.get(i).getActualAmount());

				}
				int h = result.size()+1;
				content[h][0]="-";
				content[h][1]="合计";
				content[h][2]="-";
				content[h][3]="-";
				content[h][4]=StringUtil.decimalFormat(sum[0]);
				content[h][5]=StringUtil.decimalFormat(sum[1]);
				content[h][6]=StringUtil.decimalFormat(sum[2]);
				content[h][7]="-";
				content[h][8]="-";
				content[h][9]="-";
				content[h][10]="-";
				content[h][11]="-";
				content[h][12]="-";
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("提现申请");  
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
	
	public void apply_comment_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message ="";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				String applyId = StringUtil.notNull(request.getParameter("id"));

				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				WithDrewDao wdDao = new WithDrewDao();
				WithDrew wd = wdDao.findByApplyId(applyId);
				if(wd!=null){
					request.setAttribute("sys_apply", wd);
				}else {
					message="提现信息获取失败";
				}
				}else{
					error=  "用户未登陆，请重新登陆！";
					lt++;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=  "数据操作异常，请重试！";
		}finally{
			String request_dispatcher ="with_drew_comment.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="with_drew_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="with_drew_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="with_drew_comment.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void apply_comment_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message ="";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String id = StringUtil.notNull(request.getParameter("id"));
				String applyId = StringUtil.notNull(request.getParameter("applyId"));
				String comments = StringUtil.notNull(request.getParameter("comments"));
				WithDrewDao wdDao = new WithDrewDao();
				WithDrew wd = new WithDrew();
				wd.setId(Integer.valueOf(id));
				wd.setComments(comments);
				message = wdDao.updateWithDrew(wd);
				if(message.equals("yes")){
					message= applyId+"提现评论已成功，请在提现审核中查看。";
				}
			}else {
				error= "请勿重复提交数据。";
			}
			}else{
				error=  "用户未登陆，请重新登陆！";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=  "数据操作异常，请重试！";
		}finally{
			
			String request_dispatcher ="with_drew_message.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="with_drew_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="with_drew_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="with_drew_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
}
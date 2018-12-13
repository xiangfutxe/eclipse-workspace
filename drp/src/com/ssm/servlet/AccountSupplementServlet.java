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

import com.ssm.dao.AccountSupplementDao;
import com.ssm.dao.CenterDao;
import com.ssm.dao.ChargeApplyDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.ChargeApply;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class AccountSupplementServlet extends HttpServlet {

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
			list(request, response);

		} else if (method.equals("add")) {
			admin_add(request, response);

		}else if (method.equals("save")) {
			admin_save(request, response);

		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}
	}
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		try {
			if(user!=null){
				
			}
		}finally{
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_detail.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String type = StringUtil.notNull(request.getParameter("type"));
	String payType = StringUtil.notNull(request.getParameter("payType"));

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
			AccountSupplement asm = new AccountSupplement();
			asm.setUserId(userId);
			if(!type.equals("")) asm.setType(Integer.valueOf(type));
			if(!payType.equals("")) asm.setPayType(Integer.valueOf(payType));
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				asm.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				asm.setEndTime(endTime);
			}
			double[] sum = {0};
				AccountSupplementDao asmDao = new AccountSupplementDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = asmDao.findByPage(asm,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("type",type);
				request.setAttribute("payType",payType);

				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
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
						.getRequestDispatcher("account_supplement_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("account_supplement.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	

	
	
	
	public void admin_add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("account_supplement_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("account_supplement_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void admin_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	String error = "";

		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String amountstr  = StringUtil.notNull(request.getParameter("amount"));
					String type  = StringUtil.notNull(request.getParameter("type"));
					String payType  = StringUtil.notNull(request.getParameter("payType"));
					String summary  = StringUtil.notNull(request.getParameter("summary"));
					if(userId.equals("")){ error = "用户编号不能为空！";}
					else if(amountstr.equals("")){ error = "扣款金额有误！";}
					else if(type.equals("")){ error = "未选择相应的扣款账户！";}
					else if(payType.equals("")){ error = "未选择相应的增扣类型！";}
					if(error.equals("")){
						 Timestamp date = new Timestamp(new Date().getTime());
				AccountSupplementDao asmDao = new AccountSupplementDao();
				message = asmDao.saveAsm(userId,admin.getAdminName(),Double.valueOf(amountstr),Integer.valueOf(type),Integer.valueOf(payType),summary,date);
				
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
					.getRequestDispatcher("account_supplement_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "增扣明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<AccountSupplement>  result = new ArrayList<AccountSupplement> ();
			String message="";
			try {
				String type = StringUtil.notNull(request.getParameter("type"));
				String payType = StringUtil.notNull(request.getParameter("payType"));

				String userId = StringUtil.notNull(request.getParameter("userId"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				AccountSupplement asm = new AccountSupplement();
				asm.setUserId(userId);
				if(!type.equals("")) asm.setType(Integer.valueOf(type));
				if(!payType.equals("")) asm.setPayType(Integer.valueOf(payType));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					asm.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					asm.setEndTime(endTime);
				}
				AccountSupplementDao asmDao = new AccountSupplementDao();
				double sum = 0;

				result = asmDao.findByList(asm);
				String[][] content = new String[result.size()+2][13];
				content[0][0]="序号";
				content[0][1]="交易时间";
				content[0][2]="账户类型";
				content[0][3]="补扣类型";
				content[0][4]="金额";
				content[0][5]="用户编号";
				content[0][6]="用户名称";
				content[0][7]="摘要";
			
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
					String str_type = "";
					if(result.get(i).getType()==1) str_type = "报单券账户";
					else 	if(result.get(i).getType()==2) str_type = "购物券账户";
					else 	if(result.get(i).getType()==3) str_type = "复消券账户";
					else 	if(result.get(i).getType()==4) str_type = "奖金券账户";
					content[i+1][2]  = str_type;
					
					String pay_type = "";
					if(result.get(i).getPayType()==1) pay_type = "补增";
					else 	if(result.get(i).getPayType()==2) pay_type = "补扣";
					content[i+1][3]  = pay_type;
					content[i+1][4]  = StringUtil.decimalFormat2(result.get(i).getAmount());
					content[i+1][5]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][7] = StringUtil.notNull(result.get(i).getSummary());
					sum = ArithUtil.add(sum,result.get(i).getAmount());
				}
				int h = result.size()+1;
				content[h][0]="-";
				content[h][1]="合计";
				content[h][2]="-";
				content[h][3]="-";
				content[h][4]=StringUtil.decimalFormat(sum);;
				content[h][5]="-";
				content[h][6]="-";
				content[h][7]="-";
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("增补明细");  
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
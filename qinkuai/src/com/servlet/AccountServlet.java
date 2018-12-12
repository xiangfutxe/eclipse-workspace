package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.db.DBConn;
import com.pojo.AccountDetail;
import com.pojo.AccountSupplement;
import com.pojo.Admin;
import com.pojo.BankAccount;
import com.pojo.Branch;
import com.pojo.ChargeApply;
import com.pojo.Order;
import com.pojo.PMoney;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.ArithUtil;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private int pageNo = 1;
	
	private int pageSize = 10;

	ICustomService cs = new CustomService();
	IUserService us = new UserService();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		
		}else if(method.equals("money_balance")){
			money_balance(request,response);
			
		}else if(method.equals("money_detail")){
			money_detail(request,response);
			
		}else if(method.equals("admin_balance_summary")){
			admin_balance_summary(request,response);
			
		}else if(method.equals("admin_money_detail")){
			admin_money_detail(request,response);
			
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
			
	}

}
	
	public void money_balance(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		String message = "";
		DBConn db = new DBConn();
		try {
			if(branch!=null){
			if(db.createConn()){
				conn= db.getConnection();
				String sql = "select * from branch where code='"+branch.getCode()+"'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					Branch bh = new Branch();
					bh.setId(rs.getInt("id"));
					bh.setCode(rs.getString("code"));
					bh.setEmoney(rs.getDouble("emoney"));
					request.setAttribute("branch", bh);
				}else {
					message="店铺信息获取失败，请重新登陆！";
					
				}
				}else {
					message="数据库连接已断开，请重试！";
				}
			}else {
				message="会员登陆失效，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message="数据操作有误，请重试！";
		}finally{
			db.close();
			if(!message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("money_message.jsp");
				dispatcher.forward(request, response);
			}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("money_balance.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void money_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");

		String message = "";
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		try {
			if(branch!=null){
		String type = StringUtil.notNull(request.getParameter("type"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="";
				if(!type.equals("")){
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					if(type.equals("1")){
						sql = "select * from emoneyDetail where userId ='"+branch.getCode()+"'  and entryTime>='"+startTime+"' and entryTime<='"+endTime+"' order by entryTime  desc,id desc";
					}
				}else{
					if(type.equals("1")){
						sql = "select * from emoneyDetail where userId='"+branch.getCode()+"'  order by entryTime  desc,id desc";
					}
				}
					if(!sql.equals("")){
						stmt = conn.createStatement();
						rs=stmt.executeQuery(sql);
						double[] sum = {0,0};
 						while(rs.next()){
							AccountDetail ad = new AccountDetail();
							String payType = rs.getString("payType");
							double amount = rs.getDouble("amount");
							ad.setId(rs.getLong("id"));
							ad.setAmount(amount);
							ad.setBalance(rs.getDouble("balance"));
							ad.setPayType(payType);
							ad.setSummary(rs.getString("summary"));
							ad.setTime(rs.getTimestamp("entryTime"));
							ad.setTradeType(rs.getString("tradeType"));
							if(payType.equals("1")) sum[0] =ArithUtil.add(sum[0],amount);
							else if(payType.equals("2")) sum[1] =ArithUtil.add(sum[1],amount);
							result.add(ad);
						}
						if(result.size()>0){
							if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
							if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
							int startIndex = pageSize*(pageNo-1);
							int endIndex = pageSize*pageNo;
							if(result.size()<endIndex) endIndex = result.size();
							coll = result.subList(startIndex, endIndex);
							Pager pager = new Pager(pageSize,pageNo,result.size(),coll);
							request.setAttribute("pager", pager);
						}
						request.setAttribute("type",type);
						request.setAttribute("startTime",startTimeStr);
						request.setAttribute("endTime",endTimeStr);
						request.setAttribute("sum", sum);
					}else message="未找到你选择的账户类型信息，请重试！";
				}else message="未选择需要查询的账户类型信息，请重试！";
				}else {
					message="数据库连接已断开，请重试！";
				}
			}else {
				message="会员登陆失效，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据操作有误，请重试！";
		}finally{
			db.close();
			if(!message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("money_message.jsp");
				dispatcher.forward(request, response);
			}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("money_detail.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_money_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			if(admin!=null){
			if(db.createConn()){
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String type = StringUtil.notNull(request.getParameter("type"));
				String tradeType =StringUtil.notNull(request.getParameter("tradeType"));
			
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				pageSize =10;
				pageNo = 1;
				
				double[] sum = {0,0};
				String sql ="";
				if(!type.equals("")){
					if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
						String startTime = startTimeStr+" 00:00:00";
						String  endTime = endTimeStr+" 23:59:59";
						if(type.equals("1")){
							sql = "select * from emoneyDetail where (userId like'%"+userId+"%' or userName like'%"+userId+"%') and entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and tradeType like'%"+tradeType+"%' order by entryTime desc,id desc";
						}
					}else{
						if(type.equals("1")){
							sql = "select * from emoneyDetail where (userId like'%"+userId+"%' or userName like'%"+userId+"%')    and tradeType like'%"+tradeType+"%' order by entryTime desc,id desc";
						}
					}
				}
					if(!sql.equals("")){
						stmt = db.getStmt();
						rs=stmt.executeQuery(sql);
 						while(rs.next()){
							AccountDetail ad = new AccountDetail();
							String payType = rs.getString("payType");
							double amount = rs.getDouble("amount");
							ad.setId(rs.getLong("id"));
							ad.setUserId(rs.getString("userId"));
							ad.setUserName(rs.getString("userName"));
							ad.setAmount(amount);
							ad.setBalance(rs.getDouble("balance"));
							ad.setPayType(payType);
							ad.setSummary(rs.getString("summary"));
							ad.setTime(rs.getTimestamp("entryTime"));
							ad.setTradeType(rs.getString("tradeType"));
							if(payType.equals("1")) sum[0] =ArithUtil.add(sum[0],amount);
							else if(payType.equals("2")) sum[1] =ArithUtil.add(sum[1],amount);
							result.add(ad);
						}
						if(result.size()>0){
							if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
							if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
							int startIndex = pageSize*(pageNo-1);
							int endIndex = pageSize*pageNo;
							if(result.size()<endIndex) endIndex = result.size();
							coll = result.subList(startIndex, endIndex);
							Pager pager = new Pager(pageSize,pageNo,result.size(),coll);
							request.setAttribute("pager", pager);
						}
					}
					request.setAttribute("userId",userId);
						request.setAttribute("type",type);
						request.setAttribute("tradeType",tradeType);
						request.setAttribute("startTime",startTimeStr);
						request.setAttribute("endTime",endTimeStr);
						request.setAttribute("sum", sum);
			
				}else {
					message="数据库连接已断开，请重试！";
				}
			}else {
				message="管理员未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message="数据操作有误，请重试！";
		}finally{
			db.close();
			if(!message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("money_message.jsp");
				dispatcher.forward(request, response);
			}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("money_detail.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_balance_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[6][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select sum(emoney) from branch";
				
						stmt = db.getStmt();
						rs=stmt.executeQuery(sql);
						double[] sum = {0};
 						if(rs.next()){
							sum[0] = rs.getDouble(1);
							
						}
						request.setAttribute("sum", sum);
				
			
				}else {
					message="数据库连接已断开，请重试！";
				}
			}else {
				message="你没有权限进行此操作，请重试！";
			}
		}else {
			message="管理员未登陆，请登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据操作有误，请重试！";
		}finally{
			db.close();
			if(!message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("money_message.jsp");
				dispatcher.forward(request, response);
			}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("balance_summary.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		   response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "资金明细"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List<AccountDetail>  result = new ArrayList<AccountDetail>();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[6][7].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String type = StringUtil.notNull(request.getParameter("type"));
				String tradeType = StringUtil.notNull(request.getParameter("tradeType"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				double[] sum1 = {0,0};
				String sql ="";
				if(!type.equals("")){
					if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
						String startTime = startTimeStr+" 00:00:00";
						String  endTime = endTimeStr+" 23:59:59";
						if(type.equals("1")){
							sql = "select * from emoneyDetail where (userId like'%"+userId+"%' or userName like'%"+userId+"%') and entryTime>='"+startTime+"' and entryTime<='"+endTime+"' and tradeType like'%"+tradeType+"%' order by entryTime desc,id desc";
						}
					}else{
						if(type.equals("1")){
							sql = "select * from emoneyDetail where (userId like'%"+userId+"%' or userName like'%"+userId+"%')    and tradeType like'%"+tradeType+"%' order by entryTime desc,id desc";
						}
					}
				}
					if(!sql.equals("")){
						stmt = db.getStmt();
						rs=stmt.executeQuery(sql);
 						while(rs.next()){
							AccountDetail ad = new AccountDetail();
							String payType = rs.getString("payType");
							double amount = rs.getDouble("amount");
							ad.setId(rs.getLong("id"));
							ad.setUserId(rs.getString("userId"));
							ad.setUserName(rs.getString("userName"));
							ad.setAmount(amount);
							ad.setBalance(rs.getDouble("balance"));
							ad.setPayType(payType);
							ad.setSummary(rs.getString("summary"));
							ad.setTime(rs.getTimestamp("entryTime"));
							ad.setTradeType(rs.getString("tradeType"));
							if(payType.equals("1")) sum1[0] =sum1[0]+amount;
							else if(payType.equals("2")) sum1[1] =sum1[1]+amount;
							result.add(ad);
						}
					}
					String[][] content = new String[result.size()+2][17];
					double[] sum = {0,0,0,0,0,0,0,0,0,0,0,0};
					content[0][0]="序号";
					content[0][1]="交易日期";
					content[0][2]="交易类型";
					content[0][3]="收入";
					content[0][4]="支出";
					content[0][5]="余额";
					content[0][6]="会员编号";
					content[0][7]="会员名称";
					content[0][8]="摘要";
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.parseToString(result.get(i).getTime(), DateUtil.yyyyMMdd);
						content[i+1][2]  = StringUtil.notNull(result.get(i).getTradeType());
						if(result.get(i).getPayType().equals("1"))
						content[i+1][3]  =  StringUtil.decimalFormat(result.get(i).getAmount());
						else content[i+1][3]  = "";
						if(result.get(i).getPayType().equals("2"))
							content[i+1][4]  =  StringUtil.decimalFormat(result.get(i).getAmount());
							else content[i+1][4]  = "";
						content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getBalance());
						content[i+1][6]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][7]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][8]  = StringUtil.notNull(result.get(i).getSummary());
					
					}
					if(result.size()>0){
						content[result.size()+1][0] = "合计";
						content[result.size()+1][1] = "-";
						content[result.size()+1][2] = "-";
						content[result.size()+1][3] = StringUtil.decimalFormat((double) sum1[0]);
						content[result.size()+1][4] = StringUtil.decimalFormat((double) sum1[1]);
						content[result.size()+1][5] = "-";
						content[result.size()+1][6] = "-";
						content[result.size()+1][7] ="-";
						content[result.size()+1][8] = "-";
						
					}
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
			}else {
				message="你没有权限进行此操作，请重试！";
			}
				}else {
					message="数据库连接已断开，请重试！";
				}
			}else {
				message="管理员未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message="数据操作有误，请重试！";
		}finally{
			db.close();
		}
	}
}

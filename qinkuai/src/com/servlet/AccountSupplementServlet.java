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
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.db.DBConn;
import com.jspsmart.upload.SmartUploadException;
import com.pojo.AccountDetail;
import com.pojo.AccountSupplement;
import com.pojo.Admin;
import com.pojo.Branch;
import com.pojo.Order;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.ArithUtil;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;

public class AccountSupplementServlet extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		
		}else if(method.equals("list")){
			list(request,response);
			
		}else if(method.equals("add")){
			add(request,response);
			
		}else if(method.equals("save")){
			try {
				save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
			
	}

}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String type = StringUtil.notNull(request.getParameter("type"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from accountSupplement where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and type like'%"+type+"%'  and userId like'%"+userId+"%' order by id desc";
						
					}else{
						sql ="select * from accountSupplement where type like'%"+type+"%'  and userId like'%"+userId+"%' order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0};
				while(rs.next()){
					AccountSupplement as = new AccountSupplement();
					as.setId(rs.getLong("id"));
					as.setUid(rs.getInt("uid"));
					as.setAmount(rs.getDouble("amount"));
					as.setEntryTime(rs.getTimestamp("entryTime"));
					as.setPayType(rs.getInt("payType"));
					as.setSummary(rs.getString("summary"));
					as.setType(rs.getInt("type"));
					as.setUserId(rs.getString("userId"));
					as.setUserName(rs.getString("userName"));
					as.setAdmin(rs.getString("admin"));
					sum[0]=ArithUtil.add(sum[0],rs.getDouble("amount"));
					result.add(as);
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
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

			
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("accout_supplement_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement_add.jsp");
			dispatcher.forward(request, response);
		
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String error = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				
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
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 double amount = Double.valueOf(amountstr);
					String sql = "select * from branch where code='"+userId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					 String str ="";
				if(rs.next()){
					 Timestamp date = new Timestamp(new Date().getTime());
					 Branch user = new Branch();
					 user.setId(rs.getInt("id"));
					 user.setCode(rs.getString("code"));
					 user.setName(rs.getString("name"));
					 user.setEmoney(rs.getDouble("emoney"));
				
					 if(payType.equals("1")){
						 str ="补增";
					 }else if(payType.equals("2")){
						 str ="扣款";
						 amount = -amount;
					 }
					 stmt = conn.createStatement();
					 String sqli = "insert into accountSupplement(uid,userId,userName,admin,amount,payType,type,summary,entryTime) values('"+user.getId()+"','"+user.getCode()+"','"+user.getName()+"','"+admin.getAdminName()+"','"+Math.abs(amount)
					 		+"','"+payType+"','"+type+"','"+summary+"','"+date+"')";
					 stmt.addBatch(sqli);
					 if(type.equals("1")){
						 double balance = ArithUtil.add(user.getEmoney(),amount);
						 if(balance>=0){
						 String sqlu = "update branch set emoney='"+balance+"' where code='"+user.getCode()+"'";
						 String sql1 = "insert into emoneyDetail(userId,userName,amount,balance,payType,tradeType,summary,entryTime)"
									+ " values('"+user.getCode()+"','"+user.getName()+"','"+Math.abs(amount)+"','"+balance+"','"+Integer.valueOf(payType)+"','"+Constants.MONEY_DETAIL_TYPE_KEY_13
									+"','"+summary+"','"+date+"')";
						 stmt.addBatch(sqlu);
						 stmt.addBatch(sql1);
						 stmt.executeBatch();
						 request.setAttribute("message", "现金账户"+str+"成功，"+str+"金额："+StringUtil.decimalFormat(Math.abs(amount)));
						 }else{
							 request.setAttribute("message", "现金账户余额不足，请确认！");
						 }
					
					 
					 }else{
						 request.setAttribute("message", "未找到所选账户类型信息，请重试！");
					 }
					
				}else {
					request.setAttribute("message", "未获取到你需要操作的相应会员信息，请重试！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
					}else {
						request.setAttribute("message",error);
					}
				
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在增扣明细中查看是否操作成功！");
			}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("account_supplement_message.jsp");
			dispatcher.forward(request, response);
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
		List<AccountSupplement>  result = new ArrayList<AccountSupplement>();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[6][5].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String type = StringUtil.notNull(request.getParameter("type"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
						String sql ="";
						if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
							String startTime = startTimeStr+" 00:00:00";
							String  endTime = endTimeStr+" 23:59:59";
									sql ="select * from accountSupplement where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and type like'%"+type+"%'  and userId like'%"+userId+"%' order by id desc";
								
							}else{
								sql ="select * from accountSupplement where type like'%"+type+"%'  and userId like'%"+userId+"%' order by id desc";
								}
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql);
						double[] sum = {0};
						while(rs.next()){
							AccountSupplement as = new AccountSupplement();
							as.setId(rs.getLong("id"));
							as.setUid(rs.getInt("uid"));
							as.setAmount(rs.getDouble("amount"));
							as.setEntryTime(rs.getTimestamp("entryTime"));
							as.setPayType(rs.getInt("payType"));
							as.setSummary(rs.getString("summary"));
							as.setType(rs.getInt("type"));
							as.setUserId(rs.getString("userId"));
							as.setUserName(rs.getString("userName"));
							as.setAdmin(rs.getString("admin"));
							sum[0]=ArithUtil.add(sum[0],rs.getDouble("amount"));
							result.add(as);
						}
					String[][] content = new String[result.size()+2][17];
					content[0][0]="序号";
					content[0][1]="交易日期";
					content[0][2]="账户类型";
					content[0][3]="补扣类型";
					content[0][4]="金额";
					content[0][5]="会员编号";
					content[0][6]="会员名称";
					content[0][7]="摘要";
					for(int i=0;i<result.size();i++){
						content[i+1][0]  = String.valueOf(i+1);
						content[i+1][1]  = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMdd);
						String typestr="";
						if(result.get(i).getType()==1)typestr= "现金账户"; 
						else typestr="-";
						String typestr1="";
						  if(result.get(i).getPayType()==1)typestr1="补增";
						  else if(result.get(i).getPayType()==2)typestr1="补扣";
						content[i+1][2]  = StringUtil.notNull(typestr);
						content[i+1][3]  =  StringUtil.notNull(typestr1);
						content[i+1][4]  =  StringUtil.decimalFormat(result.get(i).getAmount());
						content[i+1][5]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+1][6]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+1][7]  = StringUtil.notNull(result.get(i).getSummary());
						sum[0] = ArithUtil.add(sum[0], result.get(i).getAmount());
					}
					if(result.size()>0){
						content[result.size()+1][0] = "合计";
						content[result.size()+1][1] = "-";
						content[result.size()+1][2] = "-";
						content[result.size()+1][3] = "-";
						content[result.size()+1][4] = StringUtil.decimalFormat((double) sum[0]);
						content[result.size()+1][5] = "-";
						content[result.size()+1][6] = "-";
						content[result.size()+1][7] ="-";
						content[result.size()+1][8] = "-";
						
					}
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("补扣明细");  
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

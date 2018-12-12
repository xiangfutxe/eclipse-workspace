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
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.BankAccount;
import com.pojo.Branch;
import com.pojo.ChargeApply;
import com.pojo.User;
import com.pojo.WithDrew;
import com.service.BankAccountService;
import com.service.CustomService;
import com.service.IBankAccountService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class ChargeApplyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	IBankAccountService bs = new BankAccountService();
	
	ICustomService cs = new CustomService();

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
		
		}else if(method.equals("emoney_quick_add")){
			emoney_quick_add(request,response);
			
		}else if(method.equals("emoney_quick_save")){
			try {
				emoney_quick_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}else if(method.equals("reback")){
	try {
		reback(request,response);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}else if(method.equals("admin_list")){
	admin_chargeApply_list(request,response);
	
}else if(method.equals("exportExcel")){
	exportExcel(request,response);
}
}
	
	public void emoney_quick_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
			String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[6][0].equals("1")||admin.getState().equals("1")){
			int token = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("emoney_quick_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("emoney_quick_message.jsp");
			dispatcher.forward(request, response);
		}
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("emoney_quick_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void emoney_quick_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		DBConn db = new DBConn();
		try {
			
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String userId =StringUtil.notNull(request.getParameter("userId"));
				String amount =StringUtil.notNull(request.getParameter("amount"));
				String remark =StringUtil.notNull(request.getParameter("remark"));
				String sql = "select * from branch where code='"+userId+"' for update";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					Branch branch = new Branch();
					branch.setId(rs.getInt("id"));
					branch.setEmoney(rs.getDouble("emoney"));
					branch.setCode(rs.getString("code"));
					branch.setName(rs.getString("name"));
					if(!amount.equals("")){
						double emoney = Double.valueOf(amount);
						double balance = ArithUtil.add(branch.getEmoney(),emoney);
						if(balance>0){
							Timestamp date1 = new Timestamp(new Date().getTime());
							java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
							
							String sql1 = cs.saveChargeApply(conn,branch,admin.getAdminName(),"","","",emoney,remark,1,2,date,date);
							
							String sql2 = cs.saveEmoneyDetail(conn,branch,emoney,balance,1,Constants.MONEY_DETAIL_TYPE_KEY_2,Constants.MONEY_DETAIL_TYPE_KEY_2,date);
					
							String sql3 = "update branch set emoney='"+balance+"' where id='"+branch.getId()+"'";
							
							stmt = conn.createStatement();
							stmt.addBatch(sql1);
							stmt.addBatch(sql2);
							stmt.addBatch(sql3);
						 	stmt.executeBatch();
							cs.insetAdminLog(conn, admin.getAdminName(), "账户充值成功，充值用户"+branch.getCode()+"("+branch.getName()+")，充值金额为"+StringUtil.decimalFormat(emoney)+"元。", date);
						 	request.setAttribute("message", "账户充值成功，充值用户"+branch.getCode()+"("+branch.getName()+")，充值金额为"+StringUtil.decimalFormat(emoney)+"元。");
				
					}
					else{
					request.setAttribute("message", "账户余额不足，请重试！");
					}
				}else{
					request.setAttribute("message", "未找到相应编号的会员信息，请重新输入！");
				}
					
					}
					else{
					request.setAttribute("message", "输入金额有误，请重新输入！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在管理员列表中查看是否保存成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("emoney_quick_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_chargeApply_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String typeKey = StringUtil.notNull(request.getParameter("typeKey"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(db.createConn()){
				String sql = "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						 sql ="select * from chargeApply where applyTime>='"+startTime+"' and applyTime<='"+endTime+"' and type like '%"+typeKey+"%'  and userId like '%"+userId+"%' and state like '%"+state+"%' order by applyTime desc";
					}
				else{
							 sql ="select * from chargeApply where type like '%"+typeKey+"%' and userId like '%"+userId+"%' and state like '%"+state+"%' order by applyTime desc";
					}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0};
				while(rs.next()){
					ChargeApply ChargeApply = new ChargeApply();
		    		ChargeApply.setId(rs.getInt("id"));
			    		ChargeApply.setAmount(rs.getDouble("amount"));
			    		ChargeApply.setApplyId(rs.getString("applyId"));
			    		ChargeApply.setUserId(rs.getString("userId"));
			    		ChargeApply.setUserName(rs.getString("userName"));
			    		ChargeApply.setApplyTime(rs.getTimestamp("applyTime"));
			    		ChargeApply.setRemark(rs.getString("remark"));
			    		ChargeApply.setType(rs.getInt("type"));
			    		ChargeApply.setState(rs.getInt("state"));
			    		ChargeApply.setAccountId(rs.getString("accountId"));
			    		ChargeApply.setAccountName(rs.getString("accountName"));
			    		ChargeApply.setBankName(rs.getString("bankName"));
			    		ChargeApply.setAdmin(rs.getString("adminByReviewerId"));
			    		ChargeApply.setReviewTime(rs.getTimestamp("reviewTime"));
			    		sum[0] = ArithUtil.add(sum[0] ,rs.getDouble("amount"));
			    		result.add(ChargeApply);
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
				request.setAttribute("typeKey",typeKey);
				request.setAttribute("userId",userId);
				request.setAttribute("state",state);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("emoney_apply.jsp");
			dispatcher.forward(request, response);
		}
	}

protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
    response.setHeader("Connection", "close");  
    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
	Timestamp date1 = new Timestamp(new Date().getTime());
	java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
	 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
	 
	 String filename = "充值申请"+str+".xls";  
	    filename = cs.encodeFileName(request, filename);  
	    response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
    DBConn db = new DBConn();
	List<ChargeApply>  result = new ArrayList<ChargeApply> ();
    String userId = StringUtil.notNull(request.getParameter("userId"));
	String state = StringUtil.notNull(request.getParameter("state"));
	String typeKey = StringUtil.notNull(request.getParameter("typeKey"));
	String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
	try {
		if(db.createConn()){
			String sql = "";
			if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
				String startTime = startTimeStr+" 00:00:00";
				String  endTime = endTimeStr+" 23:59:59";
					 sql ="select * from chargeApply where applyTime>='"+startTime+"' and applyTime<='"+endTime+"' and type like '%"+typeKey+"%'  and userId like '%"+userId+"%' and state like '%"+state+"%' order by applyTime desc";
				}
			else{
						 sql ="select * from chargeApply where type like '%"+typeKey+"%' and userId like '%"+userId+"%' and state like '%"+state+"%' order by applyTime desc";
				}
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql);
			double[] sum = {0};
			while(rs.next()){
				ChargeApply ChargeApply = new ChargeApply();
	    		ChargeApply.setId(rs.getInt("id"));
		    		ChargeApply.setAmount(rs.getDouble("amount"));
		    		ChargeApply.setApplyId(rs.getString("applyId"));
		    		ChargeApply.setUserId(rs.getString("userId"));
		    		ChargeApply.setUserName(rs.getString("userName"));
		    		ChargeApply.setApplyTime(rs.getTimestamp("applyTime"));
		    		ChargeApply.setRemark(rs.getString("remark"));
		    		ChargeApply.setType(rs.getInt("type"));
		    		ChargeApply.setState(rs.getInt("state"));
		    		ChargeApply.setAccountId(rs.getString("accountId"));
		    		ChargeApply.setAccountName(rs.getString("accountName"));
		    		ChargeApply.setBankName(rs.getString("bankName"));
		    		ChargeApply.setAdmin(rs.getString("adminByReviewerId"));
		    		ChargeApply.setReviewTime(rs.getTimestamp("reviewTime"));
		    		sum[0] = ArithUtil.add(sum[0] ,rs.getDouble("amount"));
		    		result.add(ChargeApply);
			}
			String[][] content = new String[result.size()+1][13];
			content[0][0]="序号";
			content[0][1]="申请编号";
			content[0][2]="会员编号";
			content[0][3]="会员姓名";
			content[0][4]="充值金额";
			content[0][5]="充值类型";
			content[0][6]="收款账户信息";
			content[0][7]="申请时间";
			content[0][8]="审批人";
			content[0][9]="审核时间";
			content[0][10]="当前状态";
			content[0][11]="备注";
			for(int i=0;i<result.size();i++){
				content[i+1][0]  = String.valueOf(i+1);
				content[i+1][1]  = StringUtil.notNull(result.get(i).getApplyId());
				content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
				content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
				content[i+1][4]  = StringUtil.decimalFormat(result.get(i).getAmount());
				String typestr="";
				if(result.get(i).getType()==1) typestr="现金充值";
				else typestr="汇款充值";
				content[i+1][5]  =  StringUtil.notNull(typestr);
				content[i+1][6]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName());
				content[i+1][7] = StringUtil.parseToString(result.get(i).getApplyTime(), DateUtil.yyyyMMddHHmmss);
				content[i+1][8] =StringUtil.notNull(result.get(i).getAdmin());
				content[i+1][9] = StringUtil.parseToString(result.get(i).getApplyTime(), DateUtil.yyyyMMddHHmmss);
				String stateStr ="";
				if(result.get(i).getState()==0) stateStr = "已删除";
				else if(result.get(i).getState()==1) stateStr = "待审批";
				else if(result.get(i).getState()==2) stateStr = "审批通过";
				else if(result.get(i).getState()==3) stateStr = "审批不通过";
				content[i+1][10] = stateStr;
				content[i+1][11] = StringUtil.notNull(result.get(i).getRemark());
			}
			 HSSFWorkbook wb = new HSSFWorkbook();  
			 HSSFSheet sheet = wb.createSheet("充值申请");  
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
			request.setAttribute("message", "数据库连接已断开！");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
	}finally{
		db.close();
	}
}

public void reback(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
	Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
	String id = (String)request.getParameter("id");
	DBConn db = new DBConn();
	try {
		
		if(admin!=null){
		if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			String sql = "select * from chargeApply where id='"+id+"' for update";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				ChargeApply chargeApply = new ChargeApply();
				chargeApply.setId(rs.getInt("id"));
				chargeApply.setAmount(rs.getDouble("amount"));
				chargeApply.setUserId(rs.getString("userId"));
				chargeApply.setUserName(rs.getString("userName"));
				String sql1 = "select * from branch where code='"+chargeApply.getUserId()+"' for update";
				stmt.close();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql1);
				if(rs.next()){
					Branch branch = new Branch();
					branch.setId(rs.getInt("id"));
					branch.setCode(rs.getString("code"));
					branch.setName(rs.getString("name"));
					double emoney = rs.getDouble("emoney");
					double balance = ArithUtil.sub(emoney,chargeApply.getAmount());
					if(balance>=0){
						Timestamp date1 = new Timestamp(new Date().getTime());
						java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						String sql2 = "update chargeApply set state = '0' where id='"+chargeApply.getId()+"'";
						
						String sql3 = cs.saveEmoneyDetail(conn,branch,emoney,balance,2,Constants.MONEY_DETAIL_TYPE_KEY_33,Constants.MONEY_DETAIL_TYPE_KEY_33,date);
				
						String sql4 = "update branch set emoney='"+balance+"' where id='"+branch.getId()+"'";
						stmt = conn.createStatement();
						stmt.addBatch(sql2);
						stmt.addBatch(sql3);
						stmt.addBatch(sql4);
					 	stmt.executeBatch();
						cs.insetAdminLog(conn, admin.getAdminName(), "账户充值撤回成功，撤回用户"+branch.getCode()+"("+branch.getName()+")，充值金额为"+StringUtil.decimalFormat(chargeApply.getAmount())+"元。", date);
					 	request.setAttribute("message", "账户充值撤回成功，撤回用户"+branch.getCode()+"("+branch.getName()+")，充值金额为"+StringUtil.decimalFormat(chargeApply.getAmount())+"元。");
			
				}
				else{
				request.setAttribute("message", "账户余额不足，请重试！");
				}
			}else{
				request.setAttribute("message", "未找到相应的会员信息，请重新输入！");
			}
				}
				else{
				request.setAttribute("message", "输入金额有误，请重新输入！");
			}
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
		}else {
			request.setAttribute("message", "数据库连接已断开！");
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("emoney_apply_message.jsp");
		dispatcher.forward(request, response);
	}
}

}

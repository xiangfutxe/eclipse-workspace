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
import com.pojo.Admin;
import com.pojo.Product;
import com.pojo.PurchaseDetail;
import com.pojo.PurchaseApply;
import com.pojo.ShortMessage;
import com.pojo.Supplier;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;

public class PurchaseApplyServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	ICustomService  cs = new CustomService();
	
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
		}else if(method.equals("admin_list")){
			admin_list(request,response);
		}else if(method.equals("admin_detail")){
			admin_detail(request,response);
		}else if(method.equals("admin_apply_list")){
			admin_apply_list(request,response);
		}else if(method.equals("admin_apply_detail")){
			admin_apply_detail(request,response);
		}else if(method.equals("admin_apply_add")){
			admin_apply_add(request,response);
		}else if(method.equals("admin_apply_save")){
			try {
				admin_apply_save(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("admin_apply_pro_add")){
			admin_apply_pro_add(request,response);
		}else if(method.equals("admin_apply_pro_cfm")){
			admin_apply_pro_cfm(request,response);
		}else if(method.equals("admin_apply_del")){
			admin_apply_del(request,response);
		}else if(method.equals("admin_apply_pro_del")){
			admin_apply_pro_del(request,response);
		}else if(method.equals("admin_review_list")){
			admin_review_list(request,response);
		}else if(method.equals("admin_review_detail")){
			admin_review_detail(request,response);
		}else if(method.equals("admin_review_yes")){
			admin_review_yes(request,response);
		}else if(method.equals("admin_review_no_add")){
			admin_review_no_add(request,response);
		}else if(method.equals("admin_review_no")){
			admin_review_no(request,response);
		}else if(method.equals("admin_history_search")){
			admin_history_search(request,response);
		}else if(method.equals("admin_purchase_product_detail")){
			admin_purchase_product_detail(request,response);
		}else if(method.equals("exportExcel_history_search")){
			exportExcel_history_search(request,response);
		}
		
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String state = StringUtil.notNull(request.getParameter("state"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from purchase_apply where  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from purchase_apply where  state like'%"+state+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					PurchaseApply pa = new PurchaseApply();
					pa.setId(rs.getInt("id"));
					pa.setApplyId(rs.getString("apply_id"));
					pa.setSupplierId(rs.getInt("supplier_id"));
					pa.setSupplierCode(rs.getString("supplier_code"));
					pa.setSupplierName(rs.getString("supplier_name"));
					pa.setEntryTime(rs.getTimestamp("entry_time"));
					pa.setReviewTime(rs.getTimestamp("review_time"));
					pa.setOperatorId(rs.getString("operator_id"));
					pa.setReviewId(rs.getString("review_id"));
					pa.setState(rs.getInt("state"));
					result.add(pa);
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
				request.setAttribute("state", state);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_list.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_apply_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String state = StringUtil.notNull(request.getParameter("state"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from purchase_apply where  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from purchase_apply where  state like'%"+state+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					PurchaseApply pa = new PurchaseApply();
					pa.setId(rs.getInt("id"));
					pa.setApplyId(rs.getString("apply_id"));
					pa.setSupplierId(rs.getInt("supplier_id"));
					pa.setSupplierCode(rs.getString("supplier_code"));
					pa.setSupplierName(rs.getString("supplier_name"));
					pa.setEntryTime(rs.getTimestamp("entry_time"));
					pa.setReviewTime(rs.getTimestamp("review_time"));
					pa.setOperatorId(rs.getString("operator_id"));
					pa.setReviewId(rs.getString("review_id"));
					pa.setState(rs.getInt("state"));
					result.add(pa);
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
				request.setAttribute("state", state);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_list.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_review_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from purchase_apply where  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and state ='2'  order by id desc";
					
				}else{
					sqls ="select * from purchase_apply where  state ='2' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					PurchaseApply pa = new PurchaseApply();
					pa.setId(rs.getInt("id"));
					pa.setApplyId(rs.getString("apply_id"));
					pa.setSupplierId(rs.getInt("supplier_id"));
					pa.setSupplierCode(rs.getString("supplier_code"));
					pa.setSupplierName(rs.getString("supplier_name"));
					pa.setEntryTime(rs.getTimestamp("entry_time"));
					pa.setReviewTime(rs.getTimestamp("review_time"));
					pa.setOperatorId(rs.getString("operator_id"));
					pa.setReviewId(rs.getString("review_id"));
					pa.setState(rs.getInt("state"));
					result.add(pa);
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
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_list.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_apply_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message = "";
		try{
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
		
		if(db.createConn()){
			int token = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token));
		String sql ="select * from supplier where  state='1' order by id asc";
		stmt= db.getStmtread();
		rs =stmt.executeQuery(sql);
		while(rs.next()){
			Supplier pt = new Supplier();
			pt.setId(rs.getInt("id"));
			pt.setCode(rs.getString("code"));
			pt.setName(rs.getString("name"));
			coll.add(pt);
		}
		request.setAttribute("coll", coll);

		}else{
			request.setAttribute("message", "数据库连接不通过，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void admin_apply_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
	String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String supplier = StringUtil.notNull(request.getParameter("supplier"));
					
							String sql = "select * from supplier where id ='"+supplier+"'";
							stmt = conn.createStatement();
							rs =stmt.executeQuery(sql);
							if(rs.next()){
								int id = rs.getInt("id");
								String code= rs.getString("code");
								String name = rs.getString("name");
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						String  applyId = cs.createInventoryApplyId(date);
						String sql1= "insert into purchase_apply(apply_id,operator_id,supplier_id,supplier_code,supplier_name,state,entry_time) values('"+applyId+"','"+
						admin.getAdminName()+"','"+id+"','"+code+"','"+name+"','1',+'"+date+"')";
						 stmt = conn.createStatement();
						 stmt.executeUpdate(sql1);
						message= "采购申请单已生成，申请编号为："+applyId+"，请进入详情添加入库商品。";
						cs.insetAdminLog(conn, admin.getAdminName(),  message, date);
					}else message="未查询到相应的供应商信息！";
					
			}else {
				message="数据库连接已断开！";
			}
			 conn.commit();
			 conn.setAutoCommit(autoCommit);	 
				}else{
				message="您没有该操作权限，如有需要请与系统管理员联系！";
			}
				}else{
					message= "请勿重复提交数据，请在仓库列表中查看是否保存成功！";
			}
			}else{
				message="管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				PurchaseDetail total_ap = new PurchaseDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from purchase_apply where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					PurchaseApply ap = new PurchaseApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("apply_id"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					ap.setOperatorTime(rs.getTimestamp("operator_time"));
					ap.setReviewTime(rs.getTimestamp("review_time"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from purchase_apply_detail where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					PurchaseDetail pd = new PurchaseDetail();
					pd.setId(rs.getInt("id"));
					pd.setApplyId(rs.getString("apply_id"));
					pd.setProductId(rs.getString("product_id"));
					pd.setProductName(rs.getString("product_name"));
					pd.setSpecification(rs.getString("specification"));
					pd.setUnit(rs.getString("unit"));
					pd.setPrice(rs.getDouble("price"));
					pd.setRemark(rs.getString("remark"));
					pd.setNum(rs.getDouble("num"));
					pd.setTotalPrice(ArithUtil.mul(pd.getPrice(), pd.getNum()));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
					coll.add(pd);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "采购申请单信息获取失败！";
				}
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_apply_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				PurchaseDetail total_ap = new PurchaseDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from purchase_apply where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					PurchaseApply ap = new PurchaseApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("apply_id"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					ap.setOperatorTime(rs.getTimestamp("operator_time"));
					ap.setReviewTime(rs.getTimestamp("review_time"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from purchase_apply_detail where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					PurchaseDetail pd = new PurchaseDetail();
					pd.setId(rs.getInt("id"));
					pd.setApplyId(rs.getString("apply_id"));
					pd.setProductId(rs.getString("product_id"));
					pd.setProductName(rs.getString("product_name"));
					pd.setSpecification(rs.getString("specification"));
					pd.setUnit(rs.getString("unit"));
					pd.setPrice(rs.getDouble("price"));
					pd.setRemark(rs.getString("remark"));
					pd.setNum(rs.getDouble("num"));
					pd.setTotalPrice(ArithUtil.mul(pd.getPrice(), pd.getNum()));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
					coll.add(pd);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "采购申请单信息获取失败！";
				}
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_review_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				PurchaseDetail total_ap = new PurchaseDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from purchase_apply where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					PurchaseApply ap = new PurchaseApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("apply_id"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					ap.setOperatorTime(rs.getTimestamp("operator_time"));
					ap.setReviewTime(rs.getTimestamp("review_time"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from purchase_apply_detail where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					PurchaseDetail pd = new PurchaseDetail();
					pd.setId(rs.getInt("id"));
					pd.setApplyId(rs.getString("apply_id"));
					pd.setProductId(rs.getString("product_id"));
					pd.setProductName(rs.getString("product_name"));
					pd.setSpecification(rs.getString("specification"));
					pd.setUnit(rs.getString("unit"));
					pd.setPrice(rs.getDouble("price"));
					pd.setRemark(rs.getString("remark"));
					pd.setNum(rs.getDouble("num"));
					pd.setTotalPrice(ArithUtil.mul(pd.getPrice(), pd.getNum()));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
					coll.add(pd);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "采购申请单信息获取失败！";
				}
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_apply_pro_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("applyId"));
				String productId = StringUtil.notNull(request.getParameter("productId"));
				String num = StringUtil.notNull(request.getParameter("num"));
				String price = StringUtil.notNull(request.getParameter("price"));
				String remark = StringUtil.notNull(request.getParameter("remark"));

				Timestamp date = new Timestamp(new Date().getTime());
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				PurchaseDetail total_ap = new PurchaseDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				boolean b = true;
				String sql ="select * from purchase_apply where apply_id='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					PurchaseApply ap = new PurchaseApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("apply_id"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					ap.setOperatorTime(rs.getTimestamp("operator_time"));
					ap.setReviewTime(rs.getTimestamp("review_time"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from purchase_apply_detail where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					PurchaseDetail pd = new PurchaseDetail();
					pd.setId(rs.getInt("id"));
					pd.setApplyId(rs.getString("apply_id"));
					pd.setProductId(rs.getString("product_id"));
					pd.setProductName(rs.getString("product_name"));
					pd.setSpecification(rs.getString("specification"));
					pd.setUnit(rs.getString("unit"));
					pd.setPrice(rs.getDouble("price"));
					pd.setRemark(rs.getString("remark"));
					pd.setNum(rs.getDouble("num"));
					pd.setTotalPrice(ArithUtil.mul(pd.getPrice(), pd.getNum()));
					total_ap.setNum(total_ap.getNum()+rs.getDouble("num"));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
					coll.add(pd);
					if(pd.getProductId().equals(productId)) b = false;
				}
				if(b){
							String sql2 ="select * from product where productId='"+productId+"' order by id asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								int pid = rs.getInt("id");
								String productName= rs.getString("productName");
								String productType= rs.getString("productType");
								String specification = rs.getString("specification");
								String unit = rs.getString("unit");
								String sql3 = "insert into purchase_apply_detail(apply_id,p_id,product_id,product_name,product_type,specification,unit,num,price,remark,entry_time)"
										+ "values('"+applyId+"','"+pid+"','"+productId+"','"+productName+"','"+productType+"','"+specification+"','"+unit+"','"
										+num+"','"+price+"','"+remark+"','"+date+"')";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql3);
								double num1 = Double.valueOf(num);
								double price1 = Double.valueOf(price);
								PurchaseDetail pd = new PurchaseDetail();
								pd.setId(rs.getInt("id"));
								pd.setApplyId(productName);
								pd.setProductId(productId);
								pd.setProductName(productName);
								pd.setSpecification(specification);
								pd.setUnit(unit);
								pd.setPrice(price1);
								pd.setNum(num1);
								pd.setRemark(remark);
								pd.setTotalPrice(ArithUtil.mul(num1,price1));
								total_ap.setNum(ArithUtil.add(total_ap.getNum(),num1));
								total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),price1));
								total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
								coll.add(pd);
								message="产品信息添加成功！";
							}else{ 
								message="产品信息获取失败！";
							}
				}else{ 
					message="该产品已经存在，请勿重复提交产品！";
				}
				request.setAttribute("admin_apply", ap);
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				
				}else {
					message= "入库申请单信息获取不通过！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_apply_pro_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				PurchaseDetail total_ap = new PurchaseDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from purchase_apply where apply_id='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					PurchaseApply ap = new PurchaseApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("apply_id"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					ap.setOperatorTime(rs.getTimestamp("operator_time"));
					ap.setReviewTime(rs.getTimestamp("review_time"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					
					String sql1 ="select * from purchase_apply_detail where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					PurchaseDetail pd = new PurchaseDetail();
					pd.setId(rs.getInt("id"));
					pd.setApplyId(rs.getString("apply_id"));
					pd.setProductId(rs.getString("product_id"));
					pd.setProductName(rs.getString("product_name"));
					pd.setSpecification(rs.getString("specification"));
					pd.setUnit(rs.getString("unit"));
					pd.setPrice(rs.getDouble("price"));
					pd.setRemark(rs.getString("remark"));
					pd.setNum(rs.getDouble("num"));
					pd.setTotalPrice(ArithUtil.mul(pd.getPrice(), pd.getNum()));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
					coll.add(pd);
					}
						if(ap.getState()==1){
							Timestamp date = new Timestamp(new Date().getTime());
								String sql4 = "update purchase_apply set state='2',operator_time='"+date+"' where apply_id='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
								message="采购申请提交审批流程成功！";
								ap.setState(2);
								cs.insetAdminLog(conn, admin.getAdminName(), message, date);
								//start-短信提示
								cs.sendSMS(conn, "采购审批提示");
								//end-短信提示

						}else{ 
							message="申请单状态不能进行此操作！";
						}
						request.setAttribute("admin_apply", ap);
				request.setAttribute("admin_apply", ap);
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				
				}else {
					message= "采购申请单信息获取失败！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_apply_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String sql ="select * from purchase_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
					String sqlu ="update inventoryApply set state='0' where apply_id='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "采购申请删除成功，删除的编号为："+applyId+"。";
					 Timestamp date = new Timestamp(new Date().getTime());
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行删除操作，请确认！";
				}else  message= "未找到需要删除的入库信息，请重试";
				
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void admin_apply_pro_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String id = StringUtil.notNull(request.getParameter("id"));
		Collection coll = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from purchase_apply_detail where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String applyId=rs.getString("apply_id");
					String code = rs.getString("product_id");
					String sqlu ="delete from purchase_apply_detail  where id='"+id+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					 Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), "采购单"+applyId+"中的"+code+"删除成功。", date);
				
					PurchaseDetail total_ap = new PurchaseDetail();
					total_ap.setNum(0);
					total_ap.setPrice(0);
					total_ap.setTotalPrice(0);
					String sql1 ="select * from purchase_apply where apply_id='"+applyId+"' for update";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						PurchaseApply ap = new PurchaseApply();
						ap.setId(rs.getInt("id"));
						ap.setApplyId(rs.getString("apply_id"));
						ap.setSupplierId(rs.getInt("supplier_id"));
						ap.setSupplierCode(rs.getString("supplier_code"));
						ap.setSupplierName(rs.getString("supplier_name"));
						
						ap.setState(rs.getInt("state"));
						ap.setRemark(rs.getString("remark"));
						ap.setEntryTime(rs.getTimestamp("entry_time"));
						ap.setOperatorTime(rs.getTimestamp("operator_time"));
						ap.setReviewTime(rs.getTimestamp("review_time"));
						ap.setEntryTime(rs.getTimestamp("entry_time"));
						request.setAttribute("admin_apply", ap);
						String sql2 ="select * from purchase_apply_detail where apply_id='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						PurchaseDetail pd = new PurchaseDetail();
						pd.setId(rs.getInt("id"));
						pd.setApplyId(rs.getString("apply_id"));
						pd.setProductId(rs.getString("product_id"));
						pd.setProductName(rs.getString("product_name"));
						pd.setSpecification(rs.getString("specification"));
						pd.setUnit(rs.getString("unit"));
						pd.setPrice(rs.getDouble("price"));
						pd.setRemark(rs.getString("remark"));
						pd.setNum(rs.getDouble("num"));
						pd.setTotalPrice(ArithUtil.mul(pd.getPrice(), pd.getNum()));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), pd.getTotalPrice()));
						coll.add(pd);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "采购单获取不通过。";
				}
				}else {
					message= "采购信息获取失败。";
				}
			}else {
				message= "数据库连接已断开。";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系。";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆。";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_apply_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from purchase_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date = new Timestamp(new Date().getTime());
					String sqlu ="update purchase_apply set state='3',review_id='"+admin.getAdminName()+"',review_time='"+date+"' where apply_id='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "采购申请审批成功，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的采购信息，请重试";
				
			}else {
				message= "数据库连接已断开！";
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_review_no_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
					
			if(db.createConn()){
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from purchase_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					PurchaseApply ia = new PurchaseApply();
					ia.setId(rs.getInt("id"));
					ia.setApplyId(rs.getString("apply_id"));
					request.setAttribute("sys_ivy", ia);
				}else  message= "未找到需要审核的采购信息，请重试";
				
			}else {
				message= "数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void admin_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){

				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				String remark = StringUtil.notNull(request.getParameter("remark"));

					String sql ="select * from purchase_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					remark = StringUtil.notNull(rs.getString("remark"))+"采购审批备注："+remark;
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update purchase_apply set state='1',remark='"+StringUtil.notNull(remark)+"',review_id='"+admin.getAdminName()+"',review_time='"+date+"' where apply_id='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "采购申请审批不成功，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的采购信息，请重试";
				
			}else {
				message= "数据库连接已断开！";
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
				}else{
					 message= "请勿重复提交数据！";
			}
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void admin_history_search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			DBConn db = new DBConn();
			Collection coll = new ArrayList();
			List  result = new ArrayList();
			String productId = StringUtil.notNull(request.getParameter("productId"));
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
			String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
			String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int pageNo = 1;
			int pageSize = 60;
			String message ="";
			double totalNum =0;
			Product product = new Product();
			try {
				if(admin!=null){
				if(db.createConn()){
					if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
						String startTime = startTimeStr+" 00:00:00";
						String  endTime = endTimeStr+" 23:59:59";
						String sql1 = "select * from product where productId='"+productId+"'";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql1);
						if(rs.next()){
							product.setProductId(rs.getString("productId"));
							product.setProductName(rs.getString("productName"));
							product.setProductType(rs.getString("productType"));
							product.setSpecification(rs.getString("specification"));
							product.setUnit(rs.getString("unit"));
						}else product=null;
						if(product!=null){
					String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='1'  and iay.type='1' and idl.productId='"+productId+"' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						PurchaseApply pa = new PurchaseApply();
						pa.setId(rs.getInt("iay.id"));
						pa.setApplyId(rs.getString("iay.applyId"));
						pa.setSupplierId(rs.getInt("iay.supplier_id"));
						pa.setSupplierCode(rs.getString("iay.supplier_code"));
						pa.setSupplierName(rs.getString("iay.supplier_name"));
						pa.setEntryTime(rs.getTimestamp("iay.entry_time"));
						pa.setReviewTime(rs.getTimestamp("iay.confirm_time"));
						pa.setReviewTime(rs.getTimestamp("iay.review_time"));
						pa.setOperatorId(rs.getString("iay.operator_id"));
						pa.setPrice(rs.getDouble("idl.price"));
						pa.setReviewId(rs.getString("iay.review_id"));
						pa.setDocNum(rs.getString("iay.doc_num"));
						pa.setNum(rs.getDouble("idl.num"));
						pa.setState(rs.getInt("state"));
						product.setTotalNum(ArithUtil.add(product.getTotalNum(), pa.getNum()));
						totalNum =ArithUtil.add(totalNum, pa.getNum());
						result.add(pa);
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
					request.setAttribute("productId", productId);
					request.setAttribute("pa_product", product);
					request.setAttribute("totalNum", totalNum);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
						}else {
							message= "未查询到相应的产品信息！";
						}
					}else {
						message= "日期格式有误！";
					}
				}else {
					message= "数据库连接已断开！";
				}
				
					}else{
						message=  "管理员用户未登陆，请重新登陆！";
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				message=  "数据有误，请重新登陆！";
			}finally{
				db.close();
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_history_list.jsp");
					dispatcher.forward(request, response);
			}
	}
		
		public void admin_purchase_product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			DBConn db = new DBConn();
		
			String productId = StringUtil.notNull(request.getParameter("productId"));
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		
			String message ="";
			Product product = new Product();
			try {
				if(admin!=null){
				if(db.createConn()){
					if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
						String startTime = startTimeStr+" 00:00:00";
						String  endTime = endTimeStr+" 23:59:59";
						String sql1 = "select * from product where productId='"+productId+"'";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql1);
						
						if(rs.next()){
							product.setProductId(rs.getString("productId"));
							product.setProductName(rs.getString("productName"));
							product.setProductType(rs.getString("productType"));
							product.setSpecification(rs.getString("specification"));
							product.setUnit(rs.getString("unit"));
						}else product=null;
						if(product!=null){
					String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='1'  and iay.type='1' and idl.productId='"+productId+"' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						product.setPrice(ArithUtil.add(product.getPrice(),ArithUtil.mul(rs.getDouble("idl.price"), rs.getDouble("idl.num"))));
						
						product.setTotalNumIn(ArithUtil.add(product.getTotalNumIn(), rs.getDouble("idl.num")));
						product.setSupplier(rs.getString("supplier_name"));
					}
					if(product.getPrice()>0&&product.getTotalNumIn()>0){
						product.setPrice(ArithUtil.div(product.getPrice(), product.getTotalNumIn()));
					}else{
						product.setPrice(0);
					}
					sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='2'  and idl.productId='"+productId+"' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						product.setTotalNumOut(ArithUtil.add(product.getTotalNumOut(), rs.getDouble("idl.num")));
					}
					request.setAttribute("productId", productId);
					request.setAttribute("pa_product", product);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
						}else {
							message= "未查询到相应的产品信息！";
						}
					}else {
						message= "日期格式有误！";
					}
				}else {
					message= "数据库连接已断开！";
				}
				
					}else{
						message=  "管理员用户未登陆，请重新登陆！";
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				message=  "数据有误，请重新登陆！";
			}finally{
				db.close();
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("purchase_product_detail_list.jsp");
					dispatcher.forward(request, response);
			}
	}
	
		protected void exportExcel_history_search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		    response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String productId = StringUtil.notNull(request.getParameter("productId"));

			 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
			 String filename = "历史采购查询汇总表"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		    DBConn db = new DBConn();
			List<PurchaseApply>  result = new ArrayList<PurchaseApply> ();
			Product product =new Product();
			double totalNum = 0;
			try {
				if(db.createConn()){
					conn = db.getConnection();
					if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
						String startTime = startTimeStr+" 00:00:00";
						String  endTime = endTimeStr+" 23:59:59";
						String sql1 = "select * from product where productId='"+productId+"'";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql1);
						
						if(rs.next()){
							product.setProductId(rs.getString("productId"));
							product.setProductName(rs.getString("productName"));
							product.setProductType(rs.getString("productType"));
							product.setSpecification(rs.getString("specification"));
							product.setUnit(rs.getString("unit"));
						}else product=null;
						if(product!=null){
					String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='1'  and iay.type='1' and idl.productId='"+productId+"' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						PurchaseApply pa = new PurchaseApply();
						pa.setId(rs.getInt("iay.id"));
						pa.setApplyId(rs.getString("iay.applyId"));
						pa.setSupplierId(rs.getInt("iay.supplier_id"));
						pa.setSupplierCode(rs.getString("iay.supplier_code"));
						pa.setSupplierName(rs.getString("iay.supplier_name"));
						pa.setEntryTime(rs.getTimestamp("iay.entry_time"));
						pa.setReviewTime(rs.getTimestamp("iay.confirm_time"));
						pa.setReviewTime(rs.getTimestamp("iay.review_time"));
						pa.setOperatorId(rs.getString("iay.operator_id"));
						pa.setPrice(rs.getDouble("idl.price"));
						pa.setReviewId(rs.getString("iay.review_id"));
						pa.setDocNum(rs.getString("iay.doc_num"));
						pa.setNum(rs.getDouble("idl.num"));
						pa.setState(rs.getInt("state"));
						product.setTotalNum(ArithUtil.add(product.getTotalNum(), pa.getNum()));
						totalNum =ArithUtil.add(totalNum, pa.getNum());
						result.add(pa);
					}
					
					String[][] content = new String[result.size()+4][8];
					
					content[0][0]="编号";
					content[0][1]=StringUtil.notNull(product.getProductId());
					content[0][2]="货品名称";
					content[0][3]=StringUtil.notNull(product.getProductId());
					content[0][4]="规格";
					content[0][5]=StringUtil.notNull(product.getSpecification());
					content[1][0]="单位";
					content[1][1]=StringUtil.notNull(product.getUnit());
					content[1][2]="采购总数";
					content[1][3]=StringUtil.decimalFormat(product.getTotalNum());
					content[1][4]="时间段";
					content[1][5]=StringUtil.notNull(startTimeStr)+"至"+StringUtil.notNull(endTimeStr);

					content[2][0]="序号";
					content[2][1]="订单编号";
					content[2][2]="单据号";
					content[2][3]="采购单价";
					content[2][4]="采购数量";
					content[2][5]="供应商";
					content[2][6]="申请日期";
					content[2][7]="审批日期";
				
					for(int i=0;i<result.size();i++){
						content[i+3][0]  = StringUtil.notNull(String.valueOf(i+1));
						content[i+3][1]  = StringUtil.notNull(result.get(i).getApplyId());
						content[i+3][2]  = StringUtil.notNull(result.get(i).getDocNum());
						content[i+3][3]  = StringUtil.decimalFormat(result.get(i).getPrice());
						content[i+3][4]  = StringUtil.decimalFormat(result.get(i).getNum());
						content[i+3][5]  = StringUtil.notNull(result.get(i).getSupplierName());
						content[i+3][6]  = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
						content[i+3][7]  = StringUtil.parseToString(result.get(i).getReviewTime(), DateUtil.yyyyMMddHHmmss);

					}
					
					 HSSFWorkbook wb = new HSSFWorkbook();  
					 HSSFSheet sheet = wb.createSheet("历史采购查询（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
						request.setAttribute("message", "产品信息获取失败！");
					}
				}else {
					request.setAttribute("message", "日期格式有误！");
				}
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
	
	
}

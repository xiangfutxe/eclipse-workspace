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
import com.pojo.Admin;
import com.pojo.Branch;
import com.pojo.ChargeApply;
import com.pojo.Order;
import com.pojo.OrderDetail;
import com.pojo.Product;
import com.pojo.Settle;
import com.pojo.User;
import com.pojo.WReward;
import com.pojo.WSettle;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private int pageNo = 1;
	
	private int pageSize = 10;
	
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
		
		}else if(method.equals("report_performance_summary")){
			report_performance_summary(request,response);
			
		}else if(method.equals("report_branch_pro_summary")){
			report_branch_pro_summary(request,response);
			
		}else if(method.equals("report_order_summary")){
			report_order_summary(request,response);
			
		}else if(method.equals("exportExcel_order_summary")){
			exportExcel_order_summary(request,response);
			
		}else if(method.equals("report_apply_in_summary")){
			report_apply_in_summary(request,response);
			
		}else if(method.equals("report_apply_out_summary")){
			report_apply_out_summary(request,response);
			
		}else if(method.equals("report_apply_check_summary")){
			report_apply_check_summary(request,response);
			
		}else if(method.equals("report_product_detail_summary")){
			report_product_detail_summary(request,response);
			
		}else if(method.equals("exportExcel_apply_out_summary")){
			exportExcel_apply_out_summary(request,response);
			
		}else if(method.equals("exportExcel_apply_in_summary")){
			exportExcel_apply_in_summary(request,response);
			
		}else if(method.equals("report_branch_detail_summary")){
			report_branch_detail_summary(request,response);
			
		}else if(method.equals("report_branch_price_summary")){
			report_branch_price_summary(request,response);
			
		}else if(method.equals("exportExcel_report_branch_detail_summary")){
			exportExcel_report_branch_detail_summary(request,response);
			
		}else if(method.equals("exportExcel_report_branch_price_summary")){
			exportExcel_report_branch_price_summary(request,response);
			
		}else if(method.equals("report_order_delivery_summary")){
			report_order_delivery_summary(request,response);
			
		}
}
	
	
	protected void report_performance_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message="";
		try {
			if(branch!=null){
			if(db.createConn()){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				
				double total_price=0;
				int total_num=0;
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
					List<Product> result = new ArrayList<Product>();
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				String sql1 ="select * from orders where uId='"+branch.getId()+"' and  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and state>'0'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					int bid = rs.getInt("uId");	
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setOrderId(orderId);
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					Statement stmt1 =db.getStmtread();
					ResultSet rs1 =stmt1.executeQuery(sqld);
					while(rs1.next()){
						int pid = rs1.getInt("pid");
						if(pid>0){
							if(plist[pid]!=null){
								plist[pid].setTotalNum(plist[pid].getTotalNum()+rs1.getInt("num"));
								plist[pid].setTotalPrice(ArithUtil.add(plist[pid].getTotalPrice(), rs1.getDouble("price")));
							total_price = ArithUtil.add(total_price, rs1.getDouble("price"));
							total_num= total_num+rs1.getInt("num");
							plist[pid].setTag(1);
							}
						}
					}
					if(rs1!=null) rs1.close();
					if(stmt1!=null) stmt1.close();
				}
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTag()==1){
							result.add(plist[i]);
						}
					}
				}
			
					request.setAttribute("sys_plist", result);
					request.setAttribute("total_price", total_price);
					request.setAttribute("total_num", total_num);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
					
				}else {
					message = "日期格式有误！";
				}
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_performance_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void report_branch_pro_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String code = StringUtil.notNull(request.getParameter("code"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 10;
				double total_price=0;
				double total_num=0;
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
					List<Product> result = new ArrayList<Product>();
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from branch where state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch branch = new Branch();
					branch.setId(rs.getInt("id"));
					branch.setCode(rs.getString("code"));
					branch.setName(rs.getString("name"));
					branch.setLinkman(rs.getString("linkman"));
					branch.setState(rs.getInt("state"));
					branch.setTag(rs.getInt("tag"));	
					
				String sql1 ="select * from orders where uId='"+branch.getId()+"' and  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and state>'0'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					int bid = rs.getInt("uId");	
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setOrderId(orderId);
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					Statement stmt1 =db.getStmtread();
					ResultSet rs1 =stmt1.executeQuery(sqld);
					while(rs1.next()){
						int pid = rs1.getInt("pid");
						if(pid>0){
							if(plist[pid]!=null){
								plist[pid].setTotalNum(plist[pid].getTotalNum()+rs1.getDouble("num"));
								plist[pid].setTotalPrice(ArithUtil.add(plist[pid].getTotalPrice(), rs1.getDouble("price")));
							total_price = ArithUtil.add(total_price, rs1.getDouble("price"));
							total_num=ArithUtil.add(total_num,rs1.getDouble("num"));
							plist[pid].setTag(1);
							}
						}
					}
					if(rs1!=null) rs1.close();
					if(stmt1!=null) stmt1.close();
				}
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTag()==1){
							result.add(plist[i]);
						}
					}
				}
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					
					request.setAttribute("pager", pager);
				}
					request.setAttribute("admin_branch", branch);
					request.setAttribute("total_price", total_price);
					request.setAttribute("total_num", total_num);
					request.setAttribute("code", code);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
					}
				}else {
					message = "日期格式有误！";
				}
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_branch_pro_summary_list.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void report_order_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
				double total_price=0;
				double total_num=0;
				double pre_num=0;
				double pre_price=0;
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
					List<Product> result = new ArrayList<Product>();
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from orders as od,orderDetail as odd where od.orderId=odd.orderId and od.state>='1' and od.orderTime>='"+startTime+"' and od.orderTime<='"+endTime+"' order by odd.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				while(rs.next()){
					int id = rs.getInt("odd.pid");
					if(plist[id]==null) plist[id] = new Product();
					plist[id].setTotalNum(ArithUtil.add(plist[id].getTotalNum(), rs.getDouble("odd.num")));
					plist[id].setTotalPrice(ArithUtil.add(plist[id].getTotalPrice(), rs.getDouble("odd.price")));
					total_price = ArithUtil.add(total_price, rs.getDouble("odd.price"));
					total_num = ArithUtil.add(total_num, rs.getDouble("odd.num"));
					
				}
				
				int day = (int) (endTime.getTime()-startTime.getTime())/(24*60*60*1000)+1;
				
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNum()>0){
							plist[i].setPrice(ArithUtil.div(plist[i].getTotalPrice(), plist[i].getTotalNum()));
							plist[i].setPreNum(ArithUtil.div(plist[i].getTotalNum(), day));
							plist[i].setPrePrice(ArithUtil.div(plist[i].getTotalPrice(), day));
							pre_price = ArithUtil.add(plist[i].getPrePrice(), pre_price);
							pre_num = ArithUtil.add(plist[i].getPreNum(), pre_num);
							result.add(plist[i]);
						}
					}
				}
				
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
				}
					request.setAttribute("total_price", total_price);
					request.setAttribute("total_num", total_num);
					request.setAttribute("pre_price", pre_price);
					request.setAttribute("pre_num", pre_num);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
					
				}else {
					message = "日期格式有误！";
				}
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_order_summary_list.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void exportExcel_order_summary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "订单统计"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<Product>  result = new ArrayList<Product> ();
	
		try {
			if(db.createConn()){
				conn = db.getConnection();
			
				double total_price=0;
				double total_num=0;
				double pre_num=0;
				double pre_price=0;
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from orders as od,orderDetail as odd where od.orderId=odd.orderId and od.state>='1' and od.orderTime>='"+startTime+"' and od.orderTime<='"+endTime+"' order by odd.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				while(rs.next()){
					int id = rs.getInt("odd.pid");
					if(plist[id]==null) plist[id] = new Product();
					plist[id].setTotalNum(ArithUtil.add(plist[id].getTotalNum(), rs.getDouble("odd.num")));
					plist[id].setTotalPrice(ArithUtil.add(plist[id].getTotalPrice(), rs.getDouble("odd.price")));
					total_price = ArithUtil.add(total_price, rs.getDouble("odd.price"));
					total_num = ArithUtil.add(total_num, rs.getDouble("odd.num"));
					
				}
				
				int day = (int) (endTime.getTime()-startTime.getTime())/(24*60*60*1000)+1;
				
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNum()>0){
							plist[i].setPrice(ArithUtil.div(plist[i].getTotalPrice(), plist[i].getTotalNum()));
							plist[i].setPreNum(ArithUtil.div(plist[i].getTotalNum(), day));
							plist[i].setPrePrice(ArithUtil.div(plist[i].getTotalPrice(), day));
							pre_price = ArithUtil.add(plist[i].getPrePrice(), pre_price);
							pre_num = ArithUtil.add(plist[i].getPreNum(), pre_num);
							result.add(plist[i]);
						}
					}
				}
				
				String[][] content = new String[result.size()+2][10];
				content[0][0]="分类";
				content[0][1]="编号";
				content[0][2]="货品名称";
				content[0][3]="规格";
				content[0][4]="单位";
				content[0][5]="单价";
				content[0][6]="总数";
				content[0][7]="总金额";
				content[0][8]="日均销量";
				content[0][9]="日均销售额";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = StringUtil.notNull(result.get(i).getProductType());
					content[i+1][1]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getUnit());
					content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getTotalNum());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getTotalPrice());
					content[i+1][8] =StringUtil.decimalFormat(result.get(i).getPreNum());;
					content[i+1][9] = StringUtil.decimalFormat(result.get(i).getPrePrice());
				}
				content[result.size()+1][0]  = "合计";
				content[result.size()+1][1]  ="-";
				content[result.size()+1][2]  = "-";
				content[result.size()+1][3]  = "-";
				content[result.size()+1][4]  = "-";
				content[result.size()+1][5]  = "-";
				content[result.size()+1][6]  = StringUtil.decimalFormat(total_num);
				content[result.size()+1][7]  = StringUtil.decimalFormat(total_price);
				content[result.size()+1][8] =StringUtil.decimalFormat(pre_num);;
				content[result.size()+1][9] = StringUtil.decimalFormat(pre_price);
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("订单统计（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
	
	protected synchronized void report_apply_out_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
				
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
					List<Product> result = new ArrayList<Product>();
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setNumIn1(0);
					plist[id].setNumIn2(0);
					plist[id].setNumIn3(0);
					plist[id].setNumIn4(0);
					plist[id].setNumIn5(0);
					plist[id].setNumOut1(0);
					plist[id].setNumOut2(0);
					plist[id].setNumOut3(0);
					plist[id].setNumOut4(0);
					plist[id].setNumOut5(0);
					plist[id].setTotalNumIn(0);
					plist[id].setTotalNumOut(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from orders as od,orderDetail as odd where od.orderId=odd.orderId and od.state>='1' and od.orderTime>='"+startTime+"' and od.orderTime<='"+endTime+"' order by odd.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				while(rs.next()){
					int id = rs.getInt("odd.pid");
					if(plist[id]==null) plist[id] = new Product();
					plist[id].setNumOut1(ArithUtil.add(plist[id].getNumOut1(), rs.getDouble("odd.num")));
					plist[id].setTotalNumOut(ArithUtil.add(plist[id].getTotalNumOut(), rs.getDouble("odd.num")));
					
				}
				
				sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='2' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("idl.pid");
				int type = rs.getInt("iay.type");
				if(plist[id]==null) plist[id] = new Product();
				if(type==1){
					plist[id].setNumOut2(ArithUtil.add(plist[id].getNumOut2(), rs.getDouble("idl.num")));
				}else if(type==2){
					plist[id].setNumOut3(ArithUtil.add(plist[id].getNumOut3(), rs.getDouble("idl.num")));
				}else if(type==3){
					plist[id].setNumOut4(ArithUtil.add(plist[id].getNumOut4(), rs.getDouble("idl.num")));
				}else if(type==4){
					plist[id].setNumOut5(ArithUtil.add(plist[id].getNumOut5(), rs.getDouble("idl.num")));
				}else if(type==5){
					plist[id].setNumOut6(ArithUtil.add(plist[id].getNumOut6(), rs.getDouble("idl.num")));
				}else if(type==6){
					plist[id].setNumOut7(ArithUtil.add(plist[id].getNumOut7(), rs.getDouble("idl.num")));
				}else if(type==7){
					plist[id].setNumOut8(ArithUtil.add(plist[id].getNumOut8(), rs.getDouble("idl.num")));
				}else if(type==8){
					plist[id].setNumOut9(ArithUtil.add(plist[id].getNumOut9(), rs.getDouble("idl.num")));
				}
				plist[id].setTotalNumOut(ArithUtil.add(plist[id].getTotalNumOut(), rs.getDouble("idl.num")));
			}
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNumOut()>0){
							result.add(plist[i]);
						}
					}
				}
				
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
				}
					
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
					
				}else {
					message = "日期格式有误！";
				}
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_apply_out_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void report_apply_in_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
				
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
					List<Product> result = new ArrayList<Product>();
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setNumIn1(0);
					plist[id].setNumIn2(0);
					plist[id].setNumIn3(0);
					plist[id].setNumIn4(0);
					plist[id].setNumIn5(0);
					plist[id].setNumOut1(0);
					plist[id].setNumOut2(0);
					plist[id].setNumOut3(0);
					plist[id].setNumOut4(0);
					plist[id].setNumOut5(0);
					plist[id].setTotalNumIn(0);
					plist[id].setTotalNumOut(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='1' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("idl.pid");
				int type = rs.getInt("iay.type");
				if(plist[id]==null) plist[id] = new Product();
				if(type==1){
					plist[id].setNumIn2(ArithUtil.add(plist[id].getNumIn2(), rs.getDouble("idl.num")));
				}else if(type==2){
					plist[id].setNumIn3(ArithUtil.add(plist[id].getNumIn3(), rs.getDouble("idl.num")));
				}else if(type==3){
					plist[id].setNumIn4(ArithUtil.add(plist[id].getNumIn4(), rs.getDouble("idl.num")));
				}else if(type==4){
					plist[id].setNumIn5(ArithUtil.add(plist[id].getNumIn5(), rs.getDouble("idl.num")));
				}
				plist[id].setTotalNumIn(ArithUtil.add(plist[id].getTotalNumIn(), rs.getDouble("idl.num")));
			}
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNumIn()>0){
							result.add(plist[i]);
						}
					}
				}
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
				}
					
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
					
				}else {
					message = "日期格式有误！";
				}
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_apply_in_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void report_apply_check_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
				
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
			
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
					List<Product> result = new ArrayList<Product>();
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNumIn1(0);
					plist[id].setNumIn2(0);
					plist[id].setNumIn3(0);
					plist[id].setNumIn4(0);
					plist[id].setNumIn5(0);
					plist[id].setNumOut1(0);
					plist[id].setNumOut2(0);
					plist[id].setNumOut3(0);
					plist[id].setNumOut4(0);
					plist[id].setNumOut5(0);
					plist[id].setTotalNumIn(0);
					plist[id].setTotalNumOut(0);
					plist[id].setNum(rs.getDouble("num"));
					plist[id].setTotalNum(rs.getDouble("totalNum"));
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from orders as od,orderDetail as odd where od.orderId=odd.orderId and od.state>='1'  order by odd.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				while(rs.next()){
					int id = rs.getInt("odd.pid");
					if(plist[id]==null) plist[id] = new Product();
					plist[id].setTotalNumOut(ArithUtil.add(plist[id].getTotalNumOut(), rs.getDouble("odd.num")));
					
				}
				
				sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4'  order by iay.id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("idl.pid");
				int type = rs.getInt("iay.type");
				int pay_type = rs.getInt("iay.pay_type");
				if(plist[id]==null) plist[id] = new Product();
				if(pay_type==1){
					plist[id].setTotalNumIn(ArithUtil.add(plist[id].getTotalNumIn(), rs.getDouble("idl.num")));
				}else{
					plist[id].setTotalNumOut(ArithUtil.add(plist[id].getTotalNumOut(), rs.getDouble("idl.num")));
				}
			}
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNumOut()>0||plist[i].getTotalNumIn()>0){
							if(ArithUtil.sub(plist[i].getTotalNumIn(), plist[i].getTotalNumOut())!=plist[i].getNum()){
							plist[i].setPreNum(ArithUtil.sub(plist[i].getNum(),ArithUtil.sub(plist[i].getTotalNumIn(), plist[i].getTotalNumOut())));
							plist[i].setPrePrice(ArithUtil.sub(plist[i].getTotalNum(),plist[i].getTotalNumIn()));	
							result.add(plist[i]);
							}else if(ArithUtil.sub(plist[i].getTotalNum(),plist[i].getTotalNumIn())!=0){
								plist[i].setPrePrice(ArithUtil.sub(plist[i].getTotalNum(),plist[i].getTotalNumIn()));	
								result.add(plist[i]);
							}
						}
					}
				}
				
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
				}
			
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_apply_check_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void report_product_detail_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String productId = StringUtil.notNull(request.getParameter("productId"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
				List<OrderDetail> result = new ArrayList<OrderDetail>();
				
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select * from orders as os,orderDetail as od where os.orderId=od.orderId and os.state>'0' and od.productId='"+productId+"' and os.orderTime>='"+startTime+"' and os.orderTime<='"+endTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					double[] sum = {0,0};
					while(rs.next()){
						int orderType = rs.getInt("os.orderType");
						OrderDetail od = new OrderDetail();
						od.setProductId(rs.getString("od.productId"));
						od.setProductName(rs.getString("od.productName"));
						od.setSpecification(rs.getString("od.specification"));
						od.setUnit(rs.getString("od.unit"));
						od.setProductType(rs.getString("od.productType"));
						od.setPrice(rs.getDouble("od.price"));
						od.setEntryTime(rs.getTimestamp("os.orderTime"));
						if(orderType==1)
						od.setType(1);
						else if(orderType==2)
						od.setType(2);
						od.setNum(rs.getDouble("od.num"));
						sum[0] = sum[0] +od.getNum();
						result.add(od);
					}
					sql = "select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and idl.productId='"+productId+"' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.pay_type desc,iay.type asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int type =rs.getInt("iay.type");
						int payType = rs.getInt("iay.pay_type");
						OrderDetail od = new OrderDetail();
						od.setProductId(rs.getString("idl.productId"));
						od.setProductName(rs.getString("idl.productName"));
						od.setSpecification(rs.getString("idl.specification"));
						od.setUnit(rs.getString("idl.unit"));
						od.setProductType(rs.getString("idl.productType"));
						od.setPrice(rs.getDouble("idl.price"));
						od.setNum(rs.getDouble("idl.num"));
						od.setEntryTime(rs.getTimestamp("iay.confirm_time"));
						if(payType==2){
							if(type==1)od.setType(3);
							else if(type==2)od.setType(4);
							else if(type==3)od.setType(5);
							else if(type==4)od.setType(6);
							else if(type==5)od.setType(7);
							else if(type==6)od.setType(8);
							else if(type==7)od.setType(9);
							else if(type==8)od.setType(10);
							sum[0] = sum[0] +od.getNum();
						}else if(payType==1){
							if(type==1)od.setType(11);
							else if(type==2)od.setType(12);
							else if(type==3)od.setType(13);
							else if(type==4)od.setType(14);
							sum[1] = sum[1] +od.getNum();
						}
						result.add(od);
					}
			
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
					request.setAttribute("sum", sum);
				}
				
				}else {
					message = "日期格式有误，请重新输入！";
				}
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				request.setAttribute("productId", productId);
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_product_detail_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void exportExcel_apply_in_summary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "入库统计汇总表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<Product>  result = new ArrayList<Product> ();
	
		try {
			if(db.createConn()){
				conn = db.getConnection();
				
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setNumIn1(0);
					plist[id].setNumIn2(0);
					plist[id].setNumIn3(0);
					plist[id].setNumIn4(0);
					plist[id].setNumIn5(0);
					plist[id].setNumOut1(0);
					plist[id].setNumOut2(0);
					plist[id].setNumOut3(0);
					plist[id].setNumOut4(0);
					plist[id].setNumOut5(0);
					plist[id].setTotalNumIn(0);
					plist[id].setTotalNumOut(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='1' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("idl.pid");
				int type = rs.getInt("iay.type");
				if(plist[id]==null) plist[id] = new Product();
				if(type==1){
					plist[id].setNumIn2(ArithUtil.add(plist[id].getNumIn2(), rs.getDouble("idl.num")));
				}else if(type==2){
					plist[id].setNumIn3(ArithUtil.add(plist[id].getNumIn3(), rs.getDouble("idl.num")));
				}else if(type==3){
					plist[id].setNumIn4(ArithUtil.add(plist[id].getNumIn4(), rs.getDouble("idl.num")));
				}else if(type==4){
					plist[id].setNumIn5(ArithUtil.add(plist[id].getNumIn5(), rs.getDouble("idl.num")));
				}
				plist[id].setTotalNumIn(ArithUtil.add(plist[id].getTotalNumIn(), rs.getDouble("idl.num")));
			}
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNumIn()>0){
							result.add(plist[i]);
						}
					}
				}
				
			
				
				String[][] content = new String[result.size()+2][10];
				
				content[0][0]="分类";
				content[0][1]="编号";
				content[0][2]="货品名称";
				content[0][3]="规格";
				content[0][4]="单位";
				content[0][5]="采购入库数";
				content[0][6]="加工入库数";
				content[0][7]="退货入库数";
				content[0][8]="盘盈入库数";
				content[0][9]="入库总数";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = StringUtil.notNull(result.get(i).getProductType());
					content[i+1][1]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getUnit());
					content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getNumIn1());
					content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getNumIn2());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getNumIn3());
					content[i+1][8] =StringUtil.decimalFormat(result.get(i).getNumIn4());
					content[i+1][9] = StringUtil.decimalFormat(result.get(i).getTotalNumIn());

				}
				
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("入库统计汇总表（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
	
	protected void exportExcel_apply_out_summary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "出库统计汇总表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<Product>  result = new ArrayList<Product> ();
	
		try {
			if(db.createConn()){
				conn = db.getConnection();
				
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					Product[] plist = new Product[num1+1];
 					sql ="select * from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						if(plist[id]==null) plist[id] = new Product();
					plist[id].setId(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setFeatures(rs.getString("features"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setdType(rs.getString("d_type"));
					plist[id].setProductType(rs.getString("productType"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setNum(0);
					plist[id].setNumIn1(0);
					plist[id].setNumIn2(0);
					plist[id].setNumIn3(0);
					plist[id].setNumIn4(0);
					plist[id].setNumIn5(0);
					plist[id].setNumOut1(0);
					plist[id].setNumOut2(0);
					plist[id].setNumOut3(0);
					plist[id].setNumOut4(0);
					plist[id].setNumOut5(0);
					plist[id].setTotalNumIn(0);
					plist[id].setTotalNumOut(0);
					plist[id].setTotalNum(0);
					plist[id].setTotalPrice(0);
					plist[id].setPreNum(0);
					plist[id].setPrePrice(0);
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					plist[id].setTag(0);
					}
					
				sql ="select * from orders as od,orderDetail as odd where od.orderId=odd.orderId and od.state>='1' and od.orderTime>='"+startTime+"' and od.orderTime<='"+endTime+"' order by odd.id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				while(rs.next()){
					int id = rs.getInt("odd.pid");
					if(plist[id]==null) plist[id] = new Product();
					plist[id].setNumOut1(ArithUtil.add(plist[id].getNumOut1(), rs.getDouble("odd.num")));
					plist[id].setTotalNumOut(ArithUtil.add(plist[id].getTotalNumOut(), rs.getDouble("odd.num")));
					
				}
				
				sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.pay_type='2' and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' order by iay.id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("idl.pid");
				int type = rs.getInt("iay.type");
				if(plist[id]==null) plist[id] = new Product();
				if(type==1){
					plist[id].setNumOut2(ArithUtil.add(plist[id].getNumOut2(), rs.getDouble("idl.num")));
				}else if(type==2){
					plist[id].setNumOut3(ArithUtil.add(plist[id].getNumOut3(), rs.getDouble("idl.num")));
				}else if(type==3){
					plist[id].setNumOut4(ArithUtil.add(plist[id].getNumOut4(), rs.getDouble("idl.num")));
				}else if(type==4){
					plist[id].setNumOut5(ArithUtil.add(plist[id].getNumOut5(), rs.getDouble("idl.num")));
				}else if(type==5){
					plist[id].setNumOut6(ArithUtil.add(plist[id].getNumOut6(), rs.getDouble("idl.num")));
				}else if(type==6){
					plist[id].setNumOut7(ArithUtil.add(plist[id].getNumOut7(), rs.getDouble("idl.num")));
				}else if(type==7){
					plist[id].setNumOut8(ArithUtil.add(plist[id].getNumOut8(), rs.getDouble("idl.num")));
				}else if(type==8){
					plist[id].setNumOut9(ArithUtil.add(plist[id].getNumOut9(), rs.getDouble("idl.num")));
				}
				plist[id].setTotalNumOut(ArithUtil.add(plist[id].getTotalNumOut(), rs.getDouble("idl.num")));
			}
				
				for(int i=1;i<plist.length;i++){
					if(plist[i]!=null){
						if(plist[i].getTotalNumOut()>0){
							result.add(plist[i]);
						}
					}
				}
				
				String[][] content = new String[result.size()+2][16];
				
				content[0][0]="分类";
				content[0][1]="编号";
				content[0][2]="货品名称";
				content[0][3]="规格";
				content[0][4]="单位";
				content[0][5]="销售出库数";
				content[0][6]="加工出库数";
				content[0][7]="盘亏出库数";
				content[0][8]="损耗出库数";
				content[0][9]="外销出库数";
				content[0][10]="体验出库数";
				content[0][11]="赠送出库数";
				content[0][12]="研发出库数";
				content[0][13]="退货出库数";
				content[0][14]="出库总数";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = StringUtil.notNull(result.get(i).getProductType());
					content[i+1][1]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getUnit());
					content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getNumOut1());
					content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getNumOut2());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getNumOut3());
					content[i+1][8] =StringUtil.decimalFormat(result.get(i).getNumOut4());;
					content[i+1][9] = StringUtil.decimalFormat(result.get(i).getNumOut5());
					content[i+1][10] = StringUtil.decimalFormat(result.get(i).getNumOut6());
					content[i+1][11] = StringUtil.decimalFormat(result.get(i).getNumOut7());
					content[i+1][12] = StringUtil.decimalFormat(result.get(i).getNumOut8());
					content[i+1][13] = StringUtil.decimalFormat(result.get(i).getNumOut9());
					content[i+1][14] = StringUtil.decimalFormat(result.get(i).getTotalNumOut());

				}
				
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("出库统计汇总表（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
	
	protected synchronized void report_branch_detail_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		List<Branch> result = new ArrayList<Branch>();
		Branch[] bra = null;
		Product pro = null;
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String productId = StringUtil.notNull(request.getParameter("productId"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					int num =0;
					String sql0 = "select * from product where productId='"+productId+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						if(pro ==null)pro = new Product();
						pro.setId(rs.getInt("id"));
						pro.setProductId(rs.getString("productId"));
						pro.setProductName(rs.getString("productName"));
						pro.setSpecification(rs.getString("specification"));
						pro.setUnit(rs.getString("unit"));
					
					String sql1 = "select max(id) from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						num = rs.getInt(1);
					}
					if(num>0){
						
					bra = new Branch[num+1];
					String sql2 ="select * from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						int id =rs.getInt("id");
						if(bra[id]==null) bra[id]=new Branch();
						bra[id].setId(id);
						bra[id].setCode(rs.getString("code"));
						bra[id].setName(rs.getString("name"));
						bra[id].setPrice(0);
						bra[id].setNum(0);
						bra[id].setTag(0);
					}
					
					String sql ="select * from orders as os,orderDetail as od where os.orderId=od.orderId and os.state>'0' and od.productId='"+productId+"' and os.orderTime>='"+startTime+"' and os.orderTime<='"+endTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id = rs.getInt("os.uId");
						if(bra[id]!=null){
							double pnum = rs.getInt("od.num");
							double price = rs.getInt("od.price");
							bra[id].setNum(ArithUtil.add(bra[id].getNum(), pnum));
							bra[id].setPrice(ArithUtil.add(bra[id].getPrice(), price));
							bra[id].setTag(1);
						}
					}
					for(int i=1;i<bra.length;i++){
						
						if(bra[i]!=null){
							if(bra[i].getTag()==1){
								if(bra[i].getNum()>0){
									bra[i].setPrice(ArithUtil.div(bra[i].getPrice(), bra[i].getNum()));
									result.add(bra[i]);
								}
							}
						}
						
					}
			
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
					request.setAttribute("sys_pro", pro);
				}
					}else {
						message = "获取店铺相关信息失败！";
					}
					}else {
						message = "获取产品相关信息失败！";
					}
				}else {
					message = "日期格式有误，请重新输入！";
				}
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				request.setAttribute("productId", productId);
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			pro = null;
			bra = null;
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_branch_detail_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void report_branch_price_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		List<Branch> result = new ArrayList<Branch>();
		Branch[] bra = null;
		Product pro = null;
		double sum = 0;
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 100;
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					int num =0;
				
					String sql1 = "select max(id) from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						num = rs.getInt(1);
					}
					if(num>0){
						
					bra = new Branch[num+1];
					String sql2 ="select * from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						int id =rs.getInt("id");
						if(bra[id]==null) bra[id]=new Branch();
						bra[id].setId(id);
						bra[id].setCode(rs.getString("code"));
						bra[id].setName(rs.getString("name"));
						bra[id].setPrice(0);
						bra[id].setNum(0);
						bra[id].setTag(0);
					}
					
					String sql ="select * from orders  where state>'0'  and orderTime>='"+startTime+"' and orderTime<='"+endTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id = rs.getInt("uId");
						if(bra[id]!=null){
							double price = rs.getInt("price");
							bra[id].setPrice(ArithUtil.add(bra[id].getPrice(), price));
							bra[id].setTag(1);
						}
					}
					for(int i=1;i<bra.length;i++){
						
						if(bra[i]!=null){
							if(bra[i].getTag()==1){
								if(bra[i].getPrice()>0){
									sum = ArithUtil.add(sum,bra[i].getPrice());
									result.add(bra[i]);
								}
							}
						}
						
					}
			
				if(result.size()>0){
					if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
					if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
					int startIndex = pageSize*(pageNo-1);
					int endIndex = pageSize*pageNo;
					if(result.size()<endIndex) endIndex = result.size();
					Pager pager = new Pager(pageSize,pageNo,result.size(),result.subList(startIndex, endIndex));
					request.setAttribute("pager", pager);
					request.setAttribute("sum", sum);
				}
					}else {
						message = "获取店铺相关信息失败！";
					}
				
				}else {
					message = "日期格式有误，请重新输入！";
				}
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else {
				message = "数据库连接已断开！";
			}
				}else{
					message = "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			pro = null;
			bra = null;
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_branch_price_summary.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void exportExcel_report_branch_detail_summary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "单品店统计"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
	    List<Branch> result = new ArrayList<Branch>();
		Branch[] bra = null;
		Product pro = null;
		try {
			if(db.createConn()){
				conn = db.getConnection();
				String productId = StringUtil.notNull(request.getParameter("productId"));
			
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					int num =0;
					String sql0 = "select * from product where productId='"+productId+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						if(pro ==null)pro = new Product();
						pro.setId(rs.getInt("id"));
						pro.setProductId(rs.getString("productId"));
						pro.setProductName(rs.getString("productName"));
						pro.setSpecification(rs.getString("specification"));
						pro.setUnit(rs.getString("unit"));
					
					String sql1 = "select max(id) from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						num = rs.getInt(1);
					}
					if(num>0){
						
					bra = new Branch[num+1];
					String sql2 ="select * from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						int id =rs.getInt("id");
						if(bra[id]==null) bra[id]=new Branch();
						bra[id].setId(id);
						bra[id].setCode(rs.getString("code"));
						bra[id].setName(rs.getString("name"));
						bra[id].setPrice(0);
						bra[id].setNum(0);
						bra[id].setTag(0);
					}
					
					String sql ="select * from orders as os,orderDetail as od where os.orderId=od.orderId and os.state>'0' and od.productId='"+productId+"' and os.orderTime>='"+startTime+"' and os.orderTime<='"+endTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id = rs.getInt("os.uId");
						if(bra[id]!=null){
							double pnum = rs.getInt("od.num");
							double price = rs.getInt("od.price");
							bra[id].setNum(ArithUtil.add(bra[id].getNum(), pnum));
							bra[id].setPrice(ArithUtil.add(bra[id].getPrice(), price));
							bra[id].setTag(1);
						}
					}
					for(int i=1;i<bra.length;i++){
						
						if(bra[i]!=null){
							if(bra[i].getTag()==1){
								if(bra[i].getNum()>0){
									bra[i].setPrice(ArithUtil.div(bra[i].getPrice(), bra[i].getNum()));
									result.add(bra[i]);
								}
							}
						}
						
					}
				
				String[][] content = new String[result.size()+4][5];
				content[0][0]="产品编号";
				content[0][1]=pro.getProductId();
				content[0][2]="产品名称";
				content[0][3]=pro.getProductName();
				content[0][4]="";
				content[1][0]="产品规格";
				content[1][1]=pro.getSpecification();
				content[1][2]="产品单位";
				content[1][3]=pro.getUnit();
				content[1][4]="";
				content[2][4]="销售总量";
				content[2][0]="序号";
				content[2][1]="店铺编号";
				content[2][2]="店铺名称";
				content[2][3]="平均单价";
				content[2][4]="销售总量";
				
				for(int i=0;i<result.size();i++){
					content[i+3][0]  = StringUtil.notNull(String.valueOf(i+1));
					content[i+3][1]  = StringUtil.notNull(result.get(i).getCode());
					content[i+3][2]  = StringUtil.notNull(result.get(i).getName());
					content[i+3][3]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+3][4]  = StringUtil.decimalFormat(result.get(i).getNum());
				
				}
				
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("单品店统计（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
						request.setAttribute("message", "店铺信息获取失败！");
					}
					}else {
						request.setAttribute("message", "单品信息获取失败！");
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
	
	protected void exportExcel_report_branch_price_summary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "店销汇总"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
	    List<Branch> result = new ArrayList<Branch>();
		Branch[] bra = null;
		Product pro = null;
		double sum = 0;
		try {
			if(db.createConn()){
				conn = db.getConnection();
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					int num =0;
				
					String sql1 = "select max(id) from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						num = rs.getInt(1);
					}
					if(num>0){
						
					bra = new Branch[num+1];
					String sql2 ="select * from branch";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						int id =rs.getInt("id");
						if(bra[id]==null) bra[id]=new Branch();
						bra[id].setId(id);
						bra[id].setCode(rs.getString("code"));
						bra[id].setName(rs.getString("name"));
						bra[id].setPrice(0);
						bra[id].setNum(0);
						bra[id].setTag(0);
					}
					
					String sql ="select * from orders  where state>'0'  and orderTime>='"+startTime+"' and orderTime<='"+endTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id = rs.getInt("uId");
						if(bra[id]!=null){
							double price = rs.getInt("price");
							bra[id].setPrice(ArithUtil.add(bra[id].getPrice(), price));
							bra[id].setTag(1);
						}
					}
					for(int i=1;i<bra.length;i++){
						
						if(bra[i]!=null){
							if(bra[i].getTag()==1){
								if(bra[i].getPrice()>0){
									sum = ArithUtil.add(sum,bra[i].getPrice());
									result.add(bra[i]);
								}
							}
						}
						
					}
			
				String[][] content = new String[result.size()+2][4];
				
				content[0][0]="序号";
				content[0][1]="店铺编号";
				content[0][2]="店铺名称";
				content[0][3]="销售总额";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = StringUtil.notNull(String.valueOf(i+1));
					content[i+1][1]  = StringUtil.notNull(result.get(i).getCode());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getName());
					content[i+1][3]  = StringUtil.decimalFormat(result.get(i).getPrice());
				
				}
				content[result.size()+1][0]="合计";
				content[result.size()+1][1]="-";
				content[result.size()+1][2]="-";
				content[result.size()+1][3]=StringUtil.decimalFormat(sum);
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("店销汇总（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
						request.setAttribute("message", "店铺信息获取失败！");
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
		
		protected void report_order_delivery_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			DBConn db = new DBConn();
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));

			String message="";
			try {
				if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					if(!(startTimeStr.equals(""))){
						String pageNumStr = StringUtil.notNull(request.getParameter("pageNum"));

						String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
						String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
						int pageNo = 1;
						int pageSize = 15;
						int pageNum = 5;
						if(!pageNumStr.equals("")) pageNum = Integer.valueOf(pageNumStr);
						request.setAttribute("pageNum", pageNum);
						pageSize = pageNum;
						Timestamp startTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						Timestamp endTime= new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						String sql ="select max(id) from product";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql);
						int num1 = 0;
						if(rs.next()){
							num1 = rs.getInt(1);
						}
						sql ="select max(id) from branch";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql);
						int num2 = 0;
						int b_num = 0;
						if(rs.next()){
							num2 = rs.getInt(1);
						}
						request.setAttribute("b_num", b_num);
						Product[] plist = new Product[num1+1];
						List<Branch> blist = new ArrayList<Branch>();
						Double[] nlist = new Double[num1+1];
						Double[][] bnlist = new Double[num2+1][num1+1];
	 					sql ="select * from product";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql);
						
						while(rs.next()){
							int id =rs.getInt("id");
							if(plist[id]==null) plist[id] = new Product();
						plist[id].setId(rs.getInt("id"));
						plist[id].setProductName(rs.getString("productName"));
						plist[id].setFeatures(rs.getString("features"));
						plist[id].setProductId(rs.getString("productId"));
						plist[id].setdType(rs.getString("d_type"));
						plist[id].setProductType(rs.getString("productType"));
						plist[id].setSpecification(rs.getString("specification"));
						plist[id].setPrice(rs.getDouble("price"));
						plist[id].setUnit(rs.getString("unit"));
						plist[id].setNum(0);
						plist[id].setImageUrl(rs.getString("imageUrl"));
						plist[id].setState(rs.getInt("state"));
						plist[id].setEntryTime(rs.getTimestamp("entryTime"));
						plist[id].setEndTime(rs.getTimestamp("endTime"));
						nlist[id] = (double) 0;
						}
						
						sql ="select * from branch order by asc_num asc";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql);
						while(rs.next()){
						Branch branch = new Branch();
						branch.setId(rs.getInt("id"));
						branch.setCode(rs.getString("code"));
						branch.setName(rs.getString("name"));
						branch.setLinkman(rs.getString("linkman"));
						branch.setState(rs.getInt("state"));
						branch.setTag(rs.getInt("tag"));
						blist.add(branch);
						}
						
						
					String sql1 ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and state>'0'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						int bid = rs.getInt("uId");	
						Order orders = new Order();
						orders.setId(rs.getInt("id"));
						String orderId =rs.getString("orderId");
						orders.setOrderId(orderId);
						orders.setUserId(rs.getString("userId"));
						orders.setUserName(rs.getString("userName"));
						orders.setOrderType(rs.getInt("orderType"));
						orders.setOrderTime(rs.getTimestamp("orderTime"));
						orders.setReceiver(rs.getString("receiver"));
						orders.setPhone(rs.getString("phone"));
						orders.setAddress(rs.getString("address"));
						orders.setState(rs.getInt("state"));
						orders.setPrice(rs.getDouble("price"));
						orders.setRemark(rs.getString("remark"));
						orders.setSummary(rs.getString("summary"));
						orders.setComments(rs.getString("comments"));
						request.setAttribute("orders", orders);
						String sqld = "select * from orderDetail where orderId ='"+orderId+"' and num>'0'";
						Statement stmt1 =db.getStmtread();
						ResultSet rs1 =stmt1.executeQuery(sqld);
						while(rs1.next()){
							int pid = rs1.getInt("pid");
							if(pid>0){
								if(bnlist[bid][pid]==null) bnlist[bid][pid] = (double) 0;
								bnlist[bid][pid] = ArithUtil.add(bnlist[bid][pid],rs1.getDouble("num"));
								if(nlist[pid]==null) nlist[pid] = (double) 0;
								nlist[pid] =  ArithUtil.add(nlist[pid],rs1.getDouble("num"));
							}
						}
						if(rs1!=null) rs1.close();
						if(stmt1!=null) stmt1.close();
					}
					if(blist.size()>0){
						if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
						if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
						int startIndex = pageSize*(pageNo-1);
						int endIndex = pageSize*pageNo;
						if(blist.size()<endIndex) endIndex = blist.size();
						Pager pager = new Pager(pageSize,pageNo,blist.size(),blist.subList(startIndex, endIndex));
						b_num = blist.subList(startIndex, endIndex).size();
						request.setAttribute("pager", pager);
					}
					
						request.setAttribute("b_num", b_num);
						request.setAttribute("plist", plist);
						request.setAttribute("nlist", nlist);
						request.setAttribute("bnlist", bnlist);
						request.setAttribute("startTime", startTimeStr);
						request.setAttribute("endTime", endTimeStr);
					}else {
						message = "日期格式有误！";
					}
				}else {
					message = "数据库连接已断开！";
				}
					}else{
						message = "用户未登陆，请重新登陆！";
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "数据操作异常，请重新登陆！";
			}finally{
				db.close();
				
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("report_order_delivery_summary.jsp");
					dispatcher.forward(request, response);
			}
		
	}
}

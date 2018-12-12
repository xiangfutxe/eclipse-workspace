package com.servlet;

import java.io.IOException;
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

import com.db.DBConn;
import com.pojo.Address;
import com.pojo.Admin;
import com.pojo.Branch;
import com.pojo.Center;
import com.pojo.Order;
import com.pojo.OrderDetail;
import com.pojo.OrderWeek;
import com.pojo.Param;
import com.pojo.Product;
import com.pojo.ProductType;
import com.pojo.Settle;
import com.pojo.ShortMessage;
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

public class OrderServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	IUserService us = new UserService();
	
	ICustomService cs = new CustomService();
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		}else if(method.equals("orders")){
			order_list(request,response);
		}else if(method.equals("order_payoff")){
			order_payoff(request,response);
		}else if(method.equals("detail")){
			detail(request,response);
		}else if(method.equals("order_edit")){
			order_edit(request,response);
		}else if(method.equals("order_new_branch")){
			order_new_branch(request,response);
		}else if(method.equals("order_new_save")){
			order_new_save(request,response);
		}else if(method.equals("order_detail")){
			order_detail(request,response);
		}else if(method.equals("admin_orders")){
			admin_order_list(request,response);
		}else if(method.equals("admin_order_detail")){
			admin_order_detail(request,response);
		}else if(method.equals("admin_order_detail_all")){
			admin_order_detail_all(request,response);
		}else if(method.equals("admin_order_detail_delivery")){
			admin_order_detail_delivery(request,response);
		}else if(method.equals("admin_order_delivery")){
			admin_order_delivery(request,response);
		}else if(method.equals("admin_order_delivery_express")){
			admin_order_delivery_express(request,response);
		}else if(method.equals("admin_order_delivery_yes")){
			admin_order_delivery_yes(request,response);
		}else if(method.equals("admin_order_delivery_ylist")){
			admin_order_delivery_ylist(request,response);
		}
		else if(method.equals("admin_order_confirm")){
			admin_order_confirm(request,response);
		}else if(method.equals("admin_order_confirm_yes")){
			admin_order_confirm_yes(request,response);
		}else if(method.equals("admin_order_confirm_merge")){
			admin_order_confirm_merge(request,response);
		}else if(method.equals("admin_order_confirm_all_yes")){
			admin_order_confirm_all_yes(request,response);
		}else if(method.equals("admin_order_delivery_all_yes")){
			admin_order_delivery_all_yes(request,response);
		}else if(method.equals("admin_order_delivery_all_no")){
			admin_order_delivery_all_no(request,response);
		}else if(method.equals("admin_order_review_all_yes")){
			admin_order_review_all_yes(request,response);
		}else if(method.equals("admin_order_review_all_no")){
			admin_order_review_all_no(request,response);
		}else if(method.equals("admin_order_detail_confirm")){
			admin_order_detail_confirm(request,response);
		}else if(method.equals("admin_order_confirm_ylist")){
			admin_order_confirm_ylist(request,response);
		}
		else if(method.equals("admin_order_review")){
			admin_order_review(request,response);
		}else if(method.equals("admin_order_detail_review")){
			admin_order_detail_review(request,response);
		}else if(method.equals("admin_order_review_ylist")){
			admin_order_review_ylist(request,response);
		}else if(method.equals("admin_order_review_yes")){
			admin_order_review_yes(request,response);
		}else if(method.equals("admin_order_review_no")){
			admin_order_review_no(request,response);
		}else if(method.equals("admin_order_adr_edit")){
			try {
				admin_order_adr_edit(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_order_adr_update")){
			try {
				admin_order_adr_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("adr_edit")){
			try {
				order_adr_edit(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_adr_update")){
			try {
				order_adr_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("product_list")){
				product_list(request,response);
			
		}else if(method.equals("increate_p")){
			increate_p(request,response);
		}else if(method.equals("disDe_p")){
			disDe_p(request,response);
		}else if(method.equals("increate")){
			increate(request,response);
		}else if(method.equals("disDe")){
			disDe(request,response);
		}else if(method.equals("del_pro")){
			del_pro(request,response);
		}else if(method.equals("shop_cart_save")){
			shop_cart_save(request,response);
		}else if(method.equals("shop_save")){
			shop_save(request,response);
		}else if(method.equals("order_double")){
			order_double(request,response);
		}else if(method.equals("admin_order_payoff")){
			admin_order_payoff(request,response);
		}else if(method.equals("admin_order_payoff_add")){
			admin_order_payoff_add(request,response);
		}else if(method.equals("admin_order_payoff_yes")){
			admin_order_payoff_yes(request,response);
		}else if(method.equals("admin_delivery_list")){
			admin_delivery_list(request,response);
		}else if(method.equals("admin_delivery_summary_list")){
			admin_delivery_summary_list(request,response);
		}else if(method.equals("admin_branch_summary_list")){
			admin_branch_summary_list(request,response);
		}else if(method.equals("order_back")){
			order_back(request,response);
		}else if(method.equals("admin_order_back")){
			admin_order_back(request,response);
		}else if(method.equals("admin_order_new_branch_shop")){
			admin_order_new_branch_shop(request,response);
		}else if(method.equals("admin_order_new_branch_shop_add")){
			admin_order_new_branch_shop_add(request,response);
		}else if(method.equals("admin_order_new_branch_shop_save")){
			admin_order_new_branch_shop_save(request,response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(user!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state like'%"+state+"%'  and orderType like'%"+orderType+"%' and userId='"+user.getCode()+"' order by id desc";
				}else{
					sql ="select * from orders where state like'%"+state+"%'  and orderType like'%"+orderType+"%' and userId='"+user.getCode()+"' order by id desc";
					}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setDeliveryTag(rs.getInt("deliveryTag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setIntegral(rs.getDouble("integral"));
					result.add(orders);
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
				request.setAttribute("state",state);
				request.setAttribute("orderType",orderType);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
				}else{
					request.setAttribute("message", "用户未登陆，请重新登陆！");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("orders.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_divery(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(user!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					if(state.equals("")){
						if(orderType.equals(""))
							sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and userByCenterId='"+ user.getCode()+"' order by id desc";
						else sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  orderType = '"+orderType+"' and userByCenterId='"+ user.getCode()+"'  order by id desc";
					}else{
						if(orderType.equals(""))
							sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state ='"+state+"' and userId='"+user.getCode()+"' userByCenterId='"+ user.getCode()+"'  order by id desc";
						else 
							sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='"+state+"' and orderType='"+orderType+"' and  userByCenterId='"+ user.getCode()+"'  order by id desc";
					}
					}else{
						if(state.equals("")){
							if(orderType.equals(""))
								sql ="select * from orders where   userByCenterId='"+ user.getCode()+"'  order by id desc";
							else sql ="select * from orders where orderType = '"+orderType+"' and userByCenterId='"+ user.getCode()+"'  order by id desc";
						}else{
							if(orderType.equals(""))
								sql ="select * from orders where  state ='"+state+"' and userByCenterId='"+ user.getCode()+"'  order by id desc";
							else 
								sql ="select * from orders where state='"+state+"' and orderType='"+orderType+"' and userByCenterId='"+ user.getCode()+"' order by id desc";
						}
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setDeliveryTag(rs.getInt("deliveryTag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					result.add(orders);
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
				request.setAttribute("state",state);
				request.setAttribute("orderType",orderType);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
				}else{
					request.setAttribute("message", "用户未登陆，请重新登陆！");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and state like'%"+state+"%'  and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and state like'%"+state+"%'  order by id desc";
					
				}else{
					sql ="select * from orders where orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and state like'%"+state+"%'   order by id desc";
						
				}
				
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
				
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					
					result.add(orders);
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
				request.setAttribute("state",state);
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_new_branch_shop(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][7].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where  orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and state like'%"+state+"%'  and orderType ='2'  and userId like'%"+userId+"%' and state like'%"+state+"%'  order by id desc";
					
				}else{
					sql ="select * from orders where orderType ='2' and userId like'%"+userId+"%' and state like'%"+state+"%'   order by id desc";
						
				}
				
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
				
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					
					result.add(orders);
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
				request.setAttribute("state",state);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_branch_shop.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_branch_shop_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						
							sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='1' and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' order by id desc";
					
					}else{
						
								sql ="select * from orders where state='1' and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					result.add(orders);
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
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_confirm_ylist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and adminByConfirmId='"+admin.getAdminName()+"' order by id desc";
					
					}else{
						sql ="select * from orders where  orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and adminByConfirmId='"+admin.getAdminName()+"'  order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setTag(rs.getInt("tag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					
					result.add(orders);
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
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_ylist.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_review_ylist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and adminByReviewerId='"+admin.getAdminName()+"'  and tag ='1' order by id desc";
					
					}else{
						sql ="select * from orders where  orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and adminByReviewerId='"+admin.getAdminName()+"'  order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					result.add(orders);
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
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_ylist.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_delivery_ylist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and adminByDeliveryId='"+admin.getAdminName()+"'  order by id desc";
					
					}else{
						sql ="select * from orders where  orderType like'%"+orderType+"%' and userId like'%"+userId+"%' and adminByDeliveryId='"+admin.getAdminName()+"'  order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setPv(rs.getDouble("pv"));
					
					orders.setTag(rs.getInt("tag"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					
					result.add(orders);
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
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_ylist.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void admin_order_confirm_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			
				String sql ="select * from orders where orderId='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					if(orders.getState()==1){
						String sql1 ="select * from branch where code='"+orders.getUserId()+"' for update";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql1);
						if(rs.next()){
							Branch branch = new Branch();
							branch.setId(rs.getInt("id"));
							branch.setCode(rs.getString("code"));
							branch.setName(rs.getString("name"));
							branch.setCredit(rs.getDouble("credit"));
							branch.setTel(rs.getString("tel"));
							double creditUsed = rs.getDouble("credit_used");
							String sqlu = "update orders set state='2',adminByConfirmId='"+admin.getAdminName()+"',confirmTime='"+date+"' where orderId='"+id+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqlu);
							String sql4 = "update branch set credit_used='"+ArithUtil.sub(creditUsed, orders.getPrice())+"' where code='"+orders.getUserId()+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sql4);
							String sql5 = cs.getCreditDetail(branch, orders.getPrice(), ArithUtil.sub(branch.getCredit(),ArithUtil.sub(creditUsed, orders.getPrice())), 1,orders.getOrderId(), date);
							stmt = conn.createStatement();
							stmt.executeUpdate(sql5);
							message= "订单确认成功，订单编号为："+id;
								cs.insetAdminLog(conn, admin.getAdminName(), message, date);
						}else{
							message= "店铺信息获取失败，请重新确认！";
						}
					}else{
						message= "当前状态不在你的确认范围内，请重新确认！";
					}
				}else{
					message= "未查询到需要确认的订单信息，请重新确认！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void admin_order_confirm_all_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String error="";
		String error1="";
		String error2="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String[] ids = request.getParameterValues("ids");
				if(ids!=null){
							
				for(int i=0;i<ids.length;i++){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String sql ="select * from orders where id='"+ids[i]+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					if(orders.getState()==1){
						String sql1 ="select * from branch where code='"+orders.getUserId()+"' for update";
						stmt= db.getStmtread();
						rs =stmt.executeQuery(sql1);
						if(rs.next()){
							Branch branch = new Branch();
							branch.setId(rs.getInt("id"));
							branch.setCode(rs.getString("code"));
							branch.setName(rs.getString("name"));
							branch.setCredit(rs.getDouble("credit"));
							branch.setTel(rs.getString("tel"));
							double creditUsed = rs.getDouble("credit_used");
							String sqlu = "update orders set state='2',adminByConfirmId='"+admin.getAdminName()+"',confirmTime='"+date+"' where id='"+ids[i]+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqlu);
							String sql4 = "update branch set credit_used='"+ArithUtil.sub(creditUsed, orders.getPrice())+"' where code='"+orders.getUserId()+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sql4);
							String sql5 = cs.getCreditDetail(branch, orders.getPrice(), ArithUtil.sub(branch.getCredit(),ArithUtil.sub(creditUsed, orders.getPrice())), 1,orders.getOrderId(), date);
							stmt = conn.createStatement();
							stmt.executeUpdate(sql5);
							message= message+orders.getOrderId()+";";
						}else{
							error1 = orders.getUserId()+";"+error1;
						}
					}else{
						error = orders.getOrderId()+";"+error;
					}
					}else{
						error2 = ids[i]+";"+error2;
					}
				conn.commit();
				conn.setAutoCommit(autoCommit);
				}
				if(error1.equals("")&&error.equals("")&error2.equals("")){
					message = "订单批量处理成功，批量处理订单编号分别为："+message;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					if(error.equals(""))
					message = message+"以下订单当前状态不在你的确认范围内："+error;
					if(error1.equals(""))
					message = message+"以下店铺信息获取失败："+error1;
					if(error2.equals(""))
					message = message+"以下订单信息获取失败："+error2;
				}	
				
				}else {
					message= "未获取到需要确认通过的相关订单信息！";
				}
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void admin_order_confirm_merge(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String error="";
		List<String> slist = new ArrayList<String>();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
						conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String[] ids = request.getParameterValues("ids");
				if(ids!=null){
					Order new_order = new Order();
					 List<OrderDetail> olist = new ArrayList<OrderDetail>();
					 String orderId = cs.createOrderId(date);
					 new_order.setOrderId(orderId);
					 new_order.setState(1);
					 new_order.setOrderType(1);
					 new_order.setPrice(0);
					 new_order.setOrderTime(date);
					 new_order.setRemark("合并订单：");
					 int uid=0;
					 String userId="";
				for(int i=0;i<ids.length;i++){
				String sql ="select * from orders where id='"+ids[i]+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					if(uid==0) uid=rs.getInt("uId");
					else if(uid!=rs.getInt("uId")){
						error = "合并订单必须全部同为一家分店。";
					}
					if(error.equals("")){
						if(rs.getInt("state")==1){
						String oid = rs.getString("orderId");
						userId = rs.getString("userId"); 
						new_order.setuId(uid);
						new_order.setUserId(rs.getString("userId"));
						new_order.setUserName(rs.getString("userName"));
						new_order.setReceiver(rs.getString("receiver"));
						new_order.setPhone(rs.getString("phone"));
						new_order.setAddress(rs.getString("address"));
						new_order.setRemark(new_order.getRemark()+rs.getString("orderId")+",金额"+rs.getDouble("price")+";");
						new_order.setPrice(ArithUtil.add(new_order.getPrice(),rs.getDouble("price")));
						String sql3 = "delete from orders where id='"+ids[i]+"'"; 
						slist.add(sql3);
						String sql1 = "select * from orderDetail where orderId='"+oid+"' for update";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql1);
						while(rs.next()){
							OrderDetail od = new OrderDetail();
							od.setId(rs.getInt("id"));
							od.setOrderId(orderId);
							od.setPid(rs.getInt("pid"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setProductType(rs.getString("productType"));
							od.setSpecification(rs.getString("specification"));
							od.setNum(rs.getDouble("num"));
							od.setPrice(rs.getDouble("price"));
							od.setUnit(rs.getString("unit"));
							od.setImageUrl(rs.getString("image_url"));
							od.setType(0);
							for(int j=0;j<olist.size();j++){
								if(olist.get(j).getPid()==od.getPid()){
									if(ArithUtil.sub(olist.get(j).getProductPrice(),od.getProductPrice())==0){
										olist.get(j).setNum(ArithUtil.add(olist.get(j).getNum(), od.getNum()));
										olist.get(j).setPrice(ArithUtil.add(olist.get(j).getPrice(), od.getPrice()));
										od.setType(1);
									}
								}
							}
							if(od.getType()==0) olist.add(od);
							String sql4 = "delete from orderDetail where id='"+od.getId()+"'"; 
							slist.add(sql4);
						}//end  while(rs.next)
						}else{
							error ="存在状态不能进行合并的订单。";
						}
					}
				}else {
					error= "未获取到需要确认合并的相关订单信息！";
				}
					if(!error.equals("")) break;
				}
				if(error.equals("")){
					
					for(int i=0;i<olist.size();i++){
						String sql1="insert into orderDetail(orderId,pid,productId,productName,productType,unit,productPrice,num,price,image_url)"
								+ " values('"+orderId+"','"+olist.get(i).getPid()+"','"+olist.get(i).getProductId()+"','"+olist.get(i).getProductName()+"','"+olist.get(i).getProductType()+"','"+olist.get(i).getUnit()
								+"','"+olist.get(i).getProductPrice()+"','"+olist.get(i).getNum()+"','"+olist.get(i).getPrice()+"','"+olist.get(i).getImageUrl()+"');";
						slist.add(sql1);
					}
					String sqls = "insert into orders(orderId,uId,userId,userName,orderType,price,receiver,phone,address,orderTime,state,remark) "
							+ "values('"+new_order.getOrderId()+"','"+new_order.getuId()+"','"+new_order.getUserId()+"','"+new_order.getUserName()+"','"+new_order.getOrderType()+"','"+new_order.getPrice()+"','"+new_order.getReceiver()+
							"','"+new_order.getPhone()+"','"+new_order.getAddress()+"','"+date+"','"+new_order.getState()+"','"+new_order.getRemark()+"')";
					slist.add(sqls);
					if(slist.size()>0){
						stmt = conn.createStatement();
						for(int i=0;i<slist.size();i++){
							stmt.addBatch(slist.get(i));
							System.out.println(slist.get(i));
							 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
								 stmt.executeBatch();
								 stmt = conn.createStatement();
							 }
						}
					}
					message= userId+"分店订单合并成功！";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else{
						message =error;
					}
				}else {
					message= "未选择需要进行合并的订单信息！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void admin_order_delivery_all_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String error="";
		String error1="";
		String error2="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String[] ids = request.getParameterValues("ids");
				if(ids!=null){
							
				for(int i=0;i<ids.length;i++){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String sql ="select * from orders where id='"+ids[i]+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					if(orders.getState()==2){
							String sqlu = "update orders set state='3',adminByDeliveryId='"+admin.getAdminName()+"',deliveryTime='"+date+"' where id='"+ids[i]+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqlu);
							message= message+orders.getOrderId()+";";
					}else{
						error = orders.getOrderId()+";"+error;
					}
					}else{
						error2 = ids[i]+";"+error2;
					}
				conn.commit();
				conn.setAutoCommit(autoCommit);
				}
				if(error1.equals("")&&error.equals("")&error2.equals("")){
					message = "订单批量配货处理成功，批量处理订单编号分别为："+message;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					conn.rollback();
					if(error.equals(""))
					message = message+"以下订单当前状态不在你的确定范围内："+error;
					if(error1.equals(""))
					message = message+"以下店铺信息获取失败："+error1;
					if(error2.equals(""))
					message = message+"以下订单信息获取失败："+error2;
				}	
				
				}else {
					message= "未获取到需要确认通过的相关订单信息！";
				}
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void admin_order_delivery_all_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String error="";
		String error1="";
		String error2="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String[] ids = request.getParameterValues("ids");
				if(ids!=null){
							
				for(int i=0;i<ids.length;i++){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String sql ="select * from orders where id='"+ids[i]+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					if(orders.getState()==2){
							
							String sql1 ="select * from branch where code='"+orders.getUserId()+"' for update";
							stmt= db.getStmtread();
							rs =stmt.executeQuery(sql1);
							if(rs.next()){
								Branch branch = new Branch();
								branch.setId(rs.getInt("id"));
								branch.setCode(rs.getString("code"));
								branch.setName(rs.getString("name"));
								branch.setCredit(rs.getDouble("credit"));
								branch.setTel(rs.getString("tel"));
								double creditUsed = rs.getDouble("credit_used");
								String sqlu = "update orders set state='1',adminByDeliveryId='"+admin.getAdminName()+"',deliveryTime='"+date+"' where id='"+ids[i]+"'";
								stmt = conn.createStatement();
								stmt.executeUpdate(sqlu);
								String sql4 = "update branch set credit_used='"+ArithUtil.add(creditUsed, orders.getPrice())+"' where code='"+orders.getUserId()+"'";
								stmt = conn.createStatement();
								stmt.executeUpdate(sql4);
								String sql5 = cs.getCreditDetail(branch, orders.getPrice(), ArithUtil.sub(branch.getCredit(),ArithUtil.add(creditUsed, orders.getPrice())), 2,orders.getOrderId(), date);
								stmt = conn.createStatement();
								stmt.executeUpdate(sql5);
								message= message+orders.getOrderId()+";";
							}else{
								error1 = orders.getUserId()+";"+error1;
							}
						}else{
							error = orders.getOrderId()+";"+error;
						}
						}else{
							error2 = ids[i]+";"+error2;
						}
					conn.commit();
					conn.setAutoCommit(autoCommit);
					}
					if(error1.equals("")&&error.equals("")&error2.equals("")){
						
					message = "订单批量配货退回处理成功，批量处理订单编号分别为："+message;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					conn.rollback();
					if(error.equals(""))
					message = message+"以下订单当前状态不在你的确定范围内："+error;
					if(error1.equals(""))
					message = message+"以下店铺信息获取失败："+error1;
					if(error2.equals(""))
					message = message+"以下订单信息获取失败："+error2;
				}	
				
				}else {
					message= "未获取到需要退回的相关订单信息！";
				}
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void admin_order_review_all_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String error="";
		String error1="";
		String error2="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String[] ids = request.getParameterValues("ids");
				if(ids!=null){
							
				for(int i=0;i<ids.length;i++){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String sql ="select * from orders where id='"+ids[i]+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					if(orders.getState()==3){
							String sqlu = "update orders set state='4',adminByReviewerId='"+admin.getAdminName()+"',reviewerTime='"+date+"' where id='"+ids[i]+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqlu);
							message= message+orders.getOrderId()+";";
					}else{
						error = orders.getOrderId()+";"+error;
					}
					}else{
						error2 = ids[i]+";"+error2;
					}
				conn.commit();
				conn.setAutoCommit(autoCommit);
				}
				if(error1.equals("")&&error.equals("")&error2.equals("")){
					message = "订单批量处理成功，批量处理订单编号分别为："+message;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					conn.rollback();
					if(error.equals(""))
					message = message+"以下订单当前状态不在你的审核范围内："+error;
					if(error1.equals(""))
					message = message+"以下店铺信息获取失败："+error1;
					if(error2.equals(""))
					message = message+"以下订单信息获取失败："+error2;
				}	
				
				}else {
					message= "未获取到需要确认通过的相关订单信息！";
				}
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void admin_order_review_all_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String error="";
		String error1="";
		String error2="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String[] ids = request.getParameterValues("ids");
				if(ids!=null){
							
				for(int i=0;i<ids.length;i++){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String sql ="select * from orders where id='"+ids[i]+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					if(orders.getState()==3){
							String sqlu = "update orders set state='2',adminByReviewerId='"+admin.getAdminName()+"',reviewerTime='"+date+"' where id='"+ids[i]+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqlu);
							message= message+orders.getOrderId()+";";
					}else{
						error = orders.getOrderId()+";"+error;
					}
					}else{
						error2 = ids[i]+";"+error2;
					}
				conn.commit();
				conn.setAutoCommit(autoCommit);
				}
				if(error1.equals("")&&error.equals("")&error2.equals("")){
					message = "订单批量审核退回处理成功，批量处理订单编号分别为："+message;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					conn.rollback();
					if(error.equals(""))
					message = message+"以下订单当前状态不在你的审核范围内："+error;
					if(error1.equals(""))
					message = message+"以下店铺信息获取失败："+error1;
					if(error2.equals(""))
					message = message+"以下订单信息获取失败："+error2;
				}	
				
				}else {
					message= "未获取到需要退回的相关订单信息！";
				}
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message=  "数据操作异常，请重试！";
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
				db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	
	
	protected void admin_order_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setInventoryId(rs.getInt("inventoryId"));
					orders.setTag(rs.getInt("tag"));
					if(orders.getState()==3){
						String sqlu  = "update orders set state='4',adminByReviewerId='"+admin.getAdminName()+"',reviewerTime='"+date+"' where orderId='"+id+"'";
						stmt= conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "订单出库审核通过，订单编号为："+id;
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else{
						message= "当前状态不在你的确认范围内，请重新确认！";
						conn.rollback();
					}
				}else{
					message= "未查询到需要确认的订单信息，请重新确认！";
					conn.rollback();
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
		
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	
	protected void admin_order_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
	
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					
					if(orders.getState()==3){
						String sqlu = "update orders set state='2',adminByReviewerId='"+admin.getAdminName()+"',reviewerTime='"+date+"' where orderId='"+id+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "订单回退，订单编号为："+id;
					}else{
						message= "当前状态不在你的确认范围内，请重新确认！";
					}
				}else{
					message= "未查询到需要确认的订单信息，请重新确认！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	
	
	
	protected void admin_order_delivery_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date = new Timestamp(new Date().getTime());
				String sql ="select * from orders where orderId='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setFee(rs.getDouble("fee"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					
					if(orders.getState()==2){
						String sqlu = "update orders set state='3',adminByDeliveryId='"+admin.getAdminName()+"',deliveryTime='"+date+"' where orderId='"+id+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "订单出库成功，订单编号为："+id;
							cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else{
						message= "当前状态不在你的确认范围内，请重新确认！";
					}
				
				}else{
					message= "未查询到需要确认的订单信息，请重新确认！";
				}
			
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
		
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_delivery(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"'  and  state='2' and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' order by id desc";
					
					}else{
					sql ="select * from orders where state='2'  and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					orders.setExpress(rs.getString("express"));
					orders.setFee(rs.getDouble("fee"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					
					orders.setTag(rs.getInt("tag"));
					result.add(orders);
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
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and  state='3'  and orderType like'%"+orderType+"%' and userId like'%"+userId+"%'  order by id desc";
					
					}else{
					sql ="select * from orders where state='3'  and orderType like'%"+orderType+"%' and userId like'%"+userId+"%' order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("orderId"));
					orders.setUserId(rs.getString("userId"));
					orders.setUserName(rs.getString("userName"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setOrderTime(rs.getTimestamp("orderTime"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setFee(rs.getDouble("fee"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					
					orders.setTag(rs.getInt("tag"));
					sum[0] = sum[0]+rs.getDouble("price");
					sum[1] = sum[1]+rs.getDouble("pv");
					result.add(orders);
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
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_review.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"' and num>'0'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setUnit(rs.getString("unit"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<OrderDetail> coll = new ArrayList<OrderDetail>();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					request.setAttribute("sys_order", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"' and num>'0'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setUnit(rs.getString("unit"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void order_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> coll = new ArrayList<Product>();
		Collection coll_pt = new ArrayList();
		List<Product> plist = new ArrayList<Product>();
		List<Product> plist1 = new ArrayList<Product>();
		String ids = StringUtil.notNull(request.getParameter("oid"));
		String idstr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		double total_price=0;
		request.getSession().removeAttribute("shop_cart_list");
		 request.getSession().removeAttribute("shop_cart_list_1");
		request.getSession().removeAttribute("sys_order");
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
		try {
			if(user!=null){
				if(!(ids.equals(""))){
			if(db.createConn()){
				if(StringUtil.notNull(idstr).equals("")) idstr="0";
				String sql ="select * from orders where orderId='"+ids+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setTag(rs.getInt("tag"));
					orders.setModifyTag(rs.getInt("modify_tag"));
					request.getSession().setAttribute("sys_order", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						Product od = new Product();
						od.setId(rs.getInt("pid"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setType(rs.getString("type"));
						od.setProductType(rs.getString("productType"));
						od.setNum(rs.getDouble("num"));
						od.setOldNum(rs.getDouble("num"));
						plist.add(od);
					}
				
					request.setAttribute("coll", coll);
					String sql1 = "select * from productType where  state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						int id = rs.getInt("id");
						ProductType pt = new ProductType();
						pt.setId(rs.getInt("id"));
						pt.setTypeName(rs.getString("typeName"));
						coll_pt.add(pt);
					}
					request.setAttribute("coll_pt", coll_pt);
					request.setAttribute("idstr", idstr);
					
					String sql2 = "select * from product where  type like '%1%' and  state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setType(rs.getString("type"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setNum(0);
						product.setTotalNum(rs.getDouble("num"));
						product.setImageUrl(StringUtil.notNull(rs.getString("imageUrl")));
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						if(plist!=null){
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==product.getId()){
									product.setNum(plist.get(i).getNum());
									plist.get(i).setFeatures(rs.getString("features"));
									plist.get(i).setProductId(rs.getString("productId"));
									plist.get(i).setType(rs.getString("type"));
									plist.get(i).setProductType(rs.getString("productType"));
									plist.get(i).setSpecification(rs.getString("specification"));
									plist.get(i).setPrice(rs.getDouble("price"));
									plist.get(i).setTotalNum(rs.getDouble("num"));
									plist.get(i).setImageUrl(StringUtil.notNull(rs.getString("imageUrl")));
									plist.get(i).setState(rs.getInt("state"));
									plist.get(i).setEntryTime(rs.getTimestamp("entryTime"));
									plist.get(i).setEndTime(rs.getTimestamp("endTime"));
									plist.get(i).setTag(1);
									total_price = ArithUtil.add(total_price, ArithUtil.mul(plist.get(i).getNum(), product.getPrice()));
								}
							}
						}
						coll.add(product);
					}
						request.setAttribute("coll", coll);
						request.setAttribute("total_price", total_price);
						request.getSession().setAttribute("shop_cart_list", plist);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
				}else {
					message = "数据库连接已断开！";
				}
				}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_divery_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					orders.setTag(rs.getInt("tag"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void order_divery_confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				conn= db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					
					orders.setTag(rs.getInt("tag"));
					orders.setOrderType(rs.getInt("orderType"));
					orders.setState(rs.getInt("state"));
					if(orders.getState()==4){
					User user1 = us.getSaveUser(conn, orders.getUserByCenterId());
					if(user1!=null){
						if(user.getCode().equals(user1.getUserId())){
						String sql2= "update orders set state='5' where orderId='"+orderId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql2);
						stmt.close();
						message = "服务中心订单确认发货成功，订单编号为："+orderId+"！";
						}else {
							message = "你没有权限对该订单进行确认操作！";
						}
					}else {
						message = "服务店会员信息获取失败，请重试！";
					}
					}else {
						message = "订单当前状态不能进行确认发货操作，请重试！";
					}
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void order_recive_confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				conn= db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setFee(rs.getDouble("fee"));
					orders.setPv(rs.getDouble("pv"));
					orders.setState(rs.getInt("state"));
					if(orders.getState()==5){
						String sql2= "update orders set state='6' where orderId='"+orderId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql2);
						message = "订单确认收货成功，订单编号为："+orderId+"！";
						
						
					}else message = "订单当前状态不能进行确认发货操作，请重试！";
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void order_divery_tag(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				conn= db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				String sql ="select * from orders where orderId='"+idStr+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					
					orders.setTag(rs.getInt("tag"));
					User user1 = us.getSaveUser(conn, orders.getUserByCenterId());
					if(user1!=null){
						if(orders.getState()==1&orders.getTag()==0){
						String sql2= "update orders set state='2', tag='1',confirmTime='"+date+"' where orderId='"+orderId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql2);
						stmt.close();
						message = "订单转为公司发货成功，订单编号为："+orderId+",请查看及时联系公司客服进行发货！";
						}else {
							message = "当前状态不能转为公司发货，请重试！";
						}
					}else {
						message = "服务店会员信息获取失败，请重试！";
					}
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void order_tag(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String tag = StringUtil.notNull(request.getParameter("tag"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				conn= db.getConnection();
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
				String sql ="select * from orders where orderId='"+idStr+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					String orderId =rs.getString("orderId");
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					
					orders.setTag(rs.getInt("tag"));
					orders.setDeliveryTag(rs.getInt("deliveryTag"));
					User user1 = us.getSaveUser(conn, orders.getUserByCenterId());
					if(user1!=null){
						if(!String.valueOf(orders.getDeliveryTag()).equals(tag)){
						String sql2= "update orders set  deliveryTag='"+tag+"' where orderId='"+orderId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql2);
						message = "订单标记修改成功，订单编号为："+orderId+"！";
						}else {
							message = "标记状态相同，请重试！";
						}
					}else {
						message = "服务店会员信息获取失败，请重试！";
					}
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void order_divery_out(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					request.setAttribute("orders", orders);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_out.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void order_divery_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String express = StringUtil.notNull(request.getParameter("express"));
		String expressNum = StringUtil.notNull(request.getParameter("expressNum"));
		String remark = StringUtil.notNull(request.getParameter("remark"));
		String message="";
		String message1="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 Timestamp date1 = new Timestamp(new Date().getTime());
				 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			String sql ="select * from orders where orderId='"+idStr+"' for update";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql);
			if(rs.next()){
				Order orders = new Order();
				orders.setId(rs.getInt("id"));
				orders.setOrderId(rs.getString("orderId"));
				orders.setUserId(rs.getString("userId"));
				orders.setUserName(rs.getString("userName"));
				orders.setOrderType(rs.getInt("orderType"));
				orders.setOrderTime(rs.getTimestamp("orderTime"));
				orders.setReceiver(rs.getString("receiver"));
				orders.setPhone(rs.getString("phone"));
				orders.setAddress(rs.getString("address"));
				orders.setState(rs.getInt("state"));
				orders.setPrice(rs.getDouble("price"));
				orders.setPv(rs.getDouble("pv"));
				orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
				orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
				orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
				orders.setExpress(rs.getString("express"));
				orders.setExpressNum(rs.getString("expressNum"));
				orders.setInventory(rs.getString("inventory"));
				orders.setConfirmTime(rs.getTimestamp("confirmTime"));
				orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
				
				List<OrderDetail> olist = new ArrayList<OrderDetail>();
				String sql1 ="select * from orderDetail where orderId='"+orders.getOrderId()+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					OrderDetail od = new OrderDetail();
					od.setProductId(rs.getString("productId"));
					od.setProductName(rs.getString("productName"));
					od.setNum(rs.getDouble("num"));
					olist.add(od);
				}
				List<String> slist = new ArrayList<String>();
				if(olist.size()>0){
					for(int i=0;i<olist.size();i++){
						String sql2 = "select * from inventory_center where productId='"+olist.get(i).getProductId()+"' for update";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql2);
						if(rs.next()){
							double pnum = rs.getDouble("num");
							if(ArithUtil.sub(olist.get(i).getNum(),pnum)>=0){
								String sql3 = "update inventory_center set num='"+ArithUtil.sub(olist.get(i).getNum(),pnum)+"' where productId='"+olist.get(i).getProductId()+"'";
								slist.add(sql3);
							}else{
								message1=message1+olist.get(i).getProductId()+"("+olist.get(i).getProductName()+"),";
							}
						}else{
							message1=message1+olist.get(i).getProductId()+"("+olist.get(i).getProductName()+"),";
						}
					}
				}
				
				if(orders.getUserByCenterId().equals(user.getCode())){
					if(orders.getState()==2){
						if(message1.equals("")){
						String sqlu = "update orders set state='3',express='"+express+"',expressNum='"+expressNum+"',deliveryTime='"+date+"',remark='"+remark+"' where orderId='"+idStr+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						if(slist.size()>0){
							 stmt = conn.createStatement();
								for(int i=0;i<slist.size();i++){
									stmt.addBatch(slist.get(i));
									 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
										 stmt.executeBatch();
										 stmt.close();
										 stmt = conn.createStatement();
									 }
								}
							}
						message= "订单发货成功，订单编号为："+idStr;
						}else{
							message = "产品"+message1+"等库存不足，请及时配货！";
						}
					}else{
					message= "当前状态不在你的确认范围内，请重新确认！";
				}
				}else {
					message = "你没有权限进行该操作！";
				}
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			conn.commit();
			conn.setAutoCommit(autoCommit);
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
					
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
						
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_detail_all(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
					if(type==2){
							String pId = rs.getString("productId");
							double num = rs.getDouble("num");
							String oId = rs.getString("orderId");
							String sqlp = "select * from productDetail where productId='"+pId+"'";
							Statement stmt1 = conn.createStatement();
							ResultSet rs1 = stmt1.executeQuery(sqlp);
							while(rs1.next()){
								OrderDetail od = new OrderDetail();
								od.setOrderId(oId);
								od.setProductId(rs1.getString("productId"));
								od.setProductName(rs1.getString("productName"));
								od.setProductPrice(rs1.getDouble("productPrice"));
								od.setType(rs1.getInt("type"));
								od.setProductType(rs1.getString("productType"));
								od.setProductPv(rs1.getDouble("productPv"));
								od.setPrice(num*rs1.getDouble("productPrice"));
								od.setPv(num*rs1.getDouble("productPv"));
								od.setNum(num*rs1.getDouble("num"));
								coll.add(od);
							}
							rs1.close();
							stmt1.close();
						}else{
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
						}
						
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_detail_delivery(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==2){
								String pId = rs.getString("productId");
								double num = rs.getDouble("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getDouble("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getDouble("num"));
									od.setNum(num*rs1.getDouble("num"));
									coll.add(od);
								}
								if(rs1!=null)
								rs1.close();
								if(stmt1!=null)
								stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getDouble("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_delivery.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_detail_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setFee(rs.getDouble("fee"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==2){
								String pId = rs.getString("productId");
								double num = rs.getDouble("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getDouble("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getDouble("num"));
									od.setNum(num*rs1.getDouble("num"));
									coll.add(od);
								}
								if(rs1!=null)
									rs1.close();
									if(stmt1!=null)
									stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getDouble("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_review.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_delivery_express(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					request.setAttribute("orders", orders);
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqls ="select * from inventory";
					stmt = db.getStmt();
					rs = stmt.executeQuery(sqls);
					while(rs.next()){
						String name = rs.getString("inventoryName");
						coll.add(name);
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_express.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_detail_confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setInventory(rs.getString("inventory"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						int type = rs.getInt("type");
						if(type==2){
								String pId = rs.getString("productId");
								double num = rs.getDouble("num");
								String oId = rs.getString("orderId");
								String sqlp = "select * from productDetail where pId='"+pId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								while(rs1.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(oId);
									od.setProductId(rs1.getString("productId"));
									od.setProductName(rs1.getString("productName"));
									od.setProductPrice(rs1.getDouble("productPrice"));
									od.setType(1);
									od.setProductType(rs1.getString("productType"));
									od.setProductPv(rs1.getDouble("productPv"));
									od.setPrice(num*rs1.getDouble("productPrice")*rs1.getDouble("num"));
									od.setPv(num*rs1.getDouble("productPv")*rs1.getDouble("num"));
									od.setNum(num*rs1.getDouble("num"));
									coll.add(od);
								}
								if(rs1!=null)
									rs1.close();
									if(stmt1!=null)
									stmt1.close();
							}else{
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("orderId"));
							od.setProductId(rs.getString("productId"));
							od.setProductName(rs.getString("productName"));
							od.setProductPrice(rs.getDouble("productPrice"));
							od.setType(rs.getInt("type"));
							od.setProductType(rs.getString("productType"));
							od.setProductPv(rs.getDouble("productPv"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getDouble("num"));
							coll.add(od);
							}
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_confirm.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void order_emoney_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		Collection coll = new ArrayList();
		Collection coll_adr = new ArrayList();
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
					String sql = "select * from product where productType like '%复消产品%' and (team like '%"+Constants.TOP_TEAM+"%' or team like '%"+user.getTeam()+"%') and state='1' order by id asc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setType(rs.getInt("type"));
						product.setFee(rs.getDouble("fee"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setState(rs.getString("state"));
						coll.add(product);
					} 
					request.setAttribute("coll", coll);	
					String sqla = "select * from address where userId='"+user.getCode()+"' order by id desc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sqla);
					while(rs.next()){
						Address adr = new Address();
						adr.setId(rs.getInt("id"));
						adr.setAddress(rs.getString("address"));
						adr.setReceiver(rs.getString("receiver"));
						adr.setPhone(rs.getString("phone"));
						coll_adr.add(adr);
					} 
					request.setAttribute("coll_adr", coll_adr);		
					int token_new = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token_new));
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
			}
						else {
							message= "用户未登陆，请重新登陆！";
						
						}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_add.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_emoney_comments(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		String message="";
		DBConn db = new DBConn();
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(StringUtil.notNull(token_old).equals(token)){
			if(user!=null){
					if(db.createConn()){
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						String[] numstr = request.getParameterValues("numstr");
						String[] pid = request.getParameterValues("pid");
						String addressId = StringUtil.notNull(request.getParameter("addressId"));
						String centerId = StringUtil.notNull(request.getParameter("centerId"));
						String tag = StringUtil.notNull(request.getParameter("tag"));
						Center center = cs.getCenter(conn, centerId);
						String UserByCenterId ="";
						int centerById= 0;
					if(tag.equals("2")){
						if(center==null) message = "店铺信息不存在！";
						else if(center.getState()!=2) message = "店铺状态异常！";
						else {
							UserByCenterId =center.getCenterId();
							centerById = center.getId();
						}
					}
					if(message.equals("")){	
						String sqla = "select * from address where id='"+addressId+"'";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sqla);
					Order orders = new Order();
					if(rs.next()){
						orders.setAddress(rs.getString("address"));
						orders.setReceiver(rs.getString("receiver"));
						orders.setPhone(rs.getString("phone"));
					} 
					List<OrderDetail> olist = new ArrayList<OrderDetail>();
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					double totalprice = 0;
					double totalpv = 0;
					double totalfee = 0;
					String rid = cs.createOrderId(date);
					for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if(Integer.valueOf(numstr[i])>0){
								String sql = "select * from product where id='"+Integer.valueOf(pid[i])+"'";
								stmt = db.getStmt();
								rs = stmt.executeQuery(sql);
								if(rs.next()){
								OrderDetail od = new OrderDetail();
								od.setOrderId(rid);
								od.setNum(Integer.valueOf(numstr[i]));
								od.setProductId(rs.getString("productId"));
								od.setProductName(rs.getString("productName"));
								od.setProductPrice(rs.getDouble("price"));
								od.setProductPv(rs.getDouble("pv"));
								od.setProductFee(rs.getDouble("fee"));
								od.setProductType(rs.getString("productType"));
								od.setType(rs.getInt("type"));
								od.setPrice(ArithUtil.mul(rs.getDouble("price"), Integer.valueOf(numstr[i])));
								od.setPv(ArithUtil.mul(rs.getDouble("pv") , Integer.valueOf(numstr[i])));
								od.setFee(ArithUtil.mul(rs.getDouble("fee") , Integer.valueOf(numstr[i])));
								olist.add(od);
								totalprice = ArithUtil.add(totalprice ,ArithUtil.mul(rs.getDouble("price"), Integer.valueOf(numstr[i])));
								totalpv = ArithUtil.add(totalpv ,ArithUtil.mul(rs.getDouble("pv"),Integer.valueOf(numstr[i])));
								totalfee = ArithUtil.add(totalfee ,ArithUtil.mul(rs.getDouble("fee"),Integer.valueOf(numstr[i])));
								}
							}
						}
					}
					if(totalprice>=200) totalfee = 0;
					else if(tag.equals("2")) totalfee = 0;
					orders.setOrderId(rid);
					orders.setOrderType(2);
					orders.setPrice(totalprice);
					orders.setPv(totalpv);
					orders.setFee(totalfee);
					orders.setTag(Integer.valueOf(tag));
					orders.setUserByCenterId(UserByCenterId);
					orders.setCenterId(centerById);
					orders.setState(1);
					request.getSession().setAttribute("olist", olist);
					request.getSession().setAttribute("orders", orders);
						}
					 conn.commit();
						conn.setAutoCommit(autoCommit);
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
					}
						else {
							message= "用户未登陆，请重新登陆！";
				}
					
			}else{
				message= "请勿重复提交数据！";
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_comments.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_emoney_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		String message="";
		DBConn db = new DBConn();
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		Order orders = (Order)request.getSession().getAttribute("orders");
		List<OrderDetail> olist = (List<OrderDetail>)request.getSession().getAttribute("olist");
		try {
			if(StringUtil.notNull(token_old).equals(token)){
			if(user!=null){
					if(db.createConn()){
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						String comments = StringUtil.notNull(request.getParameter("comments"));
						orders.setComments(comments);
					
					List<String> slist = new ArrayList<String>();
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					for (int i = 0; i < olist.size(); i++) {
							String sqld = "insert into orderDetail(orderId,productId,productName,productPrice,productPv,price,pv,product_fee,fee,num,productType,type) values('"+olist.get(i).getOrderId()+
										 "','"+olist.get(i).getProductId()+"','"+olist.get(i).getProductName()+"','"+olist.get(i).getProductPrice()+"','"+olist.get(i).getProductPv()+"','"
										+olist.get(i).getPrice()+"','"+olist.get(i).getPv()+"','"+olist.get(i).getProductFee()+"','"+olist.get(i).getFee()+"','"+olist.get(i).getNum()+"','"+olist.get(i).getProductType()+"','"+olist.get(i).getType()+"')"; 
								slist.add(sqld);
							
					}
					
					String sqls = "insert into orders(orderId,uId,userId,userName,userByDeclarationId,orderType,price,pv,fee,receiver,phone,address,orderTime,confirmTime,userByCenterId,centerId,state,tag,comments) "
							+ "values('"+orders.getOrderId()+"','"+user.getId()+"','"+user.getCode()+"','"+user.getUserName()+"','"+user.getCode()+"','"+orders.getOrderType()+"','"+orders.getPrice()+"','"+orders.getPv()+"','"+orders.getFee()+"','"+orders.getReceiver()+
							"','"+orders.getPhone()+"','"+orders.getAddress()+"','"+date+"','"+date+"','"+orders.getUserByCenterId()+"','"+orders.getCenterId()+"','"+orders.getState()+"','"+orders.getTag()+"','"+orders.getComments()+"')";
					slist.add(sqls);
					String sqlu ="select * from users where userId='"+user.getCode()+"' for update";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sqlu);
					if(rs.next()){
						double emoney = rs.getDouble("emoney");
						double totalpv  = orders.getPv();
						double totalprice = ArithUtil.add(orders.getPrice(), orders.getFee());
						int id = rs.getInt("id");
						String uid = rs.getString("userId");
						double pv = ArithUtil.mul(totalpv, 1);
						if(ArithUtil.sub(emoney,totalprice)>=0){
									if(cs.saveEmoneyDetail(conn, user, totalprice, ArithUtil.sub(emoney,totalprice), 2, Constants.MONEY_DETAIL_TYPE_KEY_15, Constants.MONEY_DETAIL_TYPE_KEY_15, date)){
									if(slist.size()>0){
								String sqlu2 = "update users set emoney='"+ArithUtil.sub(emoney,totalprice)+"' where userId='"+uid+"'";
								 stmt =conn.createStatement();
								 stmt.addBatch(sqlu2);
								 for(int j=0;j<slist.size();j++){
								 stmt.addBatch(slist.get(j));
								 }
								 stmt.executeBatch();
								 user = us.findById(conn, uid);
								 request.getSession().setAttribute("sys_user", user);
								 message= "复消购物订单保存成功，订单编号为"+orders.getOrderId()+"，请再订单管理中查看详情！";
							}else{
								 message= "订单详情信息保存失败，请重试！";
								 conn.rollback();
							}
									}else{
								 message= "报单券帐户明细保存失败，请重试！";
								 conn.rollback();
							}
							
						}else{
							 message= "电子券账户余额不足，请充值后再购买！";
						}
					
					} else {
						message = "未获取到会员的相应信息，请核对！";
					}
					 conn.commit();
						conn.setAutoCommit(autoCommit);
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
					}
						else {
							message= "用户未登陆，请重新登陆！";
				}
					
			}else{
				message= "请勿重复提交数据！";
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	
*/

	

	/*
	public void admin_order_back(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
					if(db.createConn()){
						String idStr = StringUtil.notNull(request.getParameter("id"));
						if(!idStr.equals("")){		
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						
						String sql = "select * from orders where orderId='"+idStr+"' for update";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						String orderId = rs.getString("orderId");
						String userId= rs.getString("userId");
						User user = new User();
						user.setId(rs.getInt("uId"));
						user.setUserId(userId);
						user.setUserName(rs.getString("userName"));
						int orderType =rs.getInt("orderType");
						int state =rs.getInt("state");
						double totalprice= rs.getDouble("price");
						Timestamp date = new Timestamp(new Date().getTime());
						Timestamp orderTime =rs.getTimestamp("orderTime");
						Settle st = cs.getSettle(conn, orderTime);
						if(st==null){
								if(state==1){
									if(orderType==1){
										message= "该类型订单不能退单，如有需要，请与客服联系！";
									}else{
										String sqls ="select * from users where userId='"+userId+"' for update";
										stmt = db.getStmtread();
										rs = stmt.executeQuery(sqls);
										if(rs.next()){
												double emoney = rs.getDouble("emoney");
												double smoney = rs.getDouble("smoney");
												double dmoney = rs.getDouble("dmoney");
												String sqlu = "";
											if(orderType==2){
											  sqlu  = "update users set emoney='"+ArithUtil.add(emoney,totalprice)+"' where userId='"+user.getCode()+"'";
											  cs.saveEmoneyDetail(conn, user, totalprice, ArithUtil.add(emoney,totalprice), 1, Constants.MONEY_DETAIL_TYPE_KEY_14,  Constants.MONEY_DETAIL_TYPE_KEY_14, date);
											}else if(orderType==3){
												  sqlu  = "update users set dmoney='"+ArithUtil.add(dmoney,totalprice)+"' where userId='"+user.getCode()+"'";
												  cs.saveDmoneyDetail(conn, user, totalprice, ArithUtil.add(dmoney,totalprice), 1, Constants.MONEY_DETAIL_TYPE_KEY_14,  Constants.MONEY_DETAIL_TYPE_KEY_14, date);
											}else if(orderType==4){
												  sqlu  = "update users set smoney='"+ArithUtil.add(smoney,totalprice)+"' where userId='"+user.getCode()+"'";
												  cs.saveSmoneyDetail(conn, user, totalprice, ArithUtil.add(smoney,totalprice), 1, Constants.MONEY_DETAIL_TYPE_KEY_14,  Constants.MONEY_DETAIL_TYPE_KEY_14, date);
											}
											if(!sqlu.equals("")){
											String sqlo ="update orders set state='0' where orderId='"+orderId+"'";
											stmt = conn.createStatement();
											stmt.addBatch(sqlo);
											stmt.addBatch(sqlu);
											stmt.executeBatch();
											message= "订单退单成功，订单号为："+orderId+"，请再订单列别查看订单当前状态！";
											
											}else message= "未找到订单对应的状态信息，请重试！";
											
										}else message= "未找到订单对应的客户信息，请重试！";
									}
									}else message= "订单当前状态不能退单，如有需要，请与客服联系！";
							
						}else message= "订单已经超过了当周结算时间，不能退单！";
						
						}else message= "未能查找到需要操作的相应订单信息，请重试！";
					
					conn.commit();
					conn.setAutoCommit(autoCommit);
					}else message= "未能获得需要操作的相应订单的ID信息，请重试！";
					
				}else {
				 message= "数据库连接断开，请重试！";
				}
				}
						else {
							message= "用户未登陆，请重新登陆！";
				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	*/
	public void admin_order_adr_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
					if(db.createConn()){
						String orderId = StringUtil.notNull(request.getParameter("id"));
					String sql = "select * from orders where orderId='"+orderId+"'";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						Order od = new Order();
						od.setId(rs.getInt("id"));
						od.setOrderId(rs.getString("orderId"));
						od.setAddress(rs.getString("address"));
						od.setReceiver(rs.getString("receiver"));
						od.setPhone(rs.getString("phone"));
						request.setAttribute("orders", od);	
						int token_new = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token_new));
					}else{
						message= "未获取到相应的订单信息，请重试！";
					}
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
			}
						else {
							message= "用户未登陆，请重新登陆！";
						
						}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_adr_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_adr_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_adr_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		
		try {
			if(StringUtil.notNull(token_old).equals(token)){
			if(admin!=null){
					if(db.createConn()){
						String idStr = StringUtil.notNull(request.getParameter("id"));
						String address = StringUtil.notNull(request.getParameter("address"));
						String receiver = StringUtil.notNull(request.getParameter("receiver"));
						String phone = StringUtil.notNull(request.getParameter("phone"));
					if(!idStr.equals("")){		
						System.out.println("id:"+idStr);
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						String sql = "select * from orders where orderId='"+idStr+"' for update";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							String userId= rs.getString("userId");
							sql = "update orders set address='"+address+"',receiver='"+receiver+"',phone='"+phone+"' where orderId='"+idStr+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sql);
							 Timestamp date1 = new Timestamp(new Date().getTime());
							 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
							 stmt = conn.createStatement();
							 String sqli = "insert into address(userId,receiver,phone,address,tag,state,entryTime,endTime) values('"+userId
									 +"','"+receiver+"','"+phone+"','"+address+"','1','1','"+date+"','"+date+"')";
							 stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							message= "订单更新收货地址修改成功，订单号为："+idStr+"，请再订单列别查看订单当前状态！";
												
						}else{
							message= "未找到订单对应的状态信息，请重试！";
						}
					conn.commit();
					conn.setAutoCommit(autoCommit);
					}else{
						message= "未能获得需要操作的相应订单的ID信息，请重试！";
					}
					
				}else {
				 message= "数据库连接断开，请重试！";
				}
		}else {message= "用户未登陆，请重新登陆！";
				}
			
			}else{
				message= "请勿重复提交数据！";
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_adr_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void order_adr_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
						String orderId = StringUtil.notNull(request.getParameter("id"));
					String sql = "select * from orders where orderId='"+orderId+"'";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						Order od = new Order();
						od.setId(rs.getInt("id"));
						od.setOrderId(rs.getString("orderId"));
						od.setAddress(rs.getString("address"));
						od.setReceiver(rs.getString("receiver"));
						od.setPhone(rs.getString("phone"));
						request.setAttribute("orders", od);	
						int token_new = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token_new));
					}else{
						message= "未获取到相应的订单信息，请重试！";
					}
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
			}
						else {
							message= "用户未登陆，请重新登陆！";
						
						}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_adr_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_adr_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		String message="";
		DBConn db = new DBConn();
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		
		try {
			if(StringUtil.notNull(token_old).equals(token)){
			if(user!=null){
					if(db.createConn()){
						String idStr = StringUtil.notNull(request.getParameter("id"));
						String address = StringUtil.notNull(request.getParameter("address"));
						String receiver = StringUtil.notNull(request.getParameter("receiver"));
						String phone = StringUtil.notNull(request.getParameter("phone"));
					if(!idStr.equals("")){		
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						String sql = "select * from orders where orderId='"+idStr+"' for update";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							String userId= rs.getString("userId");
							sql = "update orders set address='"+address+"',receiver='"+receiver+"',phone='"+phone+"' where orderId='"+idStr+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sql);
							 Timestamp date1 = new Timestamp(new Date().getTime());
							 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
							 stmt = conn.createStatement();
							 String sqli = "insert into address(userId,receiver,phone,address,tag,state,entryTime,endTime) values('"+userId
									 +"','"+receiver+"','"+phone+"','"+address+"','1','1','"+date+"','"+date+"')";
							 stmt = conn.createStatement();
							stmt.executeUpdate(sqli);
							message= "订单更新收货地址修改成功，订单号为："+idStr+"，请再订单列别查看订单当前状态！";
												
						}else{
							message= "未找到订单对应的状态信息，请重试！";
						}
					conn.commit();
					conn.setAutoCommit(autoCommit);
					}else{
						message= "未能获得需要操作的相应订单的ID信息，请重试！";
					}
					
				}else {
				 message= "数据库连接断开，请重试！";
				}
		}else {message= "用户未登陆，请重新登陆！";
				}
			
			}else{
				message= "请勿重复提交数据！";
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void order_new_branch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message ="";
		List<Product> plist  = new ArrayList<Product>();
		try {
		if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from branch where id='"+branch.getId()+"' and tag='1'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				if(rs.next()){
				String sql2 = "select * from product where type like'%2%' and state='1'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql2);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("productName"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("productId"));
					product.setdType(rs.getString("d_type"));
					product.setProductType(rs.getString("productType"));
					product.setSpecification(rs.getString("specification"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setNum(rs.getInt("limit_num"));
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("totalNum"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					plist.add(product);
				}
				
				String sql1 = "select * from branch where id='"+branch.getId()+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql1);
				if(rs.next()){
					Address adr = new Address();
					adr.setId(rs.getInt("id"));
					adr.setPhone(rs.getString("tel"));
					adr.setReceiver(rs.getString("linkman"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("address"));
					request.getSession().setAttribute("sns_adr",adr);
				}
				request.getSession().setAttribute("sys_blist",plist);
				}else{
					message="店铺加盟订单已完结，请勿重复操作！";
				}
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_branch.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void order_new_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("sys_blist");
		String message ="";
		try {
		if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					String sql ="select * from branch where id='"+branch.getId()+"' and tag='1'  for update";
					stmt =conn.createStatement();
					rs= stmt.executeQuery(sql);
					if(rs.next()){
						Address adr = (Address)request.getSession().getAttribute("sns_adr");
			
				Timestamp date = new Timestamp(new Date().getTime());
				String orderId = StringUtil.parseToString(date, DateUtil.ymdhms);
				double price = 0;
				double pv=0;
				List<String> slist = new ArrayList<String>();
				for(int i=0;i<plist.size();i++){
					String sql1="insert into orderDetail(orderId,pid,productId,productName,productType,unit,productPrice,num,price,image_url)"
							+ " values('"+orderId+"','"+plist.get(i).getId()+"','"+plist.get(i).getProductId()+"','"+plist.get(i).getProductName()+"','"+plist.get(i).getProductType()+"','"+plist.get(i).getUnit()
							+"','"+plist.get(i).getPrice()+"','"+plist.get(i).getNum()+"','"+plist.get(i).getPrice()*plist.get(i).getNum()+"','"+plist.get(i).getImageUrl()+"');";
					slist.add(sql1);
				}
			
				String sql2= "insert into orders(orderId,uId,userId,userName,receiver,phone,address,"
						+ "price,pv,orderType,orderTime,state) "
						+ " values('"+orderId+"','"+branch.getId()+"','"
						+branch.getCode()+"','"+branch.getName()+"','"+adr.getReceiver()+"','"+adr.getPhone()
						+"','"+(adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAdr())+"','"+price+"','"+pv+"','2','"+date+"','1');";
				slist.add(sql2);
				String sql3 = "update branch set tag='0',end_time='"+date+"' where id='"+branch.getId()+"'";
				slist.add(sql3);
				if(slist.size()>0){
							for(int i=0;i<slist.size();i++){
								stmt.addBatch(slist.get(i));
								 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
									 stmt.executeBatch();
									 stmt = conn.createStatement();
								 }
							}
						}
					}
					message="订单提交成功！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_order_new_branch_shop_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		Collection coll = new ArrayList();
		String message="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][8].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					String sql = "select * from product where type like '%2%' and state='1' order by id asc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setState(rs.getInt("state"));
						coll.add(product);
					} 
					request.setAttribute("coll", coll);	
					int token_new = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token_new));
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
			}else{
				message= "您没有该操作权限，如有需要请与系统管理员联系！";
			}
			}else {
							message= "用户未登陆，请重新登陆！";
						
						}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_branch_shop_add.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_branch_shop_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_new_branch_shop_save(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String error="";
		List<OrderDetail> olist = new ArrayList<OrderDetail>();
		List<String> slist = new ArrayList<String>();
		try {
			
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][8].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						String[] numstr = request.getParameterValues("numstr");
						String[] pid = request.getParameterValues("pid");
						String code = request.getParameter("code");
						
						String sqla = "select * from branch where code='"+code+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sqla);
					Order orders = new Order();
					if(rs.next()){
						orders.setuId(rs.getInt("id"));
						orders.setUserId(rs.getString("code"));
						orders.setUserName(rs.getString("name"));
						orders.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						orders.setReceiver(rs.getString("linkman"));
						orders.setPhone(rs.getString("tel"));
					 Timestamp date = new Timestamp(new Date().getTime());
					double totalprice = 0;
					String rid = cs.createOrderId(date);
					for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if(Integer.valueOf(numstr[i])>0){
								String sql = "select * from product where id='"+Integer.valueOf(pid[i])+"' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								if(rs.next()){
								OrderDetail od = new OrderDetail();
								od.setPid(rs.getInt("id"));
								od.setOrderId(rid);
								od.setNum(Double.valueOf(numstr[i]));
								od.setProductId(rs.getString("productId"));
								od.setProductName(rs.getString("productName"));
								od.setSpecification(rs.getString("specification"));
								od.setProductPrice(rs.getDouble("price"));
								od.setProductType(rs.getString("productType"));
								od.setPrice(ArithUtil.mul(rs.getDouble("price"), Double.valueOf(numstr[i])));
								olist.add(od);
								totalprice = ArithUtil.add(totalprice ,ArithUtil.mul(rs.getDouble("price"), Double.valueOf(numstr[i])));
								String sqld = "insert into orderDetail(orderId,pid,productId,productName,specification,productPrice,price,num,productType) values('"+od.getOrderId()+
										"','"+od.getPid()+"','"+od.getProductId()+"','"+od.getProductName()+"','"+od.getSpecification()+"','"+od.getProductPrice()+"','"
										+od.getPrice()+"','"+od.getNum()+"','"+od.getProductType()+"')"; 
								slist.add(sqld);
								
									double num = rs.getDouble("num");
									double new_num = Double.valueOf(numstr[i]);
									if(ArithUtil.sub(num,new_num)<0){
										error = error+rs.getString("productId")+"，";
									}else{
									String sql3="update product set num='"+ArithUtil.sub(num,new_num)
													+"' where id='"+rs.getInt("id")+"'";
											slist.add(sql3);
									}
								}
							}
						}
					}
					orders.setOrderId(rid);
					orders.setOrderType(2);
					orders.setPrice(totalprice);
					orders.setState(1);
					String sqls = "insert into orders(orderId,uId,userId,userName,orderType,price,receiver,phone,address,orderTime,state) "
							+ "values('"+orders.getOrderId()+"','"+orders.getuId()+"','"+orders.getUserId()+"','"+orders.getUserName()+"','"+orders.getOrderType()+"','"+orders.getPrice()+"','"+orders.getReceiver()+
							"','"+orders.getPhone()+"','"+orders.getAddress()+"','"+date+"','"+orders.getState()+"')";
					
					slist.add(sqls);
					if(error.equals("")){
						if(slist.size()>0){
							for(int i=0;i<slist.size();i++){
								stmt.addBatch(slist.get(i));
								 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
									 stmt.executeBatch();
									 stmt = conn.createStatement();
								 }
							}
						}
						message=orders.getUserName()+"的新店订单保存成功";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else{
						message = error+"产品库存不足，请核对！";
					}
					
					}else{
						 message= "店铺信息信息获取失败，请重试！";
					}
					 conn.commit();
						conn.setAutoCommit(autoCommit);
					}else {
						 message= "数据库连接断开，请重试！";
							
						}
				}else {
				 message= "你没有权限进行该操作！";
					
				}
				}else{
					message= "请勿重复提交数据！";
				}
					}
						else {
							message= "用户未登陆，请重新登陆！";
				}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_new_branch_shop_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void product_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		String idstr = StringUtil.notNull(request.getParameter("id"));
		String typeName="";
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		try {
			if(db.createConn()){
				
			if(StringUtil.notNull(idstr).equals("")) idstr="0";
			String sql1 = "select * from productType where  state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			while(rs.next()){
				int id = rs.getInt("id");
				if(String.valueOf(id).equals(idstr)){
					typeName = rs.getString("typeName");
				}
				ProductType pt = new ProductType();
				pt.setId(rs.getInt("id"));
				pt.setTypeName(rs.getString("typeName"));
				coll_pt.add(pt);
			}
			request.setAttribute("idstr", idstr);
			request.setAttribute("coll_pt", coll_pt);
			
				String sql = "select * from product where  type like'%1%' and  productType like('%"+typeName+"%') and  state='1' order by id asc";
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				request.setAttribute("typeName", typeName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("productName"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("productId"));
					product.setdType(rs.getString("d_type"));
					product.setProductType(rs.getString("productType"));
					product.setSpecification(rs.getString("specification"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(0);
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("totalNum"));
					product.setLimitNum(rs.getInt("limit_num"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==product.getId()){
							product.setNum(plist.get(i).getNum());
						}
					}
					}
					coll.add(product);
				}
					request.setAttribute("coll", coll);
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
public void increate_p(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		try {
		//	if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String ids = StringUtil.notNull(request.getParameter("id"));
				String idstr = StringUtil.notNull(request.getParameter("idstr"));
				String typeName="";
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
				for(int i=0;i<plist.size();i++){
					if(plist.get(i).getId()==tag){
						plist.get(i).setNum(plist.get(i).getNum()+1);
					}
				}
				
				}
				
				if(StringUtil.notNull(idstr).equals("")) idstr="0";
				String sql1 = "select * from productType where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					int id = rs.getInt("id");
					if(String.valueOf(id).equals(idstr)){
						typeName = rs.getString("typeName");
					}
					ProductType pt = new ProductType();
					pt.setId(rs.getInt("id"));
					pt.setTypeName(rs.getString("typeName"));
					coll_pt.add(pt);
				}
				request.setAttribute("idstr", idstr);
				request.setAttribute("coll_pt", coll_pt);
					String sql = "select * from product where  productType like('%"+typeName+"%') and  state='1' order by id asc";
							
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					request.setAttribute("typeName", typeName);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setdType(rs.getString("d_type"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setNum(0);
						product.setFee(rs.getInt("fee"));
						product.setTotalNum(rs.getDouble("totalNum"));
						product.setImageUrl(rs.getString("imageUrl"));
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						if(plist!=null){
							int t=0;
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==product.getId()){
									product.setNum(plist.get(i).getNum());
									t++;
								}
							}
							if(t==0&&tag==product.getId()){
								product.setNum(1);
								plist.add(product);
							}
						}else if(tag==product.getId()){
							product.setNum(1);
							plist =new ArrayList<Product>();
							plist.add(product);
						}
						coll.add(product);
					}
						request.setAttribute("coll", coll);
						request.getSession().setAttribute("order_cart_list", plist);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void disDe_p(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		try {
		//	if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String ids = StringUtil.notNull(request.getParameter("id"));
				String idstr = StringUtil.notNull(request.getParameter("idstr"));
				String typeName="";
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
				for(int i=0;i<plist.size();i++){
					if(plist.get(i).getId()==tag){
						if(plist.get(i).getNum()>0)
						plist.get(i).setNum(plist.get(i).getNum()-1);
					}
				}
				
				}
				
				if(StringUtil.notNull(idstr).equals("")) idstr="0";
				String sql1 = "select * from productType where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					int id = rs.getInt("id");
					if(String.valueOf(id).equals(idstr)){
						typeName = rs.getString("typeName");
					}
					ProductType pt = new ProductType();
					pt.setId(rs.getInt("id"));
					pt.setTypeName(rs.getString("typeName"));
					coll_pt.add(pt);
				}
				request.setAttribute("idstr", idstr);
				request.setAttribute("coll_pt", coll_pt);
					String sql = "select * from product where  type like '%1%' and productType like('%"+typeName+"%') and  state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					request.setAttribute("typeName", typeName);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setdType(rs.getString("d_type"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setNum(0);
						product.setFee(rs.getInt("fee"));
						product.setTotalNum(rs.getDouble("totalNum"));
						product.setImageUrl(rs.getString("imageUrl"));
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						
						if(plist!=null){
							int t=0;
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==product.getId()){
									product.setNum(plist.get(i).getNum());
								}
							}
						}
						coll.add(product);
					}
						request.setAttribute("coll", coll);
						request.getSession().setAttribute("order_cart_list", plist);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	public void increate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.get(tag).setNum(ArithUtil.add(plist.get(tag).getNum(),1));
					}
					
						request.getSession().setAttribute("order_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart1.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void disDe(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
						if(plist.get(tag).getNum()>0)
							plist.get(tag).setNum(plist.get(tag).getNum()-1);
					}
					
						request.getSession().setAttribute("order_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del_pro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.remove(tag);
					}
						request.getSession().setAttribute("order_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart1.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void shop_cart_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("order_cart_list");
		String message ="";
		try {
		if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String[] ids = request.getParameterValues("ids");
				List<Product> slist =new ArrayList<Product>();
				request.setAttribute("num", ids.length);
					for(int i=0;i<ids.length;i++){
						int tag = Integer.valueOf(ids[i]);
						if(plist.size()>tag){
							slist.add(plist.get(tag));
						}
					}
				String sql = "select * from branch where id='"+branch.getId()+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				if(rs.next()){
					Address adr = new Address();
					adr.setId(rs.getInt("id"));
					adr.setPhone(rs.getString("tel"));
					adr.setReceiver(rs.getString("linkman"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("address"));
					request.getSession().setAttribute("sns_adr",adr);
				}
				request.getSession().setAttribute("splist_1",slist);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart_save_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void shop_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("splist_1");
		String message ="";
		try {
		if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				
				Address adr = (Address)request.getSession().getAttribute("sns_adr");
			
				String sqlw = "select * from branch where id='"+branch.getId()+"' for update";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sqlw);
				if(rs.next()){
				Timestamp date = new Timestamp(new Date().getTime());
				String orderId = StringUtil.parseToString(date, DateUtil.ymdhms);
				double price = 0;
				double pv=0;
				List<String> slist = new ArrayList<String>();
				for(int i=0;i<plist.size();i++){
					price = price+plist.get(i).getPrice()*plist.get(i).getNum();
					pv = pv +plist.get(i).getPv()*plist.get(i).getNum();
					String sql1="insert into orderDetail(orderId,pid,productId,productName,productType,unit,productPrice,num,price,image_url)"
							+ " values('"+orderId+"','"+plist.get(i).getId()+"','"+plist.get(i).getProductId()+"','"+plist.get(i).getProductName()+"','"+plist.get(i).getProductType()+"','"+plist.get(i).getUnit()
							+"','"+plist.get(i).getPrice()+"','"+plist.get(i).getNum()+"','"+plist.get(i).getPrice()*plist.get(i).getNum()+"','"+plist.get(i).getImageUrl()+"');";
					slist.add(sql1);
				}
			
				String sql2= "insert into orders(orderId,uid,userId,userName,receiver,phone,address,"
						+ "price,pv,orderType,orderTime,state) "
						+ " values('"+orderId+"','"+branch.getId()+"','"
						+branch.getCode()+"','"+branch.getName()+"','"+adr.getReceiver()+"','"+adr.getPhone()
						+"','"+(adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAdr())+"','"+price+"','"+pv+"','3','"+date+"','1');";
				slist.add(sql2);
			
				
					if(slist.size()>0){
							for(int i=0;i<slist.size();i++){
								stmt.addBatch(slist.get(i));
								 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
									 stmt.executeBatch();
									 stmt = conn.createStatement();
								 }
							}
						}
					}
					message="换货订单订单提交成功！";
			
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_save_msg_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_payoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					sql ="select * from order_week where start_time='"+startTime+"' and state like'%"+state+"%' and code like'%"+userId+"%' order by id desc";
					
				}else{
					sql ="select * from order_week where  code like'%"+userId+"%' and state like'%"+state+"%'  order by id desc";
						
				}
				
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					OrderWeek orders = new OrderWeek();
					orders.setId(rs.getInt("id"));
					orders.setBid(rs.getInt("b_id"));
					orders.setCode(rs.getString("code"));
					orders.setName(rs.getString("name"));
					orders.setPrice(rs.getDouble("price"));
					orders.setState(rs.getInt("state"));
					orders.setStartTime(rs.getTimestamp("start_time"));
					orders.setEndTime(rs.getTimestamp("end_time"));
					orders.setEntryTime(rs.getTimestamp("entry_time"));

					result.add(orders);
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
				request.setAttribute("state",state);
				request.setAttribute("orderType",orderType);
				request.setAttribute("userId",userId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("sum",sum);
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_payoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = ( Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(branch!=null){
			if(db.createConn()){
				String sql ="select * from order_week where  code ='"+branch.getCode()+"' and state ='"+state+"'  order by id desc";
			
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					OrderWeek orders = new OrderWeek();
					orders.setId(rs.getInt("id"));
					orders.setBid(rs.getInt("b_id"));
					orders.setCode(rs.getString("code"));
					orders.setName(rs.getString("name"));
					orders.setPrice(rs.getDouble("price"));
					orders.setState(rs.getInt("state"));
					orders.setStartTime(rs.getTimestamp("start_time"));
					orders.setEndTime(rs.getTimestamp("end_time"));
					orders.setEntryTime(rs.getTimestamp("entry_time"));

					result.add(orders);
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
				request.setAttribute("state",state);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_order_payoff_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
						
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void admin_order_payoff_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String id = StringUtil.notNull(request.getParameter("id"));
	
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][4].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					conn = db.getConnection();
					
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			
				String sql ="select * from order_week where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					OrderWeek orders = new OrderWeek();
					orders.setId(rs.getInt("id"));
					orders.setBid(rs.getInt("b_id"));
					orders.setCode(rs.getString("code"));
					orders.setName(rs.getString("name"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setState(rs.getInt("state"));
					if(orders.getState()==0){
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						String sql1 ="select * from branch where id='"+orders.getBid()+"' for update";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql1);
						if(rs.next()){
							double credit = rs.getInt("credit");
						String sqlu = "update order_week set state='1' where id='"+id+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						if(credit<=0){
							String sqlu1 = "update branch set credit=credit_init where id='"+orders.getBid()+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sqlu1);
						}
						message= "订单确认支付成功，支付店铺信息为："+orders.getName()+"("+orders.getCode()+")。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
						conn.commit();
						conn.setAutoCommit(autoCommit);
						}else{
							message= "当前状态不能进行支付操作，请重新确认！";
						}
					}else{
						message= "该订单无需进行支付，请重新确认！";
					}
					
				}else{
					message= "未查询到需要确认的订单信息，请重新确认！";
				}
				
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
		
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	
	protected void admin_order_payoff_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
	
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][4].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					conn = db.getConnection();
					 Timestamp date = new Timestamp(new Date().getTime());
					Timestamp startTime = cs.getPreWeekStartTime();
					Timestamp endTime = cs.getPreWeekEndTime();
					String sql0 ="select * from order_week where start_time='"+startTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(!rs.next()){
					int num=0;
					String sql ="select max(id) from branch where entry_time<='"+endTime+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					if(rs.next()){
						num =rs.getInt(1);
					} 
					OrderWeek[] ow = new OrderWeek[num+1];
				String sql1 ="select * from branch where entry_time<='"+endTime+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					int  id=rs.getInt("id");
					if(ow[id]==null) ow[id] = new OrderWeek();
					ow[id].setBid(rs.getInt("id"));
					ow[id].setCode(rs.getString("code"));
					ow[id].setName(rs.getString("name"));
					ow[id].setState(rs.getInt("state"));
					ow[id].setPrice(0);
					ow[id].setState(0);
					ow[id].setStartTime(startTime);
					ow[id].setEndTime(endTime);
					ow[id].setEntryTime(date);
				}
				
				String sql2 ="select * from orders where orderTime>='"+startTime+"' and orderTime<='"+endTime+"' and state>'0' and orderType='1' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql2);
				while(rs.next()){
					int id = rs.getInt("uId");
					double price = rs.getDouble("price");
					if(price>0){
						ow[id].setPrice(ArithUtil.add(ow[id].getPrice(), price));
					}
				}
				List<String> slist = new ArrayList<String>();
				for(int i=1;i<ow.length;i++){
					if(ow[i]!=null){
						if(ow[i].getPrice()>0){
							String sql4="insert into order_week(b_id,code,name,price,start_time,end_time,entry_time,state) "
									+" values('"+ow[i].getBid()+"','"+ow[i].getCode()+"','"+ow[i].getName()
									+"','"+ow[i].getPrice()+"','"+ow[i].getStartTime()+"','"+ow[i].getEndTime()
									+"','"+ow[i].getEntryTime()+"','"+ow[i].getState()+"');";
							slist.add(sql4);
						}
					}
				}
				
				 if(slist.size()>0){
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 stmt = conn.createStatement();
						for(int i=0;i<slist.size();i++){
							stmt.addBatch(slist.get(i));
							 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
								 stmt.executeBatch();
								 stmt.close();
								 stmt = conn.createStatement();
							 }
						}
						message=StringUtil.parseToString(startTime, DateUtil.yyyyMMdd)+"至"
								+StringUtil.parseToString(endTime, DateUtil.yyyyMMdd)+"订单汇总信息生成成功，请在订单支付查看明细！";
								cs.insetAdminLog(conn, admin.getAdminName(), message, date);
						conn.commit();
						conn.setAutoCommit(autoCommit);
				 }else{
					 message= "上周未查询到有相应的订单汇总信息！";
				 }
						}else{
							message= "上周汇总已经进行过汇总，不能重复操作，请重新确认！";
						}
				
			}else {
				message= "数据库连接已断开！";
			}

				}else{
					message= "您没有权限进行该操作！";
				}
				}else{
					message=  "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
		
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_payoff_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void admin_delivery_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(admin!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where orderId='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setPv(rs.getDouble("pv"));
					orders.setAdminByConfirmId(rs.getString("adminByConfirmId"));
					orders.setAdminByDeliveryId(rs.getString("adminByDeliveryId"));
					orders.setAdminByReviewerId(rs.getString("adminByReviewerId"));
					orders.setConfirmTime(rs.getTimestamp("confirmTime"));
					orders.setDeliveryTime(rs.getTimestamp("deliveryTime"));
					orders.setReviewerTime(rs.getTimestamp("reviewerTime"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("expressNum"));
					orders.setRemark(rs.getString("remark"));
					orders.setSummary(rs.getString("summary"));
					orders.setComments(rs.getString("comments"));
					request.setAttribute("orders", orders);
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'and num>'0'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("orderId"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setSpecification(rs.getString("specification"));
						od.setUnit(rs.getString("unit"));
						od.setProductPrice(rs.getDouble("productPrice"));
						od.setType(rs.getInt("type"));
						od.setProductType(rs.getString("productType"));
						od.setProductPv(rs.getDouble("productPv"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getDouble("num"));
						coll.add(od);
					}
					request.setAttribute("coll", coll);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
			}else {
				message = "数据库连接已断开！";
			}}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void admin_delivery_summary_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String message="";
		try {
			if(admin!=null){
			if(db.createConn()){
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
					Timestamp endTime= new Timestamp(StringUtil.parseToDate(startTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String sql ="select max(id) from product";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					sql ="select max(id) from branch where state='1'";
					stmt= db.getStmtread();
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
					plist[id].setImageUrl(rs.getString("imageUrl"));
					plist[id].setState(rs.getInt("state"));
					plist[id].setEntryTime(rs.getTimestamp("entryTime"));
					plist[id].setEndTime(rs.getTimestamp("endTime"));
					nlist[id] = (double) 0;
					}
					
					sql ="select * from branch where state='1' order by asc_num asc";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_delivery_summary_list.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected synchronized void admin_branch_summary_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				int pageSize = 60;
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
					
				sql ="select * from branch where code like'%"+code+"%' and state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					}else {
						message = "分店信息获取失败！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_branch_summary_list.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void order_back(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message ="";
		List<String> slist = new ArrayList<String>();
		try {
		if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 String id= (String) request.getParameter("id");
					String sql1= "select * from orders where orderId='"+id+"' for update";
					stmt =conn.createStatement();
					rs= stmt.executeQuery(sql1);
					if(rs.next()){
						int orderId = rs.getInt("id");
						int state = rs.getInt("state");
						int tag =rs.getInt("modify_tag");
						double price = rs.getDouble("price");
						String userId = rs.getString("userId");
						if(state==1){
						String sql2= "select * from branch where code='"+userId+"' for update";
						stmt =conn.createStatement();
						rs= stmt.executeQuery(sql2);
						if(rs.next()){
								double creditUsed = rs.getDouble("credit_used");
								String sql3 = "select * from orderDetail where orderId='"+id+"' for update";
								stmt =conn.createStatement();
								rs= stmt.executeQuery(sql3);
								while(rs.next()){
									int pid = rs.getInt("pid");
									double num = rs.getDouble("num");
									String sql5= "select * from product where id='"+pid+"' for update";
									Statement stmt1 = conn.createStatement();
									ResultSet rs1 = stmt1.executeQuery(sql5);
									if(rs1.next()){
										double p_num = rs1.getDouble("num");
											String sql6="update product set num='"+ArithUtil.add(num,p_num)
													+"' where id='"+pid+"'";
											slist.add(sql6);
									}
									if(stmt1!=null)stmt1.close();
									if(rs1!=null)rs1.close();
									
								}
					double creditUsed_new = ArithUtil.sub(creditUsed, price);
					if(creditUsed_new>=0){
						String sql7 = "update branch set credit_used='"+creditUsed_new+"' where code='"+userId+"'";
						slist.add(sql7);
						String sql8 = "update orders set state='0',modify_tag='"+(tag+1)+"' where orderId='"+id+"'";
						slist.add(sql8);
						if(slist.size()>0){
								for(int i=0;i<slist.size();i++){
									stmt.addBatch(slist.get(i));
									 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
										 stmt.executeBatch();
										 stmt = conn.createStatement();
									 }
								}
							}
						message="订单退单成功，退单金额为："+price+"元。";
					}else message = "订单额度操作有误。";
				
				}else message = "会员信息获取失败！";
				}else message = "订单状态不能进行退单";
			}else message = "订单信息获取失败或状态已发生修改！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_back(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		List<String> slist = new ArrayList<String>();
		try {
		if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 String id= (String) request.getParameter("id");
					String sql1= "select * from orders where orderId='"+id+"' for update";
					stmt =conn.createStatement();
					rs= stmt.executeQuery(sql1);
					if(rs.next()){
						int orderId = rs.getInt("id");
						int state = rs.getInt("state");
						int tag =rs.getInt("modify_tag");
						double price = rs.getDouble("price");
						int orderType = rs.getInt("orderType");
						String userId = rs.getString("userId");
						if(state==1){
						String sql2= "select * from branch where code='"+userId+"' for update";
						stmt =conn.createStatement();
						rs= stmt.executeQuery(sql2);
						if(rs.next()){
								double creditUsed = rs.getDouble("credit_used");
								String sql3 = "select * from orderDetail where orderId='"+id+"' for update";
								stmt =conn.createStatement();
								rs= stmt.executeQuery(sql3);
								while(rs.next()){
									int pid = rs.getInt("pid");
									double num = rs.getDouble("num");
									String sql5= "select * from product where id='"+pid+"' for update";
									Statement stmt1 = conn.createStatement();
									ResultSet rs1 = stmt1.executeQuery(sql5);
									if(rs1.next()){
										double p_num = rs1.getDouble("num");
											String sql6="update product set num='"+ArithUtil.add(num,p_num)
													+"' where id='"+pid+"'";
											slist.add(sql6);
									}
									if(stmt1!=null)stmt1.close();
									if(rs1!=null)rs1.close();
									
								}
								if(orderType==1){
					double creditUsed_new = ArithUtil.sub(creditUsed, price);
					if(creditUsed_new>=0){
						String sql7 = "update branch set credit_used='"+creditUsed_new+"' where code='"+userId+"'";
						slist.add(sql7);
						String sql8 = "update orders set state='0',modify_tag='"+(tag+1)+"' where orderId='"+id+"'";
						slist.add(sql8);
						if(slist.size()>0){
								for(int i=0;i<slist.size();i++){
									stmt.addBatch(slist.get(i));
									 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
										 stmt.executeBatch();
										 stmt = conn.createStatement();
									 }
								}
							}
					
					}else message = "订单额度操作有误。";
								}else{
						String sql8 = "update orders set state='0',modify_tag='"+(tag+1)+"' where orderId='"+id+"'";
						slist.add(sql8);
						if(slist.size()>0){
								for(int i=0;i<slist.size();i++){
									stmt.addBatch(slist.get(i));
									 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
										 stmt.executeBatch();
										 stmt = conn.createStatement();
									 }
								}
							}
					}
						message="订单退单成功，退单编号为"+id;
				}else message = "会员信息获取失败！";
				}else message = "订单状态不能进行退单";
			}else message = "订单信息获取失败或状态已发生修改！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void order_double(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch user = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> coll = new ArrayList<Product>();
		Collection coll_pt = new ArrayList();
		List<Product> plist = new ArrayList<Product>();
		List<Product> plist1 = new ArrayList<Product>();
		String ids = StringUtil.notNull(request.getParameter("oid"));
		String idstr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		double total_price =0;
		request.getSession().removeAttribute("shop_cart_list");
		 request.getSession().removeAttribute("shop_cart_list_1");
		request.getSession().removeAttribute("sys_order");
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
		try {
			if(user!=null){
				if(!(ids.equals(""))){
			if(db.createConn()){
				if(StringUtil.notNull(idstr).equals("")) idstr="0";
				String sql ="select * from orders where orderId='"+ids+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
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
					orders.setTag(rs.getInt("tag"));
					orders.setModifyTag(rs.getInt("modify_tag"));
					String sqld = "select * from orderDetail where orderId ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						Product od = new Product();
						od.setId(rs.getInt("pid"));
						od.setProductId(rs.getString("productId"));
						od.setProductName(rs.getString("productName"));
						od.setSpecification(rs.getString("specification"));
						od.setType(rs.getString("type"));
						od.setUnit(rs.getString("unit"));
						od.setProductType(rs.getString("productType"));
						od.setNum(rs.getDouble("num"));
						od.setOldNum(0);
						plist.add(od);
					}
				
					request.setAttribute("coll", coll);
					String sql1 = "select * from productType where  state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						int id = rs.getInt("id");
						ProductType pt = new ProductType();
						pt.setId(rs.getInt("id"));
						pt.setTypeName(rs.getString("typeName"));
						coll_pt.add(pt);
					}
					request.setAttribute("coll_pt", coll_pt);
					request.setAttribute("idstr", idstr);
					
					String sql2 = "select * from product where  type like '%1%' and  state='1' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql2);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setType(rs.getString("type"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setUnit(rs.getString("unit"));
						product.setNum(0);
						product.setTotalNum(rs.getDouble("num"));
						product.setImageUrl(StringUtil.notNull(rs.getString("imageUrl")));
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						if(plist!=null){
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==product.getId()){
									product.setNum(plist.get(i).getNum());
									plist.get(i).setFeatures(rs.getString("features"));
									plist.get(i).setProductId(rs.getString("productId"));
									plist.get(i).setType(rs.getString("type"));
									plist.get(i).setProductType(rs.getString("productType"));
									plist.get(i).setSpecification(rs.getString("specification"));
									plist.get(i).setPrice(rs.getDouble("price"));
									plist.get(i).setUnit(rs.getString("unit"));
									plist.get(i).setTotalNum(rs.getDouble("num"));
									plist.get(i).setImageUrl(StringUtil.notNull(rs.getString("imageUrl")));
									plist.get(i).setState(rs.getInt("state"));
									plist.get(i).setEntryTime(rs.getTimestamp("entryTime"));
									plist.get(i).setEndTime(rs.getTimestamp("endTime"));
									plist.get(i).setPrice(product.getPrice());
									plist.get(i).setTag(0);
									total_price = ArithUtil.add(total_price, ArithUtil.mul(plist.get(i).getNum(), product.getPrice()));
								}
							}
						}
						coll.add(product);
					}
					request.setAttribute("total_price", total_price);
						request.setAttribute("coll", coll);
						request.getSession().setAttribute("shop_cart_list", plist);
				}else {
					message = "未获得需要查询的订单ID信息！";
				}
				}else {
					message = "数据库连接已断开！";
				}
				}else {
				message = "未获得需要查询的订单ID信息！";
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
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}

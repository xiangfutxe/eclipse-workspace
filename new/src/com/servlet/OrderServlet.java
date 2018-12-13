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
import com.pojo.Center;
import com.pojo.Order;
import com.pojo.OrderDetail;
import com.pojo.Param;
import com.pojo.Product;
import com.pojo.Settle;
import com.pojo.User;
import com.pojo.WReward;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.ArithUtil;
import com.utils.Constants;
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
		}else if(method.equals("order_divery_out")){
			order_divery_out(request,response);
		}else if(method.equals("order_divery_save")){
			order_divery_save(request,response);
		}else if(method.equals("order_detail")){
			order_detail(request,response);
		}
		else if(method.equals("order_emoney_add")){
			try {
				order_emoney_add(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_emoney_save")){
			try {
				order_emoney_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_add")){
			try {
				order_add(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_save")){
			try {
				order_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_vip_add")){
			try {
				order_vip_add(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_vip_save")){
			try {
				order_vip_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_order_back")){
			try {
				admin_order_back(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("order_back")){
			try {
				order_back(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		}else if(method.equals("admin_order_confirm")){
			admin_order_confirm(request,response);
		}else if(method.equals("admin_order_confirm_yes")){
			admin_order_confirm_yes(request,response);
		}else if(method.equals("admin_order_detail_confirm")){
			admin_order_detail_confirm(request,response);
		}else if(method.equals("admin_order_confirm_ylist")){
			admin_order_confirm_ylist(request,response);
		}else if(method.equals("admin_order_review")){
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
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
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
		int pageSize = 10;
		try {
			if(user!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					if(state.equals("")){
						if(orderType.equals(""))
							sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  user_id='"+user.getUserId()+"' order by id desc";
						else sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  order_type = '"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
					}else{
						if(orderType.equals(""))
							sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  state ='"+state+"' and user_id='"+user.getUserId()+"' order by id desc";
						else 
							sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  state='"+state+"' and order_type='"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
					}
					}else{
						if(state.equals("")){
							if(orderType.equals(""))
								sql ="select * from orders where  user_id='"+user.getUserId()+"' order by id desc";
							else sql ="select * from orders where order_type = '"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
						}else{
							if(orderType.equals(""))
								sql ="select * from orders where  state ='"+state+"' and user_id='"+user.getUserId()+"' order by id desc";
							else 
								sql ="select * from orders where state='"+state+"' and order_type='"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
						}
						}
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void order_divery(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
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
		int pageSize = 10;
		try {
			if(user!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					if(state.equals("")){
						if(orderType.equals(""))
							sql ="select * from orders where orderTime>='"+startTime+"' and order_time<='"+endTime+"' and userByCenterId='"+ user.getUserId()+"' order by id desc";
						else sql ="select * from orders where orderTime>='"+startTime+"' and order_time<='"+endTime+"' and  orderType = '"+orderType+"' and userByCenterId='"+ user.getUserId()+"'  order by id desc";
					}else{
						if(orderType.equals(""))
							sql ="select * from orders where orderTime>='"+startTime+"' and order_time<='"+endTime+"' and  state ='"+state+"' and userId='"+user.getUserId()+"' userByCenterId='"+ user.getUserId()+"'  order by id desc";
						else 
							sql ="select * from orders where orderTime>='"+startTime+"' and order_time<='"+endTime+"' and  state='"+state+"' and orderType='"+orderType+"' and  userByCenterId='"+ user.getUserId()+"'  order by id desc";
					}
					}else{
						if(state.equals("")){
							if(orderType.equals(""))
								sql ="select * from orders where   userByCenterId='"+ user.getUserId()+"'  order by id desc";
							else sql ="select * from orders where orderType = '"+orderType+"' and userByCenterId='"+ user.getUserId()+"'  order by id desc";
						}else{
							if(orderType.equals(""))
								sql ="select * from orders where  state ='"+state+"' and userByCenterId='"+ user.getUserId()+"'  order by id desc";
							else 
								sql ="select * from orders where state='"+state+"' and orderType='"+orderType+"' and userByCenterId='"+ user.getUserId()+"' order by id desc";
						}
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));

					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  state like'%"+state+"%' and order_type like'%"+orderType+"%' and user_id like'%"+userId+"%'  order by id desc";
				
				}else{
					sql ="select * from orders where state like'%"+state+"%' and order_type like'%"+orderType+"%'  and user_id like'%"+userId+"%'  order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					sum[0] = sum[0]+rs.getDouble("price");
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sql ="select * from orders where  orderTime>='"+startTime+"'  and order_type like'%"+orderType+"%' and order_time<='"+endTime+"' and  state ='1' and user_id like'%"+userId+"%' order by id desc";
				}else{
					sql ="select * from orders where state='1' and order_type like'%"+orderType+"%' and user_id like'%"+userId+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					sum[0] = sum[0]+rs.getDouble("price");
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					
							sql ="select * from orders where order_type like '%"+orderType+"%' and order_time>='"+startTime+"' and order_time<='"+endTime+"'  and user_id like'%"+userId+"%' and admin_confirm_id='"+admin.getAdminName()+"' order by id desc";
					
					}else{
							if(orderType.equals(""))
								sql ="select * from orders where  order_type like '%"+orderType+"%' and  user_id like '%"+userId+"%'  and admin_confirm_id='"+admin.getAdminName()+"'  order by id desc";
					}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					sum[0] = sum[0]+rs.getDouble("price");
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						
							sql ="select * from orders where order_type like '%"+orderType+"%' and order_time>='"+startTime+"' and order_time<='"+endTime+"'  and user_id like'%"+userId+"%' and admin_review_id='"+admin.getAdminName()+"' order by id desc";
					
					}else{
						
								sql ="select * from orders where  order_type like '%"+orderType+"%' and user_id like'%"+userId+"%' and admin_review_id='"+admin.getAdminName()+"' order by id desc";
					}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					sum[0] = sum[0]+rs.getDouble("price");
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						sql ="select * from orders where order_type like '%"+orderType+"%' and order_time>='"+startTime+"' and order_time<='"+endTime+"' and user_id like'%"+userId+"%' and admin_delivery_id='"+admin.getAdminName()+"' order by id desc";
					
					}else{
						sql ="select * from orders where  order_type like '%"+orderType+"%' and user_id like'%"+userId+"%' and admin_delivery_id='"+admin.getAdminName()+"' order by id desc";
					}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					sum[0] = sum[0]+rs.getDouble("price");
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		String oid = StringUtil.notNull(request.getParameter("id"));
		User[] user = null;
		Order orders = new Order();
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
					 Param p = cs.getParam(conn, "奖金比例");
					 if(p!=null){
					 String sqlm = "select max(id) from userinfo";	
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sqlm);
						int num = 0;
						if(rs.next()){
							num = rs.getInt(1);
						}
					user = new User[num+1];

					String sqlu = "select * from userinfo order by id desc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sqlu);
					while(rs.next()){
						int id= rs.getInt("id");
						if(user[id]==null) user[id] = new User();
						user[id].setId(id);
						user[id].setUserId(rs.getString("user_id")); 
						user[id].setUserName(rs.getString("user_name"));
						user[id].setRankJoin(rs.getInt("rank_join"));
						user[id].setRefereeNode(rs.getString("referee_node"));
						user[id].setRefereeId(rs.getInt("referee_id"));
						user[id].setEntryTime(rs.getTimestamp("entry_time"));
						user[id].setTag(0);
					}
			
				String sql ="select * from orders where order_id='"+oid+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					
					orders.setId(rs.getInt("id"));
					orders.setuId(rs.getInt("u_id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPrice1(rs.getDouble("price1"));
					orders.setRankJoin(rs.getInt("rank_join"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					if(orders.getState()==1){
						List<String> slist = new ArrayList<String>();
						if(orders.getOrderType()==1){
							String sqlj = "select * from join_info where user_id='"+orders.getUserId()+"' and entry_time='"+orders.getOrderTime()+"'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sqlj);
							if(rs.next()){
								int jid = rs.getInt("id");
							String sqls  = "update userinfo set state='2' where id='"+orders.getuId()+"'";
							slist.add(sqls);
							sqls  = "update join_info set state='2' where id='"+jid+"'";
							slist.add(sqls);
							String[] str = StringUtil.notNull(user[orders.getuId()].getRefereeNode()).split(",");
							int t=3;
							for(int i=str.length-1;i>=0;i--){
								if(!str[i].equals("")){
									double award = 0;
									int sid = Integer.valueOf(str[i]);
									String remark ="";
									if(user[sid]!=null){
										if(t==3){
											if(user[sid].getRankJoin()>=1)
											award = ArithUtil.mul(orders.getPrice(), p.getAmount_1());
											remark="第1代，比例为："+ArithUtil.mul(p.getAmount_1(), 100)+"%。";
										}else if(t==2){
											if(user[sid].getRankJoin()>=2)
											award = ArithUtil.mul(orders.getPrice(), p.getAmount_2());
											remark="第2代，比例为："+ArithUtil.mul(p.getAmount_2(), 100)+"%。";
										}else if(t==1){
											if(user[sid].getRankJoin()>=3)
											award = ArithUtil.mul(orders.getPrice(), p.getAmount_3());
											remark="第3代，比例为："+ArithUtil.mul(p.getAmount_3(), 100)+"%。";
										}
										if(award>0){
											String sqlw =cs.saveReward(user[sid], user[orders.getuId()], award, 1, 1, orders.getUserId()+"加盟，金额为："+orders.getPrice()+"元；"+remark, date);
											slist.add(sqlw);
										}
									}
								}
								t--;
							}
							}
						}else if(orders.getOrderType()==2){
							String sqlj = "select * from join_info where user_id='"+orders.getUserId()+"' and entry_time='"+orders.getOrderTime()+"'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sqlj);
							if(rs.next()){
								int jid = rs.getInt("id");
								int newRank = rs.getInt("new_rank");
							String sqls  = "update userinfo set rank_join='"+newRank+"' where id='"+orders.getuId()+"'";
							slist.add(sqls);
							sqls  = "update join_info set state='2' where id='"+jid+"'";
							slist.add(sqls);
							/*奖金计算
							 *
							 */
							String[] str = StringUtil.notNull(user[orders.getuId()].getRefereeNode()).split(",");
							int t=3;
							for(int i=str.length-1;i>=0;i--){
								if(!str[i].equals("")){
									double award = 0;
									int sid = Integer.valueOf(str[i]);
									String remark ="";
									if(user[sid]!=null){
										if(t==3){
											if(user[sid].getRankJoin()>=1)
											award = ArithUtil.mul(orders.getPrice(), p.getAmount_1());
											remark="第1代，比例为："+ArithUtil.mul(p.getAmount_1(), 100)+"%。";
										}else if(t==2){
											if(user[sid].getRankJoin()>=2)
											award = ArithUtil.mul(orders.getPrice(), p.getAmount_2());
											remark="第2代，比例为："+ArithUtil.mul(p.getAmount_2(), 100)+"%。";
										}else if(t==1){
											if(user[sid].getRankJoin()>=3)
											award = ArithUtil.mul(orders.getPrice(), p.getAmount_3());
											remark="第3代，比例为："+ArithUtil.mul(p.getAmount_3(), 100)+"%。";
										}
										if(award>0){
											String sqlw =cs.saveReward(user[sid], user[orders.getuId()], award, 1, 1, orders.getUserId()+"升级，金额为："+orders.getPrice()+"元；"+remark, date);
											slist.add(sqlw);
										}
									}
								}
								t--;
							}
							}
						}else if(orders.getOrderType()==3){
							int refereeId = user[orders.getuId()].getRefereeId();
							if(user[refereeId]!=null){
								double award = ArithUtil.mul(orders.getPrice(), p.getAmount_5());
								if(award>0){
									String sqls  = cs.saveReward(user[refereeId], user[orders.getuId()], award, 2, 1, orders.getUserId()+"购物金额为："+orders.getPrice()+"元；比例为："+ArithUtil.mul(p.getAmount_3(), 100)+"%。", date);
									slist.add(sqls);
								}
							}
						}
						if(slist.size()>0){
							String sqlu1 = "update orders set state='2',admin_confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where order_id='"+oid+"'";
							slist.add(sqlu1);
						stmt = conn.createStatement();
						for(int i=0;i<slist.size();i++){
							stmt.addBatch(slist.get(i));
						}
						stmt.executeBatch();
						
						message= "订单确认成功，订单编号为："+oid;
						}
					}else{
						message= "当前状态不在你的确认范围内，请重新确认！";
					}
				}else{
					message= "未查询到需要确认的订单信息，请重新确认！";
				}
					 }else{
							message= "奖金比例参数获取失败，请重新确认！";
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
			user =null;
			orders = null;
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
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
				String sql ="select * from orders where order_id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					if(orders.getState()==3){
						User user1 = us.getSaveUser(conn, orders.getUserId());
						if(user1!=null){
						
								List<String> slist = new ArrayList<String>();
								
						String sql1="select * from order_detail where order_id='"+id+"' for update";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql1);
						String error = "";
						while(rs.next()){
							int type=rs.getInt("type");
							int pnum = rs.getInt("num");
							String productId = rs.getString("product_id");
							if(type==1){
								String sqlp="select * from product where product_id='"+productId+"' for update";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 = stmt1.executeQuery(sqlp);
								if(rs1.next()){
									int num = rs1.getInt("num");
									if(num-pnum<0){
										if(error.equals(""))
										error=error+rs1.getString("product_id");
										else error = error+"，"+rs1.getString("product_id");
									}
									else {
										String sqlpu = "update product set num='"+(num-pnum)+"' where product_id='"+productId+"'";
										slist.add(sqlpu);
									}
								}
								/*
								sqlp="select * from inventory_product where productId='"+productId+"' and inventoryName='"+orders.getInventory()+"' for update";
								stmt1 = conn.createStatement();
								rs1 = stmt1.executeQuery(sqlp);
								if(rs1.next()){
									int num = rs1.getInt("num");
									if(num-pnum<0){
										if(error.equals(""))
										error=error+rs1.getString("product_id");
										else error = error+"，"+rs1.getString("product_id");
									}
									else {
										String sqlpu = "update inventory_product set num='"+(num-pnum)+"' where productId='"+productId+"' and inventoryName='"+orders.getInventory()+"'";
										slist.add(sqlpu);
									}
								}
								*/
								rs1.close();
								stmt1.close();
							}/*
								}else{
									
									String sqlpd="select * from productDetail where pId='"+productId+"' for update";
									Statement stmt1 = conn.createStatement();
									ResultSet rs1 = stmt1.executeQuery(sqlpd);
									while(rs1.next()){
										String pid = rs1.getString("productId");
										int pdnum = rs1.getInt("num");
										String sqlp="select * from product where productId='"+pid+"' for update";
										Statement stmt2 = conn.createStatement();
										ResultSet rs2 = stmt2.executeQuery(sqlp);
										if(rs2.next()){
											int num=rs2.getInt("num");
											if(num-pnum*pdnum<0){
												if(error.equals(""))
												error=error+rs2.getString("productId");
												else error = error+"，"+rs2.getString("productId");
											}
											else {
												String sqlpu = "update product set num='"+(num-pnum*pdnum)+"' where productId='"+pid+"'";
												slist.add(sqlpu);
											}
										}
										
										sqlp="select * from inventory_product where productId='"+pid+"' and inventoryName='"+orders.getInventory()+"' for update";
										stmt2 = conn.createStatement();
										rs2 = stmt2.executeQuery(sqlp);
										if(rs2.next()){
											int num=rs2.getInt("num");
											if(num-pnum*pdnum<0){
												if(error.equals(""))
												error=error+rs2.getString("productId");
												else error = error+"，"+rs2.getString("productId");
											}
											else {
												String sqlpu = "update inventory_product set num='"+(num-pnum*pdnum)+"' where productId='"+pid+"' and inventoryName='"+orders.getInventory()+"'";
												slist.add(sqlpu);
											}
										}
										rs2.close();
										stmt2.close();
									}
									rs1.close();
									stmt1.close();
								}
								*/
						}
						if(error.equals("")){
							if(slist.size()>0){
								String sqlu = "";
								sqlu = "update orders set state='4',admin_review_id='"+admin.getAdminName()+"',review_time='"+date+"' where order_id='"+id+"'";
								stmt= conn.createStatement();
								stmt.addBatch(sqlu);
								for(int i =0;i<slist.size();i++)
								stmt.addBatch(slist.get(i));
								stmt.executeBatch();
								}
						message= "订单出库审核通过，订单编号为："+id;
						} else message="产品编号为"+error+"等商品库存不足，请联系仓库进行采购入库！";
							
					}else{
						message= "会员信息获取失败，请重试！";
						conn.rollback();
					}
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
				String sql ="select * from orders where order_id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setRemark(rs.getString("remark"));
					if(orders.getState()==3){
						String sqlu = "update orders set state='2',admin_review_id='"+admin.getAdminName()+"',reviewe_time='"+date+"' where order_id='"+id+"'";
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
		String express = StringUtil.notNull(request.getParameter("express"));
		String expressNum = StringUtil.notNull(request.getParameter("expressNum"));
		String remark = StringUtil.notNull(request.getParameter("remark"));

		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date = new Timestamp(new Date().getTime());
		
				
				String sql ="select * from orders where order_id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setTag(rs.getInt("tag"));
					if(orders.getState()==2){
						String sqlu = "update orders set state='3',express='"+express+"',express_num='"+expressNum+"',admin_delivery_id='"+admin.getAdminName()+"',delivery_time='"+date+"',remark='"+remark+"' where order_id='"+id+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "订单出库成功，订单编号为："+id;
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						if(orderType.equals(""))
							sql ="select * from orders where orderTime>='"+startTime+"' and order_time<='"+endTime+"' and  state ='2' and user_id like'%"+userId+"%' order by id desc";
						else 
							sql ="select * from orders where orderTime>='"+startTime+"' and order_time<='"+endTime+"'  and  state='2' and orderType='"+orderType+"' and user_id like'%"+userId+"%' order by id desc";
					
					}else{
							if(orderType.equals(""))
								sql ="select * from orders where  state ='2' and user_id like '%"+userId+"%' order by id desc";
							else 
								sql ="select * from orders where state='2'  and orderType='"+orderType+"' and user_id like'%"+userId+"%' order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
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
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
						sql ="select * from orders where order_type like '%"+orderType+"%' and order_time>='"+startTime+"' and order_time<='"+endTime+"' and  state='3'  and user_id like'%"+userId+"%'   order by id desc";
					
					}else{
						
							sql ="select * from orders where state='3'  and order_type like '%"+orderType+"%' and  user_id like'%"+userId+"%'  order by id desc";
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				double[] sum = {0,0};
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setRemark(rs.getString("remark"));
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
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					
					String orderId =rs.getString("order_id");
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setRemark(rs.getString("remark"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("order_id"));
						od.setProductId(rs.getString("product_id"));
						od.setProductName(rs.getString("product_name"));
						od.setProductPrice(rs.getDouble("product_price"));
						od.setProductPv(rs.getDouble("product_pv"));
						od.setType(rs.getInt("type"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getInt("num"));
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
	protected void order_divery_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));

					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orders.getOrderId()+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("order_id"));
						od.setProductId(rs.getString("product_id"));
						od.setProductName(rs.getString("product_name"));
						od.setProductPrice(rs.getDouble("product_price"));
						od.setType(rs.getInt("type"));
						od.setPrice(rs.getDouble("price"));
						od.setNum(rs.getInt("num"));
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
	

	
	protected void order_divery_out(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		String idStr = StringUtil.notNull(request.getParameter("id"));
		String message="";
		try {
			if(user!=null){
				if(!(idStr.equals(""))){
			if(db.createConn()){
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
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
		User user = (User)request.getSession().getAttribute("sys_user");
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
			String sql ="select * from orders where order_id='"+idStr+"' for update";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql);
			if(rs.next()){
				Order orders = new Order();
				orders.setId(rs.getInt("id"));
				orders.setOrderId(rs.getString("order_id"));
				orders.setUserId(rs.getString("user_id"));
				orders.setUserName(rs.getString("user_name"));
				orders.setOrderType(rs.getInt("order_type"));
				orders.setOrderTime(rs.getTimestamp("order_time"));
				orders.setReceiver(rs.getString("receiver"));
				orders.setPhone(rs.getString("phone"));
				orders.setAddress(rs.getString("address"));
				orders.setState(rs.getInt("state"));
				orders.setPrice(rs.getDouble("price"));
				orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
				orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
				orders.setAdminByReviewerId(rs.getString("admin_review_id"));
				orders.setConfirmTime(rs.getTimestamp("confirm_time"));
				orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
				orders.setReviewerTime(rs.getTimestamp("review_time"));
				orders.setInventory(rs.getString("inventory"));
				orders.setSummary(rs.getString("summary"));
				orders.setRemark(rs.getString("remark"));
				List<OrderDetail> olist = new ArrayList<OrderDetail>();
				String sql1 ="select * from order_detail where order_id='"+orders.getOrderId()+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					OrderDetail od = new OrderDetail();
					od.setProductId(rs.getString("productId"));
					od.setProductName(rs.getString("productName"));
					od.setProductPrice(rs.getDouble("product_price"));
					od.setPrice(rs.getDouble("price"));
					od.setNum(rs.getInt("num"));
					olist.add(od);
				}
				List<String> slist = new ArrayList<String>();
				if(olist.size()>0){
					for(int i=0;i<olist.size();i++){
						String sql2 = "select * from inventory_center where productId='"+olist.get(i).getProductId()+"' for update";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql2);
						if(rs.next()){
							int pnum = rs.getInt("num");
							if(olist.get(i).getNum()-pnum>=0){
								String sql3 = "update inventory_center set num='"+(olist.get(i).getNum()-pnum)+"' where productId='"+olist.get(i).getProductId()+"'";
								slist.add(sql3);
							}else{
								message1=message1+olist.get(i).getProductId()+"("+olist.get(i).getProductName()+"),";
							}
						}else{
							message1=message1+olist.get(i).getProductId()+"("+olist.get(i).getProductName()+"),";
						}
					}
				}
				
					if(orders.getState()==2){
						if(message1.equals("")){
						String sqlu = "update orders set state='3',express='"+express+"',expressNum='"+expressNum+"',deliveryTime='"+date+"',remark='"+remark+"' where order_id='"+idStr+"'";
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
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));

					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orders.getOrderId()+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
					
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("order_id"));
						od.setProductId(rs.getString("product_id"));
						od.setProductName(rs.getString("product_name"));
						od.setProductPrice(rs.getDouble("product_price"));
						od.setType(rs.getInt("type"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getInt("num"));
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
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					
					String orderId =rs.getString("order_id");
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));

					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
						OrderDetail od = new OrderDetail();
						od.setOrderId(rs.getString("order_id"));
						od.setProductId(rs.getString("product_id"));
						od.setProductName(rs.getString("product_mame"));
						od.setProductPrice(rs.getDouble("product_price"));
						od.setType(rs.getInt("type"));
						od.setPrice(rs.getDouble("price"));
						od.setPv(rs.getDouble("pv"));
						od.setNum(rs.getInt("num"));
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
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String orderId =rs.getString("order_id");
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));

					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
					
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("order_id"));
							od.setProductId(rs.getString("product_id"));
							od.setProductName(rs.getString("product_name"));
							od.setProductPrice(rs.getDouble("product_price"));
							od.setType(rs.getInt("type"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
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
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String orderId =rs.getString("order_id");
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					orders.setExpress(rs.getString("express"));
					orders.setExpressNum(rs.getString("express_num"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
					
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("order_id"));
							od.setProductId(rs.getString("product_id"));
							od.setProductName(rs.getString("product_name"));
							od.setProductPrice(rs.getDouble("product_price"));
							od.setType(rs.getInt("type"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
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
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String orderId =rs.getString("order_id");
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
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
				String sql ="select * from orders where order_id='"+idStr+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String orderId =rs.getString("order_id");
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setAdminByConfirmId(rs.getString("admin_confirm_id"));
					orders.setAdminByDeliveryId(rs.getString("admin_delivery_id"));
					orders.setAdminByReviewerId(rs.getString("admin_review_id"));
					orders.setConfirmTime(rs.getTimestamp("confirm_time"));
					orders.setDeliveryTime(rs.getTimestamp("delivery_time"));
					orders.setReviewerTime(rs.getTimestamp("review_time"));
					orders.setInventory(rs.getString("inventory"));
					orders.setSummary(rs.getString("summary"));
					orders.setRemark(rs.getString("remark"));
					request.setAttribute("orders", orders);
					String sqld = "select * from order_detail where order_id ='"+orderId+"'";
					stmt=db.getStmtread();
					rs=stmt.executeQuery(sqld);
					while(rs.next()){
					
							OrderDetail od = new OrderDetail();
							od.setOrderId(rs.getString("order_id"));
							od.setProductId(rs.getString("product_id"));
							od.setProductName(rs.getString("product_name"));
							od.setProductPrice(rs.getDouble("product_price"));
							od.setType(rs.getInt("type"));
							od.setPrice(rs.getDouble("price"));
							od.setPv(rs.getDouble("pv"));
							od.setNum(rs.getInt("num"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail_confirm.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void order_emoney_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
		Collection coll = new ArrayList();
		Collection coll_adr = new ArrayList();
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
						 conn = db.getConnection();
						Param p1 = cs.getParam(conn, "加盟资格");
						Param p2 = cs.getParam(conn, "购物折扣");
						if(p1!=null){
							if(p2!=null){
							double discount = 1;
							if(user.getRankJoin()==1) discount = p2.getAmount_1();
							else if(user.getRankJoin()==2)discount = p2.getAmount_2();
							else if(user.getRankJoin()==3)discount = p2.getAmount_3();
					double emoney = p1.getAmount_1();
					String sql = "select * from product where  state='1' order by id asc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("product_name"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("product_id"));
						product.setSpecification(rs.getString("specification"));
						product.setType(rs.getInt("type"));
						product.setFee(rs.getDouble("fee"));
						product.setPrice(ArithUtil.mul(rs.getDouble("price1"), discount));
						product.setState(rs.getString("state"));
						coll.add(product);
					} 
					request.setAttribute("coll", coll);	
					String sqla = "select * from address where user_id='"+user.getUserId()+"' order by id desc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sqla);
					while(rs.next()){
						Address adr = new Address();
						adr.setId(rs.getInt("id"));
						adr.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						adr.setReceiver(rs.getString("receiver"));
						adr.setPhone(rs.getString("phone"));
						coll_adr.add(adr);
					} 
					User new_user = new User();
					new_user.setEmoney(emoney);
					request.setAttribute("user_one", new_user);
					request.setAttribute("coll_adr", coll_adr);		
					int token_new = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token_new));
							}else {
								message = "购物折扣参数设置有误，请截图与客服联系！";
							}
						}else {
							message = "系统参数设置有误，请截图与客服联系！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_add.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_add_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	

	
	public void order_emoney_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
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
						
						Param p = cs.getParam(conn, "购物折扣");
						if(p!=null){
					
						String sqla = "select * from address where id='"+addressId+"'";
						stmt = conn.createStatement();
					rs = stmt.executeQuery(sqla);
					Order orders = new Order();
					if(rs.next()){
						orders.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						orders.setReceiver(rs.getString("receiver"));
						orders.setPhone(rs.getString("phone"));
					} 
					List<OrderDetail> olist = new ArrayList<OrderDetail>();
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					double totalprice = 0;
					double totalpv = 0;
					double totalfee = 0;
					double discount =1;
					if(user.getRankJoin()==1) discount = p.getAmount_1();
					if(user.getRankJoin()==2) discount = p.getAmount_2();
					if(user.getRankJoin()==3) discount = p.getAmount_3();
					String rid = cs.createOrderId(date);
					for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if(Integer.valueOf(numstr[i])>0){
								String sql2 = "select * from product where id='"+Integer.valueOf(pid[i])+"'";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql2);
								if(rs.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(rid);
									od.setPid(rs.getInt("id"));
									od.setNum(Integer.valueOf(numstr[i]));
									od.setProductId(rs.getString("product_id"));
									od.setProductName(rs.getString("product_name"));
									od.setProductPrice(ArithUtil.mul(rs.getDouble("price"), discount));
									od.setProductFee(rs.getDouble("fee"));
									od.setType(rs.getInt("type"));
									od.setPrice(ArithUtil.mul(od.getProductPrice(), Integer.valueOf(numstr[i])));
									od.setFee(ArithUtil.mul(rs.getDouble("fee") , Integer.valueOf(numstr[i])));
									olist.add(od);
									totalprice = ArithUtil.add(totalprice ,ArithUtil.mul(od.getProductPrice(), Integer.valueOf(numstr[i])));
									totalfee = ArithUtil.add(totalfee ,ArithUtil.mul(rs.getDouble("fee"),Integer.valueOf(numstr[i])));
								}
							}
						}
					}
					orders.setOrderId(rid);
					orders.setOrderType(3);
					orders.setPrice(totalprice);
					orders.setFee(totalfee);
					orders.setState(1);
					List<String> slist = new ArrayList<String>();
					for (int i = 0; i < olist.size(); i++) {
							String sqld = "insert into order_detail(order_id,p_id,product_id,product_name,product_price,product_pv,price,pv,product_fee,fee,num,type) values('"+olist.get(i).getOrderId()+
									"','"+olist.get(i).getPid()+ "','"+olist.get(i).getProductId()+"','"+olist.get(i).getProductName()+"','"+olist.get(i).getProductPrice()+"','"+olist.get(i).getProductPv()+"','"
										+olist.get(i).getPrice()+"','"+olist.get(i).getPv()+"','"+olist.get(i).getProductFee()+"','"+olist.get(i).getFee()+"','"+olist.get(i).getNum()+"','"+olist.get(i).getType()+"')"; 
								slist.add(sqld);
							
					}
					
					String sqls = "insert into orders(order_id,u_id,user_id,user_name,order_type,price,discount,fee,receiver,phone,address,order_time,state) "
							+ "values('"+orders.getOrderId()+"','"+user.getId()+"','"+user.getUserId()+"','"+user.getUserName()
							+"','"+orders.getOrderType()
							+"','"+orders.getPrice()+"','"+discount+"','"+orders.getFee()+"','"+orders.getReceiver()+
							"','"+orders.getPhone()+"','"+orders.getAddress()+"','"+date+"','"+orders.getState()+"')";
					slist.add(sqls);
					stmt=conn.createStatement();
								 for(int j=0;j<slist.size();j++)
									 stmt.addBatch(slist.get(j));
								 stmt.executeBatch();
								 message= "再次购物订单保存成功，订单编号为"+orders.getOrderId()+";<br>购物金额为："+totalprice+"元；<br>请再订单管理中查看详情！";
								 request.setAttribute("success_tag","1");
					 conn.commit();
						conn.setAutoCommit(autoCommit);
						}else {
							 message= "购物折扣信息获取失败。！";
								
							}
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
					}else {
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_emoney_add_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void order_vip_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
		Collection coll = new ArrayList();
		Collection coll_adr = new ArrayList();
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
						conn = db.getConnection();
						Param p1 = cs.getParam(conn, "加盟资格");
						if(p1!=null){
							double emoney = p1.getAmount_1();
							String sql = "select * from orders where  user_id='"+user.getUserId()+"' and order_type='1' and state>='1' order by id asc";

							stmt = conn.createStatement();
							rs = stmt.executeQuery(sql);
							if(!rs.next()){
					sql = "select * from product where  state='1' order by id asc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("product_name"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("product_id"));
						product.setSpecification(rs.getString("specification"));
						product.setType(rs.getInt("type"));
						product.setFee(rs.getDouble("fee"));
						
						product.setPrice(rs.getDouble("price1"));
						
						product.setState(rs.getString("state"));
						coll.add(product);
					} 
					request.setAttribute("coll", coll);	
					String sqla = "select * from address where user_id='"+user.getUserId()+"' order by id desc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sqla);
					while(rs.next()){
						Address adr = new Address();
						adr.setId(rs.getInt("id"));
						adr.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						adr.setReceiver(rs.getString("receiver"));
						adr.setPhone(rs.getString("phone"));
						coll_adr.add(adr);
					} 
					User new_user = new User();
					new_user.setEmoney(emoney);
					request.setAttribute("user_one", new_user);
					request.setAttribute("coll_adr", coll_adr);		
					int token_new = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token_new));
							}else {
								message = "首次购物已经下单，请耐心等待公司确认！";
							}
						}else {
							message = "系统参数设置有误，请截图与客服联系！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_vip_add.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_vip_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	

	
	public void order_vip_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
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
						
						String sql = "select max(id) from userinfo";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						int num =0;
						if(rs.next()){
							num =rs.getInt(1);
						}
						User[] ustr =new User[num+1];
						String sql1 = "select * from userinfo";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql1);
						while(rs.next()){
							int id = rs.getInt("id");
							if(ustr[id]==null) ustr[id] = new User();
							ustr[id].setId(id);
							ustr[id].setRankJoin(rs.getInt("rank_join"));
							ustr[id].setRefereeNode(rs.getString("referee_node"));
						}
						
						int uid1 =0;
							String[] str = StringUtil.notNull(ustr[user.getId()].getRefereeNode()).split(",");
							for(int i=str.length-1;i>=0;i--){
								if(!str[i].equals("")){
									int sid =Integer.valueOf(str[i]);
									if(ustr[sid].getRankJoin()==3){
										uid1 = sid;
									}
									if(uid1>0) break;
								}
							}
					
						String sqla = "select * from address where id='"+addressId+"'";
						stmt = conn.createStatement();
					rs = stmt.executeQuery(sqla);
					Order orders = new Order();
					if(rs.next()){
						orders.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						orders.setReceiver(rs.getString("receiver"));
						orders.setPhone(rs.getString("phone"));
					} 
					List<OrderDetail> olist = new ArrayList<OrderDetail>();
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					double totalprice = 0;
					double totalpv = 0;
					double totalfee = 0;
					double totalprice1 = 0;
					int total_num =0;
					String rid = cs.createOrderId(date);
					for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if(Integer.valueOf(numstr[i])>0){
								String sql2 = "select * from product where id='"+Integer.valueOf(pid[i])+"'";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql2);
								if(rs.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(rid);
									od.setPid(rs.getInt("id"));
									od.setNum(Integer.valueOf(numstr[i]));
									od.setProductId(rs.getString("product_id"));
									od.setProductName(rs.getString("product_name"));
									od.setProductPrice(rs.getDouble("price1"));
									
									od.setProductFee(rs.getDouble("fee"));
									od.setType(rs.getInt("type"));
									
									od.setPrice(ArithUtil.mul(od.getProductPrice(), Integer.valueOf(numstr[i])));
									od.setPv(ArithUtil.mul(od.getProductPv(), Integer.valueOf(numstr[i])));
									od.setFee(ArithUtil.mul(rs.getDouble("fee") , Integer.valueOf(numstr[i])));
									olist.add(od);
									totalprice = ArithUtil.add(totalprice ,ArithUtil.mul(od.getProductPrice(), Integer.valueOf(numstr[i])));
									if(uid1>0){
										totalprice1 = ArithUtil.add(totalprice1 ,ArithUtil.mul(rs.getDouble("award"),Integer.valueOf(numstr[i])));
									}
									totalfee = ArithUtil.add(totalfee ,ArithUtil.mul(rs.getDouble("fee"),Integer.valueOf(numstr[i])));
								}
							}
						}
					}
					orders.setOrderId(rid);
					orders.setOrderType(1);
					orders.setPrice(totalprice);
					orders.setPv(totalpv);
					orders.setPrice1(totalprice1);
					orders.setFee(totalfee);
					orders.setState(1);
					List<String> slist = new ArrayList<String>();
					for (int i = 0; i < olist.size(); i++) {
							String sqld = "insert into order_detail(order_id,p_id,product_id,product_name,product_price,product_pv,price,pv,product_fee,fee,num,type) values('"+olist.get(i).getOrderId()+
									"','"+olist.get(i).getPid()+ "','"+olist.get(i).getProductId()+"','"+olist.get(i).getProductName()+"','"+olist.get(i).getProductPrice()+"','"+olist.get(i).getProductPv()+"','"
										+olist.get(i).getPrice()+"','"+olist.get(i).getPv()+"','"+olist.get(i).getProductFee()+"','"+olist.get(i).getFee()+"','"+olist.get(i).getNum()+"','"+olist.get(i).getType()+"')"; 
								slist.add(sqld);
							
					}
					
					String sqls = "insert into orders(order_id,u_id,user_id,user_name,u_id_1,order_type,num,price,price1,fee,receiver,phone,address,order_time,state) "
							+ "values('"+orders.getOrderId()+"','"+user.getId()+"','"+user.getUserId()+"','"+user.getUserName()
							+ "','"+ uid1+"','"+orders.getOrderType()+"','"+total_num
							+"','"+orders.getPrice()+"','"+orders.getPrice1()+"','"+orders.getFee()+"','"+orders.getReceiver()+
							"','"+orders.getPhone()+"','"+orders.getAddress()+"','"+date+"','"+orders.getState()+"')";
					slist.add(sqls);
					stmt=conn.createStatement();
								 for(int j=0;j<slist.size();j++)
									 stmt.addBatch(slist.get(j));
								
								 stmt.executeBatch();
								 request.getSession().setAttribute("sys_user", user);
								 message= "首次购物订单保存成功，订单编号为"+orders.getOrderId()+"，请再订单管理中查看详情！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_vip_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void order_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
		Collection coll = new ArrayList();
		Collection coll_adr = new ArrayList();
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
						 conn = db.getConnection();
					
					String sql = "select * from product where  state='1' order by id asc";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("product_name"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("product_id"));
						product.setSpecification(rs.getString("specification"));
						product.setType(rs.getInt("type"));
						product.setFee(rs.getDouble("fee"));
						product.setPrice(rs.getDouble("price"));
						product.setState(rs.getString("state"));
						coll.add(product);
					} 
					request.setAttribute("coll", coll);	
					String sqla = "select * from address where user_id='"+user.getUserId()+"' order by id desc";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sqla);
					while(rs.next()){
						Address adr = new Address();
						adr.setId(rs.getInt("id"));
						adr.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						adr.setReceiver(rs.getString("receiver"));
						adr.setPhone(rs.getString("phone"));
						coll_adr.add(adr);
					} 
					request.setAttribute("coll_adr", coll_adr);		
					int token_new = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token_new));
						}else {
							message = "系统参数设置有误，请截图与客服联系！";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("order_add.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_add_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	

	
	public void order_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
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
						
						String sql = "select max(id) from userinfo";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						int num =0;
						if(rs.next()){
							num =rs.getInt(1);
						}
						User[] ustr =new User[num+1];
						String sql1 = "select * from userinfo ";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql1);
						while(rs.next()){
							int id = rs.getInt("id");
							if(ustr[id]==null) ustr[id] = new User();
							ustr[id].setId(id);
							ustr[id].setRankJoin(rs.getInt("rank_join"));
							ustr[id].setRefereeNode(rs.getString("referee_node"));
						}
						
						int uid1 =0;
						
							String[] str = StringUtil.notNull(ustr[user.getId()].getRefereeNode()).split(",");
							for(int i=str.length-1;i>=0;i--){
								if(!str[i].equals("")){
									int sid =Integer.valueOf(str[i]);
									if(ustr[sid].getRankJoin()==3&&uid1==0){
										uid1 = sid;
									}
									if(uid1>0) break;
								}
							}
					
						String sqla = "select * from address where id='"+addressId+"'";
						stmt = conn.createStatement();
					rs = stmt.executeQuery(sqla);
					Order orders = new Order();
					if(rs.next()){
						orders.setAddress(StringUtil.notNull(rs.getString("province"))+StringUtil.notNull(rs.getString("city"))+StringUtil.notNull(rs.getString("area"))+StringUtil.notNull(rs.getString("address")));
						orders.setReceiver(rs.getString("receiver"));
						orders.setPhone(rs.getString("phone"));
					} 
					List<OrderDetail> olist = new ArrayList<OrderDetail>();
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					double totalprice = 0;
					double totalpv = 0;
					double totalfee = 0;
					double totalprice1 = 0;
					int total_num =0;
					String rid = cs.createOrderId(date);
					for (int i = 0; i < pid.length; i++) {
							if (!(numstr.equals("") || numstr == null)) {
								if(Integer.valueOf(numstr[i])>0){
								String sql2 = "select * from product where id='"+Integer.valueOf(pid[i])+"'";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql2);
								if(rs.next()){
									OrderDetail od = new OrderDetail();
									od.setOrderId(rid);
									od.setPid(rs.getInt("id"));
									od.setNum(Integer.valueOf(numstr[i]));
									od.setProductId(rs.getString("product_id"));
									od.setProductName(rs.getString("product_name"));
									od.setProductPrice(rs.getDouble("price"));
									od.setProductFee(rs.getDouble("fee"));
									od.setType(rs.getInt("type"));
									od.setPrice(ArithUtil.mul(od.getProductPrice(), Integer.valueOf(numstr[i])));
									od.setPv(ArithUtil.mul(od.getProductPv(), Integer.valueOf(numstr[i])));
									od.setFee(ArithUtil.mul(rs.getDouble("fee") , Integer.valueOf(numstr[i])));
									olist.add(od);
									totalprice = ArithUtil.add(totalprice ,ArithUtil.mul(od.getProductPrice(), Integer.valueOf(numstr[i])));
									totalprice1 = ArithUtil.add(totalprice1 ,ArithUtil.mul(ArithUtil.sub(rs.getDouble("price"), rs.getDouble("price1")),Integer.valueOf(numstr[i])));
									totalfee = ArithUtil.add(totalfee ,ArithUtil.mul(rs.getDouble("fee"),Integer.valueOf(numstr[i])));
								}
							}
						}
					}
					orders.setOrderId(rid);
					orders.setOrderType(3);
					orders.setPrice(totalprice);
					orders.setPv(totalpv);
					orders.setPrice1(totalprice1);
					orders.setFee(totalfee);
					orders.setState(1);
					List<String> slist = new ArrayList<String>();
					for (int i = 0; i < olist.size(); i++) {
							String sqld = "insert into order_detail(order_id,p_id,product_id,product_name,product_price,product_pv,price,pv,product_fee,fee,num,type) values('"+olist.get(i).getOrderId()+
									"','"+olist.get(i).getPid()+ "','"+olist.get(i).getProductId()+"','"+olist.get(i).getProductName()+"','"+olist.get(i).getProductPrice()+"','"+olist.get(i).getProductPv()+"','"
										+olist.get(i).getPrice()+"','"+olist.get(i).getPv()+"','"+olist.get(i).getProductFee()+"','"+olist.get(i).getFee()+"','"+olist.get(i).getNum()+"','"+olist.get(i).getType()+"')"; 
								slist.add(sqld);
							
					}
					String sqls = "insert into orders(order_id,u_id,user_id,user_name,u_id_1,order_type,num,price,price1,fee,receiver,phone,address,order_time,state) "
							+ "values('"+orders.getOrderId()+"','"+user.getId()+"','"+user.getUserId()+"','"+user.getUserName()
							+ "','"+ uid1+"','"+orders.getOrderType()+"','"+total_num
							+"','"+orders.getPrice()+"','"+orders.getPrice1()+"','"+orders.getFee()+"','"+orders.getReceiver()+
							"','"+orders.getPhone()+"','"+orders.getAddress()+"','"+date+"','"+orders.getState()+"')";
					slist.add(sqls);
					stmt=conn.createStatement();
								 for(int j=0;j<slist.size();j++)
									 stmt.addBatch(slist.get(j));
								 stmt.executeBatch();
								 request.getSession().setAttribute("sys_user", user);
								 message= "再次购物订单保存成功，订单编号为"+orders.getOrderId()+"，请再订单管理中查看详情！";
					 conn.commit();
						conn.setAutoCommit(autoCommit);
				}else {
				 message= "数据库连接断开，请重试！";
					
				}
					}else {
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("order_add_message.jsp");
				dispatcher.forward(request, response);
		}
	}


	public void admin_order_back(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
					if(db.createConn()){
						String idStr = StringUtil.notNull(request.getParameter("id"));
						
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						
						String sql = "select * from orders where order_id='"+idStr+"' for update";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						String orderId = rs.getString("order_id");
						if(rs.getInt("state")==1){
							String sql1 ="delete from orders  where order_id='"+orderId+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sql1);
							String sql2 ="delete from order_detail  where order_id='"+orderId+"'";
							stmt = conn.createStatement();
							stmt.executeUpdate(sql2);
							message= "订单退单成功，订单号为："+orderId+"，请再订单列别查看订单当前状态！";
							}else{
								message= "当前状态不能进行删除！";
							}
					}else message= "未能获得需要操作的相应订单的ID信息，请重试！";
						conn.commit();
						conn.setAutoCommit(autoCommit);
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
	
	public void order_back(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
						String idStr = StringUtil.notNull(request.getParameter("id"));
						
						 conn = db.getConnection();
						 boolean autoCommit= conn.getAutoCommit();
						 conn.setAutoCommit(false);
						
						String sql = "select * from orders where order_id='"+idStr+"' for update";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						String orderId = rs.getString("order_id");
						if(rs.getInt("state")==1){
						String sql1 ="delete from orders  where order_id='"+orderId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql1);
						String sql2 ="delete from order_detail  where order_id='"+orderId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql2);
						message= "订单退单成功，订单号为："+orderId+"，请再订单列别查看订单当前状态！";
						}else{
							message= "当前状态不能进行删除！";
						}
					}else message= "未能获得需要操作的相应订单的ID信息，请重试！";
						conn.commit();
						conn.setAutoCommit(autoCommit);
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
	
	public void admin_order_adr_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
					if(db.createConn()){
						String orderId = StringUtil.notNull(request.getParameter("id"));
					String sql = "select * from orders where order_id='"+orderId+"'";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						Order od = new Order();
						od.setId(rs.getInt("id"));
						od.setOrderId(rs.getString("order_id"));
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
						String sql = "select * from orders where order_id='"+idStr+"' for update";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							String userId= rs.getString("user_id");
							sql = "update orders set address='"+address+"',receiver='"+receiver+"',phone='"+phone+"' where order_id='"+idStr+"'";
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
		User user = (User)request.getSession().getAttribute("sys_user");
		String message="";
		DBConn db = new DBConn();
		try {
			if(user!=null){
					if(db.createConn()){
						String orderId = StringUtil.notNull(request.getParameter("id"));
					String sql = "select * from orders where order_id='"+orderId+"'";
					stmt = db.getStmtread();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						Order od = new Order();
						od.setId(rs.getInt("id"));
						od.setOrderId(rs.getString("order_id"));
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
		User user = (User)request.getSession().getAttribute("sys_user");
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
						String sql = "select * from orders where order_id='"+idStr+"' for update";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							String userId= rs.getString("user_id");
							sql = "update orders set address='"+address+"',receiver='"+receiver+"',phone='"+phone+"' where order_id='"+idStr+"'";
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
	
	
}

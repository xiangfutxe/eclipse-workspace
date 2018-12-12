package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.ssm.dao.AddressDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.OrderDeliveryDao;
import com.ssm.dao.OrderDeliveryDetailDao;
import com.ssm.dao.OrderDetailDao;
import com.ssm.dao.OrderDetailProductDao;
import com.ssm.dao.ParamDao;
import com.ssm.dao.ProductDao;
import com.ssm.dao.ProductSortDao;
import com.ssm.dao.ProductStockDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductSort;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.User;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

import net.sf.json.JSONObject;

public class OrderDeliveryServlet extends HttpServlet {

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

		}else if (method.equals("admin_orders")) {
			admin_order_list(request, response);

		}else if (method.equals("admin_order_review")) {
			admin_order_review(request, response);
		}else if (method.equals("admin_order_review_express")) {
			admin_order_review_express(request, response);
		}else if (method.equals("admin_order_review_yes")) {
			admin_order_review_yes(request, response);
		}else if (method.equals("admin_order_confirm")) {
			admin_order_confirm(request, response);
		}else if (method.equals("order_confirm")) {
			order_confirm(request, response);
		}else if (method.equals("admin_order_confirm_yes")) {
			admin_order_confirm_yes(request, response);
		}else if (method.equals("orders")) {
			order_list(request, response);
		}else if (method.equals("order_divery")) {
			order_divery(request, response);

		}else if (method.equals("order_express")) {
			order_express(request, response);
		}else if (method.equals("admin_order_detail")) {
			admin_order_detail(request, response);

		}else if (method.equals("admin_order_detail_confirm")) {
			admin_order_detail_confirm(request, response);

		}else if (method.equals("admin_order_detail_review")) {
			admin_order_detail_review(request, response);

		}else if (method.equals("order_detail")) {
			order_detail(request, response);

		}else if(method.equals("exportExcel_orders")){
			exportExcel_orders(request, response);
		}else if(method.equals("exportExcel_orders_detail")){
			exportExcel_orders_detail(request, response);
		}else if(method.equals("exportExcel_orders_detail_all")){
			exportExcel_orders_detail_all(request, response);
		}else if(method.equals("shop_list")){
			shop_list(request, response);
		}else if(method.equals("stock_cart")){
			shop_cart(request, response);
		}else if(method.equals("stock_cart_confirm")){
			stock_cart_confirm(request, response);
		}else if(method.equals("stock_cart_save")){
			stock_cart_save(request, response);
		}else if(method.equals("shop_retail_list")){
			shop_retail_list(request, response);
		}else if(method.equals("shop_cash_list")){
			shop_retail_list(request, response);
		}else if(method.equals("shop_integral_list")){
			shop_retail_list(request, response);
		}else if(method.equals("shop_retail_cart")){
			shop_retail_cart(request, response);
		}else if(method.equals("increate_ajax")){
			increate_ajax(request, response);
		}else if(method.equals("sub_ajax")){
			sub_ajax(request, response);
		}else if(method.equals("add_cart")){
			add_cart(request, response);
		}else if(method.equals("increate_ajax_1")){
			increate_ajax_1(request, response);
		}else if(method.equals("sub_ajax_1")){
			sub_ajax_1(request, response);
		}else if(method.equals("stock_add_ajax")){
			stock_add_ajax(request, response);
		}else if(method.equals("stock_sub_ajax")){
			stock_sub_ajax(request, response);
		}
	}
		
	
	public void admin_order_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
	String stateStr = StringUtil.notNull(request.getParameter("state"));
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
			int orderType =0;
			int state =0;
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			if(!stateStr.equals("")) state= Integer.valueOf(stateStr);
			OrderDelivery order1 = new OrderDelivery();
			order1.setUserId(userId);;
			if(!stateStr.equals("")){
				state= Integer.valueOf(stateStr);
				order1.setState(state);
			}
			order1.setOrderId(orderId);
			order1.setOrderType(orderType);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				order1.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				order1.setEndTime(endTime);
			}
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
				pager = orderDao.findOrderByPage(order1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("state",stateStr);
				request.setAttribute("orderType",orderTypeStr);
				request.setAttribute("userId",userId);
				request.setAttribute("orderId",orderId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}

	
	public void admin_order_review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
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
				int orderType =0;
				if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
				OrderDelivery order1 = new OrderDelivery();
				order1.setUserId(userId);;
				order1.setState(2);
				order1.setOrderId(orderId);
				order1.setOrderType(orderType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setEndTime(endTime);
				}
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					pager = orderDao.findOrderByPage(order1,pager);
					request.setAttribute("pager", pager);
					request.setAttribute("orderType",orderTypeStr);
					request.setAttribute("userId",userId);
					request.setAttribute("orderId",orderId);
					request.setAttribute("startTime",startTimeStr);
					request.setAttribute("endTime",endTimeStr);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_review_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_review_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_review_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_review.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_review_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
	String express = StringUtil.notNull(request.getParameter("express"));
	String expressNum = StringUtil.notNull(request.getParameter("expressNum"));
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
				message = orderDao.reviewYes(id,admin.getAdminName(),express,expressNum, date);
				if(message.equals("yes"))
					message="配货单"+orderId+"出库成功。";
				
				else {
					error= message;
					message="";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_review_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_review_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_review_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_review_message.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_review_express(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					OrderDelivery orders = orderDao.findById(Integer.valueOf(id));
					request.setAttribute("orders", orders);
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_review_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_review_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_review_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_review_express.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
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
				int orderType =0;
				if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
				OrderDelivery order1 = new OrderDelivery();
				order1.setUserId(userId);;
				order1.setState(1);
				order1.setOrderId(orderId);
				order1.setOrderType(orderType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setEndTime(endTime);
				}
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					pager = orderDao.findOrderByPage(order1,pager);
					request.setAttribute("pager", pager);
					request.setAttribute("orderType",orderTypeStr);
					request.setAttribute("userId",userId);
					request.setAttribute("orderId",orderId);
					request.setAttribute("startTime",startTimeStr);
					request.setAttribute("endTime",endTimeStr);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_confirm_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_confirm_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_confirm_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_confirm.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void order_confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String orderId = StringUtil.notNull(request.getParameter("id"));
	
	int pageNo = 1;
	int pageSize = 20;
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
				message = orderDao.confirmYes(orderId);
				if(message.equals("yes")) {
					message = orderId+"订单签收成功。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_confirm_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_confirm_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_confirm_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					message = orderDao.confirmYes(admin.getAdminName(),id, date);
					if(message.equals("yes"))
						message="配货单"+orderId+"确认成功。";
					else {
						error= message;
						message="";
					}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_confirm_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_confirm_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_confirm_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_confirm_message.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	
	
	public void order_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
	String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
	String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
	String state = StringUtil.notNull(request.getParameter("state"));
	int pageNo = 1;
	int pageSize = 20;
		String message = "";
		try {
			if(user!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			OrderDelivery order1 = new OrderDelivery();
			order1.setUserId(user.getUserId());
			if(!state.equals("")) order1.setState(Integer.valueOf(state));
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
				pager = orderDao.findOrderByPage(order1,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("state", state);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery.jsp");
				dispatcher.forward(request, response);
		}
		
		
	}
	
	//服务配货单
	public void order_divery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
	String stateStr = StringUtil.notNull(request.getParameter("state"));
	String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
	String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
	int pageNo = 1;
	int pageSize = 20;
		String message = "";
		try {
			if(user!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			int orderType =0;
			int state =0;
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			if(!stateStr.equals("")) state= Integer.valueOf(stateStr);
			Order order1 = new Order();
			if(!stateStr.equals("")){
				state= Integer.valueOf(stateStr);
				order1.setState(state);
			}
			order1.setOrderType(orderType);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				order1.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				order1.setEndTime(endTime);
			}
				
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				OrderDao orderDao = new OrderDao();
				pager = orderDao.findOrderByPage(order1,pager);
			
				request.setAttribute("pager", pager);
				request.setAttribute("state",stateStr);
				request.setAttribute("orderType",orderTypeStr);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
						.getRequestDispatcher("order_divery_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_divery.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(user!=null){
			OrderDeliveryDao orderDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
				 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
			}else{
				message = "配货单信息获取失败。";
			}
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常。";
		} finally {
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void order_express(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(user!=null){
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
				OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				request.setAttribute("orders", orders);
			}else{
				message = "配货单信息获取失败。";
			}
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作异常。";
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_express.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
				 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
			}else{
				error = "配货单单信息获取失败。";
			}
			
			}else{
				error = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="order_delivery_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_detail.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_detail_confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
				 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
				int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
				request.getSession().setAttribute("token", String.valueOf(token));
			}else{
				error = "配货单单信息获取失败。";
			}
			
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
			String request_dispatcher ="order_delivery_confirm_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_confirm_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_confirm_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_detail_confirm.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_order_detail_review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				OrderDeliveryDao orderDao = new OrderDeliveryDao();
				OrderDelivery orders = orderDao.findByOrderId(id);
				if(orders!=null){
					OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
					 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
					request.setAttribute("orders", orders);
					request.setAttribute("coll", odlist);
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			}else{
				error = "配货单信息获取失败。";
			}
			
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="order_delivery_review_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="order_delivery_review_message.jsp";
				request.setAttribute("message", message);
			} if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="order_delivery_review_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="order_delivery_detail_review.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	

	public void exportExcel_orders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "配货单列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		List<Order>  result = new ArrayList<Order> ();
		String message = "";
		String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String centerId = StringUtil.notNull(request.getParameter("centerId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String stateStr = StringUtil.notNull(request.getParameter("state"));
			try {
				if(admin!=null){
				int orderType =0;
				int state =0;
				if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
				if(!stateStr.equals("")) state= Integer.valueOf(stateStr);
				Order order1 = new Order();
				order1.setUserId(userId);;
				if(!stateStr.equals("")){
					state= Integer.valueOf(stateStr);
					order1.setState(state);
				}
				order1.setOrderType(orderType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setEndTime(endTime);
				}
			OrderDao orderDao = new OrderDao();
			result = orderDao.findByList(order1);
		
				String[][] content = new String[result.size()+1][15];
				content[0][0]="序号";
				content[0][1]="配货单编号";
				content[0][2]="配货单金额";
				content[0][3]="配货单PV";
				content[0][4]="配货单类型";
				content[0][5]="会员编号";
				content[0][6]="会员名称";
				content[0][7]="所属服务店";
				content[0][8]="收件人";
				content[0][9]="联系电话";
				content[0][10]="收货地址";
				content[0][11]="配货单日期";
				content[0][12]="当前状态";
				
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getOrderId());
					
					content[i+1][2]  = StringUtil.decimalFormat(result.get(i).getPrice());
					String type="";
					if(result.get(i).getOrderType()==0) type = "-";
					else if(result.get(i).getOrderType()==1) type = "报单购物";
					else if(result.get(i).getOrderType()==2) type = "零售购物";
					else if(result.get(i).getOrderType()==3) type = "复消购物";
					else if(result.get(i).getOrderType()==4) type = "购物券购物";
					else if(result.get(i).getOrderType()==5) type = "升级购物";
					content[i+1][4]  = StringUtil.notNull(type);
					content[i+1][5]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getUserName());
					
					content[i+1][11] = StringUtil.parseToString(result.get(i).getOrderTime(), DateUtil.yyyyMMddHHmmss);

					String state_str ="";
					if(result.get(i).getState()==0) state_str = "已退单";
					else if(result.get(i).getState()==1) state_str = "待确认";
					else if(result.get(i).getState()==2) state_str = "待配货";
					else if(result.get(i).getState()==3) state_str = "配货中";
					else if(result.get(i).getState()==4) state_str = "出库中";
					else if(result.get(i).getState()==5) state_str = "已发货";
					else if(result.get(i).getState()==6) state_str = "已收货";
					content[i+1][12] = state_str;
					
					
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("配货单列表");  
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


	
	public void exportExcel_orders_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "配货单产品明细汇总"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		List<Product>  result = new ArrayList<Product> ();
		String message = "";
		String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String centerId = StringUtil.notNull(request.getParameter("centerId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String stateStr = StringUtil.notNull(request.getParameter("state"));
			try {
				if(admin!=null){
				int orderType =0;
				int state =0;
				if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
				if(!stateStr.equals("")) state= Integer.valueOf(stateStr);
				Order order1 = new Order();
				order1.setUserId(userId);;
				if(!stateStr.equals("")){
					state= Integer.valueOf(stateStr);
					order1.setState(state);
				}
				order1.setOrderType(orderType);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setEndTime(endTime);
				}
			OrderDao orderDao = new OrderDao();
			List<Order> olist = orderDao.findByList(order1);
			
			
				
				String sql = "(";
				for(int i=0;i<olist.size();i++){
					if(sql.equals("(")) sql += "'"+olist.get(i).getOrderId()+"'";
					else sql += ",'"+olist.get(i).getOrderId()+"'";
				}
				sql+=")";
				sql  = "select od.id id,od.od_id odId,od.pid pid,ors.orderId orderId,od.productId productId,od.productName productName,od.num num,"
						+ "ors.discount discount,od.price price,od.pv pv,od.productPrice productPrice,od.productPv productPv,od.specification specification "
						+ " from orders as ors, order_detail_product as od where ors.orderId=od.orderId and ors.orderId in "+sql;
				orderDao = new OrderDao();
				result = orderDao.orderDetailSummary(sql);
				String[][] content = new String[result.size()+1][15];
				content[0][0]="序号";
				content[0][1]="产品编码";
				content[0][2]="产品名称";
				content[0][3]="规格";
				content[0][4]="平均单价";
				content[0][5]="平均PV";
				content[0][6]="购买数量";
				content[0][7]="金额小计";
				content[0][8]="PV小计";
				
				for(int i=1;i<result.size();i++){
					content[i][0]  = String.valueOf(i);
					content[i][1]  = StringUtil.notNull(result.get(i).getProductId());
					content[i][2]  = StringUtil.notNull(result.get(i).getProductName());
					content[i][3]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i][4]  = StringUtil.decimalFormat(result.get(i).getProductPrice());
					content[i][5]  = StringUtil.decimalFormat(result.get(i).getProductPv());
					content[i][6]  = StringUtil.decimalFormat(result.get(i).getNum());
					content[i][7]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i][8]  = StringUtil.decimalFormat(result.get(i).getPv());
				}
				content[result.size()][0]  = String.valueOf(result.size());
				content[result.size()][1]  = "合计";
				content[result.size()][2]  = "-";
				content[result.size()][3]  = "-";
				content[result.size()][4]  = "-";
				content[result.size()][5]  = "-";
				content[result.size()][6]  = StringUtil.decimalFormat(result.get(0).getNum());
				content[result.size()][7]  = StringUtil.decimalFormat(result.get(0).getPrice());
				content[result.size()][8]  = StringUtil.decimalFormat(result.get(0).getPv());
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("配货单产品明细汇总");  
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
	
	public void exportExcel_orders_detail_all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "配货单产品明细"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		List<OrderDetailProduct>  result = new ArrayList<OrderDetailProduct> ();
		String message = "";
			try {
				if(admin!=null){
					String[] ids = request.getParameterValues("ids");
					if(ids!=null&&ids.length>0){
					String sql = "(";
					for(int i=0;i<ids.length;i++){
						if(sql.equals("(")) sql += "'"+ids[i]+"'";
						else sql += ",'"+ids[i]+"'";
					}
					sql+=")";
				sql  = "select ors.orderId orderId,ors.userId userId,ors.userName userName,"
						+ "od.productId productId,od.productName productName,od.num num,"
						+ "ors.receiver receiver,ors.address address,ors.phone phone from order_detail_product od left join orders ors on od.orderId=ors.orderId  where ors.orderId in "+sql;
				OrderDao orderDao = new OrderDao();
				result = orderDao.orderDetailAll(sql);
				String[][] content = new String[result.size()+1][15];
				content[0][0]="序号";
				content[0][1]="配货单编码";
				content[0][2]="制表日期";
				content[0][3]="会员编号";
				content[0][4]="会员名称";
				content[0][5]="产品编码";
				content[0][6]="产品名称";
				content[0][7]="数量";
				content[0][8]="收货人";
				content[0][9]="联系电话";
				content[0][10]="收货地址";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getOrderId());
					content[i+1][2]  = StringUtil.parseToString(date, DateUtil.yyyyMMdd);
					content[i+1][3]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][5]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getNum());
					content[i+1][8]  = StringUtil.notNull(result.get(i).getReceiver());
					content[i+1][9]  = StringUtil.notNull(result.get(i).getPhone());
					content[i+1][10]  = StringUtil.notNull(result.get(i).getAddress());
				}
			
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("配货单产品明细");  
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
						message = "未选择需要导出的配货单信息。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message ="系统繁忙中，请稍后再试。";
		}
	}
	
	
	
	
	public void shop_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{		Collection coll = new ArrayList();
	Collection coll_pt = new ArrayList();
	String idstr = StringUtil.notNull(request.getParameter("id"));
	String tag = StringUtil.notNull(request.getParameter("tag"));
	if(tag.equals("")) {
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		if(plist!=null) request.getSession().removeAttribute("shop_cart_list");
	}
	try {
		if(StringUtil.notNull(idstr).equals("")) idstr="0";
		
		ProductSortDao productSortDao = new ProductSortDao();
		coll_pt = productSortDao.findAllProductSort();
		request.setAttribute("idstr", idstr);
		request.setAttribute("coll_pt", coll_pt);
		ProductDao productDao = new ProductDao();
		Product product = new Product();
		product.setProductType("等级专区");
		product.setState(1);
		if(!idstr.equals("")) {
			 Iterator it = coll_pt.iterator();
			 while(it.hasNext()){
				 ProductSort  sort = (ProductSort)it.next();
				 if(String.valueOf(sort.getId()).equals(idstr)) {
					 product.setProductSort(sort.getName());
					 break;
				 }
			 }
		}
		coll = productDao.findByProduct(product);
		request.setAttribute("coll", coll);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		RequestDispatcher dispatcher = request.getRequestDispatcher("shop_list.jsp");
		dispatcher.forward(request, response);
	}
}
	
	public void shop_cart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String  message= "";
		try {
			if(user!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据操作异常。";
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	public void stock_cart_confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String  message= "";
		String tag = StringUtil.notNull(request.getParameter("tag"));
		try {
			if(user!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					AddressDao adrDao = new AddressDao();
					if(tag.equals("")) {
						Address  address = adrDao.findByTag(user.getUserId());
						request.setAttribute("address", address);
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据操作异常。";
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock_cart.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	public void stock_cart_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{		Collection coll = new ArrayList();
	WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		List<ProductStock> plist = (List<ProductStock>) request.getSession().getAttribute("stock_cart_list");
	String message="";
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
	if(user!=null) {
		if (StringUtil.notNull(token_old).equals(token)) {
	if(plist!=null&&plist.size()>0) {
		OrderDeliveryDao orderDao = new OrderDeliveryDao();
		String adrId= StringUtil.notNull(request.getParameter("adrId"));
		String feeStr= StringUtil.notNull(request.getParameter("fee"));
		double fee = 0;
		if(!feeStr.equals("")) fee = Double.valueOf(fee);
		Timestamp date = new Timestamp(new Date().getTime());
		String orderId = StringUtil.parseToString(date, DateUtil.ymdhms)+cs.getRandom(1, 9);
		message = orderDao.saveOrders(user.getUserId(),adrId, orderId, fee, plist, date);
		if(message.equals("yes")) {
			message="配货单提交成功，请耐心等待公司发货。";
			request.getSession().removeAttribute("stock_cart_list");
		}
	}else {
		message = "未获取到相应的产品信息。";
	}
		}else {
			message = "请勿重复提交数据。";
		}
	}else {
		message = "未获取到相应的用户信息。";
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock_message.jsp");
		dispatcher.forward(request, response);
	}
}
	
	public void shop_retail_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{		Collection coll = new ArrayList();
	Collection coll_pt = new ArrayList();
	String idstr = StringUtil.notNull(request.getParameter("id"));
	String tag = StringUtil.notNull(request.getParameter("tag"));
	if(tag.equals("")) {
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		if(plist!=null) request.getSession().removeAttribute("shop_cart_list");
	}
	try {
		if(StringUtil.notNull(idstr).equals("")) idstr="0";
		
		ProductSortDao productSortDao = new ProductSortDao();
		coll_pt = productSortDao.findAllProductSort();
		request.setAttribute("idstr", idstr);
		request.setAttribute("coll_pt", coll_pt);
		ProductDao productDao = new ProductDao();
		Product product = new Product();
		product.setProductType("零售专区");
		product.setState(1);
		if(!idstr.equals("")) {
			 Iterator it = coll_pt.iterator();
			 while(it.hasNext()){
				 ProductSort  sort = (ProductSort)it.next();
				 if(String.valueOf(sort.getId()).equals(idstr)) {
					 product.setProductSort(sort.getName());
					 break;
				 }
			 }
		}
		coll = productDao.findByProduct(product);
		request.setAttribute("coll", coll);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		RequestDispatcher dispatcher = request.getRequestDispatcher("shop_retail_list.jsp");
		dispatcher.forward(request, response);
	}
}
	
	public void shop_retail_cart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String  message= "";
		try {
			if(user!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据操作异常。";
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_retail_cart.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	
	
	public void increate_ajax(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
			double total_price=0;
			int num = 0;
			double price = 0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("tag"));
				int tag=0;
				boolean b = false;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							plist.get(i).setNum(plist.get(i).getNum()+1);
							if(plist.get(i).getNum()-plist.get(i).getTotalNum()>0)
								plist.get(i).setNum(plist.get(i).getTotalNum());
							num = plist.get(i).getNum();
							b= true;
							break;
						}
					}
				}
				if(!b) {
					ProductDao  productDao = new ProductDao();
					Product product = productDao.findProductById(tag);
					if(plist==null) plist = new ArrayList<Product>();
					if(product!=null) {
						product.setTotalNum(product.getNum());
						if(product.getNum()>0) {
							product.setNum(1);
							num=1;
							plist.add(product);
						}
					}
				}
				
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					if(plist.get(i).getId()==tag){
						price = ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum());
					}
				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", num);
			object.put("price", price);
			request.getSession().setAttribute("shop_cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sub_ajax(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
			double total_price=0;
			int num = 0;
			double price=0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("tag"));
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							if(plist.get(i).getNum()>1) {
								plist.get(i).setNum(plist.get(i).getNum()-1);
									num = plist.get(i).getNum();
							}else {
								plist.remove(i);
								num=0;
							}
							
							break;
						}
					}
				}
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					if(plist.get(i).getId()==tag){
						price = ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum());
					}

				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", num);
			object.put("price", price);
			request.getSession().setAttribute("shop_cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add_cart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
			double total_price=0;
			int num = 0;
			int total_num = 0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("id"));
			String numstr = StringUtil.notNull(request.getParameter("num"));
			if(!numstr.equals("")) num = Integer.valueOf(numstr);
			if(num>0) {
				int tag=0;
				boolean b = false;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							plist.get(i).setNum(plist.get(i).getNum()+num);
							b= true;
							break;
						}
					}
				}
				if(!b) {
					ProductDao  productDao = new ProductDao();
					Product product = productDao.findProductById(tag);
					if(plist==null) plist = new ArrayList<Product>();
					if(product!=null) {
						product.setTotalNum(product.getNum());
						if(product.getNum()>0) {
							product.setNum(num);
							plist.add(product);
						}
					}
				}
			}
				
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					total_num+=plist.get(i).getNum();
				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", total_num);
			request.getSession().setAttribute("shop_cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void increate_ajax_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Product> plist = (List<Product>) request.getSession().getAttribute("cart_list");
		try {
			double total_price=0;
			int num = 0;
			double price = 0;
			int total_num =0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("tag"));
			String totalNum = StringUtil.notNull(request.getParameter("totalNum"));
			if(!totalNum.equals("")) total_num =Integer.valueOf(totalNum);
				int tag=0;
				boolean b = false;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							plist.get(i).setNum(plist.get(i).getNum()+1);
							if(plist.get(i).getNum()-plist.get(i).getTotalNum()>0)
								plist.get(i).setNum(plist.get(i).getTotalNum());
							num = plist.get(i).getNum();
							b= true;
							break;
						}
					}
				}
				if(!b) {
					ProductDao  productDao = new ProductDao();
					Product product = productDao.findProductById(tag);
					if(plist==null) plist = new ArrayList<Product>();
					if(product!=null) {
						product.setTotalNum(total_num);
						if(product.getNum()>0) {
							product.setNum(1);
							num=1;
							plist.add(product);
						}
					}
				}
				
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					if(plist.get(i).getId()==tag){
						price = ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum());
					}
				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", num);
			object.put("price", price);
			request.getSession().setAttribute("cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sub_ajax_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Product> plist = (List<Product>) request.getSession().getAttribute("cart_list");
		try {
			double total_price=0;
			int num = 0;
			double price=0;
			int total_num =0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("tag"));
			String totalNum = StringUtil.notNull(request.getParameter("totalNum"));
			if(!totalNum.equals("")) total_num =Integer.valueOf(totalNum);
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							if(plist.get(i).getNum()>1) {
								plist.get(i).setNum(plist.get(i).getNum()-1);
									num = plist.get(i).getNum();
							}else {
								plist.remove(i);
								num=0;
							}
							
							break;
						}
					}
				}
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					if(plist.get(i).getId()==tag){
						price = ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum());
					}

				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", num);
			object.put("price", price);
			request.getSession().setAttribute("cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stock_add_ajax(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<ProductStock> plist = (List<ProductStock>) request.getSession().getAttribute("stock_cart_list");
		try {
			double total_price=0;
			int num = 0;
			double price = 0;
			int total_num =0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("tag"));
			String totalNum = StringUtil.notNull(request.getParameter("totalNum"));
			if(!totalNum.equals("")) total_num =Integer.valueOf(totalNum);
				int tag=0;
				boolean b = false;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							plist.get(i).setNum(plist.get(i).getNum()+1);
							if(plist.get(i).getNum()-plist.get(i).getTotalNum()>0)
								plist.get(i).setNum(plist.get(i).getTotalNum());
							num = plist.get(i).getNum();
							b= true;
							break;
						}
					}
				}
				if(!b) {
					ProductStockDao  stockDao = new ProductStockDao();
					ProductStock stock = stockDao.findProductById(tag);
					if(plist==null) plist = new ArrayList<ProductStock>();
					if(stock!=null) {
						stock.setTotalNum(stock.getNum());
						if(stock.getNum()>0) {
							stock.setNum(1);
							num=1;
							plist.add(stock);
						}
					}
				}
				
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					if(plist.get(i).getId()==tag){
						price = ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum());
					}
				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", num);
			object.put("price", price);
			request.getSession().setAttribute("stock_cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stock_sub_ajax(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<ProductStock> plist = (List<ProductStock>) request.getSession().getAttribute("stock_cart_list");
		try {
			double total_price=0;
			int num = 0;
			double price=0;
			int total_num =0;
			Map<String, Object> object = new HashMap<String, Object>();
			String ids = StringUtil.notNull(request.getParameter("tag"));
			String totalNum = StringUtil.notNull(request.getParameter("totalNum"));
			if(!totalNum.equals("")) total_num =Integer.valueOf(totalNum);
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						if(plist.get(i).getId()==tag){
							if(plist.get(i).getNum()>1) {
								plist.get(i).setNum(plist.get(i).getNum()-1);
									num = plist.get(i).getNum();
							}else {
								plist.remove(i);
								num=0;
							}
							
							break;
						}
					}
				}
			if(plist!=null){
				for(int i=0;i<plist.size();i++){	
					total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
					if(plist.get(i).getId()==tag){
						price = ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum());
					}

				}
			}
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("num", num);
			object.put("price", price);
			request.getSession().setAttribute("stock_cart_list", plist);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
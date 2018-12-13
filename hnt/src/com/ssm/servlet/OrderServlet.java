package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import com.ssm.dao.AddressDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.OrderDeliveryDao;
import com.ssm.dao.OrderDeliveryDetailDao;
import com.ssm.dao.OrderDetailDao;
import com.ssm.dao.OrderDetailProductDao;
import com.ssm.dao.ParamDao;
import com.ssm.dao.ProductDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class OrderServlet extends HttpServlet {

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

		}else if (method.equals("admin_order_check")) {
			admin_order_check(request, response);

		}else if (method.equals("admin_order_confirm")) {
			admin_order_confirm(request, response);

		}else if (method.equals("admin_order_confirm_yes")) {
			admin_order_confirm_yes(request, response);

		}else if (method.equals("admin_order_delivery_wait")) {
			admin_order_delivery_wait(request, response);
		}else if (method.equals("admin_order_delivery_wait_back")) {
			admin_order_delivery_wait_back(request, response);
		}else if (method.equals("admin_order_delivery")) {
			admin_order_delivery(request, response);
		}else if (method.equals("admin_order_delivery_express")) {
			admin_order_delivery_express(request, response);
		}else if (method.equals("admin_order_delivery_yes")) {
			admin_order_delivery_yes(request, response);
		}else if (method.equals("admin_order_delivery_back")) {
			admin_order_delivery_back(request, response);
		}else if (method.equals("admin_order_review")) {
			admin_order_review(request, response);
		}else if (method.equals("admin_order_review_ylist")) {
			admin_order_review_ylist(request, response);
		}else if (method.equals("orders")) {
			order_list(request, response);

		}else if (method.equals("order_divery")) {
			order_divery(request, response);

		}else if (method.equals("order_divery_detail")) {
			order_divery_detail(request, response);

		}else if (method.equals("admin_order_detail")) {
			admin_order_detail(request, response);

		}else if (method.equals("admin_order_detail_delivery_wait")) {
			admin_order_detail_delivery_wait(request, response);
		}else if(method.equals("admin_order_detail_delivery_add")){
			admin_order_detail_delivery_add(request,response);
		}else if(method.equals("admin_order_detail_delivery_save")){
				admin_order_detail_delivery_save(request,response);
		}else if (method.equals("admin_order_detail_check")) {
			admin_order_detail_check(request, response);

		}else if (method.equals("admin_order_detail_review")) {
			admin_order_detail_review(request, response);

		}else if (method.equals("admin_order_detail_review_ylist")) {
			admin_order_detail_review_ylist(request, response);

		}else if(method.equals("admin_order_adr_edit")){
				admin_order_adr_edit(request,response);
		}else if(method.equals("admin_order_back")){
			admin_order_back(request,response);
		}else if (method.equals("admin_order_adr_update")) {
			admin_order_adr_update(request, response);

		}else if (method.equals("admin_order_detail_delivery")) {
			admin_order_detail_delivery(request, response);
		}else if(method.equals("order_adr_edit")){
			
			order_adr_edit(request,response);
	}else if (method.equals("order_adr_update")) {
		order_adr_update(request, response);

	}else if (method.equals("admin_order_detail_confirm")) {
			admin_order_detail_confirm(request, response);

		}else if (method.equals("order_detail")) {
			order_detail(request, response);

		}else if(method.equals("order_emoney_add")){
			order_emoney_add(request, response);
		}else if(method.equals("order_emoney_save")){
			order_emoney_save(request, response);
		}else if(method.equals("order_dmoney_add")){
			order_dmoney_add(request, response);
		}else if(method.equals("order_dmoney_save")){
			order_dmoney_save(request, response);
		}else if(method.equals("order_zmoney_add")){
			order_zmoney_add(request, response);
		}else if(method.equals("order_zmoney_save")){
			order_zmoney_save(request, response);
		}else if(method.equals("exportExcel_orders")){
			exportExcel_orders(request, response);
		}else if(method.equals("exportExcel_orders_detail")){
			exportExcel_orders_detail(request, response);
		}else if(method.equals("admin_order_detail_summary")){
			admin_order_detail_summary(request, response);
		}else if(method.equals("admin_order_detail_delivery_summary")){
			admin_order_detail_delivery_summary(request, response);
		}else if(method.equals("admin_order_review_yes")){
			admin_order_review_yes(request,response);
		}else if(method.equals("admin_order_review_no")){
			admin_order_review_no(request,response);
		}else if(method.equals("admin_order_review_back")){
			admin_order_review_back(request,response);
		}else if(method.equals("exportExcel_orders_detail_all")){
			exportExcel_orders_detail_all(request, response);
		}
	}
		
	
	public void admin_order_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String centerId = StringUtil.notNull(request.getParameter("centerId"));
	String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
	String stateStr = StringUtil.notNull(request.getParameter("state"));
	String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
	String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
	int pageNo = 1;
	int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			int orderType =0;
			int state =0;
			OrderDetailProductDao odpDao = new OrderDetailProductDao();
			odpDao.checkOrderDetailProuct();
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			if(!stateStr.equals("")) state= Integer.valueOf(stateStr);
			Order order1 = new Order();
			order1.setUserId(userId);;
			order1.setUserByCenterId(centerId);
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
				request.setAttribute("userId",userId);
				request.setAttribute("centerId",centerId);
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
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("orders.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String centerId = StringUtil.notNull(request.getParameter("centerId"));
	String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
	String stateStr = StringUtil.notNull(request.getParameter("state"));
	String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
	String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
	int pageNo = 1;
	int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			int orderType =0;
			int state =0;
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			if(!stateStr.equals("")) state= Integer.valueOf(stateStr);
			Order order1 = new Order();
			order1.setUserId(userId);;
			order1.setUserByCenterId(centerId);
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
				request.setAttribute("userId",userId);
				request.setAttribute("centerId",centerId);
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
						.getRequestDispatcher("order_check_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_check.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String centerId = StringUtil.notNull(request.getParameter("centerId"));
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
			int orderType =0;
			OrderDetailProductDao odpDao = new OrderDetailProductDao();
			odpDao.checkOrderDetailProuct();
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			Order order1 = new Order();
			order1.setUserId(userId);;
			order1.setUserByCenterId(centerId);
			order1.setState(1);
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
				request.setAttribute("orderType",orderTypeStr);
				request.setAttribute("userId",userId);
				request.setAttribute("centerId",centerId);
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
						.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_confirm.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_delivery_wait(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String centerId = StringUtil.notNull(request.getParameter("centerId"));
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
			int orderType =0;
			OrderDetailProductDao odpDao = new OrderDetailProductDao();
			odpDao.checkOrderDetailProuct();
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			Order order1 = new Order();
			order1.setUserId(userId);;
			order1.setUserByCenterId(centerId);
			order1.setState(2);
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
				request.setAttribute("orderType",orderTypeStr);
				request.setAttribute("userId",userId);
				request.setAttribute("centerId",centerId);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据操作有误，请重试。";
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_wait_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_wait.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_delivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
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
			int orderType =0;
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			OrderDelivery order1 = new OrderDelivery();
			order1.setUserId(userId);;
			order1.setState(1);
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
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
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
			int orderType =0;
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			OrderDelivery order1 = new OrderDelivery();
			order1.setUserId(userId);;
			order1.setState(2);
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
						.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_review_ylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderTypeStr = StringUtil.notNull(request.getParameter("orderType"));
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
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
			int orderType =0;
			if(!orderTypeStr.equals("")) orderType= Integer.valueOf(orderTypeStr);
			OrderDelivery order1 = new OrderDelivery();
			order1.setUserId(userId);;
			order1.setState(3);
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
						.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_review_ylist.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_list(HttpServletRequest request, HttpServletResponse response)
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
			order1.setUserId(user.getUserId());
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
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("orders.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
	}
	
	//服务订单
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
			order1.setUserByDeclarationId(user.getUserId());
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
	User user = (User) request.getSession().getAttribute("sys_user");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(user!=null){
			OrderDao orderDao = new OrderDao();
			Order order = new Order();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDetailDao orderDetailDao = new OrderDetailDao();
				 List<OrderDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_divery_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(user!=null){
			OrderDao orderDao = new OrderDao();
			Order order = new Order();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDetailDao orderDetailDao = new OrderDetailDao();
				 List<OrderDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_divery_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDetailDao orderDetailDao = new OrderDetailDao();
				 List<OrderDetail> odlist=orderDetailDao.findByOrderId(id);
				 OrderDetailProductDao orderDetailProductDao = new OrderDetailProductDao();
				 List<OrderDetailProduct> odplist=orderDetailProductDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
				request.setAttribute("coll_1", odplist);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_delivery_wait(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				 OrderDetailProductDao orderDetailProductDao = new OrderDetailProductDao();
				 List<OrderDetailProduct> odplist=orderDetailProductDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odplist);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_delivery_wait_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_delivery_wait.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_delivery_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findById(Integer.valueOf(id));
			if(orders!=null){
				 OrderDetailProductDao orderDetailProductDao = new OrderDetailProductDao();
				 List<OrderDetailProduct> odplist=orderDetailProductDao.findByOrderId(orders.getOrderId());
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odplist);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_delivery_wait_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_delivery_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_delivery_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
	String id = StringUtil.notNull(request.getParameter("id"));

	request.setAttribute("orderId", orderId);
	String message="";
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					if(!(orderId.equals(""))){
						Timestamp date = new Timestamp(new Date().getTime());
						String deliveryId ="D"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));


				OrderDao orderDao = new OrderDao();
				message = orderDao.deliveryWaitSave(request, Integer.valueOf(id), deliveryId, admin.getAdminName(), date);
				
			}else {
				message = "未获得需要查询的订单ID信息！";
			}
				}else{
					message = "请勿重复操作。";
				}
			}else{
				message = "用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_wait_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_delivery_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDetailDao orderDetailDao = new OrderDetailDao();
				 List<OrderDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_check_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_check.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
	Collection coll_1 = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDetailDao orderDetailDao = new OrderDetailDao();
				 List<OrderDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_confirm.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_delivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDeliveryDao orderDeliveryDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDeliveryDao.findByOrderId(id);
			
			if(orders!=null){
				OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
				 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
					OrderDao orderDao = new OrderDao();
					Order order_old= orderDao.findByOrderId(orders.getToOrderId());
					if(order_old!=null){
					request.setAttribute("order_old", order_old);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
				}else{
					message = "订单信息获取失败。";
				}
			}else{
				message = "配货单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_delivery.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_delivery_express(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
			OrderDeliveryDao orderDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				request.setAttribute("orders", orders);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据操作有误，请重试。";
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_express.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_delivery_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					String express = StringUtil.notNull(request.getParameter("express"));
					String expressNum = StringUtil.notNull(request.getParameter("expressNum"));
			Timestamp date = new Timestamp(new Date().getTime());
			OrderDeliveryDao orderDao = new OrderDeliveryDao();
			message = orderDao.deliveryYes(id, express, expressNum, admin.getAdminName(), date);
			}else{
				message = "订单信息获取失败。";
			}
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据操作有误，请重试。";
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_delivery_back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
					Timestamp date = new Timestamp(new Date().getTime());
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					message = orderDao.deliveryBack(id, admin.getAdminName(), date);
			}else{
				message = "请勿重复提交数据。";
			}
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "数据操作有误，请重试。";
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_detail_review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDeliveryDao orderDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
				 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_review_ylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDeliveryDao orderDao = new OrderDeliveryDao();
			OrderDelivery orders = orderDao.findByOrderId(id);
			if(orders!=null){
				OrderDeliveryDetailDao orderDetailDao = new OrderDeliveryDetailDao();
				 List<OrderDeliveryDetail> odlist=orderDetailDao.findByOrderId(id);
				request.setAttribute("orders", orders);
				request.setAttribute("coll", odlist);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_review_ylist_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_review_ylist.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void admin_order_adr_edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				request.setAttribute("orders", orders);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_adr_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_adr_edit.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public void admin_order_adr_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String idStr = StringUtil.notNull(request.getParameter("id"));
				String orderId = StringUtil.notNull(request.getParameter("orderId"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String address = StringUtil.notNull(request.getParameter("address"));
				String province = StringUtil.notNull(request.getParameter("province"));
				String city = StringUtil.notNull(request.getParameter("city"));
				String area = StringUtil.notNull(request.getParameter("area"));
				String receiver = StringUtil.notNull(request.getParameter("receiver"));
				String phone = StringUtil.notNull(request.getParameter("phone"));
				 Timestamp date = new Timestamp(new Date().getTime());
				Order order1 = new Order();
				order1.setId(Integer.valueOf(idStr));
				order1.setAddress(province+city+area+address);
				order1.setReceiver(receiver);
				order1.setPhone(phone);
				OrderDao orderDao = new OrderDao();
				Address adr = new Address();
				AddressDao adrDao = new AddressDao();
				adr.setUserId(userId);
				adr.setPhone(phone);
				adr.setArea(area);
				adr.setProvince(province);
				adr.setReceiver(receiver);
				adr.setEntryTime(date);
				adr.setEndTime(date);
				adr.setState(1);
				adr.setTag(1);
			 if(orderDao.update(order1)>0){
				 adrDao.updateAdr(adr);
				 message= "订单更新收货地址修改成功，订单号为："+orderId+"，请再订单列别查看订单当前状态！";
				 cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_9, date);
			}else{
				message = "订单更新失败。";
			}
				}else{
					message = "请勿重复提交数据。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			message = "订单更新失败。";
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_adr_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void order_adr_edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User  user = (User) request.getSession().getAttribute("sys_user");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(user!=null){
			OrderDao orderDao = new OrderDao();
			Order orders = orderDao.findByOrderId(id);
			if(orders!=null){
				request.setAttribute("orders", orders);
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_adr_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_adr_edit.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	Collection coll = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
			OrderDao orderDao = new OrderDao();
		
			message =orderDao.orderBack(id, admin.getAdminName());
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	
	public void order_adr_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User  user = (User) request.getSession().getAttribute("sys_user");
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(user!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String idStr = StringUtil.notNull(request.getParameter("id"));
				String orderId = StringUtil.notNull(request.getParameter("orderId"));
				String address = StringUtil.notNull(request.getParameter("address"));
				String province = StringUtil.notNull(request.getParameter("province"));
				String city = StringUtil.notNull(request.getParameter("city"));
				String area = StringUtil.notNull(request.getParameter("area"));
				String receiver = StringUtil.notNull(request.getParameter("receiver"));
				String phone = StringUtil.notNull(request.getParameter("phone"));
				 Timestamp date = new Timestamp(new Date().getTime());
				Order order1 = new Order();
				order1.setId(Integer.valueOf(idStr));
				order1.setAddress(province+city+area+address);
				order1.setReceiver(receiver);
				order1.setPhone(phone);
				OrderDao orderDao = new OrderDao();
				Address adr = new Address();
				AddressDao adrDao = new AddressDao();
				adr.setUserId(user.getUserId());
				adr.setPhone(phone);
				adr.setArea(area);
				adr.setProvince(province);
				adr.setReceiver(receiver);
				adr.setEntryTime(date);
				adr.setEndTime(date);
				adr.setState(1);
				adr.setTag(1);
			 if(orderDao.update(order1)>0){
				 adrDao.updateAdr(adr);
				 message= "订单更新收货地址修改成功，订单号为："+orderId+"，请再订单列别查看订单当前状态！";
			}else{
				message = "订单更新失败。";
			}
				}else{
					message = "请勿重复提交数据。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			message = "订单地址修改失败。";
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_order_confirm_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String idStr = StringUtil.notNull(request.getParameter("id"));
				
				 Timestamp date = new Timestamp(new Date().getTime());
				Order order1 = new Order();
				order1.setId(Integer.valueOf(idStr));
				order1.setState(2);
				order1.setConfirmTime(date);
				order1.setAdminByConfirmId(admin.getAdminName());
				OrderDao orderDao = new OrderDao();
			 if(orderDao.update(order1)>0){
				 message= "订单确认成功，该订单已进入配货流程！";
			}else{
				message = "订单更新失败。";
			}
				}else{
					message = "请勿重复操作。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			message = "订单更新失败。";
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_confirm_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void order_emoney_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if(user!=null){
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
				//String type = StringUtil.notNull(request.getParameter("type"));
				String userId= user.getUserId();
				/*
				if(type.equals("2")){
					userId= request.getParameter("userId");
				}
				*/
				ParamDao paramDao = new ParamDao();
				Param p = paramDao.findParamByName("零售购物折扣");
				if(p!=null){
				if(!StringUtil.notNull(userId).equals("")){
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
						Double discount = (double) 0;
						if(user1.getRank()>0){
							discount =  p.getAmount_5();
						}else if(user1.getRankJoin()==5){
							discount =  p.getAmount_5();
						}else if(user1.getRankJoin()==4){
							discount =  p.getAmount_4();
						}else if(user1.getRankJoin()==3){
							discount =  p.getAmount_3();
						}else if(user1.getRankJoin()==2){
							discount =  p.getAmount_2();
						}else if(user1.getRankJoin()==1){
							discount =  p.getAmount_1();
						}
						request.setAttribute("discount", discount);
							request.setAttribute("user1", user1);
							AddressDao  adrDao = new AddressDao();
							List<Address> coll_adr = adrDao.findByUserId(userId);
							request.setAttribute("coll_adr", coll_adr);	
							ProductDao productDao = new ProductDao();
							Product product = new Product();
							product.setProductType("零售产品");
							product.setState("1");
							product.setIsHide(1);
							List<Product> plist = productDao.findByProduct(product);
							request.setAttribute("coll", plist);
					
					}else{
						message = "会员信息获取失败。";
					}
				
				}else{
					message = "会员编号获取失败。";
				}
				}else{
					message = "折扣参数获取失败，请与客服人员联系。";
				}
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
						.getRequestDispatcher("order_emoney_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_emoney_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_emoney_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(StringUtil.notNull(token_old).equals(token)){
				if(user!=null){
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
					String addressId = StringUtil.notNull(request.getParameter("addressId"));
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String centerId = StringUtil.notNull(request.getParameter("centerId"));

				Timestamp date = new Timestamp(new Date().getTime());
				String rid = "R"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));
				OrderDao  ordersDao = new OrderDao();
				message = ordersDao.saveEmoneyOrders(user,userId,addressId,centerId,pid,numstr,rid,date);
				if(message.equals("yes"))
					message=userId+"零售订单保存成功，订单标号为："+rid+",请在订单列表中查看详情。";
				}else {
					message= "用户未登陆，请重新登陆！";
		}
			
	}else{
		message= "请勿重复提交数据！";
	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_emoney_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void order_dmoney_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if(user!=null){
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
				//String type = StringUtil.notNull(request.getParameter("type"));
				String userId= user.getUserId();
				/*
				if(type.equals("2")){
					userId= request.getParameter("userId");
				}
				*/
				if(!StringUtil.notNull(userId).equals("")){
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
							request.setAttribute("user1", user1);
							AddressDao  adrDao = new AddressDao();
							List<Address> coll_adr = adrDao.findByUserId(userId);
				request.setAttribute("coll_adr", coll_adr);	
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product.setProductType("复消产品");
				product.setState("1");
				product.setIsHide(1);
				List<Product> plist = productDao.findByProduct(product);
				request.setAttribute("coll", plist);
						
					}else{
						message = "会员信息获取失败。";
					}
				}else{
					message = "会员编号获取失败。";
				}
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
						.getRequestDispatcher("order_dmoney_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_dmoney_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_dmoney_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(StringUtil.notNull(token_old).equals(token)){
				if(user!=null){
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
					String addressId = StringUtil.notNull(request.getParameter("addressId"));
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String centerId = StringUtil.notNull(request.getParameter("centerId"));

				Timestamp date = new Timestamp(new Date().getTime());
				String rid = "R"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));
				OrderDao  ordersDao = new OrderDao();
				message = ordersDao.saveDmoneyOrders(user,userId,addressId,centerId,pid,numstr,rid,date);
				if(message.equals("yes"))
					message="复消订单保存成功，订单标号为："+rid+",请在订单列表中查看详情。";
				}else {
					message= "用户未登陆，请重新登陆！";
		}
			
	}else{
		message= "请勿重复提交数据！";
	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_dmoney_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void exportExcel_orders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "订单列表"+str+".xls";  
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
				order1.setUserByCenterId(centerId);
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
				content[0][1]="订单编号";
				content[0][2]="订单金额";
				content[0][3]="订单PV";
				content[0][4]="订单类型";
				content[0][5]="会员编号";
				content[0][6]="会员名称";
				content[0][7]="所属服务店";
				content[0][8]="收件人";
				content[0][9]="联系电话";
				content[0][10]="收货地址";
				content[0][11]="订单日期";
				content[0][12]="当前状态";
				
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getOrderId());
					
					content[i+1][2]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+1][3]  = StringUtil.decimalFormat(result.get(i).getPv());
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
					content[i+1][7]  =  StringUtil.notNull(result.get(i).getUserByCenterId());
					content[i+1][8]  =  StringUtil.notNull(result.get(i).getReceiver());
					content[i+1][9]  =  StringUtil.notNull(result.get(i).getPhone());
					content[i+1][10]  =  StringUtil.notNull(result.get(i).getAddress());
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
				 HSSFSheet sheet = wb.createSheet("订单列表");  
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
	
	public void admin_order_delivery_wait_back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	String orderId = StringUtil.notNull(request.getParameter("orderId"));
		String message = "";
		try {
			if(admin!=null){
			
			OrderDao orderDao = new OrderDao();
			Timestamp date = new Timestamp(new Date().getTime());
			int num =orderDao.deliveryWaitReBack(Integer.valueOf(id));
			if(num==1){
				message = orderId+"订单回退成功，请在订单确认中查看。";
				cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_9, date);
			}else if(num==2){
				message="该订单存在配货订单，请确认订货订单已经全部退回。";
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
						.getRequestDispatcher("order_delivery_wait_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_order_detail_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				String[] ids = request.getParameterValues("ids");
				String orderId="";
				if(ids!=null&&ids.length>0){
					
				String sql = "(";
				for(int i=0;i<ids.length;i++){
					if(sql.equals("(")) sql += "'"+ids[i]+"'";
					else sql += ",'"+ids[i]+"'";
					orderId+=ids[i]+"；";
				}
				sql+=")";
				sql  = "select od.id id,od.od_id odId,od.pid pid, ors.orderId orderId,od.productId productId,od.productName productName,od.num num, "
						+ "ors.discount discount,od.price price,od.pv pv,od.productPrice productPrice,od.productPv productPv,od.specification specification "
						+ " from orders as ors, order_detail_product as od where ors.orderId=od.orderId and ors.orderId in "+sql;	
				OrderDao orderDao = new OrderDao();
				Collection coll = orderDao.orderDetailSummary(sql);
				request.setAttribute("coll", coll);
				request.setAttribute("orderId", orderId);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_summary.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_detail_delivery_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				String[] ids = request.getParameterValues("ids");
				String orderId="";
				if(ids!=null&&ids.length>0){
					
				String sql = "(";
				for(int i=0;i<ids.length;i++){
					if(sql.equals("(")) sql += "'"+ids[i]+"'";
					else sql += ",'"+ids[i]+"'";
					orderId+=ids[i]+"；";
				}
				sql+=")";
				sql  = "select  od.id id,od.oid oid,ors.to_oid toOid,ors.to_order_id toOrderId,od.pid pid,ors.orderId orderId,od.productId productId,od.productName productName,od.num num,"
						+ "ors.discount discount,od.price price,od.pv pv,od.productPrice productPrice,od.productPv productPv,od.specification specification "
						+ " from order_delivery as ors, order_delivery_detail as od where ors.orderId=od.orderId and ors.orderId in "+sql;
				OrderDao orderDao = new OrderDao();
				Collection coll = orderDao.orderDetailDeliverySummary(sql);
				request.setAttribute("coll", coll);
				request.setAttribute("orderId", orderId);
			}else{
				message = "订单信息获取失败。";
			}
			
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
						.getRequestDispatcher("order_delivery_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_detail_delivery_summary.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_order_review_no(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				 Timestamp date = new Timestamp(new Date().getTime());
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					message = orderDao.reviewBack(id, admin.getAdminName(), date);
		
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_review_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				 Timestamp date = new Timestamp(new Date().getTime());
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					message = orderDao.reviewYes(id, admin.getAdminName(), date);
		
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_order_review_back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				 Timestamp date = new Timestamp(new Date().getTime());
					OrderDeliveryDao orderDao = new OrderDeliveryDao();
					message = orderDao.reviewReBack(id, admin.getAdminName(), date);
		
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void exportExcel_orders_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "订单产品明细汇总"+str+".xls";  
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
				order1.setUserByCenterId(centerId);
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
				 HSSFSheet sheet = wb.createSheet("订单产品明细汇总");  
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
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "订单产品明细"+str+".xls";  
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
				content[0][1]="订单编码";
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
				 HSSFSheet sheet = wb.createSheet("订单产品明细");  
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
						message = "未选择需要导出的订单信息。";
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
	
	public void order_zmoney_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
		String message = "";
		try {
			if(user!=null){
				int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
				String userId= user.getUserId();
				if(!StringUtil.notNull(userId).equals("")){
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
							request.setAttribute("user1", user1);
							AddressDao  adrDao = new AddressDao();
							List<Address> coll_adr = adrDao.findByUserId(userId);
				request.setAttribute("coll_adr", coll_adr);	
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product.setProductType("责任产品");
				product.setState("1");
				product.setIsHide(1);
				List<Product> plist = productDao.findByProduct(product);
				request.setAttribute("coll", plist);
						
					}else{
						message = "会员信息获取失败。";
					}
				}else{
					message = "会员编号获取失败。";
				}
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
						.getRequestDispatcher("order_zmoney_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_zmoney_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void order_zmoney_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String token_old = (String)request.getSession().getAttribute("token");
	String token= (String)request.getParameter("token");
	int token_new = (int)(1+Math.random()*(1000000-1+1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(StringUtil.notNull(token_old).equals(token)){
				if(user!=null){
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
					String addressId = StringUtil.notNull(request.getParameter("addressId"));
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String centerId = StringUtil.notNull(request.getParameter("centerId"));

				Timestamp date = new Timestamp(new Date().getTime());
				String rid = "R"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));
				OrderDao  ordersDao = new OrderDao();
				message = ordersDao.saveDmoneyOrders(user,userId,addressId,centerId,pid,numstr,rid,date);
				if(message.equals("yes"))
					message="复消订单保存成功，订单标号为："+rid+",请在订单列表中查看详情。";
				}else {
					message= "用户未登陆，请重新登陆！";
		}
			
	}else{
		message= "请勿重复提交数据！";
	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("order_zmoney_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
}
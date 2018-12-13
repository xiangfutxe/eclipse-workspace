package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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

import com.ssm.dao.InventoryApplyDao;
import com.ssm.dao.InventoryDao;
import com.ssm.dao.InventoryDetailDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.OrderDetailProductDao;
import com.ssm.dao.ProductDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Inventory;
import com.ssm.pojo.InventoryApply;
import com.ssm.pojo.InventoryDetail;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Product;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class InventoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	ICustomService  cs = new CustomService();


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

		}else if (method.equals("list")) {
			list(request, response);
		}else if (method.equals("edit")) {
			edit(request, response);

		}else if (method.equals("isExit")) {
			isExit(request, response);

		}else if (method.equals("isExitUpdate")) {
			isExitUpdate(request, response);

		}else if (method.equals("update")) {
			update(request, response);

		}else if (method.equals("add")) {
			add(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}else if (method.equals("apply_in")) {
			apply_in(request, response);
		}else if (method.equals("apply_in_review")) {
			apply_in_review(request, response);
		}else if (method.equals("apply_in_add")) {
			apply_in_add(request, response);
		}else if (method.equals("apply_in_save")) {
			apply_in_save(request, response);
		}else if (method.equals("apply_in_detail")) {
			apply_in_detail(request, response);
		}else if (method.equals("apply_in_review_detail")) {
			apply_in_review_detail(request, response);
		}else if (method.equals("apply_in_review_no")) {
			apply_in_review_no(request, response);
		}else if (method.equals("apply_in_review_yes")) {
			apply_in_review_yes(request, response);
		}else if (method.equals("admin_inventory_order_summary")) {
			admin_inventory_order_summary(request, response);
		}else if(method.equals("admin_inventory_order_summary_exportExcel")){
			admin_inventory_order_summary_exportExcel(request,response);
		}else if (method.equals("apply_out")) {
			apply_out(request, response);
		}else if (method.equals("apply_out_review")) {
			apply_out_review(request, response);
		}else if (method.equals("apply_out_add")) {
			apply_out_add(request, response);
		}else if (method.equals("apply_out_save")) {
			apply_out_save(request, response);
		}else if (method.equals("apply_out_detail")) {
			apply_out_detail(request, response);
		}else if (method.equals("apply_out_review_detail")) {
			apply_out_review_detail(request, response);
		}else if (method.equals("apply_out_review_no")) {
			apply_out_review_no(request, response);
		}else if (method.equals("apply_out_review_yes")) {
			apply_out_review_yes(request, response);
		}
	}
		
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryName = StringUtil.notNull(request.getParameter("inventoryName"));
	
	int pageNo = 1;
	int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
				
			Inventory inventory = new Inventory();
			
			inventory.setInventoryName(inventoryName);
			inventory.setState("1");
			
				
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				InventoryDao inventoryDao = new InventoryDao();
				pager = inventoryDao.findInventoryByPage(inventory,pager);
			
				request.setAttribute("pager", pager);
				request.setAttribute("inventoryName",inventoryName);
				
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
						.getRequestDispatcher("inventory_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					InventoryDao inventoryDao = new InventoryDao();
				Inventory inventory = inventoryDao.findById(Integer.valueOf(id));
				request.setAttribute("inventory", inventory);
				}else{
					message = "仓库信息获取失败。";
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
							.getRequestDispatcher("inventory_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("inventory_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String ids = StringUtil.notNull(request.getParameter("ids"));
		String message = "";
		try {
			if(admin!=null){
				InventoryDao inventoryDao = new InventoryDao();
				inventoryDao.deleteAll(ids);
				message = "批量删除仓库成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("inventory_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					InventoryDao inventoryDao = new InventoryDao();
					Inventory inventory = inventoryDao.findByName(name);
					if(inventory!=null){
							tag=1;
					}else{
						tag=2;
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", tag);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void isExitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int tag = 0;
		try {
				if (StringUtil.notNull(name).equals("")) {
					tag = 0;
				} else {
					InventoryDao inventoryDao = new InventoryDao();
					Inventory inventory = inventoryDao.findByName(name);
					if(inventory!=null){
						if(String.valueOf(inventory.getId()).equals(id)){
							
							tag=2;
						}else{
							tag=1;
						}
					}else{
						tag=2;
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", String.valueOf(tag));
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryName = StringUtil.notNull(request.getParameter("inventoryName"));
	String id = StringUtil.notNull(request.getParameter("id"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String linkman = request.getParameter("linkman");
					String address = request.getParameter("address");
					String phone = request.getParameter("phone");
					if(!(StringUtil.notNull(inventoryName).equals("")||StringUtil.notNull(linkman).equals("")||
							StringUtil.notNull(address).equals(""))|StringUtil.notNull(phone).equals("")){
						
					InventoryDao inventoryDao = new InventoryDao();
					Inventory inventory = new Inventory();
					Timestamp date = new Timestamp(new Date().getTime());
					inventory.setId(Integer.valueOf(id));
					inventory.setInventoryName(inventoryName);
					inventory.setLinkman(linkman);
					inventory.setPhone(phone);
					inventory.setAddress(address);
					inventory.setEndTime(date);
					message = inventoryDao.updateInventory(inventory);
					}else {
						request.setAttribute("message", "仓库名、负责人、所在地和联系电话不能为空，请重新录入！");
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
					.getRequestDispatcher("inventory_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("inventory_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("inventory_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryName = StringUtil.notNull(request.getParameter("inventoryName"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String linkman = request.getParameter("linkman");
					String address = request.getParameter("address");
					String phone = request.getParameter("phone");
				if(!(StringUtil.notNull(inventoryName).equals("")||StringUtil.notNull(linkman).equals("")||
						StringUtil.notNull(address).equals(""))|StringUtil.notNull(phone).equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					InventoryDao inventoryDao = new InventoryDao();
					Inventory inventory = new Inventory();
					inventory.setInventoryName(inventoryName);
					inventory.setLinkman(linkman);
					inventory.setPhone(phone);
					inventory.setState("1");
					inventory.setAddress(address);
					inventory.setEntryTime(date);
					inventory.setEndTime(date);
					message = inventoryDao.saveInventory(inventory);
				inventory=null;
				}else {
					request.setAttribute("message", "仓库名、负责人、所在地和联系电话不能为空，请重新录入！");
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
					.getRequestDispatcher("inventory_add_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void apply_in(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
	String type = StringUtil.notNull(request.getParameter("type"));

	String state = StringUtil.notNull(request.getParameter("state"));
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
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				
				InventoryDao inventoryDao = new InventoryDao();
				List<Inventory> coll_1 = inventoryDao.findAll();
				request.setAttribute("coll_1", coll_1);
				InventoryApply iay = new InventoryApply();
				iay.setPayType(1);
				iay.setInventory(inventoryId);;
				if(!type.equals(""))
				iay.setType(Integer.valueOf(type));
				if(!state.equals(""))
					iay.setState(Integer.valueOf(state));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setEndTime(endTime);
				}
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				pager = inventoryApplyDao.findByPage(iay, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("state", state);
				request.setAttribute("type", type);
				request.setAttribute("inventoryId", inventoryId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				
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
						.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
	String type = StringUtil.notNull(request.getParameter("type"));

	String state = StringUtil.notNull(request.getParameter("state"));
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
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				InventoryDao inventoryDao = new InventoryDao();
				List<Inventory> coll_1 = inventoryDao.findAll();
				request.setAttribute("coll_1", coll_1);
				InventoryApply iay = new InventoryApply();
				iay.setPayType(1);
				iay.setState(1);
				iay.setInventory(inventoryId);;
				if(!type.equals(""))
				iay.setType(Integer.valueOf(type));
				if(!state.equals(""))
					iay.setState(Integer.valueOf(state));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setEndTime(endTime);
				}
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				pager = inventoryApplyDao.findByPage(iay, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("state", state);
				request.setAttribute("type", type);
				request.setAttribute("inventoryId", inventoryId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				
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
						.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		try {
		if(admin!=null){
			int token = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token));
			InventoryDao inventoryDao = new InventoryDao();
			List<Inventory> coll_1 = inventoryDao.findAll();
			request.setAttribute("coll_1", coll_1);
			ProductDao productDao = new ProductDao();
			Product product = new Product();
			product.setState("1");
			product.setType(1);
			List<Product> coll = productDao.findByProduct(product);
			request.setAttribute("coll", coll);
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_add_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_add.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_in_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				
					String inventoryName = request.getParameter("inventoryId");
					String type = request.getParameter("type");
					String[] prices = request.getParameterValues("prices");
					String[] ids = request.getParameterValues("ids");
					String[] pids = request.getParameterValues("pids");
					String[] names = request.getParameterValues("names");
					String[] productTypes = request.getParameterValues("productTypes");
					String[] types = request.getParameterValues("types");
					String[] specifications = request.getParameterValues("specifications");
					String[] nums = request.getParameterValues("nums");
					InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
					 Timestamp date = new Timestamp(new Date().getTime());
						String applyId = "I"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));

					message = inventoryApplyDao.saveApply(admin,applyId,inventoryName,1,type,ids,pids,names,prices,productTypes,types,specifications,nums,date);
					if(message.equals("yes")){
						message="入库申请已提交，申请编号为："+applyId+"。";
					}else message= "未查询到需要入库的商品信息！";
				
				}else{
					message="请勿重复提交数据，请在仓库列表中查看是否保存成功！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		try {
		if(admin!=null){
			String applyId = request.getParameter("id");
			InventoryDetailDao inventoryDetailDao = new InventoryDetailDao();
			List<InventoryDetail> coll = inventoryDetailDao.findByApplyId(applyId);
			request.setAttribute("coll", coll);
			
			InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
			InventoryApply inventory = inventoryApplyDao.findByApplyId(applyId);
			request.setAttribute("inventory", inventory);
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_in_review_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		try {
		if(admin!=null){
			String applyId = request.getParameter("id");
			InventoryDetailDao inventoryDetailDao = new InventoryDetailDao();
			List<InventoryDetail> coll = inventoryDetailDao.findByApplyId(applyId);
			request.setAttribute("coll", coll);
			InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
			InventoryApply inventory = inventoryApplyDao.findByApplyId(applyId);
			request.setAttribute("inventory", inventory);
			int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token));
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_review_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_in_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token = StringUtil.notNull(request.getParameter("token"));
		String token_old = (String) request.getSession().getAttribute("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
		if(admin!=null){
			if (StringUtil.notNull(token_old).equals(token)) {
				String applyId = request.getParameter("applyId");
				String id = request.getParameter("id");
				Timestamp date =new Timestamp(new Date().getTime());
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				InventoryApply iay = new InventoryApply();
				iay.setId(Integer.valueOf(id));
				iay.setState(3);
				iay.setAdminByReviewerId(admin.getAdminName());
				iay.setReviewTime(date);
				int up1 =  inventoryApplyDao.update(iay);
				if(up1>0){
					message=applyId+"订单审核不通过。";
					cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_8, date);
				}else{
					message="订单信息更新失败。";
				}
			}else{
				message= "请勿重复提交数据！";
			}
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
			
		}
		}
	
	public void apply_in_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token = StringUtil.notNull(request.getParameter("token"));
		String token_old = (String) request.getSession().getAttribute("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
		if(admin!=null){
			if (StringUtil.notNull(token_old).equals(token)) {
				String applyId = request.getParameter("applyId");
				Timestamp date =new Timestamp(new Date().getTime());
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				message =   inventoryApplyDao.reviewYes(admin, applyId, date);
			}else{
				message= "请勿重复提交数据！";
			}
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
			
		}
		}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_inventory_order_summary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	Collection coll = new ArrayList();
		String message = "";
		try {
			if(admin!=null){
				OrderDetailProductDao odpDao = new OrderDetailProductDao();
				odpDao.checkOrderDetailProuct();
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				Order order1 = new Order();
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setEndTime(endTime);
				}
				OrderDao orderDao = new OrderDao();
				List<OrderDetailProduct> olist = orderDao.findOrderDetailProductByList(order1);
				ProductDao porductDao = new ProductDao();
				List<Product> plist = porductDao.findAllProduct();
				porductDao = new ProductDao();
				int num = porductDao.getMaxId()+1;
				Product[] pstr = new Product[num+1];
				Integer[] sum ={0,0,0,0,0,0,0,0}; 
				for(int i =0;i<plist.size();i++){
					int pid = plist.get(i).getId();
					if(pstr[pid]==null) pstr[pid] = new Product();
					pstr[pid].setId(plist.get(i).getId());
					pstr[pid].setProductId(plist.get(i).getProductId());
					pstr[pid].setProductName(plist.get(i).getProductName());
					pstr[pid].setSpecification(plist.get(i).getSpecification());
					pstr[pid].setNum(plist.get(i).getNum());
					pstr[pid].setType(plist.get(i).getType());
					pstr[pid].setNum1(0);
					pstr[pid].setNum2(0);
					pstr[pid].setNum3(0);
					pstr[pid].setNum4(0);
					pstr[pid].setNum5(0);
					pstr[pid].setNum6(0);
					sum[6] = sum[6]+pstr[pid].getNum();
				}
				for(int i=0;i<olist.size();i++){
					int id = olist.get(i).getPid();
					int state= olist.get(i).getState();
					int pnum= olist.get(i).getNum();
					if(state==1){
						pstr[id].setNum1(pstr[id].getNum1()+pnum);
						sum[0] = sum[0]+pnum;
					}else if(state==2){
						pstr[id].setNum2(pstr[id].getNum2()+pnum);
						sum[1] = sum[1]+pnum;
					}else if(state==3){
						pstr[id].setNum3(pstr[id].getNum3()+pnum);
						sum[2] = sum[2]+pnum;
					}else if(state==4){
						pstr[id].setNum4(pstr[id].getNum4()+pnum);
						sum[3] = sum[3]+pnum;
					}else if(state>=5){
						pstr[id].setNum5(pstr[id].getNum5()+pnum);
						sum[4] = sum[4]+pnum;
					}
					if(state>0&&state<5){
						pstr[id].setNum6(pstr[id].getNum6()+pnum);
						sum[5] = sum[5]+pnum;
					}
				}
				for(int i=1;i<pstr.length;i++){
					if(pstr[i]!=null){
						if(pstr[i].getType()==1)
						coll.add(pstr[i]);
					}
				}
				request.setAttribute("coll", coll);
				request.setAttribute("sum", sum);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_order_summary.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_order_summary_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void admin_inventory_order_summary_exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
		 String filename = "库存汇总列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<Product>  result = new ArrayList<Product> ();
			try {
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				Order order1 = new Order();
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					order1.setEndTime(endTime);
				}
				OrderDao orderDao = new OrderDao();
				List<OrderDetailProduct> olist = orderDao.findOrderDetailProductByList(order1);
				ProductDao porductDao = new ProductDao();
				List<Product> plist = porductDao.findAllProduct();
				porductDao = new ProductDao();
				int num = porductDao.getMaxId()+1;
				Product[] pstr = new Product[num+1];
				Integer[] sum ={0,0,0,0,0,0,0,0}; 
				for(int i =0;i<plist.size();i++){
					int pid = plist.get(i).getId();
					if(pstr[pid]==null) pstr[pid] = new Product();
					pstr[pid].setId(plist.get(i).getId());
					pstr[pid].setProductId(plist.get(i).getProductId());
					pstr[pid].setProductName(plist.get(i).getProductName());
					pstr[pid].setSpecification(plist.get(i).getSpecification());
					pstr[pid].setNum(plist.get(i).getNum());
					pstr[pid].setType(plist.get(i).getType());
					pstr[pid].setNum1(0);
					pstr[pid].setNum2(0);
					pstr[pid].setNum3(0);
					pstr[pid].setNum4(0);
					pstr[pid].setNum5(0);
					pstr[pid].setNum6(0);
					sum[6] = sum[6]+pstr[pid].getNum();
				}
				for(int i=0;i<olist.size();i++){
					int id = olist.get(i).getPid();
					int state= olist.get(i).getState();
					int pnum= olist.get(i).getNum();
					if(state==1){
						pstr[id].setNum1(pstr[id].getNum1()+pnum);
						sum[0] = sum[0]+pnum;
					}else if(state==2){
						pstr[id].setNum2(pstr[id].getNum2()+pnum);
						sum[1] = sum[1]+pnum;
					}else if(state==3){
						pstr[id].setNum3(pstr[id].getNum3()+pnum);
						sum[2] = sum[2]+pnum;
					}else if(state==4){
						pstr[id].setNum4(pstr[id].getNum4()+pnum);
						sum[3] = sum[3]+pnum;
					}else if(state>=5){
						pstr[id].setNum5(pstr[id].getNum5()+pnum);
						sum[4] = sum[4]+pnum;
					}
					if(state>0&&state<4){
						pstr[id].setNum6(pstr[id].getNum6()+pnum);
						sum[5] = sum[5]+pnum;
					}
				}
				for(int i=1;i<pstr.length;i++){
					if(pstr[i]!=null){
						if(pstr[i].getType()==1)
							result.add(pstr[i]);
					}
				}
				String[][] content = new String[result.size()+2][12];
				content[0][0]="序号";
				content[0][1]="产品编号";
				content[0][2]="产品名称";
				content[0][3]="产品规格";
				content[0][4]="待确认数量";
				content[0][5]="待配货数量";
				content[0][6]="配货中数量";
				content[0][7]="出库中数量";
				content[0][8]="已发货数量";
				content[0][9]="待发小计";
				content[0][10]="当前库存";
				content[0][11]="差额";
				if(result.size()>0){
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][3]  =  StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][4]  = StringUtil.decimalFormat(result.get(i).getNum1());
					content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getNum2());
					content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getNum3());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getNum4());
					content[i+1][8]  = StringUtil.decimalFormat(result.get(i).getNum5());
					content[i+1][9]  = StringUtil.decimalFormat(result.get(i).getNum6());
					content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getNum());
					content[i+1][11] = StringUtil.decimalFormat(result.get(i).getNum()-result.get(i).getNum6());
				}
				content[result.size()+1][0]  = String.valueOf(result.size()+1);
				content[result.size()+1][1]  = "合计";
				content[result.size()+1][2]  = "-";
				content[result.size()+1][3]  = "-";
				content[result.size()+1][4]  = StringUtil.decimalFormat(sum[0]);
				content[result.size()+1][5]  = StringUtil.decimalFormat(sum[1]);
				content[result.size()+1][6]  = StringUtil.decimalFormat(sum[2]);
				content[result.size()+1][7] = StringUtil.decimalFormat(sum[3]);
				content[result.size()+1][8] = StringUtil.decimalFormat(sum[4]);
				content[result.size()+1][9] = StringUtil.decimalFormat(sum[5]);
				content[result.size()+1][10] = StringUtil.decimalFormat(sum[6]);
				content[result.size()+1][11] = StringUtil.decimalFormat(sum[6]-sum[4]);
				}
		 HSSFWorkbook wb = new HSSFWorkbook();  
		 HSSFSheet sheet = wb.createSheet("库存汇总列表("+startTimeStr+"至"+endTimeStr+")");  
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
	
	public void apply_out(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
	String type = StringUtil.notNull(request.getParameter("type"));

	String state = StringUtil.notNull(request.getParameter("state"));
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
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				
				InventoryDao inventoryDao = new InventoryDao();
				List<Inventory> coll_1 = inventoryDao.findAll();
				request.setAttribute("coll_1", coll_1);
				InventoryApply iay = new InventoryApply();
				iay.setPayType(2);
				iay.setInventory(inventoryId);;
				if(!type.equals(""))
				iay.setType(Integer.valueOf(type));
				if(!state.equals(""))
					iay.setState(Integer.valueOf(state));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setEndTime(endTime);
				}
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				pager = inventoryApplyDao.findByPage(iay, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("state", state);
				request.setAttribute("type", type);
				request.setAttribute("inventoryId", inventoryId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				
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
						.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_out_review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
	String type = StringUtil.notNull(request.getParameter("type"));

	String state = StringUtil.notNull(request.getParameter("state"));
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
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				InventoryDao inventoryDao = new InventoryDao();
				List<Inventory> coll_1 = inventoryDao.findAll();
				request.setAttribute("coll_1", coll_1);
				InventoryApply iay = new InventoryApply();
				iay.setPayType(2);
				iay.setState(1);
				iay.setInventory(inventoryId);;
				if(!type.equals(""))
				iay.setType(Integer.valueOf(type));
				if(!state.equals(""))
					iay.setState(Integer.valueOf(state));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					iay.setEndTime(endTime);
				}
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				pager = inventoryApplyDao.findByPage(iay, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("state", state);
				request.setAttribute("type", type);
				request.setAttribute("inventoryId", inventoryId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				
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
						.getRequestDispatcher("inventory_apply_out_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_out_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		try {
		if(admin!=null){
			int token = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token));
			InventoryDao inventoryDao = new InventoryDao();
			List<Inventory> coll_1 = inventoryDao.findAll();
			request.setAttribute("coll_1", coll_1);
			ProductDao productDao = new ProductDao();
			Product product = new Product();
			product.setState("1");
			product.setType(1);
			List<Product> coll = productDao.findByProduct(product);
			request.setAttribute("coll", coll);
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_add_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_add.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_out_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				
					String inventoryName = request.getParameter("inventoryId");
					String type = request.getParameter("type");
					String[] prices = request.getParameterValues("prices");
					String[] ids = request.getParameterValues("ids");
					String[] pids = request.getParameterValues("pids");
					String[] names = request.getParameterValues("names");
					String[] productTypes = request.getParameterValues("productTypes");
					String[] types = request.getParameterValues("types");
					String[] specifications = request.getParameterValues("specifications");
					String[] nums = request.getParameterValues("nums");
					InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
					 Timestamp date = new Timestamp(new Date().getTime());
						String applyId = "I"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));

					message = inventoryApplyDao.saveApply(admin,applyId,inventoryName,2,type,ids,pids,names,prices,productTypes,types,specifications,nums,date);
					if(message.equals("yes")){
						message="出库申请已提交，申请编号为："+applyId+"。";
					}else message= "未查询到需要出库的商品信息！";
				
				}else{
					message="请勿重复提交数据，请在仓库列表中查看是否保存成功！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		try {
		if(admin!=null){
			String applyId = request.getParameter("id");
			InventoryDetailDao inventoryDetailDao = new InventoryDetailDao();
			List<InventoryDetail> coll = inventoryDetailDao.findByApplyId(applyId);
			request.setAttribute("coll", coll);
			
			InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
			InventoryApply inventory = inventoryApplyDao.findByApplyId(applyId);
			request.setAttribute("inventory", inventory);
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_out_review_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		try {
		if(admin!=null){
			String applyId = request.getParameter("id");
			InventoryDetailDao inventoryDetailDao = new InventoryDetailDao();
			List<InventoryDetail> coll = inventoryDetailDao.findByApplyId(applyId);
			request.setAttribute("coll", coll);
			InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
			InventoryApply inventory = inventoryApplyDao.findByApplyId(applyId);
			request.setAttribute("inventory", inventory);
			int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token));
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_review_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_review_detail.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_out_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token = StringUtil.notNull(request.getParameter("token"));
		String token_old = (String) request.getSession().getAttribute("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
		if(admin!=null){
			if (StringUtil.notNull(token_old).equals(token)) {
				String applyId = request.getParameter("applyId");
				String id = request.getParameter("id");
				Timestamp date =new Timestamp(new Date().getTime());
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				InventoryApply iay = new InventoryApply();
				iay.setId(Integer.valueOf(id));
				iay.setState(3);
				iay.setAdminByReviewerId(admin.getAdminName());
				iay.setReviewTime(date);
				int up1 =  inventoryApplyDao.update(iay);
				if(up1>0){
					message=applyId+"订单审核不通过。";
					cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_8, date);
				}else{
					message="订单信息更新失败。";
				}
			}else{
				message= "请勿重复提交数据！";
			}
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_review_message.jsp");
				dispatcher.forward(request, response);
			
		}
		}
	
	public void apply_out_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token = StringUtil.notNull(request.getParameter("token"));
		String token_old = (String) request.getSession().getAttribute("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
		if(admin!=null){
			if (StringUtil.notNull(token_old).equals(token)) {
				String applyId = request.getParameter("applyId");
				Timestamp date =new Timestamp(new Date().getTime());
				InventoryApplyDao inventoryApplyDao = new InventoryApplyDao();
				message =   inventoryApplyDao.reviewOutYes(admin, applyId, date);
			}else{
				message= "请勿重复提交数据！";
			}
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("inventory_apply_out_review_message.jsp");
				dispatcher.forward(request, response);
			
		}
		}
}
	
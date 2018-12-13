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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.Inventory;
import com.pojo.InventoryApply;
import com.pojo.InventoryDetail;
import com.pojo.InventoryProduct;
import com.pojo.Product;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.Pager;
import com.utils.StringUtil;

public class InventoryServlet extends HttpServlet {
	
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
		}else if(method.equals("save")){
			try {
				save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("list")){
			list(request,response);
		}
		else if(method.equals("isExit")){
			isExit(request,response);
		}
		else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("del")){
			del(request,response);
		}else if(method.equals("apply_in_all")){
			apply_in_all(request,response);
		}else if(method.equals("apply_in")){
			apply_in(request,response);
		}else if(method.equals("apply_in_add")){
			apply_in_add(request,response);
		}else if(method.equals("apply_in_save")){
			try {
				apply_in_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("apply_in_detail")){
				apply_in_detail(request,response);
		}else if(method.equals("apply_in_detail_all")){
			apply_in_all_detail(request,response);
	}
		else if(method.equals("apply_in_del")){
			apply_in_del(request,response);
		}else if(method.equals("apply_in_review")){
			apply_in_review(request,response);
		}else if(method.equals("apply_in_review_detail")){
			apply_in_review_detail(request,response);
		}else if(method.equals("apply_in_review_yes")){
			apply_in_review_yes(request,response);
		}else if(method.equals("apply_in_review_no")){
			apply_in_review_no(request,response);
		}else if(method.equals("inventory_product")){
			inventory_product(request,response);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String inventoryName = request.getParameter("inventoryName");
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "";
						if(inventoryName==null) sql ="select * from inventory where state!='0' order by id asc";
						else if(inventoryName.equals("")) sql ="select * from inventory where state!='0' order by id asc";
						else{
							sql = "select * from inventory where inventoryName like '%"+inventoryName+"%' and state!='0' order by id asc";
							request.setAttribute("inventoryName", inventoryName);
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("inventoryName"));
					inventory.setLinkman(rs.getString("linkman"));
					inventory.setAddress(rs.getString("address"));
					inventory.setPhone(rs.getString("phone"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entryTime"));
					inventory.setEndTime(rs.getTimestamp("endTime"));
					result.add(inventory);
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
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
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
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String inventoryName = request.getParameter("inventoryName");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(inventoryName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from inventory where inventoryName ='"+inventoryName+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					if(rs.next()) tag =1;
					else
						tag = 2;
				}
			}else {
				tag = 0;
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
		}finally{
			db.close();
		}
		
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		}
	
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
	
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String inventoryName = request.getParameter("inventoryName");
					String linkman = request.getParameter("linkman");
					String address = request.getParameter("address");
					String phone = request.getParameter("phone");
				if(!(StringUtil.notNull(inventoryName).equals("")||StringUtil.notNull(linkman).equals("")||
						StringUtil.notNull(address).equals(""))|StringUtil.notNull(phone).equals("")){
					String sql = "select * from inventory where inventoryName ='"+inventoryName+"'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					db.close();
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 System.out.println(date);
					 String sqli = "insert into inventory(inventoryName,linkman,address,phone,state,entryTime,endTime) values('"+inventoryName+"','"
					 +linkman+"','"+address+"','"+phone+"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 conn.setAutoCommit(autoCommit);
					 request.setAttribute("message", "仓库信息保存成功，请核对仓库名："+inventoryName+"。");
				}else{
					request.setAttribute("message", "仓库名已被注册，请重新录入！");
				}
				}else {
					request.setAttribute("message", "仓库名、负责人、所在地和联系电话不能为空，请重新录入！");
				}
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在仓库列表中查看是否保存成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_message_add.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String idstr = request.getParameter("id");
				if(!StringUtil.notNull(idstr).equals("")){
						int id = Integer.valueOf(idstr);
				String sql = "select * from inventory where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("inventoryName");
					db.close();
					String sqli = "update inventory set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="仓库删除成功，删除仓库为"+name+"！";
				}
			
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="仓库ID信息有误，请重新选择！";
			}
			}else {
				message="数据库连接已断开！";
			}
				}else{
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			}else{
				message="管理员用户未登陆，请重新登陆！";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_all(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
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
				if(rankstr[3][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from inventory where state='1' order by id asc";
					stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("inventoryName"));
					inventory.setLinkman(rs.getString("linkman"));
					inventory.setAddress(rs.getString("address"));
					inventory.setPhone(rs.getString("phone"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entryTime"));
					inventory.setEndTime(rs.getTimestamp("endTime"));
					coll_1.add(inventory);
				}
				request.setAttribute("coll_1", coll_1);
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventory_apply where time>='"+startTime+"' and time<='"+endTime+"' and  state like'%"+state+"%' and inventory like '%"+inventoryId+"%' order by id desc";
					
				}else{
					sqls ="select * from inventory_apply where state like'%"+state+"%' and inventory like '%"+inventoryId+"%' order by id desc";
				}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setInventory(rs.getString("inventory"));
					inventory.setApplyId(rs.getString("apply_id"));
					inventory.setTime(rs.getTimestamp("time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setAdminByOperatorId(rs.getString("operator"));
					inventory.setAdminByReviewerId(rs.getString("reviewer"));
					inventory.setType(rs.getType());
					inventory.setState(rs.getInt("state"));
					result.add(inventory);
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
				request.setAttribute("inventoryId", inventoryId);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_all.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_all_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from inventory where state='1' order by id asc";
					stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("inventoryName"));
					inventory.setLinkman(rs.getString("linkman"));
					inventory.setAddress(rs.getString("address"));
					inventory.setPhone(rs.getString("phone"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entryTime"));
					inventory.setEndTime(rs.getTimestamp("endTime"));
					coll_1.add(inventory);
				}
				request.setAttribute("coll_1", coll_1);
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventory_apply where time>='"+startTime+"' and time<='"+endTime+"' and  state like'%"+state+"%' and inventory like '%"+inventoryId+"%' order by id desc";
					
				}else{
					sqls ="select * from inventory_apply where state like'%"+state+"%' and inventory like '%"+inventoryId+"%' order by id desc";
				}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setInventory(rs.getString("inventory"));
					inventory.setApplyId(rs.getString("apply_id"));
					inventory.setTime(rs.getTimestamp("time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setAdminByOperatorId(rs.getString("operator"));
					inventory.setAdminByReviewerId(rs.getString("reviewer"));
					inventory.setType(rs.getType());
					inventory.setState(rs.getInt("state"));
					result.add(inventory);
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
				request.setAttribute("inventoryId", inventoryId);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		String message = "";
		try{
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
		
		if(db.createConn()){
			int token = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token));
		String sql ="select * from product where state='1' order by id asc";
		stmt= db.getStmtread();
		rs =stmt.executeQuery(sql);
		while(rs.next()){
			Product pt = new Product();
			pt.setId(rs.getInt("id"));
			pt.setProductId(rs.getString("product_id"));
			pt.setProductName(rs.getString("product_name"));
			pt.setSpecification(rs.getString("specification"));
			pt.setType(rs.getInt("type"));
			pt.setImageUrl(rs.getString("image_url"));
			pt.setState(rs.getString("state"));
			pt.setEntryTime(rs.getTimestamp("entry_time"));
			pt.setEndTime(rs.getTimestamp("end_time"));
			coll.add(pt);
		}
		request.setAttribute("coll", coll);
		
		String sql1 ="select * from inventory where state='1' order by id asc";
		stmt= db.getStmtread();
		rs =stmt.executeQuery(sql1);
		while(rs.next()){
			Inventory inventory = new Inventory();
			inventory.setId(rs.getInt("id"));
			inventory.setInventoryName(rs.getString("inventoryName"));
			inventory.setLinkman(rs.getString("linkman"));
			inventory.setAddress(rs.getString("address"));
			inventory.setPhone(rs.getString("phone"));
			inventory.setState(rs.getString("state"));
			inventory.setEntryTime(rs.getTimestamp("entryTime"));
			inventory.setEndTime(rs.getTimestamp("endTime"));
			coll_1.add(inventory);
		}
		request.setAttribute("coll_1", coll_1);
		}else{
			request.setAttribute("message", "数据库连接失败，如有需要请与系统管理员联系！");
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_in_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
	
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String inventoryName = request.getParameter("inventoryId");
					String[] prices = request.getParameterValues("prices");
					String[] ids = request.getParameterValues("ids");
					String[] nums = request.getParameterValues("nums");
					List<InventoryDetail> plist = new ArrayList<InventoryDetail>();
					int inventoryId = 0;
					String sqly = "select * from inventory where inventoryName ='"+inventoryName+"'";
					stmt = conn.createStatement();
					rs =stmt.executeQuery(sqly);
					if(rs.next()){
						inventoryId = rs.getInt("id");
					for(int i=0;i<nums.length;i++){
						if(!(StringUtil.notNull(nums[i]).equals("")||StringUtil.notNull(nums[i]).equals("0"))){
							String sql = "select * from product where id ='"+ids[i]+"'";
							stmt = conn.createStatement();
							rs =stmt.executeQuery(sql);
							if(rs.next()){
								InventoryDetail pt = new InventoryDetail();
								pt.setId(rs.getInt("id"));
								pt.setProductId(rs.getString("product_id"));
								pt.setProductName(rs.getString("product_name"));
								pt.setSpecification(rs.getString("specification"));
								double price = 0;
								if(!StringUtil.notNull(prices[i]).equals("")) price = Double.valueOf(prices[i]);
								pt.setPrice(price);
								pt.setType(rs.getInt("type"));
								pt.setNum(Integer.valueOf(nums[i]));
								plist.add(pt);
							}
						}
					}
					
					if(plist.size()>0){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
						String  applyId = cs.createInventoryApplyId(date);
						String sql1= "insert into inventory_apply(apply_id,operator,inventory,inventory_id,state,time) values('"+applyId+"','"+
						admin.getAdminName()+"','"+inventoryName+"','"+inventoryId+"','1',+'"+date+"')";
						 stmt = conn.createStatement();
						 stmt.addBatch(sql1);
						 for(int i=0;i<plist.size();i++){
							 String sql2= "insert into inventory_detail(apply_id,product_id,product_name,specification,type,price,num) values('"+applyId+"','"+
									 plist.get(i).getProductId()+"','"+ plist.get(i).getProductName()
									 +"','"+ plist.get(i).getSpecification()+"','"+ plist.get(i).getType()+"','"
									 + plist.get(i).getPrice()+"','"+ plist.get(i).getNum()+"')";
							 stmt.addBatch(sql2);
						 }
						 stmt.executeBatch();
						 request.setAttribute("message", "入库申请已提交，申请编号为："+applyId+"。");
					}else request.setAttribute("message", "未查询到需要入库的商品信息！");
					}else {
						request.setAttribute("message", "仓库信息获取失败！");
					}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			 conn.commit();
			 conn.setAutoCommit(autoCommit);	 
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在仓库列表中查看是否保存成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_all_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String sql ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setType(rs.getInt("type"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setNum(rs.getInt("num"));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				String sql1 ="select * from inventory_apply where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("apply_id"));
					inventory.setAdminByOperatorId(rs.getString("operator"));
					inventory.setAdminByReviewerId(rs.getString("reviewer"));
					
					inventory.setTime(rs.getTimestamp("time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setInventory(rs.getString("inventory"));
					inventory.setState(rs.getInt("state"));
					request.setAttribute("inventory", inventory);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_all_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_all_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String sql ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setType(rs.getInt("type"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setNum(rs.getInt("num"));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				String sql1 ="select * from inventory_apply where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setAdminByOperatorId(rs.getString("operator"));
					inventory.setAdminByReviewerId(rs.getString("reviewer"));
					inventory.setTime(rs.getTimestamp("time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setInventory(rs.getString("inventory"));
					inventory.setState(rs.getInt("state"));
					request.setAttribute("inventory", inventory);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				Timestamp date = new Timestamp(new Date().getTime());
					String sql ="select * from inventory_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
					String sqlu ="update inventory_apply set state='0' where apply_id='"+applyId+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "入库申请删除成功，删除的编号为："+applyId+"。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="select * from inventory where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("inventoryName"));
					inventory.setLinkman(rs.getString("linkman"));
					inventory.setAddress(rs.getString("address"));
					inventory.setPhone(rs.getString("phone"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entryTime"));
					inventory.setEndTime(rs.getTimestamp("endTime"));
					coll_1.add(inventory);
				}
				request.setAttribute("coll_1", coll_1);
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventory_apply where time>='"+startTime+"' and time<='"+endTime+"' and  state ='1' and inventory like '%"+inventoryId+"%' order by id desc";
					
				}else{
					sqls ="select * from inventory_apply where state ='1' and inventory like '%"+inventoryId+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setInventory(rs.getString("inventory"));
					inventory.setApplyId(rs.getString("apply_id"));
					inventory.setTime(rs.getTimestamp("time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setAdminByOperatorId(rs.getString("operator"));
					inventory.setAdminByReviewerId(rs.getString("reviewer"));
					inventory.setType(rs.getType());
					inventory.setState(rs.getInt("state"));
					result.add(inventory);
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
				request.setAttribute("inventoryId", inventoryId);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_review_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql ="select * from inventory_detail where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("apply_id"));
					inventory.setProductId(rs.getString("product_id"));
					inventory.setProductName(rs.getString("product_name"));
					inventory.setType(rs.getInt("type"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setNum(rs.getInt("num"));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				
				String sql1 ="select * from inventory_apply where apply_id='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("apply_id"));
					inventory.setAdminByOperatorId(rs.getString("operator"));
					inventory.setAdminByReviewerId(rs.getString("reviewer"));
					inventory.setTime(rs.getTimestamp("time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setInventory(rs.getString("inventory"));
					inventory.setState(rs.getInt("state"));
					request.setAttribute("inventory", inventory);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String sql ="select * from inventory_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventory_apply set state='3',reviewer='"+admin.getAdminName()+"',review_time='"+date+"' where apply_id='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "入库申请审核不通过，编号为："+applyId+"。";
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				List<String> slist = new ArrayList<String>();
					String sql ="select * from inventory_apply where apply_id='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					String inventoryName = rs.getString("inventory");
					String inventoryId = rs.getString("inventory_id");
					if(state==1){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventory_apply set state='2',reviewer='"+admin.getAdminName()+"',review_time='"+date+"' where apply_id='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					String sql1="select * from inventory_detail where apply_id='"+applyId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						int pnum = rs.getInt("num");
						int pid = rs.getInt("id");
						String productId = rs.getString("product_id");
						String productName = rs.getString("product_name");
						String specification = rs.getString("specification");
						
						String sqlp="select * from product where product_id='"+productId+"' for update";
						Statement stmt1 = null;
						ResultSet rs1 = null;
						stmt1 = conn.createStatement();
						rs1 = stmt1.executeQuery(sqlp);
						if(rs1.next()){
							int num = rs1.getInt("num");
							int total = rs1.getInt("total_num");
							String sqlpu = "update product set num='"+(num+pnum)+"',total_num='"+(total+pnum)+"' where product_id='"+productId+"'";
							slist.add(sqlpu);
						}
					}
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
					message= "入库申请审核通过，审核编号为："+applyId+"。";
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void inventory_product(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][12].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String inventoryId = StringUtil.notNull(request.getParameter("inventoryId"));
				String productId = StringUtil.notNull(request.getParameter("productId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 10;
				String sql ="select * from inventory where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("inventoryName"));
					inventory.setLinkman(rs.getString("linkman"));
					inventory.setAddress(rs.getString("address"));
					inventory.setPhone(rs.getString("phone"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entryTime"));
					inventory.setEndTime(rs.getTimestamp("endTime"));
					coll_1.add(inventory);
				}
				System.out.println(inventoryId);
				request.setAttribute("coll_1", coll_1);
				if(!inventoryId.equals("")){
					String sql1 ="select * from inventory_product where inventoryName='"+inventoryId+"' and productId like'%"+productId+"%' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryProduct inventory = new InventoryProduct();
						inventory.setId(rs.getInt("id"));
						inventory.setInventoryName(rs.getString("inventoryName"));
						inventory.setInventoryId(rs.getInt("inventoryId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));
						inventory.setSpecification(rs.getString("specification"));
						inventory.setNum(rs.getInt("num"));
						inventory.setTotalNum(rs.getInt("totalNum"));
						inventory.setEntryTime(rs.getTimestamp("entryTime"));
						inventory.setEndTime(rs.getTimestamp("endTime"));
						result.add(inventory);
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
				request.setAttribute("inventoryId", inventoryId);
				request.setAttribute("productId", productId);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}

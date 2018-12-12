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
import com.pojo.Inventory;
import com.pojo.InventoryApply;
import com.pojo.InventoryDetail;
import com.pojo.InventoryProduct;
import com.pojo.InventoryProductStock;
import com.pojo.Product;
import com.pojo.SettleStock;
import com.pojo.Supplier;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.DateUtil;
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
		}else if(method.equals("apply_in")){
			apply_in(request,response);
		}else if(method.equals("apply_in_all")){
			apply_in_all(request,response);
		}else if(method.equals("apply_in_check")){
			apply_in_check(request,response);
		}else if(method.equals("apply_in_cfm")){
			apply_in_cfm(request,response);
		}else if(method.equals("apply_in_review")){
			apply_in_review(request,response);
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
		}else if(method.equals("apply_in_pro_add")){
			apply_in_pro_add(request,response);
		}else if(method.equals("apply_in_pro_del")){
			apply_in_pro_del(request,response);
		}else if(method.equals("apply_in_pro_cfm")){
				apply_in_pro_cfm(request,response);
			}else if(method.equals("apply_in_product_detail")){
				apply_in_product_detail(request,response);
		}else if(method.equals("exportExcel_apply_in_product_detail")){
			exportExcel_apply_in_product_detail(request,response);
			
		}else if(method.equals("apply_in_4")){
				apply_in_4(request,response);
			}else if(method.equals("apply_in_4_del")){
				apply_in_4_del(request,response);
			}else if(method.equals("apply_in_4_review")){
				apply_in_4_review(request,response);
			}
		else if(method.equals("apply_in_4_add")){
			apply_in_4_add(request,response);
		}else if(method.equals("apply_in_4_save")){
			try {
				apply_in_4_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("apply_in_4_detail")){
				apply_in_4_detail(request,response);
		}else if(method.equals("apply_in_4_pro_add")){
			apply_in_4_pro_add(request,response);
		}else if(method.equals("apply_in_4_pro_del")){
			apply_in_4_pro_del(request,response);
		}else if(method.equals("apply_in_4_pro_cfm")){
				apply_in_4_pro_cfm(request,response);
			}else if(method.equals("apply_in_4_detail_review")){
				apply_in_4_detail_review(request,response);
			}else if(method.equals("apply_in_4_review_yes")){
				try {
					apply_in_4_review_yes(request,response);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}else if(method.equals("apply_in_4_review_no")){
				try {
					apply_in_4_review_no(request,response);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		else if(method.equals("apply_in_detail_all")){
			apply_in_detail_all(request,response);
	}
		else if(method.equals("apply_in_del")){
			apply_in_del(request,response);
		}else if(method.equals("apply_in_detail_all")){
			apply_in_detail_all(request,response);
		}else if(method.equals("apply_in_detail_check")){
			apply_in_detail_check(request,response);
		}else if(method.equals("apply_in_detail_cfm")){
			apply_in_detail_cfm(request,response);
		}else if(method.equals("apply_in_detail_review")){
			apply_in_detail_review(request,response);
		}else if(method.equals("apply_in_approve_yes")){
			apply_in_approve_yes(request,response);
		}else if(method.equals("apply_in_approve_no")){
			apply_in_approve_no(request,response);
		}else if(method.equals("apply_in_approve_add")){
			apply_in_approve_add(request,response);
		}else if(method.equals("apply_in_check_yes")){
			apply_in_check_yes(request,response);
		}else if(method.equals("apply_in_check_no")){
			apply_in_check_no(request,response);
		}else if(method.equals("apply_in_cfm_edit")){
			apply_in_cfm_edit(request,response);
		}else if(method.equals("apply_in_cfm_update")){
			apply_in_cfm_update(request,response);
		}else if(method.equals("apply_in_cfm_yes")){
			apply_in_cfm_yes(request,response);
		}else if(method.equals("apply_in_cfm_no")){
			apply_in_cfm_no(request,response);
		}else if(method.equals("apply_in_review_yes")){
			apply_in_review_yes(request,response);
		}else if(method.equals("apply_in_review_no")){
			apply_in_review_no(request,response);
		}else if(method.equals("apply_in_review_add")){
			apply_in_review_add(request,response);
		}else if(method.equals("inventory_product")){
			inventory_product(request,response);
		}else if(method.equals("two_save")){
			try {
				two_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("two_list")){
			two_list(request,response);
		}
		else if(method.equals("two_isExit")){
			two_isExit(request,response);
		}
		else if(method.equals("two_add")){
			two_add(request,response);
		}else if(method.equals("two_del")){
			two_del(request,response);
		}else if(method.equals("three_save")){
			try {
				three_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("three_list")){
			three_list(request,response);
		}
		else if(method.equals("three_isExit")){
			three_isExit(request,response);
		}
		else if(method.equals("three_add")){
			three_add(request,response);
		}else if(method.equals("three_del")){
			three_del(request,response);
		}else if(method.equals("apply_out")){
			apply_out(request,response);
		}else if(method.equals("apply_out_check")){
			apply_out_check(request,response);
		}else if(method.equals("apply_out_cfm")){
			apply_out_cfm(request,response);
		}else if(method.equals("apply_out_review")){
			apply_out_review(request,response);
		}else if(method.equals("apply_out_add")){
			apply_out_add(request,response);
		}else if(method.equals("apply_out_save")){
			try {
				apply_out_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("apply_out_detail")){
				apply_out_detail(request,response);
		}else if(method.equals("apply_out_all")){
			apply_out_all(request,response);
	}else if(method.equals("apply_out_detail_all")){
			apply_out_detail_all(request,response);
	}else if(method.equals("apply_out_pro_add")){
			apply_out_pro_add(request,response);
	}else if(method.equals("apply_out_pro_cfm")){
		apply_out_pro_cfm(request,response);
}else if(method.equals("apply_out_pro_del")){
	apply_out_pro_del(request,response);
}
		else if(method.equals("apply_out_del")){
			apply_out_del(request,response);
		}else if(method.equals("apply_out_review")){
			apply_out_review(request,response);
		}else if(method.equals("apply_out_detail_check")){
			apply_out_detail_check(request,response);
		}else if(method.equals("apply_out_detail_cfm")){
			apply_out_detail_cfm(request,response);
		}else if(method.equals("apply_out_detail_review")){
			apply_out_detail_review(request,response);
		}else if(method.equals("apply_out_check_yes")){
			apply_out_check_yes(request,response);
		}else if(method.equals("apply_out_check_no")){
			apply_out_check_no(request,response);
		}else if(method.equals("apply_out_cfm_yes")){
			apply_out_cfm_yes(request,response);
		}else if(method.equals("apply_out_cfm_no")){
			apply_out_cfm_no(request,response);
		}else if(method.equals("apply_out_review_yes")){
			apply_out_review_yes(request,response);
		}else if(method.equals("apply_out_review_no")){
			apply_out_review_no(request,response);
		}else if(method.equals("apply_out_cfm_edit")){
			apply_out_cfm_edit(request,response);
		}else if(method.equals("apply_out_cfm_update")){
			apply_out_cfm_update(request,response);
		}else if(method.equals("apply_out_review_add")){
			apply_out_review_add(request,response);
		}else if(method.equals("apply_out_4")){
			apply_out_4(request,response);
		}else if(method.equals("apply_out_4_review")){
			apply_out_4_review(request,response);
		}
	else if(method.equals("apply_out_4_add")){
		apply_out_4_add(request,response);
	}else if(method.equals("apply_out_4_del")){
		apply_out_4_del(request,response);
	}else if(method.equals("apply_out_4_save")){
		try {
			apply_out_4_save(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else if(method.equals("apply_out_4_detail")){
			apply_out_4_detail(request,response);
	}else if(method.equals("apply_out_4_pro_add")){
		apply_out_4_pro_add(request,response);
	}else if(method.equals("apply_out_4_pro_del")){
		apply_out_4_pro_del(request,response);
	}else if(method.equals("apply_out_4_pro_cfm")){
			apply_out_4_pro_cfm(request,response);
		}else if(method.equals("apply_out_4_detail_review")){
			apply_out_4_detail_review(request,response);
		}else if(method.equals("apply_out_4_review_yes")){
			try {
				apply_out_4_review_yes(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("apply_out_4_review_no")){
			try {
				apply_out_4_review_no(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("apply_out_product_detail")){
			apply_out_product_detail(request,response);
	}else if(method.equals("exportExcel_apply_out_product_detail")){
		exportExcel_apply_out_product_detail(request,response);
		
	}else if(method.equals("settle_stock_start")){
			try {
				settle_stock_start(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("settle_stock_save")){
			try {
				settle_stock_save(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("settle_stock_cfm")){
			try {
				settle_stock_cfm(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("settle_stock")){
			settle_stock(request,response);
		}else if(method.equals("settle_stock_reset")){
			settle_stock_reset(request,response);
		}else if(method.equals("settle_stock_list")){
			settle_stock_list(request,response);
		}else if(method.equals("settle_stock_list_detail")){
			settle_stock_list_detail(request,response);
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
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
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
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
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
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
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
	public void apply_in(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String docNum = StringUtil.notNull(request.getParameter("docNum"));
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
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='1' and type!='4' and doc_num like'%"+docNum+"%' and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='1' and type!='4' and doc_num like'%"+docNum+"%' and type like'%"+type+"%' and state like'%"+state+"%' order by id desc";
				}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
				request.setAttribute("docNum", docNum);
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
	public void apply_in_all(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String docNum = StringUtil.notNull(request.getParameter("docNum"));
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
				if(rankstr[3][3].equals("1")||rankstr[3][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='1' and type like'%"+type+"%'  and doc_num like'%"+docNum+"%'  and type!='4' and   entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='1' and type like'%"+type+"%' and doc_num like'%"+docNum+"%' and state like'%"+state+"%' and type!='4' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("type", type);
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
	public void apply_in_check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][6].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='1' and type!='4' and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and state ='2'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='1' and type!='4' and type like'%"+type+"%' and state ='2' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_check.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_check.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='1' and type!='4' and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  (state ='3' or state='6')  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='1' and type!='4' and type like'%"+type+"%' and (state ='3' or state='6')  order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_cfm.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_cfm.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String docNum = StringUtil.notNull(request.getParameter("docNum"));
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
				if(rankstr[3][8].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where doc_num like'%"+docNum+"' and type!='4' and pay_type='1' and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state ='4'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where doc_num like'%"+docNum+"' and type!='4' and  pay_type='1' and type like'%"+type+"%' and state ='4' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_approve(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String docNum = StringUtil.notNull(request.getParameter("docNum"));
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
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where doc_num like'%"+docNum+"' and pay_type='1' and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state ='2'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where doc_num like'%"+docNum+"' and  pay_type='1' and type like'%"+type+"%' and state ='2' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_approve.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_approve.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message = "";
		try{
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
		
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
	String message = "";
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
					String supplier = StringUtil.notNull(request.getParameter("supplier"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String docNum = StringUtil.notNull(request.getParameter("docNum"));
				
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
						String sql1= "insert into inventoryApply(applyId,operator_id,supplier_id,supplier_code,supplier_name,type,doc_num,max_num,pay_type,state,entry_time) values('"+applyId+"','"+
						admin.getAdminName()+"','"+id+"','"+code+"','"+name+"','"+type+"','"+docNum+"','6','1','1',+'"+date+"')";
						 stmt = conn.createStatement();
						 stmt.executeUpdate(sql1);
						message= "入库申请单已生成，申请编号为："+applyId+"，请进入详情添加入库商品。";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
			dispatcher.forward(request, response);
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
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message ="";
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		List  result = new ArrayList();
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 60;
			
				String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' and iay.state>'3' and iay.pay_type='1' order by iay.confirm_time asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail ap = new InventoryDetail();
					ap.setId(rs.getInt("idl.id"));
					ap.setApplyId(rs.getString("idl.applyId"));
					ap.setSupplierId(rs.getInt("iay.supplier_id"));
					ap.setSupplierCode(rs.getString("iay.supplier_code"));
					ap.setSupplierName(rs.getString("iay.supplier_name"));
					ap.setDocNum(rs.getString("iay.doc_num"));
					ap.setRemark(rs.getString("iay.remark"));
					ap.setType(rs.getInt("iay.type"));
					ap.setEntryTime(rs.getTimestamp("iay.confirm_time"));
					ap.setProductId(rs.getString("idl.productId"));
					ap.setProductName(rs.getString("idl.productName"));
					ap.setSpecification(rs.getString("idl.specification"));
					ap.setUnit(rs.getString("idl.unit"));
					ap.setPrice(rs.getDouble("idl.price"));
					ap.setRemark(rs.getString("idl.remark"));
					ap.setNum(rs.getDouble("idl.num"));
					ap.setTotalPrice(ArithUtil.mul(rs.getDouble("idl.num"),rs.getDouble("idl.price")));
					
					result.add(ap);
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
					message= "日期格式有误。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_product_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void exportExcel_apply_in_product_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "入库明细表("+startTimeStr+"至"+endTimeStr+").xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<InventoryDetail>  result = new ArrayList<InventoryDetail> ();
	
		try {
			if(db.createConn()){
				conn = db.getConnection();
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 60;
			
				String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' and iay.state>'3' and iay.pay_type='1' order by iay.confirm_time asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail ap = new InventoryDetail();
					ap.setId(rs.getInt("idl.id"));
					ap.setApplyId(rs.getString("idl.applyId"));
					ap.setSupplierId(rs.getInt("iay.supplier_id"));
					ap.setSupplierCode(rs.getString("iay.supplier_code"));
					ap.setSupplierName(rs.getString("iay.supplier_name"));
					ap.setDocNum(rs.getString("iay.doc_num"));
					ap.setRemark(rs.getString("iay.remark"));
					ap.setType(rs.getInt("iay.type"));
					ap.setEntryTime(rs.getTimestamp("iay.confirm_time"));
					ap.setProductId(rs.getString("idl.productId"));
					ap.setProductName(rs.getString("idl.productName"));
					ap.setSpecification(rs.getString("idl.specification"));
					ap.setUnit(rs.getString("idl.unit"));
					ap.setPrice(rs.getDouble("idl.price"));
					ap.setRemark(rs.getString("idl.remark"));
					ap.setNum(rs.getDouble("idl.num"));
					ap.setTotalPrice(ArithUtil.mul(rs.getDouble("idl.num"),rs.getDouble("idl.price")));
					
					result.add(ap);
				}
				String[][] content = new String[result.size()+2][12];
				content[0][0]="入库日期";
				content[0][1]="入库单号";
				content[0][2]="供应商名称";
				content[0][3]="商品编号";
				content[0][4]="商品名称";
				content[0][5]="规格";
				content[0][6]="单位";
				content[0][7]="数量";
				content[0][8]="单价";
				content[0][9]="金额";
				content[0][10]="入库属性";
				content[0][11]="备注";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getApplyId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getSupplierName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][5]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getUnit());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getNum());
					content[i+1][8]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+1][9]  = StringUtil.decimalFormat(result.get(i).getTotalPrice());
					String type ="";
					if(result.get(i).getType()==1)type="采购入库";
					else if(result.get(i).getType()==2)type="加工入库";
					else if(result.get(i).getType()==3)type="退货入库";
					else if(result.get(i).getType()==4)type="盘盈入库";
					content[i+1][10] =type;
					content[i+1][11] = StringUtil.notNull(result.get(i).getRemark());
				}
				
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("入库明细（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message ="";
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		List  result = new ArrayList();
		try {
			if(admin!=null){
			if(db.createConn()){
				conn = db.getConnection();
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 60;
			
				String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' and iay.state>'3' and iay.pay_type='2' order by iay.confirm_time asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail ap = new InventoryDetail();
					ap.setId(rs.getInt("idl.id"));
					ap.setApplyId(rs.getString("idl.applyId"));
					ap.setSupplierId(rs.getInt("iay.supplier_id"));
					ap.setSupplierCode(rs.getString("iay.supplier_code"));
					ap.setSupplierName(rs.getString("iay.supplier_name"));
					ap.setDocNum(rs.getString("iay.doc_num"));
					ap.setRemark(rs.getString("iay.remark"));
					ap.setType(rs.getInt("iay.type"));
					ap.setEntryTime(rs.getTimestamp("iay.confirm_time"));
					ap.setProductId(rs.getString("idl.productId"));
					ap.setProductName(rs.getString("idl.productName"));
					ap.setSpecification(rs.getString("idl.specification"));
					ap.setUnit(rs.getString("idl.unit"));
					ap.setPrice(rs.getDouble("idl.price"));
					ap.setRemark(rs.getString("idl.remark"));
					ap.setNum(rs.getDouble("idl.num"));
					ap.setTotalPrice(ArithUtil.mul(rs.getDouble("idl.num"),rs.getDouble("idl.price")));
					
					result.add(ap);
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
					message= "日期格式有误。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_product_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	protected void exportExcel_apply_out_product_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String filename = "出库明细表("+startTimeStr+"至"+endTimeStr+").xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<InventoryDetail>  result = new ArrayList<InventoryDetail> ();
	
		try {
			if(db.createConn()){
				conn = db.getConnection();
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				
			
				String sql ="select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.confirm_time>='"+startTime+"' and iay.confirm_time<='"+endTime+"' and iay.state>'3' and iay.pay_type='2' order by iay.confirm_time asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					InventoryDetail ap = new InventoryDetail();
					ap.setId(rs.getInt("idl.id"));
					ap.setApplyId(rs.getString("idl.applyId"));
					ap.setSupplierId(rs.getInt("iay.supplier_id"));
					ap.setSupplierCode(rs.getString("iay.supplier_code"));
					ap.setSupplierName(rs.getString("iay.supplier_name"));
					ap.setDocNum(rs.getString("iay.doc_num"));
					ap.setRemark(rs.getString("iay.remark"));
					ap.setType(rs.getInt("iay.type"));
					ap.setEntryTime(rs.getTimestamp("iay.confirm_time"));
					ap.setProductId(rs.getString("idl.productId"));
					ap.setProductName(rs.getString("idl.productName"));
					ap.setSpecification(rs.getString("idl.specification"));
					ap.setUnit(rs.getString("idl.unit"));
					ap.setPrice(rs.getDouble("idl.price"));
					ap.setRemark(rs.getString("idl.remark"));
					ap.setNum(rs.getDouble("idl.num"));
					ap.setTotalPrice(ArithUtil.mul(rs.getDouble("idl.num"),rs.getDouble("idl.price")));
					
					result.add(ap);
				}
				String[][] content = new String[result.size()+2][12];
				content[0][0]="出库日期";
				content[0][1]="出库单号";
				content[0][2]="供应商名称";
				content[0][3]="商品编号";
				content[0][4]="商品名称";
				content[0][5]="规格";
				content[0][6]="单位";
				content[0][7]="数量";
				content[0][8]="单价";
				content[0][9]="金额";
				content[0][10]="入库属性";
				content[0][11]="备注";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getApplyId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getSupplierName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][5]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getUnit());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getNum());
					content[i+1][8]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+1][9]  = StringUtil.decimalFormat(result.get(i).getTotalPrice());
					String type ="";
					if(result.get(i).getType()==1)	type="加工出库";
					else if(result.get(i).getType()==2)type="盘亏出库";
					else if(result.get(i).getType()==3)type="损耗出库";
					else if(result.get(i).getType()==4)type="外销出库";
					else if(result.get(i).getType()==5)type="体验出库";
					else if(result.get(i).getType()==6)type="赠送出库";
					else if(result.get(i).getType()==7)type="研发出库";
					else if(result.get(i).getType()==8)type="退货出库";
					content[i+1][10] =type;
					content[i+1][11] = StringUtil.notNull(result.get(i).getRemark());
				}
				
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("出库明细（"+startTimeStr+"到"+endTimeStr+"）"  );  
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_detail_approve(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail_approve.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_approve.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_detail_all(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][3].equals("1")||rankstr[3][3].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail_all.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_all.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_detail_check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][6].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail_check.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_check.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_detail_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail_cfm.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_cfm.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_detail_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][8].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_pro_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
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
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setType(rs.getInt("type"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					boolean  b = true;
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));

						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						if(inventory.getProductId().equals(productId)) b =false;
						coll.add(inventory);
					}
					if(b){
						if(ap.getMaxNum()>0){
							String sql2 ="select * from product where productId='"+productId+"' order by id asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								int pid = rs.getInt("id");
								String productName= rs.getString("productName");
								String specification = rs.getString("specification");
								String unit = rs.getString("unit");
								String sql3 = "insert into inventoryDetail(applyId,pid,productId,productName,specification,unit,num,price,remark,entry_time)"
										+ "values('"+applyId+"','"+pid+"','"+productId+"','"+productName+"','"+specification+"','"+unit+"','"
										+num+"','"+price+"','"+remark+"','"+date+"')";
								stmt= conn.createStatement();
								stmt.executeUpdate( sql3, Statement.RETURN_GENERATED_KEYS);
								rs = stmt
										.getGeneratedKeys();
								if (rs.next()) {
									int new_id = rs.getInt(1);
								double num1 = Double.valueOf(num);
								double price1 = Double.valueOf(price);
								InventoryDetail inventory = new InventoryDetail();
								inventory.setId(new_id);
								inventory.setApplyId(productName);
								inventory.setProductId(productId);
								inventory.setProductName(productName);
								inventory.setSpecification(specification);
								inventory.setUnit(unit);
								inventory.setPrice(price1);
								inventory.setNum(num1);
								inventory.setRemark(remark);
								inventory.setTotalPrice(ArithUtil.mul(num1,price1));
								total_ap.setNum(ArithUtil.add(total_ap.getNum(),num1));
								total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),price1));
								total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
								coll.add(inventory);
								ap.setMaxNum(ap.getMaxNum()-1);
								String sql4 = "update inventoryApply set max_num='"+ap.getMaxNum()+"' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
								message="产品信息添加成功！";
								}else{ 
									message="产品详情保存失败！";
								}
							}else{ 
								message="产品信息获取不通过！";
							}
						}else{ 
							message="申请单信息已满，如有需要请新增申请单！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_pro_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setType(rs.getInt("type"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));

					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));
						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						coll.add(inventory);
					}
						if(ap.getState()==1){
							
								String sql4 = "update inventoryApply set state='2' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
							
								message="入库单提交审批流程成功！";
								ap.setState(2);
							
						}else{ 
							message="申请单状态不能进行此操作！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail.jsp");
				dispatcher.forward(request, response);
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
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
					String sqlu ="update inventoryApply set state='0' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "入库申请删除成功，删除的编号为："+applyId+"。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_pro_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String id = StringUtil.notNull(request.getParameter("id"));
		Collection coll = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String applyId=rs.getString("applyId");
					String code = rs.getString("productId");
					InventoryDetail total_ap = new InventoryDetail();
					total_ap.setNum(0);
					total_ap.setPrice(0);
					total_ap.setTotalPrice(0);
					String sql1 ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					int max_num = rs.getInt("max_num");
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sqlu ="delete from inventoryDetail  where id='"+id+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					sqlu ="update inventoryApply set max_num='"+(max_num+1)+"' where applyId='"+applyId+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					 Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), "入库单"+applyId+"中的"+code+"删除成功。", date);
				
					ap.setMaxNum(max_num+1);
					request.setAttribute("admin_apply", ap);
					String sql3 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql3);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库单获取不通过。";
				}
				}else {
					message= "入库信息获取不通过。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_approve_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='3',approve_id='"+admin.getAdminName()+"',approve_time='"+date+"' where applyId='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "采购申请审批成功，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_approve.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_approve_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					InventoryApply ia = new InventoryApply();
					ia.setId(rs.getInt("id"));
					ia.setApplyId(rs.getString("applyId"));
					ia.setDocNum(rs.getString("doc_num"));
					request.setAttribute("sys_ivy", ia);
				}else  message= "未找到需要审核的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_approve_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_approve.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void apply_in_approve_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				String remark = StringUtil.notNull(request.getParameter("remark"));

					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					remark = StringUtil.notNull(rs.getString("remark"))+"采购审批备注："+remark;
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='1',remark='"+StringUtil.notNull(remark)+"',approve_id='"+admin.getAdminName()+"',approve_time='"+date+"' where applyId='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "采购申请审批不成功，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_approve.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_review_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][8].equals("1")||admin.getState().equals("1")){
					
			if(db.createConn()){
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					InventoryApply ia = new InventoryApply();
					ia.setId(rs.getInt("id"));
					ia.setApplyId(rs.getString("applyId"));
					ia.setDocNum(rs.getString("doc_num"));
					request.setAttribute("sys_ivy", ia);
				}else  message= "未找到需要审核的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String remark = StringUtil.notNull(request.getParameter("remark"));

		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		String message ="";
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){

				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][8].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					remark = StringUtil.notNull(rs.getString("remark"))+"财务审批备注："+remark;

					if(state==4){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='6',remark='"+remark+"',review_id='"+admin.getAdminName()+"',review_time='"+date+"' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "入库申请审核不通过，编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_check_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][6].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='3',check_id='"+admin.getAdminName()+"',check_time='"+date+"' where applyId='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "入库申请质检验收成功，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_check.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_cfm_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
					
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					InventoryDetail ia = new InventoryDetail();
					ia.setId(rs.getInt("id"));
					ia.setApplyId(rs.getString("applyId"));
					ia.setPrice(rs.getDouble("price"));
					ia.setProductId(rs.getString("productId"));
					request.setAttribute("sys_ivd", ia);
				}else  message= "未找到需要审核的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_cfm_edit.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_cfm_edit.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_cfm_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				String price = StringUtil.notNull(request.getParameter("price"));

					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					
					String applyId = rs.getString("applyId");
					String productId =rs.getString("productId");
					Timestamp date = new Timestamp(new Date().getTime());
						 String sqlu ="update inventoryDetail set price='"+price+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "入库申请价格修改成功，编号为："+applyId+",产品编号为:"+productId;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				request.setAttribute("sys_apply_id", applyId);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_cfm_edit.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_check_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][6].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int type = rs.getInt("type");
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					
						 String sqlu ="update inventoryApply set state='1',check_id='"+admin.getAdminName()+"',check_time='"+date+"' where applyId='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "入库申请质检验收不通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_check.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_cfm_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				List<String> slist = new ArrayList<String>();
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					 Timestamp date = new Timestamp(new Date().getTime());
					if(state==3){
					String sqlu ="update inventoryApply set state='4',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					slist.add(sqlu);
					String sql1="select * from inventoryDetail where applyId='"+applyId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						double pnum = rs.getDouble("num");
						int pid = rs.getInt("pid");
						
						String sqlp="select * from product where id='"+pid+"' for update";
						Statement stmt1 = null;
						ResultSet rs1 = null;
						stmt1 = conn.createStatement();
						rs1 = stmt1.executeQuery(sqlp);
						if(rs1.next()){
							double num = rs1.getDouble("num");
							double total = rs1.getDouble("totalNum");
							String sqlpu = "update product set num='"+ArithUtil.add(num,pnum)+"',totalNum='"+(total+pnum)+"' where id='"+pid+"'";
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
					message= "入库申请仓管确认，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}
					}else if(state==6){
						String sqlu ="update inventoryApply set state='4',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "入库申请仓管重新提交，入库编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}
					else message= "当前状态不能进行审核操作，请确认！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_cfm.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_cfm_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][7].equals("1")||admin.getState().equals("1")){
				
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==3){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='2',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					stmt=conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "入库申请仓管确认不通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的入库信息，请重试";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_cfm.jsp");
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
				if(rankstr[3][8].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==4){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='5',review_id='"+admin.getAdminName()+"',review_time='"+date+"' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.executeUpdate(sqlu);
					message= "入库申请审核通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message_review.jsp");
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
						inventory.setProductType(rs.getString("productType"));
						inventory.setSpecification(rs.getString("specification"));
						//inventory.setNum(rs.getDouble("num"));
						//inventory.setTotalNum(rs.getDouble("totalNum"));
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
			message=  "数据有误，请重新登陆。";
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void two_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "";
						if(inventoryName==null) sql ="select * from inventory_two where state!='0' order by id asc";
						else if(inventoryName.equals("")) sql ="select * from inventory_two where state!='0' order by id asc";
						else{
							sql = "select * from inventory_two where name like '%"+inventoryName+"%' and state!='0' order by id asc";
							request.setAttribute("inventoryName", inventoryName);
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("name"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setEndTime(rs.getTimestamp("end_time"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_two.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void two_isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String inventoryName = request.getParameter("inventoryName");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(inventoryName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from inventory_two where name ='"+inventoryName+"' order by id asc";
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
	
	public void two_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_two_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		}
	
	public void two_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String inventoryName = request.getParameter("inventoryName");
				if(!StringUtil.notNull(inventoryName).equals("")){
					String sql = "select * from inventory_two where name ='"+inventoryName+"'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					db.close();
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String sqli = "insert into inventory_two(name,state,entry_time,end_time) values('"+inventoryName
					+"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 conn.setAutoCommit(autoCommit);
					 request.setAttribute("message", "逻辑仓库信息保存成功，请核对仓库名："+inventoryName+"。");
				cs.insetAdminLog(conn, admin.getAdminName(), "逻辑仓库信息保存成功，请核对仓库名："+inventoryName+"。", date);
				}else{
					request.setAttribute("message", "仓库名已被注册，请重新录入！");
				}
				}else {
					request.setAttribute("message", "仓库名不能为空，请重新录入！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_two_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void two_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				String sql = "select * from inventory_two where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("inventoryName");
					db.close();
					String sqli = "update inventory_two set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="逻辑仓库删除成功，删除仓库为"+name+"！";
					Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_two_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void three_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "";
						if(inventoryName==null) sql ="select * from inventory_three where state!='0' order by id asc";
						else if(inventoryName.equals("")) sql ="select * from inventory_three where state!='0' order by id asc";
						else{
							sql = "select * from inventory_three where name like '%"+inventoryName+"%' and state!='0' order by id asc";
							request.setAttribute("inventoryName", inventoryName);
						}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Inventory inventory = new Inventory();
					inventory.setId(rs.getInt("id"));
					inventory.setInventoryName(rs.getString("name"));
					inventory.setState(rs.getString("state"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setEndTime(rs.getTimestamp("end_time"));
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_three.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void three_isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String inventoryName = request.getParameter("inventoryName");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(inventoryName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from inventory_three where name ='"+inventoryName+"' order by id asc";
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
	
	public void three_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_three_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		}
	
	public void three_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String inventoryName = request.getParameter("inventoryName");
				if(!StringUtil.notNull(inventoryName).equals("")){
					String sql = "select * from inventory_three where name ='"+inventoryName+"'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					db.close();
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String sqli = "insert into inventory_three(name,state,entry_time,end_time) values('"+inventoryName
					+"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 conn.setAutoCommit(autoCommit);
					 request.setAttribute("message", "物理仓库信息保存成功，请核对仓库名："+inventoryName+"。");
				cs.insetAdminLog(conn, admin.getAdminName(), "物理仓库信息保存成功，请核对仓库名："+inventoryName+"。", date);
				}else{
					request.setAttribute("message", "仓库名已被注册，请重新录入！");
				}
				}else {
					request.setAttribute("message", "仓库名不能为空，请重新录入！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_three_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void three_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				String sql = "select * from inventory_three where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("inventoryName");
					db.close();
					String sqli = "update inventory_three set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="物理仓库删除成功，删除仓库为"+name+"！";
					Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_three_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2' and type like'%"+type+"%' and state like'%"+state+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_all(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][9].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and state like'%"+state+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_all.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_all.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][11].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and state ='2'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and state ='2' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_check.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_check.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  (state ='3' or state='7')  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='2' and type!='2'  and type like'%"+type+"%' and (state ='3' or state='7')  order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));

					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
					inventory.setState(rs.getInt("state"));
					inventory.setRemark(rs.getString("remark"));

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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_cfm.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_cfm.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_1 = new ArrayList();
		List  result = new ArrayList();
		String type = StringUtil.notNull(request.getParameter("type"));
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
				if(rankstr[3][13].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where pay_type='2' and type!='2'  and type like'%"+type+"%' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state ='4'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where   pay_type='2' and type!='2'  and type like'%"+type+"%' and state ='4' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("type", type);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_out_review_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][13].equals("1")||admin.getState().equals("1")){
					
			if(db.createConn()){
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					InventoryApply ia = new InventoryApply();
					ia.setId(rs.getInt("id"));
					ia.setApplyId(rs.getString("applyId"));
					ia.setDocNum(rs.getString("doc_num"));
					request.setAttribute("sys_ivy", ia);
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_review_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message = "";
		try{
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
		
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_out_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String supplier = StringUtil.notNull(request.getParameter("supplier"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String docNum = StringUtil.notNull(request.getParameter("docNum"));
				
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
						String sql1= "insert into inventoryApply(applyId,operator_id,supplier_id,supplier_code,supplier_name,type,doc_num,max_num,pay_type,state,entry_time) values('"+applyId+"','"+
						admin.getAdminName()+"','"+id+"','"+code+"','"+name+"','"+type+"','"+docNum+"','6','2','1',+'"+date+"')";
						 stmt = conn.createStatement();
						 stmt.executeUpdate(sql1);
						message= "出库申请单已生成，申请编号为："+applyId+"，请进入详情添加出库商品。";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					ap.setRemark(rs.getString("remark"));

					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "出库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_out_pro_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String id = StringUtil.notNull(request.getParameter("id"));
		Collection coll = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String applyId=rs.getString("applyId");
					String code = rs.getString("productId");
					
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql1 ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					int max_num = rs.getInt("max_num");
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sqlu ="delete from inventoryDetail  where id='"+id+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					sqlu ="update inventoryApply set max_num='"+(max_num+1)+"' where applyId='"+applyId+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), "出库单"+applyId+"中的"+code+"删除成功。", date);
					ap.setMaxNum(max_num+1);
					request.setAttribute("admin_apply", ap);
					String sql2 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql2);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库单获取不通过。";
				}
				}else {
					message= "入库信息获取不通过。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_detail_all(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][9].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "出库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail_all.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_all.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_detail_check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][11].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail_check.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_check.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_detail_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "出库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail_cfm.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_cfm.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_detail_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][13].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_pro_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
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
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setType(rs.getInt("type"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					boolean  b = true;
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));

						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						if(inventory.getProductId().equals(productId)) b =false;
						coll.add(inventory);
					}
					if(b){
						if(ap.getMaxNum()>0){
							String sql2 ="select * from product where productId='"+productId+"' order by id asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								int pid = rs.getInt("id");
								String productName= rs.getString("productName");
								String specification = rs.getString("specification");
								String unit = rs.getString("unit");
								String sql3 = "insert into inventoryDetail(applyId,pid,productId,productName,specification,unit,num,price,remark,entry_time)"
										+ "values('"+applyId+"','"+pid+"','"+productId+"','"+productName+"','"+specification+"','"+unit+"','"
										+num+"','"+price+"','"+remark+"','"+date+"')";
								stmt= conn.createStatement();
								stmt.executeUpdate( sql3, Statement.RETURN_GENERATED_KEYS);
								rs = stmt
										.getGeneratedKeys();
								if (rs.next()) {
									int new_id = rs.getInt(1);
								double num1 = Double.valueOf(num);
								double price1 = Double.valueOf(price);
								InventoryDetail inventory = new InventoryDetail();
								inventory.setId(new_id);
								inventory.setApplyId(productName);
								inventory.setProductId(productId);
								inventory.setProductName(productName);
								inventory.setSpecification(specification);
								inventory.setUnit(unit);
								inventory.setPrice(price1);
								inventory.setNum(num1);
								inventory.setRemark(remark);
								inventory.setTotalPrice(ArithUtil.mul(num1,price1));
								total_ap.setNum(ArithUtil.add(total_ap.getNum(),num1));
								total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),price1));
								total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
								coll.add(inventory);
								ap.setMaxNum(ap.getMaxNum()-1);
								String sql4 = "update inventoryApply set max_num='"+ap.getMaxNum()+"' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
								message="产品信息添加成功！";
								}else{ 
									message="订单详情信息保存失败！";
								}
							}else{ 
								message="产品信息获取不通过！";
							}
						}else{ 
							message="申请单信息已满，如有需要请新增申请单！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_pro_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setType(rs.getInt("type"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));
						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(),rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						coll.add(inventory);
					}
						if(ap.getState()==1){
							
								String sql4 = "update inventoryApply set state='2' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
								message="出库单提交审批流程成功！";
								ap.setState(2);
							
						}else{ 
							message="申请单状态不能进行此操作！";
						}
			
				request.setAttribute("admin_apply", ap);
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				
				}else {
					message= "出库申请单信息获取不通过！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
					String sqlu ="update inventoryApply set state='0' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "出库申请删除成功，删除的编号为："+applyId+"。";
					 Timestamp date = new Timestamp(new Date().getTime());
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行删除操作，请确认！";
				}else  message= "未找到需要删除的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String remark = StringUtil.notNull(request.getParameter("remark"));
		String message ="";
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][13].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==4){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String str = "审核备注："+rs.getString("remark")+"；"+remark;
					String sqlu ="update inventoryApply set state='7',remark='"+str+"',review_id='"+admin.getAdminName()+"',review_time='"+date+"' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "出库申请审核不通过，编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_review_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_check_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][11].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='3',check_id='"+admin.getAdminName()+"',check_time='"+date+"' where applyId='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "出库申请质检验收成功，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_check.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_check_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][11].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='1',check_id='"+admin.getAdminName()+"',check_time='"+date+"' where applyId='"+applyId+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					
					message= "出库申请质检验收不通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_check.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_cfm_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				List<String> slist = new ArrayList<String>();
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					Timestamp date = new Timestamp(new Date().getTime());
					if(state==7){
						String sqlu ="update inventoryApply set state='4',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqlu);
						message= "出库申请仓管重新提交，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else if(state==3){
						 
					String sqlu ="update inventoryApply set state='4',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					slist.add(sqlu);
					String sql1="select * from inventoryDetail where applyId='"+applyId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						double pnum = rs.getDouble("num");
						int pid = rs.getInt("pid");
						String sqlp="select * from product where id='"+pid+"' for update";
						Statement stmt1 = null;
						ResultSet rs1 = null;
						stmt1 = conn.createStatement();
						rs1 = stmt1.executeQuery(sqlp);
						if(rs1.next()){
							double num = rs1.getDouble("num");
							double total = rs1.getDouble("totalNum");
							String sqlpu = "update product set num='"+ArithUtil.sub(num,pnum)+"' where id='"+pid+"'";
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
					message= "出库申请仓管确认，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_cfm.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_cfm_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
				
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==3){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='2',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					stmt=conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "出库申请仓管确认不通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_cfm.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][13].equals("1")||admin.getState().equals("1")){
					
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					Timestamp date = new Timestamp(new Date().getTime());
					if(state==4){
						
					String sqlu ="update inventoryApply set state='5',review_id='"+admin.getAdminName()+"',review_time='"+date+"' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					
					message= "出库申请审核通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_review.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_cfm_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
					
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					InventoryDetail ia = new InventoryDetail();
					ia.setId(rs.getInt("id"));
					ia.setApplyId(rs.getString("applyId"));
					ia.setPrice(rs.getDouble("price"));
					ia.setProductId(rs.getString("productId"));
					request.setAttribute("sys_ivd", ia);
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_cfm_edit.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_cfm_edit.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_out_cfm_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				String price = StringUtil.notNull(request.getParameter("price"));
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String applyId = rs.getString("applyId");
					String productId =rs.getString("productId");
					Timestamp date = new Timestamp(new Date().getTime());
						 String sqlu ="update inventoryDetail set price='"+price+"' where id='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "出库申请价格修改成功，编号为："+applyId+",产品编号为:"+productId;
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				request.setAttribute("sys_apply_id", applyId);
				}else  message= "未找到需要审核的出库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message_cfm_edit.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void settle_stock(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		Collection coll = new ArrayList();
		int tag=0;
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
				String sql ="select * from settle_stock  order by week_tag desc";
				stmt=conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int weekTag = rs.getInt("week_tag");
					int state =rs.getInt("state");
					Timestamp startTime = rs.getTimestamp("start_time");
					Timestamp endTime = rs.getTimestamp("end_time");
					if(weekTag==0){
						request.setAttribute("startTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("state", 0);
						request.setAttribute("weekTag", String.valueOf(weekTag+1));
						tag=1;
					}else{	
						if(state==2){
							request.setAttribute("startTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
							request.setAttribute("state", state);
							request.setAttribute("weekTag", String.valueOf(weekTag+1));
							tag=1;
						}else{
							request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss));
							request.setAttribute("endTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
							request.setAttribute("state", state);
							request.setAttribute("weekTag", String.valueOf(weekTag));
							tag=2;
							String sql1 ="select * from inventory_product_stock where week_tag='"+weekTag+"' order by inventory_three asc";
							stmt=conn.createStatement();
							rs = stmt.executeQuery(sql1);
							while(rs.next()){
								InventoryProductStock ps = new InventoryProductStock();
								ps.setId(rs.getInt("id"));
								ps.setPid(rs.getInt("p_id"));
								ps.setProductId(rs.getString("product_id"));
								ps.setProductName(rs.getString("product_name"));
								ps.setSpecification(rs.getString("specification"));
								ps.setStartNum(rs.getDouble("start_num"));
								ps.setCurNum(rs.getDouble("cur_num"));
								ps.setSysNum(rs.getDouble("sys_num"));
								ps.setUnit(rs.getString("unit"));
								ps.setInventoryOne(rs.getString("inventory_one"));
								ps.setInventoryTwo(rs.getString("inventory_two"));
								ps.setInventoryThree(rs.getString("inventory_three"));
								ps.setPrice(rs.getDouble("price"));
								ps.setInNum(rs.getDouble("in_num"));
								ps.setOutNum(rs.getDouble("out_num"));
								ps.setWeekTag(rs.getInt("week_tag"));
								ps.setStartTime(rs.getTimestamp("start_time"));
								ps.setEndTime(rs.getTimestamp("end_time"));
								ps.setEntryTime(rs.getTimestamp("entry_time"));
								coll.add(ps);
							}
							request.setAttribute("coll", coll);
						}
					}
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
			if(tag>0){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void settle_stock_start(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		Timestamp date = new Timestamp(new Date().getTime());

		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String startTimeStr = (String) request.getParameter("startTime");
				String weekTagStr = (String) request.getParameter("weekTag");
				if(!StringUtil.notNull(weekTagStr).equals("")){
					if(!(StringUtil.notNull(startTimeStr).equals(""))){
					
				int weekTag = Integer.valueOf(weekTagStr);
				SettleStock st = cs.getSettleForWeekTag(conn, weekTag, "for update");
				if(st==null){
					String sql ="select max(id) from product";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					InventoryProductStock[] plist = new InventoryProductStock[num1+1];
 					String sqlp ="select * from product";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sqlp);
					while(rs.next()){
						int id =rs.getInt("id");
					if(plist[id]==null) plist[id] = new InventoryProductStock();
					plist[id].setPid(rs.getInt("id"));
					plist[id].setProductName(rs.getString("productName"));
					plist[id].setProductId(rs.getString("productId"));
					plist[id].setSpecification(rs.getString("specification"));
					plist[id].setPrice(rs.getDouble("price"));
					plist[id].setUnit(rs.getString("unit"));
					plist[id].setInventoryOne(rs.getString("inventory_one"));
					plist[id].setInventoryTwo(rs.getString("inventory_two"));
					plist[id].setInventoryThree(rs.getString("inventory_three"));
					plist[id].setCurNum(rs.getDouble("num"));
					plist[id].setSysNum(rs.getDouble("num"));
					plist[id].setStartNum(0);
					plist[id].setInNum(0);
					plist[id].setOutNum(0);
					plist[id].setWeekTag(weekTag);
					}
					int weekTag1 = weekTag-1;
					if(weekTag1>0){
						String sql1 = "select * from inventory_product_stock where week_tag='"+weekTag1+"'";
						stmt =conn.createStatement();
						rs = stmt.executeQuery(sql1);
						while(rs.next()){
							int pid = rs.getInt("p_id");
							if(plist[pid]!=null) plist[pid].setStartNum(rs.getDouble("sys_num"));
						}
					}
					String sql1 = "select * from orders as os,orderDetail as od where os.orderId=od.orderId and os.orderTime>='"+startTimeStr+" and os.orderTime<"+date+"' and os.state>'0'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql1);
					while(rs.next()){
						int pid = rs.getInt("od.pid");
						if(plist[pid]!=null)
							plist[pid].setOutNum(ArithUtil.add(plist[pid].getOutNum(),rs.getDouble("od.num")));
					}
					sql1 = "select * from inventoryApply as iay,inventoryDetail as idl where iay.applyId=idl.applyId and iay.state>='4' and iay.confirm_time>='"+startTimeStr+"' and iay.confirm_time<='"+date+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql1);
					while(rs.next()){
						int pid = rs.getInt("idl.pid");
						int payType = rs.getInt("iay.pay_type");
						if(plist[pid]!=null){
							if(payType==1){
								plist[pid].setInNum(ArithUtil.add(plist[pid].getInNum(),rs.getDouble("idl.num")));

							}else
								plist[pid].setOutNum(ArithUtil.add(plist[pid].getOutNum(),rs.getDouble("idl.num")));
						
							}
					}
			List<String> slist= new ArrayList<String>();
			String sql2 = "insert into settle_stock(week_tag,state,start_time,end_time,entry_time) values('"
				+weekTag+"','1','"+startTimeStr+"','"+date+"','"+date+"');";
			slist.add(sql2);
			for(int i =0;i<plist.length;i++){
				if(plist[i]!=null){
					sql2 = "insert into inventory_product_stock(week_tag,p_id,product_id,product_name,specification,unit,inventory_one,inventory_two,inventory_three,price,sys_num,start_num,cur_num,in_num,out_num,state,start_time,end_time,entry_time) values('"
							+weekTag+"','"+plist[i].getPid()+"','"+plist[i].getProductId()+"','"+plist[i].getProductName()+"','"+plist[i].getSpecification()+"','"+plist[i].getUnit()+"','"+plist[i].getInventoryOne()+"','"+plist[i].getInventoryTwo()+"','"+plist[i].getInventoryThree()+"','"+plist[i].getPrice()
							+"','"+plist[i].getSysNum()+"','"+plist[i].getStartNum()+"','"+plist[i].getCurNum()+"','"+plist[i].getInNum()+"','"+plist[i].getOutNum()+"','1','"+startTimeStr+"','"+date+"','"+date+"');";
					slist.add(sql2);
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
			message= "盘点信息保存成功，盘点时间为"+startTimeStr+"到"+StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss)+"。";
				conn.commit();
				conn.setAutoCommit(autoCommit);
				}else {
					message= "当前盘点信息获取失败。";
				}
				}else {
					message= "日期格式有误！";
				}
					}else {
						message= "盘点周期获取失败！";
					}
			}else {
				message= "数据库连接已断开！";
			}
				}else {
					message= "请勿重复提交数据。";
				}
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_message.jsp");
				dispatcher.forward(request, response);
		
		}
	}
	
	
	
	public void settle_stock_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		List<String> slist= new ArrayList<String>();
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
						String weekTag =(String) request.getParameter("weekTag");
				String sql ="select * from settle_stock where week_tag='"+weekTag+"' for update";
				stmt=conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					sql ="select * from inventory_product_stock where week_tag='"+weekTag+"' for update";
					stmt=conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						int id =rs.getInt("id");
						int pid = rs.getInt("p_id");
						double curNum = rs.getInt("cur_num");
						String numstr = StringUtil.notNull(request.getParameter("num"+pid));
						if(!numstr.equals("")){
							double num1 = Double.valueOf(numstr);
							if(ArithUtil.sub(curNum, num1)>0||ArithUtil.sub(curNum, num1)<0){
							String sql2="update inventory_product_stock set cur_num='"+num1+"',tag='1' where id='"+id+"'";
							slist.add(sql2);
							}
							}
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
				message= "盘点产品信息修改成功，请在盘点表认真核对。";
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
				
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void settle_stock_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
						String weekTag =(String) request.getParameter("weekTag");
				String sql ="select * from settle_stock where week_tag='"+weekTag+"' for update";
				stmt=conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Timestamp endTime= rs.getTimestamp("end_time");
				String sql3 = "update settle_stock set state='2' where week_tag='"+weekTag+"'";
				stmt = conn.createStatement();
				stmt.executeUpdate(sql3);
				sql3 = "update settle_stock set end_time='"+endTime+"' where week_tag='0'";
				stmt = conn.createStatement();
				stmt.executeUpdate(sql3);
				message= "本期盘点确认成功，盘点结束。";
				}else message= "盘点周期获取失败。";
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
				
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void settle_stock_reset(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
				String weekTag =(String) request.getParameter("weekTag");
				String sql ="select * from settle_stock where week_tag='"+weekTag+"' for update";
				stmt=conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String sql1 = "delete from settle_stock where week_tag='"+weekTag+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql1);
					String sql2 = "delete from inventory_product_stock where week_tag='"+weekTag+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql2);
					message= "第"+weekTag+"期盘点重置成功，请重新盘点。";
				}else message= "第"+weekTag+"期盘点信息获取失败。";
				conn.commit();
				conn.setAutoCommit(autoCommit);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void settle_stock_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		List<SettleStock> result = new ArrayList<SettleStock>();
		List<SettleStock> coll = new ArrayList<SettleStock>();
		int tag=0;
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					String pageNoStr = request.getParameter("pageNoStr");
					String pageSizeStr = request.getParameter("pageSizeStr");
					int pageNo = 1;
					int pageSize = 60;
				String sql ="select * from settle_stock  where week_tag>'0' order by week_tag desc";
				stmt=conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					SettleStock ss = new SettleStock();
					ss.setId(rs.getInt("id"));
					ss.setWeekTag(rs.getInt("week_tag"));
					ss.setState(rs.getInt("state"));
					ss.setStartTime(rs.getTimestamp("start_time"));
					ss.setEndTime(rs.getTimestamp("end_time"));
					result.add(ss);
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_list.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_list_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void settle_stock_list_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		List<InventoryProductStock> coll = new ArrayList<InventoryProductStock>();
		int tag=0;
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					String id = request.getParameter("id");
					
				String sql ="select * from settle_stock  where week_tag='"+id+"'";
				stmt=conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					SettleStock ss = new SettleStock();
					ss.setId(rs.getInt("id"));
					ss.setWeekTag(rs.getInt("week_tag"));
					ss.setState(rs.getInt("state"));
					ss.setStartTime(rs.getTimestamp("start_time"));
					ss.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("startTime", StringUtil.parseToString(ss.getStartTime(), DateUtil.yyyyMMddHHmmss));
					request.setAttribute("endTime", StringUtil.parseToString(ss.getEndTime(), DateUtil.yyyyMMddHHmmss));

					sql ="select * from inventory_product_stock  where week_tag='"+id+"' order by inventory_three asc";
					stmt=conn.createStatement();
					rs =stmt.executeQuery(sql);
					while(rs.next()){
						InventoryProductStock ps = new InventoryProductStock();
						ps.setId(rs.getInt("id"));
						ps.setPid(rs.getInt("p_id"));
						ps.setProductId(rs.getString("product_id"));
						ps.setProductName(rs.getString("product_name"));
						ps.setSpecification(rs.getString("specification"));
						ps.setStartNum(rs.getDouble("start_num"));
						ps.setCurNum(rs.getDouble("cur_num"));
						ps.setSysNum(rs.getDouble("sys_num"));
						ps.setInventoryOne(rs.getString("inventory_one"));
						ps.setInventoryTwo(rs.getString("inventory_two"));
						ps.setInventoryThree(rs.getString("inventory_three"));
						ps.setUnit(rs.getString("unit"));
						ps.setPrice(rs.getDouble("price"));
						ps.setInNum(rs.getDouble("in_num"));
						ps.setOutNum(rs.getDouble("out_num"));
						ps.setWeekTag(rs.getInt("week_tag"));
						ps.setStartTime(rs.getTimestamp("start_time"));
						ps.setEndTime(rs.getTimestamp("end_time"));
						ps.setEntryTime(rs.getTimestamp("entry_time"));
						coll.add(ps);
					}
					request.setAttribute("coll", coll);
				}else message= "盘点信息获取失败";
				
				
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_list_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_product_stock_list_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String state = StringUtil.notNull(request.getParameter("state"));
		String docNum = StringUtil.notNull(request.getParameter("docNum"));

		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		String message ="";
		try {
			if(admin!=null){
				
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='1' and doc_num like'%"+docNum+"%' and type='4' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='1' and doc_num like'%"+docNum+"%' and  type='4' and state like'%"+state+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String docNum = StringUtil.notNull(request.getParameter("docNum"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='1' and doc_num like'%"+docNum+"%' and type='4' and state='2' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='1' and doc_num like'%"+docNum+"%' and  type='4' and state='2'  order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
			
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "入库申请单信息获取不通过！";
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message = "";
		try{
		if(admin!=null){
	
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
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_in_4_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
			
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String supplier = StringUtil.notNull(request.getParameter("supplier"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String docNum = StringUtil.notNull(request.getParameter("docNum"));
				
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
						String sql1= "insert into inventoryApply(applyId,operator_id,supplier_id,supplier_code,supplier_name,type,doc_num,max_num,pay_type,state,entry_time) values('"+applyId+"','"+
						admin.getAdminName()+"','"+id+"','"+code+"','"+name+"','"+type+"','"+docNum+"','6','1','1',+'"+date+"')";
						 stmt = conn.createStatement();
						 stmt.executeUpdate(sql1);
						message= "盘盈入库申请单已生成，申请编号为："+applyId+"，请进入详情添加入库商品。";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4_pro_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				
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
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setType(rs.getInt("type"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					boolean  b = true;
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));

						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						if(inventory.getProductId().equals(productId)) b =false;
						coll.add(inventory);
					}
					if(b){
						if(ap.getMaxNum()>0){
							String sql2 ="select * from product where productId='"+productId+"' order by id asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								int pid = rs.getInt("id");
								String productName= rs.getString("productName");
								String specification = rs.getString("specification");
								String unit = rs.getString("unit");
								String sql3 = "insert into inventoryDetail(applyId,pid,productId,productName,specification,unit,num,price,remark,entry_time)"
										+ "values('"+applyId+"','"+pid+"','"+productId+"','"+productName+"','"+specification+"','"+unit+"','"
										+num+"','"+price+"','"+remark+"','"+date+"')";
								stmt= conn.createStatement();
								stmt.executeUpdate( sql3, Statement.RETURN_GENERATED_KEYS);
								rs = stmt
										.getGeneratedKeys();
								if (rs.next()) {
									int new_id = rs.getInt(1);
								double num1 = Double.valueOf(num);
								double price1 = Double.valueOf(price);
								InventoryDetail inventory = new InventoryDetail();
								inventory.setId(new_id);
								inventory.setApplyId(productName);
								inventory.setProductId(productId);
								inventory.setProductName(productName);
								inventory.setSpecification(specification);
								inventory.setUnit(unit);
								inventory.setPrice(price1);
								inventory.setNum(num1);
								inventory.setRemark(remark);
								inventory.setTotalPrice(ArithUtil.mul(num1,price1));
								total_ap.setNum(ArithUtil.add(total_ap.getNum(),num1));
								total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),price1));
								total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
								coll.add(inventory);
								ap.setMaxNum(ap.getMaxNum()-1);
								String sql4 = "update inventoryApply set max_num='"+ap.getMaxNum()+"' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
								message="产品信息添加成功！";
								}else{ 
									message="产品详情保存失败！";
								}
							}else{ 
								message="产品信息获取不通过！";
							}
						}else{ 
							message="申请单信息已满，如有需要请新增申请单！";
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
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_4_pro_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String id = StringUtil.notNull(request.getParameter("id"));
		Collection coll = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
			
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String applyId=rs.getString("applyId");
					String code = rs.getString("productId");
					InventoryDetail total_ap = new InventoryDetail();
					total_ap.setNum(0);
					total_ap.setPrice(0);
					total_ap.setTotalPrice(0);
					String sql1 ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					int max_num = rs.getInt("max_num");
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sqlu ="delete from inventoryDetail  where id='"+id+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					sqlu ="update inventoryApply set max_num='"+(max_num+1)+"' where applyId='"+applyId+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					 Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), "盘盈入库单"+applyId+"中的"+code+"删除成功。", date);
				
					ap.setMaxNum(max_num+1);
					request.setAttribute("admin_apply", ap);
					String sql3 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql3);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "盘盈入库单获取不通过。";
				}
				}else {
					message= "盘盈入库信息获取不通过。";
				}
			}else {
				message= "数据库连接已断开。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4_pro_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setType(rs.getInt("type"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));

					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));
						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						coll.add(inventory);
					}
						if(ap.getState()==1){
							
								String sql4 = "update inventoryApply set state='2' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
							
								message="盘盈入库单提交复核流程成功！";
								ap.setState(2);
							
						}else{ 
							message="申请单状态不能进行此操作！";
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
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_4_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
			
			if(db.createConn()){
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
					String sqlu ="update inventoryApply set state='0' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "盘盈入库申请删除成功，删除的编号为："+applyId+"。";
					 Timestamp date = new Timestamp(new Date().getTime());
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行删除操作，请确认！";
				}else  message= "未找到需要删除的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_in_4_detail_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "盘盈入库申请单信息获取不通过！";
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_detail_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_in_4_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
		
				String applyId = StringUtil.notNull(request.getParameter("id"));
				List<String> slist = new ArrayList<String>();
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					 Timestamp date = new Timestamp(new Date().getTime());
					if(state==2){
					String sqlu ="update inventoryApply set state='5',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					slist.add(sqlu);
					String sql1="select * from inventoryDetail where applyId='"+applyId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						double pnum = rs.getDouble("num");
						int pid = rs.getInt("pid");
						String sqlp="select * from product where id='"+pid+"' for update";
						Statement stmt1 = null;
						ResultSet rs1 = null;
						stmt1 = conn.createStatement();
						rs1 = stmt1.executeQuery(sqlp);
						if(rs1.next()){
							double num = rs1.getDouble("num");
							double total = rs1.getDouble("totalNum");
							String sqlpu = "update product set num='"+ArithUtil.add(num,pnum)+"',totalNum='"+(total+pnum)+"' where id='"+pid+"'";
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
					message= "盘盈入库申请复核确认无误，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的入库信息，请重试";
				
			
			conn.commit();
			conn.setAutoCommit(autoCommit);
				}else {
					message= "数据库连接已断开！";
				}
				
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message_review.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_in_4_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='1',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					stmt=conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "盘盈入库申请复核不通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的入库信息，请重试";
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
			
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_in_4_message_review.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String state = StringUtil.notNull(request.getParameter("state"));
		String docNum = StringUtil.notNull(request.getParameter("docNum"));

		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		String message ="";
		try {
			if(admin!=null){
				
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='2' and doc_num like'%"+docNum+"%' and type='2' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and  state like'%"+state+"%'  order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='2' and doc_num like'%"+docNum+"%' and  type='2' and state like'%"+state+"%' order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String docNum = StringUtil.notNull(request.getParameter("docNum"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String sqls= "";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					sqls ="select * from inventoryApply where  pay_type='2' and doc_num like'%"+docNum+"%' and type='2' and state='2' and  entry_time>='"+startTime+"' and entry_time<='"+endTime+"' order by id desc";
					
				}else{
					sqls ="select * from inventoryApply where  pay_type='2' and doc_num like'%"+docNum+"%' and  type='2' and state='2'  order by id desc";
				}
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					InventoryApply inventory = new InventoryApply();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setDocNum(rs.getString("doc_num"));
					inventory.setEntryTime(rs.getTimestamp("entry_time"));
					inventory.setReviewTime(rs.getTimestamp("review_time"));
					inventory.setConfirmTime(rs.getTimestamp("confirm_time"));
					inventory.setCheckTime(rs.getTimestamp("check_time"));
					inventory.setType(rs.getInt("type"));
					inventory.setOperatorId(rs.getString("operator_id"));
					inventory.setReviewId(rs.getString("review_id"));
					inventory.setCheckId(rs.getString("check_id"));
					inventory.setConfirmId(rs.getString("confirm_id"));
					inventory.setType(rs.getInt("type"));
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
				request.setAttribute("docNum", docNum);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_review_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
			
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "出库申请单信息获取不通过！";
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String message = "";
		try{
		if(admin!=null){
	
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
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_add.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void apply_out_4_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
			
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
			if(db.createConn()){
					String supplier = StringUtil.notNull(request.getParameter("supplier"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String docNum = StringUtil.notNull(request.getParameter("docNum"));
				
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
						String sql1= "insert into inventoryApply(applyId,operator_id,supplier_id,supplier_code,supplier_name,type,doc_num,max_num,pay_type,state,entry_time) values('"+applyId+"','"+
						admin.getAdminName()+"','"+id+"','"+code+"','"+name+"','"+type+"','"+docNum+"','6','2','1',+'"+date+"')";
						 stmt = conn.createStatement();
						 stmt.executeUpdate(sql1);
						message= "盘亏出库申请单已生成，申请编号为："+applyId+"，请进入详情添加出库商品。";
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4_pro_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
				
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
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setType(rs.getInt("type"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					boolean  b = true;
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));

						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						if(inventory.getProductId().equals(productId)) b =false;
						coll.add(inventory);
					}
					if(b){
						if(ap.getMaxNum()>0){
							String sql2 ="select * from product where productId='"+productId+"' order by id asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								int pid = rs.getInt("id");
								String productName= rs.getString("productName");
								String specification = rs.getString("specification");
								String unit = rs.getString("unit");
								String sql3 = "insert into inventoryDetail(applyId,pid,productId,productName,specification,unit,num,price,remark,entry_time)"
										+ "values('"+applyId+"','"+pid+"','"+productId+"','"+productName+"','"+specification+"','"+unit+"','"
										+num+"','"+price+"','"+remark+"','"+date+"')";
								stmt= conn.createStatement();
								stmt.executeUpdate( sql3, Statement.RETURN_GENERATED_KEYS);
								rs = stmt
										.getGeneratedKeys();
								if (rs.next()) {
									int new_id = rs.getInt(1);
								double num1 = Double.valueOf(num);
								double price1 = Double.valueOf(price);
								InventoryDetail inventory = new InventoryDetail();
								inventory.setId(new_id);
								inventory.setApplyId(productName);
								inventory.setProductId(productId);
								inventory.setProductName(productName);
								inventory.setSpecification(specification);
								inventory.setUnit(unit);
								inventory.setPrice(price1);
								inventory.setNum(num1);
								inventory.setRemark(remark);
								inventory.setTotalPrice(ArithUtil.mul(num1,price1));
								total_ap.setNum(ArithUtil.add(total_ap.getNum(),num1));
								total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),price1));
								total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
								coll.add(inventory);
								ap.setMaxNum(ap.getMaxNum()-1);
								String sql4 = "update inventoryApply set max_num='"+ap.getMaxNum()+"' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
								message="产品信息添加成功！";
								}else{ 
									message="产品详情保存失败！";
								}
							}else{ 
								message="产品信息获取失败！";
							}
						}else{ 
							message="申请单信息已满，如有需要请新增申请单！";
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
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_4_pro_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String id = StringUtil.notNull(request.getParameter("id"));
		Collection coll = new ArrayList();
		String message ="";
		try {
			if(admin!=null){
			
			if(db.createConn()){
				conn = db.getConnection();
					String sql ="select * from inventoryDetail where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					String applyId=rs.getString("applyId");
					String code = rs.getString("productId");
					InventoryDetail total_ap = new InventoryDetail();
					total_ap.setNum(0);
					total_ap.setPrice(0);
					total_ap.setTotalPrice(0);
					String sql1 ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					int max_num = rs.getInt("max_num");
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sqlu ="delete from inventoryDetail  where id='"+id+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					sqlu ="update inventoryApply set max_num='"+(max_num+1)+"' where applyId='"+applyId+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sqlu);
					 Timestamp date = new Timestamp(new Date().getTime());
					cs.insetAdminLog(conn, admin.getAdminName(), "盘亏出库单"+applyId+"中的"+code+"删除成功。", date);
				
					ap.setMaxNum(max_num+1);
					request.setAttribute("admin_apply", ap);
					String sql3 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql3);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "盘哭出库单获取不通过。";
				}
				}else {
					message= "盘亏出库信息获取不通过。";
				}
			}else {
				message= "数据库连接已断开。";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4_pro_cfm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				String applyId = StringUtil.notNull(request.getParameter("id"));
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setType(rs.getInt("type"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));

					ap.setEntryTime(rs.getTimestamp("entry_time"));
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						InventoryDetail inventory = new InventoryDetail();
						inventory.setId(rs.getInt("id"));
						inventory.setApplyId(rs.getString("applyId"));
						inventory.setProductId(rs.getString("productId"));
						inventory.setProductName(rs.getString("productName"));
						inventory.setSpecification(rs.getString("specification"));
						inventory.setUnit(rs.getString("unit"));
						inventory.setPrice(rs.getDouble("price"));
						inventory.setRemark(rs.getString("remark"));
						inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
						inventory.setNum(rs.getDouble("num"));
						total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
						total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
						total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
						coll.add(inventory);
					}
						if(ap.getState()==1){
							
								String sql4 = "update inventoryApply set state='2' where applyId='"+applyId+"'";
								stmt= conn.createStatement();
								stmt.executeUpdate(sql4);
							
								message="盘亏出库单提交复核流程成功！";
								ap.setState(2);
							
						}else{ 
							message="申请单状态不能进行此操作！";
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
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_detail.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_4_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
			
			if(db.createConn()){
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==1){
					String sqlu ="update inventoryApply set state='0' where applyId='"+applyId+"'";
					stmt= db.getStmtread();
					stmt.execute(sqlu);
					message= "盘盈入库申请删除成功，删除的编号为："+applyId+"。";
					 Timestamp date = new Timestamp(new Date().getTime());
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行删除操作，请确认！";
				}else  message= "未找到需要删除的入库信息，请重试";
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void apply_out_4_detail_review(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String applyId = StringUtil.notNull(request.getParameter("id"));
		String message ="";
		try {
			if(admin!=null){
			if(db.createConn()){
				InventoryDetail total_ap = new InventoryDetail();
				total_ap.setNum(0);
				total_ap.setPrice(0);
				total_ap.setTotalPrice(0);
				String sql ="select * from inventoryApply where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					InventoryApply ap = new InventoryApply();
					ap.setId(rs.getInt("id"));
					ap.setApplyId(rs.getString("applyId"));
					ap.setSupplierId(rs.getInt("supplier_id"));
					ap.setSupplierCode(rs.getString("supplier_code"));
					ap.setSupplierName(rs.getString("supplier_name"));
					ap.setDocNum(rs.getString("doc_num"));
					ap.setMaxNum(rs.getInt("max_num"));
					ap.setState(rs.getInt("state"));
					ap.setRemark(rs.getString("remark"));
					ap.setType(rs.getInt("type"));
					ap.setEntryTime(rs.getTimestamp("entry_time"));
					request.setAttribute("admin_apply", ap);
					String sql1 ="select * from inventoryDetail where applyId='"+applyId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					InventoryDetail inventory = new InventoryDetail();
					inventory.setId(rs.getInt("id"));
					inventory.setApplyId(rs.getString("applyId"));
					inventory.setProductId(rs.getString("productId"));
					inventory.setProductName(rs.getString("productName"));
					inventory.setSpecification(rs.getString("specification"));
					inventory.setUnit(rs.getString("unit"));
					inventory.setPrice(rs.getDouble("price"));
					inventory.setRemark(rs.getString("remark"));
					inventory.setTotalPrice(ArithUtil.mul(rs.getDouble("num"),rs.getDouble("price")));
					inventory.setNum(rs.getDouble("num"));
					total_ap.setNum(ArithUtil.add(total_ap.getNum(), rs.getDouble("num")));
					total_ap.setPrice(ArithUtil.add(total_ap.getPrice(),rs.getDouble("price")));
					total_ap.setTotalPrice(ArithUtil.add(total_ap.getTotalPrice(), inventory.getTotalPrice()));
					coll.add(inventory);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("total_ap", total_ap);
				}else {
					message= "盘亏出库申请单信息获取不失败！";
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_detail_review.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message_review.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_out_4_review_yes(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		String error="";
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
		
				String applyId = StringUtil.notNull(request.getParameter("id"));
				List<String> slist = new ArrayList<String>();
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					 Timestamp date = new Timestamp(new Date().getTime());
					if(state==2){
					String sqlu ="update inventoryApply set state='5',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					slist.add(sqlu);
					String sql1="select * from inventoryDetail where applyId='"+applyId+"' for update";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						double pnum = rs.getDouble("num");
						int pid = rs.getInt("pid");
						String sqlp="select * from product where id='"+pid+"' for update";
						System.out.println(sqlp);
						Statement stmt1 = null;
						ResultSet rs1 = null;
						stmt1 = conn.createStatement();
						rs1 = stmt1.executeQuery(sqlp);
						if(rs1.next()){
							double num = rs1.getDouble("num");
							String productId = rs1.getString("productId");
							if(ArithUtil.sub(num,pnum)>=0){
							String sqlpu = "update product set num='"+ArithUtil.sub(num,pnum)+"' where id='"+pid+"'";
							slist.add(sqlpu);
							}else{
								error  =error+productId+";";
							}
						}
					}
					if(error.equals("")){
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
					message= "盘亏出库申请复核确认无误，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else{
						message =error+"等产品库存不足，请核对。";
					}
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库信息，请重试";
				
			
			conn.commit();
			conn.setAutoCommit(autoCommit);
				}else {
					message= "数据库连接已断开！";
				}
				
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message_review.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void apply_out_4_review_no(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		
		String message ="";
		
		try {
			if(admin!=null){
				
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				conn = db.getConnection();
				String applyId = StringUtil.notNull(request.getParameter("id"));
					String sql ="select * from inventoryApply where applyId='"+applyId+"' for update";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int state = rs.getInt("state");
					if(state==2){
						 Timestamp date1 = new Timestamp(new Date().getTime());
						 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					String sqlu ="update inventoryApply set state='1',confirm_id='"+admin.getAdminName()+"',confirm_time='"+date+"' where applyId='"+applyId+"'";
					stmt=conn.createStatement();
					stmt.executeUpdate(sqlu);
					message= "盘亏出库申请复核不通过，审核编号为："+applyId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					}else message= "当前状态不能进行审核操作，请确认！";
				}else  message= "未找到需要审核的出库库信息，请重试";
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}else {
				message= "数据库连接已断开！";
			}
			
				}else{
					message=  "管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message=  "数据有误，请重新登陆！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inventory_apply_out_4_message_review.jsp");
				dispatcher.forward(request, response);
		}
	}
	
}

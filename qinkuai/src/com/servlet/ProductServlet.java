package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.db.DBConn;
import com.jspsmart.upload.SmartUploadException;
import com.pojo.Address;
import com.pojo.Admin;
import com.pojo.Branch;
import com.pojo.Inventory;
import com.pojo.Order;
import com.pojo.Product;
import com.pojo.ProductAttribute;
import com.pojo.ProductDetail;
import com.pojo.ProductType;
import com.pojo.ProductUnit;
import com.pojo.Team;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;

public class ProductServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private String productType;
	private String productName;
	private String specification;
	private double price=0;
	private String productId;
	private double pv=0;
	private String imageUrl;
	private Integer num=0;
	private String state;
	private String features;
	private String token;
	ICustomService cs = new CustomService();

	
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
		}else if(method.equals("list")){
			list(request,response);
		}else if(method.equals("product_list")){
			product_list(request,response);
		}else if(method.equals("admin_save")){
		
				try {
					try {
						admin_save(request,response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}else if(method.equals("save_select")){
			
		
				try {
					save_select(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}else if(method.equals("admin_list")){
			admin_list(request,response);
		}
		else if(method.equals("isExit")){
			isExit(request,response);
		}
		else if(method.equals("admin_add")){
			try {
				add(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("del")){
			del(request,response);
		}else if(method.equals("admin_down")){
			admin_down(request,response);
		}else if(method.equals("admin_up")){
			admin_up(request,response);
		}else if(method.equals("delAll")){
			delAll(request,response);
		}else if(method.equals("shop_list")){
			shop_list(request,response);
		}else if(method.equals("detail")){
			try {
				detail(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("product_detail")){
			try {
				product_detail(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("edit")){
			try {
				admin_edit(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("update")){
			try {
				admin_update(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("img_edit")){
			try {
				img_edit(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("img_save")){
			try {
				img_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("product_index")){
			product_index(request,response);
		}else if(method.equals("product_detail")){
			try {
				product_detail(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("increate_p")){
			increate_p(request,response);
		}else if(method.equals("disDe_p")){
			disDe_p(request,response);
		}else if(method.equals("increate_ajax")){
			increate_ajax(request,response);
		}else if(method.equals("disDe_ajax")){
			disDe_ajax(request,response);
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
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		List  result = new ArrayList();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select * from product where (productId like '%"+productName+"%'  or productName like '%"+productName+"%' ) and  productType like('%"+typeName+"%') and  type like('%"+type+"%') and state!='0' order by id asc";	
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				request.setAttribute("type", type);
				request.setAttribute("productName", productName);
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
					product.setInventoryOne(rs.getString("inventory_one"));
					product.setInventoryTwo(rs.getString("inventory_two"));
					product.setInventoryThree(rs.getString("inventory_three"));
					product.setUnit(rs.getString("unit"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setBranchType(rs.getString("branch_type"));
					product.setNum(rs.getDouble("num"));
					product.setLimitNum(rs.getDouble("limit_num"));
					product.setUnit(rs.getString("unit"));
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("totalNum"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					result.add(product);
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
				String sql1 = "select * from productType where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setTypeName(rs.getString("typeName"));
					coll_pt.add(pt);
				}
				request.setAttribute("coll_pt", coll_pt);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		String idstr = StringUtil.notNull(request.getParameter("id"));
		String typeName="";
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		List<Product> plist1 = (List<Product>) request.getSession().getAttribute("shop_cart_list_1");
		Order order = (Order) request.getSession().getAttribute("sys_order");
		if(plist!=null) request.getSession().removeAttribute("shop_cart_list");
		if(plist1!=null) request.getSession().removeAttribute("shop_cart_list_1");
		if(order!=null) request.getSession().removeAttribute("sys_order");
		request.setAttribute("total_price", (double) 0);
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
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
			
				String sql = "select * from product where type like'%1%' and  productType like('%"+typeName+"%') and  state='1' order by id asc";	
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
					product.setUnit(rs.getString("unit"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(0);
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("num"));
					product.setLimitNum(rs.getDouble("limit_num"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					product.setTag(0);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
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
				String sql = "select * from product where type like'%1%' and  productType like('%"+typeName+"%') and  state='1'   order by id asc";	
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
					product.setUnit(rs.getString("unit"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(0);
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("num"));
					product.setLimitNum(rs.getDouble("limit_num"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					product.setTag(0);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void shop_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		String idstr = StringUtil.notNull(request.getParameter("id"));
		String typeName="";
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		double total_price= 0;
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
		try {
			if(db.createConn()){
				conn = db.getConnection();
				boolean autoCommit= conn.getAutoCommit();
				conn.setAutoCommit(false);
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
				String sql = "select * from product where type like'%1%' and  productType like('%"+typeName+"%') and  state='1' order by id asc";	
				stmt= conn.createStatement();
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
					product.setUnit(rs.getString("unit"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(0);
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("num"));
					product.setLimitNum(rs.getDouble("limit_num"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					product.setTag(0);
					coll.add(product);
					if(plist!=null){
						for(int i=0;i<plist.size();i++){
							if(plist.get(i).getId()==product.getId()){
								product.setNum(plist.get(i).getNum());
								plist.get(i).setPrice(product.getPrice());
							}
						}
					}
				}
				if(plist!=null){
					for(int i=0;i<plist.size();i++){
						total_price = ArithUtil.add(total_price, ArithUtil.mul(plist.get(i).getNum(), plist.get(i).getPrice()));

					}
				}
				request.setAttribute("total_price", total_price);
					request.setAttribute("coll", coll);
					conn.commit();
					conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String productId = request.getParameter("productId");
		int tag=0;
		try {
			String productName="";
			int state=0;
			if(db.createConn()){
				
				if (StringUtil.notNull(productId).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product where productId ='"+productId+"'  order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
					if(rs.next()){
						tag =1;
						productName =rs.getString("productName");
						state  =rs.getInt("state");
					}
					else
						tag = 2;
				}
			}else {
				tag = 0;
			}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", tag);
			object.put("state", state);
			object.put("productName", productName);
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
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		Collection coll_pt = new ArrayList();
		Collection coll_pn = new ArrayList();
		Collection coll_pa = new ArrayList();
		Collection coll_1 = new ArrayList();
		Collection coll_2 = new ArrayList();
		Collection coll_3 = new ArrayList();
		DBConn db = new DBConn();
		String message="";
		try{
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				boolean autoCommit= conn.getAutoCommit();
				conn.setAutoCommit(false);
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			String sql1 = "select * from productType where state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			while(rs.next()){
				ProductType pt = new ProductType();
				pt.setTypeName(rs.getString("typeName"));
				coll_pt.add(pt);
			}
			String sql2 = "select * from product_unit where state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql2);
			while(rs.next()){
				ProductUnit pt = new ProductUnit();
				pt.setId(rs.getInt("id"));
				pt.setName(rs.getString("name"));
				coll_pn.add(pt);
			}
			
			String sql3 = "select * from product_attribute where state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql3);
			while(rs.next()){
				ProductAttribute pt = new ProductAttribute();
				pt.setId(rs.getInt("id"));
				pt.setName(rs.getString("name"));
				coll_pa.add(pt);
			}
			String sql4 = "select * from inventory where state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql4);
			while(rs.next()){
				Inventory pt = new Inventory();
				pt.setId(rs.getInt("id"));
				pt.setInventoryName(rs.getString("inventoryName"));
				coll_1.add(pt);
			}
			
			String sql5 = "select * from inventory_two where state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql5);
			while(rs.next()){
				Inventory pt = new Inventory();
				pt.setId(rs.getInt("id"));
				pt.setInventoryName(rs.getString("name"));
				coll_2.add(pt);
			}
			
			String sql6 = "select * from inventory_three where state='1' order by id asc";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql6);
			while(rs.next()){
				Inventory pt = new Inventory();
				pt.setId(rs.getInt("id"));
				pt.setInventoryName(rs.getString("name"));
				coll_3.add(pt);
			}
			request.setAttribute("coll_pt", coll_pt);
			request.setAttribute("coll_pn", coll_pn);
			request.setAttribute("coll_pa", coll_pa);
			request.setAttribute("coll_1", coll_1);
			request.setAttribute("coll_2", coll_2);
			request.setAttribute("coll_3", coll_3);
			conn.commit();
			conn.setAutoCommit(autoCommit);
		
		}else {
			message=  "数据库连接已断开！";
		}	
		
		}else{
			message= "您没有该操作权限，如有需要请与系统管理员联系！";
		}
		
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_add.jsp");
				dispatcher.forward(request, response);
				
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void img_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			String sql1 = "select * from product where id='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product pt = new Product();
				pt.setId(rs.getInt("id"));
				pt.setProductId(rs.getString("productId"));
				pt.setProductName(rs.getString("productName"));
				request.setAttribute("product", pt);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
		}else {
			message="数据库连接已断开！";
		}	
		}else{
			message= "您没有该操作权限，如有需要请与系统管理员联系！";
		}
		
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				db.close();
				if(message.equals("")){
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_img_edit.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		Collection coll_pt = new ArrayList();
		Collection coll_pn = new ArrayList();
		Collection coll_pa = new ArrayList();
		Collection coll_1 = new ArrayList();
		Collection coll_2 = new ArrayList();
		Collection coll_3 = new ArrayList();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			String sql1 = "select * from product where id='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("productName"));
				product.setFeatures(rs.getString("features"));
				product.setProductId(rs.getString("productId"));
				product.setProductType(rs.getString("productType"));
				product.setSpecification(rs.getString("specification"));
				product.setUnit(rs.getString("unit"));
				product.setPrice(rs.getDouble("price"));
				product.setType(rs.getString("type"));
				product.setBranchType(rs.getString("branch_type"));
				product.setNum(rs.getDouble("num"));
				product.setImageUrl(rs.getString("imageUrl"));
				product.setState(rs.getInt("state"));
				product.setLimitNum(rs.getDouble("limit_num"));
				product.setEntryTime(rs.getTimestamp("entryTime"));
				product.setEndTime(rs.getTimestamp("endTime"));
				product.setInventoryOne(rs.getString("inventory_one"));
				product.setInventoryTwo(rs.getString("inventory_two"));
				product.setInventoryThree(rs.getString("inventory_three"));
				product.setAttribute(rs.getString("attribute"));
				request.setAttribute("product", product);
				String sqls = "select * from productType where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setTypeName(rs.getString("typeName"));
					coll_pt.add(pt);
				}
				request.setAttribute("coll_pt", coll_pt);
				String sql2 = "select * from product_unit where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql2);
				while(rs.next()){
					ProductUnit pt = new ProductUnit();
					pt.setId(rs.getInt("id"));
					pt.setName(rs.getString("name"));
					coll_pn.add(pt);
				}
				request.setAttribute("coll_pn", coll_pn);
			
				String sql3 = "select * from product_attribute where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql3);
				while(rs.next()){
					ProductAttribute pt = new ProductAttribute();
					pt.setId(rs.getInt("id"));
					pt.setName(rs.getString("name"));
					coll_pa.add(pt);
				}
				String sql4 = "select * from inventory where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql4);
				while(rs.next()){
					Inventory pt = new Inventory();
					pt.setId(rs.getInt("id"));
					pt.setInventoryName(rs.getString("inventoryName"));
					coll_1.add(pt);
				}
				
				String sql5 = "select * from inventory_two where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql5);
				while(rs.next()){
					Inventory pt = new Inventory();
					pt.setId(rs.getInt("id"));
					pt.setInventoryName(rs.getString("name"));
					coll_2.add(pt);
				}
				
				String sql6 = "select * from inventory_three where state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql6);
				while(rs.next()){
					Inventory pt = new Inventory();
					pt.setId(rs.getInt("id"));
					pt.setInventoryName(rs.getString("name"));
					coll_3.add(pt);
				}
				request.setAttribute("coll_pa", coll_pa);
				request.setAttribute("coll_1", coll_1);
				request.setAttribute("coll_2", coll_2);
				request.setAttribute("coll_3", coll_3);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
		}else {
			message="数据库连接已断开！";
		}	
		}else{
			message= "您没有该操作权限，如有需要请与系统管理员联系！";
		}
		
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				db.close();
				if(message.equals("")){
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_edit.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		Collection coll = new ArrayList();
		DBConn db = new DBConn();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
			String sql1 = "select * from product where id='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("productName"));
				product.setFeatures(rs.getString("features"));
				product.setProductId(rs.getString("productId"));
				product.setProductType(rs.getString("productType"));
				product.setSpecification(rs.getString("specification"));
				product.setUnit(rs.getString("unit"));
				product.setPrice(rs.getDouble("price"));
				product.setPv(rs.getDouble("pv"));
				product.setType(rs.getString("type"));
				product.setNum(rs.getDouble("num"));
				product.setdType(rs.getString("d_type"));
				imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
				if(!imageUrl.equals("")) imageUrl = "/upload/"+imageUrl;
				product.setImageUrl(imageUrl);
				product.setState(rs.getInt("state"));
				product.setEntryTime(rs.getTimestamp("entryTime"));
				product.setEndTime(rs.getTimestamp("endTime"));
				product.setIntegral(rs.getDouble("integral"));
				request.setAttribute("product", product);
				
				if(product.getType().equals("2")){
					String sqls = "select * from productDetail where pId='"+product.getProductId()+"' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sqls);
					while(rs.next()){
						ProductDetail pd = new ProductDetail();
						pd.setId(rs.getInt("id"));
						pd.setpId(rs.getString("pId"));
						pd.setProductName(rs.getString("productName"));
						pd.setProductId(rs.getString("productId"));
						pd.setProductType(rs.getString("productType"));
						pd.setSpecification(rs.getString("specification"));
						pd.setPrice(rs.getDouble("price"));
						pd.setPv(rs.getDouble("pv"));
						pd.setNum(rs.getDouble("num"));
						imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
						if(!imageUrl.equals("")) imageUrl = "/upload/"+imageUrl;
						pd.setImageUrl(imageUrl);
						coll.add(pd);
					}
					request.setAttribute("coll", coll);
				}
			}else{
				message="未获取相应的产品信息，请重试！";
			}
		}else {
			message="数据库连接已断开！";
		}	
		}else{
			message= "您没有该操作权限，如有需要请与系统管理员联系！";
		}
		
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				db.close();
				if(message.equals("")){
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	public void product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		String message="";
		DBConn db = new DBConn();
		try {
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
			String sql1 = "select * from product where productId='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("productName"));
				product.setFeatures(rs.getString("features"));
				product.setProductId(rs.getString("productId"));
				product.setProductType(rs.getString("productType"));
				product.setSpecification(rs.getString("specification"));
				product.setUnit(rs.getString("unit"));
				product.setPrice(rs.getDouble("price"));
				product.setPv(rs.getDouble("pv"));
				product.setType(rs.getString("type"));
				product.setUnit(rs.getString("unit"));
				product.setNum(rs.getDouble("num"));
				product.setImageUrl(rs.getString("imageUrl"));
				product.setState(rs.getInt("state"));
				product.setEntryTime(rs.getTimestamp("entryTime"));
				product.setEndTime(rs.getTimestamp("endTime"));
				request.setAttribute("sys_product", product);
				
			}else{
				message="未获取相应的产品信息，请重试！";
			}
		}else {
			message="数据库连接已断开！";
		}	
		
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				db.close();
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	public void admin_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		 int token_new = (int)(1+Math.random()*(1000000-1+1));
		 String token = StringUtil.notNull(request.getParameter("token"));
		 request.getSession().setAttribute("token", String.valueOf(token_new));
		productId = StringUtil.notNull(request.getParameter("productId"));
		productName = StringUtil.notNull(request.getParameter("productName"));
		String[] typestr = request.getParameterValues("type");
		String[] branchTypestr = request.getParameterValues("branchType");
		specification = StringUtil.notNull(request.getParameter("specification"));
		features = StringUtil.notNull(request.getParameter("features"));
		String productType = StringUtil.notNull(request.getParameter("productType"));
		String id = StringUtil.notNull(request.getParameter("id"));
		String price = StringUtil.notNull(request.getParameter("price"));
		String limitNum = StringUtil.notNull(request.getParameter("limitNum"));
		String unit = StringUtil.notNull(request.getParameter("unit"));
		String attribute = StringUtil.notNull(request.getParameter("attribute"));

		String inventoryOne = StringUtil.notNull(request.getParameter("inventoryOne"));
		String inventoryTwo = StringUtil.notNull(request.getParameter("inventoryTwo"));
		String inventoryThree = StringUtil.notNull(request.getParameter("inventoryThree"));
		 String message = "";
		 DBConn db = new DBConn();
		 try {  
			 if(admin!=null){
				 if(StringUtil.notNull(token_old).equals(token)){
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if(rankstr[2][2].equals("1")||admin.getState().equals("1")){
			request.getSession().setAttribute("token", String.valueOf(token_new));
		 if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			 String sql1 = "select * from product where id='"+id+"' for update";
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql1);
			 if(rs.next()){
				String type="";
				if(typestr!=null){
					for(int i=0;i<typestr.length;i++){
						if(type.equals("")) type=typestr[i];
						else  type=type+","+typestr[i];
					}
				}
				String branchType="";
				if(branchTypestr!=null){
					for(int i=0;i<branchTypestr.length;i++){
						if(branchType.equals("")) branchType=branchTypestr[i];
						else  branchType=branchType+","+branchTypestr[i];
					}
				}
			 String sql = "update product set productId='"+productId+"',productName='"+productName
					 +"',productType='"+productType+"',specification='"+specification
					 +"',inventory_one='"+inventoryOne +"',inventory_two='"+inventoryTwo +"',inventory_three='"+inventoryThree
					 +"',attribute='"+attribute+"',features='"+features+"',price='"+price
					 +"',type='"+type +"',branch_type='"+branchType+"',unit='"+unit+"',limit_num='"+limitNum+"'  where id='"+id+"'";
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 message= productId+"产品更新成功.";
			 Timestamp date = new Timestamp(new Date().getTime());
				cs.insetAdminLog(conn, admin.getAdminName(), message, date);
			}else message="未查询到产品对应的类型信息！";		
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
		 }else {
			 message= "数据库连接已断开！";
		}
	
					}else{
						 message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
				 }else{
					 message= "请勿重复提交数据，请在产品列表中查看是否保存成功！";
				}
	}else{
		 message= "管理员用户未登陆，请重新登陆！";
	}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				 message= "数据操作有误，请重试！";
			}finally{
				db.close();
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				}
		}
	
	@SuppressWarnings("deprecation")
	public void img_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
		 String path = request.getRealPath("/upload");  
		 factory.setRepository(new File(path)); 
		 factory.setSizeThreshold(1024*1024) ;  
		 ServletFileUpload upload = new ServletFileUpload(factory);  
		 String message = "";
		 DBConn db = new DBConn();
		 try {  
			 if(admin!=null){
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
		 List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		 String id = "";
		 String token = "";
		 String imgurl = "";
		 for(FileItem item : list)  
         {  
             String name = item.getFieldName();  
             if(item.isFormField())  
             {                     
                 String value = item.getString() ;  
                   if(name.equals("token")){
                	   token = value;
                   }else if(name.equals("id")) id =value;
             }  
             else  
             {  
                 String value = item.getName() ;  
                
                 int start = value.lastIndexOf("\\");  
               
                 String filename = value.substring(start+1);  
                   
                 imgurl = filename;
                
                 OutputStream out = new FileOutputStream(new File(path,filename));  
                   
                 InputStream in = item.getInputStream() ;  
                 int length = 0 ;  
                 byte [] buf = new byte[1024] ;  
                 while( (length = in.read(buf) ) != -1)  
                 		{  
                      out.write(buf, 0, length);  
                 		}  
                 in.close();  
                 out.close();  
             }  
         }  
		 int token_new = (int)(1+Math.random()*(1000000-1+1));
			request.getSession().setAttribute("token", String.valueOf(token_new));
		
		 if(StringUtil.notNull(token_old).equals(token)){
		 if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			 String sql = "update product set imageUrl='"+imgurl+"' where id='"+id+"'";
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 message= "图片上传成功！";
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
		 }else {
			 message= "数据库连接已断开！";
		}
		 
	}else{
		 message= "请勿重复提交数据，请在产品列表中查看是否保存成功！";
	}
					}else{
						 message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
	}else{
		 message= "管理员用户未登陆，请重新登陆！";
	}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				conn.rollback();
				e.printStackTrace();
				 message= "管理员用户未登陆，请重新登陆！";
			}finally{
				db.close();
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
					dispatcher.forward(request, response);
				}
		}
	
	public void admin_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, SmartUploadException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		imageUrl = "";
		token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		 productId = request.getParameter("productId");
		productName = StringUtil.notNull(request.getParameter("productName"));
		specification = StringUtil.notNull(request.getParameter("specification"));
		features = StringUtil.notNull(request.getParameter("features"));
		String price = StringUtil.notNull(request.getParameter("price"));
		String productType = StringUtil.notNull(request.getParameter("productType"));
		String unit = StringUtil.notNull(request.getParameter("unit"));
		String limitNum = StringUtil.notNull(request.getParameter("limitNum"));
		String attribute = StringUtil.notNull(request.getParameter("attribute"));
		String[] typestr = request.getParameterValues("type");
		String[] branchTypestr = request.getParameterValues("branchType");
		String inventoryOne = StringUtil.notNull(request.getParameter("inventoryOne"));
		String inventoryTwo = StringUtil.notNull(request.getParameter("inventoryTwo"));
		String inventoryThree = StringUtil.notNull(request.getParameter("inventoryThree"));

		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][1].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					
									if(!(StringUtil.notNull(productId).equals(""))){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
			String type="";
			if(typestr!=null){
				for(int i=0;i<typestr.length;i++){
					if(type.equals("")) type=typestr[i];
					else  type=type+","+typestr[i];
				}
			}
			
			String branchType="";
			if(branchTypestr!=null){
				for(int i=0;i<branchTypestr.length;i++){
					if(branchType.equals("")) branchType=branchTypestr[i];
					else  branchType=branchType+","+branchTypestr[i];
				}
			}
			
			
					String sql = "select * from product where productId ='"+productId+"'";
					stmt=conn.createStatement();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String sqli = "insert into product(productId,productName,productType,type,branch_type,attribute,unit,specification,features,inventory_one,inventory_two,inventory_three,price,limit_num"
					 		+ ",imageUrl,state,entryTime,endTime) values('"+productId+"','"+productName+"','"+productType+"','"+type+"','"+branchType+"','"+attribute+"','"+unit+"','"+specification+"','"+features
					 		+"','"+inventoryOne+"','"+inventoryTwo+"','"+inventoryThree+"','"+price+"','"+limitNum+"','"+imageUrl
					 +"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 message="产品信息保存成功，请核对产品编号："+productId+"。";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				}else{
					 message= "产品编号已被注册，请重新录入！";
				}
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				}else {
					 message= "产品名称不能为空，请重新录入！";
				}
			}else {
				 message= "数据库连接已断开！";
			}
				}else{
					 message= "您没有该操作权限，如有需要请与系统管理员联系！";
			}
				}else{
					 message= "请勿重复提交数据，请在产品列表中查看是否保存成功！";
			}
			}else{
			
				 message= "管理员用户未登陆，请重新登陆！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void save_select(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		imageUrl = "";
		token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
			String[] numstr = request.getParameterValues("numstr");
			String[] pid = request.getParameterValues("pid");
		Product product = (Product)request.getSession().getAttribute("product");
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				if(!(StringUtil.notNull(productId).equals(""))){
					String sql = "select * from product where productId ='"+product.getProductId()+"'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					List<ProductDetail> olist = new ArrayList<ProductDetail>();
					List<String> slist = new ArrayList<String>();
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					double totalprice = 0;
					double totalpv = 0;
					if (numstr.length>0) {
					for (int i = 0; i < pid.length; i++) {
						
								if(Integer.valueOf(numstr[i])>0){
								String sqls = "select * from product where id='"+Integer.valueOf(pid[i])+"'";
								stmt = db.getStmt();
								rs = stmt.executeQuery(sqls);
								if(rs.next()){
								ProductDetail od = new ProductDetail();
								od.setNum(Double.valueOf(numstr[i]));
								od.setProductId(rs.getString("productId"));
								od.setProductName(rs.getString("productName"));
								od.setSpecification(rs.getString("specification"));
								od.setProductType(rs.getString("productType"));
								od.setProductPrice(rs.getDouble("price"));
								
								od.setProductPv(rs.getDouble("pv"));
								od.setImageUrl(rs.getString("imageUrl"));
								od.setPrice(ArithUtil.mul(rs.getDouble("price") , Double.valueOf(numstr[i])));
								od.setPv(ArithUtil.mul(rs.getDouble("pv") ,Double.valueOf(numstr[i])));
								totalprice = ArithUtil.add(totalprice ,ArithUtil.mul( rs.getDouble("price"), Double.valueOf(numstr[i])));
								totalpv = ArithUtil.add(totalpv , ArithUtil.mul(rs.getDouble("pv") , Double.valueOf(numstr[i])));
								String sqld = "insert into productDetail(pId,productId,productName,specification,productType,productPrice,productPv,price,pv,num,imageUrl) values('"+product.getProductId()+
										 "','"+od.getProductId()+"','"+od.getProductName()+"','"+od.getSpecification()+"','"+od.getProductType()+"','"+od.getProductPrice()+"','"+od.getProductPv()+"','"+od.getPrice()+"','"+od.getPv()+"','"+od.getNum()+"','"+od.getImageUrl()+"')"; 
								slist.add(sqld);
								}
							}
						}
					}
					if(slist.size()>0){
					 String sqli = "insert into product(productId,productName,productType,type,specification,features,price,pv,num,imageUrl,"
						 		+ "state,entryTime,endTime) values('"+product.getProductId()+"','"+product.getProductName()+"','"+product.getProductType()+"','"+product.getType()+"','"+product.getSpecification()+"','"+product.getFeatures()
						 		+"','"+totalprice+"','"+totalpv+"','0','"+imageUrl
						 +"','1','"+date+"','"+date+"')";
					 System.out.println(sqli);
					 stmt =conn.createStatement();
					 stmt.addBatch(sqli);
					 for(int j=0;j<slist.size();j++){
					 stmt.addBatch(slist.get(j));
					 }
					 stmt.executeBatch();
					 message="产品信息保存成功，请核对产品编号："+product.getProductId()+"。";
					}else{
						message="套餐产品必须选择单品，请重新定义！";
					}
				}else{
					 message= "产品编号已被注册，请重新录入！";
				}
				}else {
					 message= "产品名称不能为空，请重新录入！";
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
					 message= "请勿重复提交数据，请在产品列表中查看是否保存成功！";
			}
			}else{
				 message= "管理员用户未登陆，请重新登陆！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
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
				if(rankstr[2][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String idstr = request.getParameter("id");
				if(!StringUtil.notNull(idstr).equals("")){
						int id = Integer.valueOf(idstr);
				String sql = "select * from product where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("productName");
					String sqli = "update product set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品删除成功，删除产品为"+name+"！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				
			}else{
				message="产品ID信息有误，请重新选择！";
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_down(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
			
			if(db.createConn()){
				String idstr = request.getParameter("id");
				if(!StringUtil.notNull(idstr).equals("")){
						int id = Integer.valueOf(idstr);
				String sql = "select * from product where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("productName");
					String sqli = "update product set state='2' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品下线成功，下线产品为"+name+"！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				
			}else{
				message="产品ID信息有误，请重新选择！";
			}
			}else {
				message="数据库连接已断开！";
			}
			
			}else{
				message="管理员用户未登陆，请重新登陆！";
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
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void admin_up(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
			
			if(db.createConn()){
				String idstr = request.getParameter("id");
				if(!StringUtil.notNull(idstr).equals("")){
						int id = Integer.valueOf(idstr);
				String sql = "select * from product where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt= db.getStmtread();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("productName");
					String sqli = "update product set state='1' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品上线成功，下线产品为"+name+"！";
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				
			}else{
				message="产品ID信息有误，请重新选择！";
			}
			}else {
				message="数据库连接已断开！";
			}
			
			}else{
				message="管理员用户未登陆，请重新登陆！";
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
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void delAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[2][4].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String[] idstr = request.getParameterValues("ids");
				for(int i=0;i<idstr.length;i++){
					if(!idstr[i].equals("")){
					int id = Integer.valueOf(idstr[i]);
					String sql = "select * from product where id ='"+id+"' for update";
					 stmt= conn.createStatement();
					 rs =stmt.executeQuery(sql);
					if(rs.next()){
						String sqli = "update product set state='0' where id ='"+id+"'";
						stmt = conn.createStatement();
						stmt.executeUpdate(sqli);
					}
					}
				}
				message="批量删除产品成功！";
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public String saveImage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			String imageUrl = "";
			//为解析类提供配置信息
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//创建解析类的实例
			ServletFileUpload sfu = new ServletFileUpload(factory);
			 sfu.setHeaderEncoding("UTF-8");  //处理中文问题
			//开始解析
			sfu.setFileSizeMax(1024*400);
			//每个表单域中数据会封装到一个对应的FileItem对象上
			try {
			List<FileItem> items = sfu.parseRequest(req);
			//区分表单域
			for (int i = 0; i < items.size(); i++) {
			FileItem item = items.get(i);
			//isFormField为true，表示这不是文件上传表单域
			if(!item.isFormField()){
				ServletContext sctx = getServletContext();
				//获得存放文件的物理路径
				//upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹
	
				String path = sctx.getRealPath("/upload");
				//获得文件名
				imageUrl = item.getName();
				//该方法在某些平台(操作系统),会返回路径+文件名
				String fileName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
				File file = new File(path+"\\"+fileName);
				if(!file.exists()){
				item.write(file);
				//将上传图片的名字记录到数据库中
				}
				
			}/*else{
				String name = StringUtil.notNull(item.getName());
				if(item.getFieldName().equals("productId")) productId = name;
				else if(item.getFieldName().equals("productName")) productName = name;
				else if(item.getFieldName().equals("specification")) specification = name;
				else if(item.getFieldName().equals("price")){
					if(!name.equals(""))
					price = double.valueOf(name);
				}
				else if(item.getFieldName().equals("pv")){
					if(!name.equals(""))
					pv = double.valueOf(name);
				}
				else if(item.getFieldName().equals("num")){
					if(!name.equals(""))
					num = Integer.valueOf(name);
				}
				else if(item.getFieldName().equals("features")) features = name;
				else if(item.getFieldName().equals("token")) token = name;
				
			}
			*/
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
			return imageUrl;
	} 



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void product_index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		Collection coll_1 = new ArrayList();
		Collection coll_2 = new ArrayList();
		try {
			if(user!=null){
			
			if(db.createConn()){
				String sql = "select * from product where productType like '%报单产品%' and state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("productName"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("productId"));
					product.setdType(rs.getString("d_type"));
					product.setProductType(rs.getString("productType"));
					product.setSpecification(rs.getString("specification"));
					product.setUnit(rs.getString("unit"));

					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(rs.getDouble("num"));
					product.setTotalNum(rs.getDouble("totalNum"));
					imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
					if(!imageUrl.equals("")) imageUrl = "/upload/"+imageUrl;
					product.setImageUrl(imageUrl);
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					coll_1.add(product);
				}
				request.setAttribute("coll_1", coll_1);
				sql = "select * from product where productType like '%复消产品%' and state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("productName"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("productId"));
					product.setdType(rs.getString("d_type"));
					product.setProductType(rs.getString("productType"));
					product.setSpecification(rs.getString("specification"));
					product.setUnit(rs.getString("unit"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(rs.getDouble("num"));
					product.setTotalNum(rs.getDouble("totalNum"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					coll_2.add(product);
				}
				request.setAttribute("coll_2", coll_2);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_index.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void increate_p(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");

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
						plist.get(i).setNum(ArithUtil.add(plist.get(i).getNum(),1));
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
						product.setType(rs.getString("type"));
						product.setUnit(rs.getString("unit"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setNum(0);
						product.setFee(rs.getInt("fee"));
						product.setTotalNum(rs.getDouble("num"));
						imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
						product.setImageUrl(imageUrl);
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						if(plist!=null){
							int t=0;
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==product.getId()){
									double totalNum = ArithUtil.add(product.getTotalNum(),plist.get(i).getOldNum());
									
									if(totalNum>=plist.get(i).getNum()){
											product.setNum(plist.get(i).getNum());
									}else{
											product.setNum(totalNum);
											plist.get(i).setNum(totalNum);
									}
										t++;
								}
							}
							if(t==0&&tag==product.getId()){
								if(product.getTotalNum()>0){
								product.setNum(1);
								plist.add(product);
								}
							}
						}else if(tag==product.getId()){
							if(product.getTotalNum()>0){
								product.setNum(1);
								plist =new ArrayList<Product>();
								plist.add(product);
							}
						}
						coll.add(product);
					}
						request.setAttribute("coll", coll);
						request.getSession().setAttribute("shop_cart_list", plist);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void disDe_p(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
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
						plist.get(i).setNum(ArithUtil.sub(plist.get(i).getNum(),1));
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
						product.setUnit(rs.getString("unit"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setNum(0);
						product.setFee(rs.getInt("fee"));
						product.setTotalNum(rs.getDouble("num"));
						imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
						product.setImageUrl(imageUrl);
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
						request.getSession().setAttribute("shop_cart_list", plist);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);
		}
	}
	
public void increate_ajax(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
			double total_price=0;
			double productTotalPrice=0;
			double num = 0;
			Map<String, Object> object = new HashMap<String, Object>();
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String ids = StringUtil.notNull(request.getParameter("tag"));
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
				for(int i=0;i<plist.size();i++){
					if(plist.get(i).getId()==tag){
						plist.get(i).setNum(ArithUtil.add(plist.get(i).getNum(),1));
						productTotalPrice= ArithUtil.mul(plist.get(i).getPrice(),plist.get(i).getNum());
					}
				}
				
				}
					String sql = "select * from product where  id='"+tag+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					if(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setType(rs.getString("type"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setUnit(rs.getString("unit"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setUnit(rs.getString("unit"));
						product.setNum(0);
						product.setFee(rs.getInt("fee"));
						product.setTotalNum(rs.getDouble("num"));
						imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
						product.setImageUrl(imageUrl);
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						if(plist!=null){
							int t=0;
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==tag){
									double totalNum = ArithUtil.add(product.getTotalNum(),plist.get(i).getOldNum());
									if(totalNum>=plist.get(i).getNum()){
											product.setNum(plist.get(i).getNum());
											num = plist.get(i).getNum();
									}else{
											product.setNum(totalNum);
											plist.get(i).setNum(totalNum);
											num =totalNum;
									}
										t++;
								}
							}
							if(t==0){
								if(product.getTotalNum()>0){
								product.setNum(1);
								plist.add(product);
								num =1;
								}
							}
						}else{
							if(product.getTotalNum()>0){
								product.setNum(1);
								num =1;
								plist =new ArrayList<Product>();
								plist.add(product);
							}
						}
					}
					conn.commit();
					 conn.setAutoCommit(autoCommit);
				}
			if(plist!=null){
				for(int i=0;i<plist.size();i++){
					
				total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
				
				}
			}
			object.put("num", StringUtil.decimalFormat(num));
			object.put("total_price", StringUtil.decimalFormat(total_price));
			object.put("product_total_price", StringUtil.decimalFormat(productTotalPrice));
			request.getSession().setAttribute("shop_cart_list", plist);
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
	
	public synchronized void disDe_ajax(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		
		try {
			double total_price=0;
			double num=0;
			Map<String, Object> object = new HashMap<String, Object>();
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String ids = StringUtil.notNull(request.getParameter("tag"));
				int tag=0;
				if(!StringUtil.notNull(ids).equals("")){
					tag  =Integer.valueOf(ids);
				}
				if(plist!=null){
				for(int i=0;i<plist.size();i++){
					if(plist.get(i).getId()==tag){
						if(plist.get(i).getNum()>0){
						plist.get(i).setNum(ArithUtil.sub(plist.get(i).getNum(),1));
						}
					}
				}
				}
				
					String sql = "select * from product where  id='"+tag+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
					if(rs.next()){
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setProductName(rs.getString("productName"));
						product.setFeatures(rs.getString("features"));
						product.setProductId(rs.getString("productId"));
						product.setdType(rs.getString("d_type"));
						product.setProductType(rs.getString("productType"));
						product.setSpecification(rs.getString("specification"));
						product.setUnit(rs.getString("unit"));
						product.setPrice(rs.getDouble("price"));
						product.setPv(rs.getDouble("pv"));
						product.setUnit(rs.getString("unit"));
						product.setNum(0);
						product.setFee(rs.getInt("fee"));
						product.setTotalNum(rs.getDouble("num"));
						imageUrl = StringUtil.notNull(rs.getString("imageUrl"));
						product.setImageUrl(imageUrl);
						product.setState(rs.getInt("state"));
						product.setEntryTime(rs.getTimestamp("entryTime"));
						product.setEndTime(rs.getTimestamp("endTime"));
						if(plist!=null){
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getId()==tag){
									product.setNum(plist.get(i).getNum());
									plist.get(i).setPrice(product.getPrice());
									num = plist.get(i).getNum();
								}
							}
						}
					}
					
					
					if(plist!=null){
						for(int i=0;i<plist.size();i++){
						total_price = ArithUtil.add(total_price,plist.get(i).getNum()*plist.get(i).getPrice());
						}
					}
					
					conn.commit();
					 conn.setAutoCommit(autoCommit);
				}
		
			object.put("num", num);
			object.put("total_price", StringUtil.decimalFormat(total_price));
			request.getSession().setAttribute("shop_cart_list", plist);
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
	
	
	
	public void increate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.get(tag).setNum(plist.get(tag).getNum()+1);
					}
					
						request.getSession().setAttribute("shop_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void disDe(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
						if(plist.get(tag).getNum()>0)
							plist.get(tag).setNum(plist.get(tag).getNum()-1);
					}
					
						request.getSession().setAttribute("shop_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del_pro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.remove(tag);
					}
						request.getSession().setAttribute("shop_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void shop_cart_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
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
				request.getSession().setAttribute("splist",slist);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart_save.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public synchronized void shop_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		Order order = (Order) request.getSession().getAttribute("sys_order");
		String message ="";
		String token_old = (String)request.getSession().getAttribute("token");
		token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		int type_tag = 0;
		try {
			boolean is_shop=cs.isShop();
			if(is_shop){
			if(StringUtil.notNull(token_old).equals(token)){
		if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String sqlw = "select * from branch where id='"+branch.getId()+"' for update";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sqlw);
				if(rs.next()){
					
						type_tag =rs.getInt("type");
					if(type_tag!=3){
					Address adr =new Address();
					adr.setId(rs.getInt("id"));
					adr.setPhone(rs.getString("tel"));
					adr.setReceiver(rs.getString("linkman"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("address"));
					double credit = rs.getDouble("credit");
					double creditUsed = rs.getDouble("credit_used");
				Timestamp date = new Timestamp(new Date().getTime());
				int oid =0;
				String orderId= "";
				double price_old = 0;
				int tag = 0;
				int b = 0;
				int state = 0;
				if(order==null){
					orderId =cs.createOrderId(date);
					b = 1; 
				}else{
					orderId =order.getOrderId();
					String sql_o = "select * from orders where orderId='"+orderId+"' for update";
					stmt =conn.createStatement();
					rs= stmt.executeQuery(sql_o);
					if(rs.next()){
						state = rs.getInt("state");
						price_old = rs.getDouble("price");
						tag = rs.getInt("modify_tag");
						if(order.getModifyTag()==tag) b=2;
						if(state>1) b=3;
					}
				}
				if(b==3){
					message="订单公司已经确认，不能进行修改操作。";
				}else if(b>0){
					double price = 0;
					double pv=0;
					String str="";
					List<String> slist = new ArrayList<String>();
					for(int i=0;i<plist.size();i++){
						price = price+plist.get(i).getPrice()*plist.get(i).getNum();
						pv = pv +plist.get(i).getPv()*plist.get(i).getNum();
						if(plist.get(i).getTag()==0){
							String sql1="insert into orderDetail(orderId,pid,productId,productName,specification,productType,unit,productPrice,num,price,image_url)"
									+ " values('"+orderId+"','"+plist.get(i).getId()+"','"+plist.get(i).getProductId()+"','"+plist.get(i).getProductName()+"','"+plist.get(i).getSpecification()+"','"+plist.get(i).getProductType()+"','"+plist.get(i).getUnit()
									+"','"+plist.get(i).getPrice()+"','"+plist.get(i).getNum()+"','"+plist.get(i).getPrice()*plist.get(i).getNum()+"','"+plist.get(i).getImageUrl()+"');";
							slist.add(sql1);
							String sql2 = "select * from product where id='"+plist.get(i).getId()+"' for update";
							stmt = conn.createStatement();
							rs = stmt.executeQuery(sql2);
							if(rs.next()){
								double num = rs.getDouble("num");
								if(ArithUtil.sub(plist.get(i).getNum(),num)>0)
									str = str+plist.get(i).getProductName()+"("+plist.get(i).getProductId()+"),";
								else{
									String sql3="update product set num='"+ArithUtil.sub(num,plist.get(i).getNum())
											+"' where id='"+plist.get(i).getId()+"'";
									slist.add(sql3);
								}
							}
						}else if(plist.get(i).getTag()==1){
							
							double new_num = 0;
							if(plist.get(i).getNum()>0){
									if(plist.get(i).getNum()!=plist.get(i).getOldNum()){
										String sql1="update orderDetail set price='"
												+plist.get(i).getPrice()*plist.get(i).getNum()
												+"',num='"+plist.get(i).getNum()
												+"' where orderId='"+orderId
												+"' and pid='"+plist.get(i).getId()+"'";
										slist.add(sql1);
										new_num = ArithUtil.sub(plist.get(i).getNum(),plist.get(i).getOldNum());
									
								}
							}else{
								String sql1="delete from  orderDetail  where orderId='"+orderId
										+"' and pid='"+plist.get(i).getId()+"'";
								slist.add(sql1);
								new_num = ArithUtil.sub(plist.get(i).getNum(),plist.get(i).getOldNum());
								
							}
							if(new_num!=0){
								String sql2 = "select * from product where id='"+plist.get(i).getId()+"' for update";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql2);
								if(rs.next()){
									double num = rs.getDouble("num");
									if(ArithUtil.sub(new_num,num)>0)
										str = str+plist.get(i).getProductName()+"("+plist.get(i).getProductId()+"),";
									else{
										String sql3="update product set num='"+ArithUtil.sub(num,new_num)
												+"' where id='"+plist.get(i).getId()+"'";
										slist.add(sql3);
									}
								}
							}
						}
					}
					if(str.equals("")){
					if(order==null){
						String sql2= "insert into orders(orderId,uid,userId,userName,receiver,phone,address,"
								+ "price,pv,orderType,orderTime,state) "
								+ " values('"+orderId+"','"+branch.getId()+"','"
								+branch.getCode()+"','"+branch.getName()+"','"+adr.getReceiver()+"','"+adr.getPhone()
								+"','"+(adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAdr())+"','"+price+"','"+pv+"','1','"+date+"','1');";
						slist.add(sql2);
					}else{
						tag=tag+1;
						String sql2 = "update orders set price='"+price+"',modify_tag='"+tag+"' where orderId='"+orderId+"'";
						slist.add(sql2);
					}
					double creditUsed_new = ArithUtil.add(creditUsed, ArithUtil.sub(price,price_old));
					if(ArithUtil.sub(credit, creditUsed_new)>=0){
						String sql3 = "update branch set credit_used='"+creditUsed_new+"' where id='"+branch.getId()+"'";
						slist.add(sql3);
						int payTag=0;
						double amount =ArithUtil.sub(price,price_old);
						if(ArithUtil.sub(price,price_old)<0){
							payTag = 2;
						}else payTag=1;
						String sql4 = cs.getCreditDetail(branch, Math.abs(amount), ArithUtil.sub(credit, creditUsed_new), payTag,orderId, date);
						slist.add(sql4);
						if(slist.size()>0){
							stmt = conn.createStatement();
								for(int i=0;i<slist.size();i++){
									stmt.addBatch(slist.get(i));
									 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
										 stmt.executeBatch();
										 stmt = conn.createStatement();
									 }
								}
							}
						message="订单保存成功，订单总金额为："+price+"元。";
						request.getSession().removeAttribute("shop_cart_list");
						request.getSession().removeAttribute("shop_cart_list_1");
						request.getSession().removeAttribute("sys_order");
					
				}else message = "当日订单总额已经超出范围，如有需要请与客服人员联系！";
				}else{
					str = str+"等库存不足，请重新选购。";
					message = str;
				}
				}else message = "订单信息获取失败或状态已发生修改！";
			}
				}else message = "会员信息获取失败！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
			
		}else{
			message="用户未登陆，请重新登陆！";
		}
		}else{
			 message= "请勿重复提交数据，请在产品列表中查看是否保存成功！";
	}
			}else{
				 message= "当前时间不能进行，购物时间段为每日0点到19点。";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			if(type_tag==3){
				RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("shop_save_msg.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
		 String filename = "产品列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<Product>  result = new ArrayList<Product> ();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String type = StringUtil.notNull(request.getParameter("type"));
		try {
			
			if(db.createConn()){
				String sql = "select * from product where productName like('%"+productName+"%') and  productType like('%"+typeName+"%') and  type like('%"+type+"%') and state>'0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("productName"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("productId"));
					product.setdType(rs.getString("d_type"));
					product.setProductType(rs.getString("productType"));
					product.setSpecification(rs.getString("specification"));
					product.setUnit(rs.getString("unit"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setType(rs.getString("type"));
					product.setNum(rs.getDouble("num"));
					product.setLimitNum(rs.getDouble("limit_num"));
					product.setUnit(rs.getString("unit"));
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getDouble("totalNum"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entryTime"));
					product.setEndTime(rs.getTimestamp("endTime"));
					product.setAttribute(rs.getString("attribute"));
					result.add(product);
				}
				String[][] content = new String[result.size()+1][12];
				content[0][0]="序号";
				content[0][1]="编号";
				content[0][2]="名称";
				content[0][3]="规格";
				content[0][4]="类别";
				content[0][5]="属性";
				content[0][6]="单位";
				content[0][7]="单价";
				content[0][8]="库存";
				content[0][9]="入库总数";
				content[0][10]="安全库存";
				content[0][11]="当前状态";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getProductId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getProductName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getSpecification());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getProductType());
					content[i+1][5]  = StringUtil.notNull(result.get(i).getAttribute());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getUnit());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+1][8] =String.valueOf(result.get(i).getNum());
					content[i+1][9] = String.valueOf(result.get(i).getTotalNum());
					content[i+1][10] = String.valueOf(result.get(i).getLimitNum());
					String stateStr ="";
					if(result.get(i).getState()==0) stateStr = "已删除";
					else if(result.get(i).getState()==1) stateStr = "热销中";
					else if(result.get(i).getState()==2) stateStr = "下架";
					content[i+1][11] = stateStr;
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("产品列表");  
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
	
}

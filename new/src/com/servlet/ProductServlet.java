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

import net.sf.json.JSONObject;

import com.db.DBConn;
import com.jspsmart.upload.SmartUploadException;
import com.pojo.Admin;
import com.pojo.Product;
import com.pojo.ProductDetail;
import com.pojo.ProductType;
import com.pojo.Team;
import com.pojo.User;
import com.utils.ArithUtil;
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
					try {
						save(request,response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}else if(method.equals("list")){
			list(request,response);
		}else if(method.equals("index")){
			index(request,response);
		}
		else if(method.equals("isExit")){
			isExit(request,response);
		}
		else if(method.equals("add")){
			
				add(request,response);
			
		}else if(method.equals("del")){
			del(request,response);
		}else if(method.equals("delAll")){
			delAll(request,response);
		}else if(method.equals("detail")){
			try {
				detail(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("product_detail")){
			
				product_detail(request,response);
			
		}else if(method.equals("edit")){
			try {
				edit(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("update")){
			try {
				update(request,response);
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
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		List  result = new ArrayList();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product where product_name like('%"+productName+"%') and  type like('%"+type+"%') and state!='0' order by id asc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				request.setAttribute("type", type);
				request.setAttribute("productName", productName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("product_name"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("product_id"));
					product.setSpecification(rs.getString("specification"));
					product.setPrice(rs.getDouble("price"));
					
					product.setPrice1(rs.getDouble("price1"));
					
					product.setPrice2(rs.getDouble("price2"));
					
					product.setPrice3(rs.getDouble("price3"));
					product.setType(rs.getInt("type"));
					product.setNum(rs.getInt("num"));
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setAward(rs.getDouble("award"));

					imageUrl = StringUtil.notNull(rs.getString("image_url"));
					product.setImageUrl(imageUrl);
					product.setState(rs.getString("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
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
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
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
		int pageSize = 10;
		try {
			if(user!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product where  state='1' order by id asc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				request.setAttribute("type", type);
				request.setAttribute("productName", productName);
				request.setAttribute("typeName", typeName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("product_name"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("product_id"));
					product.setSpecification(rs.getString("specification"));
					product.setPrice(rs.getDouble("price"));
					product.setPrice1(rs.getDouble("price1"));
					product.setPrice2(rs.getDouble("price2"));
					product.setPrice3(rs.getDouble("price3"));
					product.setType(rs.getInt("type"));
					product.setNum(rs.getInt("num"));
					product.setFee(rs.getInt("fee"));
					product.setTotalNum(rs.getInt("total_num"));
					imageUrl = StringUtil.notNull(rs.getString("image_url"));
					product.setImageUrl(imageUrl);
					product.setState(rs.getString("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
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
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
				}else{
					request.setAttribute("message", "会员用户未登陆，请重新登陆！");
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
			if(db.createConn()){
				if (StringUtil.notNull(productId).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product where product_id ='"+productId+"' order by id asc";
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_add.jsp");
			dispatcher.forward(request, response);
	
		}
	
	public void img_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
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
				pt.setProductId(rs.getString("product_id"));
				pt.setProductName(rs.getString("product_name"));
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
	public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		Collection coll_pt = new ArrayList();
		Collection coll_t = new ArrayList();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
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
				product.setProductName(rs.getString("product_name"));
				product.setFeatures(rs.getString("features"));
				product.setProductId(rs.getString("product_id"));
				product.setSpecification(rs.getString("specification"));
				product.setPrice(rs.getDouble("price"));
				product.setFee(rs.getDouble("fee"));
				product.setPrice1(rs.getDouble("price1"));
				product.setPrice2(rs.getDouble("price2"));
				product.setPrice3(rs.getDouble("price3"));
				product.setType(rs.getInt("type"));
				product.setNum(rs.getInt("num"));
				imageUrl = StringUtil.notNull(rs.getString("image_url"));
				if(!imageUrl.equals("")) imageUrl = "/upload/"+imageUrl;
				product.setImageUrl(imageUrl);
				product.setState(rs.getString("state"));
				product.setEntryTime(rs.getTimestamp("entry_time"));
				product.setEndTime(rs.getTimestamp("end_time"));
				request.setAttribute("product", product);
			
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
		if(rankstr[4][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
			String sql1 = "select * from product where id='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setFeatures(rs.getString("features"));
				product.setProductId(rs.getString("product_id"));
				product.setSpecification(rs.getString("specification"));
				product.setPrice(rs.getDouble("price"));
				product.setPrice1(rs.getDouble("price1"));
				product.setPrice2(rs.getDouble("price2"));
				product.setPrice3(rs.getDouble("price3"));
				product.setType(rs.getInt("type"));
				product.setAward(rs.getDouble("award"));
				product.setNum(rs.getInt("num"));
				imageUrl = StringUtil.notNull(rs.getString("image_url"));
				product.setImageUrl(imageUrl);
				product.setState(rs.getString("state"));
				product.setEntryTime(rs.getTimestamp("entry_time"));
				product.setEndTime(rs.getTimestamp("end_time"));
				request.setAttribute("product", product);
				
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
	
	public void product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message="";
		DBConn db = new DBConn();
		try {
		if(user!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
			String sql1 = "select * from product where product_id='"+id+"'";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setFeatures(rs.getString("features"));
				product.setProductId(rs.getString("product_id"));
				product.setSpecification(rs.getString("specification"));
				product.setPrice(rs.getDouble("price"));
				product.setPrice1(rs.getDouble("price1"));
				product.setPrice2(rs.getDouble("price2"));
				product.setPrice3(rs.getDouble("price3"));
				product.setType(rs.getInt("type"));
				product.setNum(rs.getInt("num"));
				product.setAward(rs.getDouble("award"));

				imageUrl = StringUtil.notNull(rs.getString("image_url"));
				product.setImageUrl(imageUrl);
				product.setState(rs.getString("state"));
				product.setEntryTime(rs.getTimestamp("entry_time"));
				product.setEndTime(rs.getTimestamp("end_time"));
				request.setAttribute("sys_product", product);
				
			}else{
				message="未获取相应的产品信息，请重试！";
			}
		}else {
			message="数据库连接已断开！";
		}	
		}else{
			message= "用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				db.close();
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		 int token_new = (int)(1+Math.random()*(1000000-1+1));
		 String token = StringUtil.notNull(request.getParameter("token"));
		 request.getSession().setAttribute("token", String.valueOf(token_new));
		 productId = request.getParameter("productId");
		productName = StringUtil.notNull(request.getParameter("productName"));
		specification = StringUtil.notNull(request.getParameter("specification"));
		features = StringUtil.notNull(request.getParameter("features"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String id = StringUtil.notNull(request.getParameter("id"));
		String price = StringUtil.notNull(request.getParameter("price"));
		String fee = StringUtil.notNull(request.getParameter("fee"));
		String price1 = StringUtil.notNull(request.getParameter("price1"));
		String award = StringUtil.notNull(request.getParameter("award"));
		 String message = "";
		 try {  
			 if(admin!=null){
				 if(StringUtil.notNull(token_old).equals(token)){
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if(rankstr[4][2].equals("1")||admin.getState().equals("1")){
			request.getSession().setAttribute("token", String.valueOf(token_new));
		 DBConn db = new DBConn();
		 if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			 String sql = "select * from product where id='"+id+"' for update";
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql);
			 if(rs.next()){
				 String sql1 = "update product set product_name='"+productName
				 +"',specification='"+specification+"',features='"+features+"',price='"+price
				 +"',pv='"+pv+"',price1='"+price1+"',award='"+award+"',type='"+type
				 +"',fee='"+fee+"'  where id='"+id+"'";
				
				 stmt=conn.createStatement();
				 stmt.executeUpdate(sql1);
			 message= "产品更新成功！";
			 }else{
				 message= "产品信息获取失败！";
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
				 message= "数据操作有误，请重试！";
			}finally{
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
		 try {  
			 if(admin!=null){
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
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
                   
                 System.out.println(filename);  
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
		 DBConn db = new DBConn();
		 if(StringUtil.notNull(token_old).equals(token)){
		 if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			 String sql = "update product set image_url='"+imgurl+"' where id='"+id+"'";
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
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
					dispatcher.forward(request, response);
				}
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, SmartUploadException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		Collection coll_pt = new ArrayList();
		imageUrl = "";
		token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		 productId = request.getParameter("productId");
		productName = StringUtil.notNull(request.getParameter("productName"));
		specification = StringUtil.notNull(request.getParameter("specification"));
		features = StringUtil.notNull(request.getParameter("features"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String price = StringUtil.notNull(request.getParameter("price"));
		String fee = StringUtil.notNull(request.getParameter("fee"));
		String price1 = StringUtil.notNull(request.getParameter("price1"));
		String award = StringUtil.notNull(request.getParameter("award"));
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][1].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					if(!(StringUtil.notNull(productId).equals(""))){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
			
			
					String sql = "select * from product where product_id ='"+productId+"'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 System.out.println(date);
					 String sqli = "insert into product(product_id,product_name,type,specification,features,price,price1,award,fee,image_url,"
					 		+ "state,entry_time,end_time) values('"+productId+"','"+productName+"','"+type+"','"+specification+"','"+features
					 		+"','"+price+"','"+price1+"','"+award+"','"+fee+"','"+imageUrl
					 +"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					
					 message="产品信息保存成功，请核对产品编号："+productId+"。";
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_add_select.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	public void del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][4].equals("1")||admin.getState().equals("1")){
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
					name = rs.getString("product_name");
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
	
	public void delAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[4][4].equals("1")||admin.getState().equals("1")){
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
			System.out.println(item.getFieldName()+":"+item.getName());
			//isFormField为true，表示这不是文件上传表单域
			if(!item.isFormField()){
				ServletContext sctx = getServletContext();
				//获得存放文件的物理路径
				//upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹
	
				String path = sctx.getRealPath("/upload");
				System.out.println(path);
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
		try {
			if(user!=null){
			
			if(db.createConn()){
				String sql = "select * from product where product_type like '%报单产品%' and state='1' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setProductName(rs.getString("product_name"));
					product.setFeatures(rs.getString("features"));
					product.setProductId(rs.getString("product_id"));
					product.setSpecification(rs.getString("specification"));
					product.setPrice(rs.getDouble("price"));
					product.setType(rs.getInt("type"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					imageUrl = StringUtil.notNull(rs.getString("image_url"));
					product.setImageUrl(imageUrl);
					product.setState(rs.getString("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					coll_1.add(product);
				}
				request.setAttribute("coll_1", coll_1);
				
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
	
}

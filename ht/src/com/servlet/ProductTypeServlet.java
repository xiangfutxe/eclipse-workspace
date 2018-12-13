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
import com.pojo.ProductAttribute;
import com.pojo.ProductAttributeClassify;
import com.pojo.ProductType;
import com.pojo.ProductTypeToAttribute;
import com.pojo.ProductTypeToAttributeValue;
import com.utils.Pager;
import com.utils.StringUtil;

public class ProductTypeServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
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
		}else if(method.equals("type_save")){
			try {
				type_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("type_list")){
			type_list(request,response);
		}
		else if(method.equals("type_isExit")){
			type_isExit(request,response);
		}
		else if(method.equals("type_add")){
			type_add(request,response);
		}else if(method.equals("type_del")){
			type_del(request,response);
		}else if(method.equals("attribute_save")){
			try {
				attribute_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("attribute_list")){
			attribute_list(request,response);
		}
		else if(method.equals("attribute_isExit")){
			attribute_isExit(request,response);
		}
		else if(method.equals("attribute_add")){
			attribute_add(request,response);
		}else if(method.equals("attribute_del")){
			attribute_del(request,response);
		}else if(method.equals("attribute_start")){
			try {
				attribute_start(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("attribute_close")){
			try {
				attribute_close(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("attribute_value_save")){
			try {
				attribute_value_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("attribute_value_list")){
			attribute_value_list(request,response);
		}
		else if(method.equals("attribute_value_isExit")){
			attribute_value_isExit(request,response);
		}
		else if(method.equals("attribute_value_add")){
			attribute_value_add(request,response);
		}else if(method.equals("attribute_value_del")){
			attribute_value_del(request,response);
		}else if(method.equals("type_to_attribute_list")){
			type_to_attribute_list(request,response);
		}
		else if(method.equals("type_to_attribute_isExit")){
			type_to_attribute_isExit(request,response);
		}
		else if(method.equals("type_to_attribute_add")){
			type_to_attribute_add(request,response);
		}else if(method.equals("type_to_attribute_del")){
			try {
				type_to_attribute_del(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("type_to_attribute_save")){
			try {
				type_to_attribute_save(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if(method.equals("type_to_attribute_value_list")){
			type_to_attribute_value_list(request,response);
		}else if(method.equals("type_to_attribute_value_add")){
			type_to_attribute_value_add(request,response);
		}else if(method.equals("type_to_attribute_value_save")){
			try {
				type_to_attribute_value_save(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void type_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select * from product_type where name like('%"+typeName+"%') and state!='0' order by id asc";
							request.setAttribute("typeName", typeName);
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					ProductType productType = new ProductType();
					productType.setId(rs.getInt("id"));
					productType.setName(rs.getString("name"));
					productType.setState(rs.getInt("state"));
					productType.setEntryTime(rs.getTimestamp("entry_time"));
					productType.setEndTime(rs.getTimestamp("end_time"));
					result.add(productType);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_type.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void type_isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String typeName = request.getParameter("typeName");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(typeName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product_type where name ='"+typeName+"' order by id asc";
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
	
	public void type_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		}
	
	public void type_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				conn = db.getConnection();
					String typeName = request.getParameter("typeName");
				if(!(StringUtil.notNull(typeName).equals(""))){
					String sql = "select * from product_type where name ='"+typeName+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					 conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 System.out.println(date);
					 String sqli = "insert into product_type(name,state,entry_time,end_time) values('"+typeName
					 +"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 conn.commit();
					 conn.setAutoCommit(autoCommit);
					 request.setAttribute("message", "产品分类信息保存成功，请核对产品分类名："+typeName+"。");
				}else{
					request.setAttribute("message", "产品分类名已被注册，请重新录入！");
				}
				}else {
					request.setAttribute("message", "产品分类名称不能为空，请重新录入！");
				}
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在产品分类列表中查看是否保存成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void type_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String idstr = request.getParameter("id");
				if(!StringUtil.notNull(idstr).equals("")){
						int id = Integer.valueOf(idstr);
				String sql = "select * from product_type where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt = conn.createStatement();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("typeName");
					String sqli = "update product_type set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品分类删除成功，删除产品分类为"+name+"！";
				}
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 
			}else{
				message="产品分类ID信息有误，请重新选择！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void attribute_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String attributeName = StringUtil.notNull(request.getParameter("attributeName"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select * from product_attribute where name like('%"+attributeName+"%') and state!='0' order by id asc";
							request.setAttribute("attributeName", attributeName);
						
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					ProductAttribute productType = new ProductAttribute();
					productType.setId(rs.getInt("id"));
					productType.setName(rs.getString("name"));
					productType.setState(rs.getInt("state"));
					productType.setIsClassify(rs.getInt("is_classify"));
					productType.setEntryTime(rs.getTimestamp("entry_time"));
					productType.setEndTime(rs.getTimestamp("end_time"));
					result.add(productType);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void attribute_isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String attributeName = request.getParameter("attributeName");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(attributeName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product_attribute where name ='"+attributeName+"' order by id asc";
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
	
	public void attribute_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		}
	
	public void attribute_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					String attributeName = request.getParameter("attributeName");
				if(!(StringUtil.notNull(attributeName).equals(""))){
					String sql = "select * from product_attribute where name ='"+attributeName+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
				
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 System.out.println(date);
					 String sqli = "insert into product_attribute(name,state,entry_time,end_time) values('"+attributeName
					 +"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					
					 request.setAttribute("message", "产品属性信息保存成功，请核对产品分类名："+attributeName+"。");
				}else{
					request.setAttribute("message", "产品属性名已被注册，请重新录入！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				}else {
					request.setAttribute("message", "产品属性名称不能为空，请重新录入！");
				}
				
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在产品分类列表中查看是否保存成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void attribute_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				String sql = "select * from product_attribute where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt = conn.createStatement();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("typeName");
					String sqli = "update product_attribute set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品属性删除成功，删除产品属性为"+name+"！";
				}
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 
			}else{
				message="产品属性ID信息有误，请重新选择！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	public void attribute_start(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		
		DBConn db = new DBConn();
		try {
			if(admin!=null){
			
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					String id = request.getParameter("id");
			
					String sql = "select * from product_attribute where id='"+id +"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
				if(rs.next()){
					int isClassify =rs.getInt("is_classify");
					String name = rs.getString("name");
					if(isClassify==0){
					 Timestamp date = new Timestamp(new Date().getTime());
					 String sqli = "update product_attribute set is_classify='1',end_time='"+date+"' where id='"+id+"'";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					
					 request.setAttribute("message", name+"产品属性可选值开启成功，请录入相应的属性值。");
					}else{
						request.setAttribute("message",  name+"产品属性可选值状态为可选，无需修改，请在设置选值中录入相应的属性值。");
					}
					
				}else {
					request.setAttribute("message", "产品属性值获取失败，请重试！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void attribute_close(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		
		DBConn db = new DBConn();
		try {
			if(admin!=null){
			
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					String id = request.getParameter("id");
			
					String sql = "select * from product_attribute where id='"+id +"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
				if(rs.next()){
					int isClassify =rs.getInt("is_classify");
					String name = rs.getString("name");
					if(isClassify==1){
					 Timestamp date = new Timestamp(new Date().getTime());
					 String sqli = "update product_attribute set is_classify='0',end_time='"+date+"' where id='"+id+"'";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					 request.setAttribute("message", name+"产品属性可选值关闭成功！");
					}else{
						request.setAttribute("message",  name+"产品属性可选值状态为关闭，请核对状态后再进行操作！");
					}
					
				}else {
					request.setAttribute("message", "产品属性值获取失败，请重试！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void attribute_value_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String attributeId = StringUtil.notNull(request.getParameter("id"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		request.getSession().setAttribute("attributeId", attributeId);
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select * from product_attribute_classify where attribute_id ='"+attributeId+"' and state!='0' order by id asc";
				
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					ProductAttributeClassify productType = new ProductAttributeClassify();
					productType.setId(rs.getInt("id"));
					productType.setAttributeId(rs.getInt("attribute_id"));
					productType.setValue(rs.getString("value"));
					productType.setState(rs.getInt("state"));
					productType.setEntryTime(rs.getTimestamp("entry_time"));
					productType.setEndTime(rs.getTimestamp("end_time"));
					result.add(productType);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_value.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void attribute_value_isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String valueName = request.getParameter("valueName");
		String attributeId = request.getParameter("attributeId");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(valueName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product_attribute_classify where attribute_id ='"+attributeId+"' and value ='"+valueName+"' order by id asc";
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
	
	public void attribute_value_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_value_add.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_value_message.jsp");
			dispatcher.forward(request, response);
		}
			
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_value_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void attribute_value_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
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
				if(rankstr[3][2].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
					String valueName = request.getParameter("valueName");
					String attributeId = (String)request.getSession().getAttribute("attributeId");
				if(!(StringUtil.notNull(valueName).equals(""))){
					String sql = "select * from product_attribute_classify where attribute_id='"+attributeId +"' and value='"+valueName+"'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 String sqli = "insert into product_attribute_classify(attribute_id,value,state,entry_time,end_time) values('"+attributeId+"','"+valueName
					 +"','1','"+date+"','"+date+"')";
					 stmt = conn.createStatement();
					 stmt.executeUpdate(sqli);
					
					 request.setAttribute("message", "产品属性值信息保存成功，请核对产品属性值："+valueName+"。");
				}else{
					request.setAttribute("message", "产品属性值已被注册，请重新录入！");
				}
				}else {
					request.setAttribute("message", "产品属性值不能为空，请重新录入！");
				}
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在产品分类列表中查看是否保存成功！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_value_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	public void attribute_value_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
				String sql = "select * from product_attribute_classify where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt = conn.createStatement();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("typeName");
					String sqli = "update product_attribute_classify set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品属性删除成功，删除产品属性为"+name+"！";
				}
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 
			}else{
				message="产品属性ID信息有误，请重新选择！";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_attribute_value_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void type_to_attribute_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String typeId = StringUtil.notNull(request.getParameter("id"));
		request.getSession().setAttribute("typeId", typeId);
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql = "select max(id) from product_attribute";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				int num = 0;
				if(rs.next()){
					num = rs.getInt(1);
				}
				ProductAttribute[] pa = new ProductAttribute[num+1];
				String sql1 = "select * from product_attribute";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					int id = rs.getInt("id");
					if(pa[id]==null) pa[id] = new ProductAttribute();
					pa[id].setId(rs.getInt("id"));
					pa[id].setName(rs.getString("name"));
					pa[id].setState(rs.getInt("state"));
					pa[id].setEntryTime(rs.getTimestamp("entry_time"));
					pa[id].setEndTime(rs.getTimestamp("end_time"));
				}
				String sql2 = "select * from product_type_to_attribute where type_id ='"+typeId+"' and state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql2);
				while(rs.next()){
					ProductTypeToAttribute pt = new ProductTypeToAttribute();
					pt.setId(rs.getInt("id"));
					pt.setAttributeId(rs.getInt("attribute_id"));
					pt.setTypeId(rs.getInt("type_id"));
					pt.setAttributeName(pa[pt.getAttributeId()].getName());
					pt.setState(rs.getInt("state"));
					pt.setEntryTime(rs.getTimestamp("entry_time"));
					pt.setEndTime(rs.getTimestamp("end_time"));
					coll.add(pt);
				}
				request.setAttribute("coll", coll);
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void type_to_attribute_isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String valueName = request.getParameter("valueName");
		String typeId = request.getParameter("typeId");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(valueName).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product_type_to_attribute where type_id ='"+typeId+"' and value ='"+valueName+"' order by id asc";
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
	public void type_to_attribute_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
		Collection coll = new ArrayList();
		try {
			if(db.createConn()){
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
		int token = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token));
		String typeId = (String)request.getSession().getAttribute("typeId");
		String sql2 = "select * from product_attribute where id not in"
				+ " (select id from product_type_to_attribute where type_id='"+typeId+"') order by id asc";
		stmt= db.getStmtread();
		rs =stmt.executeQuery(sql2);
		while(rs.next()){
			ProductAttribute pt = new ProductAttribute();
			pt.setId(rs.getInt("id"));
			pt.setName(rs.getString("name"));
			pt.setState(rs.getInt("state"));
			pt.setEntryTime(rs.getTimestamp("entry_time"));
			pt.setEndTime(rs.getTimestamp("end_time"));
			coll.add(pt);
		}
		request.setAttribute("coll", coll);
		}else{
			message="您没有该操作权限，如有需要请与系统管理员联系！";
		}
			
		}else{
			message="管理员用户未登陆，请重新登陆！";
			
		}}else{
			message="管理员用户未登陆，请重新登陆！";
			
		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.close();
				if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_add.jsp");
				dispatcher.forward(request, response);
				}else{
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	public void type_to_attribute_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		DBConn db = new DBConn();
		 List<String> slist = new ArrayList<String>();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				
				 conn.setAutoCommit(false);
				 Timestamp date = new Timestamp(new Date().getTime());
					String[] ids = request.getParameterValues("ids");
					String typeId= (String)request.getSession().getAttribute("typeId");
					for(int i=0;i<ids.length;i++){
						String sql = "select * from product_type_to_attribute where attribute_id='"+ids[i] +"' and state='1'";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql);
						if(!rs.next()){
							String sql1 = "select * from product_attribute where id='"+ids[i] +"' and state='1'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql1);
							if(rs.next()){
								String attributeName =rs.getString("name");
								 String sqli = "insert into product_type_to_attribute(type_id,attribute_id,attribute_name,state,entry_time,end_time) values('"+typeId+"','"+ids[i]
										 +"','"+attributeName+"','1','"+date+"','"+date+"')";
								 slist.add(sqli);
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
					
					 request.setAttribute("message", "分类属性信息保存成功。");
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	public void type_to_attribute_del(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message ="";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String idstr = request.getParameter("id");
				if(!StringUtil.notNull(idstr).equals("")){
						int id = Integer.valueOf(idstr);
				String sql = "select * from product_type_to_attribute where id ='"+id+"' for update";
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 stmt = conn.createStatement();
				 rs =stmt.executeQuery(sql);
				 String name = "";
				if(rs.next()){
					name = rs.getString("typeName");
					String sqli = "update product_type_to_attribute set state='0' where id ='"+id+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqli);
					message="产品属性删除成功，删除产品属性为"+name+"！";
				}
				
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
				 
			}else{
				message="产品属性ID信息有误，请重新选择！";
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
			conn.rollback();
			e.printStackTrace();
			message= "数据操作有误，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_message.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void type_to_attribute_value_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String attributeId = StringUtil.notNull(request.getParameter("id"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		request.getSession().setAttribute("typeAttributeId", attributeId);
		String message ="";
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String sql2 = "select * from product_attribute where id='"+attributeId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql2);
				if(rs.next()){
					int is_classify = rs.getInt("is_classify");
					if(is_classify==1){
				String sql = "select * from product_type_to_attribute_classify where attribute_id ='"+attributeId+"' and state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					ProductTypeToAttributeValue productType = new ProductTypeToAttributeValue();
					productType.setId(rs.getInt("id"));
					productType.setAttributeId(rs.getInt("attribute_id"));
					productType.setValue(rs.getString("value"));
					productType.setState(rs.getInt("state"));
					productType.setEntryTime(rs.getTimestamp("entry_time"));
					productType.setEndTime(rs.getTimestamp("end_time"));
					result.add(productType);
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
					}else{
						message="该属性信息不存在属性值！";
					}
				}else{
					message="未查询到相应的属性信息！";
				}
			}else {
				message="数据库连接已断开！";
			}
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
					
				}else{
					message="管理员用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_value.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_value_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void type_to_attribute_value_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message = "";
		Collection coll = new ArrayList();
		try {
		if(db.createConn()){
			if(admin!=null){
			String[][] rankstr = StringUtil.getRankStr(admin.getRank());
			if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
				String typeAttributeId = (String)request.getSession().getAttribute("typeAttributeId");
				String sql1 = "select * from product_type_to_attribute where attribute_id='"+typeAttributeId+"' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					int id = rs.getInt("id");

					String sql3 = "select * from product_attribute_classify where id not in"
							+ " (select value_id from product_type_to_attribute_classify where attribute_id='"+id+"' ) and attribute_id='"+typeAttributeId+"' and state!='0' order by id asc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql3);
					while(rs.next()){
						ProductAttributeClassify pt = new ProductAttributeClassify();
						pt.setId(rs.getInt("id"));
						pt.setAttributeId(rs.getInt("attribute_id"));
						pt.setValue(rs.getString("value"));
						pt.setState(rs.getInt("state"));
						pt.setEntryTime(rs.getTimestamp("entry_time"));
						pt.setEndTime(rs.getTimestamp("end_time"));
						coll.add(pt);
					}
					request.setAttribute("coll", coll);
					
				}else{
					message="分类属性信息获取失败！";
				}
			}else{
				message="您没有该操作权限，如有需要请与系统管理员联系！";
			}
			}else{
				message="管理员用户未登陆，请重新登陆！";
				
			}
		
		}else{
			message="数据库连接失败，请重新登陆！";
			
		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.close();
				if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_value_add.jsp");
				dispatcher.forward(request, response);
				}else{
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_value_add_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	public void type_to_attribute_value_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token= (String)request.getParameter("token");
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		DBConn db = new DBConn();
		 List<String> slist = new ArrayList<String>();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][1].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 Timestamp date = new Timestamp(new Date().getTime());
					String[] ids = request.getParameterValues("ids");
					String attributeId= (String)request.getSession().getAttribute("typeAttributeId");
					for(int i=0;i<ids.length;i++){
						String sql = "select * from product_attribute_classify where id='"+ids[i] +"' and state='1'";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql);
						if(rs.next()){
							String value_name =rs.getString("value");
							 String sqli = "insert into product_type_to_attribute_classify(attribute_id,value_id,value,state,entry_time,end_time) values('"+attributeId+"','"+ids[i]
									 +"','"+value_name+"','1','"+date+"','"+date+"')";
							 slist.add(sqli);
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
					
					 request.setAttribute("message", "分类属性值信息保存成功。");
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据！");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_type_to_attribute_value_add_message.jsp");
			dispatcher.forward(request, response);
		}
	}
}

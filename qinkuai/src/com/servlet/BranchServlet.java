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
import com.pojo.Branch;
import com.pojo.News;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.DateUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class BranchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	

	ICustomService cs = new CustomService();
	IUserService us = new UserService();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		}else if(method.equals("login")){
			login(request,response);
			
		}else if(method.equals("logout")){
			logout(request,response);
			
		}else if(method.equals("index")){
			index(request,response);
			
		}else if(method.equals("list")){
			list(request,response);
		}else if(method.equals("credit")){
			credit(request,response);
		}else if(method.equals("detail")){
			detail(request,response);
			
		}else if(method.equals("branch_detail")){
			branch_detail(request,response);
			
		}else if(method.equals("branch_map")){
			branch_map(request,response);
			
		}else if(method.equals("admin_list")){
			admin_list(request,response);
			
		}else if(method.equals("admin_lock_list")){
			admin_lock_list(request,response);
			
		}else if(method.equals("admin_add")){
			admin_add(request,response);
			
		}else if(method.equals("admin_save")){
			try {
				admin_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(method.equals("admin_edit")){
			admin_edit(request,response);
			
		}else if(method.equals("admin_up")){
			try {
				admin_up(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}else if(method.equals("admin_down")){
			try {
				admin_down(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}else if(method.equals("admin_edit_save")){
			try {
				admin_edit_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(method.equals("admin_credit_add")){
			admin_credit_add(request,response);
			
		}else if(method.equals("admin_credit_save")){
			try {
				admin_credit_save(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if (method.equals("admin_lock")) {
			try {
				admin_lock(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (method.equals("admin_unlock")) {
			try {
				admin_unlock(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (method.equals("admin_psw1_init")) {
			try {
				admin_psw1_init(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (method.equals("admin_psw2_init")) {
			try {
				admin_psw2_init(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (method.equals("admin_delAll")) {
				delAll(request, response);
			
		}else if(method.equals("codeAjax")){
			codeAjax(request,response);
			
		}else if(method.equals("codeEditAjax")){
			codeEditAjax(request,response);
			
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
			
		}else if(method.equals("psw_edit")){
			psw_edit(request,response);
			
		}else if(method.equals("psw_update")){
			try {
				psw_update(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}

}
	
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		List<Branch> blist = new ArrayList<Branch>();
		try {
			if(db.createConn()){
				conn = db.getConnection();
				String sql =" select count(*),province,city from branch  where state='1' group by province,city";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setTag(rs.getInt(1));
					as.setProvince(rs.getString(2));
					as.setCity(rs.getString(3));
					blist.add(as);
					}
				request.setAttribute("branch_list", blist);
			}else {
				message= "数据获取失败，请与客服联系！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void branck_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		List<Branch> blist = new ArrayList<Branch>();
		try {
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from branch where state='1'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setImageUrl(rs.getString("image_url"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					blist.add(as);
					}
				request.setAttribute("branch_list", blist);
			}else {
				message= "数据获取失败，请与客服联系！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		String name = StringUtil.notNull(request.getParameter("name"));
		String psw = StringUtil.notNull(request.getParameter("psd"));
		List<News>  result = new ArrayList<News>();

		try {
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from branch where code='"+name+"' and  psw='"+MD5.GetMD5Code(psw)+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					int  state = rs.getInt("state");
					if(state==1){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setIsShop(rs.getInt("is_shop"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					request.getSession().setAttribute("sys_branch", as);
					String sql1 ="select * from news where state='1' order by id desc limit 5";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						News ns = new News();
						ns.setId(rs.getInt("id"));
						ns.setAdmin(rs.getString("admin"));
						ns.setTitle(rs.getString("title"));
						ns.setContents(rs.getString("contents"));
						ns.setSort(rs.getString("sort"));
						ns.setEndTime(rs.getTimestamp("endTime"));
						ns.setEntryTime(rs.getTimestamp("entryTime"));
						ns.setState(rs.getInt("state"));
						result.add(ns);
					}
					request.setAttribute("sys_news", result);
					}else if(state==2){
						message= "账户已冻结，请与客服联系！";
					}else{
						message= "编号不存在或密码不正确！";
					}
				}else{
					message= "编号不存在或密码不正确！";
				}
			}else {
				message= "数据获取失败，请与客服联系！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		List<News>  result = new ArrayList<News>();
		try {
			if(db.createConn()){
				conn = db.getConnection();
					String sql1 ="select * from news where state='1' order by id desc limit 5";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						News ns = new News();
						ns.setId(rs.getInt("id"));
						ns.setAdmin(rs.getString("admin"));
						ns.setTitle(rs.getString("title"));
						ns.setContents(rs.getString("contents"));
						ns.setSort(rs.getString("sort"));
						ns.setEndTime(rs.getTimestamp("endTime"));
						ns.setEntryTime(rs.getTimestamp("entryTime"));
						ns.setState(rs.getInt("state"));
						result.add(ns);
					}
					request.setAttribute("sys_news", result);
			}else {
				message= "数据获取失败，请与客服联系！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		List<News>  result = new ArrayList<News>();
		try {
			if(db.createConn()){
				conn = db.getConnection();
					String sql1 ="select * from news where state='1' order by id desc limit 5";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						News ns = new News();
						ns.setId(rs.getInt("id"));
						ns.setAdmin(rs.getString("admin"));
						ns.setTitle(rs.getString("title"));
						ns.setContents(rs.getString("contents"));
						ns.setSort(rs.getString("sort"));
						ns.setEndTime(rs.getTimestamp("endTime"));
						ns.setEntryTime(rs.getTimestamp("entryTime"));
						ns.setState(rs.getInt("state"));
						result.add(ns);
					}
					request.setAttribute("sys_news", result);
					request.getSession().removeAttribute("sys_branch");
			}else {
				message= "数据获取失败，请与客服联系！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void credit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch =(Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message ="";
		
		try {
			if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from branch where code='"+branch.getCode()+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					request.getSession().setAttribute("sys_branch", as);
					}else{
						message= "店铺信息获取失败，请重新登陆！";
					}
			}else {
				message= "数据获取失败，请与客服联系！";
			}
			}else{
				message= "店铺信息获取失败，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("credit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch =(Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message ="";
		
		try {
			if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from branch where code='"+branch.getCode()+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					request.getSession().setAttribute("sys_branch", as);
					}else{
						message= "店铺信息获取失败，请重新登陆！";
					}
			}else {
				message= "数据获取失败，请与客服联系！";
			}
			}else{
				message= "店铺信息获取失败，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_detail.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public  void branch_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		String province = StringUtil.notNull((String) request.getParameter("province"));
		String city = StringUtil.notNull((String) request.getParameter("city"));
		try {
			if(db.createConn()){
				conn = db.getConnection();
				int num=0;
				List<Branch> blist = new ArrayList<Branch>();
				String sql ="select * from branch where province='"+province+"' and city='"+city+"' and state='1'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					blist.add(as);
					num++;
					}
				Branch bra = new Branch();
				bra.setTag(num);
				bra.setProvince(province);
				bra.setCity(city);
				request.setAttribute("branch", bra);
				request.setAttribute("branch_list", blist);
			}else {
				message= "数据获取失败，请与客服联系！";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
				request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_one.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public  void branch_map(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String message ="";
		String code = StringUtil.notNull((String) request.getParameter("code"));
		try {
			if(db.createConn()){
				conn = db.getConnection();
				Branch as = new Branch();
				String sql ="select * from branch where code='"+code+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					}else as = null;
				
				request.setAttribute("branch", as);
			}else {
				message= "数据获取失败，请与客服联系！";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
				request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_map.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public synchronized void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch =(Branch)request.getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message ="";
		try {
			if(branch!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="select * from branch where code='"+branch.getCode()+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setTag(rs.getInt("tag"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					request.getSession().setAttribute("sys_branch", as);
					}else{
						message= "店铺信息获取失败，请重新登陆！";
					}
			}else {
				message= "数据获取失败，请与客服联系！";
			}
			}else{
				message= "店铺信息获取失败，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_detail.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String code = StringUtil.notNull(request.getParameter("code"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				boolean autoCommit= conn.getAutoCommit();
				conn.setAutoCommit(false);
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from branch where entry_time>='"+startTime+"' and entry_time<='"+endTime+"'  and code like'%"+code+"%' and state='1' order by asc_num asc";
						
					}else{
						sql ="select * from branch where code like'%"+code+"%' and state='1' order by asc_num asc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setAscNum(rs.getInt("asc_num"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setType(rs.getInt("type"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					result.add(as);
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
				request.setAttribute("branch_code",code);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized void admin_lock_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String message ="";
		String code = StringUtil.notNull(request.getParameter("code"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 60;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][8].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				boolean autoCommit= conn.getAutoCommit();
				conn.setAutoCommit(false);
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from branch where entry_time>='"+startTime+"' and entry_time<='"+endTime+"'  and code like'%"+code+"%' and state='2' order by asc_num asc";
						
					}else{
						sql ="select * from branch where code like'%"+code+"%' and state='2' order by asc_num asc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setAscNum(rs.getInt("asc_num"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setType(rs.getInt("type"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setEmoney(rs.getDouble("emoney"));
					as.setType(rs.getInt("type"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					result.add(as);
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
				request.setAttribute("branch_code",code);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_lock_list.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_lock_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		try {
		if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][1].equals("1")||admin.getState().equals("1")){
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					
				}else{
					message= "您没有该操作权限，如有需要请与系统管理员联系！";
				}
				
			}else{
				message= "管理员用户未登陆，请重新登陆！";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_add.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public synchronized void admin_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][1].equals("1")||admin.getState().equals("1")){
					String code = StringUtil.notNull(request.getParameter("code"));
					String name  = StringUtil.notNull(request.getParameter("name"));
					String linkman  = StringUtil.notNull(request.getParameter("linkman"));
					String psw  = StringUtil.notNull(request.getParameter("psw"));
					String tel  = StringUtil.notNull(request.getParameter("tel"));
					String type  = StringUtil.notNull(request.getParameter("type"));
					String phone  = StringUtil.notNull(request.getParameter("phone"));
					String province  = StringUtil.notNull(request.getParameter("province"));
					String city  = StringUtil.notNull(request.getParameter("city"));
					String area  = StringUtil.notNull(request.getParameter("area"));
					String address  = StringUtil.notNull(request.getParameter("address"));
					String credit  = StringUtil.notNull(request.getParameter("credit"));
					String advancePayment  = StringUtil.notNull(request.getParameter("advancePayment"));
					String standardMin  = StringUtil.notNull(request.getParameter("standardMin"));
					Timestamp date = new Timestamp(new Date().getTime());
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 int max = 0;
				 String sql = "select max(asc_num) from branch";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql);
				 if(rs.next()){
					 max = rs.getInt(1);
				 }
				 max= max+1;
				 String sql1 = "select * from branch where code='"+code+"'";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql1);
				if(!rs.next()){
					String sql2 = "insert into branch(code,name,type,linkman,psw,psw2,tel,asc_num,phone,credit,credit_init,emoney,standard_min,province,city,area,address,state,entry_time,end_time)"
							+ "values('"+code+"','"+name+"','"+type+"','"+linkman+"','"+MD5.GetMD5Code(psw)
							+"','"+MD5.GetMD5Code(psw)+"','"+tel+"','"+(max+1)+"','"+phone+"','"+credit+"','"+credit+"','"+advancePayment+"','"+standardMin
							+"','"+province+"','"+city+"','"+area+"','"+address+"','1','"+date+"','"+date+"');";
					stmt= conn.createStatement();
					stmt.executeUpdate(sql2);
					message ="分店信息保存成功,店铺编号为："+code+",店铺登陆密码和支付密码为："+psw+"。";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					 }else{
						 message= "该编号已存在，请重新输入编号！";
				 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message="数据库连接已断开！";
			}
				
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数据，请在增扣明细中查看是否操作成功！");
			}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public synchronized void admin_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
		if(admin!=null){
			if (db.createConn()) {
				conn = db.getConnection();
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][2].equals("1")||admin.getState().equals("1")){
					String code = StringUtil.notNull(request.getParameter("code"));
					String sql = "select * from branch where code='"+code+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						int token = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token));
						Branch as = new Branch();
						as.setId(rs.getInt("id"));
						as.setCode(rs.getString("code"));
						as.setName(rs.getString("name"));
						as.setLinkman(rs.getString("linkman"));
						as.setPsw(rs.getString("psw"));
						as.setPsw2(rs.getString("psw2"));
						as.setTel(rs.getString("tel"));
						as.setPhone(rs.getString("phone"));
						as.setProvince(rs.getString("province"));
						as.setCity(rs.getString("city"));
						as.setArea(rs.getString("area"));
						as.setCredit(rs.getDouble("credit"));
						as.setCreditUsed(rs.getDouble("credit_used"));
						as.setStandardMin(rs.getDouble("standard_min"));
						as.setStandardUsed(rs.getDouble("standard_used"));
						as.setEmoney(rs.getDouble("emoney"));
						as.setType(rs.getInt("type"));
						as.setAddress(rs.getString("address"));
						as.setState(rs.getInt("state"));
						as.setEndTime(rs.getTimestamp("end_time"));
						as.setEntryTime(rs.getTimestamp("entry_time"));
						request.setAttribute("admin_branch", as);
					}else{
						message= "分店信息获取失败！";
					}
					
				}else{
					message= "您没有该操作权限！";
				}
			}else{
				message= "数据库链接失败！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public synchronized void admin_edit_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
	
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][2].equals("1")||admin.getState().equals("1")){
					String id = StringUtil.notNull(request.getParameter("id"));
					String code = StringUtil.notNull(request.getParameter("code"));
					String name  = StringUtil.notNull(request.getParameter("name"));
					String linkman  = StringUtil.notNull(request.getParameter("linkman"));
					String tel  = StringUtil.notNull(request.getParameter("tel"));
					String phone  = StringUtil.notNull(request.getParameter("phone"));
					String type  = StringUtil.notNull(request.getParameter("type"));
					String province  = StringUtil.notNull(request.getParameter("province"));
					String city  = StringUtil.notNull(request.getParameter("city"));
					String area  = StringUtil.notNull(request.getParameter("area"));
					String address  = StringUtil.notNull(request.getParameter("address"));
					Timestamp date = new Timestamp(new Date().getTime());
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 String sql = "select * from branch where id='"+id+"' for update";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql);
				if(rs.next()){
					String sql1 = "update branch set code='"+code
							+"',name='"+name
							+"',linkman='"+linkman
							+"',tel='"+tel
							+"',phone='"+phone
							+"',type='"+type
							+"',province='"+province
							+"',city='"+city
							+"',area='"+area
							+"',address='"+address
							+"',end_time='"+date
							+"' where id='"+id+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sql1);
					message =code+"分店基本资料修改成功。";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					 }else{
						 message= "分店信息获取失败！";
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message="数据库连接已断开！";
			}
				
				}else{
				message="您没有该操作权限，如有需要请与系统管理员联系！";
			}
				}else{
			  message="请勿重复提交数据，请在增扣明细中查看是否操作成功！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public synchronized void admin_up(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		
		String message = "";
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		int pageNo = 1;
		int pageSize = 60;
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][2].equals("1")||admin.getState().equals("1")){
					String code = StringUtil.notNull(request.getParameter("code"));
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 Timestamp date = new Timestamp(new Date().getTime());
				 String sql = "select * from branch where code='"+code+"' for update";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql);
				if(rs.next()){
					int ascNum = rs.getInt("asc_num");
					 String sql1 = "select * from branch where asc_num<'"+ascNum+"' and state='1' order by asc_num desc for update";
					 stmt = conn.createStatement();
					 rs= stmt.executeQuery(sql1);
					 if(rs.next()){
						 int ascNum_1 = rs.getInt("asc_num");
						 String code_1 = rs.getString("code");
						 String sql2 = "update branch set asc_num='"+ascNum_1+"',end_time='"+date+"' where code='"+code+"'";
							stmt= conn.createStatement();
							stmt.executeUpdate(sql2);
						 String sql3 = "update branch set asc_num='"+ascNum+"',end_time='"+date+"' where code='"+code_1+"'";
							stmt= conn.createStatement();
							stmt.executeUpdate(sql3);
							String sql4 ="select * from branch where  state='1' order by asc_num asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql4);
							while(rs.next()){
								Branch as = new Branch();
								as.setId(rs.getInt("id"));
								as.setAscNum(rs.getInt("asc_num"));
								as.setCode(rs.getString("code"));
								as.setName(rs.getString("name"));
								as.setLinkman(rs.getString("linkman"));
								as.setPsw(rs.getString("psw"));
								as.setPsw2(rs.getString("psw2"));
								as.setTel(rs.getString("tel"));
								as.setPhone(rs.getString("phone"));
								as.setProvince(rs.getString("province"));
								as.setCity(rs.getString("city"));
								as.setArea(rs.getString("area"));
								as.setCredit(rs.getDouble("credit"));
								as.setCreditUsed(rs.getDouble("credit_used"));
								as.setStandardMin(rs.getDouble("standard_min"));
								as.setStandardUsed(rs.getDouble("standard_used"));
								as.setAddress(rs.getString("address"));
								as.setState(rs.getInt("state"));
								as.setEndTime(rs.getTimestamp("end_time"));
								as.setEntryTime(rs.getTimestamp("entry_time"));
								result.add(as);
							}
							if(result.size()>0){
								int startIndex = pageSize*(pageNo-1);
								int endIndex = pageSize*pageNo;
								if(result.size()<endIndex) endIndex = result.size();
								coll = result.subList(startIndex, endIndex);
								Pager pager = new Pager(pageSize,pageNo,result.size(),coll);
								request.setAttribute("pager", pager);
							}
					 }else{
						 message = "当前排序号已为最前，不能继续上移。";
					 }
					
					 }else{
						 message= "分店信息获取失败！";
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
			conn.rollback();
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			db.close();
			if(message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_list.jsp");
				dispatcher.forward(request, response);
			}else{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	public synchronized void admin_down(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message = "";
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		int pageNo = 1;
		int pageSize = 60;
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][2].equals("1")||admin.getState().equals("1")){
					String code = StringUtil.notNull(request.getParameter("code"));
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 Timestamp date = new Timestamp(new Date().getTime());
				 String sql = "select * from branch where code='"+code+"' for update";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql);
				if(rs.next()){
					int ascNum = rs.getInt("asc_num");
					 String sql1 = "select * from branch where asc_num>'"+ascNum+"'  and state='1' order by asc_num asc for update";
					 stmt = conn.createStatement();
					 rs= stmt.executeQuery(sql1);
					 if(rs.next()){
						 int ascNum_1 = rs.getInt("asc_num");
						 String code_1 = rs.getString("code");
						 String sql2 = "update branch set asc_num='"+ascNum_1+"' where code='"+code+"'";
							stmt= conn.createStatement();
							stmt.executeUpdate(sql2);
						 String sql3 = "update branch set asc_num='"+ascNum+"' where code='"+code_1+"'";
							stmt= conn.createStatement();
							stmt.executeUpdate(sql3);
							String sql4 ="select * from branch where  state='1' order by asc_num asc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql4);
							while(rs.next()){
								Branch as = new Branch();
								as.setId(rs.getInt("id"));
								as.setAscNum(rs.getInt("asc_num"));
								as.setCode(rs.getString("code"));
								as.setName(rs.getString("name"));
								as.setLinkman(rs.getString("linkman"));
								as.setPsw(rs.getString("psw"));
								as.setPsw2(rs.getString("psw2"));
								as.setTel(rs.getString("tel"));
								as.setPhone(rs.getString("phone"));
								as.setProvince(rs.getString("province"));
								as.setCity(rs.getString("city"));
								as.setArea(rs.getString("area"));
								as.setCredit(rs.getDouble("credit"));
								as.setCreditUsed(rs.getDouble("credit_used"));
								as.setStandardMin(rs.getDouble("standard_min"));
								as.setStandardUsed(rs.getDouble("standard_used"));
								as.setAddress(rs.getString("address"));
								as.setState(rs.getInt("state"));
								as.setEndTime(rs.getTimestamp("end_time"));
								as.setEntryTime(rs.getTimestamp("entry_time"));
								result.add(as);
							}
							if(result.size()>0){
								int startIndex = pageSize*(pageNo-1);
								int endIndex = pageSize*pageNo;
								if(result.size()<endIndex) endIndex = result.size();
								coll = result.subList(startIndex, endIndex);
								Pager pager = new Pager(pageSize,pageNo,result.size(),coll);
								request.setAttribute("pager", pager);
							}
					 }else{
						 message = "当前排序号已为最后，不能继续下移。";
					 }
					
					 }else{
						 message= "分店信息获取失败！";
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
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
			conn.rollback();
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			db.close();
			if(message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_list.jsp");
				dispatcher.forward(request, response);
			}else{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	public synchronized void admin_credit_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message="";
		try {
		if(admin!=null){
			if (db.createConn()) {
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][4].equals("1")||admin.getState().equals("1")){
					String code = StringUtil.notNull(request.getParameter("code"));
					String sql = "select * from branch where id='"+code+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						int token = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token));
						Branch branch = new Branch();
						branch.setId(rs.getInt("id"));
						branch.setCode(rs.getString("code"));
						branch.setName(rs.getString("name"));
						branch.setCredit(rs.getDouble("credit"));
						request.setAttribute("admin_branch", branch);
					}else{
						message= "分店信息获取失败！";
					}
				}else{
					message= "您没有该操作权限！";
				}

				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message= "数据库链接失败！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_credit.jsp");
			dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public synchronized void admin_credit_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[1][4].equals("1")||admin.getState().equals("1")){
					String code = StringUtil.notNull(request.getParameter("code"));
					String credit  = StringUtil.notNull(request.getParameter("credit"));
					Timestamp date = new Timestamp(new Date().getTime());
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 String sql = "select * from branch where id='"+code+"' for update";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql);
				if(rs.next()){
					String sql1 = "update branch set credit='"+credit+"',credit_init='"+credit+"',end_time='"+date+"' where id='"+code+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sql1);
					message =code+"分店授信额度调整成功,最新额度为为："+credit+"元。";
					cs.insetAdminLog(conn, admin.getAdminName(), message, date);
					 }else{
						 message= "分店信息获取失败！";
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message="数据库连接已断开！";
			}
				
				}else{
				request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
			}
				}else{
			request.setAttribute("message", "请勿重复提交数！");
			}
			}else{
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public synchronized void admin_lock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][8].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							List<String> slist = new ArrayList<String>();
							Timestamp date = new Timestamp(new Date().getTime());
							for (int i = 0; i < ids.length; i++) {
								String sql = "update branch set state='2',end_time='"+date+"' where id='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt.close();
										stmt = conn.createStatement();
									}
								}
							}
						
							request.setAttribute("message",
									"分店锁定成功，锁定用户将无法登陆会员系统！");
							cs.insetAdminLog(conn, admin.getAdminName(), "分店锁定成功，锁定用户将无法登陆会员系统！", date);
							conn.commit();
							conn.setAutoCommit(autoCommit);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得分店信息，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_unlock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][8].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							List<String> slist = new ArrayList<String>();
							Timestamp date = new Timestamp(
									new Date().getTime());
							for (int i = 0; i < ids.length; i++) {
							
										String sql = "update branch set state='1', end_time='"+date+"' where id='" + ids[i] + "'";
										slist.add(sql);
								}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt.close();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							request.setAttribute("message",
									"分店解除锁定成功，请及时通知用户！");
							cs.insetAdminLog(conn, admin.getAdminName(), "分店解除锁定成功，请及时通知会员！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_psw1_init(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][7].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							Timestamp date = new Timestamp(new Date().getTime());
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update branch set psw='"
										+ MD5.GetMD5Code("12345678")
										+ "',end_time='"+date+"' where id='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);

							request.setAttribute("message",
									"分店登陆密码重置成功，请及时通知会员修改密码！");
							
							cs.insetAdminLog(conn, admin.getAdminName(), "分店登陆密码重置成功，请及时通知会员修改密码！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得会员相应的ID信息，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void admin_psw2_init(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][7].equals("1") || admin.getState().equals("1")) {
					String[] ids = request.getParameterValues("ids");
					if (ids.length > 0) {
						if (db.createConn()) {
							conn = db.getConnection();
							boolean autoCommit = conn.getAutoCommit();
							conn.setAutoCommit(false);
							Timestamp date = new Timestamp(new Date().getTime());
							List<String> slist = new ArrayList<String>();
							for (int i = 0; i < ids.length; i++) {
								String sql = "update branch set psw2='"
										+ MD5.GetMD5Code("12345678")
										+ "',end_time='"+date+"' where id='" + ids[i] + "'";
								slist.add(sql);
							}
							if (slist.size() > 0) {
								stmt = conn.createStatement();
								for (int i = 0; i < slist.size(); i++) {
									stmt.addBatch(slist.get(i));
									if ((i % 50000 == 0 && i != 0)
											|| i == (slist.size() - 1)) {
										stmt.executeBatch();
										stmt = conn.createStatement();
									}
								}
							}
							conn.commit();
							conn.setAutoCommit(autoCommit);
							request.setAttribute("message",
									"分店支付密码重置成功，请及时通知会员修改密码！");
							
							cs.insetAdminLog(conn, admin.getAdminName(), "分店登陆密码重置成功，请及时通知会员修改密码！", date);
						} else {
							request.setAttribute("message", "数据库连接已断开！");
						}
					} else {
						request.setAttribute("message", "未获得需要重置密码的用户，请重试！");
					}

				} else {
					request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
				}
			} else {
				request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
		
			e.printStackTrace();
			request.setAttribute("message", "数据保存有误，请重新录入！");
		} finally {
			db.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		DBConn db = new DBConn();
		try {
			if (admin != null) {
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if (rankstr[1][3].equals("1") || admin.getState().equals("1")) {
				if (db.createConn()) {
					conn = db.getConnection();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					String[] ids = request.getParameterValues("ids");
					Timestamp date = new Timestamp(new Date().getTime());
					List<String> slist = new ArrayList<String>();
					for (int i = 0; i < ids.length; i++) {
						String sql = "update branch set state='0',end_time='"+date+"' where id='" + ids[i] + "'";
						slist.add(sql);
					}
						if (slist.size() > 0) {
							stmt = conn.createStatement();
							for (int i = 0; i < slist.size(); i++) {
								stmt.addBatch(slist.get(i));
								if ((i % 50000 == 0 && i != 0)
										|| i == (slist.size() - 1)) {
									stmt.executeBatch();
									stmt = conn.createStatement();
								}
							}
						}
						
						conn.commit();
						conn.setAutoCommit(autoCommit);
						message = "批量删除成功！";
						cs.insetAdminLog(conn, admin.getAdminName(), message, date);
				} else {
					message = "数据库连接已断开！";
				}
				} else {
					request.setAttribute("message", "您没有该操作权限！");
				}
			} else {
				message = "管理员用户未登陆，请重新登陆！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "数据操作有误，请重试！";
		} finally {
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("branch_message.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void codeAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String code = StringUtil.notNull(request.getParameter("code"));
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		try {
			Map<String, Object> object = new HashMap<String, Object>();
			if (admin != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					if (StringUtil.notNull(code).equals("")) {
						object.put("tag", "2");
						object.put("userName","");
					} else {
						String sql = "";
						sql = "select * from branch where code ='" + code
								+ "' order by id asc";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
								object.put("tag", "1");
								object.put("userName", rs.getString("name"));
						} else{
							object.put("tag", "0");
							object.put("userName","");

						}
					} 
				}else {
					object.put("tag", "3");
					object.put("userName","");

				}
			}else {
				object.put("tag", "4");
				object.put("userName","");

			}
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	public void codeEditAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBConn db = new DBConn();
		String code = StringUtil.notNull(request.getParameter("code"));
		String id = StringUtil.notNull(request.getParameter("id"));
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		try {
			Map<String, Object> object = new HashMap<String, Object>();
			if (admin != null) {
				if (db.createConn()) {
					conn = db.getConnection();
					if (StringUtil.notNull(code).equals("")) {
						object.put("tag", "2");
					} else {
						String sql = "";
						sql = "select * from branch where id ='" + id
								+ "' order by id asc";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							String old_code = rs.getString("code");
							if(old_code.equals("code"))
								object.put("tag", "6");
							else{
								sql = "select * from branch where code ='" + code
										+ "' and id!='"+id+"' order by id asc";
								stmt = conn.createStatement();
								rs = stmt.executeQuery(sql);
								if (rs.next()) 
									object.put("tag", "1");
								else 
									object.put("tag", "0");
							}
						} else object.put("tag", "5");
					} 
				}else {
					object.put("tag", "3");
				}
			}else {
				object.put("tag", "4");
			}
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
		 String filename = "分店列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
	    DBConn db = new DBConn();
		List<Branch>  result = new ArrayList<Branch> ();
		String code = StringUtil.notNull(request.getParameter("code"));
		
		String startTimeStr = StringUtil.notNull(request
				.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		try {
			if(db.createConn()){
				conn = db.getConnection();
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
							sql ="select * from branch where entry_time>='"+startTime+"' and entry_time<='"+endTime+"'  and code like'%"+code+"%' and state>'0' order by id desc";
						
					}else{
						sql ="select * from branch where code like'%"+code+"%' and state>'0' order by id desc";
						}
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Branch as = new Branch();
					as.setId(rs.getInt("id"));
					as.setCode(rs.getString("code"));
					as.setName(rs.getString("name"));
					as.setLinkman(rs.getString("linkman"));
					as.setPsw(rs.getString("psw"));
					as.setPsw2(rs.getString("psw2"));
					as.setTel(rs.getString("tel"));
					as.setPhone(rs.getString("phone"));
					as.setProvince(rs.getString("province"));
					as.setCity(rs.getString("city"));
					as.setArea(rs.getString("area"));
					as.setCredit(rs.getDouble("credit"));
					as.setCreditUsed(rs.getDouble("credit_used"));
					as.setStandardMin(rs.getDouble("standard_min"));
					as.setStandardUsed(rs.getDouble("standard_used"));
					as.setAddress(rs.getString("address"));
					as.setState(rs.getInt("state"));
					as.setEndTime(rs.getTimestamp("end_time"));
					as.setEntryTime(rs.getTimestamp("entry_time"));
					result.add(as);
				}
				String[][] content = new String[result.size()+1][12];
				content[0][0]="序号";
				content[0][1]="编号";
				content[0][2]="名称";
				content[0][3]="联系人";
				content[0][4]="授信额度";
				content[0][5]="已用授信";
				content[0][6]="固定电话";
				content[0][7]="手机号码";
				content[0][8]="所在地址";
				content[0][9]="注册时间";
				content[0][10]="修改时间";
				content[0][11]="当前状态";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getCode());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getName());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getLinkman());
					content[i+1][4]  = StringUtil.decimalFormat(result.get(i).getCredit());
					content[i+1][5]  =  StringUtil.decimalFormat(result.get(i).getCreditUsed());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getPhone());
					content[i+1][7]  = StringUtil.notNull(result.get(i).getTel());
					content[i+1][8]  = StringUtil.notNull(result.get(i).getProvince())+StringUtil.notNull(result.get(i).getArea())+StringUtil.notNull(result.get(i).getCity())+StringUtil.notNull(result.get(i).getAddress());
					content[i+1][9] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
					content[i+1][10] = StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
					
					String stateStr ="";
					if(result.get(i).getState()==0) stateStr = "下线";
					else if(result.get(i).getState()==1) stateStr = "正常";
					else if(result.get(i).getState()==2) stateStr = "锁定";
					content[i+1][11] = stateStr;
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("分会列表");  
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
	
	public void psw_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		DBConn db = new DBConn();
		String message="";
		try {
		if(branch!=null){
			if (db.createConn()) {
				conn = db.getConnection();
					String sql = "select * from branch where code='"+branch.getCode()+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						int token = (int)(1+Math.random()*(1000000-1+1));
						request.getSession().setAttribute("token", String.valueOf(token));
						Branch as = new Branch();
						as.setId(rs.getInt("id"));
						as.setCode(rs.getString("code"));
						as.setName(rs.getString("name"));
						as.setLinkman(rs.getString("linkman"));
						as.setPsw(rs.getString("psw"));
						as.setPsw2(rs.getString("psw2"));
						as.setTel(rs.getString("tel"));
						as.setPhone(rs.getString("phone"));
						as.setProvince(rs.getString("province"));
						as.setCity(rs.getString("city"));
						as.setArea(rs.getString("area"));
						as.setCredit(rs.getDouble("credit"));
						as.setCreditUsed(rs.getDouble("credit_used"));
						as.setAddress(rs.getString("address"));
						as.setState(rs.getInt("state"));
						as.setEndTime(rs.getTimestamp("end_time"));
						as.setEntryTime(rs.getTimestamp("entry_time"));
						request.setAttribute("sys_branch_edit", as);
						
					}else{
						message= "分店信息获取失败！";
					}
			}else{
				message= "数据库链接失败！";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
			if(message.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("psw_edit.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("psw_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void psw_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		
		String message = "";
		DBConn db = new DBConn();
		try {
			if(branch!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				
					String code = StringUtil.notNull(request.getParameter("code"));
					String pswOld = StringUtil.notNull(request.getParameter("pswOld"));
					String password = StringUtil.notNull(request.getParameter("psw"));
					Timestamp date = new Timestamp(new Date().getTime());
			if(db.createConn()){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 String sql = "select * from branch where code='"+code+"' and psw='"+MD5.GetMD5Code(pswOld)+"' for update";
				 stmt = conn.createStatement();
				 rs= stmt.executeQuery(sql);
				if(rs.next()){
					String sql1 = "update branch set psw='"+MD5.GetMD5Code(password)
							+"' where code='"+code+"'";
					stmt= conn.createStatement();
					stmt.executeUpdate(sql1);
					message =code+"登陆密码修改成功，请重新登陆。";
					 }else{
						 message= "分店信息获取失败或原密码不正确,请重新核对！";
					 }
				 conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else {
				message="数据库连接已断开！";
			}
				
				}else{
			message= "请勿重复提交数据。";
			}
			}else{
				message= "管理员用户未登陆，请重新登陆！";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			message="数据保存有误，请重新录入！";
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("psw_message.jsp");
			dispatcher.forward(request, response);
		}
	}
}

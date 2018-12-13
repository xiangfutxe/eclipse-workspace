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
import com.pojo.Address;
import com.pojo.Admin;
import com.pojo.OrderDetail;
import com.pojo.Product;
import com.pojo.ProductStock;
import com.pojo.ProductType;
import com.pojo.User;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;
import com.weixin.pojo.WeixinUserInfo;

public class ProductServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private String productName;
	private String specification;
	private String productId;
	private String imageUrl;
	private Integer num=0;
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
		}else if(method.equals("detail")){
			detail(request,response);
		}else if(method.equals("addShopCart")){
			addShopCart(request,response);
		}else if(method.equals("disDe")){
			disDe(request,response);
		}else if(method.equals("increate")){
			increate(request,response);
		}else if(method.equals("del_pro")){
			del_pro(request,response);
		}else if(method.equals("shop_cart_save")){
			shop_cart_save(request,response);
		}else if(method.equals("shop_save")){
			shop_save(request,response);
		}else if(method.equals("list_1")){
			list_1(request,response);
		}else if(method.equals("shop_cart_save_1")){
			shop_cart_save_1(request,response);
		}else if(method.equals("shop_save_1")){
			shop_save_1(request,response);
		}else if(method.equals("addShopCart_1")){
			addShopCart_1(request,response);
		}else if(method.equals("del_pro_1")){
			del_pro_1(request,response);
		}else if(method.equals("increate_1")){
			increate_1(request,response);
		}else if(method.equals("detail_1")){
			detail_1(request,response);
		}else if(method.equals("disDe_1")){
			disDe_1(request,response);
		}else if(method.equals("list_2")){
			list_2(request,response);
		}else if(method.equals("shop_cart_save_2")){
			shop_cart_save_2(request,response);
		}else if(method.equals("shop_save_2")){
			shop_save_2(request,response);
		}else if(method.equals("addShopCart_2")){
			addShopCart_2(request,response);
		}else if(method.equals("del_pro_2")){
			del_pro_2(request,response);
		}else if(method.equals("increate_2")){
			increate_2(request,response);
		}else if(method.equals("detail_2")){
			detail_2(request,response);
		}else if(method.equals("disDe_2")){
			disDe_2(request,response);
		}else if(method.equals("admin_product_save")){
					try {
						admin_product_save(request,response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
		}else if(method.equals("admin_product_list")){
			admin_product_list(request,response);
		}
		else if(method.equals("isExit")){
			isExit(request,response);
		}
		else if(method.equals("admin_product_add")){
			try {
				admin_product_add(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_product_add_2")){
			try {
				admin_product_add_2(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_product_stock")){
			
				admin_product_stock(request,response);
		
		}else if(method.equals("admin_product_stock_edit")){
			
			try {
				admin_product_stock_edit(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	
		}else if(method.equals("admin_product_stock_update")){
			
			try {
				admin_product_stock_update(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	
		}else if(method.equals("del")){
			del(request,response);
		}else if(method.equals("delAll")){
			delAll(request,response);
		}else if(method.equals("product_detail")){
			try {
				product_detail(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_product_edit")){
			try {
				admin_product_edit(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("admin_product_update")){
			try {
				admin_product_update(request,response);
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
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		List  result = new ArrayList();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			//if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product where state='1' order by id desc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				request.setAttribute("productName", productName);
				request.setAttribute("typeName", typeName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
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
				String sql1 = "select * from product_type where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
					coll_pt.add(pt);
				}
				request.setAttribute("coll_pt", coll_pt);
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
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		try {
			if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql1 = "select * from product where code='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("product", product);
					String sql0 = "select * from product_type where name='"+product.getType()+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						
						String[] str1 = product.getAttribute().split(";");
						int num1 = str1.length;
						int max =0;
						for(int i=0;i<str1.length;i++){
							if(str1[i]!=null){
								String[] str2 = str1[i].split(":");
								if(str2.length>1){
								 String[] str3  =str2[1].split(",");
										if(max<str3.length) max=str2.length;
								}
							}
						}
						String[] attribute = new String[num1];
						Integer[] attributeId = new Integer[num1];
						String[][] attributeValue = new String[num1][max];
						for(int i=0;i<str1.length;i++){
							if(str1[i]!=null){
								String[] str2 = str1[i].split(":");
								if(str2[0]!=null) attribute[i]=str2[0];
								if(str2[1]!=null){
									String[] str3 = str2[1].split(",");
								for(int j=0;j<str3.length;j++){
									if(str3[j]!=null){
										attributeValue[i][j]=str3[j];
									}
								}
							}
							}
						}
						for(int i=0;i<attribute.length;i++){
							String sql2 = "select id from product_type_to_attribute where type_id='"+pt.getId()+"' and attribute_name='"+attribute[i]+"' and state='1'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								attributeId[i] = rs.getInt("id");
							}
						}
						request.setAttribute("attributeValue", attributeValue);
						request.setAttribute("attributeId", attributeId);
						request.setAttribute("attribute", attribute);
					}
				}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
		}else {
			request.setAttribute("message", "用户未登陆,保存失败！");
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void addShopCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		String message ="";
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("code"));
				String numstr = StringUtil.notNull(request.getParameter("num"));
				String sql1 = "select * from product where code='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(Integer.valueOf(numstr));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("product", product);
					String attr ="";
					String sql0 = "select * from product_type where name='"+product.getType()+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						
						String[] str1 = product.getAttribute().split(";");
						
						for(int i=0;i<str1.length;i++){
								if(str1[i]!=null){
									String[] str2 = str1[i].split(":");
									if(str2[0]!=null) {
										int sid = 0;
										String sql2 = "select id from product_type_to_attribute where type_id='"+pt.getId()+"' and attribute_name='"+str2[0]+"' and state='1'";
										stmt= conn.createStatement();
										rs =stmt.executeQuery(sql2);
										if(rs.next()){
											sid = rs.getInt("id");
										}
										if(sid>0){
											String feature = request.getParameter("feature"+sid);
											if(StringUtil.notNull(feature).equals("")) message="产品属性选择不完整！";
											else attr=attr+str2[0]+":"+feature+";";
										}
									}
									
								}
							}
						product.setAttribute(attr);
						}//end select sql0
					if(message.equals("")){
						int t=0;
						if(plist!=null){
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getCode().equals(id)){
									if(plist.get(i).getAttribute().equals(product.getAttribute())){
										plist.get(i).setNum(plist.get(i).getNum()+Integer.valueOf(numstr));
										t++;
									}
								}
							}
						}else plist = new ArrayList<Product>();
						if(t==0) plist.add(product);
						request.getSession().setAttribute("shop_cart_list",plist);
						message= "成功加入购物车！";
					}
					}else {
						message= "产品信息获取失败,保存失败！";
					}
				
			}else {
				message= "数据库连接已断开,保存失败！";
			}
		}else {
			message= "用户未登陆,保存失败！";
		}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("message", message);
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
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void disDe(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
						if(plist.get(tag).getNum()-1>0)
							plist.get(tag).setNum(plist.get(tag).getNum()-1);
					}
					
						request.getSession().setAttribute("shop_cart_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del_pro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void shop_cart_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
		String message ="";
		try {
		if(snsUserInfo!=null){
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
				String sql = "select * from adr where user_id='"+snsUserInfo.getUserId()+"' and tag='1'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				if(rs.next()){
					Address adr = new Address();
					adr.setId(rs.getInt("id"));
					adr.setTel(rs.getString("tel"));
					adr.setReciver(rs.getString("reciver"));
					adr.setUserId(rs.getString("user_id"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("adr"));
					request.setAttribute("sns_adr",adr);
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
	
	public void shop_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("splist");
		String message ="";
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String adr_id = request.getParameter("adr_id");
				String sql = "select * from adr where id='"+adr_id+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				Address adr = new Address();
				if(rs.next()){
					adr.setId(rs.getInt("id"));
					adr.setTel(rs.getString("tel"));
					adr.setReciver(rs.getString("reciver"));
					adr.setUserId(rs.getString("user_id"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("adr"));
					request.setAttribute("sns_adr",adr);
				}
				String sqlw = "select * from weixin_userinfo where user_id='"+snsUserInfo.getUserId()+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sqlw);
				if(rs.next()){
					double emoney = rs.getDouble("emoney");
				Timestamp date = new Timestamp(new Date().getTime());
				String orderId = StringUtil.parseToString(date, DateUtil.ymdhms);
				double price = 0;
				double pv=0;
				List<String> slist = new ArrayList<String>();
				for(int i=0;i<plist.size();i++){
					price = price+plist.get(i).getPrice()*plist.get(i).getNum();
					pv = pv +plist.get(i).getPv()*plist.get(i).getNum();
					String sql1="insert into order_detail(order_id,product_id,product_name,product_type,attribute,product_price,product_pv,num,price,pv)"
							+ " values('"+orderId+"','"+plist.get(i).getCode()+"','"+plist.get(i).getName()+"','"+plist.get(i).getProductType()+"','"+plist.get(i).getAttribute()
							+"','"+plist.get(i).getPrice()+"','"+plist.get(i).getPv()+"','"+plist.get(i).getNum()+"','"+plist.get(i).getPrice()*plist.get(i).getNum()+"','"+plist.get(i).getPv()*plist.get(i).getNum()+"');";
					slist.add(sql1);
				}
			
				String sql2= "insert into orders(order_id,uid,user_id,receiver,phone,address,"
						+ "price,pv,order_type,order_time,state) "
						+ " values('"+orderId+"','"+snsUserInfo.getId()+"','"
						+snsUserInfo.getUserId()+"','"+adr.getReciver()+"','"+adr.getTel()
						+"','"+adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAdr()+"','"+price+"','"+pv+"','3','"+date+"','1');";
				slist.add(sql2);
				if(ArithUtil.sub(emoney, price)>=0){
					String sql3 = "update weixin_userinfo set emoney='"+ArithUtil.sub(emoney, price)+"' where user_id='"+snsUserInfo.getUserId()+"'";
					slist.add(sql3);
					String sql4 = cs.getEmoneyDetail(conn,snsUserInfo, price, ArithUtil.sub(emoney, price), 2, "报单购物", "报单购物", date);
					slist.add(sql4);
					if(slist.size()>0){
						for(int i=0;i<slist.size();i++){
							stmt.addBatch(slist.get(i));
							 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
								 stmt.executeBatch();
								 stmt = conn.createStatement();
							 }
						}
					}
					message="订单提交成功！";
				}else message = "账户余额不足，请及时充值！";
				}else message = "会员信息获取失败！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_save_msg.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_product_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		List  result = new ArrayList();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product where name like('%"+productName+"%') and type like('%"+typeName+"%') and state!='0' order by id asc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				request.setAttribute("productName", productName);
				request.setAttribute("typeName", typeName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1( "/upload/"+StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2( "/upload/"+StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3( "/upload/"+StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4( "/upload/"+StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5( "/upload/"+StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
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
				String sql1 = "select * from product_type where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
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
	
	public void isExit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		DBConn db = new DBConn();
		String productId = request.getParameter("productId");
		int tag=0;
		try {
			if(db.createConn()){
				if (StringUtil.notNull(productId).equals("")) {
					tag = 0;
				} else {
					String sql = "select * from product where code ='"+productId+"' order by id asc";
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
	public void admin_product_add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		Collection coll_pt = new ArrayList();
		DBConn db = new DBConn();
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
			String sql1 = "select * from product_type where state!='0' order by id asc";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql1);
			while(rs.next()){
				ProductType pt = new ProductType();
				pt.setName(rs.getString("name"));
				pt.setId(rs.getInt("id"));
				coll_pt.add(pt);
			}
			request.setAttribute("coll_pt", coll_pt);
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_add.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("message", "数据库连接已断开！");
		}	
		
		}else{
			request.setAttribute("message", "您没有该操作权限，如有需要请与系统管理员联系！");
		}
		
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
		}
	
	public void admin_product_add_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		
		DBConn db = new DBConn();
		String message ="";
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String typeName = StringUtil.notNull(request.getParameter("typeName"));
				request.setAttribute("typeName", typeName);
			String sql1 = "select * from product_type where name='"+typeName+"' and state='1' order by id asc";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				ProductType pt = new ProductType();
				pt.setName(rs.getString("name"));
				pt.setId(rs.getInt("id"));
				String sql2 = "select count(*) from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql2);
				int num1 = 0;
				if(rs.next()){
					num1 = rs.getInt(1);
				}
				String[] attribute = new String[num1];
				Integer[] attributeId = new Integer[num1];
				Integer[] ids = new Integer[num1];
				String sql3 = "select * from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql3);
				int t1 = 0;
				int max = 0;
				while(rs.next()){
					ids[t1]=rs.getInt("id");
					attribute[t1] = rs.getString("attribute_name");
					attributeId[t1] =rs.getInt("attribute_id");
					t1++;
				}
				request.setAttribute("attribute", attribute);
				request.setAttribute("attributeId", attributeId);
				for(int i=0;i<num1;i++){
					String sql4 = "select count(*) from product_type_to_attribute_classify where attribute_id='"+ids[i]+"' and state='1'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql4);
					if(rs.next()){
						int num2 = rs.getInt(1);
						if(num2>max) max=num2;
					}
				}
				String[][] attributeValue = new String[num1][max];
				Integer[][] attributeValueId = new Integer[num1][max+1];
				for(int i=0;i<num1;i++){
					String sql4 = "select * from product_type_to_attribute_classify where attribute_id='"+ids[i]+"' and state='1' order by id desc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql4);
					int t2=0;
					attributeValueId[i][max] = 0;
					while(rs.next()){
						attributeValue[i][t2] = rs.getString("value");
						attributeValueId[i][t2] = rs.getInt("id");
						attributeValueId[i][max] = 1;
						t2++;
					}
				}
				request.setAttribute("attributeValue", attributeValue);
				request.setAttribute("attributeValueId", attributeValueId);
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
			e.printStackTrace();
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_add_2.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_add_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
		
	
	public void admin_product_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String typeName = StringUtil.notNull(request.getParameter("productType"));
				String code = StringUtil.notNull(request.getParameter("code"));
				String isGroup = StringUtil.notNull(request.getParameter("isGroup"));
				String[] belongArea = request.getParameterValues("belongArea");
				String name = StringUtil.notNull(request.getParameter("productName"));
				String price = StringUtil.notNull(request.getParameter("price"));
				String pv = StringUtil.notNull(request.getParameter("pv"));
				String integral = StringUtil.notNull(request.getParameter("integral"));
				String description = StringUtil.notNull(request.getParameter("description"));
				String spec = StringUtil.notNull(request.getParameter("spec"));
				Timestamp date = new Timestamp(new Date().getTime());
				
				String bstr = "";
				for(int i=0;i<belongArea.length;i++){
					if(belongArea.length-i-1==0)
						bstr = bstr+StringUtil.notNull(belongArea[i]);
					else bstr = bstr+StringUtil.notNull(belongArea[i])+",";
				}
				String attr = "";
				String sql1 = "select * from product_type where name='"+typeName+"' and state='1' order by id asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
					pt.setId(rs.getInt("id"));
					String sql2 = "select count(*) from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql2);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					String[] attribute = new String[num1];
					Integer[] attributeId = new Integer[num1];
					Integer[] ids = new Integer[num1];
					String sql3 = "select * from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1' order by attribute_id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql3);
					int t1 = 0;
					while(rs.next()){
						ids[t1]=rs.getInt("id");
						attribute[t1] = rs.getString("attribute_name");
						attributeId[t1] =rs.getInt("attribute_id");
						t1++;
					}
				for(int i=0;i<attribute.length;i++){
					String astr =StringUtil.notNull(request.getParameter("attribute"+attributeId[i]));
					if(!astr.equals("")){
						attr = attr + astr+":";
						String value = "";
						String[] str = request.getParameterValues("feature"+attributeId[i]);
						for(int j=0;j<str.length;j++){
							if(!StringUtil.notNull(str[j]).equals("")){
								if((str.length-1-j)==0){
									value = value +str[j];
								}else value= value+str[j]+",";
							}
						}
						attr = attr+value+";";
					}
				}
			String sql4 = "insert into product(code,name,type,price,pv,integral,attribute,spec,description,is_group,belong_area,state,entry_time,end_time) values('"+code+"','"+name
					+"','"+typeName+"','"+price+"','"+pv+"','"+integral+"','"+attr+"','"+spec+"','"+description+"','"+isGroup+"','"+bstr+"','1','"+date+"','"+date+"')";
			stmt= conn.createStatement();
			stmt.executeUpdate(sql4);
			
			List<String> slist = new ArrayList<String>();
			List<String> plist = new ArrayList<String>();
			String[] str1 = attr.split(";");
			String[] array1 = new String[str1.length];
			String[] array2 = new String[str1.length];
			for(int i=0;i<str1.length;i++){
				array1[i] ="";
				array2[i] ="";
				if(!str1[i].equals("")){
					String[] str2 = str1[i].split(":");
					if(str2.length>0){
						if(!str2[0].equals("")){
							array1[i] =str2[0];
						}
					}
					if(str2.length>1){
						if(!str2[1].equals("")){
							array2[i] =str2[1];
						}
					}
				}
			}
			int num= 0;
			for(int i=0;i<array2.length;i++){
				String[] atr1 = array2[i].split(",");
				if(num<atr1.length)num=atr1.length;
			}
			String[][] atr = new String[array2.length][num];
			for(int i=0;i<array2.length;i++){
				String[] atr1 = array2[i].split(",");
				for(int j=0;j<atr1.length;j++){
					if(!atr1[j].equals(""))
						atr[i][j]=atr1[j];
				}
			}
			String[] tmp = new String[array1.length];
			cs.combination(atr, array1, 0, tmp, slist);
			for(int i=0;i<slist.size();i++){
				String sqls = "insert into product_stock(product_id,product_name,attribute,state,entry_time,end_time) values('"
						+code+"','"+name+"','"+slist.get(i)+"','1','"+date+"','"+date+"')";
				plist.add(sqls);
			}
			if(plist.size()>0){
				for(int i=0;i<plist.size();i++){
					stmt.addBatch(plist.get(i));
					 if ((i % 50000 == 0 && i != 0) || i == (plist.size() - 1)) { 
						 stmt.executeBatch();
						 stmt = conn.createStatement();
					 }
				}
			}
			message= "产品编号"+code+"保存成功！";
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
				}else {
					message= "获取产品分类信息失败！";
				}	
		}else {
			message= "数据库连接已断开！";
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
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_add_2.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_add_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	public void admin_product_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		
		DBConn db = new DBConn();
		String message ="";
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql = "select * from product where id='"+id+"'";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
					Product  product = new Product();
					product.setId(rs.getInt("id"));
					product.setCode(rs.getString("code"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIntegral(rs.getDouble("integral"));
					product.setAttribute(rs.getString("attribute"));
					product.setDescription(rs.getString("description"));
					product.setType(rs.getString("type"));
					product.setBelongArea(rs.getString("belong_area"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setSpec(rs.getString("spec"));
					String typeName =rs.getString("type");
					String sql1 = "select * from product_type where name='"+typeName+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql1);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						String sql2 = "select count(*) from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1'";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql2);
						int num1 = 0;
						if(rs.next()){
							num1 = rs.getInt(1);
						}
						String[] attribute = new String[num1];
						Integer[] attributeId = new Integer[num1];
						Integer[] ids = new Integer[num1];
						String sql3 = "select * from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1'";
						stmt= conn.createStatement();
						rs =stmt.executeQuery(sql3);
						int t1 = 0;
						int max = 0;
						while(rs.next()){
							ids[t1]=rs.getInt("id");
							attribute[t1] = rs.getString("attribute_name");
							attributeId[t1] =rs.getInt("attribute_id");
							t1++;
						}
						request.setAttribute("attribute", attribute);
						request.setAttribute("attributeId", attributeId);
						for(int i=0;i<num1;i++){
							String sql4 = "select count(*) from product_type_to_attribute_classify where attribute_id='"+ids[i]+"' and state='1'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql4);
							if(rs.next()){
								int num2 = rs.getInt(1);
								if(num2>max) max=num2;
							}
						}
						String[][] attributeValue = new String[num1][max];
						Integer[][] attributeValueId = new Integer[num1][max+1];
						Integer[][] attributeValueTag = new Integer[num1][max+1];
						for(int i=0;i<num1;i++){
							String sql4 = "select * from product_type_to_attribute_classify where attribute_id='"+ids[i]+"' and state='1' order by id desc";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql4);
							int t2=0;
							attributeValueId[i][max] = 0;
							while(rs.next()){
								attributeValue[i][t2] = rs.getString("value");
								attributeValueId[i][t2] = rs.getInt("id");
								attributeValueId[i][max] = 1;
								attributeValueTag[i][t2] = 0;
								t2++;
							}
						}
						String[] str1 = product.getAttribute().split(";");
						for(int i=0;i<str1.length;i++){
							if(!str1[i].equals("")){
								String[] str2 = str1[i].split(":");
								if(str2.length>=1){
									for(int j=0;j<attribute.length;j++){
										if(str2[0].equals(attribute[j])){
											if(str2.length>1){
												if(attributeValueId[j][max] ==0)
													 attributeValue[j][0]=str2[1];
												else{
													String[] str3 = str2[1].split(",");
													for(int k=0;k<str3.length;k++){
														if(!str3[k].equals("")){
															 for(int m=0;m<attribute[j].length();m++){
																 if(str3[k].equals(attributeValue[j][m])){
																	 attributeValueTag[j][m]=1;
																	 break;
																 }
															 }
														}
													}
												}
											}
											break;
										}
									}
								}
							}
						}
						request.setAttribute("attributeValue", attributeValue);
						request.setAttribute("attributeValueId", attributeValueId);
							request.setAttribute("attributeValueTag", attributeValueTag);
							request.setAttribute("product", product);
					}else {
						message="产品分类信息获取失败，请重试！";
					}	

					}else {
						message="产品信息获取失败，请重试！";
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
	
	public void admin_product_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				 String id = StringUtil.notNull(request.getParameter("id"));
				String typeName = StringUtil.notNull(request.getParameter("productType"));
				String code = StringUtil.notNull(request.getParameter("code"));
				String isGroup = StringUtil.notNull(request.getParameter("isGroup"));
				String[] belongArea = request.getParameterValues("belongArea");
				String name = StringUtil.notNull(request.getParameter("productName"));
				String price = StringUtil.notNull(request.getParameter("price"));
				String pv = StringUtil.notNull(request.getParameter("pv"));
				String integral = StringUtil.notNull(request.getParameter("integral"));
				String description = StringUtil.notNull(request.getParameter("description"));
				String spec = StringUtil.notNull(request.getParameter("spec"));
				Timestamp date = new Timestamp(new Date().getTime());
				
				String bstr = "";
				for(int i=0;i<belongArea.length;i++){
					if(belongArea.length-i-1==0)
						bstr = bstr+StringUtil.notNull(belongArea[i]);
					else bstr = bstr+StringUtil.notNull(belongArea[i])+",";
				}
				String attr = "";
				String sql = "select * from product where id='"+id+"' for update";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				if(rs.next()){
				String sql1 = "select * from product_type where name='"+typeName+"' and state='1' order by id asc";
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
					pt.setId(rs.getInt("id"));
					String sql2 = "select count(*) from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1'";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql2);
					int num1 = 0;
					if(rs.next()){
						num1 = rs.getInt(1);
					}
					String[] attribute = new String[num1];
					Integer[] attributeId = new Integer[num1];
					Integer[] ids = new Integer[num1];
					String sql3 = "select * from product_type_to_attribute where type_id='"+pt.getId()+"' and state='1' order by attribute_id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql3);
					int t1 = 0;
					while(rs.next()){
						ids[t1]=rs.getInt("id");
						attribute[t1] = rs.getString("attribute_name");
						attributeId[t1] =rs.getInt("attribute_id");
						t1++;
					}
				for(int i=0;i<attribute.length;i++){
					String astr =StringUtil.notNull(request.getParameter("attribute"+attributeId[i]));
					if(!astr.equals("")){
						attr = attr + astr+":";
						String value = "";
						String[] str = request.getParameterValues("feature"+attributeId[i]);
						for(int j=0;j<str.length;j++){
							if(!StringUtil.notNull(str[j]).equals("")){
								if((str.length-1-j)==0){
									value = value +str[j];
								}else value= value+str[j]+",";
							}
						}
						attr = attr+value+";";
					}
				}
			String sql4 = "update product set name='"+name+"',price='"+price
					+"',pv='"+pv+"',integral='"+integral+"',attribute='"+attr
					+"',description='"+description+"',is_group='"+isGroup+"',spec='"+spec
					+"',belong_area='"+bstr+"',end_time='"+date+"' where id='"+id+"'";
			stmt= conn.createStatement();
			stmt.executeUpdate(sql4);
			
			//保存库存内容
			List<String> slist = new ArrayList<String>();
			List<String> plist = new ArrayList<String>();
			String[] str1 = attr.split(";");
			String[] array1 = new String[str1.length];
			String[] array2 = new String[str1.length];
			for(int i=0;i<str1.length;i++){
				array1[i] ="";
				array2[i] ="";
				if(!str1[i].equals("")){
					String[] str2 = str1[i].split(":");
					if(str2.length>0){
						if(!str2[0].equals("")){
							array1[i] =str2[0];
						}
					}
					if(str2.length>1){
						if(!str2[1].equals("")){
							array2[i] =str2[1];
						}
					}
				}
			}
			int num= 0;
			for(int i=0;i<array2.length;i++){
				String[] atr1 = array2[i].split(",");
				if(num<atr1.length)num=atr1.length;
			}
			String[][] atr = new String[array2.length][num];
			for(int i=0;i<array2.length;i++){
				String[] atr1 = array2[i].split(",");
				for(int j=0;j<atr1.length;j++){
					if(!atr1[j].equals(""))
						atr[i][j]=atr1[j];
				}
			}
			String[] tmp = new String[array1.length];
			cs.combination(atr, array1, 0, tmp, slist);
			for(int i=0;i<slist.size();i++){
				String is_exit = "select * from product_stock where product_id='"+code+"' and attribute='"+slist.get(i)+"'";
				stmt = conn.createStatement();
						rs=stmt.executeQuery(is_exit);
				if(!rs.next()){
				String sqls = "insert into product_stock(product_id,product_name,attribute,state,entry_time,end_time) values('"
						+code+"','"+name+"','"+slist.get(i)+"','1','"+date+"','"+date+"')";
				plist.add(sqls);
				}
			}
			if(plist.size()>0){
				for(int i=0;i<plist.size();i++){
					stmt.addBatch(plist.get(i));
					 if ((i % 50000 == 0 && i != 0) || i == (plist.size() - 1)) { 
						 stmt.executeBatch();
						 stmt = conn.createStatement();
					 }
				}
			}
			message= "产品编号"+code+"修改成功！";
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
				}else {
					message= "获取产品分类信息失败！";
				}	
				}else {
					message= "获取产品信息失败！";
				}	
		}else {
			message= "数据库连接已断开！";
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
			message= "数据保存有误，请重新录入！";
		}finally{
			db.close();
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
				dispatcher.forward(request, response);
		}
		}
	
	public void img_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String message="";
		DBConn db = new DBConn();
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token = (int)(1+Math.random()*(1000000-1+1));
				System.out.println(token);
				request.getSession().setAttribute("token", String.valueOf(token));
			String sql1 = "select * from product where id='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product pt = new Product();
				pt.setId(rs.getInt("id"));
				pt.setCode(rs.getString("code"));
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
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
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
			
				product.setPrice(rs.getDouble("price"));
				product.setPv(rs.getDouble("pv"));
				
				product.setNum(rs.getInt("num"));
				
				product.setImageUrl1("/upload/"+StringUtil.notNull(rs.getString("imageUrl")));
				product.setState(rs.getInt("state"));
				product.setEntryTime(rs.getTimestamp("entryTime"));
				product.setEndTime(rs.getTimestamp("endTime"));
				request.setAttribute("product", product);
				
				String sqls = "select * from productType where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sqls);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
					coll_pt.add(pt);
				}
				request.setAttribute("coll_pt", coll_pt);
				
				request.setAttribute("coll_t", coll_t);
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
	public void product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message="";
		Collection coll = new ArrayList();
		DBConn db = new DBConn();
		try {
		if(user!=null){
	
			if(db.createConn()){
				String id = StringUtil.notNull(request.getParameter("id"));
			String sql1 = "select * from product where productId='"+id+"'";
			stmt= db.getStmtread();
			rs =stmt.executeQuery(sql1);
			if(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
			
				product.setPrice(rs.getDouble("price"));
				product.setPv(rs.getDouble("pv"));
			
				product.setNum(rs.getInt("num"));
				
				product.setEntryTime(rs.getTimestamp("entryTime"));
				product.setEndTime(rs.getTimestamp("endTime"));
				request.setAttribute("product", product);
				
			
					request.setAttribute("coll", coll);
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
				conn.rollback();
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				db.close();
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("user_product_detail.jsp");
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
		String[] productTypeStr = request.getParameterValues("productType");
		String[] dType = request.getParameterValues("dType");
		String[] team  = request.getParameterValues("team");
		specification = StringUtil.notNull(request.getParameter("specification"));
		features = StringUtil.notNull(request.getParameter("features"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String id = StringUtil.notNull(request.getParameter("id"));
		String price = StringUtil.notNull(request.getParameter("price"));
		String pv = StringUtil.notNull(request.getParameter("pv"));
		String purchase_price_1 = StringUtil.notNull(request.getParameter("purchase_price_1"));
		String purchase_pv_1 = StringUtil.notNull(request.getParameter("purchase_pv_1"));
		String purchase_price_2 = StringUtil.notNull(request.getParameter("purchase_price_2"));
		String purchase_pv_2 = StringUtil.notNull(request.getParameter("purchase_pv_2"));
		String purchase_pv = StringUtil.notNull(request.getParameter("purchase_pv"));
		String integral = StringUtil.notNull(request.getParameter("integral"));
		 String message = "";
		 try {  
			 if(admin!=null){
				 if(StringUtil.notNull(token_old).equals(token)){
					String[][] rankstr = StringUtil.getRankStr(admin.getRank());
					if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			request.getSession().setAttribute("token", String.valueOf(token_new));
		 DBConn db = new DBConn();
		 if(db.createConn()){
			 conn = db.getConnection();
			 boolean autoCommit= conn.getAutoCommit();
			 conn.setAutoCommit(false);
			 
			 String sql ="";
			
				 if(productTypeStr.length>0){
						if(dType.length>0){
							if(team.length>0){
								if(!StringUtil.notNull(productId).equals("")){
				String productType ="";
				String dTypeStr ="";
				String teamStr ="";
				for(int i=0;i<productTypeStr.length;i++){
					if(!StringUtil.notNull(productTypeStr[i]).equals("")){
						if(productType.equals("")) productType = productTypeStr[i];
						else productType = productType+","+productTypeStr[i];
					}
				}
				for(int i=0;i<dType.length;i++){
					if(!StringUtil.notNull(dType[i]).equals("")){
						if(dTypeStr.equals("")) dTypeStr = dType[i];
						else dTypeStr = dTypeStr+","+dType[i];
					}
				}
				for(int i=0;i<team.length;i++){
					if(!StringUtil.notNull(team[i]).equals("")){
						if(teamStr.equals("")) teamStr = team[i];
						else teamStr = teamStr+","+team[i];
					}
				}
			 if(type.equals("1"))
			 sql = "update product set productName='"+productName+"',productType='"+productType+"',specification='"+specification+"',features='"+features+"',price='"+price+"',pv='"+pv+"',purchase_price_1='"+purchase_price_1+"',purchase_pv_1='"+purchase_pv_1+"',purchase_price_2='"+purchase_price_2+"',purchase_pv_2='"+purchase_pv_2+"',purchase_pv='"+purchase_pv+"',team='"+teamStr+"',d_type='"+dTypeStr+"',integral='"+integral+"'  where id='"+id+"'";
			 else if(type.equals("2"))
			 sql = "update product set productName='"+productName+"',productType='"+productType+"',specification='"+specification+"',features='"+features+"',team='"+teamStr+"',d_type='"+dTypeStr+"' where id='"+id+"'";
			if(!sql.equals("")){
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 message= "产品更新成功！";
			}else message="未查询到产品对应的类型信息！";
							}else {
								 message= "产品名称不能为空，请重新录入！";
							}
											}else{
												 message= "产品所属团队不能为空，请重新录入！";
											}
										}else{
											 message= "报单类型不能为空，请重新录入！";
										}
									}else{
										 message= "产品分类不能为空，请重新录入！";
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
				 message= "管理员用户未登陆，请重新登陆！";
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
					if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
		 List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		 String id = "";
		 String token = "";
		 String[] imgurl = new String[5];
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
            	 int t=0;
            	  if(name.equals("image1")) t=0;
            	  else if(name.equals("image2")) t=1;
            	  else if(name.equals("image3")) t=2;
            	  else if(name.equals("image4")) t=3;
            	  else if(name.equals("image5")) t=4;
                 String value = item.getName() ;  
                if(!value.equals("")){
                 int start = value.lastIndexOf("\\");  
               
                 String filename = value.substring(start+1);  
                 if(name.equals("image1"))
                 imgurl[t] = filename;
                 
                
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
                }else{
                	imgurl[t] ="";
                }
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
			 for(int i=0;i<imgurl.length;i++){
				 if(!imgurl[i].equals("")){
					 String sql = "update product set image_url_"+(i+1)+"='"+imgurl[i]+"' where id='"+id+"'";
					 stmt=conn.createStatement();
					 stmt.executeUpdate(sql);
				 }
			 }
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
				 message= "数据保存异常！";
			}finally{
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
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
		String price = StringUtil.notNull(request.getParameter("price"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String[] productTypeStr = request.getParameterValues("productType");
		String[] dType = request.getParameterValues("dType");
		String[] team  = request.getParameterValues("team");
		String pv  = StringUtil.notNull(request.getParameter("pv"));
		String purchase_price_1  = StringUtil.notNull(request.getParameter("purchase_price_1"));
		String purchase_price_2  = StringUtil.notNull(request.getParameter("purchase_price_2"));
		String purchase_pv_1  = StringUtil.notNull(request.getParameter("purchase_pv_1"));
		String purchase_pv_2  = StringUtil.notNull(request.getParameter("purchase_pv_2"));
		String purchase_pv  = StringUtil.notNull(request.getParameter("purchase_pv"));
		
		String numStr  = StringUtil.notNull(request.getParameter("num"));
		String integral  = StringUtil.notNull(request.getParameter("integral"));
		
		if(numStr.equals("")) num= 0;
		else num = Integer.valueOf(numStr);
		String message = "";
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
					if(db.createConn()){
					if(type.equals("1")){
						if(productTypeStr.length>0){
							if(dType.length>0){
								if(team.length>0){
									if(!(StringUtil.notNull(productId).equals(""))){
				 conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
			String productType ="";
			String dTypeStr ="";
			String teamStr ="";
			for(int i=0;i<productTypeStr.length;i++){
				if(!StringUtil.notNull(productTypeStr[i]).equals("")){
					if(productType.equals("")) productType = productTypeStr[i];
					else productType = productType+","+productTypeStr[i];
				}
			}
			for(int i=0;i<dType.length;i++){
				if(!StringUtil.notNull(dType[i]).equals("")){
					if(dTypeStr.equals("")) dTypeStr = dType[i];
					else dTypeStr = dTypeStr+","+dType[i];
				}
			}
			for(int i=0;i<team.length;i++){
				if(!StringUtil.notNull(team[i]).equals("")){
					if(teamStr.equals("")) teamStr = team[i];
					else teamStr = teamStr+","+team[i];
				}
			}
					String sql = "select * from product where productId ='"+productId+"'";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql);
				if(!rs.next()){
					 Timestamp date1 = new Timestamp(new Date().getTime());
					 java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
					 System.out.println(date);
					 String sqli = "insert into product(productId,productName,productType,type,specification,features,price,pv,team,purchase_price_1,purchase_price_2,purchase_pv_1,purchase_pv_2,purchase_pv,num,imageUrl,"
					 		+ "state,entryTime,endTime,d_type,integral) values('"+productId+"','"+productName+"','"+productType+"','"+type+"','"+specification+"','"+features
					 		+"','"+price+"','"+pv+"','"+teamStr+"','"+purchase_price_1+"','"+purchase_price_2+"','"+purchase_pv_1+"','"+purchase_pv_2+"','"+purchase_pv+"','"+num+"','"+imageUrl
					 +"','1','"+date+"','"+date+"','"+dTypeStr+"','"+integral+"')";
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
								}else{
									 message= "产品所属团队不能为空，请重新录入！";
								}
							}else{
								 message= "报单类型不能为空，请重新录入！";
							}
						}else{
							 message= "产品分类不能为空，请重新录入！";
						}
			
			}else if(type.equals("2")){
				Product product = new Product();
				
				request.getSession().setAttribute("product", product);
					int token = (int)(1+Math.random()*(1000000-1+1));
					request.getSession().setAttribute("token", String.valueOf(token));
					String sql1 = "select * from product where state!='0' and type='1' order by id desc";
					stmt= db.getStmtread();
					rs =stmt.executeQuery(sql1);
					while(rs.next()){
						Product pt = new Product();
						pt.setId(rs.getInt("id"));
						
						pt.setPrice(rs.getDouble("price"));
						pt.setPv(rs.getDouble("pv"));
						coll_pt.add(pt);
					}
					if(coll_pt.size()>0){
					request.setAttribute("coll", coll_pt);
					}else{
						 message= "未找到有单品信息，请先定义单品！";
					}
					
			}else {
				 message= "数据库连接已断开！";
			}
					
					}else{
				 message= "没有选择产品类型，请重新输入！";
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
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void admin_product_stock(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		String code = StringUtil.notNull(request.getParameter("code"));
		request.setAttribute("code", code);
		try {
			if(admin!=null){
				String[][] rankstr = StringUtil.getRankStr(admin.getRank());
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product_stock where product_id='"+code+"' and state='1' order by id asc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					ProductStock ps = new ProductStock();
					ps.setId(rs.getInt("id"));
					ps.setProductId(rs.getString("product_id"));
					ps.setProductName(rs.getString("product_name"));
					ps.setAttribute(rs.getString("attribute"));
					ps.setNum(rs.getInt("num"));
					ps.setTolNum(rs.getInt("tol_num"));
					ps.setState(rs.getInt("state"));
					ps.setEntryTime(rs.getTimestamp("entry_time"));
					ps.setEndTime(rs.getTimestamp("end_time"));
					coll.add(ps);
				}
				request.setAttribute("coll", coll);
				request.setAttribute("code", code);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_product_stock_edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		Collection coll_pt = new ArrayList();
		DBConn db = new DBConn();
		
		String message="";
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				String code = StringUtil.notNull(request.getParameter("code"));
				request.setAttribute("code", code);
			String sql1 = "select * from product_stock where state='1' order by id asc";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql1);
			while(rs.next()){
				ProductStock ps = new ProductStock();
				ps.setId(rs.getInt("id"));
				ps.setProductId(rs.getString("product_id"));
				ps.setProductName(rs.getString("product_name"));
				ps.setAttribute(rs.getString("attribute"));
				ps.setNum(rs.getInt("num"));
				ps.setTolNum(rs.getInt("tol_num"));
				ps.setState(rs.getInt("state"));
				ps.setEntryTime(rs.getTimestamp("entry_time"));
				ps.setEndTime(rs.getTimestamp("end_time"));
				coll_pt.add(ps);
			}
			request.setAttribute("coll", coll_pt);
			request.setAttribute("code", code);
			
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
			if(message.equals("")){
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock_edit.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void admin_product_stock_update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		Collection coll_pt = new ArrayList();
		DBConn db = new DBConn();
		String message="";
		try {
		if(admin!=null){
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
			List<String> slist = new ArrayList<String>();
			String code = StringUtil.notNull(request.getParameter("code"));
			request.setAttribute("code", code);
			String sql = "select * from product where code ='"+code+"' for update";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql);
			if(rs.next()){
				int p_num = rs.getInt("num");
				int total_num =  rs.getInt("total_num");
			String sql1 = "select * from product_stock where product_id='"+code+"' and state='1' for update";
			stmt= conn.createStatement();
			rs =stmt.executeQuery(sql1);
			while(rs.next()){
				int id = rs.getInt("id");
				int num = rs.getInt("num");
				String numstr = StringUtil.notNull(request.getParameter("num"+id));
				if(!StringUtil.notNull(numstr).equals("")){
					int new_num = Integer.valueOf(numstr);
					p_num = p_num-num+new_num;
					total_num = total_num-num+new_num;
					String sql2 = "update product_stock set num='"+new_num+"' where id='"+id+"'";
					slist.add(sql2);
				}
			}
			if(slist.size()>0){
				String sql3 = "update product set num='"+p_num+"',total_num='"+total_num+"' where code='"+code+"'";
				slist.add(sql3);
				for(int i=0;i<slist.size();i++){
					stmt.addBatch(slist.get(i));
					 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
						 stmt.executeBatch();
						 stmt = conn.createStatement();
					 }
				}
			}
			message=code+"产品更新成功！";
			}else {
				message="产品信息获取失败，请重试";
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock_message.jsp");
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
				if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
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
	
	public void detail_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		try {
			if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql1 = "select * from product where code='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("product", product);
					String sql0 = "select * from product_type where name='"+product.getType()+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						
						String[] str1 = product.getAttribute().split(";");
						int num1 = str1.length;
						int max =0;
						for(int i=0;i<str1.length;i++){
							if(str1[i]!=null){
								String[] str2 = str1[i].split(":");
								if(str2.length>1){
								 String[] str3  =str2[1].split(",");
										if(max<str3.length) max=str2.length;
								}
							}
						}
						String[] attribute = new String[num1];
						Integer[] attributeId = new Integer[num1];
						String[][] attributeValue = new String[num1][max];
						for(int i=0;i<str1.length;i++){
							if(str1[i]!=null){
								String[] str2 = str1[i].split(":");
								if(str2[0]!=null) attribute[i]=str2[0];
								if(str2[1]!=null){
									String[] str3 = str2[1].split(",");
								for(int j=0;j<str3.length;j++){
									if(str3[j]!=null){
										attributeValue[i][j]=str3[j];
									}
								}
							}
							}
						}
						for(int i=0;i<attribute.length;i++){
							String sql2 = "select id from product_type_to_attribute where type_id='"+pt.getId()+"' and attribute_name='"+attribute[i]+"' and state='1'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								attributeId[i] = rs.getInt("id");
							}
						}
						request.setAttribute("attributeValue", attributeValue);
						request.setAttribute("attributeId", attributeId);
						request.setAttribute("attribute", attribute);
					}
				}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
		}else {
			request.setAttribute("message", "用户未登陆,保存失败！");
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void addShopCart_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		String message ="";
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_declaration_list");
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("code"));
				String numstr = StringUtil.notNull(request.getParameter("num"));
				String sql1 = "select * from product where code='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(Integer.valueOf(numstr));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("product", product);
					String attr ="";
					String sql0 = "select * from product_type where name='"+product.getType()+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						
						String[] str1 = product.getAttribute().split(";");
						
						for(int i=0;i<str1.length;i++){
								if(str1[i]!=null){
									String[] str2 = str1[i].split(":");
									if(str2[0]!=null) {
										int sid = 0;
										String sql2 = "select id from product_type_to_attribute where type_id='"+pt.getId()+"' and attribute_name='"+str2[0]+"' and state='1'";
										stmt= conn.createStatement();
										rs =stmt.executeQuery(sql2);
										if(rs.next()){
											sid = rs.getInt("id");
										}
										if(sid>0){
											String feature = request.getParameter("feature"+sid);
											if(StringUtil.notNull(feature).equals("")) message="产品属性选择不完整！";
											else attr=attr+str2[0]+":"+feature+";";
										}
									}
									
								}
							}
						product.setAttribute(attr);
						}//end select sql0
					if(message.equals("")){
						int t=0;
						if(plist!=null){
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getCode().equals(id)){
									if(plist.get(i).getAttribute().equals(product.getAttribute())){
										plist.get(i).setNum(plist.get(i).getNum()+Integer.valueOf(numstr));
										t++;
									}
								}
							}
						}else plist = new ArrayList<Product>();
						if(t==0) plist.add(product);
						request.getSession().setAttribute("shop_cart_declaration_list",plist);
						message= "成功加入购物车！";
					}
					}else {
						message= "产品信息获取失败,保存失败！";
					}
				
			}else {
				message= "数据库连接已断开,保存失败！";
			}
		}else {
			message= "用户未登陆,保存失败！";
		}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("message", message);
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
	
	public void increate_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_declaration_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.get(tag).setNum(plist.get(tag).getNum()+1);
					}
					
						request.getSession().setAttribute("shop_cart_declaration_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void disDe_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_declaration_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
						if(plist.get(tag).getNum()-1>0)
							plist.get(tag).setNum(plist.get(tag).getNum()-1);
					}
					
						request.getSession().setAttribute("shop_cart_declaration_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del_pro_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_declaration_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.remove(tag);
					}
						request.getSession().setAttribute("shop_cart_list_1",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void shop_cart_save_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_declaration_list");
		String message ="";
		try {
		if(snsUserInfo!=null){
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
				String sql = "select * from adr where user_id='"+snsUserInfo.getUserId()+"' and tag='1'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				if(rs.next()){
					Address adr = new Address();
					adr.setId(rs.getInt("id"));
					adr.setTel(rs.getString("tel"));
					adr.setReciver(rs.getString("reciver"));
					adr.setUserId(rs.getString("user_id"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("adr"));
					request.setAttribute("sns_adr",adr);
				}
				request.getSession().setAttribute("splist_1",slist);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart_save_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void shop_save_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("splist_1");
		String message ="";
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String adr_id = request.getParameter("adr_id");
				String sql = "select * from adr where id='"+adr_id+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				Address adr = new Address();
				if(rs.next()){
					adr.setId(rs.getInt("id"));
					adr.setTel(rs.getString("tel"));
					adr.setReciver(rs.getString("reciver"));
					adr.setUserId(rs.getString("user_id"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("adr"));
					request.setAttribute("sns_adr",adr);
				}
				String sqlw = "select * from weixin_userinfo where user_id='"+snsUserInfo.getUserId()+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sqlw);
				if(rs.next()){
					double emoney = rs.getDouble("emoney");
					double integral = rs.getDouble("integral");
					int rank = rs.getInt("rank_manage");
				Timestamp date = new Timestamp(new Date().getTime());
				String orderId = StringUtil.parseToString(date, DateUtil.ymdhms);
				double price = 0;
				double pv=0;
				List<String> slist = new ArrayList<String>();
				for(int i=0;i<plist.size();i++){
					price = price+plist.get(i).getPrice()*plist.get(i).getNum();
					pv = pv +plist.get(i).getPv()*plist.get(i).getNum();
					String sql1="insert into order_detail(order_id,product_id,product_name,product_type,attribute,product_price,product_pv,num,price,pv)"
							+ " values('"+orderId+"','"+plist.get(i).getCode()+"','"+plist.get(i).getName()+"','"+plist.get(i).getProductType()+"','"+plist.get(i).getAttribute()
							+"','"+plist.get(i).getPrice()+"','"+plist.get(i).getPv()+"','"+plist.get(i).getNum()+"','"+plist.get(i).getPrice()*plist.get(i).getNum()+"','"+plist.get(i).getPv()*plist.get(i).getNum()+"');";
					slist.add(sql1);
				}
				String sql2= "insert into orders(order_id,uid,user_id,receiver,phone,address,"
						+ "price,pv,order_type,order_time,state) "
						+ " values('"+orderId+"','"+snsUserInfo.getId()+"','"
						+snsUserInfo.getUserId()+"','"+adr.getReciver()+"','"+adr.getTel()
						+"','"+adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAdr()+"','"+price+"','"+pv+"','1','"+date+"','1');";
				
				slist.add(sql2);
				if(ArithUtil.sub(emoney, price)>=0){
					int rankManage = 0;
					if(price>=9800) rankManage=3;
					else if(price>=3980) rankManage=2;
					else if(price>=98) rankManage=1;
					if(rank>rankManage) rankManage = rank;
					String sql3 = "update weixin_userinfo set rank_manage='"+rankManage+"',emoney='"+ArithUtil.sub(emoney, price)+"',integral='"+ArithUtil.add(integral, price)+"' where user_id='"+snsUserInfo.getUserId()+"'";
					slist.add(sql3);
					String sql4 = cs.getEmoneyDetail(conn,snsUserInfo, price, ArithUtil.sub(emoney, price), 2, "报单购物", "报单购物", date);
					slist.add(sql4);
					String sql5 = cs.getIntegralDetail(conn,snsUserInfo, price, ArithUtil.add(integral, price), 1, "报单购物", "报单购物", date);
					slist.add(sql5);
				if(slist.size()>0){
					for(int i=0;i<slist.size();i++){
						stmt.addBatch(slist.get(i));
						 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
							 stmt.executeBatch();
							 stmt = conn.createStatement();
						 }
					}
				}
				message="订单提交成功！";
				}else message ="账户余额不足，请及时充值！";
				}else message ="会员信息获取失败！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_save_msg_1.jsp");
			dispatcher.forward(request, response);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		List  result = new ArrayList();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			//if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product where belong_area like'%报单专区%' and state='1' order by id desc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				request.setAttribute("productName", productName);
				request.setAttribute("typeName", typeName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					result.add(product);
				}
				System.out.println("size:"+result.size());
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
				String sql1 = "select * from product_type where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
					coll_pt.add(pt);
				}
				request.setAttribute("coll_pt", coll_pt);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void detail_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		try {
			if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("id"));
				String sql1 = "select * from product where code='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("product", product);
					String sql0 = "select * from product_type where name='"+product.getType()+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						
						String[] str1 = product.getAttribute().split(";");
						int num1 = str1.length;
						int max =0;
						for(int i=0;i<str1.length;i++){
							if(str1[i]!=null){
								String[] str2 = str1[i].split(":");
								if(str2.length>1){
								 String[] str3  =str2[1].split(",");
										if(max<str3.length) max=str2.length;
								}
							}
						}
						String[] attribute = new String[num1];
						Integer[] attributeId = new Integer[num1];
						String[][] attributeValue = new String[num1][max];
						for(int i=0;i<str1.length;i++){
							if(str1[i]!=null){
								String[] str2 = str1[i].split(":");
								if(str2[0]!=null) attribute[i]=str2[0];
								if(str2[1]!=null){
									String[] str3 = str2[1].split(",");
								for(int j=0;j<str3.length;j++){
									if(str3[j]!=null){
										attributeValue[i][j]=str3[j];
									}
								}
							}
							}
						}
						for(int i=0;i<attribute.length;i++){
							String sql2 = "select id from product_type_to_attribute where type_id='"+pt.getId()+"' and attribute_name='"+attribute[i]+"' and state='1'";
							stmt= conn.createStatement();
							rs =stmt.executeQuery(sql2);
							if(rs.next()){
								attributeId[i] = rs.getInt("id");
							}
						}
						request.setAttribute("attributeValue", attributeValue);
						request.setAttribute("attributeId", attributeId);
						request.setAttribute("attribute", attribute);
					}
				}
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
		}else {
			request.setAttribute("message", "用户未登陆,保存失败！");
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail_2.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void addShopCart_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		String message ="";
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_integral_list");
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("code"));
				String numstr = StringUtil.notNull(request.getParameter("num"));
				String sql1 = "select * from product where code='"+id+"'";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				if(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(Integer.valueOf(numstr));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					request.setAttribute("product", product);
					String attr ="";
					String sql0 = "select * from product_type where name='"+product.getType()+"' and state='1' order by id asc";
					stmt= conn.createStatement();
					rs =stmt.executeQuery(sql0);
					if(rs.next()){
						ProductType pt = new ProductType();
						pt.setName(rs.getString("name"));
						pt.setId(rs.getInt("id"));
						
						String[] str1 = product.getAttribute().split(";");
						
						for(int i=0;i<str1.length;i++){
								if(str1[i]!=null){
									String[] str2 = str1[i].split(":");
									if(str2[0]!=null) {
										int sid = 0;
										String sql2 = "select id from product_type_to_attribute where type_id='"+pt.getId()+"' and attribute_name='"+str2[0]+"' and state='1'";
										stmt= conn.createStatement();
										rs =stmt.executeQuery(sql2);
										if(rs.next()){
											sid = rs.getInt("id");
										}
										if(sid>0){
											String feature = request.getParameter("feature"+sid);
											if(StringUtil.notNull(feature).equals("")) message="产品属性选择不完整！";
											else attr=attr+str2[0]+":"+feature+";";
										}
									}
									
								}
							}
						product.setAttribute(attr);
						}//end select sql0
					if(message.equals("")){
						int t=0;
						if(plist!=null){
							for(int i=0;i<plist.size();i++){
								if(plist.get(i).getCode().equals(id)){
									if(plist.get(i).getAttribute().equals(product.getAttribute())){
										plist.get(i).setNum(plist.get(i).getNum()+Integer.valueOf(numstr));
										t++;
									}
								}
							}
						}else plist = new ArrayList<Product>();
						if(t==0) plist.add(product);
						request.getSession().setAttribute("shop_cart_integral_list",plist);
						message= "成功加入购物车！";
					}
					}else {
						message= "产品信息获取失败,保存失败！";
					}
				
			}else {
				message= "数据库连接已断开,保存失败！";
			}
		}else {
			message= "用户未登陆,保存失败！";
		}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("message", message);
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
	
	public void increate_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_integral_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.get(tag).setNum(plist.get(tag).getNum()+1);
					}
					
						request.getSession().setAttribute("shop_cart_integral_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart_2.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void disDe_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_integral_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
						if(plist.get(tag).getNum()-1>0)
							plist.get(tag).setNum(plist.get(tag).getNum()-1);
					}
					
						request.getSession().setAttribute("shop_cart_integral_list",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart_2.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void del_pro_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_integral_list");
		try {
		//	if(snsUserInfo!=null){
				String numstr = StringUtil.notNull(request.getParameter("tag"));
				if(plist!=null){
					int tag  =Integer.valueOf(numstr);
					if(plist.size()>tag){
							plist.remove(tag);
					}
						request.getSession().setAttribute("shop_cart_list_2",plist);
					
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("shopcart_2.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void shop_cart_save_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_integral_list");
		String message ="";
		try {
		if(snsUserInfo!=null){
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
				String sql = "select * from adr where user_id='"+snsUserInfo.getUserId()+"' and tag='1'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				if(rs.next()){
					Address adr = new Address();
					adr.setId(rs.getInt("id"));
					adr.setTel(rs.getString("tel"));
					adr.setReciver(rs.getString("reciver"));
					adr.setUserId(rs.getString("user_id"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("adr"));
					request.setAttribute("sns_adr",adr);
				}
				request.getSession().setAttribute("splist_2",slist);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cart_save_2.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void shop_save_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("splist_2");
		String message ="";
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				 boolean autoCommit= conn.getAutoCommit();
				 conn.setAutoCommit(false);
				String adr_id = request.getParameter("adr_id");
				String sql = "select * from adr where id='"+adr_id+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sql);
				Address adr = new Address();
				if(rs.next()){
					adr.setId(rs.getInt("id"));
					adr.setTel(rs.getString("tel"));
					adr.setReciver(rs.getString("reciver"));
					adr.setUserId(rs.getString("user_id"));
					adr.setProvince(rs.getString("province"));
					adr.setCity(rs.getString("city"));
					adr.setArea(rs.getString("area"));
					adr.setAdr(rs.getString("adr"));
					request.setAttribute("sns_adr",adr);
				}
				String sqlw = "select * from weixin_userinfo where user_id='"+snsUserInfo.getUserId()+"'";
				stmt =conn.createStatement();
				rs= stmt.executeQuery(sqlw);
				if(rs.next()){
					double emoney = rs.getDouble("integral");
				Timestamp date = new Timestamp(new Date().getTime());
				String orderId = StringUtil.parseToString(date, DateUtil.ymdhms);
				double price = 0;
				double pv=0;
				List<String> slist = new ArrayList<String>();
				for(int i=0;i<plist.size();i++){
					price = price+plist.get(i).getPrice()*plist.get(i).getNum();
					pv = pv +plist.get(i).getPv()*plist.get(i).getNum();
					String sql1="insert into order_detail(order_id,product_id,product_name,product_type,attribute,product_price,product_pv,num,price,pv)"
							+ " values('"+orderId+"','"+plist.get(i).getCode()+"','"+plist.get(i).getName()+"','"+plist.get(i).getProductType()+"','"+plist.get(i).getAttribute()
							+"','"+plist.get(i).getPrice()+"','"+plist.get(i).getPv()+"','"+plist.get(i).getNum()+"','"+plist.get(i).getPrice()*plist.get(i).getNum()+"','"+plist.get(i).getPv()*plist.get(i).getNum()+"');";
					slist.add(sql1);
				}
				String sql2= "insert into orders(order_id,uid,user_id,receiver,phone,address,"
						+ "price,pv,order_type,order_time,state) "
						+ " values('"+orderId+"','"+snsUserInfo.getId()+"','"
						+snsUserInfo.getUserId()+"','"+adr.getReciver()+"','"+adr.getTel()
						+"','"+adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAdr()+"','"+price+"','"+pv+"','2','"+date+"','1');";
				
				slist.add(sql2);
				if(ArithUtil.sub(emoney, price)>=0){
					String sql3 = "update weixin_userinfo set integral='"+ArithUtil.sub(emoney, price)+"' where user_id='"+snsUserInfo.getUserId()+"'";
					slist.add(sql3);
					String sql4 = cs.getIntegralDetail(conn,snsUserInfo, price, ArithUtil.sub(emoney, price), 2, "报单购物", "报单购物", date);
					slist.add(sql4);
				if(slist.size()>0){
					for(int i=0;i<slist.size();i++){
						stmt.addBatch(slist.get(i));
						 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
							 stmt.executeBatch();
							 stmt = conn.createStatement();
						 }
					}
				}
				message="订单提交成功！";
				}else message ="账户余额不足，请及时充值！";
				}else message ="会员信息获取失败！";
				conn.commit();
				 conn.setAutoCommit(autoCommit);
			}else{
				message="数据库连接失败！";
			}
		}else{
			message="用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="数据异常，错误提示："+e;
			e.printStackTrace();
		}finally{
			db.close();
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_save_msg_2.jsp");
			dispatcher.forward(request, response);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void list_2(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		Collection coll_pt = new ArrayList();
		List  result = new ArrayList();
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		int pageNo = 1;
		int pageSize = 10;
		try {
			//if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String sql = "select * from product where belong_area like'%积分专区%' and state='1' order by id desc";	
				stmt= conn.createStatement();
				rs =stmt.executeQuery(sql);
				request.setAttribute("productName", productName);
				request.setAttribute("typeName", typeName);
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCode(rs.getString("code"));
					product.setType(rs.getString("type"));
					product.setAttribute(rs.getString("attribute"));
					product.setPrice(rs.getDouble("price"));
					product.setPv(rs.getDouble("pv"));
					product.setIsGroup(rs.getInt("is_group"));
					product.setIntegral(rs.getDouble("integral"));
					product.setNum(rs.getInt("num"));
					product.setTotalNum(rs.getInt("total_num"));
					product.setImageUrl1(StringUtil.notNull(rs.getString("image_url_1")));
					product.setImageUrl2(StringUtil.notNull(rs.getString("image_url_2")));
					product.setImageUrl3(StringUtil.notNull(rs.getString("image_url_3")));
					product.setImageUrl4(StringUtil.notNull(rs.getString("image_url_4")));
					product.setImageUrl5(StringUtil.notNull(rs.getString("image_url_5")));
					product.setDescription(rs.getString("description"));
					product.setState(rs.getInt("state"));
					product.setEntryTime(rs.getTimestamp("entry_time"));
					product.setEndTime(rs.getTimestamp("end_time"));
					result.add(product);
				}
				System.out.println("size:"+result.size());
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
				String sql1 = "select * from product_type where state!='0' order by id asc";
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql1);
				while(rs.next()){
					ProductType pt = new ProductType();
					pt.setName(rs.getString("name"));
					coll_pt.add(pt);
				}
				request.setAttribute("coll_pt", coll_pt);
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_2.jsp");
			dispatcher.forward(request, response);
		}
	}


}

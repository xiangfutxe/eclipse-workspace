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
import com.pojo.Address;
import com.pojo.Admin;
import com.pojo.Order;
import com.pojo.Product;
import com.pojo.ProductType;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IUserService;
import com.service.UserService;
import com.utils.DateUtil;
import com.utils.Pager;
import com.utils.StringUtil;
import com.weixin.pojo.WeixinUserInfo;

public class OrderServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	IUserService us = new UserService();
	
	ICustomService cs = new CustomService();
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		}else if(method.equals("orders_list")){
			orders_list(request,response);
		}else if(method.equals("vip_add")){
			vip_list(request,response);
		}else if(method.equals("vip_detail")){
			vip_detail(request,response);
		}else if(method.equals("vip_select")){
			vip_select(request,response);
		}else if(method.equals("vip_save")){
			vip_save(request,response);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void orders_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		Collection coll = new ArrayList();
		List  result = new ArrayList();
		String orderType = StringUtil.notNull(request.getParameter("orderType"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String state = StringUtil.notNull(request.getParameter("state"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 10;
		try {
			if(user!=null){
			if(db.createConn()){
				String sql ="";
				if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
					String startTime = startTimeStr+" 00:00:00";
					String  endTime = endTimeStr+" 23:59:59";
					if(state.equals("")){
						if(orderType.equals(""))
							sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  user_id='"+user.getUserId()+"' order by id desc";
						else sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  order_type = '"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
					}else{
						if(orderType.equals(""))
							sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  state ='"+state+"' and user_id='"+user.getUserId()+"' order by id desc";
						else 
							sql ="select * from orders where order_time>='"+startTime+"' and order_time<='"+endTime+"' and  state='"+state+"' and order_type='"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
					}
					}else{
						if(state.equals("")){
							if(orderType.equals(""))
								sql ="select * from orders where  user_id='"+user.getUserId()+"' order by id desc";
							else sql ="select * from orders where order_type = '"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
						}else{
							if(orderType.equals(""))
								sql ="select * from orders where  state ='"+state+"' and user_id='"+user.getUserId()+"' order by id desc";
							else 
								sql ="select * from orders where state='"+state+"' and order_type='"+orderType+"' and user_id='"+user.getUserId()+"' order by id desc";
						}
						}
						
				System.out.println(sql);
				stmt= db.getStmtread();
				rs =stmt.executeQuery(sql);
				while(rs.next()){
					Order orders = new Order();
					orders.setId(rs.getInt("id"));
					orders.setOrderId(rs.getString("order_id"));
					orders.setUserId(rs.getString("user_id"));
					orders.setUserName(rs.getString("user_name"));
					orders.setOrderType(rs.getInt("order_type"));
					orders.setOrderTime(rs.getTimestamp("order_time"));
					orders.setReceiver(rs.getString("receiver"));
					orders.setPhone(rs.getString("phone"));
					orders.setAddress(rs.getString("address"));
					orders.setState(rs.getInt("state"));
					orders.setPrice(rs.getDouble("price"));
					orders.setPv(rs.getDouble("pv"));
					result.add(orders);
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
				request.setAttribute("state",state);
				request.setAttribute("orderType",orderType);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("orders.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void vip_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
			if(snsUserInfo!=null){
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
			
			}else {
				request.setAttribute("message", "用户未登陆！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("vip_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void vip_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("vip_detail.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void vip_select(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		String message ="";
		List<Product> plist = new ArrayList<Product>();
		try {
		if(snsUserInfo!=null){
			if(db.createConn()){
				conn = db.getConnection();
				String id = StringUtil.notNull(request.getParameter("code"));
				String numstr = StringUtil.notNull(request.getParameter("prodCount"));
				String sql1 = "select * from product where code='"+id+"'";
				request.setAttribute("sql1", sql1);
				stmt= conn.createStatement();
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
											if(StringUtil.notNull(feature).equals(""))
												message="产品属性选择不完整！";
											else attr=attr+str2[0]+":"+feature+";";
										}
									}
									
								}
							}
						product.setAttribute(attr);
						}//end select sql0
					if(message.equals("")){
						plist.add(product);
						request.getSession().setAttribute("vip_shop_list",plist);
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
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message= "数据格式有误，请核对！";
			e.printStackTrace();
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("vip_select.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("vip_msg.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void vip_save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo snsUserInfo = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
		DBConn db = new DBConn();
		List<Product> plist = (List<Product>) request.getSession().getAttribute("vip_shop_list");
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
				int rankManage = 0;
				if(price>=9800) rankManage=3;
				else if(price>=3980) rankManage=2;
				else if(price>=98) rankManage=1;
				if(snsUserInfo.getRankManage()<rankManage){
					String sql3="update weixin_userinfo set rank_manage='"+rankManage+"' where open_id='"+snsUserInfo.getOpenId()+"'";
					slist.add(sql3);
					snsUserInfo.setRankManage(rankManage);
					request.getSession().setAttribute("snsUserInfo", snsUserInfo);
				}
				
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("vip_msg.jsp");
			dispatcher.forward(request, response);
		}
	}
}

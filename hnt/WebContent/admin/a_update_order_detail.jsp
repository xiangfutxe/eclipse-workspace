<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.pojo.Product" %>

<%@page import="com.ssm.pojo.OrderDetail" %>
<%@page import="com.ssm.pojo.OrderDelivery" %>
<%@page import="com.ssm.pojo.OrderDeliveryDetail" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.dao.OrderDao" %>
<%@page import="com.ssm.dao.ProductDao" %>

<%@page import="com.ssm.dao.OrderDetailDao" %>
<%@page import="com.ssm.dao.OrderDeliveryDao" %>
<%@page import="com.ssm.dao.OrderDeliveryDetailDao" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%@page import="com.ssm.utils.Pager" %>
<%@page import="java.sql.Timestamp" %>
<%
try{
User sel_user = new User();
Timestamp startTime = new Timestamp(StringUtil.parseToDate("2017-11-11 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
Timestamp endTime = new Timestamp(StringUtil.parseToDate("2017-11-13 12:00:00", DateUtil.yyyyMMddHHmmss).getTime());
sel_user.setEndTime(endTime);
Pager  pager = new Pager();
pager.setPageNo(1);
pager.setPageSize(100);
OrderDao orderDao = new OrderDao();

Order order1 = new Order();
Order order2 = new Order();
order1.setOrderType(2);
order1.setStartTime(startTime);
order1.setEndTime(endTime);
order2.setOrderType(3);
order2.setStartTime(startTime);
order2.setEndTime(endTime);
List<OrderDetail> olist = (List<OrderDetail>)orderDao.findOrderDetailByList(order1);
 orderDao = new OrderDao();
List<OrderDetail> olist1 = (List<OrderDetail>)orderDao.findOrderDetailByList(order2);
olist.addAll(olist1);
out.println(olist.size());
List<OrderDetail> insert_olist = new ArrayList<OrderDetail>();
for(int i=0;i<olist.size();i++){
	OrderDetail od1 = new OrderDetail();
	OrderDetail od2 = new OrderDetail();
	int num = olist.get(i).getNum();
	od1.setId(olist.get(i).getId());
	od1.setNum(num);
	ProductDao productDao = new ProductDao();
	Product product = productDao.findProductByName(olist.get(i).getProductId());
	if(product!=null){
	productDao = new ProductDao();
	Product giv_pro = productDao.findProductByName(product.getGiveProductId());
		if(giv_pro!=null){
			od2.setOrderId(olist.get(i).getOrderId());
			od2.setPid(giv_pro.getId());
			od2.setProductId(giv_pro.getProductId());
			od2.setProductName(giv_pro.getProductName());
			od2.setSpecification(giv_pro.getSpecification());
			od2.setProductType(giv_pro.getProductType());
			od2.setProductPrice((double)0);
			od2.setProductPv((double)0);
			od2.setType(giv_pro.getType());
			od2.setPrice((double)0);
			od2.setPv((double)0);
			od2.setNum(num);
			insert_olist.add(od2);
			productDao =null;
	}
}
if(i%100==0){
out.println(insert_olist.size()+";<br>");
OrderDetailDao odDao = new OrderDetailDao();
odDao.saveAll(insert_olist);
 insert_olist = null;
 insert_olist = new ArrayList<OrderDetail>();
}
}
if(insert_olist!=null&&insert_olist.size()>0){
out.println(insert_olist.size()+";<br>");
OrderDetailDao odDao = new OrderDetailDao();
odDao.saveAll(insert_olist);
 insert_olist = null;
 insert_olist = new ArrayList<OrderDetail>();
}
}catch(Exception e){
e.printStackTrace();
}
out.println("OK");
%>

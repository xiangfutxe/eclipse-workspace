<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.OrderDetailProduct" %>

<%@page import="com.ssm.pojo.OrderDetail" %>
<%@page import="com.ssm.pojo.OrderDelivery" %>
<%@page import="com.ssm.pojo.OrderDeliveryDetail" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.dao.OrderDao" %>
<%@page import="com.ssm.dao.ProductDao" %>

<%@page import="com.ssm.dao.OrderDetailProductDao" %>

<%@page import="com.ssm.dao.OrderDetailDao" %>
<%@page import="com.ssm.dao.OrderDeliveryDao" %>
<%@page import="com.ssm.dao.OrderDeliveryDetailDao" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.utils.ArithUtil" %>

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
OrderDeliveryDao orderDeliveryDao = new OrderDeliveryDao();
OrderDao orderDao = new OrderDao();
OrderDeliveryDetailDao orderDeliveryDetailDao = new OrderDeliveryDetailDao();

Order order1 = new Order();
Order order2 = new Order();
order1.setOrderType(2);
order1.setStartTime(startTime);
order1.setEndTime(endTime);
order2.setOrderType(3);
order2.setStartTime(startTime);
order2.setEndTime(endTime);
List<Order> olist = (List<Order>)orderDao.findByList(order1);
 orderDao = new OrderDao();
List<Order> olist1 = (List<Order>)orderDao.findByList(order2);
olist.addAll(olist1);
out.println(olist.size()+"<br>");
List<OrderDeliveryDetail> insert_olist = new ArrayList<OrderDeliveryDetail>();
for(int i=0;i<olist.size();i++){
out.println(olist.get(i).getOrderId());
orderDeliveryDao = new OrderDeliveryDao();
List<OrderDelivery> odylist =  orderDeliveryDao.findByList(olist.get(i).getOrderId());
for(int j=0;j<odylist.size();j++){
orderDeliveryDetailDao = new OrderDeliveryDetailDao();
List<OrderDeliveryDetail> odlist =  orderDeliveryDetailDao.findByOrderId(odylist.get(j).getOrderId());
double totalPrice =0;
double totalPv = 0;
	for(int s=0;s<odlist.size();s++){
	OrderDeliveryDetail od1 = new OrderDeliveryDetail();
	OrderDeliveryDetail od2 = new OrderDeliveryDetail();
	int num = odlist.get(s).getNum();
	double price = ArithUtil.mul(odlist.get(s).getProductPrice(),num);
	double pv = ArithUtil.mul(odlist.get(s).getProductPrice(),num);
	totalPrice = totalPrice+price;
	totalPv = totalPv+pv;
	ProductDao productDao = new ProductDao();
	Product product = productDao.findProductByName(odlist.get(s).getProductId());
	if(product!=null){
	productDao = new ProductDao();
	Product giv_pro = productDao.findProductByName(product.getGiveProductId());
		if(giv_pro!=null){
			od2.setOid(odlist.get(s).getOid());
			od2.setOrderId(odlist.get(s).getOrderId());
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
}
}
if(i%100==0){
OrderDeliveryDetailDao odDao = new OrderDeliveryDetailDao();
odDao.saveAll(insert_olist);
 insert_olist = null;
 insert_olist = new ArrayList<OrderDeliveryDetail>();
}
}
if(insert_olist!=null&&insert_olist.size()>0){
out.println(insert_olist.size()+";<br>");
OrderDeliveryDetailDao odDao = new OrderDeliveryDetailDao();
odDao.saveAll(insert_olist);
 insert_olist = null;
 insert_olist = new ArrayList<OrderDeliveryDetail>();
}
}catch(Exception e){
e.printStackTrace();
out.println(e.getStackTrace());
}
out.println("OK");
%>

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
List<OrderDetailProduct> olist = (List<OrderDetailProduct>)orderDao.findOrderDetailProductByList(order1);
 orderDao = new OrderDao();
List<OrderDetailProduct> olist1 = (List<OrderDetailProduct>)orderDao.findOrderDetailProductByList(order2);
out.println(olist.size()+"<br>");
out.println(olist1.size()+"<br>");
olist.addAll(olist1);
out.println(olist.size()+"<br>");
List<OrderDetailProduct> insert_olist = new ArrayList<OrderDetailProduct>();
for(int i=0;i<olist.size();i++){
	OrderDetailProduct od1 = new OrderDetailProduct();
	OrderDetailProduct od2 = new OrderDetailProduct();
	int num = olist.get(i).getNum();
	int deliveryNum = olist.get(i).getDeliveryNum();
	od1.setId(olist.get(i).getId());
	od1.setNum(num);
	od1.setDeliveryNum(deliveryNum);
	ProductDao productDao = new ProductDao();
	out.println(olist.get(i).getProductId()+";");
	Product product = productDao.findProductByName(olist.get(i).getProductId());
	if(product!=null){
	productDao = new ProductDao();
	Product giv_pro = productDao.findProductByName(product.getGiveProductId());
		if(giv_pro!=null){
			od2.setOdId(olist.get(i).getOdId());
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
			od2.setDeliveryNum(deliveryNum);
			insert_olist.add(od2);
			productDao =null;
	}
}
if(i%100==0){
out.println(insert_olist.size()+";<br>");
OrderDetailProductDao odDao = new OrderDetailProductDao();
odDao.saveAll(insert_olist);
 insert_olist = null;
 insert_olist = new ArrayList<OrderDetailProduct>();
}
}
if(insert_olist!=null&&insert_olist.size()>0){
out.println(insert_olist.size()+";<br>");
OrderDetailProductDao odDao = new OrderDetailProductDao();
odDao.saveAll(insert_olist);
 insert_olist = null;
 insert_olist = new ArrayList<OrderDetailProduct>();
}
}catch(Exception e){
e.printStackTrace();
out.println(e.getStackTrace());
}
out.println("OK");
%>

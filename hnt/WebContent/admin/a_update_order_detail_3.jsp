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
List<Order> orlist = (List<Order>)orderDao.findByList(order1);
orderDao = new OrderDao();

List<OrderDetailProduct> olist = (List<OrderDetailProduct>)orderDao.findOrderDetailProductByList(order1);

/*
 orderDao = new OrderDao();
List<Order> olist1 = (List<Order>)orderDao.findByList(order2);
*/
out.println(olist.size()+"<br>");
out.println(olist.size()+"<br>");
for(int i=0;i<orlist.size();i++){
orderDeliveryDao = new OrderDeliveryDao();
List<OrderDelivery> odylist =  orderDeliveryDao.findByList(orlist.get(i).getOrderId());
for(int j=0;j<odylist.size();j++){
/*
OrderDelivery od = new OrderDelivery();
		od.setId(odylist.get(j).getId());
		od.setPrice(ArithUtil.mul(odylist.get(j).getPrice(),2));
		od.setPv(ArithUtil.mul(odylist.get(j).getPv(),2));
		orderDeliveryDao = new OrderDeliveryDao();
		orderDeliveryDao.update(od);
*/		
orderDeliveryDetailDao = new OrderDeliveryDetailDao();
List<OrderDeliveryDetail> odlist =  orderDeliveryDetailDao.findByOrderId(odylist.get(j).getOrderId());
//List<OrderDeliveryDetail> odlist1 = new ArrayList<OrderDeliveryDetail>();

for(int s=0;s<odlist.size();s++){	
	if(odlist.get(s).getPrice()>0){
	for(int t=0;t<olist.size();t++){
		if(olist.get(t).getProductId().equals(odlist.get(s).getProductId())&&olist.get(t).getOrderId().equals(orlist.get(i).getOrderId())){
			OrderDeliveryDetail odd = new OrderDeliveryDetail();
			odd.setId(odlist.get(s).getId());
			odd.setOid(olist.get(t).getId());
			odd.setNum(olist.get(t).getNum());
			odd.setPrice(olist.get(t).getPrice());
			odd.setPv(olist.get(t).getPv());
			orderDeliveryDetailDao = new OrderDeliveryDetailDao();
			orderDeliveryDetailDao.Update(odd);
			break;
		}
	}
	}
}
}
}
}catch(Exception e){
e.printStackTrace();
out.println(e.getStackTrace());
}
out.println("OK");
%>

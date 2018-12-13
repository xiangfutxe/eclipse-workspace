<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Order" %>
<%@page import="com.pojo.OrderDetail" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
Order orders = (Order)request.getAttribute("orders");
String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image"
	href="../images/splash/splash-screen.png"
	media="screen and (max-device-width: 320px)" />
<link rel="apple-touch-startup-image"
	href="../images/splash/splash-screen_402x.png"
	media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="640x1096"
	href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748"
	href="../images/splash/splash-screen-ipad-landscape"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004"
	href="../images/splash/splash-screen-ipad-portrait.png"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008"
	href="../images/splash/splash-screen-ipad-portrait-retina.png"
	media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048"
	href="../images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />

<title>订单详情</title>

<link href="../styles/style.css" rel="stylesheet" type="text/css">
<link href="../styles/framework.css" rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css" rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/order.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>

</head>
<body>

	<div class="top-deco"></div>

	<div class="content">
		<div class="header">
			<a href="#" class="homepage-logo"> <img
				src="../images/misc/index-logo.png" alt="img"> </a>
			<p class="go-title-ch">订单详情</p>
			<p class="go-title-en">ORDER DETAIL</p>
			<a href="javascript:history.go(-1);" class="go-logo">返回</a> <a
				href="main.jsp" class="go-home">首页</a> <a href="#" class="go-menu">菜单</a>
			<a href="#" class="go-back">关闭</a>
		</div>
		<div class="decoration"></div>
		<div class="navigation">
			<div class="corner-deco"></div>
			<div class="navigation-wrapper">
			  <%@ include file="menu.jsp" %>  
			</div>
		</div>
	</div>
	<div class="content">
		<div class="container">
			<div class="container no-bottom">
					
				<h4>收货信息：</h4>
					<table class="table-detail">
						<tr>
						<td>订单编号/下单时间</td>
						<td><%=StringUtil.notNull(orders.getOrderId())%><br><%=StringUtil.parseToString(orders.getOrderTime(), DateUtil.yyyyMMddHHmmss)%></td>
							</tr>
						
						<tr>
						<td>收件人/收货地址</td>
						<td><%=StringUtil.notNull(orders.getReceiver())%><br><%=StringUtil.notNull(orders.getAddress()) %></td>
							</tr>
						
						<tr>
						<td>联系电话/当前状态</td>
						<td><%=StringUtil.notNull(orders.getPhone()) %>
							<br><%if(orders.getState()==1){%>待确认<%}else if(orders.getState()==2){%>待发货<%}else if(orders.getState()==3){%>出库中<%}else if(orders.getState()==4){%>已发货<%}else if(orders.getState()==5){%>已收货<%}else if(orders.getState()==0){%>已退订<%} %>
							</td></tr>
						
						<tr>
						<td>快递公司/快递单号</td>
						<td><%=StringUtil.notNull(orders.getExpress()) %><br>
							<%=StringUtil.notNull(orders.getExpressNum()) %></td></tr>
					</table>
					</div>
				<div class="container no-bottom">
					
				<h4>商品清单：</h4>
					<table class="table-detail">
						<tr>
							<td>产品编码<br>产品名称</td>
							<td>规格<br>类型</td>
							<td>产品单价（元）<br>购买数量</td>
							<td>金额小计</td>
						</tr>
						 <%
								 Collection coll =  (Collection)request.getAttribute("coll");
						if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									OrderDetail od = (OrderDetail)it.next();
									t++;
							%>
								<tr>
									<td><%if(od.getType()==2){ %><a href="ProductServlet.do?method=product_detail&id=<%=StringUtil.notNull(od.getProductId())%>" title="点击查看产品清单"  target="_blank"><%=StringUtil.notNull(od.getProductId())%></a>
										<%}else{ %><%=StringUtil.notNull(od.getProductId())%><%} %><br>
									<%=StringUtil.notNull(od.getProductName())%>
									</td>
									<td><%=StringUtil.notNull(od.getSpecification())%><br>
									<%if(od.getType()==2){ %>套餐<%}else if(od.getType()==1) {%>单品<%}else{ %>-<%} %>
									</td>
									<td><%=StringUtil.decimalFormat(od.getProductPrice()) %><br><%=StringUtil.decimalFormat(od.getNum()) %></td>
									<td><%=StringUtil.decimalFormat(od.getPrice()) %>
   
									</td>
								</tr>
   		<%}
						}}%>
							<tr>
						
							<td colspan="2">&nbsp;</td><td>合计</td>
									<td><%=StringUtil.decimalFormat(orders.getPrice()) %>
    </td>
    
										
								</tr>
					</table>
					</div>
				</div>
				<div class="container no-bottom">
				 <div class="container">
            <a href="#" class="button button-dark" onclick="javascript:history.go(-1);">返回上一页</a>
             <a href="OrderServlet.do?method=orders" class="button button-dark">订单列表</a>
        </div>
				</div>
	</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
	<div style="height:28px;"></div>
	<div class="bottom-deco"></div>


</body>
</html>
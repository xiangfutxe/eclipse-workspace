<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.OrderDelivery"%>

<%@page import="com.ssm.utils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.Pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
int atag=3;
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String state=StringUtil.notNull((String)request.getAttribute("state"));
if(user==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
 %>
<!DOCTYPE html>
<html>
<head>
 <script src="js/rem.js"></script> 
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/detail/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/page.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/loading.css"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>我的配货单</title>

</head>
<body>
<header class="mui-bar mui-bar-nav report-header box-s" id="header">
			<a href="javascript:history.go(-1)"><i class="iconfont icon-fanhui fl"></i></a>
			<p>物流跟踪</p>
	    </header>
	    <div id="main" class="mui-clearfix">
	    	<div class="track clearfloat">
	    		<div class="warning clearfloat box-s">
	    			<p class="num">订单号：${orders.orderId}</p>
	    		</div>
	    		<div class="state clearfloat box-s">
	    			<ul>
	    				<li>快递状态：<c:if test="${orders.state==1 }">待确认</c:if>
	    				<c:if test="${orders.state==2 }">待发货</c:if>
	    				<c:if test="${orders.state==3 }">已发货</c:if>
	    				<c:if test="${orders.state==4 }">已签收</c:if>
	    				<c:if test="${orders.state==0 }">已退单</c:if></li>
	    				<li>快递公司：${orders.express }</li>
	    				<li>快递单号：${orders.expressNum }</li>
	    			</ul>
	    		</div>
	    		
	    		<div class="process clearfloat box-s">
	    			
	    		
	    			<c:if test="${orders.state>=3 }">
	    			<div class="list clearfloat">
	    				<i class="iconfont dian"></i>
	    				<div class="you clearfloat">
	    					<p class="tit">公司已发货，请继续跟踪物流信息</p>
	    					<p class="time"><fmt:formatDate value="${orders.confirmTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	    				</div>
	    			</div>
	    			</c:if>
	    			<c:if test="${orders.state>=2 }">
	    			<div class="list clearfloat">
	    				<i class="iconfont dian"></i>
	    				<div class="you clearfloat">
	    					<p class="tit">公司已确认，正在配货中</p>
	    					<p class="time"><fmt:formatDate value="${orders.confirmTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	    				</div>
	    			</div>
	    			</c:if>
	    			<c:if test="${orders.state>=1 }">
	    			<div class="list clearfloat">
	    				<i class="iconfont dian"></i>
	    				<div class="you clearfloat">
	    					<p class="tit">配货单提交成功，待确认</p>
	    					<p class="time"><fmt:formatDate value="${orders.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	    				</div>
	    			</div>
	    			</c:if>
	    		</div>
	    	</div>
	    </div>
	</body>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
	<script src="js/fastclick.js"></script>
	<script src="js/mui.min.js"></script>
	<script type="text/javascript" src="js/hmt.js" ></script>
</body>
</html>
<%}%>
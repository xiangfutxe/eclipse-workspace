<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getSession().getAttribute("sys_user");
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
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="../images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="../images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="../images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="../images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>用户管理首页</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript" src="../scripts/framework.launcher.js"></script>

</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	个人主页</p><p class="go-title-en">HOME PAGE
        </p>
        <a href="main.jsp" class="go-home">首页</a>
        <a href="#" class="go-menu">菜单</a>
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
    <div class="landing-navigation">
    <div class="landing-navigation-tablet-top-adjustment"></div> 
    	<a href="index.jsp">
        	<img class="nav-icon" src="../images/icons/misc/home.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/1s.jpg" alt="img">
            <em>欢迎页面</em>
        </a>
    	<a href="UserServlet.do?method=user_detail">
        	<img class="nav-icon" src="../images/icons/misc/user.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/3s.jpg" alt="img">
            <em>个人资料</em>
        </a>
    	
    	<a href="AddressServlet.do?method=list">
        	<img class="nav-icon" src="../images/icons/misc/end.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/4s.jpg" alt="img">
            <em>地址管理</em>
        </a>
        <%if(user.getRankJoin()<3) {%>
         <a href="UserServlet.do?method=rankJoin_up">
        	<img class="nav-icon" src="../images/icons/misc/user.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/3s.jpg" alt="img">
            <em>经销商升级</em>
        </a>
        <%} %>
        <a href="OrderServlet.do?method=order_emoney_add">
        	<img class="nav-icon" src="../images/icons/shop/shopcart.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/5s.jpg" alt="img">
            <em>再次购物</em>
        </a>
        <a href="OrderServlet.do?method=orders">
        	<img class="nav-icon" src="../images/icons/misc/order.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/2s.jpg" alt="img">
            <em>订单管理</em>
        </a>
    	
        <a href="receiver_info.jsp">
        	<img class="nav-icon" src="../images/icons/electrical/emoney.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/4s.jpg" alt="img">
            <em>结账信息</em>
        </a>
        <a href="UserServlet.do?method=login_out">
        	<img class="nav-icon" src="../images/icons/misc/exit.png" alt="img">
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="../images/general-nature/3s.jpg" alt="img">
            <em>退出系统</em>
        </a>
        <div class="clear"></div>
        <div class="landing-navigation-tablet-bottom-adjustment"></div> 
    </div>
    
  
    <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  

<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
</html>
<%}%>
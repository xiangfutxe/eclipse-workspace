<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User"%>
<%@page import="com.pojo.News"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getSession().getAttribute("sys_user");
	News news = (News) request.getSession().getAttribute("sys_news");
	
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
	if(news!=null){
		} 
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0 minimal-ui"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/splash/splash-icon.png">
<link rel="apple-touch-icon-precomposed" sizes="180x180" href="images/splash/splash-icon-big.png">
<link rel="apple-touch-startup-image" href="images/splash/splash-screen.png" 	media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="images/splash/splash-screen@2x.png" media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" href="images/splash/splash-screen-six.png" media="(device-width: 375px)">
<link rel="apple-touch-startup-image" href="images/splash/splash-screen-six-plus.png" media="(device-width: 414px)">
<link rel="apple-touch-startup-image" sizes="640x1096" href="images/splash/splash-screen@3x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>内而外会员采购平台</title>

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
<script src="js/ui.js"></script>
<script type="text/javascript">
mizhu.open(600, 400, '新浪微博', 'http://weibo.com/yuanyuanforlove');
</script>
</head>

<body>
 
<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	欢迎</p><p class="go-title-en">WELCOME
        </p>
         <a href="javascript:history.go(-1);" class="go-logo">返回</a>
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
   <div id="content" class="snap-content">      
   
   <div class="slider-container full-bottom">
            <div class="homepage-slider" data-snap-ignore="true">                
                <div>
                    <img src="images/pictures/6.jpg" class="responsive-image" alt="img">
                </div>
        </div>
            </div>
        <div class="content">            
            <div class="container no-bottom">
                <h3>Welcome!</h3>
                
                <p>
                    你可以在右上角下拉列表选择你需要的功能，也可以点击<a href="main.jsp">个人主页</a>进入操作界面!
                </p>
            </div>
            <div class="decoration"></div>
<%if(news!=null){ %>
            <div class="content-heading full-bottom">
                <h2>最新动态</h2>
                 <h4 style="text-align:center;"><%=news.getTitle() %></h4>
                <p><%=news.getContents() %></p>
                <i class="fa fa-cog"></i>
            </div>
            <div class="decoration"></div>
<%} %>
            <div class="content-heading full-bottom">
                <h2>温馨提示：</h2>
                <p>1、为了保障您的个人账户安全，请不要使用公共场所的免费wifi登录系统。</p>
<p>2、请将您的登录密码和二级密码设置为8位以上，并用字符、字母和数字的组合。</p>
<p>3、请您在不选浏览页面时退出系统，已防止他人进入系统进行恶意操作。</p>
<p>4、如系统有任何的操作异常，请截图保存并及时联系管理员。</p>
                <i class="fa fa-cog"></i>
            </div>
           
		
           
    <div class="decoration"></div>
   </div>
</div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
</html>

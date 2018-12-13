<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User"%>
<%@page import="com.utils.StringUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getSession().getAttribute("sys_user");
	String message = (String) request.getAttribute("message");
	String token = (String)request.getSession().getAttribute("token");
String tag = (String)request.getAttribute("tag");
String userId = (String)request.getAttribute("userId");
String[] mid = (String[])request.getAttribute("mid");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
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

<title>会员注册</title>

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
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>

</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	会员注册</p><p class="go-title-en">MEMBER REGISTRATION
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
      
    <div class="content">
    <div class="container"> 
     <%if(!StringUtil.notNull(tag).equals("1")){ %>
 <div class="container no-bottom">
        <h3>信息提示：</h3>
						
						  <p>
            	<%=StringUtil.notNull(message)%>
             </p>
               <p>
          <a href="UserServlet.do?method=user_add">注册会员 </a> 
             </p>
    </div>
  
    <div class="decoration"></div>
    <%}else{ %>
    <div class="container no-bottom">
        <h3>信息提示：</h3>
						
						  <p>
            	<%=StringUtil.notNull(message)%>
             </p>
               
    </div>
  
    <div class="decoration"></div>
    <form action="UserServlet.do?method=user_update_id" id="myform" method="post">
							<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
							<input type="hidden" name="id" id="id" value="<%=StringUtil.notNull(userId)%>"/> 
							
    <div>
                            <h3>备选编号：</h3>
                            <p>
                            <select name="userId" id="userId" class="contactField">
                            <option value="<%=mid[1] %>"><%=mid[1] %></option>
                            <option value="<%=mid[2] %>"><%=mid[2] %></option></select>
                            </p>
					</div>
					 <div>
                            <h3>备注说明：</h3>
                            <p>如果无需修改默认会员编号，请直接退出该页面，如果需要修改，请选中心仪的编号，一旦修改将不能再修改！ &nbsp;</p>
					</div>
					<div>
					 <a href="#" id="sbtn" class="button button-orange">确认修改</a>&nbsp;
					 <a  href="UserServlet.do?method=user_add" id="btn" class="button button-blue">注册会员</a>
					 
        </div>
					</form>
					<%} %>
					 <div class="decoration"></div>
    </div>
    </div>
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
<script type="text/javascript">
	$(function(){
	 $("#sbtn").click(function(){
  	  if (!confirm("确认需要修改会员编号？")) {
				return false;
			}else{
			 $("#myform").submit();
		       		 return true;
	 }
	   });
        });
   </script>
</html>
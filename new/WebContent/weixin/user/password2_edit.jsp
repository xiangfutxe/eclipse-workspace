<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
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

<title>支付密码修改</title>

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
</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	支付密码修改</p><p class="go-title-en">EDIT PAYMENT PSW.
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
    <div class="container no-bottom">
      <div class="contact-form no-bottom"> 
              
               <form  action="UserServlet.do?method=user_password2_update"  id="myform" name="myform" method="post">
                    <fieldset>
                        <div>
							<label>用户信息：</label><input type="text" class="contactField" name="userId" id="userId" value="<%=StringUtil.notNull(user.getUserName())%>" readonly>
						</div>
					
			 <div>
							<label>旧密码：&nbsp; &nbsp;<span id="pwdTag"></span></label><input type="password" class="contactField" name="pwd" id="pwd">
							
						</div>
						 <div>
							<label>新密码：&nbsp;&nbsp;<span id="passwordTag"></span></label>
<input type="password" name="password" id="password" class="contactField">
							
						</div>
						 <div>
							<label>确认密码：&nbsp;&nbsp;<span id="repasswordTag"></span></label><input type="password" name="repassword" id="repassword" class="contactField">
							
						</div>
                        <div>
                            <input type="button" class="buttonWrap button button-green contactSubmitButton" id="sbtn" value="提交" />
                        </div>
                    </fieldset>
                </form>       
            </div>
        </div>
    </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>

<script type="text/javascript" src="../../js/custom.js"></script>
<script type="text/javascript">
$(function() {
	$("#pwd").blur(function() {
		if($("#pwd").val()=="")$("#pwdTag").text("旧密码不能为空！").css({"color":"red","font-size":"9px"});
		else $("#pwdTag").text("");
	});
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"9px"});
		else $("#passwordTag").text("");
	});
	$("#repassword").blur(function() {
		if($("#repassword").val()!=$("#password").val())$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"9px"});
		else $("#repasswordTag").text("");
	});
	$("#sbtn").click(function() {
        if (!confirm("确认要修改密码，修改成功后需要重新登录？")) {
            return false;
        }else{
			var error="";
		if($("#pwd").val()==""){
			$("#pwdTag").text("旧密码不能为空！").css({"color":"red","font-size":"9px"});
			error = error+"1";
		}
		else if($("#pwdTag").val()!="") error = error+"1";
		
		if($("#password").val()==""){
			$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"9px"});
		}
		else if($("#passwordTag").val()!="")error = error+"1";
		
		if($("#repassword").val()!=$("#password").val()){
			$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"9px"});
			error = error+"1";
		}
		else if($("#repasswordTag").val()!="")error = error+"1";
		
		if(error!="")  return false;
		else {
			$("#myform").submit();
			return true;
		}
		}
	});
	});
</script>
</html>
<%}%>
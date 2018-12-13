<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
 <script type="text/javascript">
function loadimage(){
document.getElementById("randImage").src = "images.jsp?"+Math.random();
}
</script>
<meta charset="UTF-8">
<title>管理员登录界面</title>
<HEAD>

<!-- CSS -->
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>

	
</head>

<body>

<div class="header">
  <h1 class="headerLogo"><a title="后台管理系统" target="_blank" href="login.jsp"><img alt="logo" src="images/logo.png"></a></h1>
	<div class="headerNav">
		<a target="_blank" href="../user/login.jsp">前台首页</a>
	</div>
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"></div>
   
 <form name="loginForm" id="login_form" method="post" action="AdminServlet.do?method=login">
   <div class="fm-item">
	   <label for="logonId" class="form-label">MISS后台登陆：</label>
	   <input type="text" value="" maxlength="100" id="adminName" name="adminName" class="i-text" datatype="s6-18" errormsg="用户名至少6个字符,最多18个字符！"  >    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">登陆密码：</label>
	   <input type="password" value="" maxlength="100" id="password" name="password"  class="i-text" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！">    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item pos-r">
	   <label for="logonId" class="form-label">验证码</label>
	   <input type="text" value="" maxlength="" id="yzm" name="yzm" class="i-text yzm" nullmsg="请输入验证码！" >    
      <div class="yzm-img" >  <a href="javascript:loadimage();"><img alt="code..." id="randImage" src="images.jsp" width="110" height="40"></a>
  <div class="ui-form-explain"></div>
  </div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label"></label>
	   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"> 
       <div class="ui-form-explain"></div>
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(themes/theme-pic1.jpg) #BCE0FF center 0 no-repeat;"></li>
			<li style="background:url(themes/theme-pic2	.jpg) #BCE0FF center 0 no-repeat;"></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
   <p>版权所有  Copyright 2016-2017 , All Rights Reserved</p>
</div>

</body>
<script>
$(function(){

$(".i-text").focus(function(){
$(this).addClass('h-light');
});

$(".i-text").focusout(function(){
$(this).removeClass('h-light');
});

$("#adminName").blur(function(){
 var username = $(this).val();
 if(username==''){
 $("#msg").text('请输入用户名称！').css({"color":"red","font-size":"12px"});
 }else{$("#msg").text('');}
});

$("#password").blur(function(){
 var password = $(this).val();
 if(password==''){
 $("#msg").text('请输入登录密码！').css({"color":"red","font-size":"12px"});
 }else{$("#msg").text('');}
});

$("#yzm").blur(function(){
 var yzm = $(this).val();
 if(yzm==''){
 $("#msg").text('请输入验证码！').css({"color":"red","font-size":"12px"});
 }else{$("#msg").text('');}
});

$("#send-btn").click(function(){
		
		var errorstr = "";
		if($("#adminName").val()==""){
			$("#msg").text('请输入用户名称！').css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		
		if($("#password").val()==""){
			$("#msg").text("请输入登录密码！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		
		if($("#yzm").val()==""){
			$("#msg").text("请输入验证码！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		
		if($("#msg").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
			
       		 $("#login_form").submit();
       		 return true;
		
		}
	});
	

});




</script>
</html>
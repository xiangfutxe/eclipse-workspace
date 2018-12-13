<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="check.jsp" %>  
<%@page import="com.ssm.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message =StringUtil.notNull((String) request.getAttribute("message"));
  String ip = request.getHeader("x-forwarded-for");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
       }
if(!message.equals("")){
		out.println("<script>");
		out.println(" alert('"+message+"');");
		out.println("</script>");
}
%>
<!DOCTYPE HTML>
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>个人中心</title>
<link href="css/login.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" type="text/css" media="all" />
      <script type="text/javascript" src="js/jquery.js"></script>
 <style type="text/css">
    .code
    {
            background-color:#fff;
            font-family:Arial;
            font-style:italic;
             color:blue;
             font-size:24px;
             border:0;
             padding:2px 3px;
             letter-spacing:3px;
             font-weight:bolder;         
             cursor:pointer;
             width:110px;
             height:35px;
             line-height:35px;
             text-align:center;
             vertical-align:middle;
    }
    </style>
	 <script type="text/javascript">
        var code;
        function createCode() {
            code = "";
            var codeLength = 4; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var hiddenCode = document.getElementById("code"); 
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9); //所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) 
            {
                var charNum = Math.floor(Math.random() * 10);
                code += codeChars[charNum];
            }
            if (checkCode) 
            {
                checkCode.className = "code";
                checkCode.innerHTML = code;
                hiddenCode.value= code;
            }
        }
        </script>
     
  <script  type="text/javascript">
$(function(){

$(".i-text").focus(function(){
$(this).addClass('h-light');
});

$(".i-text").focusout(function(){
$(this).removeClass('h-light');
});

$("#userId").blur(function(){
 var userId = $(this).val();
 if(userId==''){
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
 var code = $("#code").val();
 if(yzm==""){
 	$("#msg").text('请输入验证码！').css({"color":"red","font-size":"12px"});
 }else if(yzm!=code){
 	$("#msg").text('验证码有误！').css({"color":"red","font-size":"12px"});
 }else{$("#msg").text('');}
});

$("#send-btn").click(function(){
		
		var errorstr = "";
		if($("#userId").val()==""){
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
		}else if($("#yzm").val()!=$("#code").val()){
			$("#msg").text("验证码有误！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		if($("#msg").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
			
       		 $("#loginform").submit();
       		 return true;
		
		}
	});
	

});




</script>



</head>

<body onload="createCode()"> 
<div class="header">
  <h1 class="headerLogo"><a title="个人管理中心" target="_blank" href="#"></a></h1>
	
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"><span id="msg"></span></div>
   
   <form action="UserServlet?method=login" method="post" id="loginform" name="loginform">
   <input type="hidden" name="ip" value="<%=ip%>">
   
   <div class="fm-item">
	   <label for="logonId" class="form-label">个人用户登陆：</label>
	   <input type="text" maxlength="100" id="userId" name="userId" class="i-text" placeholder="请输入您的用户名">    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">登陆密码：</label>
	   <input type="password" value="" maxlength="100" id="password" name="password" class="i-text" placeholder="请输入您的密码">    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item pos-r">
	   <label for="logonId" class="form-label">验证码</label>
	   <input type="text" maxlength="100" id="yzm" class="i-text yzm" placeholder="输入验证码"/>    
       <div class="yzm-img" > <div id="checkCode" onclick="createCode()"></div></div>
        <input type="hidden"  id="code" name="code"> 
	   <div class="ui-form-explain"></div>
	   
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label"></label>
	   <input type="button" value="" tabindex="4" id="send-btn" class="btn-login"> 
       <div class="ui-form-explain"></div>
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(themes/theme-pic1.jpg) #fff center 0 no-repeat;"><a href="login.jsp"></a></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
 <p>温馨提示：建议使用Firefox，Chrome，Safari，360，IE9以上等主流浏览器</p>
 <p>&nbsp;</p>
   <p>版权所有 Copyright 2018-2019, All Rights Reserved</p>

</div>

<div style="text-align:center;">
<p></p>
</div>

</body>

</html>

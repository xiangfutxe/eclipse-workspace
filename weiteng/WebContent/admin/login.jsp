
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统-威腾</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    }) ;
});  
</script> 

	 <script type="text/javascript">
        var code;
        function createCode() {
            code = "";
            var codeLength = 4; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var hiddenCode = document.getElementById("code"); 
            var codeChars = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9","A","B","C","D","E","F","G"); //所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) 
            {
                var charNum = Math.floor(Math.random() * 17);
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
        
        <script type="text/javascript">
$(function(){
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
</head>

<body  onload="createCode()" style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="../index.jsp">公司官网</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
    <div class="loginbox">
    <form id="login_form" action="AdminServlet?method=login" method="post">
       <input type="hidden" name="ip" value="<%=ip%>"/>
    
    <ul>
    <li>登陆账号：<input name="adminName" id="adminName" type="text" class="loginuser"/></li>
    <li>登陆密码：<input name="password" id="password" type="password" class="loginpwd" /></li>
        <li>验&nbsp;证&nbsp;码&nbsp;：<input name="yzm" id="yzm"  type="text" class="loginyzm" value="" onclick="JavaScript:this.value=''"/>
   <label  id="checkCode" onclick="createCode()"></label>  <input type="hidden"  id="code" name="code" /> 
   
        </li>
    <li><input name="" type="submit" id="send-btn" class="loginbtn" value="登录"  /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
   <li><span id="msg"></span></li>
    </ul>
    </form>
    
    </div>
    
    </div>
    
    <div class="loginbm">版权所有  2018-2019  广州微滕生物科技有限公司</div>
</body>

</html>

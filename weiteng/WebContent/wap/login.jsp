<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){

 %>

<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<link rel="stylesheet" href="css/style_login.css">
<link rel="stylesheet" href="css/tip.css">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<script type="text/javascript" src="js/createCode.js"></script>
<script type="text/javascript" src="js/tabs.js"></script>


<title>会员登陆</title>
</head>

<body onload="createCode()">
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">会员登陆</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
	 	<form id="myform" action="UserServlet?method=login" class="login" method="post">
		<div class="tab">
		<ul class="tab-hd"><li class="active">账号密码登陆</li>
		</ul>
		<ul class="tab-bd"><li class="thisclass">
			<div class="login">
			<div class="li"><input type="text" value="" name="userId" id="userId" placeholder="请输入用户名">
			</div>
			<div class="li"><input type="password" value="" name="password" id="password"  placeholder="请输入登陆密码">
			</div>
			
			</div>
			<div class="submit"> <a class="forget-psw">忘记密码</a></div>
			
			<div class="submit"> <a class="btn btn-login" id="nick_sub">登陆</a>
			</div>
			
			<div class="other"> ——————&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他登陆方式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;——————
			</div>
			 <%String message = (String) request.getAttribute("message");
		  if(!StringUtil.notNull(message).equals("")){%>
		<div class="other"> <span id="msg" class="font-red">温馨提示：${message }</span></div>
		  <%} %>
		</li><li>内容2</li></ul>
		
	</div>
		  </form>
		 
		</div>
	</div>
<div class="clear"></div>

</body>
<script type="text/javascript">
$(document).ready(function(){
$(".yzm").mouseover(function(){
  $(".yzm-img").fadeIn(200);
  });
  
  $(".yzm").mouseout(function(){
  $(".yzm-img").fadeOut(200);
  });
  
  $("#nickName").blur(function(){
 		 if($("#msg").text()!="") $("#msg").text("");
	});
	$("#password").blur(function(){
 		 if($("#msg").text()!="") $("#msg").text("");
	});
	$("#yzm").blur(function(){
 		 if($("#msg").text()!="") $("#msg").text("");
	});
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
	<%if(!StringUtil.notNull(message).equals("")){
	%>
	 $("#msg").text("<%=message%>").css({"color":"red","font-size":"12px"});
	<%}%>
	$("#sub_btn").click(function(){
	var error = 0;
	if($("#name")==""){
	   $("#msg").text("编号不能为空").css({"color":"red","font-size":"12px"});
	   error = errot+1;
	}else $("#msg").text("");
	
	if (error>0) {
			return false;
		} else {
			 $('#myform').submit();
       		 return true;
		}
	});
	
	$("#nick_sub").click(function(){
	 $('#myform').submit();
       		 return true;
	});
	
	
	if($("#message").val()!=""){
 	 $("tip").fadeIn(200);
  	}
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
 		 $(".tip").fadeOut(100);
 		 });
});
</script>
</html>
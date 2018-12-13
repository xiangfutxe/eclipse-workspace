<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%
String message = (String) request.getAttribute("message");
if (!StringUtil.notNull(message).equals("")) {
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
 %>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/style_login.css">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>店铺登陆</title>
</head>
<body>
<div class="login_div">
	<div class="col-xs-12 login_title">店铺登录</div>
	<form id="myform" action="BranchServlet.do?method=login" class="login" method="post">
		<div class="nav">
			<div class="nav login_nav">
				<div class="col-xs-4 login_username">
					店铺编号:
				</div>
				<div class="col-xs-6 login_usernameInput">
					<input type="text" name="name" id="name" value="" placeholder="&nbsp;&nbsp;用户名/手机号"  onblur="javascript:ok_or_errorBylogin(this)" />
				</div>
				<div class="col-xs-1 ok_gou">
					√
				</div>
				<div class="col-xs-1 error_cuo">
					×
				</div>
			</div>
			<div class="nav login_psdNav">
				<div class="col-xs-4">
					登陆密码:
				</div>
				<div class="col-xs-6">
					<input type="password" name="psd" id="psd" value="" placeholder="&nbsp;&nbsp;密码" onBlur="javascript:ok_or_errorBylogin(this)" />
				</div>
				<div class="col-xs-1 ok_gou">
					√
				</div>
				<div class="col-xs-1 error_cuo">
					×
				</div>
			</div>
			<div class="col-xs-12 login_btn_div">
				<input type="submit" class="sub_btn" value="登录" id="login" />
			</div>
			<div class="col-xs-6">
				<p><span id="msg"></span></p>
			</div>
		</div>
	</form>
	
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
	
	$("#sub_btn").click(function(){
	var error = 0;
	if($("#sub_btn")==""){
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
});
</script>
</body></html>


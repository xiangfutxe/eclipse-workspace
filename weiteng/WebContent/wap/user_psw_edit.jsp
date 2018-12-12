<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.utils.Constants"%>

<%
int atag =4;
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
//if(null!=sns_user){
if(user==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
String message = (String)request.getAttribute("message");
 %>

<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<link rel="stylesheet" href="css/style_login.css">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<script src="../js/calendar.js"></script>
<style>
INPUT 
{ 
border-left: #ccc 1px solid; 
border-top: #ccc 1px solid; 
border-right: #ccc 1px solid; 
border-bottom: #ccc 1px solid; 
margin: 0px 0px 10px 10px; 
padding-left: 10px; 
float: left; 
font-size: 1.5em; 
line-height: 1.5em; 
height: 30px; 
text-align: left; 
} 
</style>

<title>修改密码</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">修改密码</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
	 	<form id="myform" action="UserServlet?method=psw_update" class="login" method="post">
	 	<input type="hidden" id="token" name="token" value="${sessionScope.token }"/>
	 		 	<input type="hidden" id="id" name="id" value="${user1.id }"/>
	 	
     <div class="tab">
		<ul class="tab-hd">
		</ul>
		<ul class="tab-bd"><li class="thisclass">
    <div class="login">
			<div class="li"><input type="password" value="" name="pswOld" id="pswOld" placeholder="请输入原密码">
			</div>
			<div class="li"><input type="password" value="" name="password" id="password"  placeholder="请输入新密码">
			</div>
			<div class="li"><input type="password" value="" name="re_psw" id="re_psw"  placeholder="请输入确认密码">
			</div>
			</div>
			<div class="submit"> <a class="btn btn-login" id="sub_btn">提交修改</a>
			</div>
			
			</li>
			</ul>
			</div>
		            </form>
		</div>
	</div>
<div class="clear"></div>
<%@include file ="footer.jsp" %>
</body>
<script type="text/javascript">
$(document).ready(function(){
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
	var re_psw = $("#re_psw").val();
	var psw = $("#password").val();
	var pswOld = $("#pswOld").val();
	
	if (pswOld == "") {
			str = str + "原密码不能为空！\n";
			error=1;
			$("#pswOld").focus();
			
	}
	if (psw == "") {
			
			str = str + "新密码不能为空！\n";
			error=1;
		
			
	}else if(psw!=re_psw){
		str = str + "两次输入的密码不一致！\n";
			error=1;
	} 
	if (error>0) {
	$("#msg").text(str).css({"color":"red","font-size":"12px"});
			return false;
		} else {
			 $('#myform').submit();
       		 return true;
		}
	});
});
</script>
</html>
<%}%>
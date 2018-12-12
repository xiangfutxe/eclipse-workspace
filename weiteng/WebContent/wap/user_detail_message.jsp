<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="java.util.*"%>
<%
int atag=4;
String message = (String) request.getAttribute("message");
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user||userinfo==null){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else if(StringUtil.notNull(user.getNickName()).equals("")||StringUtil.notNull(userinfo.getUserName()).equals("")){
	out.println("<script>");
	out.println("window.location.href='UserServlet?method=user_edit';");
	out.println("</script>");
}else{
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
<link rel="stylesheet" href="css/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>会员中心</title>
<script type="text/javascript">
$(document).ready(function(){
	var attr = parseInt($(".member_m_pic_1").height());
	var attr3 = parseInt($(".member_m_z_1").height());
	var h = attr - attr3;
	var clientWidth=document.body.clientWidth;
	$(".member_mp_t_img img").css("width",clientWidth*0.3);
	$(".member_mp_t_img img").css("height",clientWidth*0.3);
	
	handleUserPic();
});

function handleUserPic(){
	var th = $(".member_m_pic").outerHeight(true);
	if(th<60){
		setTimeout("handleUserPic",500);
	}else{
		$(".member_m_pic .img-circle").css("height",th*0.9);
		$(".member_m_pic .img-circle").css("width",th*0.9);
	}
	
}
</script>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="UserServlet?method=user_detail">返回</a>
			<div class="tit">个人信息</div>
			<a class=" nav-right home-icon" href="UserServlet?method=index">首页</a>
		</div>
	</div>
</header>

<div class="container ">
    <div class="row rowcar">
    
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		           
		            <p> <span  class="pull-left font-16"><%=message%></span>
		           
		            </p>
		            <br>
		               <br>
		                <p> <span  class="pull-left font-16">
<a href="UserServlet?method=user_detail" class=" btn btn-warning"> 返回个人资料</a>&nbsp;&nbsp;或&nbsp;&nbsp;<a href="UserServlet?method=index" class=" btn btn-warning"> 返回首页</a>
</span>
		           
		            </p>
			         
		            </li>
		            </ul>
		       </div>
</div>
<div class="clear"></div>

<%@include file ="footer.jsp" %>
</body>
</html>
<%}%>
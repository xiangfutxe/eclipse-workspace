<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>


<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
if(null==user){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else{
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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>邀请海报</title>
<style> 
.div-relative{position:relative;color:#000; border:0px solid #000; width:100%;} 
.div-a{ position:absolute; left:9%; top:67.2%;  width:300px; height:300px} 
/* css注释说明： 背景为红色 */ 
.div-b{ position:absolute; left:9%; top:58.5%;  width:100px; height:100px} 
/* 背景为黄色 */ 
.div-c{ position:absolute; left:39%; top:52.2%; font-size:20px;} 
/* DIV背景颜色为蓝色 */ 
</style> 
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
<div class="maincontainer" style="max-width:768px;margin:0 auto;">

<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();">返回</a>
			<div class="tit">长按二维码发送或截屏发送邀请海报</div>
		</div>
	</div>
</header>

<div class="div-relative"> 
	<img src="images/user_code.jpg" width="100%" />
<div class="div-a"><img src="../upload/qr/<%=user.getQrImg() %>" width="50%"  height="50%"/></div> 
<div class="div-b"><img src="<%=user.getHeadImgUrl() %>" width="50%"  height="50%"/></div> 
<div class="div-c"><%=user.getUserId() %></div> 
</div>
</div>

</body>
</html>
<%} %>

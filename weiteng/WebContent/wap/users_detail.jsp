<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>


<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
if(user==null){
	
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
<link rel="stylesheet" href="css/style_login.css">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>个人信息</title>
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
			<div class="tit">资金账户</div>
		</div>
	</div>
</header>
			
		<div class="list-group pt10">
			
			<a class="list-group-item">
				<span class="left-detail">奖金账户(可用)</span>
				<span class="gary pull-right pr15 pt2 f16 li" >${user1.rmoney }</span>
			</a>
			
			<a class="list-group-item">
				<span class="left-detail">奖金账户(总)</span>
				<span class="gary pull-right pr15 pt2 f16 li" >${user1.preRmoney }</span>
			</a>
			
			<a class="list-group-item">
		
				<span class="left-detail">特买权账户</span>
				
				<span class="gary pull-right pr15 pt2 f16" >${user1.cashNum }</span>
			</a>
			
			<a class="list-group-item">
		
				<span class="left-detail">代金券账户</span>
				
				<span class="gary pull-right pr15 pt2 f16" >${user1.cash }</span>
			</a>
			
			<a class="list-group-item">
		
				<span class="left-detail">积分账户账户</span>
				
				<span class="gary pull-right pr15 pt2 f16" >${user1.integral }</span>
			</a>
			</div>
			 <div class="tab">
		
		<ul class="tab-bd"><li class="thisclass">
			 <div>
				<div class="submit"> <a href="WithDrewServlet?method=list" class="btn btn-login" id="sub_btn">提现申请</a>
				</div>
		</div>		
		</li>
		</ul>
		</div>
	
</div>

</body>
</html>
<%} %>

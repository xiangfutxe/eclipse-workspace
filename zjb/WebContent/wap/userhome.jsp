<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.Member"%>
<%@ page import="com.utils.Constants"%>
<%@ page import="com.utils.StringUtil"%>


<%
int atag=4;
Member user  = (Member) request.getSession().getAttribute(Constants.MEMBER_SESSION);
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
<script charset="utf-8" src="js/global.js?v=01291"></script>
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
<div class="maincontainer" style="max-width:768px;margin:0 auto;">

			<div class="list-group pt10">
			<div class="list-group-item tip">
			<a href="user_detail.jsp">
			<span  class="header">
			<img src="images/user_header.png" alt="images/user_header.png"/>
			</span>
			<span class="center">
			<span class="left-label">Hello,<%=user.getNickName() %><br></span>
			<span class="grey">宝号：123456</span>
			
			</span>
			</a>
			</div>

			<div class="list-group-item tip">
			<a href="member_detail.do">
			<span class="left-label">当前项目：健康树</span>
			<span class="gary pull-right pr15 pt2">切换</span>
			</a>
			</div>
		</div>
		<div class="list-group pt10">
			<a href="OrderServlet?method=orders" class="list-group-item tip">
				<span class="item_img">
						<img src="images/user_order_1.png"></span>
						<span class="left-label">我的钱包</span>
				<span class="gary pull-right pr15 pt2" >奖金收入、提现等</span>
			</a>
			<a href="OrderServlet?method=orders" class="list-group-item tip">
			<span class="item_img">
						<img src="images/user_order_2.png"></span>
				<span class="left-label">设置</span>
			</a>
		</div>
		
		<div class="list-group pt10">
			<a href="OrderServlet?method=orders" class="list-group-item tip">
			<span class="item_img">
						<img src="images/user_order_3.png"></span>
				<span class="left-label">联系客服</span>
				<span class="gary pull-right pr15 pt2">问题咨询、反馈等</span>
			</a>
			<a href="OrderServlet?method=orders" class="list-group-item tip">
			<em class="item_img">
						<img src="images/user_order_4.png"></em>
				<span class="left-label">更新日志</span>
				<span class="gary pull-right pr15 pt2">更新日志</span>
			</a>
		</div>
		
		<div class="list-group pt10">
			<a href="logout.do" class="list-group-item tip">
			<span class="item_img">
						<img src="images/user_other_logout.png"></span>
				<span class="left-label">退出系统</span>
			</a>
			
		</div>
		
<%@include file ="footer.jsp" %>
</div>

</body>
</html>
<%} %>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%
Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
if(branch==null){
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
<div class="maincontainer">
<div class="container" style="max-width:768px;margin:0 auto;">
	<div class="row">
		<div class="member_top member_top_1">
			<div class="member_top_bg"><img  src="images/member_bg.png"></div>
			<div class="member_m_pic member_m_pic_1">
				<img class="img-circle" src="">
        	</div>
			<div  class="member_m_z member_m_z_1">
				<div class="member_m_x">
				<p><%=branch.getName() %></p>
				<p><%=branch.getCode() %></p>
				</div>
			</div>
			</div>
		</div>
		
		<div class="member_mp_img" data-toggle="modal" data-target="#myModalmin" data-title="æçåç" data-tpl="mp"><img src="images/member_mp_img.png"></div>
		<div class="list-group mb10">
			<a href="OrderServlet?method=orders" class="list-group-item tip">
				<div class="list_group_img">
				<img src="images/member_img16.png">
				</div>
				我的订单
				<span class="gary pull-right">查看全部</span>
			</a>
			<div class="list-group-item p0 clearfix">
		
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<img src="images/order_bg_3.png"></em>待确认
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=2">
						<em class="order_img">
						<img src="images/order_bg_2.png"></em>待发货
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=3">
						<em class="order_img">
						<img src="images/order_bg_1.png"></em>出库中
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=4">
						<em class="order_img">
						<img src="images/order_bg.png"></em>已发货
						</a>
				</div>
			</div>
		</div>
		
				
		<div class="list-group mb10">
	
		<a href="OrderServlet?method=order_payoff&state=1" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_5.png"></div>
				已付款订单
			</a>
			<a href="OrderServlet?method=order_payoff&state=0" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_3.png"></div>
				待付款订单
			</a>
			
			<a href="BranchServlet.do?method=detail" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_9.png"></div>
				基本资料
			</a>
			
			<a href="report_performance_time.jsp" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_3.png"></div>
				订单汇总
			</a>
			<a href="MessageServlet.do?method=list" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_4.png"></div>
				留言信箱
			</a>
			</div>
			<div class="list-group mb10">
	<a href="BranchServlet.do?method=psw_edit" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_9.png"></div>
				修改密码
			</a>
		<a href="BranchServlet.do?method=logout" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_1.png"></div>
				退出系统
			</a>
	</div>

<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="BranchServlet.do?method=index">
			<i class="navIcon home"></i>
			<span class="text">主页</span>
		</a>
		
		<a href="ProductServlet?method=list">
			<i class="navIcon shop"></i>
			<span class="text">购物</span>		
		</a>
		<a href="OrderServlet?method=orders" >
			<i class="navIcon sort"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp"  class="active">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
</div>
</body>
</html>
<%}%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.Member"%>
<%@ page import="com.utils.Constants"%>
<%@ page import="com.utils.StringUtil"%>


<%
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
<div class="maincontainer" style="max-width:768px;margin:0 auto;">

			<div class="list-group">
			<ul class="list-group-item">
			<li class="header">
			<a href="member_detail.do">
			<img src="images/user_header.png" alt="images/user_header.png"/>
			</a>
			</li>
			<li class="center">
			<h2>Hello,<%=user.getNickName() %></h2>
			<a class="btn btn-rank">
			<%if(user.getRank()==0){ %>普通会员
			<%}if(user.getRank()==1){ %>青铜会员
			<%}if(user.getRank()==2){ %>银牌会员
			<%}if(user.getRank()==3){ %>金牌会员
			<%}if(user.getRank()==4){ %>铂金会员
			<%}if(user.getRank()==5){ %>钻石会员<%} %>&nbsp;&gt;</a>
			</li>
			</ul>

			
			<div class="list-group-item p0 clearfix">
			<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<b>¥<%=StringUtil.decimalFormat(user.getEmoney()) %></b></em>余额
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<b><%=user.getIntegral() %></b></em>积分
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<b>¥0.00</b></em>卡包
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<b>5</b></em>优惠券
						
						</a>
				</div>
		</div>
		</div>
		<div class="list-group pt10">
			<a href="OrderServlet?method=orders" class="list-group-item">
				<span class="left-label">我的订单</span>
				<span class="gary pull-right">查看更多全部&nbsp;&gt;</span>
			</a>
			<div class="list-group-item p0 clearfix">
		
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<img src="images/user_order_1.png"></em>待付款
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=2">
						<em class="order_img">
						<img src="images/user_order_2.png"></em>待发货
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=3">
						<em class="order_img">
						<img src="images/user_order_3.png"></em>待收货
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=4">
						<em class="order_img">
						<img src="images/user_order_4.png"></em>待评价
						</a>
				</div>
			</div>
		</div>
		
			<div class="list-group pt10">
			<a class="list-group-item">
				<span class="left-label">常用工具</span>
			</a>
			<div class="list-group-item p0 clearfix">
			<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<img src="images/user_other_member.png"></em>基本资料
						
						</a>
				</div>
				
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=2">
						<em class="order_img">
						<img src="images/user_other_sum.png"></em>订单汇总
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=3">
						<em class="order_img">
						<img src="images/user_other_adr.png"></em>收货地址
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=1">
						<em class="order_img">
						<img src="images/user_other_msg.png"></em>意见反馈
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=4">
						<em class="order_img">
						<img src="images/user_other_help.png"></em>帮助中心
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders&state=4">
						<em class="order_img">
						<img src="images/user_other_psw.png"></em>修改密码
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="logout.do">
						<em class="order_img">
						<img src="images/user_other_logout.png"></em>退出系统
						</a>
				</div>
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
</div>

</body>
</html>
<%} %>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.utils.Constants"%>

<%
int atag=1;
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
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>
<script type="text/javascript" src="js/tabs.js"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<link rel="stylesheet" href="css/style_main.css">

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

			<div class="list-group">
			
			<div class="list-group-item tip">
			<a href="user_detail.jsp">
			<span  class="header">
			<img src="images/user_header.png" alt="images/user_header.png"/>
			</span>
			<span class="center">
			<span class="left-label">不一样的自我<br></span>
			<span class="gary">我是项目方</span>
			</span>
			<span class="gary pull-right pr15 vam pt40">项目管理</span>
			
			</a>
			</div>
				<div class="list-group-item p0">
			<div class="col-3">
					<a class="tab_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<b>¥<%=StringUtil.decimalFormat(user.getEmoney()) %></b></em>本月单数
						
						</a>
				</div>
				<div class="col-3 p0">
					<a class="tab_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<b>¥<%=StringUtil.decimalFormat(user.getEmoney()) %></b></em>
						<span >本月销售额</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="tab_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<b>¥<%=StringUtil.decimalFormat(user.getEmoney()) %></b></em>
						<span>本月利润</span>
						
						</a>
				</div>
				</div>
				<div class="tab">
		<ul class="tab-hd"><li class="active"><img alt="" src="images/icons/16/tab1.png">&nbsp;管货</li>
		<li><img alt="" src="images/icons/16/tab2.png">&nbsp;管人</li>
		<li><img alt="" src="images/icons/16/tab3.png">&nbsp;管钱</li>
		<li><img alt="" src="images/icons/16/tab4.png">&nbsp;其他</li>
		</ul>
		<ul class="tab-bd list-group-item p0 clearfix">
		<li class="thisclass">
		<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">开单销售</span>
						<br><span class="gary">销售录单、快速记账</span>
						</a>
				</div>
				
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=2">
						<em class="link_img">
						<img src="images/user_other_sum.png"></em>
						<span class="black">订单处理</span>
						<br><span class="gary">待处理订单:0</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">批量发货</span>
						<br><span class="gary">发货清单批量发货</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">商品管理</span>
						<br><span class="gary">商品编辑、库存管理</span>
						</a>
				</div>
				
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=2">
						<em class="link_img">
						<img src="images/user_other_sum.png"></em>
						<span class="black">收单宝</span>
						<br><span class="gary">向客户快速收单</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">购物商城</span>
						<br><span class="gary">访问小商城</span>
						</a>
				</div>
		</li>
		
		<li>
		<div class="col-3 p0" id="tab2">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">直属代理</span>
						<br><span class="gary">直属下级代理</span>
						</a>
				</div>
				
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=2">
						<em class="link_img">
						<img src="images/user_other_sum.png"></em>
						<span class="black">所有代理</span>
						<br><span class="gary">团队所有代理</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">新增授权</span>
						<br><span class="gary">授权下级代理</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">代理审核</span>
						<br><span class="gary">待审核数：0</span>
						</a>
				</div>
				
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=2">
						<em class="link_img">
						<img src="images/user_other_sum.png"></em>
						<span class="black">订单网络</span>
						<br><span class="gary">订单上下级网络</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">推荐网络</span>
						<br><span class="gary">查看同级推荐关系</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">公告发布</span>
						<br><span class="gary">发布团队公告</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">客户管理</span>
						<br><span class="gary">管理客户信息</span>
						</a>
				</div>
				</li><li>
				<div class="col-3 p0" id="tab3">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">奖金支出</span>
						<br><span class="gary">奖金查看和发放</span>
						</a>
				</div>
				
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=2">
						<em class="link_img">
						<img src="images/user_other_sum.png"></em>
						<span class="black">提现审核</span>
						<br><span class="gary">审核代理提现:0</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=3">
						<em class="link_img">
						<img src="images/user_other_adr.png"></em>
						<span class="black">充值审核</span>
						<br><span class="gary">待审核充值:0</span>
						</a>
				</div>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">资金余额</span>
						<br><span class="gary">查看资金池明细</span>
						</a>
				</div>
				</li>
				<li>
				<div class="col-3 p0">
					<a class="order_link" href="OrderServlet?method=orders&state=1">
						<em class="link_img">
						<img src="images/user_other_member.png"></em>
						<span class="black">赠品</span>
						<br><span class="gary">设置订货赠品规则</span>
						</a>
				</div>
				</li>
		</ul>
		</div>
</div>
<%@include file ="footer.jsp" %>

</div>

</body>
</html>
<%} %>

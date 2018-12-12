<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>


<%
int atag=4;
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
User user1  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(user==null||user1==null){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else if(StringUtil.notNull(user1.getUserName()).equals("")){
	out.println("<script>");
	out.println("window.location.href='UserServlet?method=user_edit';");
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
			<a href="UserServlet?method=user_detail">
			<span  class="header">
			<img src="<%=user.getHeadImgUrl() %>" alt=""/>
			</span>
			<span class="center">
			<span class="left-label">Hello,<%=user.getNickName() %><br></span>
			<span class="grey">宝号：<%=user.getUserId() %></span>
			<span class="btn-rank"><%if(user1.getRankJoin()==0){ %>关注会员
						<%}else if(user1.getRankJoin()==1){ %>普通会员
						<%}else if(user1.getRankJoin()==2){ %>天使会员
						<%}else if(user1.getRankJoin()==3){ %>资深会员
						<%}else if(user1.getRankJoin()==4){ %>高级合伙人
						<%}else if(user1.getRankJoin()==5){ %>城市合伙人
						<%}else if(user1.getRankJoin()==6){ %>联创合伙人
						<%}else if(user1.getRankJoin()==7){ %>官方合伙人<%} %>
			</span>
			</span>
			</a>
			</div>
		
			<div class="list-group-item p0 clearfix">
			<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=users_detail">
						<em class="order_img">
						<b>¥<%=user1.getRmoney() %></b></em>佣金
						
						</a>
				</div>
				<div class="col-xs-4">
					<a class="order_tab_link" href="UserServlet?method=users_detail">
						<em class="order_img">
						<b><%=user1.getIntegral() %></b></em><span>积分</span>
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=users_detail">
						<em class="order_img">
						<b><%=user1.getCash() %></b></em><span>代金券</span>
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=users_detail">
						<em class="order_img">
						<b><%=user1.getCashNum() %></b></em><span>特卖权数</span>
						
						</a>
				</div>
		</div>
		</div>
			<div class="list-group pt0">
			<a class="list-group-item">
				<span class="left-label">商城导航</span>
			</a>
			
			<div class="list-group-item p0 clearfix">
			<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=user_report">
						<em class="order_img">
						<img src="images/menu-3.png"></em>每日签到
						
						</a>
				</div>
				
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=shop_cash_list">
						<em class="order_img">
						<img src="images/menu-2.png"></em>特卖商城
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=shop_integral_list">
						<em class="order_img">
						<img src="images/menu-6.png"></em>积分商城
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="ModuleServlet?method=sel&code=1002">
						<em class="order_img">
						<img src="images/menu-7.png"></em>会员权益
						
						</a>
				</div>
				
			</div>
			</div>
		<div class="list-group pt0">
			<a class="list-group-item">
				<span class="left-label">代理中心</span>
			</a>
			
			<div class="list-group-item p0 clearfix">
			<div class="col-xs-4 p0">
					<a class="order_tab_link" href="user_code.jsp">
						<em class="order_img">
						<img src="images/user_other_member.png"></em>邀请海报
						
						</a>
				</div>
				
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders">
						<em class="order_img">
						<img src="images/user_my_order.png"></em>我的订单
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="ProductServlet?method=product_stock">
						<em class="order_img">
						<img src="images/user_yuncang.png"></em>我的云仓
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=user_team">
						<em class="order_img">
						<img src="images/user_my_team.png"></em>我的团队
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderServlet?method=order_agent_list">
						<em class="order_img">
						<img src="images/user_agent_order.png"></em>分销订单
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=users_detail">
						<em class="order_img">
						<img src="images/user_my_rmoney.png"></em>我的钱包
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="RewardDetailServlet?method=list">
						<em class="order_img">
						<img src="images/user_my_detail.png"></em>佣金明细
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="WithDrewServlet?method=list">
						<em class="order_img">
						<img src="images/user_with_drew.png"></em>提现申请
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="AgentApplyServlet?method=add">
						<em class="order_img">
						<img src="images/user_my_agent.png"></em>代理申请
						</a>
				</div>
			</div>
			</div>
			
			
		<div class="list-group pt0">
			<a href="OrderDeliveryServlet?method=orders" class="list-group-item">
				<span class="left-label">我的配货单</span>
				<span class="gary pull-right">查看更多全部&nbsp;&gt;</span>
			</a>
			<div class="list-group-item p0 clearfix">
		
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderDeliveryServlet?method=orders&state=1">
						<em class="order_img">
						<img src="images/user_order_1.png"></em>待确认
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderDeliveryServlet?method=orders&state=2">
						<em class="order_img">
						<img src="images/user_order_2.png"></em>待发货
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderDeliveryServlet?method=orders&state=3">
						<em class="order_img">
						<img src="images/user_order_3.png"></em>已发货
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="OrderDeliveryServlet?method=orders&state=4">
						<em class="order_img">
						<img src="images/user_order_4.png"></em>待评价
						</a>
				</div>
			</div>
		</div>
	
		
		<div class="list-group pt0">
			<a class="list-group-item">
				<span class="left-label">常用工具</span>
			</a>
			<div class="list-group-item p0 clearfix">
			<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=user_detail">
						<em class="order_img">
						<img src="images/user_other_member.png"></em>个人资料
						
						</a>
				</div>
				
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="AddressServlet?method=list">
						<em class="order_img">
						<img src="images/user_other_adr.png"></em>收货地址
						</a>
				</div>
				
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="ModuleServlet?method=sel&code=1001">
						<em class="order_img">
						<img src="images/user_other_msg.png"></em>联系客服
						
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="ModuleServlet?method=sel&code=1001">
						<em class="order_img">
						<img src="images/user_other_help.png"></em>关于我们
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=psw_edit">
						<em class="order_img">
						<img src="images/user_other_psw.png"></em>修改密码
						</a>
				</div>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="UserServlet?method=login_out">
						<em class="order_img">
						<img src="images/user_other_logout.png"></em>退出系统
						</a>
				</div>
				<%if(user.getUserId().equals("601940")){ %>
				<div class="col-xs-4 p0">
					<a class="order_tab_link" href="pay.jsp">
						<em class="order_img">
						<img src="images/user_other_logout.png"></em>微信支付
						</a>
				</div>
				<%} %>
			</div>
		</div>
		
<%@include file ="footer.jsp" %>
</div>

</body>
</html>
<%} %>

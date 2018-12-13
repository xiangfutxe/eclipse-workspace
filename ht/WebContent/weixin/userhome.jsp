<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.weixin.pojo.WeixinUserInfo"%>
<%
WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
if(null!=sns_user){
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
				<img class="img-circle" src="<%=sns_user.getHeadImgUrl() %>">
        	</div>
			<div  class="member_m_z member_m_z_1">
				<div class="member_m_x">
				<p><%=sns_user.getNickName() %></p>
				<p><%=sns_user.getUserId() %></p>
				<p><%if(sns_user.getRankManage()==0){%>普通会员<%}if(sns_user.getRankManage()==1){%>白银VIP会员
				<%}if(sns_user.getRankManage()==2){%>黄金VIP会员<%}if(sns_user.getRankManage()==3){%>钻石VIP会员
				<%}%></p>
				</div>
			</div>
			<div class="member_m_r"><a  href="AddressServlet?method=list">收货地址&gt;</a>
			</div>
		</div>
		
		<div class="member_mp_img" data-toggle="modal" data-target="#myModalmin" data-title="æçåç" data-tpl="mp"><img src="images/member_mp_img.png"></div>
		<div class="list-group mb10">
			<a href="OrderServlet?method=orders_list" class="list-group-item tip">
				<div class="list_group_img">
				<img src="images/member_img16.png">
				</div>
				我的订单
				<span class="gary pull-right">查看全部</span>
			</a>
			<div class="list-group-item p0 clearfix">
		
				<div class="col-xs-3 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders_list&order_type=1">
						<em class="order_img">
						<img src="images/order_bg_3.png"></em>待付款
						
						</a>
				</div>
				<div class="col-xs-3 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders_list&order_type=2">
						<em class="order_img">
						<img src="images/order_bg_2.png"></em>待发货
						</a>
				</div>
				<div class="col-xs-3 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders_list&order_type=3">
						<em class="order_img">
						<img src="images/order_bg_1.png"></em>待收货
						</a>
				</div>
				<div class="col-xs-3 p0">
					<a class="order_tab_link" href="OrderServlet?method=orders_list&order_type=4">
						<em class="order_img">
						<img src="images/order_bg.png"></em>已完成
						</a>
				</div>
			</div>
		</div>
		
				
		<div class="list-group mb10">
		<a href="#" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_10.png"></div>
			 我的积分
			</a>
		
			<a href="#" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_10.png"></div>
				账户充值
			</a>
			<a href="#" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_10.png"></div>
				余额提现
			</a>
			<a href="OrderServlet?method=orders_list&order_type=2" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_10.png"></div>
				已付款订单
			</a>
			<a href="OrderServlet?method=orders_list&order_type=2" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_9.png"></div>
				待评价订单
			</a>
			
			</div>
		<div class="list-group mb10">
			<a href="#" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_9.png"></div>
				修改资料
			</a>
			<a href="AddressServlet?method=list" class="list-group-item tip">
				<div class="list_group_img"><img src="images/order_bg_9.png"></div>
				地址管理
			</a>
			</div>
	</div>
</div>
<div style="display: none;" class="modal fade" id="myModalmin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
      	<form class="form-horizontal" role="form" data-method="formAjax">
	         <div class="modal-header member_tc_top">
	            <button type="button" class="close member_tc_xx" data-dismiss="modal" aria-hidden="true">*</button>
	            <h4 class="modal-title" id="myModalLabel">&nbsp;&nbsp;</h4>
	         </div>
			 <div style="overflow:hidden;width: 100%;padding-top: 20px;">
				<div style="">
					 <div class="member_mp_t_img" >
					 	<img src="<%=sns_user.getHeadImgUrl() %>">
		        			</div>
					 <div class="member_mp_t_m"><%=sns_user.getNickName() %></div>
					 <div class="member_mp_t_m_m">
						  	  <img src="../upload/qr/<%=sns_user.getQrImg()%>" />
						  </div>
						  <div class="member_mp_t_tit">用微信扫一扫二维码，成为我的粉丝</div>
					  </div>
					  
			 </div>
			 <div style="height:60px;"></div>
         </form>
      </div>
	</div>
</div>
<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="index.jsp">
			<i class="navIcon home"></i>
			<span class="text">店铺主页</span>
		</a>
		
		<a href="ProductServlet?method=list">
			<i class="navIcon sort"></i>
			<span class="text">所有商品</span>		
		</a>
		<a href="shopcart.jsp" >
			<i class="navIcon shop"></i>
			<span class="text">购物车</span>
		</a>
		<a href="userhome.jsp"  class="active">
			<i class="navIcon member"></i>
			<span class="text">会员主页</span>
		</a>
	</div>
	
  </div>
</footer>
</div>
</body>
</html>
<%}%>
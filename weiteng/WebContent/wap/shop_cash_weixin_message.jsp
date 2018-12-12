<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>


<%
int atag=3;
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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>订单支付</title>
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

			<div class="list-group pt0">
			<ul class="list-group-item">
			<li>
			订单创建成功，订单编号为${orders.orderId}
			</li>
			<li>
			订单金额：<b>${orders.price+orders.fee}</b>
			</li>
			</ul>
			</div>
		<div class="list-group pt0">
			<a class="list-group-item">
				<span class="left-label">选择支付方式</span>
			</a>
			
			<ul class="list-group-item p0 clearfix">
			<li  class="list-group-item">
					<em class="order_img">
						<img src="images/weixin_zhifu.png"></em><span class="left-label gary">&nbsp;微信支付</span>
				<span class="gary pull-right pr15 pt2"><input type="radio" name="zhifu" value="3" checked="checked"/></span>
			<input type="hidden" id="orderId" name="orderId" value="${orders.orderId}">
			</li>
			</ul>
				
			
			</div>
			</div>
			<div class="clear"></div>

<footer class="footer">
  <div class="foot-con1">
	<div class="foot-con_3">
		<a  onclick="toPay()">
			<span class="text">立即支付</span>
		</a>
	</div>
  </div>
</footer>
	
<script type="text/javascript">

var appId,timeStamp,nonceStr,packageStr,signType,paySign; 
function toPay(){
	$.ajax({
		url : "OrderServlet?method=pay_orders",
		type:"POST",
		data:{
			orderId:$("#orderId").val()
		},
		dataType : 'json', // 服务器返回的格式,可以是json或xml等
		success : function(result) { // 服务器响应成功时的处理函数
				appId = result.appId;
				paySign = result.paySign;
				packageStr = result.package;
				nonceStr = result.nonceStr;
				timestamp = result.timeStamp;
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady',
								onBridgeReady, false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady',
								onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady',
								onBridgeReady);
					}
				} else {
					onBridgeReady();
				}
				/*
				$.ajax({
					url : "/"+getProjectName()+"/pay/toSavePayInfo",
					type:"POST",
					dataType : 'json', // 服务器返回的格式,可以是json或xml等
					data:{
						spbill_create_ip:spbill_create_ip,
						detail:detail,
						out_trade_no:out_trade_no,
						total_fee:1,
						powerStationId:powerStationId,
						userId:userId,
						order_type:1
					},
					success : function(result) { // 服务器响应成功时的处理函数
						if(result>0){//插入支付记录
							onBridgeReady(paySign,prepay_id,nonceStr,timestamp);
						}
					},
					error : function(data, status, e) { // 服务器响应失败时的处理函数
						$.toptip("系统出错，请联系系统运营商", 'error');
					}
				});
				*/
			},
		error : function(data, status, e) { // 服务器响应失败时的处理函数
			alert("初始化支付接口失败，请联系系统运营商", 'error');
		}
	});
}
 
function onBridgeReady(){
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId":appId,     //公众号名称，由商户传入     
           "timeStamp":timestamp,         //时间戳，自1970年以来的秒数     
           "nonceStr":nonceStr, //随机串     
           "package":packageStr,     
           "signType":"MD5",         //微信签名方式：     
           "paySign":paySign //微信签名 （这个签名获取看后台）
       },
       function(res){
    		// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
        	   alert("支付成功", 'success');
        	   window.location.replace("OrderServlet?method=order_review_yes&id="+$("#id").val()+"&orderId="+$("#orderId").val())
           }else if(res.err_msg == "get_brand_wcpay_request:cancel" ) {
        	   alert("用户取消", 'success');
           }  else if(res.err_msg == "get_brand_wcpay_request:fail"){
        	   alert(JSON.stringify(res));
        	   alert('支付失败');
               WeixinJSBridge.call('closeWindow');
           }
       }
   ); 
}
</script>
</body>
</html>
<%} %>

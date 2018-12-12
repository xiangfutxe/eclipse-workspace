<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <script type="text/javascript" src="js/jquery.js"></script>

<title>微信支付</title>
<input type="hidden" id="weixinOperId" value="${sessionScope.user_session.open_id}" />
<script type="text/javascript">

var appId,timeStamp,nonceStr,packageStr,signType,paySign; 
function toPay(){
	$.ajax({
		url : "OrderServlet?method=pay_orders",
		type:"POST",
		dataType : 'json', // 服务器返回的格式,可以是json或xml等
		success : function(result) { // 服务器响应成功时的处理函数
				appId = result.appId;
				paySign = result.paySign;
				packageStr = result.package;
				nonceStr = result.nonceStr;
				timestamp = result.timeStamp;
				alert("appId:"+appId+";paySign:"+paySign+",packageStr:"+packageStr+"nonceStr:"+nonceStr+";timestamp:"+timestamp+".");
				
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
    	   alert("res:"+res.err_msg);
    		// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
        	   alert("支付成功", 'success');
        	   window.location.replace("OrderServlet?method=orders")
           }else if(res.err_msg == "get_brand_wcpay_request:cancel" ) {
        	   alert("用户取消", 'success');
           }  else if(res.err_msg == "get_brand_wcpay_request:fail"){
        	   alert('支付失败');
               WeixinJSBridge.call('closeWindow');
           }
       }
   ); 
}
</script>
</head>
<body>
	<div  id="addressArea" style="min-height:526px;">
		<section class="SelectCityWrap" style="width:98%;">
		    <section class="content">
				<div class="nav">
			    	<a class=""  nav="nav_1" onclick="toPay()">马上支付</a>
			    </div>
			     appId: <span id="appId"></span><br>
			   timeStamp:  <span id="timeStamp"></span><br>
			     nonceStr: <span id="nonceStr"></span><br>
			      packageStr: <span id="packageStr"></span><br>
			      signType:<span id="signType"></span><br>
			</section>
		</section>
		</div>
		
</body>
</html>
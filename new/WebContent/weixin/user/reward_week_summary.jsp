<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User"%>
<%@page import="com.utils.StringUtil"%>
<%@page import="com.utils.DateUtil"%>
<%@page import="com.pojo.WReward"%>
<%@ include file="check.jsp"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
String weekTag = (String) request.getAttribute("weekTag");

	String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image"
	href="../images/splash/splash-screen.png"
	media="screen and (max-device-width: 320px)" />
<link rel="apple-touch-startup-image"
	href="../images/splash/splash-screen_402x.png"
	media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="640x1096"
	href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748"
	href="../images/splash/splash-screen-ipad-landscape"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004"
	href="../images/splash/splash-screen-ipad-portrait.png"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008"
	href="../images/splash/splash-screen-ipad-portrait-retina.png"
	media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048"
	href="../images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />

<title>再次购物</title>

<link href="../styles/style.css" rel="stylesheet" type="text/css">
<link href="../styles/framework.css" rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css" rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript">
function form_submit()
{
myform.submit();
}
</script>
</head>
<body>

	<div class="top-deco"></div>

	<div class="content">
		<div class="header">
			<a href="#" class="homepage-logo"> <img
				src="../images/misc/index-logo.png" alt="img">
			</a>
			<p class="go-title-ch">积分明细</p>
			<p class="go-title-en">INTEGRAL DETAIL</p>
			<a href="javascript:history.go(-1);" class="go-logo">返回</a> <a
				href="main.jsp" class="go-home">首页</a> <a href="#" class="go-menu">菜单</a>
			<a href="#" class="go-back">关闭</a>
		</div>
		<div class="decoration"></div>
		<div class="navigation">
			<div class="corner-deco"></div>
			<div class="navigation-wrapper">
				<%@ include file="menu.jsp"%>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="container">

			<form action="RewardServlet.do?method=reward_week_summary" id="myform"
				name="myform" method="post">

				<div class="container no-bottom">
					<p>

						选择期数： <select class="contactField" name="weekTag" id="weekTag" onchange="form_submit()">
							<option value="">--请选择周期--</option>
							<%
								List<Integer> slist = (List<Integer>)request.getAttribute("slist");
											if(slist.size()>0){
												
													for(int i=0;i<slist.size();i++){
													int week = slist.get(i);
							%>
							<option value="<%=week%>"
								<%if(weekTag.equals(String.valueOf(week))){%> selected <%}%>>第<%=week%>期
							</option>
							<%
								}}
							%>
						</select>

					</p>
				</div>
				<div class="container no-bottom">
					<table class="table-detail">
						<%
							WReward wrd = (WReward) request.getAttribute("sys_wrd");
						      	 if(wrd!=null){
						%>
						<tr>
							<th>起止时间</th>
							<td><%=StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(wrd.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>

						</tr>
						<tr>
							<th>市场推广</th>
							<td><%=StringUtil.decimalFormat3(wrd.getAmount1())%></td>

						</tr>
						<tr>
							<th>销售补贴</th>
							<td><%=StringUtil.decimalFormat3(wrd.getAmount2())%></td>

						</tr>
						<tr>
							<th>小计</th>
							<td><%=StringUtil.decimalFormat3(wrd.getTotalAmount())%></td>

						</tr>
						<tr>
							<th>管理费</th>
							<td><%=StringUtil.decimalFormat3(wrd.getTax())%></td>

						</tr>
						<tr>
							<th>实发积分</th>
							<td><%=StringUtil.decimalFormat3(wrd.getAmount())%></td>
						</tr>
						<tr>
							<th>状态</th>
							<td><%if(wrd.getState()==1){%>结算中<%}else if(wrd.getState()==2){ %>待发放
							<%}else if(wrd.getState()==3){ %>已发放<%} %></td>
						</tr>
						<%
							}else{
						%>
<tr><td>暂无记录。</td></tr><%} %>
					</table>
				</div>
			</form>
		</div>
	</div>
	<div class="decoration"></div>
	<%@ include file="footer.jsp"%>
	<div style="height:28px;"></div>
	<div class="bottom-deco"></div>



	<script type="text/javascript">
		/* 页面加载完成，绑定事件 */
		$(function() {
			var reg = /^[1-9]+[0-9]*]*$/;
			$("#addressId").blur(function() {
				if ($(this).val() == "")
					$("#addressTag").text("未选择收货信息！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				else
					$("#addressTag").text("");
			});
			$("#btn1").bind("click", function() {
				$.ajax({
					type : "post",
					url : "UserServlet.do?method=emoneyAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data : {//设置数据源
						userId : $("input[name=userId]").val()
					//这里不要加","  不然会报错，而且根本不会提示错误地方

					},

					dataType : "json",//设置需要返回的数据类型
					success : function(data) {
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						var $num = $("input[name=numstr]");
						var $price = $("input[name=pricestr]");
						var emoney = $('#emoney').val();
						var totalprice = 0;
						var error = 0;
						for (var i = 0; i < $num.size(); i++) {
							var n = 0;
							var p = 0;
							if ($num[i].value == "" || $num[i].value == 0) {
								n = 0;
							} else if (!reg.test($num[i].value)) {
								$("#msg").text("数值格式有误，请重试！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								error++;
								break;
							} else {
								n = $num[i].value;
								$("#msg").text("");
							}
							if ($price[i].value == "")
								p = 0;
							else
								p = $price[i].value;

							totalprice = totalprice + n * p;
						}
						if (error > 0)
							$("#msg").text("产品数量的格式有误，请重新核对！").css({
								"color" : "red",
								"font-size" : "12px"
							});
						else if (emoney > totalprice)
							$("#msg").text("您选购的产品金额未达到标准。").css({
								"color" : "red",
								"font-size" : "12px"
							});
						else {
							$("#msg").text("");
							$("#totalPrice").text(totalprice + "元").css({
								"color" : "green",
								"font-size" : "12px"
							});
							$("#tprice").val(totalprice);
						}
					},
					error : function() {
						alert("系统异常，请稍后重试！");
					}//这里不要加","
				});

			});

			$("input[name='numstr']").each(function() {
				if ($(this).val() == "")
					$(this).test("0");
			});

			$("#btn2").bind("click", function() {
				if (!confirm("确认保存并结算?")) {
					return false;
				} else {
					var error = 0;
					var $num = $("input[name=numstr]");
					var $price = $("input[name=pricestr]");
					var totalprice = 0;
					for (var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
						if ($num[i].value == "")
							n = 0;
						else
							n = $num[i].value;
						if ($price[i].value == "")
							p = 0;
						else
							p = $price[i].value;

						totalprice = totalprice + n * p;

					}

					if ($("#msg").text() != "") {
						error++;
					} else if ($("#addressTag").text() != "") {
						error++;

					} else if ($("#tprice").val() == "") {
						$("#msg").text("请先计算金额后再保存并结算！").css({
							"color" : "red",
							"font-size" : "12px"
						});

						error++;
					} else if (totalprice <= 0) {
						$("#msg").text("产品金额总额不能为零！").css({
							"color" : "red",
							"font-size" : "12px"
						});

						error++;
					} else if ($("#tprice").val() != totalprice) {
						$("#msg").text("产品数量有变，请重新计算金额后再保存并结算！").css({
							"color" : "red",
							"font-size" : "12px"
						});

						error++;
					} else if ($("#addressId").val() == "") {
						$("#addressTag").text("收货人信息不完整！").css({
							"color" : "red",
							"font-size" : "12px"
						});

						error++;
					}

					$("input[name='numstr']").each(function() {
						if ($(this).val() == "")
							$(this).test("0");
					});

					if (error > 0) {
						return false;
					} else {
						$("#myform").submit();
						return true;

					}
				}
			});

		});
	</script>
</body>
</html>
<%
	}
%>
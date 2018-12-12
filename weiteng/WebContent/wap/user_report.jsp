<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Address"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%@ page import="com.ssm.utils.Pager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
if(user==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
	String message= (String) request.getAttribute("message");
	if(!StringUtil.notNull(message).equals("")){
		out.println("<script>");
		out.println("alert('"+message+"';");
		out.println("</script>");
	}
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
<link rel="stylesheet" type="text/css" href="css/detail/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/page.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail/mui.min.css"/>
<title>每日签到</title>

</head>
<body>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="UserServlet?method=index"></a>
			
			<div class="tit">每日签到</div>
		</div>
	</div>
</header>
<form id="myform" action="UserServlet?method=user_report_save" method="post">
<input type="hidden" name="token" value="${sessionScope.token}">
 <div id="main" class="mui-clearfix">
	    	<div class="reinfor clearfloat box-s">
	    		<div class="tu fl clearfloat">
	    			<img src="<%=user.getHeadImgUrl()%>"/>
	    		</div>
	    		<div class="middle clearfloat fl">
	    			<p class="tel"><%=user.getNickName()%></p>
	    			<p class="hyuan"><c:if test="${user1.rankJoin==0 }">关注会员</c:if>
	    			<c:if test="${user1.rankJoin==1 }">普通会员</c:if>
	    			<c:if test="${user1.rankJoin==2 }">天使会员</c:if>
	    			<c:if test="${user1.rankJoin==3 }">资深会员</c:if>
	    			<c:if test="${user1.rankJoin==4 }">高级合伙人</c:if>
	    			<c:if test="${user1.rankJoin==5 }">城市合伙人</c:if>
	    			<c:if test="${user1.rankJoin==6 }">联创合伙人</c:if>
	    			<c:if test="${user1.rankJoin==7 }">官方合伙人</c:if></p>
	    			<p class="jfen">当前累积：<span>${user1.integral}</span>积分</p>
	    			<c:if test="${state==0}">
	    			<input type="submit" name="" id="sub" value="点击签到" />
	    			</c:if>
	    			<c:if test="${state==1}">
	    			<input type="button" name="" id="" value="今日已签到" disabled/>
	    			</c:if>
	    		</div>
	    		<div class="right clearfloat">
	    			<p class="ta-c qdao">连续签到</p>
	    			<p class="daynum ta-c">${user1.viewNum}</p>
	    			<p class="qdao ta-c">天</p>
	    		</div>
	    	</div>
	    	<div class="rerule clearfloat box-s">
	    		<ul>
	    			<li>活动规则</li>
	    			<li>1.登录之后才能签到；</li>
	    			<li>2.每次登录后点击签到按钮才能送积分；</li>
	    			<li>3.每天只送一次，积分为1分；</li>
	    			<li>4.每连续2天，多加1分，连续签到7天，加6分(即当天签到获得7分);</li>
	    			<li>5.连续签到7天为一个周期，连续7天后额外加分重新算；</li>
	    			<li>6.分享可得更多积分；</li>
	    		</ul>
	    	</div>
	    </div>
	    </form>
	</body>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
	<script src="js/fastclick.js"></script>
	<script src="js/mui.min.js"></script>
	<script type="text/javascript" src="js/hmt.js" ></script>
</html>
<%}%>
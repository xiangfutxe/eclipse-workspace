<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){
String message = (String) request.getAttribute("message");

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
<link rel="stylesheet" href="css/style_login.css">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<title>店铺登陆</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">店铺登陆</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
	 	<form id="myform" action="BranchServlet.do?method=login" class="login" method="post">

	   <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		          <div class="login_psdNav">
		            <div class="col-xs-4 ">
					店铺编号:
				</div>
				<div class="col-xs-9 " >
					<input type="text" name="name" id="name" value="" placeholder="&nbsp;&nbsp;用户名/手机号" />
				</div>
				</div>
				<div class="login_psdNav">&nbsp;</div>
				<div class="login_psdNav">
				  <div class="col-xs-4">
					登陆密码:
				</div>
				<div class="col-xs-9" >
					<input type="password" name="psd" id="psd" placeholder="&nbsp;&nbsp;密码"  />
				</div>
				</div>
					<div class="login_psdNav">&nbsp;</div>
					<div class="login_submit">
						 <a href="BranchServlet.do?method=index"   class="btn btn-warning">返回首页</a>&nbsp;
						  <a href="javascript:void(0);" id="sub_btn"  class="btn btn-success">提交登陆</a>
						  
						</div>
						<div class="login_submit"><span id="msg"></span></div>
						<div class="login_psdNav">&nbsp;</div>
		            </li>
		            </ul>
		            </form>
		</div>
	</div>
<div class="clear"></div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
	<%if(!StringUtil.notNull(message).equals("")){
	%>
	 $("#msg").text("<%=message%>").css({"color":"red","font-size":"12px"});
	<%}%>
	$("#sub_btn").click(function(){
	var error = 0;
	if($("#name")==""){
	   $("#msg").text("编号不能为空").css({"color":"red","font-size":"12px"});
	   error = errot+1;
	}else $("#msg").text("");
	
	if (error>0) {
			return false;
		} else {
			 $('#myform').submit();
       		 return true;
		}
	});
});
</script>
</html>
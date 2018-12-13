<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.pojo.Branch" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){
Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
String token = (String)request.getSession().getAttribute("token");

if(branch==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
String message = (String)request.getAttribute("message");
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
<script src="../js/calendar.js"></script>
<style>
INPUT 
{ 
border-left: #ccc 1px solid; 
border-top: #ccc 1px solid; 
border-right: #ccc 1px solid; 
border-bottom: #ccc 1px solid; 
margin: 0px 0px 10px 10px; 
padding-left: 10px; 
float: left; 
font-size: 1.5em; 
line-height: 1.5em; 
height: 30px; 
text-align: left; 
} 
</style>

<title>订单汇总</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">修改密码</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
	 	<form id="myform" action="BranchServlet.do?method=psw_update" class="login" method="post">
     <input type="hidden" name="token" id="token" value="<%=token%>"/> 
 <input type="hidden" name="code" id="code" value="<%=branch.getCode()%>"/> 
 	   <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		          <div class="login_psdNav">
		            <div class="col-xs-4 ">
					原密码:
				</div>
				<div class="col-xs-9 " >
					<input type="password" name="pswOld" id="pswOld" value="" />
				</div>
				</div>
				<div class="login_psdNav">&nbsp;</div>
				<div class="login_psdNav">
				  <div class="col-xs-4">
					新密码:
				</div>
				<div class="col-xs-9" >
					<input type="password" name="psw" id="psw" value=""/>
				</div>
					<div class="login_psdNav">&nbsp;</div>
				<div class="login_psdNav">
				  <div class="col-xs-4">
					确认密码:
				</div>
				<div class="col-xs-9" >
					<input type="password" name="re_psw" id="re_psw" value=""/>
				</div>
				</div>
					<div class="login_psdNav">&nbsp;</div>
					<div class="login_submit">
						 <a href="userhome.jsp"   class=" btn btn-warning">返回会员首页</a>&nbsp;
						  <a href="javascript:void(0);" id="sub_btn"  class=" btn btn-success">提交修改</a>
						  
						</div>
						<div class="login_submit"><span id="msg"></span></div>
						<div class="login_psdNav">&nbsp;</div>
		            </li>
		            </ul>
		            </form>
		</div>
	</div>
<div class="clear"></div>
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
	var re_psw = $("#re_psw").val();
	var psw = $("#psw").val();
	var pswOld = $("#pswOld").val();
	
	if (pswOld == "") {
			str = str + "原密码不能为空！\n";
			error=1;
			$("#pswOld").focus();
			
	}
	if (psw == "") {
			
			str = str + "新密码不能为空！\n";
			error=1;
		
			
	}else if(psw!=re_psw){
		str = str + "两次输入的密码不一致！\n";
			error=1;
	} 
	if (error>0) {
	$("#msg").text(str).css({"color":"red","font-size":"12px"});
			return false;
		} else {
			 $('#myform').submit();
       		 return true;
		}
	});
});
</script>
</html>
<%}%>
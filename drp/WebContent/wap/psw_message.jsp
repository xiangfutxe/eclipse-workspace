<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%@ page import="com.pojo.Product"%>
<%@ page import="com.pojo.Address"%>
<%@ page import="java.util.*"%>
<%
String message = (String) request.getAttribute("message");
Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
if(branch==null){
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
<title>订单详情</title>
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
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();">返回</a>
			<div class="tit">信息提示</div>
		</div>
	</div>
</header>

<div class="container ">
    <div class="row rowcar">
    
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		           
		            <p> <span  class="pull-left font-14"><%=message%></span>
		           
		            </p>
		            <br>
		               <br>
		                <p> <span  class="pull-left font-14">
<a href="BranchServlet.do?method=psw_edit" class=" btn btn-warning"> 重新修改密码</a>
</span>
		           
		            </p>
			         
		            </li>
		            </ul>
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
</footer></body>
</html>
<%}%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Branch"%>
<%@page import="com.pojo.Order" %>
<%@page import="com.utils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.utils.ArithUtil" %>
<%@page import="com.utils.StringUtil" %>
<%
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
<title>基本资料</title>
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
			<div class="tit">基本资料
			</div>
		</div>
	</div>
</header>

<div class="container" >
	 <div class="row rowcar">
				  <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
					 <p> <span  class="pull-left font-14">店铺编号：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getCode())%>
		            </span>
		            </p>
			         <br>
					 <p> <span  class="pull-left font-14">店铺名称：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getName())%>
		            </span>
		            </p>
		           <br>
					 <p> <span  class="pull-left font-14">负责人：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getLinkman())%>
		            </span>
		            </p>
		             <br>
					 <p> <span  class="pull-left font-14">手机号码：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getTel())%>
		            </span>
		            </p>
		             <br>
					 <p> <span  class="pull-left font-14">固定电话：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getPhone())%>
		            </span>
		            </p>
		             <br>
					 <p> <span  class="pull-left font-14">所在省市县：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getProvince())%><%=StringUtil.notNull(branch.getCity())%><%=StringUtil.notNull(branch.getArea())%>
		            </span>
		            </p>
		             <br>
					 <p> <span  class="pull-left font-14">详细地址：</span>
		            <span  class="pull-right font-14"><%=StringUtil.notNull(branch.getAddress())%>
		            </span>
		            </p>
		            </li>
		           
					</ul>
					
		</div>
		
		<div id="ajax_loading" style="display:none;width:300px;margin: 10px  auto 15px;text-align:center;">
			 <img src="images/loading.gif">
		</div>
		 <form  action='/m_search/prodlist' method="post" id="list_form">
				<input type="hidden" id="curPageNO" name="curPageNO"  value=""/>
			    <input type="hidden"  id="categoryId" name="categoryId" value="36" />
			    <input type="hidden" id="orders" name="orders"  value=""/>
			    <input type="hidden" id="hasProd" name="hasProd"  value="" />
			    <input type="hidden" id="keyword" name="keyword"  value="" />
			    <input type="hidden" id="prop" name="prop"  value="" />
		</form>
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
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer></body>
</html>
<%}%>
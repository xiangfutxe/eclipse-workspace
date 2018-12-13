<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.weixin.pojo.WeixinUserInfo"%>
<%@ page import="com.pojo.Address"%>
<%@ page import="java.util.*"%>
<%
WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
List<Address> alist = (List<Address>) request.getAttribute("adr_list");
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
<title>地址管理</title>
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
			<div class="tit">地址管理</div>
		</div>
	</div>
</header>

<div class="container ">
    <div class="row rowcar">
     <%if(alist!=null){
    		for(int i=0;i<alist.size();i++){
    		%>
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		           
		            <p> <span  class="pull-left font-14"><%=alist.get(i).getReciver()%></span>
		            <span  class="pull-right font-14"><%=alist.get(i).getTel()%>
		            </span>
		            </p>
			         <br>
			         <p>
			                 <span class="pull-left"><%=alist.get(i).getProvince()%> <%=alist.get(i).getCity()%> <%=alist.get(i).getArea()%> <%=alist.get(i).getAdr()%></span></p>
		            </li>
		            <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary"><input type="radio" name="tag" id="tag" value="<%=alist.get(i).getId()%>"/>&nbsp;设为默认地址</span>
		                </div>
						<div class="btn-group btn-group-sm control-num">
		                    <a onclick="disDe(this)" href="javascript:void(0);" class="btn1 btn-default">
		                   <span class="glyphicon gary"> 编辑</span></a>&nbsp;&nbsp;
		                    <a onclick="increase(this)" href="javascript:void(0);" class="btn1 btn-default"><span class="glyphicon gary">删除</span></a>
		                </div>
					</li>
					</ul>
					<%}}%>
		       </div>
</div>
<div class="clear"></div>

<footer class="footer">
  <div class="foot-con1">
	<div class="foot-con_3">
		<a href="AddressServlet?method=add">
			<span class="text">新增收货地址</span>
		</a>
	</div>
  </div>
</footer></body>
</html>
<%}%>
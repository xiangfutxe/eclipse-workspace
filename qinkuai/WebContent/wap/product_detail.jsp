<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.utils.StringUtil" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){
Product product = (Product) request.getAttribute("sys_product");

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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<title>产品详情</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">产品详情</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
	 <%if(product==null){ %>
	   <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		            <p>获取产品信息失败！</p>
		            </li>
		            </ul>
		            <%}else{ %>
				  <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		            <p><img src="../upload/<%=product.getImageUrl() %>" alt="暂无图片" style="width:100%;"></p>
		            <br>
		             <h4><%=product.getProductName() %></h4>
		            <p class="col-xs-3">产品编号：<%=product.getProductId() %></p>
		            
		            <p class="col-xs-3">规格：<%=product.getSpecification() %></p>
		            <p class="col-xs-3">类型：<%=product.getProductType() %></p>
		               <p class="col-xs-3">库存：<%=StringUtil.decimalFormat(product.getNum()) %></p>
		                   <p class="col-xs-3">单位：<%=product.getUnit() %></p>
		               
		              <p  class="col-xs-3">价格：¥<%=product.getPrice() %></p>
		           <br>
		             <p>产品详情：</p>
		             <p><%=StringUtil.notNull(product.getFeatures()) %>
		            </p>
		            <br>
		            </li>
		           
					</ul>
					<%} %>
		</div>
	</div>
<div class="clear"></div>

</body>
</html>
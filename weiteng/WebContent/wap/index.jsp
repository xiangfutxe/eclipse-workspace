<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.News" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%int atag=1; %>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>
<script charset="utf-8" src="js/scroll.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<link rel="stylesheet" href="css/scroll.css?v=01291">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>首页HOME</title>

</head>
<body>
<div class="container">
		    <div class="row">
        <div id="slide">
            <div class="hd">
                <ul><li class="on">1</li><li class="on">2</li><li class="on">3</li></ul>
            </div>
            <div class="bd">
            
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="width:3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="OrderServlet?method=shop_list" target="_blank">
	   	                    		<img src="img/banner1.jpg" alt="暂无图片" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="OrderServlet?method=shop_list" target="_blank">
	   	                    		<img src="img/banner2.jpg" alt="暂无图片" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="OrderServlet?method=shop_list" target="_blank">
	   	                    		<img src="img/banner3.jpg" alt="暂无图片">
	   	                    	</a>
	   	                    </li>
	                	</ul>
                </div>
            </div>
        </div>
    </div>
		
<script charset="utf-8" src="js/TouchSlide.js"></script>

<script type="text/javascript">
	
	TouchSlide({
		slideCell:"#slide",
		titCell:".hd ul", 
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,
		autoPage:true,
		switchLoad:"_src" 
	});
	</script>

    
       <div class="row pt10">
    		 <div class="tb_box">
					<h2 class="tab_tit">
				热销单品</h2>
				
			<div class="tb_type tb_type_even clearfix">
			<ul><li class="ta_floor"> 
			 	<a  href="ProductServlet?method=product_detail&id=c001">
					<img src="img/1.jpg" style="height:50%">
					</a>
						<span class="tit">婴儿珍海绵微珍焕颜组合套装</span>
						<span class="fu-tit"><span class="left">￥598.00</span>
						<span class="right"><a href="ProductServlet?method=product_detail">购买</a></span></span>
					</li>
				
	                		</ul>
						</div>
		</div>
		</div>
    
     <div class="row">
     <div class="tb_box">
					<h2 class="tab_tit">
				组合套装</h2>
			<div class="tb_type tb_type_even clearfix">
			<ul>
			  <c:if test="${coll_pro!=null}">
        <c:forEach items="${coll_pro}" var="result" varStatus="status">
			<li class="tb_floor"> 
			 	<a  href="OrderServlet?method=shop_confirm&id=${result.productId}&num=1">
					<img src="../upload/product/${result.imageUrl }">
					</a>
						<span class="tit">${result.productName }</span>
						<span class="fu-tit"><span class="left">
							<span class="line-through">原价:￥${result.price1 }</span>
						<br>
						￥${result.price }</span>
					<span class="right"><a href="OrderServlet?method=shop_confirm&id=${result.productId}&num=1">购买</a></span></span>
					</li>
					</c:forEach>
					</c:if>
				
	                		</ul>
						</div>
		</div>
		</div>
	 
</div>
<!-- 
<div class="foot_index">
	<div class="foot_index_tit">Aim Beauty Limited(HongKong)</div>
	<div class="foot_index_rx">æå¡ç­çº¿ï¼020-87774389</div>
</div>
-->
<%@include file ="footer.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
});
</script>
</body></html>


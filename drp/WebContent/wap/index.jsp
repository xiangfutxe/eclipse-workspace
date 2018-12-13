<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.News" %>
<%@page import="java.util.*" %>
<%@page import="com.utils.*" %>
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
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner1.jpg" alt="暂无图片" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner2.jpg" alt="暂无图片" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
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
<div class="row">
<ul class=" category">
<li>
        <a href="about_us.jsp">
            <img  src="img/menu-01.png">
           
        </a>
        <h2>关于我们</h2>
       </li>
       <li>
       
        <a href="BranchServlet.do?method=list">
            <img  src="img/menu-02.png">
        </a>
         <h2>商家信息</h2></li>
        <li >
        <a href="ProductServlet?method=product_list">
            <img  src="img/menu-03.png">
        </a> <h2>产品介绍</h2>
        </li>
        <li>
        
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>联系我们</h2></li>
        <li>
        
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>产品分类</h2></li>
         <li>
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>产品分类</h2></li>
        <li>
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>产品分类</h2></li>
        <li>
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>产品分类</h2></li>
        <li>
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>产品分类</h2></li>
        <li>
         <a href="contact_us.jsp">
            <img  src="img/menu-04.png">
        </a> <h2>产品分类</h2></li>
        </ul>
    </div>
    
<div class="row">
		<!--äº§åå-->
		<div class="tb_box">
			<div class="tb_type tb_type_even clearfix">
					<a class="tb_floor" href="ProductServlet?method=list">
								<img src="img/2.jpg" style="width:100%;">
							</a>
						
						<a class="th_link tb_last" href="ProductServlet?method=list">
								<img class="tb_pic" src="img/1.jpg" style="width:100%;">
	                		</a>
						</div>
						<p></p>
						<div class="tb_type tb_type_even clearfix">
					<a class="tb_floor" href="ProductServlet?method=list">
								<img src="img/4.jpg" style="width:100%;">
							</a>
						
						<a class="th_link tb_last" href="ProductServlet?method=list">
								<img class="tb_pic" src="img/3.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		<div class="tb_box">
			<p class="tab_tit">
				<img src="img/rec1.jpg" style="width:100%;">
				</p>
				<p class="tab_tit">
				<img src="img/rec3.jpg" style="width:100%;">
				</p>
		</div>
		
		</div>
	 
</div>
<!-- 
<div class="foot_index">
	<div class="foot_index_tit">Aim Beauty Limited(HongKong)</div>
	<div class="foot_index_rx">æå¡ç­çº¿ï¼020-87774389</div>
</div>
-->
<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="BranchServlet.do?method=index" class="active">
			<i class="navIcon home"></i>
			<span class="text">主页</span>
		</a>
		
		<a href="ProductServlet?method=list" class="active">
			<i class="navIcon shop"></i>
			<span class="text">购物</span>		
		</a>
		<a href="OrderServlet?method=orders" class="active">
			<i class="navIcon sort"></i>
			<span class="text">订单</span>
		</a>
		<a href="userhome.jsp" class="active">
			<i class="navIcon member"></i>
			<span class="text">我的</span>
		</a>
	</div>
  </div>
</footer>
<script type="text/javascript">
$(document).ready(function(){
	$("#slide img").each(function(){
	var img_src=$(this).attr("_src");
	$(this).attr("src",img_src);
	});
});
</script>
</body></html>


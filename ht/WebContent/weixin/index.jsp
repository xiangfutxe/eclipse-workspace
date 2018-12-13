<%@ page language="java" pageEncoding="UTF-8"%>
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
	   	                    	<a href="#" target="_blank">
	   	                    		<img src="img/banner01.jpg" alt="世界华埠太太俱乐部" ppsrc="img/0da8eb94-0159-4700-a6a5-bc007da5a853.jpg">
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="#" target="_blank">
	   	                    		<img src="img/banner01.jpg" alt="世界华埠太太俱乐部" >
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="#" target="_blank">
	   	                    		<img src="img/banner01.jpg" alt="世界华埠太太俱乐部">
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
		titCell:".hd ul", //å¼å¯èªå¨åé¡µ autoPage:true ï¼æ­¤æ¶è®¾ç½® titCell ä¸ºå¯¼èªåç´ åè£¹å±
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,//èªå¨æ­æ¾
		autoPage:true, //èªå¨åé¡µ
		switchLoad:"_src" //åæ¢å è½½ï¼çå®å¾çè·¯å¾ä¸º"_src"
	});
	</script>
<div class="row category">
        <a href="index.jsp" class="col-xs-3">
            <img class="img-responsive" src="img/icon_rm.png">
            <h4>首页HOME</h4>
        </a>
        <a href="ProductServlet?method=list" class="col-xs-3">
            <img class="img-responsive" src="img/icon_tm.png">
            <h4>新品发布</h4>
        </a>
        <a href="ProductServlet?method=list_2" class="col-xs-3">
            <img class="img-responsive" src="img/theme.png">
            <h4>积分专区</h4>
        </a>
        <a href="ProductServlet?method=list_1" class="col-xs-3">
            <img class="img-responsive" src="img/icon_pp.png">
            <h4>成为VIP</h4>
        </a>
    </div>
    
<div class="row">

		<!--äº§åå-->
		<div class="tb_box">
			<h2 class="tab_tit">
				<a class="more" href="ProductServlet?method=list">更多</a>
				全部商品</h2>
			
			<div class="tb_type tb_type_even clearfix"><a class="tb_floor" href="views.html">
								<img src="img/index_1.jpg" style="width:100%;">
							</a>
						<a class="th_link" href="views.html">
								<img class="tb_pic" src="img/index_2.jpg" style="width:100%;">
	                		</a>
						<a class="th_link tb_last" href="views.html">
								<img class="tb_pic" src="img/index_3.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		<!--äº§åå-->
		<div class="tb_box">
			<h2 class="tab_tit">
				<a class="more" href="ProductServlet?method=list">更多</a>
				新品发布</h2>
			
			<div class="tb_type tb_type_even clearfix"><a class="tb_floor" href="views.html">
								<img src="img/index_1.jpg" style="width:100%;">
							</a>
						<a class="th_link" href="views.html">
								<img class="tb_pic" src="img/index_2.jpg" style="width:100%;">
	                		</a>
						<a class="th_link tb_last" href="views.html">
								<img class="tb_pic" src="img/index_3.jpg" style="width:100%;">
	                		</a>
						</div>
		</div>
		<!--äº§åå-->
		<div class="tb_box">
			<h2 class="tab_tit">
				<a class="more" href="ProductServlet?method=list">更多</a>
				热卖产品</h2>
			
			<div class="tb_type tb_type_even clearfix"><a class="tb_floor" href="views.html">
								<img src="img/index_1.jpg" style="width:100%;">
							</a>
						<a class="th_link" href="views.html">
								<img class="tb_pic" src="img/index_2.jpg" style="width:100%;">
	                		</a>
						<a class="th_link tb_last" href="views.html">
								<img class="tb_pic" src="img/index_3.jpg" style="width:100%;">
	                		</a>
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
<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="index.jsp" class="active">
			<i class="navIcon home"></i>
			<span class="text">店铺主页</span>
		</a>
		
		<a href="ProductServlet?method=list">
			<i class="navIcon sort"></i>
			<span class="text">所有商品</span>		
		</a>
		<a href="shopcart.jsp" >
			<i class="navIcon shop"></i>
			<span class="text">购物车</span>
		</a>
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">会员主页</span>
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

function searchproduct(){
	   var keyword = document.getElementById("keyword").value;
	   if(keyword == undefined || keyword==null ||keyword ==""){
	     alert("è¯·è¾å¥æç´¢å³é®å­ï¼");
	     return false;
	   }
	   document.getElementById("searchform").submit();
	}
</script>
</body></html>


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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<script charset="utf-8" src="js/shopCart.js?v=01291"></script>
<title>联系我们</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">联系我们</div>
        </div>
    </div>
</header>
<div class="container" >
	 <div class="row rowcar">
				  <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
		            <h2>秦筷有限公司</h2>
		            <br>
					 <p> <span  class="pull-left font-14">地址</span>
		            <span  class="pull-right font-14">广东
		            </span>
		            </p>
			         <br><br>
					 <p> <span  class="pull-left font-14">电话</span>
		            <span  class="pull-right font-14">4009615517
		            </span>
		            </p>
		           <br><br>
					 <p> <span  class="pull-left font-14">手机：</span>
		            <span  class="pull-right font-14">18026293588
		            </span>
		            </p>
		             <br><br>
					 <p> <span  class="pull-left font-14">微信：</span>
		            <span  class="pull-right font-14">秦筷脆皮肉夹馍专卖店(qinkuai36)
		            </span>
		            </p>
		             <br><br>
					 <p> <span  class="pull-left font-14">QQ：</span>
		            <span  class="pull-right font-14">3075756507
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
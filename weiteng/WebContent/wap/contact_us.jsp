<%@ page language="java" pageEncoding="UTF-8"%>
<%int atag=1; %>
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
		            <h2>****有限公司</h2>
		            <br>
					 <p> <span  class="pull-left font-14">地址</span>
		            <span  class="pull-right font-14">广东
		            </span>
		            </p>
			         <br><br>
					 <p> <span  class="pull-left font-14">电话</span>
		            <span  class="pull-right font-14">***********
		            </span>
		            </p>
		           <br><br>
					 <p> <span  class="pull-left font-14">手机：</span>
		            <span  class="pull-right font-14">***********
		            </span>
		            </p>
		             <br><br>
					 <p> <span  class="pull-left font-14">微信：</span>
		            <span  class="pull-right font-14">***********
		            </span>
		            </p>
		             <br><br>
					 <p> <span  class="pull-left font-14">QQ：</span>
		            <span  class="pull-right font-14">***********
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

<%@include file ="footer.jsp" %>
</body>
</html>
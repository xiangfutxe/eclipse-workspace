<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Product"%>
<%@ page import="java.util.*"%>
<% 
List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_declaration_list");
double totalPrice =0;
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
<title>报单购物车</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">报单购物车</div>
        </div>
    </div>
</header>
<form action="ProductServlet?method=shop_cart_save_1" method="post" id="myform" name="myform">
<div class="container ">
    <div class="row rowcar">
     
     <%if(plist!=null){
    		for(int i=0;i<plist.size();i++){
    		totalPrice=totalPrice+plist.get(i).getPrice()*plist.get(i).getNum();
    		%>
        <ul class="list-group">
		            <li class="list-group-item text-primary">
		            <div class="icheck pull-left mr5">
		                           <input type="checkbox" checked="checked" class="ids" name="ids" value="<%=i%>" prodStatus="1"  itemkey=""/>
                                       <label class="checkLabel">
		                       <span></span>
                               </label>
		            </div>
		            	<%=plist.get(i).getCode()%></li>
		            <li class="list-group-item hproduct clearfix">
			                <div class="p-pic"><a href="/views/663"><img class="img-responsive" src="img/1787bd1d-9381-402b-b98e-97ceeddf7692.jpg"></a></div>
			                <div class="p-info">
			                    <a href="/views/663"><p class="p-title"><%=plist.get(i).getName()%></p></a>
		                    	<p class="p-attr">
			                    		<span><%=plist.get(i).getAttribute()%></span></p>
		                    	<p class="p-origin">
			                    	<a class="close p-close" onclick="javascript:if(confirm('确定删除该产品信息?')==false)return false;" href="ProductServlet?method=del_pro&tag=<%=i%>">×</a>
			                        <em class="price">¥<%=plist.get(i).getPrice()%></em>
			                    </p>
			                </div>
		            </li>
		            <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary">小计：</span>
		                	<em class="red productTotalPrice">¥<%=plist.get(i).getPrice()*plist.get(i).getNum()%></em>
		                </div>
						<div class="btn-group btn-group-sm control-num">
		                    <a  href="ProductServlet?method=disDe_1&tag=<%=i%>" class="btn btn-default"><span class="glyphicon glyphicon-minus gary"></span></a>
		                    <input type="tel" class="btn gary2 Amount" readonly="readonly" value="<%=plist.get(i).getNum()%>" maxlength="4" itemkey="" prodId="663" skuId="1358">
		                    <a  href="ProductServlet?method=increate_1&tag=<%=i%>" class="btn btn-default"><span class="glyphicon glyphicon-plus gary"></span></a>
		                </div>
					</li>
					</ul>
					<%}}%>
					
		       </div>
</div>
<div class="fixed-foot">
<div class="fixed_inner">
    <div class="pay-point">
        <div class="icheck pull-left mr10">
            <input type="checkbox" checked="checked" id="buy-sele-all" value="1">
            <label for="buy-sele-all">
                <span class="mt10"></span>全选
            </label>
        </div>
         <p>合计：<em class="red f22">¥<span id="totalPrice"><%=totalPrice%></span></em></p>
    </div>
    <div class="buy-btn-fix">
        <a href="javascript:void(0)"  id="submitShopCart" class="btn btn-danger btn-buy">去结算</a>
    </div>
</div>
</div>
</form>
<div class="clear"></div>
<script type="text/javascript">

$(document).ready(function(){  
	$("#submitShopCart").click(function() {
		if (!confirm("确认进行结算？")) {
			return false;
		} else {
			 $('#myform').submit();
       		 return true;
		}
	});
});
</script>
</body></html>


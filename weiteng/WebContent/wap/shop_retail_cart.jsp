<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.Product"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="java.util.*"%>
<% 
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
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
<title>零售购物</title>
<script>
$(document).ready(function(){  
$("a.add").click(function() {
var num_val = $(this);
	$.ajax({
				type : "post",
				url : "ProductServlet?method=increate_ajax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					tag : $(this).attr('title')
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},
				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d =  data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if(d.num>0){
						 $("#productTotalPrice").text(d.product_total_price);
						  $("#totalPrice").text(d.total_price);
						   num_val.prev().val(d.num);
						}
						else{
							alert("数据修改异常");
						 }
					},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
});
});

</script>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="OrderServlet?method=shop_list&tag=1">返回</a>
            <div class="tit">结算中</div>
            
        </div>
    </div>
</header>
<form action="OrderServlet?method=shop_save" method="post" id="myform" name="myform">
<input type="hidden" id="token" name="token" value="${sessionScope.token}"/>
<div class="container ">
    <div class="row rowcar">
      <ul class="list-group">
		            <li class="list-group-item text-primary">
		            选择支付方式：<select name="payType" id="payType" class="select1">
		            <option value="1">转账汇款</option>
		            <option value="2">向上级付款</option>
		            </select>
		            <br>
		        </li><li class="list-group-item text-primary">
		            说明：你当前所选金额对应的级别为
		            </li></ul>
     <%if(plist!=null){
    		for(int i=0;i<plist.size();i++){
    		totalPrice=totalPrice+plist.get(i).getPrice()*plist.get(i).getNum();
    		%>
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
			                <div class="p-pic">
			                <img  src="../upload/product/<%=plist.get(i).getImageUrl() %>" alt="暂无图片" w="100px;" height="100px;"></div>
			                <div class="p-info">
			                   <p class="p-title"><%=plist.get(i).getProductName()%></p>
		                    	<p class="p-attr">
			                    		<span><%=plist.get(i).getSpecification()%></span></p>
		                    	<p class="p-origin">
			                        <em class="price">¥<%=plist.get(i).getPrice()%></em>
			                    </p>
			                </div>
		            </li>
		            <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary">购买数量：</span><%=plist.get(i).getNum()%>；<span class="gary">小计：</span>
		                	<em class="red productTotalPrice">¥<span id="productTotalPrice"><%=plist.get(i).getPrice()*plist.get(i).getNum()%></span></em>
		                </div>
					</li>
					</ul>
					<%}}%>
		       </div>
</div>
<div class="fixed-foot">
<div class="fixed_inner">
    <div class="pay-point">
       
         <p>合计：<em class="red f22">¥<span id="totalPrice"><%=totalPrice%></span></em></p>
    </div>
    <div class="buy-btn-fix">
        <a href="javascript:void(0)"  id="submitShopCart" class="btn btn-danger btn-buy">提交保存</a>
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


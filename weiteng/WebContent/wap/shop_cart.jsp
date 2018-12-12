<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.Product"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.ArithUtil"%>
<%@ page import="java.util.*"%>
<% 
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
double totalPrice =0;
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else if(StringUtil.notNull(user.getNickName()).equals("")){
	out.println("<script>");
	out.println("window.location.href='UserServlet?method=user_edit';");
	out.println("</script>");
}else{
%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
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
<title>商城购物</title>

</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="OrderServlet?method=shop_list&tag=1">返回</a>
            <div class="tit">购物车</div>
        <a class=" nav-right home-icon" href="UserServlet?method=index">首页</a>
            
        </div>
    </div>
</header>
<form action="OrderServlet?method=shop_confirm" method="post" id="myform" name="myform">
<input type="hidden" id="token" name="token" value="${sessionScope.token}"/>
<div class="container ">
    <div class="row rowcar">
   
     <%if(plist!=null){
    		for(int i=0;i<plist.size();i++){
    		totalPrice=totalPrice+plist.get(i).getPrice()*plist.get(i).getNum();
    		%>
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
			                <div class="p-pic">
			                <input id="tag_<%=plist.get(i).getId() %>" value="<%=plist.get(i).getId() %>" type="hidden"/>
			                <img  src="../upload/product/<%=plist.get(i).getImageUrl() %>" alt="暂无图片" w="100px;" height="100px;"></div>
			                <div class="p-info">
			                   <p class="p-title"><%=plist.get(i).getProductName()%></p>
		                    	<p class="p-attr">
			                    		<span>产品编号：<%=plist.get(i).getProductId()%>；规格：<%=plist.get(i).getSpecification()%></span></p>
		                    	<p class="p-origin">
			                        <em class="price">¥<%=plist.get(i).getPrice()%></em>
			                    </p>
			                </div>
		            </li>
					 <li class="list-group-item clearfix">
		                <div class="pull-left mt5">
		                	<span class="gary">小计：</span>
		                	<em class="red productTotalPrice" id="price_<%=plist.get(i).getId()%>" >¥<%=ArithUtil.mul(plist.get(i).getPrice(), plist.get(i).getNum())%></em>
		                </div>
						<div class="btn-group btn-group-sm control-num">
		                    <a onclick="disDe(<%=plist.get(i).getId() %>)" href="javascript:void(0);" class="btn btn-default"><span class="glyphicon glyphicon-minus gary"></span></a>
		                    <input type="tel" id="num_<%=plist.get(i).getId() %>" class="btn gary2 Amount" readonly="readonly" value="<%=plist.get(i).getNum() %>" maxlength="4" itemkey="" prodId="663" skuId="1358">
		                    <a onclick="increate(<%=plist.get(i).getId() %>)" href="javascript:void(0);" class="btn btn-default"><span class="glyphicon glyphicon-plus gary"></span></a>
		                </div>
					</li>
					</ul>
					<%}}%>
		       </div>
</div>
<div class="fixed-foot">
<div class="fixed_inner">
    <div class="pay-point">
       
         <p>合计：<em class="red f22">¥<span id="total_price"><%=totalPrice%></span></em></p>
    </div>
    <div class="buy-btn-fix">
        <a href="javascript:void(0)" onclick="submitShopCart();"  id="submitShopCart" class="btn-cart">去结算</a>
    </div>
</div>
</div>
</form>
<div class="clear"></div>

<script type="text/javascript">
function increate(index) {
	$.ajax({
					type : "post",
					url : "OrderServlet?method=increate_ajax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data : "tag="+index,
					dataType : "json",//设置需要返回的数据类型
					success : function(data) {
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						if(d.total_price!=null){
							$("#total_price").text(d.total_price);
							$("#num_"+index).attr("value",d.num);
							$("#price_"+index).text(d.price);
						}
					},
					error : function() {
					alert("系统异常，请稍后重试！");
					}//这里不要加","
				});
}

function disDe(index) {

	$.ajax({
					type : "post",
					url : "OrderServlet?method=sub_ajax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data : "tag="+ index,
					dataType : "json",//设置需要返回的数据类型
					success : function(data) {
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						if(d.total_price!=null){
							$("#total_price").text(d.total_price);
							$("#num_"+index).attr("value",d.num);
							$("#price_"+index).text(d.price);
						}
					},
					error : function() {
					alert("系统异常，请稍后重试！");
					}//这里不要加","
				});
}

function submitShopCart(){
	 $('#myform').submit();
		 return true;
}

</script>
</body></html>
<%} %>

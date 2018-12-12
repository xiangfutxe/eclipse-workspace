<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.ProductStock"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.ArithUtil"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
List<ProductStock> plist = (List<ProductStock>) request.getSession().getAttribute("stock_cart_list");
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
<title>我的云仓</title>
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
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">结算中</div>
            
        </div>
    </div>
</header>
<form action="OrderDeliveryServlet?method=stock_cart_save" method="post" id="myform" name="myform">
<input type="hidden" id="token" name="token" value="${sessionScope.token}"/>
<div class="container ">
    <div class="row rowcar">
      <ul class="list-group">
		            <li class="list-group-item text-black">
		            <a href="AddressServlet?method=list&tag=4">
		            <input type="hidden" name="adrId" value="${address.id}"/>
		              <c:if test="${address!=null}">
		              <p> <span  class="pull-left fontSize-14">${address.reciver}</span>
		            <span  class="pull-right fontSize-14">${address.phone}
		            </span>
		            </p>
		           
			         <br>
			         <p>
			        <span class="pull-left fontSize-14">收获地址：${address.province}${address.city}${address.area}${address.address}</span></p>
			              
		          </c:if>
		           <c:if test="${address==null}">
		            <p><span  class="pull-left fontSize-14">+请点击添加地址></span></p>
		           </c:if>
		            <br>
		            </a>
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
			                    		<span>产品编号：<%=plist.get(i).getProductId()%>；规格：<%=plist.get(i).getSpecification()%>；数量：；</span></p>
		                    	<p class="p-origin">
			                       单价：¥<%=plist.get(i).getPrice()%>；数量：<%=plist.get(i).getNum()%>；总计： <em class="price"><%=ArithUtil.mul(plist.get(i).getPrice(),plist.get(i).getNum())%></em>
			                    </p>
			                </div>
		            </li>
					</ul>
					<%}}%>
		  <ul class="list-group pt0">
			
		<li  class="list-group-item tip">
			
						<span class="left-label">配送方式</span>
				<span class="pull-right pr15 pt2" >包邮</span>
		</li>
			</ul>
		</div>
</div>
<div class="fixed-foot">
<div class="fixed_inner">
    <div class="pay-point">
         <p>总计：<em class="red f18">¥<span id="totalPrice"><%=totalPrice%></span></em></p>
    </div>
    <div class="buy-btn-fix">
        <a href="javascript:void(0)"  id="submitShopCart" class="btn-cart">提交订单</a>
    </div>
</div>
</div>
</form>
<div class="clear"></div>
 <!--Beigin Cart Beigin-->
    	<div class="cart_ceng">
        	 <ul class="list-group">
        	 	<li  class="list-group-item">
						<span class="left-label gary f16">支付方式</span>
				</li>
			<li  class="list-group-item">
						<span class="left-label gary">转账汇款</span>
				<span class="gary pull-right pr15 pt2"><input type="radio" name="zhifu" value="1" checked="checked"/></span>
			</li>
			<li  class="list-group-item">
						<span class="left-label gary">向上级支付</span>
				<span class="gary pull-right pr15 pt2"><input type="radio" name="zhifu" value="2"/></span>
			</li>
			<li  class="list-group-item">
						<span class="left-label gary">微信支付</span>
				<span class="gary pull-right pr15 pt2"><input type="radio" name="zhifu" value="3"/></span>
			</li>
			</ul>
            <div class="bnt">
             <button type="submit" class="btn_t" id="t1">关闭</button>
             <button type="submit" class="btn_t" id="t2">确&nbsp;&nbsp;认</button>
            </div>
        </div>
        <div class="cover-decision"></div>
    <!--End Cart End-->
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

<%}%>
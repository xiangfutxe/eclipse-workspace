<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.Product"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.ArithUtil"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cash_cart_list");
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
	double cash = 0;
	double cashNum=0;
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
<title>特卖商城</title>

</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="OrderServlet?method=shop_cash_list&tag=1">返回</a>
            <div class="tit">结算中</div>
          <a class=" nav-right home-icon" href="UserServlet?method=index">首页</a>
            
        </div>
    </div>
</header>
<form action="OrderServlet?method=shop_cash_save" method="post" id="myform" name="myform">
<input type="hidden" id="token" name="token" value="${sessionScope.token}"/>
<div class="container ">
    <div class="row rowcar">
      <ul class="list-group">
		            <li class="list-group-item text-black">
		            <a href="AddressServlet?method=list&tag=2">
		            <input type="hidden" name="adrId" id="adrId" value="${address.id}"/>
		              <c:if test="${address!=null}">
		              <p> <span  class="pull-left font-16">${address.reciver}</span>
		            <span  class="pull-right font-16">${address.phone}
		            </span>
		            </p>
		           
			         <br>
			         <p>
			                 <span class="pull-left">收货地址：${address.province}${address.city}${address.area}${address.address}</span></p>
			              
		          </c:if>
		           <c:if test="${address==null}">
		            <p><span  class="font-18">+请点击添加地址></span></p>
		           </c:if>
		            <br>
		            </a>
		        </li></ul>
     <%if(plist!=null){
    		for(int i=0;i<plist.size();i++){
    			System.out.println("productPrice:"+plist.get(i).getProductPrice());
    		totalPrice=ArithUtil.add(totalPrice,ArithUtil.mul(plist.get(i).getProductPrice(),plist.get(i).getNum()));
    		cashNum+=plist.get(i).getCashNum()*plist.get(i).getNum();
    		cash=ArithUtil.add(cash,ArithUtil.mul(plist.get(i).getCash(),plist.get(i).getNum()));
    		%>
        <ul class="list-group">
		            <li class="list-group-item hproduct clearfix">
			                <div class="p-pic">
			                <img  src="../upload/product/<%=plist.get(i).getImageUrl() %>" alt="暂无图片" w="100px;" height="100px;"></div>
			                <div class="p-info">
			                   <p class="p-title"><%=plist.get(i).getProductName()%></p>
		                    	<p class="p-attr">
			                    		<span>产品编号：<%=plist.get(i).getProductId()%>；规格：<%=plist.get(i).getSpecification()%>；</span></p>
		                    	<p class="p-origin">
			                       单价：¥<%=plist.get(i).getPrice()%>；数量：<%=plist.get(i).getNum()%>；</em>
			                    </p>
			                </div>
		            </li>
					</ul>
					<%}}%>
		  <ul class="list-group pt0">
			<li  class="list-group-item tip">
						<span class="left-detail">支付方式</span>
				<span class="pull-right pr15 pt2" id="zhifu">在线支付</span>
				<input type="hidden" id="payType" name="payType" value="3"/>
			</li>
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
         <p>总计:<em class="red f16">¥<span id="totalPrice"><%=totalPrice%></span></em>;特卖权数:<em class="red f16"><%=cashNum%>;</em>
        代金券:<em class="red f16"><span id="cash"><%=cash%></span></em></p>
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
						<span class="left-label gary">在线支付</span>
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
	
	$('input[name="zhifu"]').click(function(){
		if($(this).val()=="1")
			$("#zhifu").text("转账汇款");
		else if($(this).val()=="2")
			$("#zhifu").text("向上级支付");
		else if($(this).val()=="3")
			$("#zhifu").text("在线支付");
		});

	$("#zhifu").click(function(){
		  $(".cart_ceng").slideToggle();
		  $(".cover-decision").show();
		  $("#t2").hide();
		  $("#t1").show();
		});
	
	$("#t1").click(function(){
		  $(".cart_ceng").slideToggle();
		  $(".cover-decision").hide();
		 
		});
	$("#submitShopCart").click(function() {
		if (!confirm("确认进行结算？")) {
			return false;
		} else {
			if($("#adrId").val()==""){
				alert("请选择收货地址。");
				return false;
			}
			else{
			 $('#myform').submit();
       		 return true;
			}
		}
	});
});
</script>
</body></html>

<%}%>
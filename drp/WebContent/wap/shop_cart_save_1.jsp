<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Product"%>
<%@ page import="com.pojo.Address"%>
<%@ page import="com.pojo.Branch"%>
<%@ page import="java.util.*"%>
<% 
List<Product> slist = (List<Product>) request.getSession().getAttribute("splist_1");
Address adr = (Address) request.getSession().getAttribute("sns_adr");
double totalPrice =0;
Branch branch = (Branch)request.getSession().getAttribute("sys_branch");
if(branch==null){
out.println("<script>");
out.println("window.location.href='login.jsp';");
out.println("</script>");
}else{
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
<title>换货结算</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">换货结算</div>
        </div>
    </div>
</header>
<form action="OrderServlet?method=shop_save" method="post" id="myform" name="myform">
<div class="container ">
    <div class="row rowcar">
       <ul class="list-group">
		            <li class="list-group-item text-primary">
		            <div class="icheck pull-left mr5">
                                       <label class="checkLabel">
		                       <span></span>
                               </label>
		            </div>
		            	收货地址</li>
		            <li class="list-group-item hproduct clearfix">
		            <%if(adr!=null){%>
		            <input type="hidden" name="adr_id" id="adr_id" value="<%=adr.getId()%>"/>
		             <p> <span  class="pull-left font-14"> <%=adr.getReceiver()%></span>
		           
		            <span  class="pull-right font-14"><%=adr.getPhone()%>
		            </span>
		            </p>
			         <br>
			         <p>
			                 <span class="pull-left"> <%=adr.getProvince()%> <%=adr.getCity()%> <%=adr.getArea()%> <%=adr.getAdr()%></span></p>
			                 <%}%>
  </li>
  </ul>
        <ul class="list-group">
		            <li class="list-group-item text-primary">
		            <div class="icheck pull-left mr5">
                                       <label class="checkLabel">
		                       <span></span>
                               </label>
		            </div>
		            	产品清单</li>
		            	   <%if(slist!=null){
    		for(int i=0;i<slist.size();i++){
    		totalPrice=totalPrice+slist.get(i).getPrice()*slist.get(i).getNum();
    		%>
		            <li class="list-group-item hproduct clearfix">
			                <div class="p-pic">
			                <img class="img-responsive" src="../upload/<%=slist.get(i).getImageUrl() %>"></div>
			                <div class="p-info">
			                    <p class="p-title"><%=slist.get(i).getProductName()%></p>
		                    	<p class="p-attr">
			                    		<span>数量:<%=slist.get(i).getNum()%></span></p>
		                    	<p class="p-origin">
			                        <em class="price">¥<%=slist.get(i).getPrice()*slist.get(i).getNum()%></em>
			                    </p>
			                </div>
		            </li>
					<%}}%>
					</ul>
					
					
		       </div>
</div>
<div class="fixed-foot">
<div class="fixed_inner">
    <div class="pay-point">
        <div class="icheck pull-left mr10">
           
            <label for="buy-sele-all">
               &nbsp;
            </label>
        </div>
         <p>合计：<em class="red f22">¥<span id="totalPrice"><%=totalPrice%></span></em>(换货购物不扣费用)</p>
    </div>
    <div class="buy-btn-fix">
        <a href="javascript:void(0)"  id="submitShopCart" class="btn btn-danger btn-buy">提交订单</a>
    </div>
</div>
</div>
</form>
<div class="clear"></div>
<script type="text/javascript">

$(document).ready(function(){  
	$("#submitShopCart").click(function() {
		if (!confirm("确认提交订单进行结算，提交订单将会扣除你的电子币？")) {
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
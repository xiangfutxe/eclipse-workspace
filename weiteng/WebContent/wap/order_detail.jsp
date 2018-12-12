<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Order"%>
<%@ page import="com.ssm.pojo.User"%>
<%@page import="com.ssm.utils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.Pager" %>
<%@ page import="com.ssm.pojo.OrderDetail"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.DateUtil"%>
<% 
int atag=3;
double totalPrice =0;
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
Order p = (Order)request.getAttribute("orders");
List<OrderDetail> slist = (List<OrderDetail>)request.getAttribute("coll");
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user){
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
<title>订单详情</title>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div> <header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">订单详情</div>
        </div>
    </div>
</header>
<div class="container ">
    <div class="row rowcar">
       <ul class="list-group">
		            <li class="list-group-item text-primary">
		            <div class="icheck pull-left mr5">
                                       <label class="checkLabel">
		                       <span></span>
                               </label>
		            </div>
		            	订单信息</li>
		           <li class="list-group-item hproduct clearfix">
					 <p> <span  class="pull-left font-14"><%=p.getOrderId() %></span>
		            <span  class="pull-right font-14"><%=StringUtil.parseToString(p.getOrderTime(), DateUtil.yyyyMMddHHmmss)%>
		            </span>
		            </p>
			         <br>
					<p>
					        <span  class="pull-right font-14">
					         <%if(p.getOrderType()==1){%>商城购物
					        <%}else if(p.getOrderType()==2){ %>特卖购物
					        <%}else if(p.getOrderType()==3){ %>积分购物
					        <%} %>
					        /
					        <%if(p.getState()==1){%>待支付
					        <%}else if(p.getState()==2){ %>已收款
					           <%}else if(p.getState()==0){ %>已退单
					        <%}else{ %>-<%} %></span>
					       </p>
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
			                <div>
			                   <p class="p-title">
			                   <a href="ProductServlet?method=product_detail&id=<%=slist.get(i).getProductId()%>">
			                   <%=i+1%>、<%=slist.get(i).getProductName()%>
			                   </a>
			                   </p>
		                    	<p class="p-attr">
			                    		<span>规格:<%=StringUtil.notNull(slist.get(i).getSpecification())%>；数量:<%=StringUtil.decimalFormat1(slist.get(i).getNum())%>；
			                    		单价:<%=StringUtil.decimalFormat(slist.get(i).getProductPrice())%>；</span>
			                        <em class="price">小计：¥<%=slist.get(i).getPrice()%></em>
			                    </p>
			                </div>
		            </li>
					<%}
					%>
					    <li class="list-group-item hproduct clearfix">
			                <div>
			                <p class="p-attr">
			                    	
			                        <em class="price">合计：¥<%=p.getPrice()%></em>
			                    </p>
			                </div>
			                </li>
					<%}%>
					</ul>
					
					
		       </div>
</div>
<div class="clear"></div>
<%@include file ="footer.jsp" %>

</body></html>
<%}%>